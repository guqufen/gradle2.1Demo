package net.fnsco.trading.constant;

import net.fnsco.core.constants.CoreConstants;

public class E789ApiConstant extends CoreConstants {
   
    public static String E_DATA_SOURCE_TIMEOUT     = "228701";                          //数据源超时
    public static String E_PAR_ERROR               = "228702";                          //参数错误
    public static String E_IMAGE_TYPE_ERROR 	   = "228703";                          //图片类型错误
    public static String E_IMAGE_LENGTH_ERROR      = "228704";                          //图片长宽错误
    public static String E_IMAGE_SIZE_ERROR    	   = "228705";                          //图片大小错误
    public static String E_IDENTIFY_FAILURE        = "228706";                          //识别失败(计费一次,识别身份证错误，出现此问题的原因一般为：您上传了非身份证图片或您上传的身份证图片不完整)
    public static String E_OTHER_ERROR         	   = "228707";                          //其他错误,具体参照reason
    public static String E_DISSUPOPORT_GET         = "228708";                          //不支持GET请求
    
    public static String E_NOT_FOUND_PRE     	   = "210301";                          //库中无此身份证记录
    public static String E_SERVER_EXC              = "210302";                          //第三方服务器异常
    public static String E_SERVER_MAINTENANCE 	   = "210303";                          //服务器维护
    public static String E_PAR_ERROR_ID 		   = "210304";                          //参数异常
    public static String E_NETWORK_ERROR    	   = "210305";                          //网络错误，请重试
    public static String E_DATA_SOURCE_ERROR       = "210306";                          //数据源错误，具体参照reason
    
    public static String E_IDCARD_OUT_OF_TIME      = "210000";                          //身份证已过期

    static {
    	//身份识别证错误信息
        ERROR_MESSGE_MAP.put(E_DATA_SOURCE_TIMEOUT, "数据源超时");
        ERROR_MESSGE_MAP.put(E_PAR_ERROR, "参数错误");
        ERROR_MESSGE_MAP.put(E_IMAGE_TYPE_ERROR, "图片类型错误");
        ERROR_MESSGE_MAP.put(E_IMAGE_LENGTH_ERROR, "图片长宽错误");
        ERROR_MESSGE_MAP.put(E_IMAGE_SIZE_ERROR, "图片大小错误");
        ERROR_MESSGE_MAP.put(E_IDENTIFY_FAILURE, "识别失败(计费一次,识别身份证错误，出现此问题的原因一般为：您上传了非身份证图片或您上传的身份证图片不完整)");
        ERROR_MESSGE_MAP.put(E_OTHER_ERROR, "其他错误,具体参照reason");
        ERROR_MESSGE_MAP.put(E_DISSUPOPORT_GET, "不支持GET请求");
        
        //身份证实名验证证错误信息
        ERROR_MESSGE_MAP.put(E_NOT_FOUND_PRE, "库中无此身份证记录");
        ERROR_MESSGE_MAP.put(E_SERVER_EXC, "第三方服务器异常");
        ERROR_MESSGE_MAP.put(E_SERVER_MAINTENANCE, "服务器维护");
        ERROR_MESSGE_MAP.put(E_PAR_ERROR_ID, "参数异常");
        ERROR_MESSGE_MAP.put(E_NETWORK_ERROR, "网络错误，请重试");
        ERROR_MESSGE_MAP.put(E_DATA_SOURCE_ERROR, "数据源错误，具体参照reason");
    }

}
