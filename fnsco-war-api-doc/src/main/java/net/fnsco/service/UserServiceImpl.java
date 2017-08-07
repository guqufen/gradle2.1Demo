package net.fnsco.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.core.constants.CoreConstants;
import net.fnsco.freamwork.business.AppUserDTO;
import net.fnsco.freamwork.business.UserService;

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
public class UserServiceImpl implements UserService {
     
    /**
     * 判断是否强制登录获取用户信息
     * (non-Javadoc)
     * @see net.fnsco.freamwork.business.UserService#getUserInfo(java.lang.String)
     */
    @Override
    public AppUserDTO getUserInfo(String userId) {
        return null;

    }

    @Override
    public AppUserDTO getCookieUser(HttpServletRequest request) {
        AppUserDTO user = null;
        Object cookeiUser = getUser(request);
        if (null == cookeiUser) {
            return user;
        }
        String userName = cookeiUser.toString().substring(cookeiUser.toString().lastIndexOf("#") + 1, cookeiUser.toString().length());
//        UserDO temp = userService.getUserByName(userName);
//        if (temp != null) {
//            user = new AppUserDTO();
//            setSessionUser(request,temp,temp.getId());
//        }
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
