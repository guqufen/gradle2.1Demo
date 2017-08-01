package net.fnsco.service.modules.trade;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import net.fnsco.api.constant.ConstantEnum;
import net.fnsco.api.dto.TradeDayDTO;
import net.fnsco.api.dto.TradeReportDTO;
import net.fnsco.api.dto.TradeTypeDTO;
import net.fnsco.api.dto.TurnoverDTO;
import net.fnsco.api.dto.WeeklyDTO;
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
            yesterdayTurnover.setOrderPrice(divide(yesterdayTurnover.getTurnover(),yesterdayTurnover.getOrderNum()));
        }else{
            yesterdayTurnover = new TurnoverDTO();
            yesterdayTurnover.setOrderPrice(new BigDecimal(0.00));
            yesterdayTurnover.setOrderNum(0);
            yesterdayTurnover.setTurnover(0l);
        }
        yesterdayTurnover.setType(0);
        yesterdayTurnover.setWeekLy(false);
        yesterdayTurnover.setWeeklyTime(yesTradeDate.substring(0, yesTradeDate.length()-6));
        datas.add(yesterdayTurnover);
        //上周的营业额
        record.setStartTradeDate(DateUtils.getMondayStr(-1));
        record.setEndTradeDate(DateUtils.getSundayStr(-1));
        record.setTradeDate(null);
        TurnoverDTO lastWeekTurnover = tradeByDayDao.selectTradeDayDataByTradeDate(record);
        if(null != lastWeekTurnover){
            lastWeekTurnover.setOrderPrice(divide(lastWeekTurnover.getTurnover(),lastWeekTurnover.getOrderNum()));
        }else{
            lastWeekTurnover = new TurnoverDTO();
            lastWeekTurnover.setOrderPrice(new BigDecimal(0.00));
            lastWeekTurnover.setOrderNum(0);
            lastWeekTurnover.setTurnover(0l);
        }
        lastWeekTurnover.setType(1);
        lastWeekTurnover.setWeekLy(false);
        lastWeekTurnover.setWeeklyTime(DateUtils.getMondayStr(-1)+"-"+DateUtils.getSundayStr(-1));
        datas.add(lastWeekTurnover);
        //本日营业额
        record.setTradeDate(DateUtils.getTimeByDayStr(0));
        TurnoverDTO totayTurnover = tradeDataDAO.queryTodayTurnover(record);
        if(null != totayTurnover && null != totayTurnover.getTurnover()){
            totayTurnover.setOrderPrice(divide(totayTurnover.getTurnover(),totayTurnover.getOrderNum()));
        }else{
            totayTurnover = new TurnoverDTO();
            totayTurnover.setOrderPrice(new BigDecimal(0.00));
            totayTurnover.setOrderNum(0);
            totayTurnover.setTurnover(0l);
        }
        totayTurnover.setType(2);
        totayTurnover.setWeekLy(false);
        totayTurnover.setWeeklyTime(DateUtils.getTimeByDayStr(0));
        datas.add(totayTurnover);
        //本周
        String thisMonday = DateUtils.getMondayStr(0);
        String toDay = DateUtils.getTimeByDayStr(0);
        if(toDay.startsWith(thisMonday)){
            totayTurnover.setType(3);
            totayTurnover.setWeeklyTime(thisMonday);
            totayTurnover.setWeeklyTime(DateUtils.getMondayStr(0)+"-"+toDay.substring(0, toDay.length()-6));
            datas.add(totayTurnover);
        }else{
            record.setStartTradeDate(thisMonday);
            record.setEndTradeDate(null);
            record.setTradeDate(null);
            TurnoverDTO thisWeekTurnover = tradeByDayDao.selectTradeDayDataByTradeDate(record);
            if(null != thisWeekTurnover){
                thisWeekTurnover.setOrderPrice(divide(thisWeekTurnover.getTurnover(),thisWeekTurnover.getOrderNum()));
            }else{
                thisWeekTurnover = new TurnoverDTO();
                thisWeekTurnover.setOrderPrice(new BigDecimal(0.00));
                thisWeekTurnover.setOrderNum(0);
                thisWeekTurnover.setTurnover(0l);
            }
            thisWeekTurnover.setWeekLy(false);
            thisWeekTurnover.setType(3);
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
                weekLyTurnover.setOrderPrice(divide(weekLyTurnover.getTurnover(),weekLyTurnover.getOrderNum()));
            }else{
                weekLyTurnover = new TurnoverDTO();
                weekLyTurnover.setOrderPrice(new BigDecimal(0.00));
                weekLyTurnover.setOrderNum(0);
                weekLyTurnover.setTurnover(0l);
            }
            weekLyTurnover.setType(4);
            weekLyTurnover.setWeekLy(true);
            weekLyTurnover.setWeeklyTime(DateUtils.getMondayStr(-1)+"-"+DateUtils.getSundayStr(-1));
            datas.add(weekLyTurnover);
        }
        return datas;
        
    }
    
    /**
     * (non-Javadoc) 查询某商家一段时间内的周报数据
     * @see net.fnsco.api.trade.TradeReportService#queryWeeklyByInnerCode(net.fnsco.api.dto.TradeReportDTO)
     * @author tangliang
     * @date 2017年8月1日 下午1:21:24
     */
    @Override
    public WeeklyDTO queryWeeklyByInnerCode(TradeReportDTO tradeReportDTO) {
        
        TradeByDay record = new TradeByDay();
        record.setInnerCode(tradeReportDTO.getInnerCode());
        record.setStartTradeDate(tradeReportDTO.getStartDate());
        record.setEndTradeDate(tradeReportDTO.getEndDate());
        TurnoverDTO turnover = tradeByDayDao.selectTradeDayDataByTradeDate(record);
        WeeklyDTO resultData = new WeeklyDTO();
        resultData.setInnerCode(tradeReportDTO.getInnerCode());
        resultData.setEndDate(tradeReportDTO.getEndDate());
        resultData.setStartDate(tradeReportDTO.getStartDate());
        resultData.setOrderNum(turnover.getOrderNum());
        resultData.setTurnover(new BigDecimal(turnover.getTurnover()));
        resultData.setOrderPrice(divide(turnover.getTurnover(),turnover.getOrderNum()));
        //返回支付渠道类型数据
        TradeByPayType payType = new TradeByPayType();
        payType.setInnerCode(tradeReportDTO.getInnerCode());
        payType.setStartDate(tradeReportDTO.getStartDate());
        payType.setEndDate(tradeReportDTO.getEndDate());
        List<TradeTypeDTO> tradeTypeData = tradeByPayTypeDao.selectTradeDataByInnerCode(payType);
        resultData.setTradeTypeData(tradeTypeData);
        //按照天返回数据
        List<TradeDayDTO> tradeDayData = tradeByDayDao.selectByInnerCode(record);
        resultData.setTradeDayData(tradeDayData);
        return resultData;
    }
    /**
     * divide:(这里用一句话描述这个方法的作用)精度为2位小数的除法
     *
     * @param turnover
     * @param orderNum
     * @return    设定文件
     * @return BigDecimal    DOM对象
     * @throws 
     * @author tangliang
     * @since  CodingExample　Ver 1.1
     */
    private BigDecimal divide(Long turnover,Integer orderNum){
        BigDecimal bd1 = new BigDecimal(turnover);
        BigDecimal bd2 = new BigDecimal(orderNum);
        return bd1.divide(bd2, 2, BigDecimal.ROUND_HALF_UP);
    }
}
