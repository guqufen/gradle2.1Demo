package net.fnsco.bigdata.api.trade;

import java.util.List;

import net.fnsco.bigdata.api.dto.TradeDataDTO;
import net.fnsco.bigdata.api.dto.TradeDataPageDTO;
import net.fnsco.bigdata.api.dto.TradeDataQueryDTO;
import net.fnsco.bigdata.service.domain.trade.TradeData;
import net.fnsco.bigdata.service.domain.trade.TradeStatistics;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

public interface TradeStatisticsService {

    /**
     * queryTradeData:(这里用一句话描述这个方法的作用) web分页查询
     * @param merchantCore
     * @param currentPageNum
     * @param perPageSize
     * @return    设定文件
     * @return ResultPageDTO<TradeDataDTO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    ResultPageDTO<TradeStatistics> queryTradeData(TradeStatistics tradeStatistics, int currentPageNum, int perPageSize);
    /**
    * 查询符合条件的数据用于excel导出
    * @param tradeDataDTO
    * @return
    */
    List<TradeData> queryDataList(TradeDataDTO tradeDataDTO);
}
