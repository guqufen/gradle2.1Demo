package net.fnsco.web.controller.e789.pay.zxyh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.trading.service.pay.channel.zxyh.PaymentService;
import net.fnsco.trading.service.pay.channel.zxyh.dto.PassivePayDTO;
import net.fnsco.trading.service.pay.channel.zxyh.dto.PassivePayReqDTO;
import net.fnsco.trading.service.pay.channel.zxyh.dto.PassivePayResultDTO;

@RestController
@RequestMapping(value = "/app2c/trade/passivePay", method = RequestMethod.POST)
@Api(value = "/app2c/trade/passivePay", tags = { "首页-扫一扫" })
public class PassivePayController extends BaseController {

	@Autowired
	private PaymentService paymentService;

	/**
	 * 支付
	 * 
	 * @param PassivePayJO:入参对象
	 * @return
	 */
	@RequestMapping("/pay")
	@ApiOperation(value = "扫一扫-支付交易接口url")
	public ResultDTO<PassivePayResultDTO> zxyhPassivePay(@RequestBody PassivePayReqDTO passivePayReqDTO) {

//		PassivePayDTO passivePayDTO = new PassivePayDTO();
//		passivePayDTO.setStdtranamt(passivePayJO.getAmt());// 交易金额
//		passivePayDTO.setStd400memo("扫码支付");// 商品描述
//		passivePayDTO.setStdauthid(passivePayJO.getAuthId());// 授权码
//		
//		
//		return PaymentService.PassivePay(passivePayJO.getUserId(), passivePayDTO);

		//判断APP用户ID是否合法
		Integer id = getUserId();
		if (null == id || id != passivePayReqDTO.getUserId()) {
			logger.error("用户登录状态非法。");
			return ResultDTO.fail("用户登录状态非法。");
		}

		// PassivePayDTO passivePayDTO = new PassivePayDTO();
		// passivePayDTO.setStdtranamt(passivePayJO.getAmt());// 交易金额
		// passivePayDTO.setStd400memo("扫码支付");// 商品描述
		// passivePayDTO.setStdauthid(passivePayJO.getAuthId());// 授权码

		logger.info("请求参数：" + passivePayReqDTO.toString());
		return paymentService.passivePay(passivePayReqDTO);
	}

	/**
	 * 支付结果查询(被扫之后，有的需要客户在手机上面手输密码点击确认才能支付成功，此时商户这边并不知道是否支付成功，需要再次调用查询接口来查询交易结果，
	 * 商户刷新交易界面)
	 * 
	 * @param PassiveResultQueryJO:入参对象
	 * @return
	 */
	// @RequestMapping("/queryPayResult")
	// @ApiOperation(value = "扫一扫-支付结果查询接口url")
	// public ResultDTO<PassiveVO> ZxyhPassivePayResult(@RequestBody
	// PassiveResultQueryJO passiveResultQueryJO) {
	//
	// PassivePayDTO passivePayDTO = new PassivePayDTO();
	// passivePayDTO.setOrgorderid((passiveResultQueryJO.getOrderNo()));// 商户订单号
	//
	// return
	// PaymentService.PassivePayResult(passiveResultQueryJO.getOrderNo());
	// }
}
