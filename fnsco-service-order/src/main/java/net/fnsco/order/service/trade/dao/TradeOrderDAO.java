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
import net.fnsco.order.service.trade.entity.TradeOrderDO;
import net.fnsco.order.service.trade.dao.helper.TradeOrderProvider;

import java.util.List;;

public interface TradeOrderDAO {

    @Results({@Result( column = "order_id",property = "orderId"),@Result( column = "pay_order_id",property = "payOrderId"),@Result( column = "txn_amount",property = "txnAmount"),@Result( column = "installment_num",property = "installmentNum"),@Result( column = "resp_code",property = "respCode"),@Result( column = "resp_msg",property = "respMsg"),@Result( column = "merc_id",property = "mercId"),@Result( column = "channel_mer_id",property = "channelMerId"),@Result( column = "channel_type",property = "channelType"),@Result( column = "complete_time",property = "completeTime"),@Result( column = "order_ceate_time",property = "orderCeateTime"),@Result( column = "txn_type",property = "txnType"),@Result( column = "txn_sub_type",property = "txnSubType"),@Result( column = "pay_type",property = "payType"),@Result( column = "pay_sub_type",property = "paySubType"),@Result( column = "settle_amount",property = "settleAmount"),@Result( column = "settle_date",property = "settleDate"),@Result( column = "create_user_id",property = "createUserId"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM t_trade_order WHERE id = #{id}")
    public TradeOrderDO getById(@Param("id") int id);

    @Insert("INSERT into t_trade_order(id,order_id,pay_order_id,txn_amount,installment_num,resp_code,resp_msg,merc_id,channel_mer_id,channel_type,complete_time,order_ceate_time,txn_type,txn_sub_type,pay_type,pay_sub_type,settle_amount,settle_date,create_user_id,create_time) VALUES (#{id},#{orderId},#{payOrderId},#{txnAmount},#{installmentNum},#{respCode},#{respMsg},#{mercId},#{channelMerId},#{channelType},#{completeTime},#{orderCeateTime},#{txnType},#{txnSubType},#{payType},#{paySubType},#{settleAmount},#{settleDate},#{createUserId},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(TradeOrderDO tradeOrder);

    @Delete("DELETE FROM t_trade_order WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = TradeOrderProvider.class, method = "update")
    public int update(@Param("tradeOrder") TradeOrderDO  tradeOrder);

    @Results({@Result( column = "order_id",property = "orderId"),@Result( column = "pay_order_id",property = "payOrderId"),@Result( column = "txn_amount",property = "txnAmount"),@Result( column = "installment_num",property = "installmentNum"),@Result( column = "resp_code",property = "respCode"),@Result( column = "resp_msg",property = "respMsg"),@Result( column = "merc_id",property = "mercId"),@Result( column = "channel_mer_id",property = "channelMerId"),@Result( column = "channel_type",property = "channelType"),@Result( column = "complete_time",property = "completeTime"),@Result( column = "order_ceate_time",property = "orderCeateTime"),@Result( column = "txn_type",property = "txnType"),@Result( column = "txn_sub_type",property = "txnSubType"),@Result( column = "pay_type",property = "payType"),@Result( column = "pay_sub_type",property = "paySubType"),@Result( column = "settle_amount",property = "settleAmount"),@Result( column = "settle_date",property = "settleDate"),@Result( column = "create_user_id",property = "createUserId"),@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = TradeOrderProvider.class, method = "pageList")
    public List<TradeOrderDO> pageList(@Param("tradeOrder") TradeOrderDO tradeOrder, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = TradeOrderProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("tradeOrder") TradeOrderDO tradeOrder);

}