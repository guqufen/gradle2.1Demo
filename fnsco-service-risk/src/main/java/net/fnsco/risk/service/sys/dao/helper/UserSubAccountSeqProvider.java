package net.fnsco.risk.service.sys.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.sys.entity.UserSubAccountSeqDO;
public class UserSubAccountSeqProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "risk_user_sub_account_seq";

    public String update(Map<String, Object> params) {
        UserSubAccountSeqDO userSubAccountSeq = (UserSubAccountSeqDO) params.get("userSubAccountSeq");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (userSubAccountSeq.getAccountBalance() != null) {
            SET("account_balance=#{userSubAccountSeq.accountBalance}");
        }
        if (StringUtils.isNotBlank(userSubAccountSeq.getRemark())){
            SET("remark=#{userSubAccountSeq.remark}");
        }
        if (userSubAccountSeq.getCreateTime() != null) {
            SET("create_time=#{userSubAccountSeq.createTime}");
        }
        WHERE("id = #{userSubAccountSeq.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        UserSubAccountSeqDO userSubAccountSeq = (UserSubAccountSeqDO) params.get("userSubAccountSeq");
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
        if (userSubAccountSeq.getId() != null) {
            WHERE("id=#{userSubAccountSeq.id}");
        }
        if (userSubAccountSeq.getAccountBalance() != null) {
            WHERE("account_balance=#{userSubAccountSeq.accountBalance}");
        }
        if (StringUtils.isNotBlank(userSubAccountSeq.getRemark())){
            WHERE("remark=#{userSubAccountSeq.remark}");
        }
        if (userSubAccountSeq.getCreateTime() != null) {
            WHERE("create_time=#{userSubAccountSeq.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        UserSubAccountSeqDO userSubAccountSeq = (UserSubAccountSeqDO) params.get("userSubAccountSeq");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (userSubAccountSeq.getId() != null) {
            WHERE("id=#{userSubAccountSeq.id}");
        }
        if (userSubAccountSeq.getAccountBalance() != null) {
            WHERE("account_balance=#{userSubAccountSeq.accountBalance}");
        }
        if (StringUtils.isNotBlank(userSubAccountSeq.getRemark())){
            WHERE("remark=#{userSubAccountSeq.remark}");
        }
        if (userSubAccountSeq.getCreateTime() != null) {
            WHERE("create_time=#{userSubAccountSeq.createTime}");
        }
        }}.toString();
    }
}

