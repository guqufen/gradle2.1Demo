package net.fnsco.order.api.constant;

import net.fnsco.core.constants.CoreConstants;

public class ApiConstant extends CoreConstants {
    public static String THIS_PROGREM_URL          = "app.download.url";
    public static String THIS_ANDROID_URL          = "app.download.android";
    public static String THIS_IOS_URL              = "app.download.ios";
    public static String IS_AUTHOR                 = "false";
    public static String OPEN_IS_AUTHOR            = "open.is.author";
    public static String WEB_IS_AUTHOR             = "web.is.author";
    public static String APP_IS_AUTHOR             = "app.is.author";
    public static String TOKEN_ID                  = "b9eccb7036f9719059b0f61076991b75";
    
    public static String E_MERCHANT_CODE_NULL      = "5023";                            //商铺码不能为空
    public static String E_USER_ID_NULL            = "5024";                            //用户ID不能为空
    public static String E_MERCHANT_CODE_NOT_EXIST = "5025";                            //此商铺码不存在，请重新输入
    public static String E_MERCHANT_CODE_OVERDUE   = "5026";                            //此商铺码已过期，请到pos机查询最新的商铺码
    public static String E_MERCHANT_ALREADY_REF    = "5027";                            //已关联此商铺，请勿重复关联
    public static String E_MERCHANT_IS_DEL         = "5028";                            //此商户已删除，关联失败
    public static String E_INNER_CODE_NULL         = "5029";                            //商户内部商户号不能为空
    public static String E_PAGE_NUM_NULL           = "5030";                            //页码为空或不合规范
    public static String E_CONFIG_NAME_NULL        = "5031";                            //type为空
    public static String E_STRING_TOO_LENGTH       = "5032";                            //入参太常
    
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
    //  原密码错误
    public static String E_PASSWORDTIME_ERROR      = "5104";                            //密码输入错误超过3次
    public static String E_UPDATEPASSWORD_ERROR    = "5107";                            //更新密码失败
    public static String E_OLDPASSWORD_ERROR       = "5105";                            //  原密码错误
    public static String E_NOREGISTER_LOGIN        = "5106";                            //用户未注册

    public static String E_USERID_NULL             = "5110";
    public static String E_UPDATE_FAIL             = "5111";

    public static String E_EDITION_ERROR           = "5107";                            //版本号格式错误
    public static String E_CODE_INVALID            = "5108";                            //验证码已失效
    public static String E_APPCODE_NULL            = "5109";                            //非法请求,手机码为空
    public static String E_VERSION_NULL            = "5112";                            //非法请求,版本号为空
    public static String E_ACCOUNTLOCKOUT_ERROR    = "5113";                            //非法请求,用户账号被注销
    public static String E_LOGIN_ERROR             = "5114";                            //登录失败
    public static String E_LOGINMSG_ERROR          = "5115";                            //用户名或密码不正确
    public static String E_LOGINOUT_ERROR          = "5116";                         //退出登录失败
    public static String E_PHONETYPE_ERROR         = "5117";                          //手机类型错误
    public static String E_NOSHOPKEEPER_ERROR      = "5118";                          // 该用户不是店主
    public static String E_DELETEBAND_ERROR        = "5119";                          // 删除绑定失败
    public static String E_NOBAND_ERROR            = "5120";                          // 该用户没有绑定商户
    public static String E_ONLYSHOPKEEPER_ERROR    = "5121";                          //  一个店铺只能有一个店主
    public static String E_CHANGEROLE_ERROR        = "5122";                          //  更改角色失败
    public static String E_FORCEDLOGINOUT_ERROR    = "5123";                          //  强制退出失败
    public static String E_EMPTYDEVICETOKEN_ERROR  = "5124";                          // 清空友盟设备号失败
    public static String E_SUGGESTEMPTY_ERROR      = "5125";                          // 反馈内容不能为空
    
    public static String       WEB_LOGIN_NULL = "5900";//用户名或密码为空
    public static String       WEB_LOGIN_FAIL ="5901";//用户名或密码错误
    public static String       WEB_MER_CHANNEL_NOTUNIQUE = "5902";//商户渠道号和渠道组合要唯一
    
