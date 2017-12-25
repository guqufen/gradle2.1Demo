package net.fnsco.web.agent;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import net.fnsco.car.service.agent.AgentService;
import net.fnsco.car.service.agent.dao.AgentDAO;
import net.fnsco.car.service.agent.entity.AgentDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.freamwork.business.WebUserDTO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月12日 下午4:31:00
 */
@Controller
@RequestMapping(value = "/web/agent")
public class CarAgentWebController extends BaseController {
	
	@Autowired
	private AgentDAO agentDAO;
	
	@Autowired
	private AgentService agentService;
	
	/**
	 * query:(分页查询)
	 *
	 * @param  @param agentDO
	 * @param  @param currentPageNum
	 * @param  @param pageSize
	 * @param  @return    设定文件
	 * @return ResultPageDTO<AgentDO>    DOM对象
	 * @author tangliang
	 * @date   2017年12月25日 下午1:23:08
	 */
	@RequestMapping(value = "/queryPage", method = RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions(value = { "car:agent:list" })
	public ResultPageDTO<AgentDO>  query(AgentDO agentDO ,Integer currentPageNum,Integer pageSize){
		logger.info("查询运营商列表");
		
		ResultPageDTO<AgentDO> result  = agentService.page(agentDO, currentPageNum, pageSize);
		return result;
	}
	
	/**
	 * toAddOrUpdate:(添加新增和修改功能，带ID表示修改，不带表示新增加)
	 *
	 * @param  @param agentDO
	 * @param  @return    设定文件
	 * @return ResultDTO<String>    DOM对象
	 * @author tangliang
	 * @date   2017年12月25日 下午2:25:04
	 */
	@RequestMapping(value = "/toAddOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "car:agent:add" })
	public ResultDTO<String>  toAdd(AgentDO agentDO){
		logger.info("添加或修改运营商");
		
		if(null == agentDO.getId()) {
			agentService.doAdd(agentDO, getUserId());
		}else {
			agentService.doUpdate(agentDO, getUserId());
		}
		return ResultDTO.successForSubmit();
	}
	
	/**
	 * toDelete:(删除功能)
	 *
	 * @param  @param ids
	 * @param  @return    设定文件
	 * @return ResultDTO<String>    DOM对象
	 * @author tangliang
	 * @date   2017年12月25日 下午2:35:35
	 */
	@RequestMapping(value = "/toDelete", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "car:agent:Delete" })
	public ResultDTO<String>  toDelete(@RequestParam(value = "ids[]")Integer[] ids){
		logger.info("删除运营商");
		for (int i : ids) {
			agentService.doDelete(i);
		}
		return ResultDTO.successForSubmit();
	}
	
	/**
	 * querySingle:(查询单个运营商信息)
	 *
	 * @param  @param id
	 * @param  @return    设定文件
	 * @return ResultDTO<AgentDO>    DOM对象
	 * @author tangliang
	 * @date   2017年12月25日 下午2:43:11
	 */
	@RequestMapping(value = "/querySingle", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "car:agent:list" })
	public ResultDTO<AgentDO> querySingle(@RequestParam(value = "id") Integer id){
		if(null == id) {
			return ResultDTO.fail();
		}
		AgentDO date  = agentService.doQueryById(id);
		
		return ResultDTO.success(date);
	}
	
	/**
	 * queryAll:(查询所有代理商)
	 *
	 * @param  @return    设定文件
	 * @return ResultDTO<List<AgentDO>>    DOM对象
	 * @author tangliang
	 * @date   2017年12月12日 下午4:33:51
	 */
	@RequestMapping(value = "/queryAll", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<List<AgentDO>> queryAll(){
		List<AgentDO> datas = agentDAO.getAll();
		return success(datas);
	}
	
	
	/**
	 * getAgentInfo:(查询登录用户单个信息)
	 *
	 * @param  @return    设定文件
	 * @return ResultDTO<AgentDO>    DOM对象
	 * @author tangliang
	 * @date   2017年12月13日 上午11:33:28
	 */
	@RequestMapping(value = "/getAgentInfo",method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<AgentDO> getAgentInfo(){
		WebUserDTO adminUser = (WebUserDTO) getSessionUser();
		 if(null != adminUser && null != adminUser.getAgentId()) {
			 AgentDO agentDO = agentDAO.getById(adminUser.getAgentId());
			 if(null != agentDO) {
				 return ResultDTO.success(agentDO);
			 }
		 }
		 return ResultDTO.success(new AgentDO());
	}
}
