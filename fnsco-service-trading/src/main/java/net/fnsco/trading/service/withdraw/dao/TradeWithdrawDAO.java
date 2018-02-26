package net.fnsco.trading.service.withdraw.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.fnsco.trading.service.withdraw.dao.helper.TradeWithdrawProvider;
import net.fnsco.trading.service.withdraw.dto.MonthWithdrawCountDTO;
import net.fnsco.trading.service.withdraw.entity.TradeWithdrawDO;;

public interface TradeWithdrawDAO {

    @Results({ @Result(column = "order_no", property = "orderNo"), @Result(column = "original_order_no", property = "originalOrderNo"), @Result(column = "app_user_id", property = "appUserId"),
               @Result(column = "settle_money", property = "settleMoney"), @Result(column = "trade_type", property = "tradeType"), @Result(column = "trade_sub_type", property = "tradeSubType"),
               @Result(column = "create_time", property = "createTime"), @Result(column = "update_time", property = "updateTime"), @Result(column = "resp_code", property = "respCode"),
               @Result(column = "resp_msg", property = "respMsg"), @Result(column = "payment_date", property = "paymentDate"), @Result(column = "succ_time", property = "succTime"),
               @Result(column = "back_url", property = "backUrl"), @Result(column = "bank_account_type", property = "bankAccountType"), @Result(column = "bank_account_no", property = "bankAccountNo"),
               @Result(column = "bank_account_name", property = "bankAccountName"), @Result(column = "bank_account_card_id", property = "bankAccountCardId"),
               @Result(column = "bank_sub_bank_name", property = "bankSubBankName"), @Result(column = "bank_open_bank", property = "bankOpenBank"),
               @Result(column = "bank_open_bank_num", property = "bankOpenBankNum"), @Result(column = "bank_account_phone", property = "bankAccountPhone"),
               @Result(column = "channel_mer_id", property = "channelMerId"), @Result(column = "channel_type", property = "channelType"),
               @Result(column = "installment_num", property = "installmentNum"), @Result(column = "order_amount", property = "orderAmount"), @Result(column = "each_money", property = "eachMoney"),
               @Result(column = "card_holder_rate", property = "cardHolderRate") })
    @Select("SELECT * FROM t_trade_withdraw WHERE id = #{id}")
    public TradeWithdrawDO getById(@Param("id") int id);

    @Results({ @Result(column = "order_no", property = "orderNo"), @Result(column = "original_order_no", property = "originalOrderNo"), @Result(column = "app_user_id", property = "appUserId"),
               @Result(column = "settle_money", property = "settleMoney"), @Result(column = "trade_type", property = "tradeType"), @Result(column = "trade_sub_type", property = "tradeSubType"),
               @Result(column = "create_time", property = "createTime"), @Result(column = "update_time", property = "updateTime"), @Result(column = "resp_code", property = "respCode"),
               @Result(column = "resp_msg", property = "respMsg"), @Result(column = "payment_date", property = "paymentDate"), @Result(column = "succ_time", property = "succTime"),
               @Result(column = "back_url", property = "backUrl"), @Result(column = "bank_account_type", property = "bankAccountType"), @Result(column = "bank_account_no", property = "bankAccountNo"),
               @Result(column = "bank_account_name", property = "bankAccountName"), @Result(column = "bank_account_card_id", property = "bankAccountCardId"),
               @Result(column = "bank_sub_bank_name", property = "bankSubBankName"), @Result(column = "bank_open_bank", property = "bankOpenBank"),
               @Result(column = "bank_open_bank_num", property = "bankOpenBankNum"), @Result(column = "bank_account_phone", property = "bankAccountPhone"),
               @Result(column = "channel_mer_id", property = "channelMerId"), @Result(column = "channel_type", property = "channelType"),
               @Result(column = "installment_num", property = "installmentNum"), @Result(column = "order_amount", property = "orderAmount"), @Result(column = "each_money", property = "eachMoney"),
               @Result(column = "card_holder_rate", property = "cardHolderRate") })
    @Select("SELECT * FROM t_trade_withdraw WHERE order_no = #{orderNo}")
    public TradeWithdrawDO queryByOrderId(@Param("orderNo") String orderNo);
    
