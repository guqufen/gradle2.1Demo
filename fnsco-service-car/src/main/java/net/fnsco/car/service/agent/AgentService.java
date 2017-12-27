package net.fnsco.car.service.agent;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.car.service.agent.dao.AgentDAO;
import net.fnsco.car.service.agent.entity.AgentDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class AgentService extends BaseService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AgentDAO agentDAO;

	// 分页
	public ResultPageDTO<AgentDO> page(AgentDO agent, Integer pageNum, Integer pageSize) {
		logger.info("开始分页查询AgentService.page, agent=" + agent.toString());
		List<AgentDO> pageList = this.agentDAO.pageList(agent, pageNum, pageSize);
		Integer count = this.agentDAO.pageListCount(agent);
		ResultPageDTO<AgentDO> pager = new ResultPageDTO<AgentDO>(count, pageList);
		return pager;
	}

	// 添加
	public AgentDO doAdd(AgentDO agent, int loginUserId) {
		logger.info("开始添加AgentService.add,agent=" + agent.toString());
		agent.setCreateTime(new Date());
		this.agentDAO.insert(agent);
		return agent;
	}

	// 修改
	public Integer doUpdate(AgentDO agent, Integer loginUserId) {
		logger.info("开始修改AgentService.update,agent=" + agent.toString());
		int rows = this.agentDAO.update(agent);
		return rows;
	}

	// 删除
	public Integer doDelete(AgentDO agent, Integer loginUserId) {
		logger.info("开始删除AgentService.delete,agent=" + agent.toString());
		int rows = this.agentDAO.deleteById(agent.getId());
		return rows;
	}

	// 删除
	public Integer doDelete(Integer id) {
		logger.info("开始删除AgentService.delete,agent=" + id);
		int rows = this.agentDAO.deleteById(id);
		return rows;
	}

	// 查询
	public AgentDO doQueryById(Integer id) {
		AgentDO obj = this.agentDAO.getById(id);
		return obj;
	}

	// 查询
	public AgentDO doQueryByCode(String code) {
		AgentDO obj = this.agentDAO.getByCode(code);
		return obj;
	}
}