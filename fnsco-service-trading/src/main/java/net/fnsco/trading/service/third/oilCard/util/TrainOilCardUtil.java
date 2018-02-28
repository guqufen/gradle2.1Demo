package net.fnsco.trading.service.third.oilCard.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.fnsco.core.constants.SignatureHashMap;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.SignatureUtil;
import net.sf.json.JSONObject;

public class TrainOilCardUtil {
    public static Logger       logger = LoggerFactory.getLogger(TrainOilCardUtil.class);
    
    public static String PARTNER_ID = "10330783"; // 商户编号
	public static String ORGCODE = "KX0000"; // 商户运营机构
	public static String AGENT_ID = "sc01132"; // 代理商编号
	public static String PASSWORD = "B72172AABF7FDD8B00D6094F6CB97423"; // 登陆密码
	public static String PAY_PASSWORD = "B72172AABF7FDD8B00D6094F6CB97423"; // 支付密码
	public static String SIGN_TYPE = "md5"; // 签名方式
	public static String KEY = "FD0762380D980D52"; // 密钥
    
    
    
    public static  String SHELF_URL    = "http://122.224.88.51:18080/fuel/shelf.json";
    public static  String CHARGE_URL   = "http://122.224.88.51:18080/fuel/charge.json";
    public static  String QUERY_URL    = "http://122.224.88.51:18080/common/query.json";
    public static String URL_ACCOUNT = "http://122.224.88.51:18080/common/account.json";
    
    
    public static JSONObject shelf() throws ClientProtocolException, IOException {
    	String ispId = "9";//8：中石油 9：中石化
    	String invoiceFlag = "1";//是否提供发票：0：提供1：不提供（默认）
    	String accountNo = "1000113200005437747";//10001开头，总共19位
    	//请求体
    	JSONObject body = new JSONObject();
    	body.put("isp_id", ispId);
    	body.put("invoice_flag", invoiceFlag);
    	body.put("account_no", accountNo);
    	//请求头
    	JSONObject header = getHeader();
    	/*JSONObject header = new JSONObject();
    	header.put("partner_id", PARTNER_ID);//商户在系统中开设的唯一编号
    	header.put("orgcode", ORGCODE);//商户在系统中开设的运营机构
    	header.put("agent_id", AGENT_ID);//代理商唯一编号
    	header.put("password", PASSWORD);//代理商密码（即后台管理系统的登陆密码），使用3DES加密后转为16进制字符
    	header.put("timestamp", DateUtils.getNowDateStr());//时间戳，格式yyyyMMddHHmmss（年月日时分秒）充值下单交易会验证时间戳，如果与我方系统差距超过3分钟，交易会直接档回
    	header.put("sign_type", SIGN_TYPE);//MD5
*/    	//签名
    	SignatureHashMap sortedParams = new SignatureHashMap();
    	sortedParams.put("isp_id", ispId);
    	sortedParams.put("invoice_flag", invoiceFlag);
    	sortedParams.put("account_no", accountNo);
    	sortedParams.put("partner_id", PARTNER_ID);
    	sortedParams.put("orgcode", ORGCODE);
    	sortedParams.put("agent_id", AGENT_ID);
    	sortedParams.put("password", PASSWORD);
    	sortedParams.put("timestamp", DateUtils.getNowDateStr());
    	sortedParams.put("sign_type", SIGN_TYPE);
		String sign = SignatureUtil.md5Signature(sortedParams,KEY);
		header.put("sign", sign);//签名，详见签名机制
    	JSONObject obj = new JSONObject();
		obj.put("header", header);
		obj.put("body", body);
		
		String message = obj.toString();
    	String data = post(SHELF_URL,message);
    	System.out.println("返回参数:"+data);
        if (data == null) {
            logger.error("调用加油卡货架查询接口返回空");
            return null;
        }
        return JSONObject.fromObject(data);
    }
    
