package net.fnsco.core.utils;

/**
 * Created by bingone on 15/12/16.
 */

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 短信http接口的java代码调用示例
 * 基于Apache HttpClient 4.3
 *
 * @author songchao
 * @since 2015-04-03
 */

public class SmsUtil {

    //查账户信息的http地址
    private static String  URI_GET_USER_INFO = "https://sms.yunpian.com/v2/user/get.json";

    //智能匹配模板发送接口的http地址
    private static String  URI_SEND_SMS      = "https://sms.yunpian.com/v2/sms/single_send.json";

    //模板发送接口的http地址
    private static String  URI_TPL_SEND_SMS  = "https://sms.yunpian.com/v2/sms/tpl_single_send.json";

    //发送语音验证码接口的http地址
    private static String  URI_SEND_VOICE    = "https://voice.yunpian.com/v2/voice/send.json";

    //编码格式。发送编码格式统一用UTF-8
    private static String  ENCODING          = "UTF-8";

    private static boolean SWITCH_OFF        = true;
    private static String  hbApikey          = "568a854396f8b62157d0e03daeb33cdc";
    //数钱吧APPkey
    private static String  sqbApikey         = "0425f962446c4b2de94e6e08e72120ad";
    //中融通盛汽车
    private static String zrtsApikey         ="a3f3ca7d03903c0aa4d789972cac2d21";

    public static String Code(String mobile, String code) throws IOException, URISyntaxException {

        //修改为您的apikey.apikey可在官网（http://www.yuanpian.com)登录后获取
        String apikey = "0425f962446c4b2de94e6e08e72120ad ";

        //设置模板ID，如使用1号模板:【#company#】您的验证码是#code#
        long tpl_id = 1;
        //设置对应的模板变量值

        String tpl_value = URLEncoder.encode("#code#", ENCODING) + "=" + URLEncoder.encode(code, ENCODING) + "&" + URLEncoder.encode("#company#", ENCODING) + "=" + URLEncoder.encode("数钱吧", ENCODING);

        String result = tplSendSms(apikey, tpl_id, tpl_value, mobile);

        return result;
    }
    
    public static String E789Code(String mobile, String code) throws IOException, URISyntaxException {

        //修改为您的apikey.apikey可在官网（http://www.yuanpian.com)登录后获取
        String apikey = "0425f962446c4b2de94e6e08e72120ad ";

        //设置模板ID，如使用1号模板:【#company#】您的验证码是#code#
        long tpl_id = 2139788;
        //设置对应的模板变量值

        String tpl_value = URLEncoder.encode("#code#", ENCODING) + "=" + URLEncoder.encode(code, ENCODING) + "&" + URLEncoder.encode("#company#", ENCODING) + "=" + URLEncoder.encode("E789", ENCODING);

        String result = tplSendSms(apikey, tpl_id, tpl_value, mobile);

        return result;
    }
    
    public static String ZRCode(String mobile, String code) throws IOException, URISyntaxException {

        //修改为您的apikey.apikey可在官网（http://www.yuanpian.com)登录后获取
        String apikey = "a3f3ca7d03903c0aa4d789972cac2d21 ";

        //设置模板ID，如使用1号模板:【#company#】您的验证码是#code#
        long tpl_id = 2100980;
        //设置对应的模板变量值

        String tpl_value = URLEncoder.encode("#code#", ENCODING) + "=" + URLEncoder.encode(code, ENCODING);// + "&" + URLEncoder.encode("#company#", ENCODING) + "=" + URLEncoder.encode("中融汽车", ENCODING);

        String result = tplSendSms(apikey, tpl_id, tpl_value, mobile);

        return result;
    }

    public static String applyUser(String mobile, String userName, String contactNum, String mercName) throws IOException {
        //修改为您的apikey.apikey可在官网（http://www.yuanpian.com)登录后获取
        //设置模板ID，#userName#（#mobile#）通过邀新活动，希望使用我们的APP。赶快打电话，咨询清楚吧!
        long tpl_id = 1983718;
        //设置对应的模板变量值

        String tpl_value = URLEncoder.encode("#userName#", ENCODING) + "=" + URLEncoder.encode(userName, ENCODING) + "&" + URLEncoder.encode("#mobile#", ENCODING) + "="
                           + URLEncoder.encode(contactNum, ENCODING);

        String result = tplSendSms(sqbApikey, tpl_id, tpl_value, mobile);

        return result;
    }

