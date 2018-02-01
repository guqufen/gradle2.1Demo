package net.fnsco.core.alipay;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.beust.jcommander.internal.Maps;

import net.fnsco.core.base.ResultDTO;

/**
 * @desc 支付宝支付下单工具类
 * @author tangliang
 * @version 0.0.1-SNAPSHOT
 * @since Ver 1.1
 * @Date 2018年1月31日 下午2:40:52
 */
@Service
public class AlipayClientUtil {

	private static Logger logger = LoggerFactory.getLogger(AlipayClientUtil.class);

	private static String appId = "";
	private static String appPrivateKey = "";
	private static String alipayPublicKey = "";

	private static AlipayClient alipayClient = null;

	public static void initAlipayClient() {
		alipayClient = new DefaultAlipayClient(AlipayConstants.SERVER_URL, appId, appPrivateKey, "json",
				AlipayConstants.CHARSET, alipayPublicKey, "RSA2");
	}

	/**
	 * createPayOrderParams:(APP支付下单方法，返回结果可以直接应用于APP调用支付宝客服端)
	 *
	 * @param @param
	 *            requestParams
	 * @param @return
	 *            设定文件
	 * @return String DOM对象
	 * @author tangliang
	 * @date 2018年1月31日 下午4:39:52
	 */
	public static String createPayOrderParams(AlipayRequestParams requestParams) {
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody(requestParams.getBody());
		model.setSubject(requestParams.getSubject());
		model.setOutTradeNo(requestParams.getOutTradeNo());
		model.setTimeoutExpress("30m");
		model.setTotalAmount(requestParams.getTotalAmount());
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl(requestParams.getNotifyUrl());
		AlipayTradeAppPayResponse response = null;
		try {
			// 这里和普通的接口调用不同，使用的是sdkExecute
			response = alipayClient.sdkExecute(request);
			logger.error(response.getBody());// 就是orderString 可以直接给客户端请求，无需再做处理。
		} catch (AlipayApiException e) {
			logger.error("发起支付宝支付报错!", e);
		}

		return response.getBody();
	}
	
	/**
	 * rsaCheckV1:(使用RSA的验签方法,只有返回结果中signature为true才是合法数据，否则可以不处理)
	 *
	 * @param  @param request
	 * @param  @return    设定文件
	 * @return boolean    DOM对象
	 * @author tangliang
	 * @date   2018年2月1日 上午10:01:51
	 */
	public static Map<String,Object> rsaCheckV1(HttpServletRequest request) {
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		Map<String, Object> resultMap = Maps.newHashMap();
		
		boolean flag = false;
		try {
			flag = AlipaySignature.rsaCheckV1(params, alipayPublicKey, AlipayConstants.CHARSET,
					"RSA2");
		} catch (AlipayApiException e) {
			logger.error("校验签名异常!", e);
		}
		resultMap.put("signature", flag);
		resultMap.put("params", params);
		
		return resultMap;
	}

	/**
	 * appId
	 *
	 * @return the appId
	 * @since CodingExample Ver 1.0
	 */

	public static String getAppId() {
		return appId;
	}

	/**
	 * appId
	 *
	 * @param appId
	 *            the appId to set
	 * @since CodingExample Ver 1.0
	 */

	public static void setAppId(String appId) {
		AlipayClientUtil.appId = appId;
	}

	/**
	 * appPrivateKey
	 *
	 * @return the appPrivateKey
	 * @since CodingExample Ver 1.0
	 */

	public static String getAppPrivateKey() {
		return appPrivateKey;
	}

	/**
	 * appPrivateKey
	 *
	 * @param appPrivateKey
	 *            the appPrivateKey to set
	 * @since CodingExample Ver 1.0
	 */

	public static void setAppPrivateKey(String appPrivateKey) {
		AlipayClientUtil.appPrivateKey = appPrivateKey;
	}

	/**
	 * alipayPublicKey
	 *
	 * @return the alipayPublicKey
	 * @since CodingExample Ver 1.0
	 */

	public static String getAlipayPublicKey() {
		return alipayPublicKey;
	}

	/**
	 * alipayPublicKey
	 *
	 * @param alipayPublicKey
	 *            the alipayPublicKey to set
	 * @since CodingExample Ver 1.0
	 */

	public static void setAlipayPublicKey(String alipayPublicKey) {
		AlipayClientUtil.alipayPublicKey = alipayPublicKey;
	}

}
