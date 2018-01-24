 
package net.fnsco.web.controller.merchant;
  
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import net.fnsco.bigdata.api.constant.BigdataConstant;
import net.fnsco.bigdata.api.dto.MerchantCoreEntityZxyhDTO;
import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.trading.service.pay.OrderPaymentService;
import net.fnsco.trading.service.pay.channel.zxyh.PaymentService;

@Controller
@RequestMapping(value = "/web/zxyh")
@Api(value = "/web", tags = { "中信入驻独立商户控制器" })
public class ZxyhMercController extends BaseController {
	
	@Autowired
	private MerchantCoreService merchantCoreService;
	@Autowired
	private PaymentService zxyhPaymentService;
	
	/**
	 * 入驻中信银行的controller
	 * @return
	 */
	@RequestMapping("/enterMerc")
    @ResponseBody
	public ResultDTO<Map<String,String>> enterMerc(Integer id){
		if(null == id){
			return ResultDTO.fail();
		}
		//根据id获取入驻中信银行商户所需的必须信息
		logger.info("id="+id);
		ResultDTO<MerchantCoreEntityZxyhDTO> result = merchantCoreService.queryZXYHInfoById(id);
		MerchantCoreEntityZxyhDTO core = null;
		if(result.isSuccess()){
			core = result.getData();
		}else{
			return ResultDTO.fail(result.getMessage());
		}
		if(core == null){
			return ResultDTO.failForMessage(BigdataConstant.APP_MER_ENTITY_INNERCODE_NULL);
		}
		//调用入驻接口将参数传过去-
		Map<String, Object> map = zxyhPaymentService.mechAdd(core);
		if("0000".equals(map.get("respCode"))){
			//回调并更新信息
			String secMerId = map.get("secMerId").toString();
			if(!Strings.isNullOrEmpty(secMerId)){
				this.merchantCoreService.updateInfoByInnerCode(core.getInnerCode(),secMerId);
				
			}
			return ResultDTO.successForSubmit();
		}else{
			logger.error("入件返回结果"+map.toString());
			return ResultDTO.fail(map,map.get("respMsg")+"");
		}
		
	}
}