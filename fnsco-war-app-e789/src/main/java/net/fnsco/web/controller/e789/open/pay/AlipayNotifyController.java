package net.fnsco.web.controller.e789.open.pay;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.alipay.AlipayClientUtil;
import net.fnsco.core.base.BaseController;

/**
 * @desc 支付宝异步通知接口
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2018年1月31日 下午5:12:52
 */
@Controller
@RequestMapping(value = "/trade/alipay", method = RequestMethod.POST)
@Api(value = "/trade/alipay", tags = { "支付宝异步通知接口" })
public class AlipayNotifyController extends BaseController{
	
	/**
	 * appAliPayNotify:(支付宝APP支付异步通知接口，仅支付宝方调用，其他人不可调用)
	 *
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @author tangliang
	 * @date   2018年1月31日 下午5:17:13
	 */
	@RequestMapping(value = "/payNotify")
	@ApiOperation(value = "支付宝APP支付异步通知接口，仅支付宝方调用")
	@ResponseBody
	public String appAliPayNotify() {
		
		Map<String,Object> rsaMap = AlipayClientUtil.rsaCheckV1(request);
		boolean flag = (boolean) rsaMap.get("signature");
		/**
		 * 先对来源数据认证，如果不是支付宝方调用，则舍弃
		 */
		if(!flag) {
			logger.error("本次调用非正常调用!");
			return "fail";
		}
		/**
		 * 在认证是支付宝发来的数据后，接下来处理业务
		 */
		
		
		return "success";
	}
	
}
