package net.fnsco.order.service.modules.sysuser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.constants.CoreConstants;
import net.fnsco.freamwork.business.AppService;
import net.fnsco.freamwork.business.WebUserDTO;
import net.fnsco.order.api.sysuser.SysUserService;
import net.fnsco.order.service.dao.master.AppUserDao;
import net.fnsco.order.service.domain.AppUser;
import net.fnsco.order.service.domain.SysUser;

@Service
public class UserServiceImpl extends BaseService implements AppService {
    @Autowired
    private AppUserDao     appUserDao;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public net.fnsco.freamwork.business.AppUser1DTO getUserInfo(String userId) {
        net.fnsco.freamwork.business.AppUser1DTO dto = null;
        try {
            AppUser user = appUserDao.selectAppUserById(Integer.parseInt(userId));
            if (user != null) {
                dto = new net.fnsco.freamwork.business.AppUser1DTO();
                dto.setForcedLoginOut(user.getForcedLoginOut());
                dto.setUserName(user.getUserName());
                dto.setId(user.getId());
            }
        } catch (Exception ex) {
            logger.error("获取用户信息出错" + userId, ex);
        }
        return dto;
    }
}
