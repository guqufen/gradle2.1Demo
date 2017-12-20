package net.fnsco.trading.service.withdraw.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.trading.service.withdraw.entity.TradeWithdrawRedDO;

import org.apache.commons.lang3.StringUtils;
public class TradeWithdrawRedProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_trade_withdraw_red";

    public String update(Map<String, Object> params) {
        TradeWithdrawRedDO tradeWithdrawRed = (TradeWithdrawRedDO) params.get("tradeWithdrawRed");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(tradeWithdrawRed.getOrderNo())){
            SET("order_no=#{tradeWithdrawRed.orderNo}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getOriginalOrderNo())){
            SET("original_order_no=#{tradeWithdrawRed.originalOrderNo}");
        }
        if (tradeWithdrawRed.getAppUserId() != null) {
            SET("app_user_id=#{tradeWithdrawRed.appUserId}");
        }
        if (tradeWithdrawRed.getAmount() != null) {
            SET("amount=#{tradeWithdrawRed.amount}");
        }
        if (tradeWithdrawRed.getFee() != null) {
            SET("fee=#{tradeWithdrawRed.fee}");
        }
        if (tradeWithdrawRed.getFund() != null) {
            SET("fund=#{tradeWithdrawRed.fund}");
        }
        if (tradeWithdrawRed.getTradeType() != null) {
            SET("trade_type=#{tradeWithdrawRed.tradeType}");
        }
        if (tradeWithdrawRed.getTradeSubType() != null) {
            SET("trade_sub_type=#{tradeWithdrawRed.tradeSubType}");
        }
        if (tradeWithdrawRed.getStatus() != null) {
            SET("status=#{tradeWithdrawRed.status}");
        }
        if (tradeWithdrawRed.getCreateTime() != null) {
            SET("create_time=#{tradeWithdrawRed.createTime}");
        }
        if (tradeWithdrawRed.getUpdateTime() != null) {
            SET("update_time=#{tradeWithdrawRed.updateTime}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getRespCode())){
            SET("resp_code=#{tradeWithdrawRed.respCode}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getRespMsg())){
            SET("resp_msg=#{tradeWithdrawRed.respMsg}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getSuccTime())){
            SET("succ_time=#{tradeWithdrawRed.succTime}");
        }
        if (tradeWithdrawRed.getSettleMoney() != null) {
            SET("settle_money=#{tradeWithdrawRed.settleMoney}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getPaymentDate())){
            SET("payment_date=#{tradeWithdrawRed.paymentDate}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBackurl())){
            SET("backUrl=#{tradeWithdrawRed.backurl}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankAccountType())){
            SET("bank_account_type=#{tradeWithdrawRed.bankAccountType}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankAccountNo())){
            SET("bank_account_no=#{tradeWithdrawRed.bankAccountNo}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankAccountName())){
            SET("bank_account_name=#{tradeWithdrawRed.bankAccountName}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankAccountCardId())){
            SET("bank_account_card_id=#{tradeWithdrawRed.bankAccountCardId}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankSubBankName())){
            SET("bank_sub_bank_name=#{tradeWithdrawRed.bankSubBankName}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankOpenBank())){
            SET("bank_open_bank=#{tradeWithdrawRed.bankOpenBank}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankOpenBankNum())){
            SET("bank_open_bank_num=#{tradeWithdrawRed.bankOpenBankNum}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankAccountPhone())){
            SET("bank_account_phone=#{tradeWithdrawRed.bankAccountPhone}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getChannelMerId())){
            SET("channel_mer_id=#{tradeWithdrawRed.channelMerId}");
        }
        WHERE("id = #{tradeWithdrawRed.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        TradeWithdrawRedDO tradeWithdrawRed = (TradeWithdrawRedDO) params.get("tradeWithdrawRed");
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
        if (tradeWithdrawRed.getId() != null) {
            WHERE("id=#{tradeWithdrawRed.id}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getOrderNo())){
            WHERE("order_no=#{tradeWithdrawRed.orderNo}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getOriginalOrderNo())){
            WHERE("original_order_no=#{tradeWithdrawRed.originalOrderNo}");
        }
        if (tradeWithdrawRed.getAppUserId() != null) {
            WHERE("app_user_id=#{tradeWithdrawRed.appUserId}");
        }
        if (tradeWithdrawRed.getAmount() != null) {
            WHERE("amount=#{tradeWithdrawRed.amount}");
        }
        if (tradeWithdrawRed.getFee() != null) {
            WHERE("fee=#{tradeWithdrawRed.fee}");
        }
        if (tradeWithdrawRed.getFund() != null) {
            WHERE("fund=#{tradeWithdrawRed.fund}");
        }
        if (tradeWithdrawRed.getTradeType() != null) {
            WHERE("trade_type=#{tradeWithdrawRed.tradeType}");
        }
        if (tradeWithdrawRed.getTradeSubType() != null) {
            WHERE("trade_sub_type=#{tradeWithdrawRed.tradeSubType}");
        }
        if (tradeWithdrawRed.getStatus() != null) {
            WHERE("status=#{tradeWithdrawRed.status}");
        }
        if (tradeWithdrawRed.getCreateTime() != null) {
            WHERE("create_time=#{tradeWithdrawRed.createTime}");
        }
        if (tradeWithdrawRed.getUpdateTime() != null) {
            WHERE("update_time=#{tradeWithdrawRed.updateTime}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getRespCode())){
            WHERE("resp_code=#{tradeWithdrawRed.respCode}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getRespMsg())){
            WHERE("resp_msg=#{tradeWithdrawRed.respMsg}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getSuccTime())){
            WHERE("succ_time=#{tradeWithdrawRed.succTime}");
        }
        if (tradeWithdrawRed.getSettleMoney() != null) {
            WHERE("settle_money=#{tradeWithdrawRed.settleMoney}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getPaymentDate())){
            WHERE("payment_date=#{tradeWithdrawRed.paymentDate}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBackurl())){
            WHERE("backUrl=#{tradeWithdrawRed.backurl}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankAccountType())){
            WHERE("bank_account_type=#{tradeWithdrawRed.bankAccountType}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankAccountNo())){
            WHERE("bank_account_no=#{tradeWithdrawRed.bankAccountNo}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankAccountName())){
            WHERE("bank_account_name=#{tradeWithdrawRed.bankAccountName}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankAccountCardId())){
            WHERE("bank_account_card_id=#{tradeWithdrawRed.bankAccountCardId}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankSubBankName())){
            WHERE("bank_sub_bank_name=#{tradeWithdrawRed.bankSubBankName}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankOpenBank())){
            WHERE("bank_open_bank=#{tradeWithdrawRed.bankOpenBank}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankOpenBankNum())){
            WHERE("bank_open_bank_num=#{tradeWithdrawRed.bankOpenBankNum}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankAccountPhone())){
            WHERE("bank_account_phone=#{tradeWithdrawRed.bankAccountPhone}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getChannelMerId())){
            WHERE("channel_mer_id=#{tradeWithdrawRed.channelMerId}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        TradeWithdrawRedDO tradeWithdrawRed = (TradeWithdrawRedDO) params.get("tradeWithdrawRed");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (tradeWithdrawRed.getId() != null) {
            WHERE("id=#{tradeWithdrawRed.id}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getOrderNo())){
            WHERE("order_no=#{tradeWithdrawRed.orderNo}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getOriginalOrderNo())){
            WHERE("original_order_no=#{tradeWithdrawRed.originalOrderNo}");
        }
        if (tradeWithdrawRed.getAppUserId() != null) {
            WHERE("app_user_id=#{tradeWithdrawRed.appUserId}");
        }
        if (tradeWithdrawRed.getAmount() != null) {
            WHERE("amount=#{tradeWithdrawRed.amount}");
        }
        if (tradeWithdrawRed.getFee() != null) {
            WHERE("fee=#{tradeWithdrawRed.fee}");
        }
        if (tradeWithdrawRed.getFund() != null) {
            WHERE("fund=#{tradeWithdrawRed.fund}");
        }
        if (tradeWithdrawRed.getTradeType() != null) {
            WHERE("trade_type=#{tradeWithdrawRed.tradeType}");
        }
        if (tradeWithdrawRed.getTradeSubType() != null) {
            WHERE("trade_sub_type=#{tradeWithdrawRed.tradeSubType}");
        }
        if (tradeWithdrawRed.getStatus() != null) {
            WHERE("status=#{tradeWithdrawRed.status}");
        }
        if (tradeWithdrawRed.getCreateTime() != null) {
            WHERE("create_time=#{tradeWithdrawRed.createTime}");
        }
        if (tradeWithdrawRed.getUpdateTime() != null) {
            WHERE("update_time=#{tradeWithdrawRed.updateTime}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getRespCode())){
            WHERE("resp_code=#{tradeWithdrawRed.respCode}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getRespMsg())){
            WHERE("resp_msg=#{tradeWithdrawRed.respMsg}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getSuccTime())){
            WHERE("succ_time=#{tradeWithdrawRed.succTime}");
        }
        if (tradeWithdrawRed.getSettleMoney() != null) {
            WHERE("settle_money=#{tradeWithdrawRed.settleMoney}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getPaymentDate())){
            WHERE("payment_date=#{tradeWithdrawRed.paymentDate}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBackurl())){
            WHERE("backUrl=#{tradeWithdrawRed.backurl}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankAccountType())){
            WHERE("bank_account_type=#{tradeWithdrawRed.bankAccountType}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankAccountNo())){
            WHERE("bank_account_no=#{tradeWithdrawRed.bankAccountNo}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankAccountName())){
            WHERE("bank_account_name=#{tradeWithdrawRed.bankAccountName}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankAccountCardId())){
            WHERE("bank_account_card_id=#{tradeWithdrawRed.bankAccountCardId}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankSubBankName())){
            WHERE("bank_sub_bank_name=#{tradeWithdrawRed.bankSubBankName}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankOpenBank())){
            WHERE("bank_open_bank=#{tradeWithdrawRed.bankOpenBank}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankOpenBankNum())){
            WHERE("bank_open_bank_num=#{tradeWithdrawRed.bankOpenBankNum}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getBankAccountPhone())){
            WHERE("bank_account_phone=#{tradeWithdrawRed.bankAccountPhone}");
        }
        if (StringUtils.isNotBlank(tradeWithdrawRed.getChannelMerId())){
            WHERE("channel_mer_id=#{tradeWithdrawRed.channelMerId}");
        }
        }}.toString();
    }
}

