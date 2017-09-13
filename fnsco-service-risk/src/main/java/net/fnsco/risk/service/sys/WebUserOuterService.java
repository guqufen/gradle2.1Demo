package net.fnsco.risk.service.sys;

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
import net.fnsco.risk.comm.RiskConstant;
import net.fnsco.risk.service.sys.dao.WebUserDAO;
import net.fnsco.risk.service.sys.dao.WebUserOuterDAO;
import net.fnsco.risk.service.sys.entity.WebUserOuterDO;

@Service
public class WebUserOuterService extends BaseService {

    private Logger  logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WebUserOuterDAO userOuterDAO;

 /*   public ResultDTO doLogin(String username, String password) {
        String pwd = Md5Util.getInstance().md5(password);
        WebUserOuterDO user = userOuterDAO.getByUserName(username);
        if (user == null) {
            return ResultDTO.fail(RiskConstant.WEB_LOGIN_USER_NOT_EXIST);
        }
        String dbPassword = user.getPassword();
        if (!dbPassword.equals(pwd)) {
            return ResultDTO.fail(RiskConstant.WEB_LOGIN_PASSWORD_FAIL);
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
        //通过真实姓名判断原密码是否正确 
        WebUserOuterDO user = userOuterDAO.getByRealName(name);
        if (null == user) {
            return ResultDTO.fail(RiskConstant.WEB_LOGIN_USER_NOT_EXIST);
        }

        if (!oldPwd.equals(user.getPassword())) {
            return ResultDTO.fail(RiskConstant.WEB_OLD_PASSWORD_FAIL);
        }
        WebUserOuterDO uUser = new WebUserOuterDO();
        uUser.setPassword(newPwd);
        uUser.setId(user.getId());
        int num = userOuterDAO.update(uUser);
        if (num == 0) {
            return ResultDTO.fail();
        }
        return ResultDTO.success();
    }*/

    // 分页
    public ResultPageDTO<WebUserOuterDO> page(WebUserOuterDO user, Integer pageNum, Integer pageSize) {
        logger.info("开始分页查询UserService.page, user=" + user.toString());
        List<WebUserOuterDO> pageList = this.userOuterDAO.pageList(user, pageNum, pageSize);
        Integer count = this.userOuterDAO.pageListCount(user);
        ResultPageDTO<WebUserOuterDO> pager = new ResultPageDTO<WebUserOuterDO>(count, pageList);
        return pager;
    }

    // 添加
    public WebUserOuterDO doAdd(WebUserOuterDO user, int loginUserId) {
        logger.info("开始添加UserService.add,user=" + user.toString());
        this.userOuterDAO.insert(user);
        return user;
    }

    // 修改
    public Integer doUpdate(WebUserOuterDO user, Integer loginUserId) {
        logger.info("开始修改UserService.update,user=" + user.toString());
        int rows = this.userOuterDAO.update(user);
        return rows;
    }

    // 删除
    public ResultDTO<String> doDelete(WebUserOuterDO user, Integer loginUserId) {
        logger.info("开始删除UserService.delete,user=" + user.toString());
        int rows = this.userOuterDAO.updateById(user.getId());
        return ResultDTO.success();
    }

    // 查询
    public WebUserOuterDO doQueryById(Integer id) {
    	WebUserOuterDO obj = this.userOuterDAO.getById(id);
        return obj;
    }

    // 查询
   /* public WebUserOuterDO getUserByName(String userName) {
    	WebUserOuterDO obj = this.userOuterDAO.getByUserName(userName);
        return obj;
    }*/

}