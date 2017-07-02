package net.fnsco.api.constant;

import java.util.Map;

import com.google.common.collect.Maps;

import net.fnsco.core.constants.CoreConstants;

public class ApiConstant extends CoreConstants {
    public static String THIS_PROGREM_URL          = "app.download.url";
    public static String IS_AUTHOR                 = "false";
    public static String OPEN_IS_AUTHOR            = "open.is.author";
    public static String WEB_IS_AUTHOR             = "web.is.author";
    public static String APP_IS_AUTHOR             = "app.is.author";
    public static String TOKEN_ID                  = "b9eccb7036f9719059b0f61076991b75";
    public static String E_TOKEN_EMPTY             = "4010";
    public static String E_TOKEN_ERROR             = "4011";
    public static String E_NOT_LOGIN               = "4012";
    public static String E_SYSTEM_EXCEPTION        = "5000";
    //  原密码错误
    public static String E_PASSWORDTIME_ERROR      = "5104";                            //密码输入错误超过3次
    public static String E_MERCHANT_CODE_NULL      = "5023";                            //商铺码不能为空
    public static String E_USER_ID_NULL            = "5024";                            //用户ID不能为空
    public static String E_MERCHANT_CODE_NOT_EXIST = "5025";                            //此商铺码不存在，请重新输入
    public static String E_MERCHANT_CODE_OVERDUE   = "5026";

    public static String E_APP_PHONE_EMPTY         = "5011";                            //非法请求,没有手机号
    public static String E_APP_PHONE_ERROR         = "5012";                            //非法请求,手机号不正确

    public static String E_APP_PASSWORD_EMPTY      = "5013";                            //非法请求,没有密码
    public static String E_APP_PASSWORD_ERROR      = "5014";                            //非法请求,密码不正确

    public static String E_APP_ID_EMPTY            = "5015";                            //非法请求,没有id
    public static String E_APP_ID_ERROR            = "5016";                            //非法请求,id不正确

    public static String E_APP_DEVICETYPE_EMPTY    = "5017";                            //非法请求,没有deviceType
    public static String E_APP_DEVICETYPE_ERROR    = "5018";                            //非法请求,deviceType不正确

    public static String E_APP_CODE_EMPTY          = "5019";                            //非法请求,没有验证码
    public static String E_APP_CODE_ERROR          = "5020";                            //非法请求,验证码不正确

    public static String E_APP_DEVICEIDNULL        = "5021";                            //非法请求,设备号为空
    public static String E_APP_DEVICETYPENULL      = "5022";                            //非法请求,设备号不正确

    public static String E_ALREADY_LOGIN           = "5101";                            //用户已注册
    public static String E_REGISTER_ERROR          = "5102";                            //注册失败
    public static String E_CODEOVERTIME_ERROR      = "5103";                            //验证码超时
    public static String E_UPDATEPASSWORD_ERROR    = "5104";                            //更新密码失败
    public static String E_OLDPASSWORD_ERROR       = "5105";                            //  原密码错误

    static {
        //APP
        ERROR_MESSGE_MAP.put("5011", "非法请求,没有手机号");
        ERROR_MESSGE_MAP.put("5012", "非法请求,手机号不正确");
        ERROR_MESSGE_MAP.put("5013", "非法请求,没有密码");
        ERROR_MESSGE_MAP.put("5014", "非法请求,密码不正确");
        ERROR_MESSGE_MAP.put("5015", "非法请求,没有id");
        ERROR_MESSGE_MAP.put("5016", "非法请求,id不正确");
        ERROR_MESSGE_MAP.put("5017", "非法请求,没有deviceType");
        ERROR_MESSGE_MAP.put("5018", "非法请求,deviceType不正确");
        ERROR_MESSGE_MAP.put("5019", "非法请求,没有验证码");
        ERROR_MESSGE_MAP.put("5020", "非法请求,验证码不正确");
        ERROR_MESSGE_MAP.put("5021", "非法请求,设备号为空");
        ERROR_MESSGE_MAP.put("5022", "非法请求,设备号不正确");

        ERROR_MESSGE_MAP.put("5101", "用户已注册");
        ERROR_MESSGE_MAP.put("5102", "注册失败");
        ERROR_MESSGE_MAP.put("5103", "验证码超时");
        ERROR_MESSGE_MAP.put("5104", "更新密码失败");
        ERROR_MESSGE_MAP.put("5105", "原密码错误");

        ERROR_MESSGE_MAP.put("5023", "商铺码不能为空");
        ERROR_MESSGE_MAP.put("5024", "用户ID不能为空");
        ERROR_MESSGE_MAP.put("5025", "此商铺码不存在，请重新输入");
        ERROR_MESSGE_MAP.put("5026", "此商铺码已过期，请到pos机查询最新的商铺码");
    }

}
