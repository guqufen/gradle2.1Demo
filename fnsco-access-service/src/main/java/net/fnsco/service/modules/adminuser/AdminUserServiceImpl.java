/**
 * 
 */
package net.fnsco.service.modules.adminuser;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.api.adminuser.AdminUserService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.Md5Util;
import net.fnsco.service.dao.master.AdminUserDao;
import net.fnsco.service.domain.AdminUser;

/**@desc AdminUserService实现类
 * @author tangliang
 * @date 2017年6月20日 下午3:07:46
 */
@Service
public class AdminUserServiceImpl extends BaseService implements AdminUserService {
	
	@Autowired
	private AdminUserDao adminUserDao;
	
	
	@Override
	public ResultDTO<AdminUser> doLogin(HttpServletResponse res, String username, String password) {
		
		ResultDTO<AdminUser> result = new ResultDTO<AdminUser>();

		String pwd=Md5Util.getInstance().md5(password);
		AdminUser adminUser = new AdminUser();
		adminUser.setName(username);
		adminUser.setPassword(pwd);
		AdminUser auser = adminUserDao.getIdBy(adminUser);
		if (auser == null) {
			return result.setError("用户名或者密码不正确");
		}
		result.setData(auser);
		// 写到缓存
		return result.setSuccess("登录成功");
	}

}
