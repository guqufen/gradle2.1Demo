package net.fnsco.auth.service.sys.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.auth.service.sys.entity.MenuDO;

public class MenuProvider {

    private Logger              logger     = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "sys_menu";

    public String update(Map<String, Object> params) {
        MenuDO menu = (MenuDO) params.get("menu");
        return new SQL() {
            {
                UPDATE(TABLE_NAME);
                if (menu.getParentId() != null) {
                    SET("parent_id=#{menu.parentId}");
                }
                if (StringUtils.isNotBlank(menu.getName())) {
                    SET("name=#{menu.name}");
                }
                if (StringUtils.isNotBlank(menu.getUrl())) {
                    SET("url=#{menu.url}");
                }
                if (StringUtils.isNotBlank(menu.getPerms())) {
                    SET("perms=#{menu.perms}");
                }
                if (menu.getType() != null) {
                    SET("type=#{menu.type}");
                }
                if (StringUtils.isNotBlank(menu.getIcon())) {
                    SET("icon=#{menu.icon}");
                }
                if (menu.getOrderNum() != null) {
                    SET("order_num=#{menu.orderNum}");
                }
                WHERE("id = #{menu.id}");
            }
        }.toString();
    }

    public String pageList(Map<String, Object> params) {
        MenuDO menu = (MenuDO) params.get("menu");
        Integer pageNum = (Integer) params.get("pageNum");
        Integer pageSize = (Integer) params.get("pageSize");
        if (pageNum == null || pageNum == 0) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 50;
        }
        int start = (pageNum - 1) * pageSize;
        int limit = pageSize;
        return new SQL() {
            {
                SELECT("*");
                FROM(TABLE_NAME);
                if (menu.getId() != null) {
                    WHERE("id=#{menu.id}");
                }
                if (menu.getParentId() != null) {
                    WHERE("parent_id=#{menu.parentId}");
                }
                if (StringUtils.isNotBlank(menu.getName())) {
                    WHERE("name=#{menu.name}");
                }
                if (StringUtils.isNotBlank(menu.getUrl())) {
                    WHERE("url=#{menu.url}");
                }
                if (StringUtils.isNotBlank(menu.getPerms())) {
                    WHERE("perms=#{menu.perms}");
                }
                if (menu.getType() != null) {
                    WHERE("type=#{menu.type}");
                }
                if (StringUtils.isNotBlank(menu.getIcon())) {
                    WHERE("icon=#{menu.icon}");
                }
                if (menu.getOrderNum() != null) {
                    WHERE("order_num=#{menu.orderNum}");
                }
                ORDER_BY("id asc limit " + start + ", " + limit);
            }
        }.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        MenuDO menu = (MenuDO) params.get("menu");
        return new SQL() {
            {
                SELECT("count(1)");
                FROM(TABLE_NAME);
                if (menu.getId() != null) {
                    WHERE("id=#{menu.id}");
                }
                if (menu.getParentId() != null) {
                    WHERE("parent_id=#{menu.parentId}");
                }
                if (StringUtils.isNotBlank(menu.getName())) {
                    WHERE("name=#{menu.name}");
                }
                if (StringUtils.isNotBlank(menu.getUrl())) {
                    WHERE("url=#{menu.url}");
                }
                if (StringUtils.isNotBlank(menu.getPerms())) {
                    WHERE("perms=#{menu.perms}");
                }
                if (menu.getType() != null) {
                    WHERE("type=#{menu.type}");
                }
                if (StringUtils.isNotBlank(menu.getIcon())) {
                    WHERE("icon=#{menu.icon}");
                }
                if (menu.getOrderNum() != null) {
                    WHERE("order_num=#{menu.orderNum}");
                }
            }
        }.toString();
    }

    public String queryList(Map<String, Object> params) {
        MenuDO menu = (MenuDO) params.get("menu");

        return new SQL() {
            {
                SELECT("select m.*,(select p.name from sys_menu p where p.menu_id = m.parent_id) as parentName");
                FROM(TABLE_NAME);
                if (menu.getId() != null) {
                    WHERE("id=#{menu.id}");
                }
                if (menu.getParentId() != null) {
                    WHERE("parent_id=#{menu.parentId}");
                }
                if (StringUtils.isNotBlank(menu.getName())) {
                    WHERE("name=#{menu.name}");
                }
                if (StringUtils.isNotBlank(menu.getUrl())) {
                    WHERE("url=#{menu.url}");
                }
                if (StringUtils.isNotBlank(menu.getPerms())) {
                    WHERE("perms=#{menu.perms}");
                }
                if (menu.getType() != null) {
                    WHERE("type=#{menu.type}");
                }
                if (StringUtils.isNotBlank(menu.getIcon())) {
                    WHERE("icon=#{menu.icon}");
                }
                if (menu.getOrderNum() != null) {
                    WHERE("order_num=#{menu.orderNum}");
                }
                ORDER_BY("order_num asc ");
                 
            }
        }.toString();
    }

}
