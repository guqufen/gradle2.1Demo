package net.fnsco.risk.web.report;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.risk.service.report.MerAllocationService;
import net.fnsco.risk.service.sys.entity.MerAllocationDO;

/**
 * 商户分配Controller
 * 主要负责查询商户数据以及更新代理商和内部商户号的关系
 * @author jiangw
 *
 */
@Controller
@RequestMapping(value="/web/MerAllocation", method=RequestMethod.POST)
@Api(value="/web/MerAllocation",tags={""})
public class MerAllocationController extends BaseController{

	@Autowired
	private MerAllocationService merAllocationService;
	
	/**
	 * 获取所有商户数据(添加，不属于)
	 * @param agentId：代理商ID
	 * @param pageNum：页码
	 * @param pageSize:每页条数
	 * @return 商户信息List集合
	 */
	@RequestMapping(value="/queryAddMerData", method=RequestMethod.GET)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ResultDTO getMerDataAdd(MerAllocationDO merAllocationDO, @RequestParam("currentPageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize){

		String agentId = request.getParameter("userAgentId");

		if(StringUtils.isBlank(agentId)){
			return fail("代理商ID为空！！");
		}

		ResultPageDTO<MerAllocationDO> pager = merAllocationService.getAddMerData(merAllocationDO, Integer.parseInt(agentId), pageNum, pageSize);
		if(pager == null){
			return fail();
		}
		return success(pager);
	}
	
	/**
	 * 获取所有商户数据(详情/移除，属于该代理商)
	 * @param agentId：代理商ID
	 * @param pageNum：页码
	 * @param pageSize:每页条数
	 * @return 商户信息List集合
	 */
	@RequestMapping(value="/queryMerData", method=RequestMethod.GET)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ResultDTO getMerData(MerAllocationDO merAllocationDO, @RequestParam("currentPageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize){

		String agentId = request.getParameter("userAgentId");
		String type = request.getParameter("type");

		if(StringUtils.isBlank(agentId)){
			return fail("代理商ID为空！！");
		}
//		if(StringUtils.isBlank(type)){
//			return fail("操作类型为空！！");
//		}

		ResultPageDTO<MerAllocationDO> pager = merAllocationService.getMerData(merAllocationDO, Integer.parseInt(agentId),  pageNum, pageSize);
		if(pager == null){
			return fail();
		}
		return success(pager);
	}
	
	@RequestMapping(value="/addMerAllo", method=RequestMethod.POST)
	@ResponseBody
	public ResultDTO addMerAllo(String agentId, String [] innerCodeList){
		
		return merAllocationService.addMerAllo(Integer.parseInt(agentId), innerCodeList);
	}
	
	@RequestMapping(value="/deleteByagentInnerId", method=RequestMethod.POST)
	@ResponseBody
	public ResultDTO deleteByagentInnerId(String agentId, String [] innerCodeList){
		return merAllocationService.delMerAllo(Integer.parseInt(agentId), innerCodeList);
	}
}
