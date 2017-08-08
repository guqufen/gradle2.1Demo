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
import net.fnsco.auth.service.sys.entity.RoleMenuDO;
import net.fnsco.auth.service.sys.dao.helper.RoleMenuProvider;

import java.util.List;;

public interface RoleMenuDAO {

    @Results({@Result( column = "role_id",property = "roleId"),@Result( column = "menu_id",property = "menuId") })
    @Select("SELECT * FROM sys_role_menu WHERE id = #{id}")
    public RoleMenuDO getById(@Param("id") int id);

    @Insert("INSERT into sys_role_menu(id,role_id,menu_id) VALUES (#{id},#{roleId},#{menuId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(RoleMenuDO roleMenu);

    @Delete("DELETE FROM sys_role_menu WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = RoleMenuProvider.class, method = "update")
    public int update(@Param("roleMenu") RoleMenuDO  roleMenu);

    @Results({@Result( column = "role_id",property = "roleId"),@Result( column = "menu_id",property = "menuId") })
    @SelectProvider(type = RoleMenuProvider.class, method = "pageList")
    public List<RoleMenuDO> pageList(@Param("roleMenu") RoleMenuDO roleMenu, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = RoleMenuProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("roleMenu") RoleMenuDO roleMenu);

}