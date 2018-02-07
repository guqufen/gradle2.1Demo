package net.fnsco.web.controller.e789.open.pay;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.alipay.AlipayClientUtil;
import net.fnsco.core.alipay.AlipayRefundRequestParams;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.trading.comm.TradeConstants;
import net.fnsco.trading.comm.TradeConstants.WithdrawStateEnum;
import net.fnsco.trading.comm.TradeConstants.thrRechargeStateEnum;
import net.fnsco.trading.service.third.reCharge.PrepaidRefillService;
import net.fnsco.trading.service.third.reCharge.RechargeOrderService;
import net.fnsco.trading.service.third.reCharge.dto.ChargeDTO;
import net.fnsco.trading.service.third.reCharge.dto.ChargeResultDTO;
import net.fnsco.trading.service.third.reCharge.dto.JuheDTO;
import net.fnsco.trading.service.third.reCharge.entity.RechargeOrderDO;
import net.fnsco.trading.service.third.ticket.TicketOrderService;
import net.fnsco.trading.service.withdraw.TradeWithdrawService;
import net.fnsco.trading.service.withdraw.entity.TradeWithdrawDO;

/**
 * @desc 支付宝异步通知接口
 * @author tangliang
 * @version 0.0.1-SNAPSHOT
 * @since Ver 1.1
 * @Date 2018年1月31日 下午5:12:52
 */
@Controller
@RequestMapping(value = "/trade/alipay", method = RequestMethod.POST)
@Api(value = "/trade/alipay", tags = { "支付宝异步通知接口" })
public class AlipayNotifyController extends BaseController {

	@Autowired
	private TradeWithdrawService tradeWithdrawService;
	@Autowired
	private TicketOrderService ticketOrderService;
	@Autowired
	private PrepaidRefillService prepaidRefillService;
	@Autowired
	private RechargeOrderService rechargeOrderService;

	/**
	 * appAliPayNotify:(支付宝APP充值支付异步通知接口，仅支付宝方调用，其他人不可调用)
	 *
	 * @param @return
	 *            设定文件
	 * @return String DOM对象
	 * @author tangliang
	 * @date 2018年1月31日 下午5:17:13
	 */
	@Transactional
	@RequestMapping(value = "/appPayNotify")
	@ApiOperation(value = "支付宝APP充值支付异步通知接口，仅支付宝方调用")
	@ResponseBody
	public String appAliPayNotify() {

		Map<String, Object> rsaMap = AlipayClientUtil.rsaCheckV1(request);
		boolean flag = (boolean) rsaMap.get("signature");
		/**
		 * 先对来源数据认证，如果不是支付宝方调用，则舍弃
		 */
		if (!flag) {
			logger.error("本次调用非正常调用!");
			return "fail";
		}
		/**
		 * 在认证是支付宝发来的数据后，接下来处理业务
		 */
		Map<String, String> params = (Map<String, String>) rsaMap.get("params");
		String orderNo = params.get("out_trade_no");
		TradeWithdrawDO tradeWithdraw = tradeWithdrawService.getByOrderNo(orderNo);
		String tradeStatus = params.get("trade_status");

		if (null == tradeWithdraw) {
			logger.error("该订单已经不存在，不处理!orderNo=" + orderNo);
			return "success";
		}

		/**
		 * 处理完成的订单，不处理
		 */
		if (tradeWithdraw.getStatus() == WithdrawStateEnum.SUCCESS.getCode()) {
			logger.error("该订单已经处理过，不处理!orderNo=" + orderNo);
			return "success";
		}
		// 交易超时未付款或关闭
		if ("TRADE_CLOSED".equals(tradeStatus)) {
			tradeWithdrawService.doAlipayRechangeNotify(params, false, tradeWithdraw);
			return "success";
		}

		boolean tradeStatusVali = AlipayClientUtil.checkTradeStatue(params);
		if (!tradeStatusVali) {
			logger.error("该订单非完成支付状态，不处理!orderNo=" + params.get("out_trade_no"));
			return "fail";
		}
		// 成功处理
		tradeWithdrawService.doAlipayRechangeNotify(params, true, tradeWithdraw);
		return "success";
	}

	/**
	 * 火车票购买支付宝支付回调
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ticketPayNotify")
	@ApiOperation(value = "支付宝APP火车票支付异步通知接口，仅支付宝方调用")
	@ResponseBody
	public String ticketPayNotify() {
		logger.error("进入火车票购买回调!");
		Map<String, Object> rsaMap = AlipayClientUtil.rsaCheckV1(request);
		boolean flag = (boolean) rsaMap.get("signature");
		/**
		 * 先对来源数据认证，如果不是支付宝方调用，则舍弃
		 */
		if (!flag) {
			logger.error("本次调用非正常调用!");
			return "fail";
		}

