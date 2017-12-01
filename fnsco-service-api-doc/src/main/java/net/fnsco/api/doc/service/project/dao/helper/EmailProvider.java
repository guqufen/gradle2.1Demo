package net.fnsco.api.doc.service.project.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.api.doc.service.project.entity.EmailDO;

import org.apache.commons.lang3.StringUtils;

public class EmailProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_email";

    public String update(Map<String, Object> params) {
        EmailDO email = (EmailDO) params.get("email");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(email.getEmailName())){
            SET("email_name=#{email.emailName}");
        }
        if (StringUtils.isNotBlank(email.getSubject())){
            SET("subject=#{email.subject}");
        }
        if (email.getRoleType() != null) {
            SET("role_type=#{email.roleType}");
        }
        if (StringUtils.isNotBlank(email.getOtherSubject())){
            SET("other_subject=#{email.otherSubject}");
        }
        if (StringUtils.isNotBlank(email.getContent())){
            SET("content=#{email.content}");
        }
        if (email.getCreateDate() != null) {
            SET("create_date=#{email.createDate}");
        }
        if (email.getModifyDate() != null) {
            SET("modify_date=#{email.modifyDate}");
        }
        if (email.getUserId() != null) {
            SET("user_id=#{email.userId}");
        }
        WHERE("id = #{email.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        EmailDO email = (EmailDO) params.get("email");
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
        if (email.getId() != null) {
            WHERE("id=#{email.id}");
        }
        if (StringUtils.isNotBlank(email.getEmailName())){
            WHERE("email_name=#{email.emailName}");
        }
        if (StringUtils.isNotBlank(email.getSubject())){
            WHERE("subject=#{email.subject}");
        }
        if (email.getRoleType() != null) {
            WHERE("role_type=#{email.roleType}");
        }
        if (StringUtils.isNotBlank(email.getOtherSubject())){
            WHERE("other_subject=#{email.otherSubject}");
        }
        if (StringUtils.isNotBlank(email.getContent())){
            WHERE("content=#{email.content}");
        }
        if (email.getCreateDate() != null) {
            WHERE("create_date=#{email.createDate}");
        }
        if (email.getModifyDate() != null) {
            WHERE("modify_date=#{email.modifyDate}");
        }
        if (email.getUserId() != null) {
            WHERE("user_id=#{email.userId}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        EmailDO email = (EmailDO) params.get("email");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (email.getId() != null) {
            WHERE("id=#{email.id}");
        }
        if (StringUtils.isNotBlank(email.getEmailName())){
            WHERE("email_name=#{email.emailName}");
        }
        if (StringUtils.isNotBlank(email.getSubject())){
            WHERE("subject=#{email.subject}");
        }
        if (email.getRoleType() != null) {
            WHERE("role_type=#{email.roleType}");
        }
        if (StringUtils.isNotBlank(email.getOtherSubject())){
            WHERE("other_subject=#{email.otherSubject}");
        }
        if (StringUtils.isNotBlank(email.getContent())){
            WHERE("content=#{email.content}");
        }
        if (email.getCreateDate() != null) {
            WHERE("create_date=#{email.createDate}");
        }
        if (email.getModifyDate() != null) {
            WHERE("modify_date=#{email.modifyDate}");
        }
        if (email.getUserId() != null) {
            WHERE("user_id=#{email.userId}");
        }
        }}.toString();
    }
}

