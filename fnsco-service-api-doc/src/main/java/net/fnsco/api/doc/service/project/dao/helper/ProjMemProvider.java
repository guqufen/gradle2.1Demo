package net.fnsco.api.doc.service.project.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.api.doc.service.project.entity.ProjMemDO;
public class ProjMemProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_proj_mem";

    public String update(Map<String, Object> params) {
        ProjMemDO projMem = (ProjMemDO) params.get("projMem");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (projMem.getCreateDate() != null) {
            SET("create_date=#{projMem.createDate}");
        }
        if (projMem.getModifyDate() != null) {
            SET("modify_date=#{projMem.modifyDate}");
        }
        if (projMem.getProjId() != null) {
            SET("proj_id=#{projMem.projId}");
        }
        if (projMem.getUserId() != null) {
            SET("user_id=#{projMem.userId}");
        }
        if (StringUtils.isNotBlank(projMem.getRole())){
            SET("role=#{projMem.role}");
        }
        if (projMem.getProjRoleId() != null) {
            SET("proj_role_id=#{projMem.projRoleId}");
        }
        WHERE("id = #{projMem.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        ProjMemDO projMem = (ProjMemDO) params.get("projMem");
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
        if (projMem.getId() != null) {
            WHERE("id=#{projMem.id}");
        }
        if (projMem.getCreateDate() != null) {
            WHERE("create_date=#{projMem.createDate}");
        }
        if (projMem.getModifyDate() != null) {
            WHERE("modify_date=#{projMem.modifyDate}");
        }
        if (projMem.getProjId() != null) {
            WHERE("proj_id=#{projMem.projId}");
        }
        if (projMem.getUserId() != null) {
            WHERE("user_id=#{projMem.userId}");
        }
        if (StringUtils.isNotBlank(projMem.getRole())){
            WHERE("role=#{projMem.role}");
        }
        if (projMem.getProjRoleId() != null) {
            WHERE("proj_role_id=#{projMem.projRoleId}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        ProjMemDO projMem = (ProjMemDO) params.get("projMem");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (projMem.getId() != null) {
            WHERE("id=#{projMem.id}");
        }
        if (projMem.getCreateDate() != null) {
            WHERE("create_date=#{projMem.createDate}");
        }
        if (projMem.getModifyDate() != null) {
            WHERE("modify_date=#{projMem.modifyDate}");
        }
        if (projMem.getProjId() != null) {
            WHERE("proj_id=#{projMem.projId}");
        }
        if (projMem.getUserId() != null) {
            WHERE("user_id=#{projMem.userId}");
        }
        if (StringUtils.isNotBlank(projMem.getRole())){
            WHERE("role=#{projMem.role}");
        }
        if (projMem.getProjRoleId() != null) {
            WHERE("proj_role_id=#{projMem.projRoleId}");
        }
        }}.toString();
    }
}

