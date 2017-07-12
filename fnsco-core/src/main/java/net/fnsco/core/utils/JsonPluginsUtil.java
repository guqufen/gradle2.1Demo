package net.fnsco.core.utils;

import net.sf.json.JSONObject;

/**
 * @desc JSON工具类 Json与javaBean之间的转换工具类
 * 现使用json-lib组件实现
 *          需要
 *              json-lib-2.4-jdk15.jar
 *              ezmorph-1.0.6.jar
 *              commons-collections-3.1.jar
 *              commons-lang-2.0.jar
 *          支持
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月12日 下午3:27:25
 */

public class JsonPluginsUtil {
    
    /**  
     * 从一个JSON 对象字符格式中得到一个java对象  
     *   
     * @param jsonString  
     * @param beanCalss  
     * @author   tangliang
     * @return  
     */
    @SuppressWarnings("unchecked")
    public static <T> T jsonToBean(String jsonString, Class<T> beanCalss) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        T bean = (T) JSONObject.toBean(jsonObject, beanCalss);
        return bean;
    }
    
    /**  
     * 将java对象转换成json字符串  
     * @param bean  
     * @author   tangliang
     * @return  
     */
    public static String beanToJson(Object bean) {
        JSONObject json = JSONObject.fromObject(bean);
        return json.toString();
    }
}