		/**
		 * 在认证是支付宝发来的数据后，接下来处理业务
		 */
		Map<String, String> params = (Map<String, String>) rsaMap.get("params");
		String orderNo = params.get("out_trade_no");
		TradeWithdrawDO tradeWithdraw = tradeWithdrawService.getByOrderNo(orderNo);// 通过订单号查找原交易
		String tradeStatus = params.get("trade_status");

		if (null == tradeWithdraw) {
			logger.error("该订单已经不存在，不处理!orderNo=" + orderNo);
			return "success";
		}

		/**
		 * 处理完成的订单，不处理
		 */
		if (tradeWithdraw.getStatus() == WithdrawStateEnum.SUCCESS.getCode()) {
			logger.error("该订单已经处理过，不处理!orderNo=" + orderNo);
			return "success";
		}
		// 交易超时未付款或关闭
		if ("TRADE_CLOSED".equals(tradeStatus)) {
			tradeWithdrawService.doAlipayRechangeNotify(params, false, tradeWithdraw);
			return "success";
		}

		boolean tradeStatusVali = AlipayClientUtil.checkTradeStatue(params);
		if (!tradeStatusVali) {
			logger.error("该订单非完成支付状态，不处理!orderNo=" + params.get("out_trade_no"));
			return "fail";
		}
		// 成功处理
		ticketOrderService.payByZFBNotify(orderNo);
		return "success";
	}

	/**
	 * 支付宝回调接口,接收支付结果信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/rechargePayNotify")
	@ApiOperation(value = "支付宝APP支付异步通知接口，仅支付宝方调用")
	@ResponseBody
	public String rechargeAliPayNotify() {

		// ChargeResultDTO ph = new ChargeResultDTO();
		Map<String, Object> rsaMap = AlipayClientUtil.rsaCheckV1(request);// 解析并校验请求

		logger.info("手机充值-支付宝回调,返给数据:" + rsaMap);

		// 先对来源数据认证，如果不是支付宝方调用，则舍弃
		boolean flag = (boolean) rsaMap.get("signature");
		if (!flag) {
			logger.error("手机充值-支付宝回调，本次调用非正常调用!");
			return "fail";
		}
		logger.info("手机充值-支付宝回调，数据源校验成功");

		Map<String, String> params = (Map<String, String>) rsaMap.get("params");
		String orderNo = params.get("out_trade_no");
		String status = params.get("trade_status");// 交易状态

		// 手机充值表查找订单号交易<主要提供充值手机号,如果为空充值交易进行不下去，故需要去退款>
		RechargeOrderDO reChargeOrderDO = rechargeOrderService.getByOrderNo(orderNo);

		if (null == reChargeOrderDO) {
			logger.error("手机充值-支付宝回调通知函数，订单号在reChargeOrder找不到原交易，orderNo=[" + orderNo + "]");

			// 退款
			AlipayRefundRequestParams requestParams = new AlipayRefundRequestParams();
			requestParams.setTradeNo(orderNo);// 设置订单号
			AlipayTradeRefundResponse alipayTradeRefundResponse = AlipayClientUtil
					.createTradeReturnOrderParams(requestParams);

			// 退款本次资金发生了变化
			if ("Y".equals(alipayTradeRefundResponse.getFundChange()) && alipayTradeRefundResponse.isSuccess()) {

				logger.error("手机充值-支付宝退款成功,orderno=[" + orderNo + "]");

				// 本次资金没有发生变化
			} else {

				logger.error("手机充值-退款失败,orderno=[" + orderNo + "]");
			}
			return "success";

		}

		// 避免订单重复获取，需要判断订单状态是否成功
		if (thrRechargeStateEnum.SUCCESS.getCode() == reChargeOrderDO.getStatus()) {

			logger.info("该笔订单已经充值成功过，故本次回调充值忽略，orderNo=[" + orderNo + "]");
			return "success";
		}

		// 交易超时未付款或关闭
		if ("TRADE_CLOSED".equals(status)) {
			prepaidRefillService.doAlipayThrChangeNotify(params, false, reChargeOrderDO);
			return "success";
		}

		// 校验交易成功
		boolean tradeStatusVali = AlipayClientUtil.checkTradeStatue(params);
		if (!tradeStatusVali) {
			logger.error("该订单非完成支付状态，不处理!orderNo=" + params.get("out_trade_no"));
			return "fail";
		}
		prepaidRefillService.doAlipayThrChangeNotify(params, true, reChargeOrderDO);

		return "success";
	}
}
