package pac;

import pac.ErrorRecord;
import pac.SportInvitation;

import net.sf.json.JSONObject;
//import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.sql.*;

public class User {//��������У�δ�����
	
	public boolean initSuccess;
	public String openid, nickname, sex, province, city, country, headimgurl;
	public float money;
	public SportInvitation sportInvitationList[];
	public int sportInvitationListLen;
	
	public User(/*���ﲻ�����κβ�������Ϊ������� javabean */) {
		
		/*
		 * 
		//����ʹ�õ���ģʽʱ�������ֶ���Ϊtrue��
		this.initSuccess = true;
		//Ȼ��������� openid, nickname, sex, province, city, country, headimgurl �ֶ���ʼ��
		//�Ա�鿴stadium.jspЧ��
		this.openid = "tungkimwa";
		this.nickname = "tungkw";
		this.sex = "male";
		this.province = "guangdong";
		this.city = "guangzhou";
		this.country = "china";
		this.headimgurl = "emmmmmmmm";
		this.sportInvitationListLen = 0;
		 */
		
	}
	
	private String regist(String openid, String nickname, String sex, String province, String city, String country) {
		//�����ݿ�ע���û�
		
        String stat;
        
		//�������ݿ�����
		Connection conn = Database.connect();
		
        //ִ�в�ѯ
		Statement stmt = Database.initSatement(conn);
		
		String sql = "INSERT INTO user (wechat_id, wechat_name, money, sex, province, city, country) "
				+ "VALUES("
					+ "'" + openid + "',"
					+ "'" + nickname + "',"
					+ 0 + ","
					+ "'" + sex + "',"
					+ "'" + province + "',"
					+ "'" + city + "',"
					+ "'" + country + "'"
				+ ")";
		if(Database.execute(stmt, sql)) stat = "success";
		else stat = "create invitaion failed";
		Database.closeStatement(stmt);
        
        //�ر�����   !!!һ��Ҫ�رգ��ͷ���Դ
        Database.disconect(conn);
		return stat;//��Ϊ������û�з���ֵ�Ĵ�����ʾ
	}
	
	public void init(String code) {
		/*��΢�ŷ�������ȡ�û���Ϣ
		 * �벻Ҫ���Ĵ˴���
		 * �벻Ҫ���Ĵ˴���
		 * �벻Ҫ���Ĵ˴���
		 */
		try {
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx8f9eff0d26c8a0a4"
					+ "&secret=309236f4ed3855a66ca1602f34e67eac"
					+ "&code=" + code
					+ "&grant_type=authorization_code";
			JSONObject jsonObject = doGetJson(url);
			if (jsonObject == null)
				return;
			String openid = jsonObject.getString("openid");
			String token = jsonObject.getString("access_token");
			String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + token
					+ "&openid=" + openid
					+ "&lang=zh_CN";
			JSONObject userInfo = doGetJson(infoUrl);
			if (userInfo == null)
				return;
			this.openid = userInfo.getString("openid");
			this.nickname = userInfo.getString("nickname");
			this.sex = userInfo.getString("sex");
			this.province = userInfo.getString("province");
			this.city = userInfo.getString("city");
			this.country = userInfo.getString("country");
			this.headimgurl = userInfo.getString("headimgurl");
			this.getSportInvitationList(false);
			this.initSuccess = true;
			regist(this.openid,this.nickname,this.sex,this.province,this.city,this.country);
			return;
		} catch (Exception e) {
			return;
		}
	}
	
	/*
	private int getSportInvitationListLen() {
		//��ȡsportInvitationList����ĳ���
		return this.sportInvitationList.length;
	}
	*/
	
	private void getSportInvitationList(boolean my) {
		getSportInvitationList(my, 20);
	}
	
	public void getSportInvitationList(boolean my,int count) {
		/* �����˶�����
		 * ����my��ʾ�Ƿ����Լ�������
		 * ��Ϊfalseʱ�������б��˷����ġ��п�λ�����룬����sportInvitation.jspҳ��
		 * ��Ϊtrueʱ���������Լ����������룬����order.jspҳ��
		 * ע�����ﲢ��ֱ�ӷ��������б������б���User.sportInvitationList������
		 * ע����init()������57�У��Ѿ�д�˵���һ���������
		 */
		
		/*
		 * ����count��������ʾ�������Ŀ
		 * Ĭ��ֵΪ20��ͨ����������غ���ʵ��
		 */
		
		//ȷ�����鳤���㹻
		this.sportInvitationList = SportInvitation.getSportInvitationList(my,count);
		for (int i = 0; i < count; i++) {
			if(this.sportInvitationList[i] == null) {
				this.sportInvitationListLen = i;
				break;
			}
		}
	}
	
	public void getMoney(){
		//�����Լ���������me.jspҳ��
		
		//�������ݿ�����
		Connection conn = Database.connect();
		
        //ִ�в�ѯ
		String sql = "SELECT money FROM user where wechat_id = " + "\"" + this.openid + "\"";
        Statement stmt = Database.initSatement(conn);
        ResultSet rs = Database.require(stmt, sql);
        
        //��ȡ��ѯ���
        try {
			while(rs.next()){
			    // ͨ���ֶμ���
			    try {
					this.money = rs.getFloat("money");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
        //�ر�����   !!!һ��Ҫ�رգ��ͷ���Դ
		Database.closeStatement(stmt);
        Database.disconect(conn);
	}
	
	public void addMoney(double money) {
		//��ֵ������recharge.jspҳ��

		//�������ݿ�����
		Connection conn = Database.connect();
		
        //ִ�в�ѯ
		String sql = "UPDATE user SET money = money + " + money + "WHERE wechat_id = " + "\"" + this.openid + "\"";
        Statement stmt = Database.initSatement(conn);
        if(Database.execute(stmt, sql)) this.money += money;
		
        //�ر�����   !!!һ��Ҫ�رգ��ͷ���Դ
		Database.closeStatement(stmt);
        Database.disconect(conn);
	}
	
	public void reduceMoney(double money) {
		//��Ǯ������payInvitation.jsp

		//�������ݿ�����
		Connection conn = Database.connect();
		
        //ִ�в�ѯ
		String sql = "UPDATE user SET money = money - " + money + "WHERE wechat_id = " + "\"" + this.openid + "\"";
        Statement stmt = Database.initSatement(conn);
        if(Database.execute(stmt, sql)) this.money -= money;
		
        //�ر�����   !!!һ��Ҫ�رգ��ͷ���Դ
		Database.closeStatement(stmt);
        Database.disconect(conn);
	}
	
	private JSONObject doGetJson(String url){
		//�������ں�΢�ŷ����������İ������ù�
		JSONObject jsonObject = null;
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String result = EntityUtils.toString(entity, "UTF-8");
				jsonObject = JSONObject.fromObject(result);
			}
			//�ͷ�����
			httpGet.releaseConnection();
		} catch (Exception e) {
			new ErrorRecord("pac.User.doGetJson detect error!\r\n"+url);
		}
		return jsonObject;
	}
	
	public static void main(String[] args) {
		//���Դ���
		User user1 = new User();
		System.out.println(user1.openid + user1.nickname + user1.sex + user1.province + user1.city + user1.country + user1.headimgurl);
		user1.getMoney();
		System.out.println(user1.money);
		user1.addMoney(50.7);
		System.out.println(user1.money);
		user1.reduceMoney(50.7);
		System.out.println(user1.money);
		
		user1.regist("1111", "2222", "3333", "4444", "5555", "6666");
	}

}