    static {
        ERROR_MESSGE_MAP.put(WEB_LOGIN_NULL, "用户名或密码为空");
        ERROR_MESSGE_MAP.put(WEB_LOGIN_FAIL, "用户名或密码错误");
        ERROR_MESSGE_MAP.put(WEB_MER_CHANNEL_NOTUNIQUE, "渠道商户号和渠道类型组合需要唯一");
        
        ERROR_MESSGE_MAP.put(E_MERCHANT_CODE_NULL, "商铺码不能为空");
        ERROR_MESSGE_MAP.put(E_USER_ID_NULL, "用户ID不能为空");
        ERROR_MESSGE_MAP.put(E_MERCHANT_CODE_NOT_EXIST, "此商铺码不存在，请重新输入");
        ERROR_MESSGE_MAP.put(E_MERCHANT_CODE_OVERDUE, "此商铺码已过期,请到pos机查询最新的商铺码");
        ERROR_MESSGE_MAP.put(E_MERCHANT_ALREADY_REF, "已关联此商铺，请勿重复关联");
        ERROR_MESSGE_MAP.put(E_MERCHANT_IS_DEL, "此商户已删除，关联失败");
        ERROR_MESSGE_MAP.put(E_INNER_CODE_NULL, "商户内部商户号不能为空");
        ERROR_MESSGE_MAP.put(E_PAGE_NUM_NULL, "页码为空或不合规范");
        ERROR_MESSGE_MAP.put(E_CONFIG_NAME_NULL, "资源类型不能为空");
        ERROR_MESSGE_MAP.put(E_STRING_TOO_LENGTH, "输入参数太长");
        //APP
        ERROR_MESSGE_MAP.put(E_USERID_NULL, "入参ID为null");
        ERROR_MESSGE_MAP.put(E_UPDATE_FAIL, "更新失败");
        ERROR_MESSGE_MAP.put(E_APP_PHONE_EMPTY, "没有手机号");
        ERROR_MESSGE_MAP.put(E_APP_PHONE_ERROR, "手机号不正确");
        ERROR_MESSGE_MAP.put(E_APP_PASSWORD_EMPTY, "密码为空");
        ERROR_MESSGE_MAP.put(E_APP_PASSWORD_ERROR, "密码不正确");
        ERROR_MESSGE_MAP.put(E_APP_ID_EMPTY, "id为空");
        ERROR_MESSGE_MAP.put(E_APP_ID_ERROR, "id不正确");
        ERROR_MESSGE_MAP.put(E_APP_DEVICETYPE_EMPTY, "deviceType为空");
        ERROR_MESSGE_MAP.put(E_APP_DEVICETYPE_ERROR, "deviceType不正确");
        ERROR_MESSGE_MAP.put(E_APP_CODE_EMPTY, "验证码为空");
        ERROR_MESSGE_MAP.put(E_APP_CODE_ERROR, "验证码不正确");
        ERROR_MESSGE_MAP.put(E_APP_DEVICEIDNULL, "设备号为空");
        ERROR_MESSGE_MAP.put(E_APP_DEVICETYPENULL, "设备号不正确");

        ERROR_MESSGE_MAP.put(E_ALREADY_LOGIN, "用户已注册");
        ERROR_MESSGE_MAP.put(E_REGISTER_ERROR, "注册失败");
        ERROR_MESSGE_MAP.put(E_CODEOVERTIME_ERROR, "验证码超时");
        ERROR_MESSGE_MAP.put(E_UPDATEPASSWORD_ERROR, "更新密码失败");
        ERROR_MESSGE_MAP.put(E_OLDPASSWORD_ERROR, "原密码错误");
        ERROR_MESSGE_MAP.put(E_NOREGISTER_LOGIN, "用户未注册");
        ERROR_MESSGE_MAP.put(E_EDITION_ERROR, "版本号格式错误");
        ERROR_MESSGE_MAP.put(E_CODE_INVALID, "验证码已失效");
        ERROR_MESSGE_MAP.put(E_APPCODE_NULL, "手机码为空");
        ERROR_MESSGE_MAP.put(E_VERSION_NULL, "版本号为空");
        ERROR_MESSGE_MAP.put(E_ACCOUNTLOCKOUT_ERROR, "用户账号被锁定");
        ERROR_MESSGE_MAP.put(E_LOGIN_ERROR, "登录失败");
        ERROR_MESSGE_MAP.put(E_LOGINMSG_ERROR, "用户名或密码不正确");
        ERROR_MESSGE_MAP.put(E_LOGINOUT_ERROR, "退出登录失败");
        ERROR_MESSGE_MAP.put(E_PHONETYPE_ERROR, "手机类型错误");
        ERROR_MESSGE_MAP.put(E_NOSHOPKEEPER_ERROR, "您还不是店主，请先成为店主");  
        ERROR_MESSGE_MAP.put(E_DELETEBAND_ERROR, "删除绑定失败");  
        ERROR_MESSGE_MAP.put(E_NOBAND_ERROR, "该用户没有绑定商户");
        ERROR_MESSGE_MAP.put(E_ONLYSHOPKEEPER_ERROR, "修改失败,一个店铺只能有一个店主");
        ERROR_MESSGE_MAP.put(E_CHANGEROLE_ERROR, "更改角色失败"); 
        ERROR_MESSGE_MAP.put(E_FORCEDLOGINOUT_ERROR, "强制退出失败"); 
        ERROR_MESSGE_MAP.put(E_EMPTYDEVICETOKEN_ERROR, "清空友盟设备号失败");  
        ERROR_MESSGE_MAP.put(E_SUGGESTEMPTY_ERROR, "反馈内容不能为空");  
    }

}
