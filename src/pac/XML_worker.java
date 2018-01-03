package pac;
//import java.io.FileWriter;
import java.util.Date;

import org.dom4j.*;

public class XML_worker {
	
	public String XML_string, 	//XML字符串
				mother, 		//公众号ID
				user, 			//发送用户openID
				createtime, 	//创建时间
				msgtype, 		//消息类型
				content;		//消息内容
	
	public XML_worker(String in) throws Exception{
		XML_string = in;

		Document doc_xml = DocumentHelper.parseText(XML_string);
		Element root = doc_xml.getRootElement();
		mother = root.elementText("ToUserName");
		user = root.elementText("FromUserName");
		msgtype = root.elementText("MsgType");
		content = "" + root.elementText("Content");
	}
	
	public String trans_parameter_to_string() {	
		Document back = DocumentHelper.createDocument();
		Element root = back.addElement("xml");
		Element ToUserName = root.addElement("ToUserName");
		ToUserName.setText(user);
		Element FromUserName = root.addElement("FromUserName");
		FromUserName.setText(mother);
		Element CreateTime = root.addElement("CreateTime");
		CreateTime.setText(new Date().getTime()+"");
		Element MsgType = root.addElement("MsgType");
		MsgType.setText("text");
		Element Content = root.addElement("Content");
		Content.setText(content);
		XML_string = root.asXML();
		return XML_string;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