    @Results({ @Result(column = "order_no", property = "orderNo"), @Result(column = "original_order_no", property = "originalOrderNo"), @Result(column = "app_user_id", property = "appUserId"),
        @Result(column = "settle_money", property = "settleMoney"), @Result(column = "trade_type", property = "tradeType"), @Result(column = "trade_sub_type", property = "tradeSubType"),
        @Result(column = "create_time", property = "createTime"), @Result(column = "update_time", property = "updateTime"), @Result(column = "resp_code", property = "respCode"),
        @Result(column = "resp_msg", property = "respMsg"), @Result(column = "payment_date", property = "paymentDate"), @Result(column = "succ_time", property = "succTime"),
        @Result(column = "back_url", property = "backUrl"), @Result(column = "bank_account_type", property = "bankAccountType"), @Result(column = "bank_account_no", property = "bankAccountNo"),
        @Result(column = "bank_account_name", property = "bankAccountName"), @Result(column = "bank_account_card_id", property = "bankAccountCardId"),
        @Result(column = "bank_sub_bank_name", property = "bankSubBankName"), @Result(column = "bank_open_bank", property = "bankOpenBank"),
        @Result(column = "bank_open_bank_num", property = "bankOpenBankNum"), @Result(column = "bank_account_phone", property = "bankAccountPhone"),
        @Result(column = "channel_mer_id", property = "channelMerId"), @Result(column = "channel_type", property = "channelType"),
        @Result(column = "installment_num", property = "installmentNum"), @Result(column = "order_amount", property = "orderAmount"), @Result(column = "each_money", property = "eachMoney"),
        @Result(column = "card_holder_rate", property = "cardHolderRate")})
    @Select("SELECT * FROM t_trade_withdraw WHERE original_order_no = #{originalOrderNo} ORDER BY id DESC LIMIT 1")
    public TradeWithdrawDO queryByOriginalOrderNo(@Param("originalOrderNo") String originalOrderNo);
    
    @Results({ @Result(column = "order_no", property = "orderNo"), @Result(column = "original_order_no", property = "originalOrderNo"), @Result(column = "app_user_id", property = "appUserId"),
               @Result(column = "settle_money", property = "settleMoney"), @Result(column = "trade_type", property = "tradeType"), @Result(column = "trade_sub_type", property = "tradeSubType"),
               @Result(column = "create_time", property = "createTime"), @Result(column = "update_time", property = "updateTime"), @Result(column = "resp_code", property = "respCode"),
               @Result(column = "resp_msg", property = "respMsg"), @Result(column = "payment_date", property = "paymentDate"), @Result(column = "succ_time", property = "succTime"),
               @Result(column = "back_url", property = "backUrl"), @Result(column = "bank_account_type", property = "bankAccountType"), @Result(column = "bank_account_no", property = "bankAccountNo"),
               @Result(column = "bank_account_name", property = "bankAccountName"), @Result(column = "bank_account_card_id", property = "bankAccountCardId"),
               @Result(column = "bank_sub_bank_name", property = "bankSubBankName"), @Result(column = "bank_open_bank", property = "bankOpenBank"),
               @Result(column = "bank_open_bank_num", property = "bankOpenBankNum"), @Result(column = "bank_account_phone", property = "bankAccountPhone"),
               @Result(column = "channel_mer_id", property = "channelMerId"), @Result(column = "channel_type", property = "channelType"),
               @Result(column = "installment_num", property = "installmentNum"), @Result(column = "order_amount", property = "orderAmount"), @Result(column = "each_money", property = "eachMoney"),
               @Result(column = "card_holder_rate", property = "cardHolderRate") })
    @Select("SELECT * FROM t_trade_withdraw WHERE original_order_no = #{originalOrderNo}")
    public TradeWithdrawDO getByOriginalOrderNo(@Param("originalOrderNo") String originalOrderNo);
    
