package net.fnsco.api.doc.service.other.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.api.doc.service.other.entity.SuggestDO;
public class SuggestProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_suggest";

    public String update(Map<String, Object> params) {
        SuggestDO suggest = (SuggestDO) params.get("suggest");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (suggest.getCreateDate() != null) {
            SET("create_date=#{suggest.createDate}");
        }
        if (suggest.getModifyDate() != null) {
            SET("modify_date=#{suggest.modifyDate}");
        }
        if (suggest.getUserId() != null) {
            SET("user_id=#{suggest.userId}");
        }
        if (StringUtils.isNotBlank(suggest.getTitle())){
            SET("title=#{suggest.title}");
        }
        if (StringUtils.isNotBlank(suggest.getContent())){
            SET("content=#{suggest.content}");
        }
        if (suggest.getDealDate() != null) {
            SET("deal_date=#{suggest.dealDate}");
        }
        if (StringUtils.isNotBlank(suggest.getStatus())){
            SET("status=#{suggest.status}");
        }
        if (StringUtils.isNotBlank(suggest.getType())){
            SET("type=#{suggest.type}");
        }
        WHERE("id = #{suggest.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        SuggestDO suggest = (SuggestDO) params.get("suggest");
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
        if (suggest.getId() != null) {
            WHERE("id=#{suggest.id}");
        }
        if (suggest.getCreateDate() != null) {
            WHERE("create_date=#{suggest.createDate}");
        }
        if (suggest.getModifyDate() != null) {
            WHERE("modify_date=#{suggest.modifyDate}");
        }
        if (suggest.getUserId() != null) {
            WHERE("user_id=#{suggest.userId}");
        }
        if (StringUtils.isNotBlank(suggest.getTitle())){
            WHERE("title=#{suggest.title}");
        }
        if (StringUtils.isNotBlank(suggest.getContent())){
            WHERE("content=#{suggest.content}");
        }
        if (suggest.getDealDate() != null) {
            WHERE("deal_date=#{suggest.dealDate}");
        }
        if (StringUtils.isNotBlank(suggest.getStatus())){
            WHERE("status=#{suggest.status}");
        }
        if (StringUtils.isNotBlank(suggest.getType())){
            WHERE("type=#{suggest.type}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        SuggestDO suggest = (SuggestDO) params.get("suggest");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (suggest.getId() != null) {
            WHERE("id=#{suggest.id}");
        }
        if (suggest.getCreateDate() != null) {
            WHERE("create_date=#{suggest.createDate}");
        }
        if (suggest.getModifyDate() != null) {
            WHERE("modify_date=#{suggest.modifyDate}");
        }
        if (suggest.getUserId() != null) {
            WHERE("user_id=#{suggest.userId}");
        }
        if (StringUtils.isNotBlank(suggest.getTitle())){
            WHERE("title=#{suggest.title}");
        }
        if (StringUtils.isNotBlank(suggest.getContent())){
            WHERE("content=#{suggest.content}");
        }
        if (suggest.getDealDate() != null) {
            WHERE("deal_date=#{suggest.dealDate}");
        }
        if (StringUtils.isNotBlank(suggest.getStatus())){
            WHERE("status=#{suggest.status}");
        }
        if (StringUtils.isNotBlank(suggest.getType())){
            WHERE("type=#{suggest.type}");
        }
        }}.toString();
    }
}

