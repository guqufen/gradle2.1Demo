package net.fnsco.api.merchant;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.domain.AppUser;

public interface AppUserService {
	//用户注册方法接口
	ResultDTO<AppUser> insertAppUser(AppUser appUser);
	//用户修改密码接口
	ResultDTO<AppUser> modify(String mobile,String password,String oldPassword);
	//生产验证码
	ResultDTO<String> getValidateCode(String deviceId, int deviceType,String mobile);
	//比对验证码
	ResultDTO<String> validateCode(String mobile, String code);
	//通过手机号找回登录密码
	ResultDTO<String> findPassword(String mobile, String code);
}
