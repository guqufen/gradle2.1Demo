package net.fnsco.web.controller.sysuser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.fnsco.core.base.BaseController;

@Controller
public class SysController extends BaseController {
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
            return "forward:/web/goLogin";
        }
        return "redirect:/index.html";
    }
}
