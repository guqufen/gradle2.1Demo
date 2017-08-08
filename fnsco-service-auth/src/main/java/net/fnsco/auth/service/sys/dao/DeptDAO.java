package net.fnsco.auth.service.sys.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.auth.service.sys.entity.DeptDO;
import net.fnsco.auth.service.sys.dao.helper.DeptProvider;

import java.util.List;;

public interface DeptDAO {

    @Results({@Result( column = "parent_id",property = "parentId"),@Result( column = "order_num",property = "orderNum"),@Result( column = "del_flag",property = "delFlag") })
    @Select("SELECT * FROM sys_dept WHERE id = #{id}")
    public DeptDO getById(@Param("id") int id);

    @Insert("INSERT into sys_dept(id,parent_id,name,order_num,del_flag) VALUES (#{id},#{parentId},#{name},#{orderNum},#{delFlag})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(DeptDO dept);

    @Delete("DELETE FROM sys_dept WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = DeptProvider.class, method = "update")
    public int update(@Param("dept") DeptDO  dept);

    @Results({@Result( column = "parent_id",property = "parentId"),@Result( column = "order_num",property = "orderNum"),@Result( column = "del_flag",property = "delFlag") })
    @SelectProvider(type = DeptProvider.class, method = "pageList")
    public List<DeptDO> pageList(@Param("dept") DeptDO dept, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = DeptProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("dept") DeptDO dept);

}