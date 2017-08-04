package net.fnsco.order.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.core.base.PageDTO;
import net.fnsco.order.api.dto.AppUserManageDTO;
import net.fnsco.order.api.dto.AppUserMerchantDTO;
import net.fnsco.order.api.dto.QueryBandDTO;
import net.fnsco.order.service.domain.AppUser;

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
	boolean updateDeviceToken(@Param("mobile")String mobile,@Param("deviceToken")String deviceToken,@Param("deviceId")String deviceId,@Param("deviceType")Integer deviceType);
	boolean updateByPrimaryKey(AppUser appUser);
	boolean updateDeviceTokenById(@Param("id")Integer id,@Param("deviceToken")String deviceToken,@Param("deviceId")String deviceId,@Param("deviceType")Integer deviceType);
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
	
	/**
	 * queryPageList:(这里用一句话描述这个方法的作用)条件分页查询
	 *
	 * @param pages
	 * @return    设定文件
	 * @return List<AppUserManageDTO>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
    List<AppUserManageDTO> queryPageList(PageDTO<AppUserManageDTO> pages);
    
    /**
     * 条件查询总数
     * @param pages
     * @return
     */
    int queryTotalByCondition(AppUserManageDTO record);
    List<QueryBandDTO> selectBandPeopleByMobile(@Param("mobile")String mobile);
    
    /**
     * queryAllPushUser:(这里用一句话描述这个方法的作用)查询所有需要推送消息的用户
     *
     * @return    设定文件
     * @return List<AppUser>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<AppUser> queryAllPushUser();
    List<AppUser> queryBydeviceToken(@Param("deviceToken")String deviceToken);
}
