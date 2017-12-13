package net.fnsco.web.agent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.car.service.agent.dao.AgentDAO;
import net.fnsco.car.service.agent.entity.AgentDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
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
