/**
 * 
 */
/**
 * @author bhl
 *
 */
package net.fnsco.web.controller.trade.zxyh;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.bigdata.service.domain.trade.MerchantCoreEntityZxyhDTO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.trading.service.pay.channel.zxyh.ZxyhPaymentService;


@Controller
@RequestMapping(value = "/web/zxyh")
@Api(value = "/web", tags = { "中信入驻独立商户控制器" })
public class ZxyhBasicInfoPrepareController extends BaseController {
	
	@Autowired
	private MerchantCoreService merchantCoreService;
	@Autowired
	private ZxyhPaymentService zxyhPaymentService;
	
	/**
	 * 入驻中信银行的controller
	 * @return
	 */
	@RequestMapping("/enterMerc")
    @ResponseBody
	public ResultDTO<String> enterMerc(Integer id){
		if(null == id){
			return ResultDTO.fail();
		}
		//根据id获取入驻中信银行商户所需的必须信息
		MerchantCoreEntityZxyhDTO core = merchantCoreService.queryZXYHInfoById(id);
		if(core == null){
			return ResultDTO.fail();
		}
		//调用入驻接口将参数传过去-
		zxyhPaymentService.mechAdd(core);
		return ResultDTO.successForSubmit();
		
	}
}