package net.fnsco.risk.service.sys.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.risk.service.sys.entity.ConfigDO;
import net.fnsco.risk.service.sys.dao.helper.ConfigProvider;

import java.util.List;;

public interface ConfigDAO {

    @Results({@Result( column = "group_name",property = "groupName") })
    @Select("SELECT * FROM sys_config WHERE id = #{id}")
    public ConfigDO getById(@Param("id") int id);

    @Insert("INSERT into sys_config(id,type,group_name,name,value,status,remark) VALUES (#{id},#{type},#{groupName},#{name},#{value},#{status},#{remark})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(ConfigDO config);

    @Delete("DELETE FROM sys_config WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = ConfigProvider.class, method = "update")
    public int update(@Param("config") ConfigDO  config);

    @Results({@Result( column = "group_name",property = "groupName") })
    @SelectProvider(type = ConfigProvider.class, method = "pageList")
    public List<ConfigDO> pageList(@Param("config") ConfigDO config, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = ConfigProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("config") ConfigDO config);

    @Results({@Result( column = "value",property = "value") ,@Result( column = "remark",property = "remark")})
    @Select("SELECT * FROM sys_config WHERE type = #{type} order by id")
    public List<ConfigDO> getByType(@Param("type") String type);
}