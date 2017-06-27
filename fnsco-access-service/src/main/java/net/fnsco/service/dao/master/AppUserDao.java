package net.fnsco.service.dao.master;

import org.apache.ibatis.annotations.Param;

import net.fnsco.service.domain.AppUser;

public interface AppUserDao {
	//新增注册信息
	boolean insertSelective(AppUser mAppUser);
	//根据用户手机号查询用户实体
	AppUser getAppUserByMobile(@Param("mobile")String mobile);
	//根据id查询用户实体
	AppUser selectById(@Param("id")Integer id);
	//修改用户信息包括密码等  
	boolean updateById(AppUser appUser);
	//根据手机号找回密码
	boolean findPasswordByPhone(@Param("mobile")String mobile,@Param("password")String password);
	//根据手机号查询用户id
	int idByPhone(@Param("mobile")String mobile);
	
}
