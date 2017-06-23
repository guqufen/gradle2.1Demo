package net.fnsco.service.dao.master;

import net.fnsco.service.domain.Alias;

public interface AliasDAO {

    int deleteByPrimaryKey(Integer id);

    int insert(Alias record);

    int insertSelective(Alias record);

    Alias selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Alias record);

    int updateByPrimaryKey(Alias record);
}