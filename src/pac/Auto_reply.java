package pac;
import pac.XML_worker;

public class Auto_reply {
	//������message.jsp�����ӣ�����΢�ŷ�������������XML���ݣ�����ͨ��get_reply����XML�ַ���
	//Main_mother_reply��Mother_replyģ�鴦���Ҫ�ظ������ݺ����Ҫ�ظ����ַ���������ģ���reply������
	String content;
	XML_worker message;
	
	String main_mother = "unknown";
	public Auto_reply(String post_str) {
		try {
			message = new XML_worker(post_str);
		}catch (Exception e) {
			System.out.println("XML����");
		}
		//
		message.content = "��ӭ��עShall We���Ժ�\n���� <a href=\"http://sysustudentunion.cn/shallwe/login.html\">�����</a>����";
	}
	
	public String get_reply() {
		return message.trans_parameter_to_string();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
