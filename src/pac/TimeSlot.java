package pac;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

public class TimeSlot {
	
	public int id;
	public Timestamp startTime, endTime;
	public boolean status;
//	public String year, month, day, week;
//	public int startHour, endHour, startMinute, endMinute;
//	boolean available;//是否有人预定
	
	public TimeSlot(int id, Timestamp startTime, Timestamp endTime, boolean status) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
	}

	public String book(User bookPeople) {
        String stat;
        
		//创建数据库连接
		Connection conn = Database.connect();
		
        //执行查询
		Statement stmt = Database.initSatement(conn);
		
		String sql = "SELECT * FROM time_slot where timeslot_id = " + this.id + " and status = " + 0;
		ResultSet rs = Database.require(stmt, sql);
		if(Database.getResultSize(rs) == 0) return "booked";
		
		sql = "INSERT book (wechat_id, timeslot_id) VALUES('" + bookPeople.openid + "', " + this.id + ")";
		if(Database.execute(stmt, sql)) {
			this.status = true;
			stat = "success";
		}
		else stat = "join invitation failed";
		Database.closeStatement(stmt);
        
        //关闭连接   !!!一定要关闭，释放资源
        Database.disconect(conn);
		return stat;
	}
	
	public static void main(String[] args) {
	}

}
