package pac;
import pac.TimeSlot;

public class Court {
	
	// �����Ǵ�����Ϣ������sportInvitationDetail.jspҳ��
	public String name;
	@SuppressWarnings("unused")
	private int id;
	// ��������ϸ��Ϣ������selectCourtTime.jspҳ��
	public TimeSlot timeSlotList[];
	public int timeSlotListLen;
	
	public Court(/*�������ޣ���Ϊ��Ʋ�������Javabean������ͨ��
	 		SportInvitation.court	��
	 		Stadium.court			����*/) {
		// TODO ���캯��
	}
	
	public int getTimeSlotListLen() {
		// TODO ��ȡʱ������
		return 0;//��Ϊ������û�з���ֵ�Ĵ�����ʾ
	}
	
	public void getTimeSlotList(int i_thDay) {
		// TODO ��ȡi_thDay����ʱ�������i_thDay==0��ʾ����
		this.timeSlotListLen = this.getTimeSlotListLen();
		this.timeSlotList = new TimeSlot[this.timeSlotListLen];
		for (int i = 0; i < this.getTimeSlotListLen(); i++) {
			this.timeSlotList[i] = new TimeSlot(/*�����Զ�*/);
			// TODO ��ʼ��ÿһ��TimeSlot
		}
	}

	public static void main(String[] args) {
		// ���Գ���
	}

}
