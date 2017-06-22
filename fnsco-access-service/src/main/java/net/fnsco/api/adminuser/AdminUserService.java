/**
 * 
 */
package net.fnsco.api.adminuser;

import javax.servlet.http.HttpServletResponse;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.domain.AdminUser;

/**@desc service层 adminuser 接口
 * @author tangliang
 * @date 2017年6月20日 下午3:06:26
 */
public interface AdminUserService {
	
	/** 后台登录方法
	 * @param req
	 * @param username
	 * @param password
	 * @return
	 */
	ResultDTO<AdminUser> doLogin(HttpServletResponse res,String username,String password); 
	
}
