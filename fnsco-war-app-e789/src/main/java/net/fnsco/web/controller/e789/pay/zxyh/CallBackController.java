package net.fnsco.web.controller.e789.pay.zxyh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.trading.service.pay.channel.zxyh.PaymentService;

@RestController
@RequestMapping(value = "/trade/zxyh", method = RequestMethod.POST)
@Api(value = "/trade/zxyh", tags = { "中信银行回调" })
/**
 * 商户被扫接口
 * @author Administrator
 *
 */
public class CallBackController extends BaseController{
	
	@Autowired
	private PaymentService zxyhPaymentService;
	
	@RequestMapping(value = "/payQueryCallBack")
	@ApiOperation(value = "支付宝主扫回调")
	public void payQueryCallBack(String resultStr) {
		zxyhPaymentService.aliCallBack(resultStr);
	}
	
	@RequestMapping(value = "/weChatCallBack")
	@ApiOperation(value = "微信主扫回调")
	public void weChatCallBack(String resultStr) {
		zxyhPaymentService.weChatCallBack(resultStr);
	}
	
	

}
