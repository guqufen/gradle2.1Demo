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
import net.fnsco.api.doc.service.other.entity.RespSchemaDO;
import net.fnsco.api.doc.service.other.dao.helper.RespSchemaProvider;

import java.util.List;;

public interface RespSchemaDAO {

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "doc_id",property = "docId"),@Result( column = "module_id",property = "moduleId"),@Result( column = "cust_schema",property = "custSchema"),@Result( column = "ref_schema_id",property = "refSchemaId") })
    @Select("SELECT * FROM t_resp_schema WHERE id = #{id}")
    public RespSchemaDO getById(@Param("id") int id);

    @Insert("INSERT into t_resp_schema(id,create_date,modify_date,doc_id,module_id,code,name,description,cust_schema,type,ref_schema_id) VALUES (#{id},#{createDate},#{modifyDate},#{docId},#{moduleId},#{code},#{name},#{description},#{custSchema},#{type},#{refSchemaId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(RespSchemaDO respSchema);

    @Delete("DELETE FROM t_resp_schema WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = RespSchemaProvider.class, method = "update")
    public int update(@Param("respSchema") RespSchemaDO  respSchema);

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "doc_id",property = "docId"),@Result( column = "module_id",property = "moduleId"),@Result( column = "cust_schema",property = "custSchema"),@Result( column = "ref_schema_id",property = "refSchemaId") })
    @SelectProvider(type = RespSchemaProvider.class, method = "pageList")
    public List<RespSchemaDO> pageList(@Param("respSchema") RespSchemaDO respSchema, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = RespSchemaProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("respSchema") RespSchemaDO respSchema);

}