package net.fnsco.controller.access;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.fnsco.core.base.BaseController;
import net.fnsco.service.domain.User;
@RestController
@RequestMapping(value = "/app/lkl", method = RequestMethod.POST)
public class LklAccessController extends BaseController{
    /**
     * 根据用户名获取用户信息，包括从库的地址信息
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/saveTransaction", method = RequestMethod.GET)
    public User findByName(@RequestParam(value = "userName", required = true) String userName) {
        return null;
    }
}
