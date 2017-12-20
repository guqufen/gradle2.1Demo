package net.fnsco.web.appad;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.order.service.ad.AdService;
import net.fnsco.order.service.ad.entity.AdDO;

@Controller
@RequestMapping(value = "/web/e789/ad")
public class AdManagerController extends BaseController{
	
	@Autowired
	private AdService adService;
	
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions(value = { "sys:app:ad:list" })
	public ResultPageDTO<AdDO> adIndex(AdDO appAdDTO, Integer currentPageNum,
			Integer pageSize) {
		return adService.page(appAdDTO, currentPageNum, pageSize);
	}
	
	
	@RequestMapping(value = "/doAdd", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "sys:app:ad:add" })
	public void add(AdDO appAdDTO) {
		Integer userId = this.getUserId();
		adService.doAdd(appAdDTO,userId);
	}

}
