package net.fnsco.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.auth.service.UserService;
import net.fnsco.auth.service.UserTokenService;
import net.fnsco.auth.service.sys.entity.UserDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.constants.CoreConstants;
import net.fnsco.freamwork.business.WebService;
import net.fnsco.freamwork.business.WebUserDTO;

@Service
public class WebUserServiceImpl extends BaseService implements WebService {
 
    @Autowired
    private UserTokenService userTokenService;
    @Autowired
    private  UserService userService;

    @Override
    public WebUserDTO getCookieUser(HttpServletRequest request) {
        WebUserDTO user = null;
        Object cookeiUser = getUser(request);
        if (null == cookeiUser) {
            return user;
        }
        String userName = cookeiUser.toString().substring(cookeiUser.toString().lastIndexOf("#") + 1, cookeiUser.toString().length());
        UserDO temp = userService.getByName(userName);
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
