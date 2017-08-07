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
import net.fnsco.api.doc.service.project.entity.ProjLogDO;
import net.fnsco.api.doc.service.project.dao.helper.ProjLogProvider;

import java.util.List;;

public interface ProjLogDAO {

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "proj_id",property = "projId"),@Result( column = "user_id",property = "userId"),@Result( column = "pub_date",property = "pubDate") })
    @Select("SELECT * FROM t_proj_log WHERE id = #{id}")
    public ProjLogDO getById(@Param("id") int id);

    @Insert("INSERT into t_proj_log(id,create_date,modify_date,proj_id,user_id,pub_date,title,content) VALUES (#{id},#{createDate},#{modifyDate},#{projId},#{userId},#{pubDate},#{title},#{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(ProjLogDO projLog);

    @Delete("DELETE FROM t_proj_log WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = ProjLogProvider.class, method = "update")
    public int update(@Param("projLog") ProjLogDO  projLog);

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "proj_id",property = "projId"),@Result( column = "user_id",property = "userId"),@Result( column = "pub_date",property = "pubDate") })
    @SelectProvider(type = ProjLogProvider.class, method = "pageList")
    public List<ProjLogDO> pageList(@Param("projLog") ProjLogDO projLog, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = ProjLogProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("projLog") ProjLogDO projLog);

}