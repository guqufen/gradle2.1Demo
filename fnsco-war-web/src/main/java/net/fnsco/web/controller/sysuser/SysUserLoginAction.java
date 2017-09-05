/**
 * 
 */
package net.fnsco.web.controller.sysuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.fnsco.core.base.BaseController;
import net.fnsco.order.api.sysuser.SysUserService;

/**@desc 后台管理系统登录控制器
 * @author tangliang
 * @date 2017年6月20日 下午3:11:23
 */
@Controller
@RequestMapping(value = "/web")
public class SysUserLoginAction extends BaseController{
	
	@Autowired
	private SysUserService adminUserService;
	/** 登录方法
	 * @param req
	 * @param adminUser
	 * @return
	 */
//	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
//	@ResponseBody
//	public ResultDTO<String> doLogin(HttpServletRequest req,HttpServletResponse res)
//	{
//		ResultDTO<SysUser> result = new ResultDTO<>();
//		String username = req.getParameter("account");
//		String password = req.getParameter("password");
//		if (StringUtils.isEmpty(password) || StringUtils.isEmpty(username)) {
//		    
//            return ResultDTO.fail(ApiConstant.WEB_LOGIN_NULL);
//        }
//		
//		result = adminUserService.doLogin( username, password);
//		if (result.isSuccess()) {
//		    SysUser user = result.getData();
//			setSessionUser(user,user.getId());
//			CookieUtils.addCookie(res, CoreConstants.COOKIE_USER_KEY, ((SysUser)result.getData()).getName());
//			return ResultDTO.success();
//		}
//		return ResultDTO.fail(ApiConstant.WEB_LOGIN_FAIL);
//	}
	
	/**
	 * 退出登录
	 * @return
	 */
//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public String logOut(){
//		removeSessionUser();
//		removeCookieUser();
//		return "redirect:/login.html";
//	}
	
	/**
	 * 获取当前用户
	 * @return
	 */
//	@RequestMapping(value = "/getCurrentUser", method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String,Object> getCurrentUser(){
//		Map<String,Object> result = new HashMap<>();
//		WebUserDTO adminUser = (WebUserDTO) getSessionUser();
//		result.put("sessionUser", adminUser);
//		return result;
//	}
//	
//	   /**
//     * 修改密码
//     * @return
//     */
//	
//	@RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
//    @ResponseBody
//    public ResultDTO<String> modifyPassword(String name, String newPassword, String oldPassword){
//       return adminUserService.modifyPassword(name,newPassword,oldPassword);
//    }
	
}
