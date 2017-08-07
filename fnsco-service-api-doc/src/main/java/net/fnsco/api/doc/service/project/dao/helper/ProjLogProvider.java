package net.fnsco.api.doc.service.project.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.api.doc.service.project.entity.ProjLogDO;
public class ProjLogProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_proj_log";

    public String update(Map<String, Object> params) {
        ProjLogDO projLog = (ProjLogDO) params.get("projLog");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (projLog.getCreateDate() != null) {
            SET("create_date=#{projLog.createDate}");
        }
        if (projLog.getModifyDate() != null) {
            SET("modify_date=#{projLog.modifyDate}");
        }
        if (projLog.getProjId() != null) {
            SET("proj_id=#{projLog.projId}");
        }
        if (projLog.getUserId() != null) {
            SET("user_id=#{projLog.userId}");
        }
        if (projLog.getPubDate() != null) {
            SET("pub_date=#{projLog.pubDate}");
        }
        if (StringUtils.isNotBlank(projLog.getTitle())){
            SET("title=#{projLog.title}");
        }
        if (StringUtils.isNotBlank(projLog.getContent())){
            SET("content=#{projLog.content}");
        }
        WHERE("id = #{projLog.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        ProjLogDO projLog = (ProjLogDO) params.get("projLog");
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
        if (projLog.getId() != null) {
            WHERE("id=#{projLog.id}");
        }
        if (projLog.getCreateDate() != null) {
            WHERE("create_date=#{projLog.createDate}");
        }
        if (projLog.getModifyDate() != null) {
            WHERE("modify_date=#{projLog.modifyDate}");
        }
        if (projLog.getProjId() != null) {
            WHERE("proj_id=#{projLog.projId}");
        }
        if (projLog.getUserId() != null) {
            WHERE("user_id=#{projLog.userId}");
        }
        if (projLog.getPubDate() != null) {
            WHERE("pub_date=#{projLog.pubDate}");
        }
        if (StringUtils.isNotBlank(projLog.getTitle())){
            WHERE("title=#{projLog.title}");
        }
        if (StringUtils.isNotBlank(projLog.getContent())){
            WHERE("content=#{projLog.content}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        ProjLogDO projLog = (ProjLogDO) params.get("projLog");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (projLog.getId() != null) {
            WHERE("id=#{projLog.id}");
        }
        if (projLog.getCreateDate() != null) {
            WHERE("create_date=#{projLog.createDate}");
        }
        if (projLog.getModifyDate() != null) {
            WHERE("modify_date=#{projLog.modifyDate}");
        }
        if (projLog.getProjId() != null) {
            WHERE("proj_id=#{projLog.projId}");
        }
        if (projLog.getUserId() != null) {
            WHERE("user_id=#{projLog.userId}");
        }
        if (projLog.getPubDate() != null) {
            WHERE("pub_date=#{projLog.pubDate}");
        }
        if (StringUtils.isNotBlank(projLog.getTitle())){
            WHERE("title=#{projLog.title}");
        }
        if (StringUtils.isNotBlank(projLog.getContent())){
            WHERE("content=#{projLog.content}");
        }
        }}.toString();
    }
}

