package net.fnsco.bigdata.service.modules.trade;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.bigdata.api.dto.TradeDataDTO;
import net.fnsco.bigdata.api.trade.TradeStatisticsService;
import net.fnsco.bigdata.service.dao.master.MerchantChannelDao;
import net.fnsco.bigdata.service.dao.master.MerchantCoreDao;
import net.fnsco.bigdata.service.dao.master.MerchantTerminalDao;
import net.fnsco.bigdata.service.dao.master.MerchantUserRelDao;
import net.fnsco.bigdata.service.dao.master.trade.TradeDataDAO;
import net.fnsco.bigdata.service.dao.master.trade.TradeStatisticsDAO;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.bigdata.service.domain.trade.TradeData;
import net.fnsco.bigdata.service.domain.trade.TradeStatistics;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.PageDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;

/**
 * 交易流水服务类
 * @author sxf
 *
 */
@Service
public class TradeStatisticsServiceImpl extends BaseService implements TradeStatisticsService {
    @Autowired
    private TradeStatisticsDAO        tradeListDAO;
    @Autowired
    private MerchantCoreDao     merchantCoreDao;
    @Autowired
    private MerchantTerminalDao merchantTerminalDao;

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
	public List<TradeData> queryDataList(TradeDataDTO tradeDataDTO) {
		// TODO Auto-generated method stub
		return null;
	}

   /* @Override
    public List<TradeData> queryDataList(TradeDataDTO tradeDataDTO) {
        TradeData tradeData = new TradeData();
        if (!StringUtils.isEmpty(tradeDataDTO.getStartSendTime())) {
            tradeDataDTO.setStartSendTime(DateUtils.getDateStartTime(tradeDataDTO.getStartSendTime()));
        }
        if (!StringUtils.isEmpty(tradeDataDTO.getEndSendTime())) {
            tradeDataDTO.setEndSendTime(DateUtils.getDateEndTime(tradeDataDTO.getEndSendTime()));
        }

        if (!StringUtils.isEmpty(tradeDataDTO.getStartTime())) {
            tradeDataDTO.setStartTime(DateUtils.getDateStartTime(tradeDataDTO.getStartTime()));
        }
        if (!StringUtils.isEmpty(tradeDataDTO.getEndTime())) {
            tradeDataDTO.setEndTime(DateUtils.getDateEndTime(tradeDataDTO.getEndTime()));
        }
        BeanUtils.copyProperties(tradeDataDTO, tradeData);
        List<TradeData> datas = tradeListDAO.queryTotalByCondition(tradeData);
        for (TradeData merchantdo : datas) {
            String id = merchantdo.getTermId();
            String code = merchantdo.getInnerCode();
            String sn = merchantTerminalDao.querySnCode(id, code);
            merchantdo.setSnCode(sn);
        }
        for (TradeData tradeData2 : datas) {
            if (!Strings.isNullOrEmpty(tradeData2.getInnerCode())) {
                MerchantCore core = merchantCoreDao.selectByInnerCode(tradeData2.getInnerCode());
                if (null != core) {
                    tradeData2.setMerName(core.getMerName());
                }
            }
        }
        return datas;
    }*/
}
