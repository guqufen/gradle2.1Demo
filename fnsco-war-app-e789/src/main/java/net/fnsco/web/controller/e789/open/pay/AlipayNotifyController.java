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
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.response.AlipayTradeRefundResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.alipay.AlipayClientUtil;
import net.fnsco.core.alipay.AlipayRefundRequestParams;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.trading.comm.TradeConstants;
import net.fnsco.trading.comm.TradeConstants.WithdrawStateEnum;
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
    	Map<String,Object> rsaMap = AlipayClientUtil.rsaCheckV1(request);
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
		Map<String,String> params = (Map<String, String>) rsaMap.get("params");
		String orderNo = params.get("out_trade_no");
		TradeWithdrawDO tradeWithdraw = tradeWithdrawService.getByOrderNo(orderNo);// 通过订单号查找原交易
		String tradeStatus = params.get("trade_status");
		
		
		if(null == tradeWithdraw) {
			logger.error("该订单已经不存在，不处理!orderNo="+orderNo);
			return "success";
		}
		
		/**
		 * 处理完成的订单，不处理
		 */
		if(tradeWithdraw.getStatus() == WithdrawStateEnum.SUCCESS.getCode()) {
			logger.error("该订单已经处理过，不处理!orderNo="+orderNo);
			return "success";
		}
		//交易超时未付款或关闭
		if("TRADE_CLOSED".equals(tradeStatus)) {
			tradeWithdrawService.doAlipayRechangeNotify(params, false, tradeWithdraw);
			return "success";
		}
		
		boolean tradeStatusVali = AlipayClientUtil.checkTradeStatue(params);
		if(!tradeStatusVali) {
			logger.error("该订单非完成支付状态，不处理!orderNo="+params.get("out_trade_no"));
			return "fail";
		}
		//成功处理
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

		ChargeResultDTO ph = new ChargeResultDTO();
		Map<String, Object> rsaMap = AlipayClientUtil.rsaCheckV1(request);// 解析并校验请求

		// 先对来源数据认证，如果不是支付宝方调用，则舍弃
		boolean flag = (boolean) rsaMap.get("signature");
		if (!flag) {
			logger.error("本次调用非正常调用!");
			return "fail";
		}

		// 认证是支付宝发来的数据后，接下来处理业务，充值话费/流量
		ChargeDTO chargeDTO = new ChargeDTO();
		String payName = null;
		String orderNo = (String) rsaMap.get("out_trade_no");// 商户订单号
		// String channerOrderId = (String) rsaMap.get("trade_no");// 支付宝订单号
		String status = (String) rsaMap.get("trade_status");// 交易状态

		// 手机充值表查找订单号交易<主要提供充值手机号,如果为空充值交易进行不下去，故需要去退款>
		RechargeOrderDO reChargeOrderDO = rechargeOrderService.getByOrderNo(orderNo);

		if (null == reChargeOrderDO) {
			logger.error("支付宝回调通知函数，订单号在reChargeOrder找不到原交易，orderNo=[" + orderNo + "]");

			// 退款
			AlipayRefundRequestParams requestParams = new AlipayRefundRequestParams();
			requestParams.setTradeNo(orderNo);// 设置订单号
			AlipayTradeRefundResponse alipayTradeRefundResponse = AlipayClientUtil
					.createTradeReturnOrderParams(requestParams);

			// 调用成功
			if ("10000".equals(alipayTradeRefundResponse.getCode())) {

				// 调用失败
			} else {

			}

			return "success";
		}

		// 订单表查找订单号交易
		TradeWithdrawDO tradeWithdrawDO = tradeWithdrawService.getByOrderNo(orderNo);// 通过订单号查找原交易
		// 订单号流水表为空,则需要将recharge表数据复制给withdraw，然后插表
		if (null == tradeWithdrawDO) {

			logger.error("支付宝回调通知函数，订单号在表中找不到原交易，orderNo=[" + orderNo + "]");
			tradeWithdrawDO.setOrderNo(orderNo);// 设置订单号
			// tradeWithdrawDO.setOriginalOrderNo(orderId);// 设置原订单号(默认等于当前订单号)
			tradeWithdrawDO.setAmount(new BigDecimal(reChargeOrderDO.getAmt()));// 设置交易金额，优惠金额
			tradeWithdrawDO.setAppUserId(chargeDTO.getUserId());// 设置帐号ID
			tradeWithdrawDO.setTradeType(2);// 交易类型:2-消费
			if ("0" == reChargeOrderDO.getType()) {
				tradeWithdrawDO.setTradeSubType(TradeConstants.TxnSubTypeEnum.BUY_HF.getCode());// 交易子类型:22话费充值
				payName = "话费充值";
			} else {
				tradeWithdrawDO.setTradeSubType(TradeConstants.TxnSubTypeEnum.BUY_LT.getCode());// 交易子类型:23流量充值
				payName = "流量充值";
			}
			tradeWithdrawDO.setStatus(WithdrawStateEnum.PROCESSING.getCode());// 设置交易状态，1-执行中
			tradeWithdrawService.doAdd(tradeWithdrawDO);// 更新数据库表
		}

		chargeDTO.setType(Integer.parseInt(reChargeOrderDO.getType()));// 设置类型

		// 支付回调返回交易失败，更新表,返回成功
		if (!"TRADE_SUCCESS".equals(status) && !"TRADE_FINISHED".equals(status)) {

			logger.error(payName + "-支付宝扣款交易失败");

			TradeWithdrawDO tradeWithdraw1 = new TradeWithdrawDO();
			tradeWithdraw1.setId(tradeWithdrawDO.getId());
			tradeWithdraw1.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());// 设置应答码
			tradeWithdraw1.setRespMsg(payName + "-支付宝扣款交易失败");// 设置应答信息
			tradeWithdraw1.setStatus(WithdrawStateEnum.FAIL.getCode());// 设置状态码
			tradeWithdrawService.doUpdate(tradeWithdraw1);

			RechargeOrderDO phoneChargeOrderDO1 = new RechargeOrderDO();
			phoneChargeOrderDO1.setId(reChargeOrderDO.getId());// 设置ID
			phoneChargeOrderDO1.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());// 设置应答码
			phoneChargeOrderDO1.setRespMsg(payName + "-支付宝扣款交易失败");// 设置应答信息
			phoneChargeOrderDO1.setStatus(WithdrawStateEnum.FAIL.getCode());// 设置状态码
			rechargeOrderService.doUpdate(phoneChargeOrderDO1);

			return "success";
		}

		// 手机充值
		chargeDTO.setPid(tradeWithdrawDO.getOriginalOrderNo());// 设置套餐ID
		chargeDTO.setPhone(reChargeOrderDO.getMobile());// 充值手机号

		RechargeOrderDO phoneChargeOrderDO1 = new RechargeOrderDO();
		TradeWithdrawDO tradeWithdrawDO1 = new TradeWithdrawDO();
		phoneChargeOrderDO1.setId(reChargeOrderDO.getId());
		tradeWithdrawDO1.setId(tradeWithdrawDO.getId());

		// 充值交易调用
		JuheDTO juhe = prepaidRefillService.recharge(chargeDTO, orderNo);
		if (juhe.getError_code() == 0) {

			Map<String, Object> map = JSONObject.parseObject(juhe.getResult().toString(), Map.class);

			phoneChargeOrderDO1.setPayOrderNo(map.get("sporder_id").toString());// 设置聚合订单号
			phoneChargeOrderDO1.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());// 订单提交成功，等待充值
			phoneChargeOrderDO1.setRespMsg(juhe.getReason());// 设置响应
			tradeWithdrawDO1.setOriginalOrderNo(map.get("sporder_id").toString());// 设置渠道订单号
			tradeWithdrawDO1.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());// 订单提交成功，等待充值需要再次调用订单查询接口进行查询
			tradeWithdrawDO1.setUpdateTime(new Date());// 设置交易完成时间
			tradeWithdrawDO1.setRespMsg(juhe.getReason());// 设置响应

			// 系统内部异常(调用充值类业务时，请务必联系客服或通过订单查询接口检测订单，避免造成损失)
		} else if (juhe.getError_code() == 10014) {

			phoneChargeOrderDO1.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());// 交易进行中
			phoneChargeOrderDO1.setRespMsg(juhe.getReason());// 设置响应
			phoneChargeOrderDO1.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());// 订单提交成功，等待充值

			tradeWithdrawDO1.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());// 交易进行中，需要再次调用订单查询接口进行查询
			tradeWithdrawDO1.setRespMsg(juhe.getReason());// 设置响应
			tradeWithdrawDO1.setUpdateTime(new Date());// 设置最后更新时间

			// 充值失败
		} else {

			phoneChargeOrderDO1.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());// 交易失败
			phoneChargeOrderDO1.setRespMsg(juhe.getReason());// 设置响应
			phoneChargeOrderDO1.setStatus(WithdrawStateEnum.FAIL.getCode());// 状态为2-失败

			tradeWithdrawDO1.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());// 交易进行中，需要再次调用订单查询接口进行查询
			tradeWithdrawDO1.setStatus(WithdrawStateEnum.FAIL.getCode());// 状态为2-失败
			tradeWithdrawDO1.setRespMsg(juhe.getReason());// 设置响应
			tradeWithdrawDO1.setUpdateTime(new Date());// 设置最后更新时间

			// 充值失败,退款
			AlipayRefundRequestParams requestParams = new AlipayRefundRequestParams();
			requestParams.setTradeNo(orderNo);// 设置订单号
			AlipayTradeRefundResponse alipayTradeRefundResponse = AlipayClientUtil
					.createTradeReturnOrderParams(requestParams);

			// 调用成功
			if ("10000".equals(alipayTradeRefundResponse.getCode())) {

				logger.error(payName + "退款成功,orderno=[" + orderNo + "]");

				// 调用失败
			} else {

				logger.error(payName + "退款失败,orderno=[" + orderNo + "]");
			}
		}

		// 更新数据
		tradeWithdrawService.doUpdate(tradeWithdrawDO1);
		rechargeOrderService.doUpdate(phoneChargeOrderDO1);

		return "success";
	}
}
