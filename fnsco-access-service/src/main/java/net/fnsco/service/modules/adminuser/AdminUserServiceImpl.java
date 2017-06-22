/**
 * 
 */
package net.fnsco.service.modules.adminuser;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.api.sysuser.SysUserService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.Md5Util;
import net.fnsco.service.dao.master.SysUserDao;
import net.fnsco.service.domain.SysUser;

/**@desc AdminUserService实现类
 * @author tangliang
 * @date 2017年6月20日 下午3:07:46
 */
@Service
public class AdminUserServiceImpl extends BaseService implements SysUserService {
	
	@Autowired
	private SysUserDao adminUserDao;
	
	
	@Override
	public ResultDTO<SysUser> doLogin(HttpServletResponse res, String username, String password) {
		
		ResultDTO<SysUser> result = new ResultDTO<SysUser>();

		String pwd=Md5Util.getInstance().md5(password);
		SysUser adminUser = new SysUser();
		adminUser.setName(username);
		adminUser.setPassword(pwd);
		SysUser auser = adminUserDao.getIdBy(adminUser);
		if (auser == null) {
			return result.setError("用户名或者密码不正确");
		}
		result.setData(auser);
		// 写到缓存
		return result.setSuccess("登录成功");
	}

}
