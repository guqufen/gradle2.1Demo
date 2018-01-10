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
public class JuheDemoFaceUtil {
	
	protected static Logger              logger = LoggerFactory.getLogger(JuheDemoFaceUtil.class.getClass());
	
	//配置您申请的身份证识别KEY
	public static final String IMAGE_APPKEY ="nPtxyEXkU30ASqFlUJzIYdhad9BpgJQq";
	
	//配置您申请的身份证识别api_secret
	public static final String IMAGE_APPSECRET ="-Ur7FR0EVmOvNlC9Kgv8kI4__HylVyC3";
	
	//身份证识别的http地址
	private static String  URI_ID_IMAGE     = "https://api-cn.faceplusplus.com/cardpp/v1/ocridcard";

    /**
     * 图片识别
     * @param firstKey
     * @param side
     * @return
     */
    public static IdCardFaceDTO valiIdImage(String filePath) {
    	IdCardFaceDTO idCardFace = new IdCardFaceDTO();
    	Map<String,String> params = Maps.newHashMap();
    	params.put("api_key", IMAGE_APPKEY);
    	params.put("api_secret", IMAGE_APPSECRET);
    	String  imgFile = getImageStr(filePath) ;
    	params.put("image_base64", imgFile);
    	String resule = HttpClientUtil.doPost(URI_ID_IMAGE, params);
    	 JSONObject json = JSONObject.parseObject(resule);
    	 String error_message = json.getString("error_message");
    	 int time_used = json.getIntValue("time_used");
    	 JSONArray cards = json.getJSONArray("cards");
    	 if(cards!=null) {
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
    	    	idCardFace.setIssued_by(issued_by);
    	    	idCardFace.setValid_date(valid_date);
    	    }
    	 }
    	 idCardFace.setError_message(error_message);
    	 idCardFace.setTime_used(time_used);
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
}
