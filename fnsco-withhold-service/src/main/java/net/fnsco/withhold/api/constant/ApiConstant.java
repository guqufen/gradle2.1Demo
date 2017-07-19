package net.fnsco.withhold.api.constant;

import net.fnsco.core.constants.CoreConstants;

public class ApiConstant extends CoreConstants {
     
    
    public static String E_MERCHANT_CODE_NULL      = "6023";                            //商铺码不能为空

    static {
        ERROR_MESSGE_MAP.put(E_MERCHANT_CODE_NULL, "用户名或密码为空");
         
    }

}
