package net.fnsco.web.controller.e789.open.pay;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.alipay.AlipayAppPayRequestParams;
import net.fnsco.core.alipay.AlipayClientUtil;
import net.fnsco.core.alipay.AlipayRefundRequestParams;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.trading.comm.TradeConstants.WithdrawStateEnum;
import net.fnsco.trading.service.third.ticket.TicketOrderService;
import net.fnsco.trading.service.withdraw.TradeWithdrawService;
import net.fnsco.trading.service.withdraw.entity.TradeWithdrawDO;

/**
 * @desc 支付宝异步通知接口
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2018年1月31日 下午5:12:52
 */
@Controller
@RequestMapping(value = "/trade/alipay", method = RequestMethod.POST)
@Api(value = "/trade/alipay", tags = { "支付宝异步通知接口" })
public class AlipayNotifyController extends BaseController{
	
	@Autowired
	private TradeWithdrawService tradeWithdrawService; 
	@Autowired
    private TicketOrderService   ticketOrderService;
	/**
	 * appAliPayNotify:(支付宝APP充值支付异步通知接口，仅支付宝方调用，其他人不可调用)
	 *
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @author tangliang
	 * @date   2018年1月31日 下午5:17:13
	 */
	@Transactional
	@RequestMapping(value = "/appPayNotify")
	@ApiOperation(value = "支付宝APP充值支付异步通知接口，仅支付宝方调用")
	@ResponseBody
	public String appAliPayNotify() {
		
		Map<String,Object> rsaMap = AlipayClientUtil.rsaCheckV1(request);
		boolean flag = (boolean) rsaMap.get("signature");
		/**
		 * 先对来源数据认证，如果不是支付宝方调用，则舍弃
		 */
		if(!flag) {
			logger.error("本次调用非正常调用!");
			return "fail";
		}
		/**
		 * 在认证是支付宝发来的数据后，接下来处理业务
		 */
		Map<String,String> params = (Map<String, String>) rsaMap.get("params");
		String orderNo = params.get("out_trade_no");
		TradeWithdrawDO tradeWithdraw = tradeWithdrawService.getByOrderNo(orderNo);
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
		}
		
		boolean tradeStatusVali = AlipayClientUtil.checkTradeStatue(params);
		if(!tradeStatusVali) {
			logger.error("该订单非完成支付状态，不处理!orderNo="+params.get("out_trade_no"));
			return "fail";
		}
		//成功处理
		tradeWithdrawService.doAlipayRechangeNotify(params, true, tradeWithdraw);
		return "success";
	}
	/**
     * 火车票购买支付宝支付回调
     * @return
     */
    @RequestMapping(value = "/ticketPayNotify")
	@ApiOperation(value = "支付宝APP火车票支付异步通知接口，仅支付宝方调用")
    public String ticketPayNotify() {
    	Map<String,Object> rsaMap = AlipayClientUtil.rsaCheckV1(request);
		boolean flag = (boolean) rsaMap.get("signature");
		/**
		 * 先对来源数据认证，如果不是支付宝方调用，则舍弃
		 */
		if(!flag) {
			logger.error("本次调用非正常调用!");
			return "fail";
		}
		/**
		 * 在认证是支付宝发来的数据后，接下来处理业务
		 */
		Map<String,String> params = (Map<String, String>) rsaMap.get("params");
		boolean tradeStatus = AlipayClientUtil.checkTradeStatue(params);
		
		if(!tradeStatus) {
			logger.error("该订单非完成支付状态，不处理!orderNo="+params.get("out_trade_no"));
			return "fail";
		}
		
		String orderNo = params.get("out_trade_no");
		ResultDTO result = ticketOrderService.payByZFBNotify(orderNo);
    	if(result.isSuccess()) {
    		return "success";
    	}else {
    	AlipayRefundRequestParams requestParams = new AlipayRefundRequestParams();
    	requestParams.setRefundReason("");
    	requestParams.setRefundAmount("");
    	requestParams.setOutTradeNo(orderNo);
    	requestParams.setNotifyUrl("");
    	String body = AlipayClientUtil.createTradeReturnOrderParams(requestParams);
    	return body;
    	}
    }
}
