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
        if (userMercRel.getAgentId() != null) {
            SET("agent_id=#{userMercRel.agentId}");
        }
        if (StringUtils.isNotBlank(userMercRel.getEntityInnerCode())){
            SET("entity_inner_code=#{userMercRel.entityInnerCode}");
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
        if (userMercRel.getAgentId() != null) {
            WHERE("agent_id=#{userMercRel.agentId}");
        }
        if (StringUtils.isNotBlank(userMercRel.getEntityInnerCode())){
            WHERE("entity_inner_code=#{userMercRel.entityInnerCode}");
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
        if (userMercRel.getAgentId() != null) {
            WHERE("agent_id=#{userMercRel.agentId}");
        }
        if (StringUtils.isNotBlank(userMercRel.getEntityInnerCode())){
            WHERE("entity_inner_code=#{userMercRel.entityInnerCode}");
        }
        }}.toString();
    }
}

