package pac;

public class Stadium {
	// 以下是粗略信息
	public String name, location, sportType;
	@SuppressWarnings("unused")
	private int id;
	// 以下是详细信息
	int availableDay;//从今天算起有几天是可以预定的，0则为只有今天
	public Court[] courtList;
	public int courtListLen;
	
	public Stadium(/*参数不限，因为设计不能用于Javabean，必须通过
			SportInvitation.stadium	或
			StadiumList.list		访问*/) {
		// TODO 构造函数
	}
	
	private int getCourtListLen() {
		// TODO 获得球场数量
		return 0;//仅为了消灭没有返回值的错误提示
	}
	
	public void getCourtList(int i_thDay) {
		// 拉取从今天算起第i天的球场信息，i==0表示今天
		this.courtListLen = this.getCourtListLen();
		this.courtList = new Court[this.courtListLen];
		for (int i = 0; i < this.courtListLen; i++) {
			this.courtList[i] = new Court(/*参数自定*/);
			// TODO 初始化每一个球场
			this.courtList[i].getTimeSlotList(i_thDay);
		}
	}

	public static void main(String[] args) {
		// 测试代码

	}

}
