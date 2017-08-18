package net.fnsco.auth.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.auth.service.sys.dao.DeptDAO;
import net.fnsco.auth.service.sys.dao.RoleDeptDAO;
import net.fnsco.auth.service.sys.entity.DeptDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.constants.CoreConstants;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class SysDeptService extends BaseService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	 @Autowired
	 private DeptDAO deptDAO;
	 @Autowired
	 private RoleDeptDAO roleDeptDAO;
	 /**
	 * 分页查询部门管理首页信息
	 * @param dept
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public ResultPageDTO<DeptDO> queryList(DeptDO dept, Integer pageNum, Integer pageSize) {
		 List<DeptDO> data = deptDAO.pageList(dept,pageNum, pageSize);
		 for(DeptDO temp :data) {
			 DeptDO tdo = deptDAO.getById(temp.getParentId());
			 if(tdo==null) {
				 temp.setParentName(null);
			 }else {
				 temp.setParentName(tdo.getName());
			 }
		 }
	        //返回根据条件查询的所有记录条数
	        int totalNum = deptDAO.pageListCount(dept);
	        //返回给页面总条数和每页查询的数据
	        ResultPageDTO<DeptDO> result = new ResultPageDTO<DeptDO>(totalNum, data);
	     return result;
	 }
	 /**
	  * 查询部门相关信息
	  * @return
	  */
	 public List<DeptDO> queryName() {
		 //返回根据条件查询的所有记录条数
		 List<DeptDO> data = deptDAO.pageNameList();
	        //ResultDTO<DeptDO> result = new ResultDTO<DeptDO>(data);
	     return data;
	 }
	 /**
	  * 添加部门
	  * @param dept
	  * @return
	  */
	 public ResultDTO<String> doAddDept(DeptDO dept) {
		 dept.setDelFlag(0);
		 int parentid=deptDAO.getByName(dept.getParentName());
		 dept.setParentId(parentid);
		 int res = deptDAO.insert(dept);
		 if (res <1) {
             return ResultDTO.fail();
         }
		 return ResultDTO.success();
	    }
	 /**
	  * 修改部门
	  * @param dept
	  * @return
	  */
	 public ResultDTO<String> toEditDept(DeptDO dept) {
		 dept.setDelFlag(0);
		 int parentid=deptDAO.getByName(dept.getParentName());
		 dept.setParentId(parentid);
		 int res = deptDAO.update(dept);
		 if (res <1) {
             return ResultDTO.fail();
         }
		 return ResultDTO.success();
	    }
	 /**
	  * 修改内的部门信息查询
	  * @param id
	  * @return
	  */
	 public DeptDO queryDeptById(Integer id) {
		 DeptDO data = deptDAO.getById(id);
		 if(data.getParentId()!=0) {
			 String tdo = deptDAO.getById(data.getParentId()).getName();
			 if(tdo==null) {
				 data. setParentName(null);
			 }else {
				 data.setParentName(tdo);
			 }
		 }
	     return  data;
	 }
	 /**
	  * 通过id删除部门（状态变-1）
	  * @param id
	  * @return
	  */
	 public ResultDTO<String> deleteById(Integer[] id) {
		 for(int i=0;i<id.length;i++){	
			int res = deptDAO.deleteById(id[i]);
		 if (res <1) {
             return ResultDTO.fail();
         }
		int re= roleDeptDAO.deleteByDeptId(id[i]);
		if (re <1) {
            return ResultDTO.fail();
        }
	    } 
		 return ResultDTO.success();
		 }
	 /**
	  * 遍历获取部门ID
	  * @param id
	  * @return
	  */
	 public ResultDTO<String> queryParentId(Integer[] id) {
		 for(int i=0;i<id.length;i++){	
			 List<Integer> dept=deptDAO.getByparentId(id[i]);
			if(dept.size()!=0) {
				return ResultDTO.fail();
			}
	    } 
		 return ResultDTO.success();
		 }
		 
}
