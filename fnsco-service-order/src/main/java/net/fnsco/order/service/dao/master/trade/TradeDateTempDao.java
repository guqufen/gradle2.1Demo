package net.fnsco.order.service.dao.master.trade;

import java.util.List;

import net.fnsco.order.api.dto.TurnoverDTO;
import net.fnsco.order.service.domain.trade.TradeByDay;
import net.fnsco.order.service.domain.trade.TradeByHour;
import net.fnsco.order.service.domain.trade.TradeByPayType;
import net.fnsco.order.service.domain.trade.TradeData;
import net.fnsco.order.service.domain.trade.TradeDateTemp;
/**
 * @desc 流水统计临时表
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月27日 上午11:31:12
 */
public interface TradeDateTempDao {

    int deleteByPrimaryKey(Integer id);

    int insert(TradeDateTemp record);

    int insertSelective(TradeDateTemp record);

    TradeDateTemp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TradeDateTemp record);

    int updateByPrimaryKey(TradeDateTemp record);
    /**
     * insertBatch:(这里用一句话描述这个方法的作用) 批量插入数据
     * @param record
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月9日 下午4:44:44
     * @return int    DOM对象
     */
    int insertBatch(List<TradeDateTemp> record);
    /**
     * 
     * deleteAll:(这里用一句话描述这个方法的作用)删除全表数据
     *
     * @return    设定文件
     * @return int    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    int deleteAll();
    /**
     * selectTradeDataByDate:(这里用一句话描述这个方法的作用)以天统计数据
     *
     * @return    设定文件
     * @return List<TradeByDay>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<TradeByDay> selectTradeDataByDate();
    /**
     * selectTradeDataByHour:(这里用一句话描述这个方法的作用)以小时为单位统计
     *
     * @return    设定文件
     * @return List<TradeByHour>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<TradeByHour> selectTradeDataByHour();
    /**
     * selectTradeDataByPayType:(这里用一句话描述这个方法的作用)按照支付方式来统计交易额
     *
     * @return    设定文件
     * @return List<TradeByPayType>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<TradeByPayType> selectTradeDataByPayType();
    
    /**
     * queryTodayTurnover:(这里用一句话描述这个方法的作用)统计今日营业额
     *
     * @param record
     * @return    设定文件
     * @return TurnoverDTO    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    TurnoverDTO queryTodayTurnover(TradeByDay record);
}