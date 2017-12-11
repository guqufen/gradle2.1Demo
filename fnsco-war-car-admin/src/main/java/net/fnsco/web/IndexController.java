package net.fnsco.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.fnsco.core.base.BaseController;

@Controller
public class IndexController extends BaseController {

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
            return "forward:/web/goLogin";
        }
        return "redirect:/index.html";
    }
}
