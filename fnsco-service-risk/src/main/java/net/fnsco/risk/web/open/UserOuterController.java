package net.fnsco.risk.web.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.risk.comm.RiskConstant;
import net.fnsco.risk.service.sys.WebUserOuterService;
import net.fnsco.risk.service.sys.entity.WebUserOuterDO;

@Controller
@RequestMapping(value = "/web/userOuter", method = RequestMethod.POST)
@Api(value = "/web/userOuter", tags = { "" })
public class UserOuterController extends BaseController {

	 @Autowired
	 private WebUserOuterService userOuterService;

    /**
     * 登录方法
     * 
     * @param req
     * @param adminUser
     * @return
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public ResultDTO doLogin(String account,String password) {
        if (Strings.isNullOrEmpty(password) || Strings.isNullOrEmpty(account)) {
            return ResultDTO.fail(RiskConstant.WEB_LOGIN_NULL);
        }

        ResultDTO result = userOuterService.doLogin(account, password);
        if (result.isSuccess()) {
        	WebUserOuterDO user = (WebUserOuterDO) result.getData();
            setSessionUser(user, user.getId());
            addCookieUser(user.getName());
            return ResultDTO.success();
        }
        return ResultDTO.fail(RiskConstant.WEB_LOGIN_FAIL);
    }

    /**
     * 退出登录
     * 
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut() {
        removeSessionUser();
        removeCookieUser();
        return "redirect:/";
    }

    /**
     * 获取当前用户
     * 
     * @return
     */
    @RequestMapping("/getCurrentUser")
    @ResponseBody
    public ResultDTO getCurrentUser() {
    	WebUserOuterDO adminUser = (WebUserOuterDO) getSessionUser();
        return ResultDTO.success(adminUser);
    }

    /**
     * 修改密码
     * 
     * @return
     */

    @RequestMapping("/modifyPassword")
    @ResponseBody
    public ResultDTO<String> modifyPassword(String name, String newPassword, String oldPassword) {
        if (Strings.isNullOrEmpty(newPassword)) {
            return ResultDTO.fail(RiskConstant.WEB_NEW_PASSWORD_NULL);
        }
        if (Strings.isNullOrEmpty(oldPassword)) {
            return ResultDTO.fail(RiskConstant.WEB_OLD_PASSWORD_NULL);
        }
        return userOuterService.modifyPassword(name, newPassword, oldPassword);
    }
}