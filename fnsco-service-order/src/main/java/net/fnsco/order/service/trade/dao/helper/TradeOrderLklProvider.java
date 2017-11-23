package net.fnsco.order.service.trade.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.order.service.trade.entity.TradeOrderLklDO;
public class TradeOrderLklProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_trade_order_lkl";

    public String update(Map<String, Object> params) {
        TradeOrderLklDO tradeOrderLkl = (TradeOrderLklDO) params.get("tradeOrderLkl");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(tradeOrderLkl.getOrderNo())){
            SET("order_no=#{tradeOrderLkl.orderNo}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getPayOrderNo())){
            SET("pay_order_no=#{tradeOrderLkl.payOrderNo}");
        }
        if (tradeOrderLkl.getTxnAmount() != null) {
            SET("txn_amount=#{tradeOrderLkl.txnAmount}");
        }
        if (tradeOrderLkl.getInstallmentNum() != null) {
            SET("installment_num=#{tradeOrderLkl.installmentNum}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getRespCode())){
            SET("resp_code=#{tradeOrderLkl.respCode}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getRespMsg())){
            SET("resp_msg=#{tradeOrderLkl.respMsg}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getEntityInnerCode())){
            SET("entity_inner_code=#{tradeOrderLkl.entityInnerCode}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getChannelMerId())){
            SET("channel_mer_id=#{tradeOrderLkl.channelMerId}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getChannelType())){
            SET("channel_type=#{tradeOrderLkl.channelType}");
        }
        if (tradeOrderLkl.getCompleteTime() != null) {
            SET("complete_time=#{tradeOrderLkl.completeTime}");
        }
        if (tradeOrderLkl.getOrderCeateTime() != null) {
            SET("order_ceate_time=#{tradeOrderLkl.orderCeateTime}");
        }
        if (tradeOrderLkl.getTxnType() != null) {
            SET("txn_type=#{tradeOrderLkl.txnType}");
        }
        if (tradeOrderLkl.getTxnSubType() != null) {
            SET("txn_sub_type=#{tradeOrderLkl.txnSubType}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getPayType())){
            SET("pay_type=#{tradeOrderLkl.payType}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getPaySubType())){
            SET("pay_sub_type=#{tradeOrderLkl.paySubType}");
        }
        if (tradeOrderLkl.getSettleAmount() != null) {
            SET("settle_amount=#{tradeOrderLkl.settleAmount}");
        }
        if (tradeOrderLkl.getSettleDate() != null) {
            SET("settle_date=#{tradeOrderLkl.settleDate}");
        }
        if (tradeOrderLkl.getSettleStatus() != null) {
            SET("settle_status=#{tradeOrderLkl.settleStatus}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getCreateUserId())){
            SET("create_user_id=#{tradeOrderLkl.createUserId}");
        }
        if (tradeOrderLkl.getCreateTime() != null) {
            SET("create_time=#{tradeOrderLkl.createTime}");
        }
        if (tradeOrderLkl.getSyncStatus() != null) {
            SET("sync_status=#{tradeOrderLkl.syncStatus}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getInnerCode())){
            SET("inner_code=#{tradeOrderLkl.innerCode}");
        }
        if (tradeOrderLkl.getHandleNum() != null) {
            SET("handle_num=#{tradeOrderLkl.handleNum}");
        }
        if (tradeOrderLkl.getOrderAmount() != null) {
            SET("order_amount=#{tradeOrderLkl.orderAmount}");
        }
        if (tradeOrderLkl.getEachMoney() != null) {
            SET("each_money=#{tradeOrderLkl.eachMoney}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getCardHolderRate())){
            SET("card_holder_rate=#{tradeOrderLkl.cardHolderRate}");
        }
        WHERE("id = #{tradeOrderLkl.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        TradeOrderLklDO tradeOrderLkl = (TradeOrderLklDO) params.get("tradeOrderLkl");
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
        if (tradeOrderLkl.getId() != null) {
            WHERE("id=#{tradeOrderLkl.id}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getOrderNo())){
            WHERE("order_no=#{tradeOrderLkl.orderNo}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getPayOrderNo())){
            WHERE("pay_order_no=#{tradeOrderLkl.payOrderNo}");
        }
        if (tradeOrderLkl.getTxnAmount() != null) {
            WHERE("txn_amount=#{tradeOrderLkl.txnAmount}");
        }
        if (tradeOrderLkl.getInstallmentNum() != null) {
            WHERE("installment_num=#{tradeOrderLkl.installmentNum}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getRespCode())){
            WHERE("resp_code=#{tradeOrderLkl.respCode}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getRespMsg())){
            WHERE("resp_msg=#{tradeOrderLkl.respMsg}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getEntityInnerCode())){
            WHERE("entity_inner_code=#{tradeOrderLkl.entityInnerCode}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getChannelMerId())){
            WHERE("channel_mer_id=#{tradeOrderLkl.channelMerId}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getChannelType())){
            WHERE("channel_type=#{tradeOrderLkl.channelType}");
        }
        if (tradeOrderLkl.getCompleteTime() != null) {
            WHERE("complete_time=#{tradeOrderLkl.completeTime}");
        }
        if (tradeOrderLkl.getOrderCeateTime() != null) {
            WHERE("order_ceate_time=#{tradeOrderLkl.orderCeateTime}");
        }
        if (tradeOrderLkl.getTxnType() != null) {
            WHERE("txn_type=#{tradeOrderLkl.txnType}");
        }
        if (tradeOrderLkl.getTxnSubType() != null) {
            WHERE("txn_sub_type=#{tradeOrderLkl.txnSubType}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getPayType())){
            WHERE("pay_type=#{tradeOrderLkl.payType}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getPaySubType())){
            WHERE("pay_sub_type=#{tradeOrderLkl.paySubType}");
        }
        if (tradeOrderLkl.getSettleAmount() != null) {
            WHERE("settle_amount=#{tradeOrderLkl.settleAmount}");
        }
        if (tradeOrderLkl.getSettleDate() != null) {
            WHERE("settle_date=#{tradeOrderLkl.settleDate}");
        }
        if (tradeOrderLkl.getSettleStatus() != null) {
            WHERE("settle_status=#{tradeOrderLkl.settleStatus}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getCreateUserId())){
            WHERE("create_user_id=#{tradeOrderLkl.createUserId}");
        }
        if (tradeOrderLkl.getCreateTime() != null) {
            WHERE("create_time=#{tradeOrderLkl.createTime}");
        }
        if (tradeOrderLkl.getSyncStatus() != null) {
            WHERE("sync_status=#{tradeOrderLkl.syncStatus}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getInnerCode())){
            WHERE("inner_code=#{tradeOrderLkl.innerCode}");
        }
        if (tradeOrderLkl.getHandleNum() != null) {
            WHERE("handle_num=#{tradeOrderLkl.handleNum}");
        }
        if (tradeOrderLkl.getOrderAmount() != null) {
            WHERE("order_amount=#{tradeOrderLkl.orderAmount}");
        }
        if (tradeOrderLkl.getEachMoney() != null) {
            WHERE("each_money=#{tradeOrderLkl.eachMoney}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getCardHolderRate())){
            WHERE("card_holder_rate=#{tradeOrderLkl.cardHolderRate}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        TradeOrderLklDO tradeOrderLkl = (TradeOrderLklDO) params.get("tradeOrderLkl");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (tradeOrderLkl.getId() != null) {
            WHERE("id=#{tradeOrderLkl.id}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getOrderNo())){
            WHERE("order_no=#{tradeOrderLkl.orderNo}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getPayOrderNo())){
            WHERE("pay_order_no=#{tradeOrderLkl.payOrderNo}");
        }
        if (tradeOrderLkl.getTxnAmount() != null) {
            WHERE("txn_amount=#{tradeOrderLkl.txnAmount}");
        }
        if (tradeOrderLkl.getInstallmentNum() != null) {
            WHERE("installment_num=#{tradeOrderLkl.installmentNum}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getRespCode())){
            WHERE("resp_code=#{tradeOrderLkl.respCode}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getRespMsg())){
            WHERE("resp_msg=#{tradeOrderLkl.respMsg}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getEntityInnerCode())){
            WHERE("entity_inner_code=#{tradeOrderLkl.entityInnerCode}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getChannelMerId())){
            WHERE("channel_mer_id=#{tradeOrderLkl.channelMerId}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getChannelType())){
            WHERE("channel_type=#{tradeOrderLkl.channelType}");
        }
        if (tradeOrderLkl.getCompleteTime() != null) {
            WHERE("complete_time=#{tradeOrderLkl.completeTime}");
        }
        if (tradeOrderLkl.getOrderCeateTime() != null) {
            WHERE("order_ceate_time=#{tradeOrderLkl.orderCeateTime}");
        }
        if (tradeOrderLkl.getTxnType() != null) {
            WHERE("txn_type=#{tradeOrderLkl.txnType}");
        }
        if (tradeOrderLkl.getTxnSubType() != null) {
            WHERE("txn_sub_type=#{tradeOrderLkl.txnSubType}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getPayType())){
            WHERE("pay_type=#{tradeOrderLkl.payType}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getPaySubType())){
            WHERE("pay_sub_type=#{tradeOrderLkl.paySubType}");
        }
        if (tradeOrderLkl.getSettleAmount() != null) {
            WHERE("settle_amount=#{tradeOrderLkl.settleAmount}");
        }
        if (tradeOrderLkl.getSettleDate() != null) {
            WHERE("settle_date=#{tradeOrderLkl.settleDate}");
        }
        if (tradeOrderLkl.getSettleStatus() != null) {
            WHERE("settle_status=#{tradeOrderLkl.settleStatus}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getCreateUserId())){
            WHERE("create_user_id=#{tradeOrderLkl.createUserId}");
        }
        if (tradeOrderLkl.getCreateTime() != null) {
            WHERE("create_time=#{tradeOrderLkl.createTime}");
        }
        if (tradeOrderLkl.getSyncStatus() != null) {
            WHERE("sync_status=#{tradeOrderLkl.syncStatus}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getInnerCode())){
            WHERE("inner_code=#{tradeOrderLkl.innerCode}");
        }
        if (tradeOrderLkl.getHandleNum() != null) {
            WHERE("handle_num=#{tradeOrderLkl.handleNum}");
        }
        if (tradeOrderLkl.getOrderAmount() != null) {
            WHERE("order_amount=#{tradeOrderLkl.orderAmount}");
        }
        if (tradeOrderLkl.getEachMoney() != null) {
            WHERE("each_money=#{tradeOrderLkl.eachMoney}");
        }
        if (StringUtils.isNotBlank(tradeOrderLkl.getCardHolderRate())){
            WHERE("card_holder_rate=#{tradeOrderLkl.cardHolderRate}");
        }
        }}.toString();
    }
}

