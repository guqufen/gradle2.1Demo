package net.fnsco.api.merchant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.domain.AppUser;

public interface AppUserService {
	//用户注册方法接口
	ResultDTO<AppUser> insertAppUser(AppUser appUser);
	//用户修改密码接口
	ResultDTO<AppUser> modify(String mobile,String password,String oldPassword);
}
