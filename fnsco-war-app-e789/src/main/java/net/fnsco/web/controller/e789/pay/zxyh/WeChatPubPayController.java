package net.fnsco.web.controller.e789.pay.zxyh;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.trading.service.pay.channel.zxyh.WeChatPubPayService;
import net.fnsco.trading.service.pay.channel.zxyh.dto.WeChatPubPayReqDTO;
import net.fnsco.trading.service.pay.channel.zxyh.dto.WeChatPubPayResultDTO;

@RestController
@RequestMapping(value = "/app2c/trade/weChatPubPay", method = RequestMethod.POST)
@Api(value = "/app2c/trade/weChatPubPay", tags = { "微信公众号支付" })
public class WeChatPubPayController extends BaseController {

	@Autowired
	private WeChatPubPayService weChatPubPayService;

	@RequestMapping("/weChatPubPay")
	@ApiOperation(value = "公众号/小程序支付函数", notes="作者：伯约")
	public ResultDTO<WeChatPubPayResultDTO> weChatPubPay(@RequestBody WeChatPubPayReqDTO weChatPubPayReqDTO) {

		return weChatPubPayService.weChatPubPay(weChatPubPayReqDTO);
	}

	@RequestMapping("/callBack")
	@ApiOperation(value = "公众号/小程序支付回调函数", notes="作者：伯约")
	public void callBack(@RequestBody String respStr) {
		// 解析返回报文
		assert respStr.startsWith("sendData=");
		String respB64Str = respStr.substring(9);

		// base64解码,并对一些特殊字符进行置换
		byte[] respJsBs = Base64.decodeBase64(respB64Str);
		String respJsStr = null;
		try {
			respJsStr = new String(respJsBs, "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		String sendData = weChatPubPayService.callBack(respStr);
		try {
			OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream(), "UTF-8");// 获取应答输出流，将应答返回
			out.write(sendData);
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
