package net.fnsco.trading.service.account.dao.helper;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.trading.service.account.entity.AppAccountBalanceDO;
public class AppAccountBalanceProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "u_app_account_balance";

    public String update(Map<String, Object> params) {
        AppAccountBalanceDO appAccountBalance = (AppAccountBalanceDO) params.get("appAccountBalance");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (appAccountBalance.getAppUserId() != null) {
            SET("app_user_id=#{appAccountBalance.appUserId}");
        }
        if (appAccountBalance.getFund() != null) {
            SET("fund=#{appAccountBalance.fund}");
        }
        if (appAccountBalance.getFreezeAmount() != null) {
            SET("freeze_amount=#{appAccountBalance.freezeAmount}");
        }
        if (appAccountBalance.getUpdateTime() != null) {
            SET("update_time=#{appAccountBalance.updateTime}");
        }
        if (appAccountBalance.getCreateTime() != null) {
            SET("create_time=#{appAccountBalance.createTime}");
        }
        WHERE("id = #{appAccountBalance.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        AppAccountBalanceDO appAccountBalance = (AppAccountBalanceDO) params.get("appAccountBalance");
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
        if (appAccountBalance.getId() != null) {
            WHERE("id=#{appAccountBalance.id}");
        }
        if (appAccountBalance.getAppUserId() != null) {
            WHERE("app_user_id=#{appAccountBalance.appUserId}");
        }
        if (appAccountBalance.getFund() != null) {
            WHERE("fund=#{appAccountBalance.fund}");
        }
        if (appAccountBalance.getFreezeAmount() != null) {
            WHERE("freeze_amount=#{appAccountBalance.freezeAmount}");
        }
        if (appAccountBalance.getUpdateTime() != null) {
            WHERE("update_time=#{appAccountBalance.updateTime}");
        }
        if (appAccountBalance.getCreateTime() != null) {
            WHERE("create_time=#{appAccountBalance.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        AppAccountBalanceDO appAccountBalance = (AppAccountBalanceDO) params.get("appAccountBalance");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (appAccountBalance.getId() != null) {
            WHERE("id=#{appAccountBalance.id}");
        }
        if (appAccountBalance.getAppUserId() != null) {
            WHERE("app_user_id=#{appAccountBalance.appUserId}");
        }
        if (appAccountBalance.getFund() != null) {
            WHERE("fund=#{appAccountBalance.fund}");
        }
        if (appAccountBalance.getFreezeAmount() != null) {
            WHERE("freeze_amount=#{appAccountBalance.freezeAmount}");
        }
        if (appAccountBalance.getUpdateTime() != null) {
            WHERE("update_time=#{appAccountBalance.updateTime}");
        }
        if (appAccountBalance.getCreateTime() != null) {
            WHERE("create_time=#{appAccountBalance.createTime}");
        }
        }}.toString();
    }
}

