package pac;

import java.sql.Timestamp;

public class TimeSlot {
	public Timestamp startTime, endTime;
	private int id;
	private boolean status;
//	public String year, month, day, week;
//	public int startHour, endHour, startMinute, endMinute;
//	boolean available;//是否有人预定
	
	public TimeSlot(Timestamp startTime, Timestamp endTime, int id, boolean status) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.id = id;
		this.status = status;
	}
	
	public TimeSlot(Timestamp startTime, Timestamp endTime) {
		this.id = -1;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = false;
	}
	
	public int getId() {
		return this.id;
	}
	
	public boolean getStatus() {
		return this.status;
	}
	
	public static void main(String[] args) {
	}

}