    public static JSONObject charge() throws ClientProtocolException, IOException {
    	String orderNo = "10001";//商户交易订单号
    	String accountNo = "10001";//加油卡卡号
    	String facePrice = "";//充值面额，单位：元
    	String ispId = "8";//8：中石油 9：中石化
    	String invoiceFlag = "1";//是否提供发票：0：提供 1：不提供（默认）
    	//请求体
    	JSONObject body = new JSONObject();
    	body.put("pay_password", PAY_PASSWORD);
    	body.put("order_no", orderNo);
    	body.put("account_no", accountNo);
    	body.put("face_price", facePrice);
    	body.put("isp_id", ispId);
    	body.put("invoice_flag", invoiceFlag);
    	//请求头
    	JSONObject header = getHeader();
    	/*JSONObject header = new JSONObject();
    	header.put("partner_id", "10330783");//商户在系统中开设的唯一编号
    	header.put("orgcode", "KX0000");//商户在系统中开设的运营机构
    	header.put("agent_id", "sc01132");//代理商唯一编号
    	header.put("password", "B72172AABF7FDD8B00D6094F6CB97423");//代理商密码（即后台管理系统的登陆密码），使用3DES加密后转为16进制字符
    	header.put("timestamp", DateUtils.getNowDateStr());//时间戳，格式yyyyMMddHHmmss（年月日时分秒）充值下单交易会验证时间戳，如果与我方系统差距超过3分钟，交易会直接档回
    	header.put("sign_type", "MD5");//MD5
*/    	//签名
    	SignatureHashMap sortedParams = new SignatureHashMap();
    	sortedParams.put("pay_password", PAY_PASSWORD);
    	sortedParams.put("order_no", orderNo);
    	sortedParams.put("account_no", accountNo);
    	sortedParams.put("face_price", facePrice);
    	sortedParams.put("isp_id", ispId);
    	sortedParams.put("invoice_flag", invoiceFlag);
    	sortedParams.put("partner_id", PARTNER_ID);
    	sortedParams.put("orgcode", ORGCODE);
    	sortedParams.put("agent_id", AGENT_ID);
    	sortedParams.put("password", PASSWORD);
    	sortedParams.put("timestamp", DateUtils.getNowDateStr());
    	sortedParams.put("sign_type", SIGN_TYPE);
		String sign = SignatureUtil.md5Signature(sortedParams,KEY);
		header.put("sign", sign);//签名，详见签名机制
    	JSONObject obj = new JSONObject();
		obj.put("header", header);
		obj.put("body", body);
		
		String message = obj.toString();
    	String data = post(CHARGE_URL,message);
    	System.out.println("返回参数:"+data);
        if (data == null) {
            logger.error("调用加油卡充值下单接口返回空");
            return null;
        }
        return JSONObject.fromObject(data);
    }
    
    public static JSONObject query(String orderNo)  throws Exception  {
    	//String orderNo = "1";//商户交易订单号
    	String orderType = "09";//订单类型 09：加油卡充值
    	//请求体
    	JSONObject body = new JSONObject();
    	body.put("order_no", orderNo);
    	body.put("order_type", orderType);
    	//请求头
    	JSONObject header = getHeader();
    	/*JSONObject header = new JSONObject();
    	header.put("partner_id", PARTNER_ID);//商户在系统中开设的唯一编号
    	header.put("orgcode", ORGCODE);//商户在系统中开设的运营机构
    	header.put("agent_id", AGENT_ID);//代理商唯一编号
    	header.put("password", PASSWORD);//代理商密码（即后台管理系统的登陆密码），使用3DES加密后转为16进制字符
    	header.put("timestamp", DateUtils.getNowDateStr());//时间戳，格式yyyyMMddHHmmss（年月日时分秒）充值下单交易会验证时间戳，如果与我方系统差距超过3分钟，交易会直接档回
    	header.put("sign_type", SIGN_TYPE);//MD5
*/    	//设置签名
    	SignatureHashMap sortedParams = new SignatureHashMap();
    	sortedParams.put("order_no", orderNo);
    	sortedParams.put("order_type", orderType);
    	sortedParams.put("partner_id", PARTNER_ID);
    	sortedParams.put("orgcode", ORGCODE);
    	sortedParams.put("agent_id", AGENT_ID);
    	sortedParams.put("password", PASSWORD);
    	sortedParams.put("timestamp", DateUtils.getNowDateStr());
    	sortedParams.put("sign_type", SIGN_TYPE);
		String sign = SignatureUtil.md5Signature(sortedParams,KEY);
		header.put("sign", sign);//签名，详见签名机制
		
		JSONObject obj = new JSONObject();
		obj.put("header", header);
		obj.put("body", body);
		
		String message = obj.toString();
    	String data = post(QUERY_URL,message);
    	
    	System.out.println("返回参数:"+data);
        if (data == null) {
            logger.error("调用订单查询结果接口返回空");
            return null;
        }
        return JSONObject.fromObject(data);
    }
    
