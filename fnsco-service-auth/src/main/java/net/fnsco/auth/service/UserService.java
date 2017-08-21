package net.fnsco.auth.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import net.fnsco.auth.service.sys.dao.UserDAO;
import net.fnsco.auth.service.sys.dao.UserRoleDAO;
import net.fnsco.auth.service.sys.entity.UserDO;
import net.fnsco.auth.service.sys.entity.UserRoleDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.freamwork.comm.Md5Util;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class UserService extends BaseService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserDAO     userDAO;
    @Autowired
    private UserRoleDAO userRoleDAO;

    public ResultDTO<UserDO> doLogin(String userName, String password) {
        String pwd = Md5Util.getInstance().md5(password);
        UserDO auser = userDAO.getByUserName(userName, pwd);
        if (auser == null) {
            return ResultDTO.fail();
        }
        // 写到缓存
        return ResultDTO.success(auser);
    }

    public UserDO getByName(String name) {
        UserDO sysUser = userDAO.getByName(name);
        return sysUser;
    }

    public ResultDTO<String> modifyPassword(String name, String newPassword, String oldPassword) {
        ResultDTO<String> result = new ResultDTO<String>();
        //非空判断
        if (Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(newPassword) || Strings.isNullOrEmpty(oldPassword)) {
            return result.fail();
        }
        String oldPwd = Md5Util.getInstance().md5(oldPassword);
        String newPwd = Md5Util.getInstance().md5(newPassword);
        //判断原密码是否正确 
        UserDO sysUser = userDAO.getByName(name);
        if (null == sysUser) {
            return ResultDTO.fail("用户名不存在");
        }
        if (!oldPwd.equals(sysUser.getPassword())) {
            return result.fail("原密码不正确");
        }
        UserDO adminUser = new UserDO();
        adminUser.setPassword(newPwd);
        adminUser.setId(sysUser.getId());
        int num = userDAO.update(adminUser);
        if (num == 0) {
            return result.fail();
        }
        return result.success();
    }

    // 分页
    public ResultPageDTO<UserDO> queryList(UserDO user, Integer pageNum, Integer pageSize) {
        List<UserDO> data = userDAO.pageList(user, pageNum, pageSize);
        for (UserDO time : data) {
            Date li = time.getModifyTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(li);
            time.setModifyTimeStr(dateString);
        }
        //返回根据条件查询的所有记录条数
        int totalNum = userDAO.pageListCount(user);
        //返回给页面总条数和每页查询的数据
        ResultPageDTO<UserDO> result = new ResultPageDTO<UserDO>(totalNum, data);
        return result;
    }
    /**
     * 通过id删除用户（改变状态0）
     * @param id
     * @return
     */
    public ResultDTO<String> deleteById(Integer[] id) {
        for (int i = 0; i < id.length; i++) {
            int res = userDAO.updateById(id[i]);
        }
        return ResultDTO.success();
    }

    /**
     * 添加用户信息
     * @param dept
     * @return
     */
    @Transactional
    public void doAddUser(UserDO user) {
    	String psw=Md5Util.getInstance().md5(user.getPassword());
    	user.setPassword(psw);
        user.setModifyTime(new Date());
        userDAO.insert(user);
        List<Integer> list = user.getRoleList();
        UserRoleDO userRole = new UserRoleDO();
        userRole.setUserId(user.getId());
        if(list!=null) {
        	for (Integer roleId : list) {
        		userRole.setRoleId(roleId);
        		userRoleDAO.insert(userRole);
        	}
        }
    }

    /**
     * 修改内的用户信息查询
     * @param id
     * @return
     */
    public UserDO queryUserById(Integer id) {
        UserDO user = userDAO.getById(id);
        List<Integer> list = userRoleDAO.getByUserId(id);
        user.setRoleList(list);
        return user;
    }

    /**
     *通过用户名查询是否存在该用户
     * @param id
     * @return
     */
    public boolean queryUserByName(String name) {
        UserDO userDO = userDAO.getByName(name);
        if (userDO != null) {
            return false;
        }
        return true;
    }

    /**
     * 修改用户信息
     * @param dept
     * @return
     */
    @Transactional
    public ResultDTO<String> toEditDept(UserDO user) {
        List<Integer> list = user.getRoleList();
        UserRoleDO userRole = new UserRoleDO();
        Integer userId = user.getId();
        userRoleDAO.deleteByUserId(userId);
        userRole.setUserId(userId);
        if(list!=null) {
        for (Integer roleId : list) {
            userRole.setRoleId(roleId);
            userRoleDAO.insert(userRole);
        }
        }
        user.setModifyTime(new Date());
        int res = userDAO.update(user);
        if (res <1) {
            return ResultDTO.fail();
        }
        return ResultDTO.success();
    }
}
