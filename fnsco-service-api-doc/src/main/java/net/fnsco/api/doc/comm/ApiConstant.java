package net.fnsco.api.doc.comm;

import net.fnsco.core.constants.CoreConstants;

public class ApiConstant extends CoreConstants {
    /**
     * 用户未注册
     */
    public static final String LOGIN_001 = "51001";

    /**
     * 用户已注册
     */
    public static final String LOGIN_002 = "51002";

    /**
     * 验证码错误
     */
    public static final String LOGIN_003 = "51003";

    /**
     * 用户名或密码错误
     */
    public static final String LOGIN_004 = "51004";

    /**
     * 账号未激活
     */
    public static final String LOGIN_005 = "51005";

    /**
     * 账号被锁定
     */
    public static final String LOGIN_006 = "51006";

    /**
     * 登陆/注册方式与提交信息不匹配
     */
    public static final String LOGIN_007 = "51007";

    /**
     * 原密码错误
     */
    public static final String LOGIN_008 = "51008";

    /**
     * 账号已激活
     */
    public static final String LOGIN_009 = "51009";

    /**
     * 登录失败次数超过限制，账号被锁定，请联系管理员或第二天自动解锁
     */
    public static final String LOGIN_010 = "51010";

    /**
     * 项目至少需要一名管理员
     */
    public static final String PROJ_001  = "52001";

    /**
     * 只有管理员才有该权限
     */
    public static final String PROJ_002  = "52002";

    /**
     * 已存在相同的请求url和请求方式
     */
    public static final String DOC_001   = "53001";

    /**
     * swagger文档格式错误
     */
    public static final String DOC_002   = "53002";
    public static String WEB_LOGIN_NULL           = "5101"; //用户名或密码为空
    public static String WEB_LOGIN_FAIL           = "5102"; //用户名或密码错误
    public static String WEB_LOGIN_USER_NOT_EXIST = "5103"; //用户名不存在
    public static String WEB_LOGIN_PASSWORD_FAIL  = "5104"; //密码错误
    public static String WEB_NOT_LOGIN            = "5105"; //SESSION失效，请重新登录
    public static String WEB_OLD_PASSWORD_NULL    = "5106"; //用户名或密码为空
    public static String OLD_PASSWORD             = "5107";
    public static String WEB_NEW_PASSWORD_NULL    = "5108";
    public static String WEB_OLD_PASSWORD_FAIL    = "5108";
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
