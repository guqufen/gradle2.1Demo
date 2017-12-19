package net.fnsco.trading.service.third.phoneBill;

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
import java.util.Date;
import java.util.HashMap;
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

import net.fnsco.bigdata.comm.ServiceConstant.TradeStateEnum;
import net.fnsco.bigdata.service.sys.SequenceService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.freamwork.comm.Md5Util;
import net.fnsco.trading.service.order.TradeOrderService;
import net.fnsco.trading.service.order.entity.TradeOrderDO;
import net.fnsco.trading.service.third.phoneBill.dto.JuheDTO;
import net.fnsco.trading.service.third.phoneBill.dto.PhoneChargeDTO;
import net.fnsco.trading.service.third.phoneBill.dto.PhoneChargePackageDTO;

@Service
public class PrepaidRefillService extends BaseService {

	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private TradeOrderService tradeOrderService;

	private final String DEF_CHATSET = "UTF-8";
	private final int DEF_CONN_TIMEOUT = 30000;
	private final int DEF_READ_TIMEOUT = 30000;
	private String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

	// HttpClient请求的相关设置，可以不用配置，用默认的参数，这里设置连接和超时时长(毫秒)
	public static RequestConfig config = RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(30000)
			.build();

	private final String OpenId = "JH284e6d24f2e3f5d89668a64b50c2c886";

	// 配置流量充值
	private final String APPKEYFLOW = "6121e6e31f9e1eff037d8c416e0ee4fe";

	// 配置话费充值
	private final String APPKEYREPAID = "9b9450b3b217f8c0c7dcb2a28eafbbb7";
	private final String telQueryUrl = "http://op.juhe.cn/ofpay/mobile/telquery?cardnum=*&phoneno=!&key="
			+ APPKEYREPAID;
	private final String onlineUrl = "http://op.juhe.cn/ofpay/mobile/onlineorder?key=" + APPKEYREPAID
			+ "&phoneno=!&cardnum=*&orderid=@&sign=$";
	public final String orderstaUrl="http://op.juhe.cn/ofpay/mobile/ordersta?key="+APPKEYREPAID+"&orderid=!";

