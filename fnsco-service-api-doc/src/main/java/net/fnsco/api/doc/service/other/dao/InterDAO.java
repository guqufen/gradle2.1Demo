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
import net.fnsco.api.doc.service.other.entity.InterDO;
import net.fnsco.api.doc.service.other.dao.helper.InterProvider;

import java.util.List;;

public interface InterDAO {

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "doc_id",property = "docId"),@Result( column = "module_id",property = "moduleId"),@Result( column = "sort_weight",property = "sortWeight") })
    @Select("SELECT * FROM t_inter WHERE id = #{id}")
    public InterDO getById(@Param("id") int id);

    @Insert("INSERT into t_inter(id,create_date,modify_date,doc_id,module_id,name,path,method,scheme,summary,description,consume,produce,deprecated,sort_weight) VALUES (#{id},#{createDate},#{modifyDate},#{docId},#{moduleId},#{name},#{path},#{method},#{scheme},#{summary},#{description},#{consume},#{produce},#{deprecated},#{sortWeight})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(InterDO inter);

    @Delete("DELETE FROM t_inter WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = InterProvider.class, method = "update")
    public int update(@Param("inter") InterDO  inter);

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "doc_id",property = "docId"),@Result( column = "module_id",property = "moduleId"),@Result( column = "sort_weight",property = "sortWeight") })
    @SelectProvider(type = InterProvider.class, method = "pageList")
    public List<InterDO> pageList(@Param("inter") InterDO inter, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = InterProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("inter") InterDO inter);

}