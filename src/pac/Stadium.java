package pac;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Stadium {
	// 以下是粗略信息
	public String name, address;
	@SuppressWarnings("unused")
	private int id;
	// 以下是详细信息
	//int availableDay;//从今天算起有几天是可以预定的，0则为只有今天
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
		//创建数据库连接
		Connection conn = Database.connect();
		
        //执行查询
        Statement stmt = Database.initSatement(conn);
        
		String sql = "SELECT court_id, court_name FROM court WHERE stadium_id = " + this.id + " and sport_type = '" + sportType + "'";
        ResultSet rs = Database.require(stmt, sql);
        
        this.courtListLen = Database.getResultSize(rs);
        
        //提取查询结果
        this.courtList = new Court[this.courtListLen];
        try {
        	int i = 0;
			while(rs.next() && i < this.courtListLen){
			    // 通过字段检索
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
		
        //关闭连接   !!!一定要关闭，释放资源
		Database.closeStatement(stmt);
        Database.disconect(conn);
	}
	
	public void getSportTypeList() {
		//创建数据库连接
		Connection conn = Database.connect();
		
        //执行查询
        Statement stmt = Database.initSatement(conn);
        
		String sql = "SELECT DISTINCT sport_type FROM court WHERE stadium_id = " + this.id;
        ResultSet rs = Database.require(stmt, sql);
        
        this.sportTypeListLen = Database.getResultSize(rs);
        
        //提取查询结果
        this.sportTypeList = new String[this.sportTypeListLen];
        try {
        	int i = 0;
			while(rs.next() && i < this.sportTypeListLen){
			    // 通过字段检索
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
		
        //关闭连接   !!!一定要关闭，释放资源
		Database.closeStatement(stmt);
        Database.disconect(conn);
	}
	
 	public static void main(String[] args) {
		// 测试代码
		Stadium stadium = new Stadium(100001);
		stadium.getCourtList("run");
		for(int i = 0 ; i < stadium.courtListLen; i++)
			System.out.println(stadium.courtList[i].name);
		stadium.getSportTypeList();
		for(int i = 0 ; i < stadium.sportTypeListLen; i++)
			System.out.println(stadium.sportTypeList[i]);
	}
}
