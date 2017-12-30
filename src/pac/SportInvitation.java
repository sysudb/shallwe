package pac;
import pac.TimeSlot;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
//import java.sql.PreparedStatement;

import pac.Stadium;

public class SportInvitation {
	// 以下是粗略信息，用于显示在sportsInvitationList.jsp页面上
	public int activityId;
	public String slogan;
	public String sportType;
	public float money;
	public boolean costType;// aa?
	public int joinPeople, totalPeople;
	public Stadium stadium;//这里面包含了sportType信息
	public TimeSlot timeslot;//这里面包含了详细的时间信息
	// 以下是详细信息，用于显示在sportsInvitationDetail.jsp页面上
	public String ownerWechatName, participantWechatname[];
	//public String court;
	
	public SportInvitation(
			int 		activityId,
			String 		slogan,
			String 		sportType,
			float		money,
			boolean		costType,
			Stadium		stadium,
			TimeSlot	timeslot,
			int			joinPeople,
			int			totalPeople
			) {
		// 构造函数
		this.activityId = activityId;
		this.slogan = slogan;
		this.sportType = sportType;
		this.money = money;
		this.costType = costType;
		this.stadium = stadium;
		this.timeslot = timeslot;
		this.joinPeople = joinPeople;
		this.totalPeople = totalPeople;
	}
	
	public void getDetails() {
		//创建数据库连接
		Connection conn = Database.connect();
		
        //执行查询
        Statement stmt = Database.initSatement(conn);
        
		String sql = ""
				+ "SELECT wechat_name "
				+ "FROM user "
				+ "WHERE wechat_id = ( "
					+ "SELECT creator_id "
					+ "FROM activities "
					+ "WHERE activity_id = " + this.activityId + ")";
        ResultSet rs = Database.require(stmt, sql);
        
        //提取查询结果
        try {
			while(rs.next()){
			    // 通过字段检索
			    try {
					this.ownerWechatName = rs.getString("wechat_name");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		sql = "SELECT wechat_name "
				+ "FROM user "
				+ "where wechat_id in ("
					+ "SELECT wechat_id "
					+ "FROM participate "
					+ "WHERE activity_id = " + this.activityId + ")";
        rs = Database.require(stmt, sql);
        
        this.participantWechatname = new String[this.joinPeople];
        int i = 0;
        //提取查询结果
        try {
			while(rs.next()){
			    // 通过字段检索
			    try {
					this.participantWechatname[i] = rs.getString("wechat_name");
					i++;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        //关闭连接   !!!一定要关闭，释放资源
		Database.closeStatement(stmt);
        Database.disconect(conn);
		return;
	}
	
	public String joinInvitation(User newParticipant) {
		// 加入运动，用于sportInvitationDetail.jsp的跳转后执行
		
        String stat;
        
		//创建数据库连接
		Connection conn = Database.connect();
		
        //执行查询
		Statement stmt = Database.initSatement(conn);
		
		String sql = "INSERT INTO participate (wechat_id, activity_id) "
				+ "VALUES("
					+ "'" + newParticipant.openid + "',"
					+ this.activityId
				+ ")";
		if(Database.execute(stmt, sql)) stat = "success";
		else stat = "create invitaion failed";
		Database.closeStatement(stmt);
        
        //关闭连接   !!!一定要关闭，释放资源
        Database.disconect(conn);
		return stat;
	}
	
	public static String makeInvitation(String slogan, double money, boolean aa, String sportType, int totalPeople, Stadium stadium, TimeSlot timeslot, String openid) {
		/* TODO 新建一个邀请
		 * 前端调用方法为
		 * new SportInvitation().makeInvitation(......)
		 * 新建成功则返回success，失败返回错误原因，用于显示给用户
		 */

        String stat;
        
		//创建数据库连接
		Connection conn = Database.connect();
		
        //执行查询
		Statement stmt = Database.initSatement(conn);
		
		String sql = "INSERT INTO activities (slogan, cost, pay_type, sport_type, max_participant, participant, stadium_id, start_time, end_time, creator_id) "
				+ "VALUES("
					+ "'" + slogan + "',"
					+ money + ","
					+ aa + ","
					+ "'" + sportType + "',"
					+ totalPeople + ","
					+ 0 + ","
					+ stadium.getId() + ","
					+ "'" + timeslot.startTime.toString().split("\\.")[0] + "',"
					+ "'" + timeslot.endTime.toString().split("\\.")[0] + "',"
					+ "'" + openid + "'"
				+ ")";
		if(Database.execute(stmt, sql)) stat = "success";
		else stat = "create invitaion failed";
		Database.closeStatement(stmt);
		
        //关闭连接   !!!一定要关闭，释放资源
        Database.disconect(conn);
		return stat;//仅为了消灭没有返回值的错误提示
	}
	
	public static SportInvitation[] getSportInvitationList(boolean my, int count) {
		SportInvitation[] temp = new SportInvitation[count];
		
		//创建数据库连接
		Connection conn = Database.connect();
		
        //执行查询
        Statement stmt = Database.initSatement(conn);
        
		String sql = "SELECT * FROM activities NATURAL JOIN stadium";
        ResultSet rs = Database.require(stmt, sql);
        
        //提取查询结果
        try {
        	int i = 0;
			while(rs.next() && i<count){
			    // 通过字段检索
			    try {
			    	temp[i] = new SportInvitation(
						rs.getInt("activity_id"),
						rs.getString("slogan"),
						rs.getString("sport_type"),
						rs.getFloat("cost"),
						rs.getBoolean("pay_type"),
						new Stadium(rs.getInt("stadium_id"),rs.getString("stadium_name"),rs.getString("location")),
						new TimeSlot(rs.getTimestamp("start_time"),rs.getTimestamp("end_time")),
						rs.getInt("participant"),
						rs.getInt("max_participant"));
			    		i++;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
        //关闭连接   !!!一定要关闭，释放资源
		Database.closeStatement(stmt);
        Database.disconect(conn);
        
		return temp;
	}

	public static SportInvitation[] getSportInvitationList(boolean my) {
		return getSportInvitationList(my, 10);
	}
	
	public static void main(String[] args) {
		Stadium stadium = new Stadium(100001);
		Timestamp start_time = new Timestamp(System.currentTimeMillis());
		Timestamp end_time = new Timestamp(System.currentTimeMillis());
		TimeSlot timeslot = new TimeSlot(start_time, end_time);
		String stat = SportInvitation.makeInvitation("what the 。。。", 100, true,"boxing", 99, stadium, timeslot, "emmmm");
		System.out.println(stat);
		if(stat == "success") {
			int count = 10;
			SportInvitation[] temp = SportInvitation.getSportInvitationList(false, count);
			for(int i = 0 ; i < count;i++) {
				if(temp[i]!=null) {
					System.out.println(temp[i].activityId + temp[i].money + temp[i].slogan + temp[i].sportType + temp[i].joinPeople + temp[i].totalPeople);
					temp[i].getDetails();
					System.out.println("creator: " + temp[i].ownerWechatName);
					for(int j = 0 ; j <temp[i].joinPeople;j++) {
						System.out.println(temp[i].participantWechatname[j]);
					}
					if(temp[i].ownerWechatName.equals("lj")) {
						stat = temp[i].joinInvitation(new User());
						System.out.println(stat);
					}
				}
			}
		}
	}
}
