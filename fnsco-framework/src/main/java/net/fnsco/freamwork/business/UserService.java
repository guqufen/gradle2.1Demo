package net.fnsco.freamwork.business;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @desc 
 * @author   sxf
 * @version  
 * @since    Ver 1.1
 * @Date	 2017年7月19日 上午9:53:18
 *
 */
public interface UserService {
    /**
     * 
     * getUserInfo:(根据用户id获取app用户信息，用于强制退出)
     *
     * @param userId
     * @return   AppUserDTO    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    AppUserDTO getUserInfo(String userId);
    
    /**
     * 
     * getUserInfo:(根据用户id获取app用户信息，用于强制退出)
     *
     * @param userId
     * @return   AppUserDTO    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    WebUserDTO getCookieUser(HttpServletRequest request);
}
