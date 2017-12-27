package net.fnsco.trading.service.third.reCharge.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 手机充值工具类
 * 
 * @author yx
 *
 */
public class RechargeUtil {

	private static final String DEF_CHATSET = "UTF-8";
	private static final int DEF_CONN_TIMEOUT = 30000;
	private static final int DEF_READ_TIMEOUT = 30000;
	private static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
	// HttpClient请求的相关设置，可以不用配置，用默认的参数，这里设置连接和超时时长(毫秒)
	private static RequestConfig config = RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(30000)
			.build();
	/**
	 * 
	 * @param strUrl:提交的url
	 * @param params:提交参数,?xxxx=xxxx&格式
	 * @param method:提交方法:POST/GET
	 * @return
	 * @throws Exception
	 */
	public static String net(String strUrl, String params, String method) throws Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = null;
		try {
			StringBuffer sb = new StringBuffer();

			System.out.println("strUrl = " + strUrl + params);
			URL url = new URL(strUrl + params);
			conn = (HttpURLConnection) url.openConnection();
			if (method == null || method.equals("GET")) {
				conn.setRequestMethod("GET");
			} else {
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
			}

			conn.setRequestProperty("User-agent", userAgent);
			conn.setUseCaches(false);
			conn.setConnectTimeout(DEF_CONN_TIMEOUT);
			conn.setReadTimeout(DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			conn.connect();

			if (params != null && method.equals("POST")) {
				try {
					DataOutputStream out = new DataOutputStream(conn.getOutputStream());
				} catch (Exception e) {

				}
			}

			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sb.append(strRead);
			}
			rs = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return rs;
	}
	
	/**
	 *
	 * @param strUrl
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param method
	 *            请求方法
	 * @return 网络请求字符串
	 * @throws Exception
	 */
	public static String net(String strUrl, Map params, String method) throws Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			if (method == null || method.equals("GET")) {
				strUrl = strUrl + "?" + urlencode(params);
			}
			System.out.println("strUrl = " + strUrl);
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			if (method == null || method.equals("GET")) {
				conn.setRequestMethod("GET");
			} else {
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
			}

			conn.setRequestProperty("User-agent", userAgent);
			conn.setUseCaches(false);
			conn.setConnectTimeout(DEF_CONN_TIMEOUT);
			conn.setReadTimeout(DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			conn.connect();

			if (params != null && method.equals("POST")) {
				try {
					DataOutputStream out = new DataOutputStream(conn.getOutputStream());
					out.writeBytes(urlencode(params));
				} catch (Exception e) {

				}
			}

			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sb.append(strRead);
			}
			rs = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return rs;
	}
	
	// 将map型转为请求参数型
		public static String urlencode(Map<String, Object> data) {
			StringBuilder sb = new StringBuilder();
			String str = null;
			for (Map.Entry i : data.entrySet()) {
				System.out.println("i=[" + i + "]");
				try {
					sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", DEF_CHATSET)).append("&");
					str = sb.toString();
					str = str.replaceAll("%22", "");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			return str.substring(0, str.length() - 1);
		}

		/**
		 * 工具类方法 get 网络请求
		 * 
		 * @param url
		 *            接收请求的网址
		 * @param tts
		 *            重试
		 * @return String类型 返回网络请求数据
		 * @throws Exception
		 *             网络异常
		 */
		public static String get(String url, int tts) throws Exception {
			if (tts > 3) {// 重试3次
				return null;
			}
			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = null;
			String result = null;
			try {
				HttpGet httpGet = new HttpGet(url);
				httpGet.setConfig(config);
				response = httpClient.execute(httpGet);
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = ConvertStreamToString(resEntity.getContent(), "UTF-8");
				}
				EntityUtils.consume(resEntity);
				return result;
			} catch (IOException e) {
				return get(url, tts++);
			} finally {
				response.close();
				httpClient.close();
			}
			// 得到的是JSON类型的数据需要第三方解析JSON的jar包来解析
		}

		/**
		 * 工具类方法 此方法是把传进的字节流转化为相应的字符串并返回，此方法一般在网络请求中用到
		 * 
		 * @param is
		 *            输入流
		 * @param charset
		 *            字符格式
		 * @return String 类型
		 * @throws Exception
		 */
		public static String ConvertStreamToString(InputStream is, String charset) throws Exception {
			StringBuilder sb = new StringBuilder();
			try (InputStreamReader inputStreamReader = new InputStreamReader(is, charset)) {
				try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
					String line = null;
					while ((line = reader.readLine()) != null) {
						sb.append(line).append("\r\n");
					}
				}
			}
			return sb.toString();
		}
}
