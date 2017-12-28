package net.fnsco.car.service.config.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.car.service.config.entity.ConfigDO;
import net.fnsco.car.service.config.dao.helper.ConfigProvider;

import java.util.List;;

public interface ConfigDAO {

    @Results({@Result( column = "id",property = "id"), @Result( column = "name",property = "name") })
    @Select("SELECT id,name FROM car_config WHERE status=1 AND type='00' ORDER BY order_no")
    public List<ConfigDO> getAll();
    
    @Results({@Result( column = "id",property = "id") })
    @Select("SELECT id FROM car_config WHERE name= #{name}")
    public Integer getId(@Param("name") String name);
    
    @Results({@Result( column = "id",property = "id"), @Result( column = "name",property = "name"), @Result( column = "value",property = "value") , @Result( column = "modify_time",property = "modifyTime")})
    @Select("SELECT id,name,value,modify_time FROM car_config WHERE status=1 AND type='01' ")
    public List<ConfigDO> getIndex();
    
    @Select("SELECT value FROM car_config WHERE status=1 AND type='01' AND name ='2'")
    public String getAmt();
    
    @Results({@Result( column = "group_name",property = "groupName"),@Result( column = "order_no",property = "orderNo") })
    @Select("SELECT * FROM car_config WHERE id = #{id}")
    public ConfigDO getById(@Param("id") int id);

    @Insert("INSERT into car_config(id,type,group_name,name,value,status,remark,order_no,keep1,keep2,keep3) VALUES (#{id},#{type},#{groupName},#{name},#{value},#{status},#{remark},#{orderNo},#{keep1},#{keep2},#{keep3})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(ConfigDO config);

    @Delete("DELETE FROM car_config WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = ConfigProvider.class, method = "update")
    public int update(@Param("config") ConfigDO  config);

    @Results({@Result( column = "group_name",property = "groupName"),@Result( column = "order_no",property = "orderNo") })
    @SelectProvider(type = ConfigProvider.class, method = "pageList")
    public List<ConfigDO> pageList(@Param("config") ConfigDO config, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = ConfigProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("config") ConfigDO config);

}