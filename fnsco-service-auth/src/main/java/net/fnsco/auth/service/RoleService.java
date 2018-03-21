package net.fnsco.auth.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fnsco.auth.service.sys.dao.RoleDAO;
import net.fnsco.auth.service.sys.entity.DeptDO;
import net.fnsco.auth.service.sys.entity.RoleDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;

@Service
public class RoleService extends BaseService {

	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private DeptService deptService;
	@Autowired
	private RoleMenuService roleMenuService;
	@Autowired
	private RoleDeptService roleDeptService;

	//分页查找所有角色数据
	public ResultPageDTO<RoleDO> pageList(RoleDO role, Integer page, Integer rows) {
		logger.info("开始分页查询RoleService.page, role=" + role.toString() + ",page=" + page + ",rows=" + rows);
		List<RoleDO> pageList = roleDAO.pageList(role, page, rows);
		
		for (RoleDO roleDO : pageList) {
			
			//给部门名称赋值
			Integer deptId = roleDO.getDeptId();
			if (null != deptId) {
				DeptDO deptDO = deptService.getById(deptId);
				if (deptDO == null) {
					roleDO.setDeptName("--");
				} else {
					roleDO.setDeptName(deptDO.getName());
				}
			}
			
			//时间格式化yyyy-MM-dd HH:mm:ss
			String createTimeStr = DateUtils.dateFormatToStr(roleDO.getCreateTime());
			roleDO.setCreateTimeStr(createTimeStr);
			
			//将数据权限放入对象
			List<Integer> deptIdList = roleDeptService.queryByRoleId(roleDO.getRoleId());
			roleDO.setDeptIdList(deptIdList);
			
			//将菜单列表放入对象
			List<Integer> menuList = roleMenuService.queryByRoleId(roleDO.getRoleId());
			roleDO.setMenuIdList(menuList);
		}

		Integer count = this.roleDAO.pageListCount(role);
		ResultPageDTO<RoleDO> pager = new ResultPageDTO<RoleDO>(count, pageList);
		return pager;
	}

	@Transactional
	public ResultDTO doAdd(RoleDO role) {

		// 保存数据到角色表
		role.setCreateTime(new Date());
		this.roleDAO.insert(role);

		// 保存(角色-菜单)数据到角色菜单表
		this.roleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());

		// 保存(角色-数据权限部门)数据到角色数据表
		this.roleDeptService.saveOrUpdate(role.getRoleId(), role.getDeptIdList());

		return ResultDTO.success();
	}

	@Transactional
	public ResultDTO doUpdate(RoleDO role) {

		if( role.getRoleId() == null){
			return ResultDTO.fail();
		}
		this.roleDAO.update(role);

		// 保存(角色-菜单)数据到角色菜单表
		this.roleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());

		// 保存(角色-数据权限部门)数据到角色数据表
		this.roleDeptService.saveOrUpdate(role.getRoleId(), role.getDeptIdList());
		return ResultDTO.success();
	}

	@Transactional
	public ResultDTO doDelete(RoleDO roleDO) {

		RoleDO role = new RoleDO();
		Integer roleId = roleDO.getRoleId();
		if (null != roleId) {
			role.setRoleId(roleId);

			// 删除角色表数据(根据ID删除)
			this.roleDAO.deleteById(roleDO.getRoleId());

			//删除角色菜单表对应角色数据
			this.roleMenuService.delete(roleId);

			//删除角色数据表对应数据
			this.roleDeptService.delete(roleId);

			return ResultDTO.success();
		}
		//否则返回失败
		return ResultDTO.fail();
	}

	 public List<RoleDO> queryRole() {
			 //返回根据条件查询的所有记录条数
			 List<RoleDO> data = roleDAO.queryAll();

		     return data;
	 }

	public List<RoleDO> queryRoleByUserID(Integer userID) {
		//返回根据条件查询的所有记录条数
		 List<RoleDO> data = roleDAO.queryRoleByUserID(userID);

	     return data;
		
	}

}
