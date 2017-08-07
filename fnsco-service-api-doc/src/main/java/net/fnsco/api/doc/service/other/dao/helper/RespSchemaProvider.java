package net.fnsco.api.doc.service.other.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.api.doc.service.other.entity.RespSchemaDO;
public class RespSchemaProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_resp_schema";

    public String update(Map<String, Object> params) {
        RespSchemaDO respSchema = (RespSchemaDO) params.get("respSchema");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (respSchema.getCreateDate() != null) {
            SET("create_date=#{respSchema.createDate}");
        }
        if (respSchema.getModifyDate() != null) {
            SET("modify_date=#{respSchema.modifyDate}");
        }
        if (respSchema.getDocId() != null) {
            SET("doc_id=#{respSchema.docId}");
        }
        if (respSchema.getModuleId() != null) {
            SET("module_id=#{respSchema.moduleId}");
        }
        if (StringUtils.isNotBlank(respSchema.getCode())){
            SET("code=#{respSchema.code}");
        }
        if (StringUtils.isNotBlank(respSchema.getName())){
            SET("name=#{respSchema.name}");
        }
        if (StringUtils.isNotBlank(respSchema.getDescription())){
            SET("description=#{respSchema.description}");
        }
        if (StringUtils.isNotBlank(respSchema.getCustSchema())){
            SET("cust_schema=#{respSchema.custSchema}");
        }
        if (StringUtils.isNotBlank(respSchema.getType())){
            SET("type=#{respSchema.type}");
        }
        if (respSchema.getRefSchemaId() != null) {
            SET("ref_schema_id=#{respSchema.refSchemaId}");
        }
        WHERE("id = #{respSchema.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        RespSchemaDO respSchema = (RespSchemaDO) params.get("respSchema");
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
        if (respSchema.getId() != null) {
            WHERE("id=#{respSchema.id}");
        }
        if (respSchema.getCreateDate() != null) {
            WHERE("create_date=#{respSchema.createDate}");
        }
        if (respSchema.getModifyDate() != null) {
            WHERE("modify_date=#{respSchema.modifyDate}");
        }
        if (respSchema.getDocId() != null) {
            WHERE("doc_id=#{respSchema.docId}");
        }
        if (respSchema.getModuleId() != null) {
            WHERE("module_id=#{respSchema.moduleId}");
        }
        if (StringUtils.isNotBlank(respSchema.getCode())){
            WHERE("code=#{respSchema.code}");
        }
        if (StringUtils.isNotBlank(respSchema.getName())){
            WHERE("name=#{respSchema.name}");
        }
        if (StringUtils.isNotBlank(respSchema.getDescription())){
            WHERE("description=#{respSchema.description}");
        }
        if (StringUtils.isNotBlank(respSchema.getCustSchema())){
            WHERE("cust_schema=#{respSchema.custSchema}");
        }
        if (StringUtils.isNotBlank(respSchema.getType())){
            WHERE("type=#{respSchema.type}");
        }
        if (respSchema.getRefSchemaId() != null) {
            WHERE("ref_schema_id=#{respSchema.refSchemaId}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        RespSchemaDO respSchema = (RespSchemaDO) params.get("respSchema");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (respSchema.getId() != null) {
            WHERE("id=#{respSchema.id}");
        }
        if (respSchema.getCreateDate() != null) {
            WHERE("create_date=#{respSchema.createDate}");
        }
        if (respSchema.getModifyDate() != null) {
            WHERE("modify_date=#{respSchema.modifyDate}");
        }
        if (respSchema.getDocId() != null) {
            WHERE("doc_id=#{respSchema.docId}");
        }
        if (respSchema.getModuleId() != null) {
            WHERE("module_id=#{respSchema.moduleId}");
        }
        if (StringUtils.isNotBlank(respSchema.getCode())){
            WHERE("code=#{respSchema.code}");
        }
        if (StringUtils.isNotBlank(respSchema.getName())){
            WHERE("name=#{respSchema.name}");
        }
        if (StringUtils.isNotBlank(respSchema.getDescription())){
            WHERE("description=#{respSchema.description}");
        }
        if (StringUtils.isNotBlank(respSchema.getCustSchema())){
            WHERE("cust_schema=#{respSchema.custSchema}");
        }
        if (StringUtils.isNotBlank(respSchema.getType())){
            WHERE("type=#{respSchema.type}");
        }
        if (respSchema.getRefSchemaId() != null) {
            WHERE("ref_schema_id=#{respSchema.refSchemaId}");
        }
        }}.toString();
    }
}

