package pac;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Stadium {
	// 以下是粗略信息
	public String name, location;
	@SuppressWarnings("unused")
	private int id;
	// 以下是详细信息
	int availableDay;//从今天算起有几天是可以预定的，0则为只有今天
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
		// TODO 获得球场数量
		return 0;//仅为了消灭没有返回值的错误提示
	}
	 * 
	 */
	
	public void getCourtList() {
		// 拉取从今天算起第i天的球场信息，i==0表示今天
		
		//创建数据库连接
		Connection conn = Database.connect();
		
        //执行查询
        Statement stmt = Database.initSatement(conn);
        
		String sql = "SELECT count(*) as court_count FROM court WHERE stadium_id = " + this.id;
        ResultSet rs = Database.require(stmt, sql);

        //提取查询结果
        try {
			while(rs.next()){
			    // 通过字段检索
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
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
        //关闭连接   !!!一定要关闭，释放资源
		Database.closeStatement(stmt);
        Database.disconect(conn);
	}

	public static void main(String[] args) {
		// 测试代码
		Stadium stadium = new Stadium(100001);
		stadium.getCourtList();
		for(int i = 0 ; i < stadium.courtListLen; i++)
			System.out.println(stadium.courtList[i].name);
	}
}
