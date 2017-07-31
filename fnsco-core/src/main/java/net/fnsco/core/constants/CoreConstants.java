/**
 * 
 */
package net.fnsco.core.constants;

import net.fnsco.freamwork.comm.FrameworkConstant;

/**@desc 常量类
 * @author tangliang
 * @date 2017年6月20日 下午5:41:40
 */
public class CoreConstants extends FrameworkConstant {
    /**
     * 登录用户cookie key值
     */
    //2开头表示正常，4开头表示权限方面的错误，51开头业务上的错误，50系统未知异常的错误
    public static final String COOKIE_USER_KEY     = "cookie_user_key";
    public static final String SESSION_USER_KEY    = "session_user_key";
    public static final String SESSION_USERID      = "session_userid";
    public static String       OK                  = "2000";
    public static String       E_COMM_BUSSICSS     = "5100";
    public static String       WEB_SAVE_OK         = "2001";            //web保存成功
    public static String       WEB_PUSH_DATE_ERROR = "5110";            //推送时间错误

    static {
        ERROR_MESSGE_MAP.put(OK, "处理成功");
        ERROR_MESSGE_MAP.put(E_COMM_BUSSICSS, "一般业务错误");
        ERROR_MESSGE_MAP.put(WEB_SAVE_OK, "保存成功");
        ERROR_MESSGE_MAP.put(WEB_PUSH_DATE_ERROR, "定时推送时间，不能早于当前时间");
    }
}
