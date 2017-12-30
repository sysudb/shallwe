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
	// �����Ǵ�����Ϣ��������ʾ��sportsInvitationList.jspҳ����
	public int activityId;
	public String slogan;
	public String sportType;
	public float money;
	public boolean costType;// aa?
	public int joinPeople, totalPeople;
	public Stadium stadium;//�����������sportType��Ϣ
	public TimeSlot timeslot;//�������������ϸ��ʱ����Ϣ
	// ��������ϸ��Ϣ��������ʾ��sportsInvitationDetail.jspҳ����
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
		// ���캯��
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
		//�������ݿ�����
		Connection conn = Database.connect();
		
        //ִ�в�ѯ
        Statement stmt = Database.initSatement(conn);
        
		String sql = ""
				+ "SELECT wechat_name "
				+ "FROM user "
				+ "WHERE wechat_id = ( "
					+ "SELECT creator_id "
					+ "FROM activities "
					+ "WHERE activity_id = " + this.activityId + ")";
        ResultSet rs = Database.require(stmt, sql);
        
        //��ȡ��ѯ���
        try {
			while(rs.next()){
			    // ͨ���ֶμ���
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
        //��ȡ��ѯ���
        try {
			while(rs.next()){
			    // ͨ���ֶμ���
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
        
        //�ر�����   !!!һ��Ҫ�رգ��ͷ���Դ
		Database.closeStatement(stmt);
        Database.disconect(conn);
		return;
	}
	
	public String joinInvitation(User newParticipant) {
		// �����˶�������sportInvitationDetail.jsp����ת��ִ��
		
        String stat;
        
		//�������ݿ�����
		Connection conn = Database.connect();
		
        //ִ�в�ѯ
		Statement stmt = Database.initSatement(conn);
		
		String sql = "INSERT INTO participate (wechat_id, activity_id) "
				+ "VALUES("
					+ "'" + newParticipant.openid + "',"
					+ this.activityId
				+ ")";
		if(Database.execute(stmt, sql)) stat = "success";
		else stat = "create invitaion failed";
		Database.closeStatement(stmt);
        
        //�ر�����   !!!һ��Ҫ�رգ��ͷ���Դ
        Database.disconect(conn);
		return stat;
	}
	
	public static String makeInvitation(String slogan, double money, boolean aa, String sportType, int totalPeople, Stadium stadium, TimeSlot timeslot, String openid) {
		/* TODO �½�һ������
		 * ǰ�˵��÷���Ϊ
		 * new SportInvitation().makeInvitation(......)
		 * �½��ɹ��򷵻�success��ʧ�ܷ��ش���ԭ��������ʾ���û�
		 */

        String stat;
        
		//�������ݿ�����
		Connection conn = Database.connect();
		
        //ִ�в�ѯ
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
		
        //�ر�����   !!!һ��Ҫ�رգ��ͷ���Դ
        Database.disconect(conn);
		return stat;//��Ϊ������û�з���ֵ�Ĵ�����ʾ
	}
	
	public static SportInvitation[] getSportInvitationList(boolean my, int count) {
		SportInvitation[] temp = new SportInvitation[count];
		
		//�������ݿ�����
		Connection conn = Database.connect();
		
        //ִ�в�ѯ
        Statement stmt = Database.initSatement(conn);
        
		String sql = "SELECT * FROM activities NATURAL JOIN stadium";
        ResultSet rs = Database.require(stmt, sql);
        
        //��ȡ��ѯ���
        try {
        	int i = 0;
			while(rs.next() && i<count){
			    // ͨ���ֶμ���
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
		
        //�ر�����   !!!һ��Ҫ�رգ��ͷ���Դ
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
		String stat = SportInvitation.makeInvitation("what the ������", 100, true,"boxing", 99, stadium, timeslot, "emmmm");
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