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

    @Insert("INSERT into sys_role_menu(id,role_id,menu_id) VALUES (#{id},#{roleId},#{menuId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(RoleMenuDO roleMenu);

    @Delete("DELETE FROM sys_role_menu WHERE role_id = #{id}")
    public int deleteById(@Param("id") Integer roleId);
    
    @Delete("DELETE FROM sys_role_menu WHERE menu_id = #{id}")
    public int deleteByMenuId(@Param("id") Integer MenuId);

    @UpdateProvider(type = RoleMenuProvider.class, method = "update")
    public int update(@Param("roleMenu") RoleMenuDO  roleMenu);

    @Results({@Result( column = "role_id",property = "roleId"),@Result( column = "menu_id",property = "menuId") })
    @SelectProvider(type = RoleMenuProvider.class, method = "pageList")
    public List<RoleMenuDO> pageList(@Param("roleMenu") RoleMenuDO roleMenu, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = RoleMenuProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("roleMenu") RoleMenuDO roleMenu);
    
    /**
     * 通过角色ID查找对应的菜单ID(一对多，列表)
     * @param roleId
     * @return
     */
    @Select("select menu_id from sys_role_menu WHERE role_id = #{roleId}")
    public List<Integer> queryByRoleId(@Param("roleId") Integer roleId);
}