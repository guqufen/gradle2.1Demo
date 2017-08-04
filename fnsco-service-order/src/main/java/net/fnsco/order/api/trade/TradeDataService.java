package net.fnsco.order.api.trade;

import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.order.api.dto.TradeDataDTO;
import net.fnsco.order.api.dto.TradeDataQueryDTO;
import net.fnsco.order.service.domain.trade.TradeData;

public interface TradeDataService {

    boolean saveTradeData(TradeDataDTO tradeData);

    /**
     * 条件查询所有数据
     * @param merchantCore
     * @return
     */
    ResultPageDTO<TradeData> queryAllByCondition(TradeDataQueryDTO merchantCore);

    TradeData queryByTradeId(String tradeId);

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
    ResultPageDTO<TradeData> queryTradeData(TradeDataDTO tradeDataDTO, int currentPageNum, int perPageSize);

}
