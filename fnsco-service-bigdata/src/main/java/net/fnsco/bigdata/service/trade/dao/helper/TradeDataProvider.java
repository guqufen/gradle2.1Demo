package net.fnsco.bigdata.service.trade.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.bigdata.service.trade.entity.TradeDataDO;
public class TradeDataProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_trade_data";

    public String update(Map<String, Object> params) {
        TradeDataDO tradeData = (TradeDataDO) params.get("tradeData");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(tradeData.getInnerCode())){
            SET("inner_code=#{tradeData.innerCode}");
        }
        if (StringUtils.isNotBlank(tradeData.getTxnType())){
            SET("txn_type=#{tradeData.txnType}");
        }
        if (StringUtils.isNotBlank(tradeData.getTxnSubType())){
            SET("txn_sub_type=#{tradeData.txnSubType}");
        }
        if (StringUtils.isNotBlank(tradeData.getCurrency())){
            SET("currency=#{tradeData.currency}");
        }
        if (StringUtils.isNotBlank(tradeData.getPayTimeOut())){
            SET("pay_time_out=#{tradeData.payTimeOut}");
        }
        if (StringUtils.isNotBlank(tradeData.getSubject())){
            SET("subject=#{tradeData.subject}");
        }
        if (StringUtils.isNotBlank(tradeData.getBody())){
            SET("body=#{tradeData.body}");
        }
        if (StringUtils.isNotBlank(tradeData.getCustomerInfo())){
            SET("customer_info=#{tradeData.customerInfo}");
        }
        if (StringUtils.isNotBlank(tradeData.getRemark())){
            SET("remark=#{tradeData.remark}");
        }
        if (StringUtils.isNotBlank(tradeData.getCustomerIp())){
            SET("customer_ip=#{tradeData.customerIp}");
        }
        if (StringUtils.isNotBlank(tradeData.getTn())){
            SET("tn=#{tradeData.tn}");
        }
        if (StringUtils.isNotBlank(tradeData.getRespCode())){
            SET("resp_code=#{tradeData.respCode}");
        }
        if (StringUtils.isNotBlank(tradeData.getRespMsg())){
            SET("resp_msg=#{tradeData.respMsg}");
        }
        if (StringUtils.isNotBlank(tradeData.getSettleAmount())){
            SET("settle_amount=#{tradeData.settleAmount}");
        }
        if (StringUtils.isNotBlank(tradeData.getSettleCurrency())){
            SET("settle_currency=#{tradeData.settleCurrency}");
        }
        if (StringUtils.isNotBlank(tradeData.getSettleDate())){
            SET("settle_date=#{tradeData.settleDate}");
        }
        if (StringUtils.isNotBlank(tradeData.getSuccTime())){
            SET("succ_time=#{tradeData.succTime}");
        }
        if (StringUtils.isNotBlank(tradeData.getOrgMerOrderId())){
            SET("org_mer_order_id=#{tradeData.orgMerOrderId}");
        }
        if (StringUtils.isNotBlank(tradeData.getCanRefAmt())){
            SET("can_ref_amt=#{tradeData.canRefAmt}");
        }
        if (StringUtils.isNotBlank(tradeData.getRefCnt())){
            SET("ref_cnt=#{tradeData.refCnt}");
        }
        if (StringUtils.isNotBlank(tradeData.getRefAmt())){
            SET("ref_amt=#{tradeData.refAmt}");
        }
        if (StringUtils.isNotBlank(tradeData.getBankId())){
            SET("bank_id=#{tradeData.bankId}");
        }
        if (StringUtils.isNotBlank(tradeData.getPpFlag())){
            SET("pp_flag=#{tradeData.ppFlag}");
        }
        if (StringUtils.isNotBlank(tradeData.getPurpose())){
            SET("purpose=#{tradeData.purpose}");
        }
        if (StringUtils.isNotBlank(tradeData.getDcType())){
            SET("dc_type=#{tradeData.dcType}");
        }
        if (StringUtils.isNotBlank(tradeData.getCertifyId())){
            SET("certify_id=#{tradeData.certifyId}");
        }
        if (StringUtils.isNotBlank(tradeData.getMsgDestId())){
            SET("msg_dest_id=#{tradeData.msgDestId}");
        }
        if (StringUtils.isNotBlank(tradeData.getCustomerNm())){
            SET("customer_nm=#{tradeData.customerNm}");
        }
        if (StringUtils.isNotBlank(tradeData.getPhoneNo())){
            SET("phone_no=#{tradeData.phoneNo}");
        }
        if (StringUtils.isNotBlank(tradeData.getSubOpenId())){
            SET("sub_open_id=#{tradeData.subOpenId}");
        }
        if (StringUtils.isNotBlank(tradeData.getChannelType())){
            SET("channel_type=#{tradeData.channelType}");
        }
        if (StringUtils.isNotBlank(tradeData.getAmt())){
            SET("amt=#{tradeData.amt}");
        }
        if (StringUtils.isNotBlank(tradeData.getOrderNo())){
            SET("order_no=#{tradeData.orderNo}");
        }
        if (StringUtils.isNotBlank(tradeData.getOrderTime())){
            SET("order_time=#{tradeData.orderTime}");
        }
        if (StringUtils.isNotBlank(tradeData.getOrderInfo())){
            SET("order_info=#{tradeData.orderInfo}");
        }
        if (StringUtils.isNotBlank(tradeData.getPayType())){
            SET("pay_type=#{tradeData.payType}");
        }
        if (StringUtils.isNotBlank(tradeData.getPaySubType())){
            SET("pay_sub_type=#{tradeData.paySubType}");
        }
        if (StringUtils.isNotBlank(tradeData.getTimeStamp())){
            SET("time_stamp=#{tradeData.timeStamp}");
        }
        if (StringUtils.isNotBlank(tradeData.getTradeDetail())){
            SET("trade_detail=#{tradeData.tradeDetail}");
        }
        if (StringUtils.isNotBlank(tradeData.getMerId())){
            SET("mer_id=#{tradeData.merId}");
        }
        if (StringUtils.isNotBlank(tradeData.getTermId())){
            SET("term_id=#{tradeData.termId}");
        }
        if (StringUtils.isNotBlank(tradeData.getBatchNo())){
            SET("batch_no=#{tradeData.batchNo}");
        }
        if (StringUtils.isNotBlank(tradeData.getTraceNo())){
            SET("trace_no=#{tradeData.traceNo}");
        }
        if (StringUtils.isNotBlank(tradeData.getReferNo())){
            SET("refer_no=#{tradeData.referNo}");
        }
        if (StringUtils.isNotBlank(tradeData.getAuthCode())){
            SET("auth_code=#{tradeData.authCode}");
        }
        if (StringUtils.isNotBlank(tradeData.getOrderIdScan())){
            SET("order_id_scan=#{tradeData.orderIdScan}");
        }
        if (StringUtils.isNotBlank(tradeData.getSource())){
            SET("source=#{tradeData.source}");
        }
        if (StringUtils.isNotBlank(tradeData.getMd5())){
            SET("md5=#{tradeData.md5}");
        }
        if (StringUtils.isNotBlank(tradeData.getSendTime())){
            SET("send_time=#{tradeData.sendTime}");
        }
        if (tradeData.getCreateTime() != null) {
            SET("create_time=#{tradeData.createTime}");
        }
        if (StringUtils.isNotBlank(tradeData.getStatus())){
            SET("status=#{tradeData.status}");
        }
        WHERE("id = #{tradeData.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        TradeDataDO tradeData = (TradeDataDO) params.get("tradeData");
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
        if (StringUtils.isNotBlank(tradeData.getId())){
            WHERE("id=#{tradeData.id}");
        }
        if (StringUtils.isNotBlank(tradeData.getInnerCode())){
            WHERE("inner_code=#{tradeData.innerCode}");
        }
        if (StringUtils.isNotBlank(tradeData.getTxnType())){
            WHERE("txn_type=#{tradeData.txnType}");
        }
        if (StringUtils.isNotBlank(tradeData.getTxnSubType())){
            WHERE("txn_sub_type=#{tradeData.txnSubType}");
        }
        if (StringUtils.isNotBlank(tradeData.getCurrency())){
            WHERE("currency=#{tradeData.currency}");
        }
        if (StringUtils.isNotBlank(tradeData.getPayTimeOut())){
            WHERE("pay_time_out=#{tradeData.payTimeOut}");
        }
        if (StringUtils.isNotBlank(tradeData.getSubject())){
            WHERE("subject=#{tradeData.subject}");
        }
        if (StringUtils.isNotBlank(tradeData.getBody())){
            WHERE("body=#{tradeData.body}");
        }
        if (StringUtils.isNotBlank(tradeData.getCustomerInfo())){
            WHERE("customer_info=#{tradeData.customerInfo}");
        }
        if (StringUtils.isNotBlank(tradeData.getRemark())){
            WHERE("remark=#{tradeData.remark}");
        }
        if (StringUtils.isNotBlank(tradeData.getCustomerIp())){
            WHERE("customer_ip=#{tradeData.customerIp}");
        }
        if (StringUtils.isNotBlank(tradeData.getTn())){
            WHERE("tn=#{tradeData.tn}");
        }
        if (StringUtils.isNotBlank(tradeData.getRespCode())){
            WHERE("resp_code=#{tradeData.respCode}");
        }
        if (StringUtils.isNotBlank(tradeData.getRespMsg())){
            WHERE("resp_msg=#{tradeData.respMsg}");
        }
        if (StringUtils.isNotBlank(tradeData.getSettleAmount())){
            WHERE("settle_amount=#{tradeData.settleAmount}");
        }
        if (StringUtils.isNotBlank(tradeData.getSettleCurrency())){
            WHERE("settle_currency=#{tradeData.settleCurrency}");
        }
        if (StringUtils.isNotBlank(tradeData.getSettleDate())){
            WHERE("settle_date=#{tradeData.settleDate}");
        }
        if (StringUtils.isNotBlank(tradeData.getSuccTime())){
            WHERE("succ_time=#{tradeData.succTime}");
        }
        if (StringUtils.isNotBlank(tradeData.getOrgMerOrderId())){
            WHERE("org_mer_order_id=#{tradeData.orgMerOrderId}");
        }
        if (StringUtils.isNotBlank(tradeData.getCanRefAmt())){
            WHERE("can_ref_amt=#{tradeData.canRefAmt}");
        }
        if (StringUtils.isNotBlank(tradeData.getRefCnt())){
            WHERE("ref_cnt=#{tradeData.refCnt}");
        }
        if (StringUtils.isNotBlank(tradeData.getRefAmt())){
            WHERE("ref_amt=#{tradeData.refAmt}");
        }
        if (StringUtils.isNotBlank(tradeData.getBankId())){
            WHERE("bank_id=#{tradeData.bankId}");
        }
        if (StringUtils.isNotBlank(tradeData.getPpFlag())){
            WHERE("pp_flag=#{tradeData.ppFlag}");
        }
        if (StringUtils.isNotBlank(tradeData.getPurpose())){
            WHERE("purpose=#{tradeData.purpose}");
        }
        if (StringUtils.isNotBlank(tradeData.getDcType())){
            WHERE("dc_type=#{tradeData.dcType}");
        }
        if (StringUtils.isNotBlank(tradeData.getCertifyId())){
            WHERE("certify_id=#{tradeData.certifyId}");
        }
        if (StringUtils.isNotBlank(tradeData.getMsgDestId())){
            WHERE("msg_dest_id=#{tradeData.msgDestId}");
        }
        if (StringUtils.isNotBlank(tradeData.getCustomerNm())){
            WHERE("customer_nm=#{tradeData.customerNm}");
        }
        if (StringUtils.isNotBlank(tradeData.getPhoneNo())){
            WHERE("phone_no=#{tradeData.phoneNo}");
        }
        if (StringUtils.isNotBlank(tradeData.getSubOpenId())){
            WHERE("sub_open_id=#{tradeData.subOpenId}");
        }
        if (StringUtils.isNotBlank(tradeData.getChannelType())){
            WHERE("channel_type=#{tradeData.channelType}");
        }
        if (StringUtils.isNotBlank(tradeData.getAmt())){
            WHERE("amt=#{tradeData.amt}");
        }
        if (StringUtils.isNotBlank(tradeData.getOrderNo())){
            WHERE("order_no=#{tradeData.orderNo}");
        }
        if (StringUtils.isNotBlank(tradeData.getOrderTime())){
            WHERE("order_time=#{tradeData.orderTime}");
        }
        if (StringUtils.isNotBlank(tradeData.getOrderInfo())){
            WHERE("order_info=#{tradeData.orderInfo}");
        }
        if (StringUtils.isNotBlank(tradeData.getPayType())){
            WHERE("pay_type=#{tradeData.payType}");
        }
        if (StringUtils.isNotBlank(tradeData.getPaySubType())){
            WHERE("pay_sub_type=#{tradeData.paySubType}");
        }
        if (StringUtils.isNotBlank(tradeData.getTimeStamp())){
            WHERE("time_stamp=#{tradeData.timeStamp}");
        }
        if (StringUtils.isNotBlank(tradeData.getTradeDetail())){
            WHERE("trade_detail=#{tradeData.tradeDetail}");
        }
        if (StringUtils.isNotBlank(tradeData.getMerId())){
            WHERE("mer_id=#{tradeData.merId}");
        }
        if (StringUtils.isNotBlank(tradeData.getTermId())){
            WHERE("term_id=#{tradeData.termId}");
        }
        if (StringUtils.isNotBlank(tradeData.getBatchNo())){
            WHERE("batch_no=#{tradeData.batchNo}");
        }
        if (StringUtils.isNotBlank(tradeData.getTraceNo())){
            WHERE("trace_no=#{tradeData.traceNo}");
        }
        if (StringUtils.isNotBlank(tradeData.getReferNo())){
            WHERE("refer_no=#{tradeData.referNo}");
        }
        if (StringUtils.isNotBlank(tradeData.getAuthCode())){
            WHERE("auth_code=#{tradeData.authCode}");
        }
        if (StringUtils.isNotBlank(tradeData.getOrderIdScan())){
            WHERE("order_id_scan=#{tradeData.orderIdScan}");
        }
        if (StringUtils.isNotBlank(tradeData.getSource())){
            WHERE("source=#{tradeData.source}");
        }
        if (StringUtils.isNotBlank(tradeData.getMd5())){
            WHERE("md5=#{tradeData.md5}");
        }
        if (StringUtils.isNotBlank(tradeData.getSendTime())){
            WHERE("send_time=#{tradeData.sendTime}");
        }
        if (tradeData.getCreateTime() != null) {
            WHERE("create_time=#{tradeData.createTime}");
        }
        if (StringUtils.isNotBlank(tradeData.getStatus())){
            WHERE("status=#{tradeData.status}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        TradeDataDO tradeData = (TradeDataDO) params.get("tradeData");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (StringUtils.isNotBlank(tradeData.getId())){
            WHERE("id=#{tradeData.id}");
        }
        if (StringUtils.isNotBlank(tradeData.getInnerCode())){
            WHERE("inner_code=#{tradeData.innerCode}");
        }
        if (StringUtils.isNotBlank(tradeData.getTxnType())){
            WHERE("txn_type=#{tradeData.txnType}");
        }
        if (StringUtils.isNotBlank(tradeData.getTxnSubType())){
            WHERE("txn_sub_type=#{tradeData.txnSubType}");
        }
        if (StringUtils.isNotBlank(tradeData.getCurrency())){
            WHERE("currency=#{tradeData.currency}");
        }
        if (StringUtils.isNotBlank(tradeData.getPayTimeOut())){
            WHERE("pay_time_out=#{tradeData.payTimeOut}");
        }
        if (StringUtils.isNotBlank(tradeData.getSubject())){
            WHERE("subject=#{tradeData.subject}");
        }
        if (StringUtils.isNotBlank(tradeData.getBody())){
            WHERE("body=#{tradeData.body}");
        }
        if (StringUtils.isNotBlank(tradeData.getCustomerInfo())){
            WHERE("customer_info=#{tradeData.customerInfo}");
        }
        if (StringUtils.isNotBlank(tradeData.getRemark())){
            WHERE("remark=#{tradeData.remark}");
        }
        if (StringUtils.isNotBlank(tradeData.getCustomerIp())){
            WHERE("customer_ip=#{tradeData.customerIp}");
        }
        if (StringUtils.isNotBlank(tradeData.getTn())){
            WHERE("tn=#{tradeData.tn}");
        }
        if (StringUtils.isNotBlank(tradeData.getRespCode())){
            WHERE("resp_code=#{tradeData.respCode}");
        }
        if (StringUtils.isNotBlank(tradeData.getRespMsg())){
            WHERE("resp_msg=#{tradeData.respMsg}");
        }
        if (StringUtils.isNotBlank(tradeData.getSettleAmount())){
            WHERE("settle_amount=#{tradeData.settleAmount}");
        }
        if (StringUtils.isNotBlank(tradeData.getSettleCurrency())){
            WHERE("settle_currency=#{tradeData.settleCurrency}");
        }
        if (StringUtils.isNotBlank(tradeData.getSettleDate())){
            WHERE("settle_date=#{tradeData.settleDate}");
        }
        if (StringUtils.isNotBlank(tradeData.getSuccTime())){
            WHERE("succ_time=#{tradeData.succTime}");
        }
        if (StringUtils.isNotBlank(tradeData.getOrgMerOrderId())){
            WHERE("org_mer_order_id=#{tradeData.orgMerOrderId}");
        }
        if (StringUtils.isNotBlank(tradeData.getCanRefAmt())){
            WHERE("can_ref_amt=#{tradeData.canRefAmt}");
        }
        if (StringUtils.isNotBlank(tradeData.getRefCnt())){
            WHERE("ref_cnt=#{tradeData.refCnt}");
        }
        if (StringUtils.isNotBlank(tradeData.getRefAmt())){
            WHERE("ref_amt=#{tradeData.refAmt}");
        }
        if (StringUtils.isNotBlank(tradeData.getBankId())){
            WHERE("bank_id=#{tradeData.bankId}");
        }
        if (StringUtils.isNotBlank(tradeData.getPpFlag())){
            WHERE("pp_flag=#{tradeData.ppFlag}");
        }
        if (StringUtils.isNotBlank(tradeData.getPurpose())){
            WHERE("purpose=#{tradeData.purpose}");
        }
        if (StringUtils.isNotBlank(tradeData.getDcType())){
            WHERE("dc_type=#{tradeData.dcType}");
        }
        if (StringUtils.isNotBlank(tradeData.getCertifyId())){
            WHERE("certify_id=#{tradeData.certifyId}");
        }
        if (StringUtils.isNotBlank(tradeData.getMsgDestId())){
            WHERE("msg_dest_id=#{tradeData.msgDestId}");
        }
        if (StringUtils.isNotBlank(tradeData.getCustomerNm())){
            WHERE("customer_nm=#{tradeData.customerNm}");
        }
        if (StringUtils.isNotBlank(tradeData.getPhoneNo())){
            WHERE("phone_no=#{tradeData.phoneNo}");
        }
        if (StringUtils.isNotBlank(tradeData.getSubOpenId())){
            WHERE("sub_open_id=#{tradeData.subOpenId}");
        }
        if (StringUtils.isNotBlank(tradeData.getChannelType())){
            WHERE("channel_type=#{tradeData.channelType}");
        }
        if (StringUtils.isNotBlank(tradeData.getAmt())){
            WHERE("amt=#{tradeData.amt}");
        }
        if (StringUtils.isNotBlank(tradeData.getOrderNo())){
            WHERE("order_no=#{tradeData.orderNo}");
        }
        if (StringUtils.isNotBlank(tradeData.getOrderTime())){
            WHERE("order_time=#{tradeData.orderTime}");
        }
        if (StringUtils.isNotBlank(tradeData.getOrderInfo())){
            WHERE("order_info=#{tradeData.orderInfo}");
        }
        if (StringUtils.isNotBlank(tradeData.getPayType())){
            WHERE("pay_type=#{tradeData.payType}");
        }
        if (StringUtils.isNotBlank(tradeData.getPaySubType())){
            WHERE("pay_sub_type=#{tradeData.paySubType}");
        }
        if (StringUtils.isNotBlank(tradeData.getTimeStamp())){
            WHERE("time_stamp=#{tradeData.timeStamp}");
        }
        if (StringUtils.isNotBlank(tradeData.getTradeDetail())){
            WHERE("trade_detail=#{tradeData.tradeDetail}");
        }
        if (StringUtils.isNotBlank(tradeData.getMerId())){
            WHERE("mer_id=#{tradeData.merId}");
        }
        if (StringUtils.isNotBlank(tradeData.getTermId())){
            WHERE("term_id=#{tradeData.termId}");
        }
        if (StringUtils.isNotBlank(tradeData.getBatchNo())){
            WHERE("batch_no=#{tradeData.batchNo}");
        }
        if (StringUtils.isNotBlank(tradeData.getTraceNo())){
            WHERE("trace_no=#{tradeData.traceNo}");
        }
        if (StringUtils.isNotBlank(tradeData.getReferNo())){
            WHERE("refer_no=#{tradeData.referNo}");
        }
        if (StringUtils.isNotBlank(tradeData.getAuthCode())){
            WHERE("auth_code=#{tradeData.authCode}");
        }
        if (StringUtils.isNotBlank(tradeData.getOrderIdScan())){
            WHERE("order_id_scan=#{tradeData.orderIdScan}");
        }
        if (StringUtils.isNotBlank(tradeData.getSource())){
            WHERE("source=#{tradeData.source}");
        }
        if (StringUtils.isNotBlank(tradeData.getMd5())){
            WHERE("md5=#{tradeData.md5}");
        }
        if (StringUtils.isNotBlank(tradeData.getSendTime())){
            WHERE("send_time=#{tradeData.sendTime}");
        }
        if (tradeData.getCreateTime() != null) {
            WHERE("create_time=#{tradeData.createTime}");
        }
        if (StringUtils.isNotBlank(tradeData.getStatus())){
            WHERE("status=#{tradeData.status}");
        }
        }}.toString();
    }
}

