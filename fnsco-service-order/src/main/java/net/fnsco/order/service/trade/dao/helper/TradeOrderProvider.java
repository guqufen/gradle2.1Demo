package net.fnsco.order.service.trade.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.order.service.trade.entity.TradeOrderDO;
public class TradeOrderProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_trade_order";

    public String update(Map<String, Object> params) {
        TradeOrderDO tradeOrder = (TradeOrderDO) params.get("tradeOrder");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(tradeOrder.getOrderId())){
            SET("order_id=#{tradeOrder.orderId}");
        }
        if (tradeOrder.getPayOrderId() != null) {
            SET("pay_order_id=#{tradeOrder.payOrderId}");
        }
        if (tradeOrder.getTxnAmount() != null) {
            SET("txn_amount=#{tradeOrder.txnAmount}");
        }
        if (tradeOrder.getInstallmentNum() != null) {
            SET("installment_num=#{tradeOrder.installmentNum}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getRespCode())){
            SET("resp_code=#{tradeOrder.respCode}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getRespMsg())){
            SET("resp_msg=#{tradeOrder.respMsg}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getMercId())){
            SET("merc_id=#{tradeOrder.mercId}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getChannelMerId())){
            SET("channel_mer_id=#{tradeOrder.channelMerId}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getChannelType())){
            SET("channel_type=#{tradeOrder.channelType}");
        }
        if (tradeOrder.getCompleteTime() != null) {
            SET("complete_time=#{tradeOrder.completeTime}");
        }
        if (tradeOrder.getOrderCeateTime() != null) {
            SET("order_ceate_time=#{tradeOrder.orderCeateTime}");
        }
        if (tradeOrder.getTxnType() != null) {
            SET("txn_type=#{tradeOrder.txnType}");
        }
        if (tradeOrder.getTxnSubType() != null) {
            SET("txn_sub_type=#{tradeOrder.txnSubType}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getPayType())){
            SET("pay_type=#{tradeOrder.payType}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getPaySubType())){
            SET("pay_sub_type=#{tradeOrder.paySubType}");
        }
        if (tradeOrder.getSettleAmount() != null) {
            SET("settle_amount=#{tradeOrder.settleAmount}");
        }
        if (tradeOrder.getSettleDate() != null) {
            SET("settle_date=#{tradeOrder.settleDate}");
        }
        if (tradeOrder.getCreateUserId() != null) {
            SET("create_user_id=#{tradeOrder.createUserId}");
        }
        if (tradeOrder.getCreateTime() != null) {
            SET("create_time=#{tradeOrder.createTime}");
        }
        WHERE("id = #{tradeOrder.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        TradeOrderDO tradeOrder = (TradeOrderDO) params.get("tradeOrder");
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
        if (tradeOrder.getId() != null) {
            WHERE("id=#{tradeOrder.id}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getOrderId())){
            WHERE("order_id=#{tradeOrder.orderId}");
        }
        if (tradeOrder.getPayOrderId() != null) {
            WHERE("pay_order_id=#{tradeOrder.payOrderId}");
        }
        if (tradeOrder.getTxnAmount() != null) {
            WHERE("txn_amount=#{tradeOrder.txnAmount}");
        }
        if (tradeOrder.getInstallmentNum() != null) {
            WHERE("installment_num=#{tradeOrder.installmentNum}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getRespCode())){
            WHERE("resp_code=#{tradeOrder.respCode}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getRespMsg())){
            WHERE("resp_msg=#{tradeOrder.respMsg}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getMercId())){
            WHERE("merc_id=#{tradeOrder.mercId}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getChannelMerId())){
            WHERE("channel_mer_id=#{tradeOrder.channelMerId}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getChannelType())){
            WHERE("channel_type=#{tradeOrder.channelType}");
        }
        if (tradeOrder.getCompleteTime() != null) {
            WHERE("complete_time=#{tradeOrder.completeTime}");
        }
        if (tradeOrder.getOrderCeateTime() != null) {
            WHERE("order_ceate_time=#{tradeOrder.orderCeateTime}");
        }
        if (tradeOrder.getTxnType() != null) {
            WHERE("txn_type=#{tradeOrder.txnType}");
        }
        if (tradeOrder.getTxnSubType() != null) {
            WHERE("txn_sub_type=#{tradeOrder.txnSubType}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getPayType())){
            WHERE("pay_type=#{tradeOrder.payType}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getPaySubType())){
            WHERE("pay_sub_type=#{tradeOrder.paySubType}");
        }
        if (tradeOrder.getSettleAmount() != null) {
            WHERE("settle_amount=#{tradeOrder.settleAmount}");
        }
        if (tradeOrder.getSettleDate() != null) {
            WHERE("settle_date=#{tradeOrder.settleDate}");
        }
        if (tradeOrder.getCreateUserId() != null) {
            WHERE("create_user_id=#{tradeOrder.createUserId}");
        }
        if (tradeOrder.getCreateTime() != null) {
            WHERE("create_time=#{tradeOrder.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        TradeOrderDO tradeOrder = (TradeOrderDO) params.get("tradeOrder");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (tradeOrder.getId() != null) {
            WHERE("id=#{tradeOrder.id}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getOrderId())){
            WHERE("order_id=#{tradeOrder.orderId}");
        }
        if (tradeOrder.getPayOrderId() != null) {
            WHERE("pay_order_id=#{tradeOrder.payOrderId}");
        }
        if (tradeOrder.getTxnAmount() != null) {
            WHERE("txn_amount=#{tradeOrder.txnAmount}");
        }
        if (tradeOrder.getInstallmentNum() != null) {
            WHERE("installment_num=#{tradeOrder.installmentNum}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getRespCode())){
            WHERE("resp_code=#{tradeOrder.respCode}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getRespMsg())){
            WHERE("resp_msg=#{tradeOrder.respMsg}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getMercId())){
            WHERE("merc_id=#{tradeOrder.mercId}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getChannelMerId())){
            WHERE("channel_mer_id=#{tradeOrder.channelMerId}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getChannelType())){
            WHERE("channel_type=#{tradeOrder.channelType}");
        }
        if (tradeOrder.getCompleteTime() != null) {
            WHERE("complete_time=#{tradeOrder.completeTime}");
        }
        if (tradeOrder.getOrderCeateTime() != null) {
            WHERE("order_ceate_time=#{tradeOrder.orderCeateTime}");
        }
        if (tradeOrder.getTxnType() != null) {
            WHERE("txn_type=#{tradeOrder.txnType}");
        }
        if (tradeOrder.getTxnSubType() != null) {
            WHERE("txn_sub_type=#{tradeOrder.txnSubType}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getPayType())){
            WHERE("pay_type=#{tradeOrder.payType}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getPaySubType())){
            WHERE("pay_sub_type=#{tradeOrder.paySubType}");
        }
        if (tradeOrder.getSettleAmount() != null) {
            WHERE("settle_amount=#{tradeOrder.settleAmount}");
        }
        if (tradeOrder.getSettleDate() != null) {
            WHERE("settle_date=#{tradeOrder.settleDate}");
        }
        if (tradeOrder.getCreateUserId() != null) {
            WHERE("create_user_id=#{tradeOrder.createUserId}");
        }
        if (tradeOrder.getCreateTime() != null) {
            WHERE("create_time=#{tradeOrder.createTime}");
        }
        }}.toString();
    }
}

