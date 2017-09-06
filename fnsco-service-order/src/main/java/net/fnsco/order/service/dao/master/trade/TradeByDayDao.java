package net.fnsco.order.service.dao.master.trade;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.order.api.dto.TradeDayDTO;
import net.fnsco.order.api.dto.TurnoverDTO;
import net.fnsco.order.api.report.dto.FinanceDayDTO;
import net.fnsco.order.api.report.dto.FinanceTurnoverDTO;
import net.fnsco.order.service.domain.trade.TradeByDay;
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
     * insertBatch:(这里用一句话描述这个方法的作用)批量插入
     * @param record
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月9日 下午5:44:16
     * @return int    DOM对象
     */
    int insertBatch(List<TradeByDay> record);
    /**
     * deleteByDate:(这里用一句话描述这个方法的作用)根据时间删除
     *
     * @param record
     * @return    设定文件
     * @return int    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    int deleteByDate(TradeByDay record);
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
     * selectFinanceByRecord:(这里用一句话描述这个方法的作用)
     * @param record
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月30日 下午4:14:33
     * @return FinanceDayDTO    DOM对象
     */
    FinanceTurnoverDTO selectFinanceByRecord(TradeByDay record);
    
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
    /**
     * 
     * selectByInnerCode:(这里用一句话描述这个方法的作用)查询每天的数据
     *
     * @param record
     * @return    设定文件
     * @return List<TradeDayDTO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<TradeDayDTO> selectByInnerCode(TradeByDay record);
    
    /**
     * selectMinTradeDateByUserId:(这里用一句话描述这个方法的作用)查询最早的交易时间
     *
     * @param appUserId
     * @param roleId
     * @return    设定文件
     * @return String    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    String selectMinTradeDateByUserId(@Param("appUserId")Integer appUserId,@Param("roleId")String roleId);
    /**
     * selectMinTradeDateByInnerCode:(这里用一句话描述这个方法的作用)查询最早的交易时间
     *
     * @param innerCode
     * @param roleId
     * @return    设定文件
     * @return String    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    String selectMinTradeDateByInnerCode(@Param("innerCode")String innerCode,@Param("roleId")String roleId);
    /**
     * selectFinaceByRecord:(按照日期位组查询统计)
     * @param record
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月30日 下午4:30:40
     * @return List<FinanceDayDTO>    DOM对象
     */
    List<FinanceDayDTO> selectFinaceByRecord(TradeByDay record);
}