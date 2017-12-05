package net.fnsco.api.doc.service.project.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.api.doc.service.project.entity.ProjDO;
public class ProjProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_proj";

    public String update(Map<String, Object> params) {
        ProjDO proj = (ProjDO) params.get("proj");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (proj.getCreateDate() != null) {
            SET("create_date=#{proj.createDate}");
        }
        if (proj.getModifyDate() != null) {
            SET("modify_date=#{proj.modifyDate}");
        }
        if (proj.getUserId() != null) {
            SET("user_id=#{proj.userId}");
        }
        if (StringUtils.isNotBlank(proj.getCode())){
            SET("code=#{proj.code}");
        }
        if (StringUtils.isNotBlank(proj.getName())){
            SET("name=#{proj.name}");
        }
        if (StringUtils.isNotBlank(proj.getDescription())){
            SET("description=#{proj.description}");
        }
        if (StringUtils.isNotBlank(proj.getStatus())){
            SET("status=#{proj.status}");
        }
        if (StringUtils.isNotBlank(proj.getUrl())){
            SET("url=#{proj.url}");
        }
        WHERE("id = #{proj.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        ProjDO proj = (ProjDO) params.get("proj");
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
        if (proj.getId() != null) {
            WHERE("id=#{proj.id}");
        }
        if (proj.getCreateDate() != null) {
            WHERE("create_date=#{proj.createDate}");
        }
        if (proj.getModifyDate() != null) {
            WHERE("modify_date=#{proj.modifyDate}");
        }
        if (proj.getUserId() != null) {
            WHERE("user_id=#{proj.userId}");
        }
        if (StringUtils.isNotBlank(proj.getCode())){
            WHERE("code=#{proj.code}");
        }
        if (StringUtils.isNotBlank(proj.getName())){
            WHERE("name=#{proj.name}");
        }
        if (StringUtils.isNotBlank(proj.getDescription())){
            WHERE("description=#{proj.description}");
        }
        if (StringUtils.isNotBlank(proj.getStatus())){
            WHERE("status=#{proj.status}");
        }
        ORDER_BY("name  limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        ProjDO proj = (ProjDO) params.get("proj");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (proj.getId() != null) {
            WHERE("id=#{proj.id}");
        }
        if (proj.getCreateDate() != null) {
            WHERE("create_date=#{proj.createDate}");
        }
        if (proj.getModifyDate() != null) {
            WHERE("modify_date=#{proj.modifyDate}");
        }
        if (proj.getUserId() != null) {
            WHERE("user_id=#{proj.userId}");
        }
        if (StringUtils.isNotBlank(proj.getCode())){
            WHERE("code=#{proj.code}");
        }
        if (StringUtils.isNotBlank(proj.getName())){
            WHERE("name=#{proj.name}");
        }
        if (StringUtils.isNotBlank(proj.getDescription())){
            WHERE("description=#{proj.description}");
        }
        if (StringUtils.isNotBlank(proj.getStatus())){
            WHERE("status=#{proj.status}");
        }
        }}.toString();
    }
}