	/**
	 * 号码充值套餐优惠资费查询，面额：10，20，30，50，100，200,循环获取各个面额原价与优惠价格
	 * 
	 * @param phone
	 * @return
	 */
	public ResultDTO<PhoneChargePackageDTO> prepaidRefillCheck(String phone) {

		Integer[] denos = { 10, 20, 30, 50, 100, 200, 300 };
		String result;
		PhoneChargePackageDTO phChargePackageDTO = new PhoneChargePackageDTO();
		List<PhoneChargeDTO> list = new ArrayList<>();

		for (Integer done : denos) {

			try {
				/**
				 * 充值资费查询，聚合数据返回 { "reason": "成功", "result": { "cardid":
				 * "191404", //卡类ID "cardname": "江苏电信话费100元直充", //卡类名称
				 * "inprice": 98.4, //购买价格 "game_area": "江苏苏州电信" //手机号码归属地 },
				 * "error_code": 0 }
				 */
				result = get(telQueryUrl.replace("*", done + "").replace("!", phone), 0);
				logger.info(result);

				JuheDTO juhe = JSON.parseObject(result, JuheDTO.class);
				if (juhe.getError_code() == 0) {

					System.out.println(juhe.getResult());

					Map<String, Object> map = JSONObject.parseObject(juhe.getResult().toString(), Map.class);
					PhoneChargeDTO phChargeDTO = new PhoneChargeDTO();
					phChargeDTO.setId(String.valueOf(done));
					phChargeDTO.setName(done + "元");
					BigDecimal bigDecimal = new BigDecimal(map.get("inprice").toString());
					phChargeDTO.setInprice(bigDecimal + "");
					list.add(phChargeDTO);
					phChargePackageDTO.setCompany(map.get("game_area").toString());
					phChargePackageDTO.setCardArea(map.get("game_area").toString());
					phChargePackageDTO.setType("1");// 设置支持类型，1-全国
				} else {
					logger.error("话费充值失败,原因:+" + juhe.getReason());
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
	public ResultDTO<PhoneChargePackageDTO> flowPackageCheck(String phone) {
		String result = null;
		String url = "http://v.juhe.cn/flow/telcheck";// 请求接口地址
		PhoneChargePackageDTO phChargePackageDTO = new PhoneChargePackageDTO();
		List<PhoneChargeDTO> list = new ArrayList<>();

		StringBuffer sb = new StringBuffer();
		String sendData = sb.append("?phone=").append(phone).append("&key=").append(APPKEYFLOW).toString();

		try {
			/**
			 * 数据返回格式如下： { "reason": "success", "result": [ { "city": "全国",
			 * //支持城市 "company": "中国移动", //运营商 "companytype": "2", //运营商ID
			 * "name": "中国移动全国流量套餐", //套餐名称 "type": "1", //支持类型1：全国 2：城市
			 * "flows": [ //流量套餐列表 { "id": "3", //套餐ID "p": "10M", //套餐流量名称 "v":
			 * "10", //套餐流量值 "inprice": "2.90" //价格 } ] } ], "error_code": 0 }
			 */
			result = net(url, sendData, "GET");
			JuheDTO juhe = JSONObject.parseObject(result, JuheDTO.class);

			// 查询成功
			if (juhe.getError_code() == 0) {
				// logger.info(juhe.getResult()+"");

				// 获取需要返回的数据域(套餐类型)
				JSONArray jsonArray = (JSONArray) juhe.getResult();
				String str = jsonArray.get(0).toString();
				/**
				 * 
				 */
				Map<String, Object> map = JSONObject.parseObject(str, Map.class);
				logger.info(map.toString());
				phChargePackageDTO.setCompany(map.get("company").toString());
				phChargePackageDTO.setType(map.get("type").toString());
				List<Map<String, String>> lists = JSONObject.parseObject(map.get("flows").toString(), List.class);
				for (Map<String, String> map2 : lists) {
					/**
					 * 返回的套餐资费list id string 套餐ID; p string 套餐流量名称 ; v string
					 * 套餐流量值 ; inprice string 价格;
					 */
					PhoneChargeDTO phChargeDTO = new PhoneChargeDTO();
					phChargeDTO.setId(map2.get("id"));
					phChargeDTO.setName(map2.get("p"));
					phChargeDTO.setInprice(map2.get("inprice"));
					list.add(phChargeDTO);
				}
				phChargePackageDTO.setList(list);

				return ResultDTO.success(phChargePackageDTO);
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
	 * 
	 * @param phone:流量充值手机号
	 * @param pid:流量充值套餐ID
	 * @param innerCode:内部商户号，便于插表数据
	 * @return 请求示例：http://v.juhe.cn/flow/recharge?key=您申请的APPKEY&phone=
	 *         18913513535&pid=8&orderid=a1122111d&sign=
	 *         721a3f667b0eb63f54517971181e7392
	 */
	public ResultDTO flowCharge(String phone, String pid, String innerCode) {

		String result = null;
		String url = "http://v.juhe.cn/flow/recharge";// 请求接口地址
		String orderid = DateUtils.getNowYMDOnlyStr() + phone + sequenceService.getOrderSequence("t_trade_order");
		

		// md5,校验值，md5(OpenID+key+phone+pid+orderid)，结果转为小写
		String sign = Md5Util.MD5(OpenId + APPKEYFLOW + phone + pid + orderid).toLowerCase();

		StringBuffer sb = new StringBuffer();
		String sendData = sb.append("?key=").append(APPKEYFLOW).append("&phone=").append(phone).append("&pid=")
				.append(pid).append("&orderid=").append(orderid).append("&sign=").append(sign).toString();

		TradeOrderDO tradeData = new TradeOrderDO();
		tradeData.setOrderNo(orderid);// 设置订单号
		tradeData.setTxnAmount(new BigDecimal(pid));// 设置交易金额
		tradeData.setChannelType("80");// 设置渠道类型，80-法奈昇余额
		tradeData.setOrderCeateTime(new Date());// 设置订单创建时间
		tradeData.setTxnType(1);// 设置交易类型:1-消费
		tradeData.setTxnSubType(13);// 设置交易子类型:13-购买流量
		tradeData.setPayType("03");// 设置支付方式:03-余额
		tradeData.setPaySubType("04");// 设置支付子方式:04-余额
		tradeData.setInnerCode(innerCode);// 设置内部商户号
		tradeData.setPayMedium("01");// 支付媒介:01-app
		tradeOrderService.doAdd(tradeData);// 添加

		try {
			result = net(url, sendData, "GET");
			logger.info(result);
			JuheDTO juhe = JSONObject.parseObject(result, JuheDTO.class);

			// 交易成功
			if (juhe.getError_code() == 0) {

				Map<String, String> map = JSONObject.parseObject(juhe.getResult().toString(), Map.class);
				tradeData.setPayOrderNo(map.get("sporder_id"));// 设置渠道订单号
				tradeData.setTxnAmount(new BigDecimal(map.get("ordercash")));// 设置实际消费金额
				tradeData.setCompleteTime(new Date());// 设置交易完成时间
				tradeData.setRespCode(TradeStateEnum.SUCCESS.getCode());// 交易成功
				tradeData.setRespMsg(juhe.getReason());// 设置响应
				tradeOrderService.doUpdate(tradeData);

				Map<String, String> map2 = new HashMap<>();
				map2.put("orderNo", map.get("orderid"));
				map2.put("respCode", TradeStateEnum.SUCCESS.getCode());
				map2.put("respMsg", juhe.getReason());
				return ResultDTO.success(map2);
			} else if (juhe.getError_code() == 10014) {// 系统内部异常(调用充值类业务时，请务必联系客服或通过订单查询接口检测订单，避免造成损失)

				tradeData.setRespCode("1000");// 交易进行中，需要再次调用订单查询接口进行查询
				tradeData.setRespMsg(juhe.getReason());// 设置响应
				tradeOrderService.doUpdate(tradeData);

				return ResultDTO.fail("系统内部异常，请稍后查询");
			} else {

				tradeData.setRespCode(TradeStateEnum.FAIL.getCode());// 交易失败
				tradeData.setRespMsg(juhe.getReason());// 设置响应
				tradeData.setCompleteTime(new Date());// 设置交易完成时间
				tradeOrderService.doUpdate(tradeData);

				return ResultDTO.fail(juhe.getReason());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultDTO.fail();
	}

	/**
	 * 话费充值
	 * 
	 * @param phone:手机号
	 * @param pid:套餐id(金额)
	 * @return
	 */
	public ResultDTO prepaidRefillCharge(String phone, String pid, String innerCode) {

		String result = null;
//		Map<String, String> map = new HashMap<>();
		String orderid = DateUtils.getNowYMDOnlyStr() + phone + sequenceService.getOrderSequence("t_trade_order");

		// md5,校验值，md5(OpenID+key+phone+pid+orderid)，结果转为小写
		String sign = Md5Util.MD5(OpenId + APPKEYREPAID + phone + pid + orderid);

		TradeOrderDO tradeData = new TradeOrderDO();
		tradeData.setOrderNo(orderid);// 设置订单号
		tradeData.setTxnAmount(new BigDecimal(pid));// 设置交易金额
		tradeData.setChannelType("80");// 设置渠道类型，80-法奈昇余额
		tradeData.setOrderCeateTime(new Date());// 设置订单创建时间
		tradeData.setTxnType(1);// 设置交易类型:1-消费
		tradeData.setTxnSubType(13);// 设置交易子类型:13-购买流量
		tradeData.setPayType("03");// 设置支付方式:03-余额
		tradeData.setPaySubType("04");// 设置支付子方式:04-余额
		tradeData.setInnerCode(innerCode);// 设置内部商户号
		tradeData.setPayMedium("01");// 支付媒介:01-app
		tradeOrderService.doAdd(tradeData);// 添加

		try {
			result = get(onlineUrl.replace("*", pid + "").replace("!", phone).replace("@", orderid).replace("$", sign),
					0);
			JuheDTO juhe = JSONObject.parseObject(result, JuheDTO.class);
			if (juhe.getError_code() == 0) {

				Map<String, String> map = JSONObject.parseObject(juhe.getResult().toString(), Map.class);
				tradeData.setPayOrderNo(map.get("sporder_id"));// 设置渠道订单号
				tradeData.setTxnAmount(new BigDecimal(map.get("ordercash")));// 设置实际消费金额
				tradeData.setCompleteTime(new Date());// 设置交易完成时间
				tradeData.setRespCode(TradeStateEnum.SUCCESS.getCode());// 交易成功
				tradeData.setRespMsg(juhe.getReason());// 设置响应
				tradeOrderService.doUpdate(tradeData);

				map.put("respCode", TradeStateEnum.SUCCESS.getCode());
				map.put("respMsg", juhe.getReason());
				map.put("orderNo", map.get("orderid"));

				return ResultDTO.success(map);
			} else if (juhe.getError_code() == 10014) {// 系统内部异常(调用充值类业务时，请务必联系客服或通过订单查询接口检测订单，避免造成损失)

				tradeData.setRespCode("1000");// 交易进行中，需要再次调用订单查询接口进行查询
				tradeData.setRespMsg(juhe.getReason());// 设置响应
				tradeOrderService.doUpdate(tradeData);

				return ResultDTO.fail("系统内部异常，请稍后查询");
			} else {

				tradeData.setRespCode(TradeStateEnum.FAIL.getCode());// 交易失败
				tradeData.setRespMsg(juhe.getReason());// 设置响应
				tradeData.setCompleteTime(new Date());// 设置交易完成时间
				tradeOrderService.doUpdate(tradeData);

				return ResultDTO.fail(juhe.getReason());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResultDTO.fail();
	}

	/**
	 * 流量订单状态查询(用于在充值返回系统内部异常时调用)
	 * @param orderNo：原订单号
	 */
    public void queryFlowResult(String orderNo){
        String result =null;
        String url ="http://v.juhe.cn/flow/batchquery";//请求接口地址

        TradeOrderDO tradeOrderDO = tradeOrderService.queryByOrderId(orderNo);
		if (null == tradeOrderDO) {
			logger.info("没有找到该交易请求交易,order_no=[" + orderNo + "]");
		}
        
        Map params = new HashMap();//请求参数
            params.put("orderid",orderNo);//用户订单号，多个以英文逗号隔开，最大支持50组
            params.put("key",APPKEYFLOW);//应用APPKEY(应用详细页查询)
 
        try {
            result =net(url, params, "GET");
            JuheDTO juhe = JSONObject.parseObject(result,JuheDTO.class);
            
            //查询返回成功，则解析result字串取其中的充值状态
            if(juhe.getError_code()==0){
            	
            	Map<String, String> map = JSONObject.parseObject(juhe.getResult().toString(), Map.class);
            	if(map.get("game_state") == "1"){//成功
            		
            		tradeOrderDO.setPayOrderNo(map.get("sporder_id"));// 设置渠道订单号
            		tradeOrderDO.setTxnAmount(new BigDecimal(map.get("uordercash")));// 设置实际消费金额
            		tradeOrderDO.setCompleteTime(new Date());// 设置交易完成时间
            		tradeOrderDO.setRespCode(TradeStateEnum.SUCCESS.getCode());// 交易成功
            		tradeOrderDO.setRespMsg(juhe.getReason());// 设置响应
    				tradeOrderService.doUpdate(tradeOrderDO);
            	}else if(map.get("game_state") == "9"){//失败
            		tradeOrderDO.setRespCode(TradeStateEnum.FAIL.getCode());// 交易失败
            		tradeOrderDO.setRespMsg(juhe.getReason());// 设置响应
    				tradeOrderDO.setCompleteTime(new Date());// 设置交易完成时间
    				tradeOrderService.doUpdate(tradeOrderDO);
            	}
            }else{

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
    * 手机订单状态查询 
    * @param orderid 商家订单号
    * @return 订单结果
    * @throws Exception
     * @throws Exception 
    */
	public void orderSta(String orderNo) {

		String result = null;
		TradeOrderDO tradeOrderDO = tradeOrderService.queryByOrderId(orderNo);
		if (null == tradeOrderDO) {
			logger.info("没有找到该交易请求交易,order_no=[" + orderNo + "]");
		}
		try {
			result = get(orderstaUrl.replace("!", orderNo), 0);
			JuheDTO juhe = JSONObject.parseObject(result, JuheDTO.class);

			// 查询返回成功，则解析result字串取其中的充值状态
			if (juhe.getError_code() == 0) {

				Map<String, String> map = JSONObject.parseObject(juhe.getResult().toString(), Map.class);
				if (map.get("game_state") == "1") {// 成功

					tradeOrderDO.setPayOrderNo(map.get("sporder_id"));// 设置渠道订单号
					tradeOrderDO.setTxnAmount(new BigDecimal(map.get("uordercash")));// 设置实际消费金额
					tradeOrderDO.setCompleteTime(new Date());// 设置交易完成时间
					tradeOrderDO.setRespCode(TradeStateEnum.SUCCESS.getCode());// 交易成功
					tradeOrderDO.setRespMsg(juhe.getReason());// 设置响应
					tradeOrderService.doUpdate(tradeOrderDO);
				} else if (map.get("game_state") == "9") {// 失败
					tradeOrderDO.setRespCode(TradeStateEnum.FAIL.getCode());// 交易失败
					tradeOrderDO.setRespMsg(juhe.getReason());// 设置响应
					tradeOrderDO.setCompleteTime(new Date());// 设置交易完成时间
					tradeOrderService.doUpdate(tradeOrderDO);
				}
			} else {

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public String urlencode(Map<String, Object> data) {
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
