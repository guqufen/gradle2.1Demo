package net.fnsco.service.dao.master;

import net.fnsco.service.domain.Version;

public interface SysVersionDao {

    int deleteByPrimaryKey(Integer id);

    int insert(Version record);

    int insertSelective(Version record);

    Version selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Version record);

    int updateByPrimaryKey(Version record);
    
    Version selectSysVersion(String appCode ,String appType);
}