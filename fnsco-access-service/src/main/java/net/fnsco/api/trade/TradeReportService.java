package net.fnsco.api.trade;

import java.util.List;

import net.fnsco.api.dto.TradeReportDTO;
import net.fnsco.api.dto.TurnoverDTO;

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
    List<TurnoverDTO> queryTurnovers(TradeReportDTO tradeReportDTO);
}
