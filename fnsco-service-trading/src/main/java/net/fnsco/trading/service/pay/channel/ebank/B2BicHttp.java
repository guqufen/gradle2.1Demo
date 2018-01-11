package net.fnsco.trading.service.pay.channel.ebank;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class B2BicHttp {

	private static final int DEF_CONN_TIMEOUT = 30000;
	private static final int DEF_READ_TIMEOUT = 30000;
	private static final String DEF_CHATSET = "GBK";

	/**
	 * 
	 * @param strUrl
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String strUrl, String params) throws Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = null;
		try {
			StringBuffer sb = new StringBuffer();

//			System.out.println("strUrl = " + strUrl);
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// conn.setRequestProperty("User-agent", userAgent);
			conn.setUseCaches(false);
			conn.setConnectTimeout(DEF_CONN_TIMEOUT);
			conn.setReadTimeout(DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			conn.connect();

			try {
				DataOutputStream out = new DataOutputStream(conn.getOutputStream());
				out.writeBytes(params);
			} catch (Exception e) {

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
}
