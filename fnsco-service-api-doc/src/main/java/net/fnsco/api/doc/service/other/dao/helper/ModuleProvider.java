package net.fnsco.api.doc.service.other.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.api.doc.service.other.entity.ModuleDO;
public class ModuleProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_module";

    public String update(Map<String, Object> params) {
        ModuleDO module = (ModuleDO) params.get("module");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (module.getCreateDate() != null) {
            SET("create_date=#{module.createDate}");
        }
        if (module.getModifyDate() != null) {
            SET("modify_date=#{module.modifyDate}");
        }
        if (module.getDocId() != null) {
            SET("doc_id=#{module.docId}");
        }
        if (StringUtils.isNotBlank(module.getCode())){
            SET("code=#{module.code}");
        }
        if (StringUtils.isNotBlank(module.getName())){
            SET("name=#{module.name}");
        }
        if (StringUtils.isNotBlank(module.getDescription())){
            SET("description=#{module.description}");
        }
        if (module.getSortWeight() != null) {
            SET("sort_weight=#{module.sortWeight}");
        }
        WHERE("id = #{module.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        ModuleDO module = (ModuleDO) params.get("module");
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
        if (module.getId() != null) {
            WHERE("id=#{module.id}");
        }
        if (module.getCreateDate() != null) {
            WHERE("create_date=#{module.createDate}");
        }
        if (module.getModifyDate() != null) {
            WHERE("modify_date=#{module.modifyDate}");
        }
        if (module.getDocId() != null) {
            WHERE("doc_id=#{module.docId}");
        }
        if (StringUtils.isNotBlank(module.getCode())){
            WHERE("code=#{module.code}");
        }
        if (StringUtils.isNotBlank(module.getName())){
            WHERE("name=#{module.name}");
        }
        if (StringUtils.isNotBlank(module.getDescription())){
            WHERE("description=#{module.description}");
        }
        if (module.getSortWeight() != null) {
            WHERE("sort_weight=#{module.sortWeight}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        ModuleDO module = (ModuleDO) params.get("module");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (module.getId() != null) {
            WHERE("id=#{module.id}");
        }
        if (module.getCreateDate() != null) {
            WHERE("create_date=#{module.createDate}");
        }
        if (module.getModifyDate() != null) {
            WHERE("modify_date=#{module.modifyDate}");
        }
        if (module.getDocId() != null) {
            WHERE("doc_id=#{module.docId}");
        }
        if (StringUtils.isNotBlank(module.getCode())){
            WHERE("code=#{module.code}");
        }
        if (StringUtils.isNotBlank(module.getName())){
            WHERE("name=#{module.name}");
        }
        if (StringUtils.isNotBlank(module.getDescription())){
            WHERE("description=#{module.description}");
        }
        if (module.getSortWeight() != null) {
            WHERE("sort_weight=#{module.sortWeight}");
        }
        }}.toString();
    }
}

