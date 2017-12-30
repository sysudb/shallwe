package pac;


import java.sql.Timestamp;

public class TimeSlot {
	
	@SuppressWarnings("unused")
	private int id;
	public Timestamp startTime, endTime;
//	public String year, month, day, week;
//	public int startHour, endHour, startMinute, endMinute;
//	boolean available;//是否有人预定
	
	public TimeSlot(Timestamp startTime, Timestamp endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public static void main(String[] args) {
		// 测试代码
	}

}
