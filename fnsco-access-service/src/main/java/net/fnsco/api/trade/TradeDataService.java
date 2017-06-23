package net.fnsco.api.trade;

import net.fnsco.api.dto.TradeDataDTO;

public interface TradeDataService {
    boolean saveTradeData(TradeDataDTO tradeData);
}
