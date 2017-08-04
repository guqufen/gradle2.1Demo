package net.fnsco.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.fnsco.core.base.BaseController;
import net.fnsco.service.dao.master.SysUserDao;
import net.fnsco.service.domain.SysUser;

@Controller
public class IndexController extends BaseController {

    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 进入首页
     * @param rep
     * @param res
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest rep, HttpServletResponse res) {

        Object obj = getSessionUser();
        if (null == obj) {
            Object cookeiUser = getCookieUser();
            if (null == cookeiUser) {
                return "redirect:/login.html";
            }

            String userName = cookeiUser.toString().substring(cookeiUser.toString().lastIndexOf("#") + 1, cookeiUser.toString().length());
            SysUser sysUser = sysUserDao.getUserByName(userName);
            setSessionUser(sysUser,sysUser.getId());
        }
        return "redirect:/index.html";
    }
}
