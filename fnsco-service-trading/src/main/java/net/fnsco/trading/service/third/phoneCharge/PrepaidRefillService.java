package net.fnsco.trading.service.third.phoneCharge;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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
import net.fnsco.trading.service.third.phoneCharge.dto.FlowPackageCheckDTO;
import net.fnsco.trading.service.third.phoneCharge.dto.JuheDTO;
import net.fnsco.trading.service.third.phoneCharge.dto.PhoneChargeDTO;
import net.fnsco.trading.service.third.phoneCharge.dto.PhoneChargePackageDTO;

@Service
public class PrepaidRefillService extends BaseService{

	@Autowired
    private SequenceService  sequenceService;
	private final String DEF_CHATSET = "UTF-8";
    private final int DEF_CONN_TIMEOUT = 30000;
    private final int DEF_READ_TIMEOUT = 30000;
    private String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

	// HttpClient请求的相关设置，可以不用配置，用默认的参数，这里设置连接和超时时长(毫秒)
	public static RequestConfig config = RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(30000)
			.build();

	//配置您申请的KEY
    private final String APPKEYFLOW ="6121e6e31f9e1eff037d8c416e0ee4fe";
    private final String OpenId="JH284e6d24f2e3f5d89668a64b50c2c886";
    private final String APPKEYREPAID = "9b9450b3b217f8c0c7dcb2a28eafbbb7";
    private final String telQueryUrl = "http://op.juhe.cn/ofpay/mobile/telquery?cardnum=*&phoneno=!&key=" + APPKEYREPAID;

	/**
	 * 号码充值套餐优惠资费查询，面额：10，20，30，50，100，200,循环获取各个面额原价与优惠价格
	 * 
	 * @param phone
	 * @return
	 */
	public ResultDTO prepaidRefillCheck(String phone) {
		Integer[] denos = { 10, 20, 30, 50, 100, 200, 300 };
		String result;
		PhoneChargePackageDTO phChargePackageDTO = new PhoneChargePackageDTO();
		List<PhoneChargeDTO> list = new ArrayList<>();

		for (Integer done : denos) {

			try {
				result = get(telQueryUrl.replace("*", done + "").replace("!", phone), 0);
				System.out.println(result);

				JuheDTO juhe = JSON.parseObject(result, JuheDTO.class);
				if (juhe.getError_code() == 0) {

					System.out.println(juhe.getResult());

					Map<String, Object> map = JSONObject.parseObject(juhe.getResult().toString(), Map.class);
					PhoneChargeDTO phChargeDTO = new PhoneChargeDTO();
					phChargeDTO.setId(String.valueOf(done));
					phChargeDTO.setName(done + "元");
					BigDecimal bigDecimal = (BigDecimal) map.get("inprice");
					phChargeDTO.setInprice(bigDecimal + "");
					list.add(phChargeDTO);
					phChargePackageDTO.setCompany(map.get("game_area").toString());
					phChargePackageDTO.setCardArea(map.get("game_area").toString());
					System.out.println(map);
				} else {
					return ResultDTO.fail(juhe.getReason());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		phChargePackageDTO.setList(list);// 设置套餐资费列表
		return ResultDTO.success(phChargePackageDTO);
	}

	/**
	 * 号码流量套餐优惠资费查询
	 * 
	 * @param phone:手机号码
	 * @return
	 */
	public ResultDTO<FlowPackageCheckDTO> flowPackageCheck(String phone) {
		String result = null;
		String url = "http://v.juhe.cn/flow/telcheck";// 请求接口地址
		PhoneChargePackageDTO phChargePackageDTO = new PhoneChargePackageDTO();
		List<PhoneChargeDTO> list = new ArrayList<>();
		
		StringBuffer sb = new StringBuffer();
		String sendData = sb.append("?phone=").append(phone).append("&key=").append(APPKEYFLOW).toString();

		try {
			result = net(url, sendData, "GET");
			JuheDTO juhe =  JSONObject.parseObject(result, JuheDTO.class);

			// 查询成功
			if (juhe.getError_code() == 0) {
				System.out.println(juhe.getResult());

				// 获取需要返回的数据域(套餐类型)
				JSONArray jsonArray = (JSONArray) juhe.getResult();
				String str = jsonArray.get(0).toString();
				Map<String, Object> map = JSONObject.parseObject(str, Map.class);
				System.out.println(map);
				phChargePackageDTO.setCompany(map.get("company").toString());
				List<Map<String, String>> lists = JSONObject.parseObject(map.get("flows").toString(), List.class);
				System.out.println(lists);
				PhoneChargeDTO phChargeDTO = new PhoneChargeDTO();
				
				FlowPackageCheckDTO flow = JSONObject.parseObject(str, FlowPackageCheckDTO.class);
				return ResultDTO.success(flow);
			} else {
				System.out.println(juhe.getError_code() + ":" + juhe.getReason());
				return ResultDTO.fail(juhe.getReason());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultDTO.fail();
	}

	/**
	 * 流量充值
	 * @param phone:流量充值手机号
	 * @param pid:流量充值套餐ID
	 * @return
	 * 请求示例：http://v.juhe.cn/flow/recharge?key=您申请的KEY&phone=18913513535&pid=8&orderid=a1122111d&sign=721a3f667b0eb63f54517971181e7392
	 */
	public ResultDTO flowCharge(String phone, String pid) {

		String result = null;
		String url = "http://v.juhe.cn/flow/recharge";// 请求接口地址
		JuheDTO juhe = new JuheDTO();
		String orderid = DateUtils.getNowYMDOnlyStr() + phone + sequenceService.getOrderSequence("t_trade_order");

		//md5,校验值，md5(OpenID+key+phone+pid+orderid)，结果转为小写
		String sign = Md5Util.MD5(OpenId+APPKEYFLOW+phone+pid+orderid).toLowerCase();

		StringBuffer sb = new StringBuffer();
		String sendData = sb.append("?key=").append(APPKEYFLOW).append("&phone=").append(phone).append("&pid=")
				.append(pid).append("&orderid=").append(orderid).append("&sign").append(sign).toString();
		try {
			result = net(url, sendData, "GET");
			System.out.println(result);
		} catch (Exception e) {

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
					e.printStackTrace();
				}
		}
		return str.substring(0, str.length()-1);
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
