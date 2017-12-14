package net.fnsco.trading.service.order.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.trading.service.order.entity.TradeOrderByPayMediumDO;
import net.fnsco.trading.service.order.dao.helper.TradeOrderByPayMediumProvider;

import java.util.List;;

public interface TradeOrderByPayMediumDAO {

    @Results({@Result( column = "pay_medium",property = "payMedium"),@Result( column = "trade_date",property = "tradeDate"),@Result( column = "inner_code",property = "innerCode"),@Result( column = "order_num",property = "orderNum"),@Result( column = "order_price",property = "orderPrice") })
    @Select("SELECT * FROM r_trade_order_by_pay_medium WHERE id = #{id}")
    public TradeOrderByPayMediumDO getById(@Param("id") int id);

    @Insert("INSERT into r_trade_order_by_pay_medium(id,pay_medium,trade_date,inner_code,turnover,order_num,order_price) VALUES (#{id},#{payMedium},#{tradeDate},#{innerCode},#{turnover},#{orderNum},#{orderPrice})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(TradeOrderByPayMediumDO tradeOrderByPayMedium);

    @Delete("DELETE FROM r_trade_order_by_pay_medium WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = TradeOrderByPayMediumProvider.class, method = "update")
    public int update(@Param("tradeOrderByPayMedium") TradeOrderByPayMediumDO  tradeOrderByPayMedium);

    @Results({@Result( column = "pay_medium",property = "payMedium"),@Result( column = "trade_date",property = "tradeDate"),@Result( column = "inner_code",property = "innerCode"),@Result( column = "order_num",property = "orderNum"),@Result( column = "order_price",property = "orderPrice") })
    @SelectProvider(type = TradeOrderByPayMediumProvider.class, method = "pageList")
    public List<TradeOrderByPayMediumDO> pageList(@Param("tradeOrderByPayMedium") TradeOrderByPayMediumDO tradeOrderByPayMedium, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = TradeOrderByPayMediumProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("tradeOrderByPayMedium") TradeOrderByPayMediumDO tradeOrderByPayMedium);
    
    /**
     * deleteByCondition:(批量删除数据)
     *
     * @param  @param tradeOrderByPayMedium
     * @param  @return    设定文件
     * @return int    DOM对象
     * @author tangliang
     * @date   2017年12月14日 下午2:34:29
     */
    @DeleteProvider(type = TradeOrderByPayMediumProvider.class, method = "deleteByCondition")
    public int deleteByCondition(@Param("tradeOrderByPayMedium") TradeOrderByPayMediumDO tradeOrderByPayMedium);

}