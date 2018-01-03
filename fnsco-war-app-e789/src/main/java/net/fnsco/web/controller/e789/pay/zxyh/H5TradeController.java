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
import net.fnsco.trading.service.pay.channel.zxyh.H5PaymentService;
import net.fnsco.trading.service.pay.channel.zxyh.dto.H5PayReqDTO;
import net.fnsco.trading.service.pay.channel.zxyh.dto.H5PayResultDTO;

/**
 * h5微信支付
 * 
 * @author yx
 *
 */
@RestController
@RequestMapping(value = "/app2c/trade/h5", method = RequestMethod.POST)
@Api(value = "/app2c/trade/h5", tags = { "h5支付" })
public class H5TradeController extends BaseController {

	@Autowired
	private H5PaymentService h5PaymentService;

	@RequestMapping("h5Pay")
	@ApiOperation("h5支付")
	public ResultDTO<H5PayResultDTO> H5Pay(@RequestBody H5PayReqDTO h5PayJO){

		return h5PaymentService.H5Pay(h5PayJO);
	}
	
	@RequestMapping("/callBack")
	@ApiOperation("h5支付回调函数")
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

		String sendData = h5PaymentService.callBack(respJsStr);
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
