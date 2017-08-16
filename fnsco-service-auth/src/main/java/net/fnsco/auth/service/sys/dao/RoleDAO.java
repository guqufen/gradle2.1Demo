package net.fnsco.auth.service.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import net.fnsco.auth.service.sys.dao.helper.RoleProvider;
import net.fnsco.auth.service.sys.entity.RoleDO;

/**
 * 角色管理
 * 
 * @author yx,20170808
 *
 */
@Repository
public interface RoleDAO {

	@Insert("INSERT into sys_role(role_id, dept_id, role_name,remark,create_time) VALUES (#{roleId},#{deptId},#{roleName},#{remark},#{createTime})")
	@Options(useGeneratedKeys = true, keyProperty = "roleId")
	public void insert(RoleDO roleDO);

	@Results({ @Result(column = "role_id", property = "roleId"), 
		@Result(column = "role_name", property = "roleName"),
			@Result(column = "remark", property = "remark"), 
			@Result(column = "create_time", property = "createTime"),
			@Result(column = "dept_id", property = "deptId") })
	@SelectProvider(type = RoleProvider.class, method = "queryList")
	public List<RoleDO> queryList(@Param("role") RoleDO role, @Param("pageNum") Integer pageNum,
			@Param("pageSize") Integer pageSize);

	@SelectProvider(type = RoleProvider.class, method = "pageListCount")
	public Integer pageListCount(@Param("role") RoleDO role);
	
	@UpdateProvider(type = RoleProvider.class, method = "update")
	public int update(@Param("roleDO") RoleDO roleDO);
	
	@Delete("DELETE FROM sys_role WHERE role_id=#{roleId}")
	public int delete(RoleDO roleDO);
}
