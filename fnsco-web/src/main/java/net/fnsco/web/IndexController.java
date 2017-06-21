package net.fnsco.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import net.fnsco.core.base.BaseController;

@Controller
public class IndexController extends BaseController{
	
	/**
	 * 进入首页
	 * @param rep
	 * @param res
	 * @return
	 */
    @RequestMapping("/")
    public String index(HttpServletRequest rep,HttpServletResponse res) {
    	
    	Object obj = getSessionUser();
    	if(null == obj)
    	{
    		Object cookeiUser = getCookieUser();
    		if(null == cookeiUser){return "redirect:/login.html";}
    		setSessionUser(obj);
    	}	
        return "redirect:/index.html";
    }
}
