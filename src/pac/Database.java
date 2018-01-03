package pac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Database {
	static {
		// 注册 JDBC 驱动
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			new ErrorRecord(e.toString());
		}
	}
	
	
	private static String db_name = "dbteam";
	private static String db_passwd = "666666";
	private static String db_url = "jdbc:mysql://sysustudentunion.cn:3306/test?characterEncoding=utf8&useSSL=false";
	//private static String db_url = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=false";
	 

//	private static String db_name = "shallwe";
//	private static String db_passwd = "123456";
//	private static String db_url = "jdbc:mysql://172.18.158.120:3306/shallwe?characterEncoding=utf8&useSSL=false";
	
	public static Connection connect() {
		Connection conn = null;
    
        // 打开链接
        try {
			conn = DriverManager.getConnection(db_url, db_name, db_passwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new ErrorRecord(e.toString());
			e.printStackTrace();
			return null;
		}
        return conn;
	}
	
	public static Statement initSatement(Connection conn) {
    	Statement stmt = null;
        // 执行查询,实例化Statement对
        try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			return stmt;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new ErrorRecord(e.toString());
			e.printStackTrace();
			return null;
		}
	}
	
	public static PreparedStatement initSatement(Connection conn, String sql) {
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			return stmt;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new ErrorRecord(e.toString());
			e.printStackTrace();
			return null;
		}
	}
	
	public static ResultSet require(Statement stmt, String sql) {
        ResultSet rs;
		try {
			rs = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			new ErrorRecord(e.toString());
			e.printStackTrace();
			return null;
		}
	}

	public static ResultSet require(PreparedStatement stmt) {
        ResultSet rs;
		try {
			rs = stmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			new ErrorRecord(e.toString());
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean execute(Statement stmt, String sql) {
		try {
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			new ErrorRecord(e.toString());
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean execute(PreparedStatement stmt) {
		try {
			stmt.execute();
			return true;
		} catch (SQLException e) {
			new ErrorRecord(e.toString());
			e.printStackTrace();
			return false;
		}
	}
	
	public static int getResultSize(ResultSet rs) {
		try{
			rs.last();//移到最后一行  
			int count = rs.getRow();  
			rs.beforeFirst();//移到初始位置  
			return count;
		}catch(SQLException e){
			// TODO Auto-generated catch block
			new ErrorRecord(e.toString());
			e.printStackTrace();
			return -1;
		}
	}
	
	public static void closeStatement(Statement stmt) {
        try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new ErrorRecord(e.toString());
			e.printStackTrace();
		}
	}
	
	public static void disconect(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new ErrorRecord(e.toString());
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Connection connection = Database.connect();
		System.out.print("debug");
	}
}
