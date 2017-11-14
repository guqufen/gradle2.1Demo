package net.fnsco.trading.util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import net.fnsco.freamwork.comm.Md5Util;

public class SignUtil {
    //    签名生成的通用步骤如下：
    //    1.  若发送或者接收到的数据为集合M，将集合M内非空字段按照ASCII码从小到大排序（字典序），使用键值对的格式（key1=value1&key2=value2…）拼接成字符串stringA。
    //    注意：如果字段的值为空不参与签名，字段名区分大小写。
    //    2.  在stringA最后拼接上key得到stringSignTemp字符串，并对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值signAture。

    public static String zxyhSign(Map<String, String> parameterMap) {
        String key = "192006250b4c09247ec02edce69f6a2d";
        String strParameter = prepareParam(parameterMap);
        String stringSignTemp = strParameter + "&key=" + key;
        String signAture = Md5Util.MD5(stringSignTemp).toUpperCase();
        return signAture;
    }

    public static void main(String[] args) {
        Map<String, String> paras = new HashMap<String, String>();
        paras.put("version", "1.0.0");// 消息版本号
        paras.put("txnSubType", "00");// 交易子类型
        paras.put("bizType", "000000");// 产品类型
        paras.put("accessType", "0");// 接入类型
        paras.put("accessMode", "01");// 接入方式
        paras.put("payType", "0701");// 支付方式
        paras.put("merId", "929010048160219");// 商户号
        String result = prepareParam(paras);
        System.out.println(result);
    }

    private static String prepareParam(Map<String, String> parameterMap) {
        TreeMap<String, String> paramMap = new TreeMap<>();
        paramMap.putAll(parameterMap);
        String result = "";
        if (paramMap.isEmpty()) {
            return result;
        }
        StringBuffer sb = new StringBuffer();
        for (String key : paramMap.keySet()) {
            String value = (String) paramMap.get(key);
            if (value == null || value.isEmpty()) {
                continue;
            }
            if (sb.length() < 1) {
                sb.append(key).append("=").append(value);
            } else {
                sb.append("&").append(key).append("=").append(value);
            }
            result = sb.toString();
        }
        return result;
    }
}
