package net.fnsco.api.doc.service.project.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.api.doc.service.project.entity.ProjRoleDO;
public class ProjRoleProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_proj_role";

    public String update(Map<String, Object> params) {
        ProjRoleDO projRole = (ProjRoleDO) params.get("projRole");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (projRole.getCreateDate() != null) {
            SET("create_date=#{projRole.createDate}");
        }
        if (projRole.getModifyDate() != null) {
            SET("modify_date=#{projRole.modifyDate}");
        }
        if (StringUtils.isNotBlank(projRole.getCode())){
            SET("code=#{projRole.code}");
        }
        if (StringUtils.isNotBlank(projRole.getName())){
            SET("name=#{projRole.name}");
        }
        if (StringUtils.isNotBlank(projRole.getDescription())){
            SET("description=#{projRole.description}");
        }
        WHERE("id = #{projRole.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        ProjRoleDO projRole = (ProjRoleDO) params.get("projRole");
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
        if (projRole.getId() != null) {
            WHERE("id=#{projRole.id}");
        }
        if (projRole.getCreateDate() != null) {
            WHERE("create_date=#{projRole.createDate}");
        }
        if (projRole.getModifyDate() != null) {
            WHERE("modify_date=#{projRole.modifyDate}");
        }
        if (StringUtils.isNotBlank(projRole.getCode())){
            WHERE("code=#{projRole.code}");
        }
        if (StringUtils.isNotBlank(projRole.getName())){
            WHERE("name=#{projRole.name}");
        }
        if (StringUtils.isNotBlank(projRole.getDescription())){
            WHERE("description=#{projRole.description}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        ProjRoleDO projRole = (ProjRoleDO) params.get("projRole");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (projRole.getId() != null) {
            WHERE("id=#{projRole.id}");
        }
        if (projRole.getCreateDate() != null) {
            WHERE("create_date=#{projRole.createDate}");
        }
        if (projRole.getModifyDate() != null) {
            WHERE("modify_date=#{projRole.modifyDate}");
        }
        if (StringUtils.isNotBlank(projRole.getCode())){
            WHERE("code=#{projRole.code}");
        }
        if (StringUtils.isNotBlank(projRole.getName())){
            WHERE("name=#{projRole.name}");
        }
        if (StringUtils.isNotBlank(projRole.getDescription())){
            WHERE("description=#{projRole.description}");
        }
        }}.toString();
    }
}

