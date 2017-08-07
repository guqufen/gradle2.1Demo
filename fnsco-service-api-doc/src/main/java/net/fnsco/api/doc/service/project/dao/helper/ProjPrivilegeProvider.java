package net.fnsco.api.doc.service.project.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.api.doc.service.project.entity.ProjPrivilegeDO;
public class ProjPrivilegeProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_proj_privilege";

    public String update(Map<String, Object> params) {
        ProjPrivilegeDO projPrivilege = (ProjPrivilegeDO) params.get("projPrivilege");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (projPrivilege.getCreateDate() != null) {
            SET("create_date=#{projPrivilege.createDate}");
        }
        if (projPrivilege.getModifyDate() != null) {
            SET("modify_date=#{projPrivilege.modifyDate}");
        }
        if (StringUtils.isNotBlank(projPrivilege.getCode())){
            SET("code=#{projPrivilege.code}");
        }
        if (StringUtils.isNotBlank(projPrivilege.getName())){
            SET("name=#{projPrivilege.name}");
        }
        if (StringUtils.isNotBlank(projPrivilege.getDescription())){
            SET("description=#{projPrivilege.description}");
        }
        WHERE("id = #{projPrivilege.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        ProjPrivilegeDO projPrivilege = (ProjPrivilegeDO) params.get("projPrivilege");
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
        if (projPrivilege.getId() != null) {
            WHERE("id=#{projPrivilege.id}");
        }
        if (projPrivilege.getCreateDate() != null) {
            WHERE("create_date=#{projPrivilege.createDate}");
        }
        if (projPrivilege.getModifyDate() != null) {
            WHERE("modify_date=#{projPrivilege.modifyDate}");
        }
        if (StringUtils.isNotBlank(projPrivilege.getCode())){
            WHERE("code=#{projPrivilege.code}");
        }
        if (StringUtils.isNotBlank(projPrivilege.getName())){
            WHERE("name=#{projPrivilege.name}");
        }
        if (StringUtils.isNotBlank(projPrivilege.getDescription())){
            WHERE("description=#{projPrivilege.description}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        ProjPrivilegeDO projPrivilege = (ProjPrivilegeDO) params.get("projPrivilege");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (projPrivilege.getId() != null) {
            WHERE("id=#{projPrivilege.id}");
        }
        if (projPrivilege.getCreateDate() != null) {
            WHERE("create_date=#{projPrivilege.createDate}");
        }
        if (projPrivilege.getModifyDate() != null) {
            WHERE("modify_date=#{projPrivilege.modifyDate}");
        }
        if (StringUtils.isNotBlank(projPrivilege.getCode())){
            WHERE("code=#{projPrivilege.code}");
        }
        if (StringUtils.isNotBlank(projPrivilege.getName())){
            WHERE("name=#{projPrivilege.name}");
        }
        if (StringUtils.isNotBlank(projPrivilege.getDescription())){
            WHERE("description=#{projPrivilege.description}");
        }
        }}.toString();
    }
}

