package net.fnsco.car.service.agent.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.car.service.agent.entity.AgentDO;
import net.fnsco.car.service.agent.dao.helper.AgentProvider;

import java.util.List;;

public interface AgentDAO {

    @Results({@Result( column = "provinceId",property = "provinceid"),@Result( column = "provinceName",property = "provincename"),
    @Result( column = "cityId",property = "cityid"),@Result( column = "cityName",property = "cityname"),@Result( column = "areaId",property = "areaid"),@Result( column = "areaName",property = "areaname"),
    @Result( column = "suggest_code",property = "suggestCode"),@Result( column = "mobile",property = "mobile"),@Result( column = "short_name",property = "shortName"),@Result( column = "principal",property = "principal")
    ,@Result( column = "create_time",property = "createTime")})
    @Select("SELECT * FROM car_agent WHERE id = #{id}")
    public AgentDO getById(@Param("id") int id);

    @Insert("INSERT into car_agent(id,name,type,provinceId,provinceName,cityId,cityName,areaId,areaName,address,suggest_code,mobile,short_name,principal,create_time) VALUES (#{id},#{name},#{type},#{provinceid},#{provincename},#{cityid},#{cityname},#{areaid},#{areaname},#{address},#{suggestCode},#{mobile},#{shortName},#{principal},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(AgentDO agent);

    @Delete("DELETE FROM car_agent WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = AgentProvider.class, method = "update")
    public int update(@Param("agent") AgentDO  agent);

    @Results({@Result( column = "provinceId",property = "provinceid"),@Result( column = "provinceName",property = "provincename"),@Result( column = "cityId",property = "cityid"),@Result( column = "cityName",property = "cityname"),
    @Result( column = "areaId",property = "areaid"),@Result( column = "areaName",property = "areaname"),@Result( column = "suggest_code",property = "suggestCode"),@Result( column = "mobile",property = "mobile"),@Result( column = "short_name",property = "shortName"),@Result( column = "principal",property = "principal")
    ,@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = AgentProvider.class, method = "pageList")
    public List<AgentDO> pageList(@Param("agent") AgentDO agent, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = AgentProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("agent") AgentDO agent);
    
    /**
     * getAll:(查询所有)
     *
     * @param  @return    设定文件
     * @return List<AgentDO>    DOM对象
     * @author tangliang
     * @date   2017年12月12日 下午4:29:52
     */
    @Results({@Result( column = "provinceId",property = "provinceid"),@Result( column = "provinceName",property = "provincename"),@Result( column = "cityId",property = "cityid"),@Result( column = "cityName",property = "cityname"),@Result( column = "areaId",property = "areaid"),@Result( column = "areaName",property = "areaname"),@Result( column = "suggest_code",property = "suggestCode") })
    @Select("SELECT * FROM car_agent ")
    public List<AgentDO> getAll();
    
    /**
     * getBySuggestCode:(根据推荐码获取运营商信息)
     *
     * @param  @param suggestCode
     * @param  @return    设定文件
     * @return AgentDO    DOM对象
     * @author tangliang
     * @date   2017年12月13日 上午9:40:13
     */
    @Results({@Result( column = "provinceId",property = "provinceid"),@Result( column = "provinceName",property = "provincename"),@Result( column = "cityId",property = "cityid"),@Result( column = "cityName",property = "cityname"),@Result( column = "areaId",property = "areaid"),@Result( column = "areaName",property = "areaname"),@Result( column = "suggest_code",property = "suggestCode") })
    @Select("SELECT * FROM car_agent where suggest_code = #{suggestCode} limit 1")
    public AgentDO getBySuggestCode(@Param("suggestCode") Integer suggestCode);

}