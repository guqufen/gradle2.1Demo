package net.fnsco.order.api.appuser;

import java.util.List;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.freamwork.business.UserService;
import net.fnsco.order.api.dto.AppUserDTO;
import net.fnsco.order.api.dto.AppUserManageDTO;
import net.fnsco.order.api.dto.AppUserMerchantDTO;
import net.fnsco.order.api.dto.BandDto;
import net.fnsco.order.service.domain.AppUser;
import net.fnsco.order.service.domain.AppUserMerchant;

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

	/**
	 * queryPageList:(这里用一句话描述这个方法的作用) 条件分页查询
	 *
	 * @param record
	 * @param currentPageNum
	 * @param perPageSize
	 * @return    设定文件
	 * @return ResultPageDTO<AppUserManageDTO>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	ResultPageDTO<AppUserManageDTO> queryPageList(AppUserManageDTO record,int currentPageNum, int perPageSize);
	
	ResultDTO<String> modifyRole(BandDto bandDto);
	
    ResultDTO<String> changeRole(List<AppUserMerchantDTO> params);
	/**
	 * queryAllPushUser:(这里用一句话描述这个方法的作用)查询所有需要推送消息的用户
	 *
	 * @return    设定文件
	 * @return List<AppUser>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */ 
	List<AppUser> queryAllPushUser();
	
	//根据id查询用户实体
    AppUser selectAppUserById(Integer id);
    
    
    ResultDTO<String> judgeRoles(List<AppUserMerchantDTO> params);
    
    void addAppUserMerchantRole(AppUserMerchant dto);
    
    /**
     * modifyInfo:(这里用一句话描述这个方法的作用)修改用户信息接口
     *
     * @param appUserDTO
     * @return    设定文件
     * @return ResultDTO<String>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    ResultDTO<String> modifyInfo(AppUserDTO appUserDTO);
    ResultDTO<String> getUserInfo(AppUserDTO appUserDTO);
}