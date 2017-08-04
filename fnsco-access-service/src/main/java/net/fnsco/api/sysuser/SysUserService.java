/**
 * 
 */
package net.fnsco.api.sysuser;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.domain.SysUser;

/**@desc service层 adminuser 接口
 * @author tangliang
 * @date 2017年6月20日 下午3:06:26
 */
public interface SysUserService {
	
	/** 后台登录方法
	 * @param req
	 * @param username
	 * @param password
	 * @return
	 */
	ResultDTO<SysUser> doLogin(String username,String password); 
	/** 修改密码方法
     * @param id
     * @param password
     * @param oldPassword
     * @return
     */
	ResultDTO<String> modifyPassword(String name,String newPassword,String oldPassword );
	/**
	 * 
	 * getUserByName:(根据用户名查询用户)
	 *
	 * @param username
	 * @return   SysUser    返回Result对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	SysUser getUserByName(String username); 
}
