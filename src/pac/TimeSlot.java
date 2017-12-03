package pac;

public class TimeSlot {
	
	@SuppressWarnings("unused")
	private int id;
	public String year, month, day, week;
	public int startHour, endHour, startMinute, endMinute;
	boolean available;//是否有人预定
	
	public TimeSlot(/*参数不限，因为设计不能用于Javabean，必须通过
	 		SportInvitation.court	或
	 		Stadium.timeSlot		访问*/) {
		// TODO 构造函数
		//初始化所有成员变量
	}

	public static void main(String[] args) {
		// 测试代码
	}

}
