package net.fnsco.web.controller.appadvertise;
import java.util.Arrays;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.service.domain.MerchantBank;
import net.fnsco.bigdata.service.domain.MerchantEntity;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

/**
 * 
 */
/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/web/e789/ad")
public class AdvertiseController extends BaseController{
	
	
	 @RequestMapping(value = "/add", method = RequestMethod.POST)
	 @ApiOperation(value = "新增广告或资讯")
	 @ResponseBody
	 @RequiresPermissions(value = { "sys:advertise:save" })
	 public ResultDTO<String> addAdvertise() {
			
	      return success(null);
	 }
	 
	
//	@RequestMapping(value = "/update")
//	@ApiOperation(value = "修改广告或资讯")
//	@ResponseBody
//	@RequiresPermissions(value = {"sys:advertise:update"})
//	public ResultDTO<String> update(AppAdDO appAdDO) {
//		
//	 return success(null);
//	}
	
	@RequestMapping(value = "/delete")
	@ApiOperation(value = "删除广告或资讯")
	@ResponseBody
	@RequiresPermissions(value = {"sys:advertise:delete"})
	public ResultDTO delete(@RequestParam(value = "id") Integer id) {
		
      return ResultDTO.success();
	}
	
	
//	@RequestMapping(value = "/query")
//	@ApiOperation(value = "查詢广告或资讯")
//	@ResponseBody
//	@RequiresPermissions(value = {"sys:advertise:query"})
//	public ResultPageDTO<> query( Integer currentPageNum,Integer pageSize) {
//		
//      return null;
//	}
	
}