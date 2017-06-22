/**
 * 
 */
package net.fnsco.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.Constants;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.CookieUtils;
import net.fnsco.service.domain.AdminUser;
import net.fnsco.service.service.AdminUserService;

/**@desc 后台管理系统登录控制器
 * @author tangliang
 * @date 2017年6月20日 下午3:11:23
 */
@Controller
@RequestMapping("/back_login")
public class AdminUserLoginAction extends BaseController{
	
	@Autowired
	private AdminUserService adminUserService;
	/** 登录方法
	 * @param req
	 * @param adminUser
	 * @return
	 */
	@RequestMapping("/doLogin")
	public String doLogin(HttpServletRequest req,HttpServletResponse res)
	{
		ResultDTO<AdminUser> result = new ResultDTO<>();
		String username = req.getParameter("account");
		String password = req.getParameter("password");
		if (StringUtils.isEmpty(password)) {
            result.setError("输入密码错误!");
            return "forward:/";
        }
		
		result = adminUserService.doLogin(res, username, password);
		if (result.isSuccess()) {
			setSessionUser(result.getData());
			CookieUtils.addCookie(res, Constants.COOKIE_USER_KEY, ((AdminUser)result.getData()).getName());
		}
		return "forward:/";
	}
	
	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping("/logout")
	public String logOut(){
		removeSessionUser();
		removeCookieUser();
		return "redirect:/";
	}
	
	/**
	 * 获取当前用户
	 * @return
	 */
	@RequestMapping("/getCurrentUser")
	@ResponseBody
	public Map<String,Object> getCurrentUser(){
		Map<String,Object> result = new HashMap<>();
		AdminUser adminUser = (AdminUser) getSessionUser();
		if(null == adminUser)
		{
			String cookie = (String) getCookieUser();
			adminUser = new AdminUser();
			adminUser.setName(cookie.substring(cookie.lastIndexOf("#")+1, cookie.length()));
		}
		result.put("sessionUser", adminUser);
		return result;
	}
	
}
