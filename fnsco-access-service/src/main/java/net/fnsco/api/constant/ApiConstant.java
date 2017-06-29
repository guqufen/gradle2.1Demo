package net.fnsco.api.constant;

import java.util.Map;

import com.google.common.collect.Maps;

import net.fnsco.core.constants.CoreConstants;

public class ApiConstant extends CoreConstants {
    public static String THIS_PROGREM_URL   = "http://www.zheft.cn/download";

    public static String              E_APP_PASSWORD_ERROR = "5101";    //密码错误
    public static String              E_APP_MODIFYPASSWORD_ERROR = "5103";
    public static String              E_APP_NULLERROR="5102";         //空字段
 
    static {
        ERROR_MESSGE_MAP.put("4012", "请登录");
        ERROR_MESSGE_MAP.put("5000", "系统异常");
        ERROR_MESSGE_MAP.put("5101", "密码错误");
        ERROR_MESSGE_MAP.put("5102", "空字段错误");
        ERROR_MESSGE_MAP.put("4010", "非法请求,没有token");
        ERROR_MESSGE_MAP.put("4011", "非法请求,token不正确");
        ERROR_MESSGE_MAP.put("4012", "请登录");
        ERROR_MESSGE_MAP.put("5000", "系统异常");

}
}
