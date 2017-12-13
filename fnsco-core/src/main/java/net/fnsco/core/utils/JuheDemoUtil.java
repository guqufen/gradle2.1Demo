package net.fnsco.core.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

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
	//配置您申请的身份证识别KEY
	public static final String IMAGE_APPKEY ="541432479017131ffa89cd68a5ed561c";
	
	//配置您申请的身份证实名认证KEY
	public static final String CARD_APPKEY ="d3abde4405b87887b184956b69a7c7b3";
		
    //身份证识别的http地址
    private static String  URI_ID_IMAGE     = "http://apis.juhe.cn/idimage/verify";
    
    //身份证实名认证的http地址
    private static String  URI_ID_CARD      = "http://op.juhe.cn/idcard/query";
    /**
     * 身份证验证
     * @param image
     * @param side
     * @return
     */
    public static IdCardDTO idVerification(String image,String side) {
    	IdCardDTO idCard = valiIdImage(image, side);
    	if(idCard.getErrorCode()==0) {
    		return valiIdCard(idCard.getIdcard(),idCard.getRealname());
    	}
    	return idCard;
    }
    /**
     * 图片识别
     * @param image
     * @param side
     * @return
     */
    public static IdCardDTO valiIdImage(String image,String side) {
    	IdCardDTO idCard = new IdCardDTO();
    	Map<String,String> params = Maps.newHashMap();
    	params.put("key", IMAGE_APPKEY);
    	String  imgFile = getImageStr(image) ;
    	params.put("image", imgFile);
    	params.put("side", side);
    	String resule = HttpClientUtil.doPost(URI_ID_IMAGE, params);
    	 JSONObject json = JSONObject.parseObject(resule);
    	 String reason = json.getString("reason");
    	 JSONObject obj = json.getJSONObject("result");
    	 String realname = obj.getString("realname");
    	 String idcard = obj.getString("idcard");
    	 int errorCode = json.getInteger("error_code");
    	 idCard.setReason(reason);
    	 idCard.setRealname(realname);
    	 idCard.setIdcard(idcard);
    	 idCard.setErrorCode(errorCode);
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
    	params.put("realname", realname);
    	String resule = HttpClientUtil.doPost(URI_ID_CARD, params);
    	 JSONObject json = JSONObject.parseObject(resule);
    	 String reason = json.getString("reason");
    	 JSONObject obj = json.getJSONObject("result");
    	 String name = obj.getString("realname");
    	 String card = obj.getString("idcard");
    	 String res = obj.getString("res");
    	 idCard.setReason(reason);
    	 idCard.setRealname(name);
    	 idCard.setIdcard(card);
    	 idCard.setRes(Integer.valueOf(res));
    	return idCard;
    }
    /**
     * BASE64转换
     * @param imgFile
     * @return
     */
    public static String getImageStr(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
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
