package net.fnsco.api.constant;

import java.util.Map;

import com.google.common.collect.Maps;

public class ApiConstant {
    public static String THIS_PROGREM_URL = "http://www.zheft.cn/download";
    public static String              E_TOKEN_EMPTY    = "4000";
    public static String              E_TOKEN_ERROR    = "4001";
    public static Map<String, String> ERROR_MESSGE_MAP = Maps.newHashMap();
    static {
        ERROR_MESSGE_MAP.put("4000", "非法请求,没有token");
        ERROR_MESSGE_MAP.put("4001", "非法请求,token不正确");
    }
}
