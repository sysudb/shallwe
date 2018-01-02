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
	private int activityId;
	public String slogan;
	public String sportType;
	public float money;
	public boolean costType;// aa?
	public int joinPeople, totalPeople;
	public Stadium stadium;//这里面包含了sportType信息
	public TimeSlot timeslot;//这里面包含了详细的时间信息
	public String location;
	

	// 以下是详细信息，用于显示在sportsInvitationDetail.jsp页面上
	public String ownerWechatName, ownerIcon, participantWechatname[], participantIcon[];
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
			int			totalPeople,
			String 		location
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
		this.location = location;
	}
	
	public int getId(){
		return this.activityId;
	}
	
	public void getDetails() {
		//创建数据库连接
		Connection conn = Database.connect();
		
        //执行查询
        Statement stmt = Database.initSatement(conn);
        
		String sql = ""
				+ "SELECT wechat_name, icon_url "
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
					this.ownerIcon = rs.getString("icon_url");
				} catch (SQLException e) {
					e.printStackTrace();
					new ErrorRecord(e.toString());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			new ErrorRecord(e.toString());
		}


		sql = "SELECT wechat_name, icon_url "
				+ "FROM user "
				+ "where wechat_id in ("
					+ "SELECT wechat_id "
					+ "FROM participate "
					+ "WHERE activity_id = " + this.activityId + ")";
        rs = Database.require(stmt, sql);
        
        this.participantWechatname = new String[this.joinPeople];
        this.participantIcon = new String[this.joinPeople];
        int i = 0;
        //提取查询结果
        try {
			while(rs.next()){
			    // 通过字段检索
			    try {
					this.participantWechatname[i] = rs.getString("wechat_name");
					this.participantIcon[i] = rs.getString("icon_url");
					i++;
				} catch (SQLException e) {
					e.printStackTrace();
					new ErrorRecord(e.toString());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			new ErrorRecord(e.toString());
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
		
		String sql = "SELECT wechat_id FROM participate where activity_id = " + this.activityId + " and wechat_id = '" + newParticipant.openid + "'";
		ResultSet rs = Database.require(stmt, sql);
		if(Database.getResultSize(rs) > 0) return "joined";
		
		sql = "INSERT INTO participate (wechat_id, activity_id) "
				+ "VALUES("
					+ "'" + newParticipant.openid + "',"
					+ this.activityId
				+ ")";
		if(Database.execute(stmt, sql)) {
			this.joinPeople += 1;
			newParticipant.getSportInvitationList(false);
			stat = "success";
		}
		else stat = "join invitation failed";
		Database.closeStatement(stmt);
        
        //关闭连接   !!!一定要关闭，释放资源
        Database.disconect(conn);
		return stat;
	}
	
	public static String makeInvitation(String slogan, double money, boolean aa, String sportType, int totalPeople, Stadium stadium, TimeSlot timeslot, String openid, String location) {
		/* TODO 新建一个邀请
		 * 前端调用方法为
		 * new SportInvitation().makeInvitation(......)
		 * 新建成功则返回success，失败返回错误原因，用于显示给用户
		 */

        String stat;

        if(totalPeople <= 0) return "wrong maximum of people";
        
		//创建数据库连接
		Connection conn = Database.connect();
		
        //执行查询
		Statement stmt = Database.initSatement(conn);
		String sql;
		if(stadium!=null) {
			sql = "INSERT INTO activities (slogan, cost, pay_type, sport_type, max_participant, stadium_id, start_time, end_time, creator_id, location) "
					+ "VALUES("
						+ "'" + slogan + "',"
						+ money + ","
						+ aa + ","
						+ "'" + sportType + "',"
						+ totalPeople + ","
						+ stadium.getId() + ","
						+ "'" + timeslot.startTime.toString().split("\\.")[0] + "',"
						+ "'" + timeslot.endTime.toString().split("\\.")[0] + "',"
						+ "'" + openid + "',"
						+ "'" + location + "'"
					+ ")";
		}
		else {
			sql = "INSERT INTO activities (slogan, cost, pay_type, sport_type, max_participant, stadium_id, start_time, end_time, creator_id, location) "
					+ "VALUES("
						+ "'" + slogan + "',"
						+ money + ","
						+ aa + ","
						+ "'" + sportType + "',"
						+ totalPeople + ","
						+ "null" + ","
						+ "'" + timeslot.startTime.toString().split("\\.")[0] + "',"
						+ "'" + timeslot.endTime.toString().split("\\.")[0] + "',"
						+ "'" + openid + "',"
						+ "'" + location + "'"
					+ ")";
		}
		if(Database.execute(stmt, sql)) stat = "success";
		else stat = "create invitation failed";
		Database.closeStatement(stmt);
		
        //关闭连接   !!!一定要关闭，释放资源
        Database.disconect(conn);
		return stat;//仅为了消灭没有返回值的错误提示
	}
	
	public static void main(String[] args) {
		Stadium stadium = new Stadium(100001);
		Timestamp start_time = new Timestamp(System.currentTimeMillis());
		Timestamp end_time = new Timestamp(System.currentTimeMillis());
		TimeSlot timeslot = new TimeSlot(start_time, end_time);
		String stat = SportInvitation.makeInvitation("再来一瓶", 100, true,"swimming", 10, null, timeslot, "0001","呵呵");
		System.out.println(stat);
	}
}
