package net.fnsco.auth.web.role;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@RequestMapping("list")
	@ResponseBody
	public ResultDTO pageList(MenuDO menuDO) {
		logger.info("开始分页查询RoleController.pageList, role=" + menuDO.toString());
		Map<String, Integer> params = super.copyParamsToInteger(new String[] { "currentPageNum", "pageSize" });
		Integer page = params.get("currentPageNum");
		Integer rows = params.get("pageSize");

		ResultPageDTO<MenuDO> pager = menuService.pageList(menuDO, page, rows);
		return success(pager);
	}
	
	//添加菜单信息
	@RequestMapping("select")
	@ResponseBody
	public ResultDTO select(){
		
		//查询列表数据
		ResultPageDTO<MenuDO> pager = menuService.queryNotButtonList();

		return success(pager);
	}
	
	@RequestMapping("add")
	@ResponseBody
	public  ResultDTO doAdd(MenuDO menu){
		logger.info("开始插数据 RoleController.doAdd, menu=" + menu.toString());
		
		MenuDO menuDO = this.menuService.doAdd(menu);
		if(null == menuDO){
			logger.info("数据存储失败 RoleController.doAdd, role=" + menu.toString());
			return ResultDTO.fail(CoreConstants.E_COMM_BUSSICSS);
		}
		
		return success(menu);
	}
}
