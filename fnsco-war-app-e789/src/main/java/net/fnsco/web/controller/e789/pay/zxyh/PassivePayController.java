package net.fnsco.web.controller.e789.pay.zxyh;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import net.fnsco.core.base.BaseController;
import net.fnsco.trading.service.pay.channel.zxyh.PaymentService;
import net.fnsco.trading.service.pay.channel.zxyh.dto.PassivePayDTO;
import net.fnsco.web.controller.e789.pay.zxyh.jo.PassivePayJO;

@RestController
@RequestMapping(value="/app/zxyh/PassivePay", method=RequestMethod.POST)
@Api(value="/app/zxyh/PassivePay", tags={"中信银行被扫支付接口"})
public class PassivePayController extends BaseController{

	@Autowired
	private PaymentService PaymentService;

	/**
	 * 支付
	 * @param passivePayDTO:带交易必要元素的对象(JSON字串)
	 * @return
	 */
	@RequestMapping("/pay")
	public String ZxyhPassivePay(@RequestBody PassivePayJO zxyhPassivePayJO){
		
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
    		Map<String, String> map = new HashMap<>();
			map.put("respCode", "9999");
			map.put("respMsg", "交易码错误");
    		return map.toString();
    	}

		passivePayDTO.setStdtranamt(zxyhPassivePayJO.getAmt());//交易金额
		passivePayDTO.setStd400memo(zxyhPassivePayJO.getBody());//商品描述
		passivePayDTO.setStdauthid(zxyhPassivePayJO.getAuthid());//授权码
		passivePayDTO.setSignAture(zxyhPassivePayJO.getSignAture());//签名

		//调用service处理(值补全和存储以及发送请求给中信银行),并返回应答
		return PaymentService.PassivePay(zxyhPassivePayJO.getInnerCode(), passivePayDTO);
	}
	
	/**
	 * 支付结果查询(被扫之后，需要客户在手机上面手输密码点击确认才能支付成功，此时商户这边并不知道是否支付成功，需要再次调用查询接口来查询交易结果，商户刷新交易界面)
	 * @param passivePayDTO:带查询的必要元素对象(JSON字串)
	 * @return
	 */
	@RequestMapping("/queryPayResult")
	public String ZxyhPassivePayResult(@RequestBody PassivePayJO zxyhPassivePayJO) {

		// 对接收的报文进行处理

		// 签名校验

		PassivePayDTO passivePayDTO = new PassivePayDTO();
		if ("ZFB_CX".equals(zxyhPassivePayJO.getPayType())) {
			passivePayDTO.setStdprocode("381003");// 交易码，381003：支付宝二清消费
		} else if ("WX_CX".equals(zxyhPassivePayJO.getPayType())) {
			passivePayDTO.setStdprocode("381000");// 交易码，381000：微信消费
		} else {
			logger.info("交易码错误" + passivePayDTO.getStdprocode());
			Map<String, String> map = new HashMap<>();
			map.put("respCode", "9999");
			map.put("respMsg", "交易码错误");
			return map.toString();
		}

		passivePayDTO.setOrgorderid((zxyhPassivePayJO.getOrgOrderId()));// 原商户订单号
		passivePayDTO.setSignAture(zxyhPassivePayJO.getSignAture());// 签名

		return PaymentService.PassivePayResult(zxyhPassivePayJO.getInnerCode(), passivePayDTO);
	}
	
	@RequestMapping("/authIdCheck")
	public String PassiveAuthIdCheck(@RequestBody PassivePayJO zxyhPassivePayJO) {

		// 对接收的报文进行处理

		// 签名校验

		PassivePayDTO passivePayDTO = new PassivePayDTO();

		passivePayDTO.setStdauthid((zxyhPassivePayJO.getAuthid()));// 授权码
		passivePayDTO.setSignAture(zxyhPassivePayJO.getSignAture());// 签名

		return PaymentService.PassiveAuthIdCheck(zxyhPassivePayJO.getInnerCode(), passivePayDTO);
	}
}
