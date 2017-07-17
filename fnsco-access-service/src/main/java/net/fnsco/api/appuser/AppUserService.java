package net.fnsco.api.appuser;

import java.util.List;

import net.fnsco.api.dto.AppUserDTO;
import net.fnsco.api.dto.AppUserManageDTO;
import net.fnsco.api.dto.BandDto;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
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
	
	/**
	 * queryAllPushUser:(这里用一句话描述这个方法的作用)查询所有需要推送消息的用户
	 *
	 * @return    设定文件
	 * @return List<AppUser>    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	List<AppUser> queryAllPushUser();
}
