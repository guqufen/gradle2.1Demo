/**
 * 
 */
package net.fnsco.api.sysuser;

import javax.servlet.http.HttpServletResponse;

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
	ResultDTO<SysUser> doLogin(HttpServletResponse res,String username,String password); 
	
}
