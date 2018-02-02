package net.fnsco.web.controller.e789.third.reCharge;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.response.AlipayTradeRefundResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.alipay.AlipayClientUtil;
import net.fnsco.core.alipay.AlipayRefundRequestParams;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.utils.CodeUtil;
import net.fnsco.trading.service.third.reCharge.PrepaidRefillService;
import net.fnsco.trading.service.third.reCharge.dto.ChargeDTO;

/**
 * 支付宝异步通知接口
 * 
 * @author 伯约
 *
 */
@Controller
@RequestMapping(value = "/trade/alipay", method = RequestMethod.POST)
@Api(value = "/trade/alipay", tags = { "支付宝异步通知接口" })
public class RechargeAlipayController extends BaseController {

	@Autowired
	private PrepaidRefillService prepaidRefillService;
	/**
	 * 支付宝回调接口,接收支付结果信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/reChargePayNotify")
	@ApiOperation(value = "支付宝APP支付异步通知接口，仅支付宝方调用")
	@ResponseBody
	public String appAliPayNotify() {

		Map<String, Object> rsaMap = AlipayClientUtil.rsaCheckV1(request);// 解析并校验请求
		boolean flag = (boolean) rsaMap.get("signature");

		/**
		 * 先对来源数据认证，如果不是支付宝方调用，则舍弃
		 */
		if (!flag) {
			logger.error("本次调用非正常调用!");
			return "fail";
		}

		/**
		 * 在认证是支付宝发来的数据后，接下来处理业务，充值话费/流量
		 */
		String orderId = (String) rsaMap.get("out_trade_no");//商户订单号
		String channerOrderId = (String) rsaMap.get("trade_no");//支付宝订单号
		String status = (String) rsaMap.get("trade_status");//交易状态
		if("TRADE_SUCCESS".equals(status) || "TRADE_FINISHED".equals(status)){
			
		}
		ChargeDTO chargeDTO = new ChargeDTO();
		String orderid = CodeUtil.generateOrderCode("");
		prepaidRefillService.recharge(chargeDTO, orderid);

		// 退款
		AlipayRefundRequestParams requestParams = new AlipayRefundRequestParams();
		AlipayTradeRefundResponse alipayTradeRefundResponse = AlipayClientUtil
				.createTradeReturnOrderParams(requestParams);

		// 调用成功
		if ("10000".equals(alipayTradeRefundResponse.getCode())) {

			// 调用失败
		} else {

		}

		return "success";
	}
}
