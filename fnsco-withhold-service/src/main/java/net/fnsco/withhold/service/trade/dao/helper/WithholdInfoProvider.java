package net.fnsco.withhold.service.trade.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.withhold.service.trade.entity.WithholdInfoDO;

public class WithholdInfoProvider {

    private Logger              logger     = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "w_withhold_info";

    public String update(Map<String, Object> params) {
        WithholdInfoDO withholdInfo = (WithholdInfoDO) params.get("withholdInfo");
        return new SQL() {
            {
                UPDATE(TABLE_NAME);
                if (StringUtils.isNotBlank(withholdInfo.getUserName())) {
                    SET("user_name=#{withholdInfo.userName}");
                }
                if (StringUtils.isNotBlank(withholdInfo.getMobile())) {
                    SET("mobile=#{withholdInfo.mobile}");
                }
                if (StringUtils.isNotBlank(withholdInfo.getCertifType())) {
                    SET("certif_type=#{withholdInfo.certifType}");
                }
                if (StringUtils.isNotBlank(withholdInfo.getCertifyId())) {
                    SET("certify_id=#{withholdInfo.certifyId}");
                }
                if (StringUtils.isNotBlank(withholdInfo.getDebitDay())) {
                    SET("debit_day=#{withholdInfo.debitDay}");
                }
                if (withholdInfo.getAmount() != null) {
                    SET("amount=#{withholdInfo.amount}");
                }
                if (withholdInfo.getAmountTotal() != null) {
                    SET("amount_total=#{withholdInfo.amountTotal}");
                }
                if (StringUtils.isNotBlank(withholdInfo.getBankCard())) {
                    SET("bank_card=#{withholdInfo.bankCard}");
                }
                if (withholdInfo.getStatus() != null) {
                    SET("status=#{withholdInfo.status}");
                }
                if (withholdInfo.getModifyUserId() != null) {
                    SET("modify_user_id=#{withholdInfo.modifyUserId}");
                }
                if (withholdInfo.getModifyTime() != null) {
                    SET("modify_time=#{withholdInfo.modifyTime}");
                }
                if (withholdInfo.getTotal() != null) {
                    SET("total=#{withholdInfo.total}");
                }
                if (StringUtils.isNotBlank(withholdInfo.getSubBankName())) {
                    SET("sub_bank_name=#{withholdInfo.subBankName}");
                }
                if (StringUtils.isNotBlank(withholdInfo.getAnBankId())) {
                    SET("an_bank_id=#{withholdInfo.anBankId}");
                }
                if (StringUtils.isNotBlank(withholdInfo.getAccountType())) {
                    SET("account_type=#{withholdInfo.accountType}");
                }
                if (withholdInfo.getFailTotal() != null) {
                    SET("fail_total=#{withholdInfo.failTotal}");
                }
                WHERE("id = #{withholdInfo.id}");
            }
        }.toString();
    }

