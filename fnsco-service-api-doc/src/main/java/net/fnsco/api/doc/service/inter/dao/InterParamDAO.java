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

import net.fnsco.api.doc.service.inter.dao.helper.InterParamProvider;
import net.fnsco.api.doc.service.inter.entity.InterParamDO;;

public interface InterParamDAO {

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "doc_id",property = "docId"),@Result( column = "inter_id",property = "interId"),@Result( column = "cust_schema",property = "custSchema"),@Result( column = "ext_schema",property = "extSchema"),@Result( column = "ref_schema_id",property = "refSchemaId"),@Result( column = "def_value",property = "defValue") })
    @Select("SELECT * FROM t_inter_param WHERE id = #{id}")
    public InterParamDO getById(@Param("id") int id);

    @Insert("INSERT into t_inter_param(id,create_date,modify_date,doc_id,inter_id,code,name,description,type,format,position,required,cust_schema,ext_schema,ref_schema_id,def_value) VALUES (#{id},#{createDate},#{modifyDate},#{docId},#{interId},#{code},#{name},#{description},#{type},#{format},#{position},#{required},#{custSchema},#{extSchema},#{refSchemaId},#{defValue})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(InterParamDO interParam);

    @Delete("DELETE FROM t_inter_param WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = InterParamProvider.class, method = "update")
    public int update(@Param("interParam") InterParamDO  interParam);

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "doc_id",property = "docId"),@Result( column = "inter_id",property = "interId"),@Result( column = "cust_schema",property = "custSchema"),@Result( column = "ext_schema",property = "extSchema"),@Result( column = "ref_schema_id",property = "refSchemaId"),@Result( column = "def_value",property = "defValue") })
    @SelectProvider(type = InterParamProvider.class, method = "pageList")
    public List<InterParamDO> pageList(@Param("interParam") InterParamDO interParam, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = InterParamProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("interParam") InterParamDO interParam);

}