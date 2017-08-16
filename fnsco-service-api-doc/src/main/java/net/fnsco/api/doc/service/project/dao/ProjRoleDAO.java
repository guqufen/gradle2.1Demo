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
import net.fnsco.api.doc.service.project.entity.ProjRoleDO;
import net.fnsco.api.doc.service.project.dao.helper.ProjRoleProvider;

import java.util.List;;

public interface ProjRoleDAO {

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate") })
    @Select("SELECT * FROM t_proj_role WHERE id = #{id}")
    public ProjRoleDO getById(@Param("id") int id);

    @Insert("INSERT into t_proj_role(id,create_date,modify_date,code,name,description) VALUES (#{id},#{createDate},#{modifyDate},#{code},#{name},#{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(ProjRoleDO projRole);

    @Delete("DELETE FROM t_proj_role WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = ProjRoleProvider.class, method = "update")
    public int update(@Param("projRole") ProjRoleDO  projRole);

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate") })
    @SelectProvider(type = ProjRoleProvider.class, method = "pageList")
    public List<ProjRoleDO> pageList(@Param("projRole") ProjRoleDO projRole, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = ProjRoleProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("projRole") ProjRoleDO projRole);

}