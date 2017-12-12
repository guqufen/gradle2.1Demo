package net.fnsco.trading.service.third.phoneCharge;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.fnsco.bigdata.service.sys.SequenceService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.freamwork.comm.Md5Util;
import net.fnsco.trading.service.pay.channel.zxyh.demo.MD5;
import net.fnsco.trading.service.third.phoneCharge.dto.FlowPackageCheckDTO;
import net.fnsco.trading.service.third.phoneCharge.dto.JuheDTO;

@Service
public class PrepaidRefillService extends BaseService{

	@Autowired
    private SequenceService  sequenceService;
	private final String DEF_CHATSET = "UTF-8";
    private final int DEF_CONN_TIMEOUT = 30000;
    private final int DEF_READ_TIMEOUT = 30000;
    private String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

	//配置您申请的KEY
    private final String APPKEY ="6121e6e31f9e1eff037d8c416e0ee4fe";
    private final String OpenId="JH284e6d24f2e3f5d89668a64b50c2c886";

	/**
	 * 检测号码支持的流量套餐
	 * 
	 * @param phone:手机号码
	 * @return
	 */
	public ResultDTO<FlowPackageCheckDTO> flowPackageCheck(String phone) {
		String result = null;
		String url = "http://v.juhe.cn/flow/telcheck";// 请求接口地址
		JuheDTO juhe = new JuheDTO();
		
		StringBuffer sb = new StringBuffer();
		String sendData = sb.append("?phone=").append(phone).append("&key=").append(APPKEY).toString();

		try {
			result = net(url, sendData, "GET");
			juhe = JSONObject.parseObject(result, JuheDTO.class);

			// 查询成功
			if (juhe.getError_code() == 0) {
				System.out.println(juhe.getResult());

				// 获取需要返回的数据域(套餐类型)
				JSONArray jsonArray = (JSONArray) juhe.getResult();
				String str = jsonArray.get(0).toString();
				FlowPackageCheckDTO flow = JSONObject.parseObject(str, FlowPackageCheckDTO.class);
				return ResultDTO.success(flow);
			} else {
				System.out.println(juhe.getError_code() + ":" + juhe.getReason());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultDTO.fail(juhe.getReason());
	}

	/**
	 * 流量充值
	 * @param phone:流量充值手机号
	 * @param pid:流量充值套餐ID
	 * @return
	 */
	public ResultDTO flowCharge(String phone, String pid) {

		String result = null;
		String url = "http://v.juhe.cn/flow/recharge";// 请求接口地址
		JuheDTO juhe = new JuheDTO();
		String orderid = DateUtils.getNowYMDOnlyStr() + phone + sequenceService.getOrderSequence("t_trade_order");

		StringBuffer sb = new StringBuffer();
		String enData = sb.append("OpenID=").append(OpenId).append("&key=").append(APPKEY).append("&phone=")
				.append(phone).append("&orderid").append(orderid).toString();
		String sign = Md5Util.MD5(enData);
		sb.delete(0, sb.length());//清空
		String sendData = sb.append("?key=").append(APPKEY).append("&phone=").append(phone).append("&orderid=")
		.append(orderid).append("&sign").append(sign).toString();
		try {
			result = net(url, sendData, "GET");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @param strUrl:提交的url
	 * @param params:提交参数,?xxxx=xxxx&格式
	 * @param method:提交方法:POST/GET
	 * @return
	 * @throws Exception
	 */
	public String net(String strUrl, String params, String method) throws Exception {
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
	public String net(String strUrl, Map params, String method) throws Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			if (method == null || method.equals("GET")) {
				strUrl = strUrl + "?" + urlencode(params);
			}
			System.out.println("strUrl = "+strUrl);
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
	public String urlencode(Map<String, Object> data) {
		StringBuilder sb = new StringBuilder();
		String str = null;
		for (Map.Entry i : data.entrySet()) {
				System.out.println("i=["+i+"]");
				try {
					sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", DEF_CHATSET)).append("&");
					str = sb.toString();
					str = str.replaceAll("%22", "");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return str.substring(0, str.length()-1);
	}
}
