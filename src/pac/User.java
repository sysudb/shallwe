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

public class User {//还在设计中，未完待续
	
	public boolean initSuccess;
	public String openid, nickname, sex, province, city, country, headimgurl;
	public int money;
	public SportInvitation sportInvitationList[];
	public int sportInvitationListLen;
	
	public User(/*这里不能有任何参数，因为设计用于Javabean*/) {
		//【当使用调试模式时把这里手动置为true】
		this.initSuccess = false;
		//然后在这里把openid, nickname, sex, province, city, country, headimgurl 手动初始化
		//以便查看stadium.jsp效果
		//TODO
	}
	
	public void init(String code) {
		/*从微信服务器拉取用户信息
		 * 请不要更改此代码
		 * 请不要更改此代码
		 * 请不要更改此代码
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
		//获取sportInvitationList数组的长度
		//TODO
		return 0;//仅为了消灭没有返回值的错误提示
	}
	
	public void getSportInvitationList(boolean my) {
		/* 返回运动邀请
		 * 参数my表示是否是自己的邀请
		 * 当为false时返回所有别人发出的、有空位的邀请，用于sportInvitation.jsp页面
		 * 当为true时返回所有自己发出的邀请，用于order.jsp页面
		 * 注意这里并不直接返回邀请列表，邀请列表在User.sportInvitationList数组中
		 * 注意在init()函数（57行）已经写了调用一次这个函数
		 */
		this.sportInvitationListLen = this.getSportInvitationListLen();
		this.sportInvitationList = new SportInvitation[this.sportInvitationListLen];
		for (int i = 0; i < this.sportInvitationListLen; i++) {
			this.sportInvitationList[i] = new SportInvitation(/*参数自定*/);
			//TODO 初始化每个SportInvitation
		}
		
	}
	
	public int getMoney() {
		//返回自己的余额，用于me.jsp页面
		//TODO
		return 0;//仅为了消灭没有返回值的错误提示
	}
	
	public void addMoney(int money) {
		//充值，用于recharge.jsp页面
		//TODO
	}
	
	public void reduceMoney(int money) {
		//扣钱，用于payInvitation.jsp
		//TODO
	}
	
	private JSONObject doGetJson(String url){
		//抄的用于和微信服务器交互的包，不用管
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
			//释放连接
			httpGet.releaseConnection();
		} catch (Exception e) {
			new ErrorRecord("pac.User.doGetJson detect error!\r\n"+url);
		}
		return jsonObject;
	}
	
	public static void main(String[] args) {
		//测试代码
	}

}
