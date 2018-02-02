package net.fnsco.core.alipay;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.beust.jcommander.internal.Maps;

/**
 * @desc 支付宝支付下单工具类
 * @author tangliang
 * @version 0.0.1-SNAPSHOT
 * @since Ver 1.1
 * @Date 2018年1月31日 下午2:40:52
 */
public class AlipayClientUtil {

	private static Logger logger = LoggerFactory.getLogger(AlipayClientUtil.class);

	private static String appId = "";
	private static String appPrivateKey = "";
	private static String alipayPublicKey = "";
	private static String alipayAppPublicKey = "";

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
	public static String createPayOrderParams(AlipayAppPayRequestParams requestParams) {
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
	 * createTradeReturnOrderParams:(本方法为支付宝退款接口，详情说明:当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，支付宝将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退到买家帐号上。 交易超过约定时间（签约时设置的可退款时间）的订单无法进行退款 支付宝退款支持单笔交易分多次退款，多次退款需要提交原支付订单的商户订单号和设置不同的退款单号。一笔退款失败后重新提交，要采用原来的退款单号。总退款金额不能超过用户实际支付金额)
	 * 
	 * @param  @param requestParams
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @author tangliang
	 * @date   2018年2月1日 下午4:29:53
	 */
	public static AlipayTradeRefundResponse createTradeReturnOrderParams(AlipayRefundRequestParams requestParams) {
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		
		AlipayTradeRefundModel model = new AlipayTradeRefundModel();
		model.setRefundAmount(requestParams.getRefundAmount());
		model.setRefundReason(requestParams.getRefundReason());
		model.setOutTradeNo(requestParams.getOutTradeNo());
		request.setBizModel(model);
		AlipayTradeRefundResponse response = null;
		try {
			response = alipayClient.sdkExecute(request);
		} catch (AlipayApiException e) {
			logger.error("发起支付宝退款接口报错!", e);
		}
		
		if(response.isSuccess()) {
			logger.error("该订单退款成功!orderNo="+requestParams.getOutTradeNo()+",退款金额为:"+requestParams.getRefundAmount());
		}
		return response;
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
			flag = AlipaySignature.rsaCheckV1(params, alipayAppPublicKey, AlipayConstants.CHARSET,
					"RSA2");
		} catch (AlipayApiException e) {
			logger.error("校验签名异常!", e);
		}
		resultMap.put("signature", flag);
		resultMap.put("params", params);
		
		return resultMap;
	}
	
	/**
	 * checkTradeStatue:(根据支付宝POST返回的数据集合判断交易状态，只有为交易成功或交易完成才能是成功)
	 * 状态说明:TRADE_FINISHED --交易完成,触发条件是商户签约的产品不支持退款功能的前提下，买家付款成功；或者，商户签约的产品支持退款功能的前提下，交易已经成功并且已经超过可退款期限。  
	 * 状态说明:TRADE_SUCCESS  --支付成功,触发条件是商户签约的产品支持退款功能的前提下，买家付款成功； 
	 * @param  @param params
	 * @param  @return    设定文件
	 * @return boolean    DOM对象
	 * @author tangliang
	 * @date   2018年2月1日 上午11:38:42
	 */
	public static boolean checkTradeStatue(Map<String,String> params) {
		if(params != null && params.get("trade_status") != null) {
			if("TRADE_SUCCESS".equals(params.get("trade_status")) || "TRADE_FINISHED".equals(params.get("trade_status"))) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * alipayAppPublicKey
	 *
	 * @return  the alipayAppPublicKey
	 * @since   CodingExample Ver 1.0
	*/
	
	public static String getAlipayAppPublicKey() {
		return alipayAppPublicKey;
	}

	/**
	 * alipayAppPublicKey
	 *
	 * @param   alipayAppPublicKey    the alipayAppPublicKey to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public static void setAlipayAppPublicKey(String alipayAppPublicKey) {
		AlipayClientUtil.alipayAppPublicKey = alipayAppPublicKey;
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
