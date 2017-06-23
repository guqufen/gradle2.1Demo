package net.fnsco.service.dao.master;

import java.util.List;

import net.fnsco.service.domain.Alias;

public interface AliasDAO {

    int deleteByPrimaryKey(Integer id);

    int insert(Alias record);

    int insertSelective(Alias record);

    Alias selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Alias record);

    int updateByPrimaryKey(Alias record);

    List<Alias> selectByInnerCode(String innerCode);

    List<Alias> selectByRandomCode(String randomCode);
}