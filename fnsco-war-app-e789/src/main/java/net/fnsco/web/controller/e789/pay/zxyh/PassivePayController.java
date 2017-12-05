package net.fnsco.web.controller.e789.pay.zxyh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.trading.service.pay.channel.zxyh.PaymentService;
import net.fnsco.trading.service.pay.channel.zxyh.dto.PassivePayDTO;
import net.fnsco.web.controller.e789.pay.zxyh.jo.PassivePayJO;
import net.fnsco.web.controller.e789.pay.zxyh.jo.PassiveResultQueryJO;
import net.fnsco.web.controller.e789.vo.PassiveVO;

@RestController
@RequestMapping(value="/app2c/zxyh/PassivePay", method=RequestMethod.POST)
@Api(value="/app2c/zxyh/PassivePay", tags={"中信银行被扫支付接口"})
public class PassivePayController extends BaseController{

	@Autowired
	private PaymentService PaymentService;

	/**
	 * 支付
	 * @param passivePayDTO:带交易必要元素的对象(JSON字串)
	 * @return
	 */
	@RequestMapping("/pay")
	@ApiOperation(value="中信银行支付宝/微信被扫支付交易接口url")
	public PassiveVO ZxyhPassivePay(@RequestBody PassivePayJO passivePayJO){
		
		//对接收的报文进行处理
		String str = JSON.toJSONString(passivePayJO);
		
		
		//签名校验
		
		PassivePayDTO passivePayDTO = new PassivePayDTO();
		passivePayDTO.setStdtranamt(passivePayJO.getAmt());//交易金额
		passivePayDTO.setStd400memo(passivePayJO.getBody());//商品描述
		passivePayDTO.setStdauthid(passivePayJO.getAuthid());//授权码
		passivePayDTO.setSignAture(passivePayJO.getSignAture());//签名

		//调用service处理(值补全和存储以及发送请求给中信银行),并返回应答字符串
		String result = PaymentService.PassivePay(passivePayJO.getInnerCode(), passivePayDTO);
		//将应答字符串放入对象，并返回对象给APP
		PassiveVO passiveVO = JSON.parseObject(result, PassiveVO.class);
		return passiveVO;
	}

	/**
	 * 支付结果查询(被扫之后，有的需要客户在手机上面手输密码点击确认才能支付成功，此时商户这边并不知道是否支付成功，需要再次调用查询接口来查询交易结果，商户刷新交易界面)
	 * @param passivePayDTO:带查询的必要元素对象(JSON字串)
	 * @return
	 */
	@RequestMapping("/queryPayResult")
	@ApiOperation(value="中信银行支付宝/微信支付结果查询接口url")
	public PassiveVO ZxyhPassivePayResult(@RequestBody PassiveResultQueryJO passiveResultQueryJO) {

		// 对接收的报文进行处理

		// 签名校验

		PassivePayDTO passivePayDTO = new PassivePayDTO();
		passivePayDTO.setOrgorderid((passiveResultQueryJO.getOrgOrderId()));// 原商户订单号
		passivePayDTO.setSignAture(passiveResultQueryJO.getSignAture());// 签名

		String result = PaymentService.PassivePayResult(passiveResultQueryJO.getOrgOrderId());
		PassiveVO passiveVO = JSON.parseObject(result, PassiveVO.class);
		return passiveVO;
	}
}
