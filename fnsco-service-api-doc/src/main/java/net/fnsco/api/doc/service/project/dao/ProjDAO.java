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
import net.fnsco.api.doc.service.project.entity.ProjDO;
import net.fnsco.api.doc.service.project.dao.helper.ProjProvider;

import java.util.List;;

public interface ProjDAO {

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "user_id",property = "userId") })
    @Select("SELECT * FROM t_proj WHERE id = #{id}")
    public ProjDO getById(@Param("id") int id);

    @Results({@Result( column = "code",property = "code"),@Result( column = "name",property = "name") })
    @Select("SELECT code,name,url FROM t_proj ORDER BY name")
    public List<ProjDO> queryMenuist();
    
    @Insert("INSERT into t_proj(id,create_date,modify_date,user_id,code,name,description,status,url) VALUES (#{id},#{createDate},#{modifyDate},#{userId},#{code},#{name},#{description},#{status},#{url})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(ProjDO proj);

    @Delete("DELETE FROM t_proj WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = ProjProvider.class, method = "update")
    public int update(@Param("proj") ProjDO  proj);

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "user_id",property = "userId") })
    @SelectProvider(type = ProjProvider.class, method = "pageList")
    public List<ProjDO> pageList(@Param("proj") ProjDO proj, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = ProjProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("proj") ProjDO proj);

}