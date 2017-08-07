package net.fnsco.api.doc.service.other.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.api.doc.service.other.entity.InterRespDO;
public class InterRespProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_inter_resp";

    public String update(Map<String, Object> params) {
        InterRespDO interResp = (InterRespDO) params.get("interResp");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (interResp.getCreateDate() != null) {
            SET("create_date=#{interResp.createDate}");
        }
        if (interResp.getModifyDate() != null) {
            SET("modify_date=#{interResp.modifyDate}");
        }
        if (interResp.getDocId() != null) {
            SET("doc_id=#{interResp.docId}");
        }
        if (interResp.getInterId() != null) {
            SET("inter_id=#{interResp.interId}");
        }
        if (StringUtils.isNotBlank(interResp.getCode())){
            SET("code=#{interResp.code}");
        }
        if (StringUtils.isNotBlank(interResp.getName())){
            SET("name=#{interResp.name}");
        }
        if (StringUtils.isNotBlank(interResp.getDescription())){
            SET("description=#{interResp.description}");
        }
        if (StringUtils.isNotBlank(interResp.getType())){
            SET("type=#{interResp.type}");
        }
        if (interResp.getRefSchemaId() != null) {
            SET("ref_schema_id=#{interResp.refSchemaId}");
        }
        if (interResp.getDef() != null) {
            SET("def=#{interResp.def}");
        }
        if (interResp.getRequired() != null) {
            SET("required=#{interResp.required}");
        }
        if (StringUtils.isNotBlank(interResp.getCustSchema())){
            SET("cust_schema=#{interResp.custSchema}");
        }
        if (StringUtils.isNotBlank(interResp.getExtSchema())){
            SET("ext_schema=#{interResp.extSchema}");
        }
        if (interResp.getSortWeight() != null) {
            SET("sort_weight=#{interResp.sortWeight}");
        }
        WHERE("id = #{interResp.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        InterRespDO interResp = (InterRespDO) params.get("interResp");
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
        if (interResp.getId() != null) {
            WHERE("id=#{interResp.id}");
        }
        if (interResp.getCreateDate() != null) {
            WHERE("create_date=#{interResp.createDate}");
        }
        if (interResp.getModifyDate() != null) {
            WHERE("modify_date=#{interResp.modifyDate}");
        }
        if (interResp.getDocId() != null) {
            WHERE("doc_id=#{interResp.docId}");
        }
        if (interResp.getInterId() != null) {
            WHERE("inter_id=#{interResp.interId}");
        }
        if (StringUtils.isNotBlank(interResp.getCode())){
            WHERE("code=#{interResp.code}");
        }
        if (StringUtils.isNotBlank(interResp.getName())){
            WHERE("name=#{interResp.name}");
        }
        if (StringUtils.isNotBlank(interResp.getDescription())){
            WHERE("description=#{interResp.description}");
        }
        if (StringUtils.isNotBlank(interResp.getType())){
            WHERE("type=#{interResp.type}");
        }
        if (interResp.getRefSchemaId() != null) {
            WHERE("ref_schema_id=#{interResp.refSchemaId}");
        }
        if (interResp.getDef() != null) {
            WHERE("def=#{interResp.def}");
        }
        if (interResp.getRequired() != null) {
            WHERE("required=#{interResp.required}");
        }
        if (StringUtils.isNotBlank(interResp.getCustSchema())){
            WHERE("cust_schema=#{interResp.custSchema}");
        }
        if (StringUtils.isNotBlank(interResp.getExtSchema())){
            WHERE("ext_schema=#{interResp.extSchema}");
        }
        if (interResp.getSortWeight() != null) {
            WHERE("sort_weight=#{interResp.sortWeight}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        InterRespDO interResp = (InterRespDO) params.get("interResp");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (interResp.getId() != null) {
            WHERE("id=#{interResp.id}");
        }
        if (interResp.getCreateDate() != null) {
            WHERE("create_date=#{interResp.createDate}");
        }
        if (interResp.getModifyDate() != null) {
            WHERE("modify_date=#{interResp.modifyDate}");
        }
        if (interResp.getDocId() != null) {
            WHERE("doc_id=#{interResp.docId}");
        }
        if (interResp.getInterId() != null) {
            WHERE("inter_id=#{interResp.interId}");
        }
        if (StringUtils.isNotBlank(interResp.getCode())){
            WHERE("code=#{interResp.code}");
        }
        if (StringUtils.isNotBlank(interResp.getName())){
            WHERE("name=#{interResp.name}");
        }
        if (StringUtils.isNotBlank(interResp.getDescription())){
            WHERE("description=#{interResp.description}");
        }
        if (StringUtils.isNotBlank(interResp.getType())){
            WHERE("type=#{interResp.type}");
        }
        if (interResp.getRefSchemaId() != null) {
            WHERE("ref_schema_id=#{interResp.refSchemaId}");
        }
        if (interResp.getDef() != null) {
            WHERE("def=#{interResp.def}");
        }
        if (interResp.getRequired() != null) {
            WHERE("required=#{interResp.required}");
        }
        if (StringUtils.isNotBlank(interResp.getCustSchema())){
            WHERE("cust_schema=#{interResp.custSchema}");
        }
        if (StringUtils.isNotBlank(interResp.getExtSchema())){
            WHERE("ext_schema=#{interResp.extSchema}");
        }
        if (interResp.getSortWeight() != null) {
            WHERE("sort_weight=#{interResp.sortWeight}");
        }
        }}.toString();
    }
}

