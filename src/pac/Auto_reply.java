package pac;
import pac.XML_worker;

public class Auto_reply {
	//用于与message.jsp相连接，处理微信服务器发送来的XML数据，并且通过get_reply返回XML字符串
	//Main_mother_reply和Mother_reply模块处理好要回复的内容后均将要回复的字符串存入其模块的reply变量中
	String content;
	XML_worker message;
	
	String main_mother = "unknown";
	public Auto_reply(String post_str) {
		try {
			message = new XML_worker(post_str);
		}catch (Exception e) {
			System.out.println("XML出错");
		}
		//
		message.content = "欢迎关注Shall We测试号\n请点击 <a href=\"http://sysustudentunion.cn/shallwe/login.html\">【这里】</a>进入";
	}
	
	public String get_reply() {
		return message.trans_parameter_to_string();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
