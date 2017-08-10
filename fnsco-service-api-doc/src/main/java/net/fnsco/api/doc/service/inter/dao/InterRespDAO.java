package net.fnsco.api.doc.service.inter.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.fnsco.api.doc.service.inter.dao.helper.InterRespProvider;
import net.fnsco.api.doc.service.inter.entity.InterRespDO;;

public interface InterRespDAO {

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "doc_id",property = "docId"),@Result( column = "inter_id",property = "interId"),@Result( column = "ref_schema_id",property = "refSchemaId"),@Result( column = "cust_schema",property = "custSchema"),@Result( column = "ext_schema",property = "extSchema"),@Result( column = "sort_weight",property = "sortWeight") })
    @Select("SELECT * FROM t_inter_resp WHERE id = #{id}")
    public InterRespDO getById(@Param("id") int id);

    @Insert("INSERT into t_inter_resp(id,create_date,modify_date,doc_id,inter_id,code,name,description,type,ref_schema_id,def,required,cust_schema,ext_schema,sort_weight) VALUES (#{id},#{createDate},#{modifyDate},#{docId},#{interId},#{code},#{name},#{description},#{type},#{refSchemaId},#{def},#{required},#{custSchema},#{extSchema},#{sortWeight})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(InterRespDO interResp);

    @Delete("DELETE FROM t_inter_resp WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = InterRespProvider.class, method = "update")
    public int update(@Param("interResp") InterRespDO  interResp);

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "doc_id",property = "docId"),@Result( column = "inter_id",property = "interId"),@Result( column = "ref_schema_id",property = "refSchemaId"),@Result( column = "cust_schema",property = "custSchema"),@Result( column = "ext_schema",property = "extSchema"),@Result( column = "sort_weight",property = "sortWeight") })
    @SelectProvider(type = InterRespProvider.class, method = "pageList")
    public List<InterRespDO> pageList(@Param("interResp") InterRespDO interResp, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = InterRespProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("interResp") InterRespDO interResp);

}