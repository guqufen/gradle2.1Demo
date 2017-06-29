package net.fnsco.api.constant;

import net.fnsco.core.constants.CoreConstants;

public class ApiConstant extends CoreConstants {
    public static String THIS_PROGREM_URL   = "http://www.zheft.cn/download";
    static {
        ERROR_MESSGE_MAP.put("4010", "非法请求,没有token");
        ERROR_MESSGE_MAP.put("4011", "非法请求,token不正确");
        ERROR_MESSGE_MAP.put("4012", "请登录");
        ERROR_MESSGE_MAP.put("5000", "系统异常");
    }
}
