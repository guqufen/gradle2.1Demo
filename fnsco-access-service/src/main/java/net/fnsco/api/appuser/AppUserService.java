package net.fnsco.api.appuser;

import net.fnsco.api.dto.AppUserDTO;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.domain.AppUser;

public interface AppUserService {
	//用户注册方法接口
	ResultDTO<AppUser> insertSelective(AppUserDTO appUserDTO);
	//用户修改密码接口
	ResultDTO<String> modifyPassword(AppUserDTO appUserDTO);
	//生产验证码
	ResultDTO<String> getValidateCode(AppUserDTO appUserDTO);
	//通过手机号找回登录密码
	ResultDTO<String> findPassword(AppUserDTO appUserDTO);
	//根据手机号查询用户实体
	ResultDTO<String> loginByMoblie(AppUserDTO appUserDTO);
	//退出登录
	ResultDTO<String> loginOut(AppUserDTO appUserDTO);
}
