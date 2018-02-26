package net.fnsco.core.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.alipay.api.internal.util.StringUtils;

import net.fnsco.core.constants.SignatureHashMap;
import net.fnsco.freamwork.comm.Md5Util;

/**
 * @desc 关于签名公共方法
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2018年2月26日 上午11:36:50
 */
public class SignatureUtil {
	
	/**
	 * md5Signature:(MD5加密待签名的字符串)
	 *
	 * @param  @param sortedParams
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @author tangliang
	 * @date   2018年2月26日 上午11:41:47
	 */
	public static String md5Signature(SignatureHashMap requestParams) {
		
		Map<String, String> sortedParams = new TreeMap<String, String>();
		if (requestParams != null && requestParams.size() > 0) {
			sortedParams.putAll(requestParams);
        }else {
        	return null;
        }
		
		String signContent =  getSignContent(requestParams);
		return Md5Util.MD5(signContent);
	}
	
	/**
	 * getSignContent:(返回已经按照key排序好的待签名使用的字符串,所有签名方法可用)
	 *
	 * @param  @param sortedParams
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @author tangliang
	 * @date   2018年2月26日 上午11:31:43
	 */
    public static String getSignContent(Map<String, String> sortedParams) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = sortedParams.get(key);
            if (StringUtils.areNotEmpty(key, value)) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                index++;
            }
        }
        return content.toString();
    }
}
