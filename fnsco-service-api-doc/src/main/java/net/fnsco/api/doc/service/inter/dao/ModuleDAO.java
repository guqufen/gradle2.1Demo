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

import net.fnsco.api.doc.service.inter.dao.helper.ModuleProvider;
import net.fnsco.api.doc.service.inter.entity.ModuleDO;;

public interface ModuleDAO {

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "doc_id",property = "docId"),@Result( column = "sort_weight",property = "sortWeight") })
    @Select("SELECT * FROM t_module WHERE id = #{id}")
    public ModuleDO getById(@Param("id") int id);

    @Insert("INSERT into t_module(id,create_date,modify_date,doc_id,code,name,description,sort_weight) VALUES (#{id},#{createDate},#{modifyDate},#{docId},#{code},#{name},#{description},#{sortWeight})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(ModuleDO module);

    @Delete("DELETE FROM t_module WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = ModuleProvider.class, method = "update")
    public int update(@Param("module") ModuleDO  module);

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "doc_id",property = "docId"),@Result( column = "sort_weight",property = "sortWeight") })
    @SelectProvider(type = ModuleProvider.class, method = "pageList")
    public List<ModuleDO> pageList(@Param("module") ModuleDO module, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = ModuleProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("module") ModuleDO module);
    
    /**
     * getByName:(这里用一句话描述这个方法的作用)根据模块名称查询
     * @param name
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月10日 下午1:56:16
     * @return ModuleDO    DOM对象
     */
    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "doc_id",property = "docId"),@Result( column = "sort_weight",property = "sortWeight") })
    @Select("SELECT * FROM t_module WHERE name = #{name}")
    public ModuleDO getByName(@Param("name") String name);

}