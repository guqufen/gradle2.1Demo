package net.fnsco.bigdata.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.bigdata.service.domain.Alias;

public interface AliasDAO {

    int deleteByPrimaryKey(Integer id);

    int insert(Alias record);

    int insertSelective(Alias record);

    Alias selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Alias record);

    int updateByPrimaryKey(Alias record);

    List<Alias> selectByInnerCode(@Param("innerCode") String innerCode);

    Alias selectByRandomCode(@Param("randomCode") String randomCode);
}