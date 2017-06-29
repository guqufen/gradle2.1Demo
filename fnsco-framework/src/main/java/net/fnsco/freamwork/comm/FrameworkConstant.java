package net.fnsco.freamwork.comm;

import java.util.Map;

import com.google.common.collect.Maps;

public class FrameworkConstant {
    public static String              IS_AUTHOR = "false";
    public static String              OPEN_IS_AUTHOR ="open.is.author";
    public static String              WEB_IS_AUTHOR ="web.is.author";
    public static String              APP_IS_AUTHOR ="app.is.author";
    public static String              TOKEN_ID           = "b9eccb7036f9719059b0f61076991b75";
    public static String              E_TOKEN_EMPTY      = "4010";
    public static String              E_TOKEN_ERROR      = "4011";
    public static String              E_NOT_LOGIN        = "4012";
    public static String              E_SYSTEM_EXCEPTION = "5000";
    public static String              E_APP_PASSWORD_ERROR = "5101";    //密码错误
    public static String              E_APP_MODIFYPASSWORD_ERROR = "5103";
    public static String              E_APP_NULLERROR="5102";         //空字段
    public static String              E_APP_CODE_ERROR="5103";        //验证码
    public static String              E_APP_INSERT="5104";            //注册失败
    public static Map<String, String> ERROR_MESSGE_MAP   = Maps.newHashMap();
    static {
        ERROR_MESSGE_MAP.put("4010", "非法请求,没有token");
        ERROR_MESSGE_MAP.put("4011", "非法请求,token不正确");
        ERROR_MESSGE_MAP.put("4012", "请登录");
        ERROR_MESSGE_MAP.put("5000", "系统异常");
        ERROR_MESSGE_MAP.put("5101", "密码错误");
        ERROR_MESSGE_MAP.put("5102", "空字段错误");
        ERROR_MESSGE_MAP.put("5103", "验证码错误");
        ERROR_MESSGE_MAP.put("5104", "注册失败");
    }
}
