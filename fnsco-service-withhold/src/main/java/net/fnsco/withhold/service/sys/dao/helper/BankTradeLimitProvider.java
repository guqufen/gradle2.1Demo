package net.fnsco.withhold.service.sys.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.withhold.service.sys.entity.BankTradeLimitDO;
public class BankTradeLimitProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "sys_bank_trade_limit";

    public String update(Map<String, Object> params) {
        BankTradeLimitDO bankTradeLimit = (BankTradeLimitDO) params.get("bankTradeLimit");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(bankTradeLimit.getBankCode())){
            SET("bank_code=#{bankTradeLimit.bankCode}");
        }
        if (StringUtils.isNotBlank(bankTradeLimit.getBankName())){
            SET("bank_name=#{bankTradeLimit.bankName}");
        }
        if (StringUtils.isNotBlank(bankTradeLimit.getTradeTimesLimit())){
            SET("trade_times_limit=#{bankTradeLimit.tradeTimesLimit}");
        }
        if (StringUtils.isNotBlank(bankTradeLimit.getTradeDayLimit())){
            SET("trade_day_limit=#{bankTradeLimit.tradeDayLimit}");
        }
        WHERE("id = #{bankTradeLimit.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        BankTradeLimitDO bankTradeLimit = (BankTradeLimitDO) params.get("bankTradeLimit");
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
        if (bankTradeLimit.getId() != null) {
            WHERE("id=#{bankTradeLimit.id}");
        }
        if (StringUtils.isNotBlank(bankTradeLimit.getBankCode())){
            WHERE("bank_code=#{bankTradeLimit.bankCode}");
        }
        if (StringUtils.isNotBlank(bankTradeLimit.getBankName())){
            WHERE("bank_name=#{bankTradeLimit.bankName}");
        }
        if (StringUtils.isNotBlank(bankTradeLimit.getTradeTimesLimit())){
            WHERE("trade_times_limit=#{bankTradeLimit.tradeTimesLimit}");
        }
        if (StringUtils.isNotBlank(bankTradeLimit.getTradeDayLimit())){
            WHERE("trade_day_limit=#{bankTradeLimit.tradeDayLimit}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        BankTradeLimitDO bankTradeLimit = (BankTradeLimitDO) params.get("bankTradeLimit");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (bankTradeLimit.getId() != null) {
            WHERE("id=#{bankTradeLimit.id}");
        }
        if (StringUtils.isNotBlank(bankTradeLimit.getBankCode())){
            WHERE("bank_code=#{bankTradeLimit.bankCode}");
        }
        if (StringUtils.isNotBlank(bankTradeLimit.getBankName())){
            WHERE("bank_name=#{bankTradeLimit.bankName}");
        }
        if (StringUtils.isNotBlank(bankTradeLimit.getTradeTimesLimit())){
            WHERE("trade_times_limit=#{bankTradeLimit.tradeTimesLimit}");
        }
        if (StringUtils.isNotBlank(bankTradeLimit.getTradeDayLimit())){
            WHERE("trade_day_limit=#{bankTradeLimit.tradeDayLimit}");
        }
        }}.toString();
    }
}

