package net.fnsco.risk.service.sys.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.sys.entity.UserSubAccountDO;
public class UserSubAccountProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "risk_user_sub_account";

    public String update(Map<String, Object> params) {
        UserSubAccountDO userSubAccount = (UserSubAccountDO) params.get("userSubAccount");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (userSubAccount.getAgentId() != null) {
            SET("agent_id=#{userSubAccount.agentId}");
        }
        if (userSubAccount.getAccountBalance() != null) {
            SET("account_balance=#{userSubAccount.accountBalance}");
        }
        if (userSubAccount.getLastModifyTime() != null) {
            SET("last_modify_time=#{userSubAccount.lastModifyTime}");
        }
        WHERE("id = #{userSubAccount.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        UserSubAccountDO userSubAccount = (UserSubAccountDO) params.get("userSubAccount");
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
        if (userSubAccount.getId() != null) {
            WHERE("id=#{userSubAccount.id}");
        }
        if (userSubAccount.getAgentId() != null) {
            WHERE("agent_id=#{userSubAccount.agentId}");
        }
        if (userSubAccount.getAccountBalance() != null) {
            WHERE("account_balance=#{userSubAccount.accountBalance}");
        }
        if (userSubAccount.getLastModifyTime() != null) {
            WHERE("last_modify_time=#{userSubAccount.lastModifyTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        UserSubAccountDO userSubAccount = (UserSubAccountDO) params.get("userSubAccount");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (userSubAccount.getId() != null) {
            WHERE("id=#{userSubAccount.id}");
        }
        if (userSubAccount.getAgentId() != null) {
            WHERE("agent_id=#{userSubAccount.agentId}");
        }
        if (userSubAccount.getAccountBalance() != null) {
            WHERE("account_balance=#{userSubAccount.accountBalance}");
        }
        if (userSubAccount.getLastModifyTime() != null) {
            WHERE("last_modify_time=#{userSubAccount.lastModifyTime}");
        }
        }}.toString();
    }
}

