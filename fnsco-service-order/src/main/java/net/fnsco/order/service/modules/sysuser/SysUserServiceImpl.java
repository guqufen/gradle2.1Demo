/**
 * 
 */
package net.fnsco.order.service.modules.sysuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.freamwork.comm.Md5Util;
import net.fnsco.order.api.sysuser.SysUserService;
import net.fnsco.order.service.dao.master.SysUserDao;
import net.fnsco.order.service.domain.SysUser;

/**@desc AdminUserService实现类
 * @author tangliang
 * @date 2017年6月20日 下午3:07:46
 */
@Service
public class SysUserServiceImpl extends BaseService implements SysUserService {

    @Autowired
    private SysUserDao adminUserDao;

    @Override
    public ResultDTO<SysUser> doLogin(String username, String password) {

        ResultDTO<SysUser> result = new ResultDTO<SysUser>();

        String pwd = Md5Util.getInstance().md5(password);
        SysUser adminUser = new SysUser();
        adminUser.setName(username);
        adminUser.setPassword(pwd);
        SysUser auser = adminUserDao.getIdBy(adminUser);
        if (auser == null) {
            return result.fail();
        }
        // 写到缓存
        return result.success(auser);
    }

    //修改密码
    @Override
    public ResultDTO<String> modifyPassword(String name, String newPassword, String oldPassword) {
        ResultDTO<String> result = new ResultDTO<String>();
        //非空判断
        if (Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(newPassword) || Strings.isNullOrEmpty(oldPassword)) {
            return result.fail();
        }
        String oldPwd = Md5Util.getInstance().md5(oldPassword);
        String newPwd = Md5Util.getInstance().md5(newPassword);
        //判断原密码是否正确 
        SysUser sysUser = adminUserDao.getUserByName(name);
        if (null == sysUser) {
            return ResultDTO.fail("用户名不存在");
        }
        if (!oldPwd.equals(sysUser.getPassword())) {
            return result.fail("原密码不正确");
        }
        SysUser adminUser = new SysUser();
        adminUser.setPassword(newPwd);
        adminUser.setId(sysUser.getId());
        int num = adminUserDao.updateByPrimaryKeySelective(adminUser);
        if (num == 0) {
            return result.fail();
        }
        return result.success();
    }

    //修改密码
    @Override
    public SysUser getUserByName(String username) {
        SysUser sysUser = adminUserDao.getUserByName(username);
        return sysUser;
    }
}
