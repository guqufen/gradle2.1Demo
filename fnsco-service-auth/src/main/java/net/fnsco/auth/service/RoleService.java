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
import net.fnsco.core.base.ResultPageDTO;

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

	public ResultPageDTO<RoleDO> pageList(RoleDO role, Integer page, Integer rows) {
		logger.info("开始分页查询RoleService.page, role=" + role.toString() + ",page=" + page + ",rows=" + rows);
		List<RoleDO> pageList = roleDAO.queryList(role, page, rows);
		for (RoleDO roleDO : pageList) {
			Integer deptId = roleDO.getDeptId();
			if (null != deptId) {
				DeptDO deptDO = deptService.getById(deptId);
				if (deptDO == null) {
					roleDO.setDeptName("--");
				} else {
					roleDO.setDeptName(deptDO.getName());
				}
			}
		}
		Integer count = this.roleDAO.pageListCount(role);
		ResultPageDTO<RoleDO> pager = new ResultPageDTO<RoleDO>(count, pageList);
		return pager;
	}

	@Transactional
	public RoleDO doAdd(RoleDO role) {

		// 保存数据到角色表
		role.setCreateTime(new Date());
		this.roleDAO.insert(role);

		// 保存(角色-菜单)数据到角色菜单表
		this.roleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());

		// 保存(角色-数据权限部门)数据到角色数据表
		this.roleDeptService.saveOrUpdate(role.getRoleId(), role.getDeptIdList());

		return role;
	}

	@Transactional
	public RoleDO doUpdate(RoleDO role) {

		if( role.getRoleId() == null){
			return null;
		}
		int result = this.roleDAO.update(role);

		// 保存(角色-菜单)数据到角色菜单表
		result = this.roleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());

		// 保存(角色-数据权限部门)数据到角色数据表
		this.roleDeptService.saveOrUpdate(role.getRoleId(), role.getDeptIdList());
		return role;
	}

	@Transactional
	public RoleDO doDelete(RoleDO roleDO) {

		RoleDO role = new RoleDO();
		Long roleId = roleDO.getRoleId();
		if (null != roleId) {
			role.setRoleId(roleId);

			// 删除角色表数据(根据ID删除)
			int result = this.roleDAO.delete(roleDO);
			if (result <= 0) {
				roleDO = null;
				return roleDO;
			}

			result = this.roleMenuService.delete(roleId);
			if (result <= 0) {
				roleDO = null;
				return roleDO;
			}

			result = this.roleDeptService.delete(roleId);
			if (result <= 0) {
				roleDO = null;
				return roleDO;
			}
		}

		return roleDO;
	}

	
	 public List<RoleDO> queryType() {
			 //返回根据条件查询的所有记录条数
			 List<RoleDO> data = roleDAO.query();
		        //ResultDTO<DeptDO> result = new ResultDTO<DeptDO>(data);
		     return data;
	 }

}
