package net.fnsco.bigdata.service.sys.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.bigdata.service.sys.entity.AppAdDO;

import org.apache.commons.lang3.StringUtils;
public class AppAdProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "sys_app_ad";

    public String update(Map<String, Object> params) {
        AppAdDO appAd = (AppAdDO) params.get("appAd");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(appAd.getFilename())){
            SET("fileName=#{appAd.filename}");
        }
        if (StringUtils.isNotBlank(appAd.getFilepath())){
            SET("filePath=#{appAd.filepath}");
        }
        if (appAd.getCategory() != null) {
            SET("category=#{appAd.category}");
        }
        if (StringUtils.isNotBlank(appAd.getContent())){
            SET("content=#{appAd.content}");
        }
        if (appAd.getCreatetime() != null) {
            SET("createTime=#{appAd.createtime}");
        }
        if (appAd.getUpdatetime() != null) {
            SET("updateTime=#{appAd.updatetime}");
        }
        if (appAd.getCreateuserid() != null) {
            SET("createUserId=#{appAd.createuserid}");
        }
        WHERE("id = #{appAd.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        AppAdDO appAd = (AppAdDO) params.get("appAd");
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
        if (appAd.getId() != null) {
            WHERE("id=#{appAd.id}");
        }
        if (StringUtils.isNotBlank(appAd.getFilename())){
            WHERE("fileName=#{appAd.filename}");
        }
        if (StringUtils.isNotBlank(appAd.getFilepath())){
            WHERE("filePath=#{appAd.filepath}");
        }
        if (appAd.getCategory() != null) {
            WHERE("category=#{appAd.category}");
        }
        if (StringUtils.isNotBlank(appAd.getContent())){
            WHERE("content=#{appAd.content}");
        }
        if (appAd.getCreatetime() != null) {
            WHERE("createTime=#{appAd.createtime}");
        }
        if (appAd.getUpdatetime() != null) {
            WHERE("updateTime=#{appAd.updatetime}");
        }
        if (appAd.getCreateuserid() != null) {
            WHERE("createUserId=#{appAd.createuserid}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        AppAdDO appAd = (AppAdDO) params.get("appAd");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (appAd.getId() != null) {
            WHERE("id=#{appAd.id}");
        }
        if (StringUtils.isNotBlank(appAd.getFilename())){
            WHERE("fileName=#{appAd.filename}");
        }
        if (StringUtils.isNotBlank(appAd.getFilepath())){
            WHERE("filePath=#{appAd.filepath}");
        }
        if (appAd.getCategory() != null) {
            WHERE("category=#{appAd.category}");
        }
        if (StringUtils.isNotBlank(appAd.getContent())){
            WHERE("content=#{appAd.content}");
        }
        if (appAd.getCreatetime() != null) {
            WHERE("createTime=#{appAd.createtime}");
        }
        if (appAd.getUpdatetime() != null) {
            WHERE("updateTime=#{appAd.updatetime}");
        }
        if (appAd.getCreateuserid() != null) {
            WHERE("createUserId=#{appAd.createuserid}");
        }
        }}.toString();
    }
}

