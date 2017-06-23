package net.fnsco.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.fnsco.api.merchant.AppUserService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.domain.AppUser;

@RestController
@RequestMapping("/app/user")
public class AppUserController extends BaseController{
	@Autowired
	private AppUserService mAppUserService;
	@RequestMapping(value = "/register")
	@ApiOperation(value = "用户注册")
	@ResponseBody
	public ResultDTO register(){
		 String mobile=request.getParameter("mobile");
		 String password=request.getParameter("password");
		 AppUser appUser=new AppUser();
		 appUser.setMobile(mobile);
		 appUser.setPassword(password);
		 //验证码验证
		 
		 //注册
		 ResultDTO<AppUser> result=mAppUserService.insertAppUser(appUser);
		 return result;
	}
	
	//修改密码     旧密码和新密码
	@RequestMapping(value = "/modify")
	@ResponseBody
	public ResultDTO<AppUser> modify(){
		//创建实体对象
		ResultDTO<AppUser> result = new ResultDTO<>();
		//如果密码为空
		String oldPassword=request.getParameter("oldPassword");
		if(StringUtils.isEmpty(oldPassword)){
			 return result.setError("密码不能为空");
		 }
		result=mAppUserService.modify(res, req);
		return result;
	}
}
















