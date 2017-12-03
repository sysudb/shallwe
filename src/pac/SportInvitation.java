package pac;
import pac.TimeSlot;
import pac.Stadium;

public class SportInvitation {

	// 以下是粗略信息，用于显示在sportsInvitationList.jsp页面上
	public String slogan, money;
	public int joinPeople, totalPeople;
	public Stadium stadium;//这里面包含了sportType信息
	public TimeSlot timeslot;//这里面包含了详细的时间信息
	// 以下是详细信息，用于显示在sportsInvitationDetail.jsp页面上
	public String ownerWechatName, participantWechatname[];
	public String court;
	
	public SportInvitation(/*参数不限，因为设计不能用于Javabean，必须通过User.sportInvitatiionList访问*/) {
		// 构造函数
	}
	
	public void getDetails() {
		// TODO 获取详细信息，用于sportInvitationDetail.jsp
	}
	
	public void joinInvitation(User newParticipant) {
		// TODO 加入运动，用于sportInvitationDetail.jsp的跳转后执行
	}
	
	public String makeInvitation(String slogan, String money, boolean aa, int totalPeople, Stadium stadium, TimeSlot timeslot) {
		/* TODO 新建一个邀请
		 * 前端调用方法为
		 * new SportInvitation().makeInvitation(......)
		 * 新建成功则返回success，失败返回错误原因，用于显示给用户
		 */
		return "success";//仅为了消灭没有返回值的错误提示
	}
	
	public static void main(String[] args) {
		// 测试代码
	}
	
}
