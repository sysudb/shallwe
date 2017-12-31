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
//	boolean available;//�Ƿ�����Ԥ��
	
	public TimeSlot(int id, Timestamp startTime, Timestamp endTime, boolean status) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
	}

	public String book(User bookPeople) {
        String stat;
        
		//�������ݿ�����
		Connection conn = Database.connect();
		
        //ִ�в�ѯ
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
        
        //�ر�����   !!!һ��Ҫ�رգ��ͷ���Դ
        Database.disconect(conn);
		return stat;
	}
	
	public static void main(String[] args) {
	}

}
