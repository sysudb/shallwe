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
	// �����Ǵ�����Ϣ��������ʾ��sportsInvitationList.jspҳ����
	private int activityId;
	public String sportType;
	public String slogan;
	public TimeSlot timeslot;//�������������ϸ��ʱ����Ϣ
	public String location;
	public int payType; //1:���  2: �����  3: AA
	public int joinPeople, totalPeople;
	public int sexneed; //1: ����   2: ֻ����   3��ֻ��Ů
	public String discription;  //�˶���������

	// ��������ϸ��Ϣ��������ʾ��sportsInvitationDetail.jspҳ����
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
		// ���캯��
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
		//�������ݿ�����
		Connection conn = Database.connect();
		
        //ִ�в�ѯ
        Statement stmt = Database.initSatement(conn);
        
		String sql = ""
				+ "SELECT wechat_name, icon_url "
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
        //��ȡ��ѯ���
        try {
			while(rs.next()){
			    // ͨ���ֶμ���
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
        
        //�ر�����   !!!һ��Ҫ�رգ��ͷ���Դ
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
		
        //�ر�����   !!!һ��Ҫ�رգ��ͷ���Դ
        Database.disconect(conn);
		return stat;//��Ϊ������û�з���ֵ�Ĵ�����ʾ
	}
	
	public static void main(String[] args) {
		Timestamp start_time = new Timestamp(System.currentTimeMillis());
		Timestamp end_time = new Timestamp(System.currentTimeMillis());
		TimeSlot timeslot = new TimeSlot(start_time, end_time);
		String stat = SportInvitation2.makeInvitation2(1056, "tungkimwa", "basketball", "YaHoo!!!", timeslot, "��ɽ��ѧ��У���ٳ�", 1, 11, 1, "��������");
		System.out.println(stat);
	}
}

