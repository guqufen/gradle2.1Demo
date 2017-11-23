package net.fnsco.order.service.trade.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.order.service.trade.entity.TradeOrderLklDO;
import net.fnsco.order.service.trade.dao.helper.TradeOrderLklProvider;

import java.util.List;;

public interface TradeOrderLklDAO {

    @Results({@Result( column = "order_no",property = "orderNo"),@Result( column = "pay_order_no",property = "payOrderNo"),@Result( column = "txn_amount",property = "txnAmount"),@Result( column = "installment_num",property = "installmentNum"),@Result( column = "resp_code",property = "respCode"),@Result( column = "resp_msg",property = "respMsg"),@Result( column = "entity_inner_code",property = "entityInnerCode"),@Result( column = "channel_mer_id",property = "channelMerId"),@Result( column = "channel_type",property = "channelType"),@Result( column = "complete_time",property = "completeTime"),@Result( column = "order_ceate_time",property = "orderCeateTime"),@Result( column = "txn_type",property = "txnType"),@Result( column = "txn_sub_type",property = "txnSubType"),@Result( column = "pay_type",property = "payType"),@Result( column = "pay_sub_type",property = "paySubType"),@Result( column = "settle_amount",property = "settleAmount"),@Result( column = "settle_date",property = "settleDate"),@Result( column = "settle_status",property = "settleStatus"),@Result( column = "create_user_id",property = "createUserId"),@Result( column = "create_time",property = "createTime"),@Result( column = "sync_status",property = "syncStatus"),@Result( column = "inner_code",property = "innerCode"),@Result( column = "handle_num",property = "handleNum"),@Result( column = "order_amount",property = "orderAmount"),@Result( column = "each_money",property = "eachMoney"),@Result( column = "card_holder_rate",property = "cardHolderRate") })
    @Select("SELECT * FROM t_trade_order_lkl WHERE id = #{id}")
    public TradeOrderLklDO getById(@Param("id") int id);

    @Insert("INSERT into t_trade_order_lkl(id,order_no,pay_order_no,txn_amount,installment_num,resp_code,resp_msg,entity_inner_code,channel_mer_id,channel_type,complete_time,order_ceate_time,txn_type,txn_sub_type,pay_type,pay_sub_type,settle_amount,settle_date,settle_status,create_user_id,create_time,sync_status,inner_code,handle_num,order_amount,each_money,card_holder_rate) VALUES (#{id},#{orderNo},#{payOrderNo},#{txnAmount},#{installmentNum},#{respCode},#{respMsg},#{entityInnerCode},#{channelMerId},#{channelType},#{completeTime},#{orderCeateTime},#{txnType},#{txnSubType},#{payType},#{paySubType},#{settleAmount},#{settleDate},#{settleStatus},#{createUserId},#{createTime},#{syncStatus},#{innerCode},#{handleNum},#{orderAmount},#{eachMoney},#{cardHolderRate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(TradeOrderLklDO tradeOrderLkl);

    @Delete("DELETE FROM t_trade_order_lkl WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = TradeOrderLklProvider.class, method = "update")
    public int update(@Param("tradeOrderLkl") TradeOrderLklDO  tradeOrderLkl);

    @Results({@Result( column = "order_no",property = "orderNo"),@Result( column = "pay_order_no",property = "payOrderNo"),@Result( column = "txn_amount",property = "txnAmount"),@Result( column = "installment_num",property = "installmentNum"),@Result( column = "resp_code",property = "respCode"),@Result( column = "resp_msg",property = "respMsg"),@Result( column = "entity_inner_code",property = "entityInnerCode"),@Result( column = "channel_mer_id",property = "channelMerId"),@Result( column = "channel_type",property = "channelType"),@Result( column = "complete_time",property = "completeTime"),@Result( column = "order_ceate_time",property = "orderCeateTime"),@Result( column = "txn_type",property = "txnType"),@Result( column = "txn_sub_type",property = "txnSubType"),@Result( column = "pay_type",property = "payType"),@Result( column = "pay_sub_type",property = "paySubType"),@Result( column = "settle_amount",property = "settleAmount"),@Result( column = "settle_date",property = "settleDate"),@Result( column = "settle_status",property = "settleStatus"),@Result( column = "create_user_id",property = "createUserId"),@Result( column = "create_time",property = "createTime"),@Result( column = "sync_status",property = "syncStatus"),@Result( column = "inner_code",property = "innerCode"),@Result( column = "handle_num",property = "handleNum"),@Result( column = "order_amount",property = "orderAmount"),@Result( column = "each_money",property = "eachMoney"),@Result( column = "card_holder_rate",property = "cardHolderRate") })
    @SelectProvider(type = TradeOrderLklProvider.class, method = "pageList")
    public List<TradeOrderLklDO> pageList(@Param("tradeOrderLkl") TradeOrderLklDO tradeOrderLkl, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = TradeOrderLklProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("tradeOrderLkl") TradeOrderLklDO tradeOrderLkl);

}