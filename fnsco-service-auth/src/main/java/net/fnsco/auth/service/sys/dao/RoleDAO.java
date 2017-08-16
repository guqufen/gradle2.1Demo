package net.fnsco.auth.service.sys.dao;

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

import net.fnsco.auth.service.sys.dao.helper.RoleProvider;
import net.fnsco.auth.service.sys.entity.RoleDO;;

public interface RoleDAO {

    @Results({@Result( column = "role_id",property = "roleId"),@Result( column = "role_name",property = "roleName"),@Result( column = "dept_id",property = "deptId"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM sys_role WHERE id = #{id}")
    public RoleDO getById(@Param("id") int id);

    @Insert("INSERT into sys_role(role_id,role_name,remark,dept_id,create_time) VALUES (#{roleId},#{roleName},#{remark},#{deptId},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(RoleDO role);

    @Delete("DELETE FROM sys_role WHERE id = #{id}")
    public int deleteById(@Param("id") Long id);

    @UpdateProvider(type = RoleProvider.class, method = "update")
    public int update(@Param("role") RoleDO  role);

    @Results({@Result( column = "role_id",property = "roleId"),@Result( column = "role_name",property = "roleName"),@Result( column = "dept_id",property = "deptId"),@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = RoleProvider.class, method = "pageList")
    public List<RoleDO> pageList(@Param("role") RoleDO role, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = RoleProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("role") RoleDO role);
    
    @Results({@Result( column = "role_id",property = "roleId"),@Result( column = "role_name",property = "roleName"),@Result( column = "dept_id",property = "deptId"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM sys_role")
    public List<RoleDO> query();

}