    /**
     * 
     * withholdSucc:(代扣提醒短信通知)
     *  【恒镔资产】温馨提醒：#userName#先生/女生，您本期融资服务费 #amount# 元 ，将于#month#月#day#日自动还款，
     *  请于扣息日前存入足够资金，以免逾期。如有疑问，请咨询客户电话：“电话”
     * @param mobile
     * @param code
     * @return
     * @throws IOException
     * @throws URISyntaxException   String    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static String withholdRemind(String mobile, String userName, String amount, String month, String day) throws IOException, URISyntaxException {

        //修改为您的apikey.apikey可在官网（http://www.yuanpian.com)登录后获取

        //设置模板ID，如使用1号模板:【#company#】您的验证码是#code#
        long tpl_id = 1889030;
        //设置对应的模板变量值

        String tpl_value = URLEncoder.encode("#userName#", ENCODING) + "=" + URLEncoder.encode(userName, ENCODING) + "&" + URLEncoder.encode("#amount#", ENCODING) + "="
                           + URLEncoder.encode(amount, ENCODING) + "&" + URLEncoder.encode("#month#", ENCODING) + "=" + URLEncoder.encode(month, ENCODING) + "&" + URLEncoder.encode("#day#", ENCODING)
                           + "=" + URLEncoder.encode(day, ENCODING);

        String result = tplSendSms(hbApikey, tpl_id, tpl_value, mobile);

        return result;
    }

    /**
     * 
     * withholdSucc:(代扣成功短信通知)
     * 【恒镔资产】本期#month#月融资服务费#amount#元已扣款成功。
     * 注8月
     * @param mobile
     * @param code
     * @return
     * @throws IOException
     * @throws URISyntaxException   String    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static String withholdSucc(String mobile, String month, String amount) throws IOException, URISyntaxException {

        //设置模板ID，如使用1号模板:【#company#】您的验证码是#code#
        long tpl_id = 1889002;
        //设置对应的模板变量值

        String tpl_value = URLEncoder.encode("#month#", ENCODING) + "=" + URLEncoder.encode(month, ENCODING) + "&" + URLEncoder.encode("#amount#", ENCODING) + "="
                           + URLEncoder.encode(amount, ENCODING);

        String result = tplSendSms(hbApikey, tpl_id, tpl_value, mobile);

        return result;
    }

    /**
     * 
     * withholdFail:(代扣失败短信通知)
     * 【恒镔资产】温馨提醒：#userName#先生/女士，本次扣款失败，今日为还息日,请及时存入足够资金， 以免逾期。如有疑问，请咨询客户电话:'电话'。
     * @param mobile
     * @param code
     * @return
     * @throws IOException
     * @throws URISyntaxException   String    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static String withholdFail(String mobile, String userName) throws IOException, URISyntaxException {

        //设置模板ID，如使用1号模板:【#company#】您的验证码是#code#
        long tpl_id = 1889022;
        //设置对应的模板变量值

        String tpl_value = URLEncoder.encode("#userName#", ENCODING) + "=" + URLEncoder.encode(userName, ENCODING);

        String result = tplSendSms(hbApikey, tpl_id, tpl_value, mobile);

        return result;
    }
    /**
     * 
     * withholdFail:(三次代扣失败短信通知管理员)
     * 【恒镔资产】您好，关于#userName#的代扣业务扣款不成功，失败原因：#failReason#，请及时进行跟进！
     * @param mobile
     * @param code
     * @return
     * @throws IOException
     * @throws URISyntaxException   String    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static String withholdAllFailWarn(String mobile, String userName,String failReason) throws IOException, URISyntaxException {

        //设置模板ID，【恒镔资产】您好，关于#userName#的代扣业务扣款不成功，失败原因：#failReason#，请及时进行跟进！
        long tpl_id = 1984924;
        //设置对应的模板变量值

        String tpl_value = URLEncoder.encode("#userName#", ENCODING) + "=" + URLEncoder.encode(userName, ENCODING)+ "&" + URLEncoder.encode("#failReason#", ENCODING) + "="
                + URLEncoder.encode(failReason, ENCODING);

        String result = tplSendSms(hbApikey, tpl_id, tpl_value, mobile);

        return result;
    }
    /**
     * 取账户信息
     *
     * @return json格式字符串
     * @throws java.io.IOException
     */

    public static String getUserInfo(String apikey) throws IOException, URISyntaxException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        return post(URI_GET_USER_INFO, params);
    }

    /**
     * 智能匹配模板接口发短信
     *
     * @param apikey apikey
     * @param text   　短信内容
     * @param mobile 　接受的手机号
     * @return json格式字符串
     * @throws IOException
     */

    public static String sendSms(String apikey, String text, String mobile) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("text", text);
        params.put("mobile", mobile);
        return post(URI_SEND_SMS, params);
    }

    /**
     * 通过模板发送短信(不推荐)
     *
     * @param apikey    apikey
     * @param tpl_id    　模板id
     * @param tpl_value 　模板变量值
     * @param mobile    　接受的手机号
     * @return json格式字符串
     * @throws IOException
     */

    public static String tplSendSms(String apikey, long tpl_id, String tpl_value, String mobile) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("tpl_id", String.valueOf(tpl_id));
        params.put("tpl_value", tpl_value);
        params.put("mobile", mobile);
        return post(URI_TPL_SEND_SMS, params);
    }

    /**
     * 通过接口发送语音验证码
     * @param apikey apikey
     * @param mobile 接收的手机号
     * @param code   验证码
     * @return
     */

    public static String sendVoice(String apikey, String mobile, String code) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("mobile", mobile);
        params.put("code", code);
        return post(URI_SEND_VOICE, params);
    }

    /**
     * 基于HttpClient 4.3的通用POST方法
     *
     * @param url       提交的URL
     * @param paramsMap 提交<参数，值>Map
     * @return 提交响应
     */

    public static String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseText;
    }
}
