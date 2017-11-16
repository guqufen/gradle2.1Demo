package net.fnsco.trading.service.pay.channel.zxyh.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 中信银行杭州分行-移动收单平台接口MD5签名/验签示例代码
 * @author lilj
 * @version 1.0 20161026
 */
public class ZxyhPayMD5Util {
    private static Logger         logger               = LoggerFactory.getLogger(ZxyhPayMD5Util.class);
    /**
     * 默认的http连接超时时间
     */
    private final static int      DEFAULT_CONN_TIMEOUT = 10000;                                                                             //10s
    /**
     * 默认的http read超时时间
     */
    private final static int      DEFAULT_READ_TIMEOUT = 120000;                                                                            //120s
    /**
     * 请求的目标URL
     * 配置在此处仅为演示方便，正式生产代码中，应该做外置可配置处理
     */
    private static String         reqUrl               = "https://120.27.165.177:9001/MPay/backTransAction.do";
    /**MD5加密方式
     * 用于数据电子签名使用的MD5密钥，由中信银行开商户时自动生成，请妥善保管
     * 配置在此处仅为演示方便，正式生产代码中，商户应该将其外置于安全的地方，且妥善保护该密钥，如有泄露，请第一时间通知我行进行变更！！！
     */
    private static String         md5key               = "85925831831191481934561344882551";

    private final static String[] hexDigits            = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public static void init(String md5key, String reqUrl) {
        ZxyhPayMD5Util.reqUrl = reqUrl;
        ZxyhPayMD5Util.md5key = md5key;
    }

    /**
     * 转换字节数组为16进制字串
     * 
     * @param b
     *            字节数组
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }

    /**
     * 转换byte到16进制
     * 
     * @param b
     *            要转换的byte
     * @return 16进制格式
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * MD5计算
     * 
     * @param origin
     *            原始字符串
     * @return 经过MD5加密之后的结果
     */
    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    public static byte[] base64Encode(byte[] inputByte) throws IOException {
        return Base64.encodeBase64(inputByte);
    }

