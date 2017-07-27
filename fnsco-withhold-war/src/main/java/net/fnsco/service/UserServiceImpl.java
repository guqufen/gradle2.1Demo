package net.fnsco.service;

import org.springframework.stereotype.Service;

import net.fnsco.freamwork.business.AppUserDTO;
import net.fnsco.freamwork.business.UserService;

/**
 * 判断是否强制登录获取用户信息
 * @desc 
 * @author   sxf
 * @version  
 * @since    Ver 1.1
 * @Date	 2017年7月27日 上午10:54:30
 *
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * 判断是否强制登录获取用户信息
     * (non-Javadoc)
     * @see net.fnsco.freamwork.business.UserService#getUserInfo(java.lang.String)
     */
    @Override
    public AppUserDTO getUserInfo(String userId) {

        return null;

    }

}
