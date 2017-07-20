package net.fnsco.service.dao.master;

import net.fnsco.service.domain.SysMsgAppFail;

public interface SysMsgAppFailDao {

    int deleteByPrimaryKey(Integer id);

    int insert(SysMsgAppFail record);

    int insertSelective(SysMsgAppFail record);

    SysMsgAppFail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMsgAppFail record);

    int updateByPrimaryKey(SysMsgAppFail record);
}