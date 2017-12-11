package net.fnsco.car.service.city.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.car.service.city.entity.DicCityDO;
import net.fnsco.car.service.city.dao.helper.DicCityProvider;

import java.util.List;;

public interface DicCityDAO {

    @Results({@Result( column = "supper_id",property = "supperId") })
    @Select("SELECT * FROM car_dic_city WHERE id = #{id}")
    public DicCityDO getById(@Param("id") int id);

    @Insert("INSERT into car_dic_city(id,name,supper_id,level) VALUES (#{id},#{name},#{supperId},#{level})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(DicCityDO dicCity);

    @Delete("DELETE FROM car_dic_city WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = DicCityProvider.class, method = "update")
    public int update(@Param("dicCity") DicCityDO  dicCity);

    @Results({@Result( column = "supper_id",property = "supperId") })
    @SelectProvider(type = DicCityProvider.class, method = "pageList")
    public List<DicCityDO> pageList(@Param("dicCity") DicCityDO dicCity, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = DicCityProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("dicCity") DicCityDO dicCity);

    @Results({@Result( column = "id",property = "id"),@Result( column = "name",property = "name") })
    @Select("SELECT id,name FROM car_dic_city")
	public List<DicCityDO> queryAll();

}