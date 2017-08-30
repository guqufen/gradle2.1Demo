package net.fnsco.withhold.service.sys.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.withhold.service.sys.entity.UserDO;
import net.fnsco.withhold.service.sys.dao.helper.UserProvider;

import java.util.List;;

public interface UserDAO {
	@Results({ @Result(column = "real_name", property = "realName"), @Result(column = "role_type", property = "roleType"),
			@Result(column = "alias_name", property = "aliasName"), @Result(column = "agent_id", property = "agentId"),
			@Result(column = "modify_time", property = "modifyTime"),
			@Result(column = "modify_user_id", property = "modifyUserId") })
	@Select("SELECT * FROM sys_user WHERE id = #{id}")
	public UserDO getById(@Param("id") int id);

	@Results({ @Result(column = "real_name", property = "realName"), @Result(column = "role_type", property = "roleType"),
			@Result(column = "alias_name", property = "aliasName"), @Result(column = "agent_id", property = "agentId"),
			@Result(column = "modify_time", property = "modifyTime"),
			@Result(column = "modify_user_id", property = "modifyUserId") })
	@Select("SELECT * FROM sys_user WHERE name = #{name}")
	public UserDO getByUserName(@Param("name") String name);

	@Results({ @Result(column = "real_name", property = "realName"), @Result(column = "role_type", property = "roleType"),
			@Result(column = "alias_name", property = "aliasName"), @Result(column = "agent_id", property = "agentId"),
			@Result(column = "modify_time", property = "modifyTime"),
			@Result(column = "modify_user_id", property = "modifyUserId") })
	@Select("SELECT * FROM sys_user WHERE real_name = #{realName}")
	public UserDO getByRealName(@Param("realName") String realName);

	@Insert("INSERT into sys_user(id,type,name,password,real_name,mobile,sex,alias_name,department,agent_id,remark,modify_time,modify_user_id,role_type) VALUES (#{id},#{type},#{name},#{password},#{realName},#{mobile},#{sex},#{aliasName},#{department},#{agentId},#{remark},#{modifyTime},#{modifyUserId},#{roleType})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insert(UserDO user);

	@Delete("DELETE FROM sys_user WHERE id = #{id}")
	public int deleteById(@Param("id") int id);

	@UpdateProvider(type = UserProvider.class, method = "update")
	public int update(@Param("user") UserDO user);

	@Results({ @Result(column = "real_name", property = "realName"), @Result(column = "role_type", property = "roleType"),
			@Result(column = "alias_name", property = "aliasName"), @Result(column = "agent_id", property = "agentId"),
			@Result(column = "modify_time", property = "modifyTime"),
			@Result(column = "modify_user_id", property = "modifyUserId") })
	@SelectProvider(type = UserProvider.class, method = "pageList")
	public List<UserDO> pageList(@Param("user") UserDO user, @Param("pageNum") Integer pageNum,
			@Param("pageSize") Integer pageSize);

	@SelectProvider(type = UserProvider.class, method = "pageListCount")
	public Integer pageListCount(@Param("user") UserDO user);

}