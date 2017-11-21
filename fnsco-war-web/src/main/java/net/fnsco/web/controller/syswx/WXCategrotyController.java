/**
 * 
 */
/**
 * @author Administrator
 *
 */
package net.fnsco.web.controller.syswx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.bigdata.service.sys.PayCategroryWxService;
import net.fnsco.bigdata.service.sys.entity.PayCategroryWxDO;
import net.fnsco.core.base.ResultDTO;


@Controller
@RequestMapping("/categroty")
public class WXCategrotyController extends net.fnsco.core.base.BaseController{
	
	@Autowired
	private PayCategroryWxService payCategroryWxService;
	/**
	 * 加载一级类目
	 * @return
	 */
	@RequestMapping("showFirstClassify")
	@ResponseBody
	public ResultDTO<PayCategroryWxDO> showFirstClassify(@RequestParam("etpAttr") String etpAttr, @RequestParam("terminal_type") String terminal_type){
		if(!"02".equals(terminal_type)){//选择微信才去查询分类
			return null;
		}
		List<PayCategroryWxDO> categroty = payCategroryWxService.getFirstCategrotyList(etpAttr);
		return success(categroty);
	}
	
	@RequestMapping("showSecondClassify")
	@ResponseBody
	public ResultDTO<PayCategroryWxDO> showSecondClassify(@RequestParam("etpAttr") String etpAttr, 
				@RequestParam("group_id") Integer group_id){
		List<PayCategroryWxDO> categroty = payCategroryWxService.getSecondCategrotyList(etpAttr,group_id);
		return success(categroty);
	}
	
}