    /**
     * 对Map报文进行签名，并发送
     * @param reqMap
     * @return
     */
    public static String request(Map<String, String> reqMap, String url) {
        //将reqMap排序
        SortedMap<String, String> sm = new TreeMap<String, String>(reqMap);
        //按序拼接
        StringBuilder sb = new StringBuilder();
        for (Entry<String, String> sme : sm.entrySet()) {
            String v = sme.getValue();
            //空字段不参加签名
            if (null == v || v.length() == 0)
                continue;
            sb.append("&").append(sme.getKey()).append("=").append(v);
        }
        //System.out.println(sb.substring(1));

        //尾部加上md5key签名		
        sb.append("&key=").append(md5key);

        System.out.println("加签报文：" + sb.substring(1));

        try {

            String signAture = MD5Encode(sb.substring(1)).toUpperCase();
            System.out.println("本地加签后的：" + signAture);
            //将签名信息加入原始请求报文map
            reqMap.put("signAture", signAture);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //将Map转成Json
        //Json2 reqJs = Json2.make(reqMap);
        String reqStr = JSON.toJSONString(reqMap);
        //Map<String,Object> reqJs = JSON.parseObject(mapStr, Map.class);
        //生成json字符串
        //String reqStr = reqJs.toString();
        //System.out.println(reqStr);
        //再将json字符串用base64编码,并对一些特殊字符进行置换
        String b64ReqStr = null;
        try {
            b64ReqStr = Base64.encodeBase64String(reqStr.getBytes("utf-8")).replaceAll("\\+", "#");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        //生成最后的报文
        String finalB64ReqStr = "sendData=" + b64ReqStr;
        System.out.println("req :" + finalB64ReqStr);
        //HTTP POST方式发送报文，并获取返回结果

        String respStr = postReq(reqUrl + url, finalB64ReqStr);

        return respStr;
    }

    /**
     * 解析返回的报文，并验签:
     * @param finalRespStr
     * @return
     */
    public static Map<String, Object> getResp(String finalRespStr) {
        assert finalRespStr.startsWith("sendData=");
        String respB64Str = finalRespStr.substring(9);
        //base64解码,并对一些特殊字符进行置换
        byte[] respJsBs = Base64.decodeBase64(respB64Str.replaceAll("#", "+"));
        String respJsStr = null;
        try {
            respJsStr = new String(respJsBs, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        //解析json
        //Json2 respJs = Json2.read(respJsStr);
        Map<String, Object> respJs = JSON.parseObject(respJsStr, Map.class);
        //转成map方便排序
        SortedMap<String, Object> sm = new TreeMap<String, Object>(respJs);
        //按序拼接
        StringBuilder sb = new StringBuilder();
        for (Entry<String, Object> sme : sm.entrySet()) {
            //排除signAture字段
            if ("signAture".equals(sme.getKey()))
                continue;
            String v = (String) sme.getValue();
            //空字段不参加验签
            if (null == v || v.length() == 0)
                continue;
            sb.append("&").append(sme.getKey()).append("=").append(v);
        }

        //尾部加上md5key签名		
        sb.append("&key=").append(md5key);
        System.out.println("加签报文：" + sb.substring(1));

        try {

            String signAture = MD5Encode(sb.substring(1)).toUpperCase();
            System.out.println("本地加签后的：" + signAture);
            String respSign = respJs.get("signAture").toString();
            respSign = respSign.substring(1, respSign.length() - 1);
            System.out.println("接收报文中的：" + respSign);

            if (respSign.equals(signAture)) {
                System.out.println("md5 OK!");
            } else
                System.out.println("md5 ERROR!");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return respJs;
    }

    /**
     * http post,有返回String
     * @param requrl
     * @param req
     * @param connTimeOut
     * @param readTimeOut
     * @return
     */
    public static String postReq(String requrl, String req, int connTimeOut, int readTimeOut) {
        try {
            HttpURLConnection conn = null;
            try {
                URL url = new URL(requrl);
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true); //POST
                conn.setRequestMethod("POST");
                conn.setUseCaches(false);
                conn.setConnectTimeout(connTimeOut);
                conn.setReadTimeout(readTimeOut);
                conn.connect();
            } catch (Exception e) {
                logger.error("调用中信银行接口出错", e);
                throw new RuntimeException(e);
            }

            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
            out.write(req);
            out.flush();
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            char[] buff = new char[2048];
            int cnt = 0;
            while ((cnt = in.read(buff)) != -1)
                sb.append(buff, 0, cnt);
            in.close();
            String rtStr = sb.toString();
            return rtStr;
        } catch (IOException e) {
            //System.out.println(e);
            logger.error("调用中信银行接口出错", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 标准http post,有返回String
     * @param requrl
     * @param req
     * @return
     */
    public static String postReq(String requrl, String req) {
        return postReq(requrl, req, DEFAULT_CONN_TIMEOUT, DEFAULT_READ_TIMEOUT);
    }

    public static void main(String[] args) {
        //构建演示用报文！！！注意，此为演示用报文，请勿用于生产！！！
        Map<String, String> reqMap = new HashMap<String, String>();
        reqMap.put("backEndUrl", "http://www.baidu.com"); //后台通知地址,商户端接收支付网关异步通知回调地址，此处配置仅做演示
        reqMap.put("channelType", "6002"); //接入渠道
        reqMap.put("currencyType", "156"); //交易币种
        reqMap.put("encoding", "UTF-8"); //编码方式
        reqMap.put("merId", "000000000000000"); //测试商户编号
        reqMap.put("orderBody", "测试产品"); //商品描述
        reqMap.put("orderTime", "20160607143922"); //订单生成时间
        reqMap.put("payAccessType", "02"); //接入支付类型
        reqMap.put("productId", "YLCS888"); //商品ID
        reqMap.put("signMethod", "02"); //签名方法
        reqMap.put("termId", "WEB"); //终端编号
        reqMap.put("termIp", "127.0.0.1"); //终端IP
        reqMap.put("txnAmt", "100"); //交易金额
        reqMap.put("txnSubType", "010130"); //交易子类型
        reqMap.put("txnType", "01"); //交易类型
        reqMap.put("orderId", System.currentTimeMillis() + ""); //商户订单号,此处取当前时间戳仅作演示用，生产环境请勿如此使用

        //发送中信报文
        String respStr = request(reqMap, "");

        //解析中信报文
        System.out.println("resp:" + respStr);
        //解析返回报文
        Map<String, Object> respMap = getResp(respStr);
        System.out.println(JSON.toJSON(respMap).toString());
    }
}
