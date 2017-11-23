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
import net.fnsco.api.doc.service.project.entity.ProjPrivilegeDO;
import net.fnsco.api.doc.service.project.dao.helper.ProjPrivilegeProvider;

import java.util.List;;

public interface ProjPrivilegeDAO {

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate") })
    @Select("SELECT * FROM t_proj_privilege WHERE id = #{id}")
    public ProjPrivilegeDO getById(@Param("id") int id);

    @Insert("INSERT into t_proj_privilege(id,create_date,modify_date,code,name,description,status) VALUES (#{id},#{createDate},#{modifyDate},#{code},#{name},#{description},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(ProjPrivilegeDO projPrivilege);

    @Delete("DELETE FROM t_proj_privilege WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = ProjPrivilegeProvider.class, method = "update")
    public int update(@Param("projPrivilege") ProjPrivilegeDO  projPrivilege);

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate") })
    @SelectProvider(type = ProjPrivilegeProvider.class, method = "pageList")
    public List<ProjPrivilegeDO> pageList(@Param("projPrivilege") ProjPrivilegeDO projPrivilege, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = ProjPrivilegeProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("projPrivilege") ProjPrivilegeDO projPrivilege);

}