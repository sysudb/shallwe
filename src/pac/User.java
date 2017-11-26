package pac;

import pac.ErrorRecord;
import net.sf.json.JSONObject;
//import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class User {
	
	public String openid, nickname, sex, province, city, country, headimgurl;
	
	public User(/*这里不能有任何参数，谢谢*/) {}
	
	public boolean init(String code) {
		try {
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx8f9eff0d26c8a0a4"
					+ "&secret=309236f4ed3855a66ca1602f34e67eac"
					+ "&code=" + code
					+ "&grant_type=authorization_code";
			JSONObject jsonObject = doGetJson(url);
			if (jsonObject == null)
				return false;
			String openid = jsonObject.getString("openid");
			String token = jsonObject.getString("access_token");
			String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + token
					+ "&openid=" + openid
					+ "&lang=zh_CN";
			JSONObject userInfo = doGetJson(infoUrl);
			if (userInfo == null)
				return false;
			this.openid = userInfo.getString("openid");
			this.nickname = userInfo.getString("nickname");
			this.sex = userInfo.getString("sex");
			this.province = userInfo.getString("province");
			this.city = userInfo.getString("city");
			this.country = userInfo.getString("country");
			this.headimgurl = userInfo.getString("headimgurl");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public JSONObject doGetJson(String url){
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
		// TODO Auto-generated method stub

	}

}
