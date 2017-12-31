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
	
	public void getStadiumListBySportType(String sportType) {
		
		//��ȡĳ���ض��ĳ�����Ϣ
		
		//�������ݿ�����
		Connection conn = Database.connect();
		
        //ִ�в�ѯ
        Statement stmt = Database.initSatement(conn);
        
		String sql = "SELECT DISTINCT stadium_id, stadium_name, address FROM stadium NATURAL JOIN court WHERE sport_type = \"" + sportType + "\"";
        ResultSet rs = Database.require(stmt, sql);
        
        this.listLen = Database.getResultSize(rs);
        
        //��ȡ��ѯ���
        this.list = new Stadium[this.listLen];
        try {
        	int i = 0;
			while(rs.next() && i < this.listLen){
			    // ͨ���ֶμ���
			    try {
			    	this.list[i] = new Stadium(rs.getInt("stadium_id"), rs.getString("stadium_name"), rs.getString("address"));
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
		StadiumList list = new StadiumList();
		list.getStadiumListBySportType("run");
		for(int i = 0 ; i < list.listLen; i++) {
			System.out.println(list.list[i].name + " " + list.list[i].address);
		}
	}
}
