package net.fnsco.api.doc.service.other.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.api.doc.service.other.entity.ApiDocDO;
public class ApiDocProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_api_doc";

    public String update(Map<String, Object> params) {
        ApiDocDO apiDoc = (ApiDocDO) params.get("apiDoc");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (apiDoc.getCreateDate() != null) {
            SET("create_date=#{apiDoc.createDate}");
        }
        if (apiDoc.getModifyDate() != null) {
            SET("modify_date=#{apiDoc.modifyDate}");
        }
        if (apiDoc.getProjId() != null) {
            SET("proj_id=#{apiDoc.projId}");
        }
        if (StringUtils.isNotBlank(apiDoc.getTitle())){
            SET("title=#{apiDoc.title}");
        }
        if (StringUtils.isNotBlank(apiDoc.getDescription())){
            SET("description=#{apiDoc.description}");
        }
        if (StringUtils.isNotBlank(apiDoc.getHost())){
            SET("host=#{apiDoc.host}");
        }
        if (StringUtils.isNotBlank(apiDoc.getBasePath())){
            SET("base_path=#{apiDoc.basePath}");
        }
        if (apiDoc.getPub() != null) {
            SET("pub=#{apiDoc.pub}");
        }
        if (apiDoc.getOpen() != null) {
            SET("open=#{apiDoc.open}");
        }
        if (StringUtils.isNotBlank(apiDoc.getScheme())){
            SET("scheme=#{apiDoc.scheme}");
        }
        if (StringUtils.isNotBlank(apiDoc.getConsume())){
            SET("consume=#{apiDoc.consume}");
        }
        if (StringUtils.isNotBlank(apiDoc.getProduce())){
            SET("produce=#{apiDoc.produce}");
        }
        if (StringUtils.isNotBlank(apiDoc.getVersion())){
            SET("version=#{apiDoc.version}");
        }
        WHERE("id = #{apiDoc.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        ApiDocDO apiDoc = (ApiDocDO) params.get("apiDoc");
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
        if (apiDoc.getId() != null) {
            WHERE("id=#{apiDoc.id}");
        }
        if (apiDoc.getCreateDate() != null) {
            WHERE("create_date=#{apiDoc.createDate}");
        }
        if (apiDoc.getModifyDate() != null) {
            WHERE("modify_date=#{apiDoc.modifyDate}");
        }
        if (apiDoc.getProjId() != null) {
            WHERE("proj_id=#{apiDoc.projId}");
        }
        if (StringUtils.isNotBlank(apiDoc.getTitle())){
            WHERE("title=#{apiDoc.title}");
        }
        if (StringUtils.isNotBlank(apiDoc.getDescription())){
            WHERE("description=#{apiDoc.description}");
        }
        if (StringUtils.isNotBlank(apiDoc.getHost())){
            WHERE("host=#{apiDoc.host}");
        }
        if (StringUtils.isNotBlank(apiDoc.getBasePath())){
            WHERE("base_path=#{apiDoc.basePath}");
        }
        if (apiDoc.getPub() != null) {
            WHERE("pub=#{apiDoc.pub}");
        }
        if (apiDoc.getOpen() != null) {
            WHERE("open=#{apiDoc.open}");
        }
        if (StringUtils.isNotBlank(apiDoc.getScheme())){
            WHERE("scheme=#{apiDoc.scheme}");
        }
        if (StringUtils.isNotBlank(apiDoc.getConsume())){
            WHERE("consume=#{apiDoc.consume}");
        }
        if (StringUtils.isNotBlank(apiDoc.getProduce())){
            WHERE("produce=#{apiDoc.produce}");
        }
        if (StringUtils.isNotBlank(apiDoc.getVersion())){
            WHERE("version=#{apiDoc.version}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        ApiDocDO apiDoc = (ApiDocDO) params.get("apiDoc");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (apiDoc.getId() != null) {
            WHERE("id=#{apiDoc.id}");
        }
        if (apiDoc.getCreateDate() != null) {
            WHERE("create_date=#{apiDoc.createDate}");
        }
        if (apiDoc.getModifyDate() != null) {
            WHERE("modify_date=#{apiDoc.modifyDate}");
        }
        if (apiDoc.getProjId() != null) {
            WHERE("proj_id=#{apiDoc.projId}");
        }
        if (StringUtils.isNotBlank(apiDoc.getTitle())){
            WHERE("title=#{apiDoc.title}");
        }
        if (StringUtils.isNotBlank(apiDoc.getDescription())){
            WHERE("description=#{apiDoc.description}");
        }
        if (StringUtils.isNotBlank(apiDoc.getHost())){
            WHERE("host=#{apiDoc.host}");
        }
        if (StringUtils.isNotBlank(apiDoc.getBasePath())){
            WHERE("base_path=#{apiDoc.basePath}");
        }
        if (apiDoc.getPub() != null) {
            WHERE("pub=#{apiDoc.pub}");
        }
        if (apiDoc.getOpen() != null) {
            WHERE("open=#{apiDoc.open}");
        }
        if (StringUtils.isNotBlank(apiDoc.getScheme())){
            WHERE("scheme=#{apiDoc.scheme}");
        }
        if (StringUtils.isNotBlank(apiDoc.getConsume())){
            WHERE("consume=#{apiDoc.consume}");
        }
        if (StringUtils.isNotBlank(apiDoc.getProduce())){
            WHERE("produce=#{apiDoc.produce}");
        }
        if (StringUtils.isNotBlank(apiDoc.getVersion())){
            WHERE("version=#{apiDoc.version}");
        }
        }}.toString();
    }
}

