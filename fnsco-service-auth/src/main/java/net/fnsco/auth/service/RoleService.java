package net.fnsco.auth.service;

import java.text.SimpleDateFormat;
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createTimeStr = sdf.format(roleDO.getCreateTime());
			roleDO.setCreateTimeStr(createTimeStr);
			
			//将数据权限放入对象
			List<Long> deptIdList = roleDeptService.queryByRoleId(roleDO.getRoleId());
			roleDO.setDeptIdList(deptIdList);
			
			//将菜单列表放入对象
			List<Long> menuList = roleMenuService.queryByRoleId(roleDO.getRoleId());
			roleDO.setMenuIdList(menuList);
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
			int result = this.roleDAO.deleteById(roleDO.getRoleId());
			if (result <= 0) {
				roleDO = null;
				return roleDO;
			}

			//删除角色菜单表对应角色数据
			result = this.roleMenuService.delete(roleId);
			if (result <= 0) {
				roleDO = null;
				return roleDO;
			}

			//删除角色数据表对应数据
			result = this.roleDeptService.delete(roleId);
			if (result <= 0) {
				roleDO = null;
				return roleDO;
			}
		}

		return roleDO;
	}

	 public List<RoleDO> queryRole() {
			 //返回根据条件查询的所有记录条数
			 List<RoleDO> data = roleDAO.queryAll();

		     return data;
	 }

}
