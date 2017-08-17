package net.fnsco.auth.service.sys.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.auth.service.sys.entity.RoleDeptDO;
public class RoleDeptProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "sys_role_dept";

    public String update(Map<String, Object> params) {
        RoleDeptDO roleDept = (RoleDeptDO) params.get("roleDept");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (roleDept.getRoleId() != null) {
            SET("role_id=#{roleDept.roleId}");
        }
        if (roleDept.getDeptId() != null) {
            SET("dept_id=#{roleDept.deptId}");
        }
        WHERE("id = #{roleDept.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        RoleDeptDO roleDept = (RoleDeptDO) params.get("roleDept");
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
        if (roleDept.getId() != null) {
            WHERE("id=#{roleDept.id}");
        }
        if (roleDept.getRoleId() != null) {
            WHERE("role_id=#{roleDept.roleId}");
        }
        if (roleDept.getDeptId() != null) {
            WHERE("dept_id=#{roleDept.deptId}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        RoleDeptDO roleDept = (RoleDeptDO) params.get("roleDept");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (roleDept.getId() != null) {
            WHERE("id=#{roleDept.id}");
        }
        if (roleDept.getRoleId() != null) {
            WHERE("role_id=#{roleDept.roleId}");
        }
        if (roleDept.getDeptId() != null) {
            WHERE("dept_id=#{roleDept.deptId}");
        }
        }}.toString();
    }
    
    public String query(Map<String, Object> params) {
    	RoleDeptDO roleDept = (RoleDeptDO) params.get("roleDept");
        return new SQL() {{
        SELECT("*");
        FROM(TABLE_NAME);
        if (roleDept.getId() != null) {
            WHERE("id=#{roleDept.id}");
        }
        if (roleDept.getRoleId() != null) {
            WHERE("role_id=#{roleDept.roleId}");
        }
        if (roleDept.getDeptId() != null) {
            WHERE("dept_id=#{roleDept.deptId}");
        }
        }}.toString();
    }
}

