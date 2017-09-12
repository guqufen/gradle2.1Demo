package net.fnsco.risk.service.sys.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.sys.entity.WebUserDO;
public class WebUserProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "risk_web_user";

    public String update(Map<String, Object> params) {
        WebUserDO webUser = (WebUserDO) params.get("webUser");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (webUser.getType() != null) {
            SET("type=#{webUser.type}");
        }
        if (StringUtils.isNotBlank(webUser.getName())){
            SET("name=#{webUser.name}");
        }
        if (StringUtils.isNotBlank(webUser.getPassword())){
            SET("password=#{webUser.password}");
        }
        if (StringUtils.isNotBlank(webUser.getRealName())){
            SET("real_name=#{webUser.realName}");
        }
        if (StringUtils.isNotBlank(webUser.getMobile())){
            SET("mobile=#{webUser.mobile}");
        }
        if (webUser.getSex() != null) {
            SET("sex=#{webUser.sex}");
        }
        if (StringUtils.isNotBlank(webUser.getAliasName())){
            SET("alias_name=#{webUser.aliasName}");
        }
        if (StringUtils.isNotBlank(webUser.getDepartment())){
            SET("department=#{webUser.department}");
        }
        if (webUser.getAgentId() != null) {
            SET("agent_id=#{webUser.agentId}");
        }
        if (StringUtils.isNotBlank(webUser.getRemark())){
            SET("remark=#{webUser.remark}");
        }
        if (webUser.getModifyTime() != null) {
            SET("modify_time=#{webUser.modifyTime}");
        }
        if (webUser.getModifyUserId() != null) {
            SET("modify_user_id=#{webUser.modifyUserId}");
        }
        if (webUser.getCreaterTime() != null) {
            SET("creater_time=#{webUser.createrTime}");
        }
        WHERE("id = #{webUser.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        WebUserDO webUser = (WebUserDO) params.get("webUser");
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
        if (webUser.getId() != null) {
            WHERE("id=#{webUser.id}");
        }
        if (webUser.getType() != null) {
            WHERE("type=#{webUser.type}");
        }
        if (StringUtils.isNotBlank(webUser.getName())){
            WHERE("name=#{webUser.name}");
        }
        if (StringUtils.isNotBlank(webUser.getPassword())){
            WHERE("password=#{webUser.password}");
        }
        if (StringUtils.isNotBlank(webUser.getRealName())){
            WHERE("real_name=#{webUser.realName}");
        }
        if (StringUtils.isNotBlank(webUser.getMobile())){
            WHERE("mobile=#{webUser.mobile}");
        }
        if (webUser.getSex() != null) {
            WHERE("sex=#{webUser.sex}");
        }
        if (StringUtils.isNotBlank(webUser.getAliasName())){
            WHERE("alias_name=#{webUser.aliasName}");
        }
        if (StringUtils.isNotBlank(webUser.getDepartment())){
            WHERE("department=#{webUser.department}");
        }
        if (webUser.getAgentId() != null) {
            WHERE("agent_id=#{webUser.agentId}");
        }
        if (StringUtils.isNotBlank(webUser.getRemark())){
            WHERE("remark=#{webUser.remark}");
        }
        if (webUser.getModifyTime() != null) {
            WHERE("modify_time=#{webUser.modifyTime}");
        }
        if (webUser.getModifyUserId() != null) {
            WHERE("modify_user_id=#{webUser.modifyUserId}");
        }
        if (webUser.getCreaterTime() != null) {
            WHERE("creater_time=#{webUser.createrTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        WebUserDO webUser = (WebUserDO) params.get("webUser");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (webUser.getId() != null) {
            WHERE("id=#{webUser.id}");
        }
        if (webUser.getType() != null) {
            WHERE("type=#{webUser.type}");
        }
        if (StringUtils.isNotBlank(webUser.getName())){
            WHERE("name=#{webUser.name}");
        }
        if (StringUtils.isNotBlank(webUser.getPassword())){
            WHERE("password=#{webUser.password}");
        }
        if (StringUtils.isNotBlank(webUser.getRealName())){
            WHERE("real_name=#{webUser.realName}");
        }
        if (StringUtils.isNotBlank(webUser.getMobile())){
            WHERE("mobile=#{webUser.mobile}");
        }
        if (webUser.getSex() != null) {
            WHERE("sex=#{webUser.sex}");
        }
        if (StringUtils.isNotBlank(webUser.getAliasName())){
            WHERE("alias_name=#{webUser.aliasName}");
        }
        if (StringUtils.isNotBlank(webUser.getDepartment())){
            WHERE("department=#{webUser.department}");
        }
        if (webUser.getAgentId() != null) {
            WHERE("agent_id=#{webUser.agentId}");
        }
        if (StringUtils.isNotBlank(webUser.getRemark())){
            WHERE("remark=#{webUser.remark}");
        }
        if (webUser.getModifyTime() != null) {
            WHERE("modify_time=#{webUser.modifyTime}");
        }
        if (webUser.getModifyUserId() != null) {
            WHERE("modify_user_id=#{webUser.modifyUserId}");
        }
        if (webUser.getCreaterTime() != null) {
            WHERE("creater_time=#{webUser.createrTime}");
        }
        }}.toString();
    }
}

