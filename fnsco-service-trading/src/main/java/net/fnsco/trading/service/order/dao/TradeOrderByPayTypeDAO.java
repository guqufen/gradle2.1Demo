package net.fnsco.trading.service.order.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.trading.service.order.entity.TradeOrderByPayTypeDO;
import net.fnsco.trading.service.order.dao.helper.TradeOrderByPayTypeProvider;

import java.util.List;;

public interface TradeOrderByPayTypeDAO {

    @Results({@Result( column = "pay_type",property = "payType"),@Result( column = "trade_date",property = "tradeDate"),@Result( column = "inner_code",property = "innerCode"),@Result( column = "order_num",property = "orderNum"),@Result( column = "order_price",property = "orderPrice") })
    @Select("SELECT * FROM r_trade_order_by_pay_type WHERE id = #{id}")
    public TradeOrderByPayTypeDO getById(@Param("id") int id);

    @Insert("INSERT into r_trade_order_by_pay_type(id,pay_type,trade_date,inner_code,turnover,order_num,order_price) VALUES (#{id},#{payType},#{tradeDate},#{innerCode},#{turnover},#{orderNum},#{orderPrice})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(TradeOrderByPayTypeDO tradeOrderByPayType);

    @Delete("DELETE FROM r_trade_order_by_pay_type WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = TradeOrderByPayTypeProvider.class, method = "update")
    public int update(@Param("tradeOrderByPayType") TradeOrderByPayTypeDO  tradeOrderByPayType);

    @Results({@Result( column = "pay_type",property = "payType"),@Result( column = "trade_date",property = "tradeDate"),@Result( column = "inner_code",property = "innerCode"),@Result( column = "order_num",property = "orderNum"),@Result( column = "order_price",property = "orderPrice") })
    @SelectProvider(type = TradeOrderByPayTypeProvider.class, method = "pageList")
    public List<TradeOrderByPayTypeDO> pageList(@Param("tradeOrderByPayType") TradeOrderByPayTypeDO tradeOrderByPayType, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = TradeOrderByPayTypeProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("tradeOrderByPayType") TradeOrderByPayTypeDO tradeOrderByPayType);

}