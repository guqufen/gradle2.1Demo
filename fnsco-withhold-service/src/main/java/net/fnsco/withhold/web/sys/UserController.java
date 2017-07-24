package net.fnsco.withhold.web.sys;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.withhold.comm.ApiConstant;
import net.fnsco.withhold.service.sys.UserService;
import net.fnsco.withhold.service.sys.entity.UserDO;

@Controller
@RequestMapping(value = "/web/user", method = RequestMethod.POST)
@Api(value = "/web/user", tags = { "" })
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /** 登录方法
     * @param req
     * @param adminUser
     * @return
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public ResultDTO doLogin() {
        String username = request.getParameter("account");
        String password = request.getParameter("password");
        if (Strings.isNullOrEmpty(password) || Strings.isNullOrEmpty(username)) {
            return ResultDTO.fail(ApiConstant.WEB_LOGIN_NULL);
        }

        ResultDTO result = userService.doLogin(username, password);
        if (result.isSuccess()) {
            UserDO user = (UserDO) result.getData();
            setSessionUser(user);
            addCookie(ApiConstant.COOKIE_USER_KEY, user.getName());
            return ResultDTO.success();
        }
        return ResultDTO.fail(ApiConstant.WEB_LOGIN_FAIL);
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("/logout")
    public String logOut() {
        removeSessionUser();
        removeCookieUser();
        return "redirect:/";
    }

    /**
     * 获取当前用户
     * @return
     */
    @RequestMapping("/getCurrentUser")
    @ResponseBody
    public ResultDTO getCurrentUser() {
        UserDO adminUser = (UserDO) getSessionUser();
        if (null == adminUser) {
            //String cookie = (String) getCookieUser();
            //if (Strings.isNullOrEmpty(cookie)){
            return ResultDTO.fail(ApiConstant.WEB_NOT_LOGIN);
        }
        return ResultDTO.success(adminUser);
    }

    /**
    * 修改密码
    * @return
    */

    @RequestMapping("/modifyPassword")
    @ResponseBody
    public ResultDTO<String> modifyPassword(String name, String newPassword, String oldPassword) {
        if (Strings.isNullOrEmpty(newPassword)) {
            return ResultDTO.fail(ApiConstant.WEB_NEW_PASSWORD_NULL);
        }
        if (Strings.isNullOrEmpty(oldPassword)) {
            return ResultDTO.fail(ApiConstant.WEB_OLD_PASSWORD_NULL);
        }
        return userService.modifyPassword(name, newPassword, oldPassword);
    }
}