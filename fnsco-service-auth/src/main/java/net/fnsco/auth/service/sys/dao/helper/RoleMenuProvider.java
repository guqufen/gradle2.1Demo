package net.fnsco.auth.service.sys.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.auth.service.sys.entity.RoleMenuDO;
public class RoleMenuProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "sys_role_menu";

    public String update(Map<String, Object> params) {
        RoleMenuDO roleMenu = (RoleMenuDO) params.get("roleMenu");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (roleMenu.getRoleId() != null) {
            SET("role_id=#{roleMenu.roleId}");
        }
        if (roleMenu.getMenuId() != null) {
            SET("menu_id=#{roleMenu.menuId}");
        }
        WHERE("id = #{roleMenu.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        RoleMenuDO roleMenu = (RoleMenuDO) params.get("roleMenu");
        Integer pageNum = (Integer) params.get("pageNum");
        Integer pageSize = (Integer) params.get("pageSize");
        if (pageNum == null || pageNum == 0) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 20;
        }
        int start = (pageNum - 1) * pageSize;
        int limit = pageSize;
        return new SQL() {{
        SELECT("*");
        FROM(TABLE_NAME);
        if (roleMenu.getId() != null) {
            WHERE("id=#{roleMenu.id}");
        }
        if (roleMenu.getRoleId() != null) {
            WHERE("role_id=#{roleMenu.roleId}");
        }
        if (roleMenu.getMenuId() != null) {
            WHERE("menu_id=#{roleMenu.menuId}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        RoleMenuDO roleMenu = (RoleMenuDO) params.get("roleMenu");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (roleMenu.getId() != null) {
            WHERE("id=#{roleMenu.id}");
        }
        if (roleMenu.getRoleId() != null) {
            WHERE("role_id=#{roleMenu.roleId}");
        }
        if (roleMenu.getMenuId() != null) {
            WHERE("menu_id=#{roleMenu.menuId}");
        }
        }}.toString();
    }
}

