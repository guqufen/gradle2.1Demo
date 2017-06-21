package net.fnsco.service.dao.master;

import net.fnsco.service.domain.AdminUser;

/**
 * @desc 后台系统管理帐号dao
 * @author tangliang
 * @date 2017年6月20日 下午3:00:39
 */
public interface AdminUserDao {

    int deleteByPrimaryKey(Integer id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);
    /**
     * 根据用户名 密码查询ID
     * @param username
     * @param password
     * @return
     */
    AdminUser getIdBy(AdminUser record);
}