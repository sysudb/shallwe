package pac;
import pac.TimeSlot;
import pac.Stadium;

public class SportInvitation {

	// �����Ǵ�����Ϣ��������ʾ��sportsInvitationList.jspҳ����
	public String slogan, money;
	public int joinPeople, totalPeople;
	public Stadium stadium;//�����������sportType��Ϣ
	public TimeSlot timeslot;//�������������ϸ��ʱ����Ϣ
	// ��������ϸ��Ϣ��������ʾ��sportsInvitationDetail.jspҳ����
	public String ownerWechatName, participantWechatname[];
	public String court;
	
	public SportInvitation(/*�������ޣ���Ϊ��Ʋ�������Javabean������ͨ��User.sportInvitatiionList����*/) {
		// ���캯��
	}
	
	public void getDetails() {
		// TODO ��ȡ��ϸ��Ϣ������sportInvitationDetail.jsp
	}
	
	public void joinInvitation(User newParticipant) {
		// TODO �����˶�������sportInvitationDetail.jsp����ת��ִ��
	}
	
	public String makeInvitation(String slogan, String money, boolean aa, int totalPeople, Stadium stadium, TimeSlot timeslot) {
		/* TODO �½�һ������
		 * ǰ�˵��÷���Ϊ
		 * new SportInvitation().makeInvitation(......)
		 * �½��ɹ��򷵻�success��ʧ�ܷ��ش���ԭ��������ʾ���û�
		 */
		return "success";//��Ϊ������û�з���ֵ�Ĵ�����ʾ
	}
	
	public static void main(String[] args) {
		// ���Դ���
	}
	
}
