package net.fnsco.api.doc.service.other.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.api.doc.service.other.entity.InterParamDO;
public class InterParamProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_inter_param";

    public String update(Map<String, Object> params) {
        InterParamDO interParam = (InterParamDO) params.get("interParam");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (interParam.getCreateDate() != null) {
            SET("create_date=#{interParam.createDate}");
        }
        if (interParam.getModifyDate() != null) {
            SET("modify_date=#{interParam.modifyDate}");
        }
        if (interParam.getDocId() != null) {
            SET("doc_id=#{interParam.docId}");
        }
        if (interParam.getInterId() != null) {
            SET("inter_id=#{interParam.interId}");
        }
        if (StringUtils.isNotBlank(interParam.getCode())){
            SET("code=#{interParam.code}");
        }
        if (StringUtils.isNotBlank(interParam.getName())){
            SET("name=#{interParam.name}");
        }
        if (StringUtils.isNotBlank(interParam.getDescription())){
            SET("description=#{interParam.description}");
        }
        if (StringUtils.isNotBlank(interParam.getType())){
            SET("type=#{interParam.type}");
        }
        if (StringUtils.isNotBlank(interParam.getFormat())){
            SET("format=#{interParam.format}");
        }
        if (StringUtils.isNotBlank(interParam.getPosition())){
            SET("position=#{interParam.position}");
        }
        if (interParam.getRequired() != null) {
            SET("required=#{interParam.required}");
        }
        if (StringUtils.isNotBlank(interParam.getCustSchema())){
            SET("cust_schema=#{interParam.custSchema}");
        }
        if (StringUtils.isNotBlank(interParam.getExtSchema())){
            SET("ext_schema=#{interParam.extSchema}");
        }
        if (interParam.getRefSchemaId() != null) {
            SET("ref_schema_id=#{interParam.refSchemaId}");
        }
        if (StringUtils.isNotBlank(interParam.getDefValue())){
            SET("def_value=#{interParam.defValue}");
        }
        WHERE("id = #{interParam.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        InterParamDO interParam = (InterParamDO) params.get("interParam");
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
        if (interParam.getId() != null) {
            WHERE("id=#{interParam.id}");
        }
        if (interParam.getCreateDate() != null) {
            WHERE("create_date=#{interParam.createDate}");
        }
        if (interParam.getModifyDate() != null) {
            WHERE("modify_date=#{interParam.modifyDate}");
        }
        if (interParam.getDocId() != null) {
            WHERE("doc_id=#{interParam.docId}");
        }
        if (interParam.getInterId() != null) {
            WHERE("inter_id=#{interParam.interId}");
        }
        if (StringUtils.isNotBlank(interParam.getCode())){
            WHERE("code=#{interParam.code}");
        }
        if (StringUtils.isNotBlank(interParam.getName())){
            WHERE("name=#{interParam.name}");
        }
        if (StringUtils.isNotBlank(interParam.getDescription())){
            WHERE("description=#{interParam.description}");
        }
        if (StringUtils.isNotBlank(interParam.getType())){
            WHERE("type=#{interParam.type}");
        }
        if (StringUtils.isNotBlank(interParam.getFormat())){
            WHERE("format=#{interParam.format}");
        }
        if (StringUtils.isNotBlank(interParam.getPosition())){
            WHERE("position=#{interParam.position}");
        }
        if (interParam.getRequired() != null) {
            WHERE("required=#{interParam.required}");
        }
        if (StringUtils.isNotBlank(interParam.getCustSchema())){
            WHERE("cust_schema=#{interParam.custSchema}");
        }
        if (StringUtils.isNotBlank(interParam.getExtSchema())){
            WHERE("ext_schema=#{interParam.extSchema}");
        }
        if (interParam.getRefSchemaId() != null) {
            WHERE("ref_schema_id=#{interParam.refSchemaId}");
        }
        if (StringUtils.isNotBlank(interParam.getDefValue())){
            WHERE("def_value=#{interParam.defValue}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        InterParamDO interParam = (InterParamDO) params.get("interParam");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (interParam.getId() != null) {
            WHERE("id=#{interParam.id}");
        }
        if (interParam.getCreateDate() != null) {
            WHERE("create_date=#{interParam.createDate}");
        }
        if (interParam.getModifyDate() != null) {
            WHERE("modify_date=#{interParam.modifyDate}");
        }
        if (interParam.getDocId() != null) {
            WHERE("doc_id=#{interParam.docId}");
        }
        if (interParam.getInterId() != null) {
            WHERE("inter_id=#{interParam.interId}");
        }
        if (StringUtils.isNotBlank(interParam.getCode())){
            WHERE("code=#{interParam.code}");
        }
        if (StringUtils.isNotBlank(interParam.getName())){
            WHERE("name=#{interParam.name}");
        }
        if (StringUtils.isNotBlank(interParam.getDescription())){
            WHERE("description=#{interParam.description}");
        }
        if (StringUtils.isNotBlank(interParam.getType())){
            WHERE("type=#{interParam.type}");
        }
        if (StringUtils.isNotBlank(interParam.getFormat())){
            WHERE("format=#{interParam.format}");
        }
        if (StringUtils.isNotBlank(interParam.getPosition())){
            WHERE("position=#{interParam.position}");
        }
        if (interParam.getRequired() != null) {
            WHERE("required=#{interParam.required}");
        }
        if (StringUtils.isNotBlank(interParam.getCustSchema())){
            WHERE("cust_schema=#{interParam.custSchema}");
        }
        if (StringUtils.isNotBlank(interParam.getExtSchema())){
            WHERE("ext_schema=#{interParam.extSchema}");
        }
        if (interParam.getRefSchemaId() != null) {
            WHERE("ref_schema_id=#{interParam.refSchemaId}");
        }
        if (StringUtils.isNotBlank(interParam.getDefValue())){
            WHERE("def_value=#{interParam.defValue}");
        }
        }}.toString();
    }
}

