package net.fnsco.withhold.service.sys.dao.helper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.withhold.service.sys.entity.UserDO;

public class UserProvider {

    private Logger              logger     = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "sys_user";

    public String update(Map<String, Object> params) {
        UserDO user = (UserDO) params.get("user");
        BEGIN();
        UPDATE(TABLE_NAME);
        if (user.getType() != null) {
            SET("type=#{user.type}");
        }
        if (StringUtils.isNotBlank(user.getName())) {
            SET("name=#{user.name}");
        }
        if (StringUtils.isNotBlank(user.getPassword())) {
            SET("password=#{user.password}");
        }
        if (StringUtils.isNotBlank(user.getRealName())) {
            SET("real_Name=#{user.realName}");
        }
        if (StringUtils.isNotBlank(user.getMobile())) {
            SET("mobile=#{user.mobile}");
        }
        if (user.getSex() != null) {
            SET("sex=#{user.sex}");
        }
        if (StringUtils.isNotBlank(user.getAliasName())) {
            SET("alias_Name=#{user.aliasName}");
        }
        if (StringUtils.isNotBlank(user.getDepartment())) {
            SET("department=#{user.department}");
        }
        if (user.getAgentId() != null) {
            SET("agent_Id=#{user.agentId}");
        }
        if (StringUtils.isNotBlank(user.getRemark())) {
            SET("remark=#{user.remark}");
        }
        if (user.getModifyTime() != null) {
            SET("modify_Time=#{user.modifyTime}");
        }
        if (user.getModifyUserId() != null) {
            SET("modify_UserId=#{user.modifyUserId}");
        }
        WHERE("id = #{user.id}");
        String sql = SQL();
        return sql;
    }

    public String pageList(Map<String, Object> params) {
        UserDO user = (UserDO) params.get("user");
        Integer pageNum = (Integer) params.get("pageNum");
        Integer pageSize = (Integer) params.get("pageSize");
        BEGIN();
        SELECT("*");
        FROM(TABLE_NAME);
        if (user.getId() != null) {
            WHERE("id=#{user.id}");
        }
        if (user.getType() != null) {
            WHERE("type=#{user.type}");
        }
        if (StringUtils.isNotBlank(user.getName())) {
            WHERE("name=#{user.name}");
        }
        if (StringUtils.isNotBlank(user.getPassword())) {
            WHERE("password=#{user.password}");
        }
        if (StringUtils.isNotBlank(user.getRealName())) {
            WHERE("real_Name=#{user.realName}");
        }
        if (StringUtils.isNotBlank(user.getMobile())) {
            WHERE("mobile=#{user.mobile}");
        }
        if (user.getSex() != null) {
            WHERE("sex=#{user.sex}");
        }
        if (StringUtils.isNotBlank(user.getAliasName())) {
            WHERE("alias_Name=#{user.aliasName}");
        }
        if (StringUtils.isNotBlank(user.getDepartment())) {
            WHERE("department=#{user.department}");
        }
        if (user.getAgentId() != null) {
            WHERE("agent_Id=#{user.agentId}");
        }
        if (StringUtils.isNotBlank(user.getRemark())) {
            WHERE("remark=#{user.remark}");
        }
        if (user.getModifyTime() != null) {
            WHERE("modify_Time=#{user.modifyTime}");
        }
        if (user.getModifyUserId() != null) {
            WHERE("modify_UserId=#{user.modifyUserId}");
        }
        ORDER_BY("id desc");
        String sql = SQL();
        int start = 0;
        int limit = 0;
        if (pageNum == null || pageNum == 0) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 20;
        }
        start = (pageNum - 1) * pageSize;
        limit = pageSize;
        sql += " limit " + start + ", " + limit;
        return sql;
    }

    public String pageListCount(Map<String, Object> params) {
        UserDO user = (UserDO) params.get("user");
        BEGIN();
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (user.getId() != null) {
            WHERE("id=#{user.id}");
        }
        if (user.getType() != null) {
            WHERE("type=#{user.type}");
        }
        if (StringUtils.isNotBlank(user.getName())) {
            WHERE("name=#{user.name}");
        }
        if (StringUtils.isNotBlank(user.getPassword())) {
            WHERE("password=#{user.password}");
        }
        if (StringUtils.isNotBlank(user.getRealName())) {
            WHERE("real_Name=#{user.realName}");
        }
        if (StringUtils.isNotBlank(user.getMobile())) {
            WHERE("mobile=#{user.mobile}");
        }
        if (user.getSex() != null) {
            WHERE("sex=#{user.sex}");
        }
        if (StringUtils.isNotBlank(user.getAliasName())) {
            WHERE("alias_Name=#{user.aliasName}");
        }
        if (StringUtils.isNotBlank(user.getDepartment())) {
            WHERE("department=#{user.department}");
        }
        if (user.getAgentId() != null) {
            WHERE("agent_Id=#{user.agentId}");
        }
        if (StringUtils.isNotBlank(user.getRemark())) {
            WHERE("remark=#{user.remark}");
        }
        if (user.getModifyTime() != null) {
            WHERE("modify_Time=#{user.modifyTime}");
        }
        if (user.getModifyUserId() != null) {
            WHERE("modify_UserId=#{user.modifyUserId}");
        }
        String sql = SQL();
        return sql;
    }
}
