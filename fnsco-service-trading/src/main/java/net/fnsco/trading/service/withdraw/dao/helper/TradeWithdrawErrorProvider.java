package net.fnsco.trading.service.withdraw.dao.helper;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.trading.service.withdraw.entity.TradeWithdrawErrorDO;
public class TradeWithdrawErrorProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_trade_withdraw_error";

    public String update(Map<String, Object> params) {
        TradeWithdrawErrorDO tradeWithdrawError = (TradeWithdrawErrorDO) params.get("tradeWithdrawError");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(tradeWithdrawError.getOrderNo())){
            SET("order_no=#{tradeWithdrawError.orderNo}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getOriginalOrderNo())){
            SET("original_order_no=#{tradeWithdrawError.originalOrderNo}");
        }
        if (tradeWithdrawError.getAppUserId() != null) {
            SET("app_user_id=#{tradeWithdrawError.appUserId}");
        }
        if (tradeWithdrawError.getAmount() != null) {
            SET("amount=#{tradeWithdrawError.amount}");
        }
        if (tradeWithdrawError.getFee() != null) {
            SET("fee=#{tradeWithdrawError.fee}");
        }
        if (tradeWithdrawError.getFund() != null) {
            SET("fund=#{tradeWithdrawError.fund}");
        }
        if (tradeWithdrawError.getTradeType() != null) {
            SET("trade_type=#{tradeWithdrawError.tradeType}");
        }
        if (tradeWithdrawError.getTradeSubType() != null) {
            SET("trade_sub_type=#{tradeWithdrawError.tradeSubType}");
        }
        if (tradeWithdrawError.getStatus() != null) {
            SET("status=#{tradeWithdrawError.status}");
        }
        if (tradeWithdrawError.getCreateTime() != null) {
            SET("create_time=#{tradeWithdrawError.createTime}");
        }
        if (tradeWithdrawError.getUpdateTime() != null) {
            SET("update_time=#{tradeWithdrawError.updateTime}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getRespCode())){
            SET("resp_code=#{tradeWithdrawError.respCode}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getRespMsg())){
            SET("resp_msg=#{tradeWithdrawError.respMsg}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getSuccTime())){
            SET("succ_time=#{tradeWithdrawError.succTime}");
        }
        if (tradeWithdrawError.getSettleMoney() != null) {
            SET("settle_money=#{tradeWithdrawError.settleMoney}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getPaymentDate())){
            SET("payment_date=#{tradeWithdrawError.paymentDate}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBackurl())){
            SET("backUrl=#{tradeWithdrawError.backurl}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankAccountType())){
            SET("bank_account_type=#{tradeWithdrawError.bankAccountType}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankAccountNo())){
            SET("bank_account_no=#{tradeWithdrawError.bankAccountNo}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankAccountName())){
            SET("bank_account_name=#{tradeWithdrawError.bankAccountName}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankAccountCardId())){
            SET("bank_account_card_id=#{tradeWithdrawError.bankAccountCardId}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankSubBankName())){
            SET("bank_sub_bank_name=#{tradeWithdrawError.bankSubBankName}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankOpenBank())){
            SET("bank_open_bank=#{tradeWithdrawError.bankOpenBank}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankOpenBankNum())){
            SET("bank_open_bank_num=#{tradeWithdrawError.bankOpenBankNum}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankAccountPhone())){
            SET("bank_account_phone=#{tradeWithdrawError.bankAccountPhone}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getChannelMerId())){
            SET("channel_mer_id=#{tradeWithdrawError.channelMerId}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getChannelType())){
            SET("channel_type=#{tradeWithdrawError.channelType}");
        }
        if (tradeWithdrawError.getInstallmentNum() != null) {
            SET("installment_num=#{tradeWithdrawError.installmentNum}");
        }
        if (tradeWithdrawError.getOrderAmount() != null) {
            SET("order_amount=#{tradeWithdrawError.orderAmount}");
        }
        if (tradeWithdrawError.getEachMoney() != null) {
            SET("each_money=#{tradeWithdrawError.eachMoney}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getCardHolderRate())){
            SET("card_holder_rate=#{tradeWithdrawError.cardHolderRate}");
        }
        WHERE("id = #{tradeWithdrawError.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        TradeWithdrawErrorDO tradeWithdrawError = (TradeWithdrawErrorDO) params.get("tradeWithdrawError");
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
        if (tradeWithdrawError.getId() != null) {
            WHERE("id=#{tradeWithdrawError.id}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getOrderNo())){
            WHERE("order_no=#{tradeWithdrawError.orderNo}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getOriginalOrderNo())){
            WHERE("original_order_no=#{tradeWithdrawError.originalOrderNo}");
        }
        if (tradeWithdrawError.getAppUserId() != null) {
            WHERE("app_user_id=#{tradeWithdrawError.appUserId}");
        }
        if (tradeWithdrawError.getAmount() != null) {
            WHERE("amount=#{tradeWithdrawError.amount}");
        }
        if (tradeWithdrawError.getFee() != null) {
            WHERE("fee=#{tradeWithdrawError.fee}");
        }
        if (tradeWithdrawError.getFund() != null) {
            WHERE("fund=#{tradeWithdrawError.fund}");
        }
        if (tradeWithdrawError.getTradeType() != null) {
            WHERE("trade_type=#{tradeWithdrawError.tradeType}");
        }
        if (tradeWithdrawError.getTradeSubType() != null) {
            WHERE("trade_sub_type=#{tradeWithdrawError.tradeSubType}");
        }
        if (tradeWithdrawError.getStatus() != null) {
            WHERE("status=#{tradeWithdrawError.status}");
        }
        if (tradeWithdrawError.getCreateTime() != null) {
            WHERE("create_time=#{tradeWithdrawError.createTime}");
        }
        if (tradeWithdrawError.getUpdateTime() != null) {
            WHERE("update_time=#{tradeWithdrawError.updateTime}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getRespCode())){
            WHERE("resp_code=#{tradeWithdrawError.respCode}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getRespMsg())){
            WHERE("resp_msg=#{tradeWithdrawError.respMsg}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getSuccTime())){
            WHERE("succ_time=#{tradeWithdrawError.succTime}");
        }
        if (tradeWithdrawError.getSettleMoney() != null) {
            WHERE("settle_money=#{tradeWithdrawError.settleMoney}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getPaymentDate())){
            WHERE("payment_date=#{tradeWithdrawError.paymentDate}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBackurl())){
            WHERE("backUrl=#{tradeWithdrawError.backurl}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankAccountType())){
            WHERE("bank_account_type=#{tradeWithdrawError.bankAccountType}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankAccountNo())){
            WHERE("bank_account_no=#{tradeWithdrawError.bankAccountNo}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankAccountName())){
            WHERE("bank_account_name=#{tradeWithdrawError.bankAccountName}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankAccountCardId())){
            WHERE("bank_account_card_id=#{tradeWithdrawError.bankAccountCardId}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankSubBankName())){
            WHERE("bank_sub_bank_name=#{tradeWithdrawError.bankSubBankName}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankOpenBank())){
            WHERE("bank_open_bank=#{tradeWithdrawError.bankOpenBank}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankOpenBankNum())){
            WHERE("bank_open_bank_num=#{tradeWithdrawError.bankOpenBankNum}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankAccountPhone())){
            WHERE("bank_account_phone=#{tradeWithdrawError.bankAccountPhone}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getChannelMerId())){
            WHERE("channel_mer_id=#{tradeWithdrawError.channelMerId}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getChannelType())){
            WHERE("channel_type=#{tradeWithdrawError.channelType}");
        }
        if (tradeWithdrawError.getInstallmentNum() != null) {
            WHERE("installment_num=#{tradeWithdrawError.installmentNum}");
        }
        if (tradeWithdrawError.getOrderAmount() != null) {
            WHERE("order_amount=#{tradeWithdrawError.orderAmount}");
        }
        if (tradeWithdrawError.getEachMoney() != null) {
            WHERE("each_money=#{tradeWithdrawError.eachMoney}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getCardHolderRate())){
            WHERE("card_holder_rate=#{tradeWithdrawError.cardHolderRate}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        TradeWithdrawErrorDO tradeWithdrawError = (TradeWithdrawErrorDO) params.get("tradeWithdrawError");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (tradeWithdrawError.getId() != null) {
            WHERE("id=#{tradeWithdrawError.id}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getOrderNo())){
            WHERE("order_no=#{tradeWithdrawError.orderNo}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getOriginalOrderNo())){
            WHERE("original_order_no=#{tradeWithdrawError.originalOrderNo}");
        }
        if (tradeWithdrawError.getAppUserId() != null) {
            WHERE("app_user_id=#{tradeWithdrawError.appUserId}");
        }
        if (tradeWithdrawError.getAmount() != null) {
            WHERE("amount=#{tradeWithdrawError.amount}");
        }
        if (tradeWithdrawError.getFee() != null) {
            WHERE("fee=#{tradeWithdrawError.fee}");
        }
        if (tradeWithdrawError.getFund() != null) {
            WHERE("fund=#{tradeWithdrawError.fund}");
        }
        if (tradeWithdrawError.getTradeType() != null) {
            WHERE("trade_type=#{tradeWithdrawError.tradeType}");
        }
        if (tradeWithdrawError.getTradeSubType() != null) {
            WHERE("trade_sub_type=#{tradeWithdrawError.tradeSubType}");
        }
        if (tradeWithdrawError.getStatus() != null) {
            WHERE("status=#{tradeWithdrawError.status}");
        }
        if (tradeWithdrawError.getCreateTime() != null) {
            WHERE("create_time=#{tradeWithdrawError.createTime}");
        }
        if (tradeWithdrawError.getUpdateTime() != null) {
            WHERE("update_time=#{tradeWithdrawError.updateTime}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getRespCode())){
            WHERE("resp_code=#{tradeWithdrawError.respCode}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getRespMsg())){
            WHERE("resp_msg=#{tradeWithdrawError.respMsg}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getSuccTime())){
            WHERE("succ_time=#{tradeWithdrawError.succTime}");
        }
        if (tradeWithdrawError.getSettleMoney() != null) {
            WHERE("settle_money=#{tradeWithdrawError.settleMoney}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getPaymentDate())){
            WHERE("payment_date=#{tradeWithdrawError.paymentDate}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBackurl())){
            WHERE("backUrl=#{tradeWithdrawError.backurl}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankAccountType())){
            WHERE("bank_account_type=#{tradeWithdrawError.bankAccountType}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankAccountNo())){
            WHERE("bank_account_no=#{tradeWithdrawError.bankAccountNo}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankAccountName())){
            WHERE("bank_account_name=#{tradeWithdrawError.bankAccountName}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankAccountCardId())){
            WHERE("bank_account_card_id=#{tradeWithdrawError.bankAccountCardId}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankSubBankName())){
            WHERE("bank_sub_bank_name=#{tradeWithdrawError.bankSubBankName}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankOpenBank())){
            WHERE("bank_open_bank=#{tradeWithdrawError.bankOpenBank}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankOpenBankNum())){
            WHERE("bank_open_bank_num=#{tradeWithdrawError.bankOpenBankNum}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getBankAccountPhone())){
            WHERE("bank_account_phone=#{tradeWithdrawError.bankAccountPhone}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getChannelMerId())){
            WHERE("channel_mer_id=#{tradeWithdrawError.channelMerId}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getChannelType())){
            WHERE("channel_type=#{tradeWithdrawError.channelType}");
        }
        if (tradeWithdrawError.getInstallmentNum() != null) {
            WHERE("installment_num=#{tradeWithdrawError.installmentNum}");
        }
        if (tradeWithdrawError.getOrderAmount() != null) {
            WHERE("order_amount=#{tradeWithdrawError.orderAmount}");
        }
        if (tradeWithdrawError.getEachMoney() != null) {
            WHERE("each_money=#{tradeWithdrawError.eachMoney}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawError.getCardHolderRate())){
            WHERE("card_holder_rate=#{tradeWithdrawError.cardHolderRate}");
        }
        }}.toString();
    }
}

