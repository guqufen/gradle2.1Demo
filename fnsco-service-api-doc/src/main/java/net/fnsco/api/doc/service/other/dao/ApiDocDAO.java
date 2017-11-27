package net.fnsco.api.doc.service.other.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.api.doc.service.other.entity.ApiDocDO;
import net.fnsco.api.doc.service.other.dao.helper.ApiDocProvider;

import java.util.List;;

public interface ApiDocDAO {

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "proj_id",property = "projId"),@Result( column = "base_path",property = "basePath") })
    @Select("SELECT * FROM t_api_doc WHERE id = #{id}")
    public ApiDocDO getById(@Param("id") int id);

    @Results({@Result( column = "id",property = "id"),@Result( column = "proj_id",property = "projId"),@Result( column = "title",property = "title"),@Result( column = "base_path",property = "basePath") })
    @Select("SELECT id,proj_id,title,base_path FROM t_api_doc")
    public List<ApiDocDO> queryMenuist();
    
    @Insert("INSERT into t_api_doc(id,create_date,modify_date,proj_id,title,description,host,base_path,pub,open,scheme,consume,produce,version) VALUES (#{id},#{createDate},#{modifyDate},#{projId},#{title},#{description},#{host},#{basePath},#{pub},#{open},#{scheme},#{consume},#{produce},#{version})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(ApiDocDO apiDoc);

    @Delete("DELETE FROM t_api_doc WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = ApiDocProvider.class, method = "update")
    public int update(@Param("apiDoc") ApiDocDO  apiDoc);

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "proj_id",property = "projId"),@Result( column = "base_path",property = "basePath") })
    @SelectProvider(type = ApiDocProvider.class, method = "pageList")
    public List<ApiDocDO> pageList(@Param("apiDoc") ApiDocDO apiDoc, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = ApiDocProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("apiDoc") ApiDocDO apiDoc);

}