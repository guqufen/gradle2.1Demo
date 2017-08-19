package net.fnsco.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fnsco.auth.service.sys.dao.MenuDAO;
import net.fnsco.auth.service.sys.entity.MenuDO;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class MenuService extends BaseService {

	@Autowired
	private MenuDAO menuDAO;

	@Autowired
	private RoleMenuService roleMenuService;

	public ResultPageDTO<MenuDO> pageList(MenuDO menu, Integer page, Integer rows) {
		logger.info("开始分页查询MenuService.page, role=" + menu.toString());
		List<MenuDO> pageList = menuDAO.pageList(menu, page, rows);
		for (MenuDO menuDO : pageList) {
			// 设置父菜单名称
			MenuDO m = menuDAO.getById(menuDO.getParentId());
			if (m == null) {
				menuDO.setParentName("--");
			} else {
				menuDO.setParentName(m.getName());
			}
		}
		Integer count = this.menuDAO.pageListCount(menu);
		ResultPageDTO<MenuDO> pager = new ResultPageDTO<MenuDO>(count, pageList);
		return pager;
	}

	public ResultPageDTO<MenuDO> queryNotButtonList() {

		List<MenuDO> menuList = menuDAO.queryNotButtonList();

		// 添加顶级菜单
		MenuDO root = new MenuDO();
		root.setId(0);
		root.setName("一级菜单");
		root.setParentId(-1);
		root.setOpen(true);
		menuList.add(root);

		ResultPageDTO<MenuDO> pager = new ResultPageDTO<MenuDO>(0, menuList);
		return pager;
	}

	@Transactional
	public MenuDO doAdd(MenuDO menu) {

		this.menuDAO.insert(menu);
		return menu;
	}

	@Transactional
	public MenuDO doUpdate(MenuDO menu) {

		// 通过传入菜单ID查找该菜单信息
		MenuDO menuDO = this.menuDAO.getById(menu.getId());
		if (menuDO == null) {
			return null;
		}
		this.menuDAO.update(menu);

		return menu;
	}

	@Transactional
	public MenuDO doDelete(MenuDO menu) {

		// 删除目录，需要查询是否含有菜单，有则删除失败；删除菜单，需要同时删除其按钮；删除按钮，直接删除
		// 如果类型为目录类型，查询是否有菜单，如果有菜单，则删除失败(返回失败，提示信息:请先删除其子菜单)
		if (menu.getType() == 0) {
			MenuDO menuDO = new MenuDO();

			// 通过父菜单ID(为当前目录ID)和类型为1菜单来查找子菜单
			menuDO.setParentId(menu.getId());
			menuDO.setType(1);

			// 查询是否有子菜单
			Integer count = this.menuDAO.pageListCount(menuDO);

			// 如果有子菜单
			if (count > 0) {
				logger.info("删除MenuService.doDelete, 当前有子菜单，不能删除");
				// 表示当前菜单不能删除，需返回失败(返回失败，提示信息:请先删除其子菜单)
				return null;
			}
			// 否则，没有子菜单，则可以直接删除
			// 如果是菜单，则需要将菜单和其相关按钮都删掉
		} else if (menu.getType() == 1) {

			// 通过父菜单ID(为当前目录ID)来删除菜单按钮
			this.menuDAO.deleteByParentId(menu.getId());
		}

		// 删除菜单
		this.menuDAO.deleteById(menu.getId());

		// 删除角色菜单表里面角色对应的菜单，不删除的话，容易造成角色对应空菜单
		this.roleMenuService.deleteByMenuId(menu.getId());

		return menu;
	}
}
