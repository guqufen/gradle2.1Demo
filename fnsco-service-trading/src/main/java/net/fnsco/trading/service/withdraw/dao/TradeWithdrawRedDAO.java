package net.fnsco.trading.service.withdraw.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.fnsco.trading.service.withdraw.dao.helper.TradeWithdrawRedProvider;
import net.fnsco.trading.service.withdraw.entity.TradeWithdrawRedDO;

import java.util.List;;

public interface TradeWithdrawRedDAO {

    @Results({@Result( column = "order_no",property = "orderNo"),@Result( column = "original_order_no",property = "originalOrderNo"),@Result( column = "app_user_id",property = "appUserId"),@Result( column = "trade_type",property = "tradeType"),@Result( column = "trade_sub_type",property = "tradeSubType"),@Result( column = "create_time",property = "createTime"),@Result( column = "update_time",property = "updateTime"),@Result( column = "resp_code",property = "respCode"),@Result( column = "resp_msg",property = "respMsg"),@Result( column = "succ_time",property = "succTime"),@Result( column = "settle_money",property = "settleMoney"),@Result( column = "payment_date",property = "paymentDate"),@Result( column = "backUrl",property = "backurl"),@Result( column = "bank_account_type",property = "bankAccountType"),@Result( column = "bank_account_no",property = "bankAccountNo"),@Result( column = "bank_account_name",property = "bankAccountName"),@Result( column = "bank_account_card_id",property = "bankAccountCardId"),@Result( column = "bank_sub_bank_name",property = "bankSubBankName"),@Result( column = "bank_open_bank",property = "bankOpenBank"),@Result( column = "bank_open_bank_num",property = "bankOpenBankNum"),@Result( column = "bank_account_phone",property = "bankAccountPhone"),@Result( column = "channel_mer_id",property = "channelMerId") })
    @Select("SELECT * FROM t_trade_withdraw_red WHERE id = #{id}")
    public TradeWithdrawRedDO getById(@Param("id") int id);

    @Insert("INSERT into t_trade_withdraw_red(id,order_no,original_order_no,app_user_id,amount,fee,fund,trade_type,trade_sub_type,status,create_time,update_time,resp_code,resp_msg,succ_time,settle_money,payment_date,backUrl,bank_account_type,bank_account_no,bank_account_name,bank_account_card_id,bank_sub_bank_name,bank_open_bank,bank_open_bank_num,bank_account_phone,channel_mer_id) VALUES (#{id},#{orderNo},#{originalOrderNo},#{appUserId},#{amount},#{fee},#{fund},#{tradeType},#{tradeSubType},#{status},#{createTime},#{updateTime},#{respCode},#{respMsg},#{succTime},#{settleMoney},#{paymentDate},#{backurl},#{bankAccountType},#{bankAccountNo},#{bankAccountName},#{bankAccountCardId},#{bankSubBankName},#{bankOpenBank},#{bankOpenBankNum},#{bankAccountPhone},#{channelMerId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(TradeWithdrawRedDO tradeWithdrawRed);

    @Delete("DELETE FROM t_trade_withdraw_red WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = TradeWithdrawRedProvider.class, method = "update")
    public int update(@Param("tradeWithdrawRed") TradeWithdrawRedDO  tradeWithdrawRed);

    @Results({@Result( column = "order_no",property = "orderNo"),@Result( column = "original_order_no",property = "originalOrderNo"),@Result( column = "app_user_id",property = "appUserId"),@Result( column = "trade_type",property = "tradeType"),@Result( column = "trade_sub_type",property = "tradeSubType"),@Result( column = "create_time",property = "createTime"),@Result( column = "update_time",property = "updateTime"),@Result( column = "resp_code",property = "respCode"),@Result( column = "resp_msg",property = "respMsg"),@Result( column = "succ_time",property = "succTime"),@Result( column = "settle_money",property = "settleMoney"),@Result( column = "payment_date",property = "paymentDate"),@Result( column = "backUrl",property = "backurl"),@Result( column = "bank_account_type",property = "bankAccountType"),@Result( column = "bank_account_no",property = "bankAccountNo"),@Result( column = "bank_account_name",property = "bankAccountName"),@Result( column = "bank_account_card_id",property = "bankAccountCardId"),@Result( column = "bank_sub_bank_name",property = "bankSubBankName"),@Result( column = "bank_open_bank",property = "bankOpenBank"),@Result( column = "bank_open_bank_num",property = "bankOpenBankNum"),@Result( column = "bank_account_phone",property = "bankAccountPhone"),@Result( column = "channel_mer_id",property = "channelMerId") })
    @SelectProvider(type = TradeWithdrawRedProvider.class, method = "pageList")
    public List<TradeWithdrawRedDO> pageList(@Param("tradeWithdrawRed") TradeWithdrawRedDO tradeWithdrawRed, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = TradeWithdrawRedProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("tradeWithdrawRed") TradeWithdrawRedDO tradeWithdrawRed);

}