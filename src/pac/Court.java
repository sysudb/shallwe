package pac;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pac.TimeSlot;

public class Court {
	
	// �����Ǵ�����Ϣ������sportInvitationDetail.jspҳ��
	public String name;
	@SuppressWarnings("unused")
	private int id;
	// ��������ϸ��Ϣ������selectCourtTime.jspҳ��
	public TimeSlot timeSlotList[];
	public int timeSlotListLen;
	
	public Court(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getTimeSlotListLen() {
		// TODO ��ȡʱ������
		return 0;//��Ϊ������û�з���ֵ�Ĵ�����ʾ
	}
	
	public void getTimeSlotList(int i_thDay) {
		// TODO ��ȡi_thDay����ʱ�������i_thDay==0��ʾ����
		
		//�������ݿ�����
		Connection conn = Database.connect();
		
        //ִ�в�ѯ
        Statement stmt = Database.initSatement(conn);
        
		String sql = "SELECT count(*) as timeslot_count FROM time_slot WHERE court_id = " + this.id;
        ResultSet rs = Database.require(stmt, sql);

        //��ȡ��ѯ���
        try {
			while(rs.next()){
			    // ͨ���ֶμ���
			    try {
			    	this.timeSlotListLen = rs.getInt("timeslot_count");
				} catch (SQLException e) {
					this.timeSlotListLen = 0;
					e.printStackTrace();
					return;
				}
			}
		} catch (SQLException e) {
	        Database.disconect(conn);
			e.printStackTrace();
			return;
		}
        
		sql = "SELECT from_time, to_time FROM time_slot WHERE court_id = " + this.id;
        rs = Database.require(stmt, sql);
        //��ȡ��ѯ���
        this.timeSlotList = new TimeSlot[this.timeSlotListLen];
        try {
        	int i = 0;
			while(rs.next() && i < this.timeSlotListLen){
			    // ͨ���ֶμ���
			    try {
			    	this.timeSlotList[i] = new TimeSlot(rs.getTimestamp("from_time"), rs.getTimestamp("to_time"));
			    	i++;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
        //�ر�����   !!!һ��Ҫ�رգ��ͷ���Դ
		Database.closeStatement(stmt);
        Database.disconect(conn);
	}

	public static void main(String[] args) {
		// ���Գ���
		Court court = new Court(1, "court1");
		court.getTimeSlotList(3);
		System.out.println(court.timeSlotListLen);
		for(int i = 0 ; i < court.timeSlotListLen;i++) {
			System.out.println(court.timeSlotList[i].startTime.toString() + "    " + court.timeSlotList[i].endTime.toString());
		}
	}

}
