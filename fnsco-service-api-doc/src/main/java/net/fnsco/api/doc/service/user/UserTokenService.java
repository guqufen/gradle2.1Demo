package net.fnsco.api.doc.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.api.doc.service.user.dao.UserTokenDAO;
import net.fnsco.api.doc.service.user.entity.UserTokenDO;
import net.fnsco.core.base.BaseService;

/**
 * 
		* <p>Title: 用户登陆凭证</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年11月30日下午4:22:56</p>
 */
@Service
public class UserTokenService extends BaseService {

    @Autowired
    private UserTokenDAO userTokenDAO;

    public UserTokenDO getByToken(String token) {
        return userTokenDAO.getByToken(token);
    }

    public UserTokenDO getByUserId(Long userId) {
        return userTokenDAO.getByUserId(userId);
    }

    public void update(UserTokenDO userToken) {
        userTokenDAO.update(userToken);
    }

    public void add(UserTokenDO userToken) {
        userTokenDAO.insert(userToken);
    }
}
