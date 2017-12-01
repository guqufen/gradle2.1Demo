package net.fnsco.bigdata.service.modules.trade;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.bigdata.api.trade.TradeStatisticsService;
import net.fnsco.bigdata.service.dao.master.trade.TradeStatisticsDAO;
import net.fnsco.bigdata.service.domain.trade.TradeStatistics;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.PageDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;

/**
 * 交易流水服务类
 * @author 
 *
 */
@Service
public class TradeStatisticsServiceImpl extends BaseService implements TradeStatisticsService {
    @Autowired
    private TradeStatisticsDAO        tradeListDAO;

    /**
     * 条件分页查询
     */
    @Override
    public ResultPageDTO<TradeStatistics> queryTradeData(TradeStatistics tradeStatistics, int currentPageNum, int perPageSize) {
        if (!StringUtils.isEmpty(tradeStatistics.getStartTime())) {
        	tradeStatistics.setStartTime(DateUtils.formatDateStrInput(tradeStatistics.getStartTime()));
        }
        if (!StringUtils.isEmpty(tradeStatistics.getEndTime())) {
        	tradeStatistics.setEndTime(DateUtils.formatDateStrInput(tradeStatistics.getEndTime()));
        }
        PageDTO<TradeStatistics> pages = new PageDTO<TradeStatistics>(currentPageNum, perPageSize, tradeStatistics);
        List<TradeStatistics> datas = tradeListDAO.queryPageList(pages);
        for(TradeStatistics trade : datas) {
        	TradeStatistics statistics = tradeListDAO.queryMerIdByInnerCode(trade.getInnerCode());
        	if(statistics!=null) {
        		trade.setMerId(statistics.getMerId());
        		trade.setMerName(statistics.getMerName());
        	}
        }
        int total = tradeListDAO.queryTotalByCondition(tradeStatistics);
        ResultPageDTO<TradeStatistics> result = new ResultPageDTO<TradeStatistics>(total, datas);
        result.setCurrentPage(currentPageNum);
        return result;

    }

    @Override
    public List<TradeStatistics> queryDataList(TradeStatistics tradeStatistics) {
    	if (!StringUtils.isEmpty(tradeStatistics.getStartTime())) {
        	tradeStatistics.setStartTime(DateUtils.formatDateStrInput(tradeStatistics.getStartTime()));
        }
        if (!StringUtils.isEmpty(tradeStatistics.getEndTime())) {
        	tradeStatistics.setEndTime(DateUtils.formatDateStrInput(tradeStatistics.getEndTime()));
        }
        List<TradeStatistics> datas = tradeListDAO.queryByAllCondition(tradeStatistics);
        for(TradeStatistics trade : datas) {
        	TradeStatistics statistics = tradeListDAO.queryMerIdByInnerCode(trade.getInnerCode());
        	if(statistics!=null) {
        		trade.setMerId(statistics.getMerId());
        		trade.setMerName(statistics.getMerName());
        	}
        }
        return datas;
    }
}
