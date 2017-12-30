package pac;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pac.Stadium;

public class StadiumList {
	
	public Stadium list[];
	public int listLen;

	public StadiumList(/*���ﲻ�����κβ�������Ϊ�������Javabean*/) {
		//���캯��
	}
	
	/*
	private int getListLen() {
		//��ó�������
		return 0;//��Ϊ������û�з���ֵ�Ĵ�����ʾ
	}
	 * 
	 */
	
	public void getStadiumListBySportType(String sportType) {
		
		//��ȡĳ���ض��ĳ�����Ϣ
		
		//�������ݿ�����
		Connection conn = Database.connect();
		
        //ִ�в�ѯ
        Statement stmt = Database.initSatement(conn);
        
		String sql = "SELECT count(*) as stadium_count "
					+ "FROM ("
						+ "SELECT DISTINCT stadium_id, stadium_name, location FROM stadium NATURAL JOIN court WHERE sport_type = \"" + sportType + "\""
					+ ") as T";
        ResultSet rs = Database.require(stmt, sql);
        try {
			while(rs.next()){
			    // ͨ���ֶμ���
			    try {
			    	this.listLen = rs.getInt("stadium_count");
				} catch (SQLException e) {
					this.listLen = 0;
					e.printStackTrace();
					return;
				}
			}
		} catch (SQLException e) {
	        Database.disconect(conn);
			e.printStackTrace();
			return;
		}
        
		sql = "SELECT DISTINCT stadium_id, stadium_name, location FROM stadium NATURAL JOIN court WHERE sport_type = \"" + sportType + "\"";
        rs = Database.require(stmt, sql);
        //��ȡ��ѯ���
        this.list = new Stadium[this.listLen];
        try {
        	int i = 0;
			while(rs.next() && i < this.listLen){
			    // ͨ���ֶμ���
			    try {
			    	this.list[i] = new Stadium(rs.getInt("stadium_id"), rs.getString("stadium_name"), rs.getString("location"));
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
		StadiumList list = new StadiumList();
		list.getStadiumListBySportType("run");
		for(int i = 0 ; i < list.listLen; i++) {
			System.out.println(list.list[i].name + " " + list.list[i].location);
		}
	}
}
