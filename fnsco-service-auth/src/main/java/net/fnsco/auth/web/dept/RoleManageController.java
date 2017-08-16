package net.fnsco.auth.web.dept;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.auth.service.RoleService;
import net.fnsco.auth.service.sys.entity.RoleDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;


/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/web/auth/role")
public class RoleManageController extends BaseController {
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value ="/queryType",method= RequestMethod.POST)
	@ResponseBody
	public ResultDTO<RoleDO> queryType(){
		List<RoleDO> result = roleService.queryType();
		return success(result);
	}
}














