package net.fnsco.trading.service.withdraw.dao.helper;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

import net.fnsco.trading.service.withdraw.entity.TradeWithdrawDO;

public class TradeWithdrawProvider {

    private Logger              logger     = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_trade_withdraw";

    public String updateOnlyFail(Map<String, Object> params) {
        TradeWithdrawDO tradeWithdraw = (TradeWithdrawDO) params.get("tradeWithdraw");
        return new SQL() {
            {
                UPDATE(TABLE_NAME);
                if (StringUtils.isNotBlank(tradeWithdraw.getOrderNo())) {
                    SET("order_no=#{tradeWithdraw.orderNo}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getOriginalOrderNo())) {
                    SET("original_order_no=#{tradeWithdraw.originalOrderNo}");
                }
                if (tradeWithdraw.getAppUserId() != null) {
                    SET("app_user_id=#{tradeWithdraw.appUserId}");
                }
                if (tradeWithdraw.getAmount() != null) {
                    SET("amount=#{tradeWithdraw.amount}");
                }
                if (tradeWithdraw.getFee() != null) {
                    SET("fee=#{tradeWithdraw.fee}");
                }
                if (tradeWithdraw.getSettleMoney() != null) {
                    SET("settle_money=#{tradeWithdraw.settleMoney}");
                }
                if (tradeWithdraw.getFund() != null) {
                    SET("fund=#{tradeWithdraw.fund}");
                }
                if (tradeWithdraw.getTradeType() != null) {
                    SET("trade_type=#{tradeWithdraw.tradeType}");
                }
                if (tradeWithdraw.getTradeSubType() != null) {
                    SET("trade_sub_type=#{tradeWithdraw.tradeSubType}");
                }
                if (tradeWithdraw.getStatus() != null) {
                    SET("status=#{tradeWithdraw.status}");
                }
                if (tradeWithdraw.getCreateTime() != null) {
                    SET("create_time=#{tradeWithdraw.createTime}");
                }
                if (tradeWithdraw.getUpdateTime() != null) {
                    SET("update_time=#{tradeWithdraw.updateTime}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getRespCode())) {
                    SET("resp_code=#{tradeWithdraw.respCode}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getRespMsg())) {
                    SET("resp_msg=#{tradeWithdraw.respMsg}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getPaymentDate())) {
                    SET("payment_date=#{tradeWithdraw.paymentDate}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getSuccTime())) {
                    SET("succ_time=#{tradeWithdraw.succTime}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBackUrl())) {
                    SET("back_url=#{tradeWithdraw.backUrl}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankAccountType())) {
                    SET("bank_account_type=#{tradeWithdraw.bankAccountType}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankAccountNo())) {
                    SET("bank_account_no=#{tradeWithdraw.bankAccountNo}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankAccountName())) {
                    SET("bank_account_name=#{tradeWithdraw.bankAccountName}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankAccountCardId())) {
                    SET("bank_account_card_id=#{tradeWithdraw.bankAccountCardId}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankSubBankName())) {
                    SET("bank_sub_bank_name=#{tradeWithdraw.bankSubBankName}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankOpenBank())) {
                    SET("bank_open_bank=#{tradeWithdraw.bankOpenBank}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankOpenBankNum())) {
                    SET("bank_open_bank_num=#{tradeWithdraw.bankOpenBankNum}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankAccountPhone())) {
                    SET("bank_account_phone=#{tradeWithdraw.bankAccountPhone}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getChannelMerId())) {
                    SET("channel_mer_id=#{tradeWithdraw.channelMerId}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getChannelType())) {
                    SET("channel_type=#{tradeWithdraw.channelType}");
                }
                if (tradeWithdraw.getInstallmentNum() != null) {
                    SET("installment_num=#{tradeWithdraw.installmentNum}");
                }
                if (tradeWithdraw.getOrderAmount() != null) {
                    SET("order_amount=#{tradeWithdraw.orderAmount}");
                }
                if (tradeWithdraw.getEachMoney() != null) {
                    SET("each_money=#{tradeWithdraw.eachMoney}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getCardHolderRate())) {
                    SET("card_holder_rate=#{tradeWithdraw.cardHolderRate}");
                }
            }
        }.toString();
    }

    public String update(Map<String, Object> params) {
        TradeWithdrawDO tradeWithdraw = (TradeWithdrawDO) params.get("tradeWithdraw");
        return new SQL() {
            {
                UPDATE(TABLE_NAME);
                if (StringUtils.isNotBlank(tradeWithdraw.getOrderNo())) {
                    SET("order_no=#{tradeWithdraw.orderNo}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getOriginalOrderNo())) {
                    SET("original_order_no=#{tradeWithdraw.originalOrderNo}");
                }
                if (tradeWithdraw.getAppUserId() != null) {
                    SET("app_user_id=#{tradeWithdraw.appUserId}");
                }
                if (tradeWithdraw.getAmount() != null) {
                    SET("amount=#{tradeWithdraw.amount}");
                }
                if (tradeWithdraw.getFee() != null) {
                    SET("fee=#{tradeWithdraw.fee}");
                }
                if (tradeWithdraw.getSettleMoney() != null) {
                    SET("settle_money=#{tradeWithdraw.settleMoney}");
                }
                if (tradeWithdraw.getFund() != null) {
                    SET("fund=#{tradeWithdraw.fund}");
                }
                if (tradeWithdraw.getTradeType() != null) {
                    SET("trade_type=#{tradeWithdraw.tradeType}");
                }
                if (tradeWithdraw.getTradeSubType() != null) {
                    SET("trade_sub_type=#{tradeWithdraw.tradeSubType}");
                }
                if (tradeWithdraw.getStatus() != null) {
                    SET("status=#{tradeWithdraw.status}");
                }
                if (tradeWithdraw.getCreateTime() != null) {
                    SET("create_time=#{tradeWithdraw.createTime}");
                }
                if (tradeWithdraw.getUpdateTime() != null) {
                    SET("update_time=#{tradeWithdraw.updateTime}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getRespCode())) {
                    SET("resp_code=#{tradeWithdraw.respCode}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getRespMsg())) {
                    SET("resp_msg=#{tradeWithdraw.respMsg}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getPaymentDate())) {
                    SET("payment_date=#{tradeWithdraw.paymentDate}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getSuccTime())) {
                    SET("succ_time=#{tradeWithdraw.succTime}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBackUrl())) {
                    SET("back_url=#{tradeWithdraw.backUrl}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankAccountType())) {
                    SET("bank_account_type=#{tradeWithdraw.bankAccountType}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankAccountNo())) {
                    SET("bank_account_no=#{tradeWithdraw.bankAccountNo}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankAccountName())) {
                    SET("bank_account_name=#{tradeWithdraw.bankAccountName}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankAccountCardId())) {
                    SET("bank_account_card_id=#{tradeWithdraw.bankAccountCardId}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankSubBankName())) {
                    SET("bank_sub_bank_name=#{tradeWithdraw.bankSubBankName}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankOpenBank())) {
                    SET("bank_open_bank=#{tradeWithdraw.bankOpenBank}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankOpenBankNum())) {
                    SET("bank_open_bank_num=#{tradeWithdraw.bankOpenBankNum}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankAccountPhone())) {
                    SET("bank_account_phone=#{tradeWithdraw.bankAccountPhone}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getChannelMerId())) {
                    SET("channel_mer_id=#{tradeWithdraw.channelMerId}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getChannelType())) {
                    SET("channel_type=#{tradeWithdraw.channelType}");
                }
                if (tradeWithdraw.getInstallmentNum() != null) {
                    SET("installment_num=#{tradeWithdraw.installmentNum}");
                }
                if (tradeWithdraw.getOrderAmount() != null) {
                    SET("order_amount=#{tradeWithdraw.orderAmount}");
                }
                if (tradeWithdraw.getEachMoney() != null) {
                    SET("each_money=#{tradeWithdraw.eachMoney}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getCardHolderRate())) {
                    SET("card_holder_rate=#{tradeWithdraw.cardHolderRate}");
                }
                WHERE("id = #{tradeWithdraw.id}");
            }
        }.toString();
    }

    public String pageList(Map<String, Object> params) {
        TradeWithdrawDO tradeWithdraw = (TradeWithdrawDO) params.get("tradeWithdraw");
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
                if (StringUtils.isNotBlank(tradeWithdraw.getUserName())) {
                    WHERE("app_user_id in(SELECT id FROM u_app_user WHERE user_name = #{tradeWithdraw.userName})");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getMobile())) {
                    WHERE("app_user_id = SELECT id FROM u_app_user WHERE user_name = #{tradeWithdraw.mobile}");
                }
                if(tradeWithdraw.getSubTypeIn()!=null) {
                	if(tradeWithdraw.getSubTypeIn()==1) {
                		WHERE("trade_sub_type in(22,23,24,25)");
                	}else if(tradeWithdraw.getSubTypeIn()==2){
                		WHERE("trade_sub_type in(20)");
                	}else if(tradeWithdraw.getSubTypeIn()==3) {
                		WHERE("trade_sub_type in(10)");
                	}
                }
                if (tradeWithdraw.getId() != null) {
                    WHERE("id=#{tradeWithdraw.id}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getOrderNo())) {
                    WHERE("order_no=#{tradeWithdraw.orderNo}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getOriginalOrderNo())) {
                    WHERE("original_order_no=#{tradeWithdraw.originalOrderNo}");
                }
                if (tradeWithdraw.getAppUserId() != null) {
                    WHERE("app_user_id=#{tradeWithdraw.appUserId}");
                }
                if (tradeWithdraw.getAmount() != null) {
                    WHERE("amount=#{tradeWithdraw.amount}");
                }
                if (tradeWithdraw.getFee() != null) {
                    WHERE("fee=#{tradeWithdraw.fee}");
                }
                if (tradeWithdraw.getSettleMoney() != null) {
                    WHERE("settle_money=#{tradeWithdraw.settleMoney}");
                }
                if (tradeWithdraw.getFund() != null) {
                    WHERE("fund=#{tradeWithdraw.fund}");
                }
                if (tradeWithdraw.getTradeType() != null) {
                    WHERE("trade_type=#{tradeWithdraw.tradeType}");
                }
                if (tradeWithdraw.getTradeSubType() != null) {
                    WHERE("trade_sub_type=#{tradeWithdraw.tradeSubType}");
                }
                if (tradeWithdraw.getStatus() != null) {
                    WHERE("status=#{tradeWithdraw.status}");
                }
                if (tradeWithdraw.getCreateTime() != null) {
                    WHERE("create_time=#{tradeWithdraw.createTime}");
                }
                if (tradeWithdraw.getUpdateTime() != null) {
                    WHERE("update_time=#{tradeWithdraw.updateTime}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getRespCode())) {
                    WHERE("resp_code=#{tradeWithdraw.respCode}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getRespMsg())) {
                    WHERE("resp_msg=#{tradeWithdraw.respMsg}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getPaymentDate())) {
                    WHERE("payment_date=#{tradeWithdraw.paymentDate}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getSuccTime())) {
                    WHERE("succ_time=#{tradeWithdraw.succTime}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBackUrl())) {
                    WHERE("back_url=#{tradeWithdraw.backUrl}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankAccountType())) {
                    WHERE("bank_account_type=#{tradeWithdraw.bankAccountType}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankAccountNo())) {
                    WHERE("bank_account_no=#{tradeWithdraw.bankAccountNo}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankAccountName())) {
                    WHERE("bank_account_name=#{tradeWithdraw.bankAccountName}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankAccountCardId())) {
                    WHERE("bank_account_card_id=#{tradeWithdraw.bankAccountCardId}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankSubBankName())) {
                    WHERE("bank_sub_bank_name=#{tradeWithdraw.bankSubBankName}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankOpenBank())) {
                    WHERE("bank_open_bank=#{tradeWithdraw.bankOpenBank}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankOpenBankNum())) {
                    WHERE("bank_open_bank_num=#{tradeWithdraw.bankOpenBankNum}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankAccountPhone())) {
                    WHERE("bank_account_phone=#{tradeWithdraw.bankAccountPhone}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getChannelMerId())) {
                    WHERE("channel_mer_id=#{tradeWithdraw.channelMerId}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getChannelType())) {
                    WHERE("channel_type=#{tradeWithdraw.channelType}");
                }
                if (tradeWithdraw.isAppShowList()) {
                    WHERE("status in (2,3)");
                }
                if(tradeWithdraw.getStartCreateTime()!= null) {
                	WHERE("create_time >= #{tradeWithdraw.startCreateTime}");
                }
                if(tradeWithdraw.getEndCreateTime()!= null) {
                	WHERE("create_time <= #{tradeWithdraw.endCreateTime}");
                }
                ORDER_BY("id desc limit " + start + ", " + limit);
            }
        }.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        TradeWithdrawDO tradeWithdraw = (TradeWithdrawDO) params.get("tradeWithdraw");
        return new SQL() {
            {
                SELECT("count(1)");
                FROM(TABLE_NAME);
                if (StringUtils.isNotBlank(tradeWithdraw.getUserName())) {
                    WHERE("app_user_id in(SELECT id FROM u_app_user WHERE user_name = #{tradeWithdraw.userName})");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getMobile())) {
                    WHERE("app_user_id = SELECT id FROM u_app_user WHERE user_name = #{tradeWithdraw.mobile}");
                }
                if(tradeWithdraw.getSubTypeIn()!=null) {
                	if(tradeWithdraw.getSubTypeIn()==1) {
                		WHERE("trade_sub_type in(22,23,24,25)");
                	}else if(tradeWithdraw.getSubTypeIn()==2){
                		WHERE("trade_sub_type in(20)");
                	}else if(tradeWithdraw.getSubTypeIn()==3) {
                		WHERE("trade_sub_type in(10)");
                	}
                }
                if (tradeWithdraw.getId() != null) {
                    WHERE("id=#{tradeWithdraw.id}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getOrderNo())) {
                    WHERE("order_no=#{tradeWithdraw.orderNo}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getOriginalOrderNo())) {
                    WHERE("original_order_no=#{tradeWithdraw.originalOrderNo}");
                }
                if (tradeWithdraw.getAppUserId() != null) {
                    WHERE("app_user_id=#{tradeWithdraw.appUserId}");
                }
                if (tradeWithdraw.getAmount() != null) {
                    WHERE("amount=#{tradeWithdraw.amount}");
                }
                if (tradeWithdraw.getFee() != null) {
                    WHERE("fee=#{tradeWithdraw.fee}");
                }
                if (tradeWithdraw.getSettleMoney() != null) {
                    WHERE("settle_money=#{tradeWithdraw.settleMoney}");
                }
                if (tradeWithdraw.getFund() != null) {
                    WHERE("fund=#{tradeWithdraw.fund}");
                }
                if (tradeWithdraw.getTradeType() != null) {
                    WHERE("trade_type=#{tradeWithdraw.tradeType}");
                }
                if (tradeWithdraw.getTradeSubType() != null) {
                    WHERE("trade_sub_type=#{tradeWithdraw.tradeSubType}");
                }
                if (tradeWithdraw.getStatus() != null) {
                    WHERE("status=#{tradeWithdraw.status}");
                }
                if (tradeWithdraw.getCreateTime() != null) {
                    WHERE("create_time=#{tradeWithdraw.createTime}");
                }
                if (tradeWithdraw.getUpdateTime() != null) {
                    WHERE("update_time=#{tradeWithdraw.updateTime}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getRespCode())) {
                    WHERE("resp_code=#{tradeWithdraw.respCode}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getRespMsg())) {
                    WHERE("resp_msg=#{tradeWithdraw.respMsg}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getPaymentDate())) {
                    WHERE("payment_date=#{tradeWithdraw.paymentDate}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getSuccTime())) {
                    WHERE("succ_time=#{tradeWithdraw.succTime}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBackUrl())) {
                    WHERE("back_url=#{tradeWithdraw.backUrl}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankAccountType())) {
                    WHERE("bank_account_type=#{tradeWithdraw.bankAccountType}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankAccountNo())) {
                    WHERE("bank_account_no=#{tradeWithdraw.bankAccountNo}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankAccountName())) {
                    WHERE("bank_account_name=#{tradeWithdraw.bankAccountName}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankAccountCardId())) {
                    WHERE("bank_account_card_id=#{tradeWithdraw.bankAccountCardId}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankSubBankName())) {
                    WHERE("bank_sub_bank_name=#{tradeWithdraw.bankSubBankName}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankOpenBank())) {
                    WHERE("bank_open_bank=#{tradeWithdraw.bankOpenBank}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankOpenBankNum())) {
                    WHERE("bank_open_bank_num=#{tradeWithdraw.bankOpenBankNum}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getBankAccountPhone())) {
                    WHERE("bank_account_phone=#{tradeWithdraw.bankAccountPhone}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getChannelMerId())) {
                    WHERE("channel_mer_id=#{tradeWithdraw.channelMerId}");
                }
                if (StringUtils.isNotBlank(tradeWithdraw.getChannelType())) {
                    WHERE("channel_type=#{tradeWithdraw.channelType}");
                }
                if (tradeWithdraw.isAppShowList()) {
                    WHERE("status in (2,3)");
                }
                if(tradeWithdraw.getStartCreateTime()!= null) {
                	WHERE("create_time >= #{tradeWithdraw.startCreateTime}");
                }
                if(tradeWithdraw.getEndCreateTime()!= null) {
                	WHERE("create_time <= #{tradeWithdraw.endCreateTime}");
                }
            }
        }.toString();
    }

    /**
     * queryTotalAmount:(按照月份查询某用户总账单和)
     *
     * @param  @param params
     * @param  @return    设定文件
     * @return String    DOM对象
     * @author tangliang
     * @date   2017年12月7日 上午11:47:48
     */
    public String queryTotalAmount(Map<String, Object> params) {
        Integer appUserId = (Integer) params.get("appUserId");
        String tradeMonth = (String) params.get("tradeMonth");
        List<Integer> status = (List<Integer>) params.get("status");

        return new SQL() {
            {
                SELECT("trade_type AS tradeType,SUM(amount) AS totalAmount");
                FROM(TABLE_NAME);
                if (StringUtils.isNotBlank(tradeMonth)) {
                    WHERE("DATE_FORMAT(create_time,'%Y年%m月') = #{tradeMonth}");
                }
                if (null != appUserId) {
                    WHERE("app_user_id = #{appUserId}");
                }
                if (CollectionUtils.isNotEmpty(status)) {
                    StringBuffer sb = new StringBuffer();
                    for (Integer st : status) {
                        sb.append(st.toString()).append(",");
                    }
                    String statusStr = sb.toString().substring(0, sb.toString().length() - 1);
                    WHERE("status in (" + statusStr + ")");
                }
                GROUP_BY("trade_type");

            }
        }.toString();
    }
}
