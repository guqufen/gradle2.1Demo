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
    public static final String COOKIE_USER_KEY = "cookie_user_key";
    public static String       OK              = "2000";
    public static String       E_COMM_BUSSICSS = "4000";
    static {
        ERROR_MESSGE_MAP.put(OK, "处理成功");
        ERROR_MESSGE_MAP.put(E_COMM_BUSSICSS, "一般业务错误");
    }
}
