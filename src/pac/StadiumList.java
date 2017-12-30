package pac;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pac.Stadium;

public class StadiumList {
	
	public Stadium list[];
	public int listLen;

	public StadiumList(/*这里不能有任何参数，因为设计用于Javabean*/) {
		//构造函数
	}
	
	/*
	private int getListLen() {
		//获得场馆数量
		return 0;//仅为了消灭没有返回值的错误提示
	}
	 * 
	 */
	
	public void getStadiumListBySportType(String sportType) {
		
		//获取某种特定的场馆信息
		
		//创建数据库连接
		Connection conn = Database.connect();
		
        //执行查询
        Statement stmt = Database.initSatement(conn);
        
		String sql = "SELECT count(*) as stadium_count "
					+ "FROM ("
						+ "SELECT DISTINCT stadium_id, stadium_name, location FROM stadium NATURAL JOIN court WHERE sport_type = \"" + sportType + "\""
					+ ") as T";
        ResultSet rs = Database.require(stmt, sql);
        try {
			while(rs.next()){
			    // 通过字段检索
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
        //提取查询结果
        this.list = new Stadium[this.listLen];
        try {
        	int i = 0;
			while(rs.next() && i < this.listLen){
			    // 通过字段检索
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
		
        //关闭连接   !!!一定要关闭，释放资源
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
