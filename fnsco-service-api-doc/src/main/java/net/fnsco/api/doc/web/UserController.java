package net.fnsco.api.doc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.api.doc.service.user.UserServiceIml;
import net.fnsco.core.base.ResultDTO;

@Controller
@RequestMapping(value = "/web/user", method = RequestMethod.POST)
public class UserController {
//    @Autowired
//    private UserServiceIml userServiceIml;
    @RequestMapping("/doLogin")
    @ResponseBody
    public ResultDTO doLogin() {
       
        return null;
    }
}
