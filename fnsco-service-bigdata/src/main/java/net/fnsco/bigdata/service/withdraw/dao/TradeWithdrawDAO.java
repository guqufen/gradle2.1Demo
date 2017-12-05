package net.fnsco.bigdata.service.withdraw.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.bigdata.service.withdraw.entity.TradeWithdrawDO;
import net.fnsco.bigdata.service.withdraw.dao.helper.TradeWithdrawProvider;

import java.util.List;;

public interface TradeWithdrawDAO {

    @Results({@Result( column = "order_no",property = "orderNo"),@Result( column = "original_order_no",property = "originalOrderNo"),@Result( column = "app_user_id",property = "appUserId"),@Result( column = "settle_money",property = "settleMoney"),@Result( column = "trade_type",property = "tradeType"),@Result( column = "create_time",property = "createTime"),@Result( column = "update_time",property = "updateTime"),@Result( column = "resp_code",property = "respCode"),@Result( column = "resp_msg",property = "respMsg"),@Result( column = "payment_date",property = "paymentDate"),@Result( column = "succ_time",property = "succTime"),@Result( column = "back_url",property = "backUrl"),@Result( column = "bank_account_type",property = "bankAccountType"),@Result( column = "bank_account_no",property = "bankAccountNo"),@Result( column = "bank_account_name",property = "bankAccountName"),@Result( column = "bank_account_card_id",property = "bankAccountCardId"),@Result( column = "bank_sub_bank_name",property = "bankSubBankName"),@Result( column = "bank_open_bank",property = "bankOpenBank"),@Result( column = "bank_open_bank_num",property = "bankOpenBankNum"),@Result( column = "bank_account_phone",property = "bankAccountPhone"),@Result( column = "channel_mer_id",property = "channelMerId") })
    @Select("SELECT * FROM t_trade_withdraw WHERE id = #{id}")
    public TradeWithdrawDO getById(@Param("id") int id);

    @Insert("INSERT into t_trade_withdraw(id,order_no,original_order_no,app_user_id,amount,fee,settle_money,fund,trade_type,status,create_time,update_time,resp_code,resp_msg,payment_date,succ_time,back_url,bank_account_type,bank_account_no,bank_account_name,bank_account_card_id,bank_sub_bank_name,bank_open_bank,bank_open_bank_num,bank_account_phone,channel_mer_id) VALUES (#{id},#{orderNo},#{originalOrderNo},#{appUserId},#{amount},#{fee},#{settleMoney},#{fund},#{tradeType},#{status},#{createTime},#{updateTime},#{respCode},#{respMsg},#{paymentDate},#{succTime},#{backUrl},#{bankAccountType},#{bankAccountNo},#{bankAccountName},#{bankAccountCardId},#{bankSubBankName},#{bankOpenBank},#{bankOpenBankNum},#{bankAccountPhone},#{channelMerId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(TradeWithdrawDO tradeWithdraw);

    @Delete("DELETE FROM t_trade_withdraw WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = TradeWithdrawProvider.class, method = "update")
    public int update(@Param("tradeWithdraw") TradeWithdrawDO  tradeWithdraw);

    @Results({@Result( column = "order_no",property = "orderNo"),@Result( column = "original_order_no",property = "originalOrderNo"),@Result( column = "app_user_id",property = "appUserId"),@Result( column = "settle_money",property = "settleMoney"),@Result( column = "trade_type",property = "tradeType"),@Result( column = "create_time",property = "createTime"),@Result( column = "update_time",property = "updateTime"),@Result( column = "resp_code",property = "respCode"),@Result( column = "resp_msg",property = "respMsg"),@Result( column = "payment_date",property = "paymentDate"),@Result( column = "succ_time",property = "succTime"),@Result( column = "back_url",property = "backUrl"),@Result( column = "bank_account_type",property = "bankAccountType"),@Result( column = "bank_account_no",property = "bankAccountNo"),@Result( column = "bank_account_name",property = "bankAccountName"),@Result( column = "bank_account_card_id",property = "bankAccountCardId"),@Result( column = "bank_sub_bank_name",property = "bankSubBankName"),@Result( column = "bank_open_bank",property = "bankOpenBank"),@Result( column = "bank_open_bank_num",property = "bankOpenBankNum"),@Result( column = "bank_account_phone",property = "bankAccountPhone"),@Result( column = "channel_mer_id",property = "channelMerId") })
    @SelectProvider(type = TradeWithdrawProvider.class, method = "pageList")
    public List<TradeWithdrawDO> pageList(@Param("tradeWithdraw") TradeWithdrawDO tradeWithdraw, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = TradeWithdrawProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("tradeWithdraw") TradeWithdrawDO tradeWithdraw);

}