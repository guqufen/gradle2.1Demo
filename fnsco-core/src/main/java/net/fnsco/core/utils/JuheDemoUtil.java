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

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import net.fnsco.core.utils.dto.IdCardDTO;
import sun.misc.BASE64Encoder;

/**
 * @desc 身份证验证http接口的java代码调用示例
 * @author hjt
 * @version 
 * @Date 2017年12月12日 下午4:11:30
 */
public class JuheDemoUtil {
	
	protected static Logger              logger = LoggerFactory.getLogger(JuheDemoUtil.class.getClass());
	
	//配置您申请的身份证识别KEY
	public static final String IMAGE_APPKEY ="541432479017131ffa89cd68a5ed561c";
	
	//配置您申请的身份证实名认证KEY
	public static final String CARD_APPKEY ="d3abde4405b87887b184956b69a7c7b3";
		
    //身份证识别的http地址
    private static String  URI_ID_IMAGE     = "http://apis.juhe.cn/idimage/verify";
    
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
    public static IdCardDTO valiIdImage(String filePath, String side) {
    	IdCardDTO idCard = new IdCardDTO();
    	Map<String,String> params = Maps.newHashMap();
    	params.put("key", IMAGE_APPKEY);
    	String  imgFile = getImageStr(filePath) ;
    	params.put("image", imgFile);
    	params.put("side", side);
    	String resule = HttpClientUtil.doPost(URI_ID_IMAGE, params);
    	 JSONObject json = JSONObject.parseObject(resule);
    	 String reason = json.getString("reason");
    	 JSONObject obj = json.getJSONObject("result");
    	 idCard.setReason(reason);
    	 if("front".equals(side)) {
    		 String realname = obj.getString("realname");
    		 String idcard = obj.getString("idcard");
    		 int errorCode = json.getInteger("error_code");
    		 idCard.setRealname(realname);
    		 idCard.setIdcard(idcard);
    		 idCard.setErrorCode(errorCode);
    	 }else if("back".equals(side)){
    		 String end = obj.getString("end");
    		 int errorCode = json.getInteger("error_code");
    		 idCard.setErrorCode(errorCode);
    		 idCard.setEnd(end);
    	 }
    	return idCard;
    }
    /**
     * 实名认证
     * @param idcard
     * @param realname
     * @return
     */
    public static IdCardDTO valiIdCard(String idcard,String realname) {
    	IdCardDTO idCard = new IdCardDTO();
    	Map<String,String> params = Maps.newHashMap();
    	params.put("key", CARD_APPKEY);
    	params.put("idcard", idcard );
    	/*try {
    		realname = URLEncoder.encode(realname,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.info("URLEncoder编码失败");
			e.printStackTrace();
		}*/
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
    	//String resule = HttpUtils.get(URI_ID_CARD, params);
    	//String resule = HttpClientUtil.doPost(URI_ID_CARD, params);
    	 JSONObject json = JSONObject.parseObject(resule);
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
     * BASE64转换
     * @param firstKey
     * @return
     */
    public static String getImageStr(String filePath) {
    	//filePath = OssLoaclUtil.getFileToLocal(OssLoaclUtil.getHeadBucketName(), firstKey ,filePath);
        InputStream inputStream = null;
        //byte[] data = new byte[1024];
        byte[] data =null;
        try {
        	inputStream = new FileInputStream(filePath);
            //inputStream = OssLoaclUtil.getFileInputStream(OssLoaclUtil.getHeadBucketName(), firstKey);
           /* for (int n = 0; n != -1; ) {
                n = inputStream.read(data, 0, data.length);
            }*/
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
