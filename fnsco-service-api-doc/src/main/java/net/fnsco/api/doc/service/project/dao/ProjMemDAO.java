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
import net.fnsco.api.doc.service.project.entity.ProjMemDO;
import net.fnsco.api.doc.service.project.dao.helper.ProjMemProvider;

import java.util.List;;

public interface ProjMemDAO {

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "proj_id",property = "projId"),@Result( column = "user_id",property = "userId"),@Result( column = "proj_role_id",property = "projRoleId") })
    @Select("SELECT * FROM t_proj_mem WHERE id = #{id}")
    public ProjMemDO getById(@Param("id") int id);

    @Insert("INSERT into t_proj_mem(id,create_date,modify_date,proj_id,user_id,role,proj_role_id) VALUES (#{id},#{createDate},#{modifyDate},#{projId},#{userId},#{role},#{projRoleId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(ProjMemDO projMem);

    @Delete("DELETE FROM t_proj_mem WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = ProjMemProvider.class, method = "update")
    public int update(@Param("projMem") ProjMemDO  projMem);

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "proj_id",property = "projId"),@Result( column = "user_id",property = "userId"),@Result( column = "proj_role_id",property = "projRoleId") })
    @SelectProvider(type = ProjMemProvider.class, method = "pageList")
    public List<ProjMemDO> pageList(@Param("projMem") ProjMemDO projMem, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = ProjMemProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("projMem") ProjMemDO projMem);

}