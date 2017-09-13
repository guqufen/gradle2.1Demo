package net.fnsco.order.api.push;

import java.util.Map;

import org.json.JSONObject;

/**
 * @desc APP消息推送调用友盟接口
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月12日 上午10:43:52
 */

public interface AppPushService {
    
    /**
     * sendAndroidListcast:(这里用一句话描述这个方法的作用)  安卓推送--列播
     *
     * @param param_and
     * @param content
     * @param sendTime
     * @return
     * @throws Exception    设定文件
     * @return Integer    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    Integer sendAndroidListcast(String param_and, String content,String sendTime,String contentJson) throws Exception;
    
    /**
     * sendAndroidListcast:(带扩展参数的  安卓推送--列播)
     * @param param_and
     * @param content
     * @param extraField
     * @return
     * @throws Exception    设定文件
     * @author    tangliang
     * @date      2017年9月1日 上午9:44:34
     * @return Integer    DOM对象
     */
    Integer sendAndroidListcast(String param_and, String content,Map<String,String> extraField) throws Exception;
    
    /**
     * sendIOSListcast:(这里用一句话描述这个方法的作用) ios推送--列播
     *
     * @param param_ios
     * @param content
     * @param sendTime
     * @return
     * @throws Exception    设定文件
     * @return Integer    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public Integer sendIOSListcast(String param_ios, String content,String sendTime) throws Exception;
    
    /**
     * sendAndroidBroadcast:(这里用一句话描述这个方法的作用) 安卓--广播
     *
     * @param content
     * @param sendTime
     * @return
     * @throws Exception    设定文件
     * @return Integer    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public Integer sendAndroidBroadcast(String content,String sendTime,String contentJson) throws Exception;
    
    /**
     * sendIOSBroadcast:(这里用一句话描述这个方法的作用) ios--广播
     *
     * @param content
     * @param sendTime
     * @return
     * @throws Exception    设定文件
     * @return Integer    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public Integer sendIOSBroadcast(String content,String sendTime) throws Exception;
    
    /**
     * sendAndroidUnicast:(这里用一句话描述这个方法的作用) andorid -- 单播
     *
     * @param adrUnicastToken
     * @param innerTermCode
     * @param content
     * @throws Exception    设定文件
     * @return void    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public Integer sendAndroidUnicast(String adrUnicastToken,String innerTermCode,String content) throws Exception;
    
    /**
     * sendIOSUnicast:(这里用一句话描述这个方法的作用) ios -- 单播
     *
     * @param iosUnicastToken
     * @param innerTermCode
     * @param content
     * @throws Exception    设定文件
     * @return void    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public Integer sendIOSUnicast(String iosUnicastToken,JSONObject content,Integer badge,String contentJson) throws Exception ;
    
    /**
     * sendIOSUnicast:(带扩展参数的  IOS推送--单播)
     * @param iosUnicastToken
     * @param content
     * @param badge
     * @param extraField
     * @return
     * @throws Exception    设定文件
     * @author    tangliang
     * @date      2017年9月1日 上午9:46:37
     * @return Integer    DOM对象
     */
    Integer sendIOSUnicast(String iosUnicastToken,JSONObject content,Integer badge,Map<String,String> extraField) throws Exception ;
    
    /**
     * sendSystemMgs:(这里用一句话描述这个方法的作用)发送系统消息
     *    设定文件
     * @return void    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public void sendSystemMgs();
    
    /**
     * sendFirstFailMgs:(这里用一句话描述这个方法的作用)第一次发送失败消息
     *    设定文件
     * @return void    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public void sendFailMgs(Integer frequencyNum);
    
    /**
     * sendWeeklyDataMgs:(推送周报数据)    设定文件
     * @author    tangliang
     * @date      2017年8月31日 上午11:43:10
     * @return void    DOM对象
     */
    public void sendWeeklyDataMgs();
    void sendFixQRMgs(String innerCode,String msgContent);
}
