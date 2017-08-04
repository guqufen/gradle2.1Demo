package net.fnsco.order.service.dao.master.trade;

import java.util.List;

import net.fnsco.core.base.PageDTO;
import net.fnsco.order.api.dto.TurnoverDTO;
import net.fnsco.order.service.domain.trade.TradeByDay;
import net.fnsco.order.service.domain.trade.TradeData;
import net.fnsco.order.service.domain.trade.TradeDateTemp;
/**
 * 
 * @desc
 * @author   tangliang
 * @version  
 * @since    Ver 1.1
 * @Date	 2017 2017年6月28日 下午3:42:26
 *
 */
public interface TradeDataDAO {

    int deleteByPrimaryKey(String id);

    int insert(TradeData record);

    int insertSelective(TradeData record);

    TradeData selectByPrimaryKey(String id);

    TradeData selectByMd5(String md5);

    int updateByPrimaryKeySelective(TradeData record);

    int updateByPrimaryKey(TradeData record);
    
    /**
     * 条件分页查询
     */
    List<TradeData> queryPageList(PageDTO<TradeData> pages);
    
    /**
     * 条件查询总数
     * @param pages
     * @return
     */
    int queryTotalByCondition(TradeData record);
    
    /**
     * queryByCondition:(这里用一句话描述这个方法的作用)
     *
     * @param record
     * @return    设定文件
     * @return List<TradeData>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<TradeDateTemp> queryTempByCondition(TradeData record);
    
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