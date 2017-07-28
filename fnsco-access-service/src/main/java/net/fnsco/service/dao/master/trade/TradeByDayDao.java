package net.fnsco.service.dao.master.trade;

import net.fnsco.api.dto.TurnoverDTO;
import net.fnsco.service.domain.trade.TradeByDay;
/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月27日 上午9:45:15
 */
public interface TradeByDayDao {

    int insert(TradeByDay record);

    int insertSelective(TradeByDay record);
    
    /**
     * selectTradeDayDataByTradeDate:(这里用一句话描述这个方法的作用)
     *
     * @return    设定文件
     * @return TurnoverDTO    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    TurnoverDTO selectTradeDayDataByTradeDate(TradeByDay record);
    
    /**
     * selectWeekLyTradeData:(这里用一句话描述这个方法的作用)查找周报数据（只有店长有周报数据）
     *
     * @param record
     * @return    设定文件
     * @return TurnoverDTO    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    TurnoverDTO selectWeekLyTradeData(TradeByDay record);
}