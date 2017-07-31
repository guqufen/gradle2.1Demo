package net.fnsco.withhold.service.sys.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.withhold.service.sys.entity.BankCodeDO;

public class BankCodeProvider {

    private Logger              logger     = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "sys_bank_code";

    public String update(Map<String, Object> params) {
        BankCodeDO bankCode = (BankCodeDO) params.get("bankCode");
        return new SQL() {
            {
                UPDATE(TABLE_NAME);
                if (StringUtils.isNotBlank(bankCode.getCode())) {
                    SET("code=#{bankCode.code}");
                }
                if (StringUtils.isNotBlank(bankCode.getBankName())) {
                    SET("bank_name=#{bankCode.bankName}");
                }
                if (StringUtils.isNotBlank(bankCode.getCardName())) {
                    SET("card_name=#{bankCode.cardName}");
                }
                if (StringUtils.isNotBlank(bankCode.getCardTrimValue())) {
                    SET("card_trim_value=#{bankCode.cardTrimValue}");
                }
                if (StringUtils.isNotBlank(bankCode.getType())) {
                    SET("type=#{bankCode.type}");
                }
                if (bankCode.getCardTrimLength() != null) {
                    SET("card_trim_length=#{bankCode.cardTrimLength}");
                }
                if (bankCode.getCardTotalLength() != null) {
                    SET("card_total_length=#{bankCode.cardTotalLength}");
                }
                WHERE("id = #{bankCode.id}");
            }
        }.toString();
    }

    public String pageList(Map<String, Object> params) {
        BankCodeDO bankCode = (BankCodeDO) params.get("bankCode");
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
                if (bankCode.getId() != null) {
                    WHERE("id=#{bankCode.id}");
                }
                if (StringUtils.isNotBlank(bankCode.getCode())) {
                    WHERE("code=#{bankCode.code}");
                }
                if (StringUtils.isNotBlank(bankCode.getBankName())) {
                    WHERE("bank_name=#{bankCode.bankName}");
                }
                if (StringUtils.isNotBlank(bankCode.getCardName())) {
                    WHERE("card_name=#{bankCode.cardName}");
                }
                if (StringUtils.isNotBlank(bankCode.getCardTrimValue())) {
                    WHERE("card_trim_value=#{bankCode.cardTrimValue}");
                }
                if (StringUtils.isNotBlank(bankCode.getType())) {
                    WHERE("type=#{bankCode.type}");
                }
                if (bankCode.getCardTrimLength() != null) {
                    WHERE("card_trim_length=#{bankCode.cardTrimLength}");
                }
                if (bankCode.getCardTotalLength() != null) {
                    WHERE("card_total_length=#{bankCode.cardTotalLength}");
                }
                ORDER_BY("id desc limit " + start + ", " + limit);
            }
        }.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        BankCodeDO bankCode = (BankCodeDO) params.get("bankCode");
        return new SQL() {
            {
                SELECT("count(1)");
                FROM(TABLE_NAME);
                if (bankCode.getId() != null) {
                    WHERE("id=#{bankCode.id}");
                }
                if (StringUtils.isNotBlank(bankCode.getCode())) {
                    WHERE("code=#{bankCode.code}");
                }
                if (StringUtils.isNotBlank(bankCode.getBankName())) {
                    WHERE("bank_name=#{bankCode.bankName}");
                }
                if (StringUtils.isNotBlank(bankCode.getCardName())) {
                    WHERE("card_name=#{bankCode.cardName}");
                }
                if (StringUtils.isNotBlank(bankCode.getCardTrimValue())) {
                    WHERE("card_trim_value=#{bankCode.cardTrimValue}");
                }
                if (StringUtils.isNotBlank(bankCode.getType())) {
                    WHERE("type=#{bankCode.type}");
                }
                if (bankCode.getCardTrimLength() != null) {
                    WHERE("card_trim_length=#{bankCode.cardTrimLength}");
                }
                if (bankCode.getCardTotalLength() != null) {
                    WHERE("card_total_length=#{bankCode.cardTotalLength}");
                }
            }
        }.toString();
    }
}
