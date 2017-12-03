package pac;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ErrorRecord {
	//用于记录错误日志
	public ErrorRecord(String error) {
		try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter("~/shall_we.log", true);
            String date = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//设置日期格式
            writer.write(date + " : " + error + "\r\n\r\n");
            writer.close();
        } catch (Exception e) {}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
