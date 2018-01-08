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

/**
 * @desc 身份证验证http接口的java代码调用示例
 * @author hjt
 * @version 
 * @Date 2017年12月12日 下午4:11:30
 */
public class JuheDemoUtil2 {
	
	protected static Logger              logger = LoggerFactory.getLogger(JuheDemoUtil2.class.getClass());
	
	//配置您申请的身份证识别KEY
	public static final String IMAGE_APPKEY ="nPtxyEXkU30ASqFlUJzIYdhad9BpgJQq  -Ur7FR0EVmOvNlC9Kgv8kI4__HylVyC3";
	
	//身份证识别的http地址
	private static String  URI_ID_IMAGE     = "https://api-cn.faceplusplus.com/cardpp/v1/ocridcard";

    /**
     * 图片识别
     * @param firstKey
     * @param side
     * @return
     */
    public static IdCardDTO valiIdImage(String filePath, String side) {
    	IdCardDTO idCard = new IdCardDTO();
    	Map<String,String> params = Maps.newHashMap();
    	params.put("api_key", IMAGE_APPKEY);
    	params.put("api_secret", IMAGE_APPKEY);
    	String  imgFile = getImageStr(filePath) ;
    	params.put("image_base64", imgFile);
    	String resule = HttpClientUtil.doPost(URI_ID_IMAGE, params);
    	 JSONObject json = JSONObject.parseObject(resule);
    	 String reason = json.getString("cards");
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
}
