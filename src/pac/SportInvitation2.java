package pac;
import pac.TimeSlot;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
//import java.sql.PreparedStatement;

import pac.Stadium;

public class SportInvitation2 {
	// 以下是粗略信息，用于显示在sportsInvitationList.jsp页面上
	private int activityId;
	public String sportType;
	public String slogan;
	public TimeSlot timeslot;//这里面包含了详细的时间信息
	public String location;
	public int payType; //1:免费  2: 我请客  3: AA
	public int joinPeople, totalPeople;
	public int sexneed; //1: 不限   2: 只限男   3：只限女
	public String discription;  //运动具体描述

	// 以下是详细信息，用于显示在sportsInvitationDetail.jsp页面上
	public String ownerWechatName, ownerIcon, participantWechatname[], participantIcon[];
	//public String court;
	
	public SportInvitation2(
			int 		activityId,
			String 		sportType,
			String 		slogan,
			TimeSlot	timeslot,
			String 		location,
			int		    payType,
			int			joinPeople,
			int			totalPeople,
			int			sexneed,
			String 		discription
			) {
		// 构造函数
		this.activityId = activityId;
		this.slogan = slogan;
		this.sportType = sportType;
		this.payType = payType;
		this.timeslot = timeslot;
		this.joinPeople = joinPeople;
		this.totalPeople = totalPeople;
		this.sexneed = sexneed;
		this.discription = discription;
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
			stat = "success";
		}
		else stat = "join invitation failed";
		Database.closeStatement(stmt);
        
        //关闭连接   !!!一定要关闭，释放资源
        Database.disconect(conn);
		return stat;
	}
	
	public static String makeInvitation2(
			int 		activityId,
			String		openId,
			String 		sportType,
			String 		slogan,
			TimeSlot	timeslot,
			String 		location,
			int		    payType,
			int			totalPeople,
			int			sexneed,
			String 		discription) {
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
		
		String sql = "INSERT INTO sport_activities (activity_id, creator_id, sport_type, slogan, start_time, end_time, location, pay_type, max_participant, sexneed, discription) "
				+ "VALUES("
					+ activityId + ",'" + openId + "'," + "'" + sportType + "',"
					+ "'" + slogan + "',"
					+ "'" + timeslot.startTime.toString().split("\\.")[0] + "',"
					+ "'" + timeslot.endTime.toString().split("\\.")[0] + "',"
					+ "'" + location + "',"
					+ payType + ","
					+ totalPeople + ","
					+ sexneed + ","
					+ "'" + discription + "'"
				+ ")";
		if(Database.execute(stmt, sql)) stat = "success";
		else stat = "create invitation failed";
		Database.closeStatement(stmt);
		
        //关闭连接   !!!一定要关闭，释放资源
        Database.disconect(conn);
		return stat;//仅为了消灭没有返回值的错误提示
	}
	
	public static void main(String[] args) {
		Timestamp start_time = new Timestamp(System.currentTimeMillis());
		Timestamp end_time = new Timestamp(System.currentTimeMillis());
		TimeSlot timeslot = new TimeSlot(start_time, end_time);
		String stat = SportInvitation2.makeInvitation2(1056, "tungkimwa", "basketball", "YaHoo!!!", timeslot, "中山大学东校区操场", 1, 11, 1, "功夫篮球");
		System.out.println(stat);
	}
}

