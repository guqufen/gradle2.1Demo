package net.fnsco.auth.service;

import java.util.ArrayList;
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
	public int saveOrUpdate(Integer roleId, List<Integer> deptIdList) {

		// 判断如果部门集合为空，直接返回(表示没有修改或者该角色不属于任何现有部门)
		if (deptIdList.size() == 0) {
			return 0;
		}

		// 先删除角色和部门(数据权限，不代表角色所属部门)关系
		roleDeptDAO.deleteById(roleId);

		// 批量插表
		for (Integer deptId : deptIdList) {
			RoleDeptDO roleDeptDO = new RoleDeptDO();
			roleDeptDO.setRoleId(roleId);
			roleDeptDO.setDeptId(deptId);
			roleDeptDAO.insert(roleDeptDO);
		}

		return 0;
	}

	// 通过角色ID将对应的数据权限ID全部删掉
	@Transactional
	public int delete(Integer roleId) {
		int result = roleDeptDAO.deleteById(roleId);
		return result;
	}

	// 通过角色ID查找部门ID(一对多，此处部门ID属于数据权限ID),并将查出来的数据的deptId存于list集合
	public List<Integer> queryByRoleId(Integer roleId) {

		RoleDeptDO roleDept = new RoleDeptDO();
		roleDept.setRoleId(roleId);

		List<RoleDeptDO> list = roleDeptDAO.query(roleDept);

		// 遍历，将角色ID对应的数据权限ID查出来并放入集合list
		List<Integer> deptIdList = new ArrayList<>();
		for (RoleDeptDO roleDeptDO : list) {
			deptIdList.add(roleDeptDO.getDeptId());
		}
		return deptIdList;
	}
}
