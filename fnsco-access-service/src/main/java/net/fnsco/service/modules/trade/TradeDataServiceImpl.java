package net.fnsco.service.modules.trade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.api.dto.TradeDataDTO;
import net.fnsco.api.trade.TradeDataService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.utils.DbUtil;
import net.fnsco.service.dao.master.trade.TradeDataDAO;
import net.fnsco.service.domain.trade.TradeData;

/**
 * 交易流水服务类
 * @author sxf
 *
 */
@Service
public class TradeDataServiceImpl extends BaseService implements TradeDataService {
    @Autowired
    private TradeDataDAO tradeListDAO;

    public boolean saveTradeData(TradeDataDTO tradeData) {
        TradeData tradeDataEntity = new TradeData();
        tradeDataEntity.setId(DbUtil.getUuid());
        tradeDataEntity.setAmt(tradeData.getAmt());
        tradeListDAO.insert(tradeDataEntity);
        return true;
    }
}
