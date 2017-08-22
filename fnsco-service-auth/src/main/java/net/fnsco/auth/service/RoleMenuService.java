package net.fnsco.auth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fnsco.auth.service.sys.dao.RoleMenuDAO;
import net.fnsco.auth.service.sys.entity.RoleMenuDO;
import net.fnsco.core.base.BaseService;

@Service
public class RoleMenuService extends BaseService {

	@Autowired
	private RoleMenuDAO roleMenuDAO;

	// 修改或者更新都走这个方法
	@Transactional
	public int saveOrUpdate(Integer roleId, List<Integer> menuIdList) {

		// 如果菜单集合为空，直接返回
		if (menuIdList.size() == 0) {
			return 0;
		}

		// 先删除角色与菜单对应关系，因为角色和菜单是一对多关系，更新不太好更新
		roleMenuDAO.deleteById(roleId);

		// 保存角色与菜单的关系
		for (Integer menuId : menuIdList) {
			RoleMenuDO roleMenuDO = new RoleMenuDO();
			roleMenuDO.setRoleId(roleId);
			roleMenuDO.setMenuId(menuId);
			roleMenuDAO.insert(roleMenuDO);
		}

		return 0;
	}

	public void delete(Integer roleId) {

		// 通过角色ID删除角色与菜单的关系
		roleMenuDAO.deleteById(roleId);
	}

	public void deleteByMenuId(Integer integer) {

		// 通过菜单ID删除角色与菜单的关系
		roleMenuDAO.deleteByMenuId(integer);
	}

	public List<Integer> queryByRoleId(Integer roleId) {

		// 通过角色ID查找对应菜单ID
		RoleMenuDO roleMenuDO = new RoleMenuDO();
		roleMenuDO.setRoleId(roleId);
		List<RoleMenuDO> list = roleMenuDAO.query(roleMenuDO);

		// 将菜单ID放入集合
		List<Integer> roleMenuList = new ArrayList<Integer>();
		for (RoleMenuDO roleMenu : list) {
			roleMenuList.add(roleMenu.getMenuId());
		}

		return roleMenuList;
	}

	// 通过角色ID列表，查询对应菜单列表(去重)
	public List<Integer> queryByRoleIdList(List<Integer> roleIdList) {

		// 菜单ID集合
		List<Integer> roleMenuList = new ArrayList<Integer>();

		for (Integer roleId : roleIdList) {
			// 通过角色ID查找对应菜单ID
			RoleMenuDO roleMenuDO = new RoleMenuDO();
			roleMenuDO.setRoleId(roleId);
			List<RoleMenuDO> list = roleMenuDAO.query(roleMenuDO);

			// 将查询出来的menuList放入List，然后返回
			for (RoleMenuDO roleMenu : list) {
				roleMenuList.add(roleMenu.getMenuId());
			}
		}

		// 菜单ID去重
		TreeSet<Integer> hash = new TreeSet<Integer>(roleMenuList);
		roleMenuList.clear();
		roleMenuList.addAll(hash);

		return roleMenuList;
	}
}
