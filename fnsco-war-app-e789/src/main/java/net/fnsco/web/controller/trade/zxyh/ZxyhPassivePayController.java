package net.fnsco.web.controller.trade.zxyh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.trading.service.pay.channel.zxyh.ZxyhPaymentService;
import net.fnsco.trading.service.pay.channel.zxyh.dto.PassivePayDTO;

@Controller
@RequestMapping(value="/app/zxyh/PassivePay", method=RequestMethod.POST)
@Api(value="/app/zxyh/PassivePay", tags={"中信银行被扫支付接口"})
public class ZxyhPassivePayController extends BaseController{

	@Autowired
	private ZxyhPaymentService zxyhPaymentService;

	/**
	 * 支付
	 * @param passivePayDTO:带交易必要元素的对象(JSON字串)
	 * @return
	 */
	@RequestMapping("/pay")
	public ResultDTO ZxyhPassivePay(@RequestBody PassivePayDTO passivePayDTO){
		
		//对接收的报文进行处理
		
		//签名校验
		
		//调用service处理(值补全和存储以及发送请求给中信银行)
		zxyhPaymentService.PassivePay(passivePayDTO);
		return null;
	}
	
	/**
	 * 支付结果查询(被扫之后，需要客户在手机上面手输密码点击确认才能支付成功，此时商户这边并不知道是否支付成功，需要再次调用查询接口来查询交易结果，商户刷新交易界面)
	 * @param passivePayDTO:带查询的必要元素对象(JSON字串)
	 * @return
	 */
	@RequestMapping("/queryPayResult")
	public ResultDTO ZxyhPassivePayResult(@RequestBody PassivePayDTO passivePayDTO){

		zxyhPaymentService.PassivePayResult(passivePayDTO);
		return null;
	}
	
	/**
	 * 退货
	 * @param passivePayDTO
	 * @return
	 */
	@RequestMapping("/payRefund")
	public ResultDTO ZxyhPassivePayRefund(@RequestBody PassivePayDTO passivePayDTO){
		zxyhPaymentService.PassivePayRefund(passivePayDTO);
		return null;
		
	}
}
