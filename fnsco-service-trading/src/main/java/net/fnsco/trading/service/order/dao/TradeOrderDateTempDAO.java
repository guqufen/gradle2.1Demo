package net.fnsco.trading.service.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.fnsco.trading.service.order.dao.helper.TradeOrderDateTempProvider;
import net.fnsco.trading.service.order.entity.TradeOrderByDayDO;
import net.fnsco.trading.service.order.entity.TradeOrderByPayMediumDO;
import net.fnsco.trading.service.order.entity.TradeOrderByPayTypeDO;
import net.fnsco.trading.service.order.entity.TradeOrderDateTempDO;;

public interface TradeOrderDateTempDAO {

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "pay_sub_type",property = "paySubType"),@Result( column = "time_stamp",property = "timeStamp"),@Result( column = "trade_date",property = "tradeDate"),@Result( column = "trade_houre",property = "tradeHoure"),@Result( column = "procedure_fee",property = "procedureFee"),@Result( column = "terminal_code",property = "terminalCode") })
    @Select("SELECT * FROM r_trade_order_date_temp WHERE id = #{id}")
    public TradeOrderDateTempDO getById(@Param("id") int id);

    @Insert("INSERT into r_trade_order_date_temp(id,inner_code,amt,pay_sub_type,time_stamp,trade_date,trade_houre,procedure_fee,terminal_code) VALUES (#{id},#{innerCode},#{amt},#{paySubType},#{timeStamp},#{tradeDate},#{tradeHoure},#{procedureFee},#{terminalCode})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(TradeOrderDateTempDO tradeOrderDateTemp);

    @Delete("DELETE FROM r_trade_order_date_temp WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = TradeOrderDateTempProvider.class, method = "update")
    public int update(@Param("tradeOrderDateTemp") TradeOrderDateTempDO  tradeOrderDateTemp);

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "pay_sub_type",property = "paySubType"),@Result( column = "time_stamp",property = "timeStamp"),@Result( column = "trade_date",property = "tradeDate"),@Result( column = "trade_houre",property = "tradeHoure"),@Result( column = "procedure_fee",property = "procedureFee"),@Result( column = "terminal_code",property = "terminalCode") })
    @SelectProvider(type = TradeOrderDateTempProvider.class, method = "pageList")
    public List<TradeOrderDateTempDO> pageList(@Param("tradeOrderDateTemp") TradeOrderDateTempDO tradeOrderDateTemp, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = TradeOrderDateTempProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("tradeOrderDateTemp") TradeOrderDateTempDO tradeOrderDateTemp);
    
    /**
     * deleteAll:(删除全表数据，从新统计)
     *
     * @param  @return    设定文件
     * @return int    DOM对象
     * @author tangliang
     * @date   2017年12月13日 下午4:55:56
     */
    @Delete("DELETE FROM r_trade_order_date_temp")
    public int deleteAll();
    
    /**
     * insertBatch:(批量插入数据)
     *
     * @param  @param datas
     * @param  @return    设定文件
     * @return int    DOM对象
     * @author tangliang
     * @date   2017年12月14日 上午9:55:30
     */
    @InsertProvider(type = TradeOrderDateTempProvider.class, method = "insertBatch")
    public int insertBatch(@Param("list")List<TradeOrderDateTempDO> datas);
    
    /**
     * selectTradeDataByDay:(按照天统计)
     *
     * @param  @return    设定文件
     * @return List<TradeOrderByDayDO>    DOM对象
     * @author tangliang
     * @date   2017年12月14日 下午2:43:59
     */
    @SelectProvider(type = TradeOrderDateTempProvider.class, method = "selectTradeDataByDay")
    public List<TradeOrderByDayDO> selectTradeDataByDay();
    
    /**
     * selectTradeDataByPayType:(按照支付方式统计)
     *
     * @param  @return    设定文件
     * @return List<TradeOrderByPayTypeDO>    DOM对象
     * @author tangliang
     * @date   2017年12月14日 下午3:14:33
     */
    @SelectProvider(type = TradeOrderDateTempProvider.class, method = "selectTradeDataByPayType")
    public List<TradeOrderByPayTypeDO> selectTradeDataByPayType();
    
    /**
     * selectTradeDataByPayMedium:(按照支付媒介统计)
     *
     * @param  @return    设定文件
     * @return List<TradeOrderByPayMediumDO>    DOM对象
     * @author tangliang
     * @date   2017年12月14日 下午3:51:08
     */
    @SelectProvider(type = TradeOrderDateTempProvider.class, method = "selectTradeDataByPayMedium")
    public List<TradeOrderByPayMediumDO> selectTradeDataByPayMedium();

}