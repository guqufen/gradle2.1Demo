package net.fnsco.service.modules.trade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fnsco.api.trade.TradeReportService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.service.dao.master.trade.TradeByDayDao;
import net.fnsco.service.dao.master.trade.TradeByHourDao;
import net.fnsco.service.dao.master.trade.TradeByPayTypeDao;
import net.fnsco.service.dao.master.trade.TradeDataDAO;
import net.fnsco.service.dao.master.trade.TradeDateTempDao;
import net.fnsco.service.domain.trade.TradeByDay;
import net.fnsco.service.domain.trade.TradeByHour;
import net.fnsco.service.domain.trade.TradeByPayType;
import net.fnsco.service.domain.trade.TradeData;
import net.fnsco.service.domain.trade.TradeDateTemp;

/**
 * @desc 交易统计service
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月27日 上午10:02:54
 */
@Service
public class TradeReportServiceImpl extends BaseService implements TradeReportService {
    
    @Autowired
    private TradeDateTempDao   tradeDateTempDao;
    
    @Autowired
    private TradeDataDAO       tradeDataDAO;
    
    @Autowired
    private TradeByDayDao      tradeByDayDao;
    
    @Autowired
    private TradeByHourDao     tradeByHourDao;
    
    @Autowired
    private TradeByPayTypeDao  tradeByPayTypeDao;
    /**
     * (non-Javadoc)生成统计数据
     * @see net.fnsco.api.trade.TradeReportService#buildTradeReportDaTa()
     * @author tangliang
     * @date 2017年7月27日 上午11:07:01  
     */
    @Transactional
    @Override
    public void buildTradeReportDaTa() {
        
        //首先清空临时表
        tradeDateTempDao.deleteAll();
        //查询最新交易流水数据插入临时表
        TradeData record = new TradeData();
        record.setStartTime(DateUtils.getTimeByDayStr(-1));
        record.setEndTime(DateUtils.getTimeByDayStr(0));
        List<TradeDateTemp> tempDatas = tradeDataDAO.queryTempByCondition(record);
        String tradeDateStr = DateUtils.getTimeByDayStr(-1);
        for (TradeDateTemp tradeDateTemp : tempDatas) {
            String timeStamp =  tradeDateTemp.getTimeStamp();
            tradeDateTemp.setTradeDate(timeStamp.substring(0, tradeDateStr.length()-6));
            tradeDateTemp.setTradeHoure(timeStamp.substring(8, 10));
            tradeDateTempDao.insertSelective(tradeDateTemp);
        }
        
        //分别按小时、天、支付渠道统计查询且插入对应表中
        
        List<TradeByDay> tradeDayData = tradeDateTempDao.selectTradeDataByDate();
        for (TradeByDay tradeByDay : tradeDayData) {
            tradeByDay.setTradeDate(tradeDateStr.substring(0, tradeDateStr.length()-6));
            tradeByDayDao.insertSelective(tradeByDay);
        }
        
        List<TradeByHour> tradeHourData = tradeDateTempDao.selectTradeDataByHour();
        for (TradeByHour tradeByHour : tradeHourData) {
            tradeByHour.setTradeDate(tradeDateStr.substring(0, tradeDateStr.length()-6));
            tradeByHourDao.insertSelective(tradeByHour);
        }
        
        List<TradeByPayType> tradePayTypeData = tradeDateTempDao.selectTradeDataByPayType();
        for (TradeByPayType tradeByPayType : tradePayTypeData) {
            tradeByPayType.setTradeDate(tradeDateStr.substring(0, tradeDateStr.length()-6));
            tradeByPayTypeDao.insert(tradeByPayType);
        }
    }
}
