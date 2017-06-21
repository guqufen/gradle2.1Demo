package net.fnsco.service.dao.cluster;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import net.fnsco.service.domain.AppUser;
@Mapper
public interface AppUserDao {
	//根据手机号查询用户信息是否已经存在
	Integer getByPhoneNum1(@Param("phonenum") String phonenum);
	//向数据库新增一条用户信息记录--注册
	boolean insertAppUser(AppUser appUser);
}
