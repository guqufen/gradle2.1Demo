package net.fnsco.auth.service.oauth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.auth.comm.AuthConstant;
import net.fnsco.auth.service.sys.dao.MenuDAO;
import net.fnsco.auth.service.sys.dao.UserDAO;
import net.fnsco.auth.service.sys.dao.UserTokenDAO;
import net.fnsco.auth.service.sys.entity.MenuDO;
import net.fnsco.auth.service.sys.entity.UserDO;
import net.fnsco.auth.service.sys.entity.UserTokenDO;

@Service
public class ShiroService {
    @Autowired
    private MenuDAO      sysMenuDao;
    @Autowired
    private UserDAO      sysUserDao;
    @Autowired
    private UserTokenDAO sysUserTokenDao;

    public Set<String> getUserPermissions(Integer userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if (userId == AuthConstant.SUPER_ADMIN) {
            List<MenuDO> menuList = sysMenuDao.queryList(new MenuDO());
            permsList = new ArrayList<>(menuList.size());
            for (MenuDO menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = sysUserDao.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    public UserTokenDO queryByToken(String token) {
        return sysUserTokenDao.queryByToken(token);
    }

    public UserDO queryUser(Integer userId) {
        return sysUserDao.getById(userId);
    }
}
