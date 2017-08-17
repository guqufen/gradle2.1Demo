package net.fnsco.auth.service.sys.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.auth.service.sys.entity.UserDO;

public class UserProvider {

    private Logger              logger     = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "sys_user";

    public String update(Map<String, Object> params) {
        UserDO user = (UserDO) params.get("user");
        return new SQL() {
            {
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
                    SET("real_name=#{user.realName}");
                }
                if (StringUtils.isNotBlank(user.getMobile())) {
                    SET("mobile=#{user.mobile}");
                }
                if (user.getSex() != null) {
                    SET("sex=#{user.sex}");
                }
                if (StringUtils.isNotBlank(user.getAliasName())) {
                    SET("alias_name=#{user.aliasName}");
                }
                if (StringUtils.isNotBlank(user.getDepartment())) {
                    SET("department=#{user.department}");
                }
                if (user.getAgentId() != null) {
                    SET("agent_id=#{user.agentId}");
                }
                if (StringUtils.isNotBlank(user.getRemark())) {
                    SET("remark=#{user.remark}");
                }
                if (user.getModifyTime() != null) {
                    SET("modify_time=#{user.modifyTime}");
                }
                if (user.getModifyUserId() != null) {
                    SET("modify_user_id=#{user.modifyUserId}");
                }
                WHERE("id = #{user.id}");
            }
        }.toString();
    }

    public String pageList(Map<String, Object> params) {
        UserDO user = (UserDO) params.get("user");
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
        return new SQL() {
            {
                SELECT("*");
                FROM(TABLE_NAME);
                if (user.getId() != null) {
                    WHERE("id=#{user.id}");
                }
                if (user.getType() != null) {
                    WHERE("type=#{user.type}");
                }
                if (StringUtils.isNotBlank(user.getName())) {
                    WHERE("name like CONCAT('%',#{user.name},'%')");
                }
                if (StringUtils.isNotBlank(user.getPassword())) {
                    WHERE("password=#{user.password}");
                }
                if (StringUtils.isNotBlank(user.getRealName())) {
                    WHERE("real_name=#{user.realName}");
                }
                if (StringUtils.isNotBlank(user.getMobile())) {
                    WHERE("mobile=#{user.mobile}");
                }
                if (user.getSex() != null) {
                    WHERE("sex=#{user.sex}");
                }
                if (StringUtils.isNotBlank(user.getAliasName())) {
                    WHERE("alias_name=#{user.aliasName}");
                }
                if (StringUtils.isNotBlank(user.getDepartment())) {
                    WHERE("department=#{user.department}");
                }
                if (user.getAgentId() != null) {
                    WHERE("agent_id=#{user.agentId}");
                }
                if (StringUtils.isNotBlank(user.getRemark())) {
                    WHERE("remark=#{user.remark}");
                }
                if (user.getModifyTime() != null) {
                    WHERE("modify_time=#{user.modifyTime}");
                }
                if (user.getModifyUserId() != null) {
                    WHERE("modify_user_id=#{user.modifyUserId}");
                }
                WHERE("status!=-1");
                ORDER_BY("id limit " + start + ", " + limit);
            }
        }.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        UserDO user = (UserDO) params.get("user");
        return new SQL() {
            {
                SELECT("count(1)");
                FROM(TABLE_NAME);
                if (user.getId() != null) {
                    WHERE("id=#{user.id}");
                }
                if (user.getType() != null) {
                    WHERE("type=#{user.type}");
                }
                if (StringUtils.isNotBlank(user.getName())) {
                    WHERE("name like CONCAT('%',#{user.name},'%')");
                }
                if (StringUtils.isNotBlank(user.getPassword())) {
                    WHERE("password=#{user.password}");
                }
                if (StringUtils.isNotBlank(user.getRealName())) {
                    WHERE("real_name=#{user.realName}");
                }
                if (StringUtils.isNotBlank(user.getMobile())) {
                    WHERE("mobile=#{user.mobile}");
                }
                if (user.getSex() != null) {
                    WHERE("sex=#{user.sex}");
                }
                if (StringUtils.isNotBlank(user.getAliasName())) {
                    WHERE("alias_name=#{user.aliasName}");
                }
                if (StringUtils.isNotBlank(user.getDepartment())) {
                    WHERE("department=#{user.department}");
                }
                if (user.getAgentId() != null) {
                    WHERE("agent_id=#{user.agentId}");
                }
                if (StringUtils.isNotBlank(user.getRemark())) {
                    WHERE("remark=#{user.remark}");
                }
                if (user.getModifyTime() != null) {
                    WHERE("modify_time=#{user.modifyTime}");
                }
                if (user.getModifyUserId() != null) {
                    WHERE("modify_user_id=#{user.modifyUserId}");
                }
            }
        }.toString();
    }

    public String queryAllPerms(Map<String, Object> params) {
        Integer userId = (Integer) params.get("userId");
        return new SQL() {
            {
                SELECT("select m.perms from sys_user_role ur LEFT JOIN sys_role_menu rm on ur.role_id = rm.role_id LEFT JOIN sys_menu m on rm.menu_id = m.menu_id ");
                WHERE("ur.user_id = #{userId}");
            }
        }.toString();
    }

}
