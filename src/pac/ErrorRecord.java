package pac;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ErrorRecord {
	//���ڼ�¼������־
	public ErrorRecord(String error) {
		try {
            //��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
            FileWriter writer = new FileWriter("~/shall_we.log", true);
            String date = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//�������ڸ�ʽ
            writer.write(date + " : " + error + "\r\n\r\n");
            writer.close();
        } catch (Exception e) {}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
