package net.fnsco.api.doc.service.other.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.api.doc.service.other.entity.InterDO;
public class InterProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_inter";

    public String update(Map<String, Object> params) {
        InterDO inter = (InterDO) params.get("inter");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (inter.getCreateDate() != null) {
            SET("create_date=#{inter.createDate}");
        }
        if (inter.getModifyDate() != null) {
            SET("modify_date=#{inter.modifyDate}");
        }
        if (inter.getDocId() != null) {
            SET("doc_id=#{inter.docId}");
        }
        if (inter.getModuleId() != null) {
            SET("module_id=#{inter.moduleId}");
        }
        if (StringUtils.isNotBlank(inter.getName())){
            SET("name=#{inter.name}");
        }
        if (StringUtils.isNotBlank(inter.getPath())){
            SET("path=#{inter.path}");
        }
        if (StringUtils.isNotBlank(inter.getMethod())){
            SET("method=#{inter.method}");
        }
        if (StringUtils.isNotBlank(inter.getScheme())){
            SET("scheme=#{inter.scheme}");
        }
        if (StringUtils.isNotBlank(inter.getSummary())){
            SET("summary=#{inter.summary}");
        }
        if (StringUtils.isNotBlank(inter.getDescription())){
            SET("description=#{inter.description}");
        }
        if (StringUtils.isNotBlank(inter.getConsume())){
            SET("consume=#{inter.consume}");
        }
        if (StringUtils.isNotBlank(inter.getProduce())){
            SET("produce=#{inter.produce}");
        }
        if (inter.getDeprecated() != null) {
            SET("deprecated=#{inter.deprecated}");
        }
        if (inter.getSortWeight() != null) {
            SET("sort_weight=#{inter.sortWeight}");
        }
        WHERE("id = #{inter.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        InterDO inter = (InterDO) params.get("inter");
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
        if (inter.getId() != null) {
            WHERE("id=#{inter.id}");
        }
        if (inter.getCreateDate() != null) {
            WHERE("create_date=#{inter.createDate}");
        }
        if (inter.getModifyDate() != null) {
            WHERE("modify_date=#{inter.modifyDate}");
        }
        if (inter.getDocId() != null) {
            WHERE("doc_id=#{inter.docId}");
        }
        if (inter.getModuleId() != null) {
            WHERE("module_id=#{inter.moduleId}");
        }
        if (StringUtils.isNotBlank(inter.getName())){
            WHERE("name=#{inter.name}");
        }
        if (StringUtils.isNotBlank(inter.getPath())){
            WHERE("path=#{inter.path}");
        }
        if (StringUtils.isNotBlank(inter.getMethod())){
            WHERE("method=#{inter.method}");
        }
        if (StringUtils.isNotBlank(inter.getScheme())){
            WHERE("scheme=#{inter.scheme}");
        }
        if (StringUtils.isNotBlank(inter.getSummary())){
            WHERE("summary=#{inter.summary}");
        }
        if (StringUtils.isNotBlank(inter.getDescription())){
            WHERE("description=#{inter.description}");
        }
        if (StringUtils.isNotBlank(inter.getConsume())){
            WHERE("consume=#{inter.consume}");
        }
        if (StringUtils.isNotBlank(inter.getProduce())){
            WHERE("produce=#{inter.produce}");
        }
        if (inter.getDeprecated() != null) {
            WHERE("deprecated=#{inter.deprecated}");
        }
        if (inter.getSortWeight() != null) {
            WHERE("sort_weight=#{inter.sortWeight}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        InterDO inter = (InterDO) params.get("inter");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (inter.getId() != null) {
            WHERE("id=#{inter.id}");
        }
        if (inter.getCreateDate() != null) {
            WHERE("create_date=#{inter.createDate}");
        }
        if (inter.getModifyDate() != null) {
            WHERE("modify_date=#{inter.modifyDate}");
        }
        if (inter.getDocId() != null) {
            WHERE("doc_id=#{inter.docId}");
        }
        if (inter.getModuleId() != null) {
            WHERE("module_id=#{inter.moduleId}");
        }
        if (StringUtils.isNotBlank(inter.getName())){
            WHERE("name=#{inter.name}");
        }
        if (StringUtils.isNotBlank(inter.getPath())){
            WHERE("path=#{inter.path}");
        }
        if (StringUtils.isNotBlank(inter.getMethod())){
            WHERE("method=#{inter.method}");
        }
        if (StringUtils.isNotBlank(inter.getScheme())){
            WHERE("scheme=#{inter.scheme}");
        }
        if (StringUtils.isNotBlank(inter.getSummary())){
            WHERE("summary=#{inter.summary}");
        }
        if (StringUtils.isNotBlank(inter.getDescription())){
            WHERE("description=#{inter.description}");
        }
        if (StringUtils.isNotBlank(inter.getConsume())){
            WHERE("consume=#{inter.consume}");
        }
        if (StringUtils.isNotBlank(inter.getProduce())){
            WHERE("produce=#{inter.produce}");
        }
        if (inter.getDeprecated() != null) {
            WHERE("deprecated=#{inter.deprecated}");
        }
        if (inter.getSortWeight() != null) {
            WHERE("sort_weight=#{inter.sortWeight}");
        }
        }}.toString();
    }
}

