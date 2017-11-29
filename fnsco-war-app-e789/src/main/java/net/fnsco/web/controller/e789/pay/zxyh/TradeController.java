package net.fnsco.web.controller.e789.pay.zxyh;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.trading.service.pay.channel.zxyh.ZxyhPaymentService;
import net.fnsco.web.controller.e789.pay.zxyh.jo.GenerateQRCodeAliPayJO;
import net.fnsco.web.controller.e789.pay.zxyh.jo.GenerateQRCodeWeChatJO;


@RestController
@RequestMapping(value = "/e789/trade/zxyh", method = RequestMethod.POST)
@Api(value = "/e789/trade/zxyh", tags = { "中信银行主扫相关功能接口" })
public class TradeController extends BaseController{
	
	@Autowired
	private ZxyhPaymentService zxyhPaymentService;

	
	
	
	
	
	@RequestMapping(value = "/getWeChatQRUrl")
	@ApiOperation(value = "获取中信银行微信主扫二维码url")
	public ResultDTO<Object> generateQRCodeWeiXin(GenerateQRCodeWeChatJO weChatJO){
		String innerCode = weChatJO.getInnerCode();
		String orderBody = weChatJO.getOrderBody();
		String txnAmt = weChatJO.getTxnAmt();
		if(Strings.isNullOrEmpty(innerCode)){
			return ResultDTO.fail("内部商户号为空");
		}
		if(Strings.isNullOrEmpty(orderBody)){
			return ResultDTO.fail("商品或支付单简要描述为空");
		}
		if(Strings.isNullOrEmpty(txnAmt)){
			return ResultDTO.fail("交易金额为空");
		}
		Map<String, Object> reqMap = zxyhPaymentService.generateQRCodeWeiXin(innerCode,orderBody,txnAmt);
		return  success(reqMap);
	}
	
	@RequestMapping(value = "/getAliPayQRUrl")
	@ApiOperation(value = "获取中信银行支付宝主扫二维码url")
	public ResultDTO<Object> generateQRCodeAliPay(GenerateQRCodeAliPayJO aliPayJO){
		String innerCode = aliPayJO.getInnerCode();
		String ip = aliPayJO.getIp();
		String orderBody = aliPayJO.getOrderBody();
		String txnAmt = aliPayJO.getTxnAmt();
		if(Strings.isNullOrEmpty(innerCode)){
			return ResultDTO.fail("内部商户号为空");
		}
		if(Strings.isNullOrEmpty(ip)){
			return ResultDTO.fail("ip为空");
		}
		if(Strings.isNullOrEmpty(orderBody)){
			return ResultDTO.fail("商品或支付单简要描述为空");
		}
		if(Strings.isNullOrEmpty(txnAmt)){
			return ResultDTO.fail("交易金额为空");
		}
		Map<String, Object> reqMap = zxyhPaymentService.generateQRCodeAliPay(innerCode,ip,orderBody,txnAmt);
		return  success(reqMap);
	}
	
	
	@RequestMapping(value = "/aliCallBack")
	@ApiOperation(value = "支付宝主扫回调")
	public void aliCallBack(String resultStr) {
		zxyhPaymentService.aliCallBack(resultStr);
	}
	
	@RequestMapping(value = "/weChatCallBack")
	@ApiOperation(value = "微信主扫回调")
	public void weChatCallBack(String resultStr) {
		zxyhPaymentService.weChatCallBack(resultStr);
	}
	


}
