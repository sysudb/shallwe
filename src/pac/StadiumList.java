package pac;
import pac.Stadium;

public class StadiumList {
	
	public Stadium list[];
	public int listLen;
	
	private int getListLen() {
		//��ó�������
		return 0;//��Ϊ������û�з���ֵ�Ĵ�����ʾ
	}
	
	public StadiumList(/*���ﲻ�����κβ�������Ϊ�������Javabean*/) {
		//���캯��
	}
	
	public void init(String sportType) {
		//��ȡĳ���ض��ĳ�����Ϣ
		this.listLen = this.getListLen();
		this.list = new Stadium[this.listLen];
		for (int i = 0; i < this.listLen; i++) {
			this.list[i] = new Stadium(/*�����Զ�*/);
			//TODO ��ʼ��ÿһ��Stadium�Ĵ�����Ϣ
		}
	}

	public static void main(String[] args) {
		//���Դ���
	}

}
