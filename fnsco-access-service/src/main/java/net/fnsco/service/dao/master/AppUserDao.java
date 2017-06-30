package net.fnsco.service.dao.master;

import org.apache.ibatis.annotations.Param;

import net.fnsco.service.domain.AppUser;

public interface AppUserDao {
	//新增注册信息
	boolean insertSelective(AppUser mAppUser);
	//根据用户手机号查询用户实体
	AppUser selectAppUserByMobile(@Param("mobile")String mobile);
	//根据id查询用户实体
	AppUser selectAppUserById(@Param("id")Integer id);
	//修改用户信息包括密码等  
	boolean updateByPrimaryKeySelective(AppUser appUser);
	//根据手机号找回密码
	boolean updatePasswordByPhone(@Param("mobile")String mobile,@Param("password")String password);
	
}
