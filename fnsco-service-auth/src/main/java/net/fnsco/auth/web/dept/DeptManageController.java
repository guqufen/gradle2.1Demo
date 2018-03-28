package net.fnsco.auth.web.dept;

import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.stat.TableStat.Name;

import net.fnsco.auth.service.SysDeptService;
import net.fnsco.auth.service.UserService;
import net.fnsco.auth.service.sys.entity.DeptDO;
import net.fnsco.auth.service.sys.entity.UserDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/web/auth/dept")
public class DeptManageController extends BaseController {
	@Autowired
	private SysDeptService sysDeptService;
	@Autowired
	private UserService userService;

	/**
	 * 页面信息查询
	 * 
	 * @param dept
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions(value = {"sys:dept:info"})
	public ResultDTO<DeptDO> query(DeptDO dept, @RequestParam("currentPageNum") Integer pageNum,
			@RequestParam("pageSize") Integer pageSize) {
		Integer userID = this.getUserId();
		UserDO userDO = userService.getUserById(userID);
		//用户有代理查代理下部门，无代理查全部
		ResultPageDTO<DeptDO> result = null;
		if(userDO != null){
			if(userDO.getAgentId() == null){
				result = sysDeptService.queryList(dept, pageNum, pageSize);
			}else{
				dept.setAgentId(userDO.getAgentId());
				result = sysDeptService.queryList2(dept, pageNum, pageSize);
			}
		}
		return success(result);
		
	}

	/**
	 * 部门树查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "/querytree", method = RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions(value = {"sys:dept:info"})
	public ResultDTO<DeptDO> querytree() {
		Integer userID = this.getUserId();
		UserDO userDO = userService.getUserById(userID);
		if(userDO == null){
			return null;
		}
//		Integer agentId = null;
//		String name;
//		if(userDO != null){
//			if(userDO.getAgentId() != null){
//				name = userDO.getName();
//			}
//		}
		List<DeptDO> result = sysDeptService.queryName(false,userDO);
		return success(result);
	}
	
	/**
	 * 部门树查询,不带root根节点
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryNoRoottree", method = RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions(value = {"sys:dept:info"})
	public ResultDTO<DeptDO> queryNoRoottree() {
		Integer userID = this.getUserId();
		UserDO userDO = userService.getUserById(userID);
//		Integer agentId = null;
//		if(userDO != null){
//			if(userDO.getAgentId() != null){
//				agentId = userDO.getAgentId();
//			}
//		}
		List<DeptDO> result = sysDeptService.queryName(false,userDO);
		return success(result);
	}

	/**
	 * 部门添加
	 * 
	 * @param dept
	 * @return
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = {"sys:dept:save"})
	public ResultDTO<String> toAdd(DeptDO dept) {
		sysDeptService.doAddDept(dept);
		return ResultDTO.successForSave(null);
	}

	/**
	 * 部门修改
	 * 
	 * @param dept
	 * @return
	 */
	@RequestMapping(value = "/toEdit", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = {"sys:dept:update"})
	public ResultDTO<String> toEdit(DeptDO dept) {
		ResultDTO<String> result = sysDeptService.toEditDept(dept);
		return result;
	}

	/**
	 * 通过id查询修改对象的数据
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryDeptById", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = {"sys:dept:info"})
	public ResultDTO<DeptDO> queryDeptById(Integer id) {
		DeptDO result = sysDeptService.queryDeptById(id);
		return success(result);
	}

	/**
	 * 通过id查询删除对象（状态重0改-1）
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteDeptById", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = {"sys:dept:delete"})
	public ResultDTO<String> deleteDeptById(@RequestParam(value = "id[]") Integer[] id) {
		ResultDTO<String> result = sysDeptService.deleteById(id);
		return result;
	}

}
