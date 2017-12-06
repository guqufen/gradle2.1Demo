package net.fnsco.web.controller.e789.pay.zxyh;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.trading.service.order.entity.TradeOrderDO;
import net.fnsco.trading.service.pay.channel.pfyh.PFOrderPaymentService;
import net.fnsco.trading.service.pay.channel.zxyh.PaymentService;
import net.fnsco.web.controller.e789.jo.GenerateQRCodeJO;
import net.fnsco.web.controller.e789.vo.GetQRUrlResultVO;


@RestController
@RequestMapping(value = "/app2c/trade", method = RequestMethod.POST)
@Api(value = "/app2c/trade", tags = { "扫一扫和付款相关功能接口" })
public class TradeController extends BaseController{
	
	@Autowired
	private PaymentService zxyhPaymentService;
	@Autowired
	private PFOrderPaymentService pfOrderPaymentService;

	
	
	
	
	
//	@RequestMapping(value = "/getWeChatQRUrl")
//	@ApiOperation(value = "获取中信银行微信主扫二维码url")
//	public ResultDTO<Object> generateQRCodeWeiXin(GenerateQRCodeWeChatJO weChatJO){
//		String innerCode = weChatJO.getInnerCode();
//		String orderBody = weChatJO.getOrderBody();
//		String txnAmt = weChatJO.getTxnAmt();
//		if(Strings.isNullOrEmpty(innerCode)){
//			return ResultDTO.fail("内部商户号为空");
//		}
//		if(Strings.isNullOrEmpty(orderBody)){
//			return ResultDTO.fail("商品或支付单简要描述为空");
//		}
//		if(Strings.isNullOrEmpty(txnAmt)){
//			return ResultDTO.fail("交易金额为空");
//		}
//		Map<String, Object> reqMap = zxyhPaymentService.generateQRCodeWeiXin(innerCode,orderBody,txnAmt);
//		return  success(reqMap);
//	}
	
	@RequestMapping(value = "/getQRUrl")
	@ApiOperation(value = "获取（用户）主扫二维码url，（中信银行或者浦发通道） ")
	public ResultDTO<Object> generateQRCode(GenerateQRCodeJO qrJO){
		GetQRUrlResultVO qrVo = new GetQRUrlResultVO();
		String innerCode = qrJO.getInnerCode();
		String ip = qrJO.getIp();
		String orderBody = qrJO.getOrderBody();
		String txnAmt = qrJO.getTxnAmt();
		String paySubType = qrJO.getPaySubType();//
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
		//交易子类型01微信02支付宝
		if(Strings.isNullOrEmpty(paySubType)){
			return ResultDTO.fail("交易子类型为空");
		}
		qrJO.setChannelType("05");//需求暂未定，设置默认中信通道
		Map<String, Object> reqMap = new HashMap<>();
		if("05".equals(qrJO.getChannelType())){//中信通道
			if("41".equals(paySubType)){//微信
				reqMap = zxyhPaymentService.generateQRCodeWeiXin(innerCode,orderBody,txnAmt);
				qrVo.setUrl(reqMap.get("codeUrl").toString());
			
			}else if("42".equals(paySubType)){//支付宝
				reqMap = zxyhPaymentService.generateQRCodeAliPay(innerCode,ip,orderBody,txnAmt);
				qrVo.setUrl(reqMap.get("codeUrl").toString());
			}
			return  success(qrVo);
		}else if("01".equals(qrJO.getChannelType())){//浦发通道
			TradeOrderDO orderPayment = new TradeOrderDO();
			//赋值
			
			orderPayment = pfOrderPaymentService.beisaoPaySendPost(orderPayment);
			String reqMapStr = JSON.toJSONString(orderPayment);
			Map<String, Object> resultMap = JSON.parseObject(reqMapStr);
			return success(resultMap);
			
		}
		return ResultDTO.fail("请联系管理员");
	}
	
	
	
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
	
	
	@RequestMapping(value = "/queryMercScannedTrade")
	@ApiOperation(value = "查询商户被扫交易记录")
	public void queryMercScannedTrade() {
//		zxyhPaymentService.queryMercScannedTrade(resultStr);
		
		
	}
	
	

}
