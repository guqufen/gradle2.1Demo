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
import net.fnsco.auth.service.sys.entity.MenuDO;
import net.fnsco.auth.service.sys.dao.helper.MenuProvider;

import java.util.List;;

public interface MenuDAO {

    @Results({@Result( column = "parent_id",property = "parentId"),@Result( column = "order_num",property = "orderNum") })
    @Select("SELECT * FROM sys_menu WHERE id = #{id}")
    public MenuDO getById(@Param("id") int id);

    @Insert("INSERT into sys_menu(id,parent_id,name,url,perms,type,icon,order_num) VALUES (#{id},#{parentId},#{name},#{url},#{perms},#{type},#{icon},#{orderNum})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(MenuDO menu);

    @Delete("DELETE FROM sys_menu WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = MenuProvider.class, method = "update")
    public int update(@Param("menu") MenuDO  menu);

    @Results({@Result( column = "parent_id",property = "parentId"),@Result( column = "order_num",property = "orderNum") })
    @SelectProvider(type = MenuProvider.class, method = "pageList")
    public List<MenuDO> pageList(@Param("menu") MenuDO menu, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = MenuProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("menu") MenuDO menu);

}