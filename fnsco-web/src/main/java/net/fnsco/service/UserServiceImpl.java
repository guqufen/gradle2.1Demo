package net.fnsco.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.freamwork.business.AppUserDTO;
import net.fnsco.freamwork.business.UserService;
import net.fnsco.service.dao.master.AppUserDao;
import net.fnsco.service.domain.AppUser;

@Service
public class UserServiceImpl extends BaseService implements UserService {
    @Autowired
    private AppUserDao  appUserDao;

    @Override
    public net.fnsco.freamwork.business.AppUserDTO getUserInfo(String userId) {
        net.fnsco.freamwork.business.AppUserDTO dto = null;
        try {
            AppUser user = appUserDao.selectAppUserById(Integer.parseInt(userId));
            if (user != null) {
                dto = new net.fnsco.freamwork.business.AppUserDTO();
                dto.setForcedLoginOut(user.getForcedLoginOut());
                dto.setUserName(user.getUserName());
                dto.setId(user.getId());
            }
        } catch (Exception ex) {
            logger.error("获取用户信息出错" + userId, ex);
        }
        return dto;
    }

    @Override
    public AppUserDTO getCookieUser(HttpServletRequest request) {

        // TODO Auto-generated method stub
        return null;

    }
}
