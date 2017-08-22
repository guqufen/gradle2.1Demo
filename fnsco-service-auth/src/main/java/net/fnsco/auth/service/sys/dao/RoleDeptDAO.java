package net.fnsco.auth.service.sys.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.auth.service.sys.entity.RoleDeptDO;
import net.fnsco.auth.service.sys.dao.helper.RoleDeptProvider;

import java.util.List;;

public interface RoleDeptDAO {

    @Results({@Result( column = "role_id",property = "roleId"),@Result( column = "dept_id",property = "deptId") })
    @Select("SELECT * FROM sys_role_dept WHERE id = #{id}")
    public RoleDeptDO getById(@Param("id") int id);

    @Insert("INSERT into sys_role_dept(id,role_id,dept_id) VALUES (#{id},#{roleId},#{deptId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(RoleDeptDO roleDept);

    @Delete("DELETE FROM sys_role_dept WHERE role_id = #{id}")
    public int deleteById(@Param("id") Integer id);
    
    @Delete("DELETE FROM sys_role_dept WHERE dept_id = #{id}")
    public int deleteByDeptId(@Param("id") Integer id);

    @UpdateProvider(type = RoleDeptProvider.class, method = "update")
    public int update(@Param("roleDept") RoleDeptDO  roleDept);

    @Results({@Result( column = "role_id",property = "roleId"),@Result( column = "dept_id",property = "deptId") })
    @SelectProvider(type = RoleDeptProvider.class, method = "pageList")
    public List<RoleDeptDO> pageList(@Param("roleDept") RoleDeptDO roleDept, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = RoleDeptProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("roleDept") RoleDeptDO roleDept);
    
    @Results({@Result( column = "id",property = "id"),@Result( column = "role_id",property = "roleId"),@Result( column = "dept_id",property = "deptId") })
    @SelectProvider(type = RoleDeptProvider.class, method = "query")
    public List<RoleDeptDO> query(@Param("roleDept") RoleDeptDO roleDept);

}