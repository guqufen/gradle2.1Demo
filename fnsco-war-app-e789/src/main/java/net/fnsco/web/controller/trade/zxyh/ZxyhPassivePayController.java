package net.fnsco.web.controller.trade.zxyh;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import net.fnsco.bigdata.api.merchant.MerchantChannelService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.trading.service.pay.channel.zxyh.ZxyhPaymentService;
import net.fnsco.trading.service.pay.channel.zxyh.dto.PassivePayDTO;
import net.fnsco.web.controller.trade.jo.zxyh.ZxyhPassivePayJO;

@Controller
@RequestMapping(value="/app/zxyh/PassivePay", method=RequestMethod.POST)
@Api(value="/app/zxyh/PassivePay", tags={"中信银行被扫支付接口"})
public class ZxyhPassivePayController extends BaseController{

	@Autowired
	private ZxyhPaymentService zxyhPaymentService;
	@Autowired
	private MerchantChannelService merchantChannelService;

	/**
	 * 支付
	 * @param passivePayDTO:带交易必要元素的对象(JSON字串)
	 * @return
	 */
	@RequestMapping("/pay")
	public ResultDTO ZxyhPassivePay(@RequestBody ZxyhPassivePayJO zxyhPassivePayJO){
		
		//对接收的报文进行处理
		
		//签名校验
		
		PassivePayDTO passivePayDTO = new PassivePayDTO();
		if( "ZFB_PAY".equals(zxyhPassivePayJO.getPayType()) ){
    		//不可优惠金额，支付宝必填
    		if( StringUtils.isNotEmpty(zxyhPassivePayJO.getDiscamt()) ){
    			passivePayDTO.setStddiscamt(zxyhPassivePayJO.getDiscamt());
    		}else{
    			passivePayDTO.setStddiscamt("0");
    		}
    		passivePayDTO.setStdprocode("481003");//交易码，481000：微信消费 481003：支付宝二清消费
    	}else if("WX_PAY".equals(zxyhPassivePayJO.getPayType())){
    		passivePayDTO.setStdprocode("481000");//交易码，481000：微信消费 481003：支付宝二清消费
    	}else{
    		logger.info("交易码错误"+passivePayDTO.getStdprocode());
    		return null;
    	}

		passivePayDTO.setStdtranamt(zxyhPassivePayJO.getAmt());//交易金额
		passivePayDTO.setStd400memo(zxyhPassivePayJO.getBody());//商品描述
		passivePayDTO.setStdauthid(zxyhPassivePayJO.getAuthid());//授权码
		passivePayDTO.setSignAture(zxyhPassivePayJO.getSignAture());//签名

		//调用service处理(值补全和存储以及发送请求给中信银行)
		zxyhPaymentService.PassivePay(zxyhPassivePayJO.getInnerCode(), passivePayDTO);
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
