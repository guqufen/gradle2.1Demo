package net.fnsco.auth.web.role;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.auth.service.MenuService;
import net.fnsco.auth.service.sys.entity.MenuDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.constants.CoreConstants;

@Controller
@RequestMapping("/web/auth/menu")
public class MenuController extends BaseController {

	@Autowired
	private MenuService menuService;

	//查询菜单列表
	@RequestMapping("list")
	@ResponseBody
	@RequiresPermissions(value = { "sys:menu:list" })
	public ResultDTO pageList(MenuDO menuDO) {
		logger.info("开始分页查询MenuController.pageList, role=" + menuDO.toString());

		Map<String, Integer> params = super.copyParamsToInteger(new String[] { "currentPageNum", "pageSize" });
		Integer page = params.get("currentPageNum");
		Integer rows = params.get("pageSize");

		ResultPageDTO<MenuDO> pager = menuService.pageList(menuDO, page, rows);
		return success(pager);
	}

	// 选择菜单信息，去掉按钮类型
	@RequestMapping("select")
	@ResponseBody
	@RequiresPermissions(value = { "sys:menu:list" })
	public ResultDTO select() {

		return menuService.queryNotButtonList();
	}

	//新增
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "sys:menu:add" })
	public ResultDTO doAdd(MenuDO menu) {
		logger.info("开始插数据 MenuController.doAdd, menu=" + menu.toString());

		MenuDO menuDO = this.menuService.doAdd(menu);
		if (null == menuDO) {
			logger.info("数据存储失败 MenuController.doAdd, menu=" + menu.toString());
			return ResultDTO.fail(CoreConstants.E_COMM_BUSSICSS);
		}

		return success(menuDO);
	}

	//修改
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "sys:menu:update" })
	public ResultDTO doUpdate(MenuDO menu) {
		logger.info("开始更新数据 MenuController.doUpdate, menu=" + menu.toString());

		MenuDO menuDO = this.menuService.doUpdate(menu);
		if (null == menuDO) {
			logger.info("数据更新失败 MenuController.doUpdate, menu=" + menu.toString());
			return ResultDTO.fail(CoreConstants.E_COMM_BUSSICSS);
		}

		return success(menuDO);
	}

	// 删除
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "sys:menu:delete" })
	public ResultDTO doDelete(MenuDO menu) {
		logger.info("开始删除数据 MenuController.doDelete, menu=" + menu.toString());

		return menuService.doDelete(menu);
	}
	
	// 用户登录，根据用户，查询角色信息，再根据角色信息查询菜单
	@RequestMapping("userMenuist")
	@ResponseBody
	public ResultDTO userMenuist() {

		// 将在session，获取的当前用户ID,带入service处理
		return this.menuService.userMenuist(getUserId());
	}
}
