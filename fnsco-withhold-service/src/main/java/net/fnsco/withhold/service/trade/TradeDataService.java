package net.fnsco.withhold.service.trade;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.withhold.service.trade.dao.TradeDataDAO;
import net.fnsco.withhold.service.trade.entity.TradeDataDO;

@Service
public class TradeDataService extends BaseService {

    private Logger       logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TradeDataDAO tradeDataDAO;

    // 查询
    public TradeDataDO doQueryById(Integer id) {
        TradeDataDO obj = this.tradeDataDAO.getById(id);
        return obj;
    }

    public TradeDataDO findByOrderSn(String orderId) {
        TradeDataDO obj = this.tradeDataDAO.getByOrderSn(orderId);
        return obj;
    }

    public void update(TradeDataDO tradeData) {
        this.tradeDataDAO.update(tradeData);
    }

    public ResultPageDTO<TradeDataDO> page(TradeDataDO tradeData, Integer pageNum, Integer pageSize) {
        logger.info("开始分页查询TradeDataService.page, tradeData=" + tradeData.toString());
        List<TradeDataDO> pageList = this.tradeDataDAO.pageList(tradeData, pageNum, pageSize);
        Integer count = this.tradeDataDAO.pageListCount(tradeData);
        ResultPageDTO<TradeDataDO> pager = new ResultPageDTO<TradeDataDO>(count, pageList);
        return pager;
    }
}