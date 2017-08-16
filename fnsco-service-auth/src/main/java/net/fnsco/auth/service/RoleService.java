package net.fnsco.auth.service;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.auth.service.sys.dao.RoleDAO;
import net.fnsco.auth.service.sys.entity.RoleDO;
import net.fnsco.core.base.BaseService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class RoleService extends BaseService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	 @Autowired
	 private RoleDAO roleDAO; 
	
	 public List<RoleDO> queryType() {
			 //返回根据条件查询的所有记录条数
			 List<RoleDO> data = roleDAO.query();
		        //ResultDTO<DeptDO> result = new ResultDTO<DeptDO>(data);
		     return data;
	 }
}
