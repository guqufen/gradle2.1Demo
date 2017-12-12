package net.fnsco.core.utils;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 身份证验证http接口的java代码调用示例
 * @desc TODO
 * @author hjt
 * @version 
 * @Date 2017年12月12日 下午4:11:30
 */
public class JuheDemoUtil {
	//配置您申请的KEY
	public static final String APPKEY ="*************************";

    //身份证识别的http地址
    private static String  URI_ID_IMAGE     = "http://apis.juhe.cn/idimage/verify";
    
    //身份证实名认证的http地址
    private static String  URI_ID_CARD      = "http://op.juhe.cn/idcard/query";
    
    public static void main(String[] args) {
		Map<String,String> params = Maps.newHashMap();
		
	}
}
