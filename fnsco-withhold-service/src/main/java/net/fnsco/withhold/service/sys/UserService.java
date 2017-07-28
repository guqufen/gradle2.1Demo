package net.fnsco.withhold.service.sys;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.freamwork.comm.Md5Util;
import net.fnsco.withhold.comm.ApiConstant;
import net.fnsco.withhold.service.sys.dao.UserDAO;
import net.fnsco.withhold.service.sys.entity.UserDO;

@Service
public class UserService extends BaseService {

    private Logger  logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserDAO userDAO;

    public ResultDTO doLogin(String username, String password) {
        String pwd = Md5Util.getInstance().md5(password);
        UserDO user = userDAO.getByUserName(username);
        if (user == null) {
            return ResultDTO.fail(ApiConstant.WEB_LOGIN_USER_NOT_EXIST);
        }
        String dbPassword = user.getPassword();
        if (!dbPassword.equals(pwd)) {
            return ResultDTO.fail(ApiConstant.WEB_LOGIN_PASSWORD_FAIL);
        }
        return ResultDTO.success(user);
    }

    //修改密码
    public ResultDTO modifyPassword(String name, String newPassword, String oldPassword) {
        //非空判断
        if (Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(newPassword) || Strings.isNullOrEmpty(oldPassword)) {
            return ResultDTO.fail();
        }
        String oldPwd = Md5Util.getInstance().md5(oldPassword);
        String newPwd = Md5Util.getInstance().md5(newPassword);
        //判断原密码是否正确 
        UserDO user = userDAO.getByUserName(name);
        if (null == user) {
            return ResultDTO.fail(ApiConstant.WEB_LOGIN_USER_NOT_EXIST);
        }
        if (!oldPwd.equals(user.getPassword())) {
            return ResultDTO.fail(ApiConstant.WEB_OLD_PASSWORD_FAIL);
        }
        UserDO uUser = new UserDO();
        uUser.setPassword(newPwd);
        uUser.setId(user.getId());
        int num = userDAO.update(uUser);
        if (num == 0) {
            return ResultDTO.fail();
        }
        return ResultDTO.success();
    }

    // 分页
    public ResultPageDTO<UserDO> page(UserDO user, Integer pageNum, Integer pageSize) {
        logger.info("开始分页查询UserService.page, user=" + user.toString());
        List<UserDO> pageList = this.userDAO.pageList(user, pageNum, pageSize);
        Integer count = this.userDAO.pageListCount(user);
        ResultPageDTO<UserDO> pager = new ResultPageDTO<UserDO>(count, pageList);
        return pager;
    }

    // 添加
    public UserDO doAdd(UserDO user, int loginUserId) {
        logger.info("开始添加UserService.add,user=" + user.toString());
        this.userDAO.insert(user);
        return user;
    }

    // 修改
    public Integer doUpdate(UserDO user, Integer loginUserId) {
        logger.info("开始修改UserService.update,user=" + user.toString());
        int rows = this.userDAO.update(user);
        return rows;
    }

    // 删除
    public Integer doDelete(UserDO user, Integer loginUserId) {
        logger.info("开始删除UserService.delete,user=" + user.toString());
        int rows = this.userDAO.deleteById(user.getId());
        return rows;
    }

    // 查询
    public UserDO doQueryById(Integer id) {
        UserDO obj = this.userDAO.getById(id);
        return obj;
    }

    // 查询
    public UserDO getUserByName(String userName) {
        UserDO obj = this.userDAO.getByUserName(userName);
        return obj;
    }
}