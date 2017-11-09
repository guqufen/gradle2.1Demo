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

    @Results({ @Result(column = "order_no", property = "orderNo"), @Result(column = "pay_order_no", property = "payOrderNo"), @Result(column = "txn_amount", property = "txnAmount"),
               @Result(column = "installment_num", property = "installmentNum"), @Result(column = "resp_code", property = "respCode"), @Result(column = "resp_msg", property = "respMsg"),
               @Result(column = "entity_inner_code", property = "entityInnerCode"), @Result(column = "channel_mer_id", property = "channelMerId"), @Result(column = "channel_type", property = "channelType"),
               @Result(column = "complete_time", property = "completeTime"), @Result(column = "order_ceate_time", property = "orderCeateTime"), @Result(column = "txn_type", property = "txnType"),
               @Result(column = "txn_sub_type", property = "txnSubType"), @Result(column = "pay_type", property = "payType"), @Result(column = "pay_sub_type", property = "paySubType"),
               @Result(column = "settle_amount", property = "settleAmount"), @Result(column = "settle_date", property = "settleDate"), @Result(column = "settle_status", property = "settleStatus"),
               @Result(column = "create_user_id", property = "createUserId"), @Result(column = "create_time", property = "createTime"), @Result(column = "sync_status", property = "syncStatus"),
               @Result(column = "inner_code", property = "innerCode") })
    @Select("SELECT * FROM t_trade_order WHERE id = #{id}")
    public TradeOrderDO getById(@Param("id") int id);

    @Results({ @Result(column = "order_no", property = "orderNo"), @Result(column = "pay_order_no", property = "payOrderNo"), @Result(column = "txn_amount", property = "txnAmount"),
               @Result(column = "installment_num", property = "installmentNum"), @Result(column = "resp_code", property = "respCode"), @Result(column = "resp_msg", property = "respMsg"),
               @Result(column = "entity_inner_code", property = "entityInnerCode"), @Result(column = "channel_mer_id", property = "channelMerId"), @Result(column = "channel_type", property = "channelType"),
               @Result(column = "complete_time", property = "completeTime"), @Result(column = "order_ceate_time", property = "orderCeateTime"), @Result(column = "txn_type", property = "txnType"),
               @Result(column = "txn_sub_type", property = "txnSubType"), @Result(column = "pay_type", property = "payType"), @Result(column = "pay_sub_type", property = "paySubType"),
               @Result(column = "settle_amount", property = "settleAmount"), @Result(column = "settle_date", property = "settleDate"), @Result(column = "settle_status", property = "settleStatus"),
               @Result(column = "create_user_id", property = "createUserId"), @Result(column = "create_time", property = "createTime"), @Result(column = "sync_status", property = "syncStatus"),
               @Result(column = "inner_code", property = "innerCode") })
    @Select("SELECT * FROM t_trade_order WHERE order_no = #{orderNo} order by create_time limit 1 ")
    public TradeOrderDO queryByOrderId(@Param("orderNo") String orderNo);

    @Results({ @Result(column = "order_no", property = "orderNo"), @Result(column = "pay_order_no", property = "payOrderNo"), @Result(column = "txn_amount", property = "txnAmount"),
               @Result(column = "installment_num", property = "installmentNum"), @Result(column = "resp_code", property = "respCode"), @Result(column = "resp_msg", property = "respMsg"),
               @Result(column = "entity_inner_code", property = "entityInnerCode"), @Result(column = "channel_mer_id", property = "channelMerId"), @Result(column = "channel_type", property = "channelType"),
               @Result(column = "complete_time", property = "completeTime"), @Result(column = "order_ceate_time", property = "orderCeateTime"), @Result(column = "txn_type", property = "txnType"),
               @Result(column = "txn_sub_type", property = "txnSubType"), @Result(column = "pay_type", property = "payType"), @Result(column = "pay_sub_type", property = "paySubType"),
               @Result(column = "settle_amount", property = "settleAmount"), @Result(column = "settle_date", property = "settleDate"), @Result(column = "settle_status", property = "settleStatus"),
               @Result(column = "create_user_id", property = "createUserId"), @Result(column = "create_time", property = "createTime"), @Result(column = "sync_status", property = "syncStatus"),
               @Result(column = "inner_code", property = "innerCode") })
    //@Select("SELECT * FROM t_trade_order WHERE resp_code = '1000'")
    @SelectProvider(type = TradeOrderProvider.class, method = "queryAllNotComplete")
    public List<TradeOrderDO> queryAllNotComplete(@Param("orderNo") String orderNo);

    @Results({ @Result(column = "order_no", property = "orderNo"), @Result(column = "pay_order_no", property = "payOrderNo"), @Result(column = "txn_amount", property = "txnAmount"),
               @Result(column = "installment_num", property = "installmentNum"), @Result(column = "resp_code", property = "respCode"), @Result(column = "resp_msg", property = "respMsg"),
               @Result(column = "entity_inner_code", property = "entityInnerCode"), @Result(column = "channel_mer_id", property = "channelMerId"), @Result(column = "channel_type", property = "channelType"),
               @Result(column = "complete_time", property = "completeTime"), @Result(column = "order_ceate_time", property = "orderCeateTime"), @Result(column = "txn_type", property = "txnType"),
               @Result(column = "txn_sub_type", property = "txnSubType"), @Result(column = "pay_type", property = "payType"), @Result(column = "pay_sub_type", property = "paySubType"),
               @Result(column = "settle_amount", property = "settleAmount"), @Result(column = "settle_date", property = "settleDate"), @Result(column = "settle_status", property = "settleStatus"),
               @Result(column = "create_user_id", property = "createUserId"), @Result(column = "create_time", property = "createTime"), @Result(column = "sync_status", property = "syncStatus"),
               @Result(column = "inner_code", property = "innerCode") })
    @Select("SELECT * FROM t_trade_order WHERE sync_status = '0' and resp_code ='1001' order by create_time desc ")
    public List<TradeOrderDO> queryAllNotSyncDate();

    @Results({ @Result(column = "order_no", property = "orderNo"), @Result(column = "pay_order_no", property = "payOrderNo"), @Result(column = "txn_amount", property = "txnAmount"),
               @Result(column = "installment_num", property = "installmentNum"), @Result(column = "resp_code", property = "respCode"), @Result(column = "resp_msg", property = "respMsg"),
               @Result(column = "entity_inner_code", property = "entityInnerCode"), @Result(column = "channel_mer_id", property = "channelMerId"), @Result(column = "channel_type", property = "channelType"),
               @Result(column = "complete_time", property = "completeTime"), @Result(column = "order_ceate_time", property = "orderCeateTime"), @Result(column = "txn_type", property = "txnType"),
               @Result(column = "txn_sub_type", property = "txnSubType"), @Result(column = "pay_type", property = "payType"), @Result(column = "pay_sub_type", property = "paySubType"),
               @Result(column = "settle_amount", property = "settleAmount"), @Result(column = "settle_date", property = "settleDate"), @Result(column = "settle_status", property = "settleStatus"),
               @Result(column = "create_user_id", property = "createUserId"), @Result(column = "create_time", property = "createTime"), @Result(column = "sync_status", property = "syncStatus"),
               @Result(column = "inner_code", property = "innerCode") })
    @Select("SELECT * FROM t_trade_order WHERE pay_order_no = #{salesOrderNo}")
    public TradeOrderDO queryBySalesOrderNo(@Param("salesOrderNo") String salesOrderNo);

    @Insert("INSERT into t_trade_order(id,order_no,pay_order_no,txn_amount,installment_num,resp_code,resp_msg,entity_inner_code,channel_mer_id,channel_type,complete_time,order_ceate_time,txn_type,txn_sub_type,pay_type,pay_sub_type,settle_amount,settle_date,settle_status,create_user_id,create_time,sync_status,inner_code) VALUES (#{id},#{orderNo},#{payOrderNo},#{txnAmount},#{installmentNum},#{respCode},#{respMsg},#{entityInnerCode},#{channelMerId},#{channelType},#{completeTime},#{orderCeateTime},#{txnType},#{txnSubType},#{payType},#{paySubType},#{settleAmount},#{settleDate},#{settleStatus},#{createUserId},#{createTime},#{syncStatus},#{innerCode})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(TradeOrderDO tradeOrder);

    @Delete("DELETE FROM t_trade_order WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = TradeOrderProvider.class, method = "update")
    public int update(@Param("tradeOrder") TradeOrderDO tradeOrder);
    
    @UpdateProvider(type = TradeOrderProvider.class, method = "updateOnlyFail")
    public int updateOnlyFail(@Param("tradeOrder") TradeOrderDO tradeOrder);
    @Results({ @Result(column = "order_no", property = "orderNo"), @Result(column = "pay_order_no", property = "payOrderNo"), @Result(column = "txn_amount", property = "txnAmount"),
               @Result(column = "installment_num", property = "installmentNum"), @Result(column = "resp_code", property = "respCode"), @Result(column = "resp_msg", property = "respMsg"),
               @Result(column = "entity_inner_code", property = "entityInnerCode"), @Result(column = "channel_mer_id", property = "channelMerId"), @Result(column = "channel_type", property = "channelType"),
               @Result(column = "complete_time", property = "completeTime"), @Result(column = "order_ceate_time", property = "orderCeateTime"), @Result(column = "txn_type", property = "txnType"),
               @Result(column = "txn_sub_type", property = "txnSubType"), @Result(column = "pay_type", property = "payType"), @Result(column = "pay_sub_type", property = "paySubType"),
               @Result(column = "settle_amount", property = "settleAmount"), @Result(column = "settle_date", property = "settleDate"), @Result(column = "settle_status", property = "settleStatus"),
               @Result(column = "create_user_id", property = "createUserId"), @Result(column = "create_time", property = "createTime"), @Result(column = "sync_status", property = "syncStatus"),
               @Result(column = "inner_code", property = "innerCode") })
    @SelectProvider(type = TradeOrderProvider.class, method = "pageList")
    public List<TradeOrderDO> pageList(@Param("tradeOrder") TradeOrderDO tradeOrder, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = TradeOrderProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("tradeOrder") TradeOrderDO tradeOrder);

}