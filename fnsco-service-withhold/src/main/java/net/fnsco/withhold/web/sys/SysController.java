package net.fnsco.withhold.web.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.fnsco.core.base.BaseController;
import net.fnsco.withhold.service.sys.UserService;
import net.fnsco.withhold.service.sys.entity.UserDO;

@Controller
public class SysController extends BaseController {
    @Autowired
    private UserService userService;

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
            UserDO sysUser = userService.getUserByName(userName);
            if (null == sysUser) {
                return "redirect:/login.html";
            }
            setSessionUser(sysUser, sysUser.getId());
        }
        return "redirect:/index.html";
    }
}
