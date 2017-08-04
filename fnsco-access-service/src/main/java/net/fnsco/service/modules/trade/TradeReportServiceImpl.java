package net.fnsco.service.modules.trade;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import net.fnsco.api.constant.ConstantEnum;
import net.fnsco.api.dto.BusinessTrendDTO;
import net.fnsco.api.dto.ConsumptionDTO;
import net.fnsco.api.dto.DateDTO;
import net.fnsco.api.dto.PeakTradeDTO;
import net.fnsco.api.dto.TradeDayDTO;
import net.fnsco.api.dto.TradeHourDTO;
import net.fnsco.api.dto.TradeReportDTO;
import net.fnsco.api.dto.TradeTurnoverDTO;
import net.fnsco.api.dto.TradeTypeDTO;
import net.fnsco.api.dto.TurnoverDTO;
import net.fnsco.api.dto.WeeklyDTO;
import net.fnsco.api.dto.WeeklyHisDateDTO;
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
    
    private final static int pageSize = 20;
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
    public TradeTurnoverDTO queryTurnovers(TradeReportDTO tradeReportDTO) {
        
        //返回集合
        TradeTurnoverDTO result = new TradeTurnoverDTO();
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
        String yesterdayStr = DateUtils.formatDateStrOutput(yesTradeDate.substring(0, yesTradeDate.length()-6));
        yesterdayTurnover.setStartDate(yesterdayStr);
        yesterdayTurnover.setEndDate(yesterdayStr);;
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
        lastWeekTurnover.setStartDate(DateUtils.formatDateStrOutput(DateUtils.getMondayStr(-1)));
        lastWeekTurnover.setEndDate(DateUtils.formatDateStrOutput(DateUtils.getSundayStr(-1)));
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
        String totayDate = DateUtils.getTimeByDayStr(0).substring(0,8);
        totayTurnover.setStartDate(DateUtils.formatDateStrOutput(totayDate));
        totayTurnover.setEndDate(DateUtils.formatDateStrOutput(totayDate));
        datas.add(totayTurnover);
        //本周
        String thisMonday = DateUtils.getMondayStr(0);
        String toDay = DateUtils.getTimeByDayStr(0).substring(0, 8);
        if(toDay.startsWith(thisMonday)){
            totayTurnover.setType(3);
            totayTurnover.setStartDate(DateUtils.formatDateStrOutput(thisMonday));
            totayTurnover.setEndDate(DateUtils.formatDateStrOutput(toDay));
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
            thisWeekTurnover.setStartDate(DateUtils.formatDateStrOutput(DateUtils.getMondayStr(0)));
            thisWeekTurnover.setEndDate(DateUtils.formatDateStrOutput(toDay));
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
            weekLyTurnover.setStartDate(DateUtils.formatDateStrOutput(DateUtils.getMondayStr(-1)));
            weekLyTurnover.setEndDate(DateUtils.formatDateStrOutput(DateUtils.getSundayStr(-1)));
            datas.add(weekLyTurnover);
        }
        result.setTurnovers(datas);
        
        return result;
        
    }
    
    /**
     * (non-Javadoc) 查询某商家一段时间内的周报数据
     * @see net.fnsco.api.trade.TradeReportService#queryWeeklyByInnerCode(net.fnsco.api.dto.TradeReportDTO)
     * @author tangliang
     * @date 2017年8月1日 下午1:21:24
     */
    @Override
    public WeeklyDTO queryWeeklyByInnerCode(TradeReportDTO tradeReportDTO) {
        
        //如果没有传递时间和商家，则默认上周和全部商户数据
        if(Strings.isNullOrEmpty(tradeReportDTO.getStartDate()) || Strings.isNullOrEmpty(tradeReportDTO.getEndDate())){
            tradeReportDTO.setStartDate(DateUtils.getMondayStr(-1));
            tradeReportDTO.setEndDate(DateUtils.getSundayStr(-1));
        }
        
        TradeByDay record = new TradeByDay();
        record.setInnerCode(tradeReportDTO.getInnerCode());
        record.setStartTradeDate(tradeReportDTO.getStartDate());
        record.setEndTradeDate(tradeReportDTO.getEndDate());
        record.setUserId(tradeReportDTO.getUserId());
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
        payType.setUserId(tradeReportDTO.getUserId());
        payType.setRoleId(ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode());
        List<TradeTypeDTO> tradeTypeData = tradeByPayTypeDao.selectTradeDataByInnerCode(payType);
        //如果数据为空 需要填充数据
        if(tradeTypeData.size()<3){
            List<String> tempParam = Lists.newArrayList();
            tempParam.add(ConstantEnum.PayTypeEnum.PAYBYCARD.getCode());
            tempParam.add(ConstantEnum.PayTypeEnum.PAYBYWX.getCode());
            tempParam.add(ConstantEnum.PayTypeEnum.PAYBYALIPAY.getCode());
            for (TradeTypeDTO tradeTypeDTO : tradeTypeData) {
                tempParam.remove(tradeTypeDTO.getPayType());
            }
            for (String param : tempParam) {
                TradeTypeDTO tempDTO = new TradeTypeDTO();
                tempDTO.setPayType(param);
                tempDTO.setOrderNum(0);
                tempDTO.setTurnover(new BigDecimal(0.00));
                tradeTypeData.add(tempDTO);
            }
        }
        resultData.setTradeTypeData(tradeTypeData);
        //按照天返回数据
        List<TradeDayDTO> tradeDayData = tradeByDayDao.selectByInnerCode(record);
        //按照天为组,如果数据为空，需要填充数据
        List<TradeDayDTO> data = installTradeDay(tradeReportDTO,tradeDayData);
        resultData.setTradeDayData(data);
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
    
    /**
     * (non-Javadoc)查询周报历史时间段
     * @see net.fnsco.api.trade.TradeReportService#queryWeeklyHisDate(net.fnsco.api.dto.TradeReportDTO)
     * @author tangliang
     * @date 2017年8月2日 上午9:22:25
     */
    @Override
    public WeeklyHisDateDTO queryWeeklyHisDate(TradeReportDTO tradeReportDTO) {
       
       WeeklyHisDateDTO datas = new WeeklyHisDateDTO();
       String minDate = tradeByDayDao.selectMinTradeDateByUserId(tradeReportDTO.getUserId(), ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode());
       if(Strings.isNullOrEmpty(minDate)){
           datas.setCurrentPageNum(tradeReportDTO.getPageNum());
           datas.setTotalPageNum(0);
           return datas;
       }
       List<DateDTO> data = countTradeHisDate(minDate);
       datas.setCurrentPageNum(tradeReportDTO.getPageNum());
       datas.setTotalPageNum(data.size());
       //分页处理
       Integer pageNum = tradeReportDTO.getPageNum();
      //不存在的页码
       if(data.size() <= (pageNum-1) * pageSize){
           return null;
       }
       //当现有长度大于分页要的数据
       if(data.size() > pageNum * pageSize){
           data = data.subList((pageNum-1) * pageSize, pageNum * pageSize);
       }
       
       if(data.size() <= pageNum * pageSize){
           data = data.subList((pageNum-1) * pageSize, data.size());
       }
       
       datas.setHisDate(data);
       return datas;
        
    }
    
    /**
     * countTradeHisDate:(这里用一句话描述这个方法的作用)计算日期间隔周期，且将周期封装返回
     *
     * @param minDateStr
     * @return    设定文件
     * @return List<DateDTO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    private static List<DateDTO> countTradeHisDate(String minDateStr){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        List<DateDTO> dates = Lists.newArrayList();
        try {
            Date startDate=format.parse(minDateStr);
            start.setTime(startDate); 
            /**
             * 开始时间为产生数据的一周的周一开始，以自然周开始
             */
            while(start.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY){
                start.set(Calendar.DATE, start.get(Calendar.DATE) - 1);
            }
            
            DateDTO date = null;
            while(end.compareTo(start) >= 0) {
                int w = end.get(Calendar.DAY_OF_WEEK);
                String dateTime = format.format(end.getTime());
                if(w == Calendar.MONDAY){
                    if(date != null){
                        date.setStartDate(dateTime);
                        dates.add(date);
                    }
                }
                if(w == Calendar.SUNDAY){
                    date = new DateDTO();
                    date.setEndDate(dateTime);
                }
                //循环，每次天数加1
                end.set(Calendar.DATE, end.get(Calendar.DATE) - 1);
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dates;
    }
    /**
     * installTradeDay:(这里用一句话描述这个方法的作用)填充数据
     *
     * @param tradeReportDTO
     * @param tradeDayData
     * @return    设定文件
     * @return List<TradeDayDTO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    private List<TradeDayDTO> installTradeDay(TradeReportDTO tradeReportDTO,List<TradeDayDTO> tradeDayData){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        List<TradeDayDTO> datas = Lists.newArrayList();
        Date startDate;
        try {
            startDate = format.parse(tradeReportDTO.getStartDate());
            start.setTime(startDate); 
            /**
             * 不够一周数据的、填充数据
             */
            Date endDate  = format.parse(tradeReportDTO.getEndDate());
            end.setTime(endDate);
            boolean flag = true;
            while(end.compareTo(start) >= 0) {
                flag = true;
                String dateTime = format.format(end.getTime());
                for (TradeDayDTO tradeDayDTO : tradeDayData) {
                    if(dateTime.equals(tradeDayDTO.getTradeDate())){
                        datas.add(tradeDayDTO);
                        flag = false;
                    }
                }
                if(flag){
                    TradeDayDTO tempDto = new TradeDayDTO();
                    tempDto.setTradeDate(dateTime);
                    tempDto.setOrderNum(0);
                    tempDto.setTurnover(new BigDecimal(0.00));
                    datas.add(tempDto);
                }
               
                //循环，每次天数加1
                end.set(Calendar.DATE, end.get(Calendar.DATE) - 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return datas;
    }
    
    /**
     * (non-Javadoc)查询消费模式
     * @see net.fnsco.api.trade.TradeReportService#queryTrandPeak(net.fnsco.api.dto.TradeReportDTO)
     * @author tangliang
     * @date 2017年8月3日 下午2:48:48
     */
    @Override
    public ConsumptionDTO queryConsumption(TradeReportDTO tradeReportDTO) {
        
      //如果没有传递时间和商家，则默认上周和全部商户数据
        if(Strings.isNullOrEmpty(tradeReportDTO.getStartDate()) || Strings.isNullOrEmpty(tradeReportDTO.getEndDate())){
            tradeReportDTO.setStartDate(DateUtils.getMondayStr(-1));
            tradeReportDTO.setEndDate(DateUtils.getSundayStr(-1));
        }
        ConsumptionDTO datas = new ConsumptionDTO();
        TradeByPayType payType = new TradeByPayType();
        payType.setInnerCode(tradeReportDTO.getInnerCode());
        payType.setStartDate(tradeReportDTO.getStartDate());
        payType.setEndDate(tradeReportDTO.getEndDate());
        payType.setUserId(tradeReportDTO.getUserId());
        payType.setRoleId(ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode());
        List<TradeTypeDTO> tradeTypeData = tradeByPayTypeDao.selectTradeDataByInnerCode(payType);
        //如果数据为空 需要填充数据
        if(tradeTypeData.size()<3){
            List<String> tempParam = Lists.newArrayList();
            tempParam.add(ConstantEnum.PayTypeEnum.PAYBYCARD.getCode());
            tempParam.add(ConstantEnum.PayTypeEnum.PAYBYWX.getCode());
            tempParam.add(ConstantEnum.PayTypeEnum.PAYBYALIPAY.getCode());
            for (TradeTypeDTO tradeTypeDTO : tradeTypeData) {
                tempParam.remove(tradeTypeDTO.getPayType());
            }
            for (String param : tempParam) {
                TradeTypeDTO tempDTO = new TradeTypeDTO();
                tempDTO.setPayType(param);
                tempDTO.setOrderNum(0);
                tempDTO.setTurnover(new BigDecimal(0.00));
                tradeTypeData.add(tempDTO);
            }
        }
        
        int orderNumTotal = 0;
        BigDecimal turnoverTotal = new BigDecimal(0.00);
        for (TradeTypeDTO tradeTypeDTO : tradeTypeData) {
            orderNumTotal += tradeTypeDTO.getOrderNum();
            turnoverTotal = turnoverTotal.add(tradeTypeDTO.getTurnover());
        }
        datas.setOrderNumTotal(orderNumTotal);
        datas.setTurnoverTotal(turnoverTotal);
        datas.setTradeTypeData(tradeTypeData);
        return datas;
        
    }
    
    /**
     * (non-Javadoc)查询经营趋势的数据
     * @see net.fnsco.api.trade.TradeReportService#queryBusinessTrends(net.fnsco.api.dto.TradeReportDTO)
     * @author tangliang
     * @date 2017年8月3日 下午4:41:46
     */
    @Override
    public BusinessTrendDTO queryBusinessTrends(TradeReportDTO tradeReportDTO) {
        
      //如果没有传递时间和商家，则默认上周和全部商户数据
        if(Strings.isNullOrEmpty(tradeReportDTO.getStartDate()) || Strings.isNullOrEmpty(tradeReportDTO.getEndDate())){
            tradeReportDTO.setStartDate(DateUtils.getMondayStr(-1));
            tradeReportDTO.setEndDate(DateUtils.getSundayStr(-1));
        }
        BusinessTrendDTO datas = new BusinessTrendDTO();
        TradeByDay record = new TradeByDay();
        record.setInnerCode(tradeReportDTO.getInnerCode());
        record.setStartTradeDate(tradeReportDTO.getStartDate());
        record.setEndTradeDate(tradeReportDTO.getEndDate());
        record.setUserId(tradeReportDTO.getUserId());
        List<TradeDayDTO> tradeDayData = tradeByDayDao.selectByInnerCode(record);
        List<TradeDayDTO> data = installTradeDay(tradeReportDTO,tradeDayData);
        int orderNumTotal = 0;
        BigDecimal turnoverTotal = new BigDecimal(0.00);
        for (TradeDayDTO tradeDayDTO : data) {
            orderNumTotal += tradeDayDTO.getOrderNum();
            turnoverTotal = turnoverTotal.add(tradeDayDTO.getTurnover());
        }
        //设置返回数据
        datas.setOrderNumTotal(orderNumTotal);
        datas.setTurnoverTotal(turnoverTotal);
        datas.setOrderPrice(turnoverTotal.divide(new BigDecimal(orderNumTotal), 2, BigDecimal.ROUND_HALF_UP));
        datas.setTradeDayData(data);
        return datas;
        
    }
    
    /**
     * (non-Javadoc)查询交易峰值
     * @see net.fnsco.api.trade.TradeReportService#queryPeakTrade(net.fnsco.api.dto.TradeReportDTO)
     * @author tangliang
     * @date 2017年8月3日 下午5:34:19
     */
    @Override
    public PeakTradeDTO queryPeakTrade(TradeReportDTO tradeReportDTO) {
        
      //如果没有传递时间和商家，则默认上周和全部商户数据
        if(Strings.isNullOrEmpty(tradeReportDTO.getStartDate()) || Strings.isNullOrEmpty(tradeReportDTO.getEndDate())){
            tradeReportDTO.setStartDate(DateUtils.getMondayStr(-1));
            tradeReportDTO.setEndDate(DateUtils.getSundayStr(-1));
        }
        TradeByHour record = new TradeByHour();
        record.setStartTradeDate(tradeReportDTO.getStartDate());
        record.setEndTradeDate(tradeReportDTO.getEndDate());
        record.setUserId(tradeReportDTO.getUserId());
        List<TradeHourDTO> tradeHourData = tradeByHourDao.selectByCondition(record);
        PeakTradeDTO data = new PeakTradeDTO();
        int orderNumTotal = 0;
        BigDecimal turnoverTotal = new BigDecimal(0.00);
        for (TradeHourDTO tradeHourDTO : tradeHourData) {
            orderNumTotal += tradeHourDTO.getOrderNum();
            turnoverTotal = turnoverTotal.add(tradeHourDTO.getTurnover());
        }
        data.setOrderNumTotal(orderNumTotal);
        data.setTurnoverTotal(turnoverTotal);
        //如果没有数据填充数据
        List<TradeHourDTO> datas = installTradeHour(tradeReportDTO,tradeHourData);
        data.setTradeHourdata(datas);
        return data;
        
    }
    
    /**
     * installTradeHour:(这里用一句话描述这个方法的作用)填充数据
     *
     * @param tradeReportDTO
     * @param tradeDayData
     * @return    设定文件
     * @return List<TradeHourDTO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    private List<TradeHourDTO> installTradeHour(TradeReportDTO tradeReportDTO,List<TradeHourDTO> tradeDayHour){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd hh");
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        Date startDate;
        Date endDate;
        List<TradeHourDTO> datas = Lists.newArrayList();
        try {
            startDate = format.parse(tradeReportDTO.getStartDate()+" 00");
            start.setTime(startDate);
            endDate = format.parse(tradeReportDTO.getEndDate()+" 23");
            end.setTime(endDate);
            boolean flag = true;
            while (end.compareTo(start) >= 0) {
                flag = true;
                String dateTime = format.format(end.getTime());
                int hour = end.get(Calendar.HOUR_OF_DAY);
                for (TradeHourDTO tradeHourDTO : tradeDayHour) {
                    if(dateTime.startsWith(tradeHourDTO.getTradeDate()) && hour ==Integer.valueOf(tradeHourDTO.getTradeHour())){
                        datas.add(tradeHourDTO);
                        flag = false;
                    }
                }
                if(flag){
                    TradeHourDTO temp = new TradeHourDTO();
                    temp.setOrderNum(0);
                    String tradeDate = dateTime.substring(0, 8);
                    temp.setTradeDate(DateUtils.formatDateStrOutput(tradeDate));
                    temp.setTradeHour(String.valueOf(hour));
                    temp.setTurnover(new BigDecimal(0.00));
                    datas.add(temp);
                }
              //循环，每次天数加1
                end.set(Calendar.HOUR_OF_DAY, end.get(Calendar.HOUR_OF_DAY) - 1);
            }
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
       
        return datas;
    }
}
