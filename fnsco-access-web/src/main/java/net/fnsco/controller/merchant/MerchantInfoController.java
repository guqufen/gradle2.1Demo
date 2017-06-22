/**
 * 
 */
package net.fnsco.controller.merchant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;

/**@desc 商户信息控制器
 * @author tangliang
 * @date 2017年6月21日 下午7:39:22
 */
@Controller
public class MerchantInfoController extends BaseController{
	
	/**
	 * 跳转到商户信息首页
	 * @return
	 */
	@RequestMapping("/merchatIndex")
	public ResultDTO<Object> merchatInfoIndex(){
		
		
		return null;
	}
}
