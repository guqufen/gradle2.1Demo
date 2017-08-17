package net.fnsco.order.service.modules.sysuser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.auth.service.UserTokenService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.constants.CoreConstants;
import net.fnsco.freamwork.business.UserService;
import net.fnsco.freamwork.business.WebUserDTO;
import net.fnsco.order.api.sysuser.SysUserService;
import net.fnsco.order.service.dao.master.AppUserDao;
import net.fnsco.order.service.domain.AppUser;
import net.fnsco.order.service.domain.SysUser;

@Service
public class UserServiceImpl extends BaseService implements UserService {
    @Autowired
    private AppUserDao appUserDao;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private UserTokenService userTokenService;
    @Override 
    public net.fnsco.freamwork.business.AppUserDTO getUserInfo(String userId) {
        net.fnsco.freamwork.business.AppUserDTO dto = null;
        try {
            AppUser user = appUserDao.selectAppUserById(Integer.parseInt(userId));
            if (user != null) {
                dto = new net.fnsco.freamwork.business.AppUserDTO();
                dto.setForcedLoginOut(user.getForcedLoginOut());
                dto.setUserName(user.getUserName());
                dto.setId(user.getId());
            }
        } catch (Exception ex) {
            logger.error("获取用户信息出错" + userId, ex);
        }
        return dto;
    }

    @Override
    public WebUserDTO getCookieUser(HttpServletRequest request) {
        WebUserDTO user = null;
        Object cookeiUser = getUser(request);
        if (null == cookeiUser) {
            return user;
        }
        String userName = cookeiUser.toString().substring(cookeiUser.toString().lastIndexOf("#") + 1, cookeiUser.toString().length());
        SysUser temp = sysUserService.getUserByName(userName);
        if (temp != null) {
            user = new WebUserDTO();
            user.setId(temp.getId());
            user.setName(temp.getName());
            String tokenId = userTokenService.createToken(user.getId());
            user.setTokenId(tokenId);
            setSessionUser(request, user, user.getId());
        }
        return user;
    }

    private void setSessionUser(HttpServletRequest request, Object userDO, Integer userId) {
        HttpSession session = request.getSession();
        session.setAttribute(CoreConstants.SESSION_USER_KEY, userDO);
        session.setAttribute(CoreConstants.SESSION_USERID, userId);
    }

    private String getUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if (!Strings.isNullOrEmpty(name) && name.equalsIgnoreCase(CoreConstants.COOKIE_USER_KEY)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
