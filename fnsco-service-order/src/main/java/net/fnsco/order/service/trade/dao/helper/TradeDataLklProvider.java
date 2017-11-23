package net.fnsco.order.service.trade.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.order.service.trade.entity.TradeDataLklDO;
public class TradeDataLklProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_trade_data_lkl";

    public String update(Map<String, Object> params) {
        TradeDataLklDO tradeDataLkl = (TradeDataLklDO) params.get("tradeDataLkl");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(tradeDataLkl.getInnerCode())){
            SET("inner_code=#{tradeDataLkl.innerCode}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getTxnType())){
            SET("txn_type=#{tradeDataLkl.txnType}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getTxnSubType())){
            SET("txn_sub_type=#{tradeDataLkl.txnSubType}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getCurrency())){
            SET("currency=#{tradeDataLkl.currency}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getPayTimeOut())){
            SET("pay_time_out=#{tradeDataLkl.payTimeOut}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSubject())){
            SET("subject=#{tradeDataLkl.subject}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getBody())){
            SET("body=#{tradeDataLkl.body}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getCustomerInfo())){
            SET("customer_info=#{tradeDataLkl.customerInfo}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getRemark())){
            SET("remark=#{tradeDataLkl.remark}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getCustomerIp())){
            SET("customer_ip=#{tradeDataLkl.customerIp}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getTn())){
            SET("tn=#{tradeDataLkl.tn}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getRespCode())){
            SET("resp_code=#{tradeDataLkl.respCode}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getRespMsg())){
            SET("resp_msg=#{tradeDataLkl.respMsg}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSettleAmount())){
            SET("settle_amount=#{tradeDataLkl.settleAmount}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSettleCurrency())){
            SET("settle_currency=#{tradeDataLkl.settleCurrency}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSettleDate())){
            SET("settle_date=#{tradeDataLkl.settleDate}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSuccTime())){
            SET("succ_time=#{tradeDataLkl.succTime}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getOrgMerOrderId())){
            SET("org_mer_order_id=#{tradeDataLkl.orgMerOrderId}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getCanRefAmt())){
            SET("can_ref_amt=#{tradeDataLkl.canRefAmt}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getRefCnt())){
            SET("ref_cnt=#{tradeDataLkl.refCnt}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getRefAmt())){
            SET("ref_amt=#{tradeDataLkl.refAmt}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getBankId())){
            SET("bank_id=#{tradeDataLkl.bankId}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getPpFlag())){
            SET("pp_flag=#{tradeDataLkl.ppFlag}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getPurpose())){
            SET("purpose=#{tradeDataLkl.purpose}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getDcType())){
            SET("dc_type=#{tradeDataLkl.dcType}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getCertifyId())){
            SET("certify_id=#{tradeDataLkl.certifyId}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getMsgDestId())){
            SET("msg_dest_id=#{tradeDataLkl.msgDestId}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getCustomerNm())){
            SET("customer_nm=#{tradeDataLkl.customerNm}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getPhoneNo())){
            SET("phone_no=#{tradeDataLkl.phoneNo}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSubOpenId())){
            SET("sub_open_id=#{tradeDataLkl.subOpenId}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getChannelType())){
            SET("channel_type=#{tradeDataLkl.channelType}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getAmt())){
            SET("amt=#{tradeDataLkl.amt}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getOrderNo())){
            SET("order_no=#{tradeDataLkl.orderNo}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getOrderTime())){
            SET("order_time=#{tradeDataLkl.orderTime}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getOrderInfo())){
            SET("order_info=#{tradeDataLkl.orderInfo}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getPayType())){
            SET("pay_type=#{tradeDataLkl.payType}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getPaySubType())){
            SET("pay_sub_type=#{tradeDataLkl.paySubType}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getTimeStamp())){
            SET("time_stamp=#{tradeDataLkl.timeStamp}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getTradeDetail())){
            SET("trade_detail=#{tradeDataLkl.tradeDetail}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getMerId())){
            SET("mer_id=#{tradeDataLkl.merId}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getTermId())){
            SET("term_id=#{tradeDataLkl.termId}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getBatchNo())){
            SET("batch_no=#{tradeDataLkl.batchNo}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getTraceNo())){
            SET("trace_no=#{tradeDataLkl.traceNo}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getReferNo())){
            SET("refer_no=#{tradeDataLkl.referNo}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getAuthCode())){
            SET("auth_code=#{tradeDataLkl.authCode}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getOrderIdScan())){
            SET("order_id_scan=#{tradeDataLkl.orderIdScan}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSource())){
            SET("source=#{tradeDataLkl.source}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getMd5())){
            SET("md5=#{tradeDataLkl.md5}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSendTime())){
            SET("send_time=#{tradeDataLkl.sendTime}");
        }
        if (tradeDataLkl.getCreateTime() != null) {
            SET("create_time=#{tradeDataLkl.createTime}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getStatus())){
            SET("status=#{tradeDataLkl.status}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getPayMedium())){
            SET("pay_medium=#{tradeDataLkl.payMedium}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getChannelTermCode())){
            SET("channel_term_code=#{tradeDataLkl.channelTermCode}");
        }
        WHERE("id = #{tradeDataLkl.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        TradeDataLklDO tradeDataLkl = (TradeDataLklDO) params.get("tradeDataLkl");
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
        if (StringUtils.isNotBlank(tradeDataLkl.getId())){
            WHERE("id=#{tradeDataLkl.id}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getInnerCode())){
            WHERE("inner_code=#{tradeDataLkl.innerCode}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getTxnType())){
            WHERE("txn_type=#{tradeDataLkl.txnType}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getTxnSubType())){
            WHERE("txn_sub_type=#{tradeDataLkl.txnSubType}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getCurrency())){
            WHERE("currency=#{tradeDataLkl.currency}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getPayTimeOut())){
            WHERE("pay_time_out=#{tradeDataLkl.payTimeOut}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSubject())){
            WHERE("subject=#{tradeDataLkl.subject}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getBody())){
            WHERE("body=#{tradeDataLkl.body}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getCustomerInfo())){
            WHERE("customer_info=#{tradeDataLkl.customerInfo}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getRemark())){
            WHERE("remark=#{tradeDataLkl.remark}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getCustomerIp())){
            WHERE("customer_ip=#{tradeDataLkl.customerIp}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getTn())){
            WHERE("tn=#{tradeDataLkl.tn}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getRespCode())){
            WHERE("resp_code=#{tradeDataLkl.respCode}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getRespMsg())){
            WHERE("resp_msg=#{tradeDataLkl.respMsg}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSettleAmount())){
            WHERE("settle_amount=#{tradeDataLkl.settleAmount}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSettleCurrency())){
            WHERE("settle_currency=#{tradeDataLkl.settleCurrency}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSettleDate())){
            WHERE("settle_date=#{tradeDataLkl.settleDate}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSuccTime())){
            WHERE("succ_time=#{tradeDataLkl.succTime}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getOrgMerOrderId())){
            WHERE("org_mer_order_id=#{tradeDataLkl.orgMerOrderId}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getCanRefAmt())){
            WHERE("can_ref_amt=#{tradeDataLkl.canRefAmt}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getRefCnt())){
            WHERE("ref_cnt=#{tradeDataLkl.refCnt}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getRefAmt())){
            WHERE("ref_amt=#{tradeDataLkl.refAmt}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getBankId())){
            WHERE("bank_id=#{tradeDataLkl.bankId}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getPpFlag())){
            WHERE("pp_flag=#{tradeDataLkl.ppFlag}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getPurpose())){
            WHERE("purpose=#{tradeDataLkl.purpose}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getDcType())){
            WHERE("dc_type=#{tradeDataLkl.dcType}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getCertifyId())){
            WHERE("certify_id=#{tradeDataLkl.certifyId}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getMsgDestId())){
            WHERE("msg_dest_id=#{tradeDataLkl.msgDestId}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getCustomerNm())){
            WHERE("customer_nm=#{tradeDataLkl.customerNm}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getPhoneNo())){
            WHERE("phone_no=#{tradeDataLkl.phoneNo}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSubOpenId())){
            WHERE("sub_open_id=#{tradeDataLkl.subOpenId}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getChannelType())){
            WHERE("channel_type=#{tradeDataLkl.channelType}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getAmt())){
            WHERE("amt=#{tradeDataLkl.amt}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getOrderNo())){
            WHERE("order_no=#{tradeDataLkl.orderNo}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getOrderTime())){
            WHERE("order_time=#{tradeDataLkl.orderTime}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getOrderInfo())){
            WHERE("order_info=#{tradeDataLkl.orderInfo}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getPayType())){
            WHERE("pay_type=#{tradeDataLkl.payType}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getPaySubType())){
            WHERE("pay_sub_type=#{tradeDataLkl.paySubType}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getTimeStamp())){
            WHERE("time_stamp=#{tradeDataLkl.timeStamp}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getTradeDetail())){
            WHERE("trade_detail=#{tradeDataLkl.tradeDetail}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getMerId())){
            WHERE("mer_id=#{tradeDataLkl.merId}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getTermId())){
            WHERE("term_id=#{tradeDataLkl.termId}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getBatchNo())){
            WHERE("batch_no=#{tradeDataLkl.batchNo}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getTraceNo())){
            WHERE("trace_no=#{tradeDataLkl.traceNo}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getReferNo())){
            WHERE("refer_no=#{tradeDataLkl.referNo}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getAuthCode())){
            WHERE("auth_code=#{tradeDataLkl.authCode}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getOrderIdScan())){
            WHERE("order_id_scan=#{tradeDataLkl.orderIdScan}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSource())){
            WHERE("source=#{tradeDataLkl.source}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getMd5())){
            WHERE("md5=#{tradeDataLkl.md5}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSendTime())){
            WHERE("send_time=#{tradeDataLkl.sendTime}");
        }
        if (tradeDataLkl.getCreateTime() != null) {
            WHERE("create_time=#{tradeDataLkl.createTime}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getStatus())){
            WHERE("status=#{tradeDataLkl.status}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getPayMedium())){
            WHERE("pay_medium=#{tradeDataLkl.payMedium}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getChannelTermCode())){
            WHERE("channel_term_code=#{tradeDataLkl.channelTermCode}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        TradeDataLklDO tradeDataLkl = (TradeDataLklDO) params.get("tradeDataLkl");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (StringUtils.isNotBlank(tradeDataLkl.getId())){
            WHERE("id=#{tradeDataLkl.id}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getInnerCode())){
            WHERE("inner_code=#{tradeDataLkl.innerCode}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getTxnType())){
            WHERE("txn_type=#{tradeDataLkl.txnType}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getTxnSubType())){
            WHERE("txn_sub_type=#{tradeDataLkl.txnSubType}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getCurrency())){
            WHERE("currency=#{tradeDataLkl.currency}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getPayTimeOut())){
            WHERE("pay_time_out=#{tradeDataLkl.payTimeOut}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSubject())){
            WHERE("subject=#{tradeDataLkl.subject}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getBody())){
            WHERE("body=#{tradeDataLkl.body}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getCustomerInfo())){
            WHERE("customer_info=#{tradeDataLkl.customerInfo}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getRemark())){
            WHERE("remark=#{tradeDataLkl.remark}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getCustomerIp())){
            WHERE("customer_ip=#{tradeDataLkl.customerIp}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getTn())){
            WHERE("tn=#{tradeDataLkl.tn}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getRespCode())){
            WHERE("resp_code=#{tradeDataLkl.respCode}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getRespMsg())){
            WHERE("resp_msg=#{tradeDataLkl.respMsg}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSettleAmount())){
            WHERE("settle_amount=#{tradeDataLkl.settleAmount}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSettleCurrency())){
            WHERE("settle_currency=#{tradeDataLkl.settleCurrency}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSettleDate())){
            WHERE("settle_date=#{tradeDataLkl.settleDate}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSuccTime())){
            WHERE("succ_time=#{tradeDataLkl.succTime}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getOrgMerOrderId())){
            WHERE("org_mer_order_id=#{tradeDataLkl.orgMerOrderId}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getCanRefAmt())){
            WHERE("can_ref_amt=#{tradeDataLkl.canRefAmt}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getRefCnt())){
            WHERE("ref_cnt=#{tradeDataLkl.refCnt}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getRefAmt())){
            WHERE("ref_amt=#{tradeDataLkl.refAmt}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getBankId())){
            WHERE("bank_id=#{tradeDataLkl.bankId}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getPpFlag())){
            WHERE("pp_flag=#{tradeDataLkl.ppFlag}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getPurpose())){
            WHERE("purpose=#{tradeDataLkl.purpose}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getDcType())){
            WHERE("dc_type=#{tradeDataLkl.dcType}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getCertifyId())){
            WHERE("certify_id=#{tradeDataLkl.certifyId}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getMsgDestId())){
            WHERE("msg_dest_id=#{tradeDataLkl.msgDestId}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getCustomerNm())){
            WHERE("customer_nm=#{tradeDataLkl.customerNm}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getPhoneNo())){
            WHERE("phone_no=#{tradeDataLkl.phoneNo}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSubOpenId())){
            WHERE("sub_open_id=#{tradeDataLkl.subOpenId}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getChannelType())){
            WHERE("channel_type=#{tradeDataLkl.channelType}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getAmt())){
            WHERE("amt=#{tradeDataLkl.amt}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getOrderNo())){
            WHERE("order_no=#{tradeDataLkl.orderNo}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getOrderTime())){
            WHERE("order_time=#{tradeDataLkl.orderTime}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getOrderInfo())){
            WHERE("order_info=#{tradeDataLkl.orderInfo}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getPayType())){
            WHERE("pay_type=#{tradeDataLkl.payType}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getPaySubType())){
            WHERE("pay_sub_type=#{tradeDataLkl.paySubType}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getTimeStamp())){
            WHERE("time_stamp=#{tradeDataLkl.timeStamp}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getTradeDetail())){
            WHERE("trade_detail=#{tradeDataLkl.tradeDetail}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getMerId())){
            WHERE("mer_id=#{tradeDataLkl.merId}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getTermId())){
            WHERE("term_id=#{tradeDataLkl.termId}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getBatchNo())){
            WHERE("batch_no=#{tradeDataLkl.batchNo}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getTraceNo())){
            WHERE("trace_no=#{tradeDataLkl.traceNo}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getReferNo())){
            WHERE("refer_no=#{tradeDataLkl.referNo}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getAuthCode())){
            WHERE("auth_code=#{tradeDataLkl.authCode}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getOrderIdScan())){
            WHERE("order_id_scan=#{tradeDataLkl.orderIdScan}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSource())){
            WHERE("source=#{tradeDataLkl.source}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getMd5())){
            WHERE("md5=#{tradeDataLkl.md5}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getSendTime())){
            WHERE("send_time=#{tradeDataLkl.sendTime}");
        }
        if (tradeDataLkl.getCreateTime() != null) {
            WHERE("create_time=#{tradeDataLkl.createTime}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getStatus())){
            WHERE("status=#{tradeDataLkl.status}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getPayMedium())){
            WHERE("pay_medium=#{tradeDataLkl.payMedium}");
        }
        if (StringUtils.isNotBlank(tradeDataLkl.getChannelTermCode())){
            WHERE("channel_term_code=#{tradeDataLkl.channelTermCode}");
        }
        }}.toString();
    }
}

