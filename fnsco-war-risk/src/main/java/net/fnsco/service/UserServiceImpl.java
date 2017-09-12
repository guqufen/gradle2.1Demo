package net.fnsco.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.core.constants.CoreConstants;
import net.fnsco.freamwork.business.AppService;
import net.fnsco.freamwork.business.AppUser1DTO;
import net.fnsco.freamwork.business.WebService;
import net.fnsco.freamwork.business.WebUserDTO;
import net.fnsco.risk.service.sys.WebUserService;
import net.fnsco.risk.service.sys.entity.WebUserDO;

/**
 * 判断是否强制登录获取用户信息
 * @desc 
 * @author   sxf
 * @version  
 * @since    Ver 1.1
 * @Date	 2017年7月27日 上午10:54:30
 *
 */
@Service
public class UserServiceImpl implements WebService, AppService {
    @Autowired
    private WebUserService userService;

    /**
     * 判断是否强制登录获取用户信息
     * (non-Javadoc)
     * @see net.fnsco.freamwork.business.UserService#getUserInfo(java.lang.String)
     */
    @Override
    public AppUser1DTO getUserInfo(String userId) {
        return null;

    }

    @Override
    public WebUserDTO getCookieUser(HttpServletRequest request) {
        WebUserDTO user = null;
        Object cookeiUser = getUser(request);
        if (null == cookeiUser) {
            return user;
        }
        String userName = cookeiUser.toString().substring(cookeiUser.toString().lastIndexOf("#") + 1, cookeiUser.toString().length());
        WebUserDO temp = userService.getUserByName(userName);
        if (temp != null) {
            user = new WebUserDTO();
            setSessionUser(request, temp, temp.getId());
        }
        return user;
    }

    public void setSessionUser(HttpServletRequest request, Object userDO, Integer userId) {
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
