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
import net.fnsco.auth.service.sys.entity.UserRoleDO;
import net.fnsco.auth.service.sys.dao.helper.UserRoleProvider;

import java.util.List;;

public interface UserRoleDAO {

    @Results({@Result( column = "user_id",property = "userId"),@Result( column = "role_id",property = "roleId") })
    @Select("SELECT * FROM sys_user_role WHERE id = #{id}")
    public UserRoleDO getById(@Param("id") int id);

    @Insert("INSERT into sys_user_role(id,user_id,role_id) VALUES (#{id},#{userId},#{roleId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(UserRoleDO userRole);

    @Delete("DELETE FROM sys_user_role WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = UserRoleProvider.class, method = "update")
    public int update(@Param("userRole") UserRoleDO  userRole);

    @Results({@Result( column = "user_id",property = "userId"),@Result( column = "role_id",property = "roleId") })
    @SelectProvider(type = UserRoleProvider.class, method = "pageList")
    public List<UserRoleDO> pageList(@Param("userRole") UserRoleDO userRole, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = UserRoleProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("userRole") UserRoleDO userRole);

}