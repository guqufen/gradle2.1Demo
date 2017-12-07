package net.fnsco.order.service.ad.dao.helper;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.order.service.ad.entity.AdDO;

public class AdProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "sys_ad";

    public String update(Map<String, Object> params) {
        AdDO ad = (AdDO) params.get("ad");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(ad.getTitle())){
            SET("title=#{ad.title}");
        }
        if (StringUtils.isNotBlank(ad.getImgPath())){
            SET("img_path=#{ad.imgPath}");
        }
        if (ad.getCategory() != null) {
            SET("category=#{ad.category}");
        }
        if (StringUtils.isNotBlank(ad.getSummary())){
            SET("summary=#{ad.summary}");
        }
        if (StringUtils.isNotBlank(ad.getContent())){
            SET("content=#{ad.content}");
        }
        if (ad.getCreateTime() != null) {
            SET("create_time=#{ad.createTime}");
        }
        if (ad.getUpdateTime() != null) {
            SET("update_time=#{ad.updateTime}");
        }
        if (ad.getCreateUserId() != null) {
            SET("create_user_id=#{ad.createUserId}");
        }
        WHERE("id = #{ad.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        AdDO ad = (AdDO) params.get("ad");
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
        if (ad.getId() != null) {
            WHERE("id=#{ad.id}");
        }
        if (StringUtils.isNotBlank(ad.getTitle())){
            WHERE("title=#{ad.title}");
        }
        if (StringUtils.isNotBlank(ad.getImgPath())){
            WHERE("img_path=#{ad.imgPath}");
        }
        if (ad.getCategory() != null) {
            WHERE("category=#{ad.category}");
        }
        if (StringUtils.isNotBlank(ad.getSummary())){
            WHERE("summary=#{ad.summary}");
        }
        if (StringUtils.isNotBlank(ad.getContent())){
            WHERE("content=#{ad.content}");
        }
        if (ad.getCreateTime() != null) {
            WHERE("create_time=#{ad.createTime}");
        }
        if (ad.getUpdateTime() != null) {
            WHERE("update_time=#{ad.updateTime}");
        }
        if (ad.getCreateUserId() != null) {
            WHERE("create_user_id=#{ad.createUserId}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        AdDO ad = (AdDO) params.get("ad");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (ad.getId() != null) {
            WHERE("id=#{ad.id}");
        }
        if (StringUtils.isNotBlank(ad.getTitle())){
            WHERE("title=#{ad.title}");
        }
        if (StringUtils.isNotBlank(ad.getImgPath())){
            WHERE("img_path=#{ad.imgPath}");
        }
        if (ad.getCategory() != null) {
            WHERE("category=#{ad.category}");
        }
        if (StringUtils.isNotBlank(ad.getSummary())){
            WHERE("summary=#{ad.summary}");
        }
        if (StringUtils.isNotBlank(ad.getContent())){
            WHERE("content=#{ad.content}");
        }
        if (ad.getCreateTime() != null) {
            WHERE("create_time=#{ad.createTime}");
        }
        if (ad.getUpdateTime() != null) {
            WHERE("update_time=#{ad.updateTime}");
        }
        if (ad.getCreateUserId() != null) {
            WHERE("create_user_id=#{ad.createUserId}");
        }
        }}.toString();
    }
}

