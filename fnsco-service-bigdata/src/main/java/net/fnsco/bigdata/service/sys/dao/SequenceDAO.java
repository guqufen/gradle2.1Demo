package net.fnsco.bigdata.service.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.fnsco.bigdata.service.sys.dao.helper.SequenceProvider;
import net.fnsco.bigdata.service.sys.entity.SequenceDO;;

public interface SequenceDAO {

    @Results({ @Result(column = "table_name", property = "tableName"), @Result(column = "current_value", property = "currentValue"), @Result(column = "next_value", property = "nextValue") })
    @Select("SELECT * FROM sys_sequence WHERE id = #{id}")
    public SequenceDO getById(@Param("id") int id);

    @Results({ @Result(column = "table_name", property = "tableName"), @Result(column = "current_value", property = "currentValue"), @Result(column = "next_value", property = "nextValue") })
    @Select("SELECT * FROM sys_sequence WHERE table_name = 't_trade_order'")
    public SequenceDO getTradeOrder();
    
    @Select("select gen_id('t_trade_order')")
    public int getOrderSequence();

    @Insert("INSERT into sys_sequence(id,table_name,current_value,next_value,step) VALUES (#{id},#{tableName},#{currentValue},#{nextValue},#{step})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(SequenceDO sequence);

    @Delete("DELETE FROM sys_sequence WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = SequenceProvider.class, method = "update")
    public int update(@Param("sequence") SequenceDO sequence);

    @Results({ @Result(column = "table_name", property = "tableName"), @Result(column = "current_value", property = "currentValue"), @Result(column = "next_value", property = "nextValue") })
    @SelectProvider(type = SequenceProvider.class, method = "pageList")
    public List<SequenceDO> pageList(@Param("sequence") SequenceDO sequence, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = SequenceProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("sequence") SequenceDO sequence);

}