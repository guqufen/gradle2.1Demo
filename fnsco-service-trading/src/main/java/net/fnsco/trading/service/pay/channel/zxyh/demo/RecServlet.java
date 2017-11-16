package net.fnsco.trading.service.pay.channel.zxyh.demo;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;


/**
 * 中信银行杭州分行-移动收单平台 异步通知接收服务demo
 * @author lilj
 * @version 1.0 20170720
 */
@WebServlet("/RecServlet")
public class RecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	private static final String md5key = "83694572786487019829744362696663";

	public static Map<String, Object> getResp(String finalRespStr){

		//base64解码,并对一些特殊字符进行置换
		byte [] respJsBs = Base64.decodeBase64(finalRespStr.replaceAll("#","+"));
		String respJsStr = null;
		try {
			respJsStr = new String(respJsBs,"utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		//解析json
		Json2 respJs = Json2.read(respJsStr);
		//转成map方便排序
		SortedMap<String, Object> sm = new TreeMap<String, Object>(respJs.asMap());
		//按序拼接
		StringBuilder sb = new StringBuilder();
		for(Entry<String, Object> sme : sm.entrySet()){
			//排除signAture字段
			if("signAture".equals(sme.getKey()))
				continue;
			String v = (String)sme.getValue();
			//空字段不参加验签
			if(null == v || v.length()==0)
				continue;
			sb.append("&").append(sme.getKey()).append("=").append(v);
		}
		
		//尾部加上md5key签名		
		sb.append("&key=").append(md5key);
		System.out.println("加签报文："+sb.substring(1));
		
		
		try {

			String signAture = MD5Encode(sb.substring(1)).toUpperCase();
			System.out.println("本地加签后的："+signAture);
			String respSign = respJs.at("signAture").toString();
			respSign=respSign.substring(1,respSign.length()-1);
			System.out.println("接收报文中的："+respSign);
			
			if (respSign.equals(signAture)){
				System.out.println("md5 OK!");
			}else System.out.println("md5 ERROR!");
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return respJs.asMap();
	}


	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

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
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes("UTF-8")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get  :"+request.getParameterMap().toString());
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("please send msg by post!!");
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 Map<String,String[]> map=request.getParameterMap();
		 String recstr=map.get("sendData")[0].toString();
		 System.out.println("post rec:"+recstr);

		 response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 if (recstr!=null&&!recstr.equals("")){

			 try {		 
				 Map<String, Object> respMap = getResp(recstr);
				 System.out.println(Json2.make(respMap).toString());
				 String respCode=respMap.get("respCode").toString();
				 if (respCode.equals("0000"))
					 /*
					  * 此处应为商户端判断异步通知报文是否合法的代码。
					  * 
					  * demo中仅判断redpCode=0000。商户应根据实际情况自行决定需要判断哪些字段。
					  * 
					  * */
				 {		
					 out.println("sendData=eyJyZXNwQ29kZSI6IjAwMDAiLCJyZXNwTXNnIjoiT0sifQ==");			 
				 }else{
					 out.println("ERROR");
				 }
					 
			 } catch (Exception e) {
				 out.println("ERROR");
			 }
		 }else{
			 out.println("ERROR");
		 }
			 
		 out.flush();
		 out.close();


	
	}
}
