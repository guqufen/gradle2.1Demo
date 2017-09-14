package net.fnsco.risk.service.report.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.report.entity.UserMercRelDO;
public class UserMercRelProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "risk_user_merc_rel";

    public String update(Map<String, Object> params) {
        UserMercRelDO userMercRel = (UserMercRelDO) params.get("userMercRel");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (userMercRel.getWebUserOuterId() != null) {
            SET("web_user_outer_id=#{userMercRel.webUserOuterId}");
        }
        if (StringUtils.isNotBlank(userMercRel.getInnerCode())){
            SET("inner_code=#{userMercRel.innerCode}");
        }
        WHERE("id = #{userMercRel.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        UserMercRelDO userMercRel = (UserMercRelDO) params.get("userMercRel");
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
        if (userMercRel.getId() != null) {
            WHERE("id=#{userMercRel.id}");
        }
        if (userMercRel.getWebUserOuterId() != null) {
            WHERE("web_user_outer_id=#{userMercRel.webUserOuterId}");
        }
        if (StringUtils.isNotBlank(userMercRel.getInnerCode())){
            WHERE("inner_code=#{userMercRel.innerCode}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        UserMercRelDO userMercRel = (UserMercRelDO) params.get("userMercRel");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (userMercRel.getId() != null) {
            WHERE("id=#{userMercRel.id}");
        }
        if (userMercRel.getWebUserOuterId() != null) {
            WHERE("web_user_outer_id=#{userMercRel.webUserOuterId}");
        }
        if (StringUtils.isNotBlank(userMercRel.getInnerCode())){
            WHERE("inner_code=#{userMercRel.innerCode}");
        }
        }}.toString();
    }
}

