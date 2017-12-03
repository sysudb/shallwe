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

public class User {//��������У�δ�����
	
	public boolean initSuccess;
	public String openid, nickname, sex, province, city, country, headimgurl;
	public int money;
	public SportInvitation sportInvitationList[];
	public int sportInvitationListLen;
	
	public User(/*���ﲻ�����κβ�������Ϊ�������Javabean*/) {
		//����ʹ�õ���ģʽʱ�������ֶ���Ϊtrue��
		this.initSuccess = false;
		//Ȼ���������openid, nickname, sex, province, city, country, headimgurl �ֶ���ʼ��
		//�Ա�鿴stadium.jspЧ��
		//TODO
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
			return;
		} catch (Exception e) {
			return;
		}
	}
	
	private int getSportInvitationListLen() {
		//��ȡsportInvitationList����ĳ���
		//TODO
		return 0;//��Ϊ������û�з���ֵ�Ĵ�����ʾ
	}
	
	public void getSportInvitationList(boolean my) {
		/* �����˶�����
		 * ����my��ʾ�Ƿ����Լ�������
		 * ��Ϊfalseʱ�������б��˷����ġ��п�λ�����룬����sportInvitation.jspҳ��
		 * ��Ϊtrueʱ���������Լ����������룬����order.jspҳ��
		 * ע�����ﲢ��ֱ�ӷ��������б������б���User.sportInvitationList������
		 * ע����init()������57�У��Ѿ�д�˵���һ���������
		 */
		this.sportInvitationListLen = this.getSportInvitationListLen();
		this.sportInvitationList = new SportInvitation[this.sportInvitationListLen];
		for (int i = 0; i < this.sportInvitationListLen; i++) {
			this.sportInvitationList[i] = new SportInvitation(/*�����Զ�*/);
			//TODO ��ʼ��ÿ��SportInvitation
		}
		
	}
	
	public int getMoney() {
		//�����Լ���������me.jspҳ��
		//TODO
		return 0;//��Ϊ������û�з���ֵ�Ĵ�����ʾ
	}
	
	public void addMoney(int money) {
		//��ֵ������recharge.jspҳ��
		//TODO
	}
	
	public void reduceMoney(int money) {
		//��Ǯ������payInvitation.jsp
		//TODO
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
	}

}
