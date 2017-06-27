package net.fnsco.freamwork.comm;

import java.util.Map;

import com.google.common.collect.Maps;

public class Constant {
    public static String              TOKEN_ID         = "b9eccb7036f9719059b0f61076991b75";
    public static String              E_TOKEN_EMPTY    = "4000";
    public static String              E_TOKEN_ERROR    = "4001";
    public static Map<String, String> ERROR_MESSGE_MAP = Maps.newHashMap();
    static {
        ERROR_MESSGE_MAP.put("4000", "非法请求,没有token");
        ERROR_MESSGE_MAP.put("4001", "非法请求,token不正确");
    }
}
