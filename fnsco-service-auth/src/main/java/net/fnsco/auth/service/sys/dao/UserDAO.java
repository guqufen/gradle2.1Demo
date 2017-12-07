package net.fnsco.auth.service.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import net.fnsco.auth.service.sys.dao.helper.UserProvider;
import net.fnsco.auth.service.sys.entity.UserDO;;

public interface UserDAO {

    @Results({ @Result(column = "real_name", property = "realName"), @Result(column = "alias_name", property = "aliasName"), @Result(column = "agent_id", property = "agentId"),
               @Result(column = "modify_time", property = "modifyTime"), @Result(column = "modify_user_id", property = "modifyUserId") })
    @Select("SELECT * FROM sys_user WHERE id = #{id}")
    public UserDO getById(@Param("id") int id);

    @Results({ @Result(column = "real_name", property = "realName"), @Result(column = "alias_name", property = "aliasName"), @Result(column = "agent_id", property = "agentId"),
               @Result(column = "modify_time", property = "modifyTime"), @Result(column = "modify_user_id", property = "modifyUserId") })
    @Select("SELECT * FROM sys_user WHERE name=#{name} and password = #{password} and status ='1' ")
    public UserDO getByUserName(@Param("name") String name, @Param("password") String password);

    @Results({ @Result(column = "real_name", property = "realName"), @Result(column = "alias_name", property = "aliasName"), @Result(column = "agent_id", property = "agentId"),
               @Result(column = "modify_time", property = "modifyTime"), @Result(column = "modify_user_id", property = "modifyUserId") })
    @Select("SELECT * FROM sys_user WHERE name=#{name}")
    public UserDO getByName(@Param("name") String name);

    @Insert("INSERT into sys_user(id,type,name,password,real_name,mobile,sex,alias_name,department,agent_id,remark,modify_time,modify_user_id,status) VALUES (#{id},#{type},#{name},#{password},#{realName},#{mobile},#{sex},#{aliasName},#{department},#{agentId},#{remark},#{modifyTime},#{modifyUserId},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(UserDO user);

    @Update("UPDATE sys_user SET status='0' WHERE id = #{id}")
    public int updateById(@Param("id") Integer id);

    @UpdateProvider(type = UserProvider.class, method = "update")
    public int update(@Param("user") UserDO user);

    @Results({ @Result(column = "real_name", property = "realName"), @Result(column = "alias_name", property = "aliasName"), @Result(column = "agent_id", property = "agentId"),
               @Result(column = "modify_time", property = "modifyTime"), @Result(column = "modify_user_id", property = "modifyUserId") })
    @SelectProvider(type = UserProvider.class, method = "pageList")
    public List<UserDO> pageList(@Param("user") UserDO user, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = UserProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("user") UserDO user);

    @Results({ @Result(column = "perms", property = "perms") })
    @SelectProvider(type = UserProvider.class, method = "queryAllPerms")
    public List<String> queryAllPerms(@Param("userId") Integer userId);
}