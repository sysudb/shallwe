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
		
		//����ʹ�õ���ģʽʱ�������ֶ���Ϊtrue��
		this.initSuccess = true;
		//Ȼ��������� openid, nickname, sex, province, city, country, headimgurl �ֶ���ʼ��
		//�Ա�鿴stadium.jspЧ��
		this.openid = "tungkimwa2";
		this.nickname = "tungkw2";
		this.sex = "1";
		this.province = "guangdong";
		this.city = "guangzhou";
		this.country = "china";
		this.sportInvitationListLen = 0;
		this.headimgurl = "/shallwe/image/default_icon.jpg";
		regist(this.openid,this.nickname,this.sex,this.province,this.city,this.country,this.headimgurl);
		this.getMoney();
	}
	
	private String regist(String openid, String nickname, String sex, String province, String city, String country, String iconUrl) {
		//�����ݿ�ע���û�
		
        String stat;
        
		//�������ݿ�����
		Connection conn = Database.connect();
		
        //ִ�в�ѯ
		Statement stmt = Database.initSatement(conn);
		
		String sql = "INSERT INTO user (wechat_id, wechat_name, money, sex, province, city, country, icon_url) "
				+ "VALUES("
					+ "'" + openid + "',"
					+ "'" + nickname + "',"
					+ 0.0f + ","
					+ "'" + sex + "',"
					+ "'" + province + "',"
					+ "'" + city + "',"
					+ "'" + country + "',"
					+ "'" + iconUrl + "'"
				+ ") "
				+ "ON duplicate KEY UPDATE wechat_id = wechat_id";
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
			regist(this.openid,this.nickname,this.sex,this.province,this.city,this.country,this.headimgurl);
			return;
		} catch (Exception e) {
			return;
		}
	}
	
	public void getSportInvitationList(boolean my) {
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

		this.sportInvitationList = new SportInvitation[count];
		
		//�������ݿ�����
		Connection conn = Database.connect();
		
        //ִ�в�ѯ
        Statement stmt = Database.initSatement(conn);
        
		String sql = "SELECT * FROM activities NATURAL JOIN stadium";
        ResultSet rs = Database.require(stmt, sql);
        
        //��ȡ��ѯ���
        try {
        	int i = 0;
			while(rs.next() && i<count){
			    // ͨ���ֶμ���
			    try {
			    	this.sportInvitationList[i] = new SportInvitation(
					rs.getInt("activity_id"),
					rs.getString("slogan"),
					rs.getString("sport_type"),
					rs.getFloat("cost"),
					rs.getBoolean("pay_type"),
					new Stadium(rs.getInt("stadium_id"),rs.getString("stadium_name"),rs.getString("address")),
					new TimeSlot(rs.getTimestamp("start_time"),rs.getTimestamp("end_time")),
					0,
					rs.getInt("max_participant"),
					rs.getString("location"));
		    		i++;
				} catch (SQLException e) {
					e.printStackTrace();
					new ErrorRecord(e.toString());
				}
			}
			this.sportInvitationListLen = i;
		} catch (SQLException e) {
			e.printStackTrace();
			new ErrorRecord(e.toString());
		}
		
        for(int i = 0 ; i < this.sportInvitationListLen;i++) {
            sql = "SELECT count(*) as num_participant FROM participate WHERE activity_id = " + this.sportInvitationList[i].getId();
            rs = Database.require(stmt, sql);
            try {
				while(rs.next() && i<count){
					// ͨ���ֶμ���
			        try {
			        	this.sportInvitationList[i].joinPeople = rs.getInt("num_participant");
			        	this.sportInvitationList[i].getDetails();
					} catch (SQLException e) {
						e.printStackTrace();
						new ErrorRecord(e.toString());
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				new ErrorRecord(e.toString());
			}
        }
        //�ر�����   !!!һ��Ҫ�رգ��ͷ���Դ
		Database.closeStatement(stmt);
        Database.disconect(conn);
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
					new ErrorRecord(e.toString());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			new ErrorRecord(e.toString());
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
		
		user1.regist("7777", "2222", "3333", "4444", "5555", "6666","/shallwe/image/default_icon.jpg");
		

		int count = 10;
		user1.getSportInvitationList(false, count);
		for(int i = 0 ; i < count;i++) {
			if(user1.sportInvitationList[i]!=null) {
				System.out.println(
						user1.sportInvitationList[i].getId() + 
						user1.sportInvitationList[i].money + 
						user1.sportInvitationList[i].slogan + 
						user1.sportInvitationList[i].sportType + 
						user1.sportInvitationList[i].joinPeople + 
						user1.sportInvitationList[i].totalPeople);
				user1.sportInvitationList[i].getDetails();
				System.out.println("creator: " + user1.sportInvitationList[i].ownerWechatName);
				for(int j = 0 ; j <user1.sportInvitationList[i].joinPeople;j++) {
					System.out.println(user1.sportInvitationList[i].participantWechatname[j] + " " + user1.sportInvitationList[i].participantIcon[j]);
				}
				if(user1.sportInvitationList[i].ownerWechatName.equals("lj")) {
					String stat = user1.sportInvitationList[i].joinInvitation(new User());
					System.out.println(stat);
				}
			}
		}
	}
}
