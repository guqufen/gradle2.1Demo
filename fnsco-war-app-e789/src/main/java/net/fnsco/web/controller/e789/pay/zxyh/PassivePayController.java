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
import net.fnsco.web.controller.e789.jo.PassivePayJO;
import net.fnsco.web.controller.e789.jo.PassiveResultQueryJO;
import net.fnsco.web.controller.e789.vo.PassiveVO;

@RestController
@RequestMapping(value = "/app2c/trade/passivePay", method = RequestMethod.POST)
@Api(value = "/app2c/trade/passivePay", tags = { "扫一扫" })
public class PassivePayController extends BaseController {

	@Autowired
	private PaymentService PaymentService;

	/**
	 * 支付
	 * 
	 * @param PassivePayJO:入参对象
	 * @return
	 */
	@RequestMapping("/pay")
	@ApiOperation(value = "扫一扫支付交易接口url")
	public ResultDTO<PassiveVO> ZxyhPassivePay(@RequestBody PassivePayJO passivePayJO) {

		PassivePayDTO passivePayDTO = new PassivePayDTO();
		passivePayDTO.setStdtranamt(passivePayJO.getAmt());// 交易金额
		passivePayDTO.setStd400memo("扫码支付");// 商品描述
		passivePayDTO.setStdauthid(passivePayJO.getAuthId());// 授权码

		return PaymentService.PassivePay(passivePayJO.getInnerCode(), passivePayDTO);
	}

	/**
	 * 支付结果查询(被扫之后，有的需要客户在手机上面手输密码点击确认才能支付成功，此时商户这边并不知道是否支付成功，需要再次调用查询接口来查询交易结果，
	 * 商户刷新交易界面)
	 * 
	 * @param PassiveResultQueryJO:入参对象
	 * @return
	 */
	@RequestMapping("/queryPayResult")
	@ApiOperation(value = "扫一扫支付结果查询接口url")
	public ResultDTO<PassiveVO> ZxyhPassivePayResult(@RequestBody PassiveResultQueryJO passiveResultQueryJO) {

		PassivePayDTO passivePayDTO = new PassivePayDTO();
		passivePayDTO.setOrgorderid((passiveResultQueryJO.getOrderNo()));// 商户订单号

		return PaymentService.PassivePayResult(passiveResultQueryJO.getOrderNo());
	}
}
