package net.fnsco.api.doc.service.project.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.api.doc.service.project.entity.ProjRolePrivilegeDO;
import net.fnsco.api.doc.service.project.dao.helper.ProjRolePrivilegeProvider;

import java.util.List;;

public interface ProjRolePrivilegeDAO {

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "proj_role_id",property = "projRoleId"),@Result( column = "proj_privilege_id",property = "projPrivilegeId") })
    @Select("SELECT * FROM t_proj_role_privilege WHERE id = #{id}")
    public ProjRolePrivilegeDO getById(@Param("id") int id);

    @Insert("INSERT into t_proj_role_privilege(id,create_date,modify_date,proj_role_id,proj_privilege_id) VALUES (#{id},#{createDate},#{modifyDate},#{projRoleId},#{projPrivilegeId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(ProjRolePrivilegeDO projRolePrivilege);

    @Delete("DELETE FROM t_proj_role_privilege WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = ProjRolePrivilegeProvider.class, method = "update")
    public int update(@Param("projRolePrivilege") ProjRolePrivilegeDO  projRolePrivilege);

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "proj_role_id",property = "projRoleId"),@Result( column = "proj_privilege_id",property = "projPrivilegeId") })
    @SelectProvider(type = ProjRolePrivilegeProvider.class, method = "pageList")
    public List<ProjRolePrivilegeDO> pageList(@Param("projRolePrivilege") ProjRolePrivilegeDO projRolePrivilege, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = ProjRolePrivilegeProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("projRolePrivilege") ProjRolePrivilegeDO projRolePrivilege);

}