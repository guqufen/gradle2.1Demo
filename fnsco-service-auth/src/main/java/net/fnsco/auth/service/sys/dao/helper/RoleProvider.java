package net.fnsco.auth.service.sys.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.auth.service.sys.entity.RoleDO;
public class RoleProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "sys_role";

    public String update(Map<String, Object> params) {
        RoleDO role = (RoleDO) params.get("role");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (role.getRoleId() != null) {
            SET("role_id=#{role.roleId}");
        }
        if (StringUtils.isNotBlank(role.getRoleName())){
            SET("role_name=#{role.roleName}");
        }
        if (StringUtils.isNotBlank(role.getRemark())){
            SET("remark=#{role.remark}");
        }
        if (role.getDeptId() != null) {
            SET("dept_id=#{role.deptId}");
        }
        if (role.getCreateTime() != null) {
            SET("create_time=#{role.createTime}");
        }
        WHERE("id = #{role.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        RoleDO role = (RoleDO) params.get("role");
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
        if (role.getRoleId() != null) {
            WHERE("role_id=#{role.roleId}");
        }
        if (StringUtils.isNotBlank(role.getRoleName())){
            WHERE("role_name=#{role.roleName}");
        }
        if (StringUtils.isNotBlank(role.getRemark())){
            WHERE("remark=#{role.remark}");
        }
        if (role.getDeptId() != null) {
            WHERE("dept_id=#{role.deptId}");
        }
        if (role.getCreateTime() != null) {
            WHERE("create_time=#{role.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        RoleDO role = (RoleDO) params.get("role");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (role.getRoleId() != null) {
            WHERE("role_id=#{role.roleId}");
        }
        if (StringUtils.isNotBlank(role.getRoleName())){
            WHERE("role_name=#{role.roleName}");
        }
        if (StringUtils.isNotBlank(role.getRemark())){
            WHERE("remark=#{role.remark}");
        }
        if (role.getDeptId() != null) {
            WHERE("dept_id=#{role.deptId}");
        }
        if (role.getCreateTime() != null) {
            WHERE("create_time=#{role.createTime}");
        }
        }}.toString();
    }
}

