package net.fnsco.service.modules.trade;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import net.fnsco.api.constant.ConstantEnum;
import net.fnsco.api.dto.TradeReportDTO;
import net.fnsco.api.dto.TurnoverDTO;
import net.fnsco.api.trade.TradeReportService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.service.dao.master.AppUserMerchantDao;
import net.fnsco.service.dao.master.trade.TradeByDayDao;
import net.fnsco.service.dao.master.trade.TradeByHourDao;
import net.fnsco.service.dao.master.trade.TradeByPayTypeDao;
import net.fnsco.service.dao.master.trade.TradeDataDAO;
import net.fnsco.service.dao.master.trade.TradeDateTempDao;
import net.fnsco.service.domain.AppUserMerchant;
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
    
    @Autowired
    private AppUserMerchantDao appUserMerchantDao;
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
    
    /**
     * (non-Javadoc)根据用户ID查询营业额数据
     * @see net.fnsco.api.trade.TradeReportService#queryTurnovers(net.fnsco.api.dto.TradeReportDTO)
     * @author tangliang
     * @date 2017年7月27日 下午3:41:05
     */
    @Override
    public List<TurnoverDTO> queryTurnovers(TradeReportDTO tradeReportDTO) {
        
        //返回集合
        List<TurnoverDTO> datas = Lists.newArrayList();
        TradeByDay record = new TradeByDay();
        record.setUserId(tradeReportDTO.getUserId());
        String yesTradeDate = DateUtils.getTimeByDayStr(-1);
        record.setTradeDate(yesTradeDate.substring(0, yesTradeDate.length()-6));
        //昨天营业额
        TurnoverDTO yesterdayTurnover = tradeByDayDao.selectTradeDayDataByTradeDate(record);
        if(null != yesterdayTurnover){
            yesterdayTurnover.setOrderPrice(new BigDecimal(yesterdayTurnover.getTurnover()).divide(new BigDecimal(yesterdayTurnover.getOrderNum())).setScale(2, BigDecimal.ROUND_HALF_UP));
        }else{
            yesterdayTurnover = new TurnoverDTO();
            yesterdayTurnover.setOrderPrice(new BigDecimal(0.00));
            yesterdayTurnover.setOrderNum(0);
            yesterdayTurnover.setTurnover(0l);
        }
        yesterdayTurnover.setWeekLy(false);
        yesterdayTurnover.setWeeklyTime(yesTradeDate.substring(0, yesTradeDate.length()-6));
        datas.add(yesterdayTurnover);
        //上周的营业额
        record.setStartTradeDate(DateUtils.getMondayStr(-1));
        record.setEndTradeDate(DateUtils.getSundayStr(-1));
        record.setTradeDate(null);
        TurnoverDTO lastWeekTurnover = tradeByDayDao.selectTradeDayDataByTradeDate(record);
        if(null != lastWeekTurnover){
            lastWeekTurnover.setOrderPrice(new BigDecimal(lastWeekTurnover.getTurnover()).divide(new BigDecimal(lastWeekTurnover.getOrderNum())).setScale(2, BigDecimal.ROUND_HALF_UP));
        }else{
            lastWeekTurnover = new TurnoverDTO();
            lastWeekTurnover.setOrderPrice(new BigDecimal(0.00));
            lastWeekTurnover.setOrderNum(0);
            lastWeekTurnover.setTurnover(0l);
        }
        lastWeekTurnover.setWeekLy(false);
        lastWeekTurnover.setWeeklyTime(DateUtils.getMondayStr(-1)+"-"+DateUtils.getSundayStr(-1));
        datas.add(lastWeekTurnover);
        //本日营业额
        record.setTradeDate(DateUtils.getTimeByDayStr(0));
        TurnoverDTO totayTurnover = tradeDataDAO.queryTodayTurnover(record);
        if(null != totayTurnover && null != totayTurnover.getTurnover()){
            totayTurnover.setOrderPrice(new BigDecimal(totayTurnover.getTurnover()).divide(new BigDecimal(totayTurnover.getOrderNum())).setScale(2, BigDecimal.ROUND_HALF_UP));
        }else{
            totayTurnover = new TurnoverDTO();
            totayTurnover.setOrderPrice(new BigDecimal(0.00));
            totayTurnover.setOrderNum(0);
            totayTurnover.setTurnover(0l);
        }
        totayTurnover.setWeekLy(false);
        totayTurnover.setWeeklyTime(DateUtils.getTimeByDayStr(0));
        datas.add(totayTurnover);
        //本周
        String thisMonday = DateUtils.getMondayStr(0);
        String toDay = DateUtils.getTimeByDayStr(0);
        if(toDay.startsWith(thisMonday)){
            totayTurnover.setWeeklyTime(thisMonday);
            datas.add(totayTurnover);
        }else{
            record.setStartTradeDate(thisMonday);
            record.setEndTradeDate(null);
            record.setTradeDate(null);
            TurnoverDTO thisWeekTurnover = tradeByDayDao.selectTradeDayDataByTradeDate(record);
            if(null != thisWeekTurnover){
                thisWeekTurnover.setOrderPrice(new BigDecimal(thisWeekTurnover.getTurnover()).divide(new BigDecimal(thisWeekTurnover.getOrderNum())).setScale(2, BigDecimal.ROUND_HALF_UP));
            }else{
                thisWeekTurnover = new TurnoverDTO();
                thisWeekTurnover.setOrderPrice(new BigDecimal(0.00));
                thisWeekTurnover.setOrderNum(0);
                thisWeekTurnover.setTurnover(0l);
            }
            thisWeekTurnover.setWeekLy(false);
            thisWeekTurnover.setWeeklyTime(DateUtils.getMondayStr(0)+"-"+toDay.substring(0, toDay.length()-6));
            datas.add(thisWeekTurnover);
        }
        //返回首页周报数据
        List<AppUserMerchant> merchants = appUserMerchantDao.selectByPrimaryKey(tradeReportDTO.getUserId(), ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode());
        if(CollectionUtils.isNotEmpty(merchants) ){
            record.setStartTradeDate(DateUtils.getMondayStr(-1));
            record.setEndTradeDate(DateUtils.getSundayStr(-1));
            record.setTradeDate(null);
            record.setRoleId(ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode());
            TurnoverDTO weekLyTurnover = tradeByDayDao.selectWeekLyTradeData(record);
            if(null != weekLyTurnover){
                weekLyTurnover.setOrderPrice(new BigDecimal(weekLyTurnover.getTurnover()).divide(new BigDecimal(weekLyTurnover.getOrderNum())).setScale(2, BigDecimal.ROUND_HALF_UP));
            }else{
                weekLyTurnover = new TurnoverDTO();
                weekLyTurnover.setOrderPrice(new BigDecimal(0.00));
                weekLyTurnover.setOrderNum(0);
                weekLyTurnover.setTurnover(0l);
            }
            weekLyTurnover.setWeekLy(true);
            weekLyTurnover.setWeeklyTime(DateUtils.getMondayStr(-1)+"-"+DateUtils.getSundayStr(-1));
            datas.add(weekLyTurnover);
        }
        return datas;
        
    }
}
