package net.fnsco.api.doc.service.project.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.api.doc.service.project.entity.ProjRolePrivilegeDO;
public class ProjRolePrivilegeProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_proj_role_privilege";

    public String update(Map<String, Object> params) {
        ProjRolePrivilegeDO projRolePrivilege = (ProjRolePrivilegeDO) params.get("projRolePrivilege");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (projRolePrivilege.getCreateDate() != null) {
            SET("create_date=#{projRolePrivilege.createDate}");
        }
        if (projRolePrivilege.getModifyDate() != null) {
            SET("modify_date=#{projRolePrivilege.modifyDate}");
        }
        if (projRolePrivilege.getProjRoleId() != null) {
            SET("proj_role_id=#{projRolePrivilege.projRoleId}");
        }
        if (projRolePrivilege.getProjPrivilegeId() != null) {
            SET("proj_privilege_id=#{projRolePrivilege.projPrivilegeId}");
        }
        WHERE("id = #{projRolePrivilege.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        ProjRolePrivilegeDO projRolePrivilege = (ProjRolePrivilegeDO) params.get("projRolePrivilege");
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
        if (projRolePrivilege.getId() != null) {
            WHERE("id=#{projRolePrivilege.id}");
        }
        if (projRolePrivilege.getCreateDate() != null) {
            WHERE("create_date=#{projRolePrivilege.createDate}");
        }
        if (projRolePrivilege.getModifyDate() != null) {
            WHERE("modify_date=#{projRolePrivilege.modifyDate}");
        }
        if (projRolePrivilege.getProjRoleId() != null) {
            WHERE("proj_role_id=#{projRolePrivilege.projRoleId}");
        }
        if (projRolePrivilege.getProjPrivilegeId() != null) {
            WHERE("proj_privilege_id=#{projRolePrivilege.projPrivilegeId}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        ProjRolePrivilegeDO projRolePrivilege = (ProjRolePrivilegeDO) params.get("projRolePrivilege");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (projRolePrivilege.getId() != null) {
            WHERE("id=#{projRolePrivilege.id}");
        }
        if (projRolePrivilege.getCreateDate() != null) {
            WHERE("create_date=#{projRolePrivilege.createDate}");
        }
        if (projRolePrivilege.getModifyDate() != null) {
            WHERE("modify_date=#{projRolePrivilege.modifyDate}");
        }
        if (projRolePrivilege.getProjRoleId() != null) {
            WHERE("proj_role_id=#{projRolePrivilege.projRoleId}");
        }
        if (projRolePrivilege.getProjPrivilegeId() != null) {
            WHERE("proj_privilege_id=#{projRolePrivilege.projPrivilegeId}");
        }
        }}.toString();
    }
}

