package net.fnsco.withhold.service.trade.pay.ainpay;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

public class HttpRequestPayHelper {

    private static Logger logger = Logger.getLogger(HttpRequestPayHelper.class);

    public static Map<String, String> sendPost(String apiHost, String payKey, Map<String, String> paras, String encoding, int sendType) {

        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(paras);
        try {
            logger.error("调用爱农输入参数" + treeMap.toString());
            String result = HttpRequest.doPost(apiHost, payKey, treeMap, encoding, sendType);
            logger.error("调用爱农返回原报文：" + result);
            return buildResult(result, encoding);
        } catch (Exception e) {
            logger.error("调用爱农失败！", e);
        }
        return null;
    }

    private static Map<String, String> buildResult(String jsonString, String encoding) {
        Map<String, String> result = new HashMap<>();
        String[] args = jsonString.split("&");

        for (String string : args) {
            String[] tmp = string.split("=");
            String key = tmp[0];
            String value = tmp.length < 2 ? "" : tmp[1];
            if (key.equals("respMsg") || key.equals("resv")) {
                try {
                    value = new String(Base64.getDecoder().decode(value.getBytes()), encoding);
                } catch (Exception e) {//UnsupportedEncodingException
                    logger.error("爱农返回数据转换成base64出错", e);
                }
            }
            result.put(key, value);
        }
        return result;
    }

}