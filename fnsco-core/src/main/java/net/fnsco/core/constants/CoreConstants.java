/**
 * 
 */
package net.fnsco.core.constants;

import java.util.Map;

import com.google.common.collect.Maps;

/**@desc 常量类
 * @author tangliang
 * @date 2017年6月20日 下午5:41:40
 */
public class CoreConstants {
    /**
     * 登录用户cookie key值
     */
    public static final String        COOKIE_USER_KEY  = "cookie_user_key";
    public static String              E_TOKEN_EMPTY    = "4000";
    public static String              E_TOKEN_ERROR    = "4001";
    public static Map<String, String> ERROR_MESSGE_MAP = Maps.newHashMap();
    static {
        ERROR_MESSGE_MAP.put("0000", "未知异常");
        ERROR_MESSGE_MAP.put("4000", "非法请求,没有token");
        ERROR_MESSGE_MAP.put("4001", "非法请求,token不正确");
    }
}
