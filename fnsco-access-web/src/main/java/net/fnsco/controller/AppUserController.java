package net.fnsco.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.domain.AppUser;
import net.fnsco.service.service.AppUserService;

@RestController
public class AppUserController {
	@Autowired
	private AppUserService appUserService;
	@RequestMapping(value = "/register", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public ResultDTO<Object> register(HttpServletRequest req){
		 //创建实体对象
		 AppUser appUser=new AppUser();
		 String phonenum=req.getParameter("phonenum");
	     String password=req.getParameter("password");
	     appUser.setPhonenum(phonenum);
	     appUser.setPassword(password);
	     //调用service注册方法
	     ResultDTO<Object> result =appUserService.insertAppUser(appUser);
	     return result;
	}
}
