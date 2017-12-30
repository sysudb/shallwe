package pac;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pac.TimeSlot;

public class Court {
	
	// 以下是粗略信息，用于sportInvitationDetail.jsp页面
	public String name;
	@SuppressWarnings("unused")
	private int id;
	// 以下是详细信息，用于selectCourtTime.jsp页面
	public TimeSlot timeSlotList[];
	public int timeSlotListLen;
	
	public Court(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public void getTimeSlotList(int i_thDay) {
		// TODO 获取i_thDay天后的时段情况，i_thDay==0表示今天
		
		//创建数据库连接
		Connection conn = Database.connect();
		
        //执行查询
        Statement stmt = Database.initSatement(conn);

        String sql = "set time_zone = '+8:00'";
        Database.execute(stmt, sql);
        
		sql = "SELECT from_time, to_time FROM time_slot WHERE court_id = " + this.id + " AND TO_DAYS(from_time) - TO_DAYS(NOW()) between 0 and " + i_thDay;
        ResultSet rs = Database.require(stmt, sql);
        
        this.timeSlotListLen = Database.getResultSize(rs);
        
        //提取查询结果
        this.timeSlotList = new TimeSlot[this.timeSlotListLen];
        try {
        	int i = 0;
			while(rs.next() && i < this.timeSlotListLen){
			    // 通过字段检索
			    try {
			    	this.timeSlotList[i] = new TimeSlot(rs.getTimestamp("from_time"), rs.getTimestamp("to_time"));
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
		// 测试程序
		Court court = new Court(1, "court1");
		court.getTimeSlotList(0);
		System.out.println(court.timeSlotListLen);
		for(int i = 0 ; i < court.timeSlotListLen;i++) {
			System.out.println(court.timeSlotList[i].startTime.toString() + "    " + court.timeSlotList[i].endTime.toString());
		}
	}

}
