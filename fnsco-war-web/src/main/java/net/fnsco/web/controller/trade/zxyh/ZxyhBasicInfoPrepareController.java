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
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.bigdata.service.domain.trade.MerchantCoreEntityZxyhDTO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.trading.service.pay.channel.zxyh.ZxyhPaymentService;


@Controller
@RequestMapping(value = "/web/zxyh")
@Api(value = "/web", tags = { "中信入驻独立商户控制器" })
public class ZxyhBasicInfoPrepareController extends BaseController {
	
	@Autowired
	private ZxyhPaymentService zxyhPaymentService;
	@Autowired
	private MerchantCoreService merchantCoreService;
	
	/**
	 * 入驻中信银行的controller
	 * @return
	 */
	@RequestMapping("/enterMerc")
    @ResponseBody
	public ResultDTO<Object> enterMerc(Integer id){
		//根据id获取入驻中信银行商户所需的必须信息
		MerchantCoreEntityZxyhDTO merchantCoreEntityZxyhDTO = merchantCoreService.queryMercCoreById(id);
//		merchantCore.getRegist_province();
		//只能入驻一次，不能二次点击
		
		//调用入驻接口将参数传过去
//		zxyhPaymentService.mchtadd();
		return null;
		
	}
}