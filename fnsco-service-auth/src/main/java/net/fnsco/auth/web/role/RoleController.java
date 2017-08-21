package net.fnsco.auth.web.role;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.auth.service.RoleService;
import net.fnsco.auth.service.sys.entity.RoleDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.constants.CoreConstants;

/**
 * 角色管理控制器
 * @author yx
 *
 */
@Controller
@RequestMapping("/web/auth/role")
public class RoleController extends BaseController{
	
	@Autowired
	private RoleService roleService;

	@RequestMapping("list")
	@ResponseBody
	public ResultDTO pageList(RoleDO role) {
		logger.info("开始分页查询RoleController.pageList, role=" + role.toString());
		Map<String, Integer> params = super.copyParamsToInteger(new String[] { "currentPageNum", "pageSize" });
		Integer page = params.get("currentPageNum");
		Integer rows = params.get("pageSize");

		ResultPageDTO<RoleDO> pager = this.roleService.pageList(role, page, rows);
		return success(pager);
	}

	@RequestMapping("add")
	@ResponseBody
	public  ResultDTO doAdd(RoleDO role){
		logger.info("开始新增RoleController.doAdd, role=" + role.toString());
		
		RoleDO roleDO = this.roleService.doAdd(role); 
		if(null == roleDO){
			logger.info("数据存储失败 RoleController.doAdd, role=" + role.toString());
			return ResultDTO.fail(CoreConstants.E_COMM_BUSSICSS);
		}
		return success(roleDO);
	}
	
	@RequestMapping("update")
	@ResponseBody
	public ResultDTO doUpdate(RoleDO role){
		logger.info("开始修改RoleController.doUpdate, roleId=" + role.getRoleId());
		
		RoleDO roleDO = this.roleService.doUpdate(role);
		if(null == roleDO){
			logger.info("数据存储失败 RoleController.doUpdate, roleId=" + role.getRoleId());
			return ResultDTO.fail(CoreConstants.E_COMM_BUSSICSS);
		}
		
		return success(roleDO);
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ResultDTO doDelete(RoleDO role){
		logger.info("开始新增RoleController.doDelete, roleId=" + role.getRoleId());
		
		RoleDO roleDO = this.roleService.doDelete(role);
		if(null == roleDO){
			logger.info("数据删除失败 RoleController.doUpdate, roleId=" + role.getRoleId());
			return ResultDTO.fail(CoreConstants.E_COMM_BUSSICSS);
		}
		
		return success(roleDO);
	}
	@RequestMapping(value ="/queryRole",method= RequestMethod.POST)
	@ResponseBody
	public ResultDTO<RoleDO> queryRole(){
		List<RoleDO> result = roleService.queryRole();
		return success(result);
	}
}
