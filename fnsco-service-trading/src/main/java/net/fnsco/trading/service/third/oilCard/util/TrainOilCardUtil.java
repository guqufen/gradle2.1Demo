package net.fnsco.trading.service.third.oilCard.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.core.constants.SignatureHashMap;
import net.fnsco.core.utils.HttpUtils;
import net.fnsco.core.utils.SignatureUtil;
import net.sf.json.JSONObject;

public class TrainOilCardUtil {
    public static Logger       logger = LoggerFactory.getLogger(TrainOilCardUtil.class);
    
    public static final String shelfURL    = "http://[ip:prot]/fuel/shelf.json";
    public static final String chargeURL   = "http://[ip:prot]/common/query.json";
    public static final String queryURL    = "http://[ip:prot]/common/query.json";
    
    
    public static JSONObject shelf() {
    	String ispId = "9";//8：中石油 9：中石化
    	String invoiceFlag = "1";//是否提供发票：0：提供1：不提供（默认）
    	String accountNo = "10001";//10001开头，总共19位
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("isp_id", ispId);
    	params.put("invoice_flag", invoiceFlag);
    	params.put("account_no", accountNo);
    	Map<String, String> headers = new HashMap<String, String>();
    	headers.put("partner_id", "");//商户在系统中开设的唯一编号
    	headers.put("orgcode", "");//商户在系统中开设的运营机构
    	headers.put("agent_id", "");//代理商唯一编号
    	
    	headers.put("password", "");//代理商密码（即后台管理系统的登陆密码），使用3DES加密后转为16进制字符
    	Date date = new Date();
    	String timestamp = String.valueOf(date.getTime()/1000);  
    	headers.put("timestamp", timestamp);//时间戳，格式yyyyMMddHHmmss（年月日时分秒）充值下单交易会验证时间戳，如果与我方系统差距超过3分钟，交易会直接档回
    	headers.put("sign_type", "MD5");//MD5
    	SignatureHashMap sortedParams = new SignatureHashMap();
    	sortedParams.put("isp_id", ispId);
    	sortedParams.put("invoice_flag", invoiceFlag);
    	sortedParams.put("account_no", accountNo);
    	sortedParams.put("partner_id", "");
    	sortedParams.put("orgcode", "");
    	sortedParams.put("agent_id", "");
    	sortedParams.put("password", "");
    	sortedParams.put("timestamp", timestamp);
    	sortedParams.put("sign_type", "MD5");
    	String key ="";
		String sign = SignatureUtil.md5Signature(sortedParams,key);
    	headers.put("sign", sign);//签名，详见签名机制
    	String data = HttpUtils.get(shelfURL,params,headers);
        if (data == null) {
            logger.error("调用油卡充值接口返回空");
            return null;
        }
        JSONObject jSONObject = JSONObject.fromObject(data);
        String js = jSONObject.getString(data);
        return JSONObject.fromObject(data);
    }
    
    public static JSONObject charge() {
    	String payPassword = "";//支付密码，使用3DES加密
    	String orderNo = "10001";//商户交易订单号
    	String accountNo = "10001";//加油卡卡号
    	String facePrice = "";//充值面额，单位：元
    	String ispId = "8";//8：中石油 9：中石化
    	String invoiceFlag = "1";//是否提供发票：0：提供 1：不提供（默认）
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("pay_password", payPassword);
    	params.put("order_no", orderNo);
    	params.put("account_no", accountNo);
    	params.put("face_price", facePrice);
    	params.put("isp_id", ispId);
    	params.put("invoice_flag", invoiceFlag);
    	String data = HttpUtils.get(shelfURL,params);
        if (data == null) {
            logger.error("调用火车票查询余票接口返回空");
            return null;
        }
        JSONObject jSONObject = JSONObject.fromObject(data);
        String js = jSONObject.getString(data);
        return JSONObject.fromObject(data);
    }
    
    public static JSONObject query() {
    	String orderNo = "1";//商户交易订单号
    	String orderType = "09";//订单类型 09：加油卡充值
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("order_no", orderNo);
    	params.put("order_type", orderType);
    	String data = HttpUtils.get(shelfURL,params);
        if (data == null) {
            logger.error("调用火车票查询余票接口返回空");
            return null;
        }
        JSONObject jSONObject = JSONObject.fromObject(data);
        String js = jSONObject.getString(data);
        return JSONObject.fromObject(data);
    }
    
    public static void main(String[] args) {
        
    }
}
