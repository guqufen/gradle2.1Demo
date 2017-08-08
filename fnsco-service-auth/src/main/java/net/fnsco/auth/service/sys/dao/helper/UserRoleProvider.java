package net.fnsco.auth.service.sys.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.auth.service.sys.entity.UserRoleDO;
public class UserRoleProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "sys_user_role";

    public String update(Map<String, Object> params) {
        UserRoleDO userRole = (UserRoleDO) params.get("userRole");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (userRole.getUserId() != null) {
            SET("user_id=#{userRole.userId}");
        }
        if (userRole.getRoleId() != null) {
            SET("role_id=#{userRole.roleId}");
        }
        WHERE("id = #{userRole.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        UserRoleDO userRole = (UserRoleDO) params.get("userRole");
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
        if (userRole.getId() != null) {
            WHERE("id=#{userRole.id}");
        }
        if (userRole.getUserId() != null) {
            WHERE("user_id=#{userRole.userId}");
        }
        if (userRole.getRoleId() != null) {
            WHERE("role_id=#{userRole.roleId}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        UserRoleDO userRole = (UserRoleDO) params.get("userRole");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (userRole.getId() != null) {
            WHERE("id=#{userRole.id}");
        }
        if (userRole.getUserId() != null) {
            WHERE("user_id=#{userRole.userId}");
        }
        if (userRole.getRoleId() != null) {
            WHERE("role_id=#{userRole.roleId}");
        }
        }}.toString();
    }
}

