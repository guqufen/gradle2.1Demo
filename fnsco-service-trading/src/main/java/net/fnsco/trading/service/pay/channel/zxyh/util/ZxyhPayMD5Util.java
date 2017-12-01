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
import com.google.common.base.Strings;

import net.fnsco.trading.service.pay.channel.zxyh.dto.MerchantZxyhDTO;

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
//    private static String         reqUrl               = "https://120.27.165.177:9001";  //入建
//    private static String         reqUrl               = " https://120.27.165.177:8099";  //主扫
    private static String         reqUrl               = "https://120.27.165.177:8099";  //公众号
    /**MD5加密方式
     * 用于数据电子签名使用的MD5密钥，由中信银行开商户时自动生成，请妥善保管
     * 配置在此处仅为演示方便，正式生产代码中，商户应该将其外置于安全的地方，且妥善保护该密钥，如有泄露，请第一时间通知我行进行变更！！！
     */
    private static String         md5key               = "83091094209057371094820737983886";

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
     * 对Map报文进行签名，并发送
     * @param reqMap:请求的MAP数据
     * @param url：请求地址URL
     * @param prefix：前缀
     * @return
     */
	public static String request(Map<String, String> reqMap, String url, String prefix) {

		// 将reqMap排序
		SortedMap<String, String> sm = new TreeMap<String, String>(reqMap);
		// 按序拼接
		StringBuilder sb = new StringBuilder();
		for (Entry<String, String> sme : sm.entrySet()) {
			String v = sme.getValue();
			// 空字段不参加签名
			if (null == v || v.length() == 0)
				continue;
			sb.append("&").append(sme.getKey()).append("=").append(v);
		}
		// System.out.println(sb.substring(1));

		// 尾部加上md5key签名
		sb.append("&key=").append(md5key);

		System.out.println("加签报文：" + sb.substring(1));

		try {

			String signAture = MD5Encode(sb.substring(1)).toUpperCase();
			System.out.println("本地加签后的：" + signAture);
			// 将签名信息加入原始请求报文map
			reqMap.put("signAture", signAture);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// 将Map转成Json
		// Json2 reqJs = Json2.make(reqMap);
		System.out.println("req=["+reqMap+"]");
		String reqStr = JSON.toJSONString(reqMap);
		// Map<String,Object> reqJs = JSON.parseObject(mapStr, Map.class);
		// 生成json字符串
		// String reqStr = reqJs.toString();
		// System.out.println(reqStr);
		// 再将json字符串用base64编码,并对一些特殊字符进行置换
		String b64ReqStr = null;
		try {
			b64ReqStr = Base64.encodeBase64String(reqStr.getBytes("utf-8")).replaceAll("\\+", "#");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		// 生成最后的报文
		String finalB64ReqStr = "sendData=" + b64ReqStr;
		System.out.println("req :" + finalB64ReqStr);

		// HTTP POST方式发送报文，并获取返回结果
		String respStr = postReq(prefix + url, finalB64ReqStr);

		return respStr;
	}

	/**
	 * 将map按照中信银行JSON字串处理要求组装，并加上签名
	 * @param reqMap
	 * @return：转换处理后的JSON字串，带签名
	 */
	public String convZxyhJsonStr(Map<String, String> reqMap, String md5Key) {
		// 将reqMap排序
		SortedMap<String, String> sm = new TreeMap<String, String>(reqMap);
		// 按序拼接
		StringBuilder sb = new StringBuilder();
		for (Entry<String, String> sme : sm.entrySet()) {
			String v = sme.getValue();
			// 空字段不参加签名
			if (null == v || v.length() == 0)
				continue;
			sb.append("&").append(sme.getKey()).append("=").append(v);
		}

		// 尾部加上md5key签名
		sb.append("&key=").append(md5Key);

		System.out.println("加签报文：" + sb.substring(1));

		try {

			String signAture = MD5Encode(sb.substring(1)).toUpperCase();
			System.out.println("本地加签后的：" + signAture);
			// 将签名信息加入原始请求报文map
			reqMap.put("signAture", signAture);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// 将Map转成Json
		// Json2 reqJs = Json2.make(reqMap);
		String reqStr = JSON.toJSONString(reqMap);
		// Map<String,Object> reqJs = JSON.parseObject(mapStr, Map.class);
		// 生成json字符串
		// String reqStr = reqJs.toString();
		// System.out.println(reqStr);
		// 再将json字符串用base64编码,并对一些特殊字符进行置换
		String b64ReqStr = null;
		try {
			b64ReqStr = Base64.encodeBase64String(reqStr.getBytes("utf-8")).replaceAll("\\+", "#");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		// 生成最后的报文
		String finalB64ReqStr = "sendData=" + b64ReqStr;
		System.out.println("req :" + finalB64ReqStr);
		return finalB64ReqStr;
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
        logger.info("加签报文：" + sb.substring(1));

        try {

            String signAture = MD5Encode(sb.substring(1)).toUpperCase();
            logger.info("本地加签后的：" + signAture);
            String respSign = (String) respJs.get("signAture");
            logger.info("接收报文中的：" + respSign);

            if (signAture.equals(respSign)) {
                logger.info("md5 OK!");
            } else {
                logger.error("md5 ERROR!");
            }
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
    
    
    /**
     * 公众号支付测试
     * @param args
     */
    public static void main(String[] args) {
    	String url = "/MPay/backTransAction.do";
        Map<String, String> reqMap = new HashMap<String, String>();
        reqMap.put("encoding", "UTF-8"); //
        reqMap.put("signMethod", "02"); //
        reqMap.put("txnType", "01"); //
        reqMap.put("txnSubType", "010131"); //
        reqMap.put("channelType", "6002"); //
        reqMap.put("payAccessType", "02"); //
        reqMap.put("backEndUrl", "http://www.baidu.com"); //接收支付网关异步通知回调地址
        reqMap.put("merId", "994400000000009"); //普通商户或平台商户的商户号
//        reqMap.put("secMerId", "999900000010728"); //独立商户号  
//        reqMap.put("termId", "WEB");
        reqMap.put("termIp", "192.168.1.162");
        reqMap.put("orderId", "O10000"); //商户系统内部的订单号,32 个字符内、可包含字母, 确保在商户系统唯一
        reqMap.put("orderTime", System.currentTimeMillis() + ""); //订单生成时间，格式 为[yyyyMMddHHmmss] ,如2009年12月25日9点10分10秒 表示为20091225091010
//        reqMap.put("productId", "");
        reqMap.put("orderBody", "零食"); //商品或支付单简要描述
        reqMap.put("orderDetail", ""); 
//        reqMap.put("orderGoodsTag", ""); 
        reqMap.put("orderSubOpenid", "05202002");//用户在商户公众号的唯一标识（openid）
        reqMap.put("txnAmt", "100"); //订单总金额(交易单位为分，例:1.23元=123) 只能整数
        reqMap.put("currencyType", "156"); //默认是156：人民币
        
        reqMap.put("accountFlag", "Y");
        reqMap.put("secMerFeeRate", "");
        reqMap.put("attach", "");
        reqMap.put("limitPay", "");
        reqMap.put("needBankType", "");
        reqMap.put("independentTransactionFlag", "Y");
        reqMap.put("orderType", "");
        
        //发送中信报文
//        String respStr = request(reqMap, url);
        String respStr = ZxyhPayMD5Util.request(reqMap, url);
        //解析返回报文
        Map<String, Object> respMap = ZxyhPayMD5Util.getResp(respStr);
        System.out.println(JSON.toJSON(respMap).toString());
    }
    
    
    /**
     * 支付宝主扫测试
     * @param args
     */
    public static void main3(String[] args) {
    	String url = "/MPay/backTransAction.do";
        Map<String, String> reqMap = new HashMap<String, String>();
        reqMap.put("encoding", "UTF-8"); //
        reqMap.put("signMethod", "02"); //
        reqMap.put("txnType", "01"); //
        reqMap.put("txnSubType", "010302"); //
        reqMap.put("channelType", "6002"); //
//        reqMap.put("payAccessType", "02"); //
        reqMap.put("backEndUrl", "http://www.baidu.com"); //接收支付网关异步通知回调地址
        reqMap.put("merId", "994400000000009"); //普通商户或平台商户的商户号
        reqMap.put("secMerId", "999900000010728"); //独立商户号  
//        reqMap.put("termId", "WEB");
        reqMap.put("termIp", "192.168.1.162");
        reqMap.put("orderId", "O10000"); //商户系统内部的订单号,32 个字符内、可包含字母, 确保在商户系统唯一
        reqMap.put("orderTime", System.currentTimeMillis() + ""); //订单生成时间，格式 为[yyyyMMddHHmmss] ,如2009年12月25日9点10分10秒 表示为20091225091010
//        reqMap.put("productId", "");
        reqMap.put("orderBody", "零食"); //商品或支付单简要描述
        reqMap.put("orderDetail", ""); 
//        reqMap.put("orderGoodsTag", ""); 
        reqMap.put("txnAmt", "100"); //订单总金额(交易单位为分，例:1.23元=123) 只能整数
        reqMap.put("currencyType", "156"); //默认是156：人民币
        
        reqMap.put("accountFlag", "Y");
        reqMap.put("secMerFeeRate", "");
        reqMap.put("attach", "");
        reqMap.put("limitPay", "");
        reqMap.put("needBankType", "");
        reqMap.put("independentTransactionFlag", "Y");
        reqMap.put("orderType", "");
        
        //发送中信报文
//        String respStr = request(reqMap, url);
        String respStr = ZxyhPayMD5Util.request(reqMap, url);
        //解析返回报文
        Map<String, Object> respMap = ZxyhPayMD5Util.getResp(respStr);
        System.out.println(JSON.toJSON(respMap).toString());
    }
    

    /**
     * 微信主扫测试
     * @param args
     */
    public static void main2(String[] args) {
    	String url = "/MPay/backTransAction.do";
        Map<String, String> reqMap = new HashMap<String, String>();
        reqMap.put("encoding", "UTF-8"); //
        reqMap.put("signMethod", "02"); //
        reqMap.put("txnType", "01"); //
        reqMap.put("txnSubType", "010130"); //
        reqMap.put("channelType", "6002"); //
        reqMap.put("payAccessType", "02"); //
        reqMap.put("backEndUrl", "http://www.baidu.com"); //接收支付网关异步通知回调地址
        reqMap.put("merId", "994400000000009"); //普通商户或平台商户的商户号  
//        reqMap.put("secMerId", ""); //独立商户号  
        reqMap.put("secMerId", "999900000010727"); //独立商户号  999900000010724
        reqMap.put("termId", "WEB");
        reqMap.put("termIp", "");
        reqMap.put("orderId", "O10000"); //商户系统内部的订单号,32 个字符内、可包含字母, 确保在商户系统唯一
        reqMap.put("orderTime", System.currentTimeMillis() + ""); //订单生成时间，格式 为[yyyyMMddHHmmss] ,如2009年12月25日9点10分10秒 表示为20091225091010
        reqMap.put("productId", "");
        reqMap.put("orderBody", "零食"); //商品或支付单简要描述
        reqMap.put("orderDetail", ""); 
        reqMap.put("orderGoodsTag", ""); 
        reqMap.put("txnAmt", "100"); //订单总金额(交易单位为分，例:1.23元=123) 只能整数
        reqMap.put("currencyType", "156"); //默认是156：人民币
        
        reqMap.put("accountFlag", "Y");
        reqMap.put("secMerFeeRate", "");
        reqMap.put("attach", "");
        reqMap.put("limitPay", "");
        reqMap.put("needBankType", "");
        reqMap.put("independentTransactionFlag", "Y");
        reqMap.put("orderType", "");
        
        //发送中信报文
//        String respStr = request(reqMap, url);
        String respStr = ZxyhPayMD5Util.request(reqMap, url);
        //解析返回报文
        Map<String, Object> respMap = ZxyhPayMD5Util.getResp(respStr);
        System.out.println(JSON.toJSON(respMap).toString());
    }
    
    
    public static void main1(String[] args) {
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
    
    public static void main4(String[] args){
        String pid = "2088022294504639";
        String merId = "994400000000009"; 
        String url = "/MPayTransaction/ind/mchtadd.do";
        MerchantZxyhDTO mercDTO = new MerchantZxyhDTO();
        mercDTO.init(pid,merId);
        mercDTO.setMchtNm("杭州法奈昇科技有限公司");
        mercDTO.setMchtCnAbbr("法奈昇科");
        mercDTO.setEtpsAttr("3");
        mercDTO.setEtpsTp("1");
        mercDTO.setMchtTel("18668096830");
        mercDTO.setContact("胡滨");
        mercDTO.setCommTel("18668096830");
        mercDTO.setCommEmail("18668096830@139.com");
        mercDTO.setLicenceNo("913301030536607660");
        mercDTO.setManager("胡滨");
        mercDTO.setIdentityNo("330182198109183158");
        mercDTO.setProvCode("330000");
        mercDTO.setCityCode("330100");
        mercDTO.setAreaCode("330109");
        mercDTO.setAddr("信息港");
        /////////////////////
        mercDTO.setWXActive("Y");
        mercDTO.setMainMchtTp("00");
        mercDTO.setOlCode1("01|02|06");
        mercDTO.setqGroupId("01");
        mercDTO.setCategroryId("143");
        mercDTO.setFeeRate("1");
        mercDTO.setSettleCycle("T1");

        //////////支付宝//////////////////
        mercDTO.setZFBActive("Y");
        mercDTO.setCategIdC("2016042200000148");
        mercDTO.setFeeRateA("1");
        mercDTO.setSettleCycleA("D0");//---填写字母，T1一工作日、D1一天、D0
        //////////百度///////////
        mercDTO.setBDActive("N");
        //////////银行信息///////////
        mercDTO.setThirdMchtNo("071211347434539");
        mercDTO.setIsOrNotZxMchtNo("N");
        mercDTO.setAcctType("1");//填写数字1-企业账户，2-个人账 户。
        mercDTO.setSettleAcctNm("胡滨");
        mercDTO.setSettleAcct("6210580199002792916");
        mercDTO.setAccIdeNo("330182198109183158");
        mercDTO.setAccPhone("18668096830");
        mercDTO.setSettleBankAllName("杭州联合农村商业银行股份有限公司吴山支行");
        mercDTO.setSettleBankCode("402331000912");
        mercDTO.setApType("1");//1-新增 
        mercDTO.setSignMethod("02");//02-MD5  03-RSA
        
        String mercStr = JSON.toJSONString(mercDTO);
        Map<String, String> mercMap = JSON.parseObject(mercStr, Map.class);
        String respStr = ZxyhPayMD5Util.request(mercMap, url);
        //解析返回报文
        Map<String, Object> respMap = ZxyhPayMD5Util.getResp(respStr);
        //{respMsg=签名校验异常, txnTime=20171116161243314, respCode=5017}
        //{"respCode":"0000","signMethod":"02","respMsg":"新增商户成功","secMerId":"999900000010598","signAture":"5E335BBAF3FF5BD7C988416B85156F8D","txnTime":"20171117110340745"}
        String resultJson = "";
    }
}
