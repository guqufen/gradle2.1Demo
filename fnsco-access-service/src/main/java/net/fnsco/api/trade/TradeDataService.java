package net.fnsco.api.trade;

import java.util.List;

import net.fnsco.api.dto.TradeDataDTO;
import net.fnsco.service.domain.trade.TradeData;

public interface TradeDataService {
    boolean saveTradeData(TradeDataDTO tradeData);

    /**
     * 条件查询所有数据
     * @param merchantCore
     * @return
     */
    List<TradeData> queryAllByCondition(TradeDataDTO merchantCore);

}
