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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.alipay.AlipayClientUtil;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.trading.comm.TradeConstants.WithdrawStateEnum;
import net.fnsco.trading.service.account.AppAccountBalanceService;
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
	private AppAccountBalanceService appAccountBalanceService;
	@Autowired
    private TicketOrderService   ticketOrderService;
	/**
	 * appAliPayNotify:(支付宝APP支付异步通知接口，仅支付宝方调用，其他人不可调用)
	 *
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @author tangliang
	 * @date   2018年1月31日 下午5:17:13
	 */
	@Transactional
	@RequestMapping(value = "/appPayNotify")
	@ApiOperation(value = "支付宝APP支付异步通知接口，仅支付宝方调用")
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
		boolean tradeStatus = AlipayClientUtil.checkTradeStatue(params);
		
		if(!tradeStatus) {
			logger.error("该订单非完成支付状态，不处理!orderNo="+params.get("out_trade_no"));
			return "fail";
		}
		
		String orderNo = params.get("out_trade_no");
		TradeWithdrawDO tradeWithdraw = tradeWithdrawService.getByOrderNo(orderNo);
		if(null == tradeWithdraw) {
			logger.error("该订单已经不存在，不处理!orderNo="+orderNo);
			return "fail";
		}
		
		/**
		 * 处理完成的订单，不处理
		 */
		if(tradeWithdraw.getStatus() == WithdrawStateEnum.SUCCESS.getCode()) {
			logger.error("该订单已经处理过，不处理!orderNo="+orderNo);
			return "fail";
		}
		
		/**
		 * 充值成功后，需要在帐号上增加余额
		 */
		Integer appUserId = tradeWithdraw.getAppUserId();
		BigDecimal fund = new BigDecimal(params.get("total_amount")).multiply(new BigDecimal(100));
		appAccountBalanceService.doQueryByAppUserId(appUserId);
		appAccountBalanceService.updateFund(appUserId,BigDecimal.ZERO.subtract(fund));
		
		/**
		 * 更新订单信息
		 */
		tradeWithdraw.setStatus(3);
		tradeWithdraw.setRespCode("1001");
		tradeWithdraw.setUpdateTime(new Date());
		tradeWithdraw.setOriginalOrderNo(params.get("trade_no"));//支付宝交易凭证号
		tradeWithdraw.setSuccTime(DateUtils.dateFormat1ToStr(new Date()));
		tradeWithdraw.setRespMsg("支付宝充值成功");
		tradeWithdrawService.doUpdate(tradeWithdraw);
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
		TradeWithdrawDO tradeWithdraw = tradeWithdrawService.getByOrderNo(orderNo);
		if(null == tradeWithdraw) {
			logger.error("该订单已经不存在，不处理!orderNo="+orderNo);
			return "fail";
		}
		ResultDTO result = ticketOrderService.payByZFBNotify(tradeWithdraw);
    	if(result.isSuccess()) {
    		return "success";
    	}
    	return "fail";
    }
}
