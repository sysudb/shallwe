package pac;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Stadium {
	// �����Ǵ�����Ϣ
	public String name, location;
	@SuppressWarnings("unused")
	private int id;
	// ��������ϸ��Ϣ
	int availableDay;//�ӽ��������м����ǿ���Ԥ���ģ�0��Ϊֻ�н���
	String sportType[];
	public Court[] courtList;
	public int courtListLen;
	
	public Stadium(int id, String name, String location) {
		this.id = id;
		this.name = name;
		this.location = location;
	}
	
	public Stadium(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	/*
	private int getCourtListLen() {
		// TODO ���������
		return 0;//��Ϊ������û�з���ֵ�Ĵ�����ʾ
	}
	 * 
	 */
	
	public void getCourtList() {
		// ��ȡ�ӽ��������i�������Ϣ��i==0��ʾ����
		
		//�������ݿ�����
		Connection conn = Database.connect();
		
        //ִ�в�ѯ
        Statement stmt = Database.initSatement(conn);
        
		String sql = "SELECT count(*) as court_count FROM court WHERE stadium_id = " + this.id;
        ResultSet rs = Database.require(stmt, sql);

        //��ȡ��ѯ���
        try {
			while(rs.next()){
			    // ͨ���ֶμ���
			    try {
			    	this.courtListLen = rs.getInt("court_count");
				} catch (SQLException e) {
					this.courtListLen = 0;
					e.printStackTrace();
					return;
				}
			}
		} catch (SQLException e) {
	        Database.disconect(conn);
			e.printStackTrace();
			return;
		}
        
		sql = "SELECT court_id, court_name FROM court WHERE stadium_id = " + this.id;
        rs = Database.require(stmt, sql);
        //��ȡ��ѯ���
        this.courtList = new Court[this.courtListLen];
        try {
        	int i = 0;
			while(rs.next() && i < this.courtListLen){
			    // ͨ���ֶμ���
			    try {
			    	this.courtList[i] = new Court(rs.getInt("court_id"), rs.getString("court_name"));
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
		// ���Դ���
		Stadium stadium = new Stadium(100001);
		stadium.getCourtList();
		for(int i = 0 ; i < stadium.courtListLen; i++)
			System.out.println(stadium.courtList[i].name);
	}
}
