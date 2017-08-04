package net.fnsco.order.api.trade;

import net.fnsco.order.api.dto.BusinessTrendDTO;
import net.fnsco.order.api.dto.ConsumptionDTO;
import net.fnsco.order.api.dto.PeakTradeDTO;
import net.fnsco.order.api.dto.TradeReportDTO;
import net.fnsco.order.api.dto.TradeTurnoverDTO;
import net.fnsco.order.api.dto.WeeklyDTO;
import net.fnsco.order.api.dto.WeeklyHisDateDTO;

/**
 * @desc 统计service
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月27日 上午10:01:56
 */

public interface TradeReportService {
    
    /**
     * buildTradeReportDaTa:(这里用一句话描述这个方法的作用)生成报表数据、主要按照小时、天、渠道统计生成数据
     *    设定文件
     * @return void    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    void buildTradeReportDaTa();
    
    /**
     * queryTurnovers:(这里用一句话描述这个方法的作用)根据用户ID查询营业额数据
     *
     * @param tradeReportDTO
     * @return    设定文件
     * @return List<TurnoverDTO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    TradeTurnoverDTO queryTurnovers(TradeReportDTO tradeReportDTO);
    /**
     * queryWeeklyByInnerCode:(这里用一句话描述这个方法的作用)查询某时间段的周报详情数据
     *
     * @param tradeReportDTO
     * @return    设定文件
     * @return WeeklyDTO    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    WeeklyDTO queryWeeklyByInnerCode(TradeReportDTO tradeReportDTO);
    /**
     * queryWeeklyHisDate:(这里用一句话描述这个方法的作用)查询周报历史时间段
     *
     * @param tradeReportDTO
     * @return    设定文件
     * @return List<WeeklyHisDateDTO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    WeeklyHisDateDTO queryWeeklyHisDate(TradeReportDTO tradeReportDTO);
    /**
     * 
     * queryTrandPeak:(这里用一句话描述这个方法的作用)查询峰值
     *
     * @param tradeReportDTO
     * @return    设定文件
     * @return TrandPeakDTO    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    ConsumptionDTO queryConsumption(TradeReportDTO tradeReportDTO);
    /**
     * queryBusinessTrends:(这里用一句话描述这个方法的作用)查询经营趋势数据
     *
     * @param tradeReportDTO
     * @return    设定文件
     * @return BusinessTrendDTO    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    BusinessTrendDTO queryBusinessTrends(TradeReportDTO tradeReportDTO);
    /**
     * queryPeakTrade:(这里用一句话描述这个方法的作用)查询交易峰值
     *
     * @param tradeReportDTO
     * @return    设定文件
     * @return PeakTradeDTO    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    PeakTradeDTO queryPeakTrade(TradeReportDTO tradeReportDTO);
}
