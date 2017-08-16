package net.fnsco.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fnsco.auth.service.sys.dao.RoleDeptDAO;
import net.fnsco.auth.service.sys.entity.RoleDeptDO;
import net.fnsco.core.base.BaseService;

@Service
public class RoleDeptService extends BaseService {

	@Autowired
	private RoleDeptDAO roleDeptDAO;

	@Transactional
	public int saveOrUpdate(Long roleId, List<Long> deptIdList) {

		// 判断如果部门集合为空，直接返回(表示没有修改或者该角色不属于任何现有部门)
		if (deptIdList.size() == 0) {
			return 0;
		}

		// 先删除角色和部门(数据权限，不代表角色所属部门)关系
		roleDeptDAO.deleteById(roleId);

		// 批量插表
		for (Long deptId : deptIdList) {
			RoleDeptDO roleDeptDO = new RoleDeptDO();
			roleDeptDO.setRoleId(roleId);
			roleDeptDO.setDeptId(deptId);
			roleDeptDAO.insert(roleDeptDO);
		}

		return 0;
	}

	@Transactional
	public int delete(Long roleId) {
		int result = roleDeptDAO.deleteById(roleId);
		return result;
	}
}
