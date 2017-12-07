package net.fnsco.web.controller.e789.pay.zxyh;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.trading.service.pay.channel.pfyh.PFOrderPaymentService;
import net.fnsco.trading.service.pay.channel.zxyh.PaymentService;
import net.fnsco.web.controller.e789.jo.GenerateQRJO;
import net.fnsco.web.controller.e789.vo.GenerateQRVO;


@RestController
@RequestMapping(value = "/app2c/trade", method = RequestMethod.POST)
@Api(value = "/app2c/trade", tags = { "首页-付款相关功能接口" })
/**
 * 商户被扫接口
 * @author Administrator
 *
 */
public class ScannedTradeController extends BaseController{
	
	@Autowired
	private PaymentService zxyhPaymentService;
	@Autowired
	private PFOrderPaymentService pfOrderPaymentService;


	@RequestMapping(value = "/getQRUrl")
	@ApiOperation(value = "收款-获取付款二维码url")
	public ResultDTO<GenerateQRVO> generateQRCode(@RequestBody GenerateQRJO qrJO){
		GenerateQRVO qrVo = new GenerateQRVO();
		Integer userId = qrJO.getUserId();
		String ip = this.getIp();
		String txnAmt = qrJO.getTxnAmt();
		String paySubType = qrJO.getPaySubType();//
		if(userId == null){
			return ResultDTO.fail("userId为空");
		}
		if(Strings.isNullOrEmpty(txnAmt)){
			return ResultDTO.fail("交易金额为空");
		}
		//交易子类型01微信02支付宝
		if(Strings.isNullOrEmpty(paySubType)){
			return ResultDTO.fail("交易子类型为空");
		}
		
		Map<String, Object> reqMap = new HashMap<>();
		if("41".equals(paySubType)){//微信
			reqMap = zxyhPaymentService.generateQRCodeWeiXin(userId,txnAmt);
			qrVo.setUrl(reqMap.get("codeUrl").toString());
		
		}else if("42".equals(paySubType)){//支付宝
			reqMap = zxyhPaymentService.generateQRCodeAliPay(userId,ip,txnAmt);
			qrVo.setUrl(reqMap.get("codeUrl").toString());
		}
		return  ResultDTO.success(qrVo);
			
	}
	
	

}
