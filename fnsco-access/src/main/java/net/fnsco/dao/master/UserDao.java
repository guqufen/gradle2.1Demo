package net.fnsco.dao.master;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import net.fnsco.domain.User;

/**
 * 用户 DAO 接口类
 *
 * Created by bysocket on 07/02/2017.
 */
@Mapper
public interface UserDao {

    /**
     * 根据用户名获取用户信息
     *
     * @param userName
     * @return
     */
    User findByName(@Param("userName") String userName);
}