    public String pageList(Map<String, Object> params) {
        WithholdInfoDO withholdInfo = (WithholdInfoDO) params.get("withholdInfo");
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
                if (withholdInfo.getId() != null) {
                    WHERE("id=#{withholdInfo.id}");
                }
                if (StringUtils.isNotBlank(withholdInfo.getUserName())) {
                    WHERE("user_name like CONCAT('%',#{withholdInfo.userName},'%')");
                }
                if (StringUtils.isNotBlank(withholdInfo.getMobile())) {
                    WHERE("mobile like CONCAT('%',#{withholdInfo.mobile},'%')");
                }
                if (StringUtils.isNotBlank(withholdInfo.getCertifType())) {
                    WHERE("certif_type=#{withholdInfo.certifType}");
                }
                if (StringUtils.isNotBlank(withholdInfo.getCertifyId())) {
                    WHERE("certify_id like CONCAT('%',#{withholdInfo.certifyId},'%')");
                }
                if (StringUtils.isNotBlank(withholdInfo.getDebitDay())) {
                    WHERE("debit_day=#{withholdInfo.debitDay}");
                }
                if (withholdInfo.getAmount() != null) {
                    WHERE("amount=#{withholdInfo.amount}");
                }
                if (withholdInfo.getAmountTotal() != null) {
                    WHERE("amount_total=#{withholdInfo.amountTotal}");
                }
                if (StringUtils.isNotBlank(withholdInfo.getBankCard())) {
                    WHERE("bank_card=#{withholdInfo.bankCard}");
                }
                if (withholdInfo.getStatus() != null) {
                    WHERE("status=#{withholdInfo.status}");
                }
                if (withholdInfo.getModifyUserId() != null) {
                    WHERE("modify_user_id=#{withholdInfo.modifyUserId}");
                }
                if (withholdInfo.getModifyTime() != null) {
                    WHERE("modify_time=#{withholdInfo.modifyTime}");
                }
                if (withholdInfo.getTotal() != null) {
                    WHERE("total=#{withholdInfo.total}");
                }
                if (StringUtils.isNotBlank(withholdInfo.getSubBankName())) {
                    WHERE("sub_bank_name=#{withholdInfo.subBankName}");
                }
                if (StringUtils.isNotBlank(withholdInfo.getAnBankId())) {
                    WHERE("an_bank_id=#{withholdInfo.anBankId}");
                }
                if (StringUtils.isNotBlank(withholdInfo.getAccountType())) {
                    WHERE("account_type=#{withholdInfo.accountType}");
                }
                ORDER_BY("id desc limit " + start + ", " + limit);
            }
        }.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        WithholdInfoDO withholdInfo = (WithholdInfoDO) params.get("withholdInfo");
        return new SQL() {
            {
                SELECT("count(1)");
                FROM(TABLE_NAME);
                if (withholdInfo.getId() != null) {
                    WHERE("id=#{withholdInfo.id}");
                }
                if (StringUtils.isNotBlank(withholdInfo.getUserName())) {
                    WHERE("user_name like CONCAT('%',#{withholdInfo.userName},'%')");
                }
                if (StringUtils.isNotBlank(withholdInfo.getMobile())) {
                    WHERE("mobile like CONCAT('%',#{withholdInfo.mobile},'%')");
                }
                if (StringUtils.isNotBlank(withholdInfo.getCertifType())) {
                    WHERE("certif_type=#{withholdInfo.certifType}");
                }
                if (StringUtils.isNotBlank(withholdInfo.getCertifyId())) {
                    WHERE("certify_id like CONCAT('%',#{withholdInfo.certifyId},'%')");
                }
                if (StringUtils.isNotBlank(withholdInfo.getDebitDay())) {
                    WHERE("debit_day=#{withholdInfo.debitDay}");
                }
                if (withholdInfo.getAmount() != null) {
                    WHERE("amount=#{withholdInfo.amount}");
                }
                if (withholdInfo.getAmountTotal() != null) {
                    WHERE("amount_total=#{withholdInfo.amountTotal}");
                }
                if (StringUtils.isNotBlank(withholdInfo.getBankCard())) {
                    WHERE("bank_card=#{withholdInfo.bankCard}");
                }
                if (withholdInfo.getStatus() != null) {
                    WHERE("status=#{withholdInfo.status}");
                }
                if (withholdInfo.getModifyUserId() != null) {
                    WHERE("modify_user_id=#{withholdInfo.modifyUserId}");
                }
                if (withholdInfo.getModifyTime() != null) {
                    WHERE("modify_time=#{withholdInfo.modifyTime}");
                }
                if (withholdInfo.getTotal() != null) {
                    WHERE("total=#{withholdInfo.total}");
                }
                if (StringUtils.isNotBlank(withholdInfo.getSubBankName())) {
                    WHERE("sub_bank_name=#{withholdInfo.subBankName}");
                }
                if (StringUtils.isNotBlank(withholdInfo.getAnBankId())) {
                    WHERE("an_bank_id=#{withholdInfo.anBankId}");
                }
                if (StringUtils.isNotBlank(withholdInfo.getAccountType())) {
                    WHERE("account_type=#{withholdInfo.accountType}");
                }
            }
        }.toString();
    }
}