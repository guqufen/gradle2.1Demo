package net.fnsco.api.doc.service.user;

import org.springframework.beans.factory.annotation.Autowired;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.freamwork.comm.Md5Util;
import net.fnsco.withhold.service.sys.dao.UserDAO;
public class UserServiceIml {
    @Autowired
    private UserDAO userDAO;
    public ResultDTO doLogin(String username, String password) {
       
        return ResultDTO.success();
    }
}
