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
import net.fnsco.trading.service.order.entity.TradeOrderByDayDO;
import net.fnsco.trading.service.order.dao.helper.TradeOrderByDayProvider;

import java.util.List;;

public interface TradeOrderByDayDAO {

    @Results({@Result( column = "trade_date",property = "tradeDate"),@Result( column = "inner_code",property = "innerCode"),@Result( column = "order_num",property = "orderNum"),@Result( column = "order_price",property = "orderPrice"),@Result( column = "procedure_fee",property = "procedureFee"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM r_trade_order_by_day WHERE id = #{id}")
    public TradeOrderByDayDO getById(@Param("id") int id);

    @Insert("INSERT into r_trade_order_by_day(id,trade_date,inner_code,turnover,order_num,order_price,procedure_fee,create_time) VALUES (#{id},#{tradeDate},#{innerCode},#{turnover},#{orderNum},#{orderPrice},#{procedureFee},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(TradeOrderByDayDO tradeOrderByDay);

    @Delete("DELETE FROM r_trade_order_by_day WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = TradeOrderByDayProvider.class, method = "update")
    public int update(@Param("tradeOrderByDay") TradeOrderByDayDO  tradeOrderByDay);

    @Results({@Result( column = "trade_date",property = "tradeDate"),@Result( column = "inner_code",property = "innerCode"),@Result( column = "order_num",property = "orderNum"),@Result( column = "order_price",property = "orderPrice"),@Result( column = "procedure_fee",property = "procedureFee"),@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = TradeOrderByDayProvider.class, method = "pageList")
    public List<TradeOrderByDayDO> pageList(@Param("tradeOrderByDay") TradeOrderByDayDO tradeOrderByDay, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = TradeOrderByDayProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("tradeOrderByDay") TradeOrderByDayDO tradeOrderByDay);
    
    @Select("SELECT SUM(turnover) FROM r_trade_order_by_day WHERE trade_date = #{tradeDate} AND inner_code IN (SELECT inner_code FROM u_app_user_merchant WHERE app_user_id = #{userId})")
    public String selectDayTurnover(@Param("tradeDate") String tradeDate,@Param("userId")Integer userId);
    
    
    @Select("SELECT SUM(turnover) FROM r_trade_order_by_day WHERE trade_date >= #{startTradeDate}  AND trade_date >= #{endTradeDate} AND inner_code IN (SELECT inner_code FROM u_app_user_merchant WHERE app_user_id = #{userId})")
    public String selectTotalTurnover(@Param("startTradeDate") String startTradeDate,@Param("endTradeDate") String endTradeDate,@Param("userId")Integer userId);
}