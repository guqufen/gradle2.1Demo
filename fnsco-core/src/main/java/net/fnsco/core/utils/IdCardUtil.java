package net.fnsco.core.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import net.fnsco.core.utils.dto.IdCardDTO;
import net.fnsco.core.utils.dto.IdCardFaceDTO;
import sun.misc.BASE64Encoder;

/**
 * @desc 身份证验证http接口的java代码调用示例
 * @author hjt
 * @version 
 * @Date 2017年12月12日 下午4:11:30
 */
public class IdCardUtil {
	protected static Logger              logger = LoggerFactory.getLogger(IdCardUtil.class.getClass());
		
	//身份证识别的http地址
	private static String  URI_ID_IMAGE     = "https://api-cn.faceplusplus.com/cardpp/v1/ocridcard";
    //身份证实名认证的http地址
    private static String  URI_ID_CARD      = "http://op.juhe.cn/idcard/query";
    
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
    /**
     * 图片识别
     * @param firstKey
     * @param side
     * @return
     */
    public static IdCardFaceDTO valiIdImage(String filePath,String IMAGE_APPKEY,String IMAGE_APPSECRET) {
    	Map<String,String> params = Maps.newHashMap();
    	params.put("api_key", IMAGE_APPKEY);
    	params.put("api_secret", IMAGE_APPSECRET);
    	String  imgFile = getImageStr(filePath) ;
    	params.put("image_base64", imgFile);
    	logger.error(filePath + "调用图片识别接口！");
    	String resule = HttpClientUtil.doPost(URI_ID_IMAGE, params);
    	logger.error( "返回参数：" + resule.toString());
    	JSONObject json = JSONObject.parseObject(resule);
    	return parsingImageJson(json);
    }
    /**
     * 图片识别返回参数处理
     * @param firstKey
     * @param side
     * @return
     */
    public static IdCardFaceDTO parsingImageJson(JSONObject json) {
    	IdCardFaceDTO idCardFace = new IdCardFaceDTO();
    	 String error_message = json.getString("error_message");
    	 int time_used = json.getIntValue("time_used");
    	 JSONArray cards = json.getJSONArray("cards");
    	 if(cards==null) {
    		 idCardFace.setErrorMessage("2100033");
        	 idCardFace.setTimeUsed(time_used);
        	return idCardFace;
    	 }
    	 if(cards.size()==0) {
    		 idCardFace.setErrorMessage("2100033");
        	 idCardFace.setTimeUsed(time_used);
        	return idCardFace;
    	 }
    		//取出数组第一个元素  
    	    JSONObject reason = cards.getJSONObject(0);  
    	    String side = reason.getString("side");
    	    idCardFace.setSide(side);
    	    if("front".equals(side)) {
    	    	String gender = reason.getString("gender");
    	    	String name= reason.getString("name");
    	    	String id_card_number = reason.getString("id_card_number");
    	    	String birthday = reason.getString("birthday");
    	    	String race = reason.getString("race");
    	    	String address = reason.getString("address");
    	    	int type = reason.getInteger("type");
    	    	idCardFace.setAddress(address);
    	    	idCardFace.setBirthday(birthday);
    	    	idCardFace.setGender(gender);
    	    	idCardFace.setId_card_number(id_card_number);
    	    	idCardFace.setName(name);
    	    	idCardFace.setType(type);
    	    	idCardFace.setRace(race);
    	    }else if("back".equals(side)) {
    	    	String issued_by = reason.getString("issued_by");
    	    	String valid_date = reason.getString("valid_date");
    	    	idCardFace.setIssuedBy(issued_by);
    	    	idCardFace.setValidDate(valid_date);
    	    }
    	 
    	 idCardFace.setErrorMessage(error_message);
    	 idCardFace.setTimeUsed(time_used);
    	return idCardFace;
    }
    /**
     * BASE64转换
     * @param firstKey
     * @return
     */
    public static String getImageStr(String filePath) {
        InputStream inputStream = null;
        byte[] data =null;
        try {
        	inputStream = new FileInputStream(filePath);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
    /**
     * 实名认证
     * @param idcard
     * @param realname
     * @return
     */
    public static IdCardDTO valiIdCard(String idcard,String realname,String CARD_APPKEY) {
    	Map<String,String> params = Maps.newHashMap();
    	params.put("key", CARD_APPKEY);
    	params.put("idcard", idcard );
    	params.put("realname", realname);
    	String resule = null;
    	logger.info("实名认证参数："+params.toString());
		try {
			resule = net(URI_ID_CARD, params,"GET");
			logger.info("实名认证返回值："+resule.toString());
		} catch (Exception e) {
			logger.info("实名认证验证失败");
			e.printStackTrace();
		}
		JSONObject json = JSONObject.parseObject(resule);
    	return parsingCardJson(json);
    }
    /**
     * 实名认证返回参数处理
     * @param idcard
     * @param realname
     * @return
     */
    public static IdCardDTO parsingCardJson(JSONObject json) {
    	IdCardDTO idCard = new IdCardDTO();
    	 int errorCode = json.getInteger("error_code");
    	 String reason = json.getString("reason");
    	 JSONObject obj = json.getJSONObject("result");
    	 if(obj!=null) {
    		 String name = obj.getString("realname");
        	 String card = obj.getString("idcard");
        	 int res = obj.getInteger("res");
        	 idCard.setRealname(name);
        	 idCard.setIdcard(card);
        	 idCard.setRes(res); 
    	 }
    	 idCard.setErrorCode(errorCode);
    	 idCard.setReason(reason);
    	return idCard;
    }
    /**
    *
    * @param strUrl 请求地址
    * @param params 请求参数
    * @param method 请求方法
    * @return  网络请求字符串
    * @throws Exception
    */
   public static String net(String strUrl, Map params,String method) throws Exception {
       HttpURLConnection conn = null;
       BufferedReader reader = null;
       String rs = null;
       try {
           StringBuffer sb = new StringBuffer();
           if(method==null || method.equals("GET")){
               strUrl = strUrl+"?"+urlencode(params);
           }
           URL url = new URL(strUrl);
           conn = (HttpURLConnection) url.openConnection();
           if(method==null || method.equals("GET")){
               conn.setRequestMethod("GET");
           }else{
               conn.setRequestMethod("POST");
               conn.setDoOutput(true);
           }
           conn.setRequestProperty("User-agent", userAgent);
           conn.setUseCaches(false);
           conn.setConnectTimeout(DEF_CONN_TIMEOUT);
           conn.setReadTimeout(DEF_READ_TIMEOUT);
           conn.setInstanceFollowRedirects(false);
           conn.connect();
           if (params!= null && method.equals("POST")) {
               try {
                   DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                       out.writeBytes(urlencode(params));
               } catch (Exception e) {
                   // TODO: handle exception
               }
           }
           InputStream is = conn.getInputStream();
           reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
           String strRead = null;
           while ((strRead = reader.readLine()) != null) {
               sb.append(strRead);
           }
           rs = sb.toString();
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           if (reader != null) {
               reader.close();
           }
           if (conn != null) {
               conn.disconnect();
           }
       }
       return rs;
   }

   //将map型转为请求参数型
   public static String urlencode(Map<String,Object>data) {
       StringBuilder sb = new StringBuilder();
       for (Map.Entry i : data.entrySet()) {
           try {
               sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
           } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
           }
       }
       return sb.toString();
   }
}
