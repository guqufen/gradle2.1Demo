package net.fnsco.auth.service.dept;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import net.fnsco.auth.service.sys.dao.DeptDAO;
import net.fnsco.auth.service.sys.entity.DeptDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.PageDTO;
import net.fnsco.core.base.ResultPageDTO;

/**
 * 部门管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-06-20 15:23:47
 */
public class SysDeptService extends BaseService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	 @Autowired
	 private DeptDAO deptDAO;
	 // 分页
	 public ResultPageDTO<DeptDO> queryList(DeptDO dept, Integer pageNum, Integer pageSize) {
		 PageDTO<DeptDO> params = new PageDTO<DeptDO>(pageNum, pageSize, dept);
		 List<DeptDO> data = deptDAO.pageList(params);
	        //返回根据条件查询的所有记录条数
	        int totalNum = deptDAO.pageListCount(dept);
	        //返回给页面总条数和每页查询的数据
	        ResultPageDTO<DeptDO> result = new ResultPageDTO<DeptDO>(totalNum, data);
	     return result;
	 }
}
