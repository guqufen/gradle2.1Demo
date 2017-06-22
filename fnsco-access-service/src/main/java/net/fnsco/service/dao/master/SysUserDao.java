package net.fnsco.service.dao.master;

import net.fnsco.service.domain.SysUser;

/**
 * @desc 后台系统管理帐号dao
 * @author tangliang
 * @date 2017年6月20日 下午3:00:39
 */
public interface SysUserDao {

    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    /**
     * 根据用户名 密码查询ID
     * @param username
     * @param password
     * @return
     */
    SysUser getIdBy(SysUser record);
}