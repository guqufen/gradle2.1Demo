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
        if (StringUtils.isNotBlank(tradeOrder.getOrderNo())){
            SET("order_no=#{tradeOrder.orderNo}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getPayOrderNo())){
            SET("pay_order_no=#{tradeOrder.payOrderNo}");
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
        if (tradeOrder.getSettleStatus() != null) {
            SET("settle_status=#{tradeOrder.settleStatus}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getCreateUserId())){
            SET("create_user_id=#{tradeOrder.createUserId}");
        }
        if (tradeOrder.getCreateTime() != null) {
            SET("create_time=#{tradeOrder.createTime}");
        }
        if (tradeOrder.getSyncStatus() != null) {
            SET("sync_status=#{tradeOrder.syncStatus}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getInnerCode())){
            SET("inner_code=#{tradeOrder.innerCode}");
        }
        WHERE("id = #{tradeOrder.id}");
        }}.toString();
    }
    public String  queryAllNotComplete(Map<String, Object> params) {
        String orderNo = (String)params.get("orderNo");
        return new SQL() {{
        SELECT("*");
        FROM(TABLE_NAME);
        WHERE("resp_code = '1000'");
        if (StringUtils.isNotBlank(orderNo)){
            WHERE("order_no=#{orderNo}");
        }
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
        if (StringUtils.isNotBlank(tradeOrder.getOrderNo())){
            WHERE("order_no=#{tradeOrder.orderNo}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getPayOrderNo())){
            WHERE("pay_order_no=#{tradeOrder.payOrderNo}");
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
        if (tradeOrder.getSettleStatus() != null) {
            WHERE("settle_status=#{tradeOrder.settleStatus}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getCreateUserId())){
            WHERE("create_user_id=#{tradeOrder.createUserId}");
        }
        if (tradeOrder.getCreateTime() != null) {
            WHERE("create_time=#{tradeOrder.createTime}");
        }
        if (tradeOrder.getSyncStatus() != null) {
            WHERE("sync_status=#{tradeOrder.syncStatus}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getInnerCode())){
            WHERE("inner_code=#{tradeOrder.innerCode}");
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
        if (StringUtils.isNotBlank(tradeOrder.getOrderNo())){
            WHERE("order_no=#{tradeOrder.orderNo}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getPayOrderNo())){
            WHERE("pay_order_no=#{tradeOrder.payOrderNo}");
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
        if (tradeOrder.getSettleStatus() != null) {
            WHERE("settle_status=#{tradeOrder.settleStatus}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getCreateUserId())){
            WHERE("create_user_id=#{tradeOrder.createUserId}");
        }
        if (tradeOrder.getCreateTime() != null) {
            WHERE("create_time=#{tradeOrder.createTime}");
        }
        if (tradeOrder.getSyncStatus() != null) {
            WHERE("sync_status=#{tradeOrder.syncStatus}");
        }
        if (StringUtils.isNotBlank(tradeOrder.getInnerCode())){
            WHERE("inner_code=#{tradeOrder.innerCode}");
        }
        }}.toString();
    }
}

