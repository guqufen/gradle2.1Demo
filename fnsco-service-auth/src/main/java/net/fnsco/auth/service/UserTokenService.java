package net.fnsco.auth.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.auth.service.oauth.TokenGenerator;
import net.fnsco.auth.service.sys.dao.UserTokenDAO;
import net.fnsco.auth.service.sys.entity.UserTokenDO;
import net.fnsco.core.base.BaseService;

@Service
public class UserTokenService extends BaseService {
    @Autowired
    private UserTokenDAO     sysUserTokenDao;
    //12小时后过期
    private final static int EXPIRE = 3600 * 12 * 7;

    public UserTokenDO queryByUserId(Integer userId) {
        return sysUserTokenDao.queryByUserId(userId);
    }

    public UserTokenDO queryByToken(String token) {
        return sysUserTokenDao.queryByToken(token);
    }

    public void save(UserTokenDO token) {
        sysUserTokenDao.insert(token);
    }

    public void update(UserTokenDO token) {
        sysUserTokenDao.update(token);
    }

    public String createToken(Integer userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        UserTokenDO tokenEntity = queryByUserId(userId);
        if (tokenEntity == null) {
            tokenEntity = new UserTokenDO();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            save(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            update(tokenEntity);
        }
        return token;
    }
}
