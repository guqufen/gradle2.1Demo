package net.fnsco.auth.service.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import net.fnsco.auth.service.sys.dao.helper.DeptProvider;
import net.fnsco.auth.service.sys.entity.DeptDO;;

public interface DeptDAO {

    @Results({@Result( column = "parent_id",property = "parentId"),@Result( column = "order_num",property = "orderNum"),@Result( column = "del_flag",property = "delFlag") })
    @Select("SELECT * FROM sys_dept WHERE id = #{id}")
    public DeptDO getById(@Param("id") Integer id);
    
    @Results({@Result( column = "id",property = "id")})
    @Select("SELECT id FROM sys_dept WHERE parent_id= #{id}")
    public List<Integer> getByparentId(@Param("id") Integer id);
    
    //@Results({@Result( column = "parent_id",property = "parentId"),@Result( column = "order_num",property = "orderNum"),@Result( column = "del_flag",property = "delFlag") })
    @Select("SELECT id FROM sys_dept WHERE name= #{parentName}")
    public int getByName(@Param("parentName") String parentName);

    @Insert("INSERT into sys_dept(parent_id,name,order_num,del_flag) VALUES (#{parentId},#{name},#{orderNum},#{delFlag})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(DeptDO dept);

    @Update("UPDATE sys_dept SET del_flag='-1' WHERE id = #{id}")
    public int deleteById(@Param("id") Integer id);

    @UpdateProvider(type = DeptProvider.class, method = "update")
    public int update(@Param("dept") DeptDO  dept);

    @Results({@Result( column = "parent_id",property = "parentId"),@Result( column = "order_num",property = "orderNum"),@Result( column = "del_flag",property = "delFlag"),@Result( column = "parentName",property = "parentName")})
    @SelectProvider(type = DeptProvider.class, method = "pageList")
    public List<DeptDO> pageList(@Param("dept") DeptDO dept, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
    
    @Results({@Result( column = "parent_id",property = "parentId"),@Result( column = "order_num",property = "orderNum"),@Result( column = "del_flag",property = "delFlag") })
    @Select("SELECT * FROM sys_dept order by order_num")
    public List<DeptDO> pageNameList();

    @SelectProvider(type = DeptProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("dept") DeptDO dept);

}