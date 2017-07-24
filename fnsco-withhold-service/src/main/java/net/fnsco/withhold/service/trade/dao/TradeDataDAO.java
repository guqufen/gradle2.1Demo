package net.fnsco.withhold.service.trade.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.withhold.service.trade.entity.TradeDataDO;
import net.fnsco.withhold.service.trade.dao.helper.TradeDataProvider;

import java.util.List;;

public interface TradeDataDAO {

    @Select("SELECT * FROM t_trade_data WHERE id = #{id}")
    public TradeDataDO getById(@Param("id") int id);

    @Results({@Result( column = "withhold_id",property = "withholdId"),@Result( column = "txn_amt",property = "txnAmt"),@Result( column = "fail_reason",property = "failReason"),@Result( column = "user_name",property = "userName"),@Result( column = "card_num",property = "cardNum"),@Result( column = "bank_card",property = "bankCard"),@Result( column = "sub_bank_name",property = "subBankName"),@Result( column = "an_bank_id",property = "anBankId"),@Result( column = "account_type",property = "accountType"),@Result( column = "txn_type",property = "txnType"),@Result( column = "txn_sub_type",property = "txnSubType"),@Result( column = "biz_type",property = "bizType"),@Result( column = "access_type",property = "accessType"),@Result( column = "mer_id",property = "merId"),@Result( column = "order_sn",property = "orderSn"),@Result( column = "txn_time",property = "txnTime"),@Result( column = "back_url",property = "backUrl"),@Result( column = "pay_type",property = "payType"),@Result( column = "customer_info",property = "customerInfo"),@Result( column = "resp_code",property = "respCode"),@Result( column = "resp_msg",property = "respMsg") })
    @Insert("INSERT into t_trade_data(id,withhold_id,txn_amt,status,fail_reason,user_name,mobile,card_num,bank_card,sub_bank_name,an_bank_id,account_type,txn_type,txn_sub_type,biz_type,access_type,mer_id,order_sn,txn_time,currency,back_url,pay_type,subject,body,customer_info,purpose,resp_code,resp_msg) VALUES (#{id},#{withholdId},#{txnAmt},#{status},#{failReason},#{userName},#{mobile},#{cardNum},#{bankCard},#{subBankName},#{anBankId},#{accountType},#{txnType},#{txnSubType},#{bizType},#{accessType},#{merId},#{orderSn},#{txnTime},#{currency},#{backUrl},#{payType},#{subject},#{body},#{customerInfo},#{purpose},#{respCode},#{respMsg})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(TradeDataDO tradeData);

    @Delete("DELETE FROM t_trade_data WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = TradeDataProvider.class, method = "update")
    public int update(@Param("tradeData") TradeDataDO  tradeData);

    @Results({@Result( column = "withhold_id",property = "withholdId"),@Result( column = "txn_amt",property = "txnAmt"),@Result( column = "fail_reason",property = "failReason"),@Result( column = "user_name",property = "userName"),@Result( column = "card_num",property = "cardNum"),@Result( column = "bank_card",property = "bankCard"),@Result( column = "sub_bank_name",property = "subBankName"),@Result( column = "an_bank_id",property = "anBankId"),@Result( column = "account_type",property = "accountType"),@Result( column = "txn_type",property = "txnType"),@Result( column = "txn_sub_type",property = "txnSubType"),@Result( column = "biz_type",property = "bizType"),@Result( column = "access_type",property = "accessType"),@Result( column = "mer_id",property = "merId"),@Result( column = "order_sn",property = "orderSn"),@Result( column = "txn_time",property = "txnTime"),@Result( column = "back_url",property = "backUrl"),@Result( column = "pay_type",property = "payType"),@Result( column = "customer_info",property = "customerInfo"),@Result( column = "resp_code",property = "respCode"),@Result( column = "resp_msg",property = "respMsg") })
    @SelectProvider(type = TradeDataProvider.class, method = "pageList")
    public List<TradeDataDO> pageList(@Param("tradeData") TradeDataDO tradeData, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = TradeDataProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("tradeData") TradeDataDO tradeData);

}