    /**
	 * 余额查询
	 */
	public static void account() throws Exception {
		JSONObject header = getHeader();
		JSONObject body = new JSONObject();
		body.put("query_type", "1");
		//签名
    	SignatureHashMap sortedParams = new SignatureHashMap();
    	sortedParams.put("query_type", "1");
    	sortedParams.put("partner_id", PARTNER_ID);
    	sortedParams.put("orgcode", ORGCODE);
    	sortedParams.put("agent_id", AGENT_ID);
    	sortedParams.put("password", PASSWORD);
    	sortedParams.put("timestamp", DateUtils.getNowDateStr());
    	sortedParams.put("sign_type", SIGN_TYPE);
		String sign = SignatureUtil.md5Signature(sortedParams,KEY);
		header.put("sign", sign);//签名，详见签名机制
		
		JSONObject obj = new JSONObject();
		obj.put("header", header);
		obj.put("body", body);

		String message = obj.toString();

		String retMsg = post(URL_ACCOUNT, message);

		if (retMsg != null) {
			JsonParser parser = new JsonParser();
			JsonObject ret = parser.parse(retMsg).getAsJsonObject();

			JsonObject result = ret.get("result").getAsJsonObject();
			String retcode = result.get("ret_code").getAsString();
			String retmsg = result.get("ret_msg").getAsString();

			if ("10000000".equals(retcode)) {
				logger.info("查询成功");

				JsonObject retbody = ret.get("body").getAsJsonObject();
				String balance = retbody.get("balance").getAsString();

				logger.info("余额：{}", balance);

			} else if ("11310100".equals(retcode)) {
				logger.info("系统无此订单");
			} else {
				logger.info("查询失败，失败原因：[{}]", retmsg);
			}
		}
	}
    
    public static void main(String[] args) throws Exception {
    	account();
    }
    
    public static String post(String url, String message) throws ClientProtocolException, IOException {
    	logger.info("发送消息：{}", message);

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);

		StringEntity entity = new StringEntity(message, "UTF-8");
		entity.setContentType("application/json;charset=UTF-8");
		httppost.setEntity(entity);
		httppost.addHeader("Accept", "application/json");
		httppost.addHeader("ContentType", "application/json;charset=UTF-8");
		System.out.println(httppost.toString());
		CloseableHttpResponse response = httpclient.execute(httppost);

		try {
			int code = response.getStatusLine().getStatusCode();
			logger.error("code-->{}", code);
			if (HttpURLConnection.HTTP_OK == code) {
				String retMsg = EntityUtils.toString(response.getEntity(), "UTF-8");
				logger.info("返回消息：{}", retMsg);

				return retMsg;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			response.close();
		}
		return null;
	}
    public static String sign(JsonObject... jsonObjects) {
		StringBuffer sb = new StringBuffer();

		Map<String, String> attrs = new TreeMap<String, String>();
		for (JsonObject obj : jsonObjects) {
			for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue().getAsString();
				if (value != null && !"".equals(value.trim())) {
					attrs.put(key, value);
				}
			}
		}

		Set<Map.Entry<String, String>> es = attrs.entrySet();
		Iterator<Map.Entry<String, String>> it = es.iterator();

		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			String key = entry.getKey();
			String value = entry.getValue();
			sb.append(key).append("=").append(value).append("&");
		}
		sb.deleteCharAt(sb.length() - 1).append(KEY);

		logger.info("签名前串：{}", sb.toString());

		String sign = DigestUtils.md5Hex(sb.toString().getBytes(Charset.forName("UTF-8")));

		logger.info("签名：{}", sign);
		return sign;
	}
    public static JSONObject getHeader() {
    	//请求头
    	JSONObject header = new JSONObject();
    	header.put("partner_id", PARTNER_ID);//商户在系统中开设的唯一编号
    	header.put("orgcode", ORGCODE);//商户在系统中开设的运营机构
    	header.put("agent_id", AGENT_ID);//代理商唯一编号
    	header.put("password", PASSWORD);//代理商密码（即后台管理系统的登陆密码），使用3DES加密后转为16进制字符
    	header.put("timestamp", DateUtils.getNowDateStr());//时间戳，格式yyyyMMddHHmmss（年月日时分秒）充值下单交易会验证时间戳，如果与我方系统差距超过3分钟，交易会直接档回
    	header.put("sign_type", SIGN_TYPE);//MD5
    	return header;
	}
}
