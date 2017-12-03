package pac;
import pac.TimeSlot;

public class Court {
	
	// 以下是粗略信息，用于sportInvitationDetail.jsp页面
	public String name;
	@SuppressWarnings("unused")
	private int id;
	// 以下是详细信息，用于selectCourtTime.jsp页面
	public TimeSlot timeSlotList[];
	public int timeSlotListLen;
	
	public Court(/*参数不限，因为设计不能用于Javabean，必须通过
	 		SportInvitation.court	或
	 		Stadium.court			访问*/) {
		// TODO 构造函数
	}
	
	public int getTimeSlotListLen() {
		// TODO 获取时段数量
		return 0;//仅为了消灭没有返回值的错误提示
	}
	
	public void getTimeSlotList(int i_thDay) {
		// TODO 获取i_thDay天后的时段情况，i_thDay==0表示今天
		this.timeSlotListLen = this.getTimeSlotListLen();
		this.timeSlotList = new TimeSlot[this.timeSlotListLen];
		for (int i = 0; i < this.getTimeSlotListLen(); i++) {
			this.timeSlotList[i] = new TimeSlot(/*参数自定*/);
			// TODO 初始化每一个TimeSlot
		}
	}

	public static void main(String[] args) {
		// 测试程序
	}

}
