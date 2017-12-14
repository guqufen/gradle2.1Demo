package net.fnsco.trading.service.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.fnsco.trading.service.order.dao.helper.TradeOrderByPayTypeProvider;
import net.fnsco.trading.service.order.dto.OrderPayTypeDTO;
import net.fnsco.trading.service.order.entity.TradeOrderByPayTypeDO;;

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
    
    /**
     * selectByCondition:(按照条件查询)
     *
     * @param  @param startTradeDate
     * @param  @param endTradeDate
     * @param  @param userId
     * @param  @return    设定文件
     * @return OrderPayTypeDTO    DOM对象
     * @author tangliang
     * @date   2017年12月7日 下午4:58:58
     */
    @Results({@Result( column = "pay_type",property = "payType"),@Result( column = "order_num",property = "orderNum"),@Result( column = "turnover",property = "turnover")})
    @Select("SELECT SUM(turnover) AS turnover , SUM(order_num) AS orderNum,pay_type AS payType FROM r_trade_order_by_pay_type WHERE inner_code IN "
    		+ "(SELECT inner_code FROM u_app_user_merchant WHERE app_user_id = #{userId}) AND trade_date >= #{startTradeDate} AND trade_date <= #{endTradeDate} GROUP BY pay_type")
    public List<OrderPayTypeDTO> selectByCondition(@Param("startTradeDate") String startTradeDate,@Param("endTradeDate") String endTradeDate,@Param("userId")Integer userId);
    
    /**
     * deleteByCondition:(条件删除数据)
     *
     * @param  @param tradeOrderByPayType
     * @param  @return    设定文件
     * @return int    DOM对象
     * @author tangliang
     * @date   2017年12月14日 下午1:54:35
     */
    @DeleteProvider(type = TradeOrderByPayTypeProvider.class, method = "deleteByCondition")
    public int deleteByCondition(@Param("tradeOrderByPayType") TradeOrderByPayTypeDO tradeOrderByPayType);
}