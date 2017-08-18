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
			//设置父菜单名称
			MenuDO m = menuDAO.getById(menuDO.getParentId());
			if(m == null){
				menuDO.setParentName("--");
			}else{
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
	public MenuDO doUpdate(MenuDO menu){

		//通过传入菜单ID查找该菜单信息
		MenuDO menuDO = this.menuDAO.getById(menu.getId());
		if(menuDO ==null){
			return null;
		}
		this.menuDAO.update(menu);

		return menu;
	}
	
	@Transactional
	public MenuDO doDelete(MenuDO menu){
		
		//删除菜单
		this.menuDAO.deleteById(menu.getId());
		
		//删除角色菜单表里面角色对应的菜单，不删除的话，容易造成角色对应空菜单
		this.roleMenuService.deleteByMenuId(menu.getId());
		
		return menu;
	}
}
