package net.fnsco.withhold.service.trade.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.withhold.service.trade.entity.TradeDataDO;
public class TradeDataProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_trade_data";

    public String update(Map<String, Object> params) {
        TradeDataDO tradeData = (TradeDataDO) params.get("tradeData");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (tradeData.getWithholdId() != null) {
            SET("withhold_id=#{tradeData.withholdId}");
        }
        if (tradeData.getTxnAmt() != null) {
            SET("txn_amt=#{tradeData.txnAmt}");
        }
        if (tradeData.getStatus() != null) {
            SET("status=#{tradeData.status}");
        }
        if (StringUtils.isNotBlank(tradeData.getFailReason())){
            SET("fail_reason=#{tradeData.failReason}");
        }
        if (StringUtils.isNotBlank(tradeData.getUserName())){
            SET("user_name=#{tradeData.userName}");
        }
        if (StringUtils.isNotBlank(tradeData.getMobile())){
            SET("mobile=#{tradeData.mobile}");
        }
        if (StringUtils.isNotBlank(tradeData.getBankCard())){
            SET("bank_card=#{tradeData.bankCard}");
        }
        if (StringUtils.isNotBlank(tradeData.getSubBankName())){
            SET("sub_bank_name=#{tradeData.subBankName}");
        }
        if (StringUtils.isNotBlank(tradeData.getAnBankId())){
            SET("an_bank_id=#{tradeData.anBankId}");
        }
        if (StringUtils.isNotBlank(tradeData.getAccountType())){
            SET("account_type=#{tradeData.accountType}");
        }
        if (StringUtils.isNotBlank(tradeData.getTxnType())){
            SET("txn_type=#{tradeData.txnType}");
        }
        if (StringUtils.isNotBlank(tradeData.getTxnSubType())){
            SET("txn_sub_type=#{tradeData.txnSubType}");
        }
        if (StringUtils.isNotBlank(tradeData.getBizType())){
            SET("biz_type=#{tradeData.bizType}");
        }
        if (StringUtils.isNotBlank(tradeData.getAccessType())){
            SET("access_type=#{tradeData.accessType}");
        }
        if (StringUtils.isNotBlank(tradeData.getMerId())){
            SET("mer_id=#{tradeData.merId}");
        }
        if (StringUtils.isNotBlank(tradeData.getOrderSn())){
            SET("order_sn=#{tradeData.orderSn}");
        }
        if (StringUtils.isNotBlank(tradeData.getTxnTime())){
            SET("txn_time=#{tradeData.txnTime}");
        }
        if (StringUtils.isNotBlank(tradeData.getCurrency())){
            SET("currency=#{tradeData.currency}");
        }
        if (StringUtils.isNotBlank(tradeData.getBackUrl())){
            SET("back_url=#{tradeData.backUrl}");
        }
        if (StringUtils.isNotBlank(tradeData.getPayType())){
            SET("pay_type=#{tradeData.payType}");
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
        if (StringUtils.isNotBlank(tradeData.getPurpose())){
            SET("purpose=#{tradeData.purpose}");
        }
        if (StringUtils.isNotBlank(tradeData.getRespCode())){
            SET("resp_code=#{tradeData.respCode}");
        }
        if (StringUtils.isNotBlank(tradeData.getRespMsg())){
            SET("resp_msg=#{tradeData.respMsg}");
        }
        if (StringUtils.isNotBlank(tradeData.getCertifType())){
            SET("certif_type=#{tradeData.certifType}");
        }
        if (StringUtils.isNotBlank(tradeData.getCertifyId())){
            SET("certify_id=#{tradeData.certifyId}");
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
        if (tradeData.getId() != null) {
            WHERE("id=#{tradeData.id}");
        }
        if (tradeData.getWithholdId() != null) {
            WHERE("withhold_id=#{tradeData.withholdId}");
        }
        if (tradeData.getTxnAmt() != null) {
            WHERE("txn_amt=#{tradeData.txnAmt}");
        }
        if (StringUtils.isNotBlank(tradeData.getFailReason())){
            WHERE("fail_reason=#{tradeData.failReason}");
        }
        if (StringUtils.isNotBlank(tradeData.getUserName())){
            WHERE("user_name like CONCAT('%',#{tradeData.userName},'%')");
        }
        if (StringUtils.isNotBlank(tradeData.getMobile())){
            WHERE("mobile=#{tradeData.mobile}");
        }
        if (StringUtils.isNotBlank(tradeData.getBankCard())){
            WHERE("bank_card=#{tradeData.bankCard}");
        }
        if (StringUtils.isNotBlank(tradeData.getSubBankName())){
            WHERE("sub_bank_name=#{tradeData.subBankName}");
        }
        if (StringUtils.isNotBlank(tradeData.getAnBankId())){
            WHERE("an_bank_id=#{tradeData.anBankId}");
        }
        if (StringUtils.isNotBlank(tradeData.getAccountType())){
            WHERE("account_type=#{tradeData.accountType}");
        }
        if (StringUtils.isNotBlank(tradeData.getTxnType())){
            WHERE("txn_type=#{tradeData.txnType}");
        }
        if (StringUtils.isNotBlank(tradeData.getTxnSubType())){
            WHERE("txn_sub_type=#{tradeData.txnSubType}");
        }
        if (StringUtils.isNotBlank(tradeData.getBizType())){
            WHERE("biz_type=#{tradeData.bizType}");
        }
        if (StringUtils.isNotBlank(tradeData.getAccessType())){
            WHERE("access_type=#{tradeData.accessType}");
        }
        if (StringUtils.isNotBlank(tradeData.getMerId())){
            WHERE("mer_id=#{tradeData.merId}");
        }
        if (StringUtils.isNotBlank(tradeData.getOrderSn())){
            WHERE("order_sn=#{tradeData.orderSn}");
        }
        if (StringUtils.isNotBlank(tradeData.getTxnTime())){
            WHERE("txn_time=#{tradeData.txnTime}");
        }
        if (StringUtils.isNotBlank(tradeData.getCurrency())){
            WHERE("currency=#{tradeData.currency}");
        }
        if (StringUtils.isNotBlank(tradeData.getBackUrl())){
            WHERE("back_url=#{tradeData.backUrl}");
        }
        if (StringUtils.isNotBlank(tradeData.getPayType())){
            WHERE("pay_type=#{tradeData.payType}");
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
        if (StringUtils.isNotBlank(tradeData.getPurpose())){
            WHERE("purpose=#{tradeData.purpose}");
        }
        if (StringUtils.isNotBlank(tradeData.getRespCode())){
            WHERE("resp_code=#{tradeData.respCode}");
        }
        if (StringUtils.isNotBlank(tradeData.getRespMsg())){
            WHERE("resp_msg=#{tradeData.respMsg}");
        }
        if (StringUtils.isNotBlank(tradeData.getCertifType())){
            WHERE("certif_type=#{tradeData.certifType}");
        }
        if (StringUtils.isNotBlank(tradeData.getCertifyId())){
            WHERE("certify_id like CONCAT('%',#{tradeData.certifyId},'%')");
        }//Withholdday
        if(StringUtils.isNotBlank(tradeData.getStartDate())){
            WHERE("txn_time >= #{tradeData.startDate}");
        }
        if(StringUtils.isNotBlank(tradeData.getEndDate())){
            WHERE("txn_time <= #{tradeData.endDate}");
        }
        if(StringUtils.isNotBlank(tradeData.getWithholdday())){
            //WHERE("substring(txn_time,7,2) = #{tradeData.withholdday}");
            WHERE("withhold_date like CONCAT('%',#{tradeData.withholdday})");
        }
        if(tradeData.getStatus()!=null){
            WHERE(" status = '"+tradeData.getStatus()+"'");
        }else{
            WHERE("status in (0,1,2,9)");
        }
        ORDER_BY("txn_time desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        TradeDataDO tradeData = (TradeDataDO) params.get("tradeData");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (tradeData.getId() != null) {
            WHERE("id=#{tradeData.id}");
        }
        if (tradeData.getWithholdId() != null) {
            WHERE("withhold_id=#{tradeData.withholdId}");
        }
        if (tradeData.getTxnAmt() != null) {
            WHERE("txn_amt=#{tradeData.txnAmt}");
        }
        if (StringUtils.isNotBlank(tradeData.getFailReason())){
            WHERE("fail_reason=#{tradeData.failReason}");
        }
        if (StringUtils.isNotBlank(tradeData.getUserName())){
            WHERE("user_name like CONCAT('%',#{tradeData.userName},'%')");
        }
        if (StringUtils.isNotBlank(tradeData.getMobile())){
            WHERE("mobile=#{tradeData.mobile}");
        }
        if (StringUtils.isNotBlank(tradeData.getBankCard())){
            WHERE("bank_card=#{tradeData.bankCard}");
        }
        if (StringUtils.isNotBlank(tradeData.getSubBankName())){
            WHERE("sub_bank_name=#{tradeData.subBankName}");
        }
        if (StringUtils.isNotBlank(tradeData.getAnBankId())){
            WHERE("an_bank_id=#{tradeData.anBankId}");
        }
        if (StringUtils.isNotBlank(tradeData.getAccountType())){
            WHERE("account_type=#{tradeData.accountType}");
        }
        if (StringUtils.isNotBlank(tradeData.getTxnType())){
            WHERE("txn_type=#{tradeData.txnType}");
        }
        if (StringUtils.isNotBlank(tradeData.getTxnSubType())){
            WHERE("txn_sub_type=#{tradeData.txnSubType}");
        }
        if (StringUtils.isNotBlank(tradeData.getBizType())){
            WHERE("biz_type=#{tradeData.bizType}");
        }
        if (StringUtils.isNotBlank(tradeData.getAccessType())){
            WHERE("access_type=#{tradeData.accessType}");
        }
        if (StringUtils.isNotBlank(tradeData.getMerId())){
            WHERE("mer_id=#{tradeData.merId}");
        }
        if (StringUtils.isNotBlank(tradeData.getOrderSn())){
            WHERE("order_sn=#{tradeData.orderSn}");
        }
        if (StringUtils.isNotBlank(tradeData.getTxnTime())){
            WHERE("txn_time=#{tradeData.txnTime}");
        }
        if (StringUtils.isNotBlank(tradeData.getCurrency())){
            WHERE("currency=#{tradeData.currency}");
        }
        if (StringUtils.isNotBlank(tradeData.getBackUrl())){
            WHERE("back_url=#{tradeData.backUrl}");
        }
        if (StringUtils.isNotBlank(tradeData.getPayType())){
            WHERE("pay_type=#{tradeData.payType}");
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
        if (StringUtils.isNotBlank(tradeData.getPurpose())){
            WHERE("purpose=#{tradeData.purpose}");
        }
        if (StringUtils.isNotBlank(tradeData.getRespCode())){
            WHERE("resp_code=#{tradeData.respCode}");
        }
        if (StringUtils.isNotBlank(tradeData.getRespMsg())){
            WHERE("resp_msg=#{tradeData.respMsg}");
        }
        if (StringUtils.isNotBlank(tradeData.getCertifType())){
            WHERE("certif_type=#{tradeData.certifType}");
        }
        if (StringUtils.isNotBlank(tradeData.getCertifyId())){
            WHERE("certify_id like CONCAT('%',#{tradeData.certifyId},'%')");
        }//Withholdday
        if(StringUtils.isNotBlank(tradeData.getStartDate())){
            WHERE("txn_time >= #{tradeData.startDate}");
        }
        if(StringUtils.isNotBlank(tradeData.getEndDate())){
            WHERE("txn_time <= #{tradeData.endDate}");
        }
        if(StringUtils.isNotBlank(tradeData.getWithholdday())){
            WHERE("withhold_date like CONCAT('%',#{tradeData.withholdday})");
        }
        if(tradeData.getStatus()!=null){
            WHERE(" status = '"+tradeData.getStatus()+"'");
        }else{
            WHERE("status in (0,1,2,9)");
        }
        }}.toString();
    }
    
    public String pageListCountTxnamt(Map<String, Object> params) {
        TradeDataDO tradeData = (TradeDataDO) params.get("tradeData");
        return new SQL() {{
        SELECT("SUM(txn_amt)");
        FROM(TABLE_NAME);
        if (tradeData.getId() != null) {
            WHERE("id=#{tradeData.id}");
        }
        if (tradeData.getWithholdId() != null) {
            WHERE("withhold_id=#{tradeData.withholdId}");
        }
        if (tradeData.getTxnAmt() != null) {
            WHERE("txn_amt=#{tradeData.txnAmt}");
        }
        if (StringUtils.isNotBlank(tradeData.getFailReason())){
            WHERE("fail_reason=#{tradeData.failReason}");
        }
        if (StringUtils.isNotBlank(tradeData.getUserName())){
            WHERE("user_name like CONCAT('%',#{tradeData.userName},'%')");
        }
        if (StringUtils.isNotBlank(tradeData.getMobile())){
            WHERE("mobile=#{tradeData.mobile}");
        }
        if (StringUtils.isNotBlank(tradeData.getBankCard())){
            WHERE("bank_card=#{tradeData.bankCard}");
        }
        if (StringUtils.isNotBlank(tradeData.getSubBankName())){
            WHERE("sub_bank_name=#{tradeData.subBankName}");
        }
        if (StringUtils.isNotBlank(tradeData.getAnBankId())){
            WHERE("an_bank_id=#{tradeData.anBankId}");
        }
        if (StringUtils.isNotBlank(tradeData.getAccountType())){
            WHERE("account_type=#{tradeData.accountType}");
        }
        if (StringUtils.isNotBlank(tradeData.getTxnType())){
            WHERE("txn_type=#{tradeData.txnType}");
        }
        if (StringUtils.isNotBlank(tradeData.getTxnSubType())){
            WHERE("txn_sub_type=#{tradeData.txnSubType}");
        }
        if (StringUtils.isNotBlank(tradeData.getBizType())){
            WHERE("biz_type=#{tradeData.bizType}");
        }
        if (StringUtils.isNotBlank(tradeData.getAccessType())){
            WHERE("access_type=#{tradeData.accessType}");
        }
        if (StringUtils.isNotBlank(tradeData.getMerId())){
            WHERE("mer_id=#{tradeData.merId}");
        }
        if (StringUtils.isNotBlank(tradeData.getOrderSn())){
            WHERE("order_sn=#{tradeData.orderSn}");
        }
        if (StringUtils.isNotBlank(tradeData.getTxnTime())){
            WHERE("txn_time=#{tradeData.txnTime}");
        }
        if (StringUtils.isNotBlank(tradeData.getCurrency())){
            WHERE("currency=#{tradeData.currency}");
        }
        if (StringUtils.isNotBlank(tradeData.getBackUrl())){
            WHERE("back_url=#{tradeData.backUrl}");
        }
        if (StringUtils.isNotBlank(tradeData.getPayType())){
            WHERE("pay_type=#{tradeData.payType}");
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
        if (StringUtils.isNotBlank(tradeData.getPurpose())){
            WHERE("purpose=#{tradeData.purpose}");
        }
        if (StringUtils.isNotBlank(tradeData.getRespCode())){
            WHERE("resp_code=#{tradeData.respCode}");
        }
        if (StringUtils.isNotBlank(tradeData.getRespMsg())){
            WHERE("resp_msg=#{tradeData.respMsg}");
        }
        if (StringUtils.isNotBlank(tradeData.getCertifType())){
            WHERE("certif_type=#{tradeData.certifType}");
        }
        if (StringUtils.isNotBlank(tradeData.getCertifyId())){
            WHERE("certify_id like CONCAT('%',#{tradeData.certifyId},'%')");
        }//Withholdday
        if(StringUtils.isNotBlank(tradeData.getStartDate())){
            WHERE("txn_time >= #{tradeData.startDate}");
        }
        if(StringUtils.isNotBlank(tradeData.getEndDate())){
            WHERE("txn_time <= #{tradeData.endDate}");
        }
        if(StringUtils.isNotBlank(tradeData.getWithholdday())){
            WHERE("withhold_date like CONCAT('%',#{tradeData.withholdday})");
        }
        if(tradeData.getStatus()!=null){
            WHERE("status=#{tradeData.status}");
        }
        }}.toString();
    }
}

