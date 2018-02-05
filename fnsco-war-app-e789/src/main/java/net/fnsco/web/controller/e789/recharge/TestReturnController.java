package net.fnsco.web.controller.e789.recharge;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alipay.api.response.AlipayTradeRefundResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.alipay.AlipayClientUtil;
import net.fnsco.core.alipay.AlipayRefundRequestParams;
import net.fnsco.core.base.ResultDTO;

@RestController
@RequestMapping(value = "/app2c/rechange")
@Api(value = "/app2c/rechange", tags = { "测试退款业务" })
public class TestReturnController {
	
	@RequestMapping(value = "/ss/ss", method = RequestMethod.POST)
    @ApiOperation(value = "测试退款业务")
	public ResultDTO<String> testReturn(){
		AlipayRefundRequestParams requestParams = new AlipayRefundRequestParams();
		requestParams.setOutTradeNo("2018020117150397565606");
		requestParams.setRefundAmount("0.01");
		requestParams.setRefundReason("正常退款");
		requestParams.setTradeNo("2018020121001004880591005160");
		AlipayTradeRefundResponse params = AlipayClientUtil.createTradeReturnOrderParams(requestParams);
		if(params.getFundChange().equals("Y")) {
			//正真退款
		}else {
			//已经退款
		}
		System.out.println(JSON.toJSONString(params));
		return ResultDTO.success();
	}
}
