package pac;

public class Stadium {
	// �����Ǵ�����Ϣ
	public String name, location, sportType;
	@SuppressWarnings("unused")
	private int id;
	// ��������ϸ��Ϣ
	int availableDay;//�ӽ��������м����ǿ���Ԥ���ģ�0��Ϊֻ�н���
	public Court[] courtList;
	public int courtListLen;
	
	public Stadium(/*�������ޣ���Ϊ��Ʋ�������Javabean������ͨ��
			SportInvitation.stadium	��
			StadiumList.list		����*/) {
		// TODO ���캯��
	}
	
	private int getCourtListLen() {
		// TODO ���������
		return 0;//��Ϊ������û�з���ֵ�Ĵ�����ʾ
	}
	
	public void getCourtList(int i_thDay) {
		// ��ȡ�ӽ��������i�������Ϣ��i==0��ʾ����
		this.courtListLen = this.getCourtListLen();
		this.courtList = new Court[this.courtListLen];
		for (int i = 0; i < this.courtListLen; i++) {
			this.courtList[i] = new Court(/*�����Զ�*/);
			// TODO ��ʼ��ÿһ����
			this.courtList[i].getTimeSlotList(i_thDay);
		}
	}

	public static void main(String[] args) {
		// ���Դ���

	}

}
