package net.fnsco.car.comm;

import net.fnsco.core.constants.CoreConstants;

public class CarConstant extends CoreConstants {
    public static String AN_PAY_URL               = "anPayURL";
    public static String AN_PAY_KEY               = "merchant.payKey";
    public static String MER_ID                   = "merchant.code";  //商户号
    public static String WEB_LOGIN_NULL           = "5101";    //用户名或密码为空
    public static String WEB_LOGIN_FAIL           = "5102";    //用户名或密码错误
    public static String WEB_LOGIN_USER_NOT_EXIST = "5103";    //用户名不存在
    public static String WEB_LOGIN_PASSWORD_FAIL  = "5104";    //密码错误
    public static String WEB_NOT_LOGIN            = "5105";    //SESSION失效，请重新登录
    public static String WEB_OLD_PASSWORD_NULL    = "5106";    //用户名或密码为空
    public static String OLD_PASSWORD             = "5107";
    public static String WEB_NEW_PASSWORD_NULL    = "5108";
    public static String WEB_OLD_PASSWORD_FAIL    = "5111";
    public static String WEB_BANK_CARD_NULL       = "5109";
    public static String WEB_TIME_ERROR           = "5110";
    static {
        ERROR_MESSGE_MAP.put(WEB_LOGIN_NULL, "用户名或密码为空");
        ERROR_MESSGE_MAP.put(WEB_LOGIN_FAIL, "用户名或密码错误");
        ERROR_MESSGE_MAP.put(WEB_LOGIN_USER_NOT_EXIST, "用户名不存在");
        ERROR_MESSGE_MAP.put(WEB_LOGIN_PASSWORD_FAIL, "密码错误");
        ERROR_MESSGE_MAP.put(WEB_NOT_LOGIN, "SESSION失效，请重新登录");
        ERROR_MESSGE_MAP.put(WEB_OLD_PASSWORD_NULL, "原密码不能为空");
        ERROR_MESSGE_MAP.put(WEB_NEW_PASSWORD_NULL, "新密码不能为空");
        ERROR_MESSGE_MAP.put(WEB_OLD_PASSWORD_FAIL, "原密码不正确");
        ERROR_MESSGE_MAP.put(WEB_BANK_CARD_NULL, "不支持该银行卡类型");
        ERROR_MESSGE_MAP.put(WEB_TIME_ERROR, "时间错误");
    }

}
