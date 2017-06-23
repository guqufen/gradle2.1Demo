package net.fnsco.service.dao.cluster;

import net.fnsco.service.domain.AppUser;

public interface AppUserDao {
	//新增注册信息
	boolean insertSelective(AppUser mAppUser);
	//根据用户手机号查询用户之前是否已经注册
	int selectByPrimaryKey(String mobile);
	//根据用户手机号查询用户实体
	AppUser selectByMobile(String mobile);
	//修改用户信息包括密码等  
	boolean updateByPrimaryKeySelective(AppUser mAppUser);
}
