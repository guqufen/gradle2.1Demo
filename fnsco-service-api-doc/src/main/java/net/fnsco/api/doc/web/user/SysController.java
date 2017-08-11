package net.fnsco.api.doc.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.fnsco.api.doc.service.user.LoginService;
import net.fnsco.api.doc.service.vo.LoginParamInfo;
import net.fnsco.api.doc.service.vo.UserInfo;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;

@Controller
public class SysController extends BaseController {
    @Autowired
    private LoginService loginService;

    /**
     * 进入首页
     * @param rep
     * @param res
     * @return
     */
    @RequestMapping("/idx")
    public String index() {
        Object obj = getSessionUser();
        if (null == obj) {
            Object cookeiUser = getCookieUser();
            if (null == cookeiUser) {
                return "redirect:/login.html";
            }

            String userName = cookeiUser.toString().substring(cookeiUser.toString().lastIndexOf("#") + 1, cookeiUser.toString().length());
            LoginParamInfo loginParamInfo = new LoginParamInfo();
            loginParamInfo.setToken(userName);
            ResultDTO result = loginService.loginByToken(loginParamInfo);
            if (!result.isSuccess()) {
                return "redirect:/login.html";
            }
            UserInfo sysUser = (UserInfo) result.getData();
            setSessionUser(sysUser, sysUser.getToken());
        }
        return "redirect:/index.html";
    }
}
