package net.fnsco.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.service.domain.AppUser;

public interface AppUserDao {
	//新增注册信息
    boolean insertSelective(AppUser appUser);
	//根据用户手机号查询用户实体
	AppUser selectAppUserByMobile(@Param("mobile")String mobile);
	//根据id查询用户实体
	AppUser selectAppUserById(@Param("id")Integer id);
	//修改用户信息包括密码等  
	boolean updateByPrimaryKeySelective(AppUser appUser);
	//根据手机号找回密码
	boolean updatePasswordByPhone(@Param("mobile")String mobile,@Param("password")String password);
	//根据用户手机号和状态查询用户实体
	AppUser selectAppUserByMobileAndState(@Param("mobile")String mobile,@Param("state")Integer state);
	/**
	 * selectByInnerCode:(这里用一句话描述这个方法的作用)根据innerCode查询
	 *
	 * @param innerCode
	 * @return    设定文件
	 * @return List<AppUser>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	List<AppUser> selectByInnerCode(@Param("innerCode")String innerCode);
}
