package net.fnsco.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.auth.service.sys.dao.DeptDAO;
import net.fnsco.auth.service.sys.entity.DeptDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class DeptService extends BaseService{

	@Autowired
	private DeptDAO deptDAO;
	
	public ResultPageDTO pageList(DeptDO deptDO, Integer page, Integer rows) {
		logger.info("开始分页查询DeptService.page, role=" + deptDO.toString());
		List<DeptDO> pageList = deptDAO.pageList(deptDO, page, rows);
		Integer count = deptDAO.pageListCount(deptDO);
		ResultPageDTO<DeptDO> pager = new ResultPageDTO<DeptDO>(count, pageList);
		return pager;
	}

	public DeptDO getById(Integer deptId) {
		
		return deptDAO.getById(deptId);
	}
}
