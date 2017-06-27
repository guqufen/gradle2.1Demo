package net.fnsco.service.dao.master;

import org.apache.ibatis.annotations.Param;

import net.fnsco.service.domain.AppUser;

public interface AppUserDao {
	//新增注册信息
	boolean insertSelective(AppUser mAppUser);
	//根据用户手机号查询用户之前是否已经注册
	int selectByPrimaryKey(@Param("mobile")String mobile);
	//根据用户手机号查询用户实体
	AppUser selectByMobile(@Param("mobile")String mobile);
	//修改用户信息包括密码等  
	boolean updateByPrimaryKeySelective(AppUser mAppUser);
	//根据手机号更新用户密码---找回密码
	boolean findPasswordByPhone(@Param("mobile")String mobile,@Param("password")String password);
	//根据手机号查询用户id
	int idByPhone(@Param("mobile")String mobile); 
}
