package pac;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Stadium {
	// �����Ǵ�����Ϣ
	public String name, address;
	@SuppressWarnings("unused")
	private int id;
	// ��������ϸ��Ϣ
	//int availableDay;//�ӽ��������м����ǿ���Ԥ���ģ�0��Ϊֻ�н���
	public String[] sportTypeList;
	public int sportTypeListLen;
	public Court[] courtList;
	public int courtListLen;
	
	public Stadium(int id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}
	
	public Stadium(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void getCourtList(String sportType) {
		//�������ݿ�����
		Connection conn = Database.connect();
		
        //ִ�в�ѯ
        Statement stmt = Database.initSatement(conn);
        
		String sql = "SELECT court_id, court_name FROM court WHERE stadium_id = " + this.id + " and sport_type = '" + sportType + "'";
        ResultSet rs = Database.require(stmt, sql);
        
        this.courtListLen = Database.getResultSize(rs);
        
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
	}
	
	public void getSportTypeList() {
		//�������ݿ�����
		Connection conn = Database.connect();
		
        //ִ�в�ѯ
        Statement stmt = Database.initSatement(conn);
        
		String sql = "SELECT DISTINCT sport_type FROM court WHERE stadium_id = " + this.id;
        ResultSet rs = Database.require(stmt, sql);
        
        this.sportTypeListLen = Database.getResultSize(rs);
        
        //��ȡ��ѯ���
        this.sportTypeList = new String[this.sportTypeListLen];
        try {
        	int i = 0;
			while(rs.next() && i < this.sportTypeListLen){
			    // ͨ���ֶμ���
			    try {
			    	this.sportTypeList[i] =rs.getString("sport_type");
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
	}
	
 	public static void main(String[] args) {
		// ���Դ���
		Stadium stadium = new Stadium(100001);
		stadium.getCourtList("run");
		for(int i = 0 ; i < stadium.courtListLen; i++)
			System.out.println(stadium.courtList[i].name);
		stadium.getSportTypeList();
		for(int i = 0 ; i < stadium.sportTypeListLen; i++)
			System.out.println(stadium.sportTypeList[i]);
	}
}
