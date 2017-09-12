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
import net.fnsco.risk.service.sys.entity.WebUserDO;
import net.fnsco.risk.service.sys.dao.helper.WebUserProvider;

import java.util.List;;

public interface WebUserDAO {

    @Results({ @Result(column = "real_name", property = "realName"), @Result(column = "alias_name", property = "aliasName"), @Result(column = "agent_id", property = "agentId"),
               @Result(column = "modify_time", property = "modifyTime"), @Result(column = "modify_user_id", property = "modifyUserId"), @Result(column = "creater_time", property = "createrTime") })
    @Select("SELECT * FROM risk_web_user WHERE id = #{id}")
    public WebUserDO getById(@Param("id") int id);

    @Results({ @Result(column = "real_name", property = "realName"), @Result(column = "alias_name", property = "aliasName"), @Result(column = "agent_id", property = "agentId"),
               @Result(column = "modify_time", property = "modifyTime"), @Result(column = "modify_user_id", property = "modifyUserId"), @Result(column = "creater_time", property = "createrTime") })
    @Select("SELECT * FROM risk_web_user WHERE name = #{name}")
    public WebUserDO getByUserName(@Param("name") String name);

    @Results({ @Result(column = "real_name", property = "realName"), @Result(column = "alias_name", property = "aliasName"), @Result(column = "agent_id", property = "agentId"),
               @Result(column = "modify_time", property = "modifyTime"), @Result(column = "modify_user_id", property = "modifyUserId"), @Result(column = "creater_time", property = "createrTime") })
    @Select("SELECT * FROM risk_web_user WHERE real_name = #{realName}")
    public WebUserDO getByRealName(@Param("realName") String realName);

    @Insert("INSERT into risk_web_user(id,type,name,password,real_name,mobile,sex,alias_name,department,agent_id,remark,modify_time,modify_user_id,creater_time) VALUES (#{id},#{type},#{name},#{password},#{realName},#{mobile},#{sex},#{aliasName},#{department},#{agentId},#{remark},#{modifyTime},#{modifyUserId},#{createrTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(WebUserDO webUser);

    @Delete("DELETE FROM risk_web_user WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = WebUserProvider.class, method = "update")
    public int update(@Param("webUser") WebUserDO webUser);

    @Results({ @Result(column = "real_name", property = "realName"), @Result(column = "alias_name", property = "aliasName"), @Result(column = "agent_id", property = "agentId"),
               @Result(column = "modify_time", property = "modifyTime"), @Result(column = "modify_user_id", property = "modifyUserId"), @Result(column = "creater_time", property = "createrTime") })
    @SelectProvider(type = WebUserProvider.class, method = "pageList")
    public List<WebUserDO> pageList(@Param("webUser") WebUserDO webUser, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = WebUserProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("webUser") WebUserDO webUser);

}