    @Insert("INSERT into t_trade_withdraw(id,order_no,original_order_no,app_user_id,amount,fee,settle_money,fund,trade_type,trade_sub_type,status,create_time,update_time,resp_code,resp_msg,payment_date,succ_time,back_url,bank_account_type,bank_account_no,bank_account_name,bank_account_card_id,bank_sub_bank_name,bank_open_bank,bank_open_bank_num,bank_account_phone,channel_mer_id,channel_type,installment_num,order_amount,each_money,card_holder_rate) VALUES (#{id},#{orderNo},#{originalOrderNo},#{appUserId},#{amount},#{fee},#{settleMoney},#{fund},#{tradeType},#{tradeSubType},#{status},#{createTime},#{updateTime},#{respCode},#{respMsg},#{paymentDate},#{succTime},#{backUrl},#{bankAccountType},#{bankAccountNo},#{bankAccountName},#{bankAccountCardId},#{bankSubBankName},#{bankOpenBank},#{bankOpenBankNum},#{bankAccountPhone},#{channelMerId},#{channelType},#{installmentNum},#{orderAmount},#{eachMoney},#{cardHolderRate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(TradeWithdrawDO tradeWithdraw);

    @Delete("DELETE FROM t_trade_withdraw WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = TradeWithdrawProvider.class, method = "update")
    public int update(@Param("tradeWithdraw") TradeWithdrawDO tradeWithdraw);

    @UpdateProvider(type = TradeWithdrawProvider.class, method = "updateOnlyFail")
    public int updateOnlyFail(@Param("tradeWithdraw") TradeWithdrawDO tradeWithdraw);

    @Results({ @Result(column = "order_no", property = "orderNo"), @Result(column = "original_order_no", property = "originalOrderNo"), @Result(column = "app_user_id", property = "appUserId"),
               @Result(column = "settle_money", property = "settleMoney"), @Result(column = "trade_type", property = "tradeType"), @Result(column = "trade_sub_type", property = "tradeSubType"),
               @Result(column = "create_time", property = "createTime"), @Result(column = "update_time", property = "updateTime"), @Result(column = "resp_code", property = "respCode"),
               @Result(column = "resp_msg", property = "respMsg"), @Result(column = "payment_date", property = "paymentDate"), @Result(column = "succ_time", property = "succTime"),
               @Result(column = "back_url", property = "backUrl"), @Result(column = "bank_account_type", property = "bankAccountType"), @Result(column = "bank_account_no", property = "bankAccountNo"),
               @Result(column = "bank_account_name", property = "bankAccountName"), @Result(column = "bank_account_card_id", property = "bankAccountCardId"),
               @Result(column = "bank_sub_bank_name", property = "bankSubBankName"), @Result(column = "bank_open_bank", property = "bankOpenBank"),
               @Result(column = "bank_open_bank_num", property = "bankOpenBankNum"), @Result(column = "bank_account_phone", property = "bankAccountPhone"),
               @Result(column = "channel_mer_id", property = "channelMerId"), @Result(column = "channel_type", property = "channelType"),
               @Result(column = "installment_num", property = "installmentNum"), @Result(column = "order_amount", property = "orderAmount"), @Result(column = "each_money", property = "eachMoney"),
               @Result(column = "card_holder_rate", property = "cardHolderRate") })
    @SelectProvider(type = TradeWithdrawProvider.class, method = "pageList")
    public List<TradeWithdrawDO> pageList(@Param("tradeWithdraw") TradeWithdrawDO tradeWithdraw, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = TradeWithdrawProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("tradeWithdraw") TradeWithdrawDO tradeWithdraw);

    /**
     * queryTotalAmount:(按照月份查询某用户总账单和)
     *
     * @param  @param appUserId
     * @param  @param tradeMonth
     * @param  @return    设定文件
     * @return String    DOM对象
     * @author tangliang
     * @date   2017年12月7日 上午11:46:23
     */
    @SelectProvider(type = TradeWithdrawProvider.class, method = "queryTotalAmount")
    public List<MonthWithdrawCountDTO> queryTotalAmount(@Param("appUserId") Integer appUserId, @Param("tradeMonth") String tradeMonth, @Param("status") List<Integer> status);

    /**
     * 按交易类型查询正在进行的交易列表，便于定时查询交易状态(按照时间大到小)
     * @param start：查询开始时间
     * @param type：交易类型
     * @return
     */
    @Results({ @Result(column = "order_no", property = "orderNo"), @Result(column = "original_order_no", property = "originalOrderNo"), @Result(column = "app_user_id", property = "appUserId"),
               @Result(column = "settle_money", property = "settleMoney"), @Result(column = "trade_type", property = "tradeType"), @Result(column = "trade_sub_type", property = "tradeSubType"),
               @Result(column = "create_time", property = "createTime"), @Result(column = "update_time", property = "updateTime"), @Result(column = "resp_code", property = "respCode"),
               @Result(column = "resp_msg", property = "respMsg"), @Result(column = "payment_date", property = "paymentDate"), @Result(column = "succ_time", property = "succTime"),
               @Result(column = "back_url", property = "backUrl"), @Result(column = "bank_account_type", property = "bankAccountType"), @Result(column = "bank_account_no", property = "bankAccountNo"),
               @Result(column = "bank_account_name", property = "bankAccountName"), @Result(column = "bank_account_card_id", property = "bankAccountCardId"),
               @Result(column = "bank_sub_bank_name", property = "bankSubBankName"), @Result(column = "bank_open_bank", property = "bankOpenBank"),
               @Result(column = "bank_open_bank_num", property = "bankOpenBankNum"), @Result(column = "bank_account_phone", property = "bankAccountPhone"),
               @Result(column = "channel_mer_id", property = "channelMerId"), @Result(column = "channel_type", property = "channelType"),
               @Result(column = "installment_num", property = "installmentNum"), @Result(column = "order_amount", property = "orderAmount"), @Result(column = "each_money", property = "eachMoney"),
               @Result(column = "card_holder_rate", property = "cardHolderRate") })
    @Select("SELECT * FROM t_trade_withdraw WHERE status=1 AND trade_type=#{type} AND create_time >=#{start}")
    public List<TradeWithdrawDO> queryOngoing(@Param("start") Date start, @Param("type") Integer type);

    @Results({ @Result(column = "order_no", property = "orderNo"), @Result(column = "original_order_no", property = "originalOrderNo"), @Result(column = "app_user_id", property = "appUserId"),
               @Result(column = "settle_money", property = "settleMoney"), @Result(column = "trade_type", property = "tradeType"), @Result(column = "trade_sub_type", property = "tradeSubType"),
               @Result(column = "create_time", property = "createTime"), @Result(column = "update_time", property = "updateTime"), @Result(column = "resp_code", property = "respCode"),
               @Result(column = "resp_msg", property = "respMsg"), @Result(column = "payment_date", property = "paymentDate"), @Result(column = "succ_time", property = "succTime"),
               @Result(column = "back_url", property = "backUrl"), @Result(column = "bank_account_type", property = "bankAccountType"), @Result(column = "bank_account_no", property = "bankAccountNo"),
               @Result(column = "bank_account_name", property = "bankAccountName"), @Result(column = "bank_account_card_id", property = "bankAccountCardId"),
               @Result(column = "bank_sub_bank_name", property = "bankSubBankName"), @Result(column = "bank_open_bank", property = "bankOpenBank"),
               @Result(column = "bank_open_bank_num", property = "bankOpenBankNum"), @Result(column = "bank_account_phone", property = "bankAccountPhone"),
               @Result(column = "channel_mer_id", property = "channelMerId"), @Result(column = "channel_type", property = "channelType"),
               @Result(column = "installment_num", property = "installmentNum"), @Result(column = "order_amount", property = "orderAmount"), @Result(column = "each_money", property = "eachMoney"),
               @Result(column = "card_holder_rate", property = "cardHolderRate") })
    @Select("SELECT * FROM t_trade_withdraw WHERE order_no = #{orderNo} order by id desc limit 1 ")
    public TradeWithdrawDO getByOrderNo(@Param("orderNo") String orderNo);

    @Results({ @Result(column = "order_no", property = "orderNo"), @Result(column = "original_order_no", property = "originalOrderNo"), @Result(column = "app_user_id", property = "appUserId"),
               @Result(column = "settle_money", property = "settleMoney"), @Result(column = "trade_type", property = "tradeType"), @Result(column = "trade_sub_type", property = "tradeSubType"),
               @Result(column = "create_time", property = "createTime"), @Result(column = "update_time", property = "updateTime"), @Result(column = "resp_code", property = "respCode"),
               @Result(column = "resp_msg", property = "respMsg"), @Result(column = "payment_date", property = "paymentDate"), @Result(column = "succ_time", property = "succTime"),
               @Result(column = "back_url", property = "backUrl"), @Result(column = "bank_account_type", property = "bankAccountType"), @Result(column = "bank_account_no", property = "bankAccountNo"),
               @Result(column = "bank_account_name", property = "bankAccountName"), @Result(column = "bank_account_card_id", property = "bankAccountCardId"),
               @Result(column = "bank_sub_bank_name", property = "bankSubBankName"), @Result(column = "bank_open_bank", property = "bankOpenBank"),
               @Result(column = "bank_open_bank_num", property = "bankOpenBankNum"), @Result(column = "bank_account_phone", property = "bankAccountPhone"),
               @Result(column = "channel_mer_id", property = "channelMerId"), @Result(column = "channel_type", property = "channelType"),
               @Result(column = "installment_num", property = "installmentNum"), @Result(column = "order_amount", property = "orderAmount"), @Result(column = "each_money", property = "eachMoney"),
               @Result(column = "card_holder_rate", property = "cardHolderRate") })
    @Select("SELECT * FROM t_trade_withdraw WHERE original_order_no = #{orderNo} and trade_sub_type = '27' order by id desc limit 1 ")
    public TradeWithdrawDO getUndoByOrderNo(@Param("orderNo") String orderNo);
}