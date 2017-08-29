package net.fnsco.bigdata.api.constant;

import net.fnsco.core.constants.CoreConstants;

public class BigdataConstant extends CoreConstants {
    public static String E_USERID_NULL             = "5110";
    public static String E_UPDATE_FAIL             = "5111";
    public static String WEB_MER_CHANNEL_NOTUNIQUE = "5902"; //商户渠道号和渠道组合要唯一
    static {
        ERROR_MESSGE_MAP.put(WEB_MER_CHANNEL_NOTUNIQUE, "渠道商户号和渠道类型组合需要唯一");
        ERROR_MESSGE_MAP.put(E_USERID_NULL, "入参ID为null");
        ERROR_MESSGE_MAP.put(E_UPDATE_FAIL, "更新失败");
    }
}
