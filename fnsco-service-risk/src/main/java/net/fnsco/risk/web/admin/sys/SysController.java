package net.fnsco.risk.web.admin.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.fnsco.core.base.BaseController;
import net.fnsco.risk.service.sys.WebUserService;
import net.fnsco.risk.service.sys.entity.WebUserDO;

@Controller
public class SysController extends BaseController {
    @Autowired
    private WebUserService userService;

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
            WebUserDO sysUser = userService.getUserByName(userName);
            if (null == sysUser) {
                return "redirect:/login.html";
            }
            setSessionUser(sysUser, sysUser.getId());
        }
        return "redirect:/index.html";
    }
}
