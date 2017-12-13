/**
 * 
 */
package net.fnsco.auth.web.sys;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.auth.comm.AuthConstant;
import net.fnsco.auth.service.UserService;
import net.fnsco.auth.service.UserTokenService;
import net.fnsco.auth.service.sys.entity.UserDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.constants.CoreConstants;
import net.fnsco.core.utils.CookieUtils;
import net.fnsco.freamwork.business.WebUserDTO;

/**@desc 后台管理系统登录控制器
 * @author tangliang
 * @date 2017年6月20日 下午3:11:23
 */
@Controller
@RequestMapping(value = "/web")
public class UserLoginAction extends BaseController {

    @Autowired
    private UserService      userService;
    @Autowired
    private UserTokenService userTokenService;

    /** 登录方法
     * @param req
     * @param adminUser
     * @return
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO doLogin() {
        String username = request.getParameter("account");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(username)) {

            return ResultDTO.fail(AuthConstant.WEB_LOGIN_NULL);
        }

        ResultDTO result = userService.doLogin(username, password);
        if (!result.isSuccess()) {
            return ResultDTO.fail(AuthConstant.WEB_LOGIN_FAIL);
        }
        UserDO user = (UserDO) result.getData();
        WebUserDTO webUser = new WebUserDTO();
        webUser.setId(user.getId());
        webUser.setName(user.getName());
        webUser.setType(user.getType());
        webUser.setAgentId(user.getAgentId());

        CookieUtils.addCookie(response, CoreConstants.COOKIE_USER_KEY, user.getName());
        String tokenId = userTokenService.createToken(user.getId());

        webUser.setTokenId(tokenId);
        setSessionUser(webUser, webUser.getId());
        return ResultDTO.success();
    }

    /** 登录方法
     * @param req
     * @param adminUser
     * @return
     */
    @RequestMapping(value = "/goLogin", method = RequestMethod.GET)
    public String goLogin() {
        Object cookeiUser = getCookieUser();
        if (null == cookeiUser) {
            return "redirect:/login.html";
        }

        String userName = cookeiUser.toString().substring(cookeiUser.toString().lastIndexOf("#") + 1, cookeiUser.toString().length());
        UserDO sysUser = userService.getByName(userName);
        if (null == sysUser) {
            return "redirect:/login.html";
        }
        WebUserDTO user = new WebUserDTO();
        user.setId(sysUser.getId());
        user.setName(sysUser.getName());
        String tokenId = userTokenService.createToken(user.getId());
        user.setTokenId(tokenId);
        setSessionUser(user, user.getId());
        return "redirect:/index.html";
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut() {
        removeSessionUser();
        removeCookieUser();
        return "redirect:/login.html";
    }

    /**
     * 获取当前用户
     * @return
     */
    @RequestMapping(value = "/getCurrentUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getCurrentUser() {
        Map<String, Object> result = new HashMap<>();
        WebUserDTO adminUser = (WebUserDTO) getSessionUser();
        result.put("sessionUser", adminUser);
        return result;
    }

    /**
    * 修改密码
    * @return
    */

    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO<String> modifyPassword(String name, String newPassword, String oldPassword) {
        return userService.modifyPassword(name, newPassword, oldPassword);
    }
}
