package net.fnsco.order.service.modules.trade;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.order.api.constant.ConstantEnum;
import net.fnsco.order.api.dto.BusinessTrendDTO;
import net.fnsco.order.api.dto.ConsPatternDTO;
import net.fnsco.order.api.dto.DateDTO;
import net.fnsco.order.api.dto.PeakTradeDTO;
import net.fnsco.order.api.dto.TradeDayDTO;
import net.fnsco.order.api.dto.TradeHourDTO;
import net.fnsco.order.api.dto.TradeReportDTO;
import net.fnsco.order.api.dto.TradeTurnoverDTO;
import net.fnsco.order.api.dto.TradeTypeDTO;
import net.fnsco.order.api.dto.TurnoverDTO;
import net.fnsco.order.api.dto.WeeklyDTO;
import net.fnsco.order.api.dto.WeeklyHisDateDTO;
import net.fnsco.order.api.trade.TradeReportService;
import net.fnsco.order.service.dao.master.AppUserMerchantDao;
import net.fnsco.order.service.dao.master.trade.TradeByDayDao;
import net.fnsco.order.service.dao.master.trade.TradeByHourDao;
import net.fnsco.order.service.dao.master.trade.TradeByPayTypeDao;
import net.fnsco.order.service.dao.master.trade.TradeDataDAO;
import net.fnsco.order.service.dao.master.trade.TradeDateTempDao;
import net.fnsco.order.service.domain.AppUserMerchant;
import net.fnsco.order.service.domain.trade.TradeByDay;
import net.fnsco.order.service.domain.trade.TradeByHour;
import net.fnsco.order.service.domain.trade.TradeByPayType;
import net.fnsco.order.service.domain.trade.TradeData;
import net.fnsco.order.service.domain.trade.TradeDateTemp;

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

    private final static int   pageSize = 20;

    /**
     * (non-Javadoc)生成统计数据 入参时间格式 yyyyMMddHHmmdd
     * @see net.fnsco.order.api.trade.TradeReportService#buildTradeReportDaTa()
     * @author tangliang
     * @date 2017年7月27日 上午11:07:01  
     */
    @Transactional
    @Override
    public void buildTradeReportDaTa(String startTime,String endTime) {

        //首先清空临时表
        tradeDateTempDao.deleteAll();
        //查询最新交易流水数据插入临时表
        TradeData record = new TradeData();
        record.setStartTime(startTime);
        record.setEndTime(endTime);
        String startDate = startTime.substring(0, 8);
        List<TradeDateTemp> tempDatas = tradeDataDAO.queryTempByCondition(record);
        String tradeDateStr = DateUtils.getTimeByDayStr(-1);
        for (TradeDateTemp tradeDateTemp : tempDatas) {
            String timeStamp = tradeDateTemp.getTimeStamp();
            tradeDateTemp.setTradeDate(timeStamp.substring(0, tradeDateStr.length() - 6));
            tradeDateTemp.setTradeHoure(timeStamp.substring(8, 10));
            tradeDateTempDao.insertSelective(tradeDateTemp);
        }
        //统计之前先删除，防止重复统计
        TradeByDay dayCondition = new TradeByDay();
        dayCondition.setTradeDate(startDate);
        tradeByDayDao.deleteByDate(dayCondition);
        TradeByHour hourCondition = new TradeByHour();
        hourCondition.setTradeDate(startDate);
        tradeByHourDao.deleteByCondition(hourCondition);
        TradeByPayType payTypeCondition = new TradeByPayType();
        payTypeCondition.setStartDate(startDate);
        tradeByPayTypeDao.deleteByCondition(payTypeCondition);
        
        //分别按小时、天、支付渠道统计查询且插入对应表中
        List<TradeByDay> tradeDayData = tradeDateTempDao.selectTradeDataByDate();
        for (TradeByDay tradeByDay : tradeDayData) {
            tradeByDay.setTradeDate(tradeDateStr.substring(0, tradeDateStr.length() - 6));
            tradeByDayDao.insertSelective(tradeByDay);
        }

        List<TradeByHour> tradeHourData = tradeDateTempDao.selectTradeDataByHour();
        for (TradeByHour tradeByHour : tradeHourData) {
            tradeByHour.setTradeDate(tradeDateStr.substring(0, tradeDateStr.length() - 6));
            tradeByHourDao.insertSelective(tradeByHour);
        }

        List<TradeByPayType> tradePayTypeData = tradeDateTempDao.selectTradeDataByPayType();
        for (TradeByPayType tradeByPayType : tradePayTypeData) {
            tradeByPayType.setTradeDate(tradeDateStr.substring(0, tradeDateStr.length() - 6));
            tradeByPayTypeDao.insert(tradeByPayType);
        }
    }

    /**
     * (non-Javadoc)根据用户ID查询营业额数据
     * @see net.fnsco.order.api.trade.TradeReportService#queryTurnovers(net.fnsco.order.api.dto.TradeReportDTO)
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
        record.setTradeDate(yesTradeDate.substring(0, yesTradeDate.length() - 6));
        //昨天营业额
        TurnoverDTO yesterdayTurnover = tradeByDayDao.selectTradeDayDataByTradeDate(record);
        if (null != yesterdayTurnover) {
            yesterdayTurnover.setOrderPrice(divide(yesterdayTurnover.getTurnover(), yesterdayTurnover.getOrderNum()));
        } else {
            yesterdayTurnover = new TurnoverDTO();
            yesterdayTurnover.setOrderPrice(new BigDecimal(0.00));
            yesterdayTurnover.setOrderNum(0);
            yesterdayTurnover.setTurnover(0l);
        }
        yesterdayTurnover.setType(0);
        yesterdayTurnover.setWeekLy(false);
        String yesterdayStr = DateUtils.formatDateStrOutput(yesTradeDate.substring(0, yesTradeDate.length() - 6));
        yesterdayTurnover.setStartDate(yesterdayStr);
        yesterdayTurnover.setEndDate(yesterdayStr);
        datas.add(yesterdayTurnover);
        //上周的营业额
        record.setStartTradeDate(DateUtils.getMondayStr(-1));
        record.setEndTradeDate(DateUtils.getSundayStr(-1));
        record.setTradeDate(null);
        TurnoverDTO lastWeekTurnover = tradeByDayDao.selectTradeDayDataByTradeDate(record);
        if (null != lastWeekTurnover) {
            lastWeekTurnover.setOrderPrice(divide(lastWeekTurnover.getTurnover(), lastWeekTurnover.getOrderNum()));
        } else {
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
        if (null != totayTurnover && null != totayTurnover.getTurnover()) {
            totayTurnover.setOrderPrice(divide(totayTurnover.getTurnover(), totayTurnover.getOrderNum()));
        } else {
            totayTurnover = new TurnoverDTO();
            totayTurnover.setOrderPrice(new BigDecimal(0.00));
            totayTurnover.setOrderNum(0);
            totayTurnover.setTurnover(0l);
        }
        totayTurnover.setType(2);
        totayTurnover.setWeekLy(false);
        String totayDate = DateUtils.getTimeByDayStr(0).substring(0, 8);
        totayTurnover.setStartDate(DateUtils.formatDateStrOutput(totayDate));
        totayTurnover.setEndDate(DateUtils.formatDateStrOutput(totayDate));
        datas.add(totayTurnover);
        //本周
        String thisMonday = DateUtils.getMondayStr(0);
        String toDay = DateUtils.getTimeByDayStr(0).substring(0, 8);
        if (toDay.startsWith(thisMonday)) {
            totayTurnover.setType(3);
            totayTurnover.setStartDate(DateUtils.formatDateStrOutput(thisMonday));
            totayTurnover.setEndDate(DateUtils.formatDateStrOutput(toDay));
            datas.add(totayTurnover);
        } else {
            record.setStartTradeDate(thisMonday);
            record.setEndTradeDate(null);
            record.setTradeDate(null);
            TurnoverDTO thisWeekTurnover = tradeByDayDao.selectTradeDayDataByTradeDate(record);
            if (null != thisWeekTurnover) {
                thisWeekTurnover.setOrderPrice(divide(thisWeekTurnover.getTurnover(), thisWeekTurnover.getOrderNum()));
            } else {
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
        if (CollectionUtils.isNotEmpty(merchants)) {
            record.setStartTradeDate(DateUtils.getMondayStr(-1));
            record.setEndTradeDate(DateUtils.getSundayStr(-1));
            record.setTradeDate(null);
            record.setRoleId(ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode());
            TurnoverDTO weekLyTurnover = tradeByDayDao.selectWeekLyTradeData(record);
            String minDate = tradeByDayDao.selectMinTradeDateByUserId(tradeReportDTO.getUserId(), ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode());

            if (null != weekLyTurnover) {
                weekLyTurnover.setOrderPrice(divide(weekLyTurnover.getTurnover(), weekLyTurnover.getOrderNum()));
            } else if (!Strings.isNullOrEmpty(minDate) && minDate.compareTo(record.getEndTradeDate()) <= 0) {
                weekLyTurnover = new TurnoverDTO();
                weekLyTurnover.setOrderPrice(new BigDecimal(0.00));
                weekLyTurnover.setOrderNum(0);
                weekLyTurnover.setTurnover(0l);
            } else {
                result.setTurnovers(datas);
                return result;
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
     * @see net.fnsco.order.api.trade.TradeReportService#queryWeeklyByInnerCode(net.fnsco.order.api.dto.TradeReportDTO)
     * @author tangliang
     * @date 2017年8月1日 下午1:21:24
     */
    @Override
    public WeeklyDTO queryWeeklyByInnerCode(TradeReportDTO tradeReportDTO) {

        formatInputDate(tradeReportDTO, true);//格式化入参
        TradeByDay record = new TradeByDay();
        record.setInnerCode(tradeReportDTO.getInnerCode());
        record.setStartTradeDate(tradeReportDTO.getStartDate());
        record.setEndTradeDate(tradeReportDTO.getEndDate());
        record.setUserId(tradeReportDTO.getUserId());
        TurnoverDTO turnover = tradeByDayDao.selectTradeDayDataByTradeDate(record);
        WeeklyDTO resultData = new WeeklyDTO();
        resultData.setInnerCode(tradeReportDTO.getInnerCode());
        resultData.setEndDate(DateUtils.formatDateStrOutput(tradeReportDTO.getEndDate()));
        resultData.setStartDate(DateUtils.formatDateStrOutput(tradeReportDTO.getStartDate()));
        if(null == turnover){
            resultData.setOrderNum(0);
            resultData.setTurnover(new BigDecimal(0.00));
            resultData.setOrderPrice(new BigDecimal(0.00));
        }else{
            resultData.setOrderNum(turnover.getOrderNum());
            resultData.setTurnover(new BigDecimal(turnover.getTurnover()));
            resultData.setOrderPrice(divide(turnover.getTurnover(), turnover.getOrderNum()));
        }
        
        //返回支付渠道类型数据
        TradeByPayType payType = new TradeByPayType();
        payType.setInnerCode(tradeReportDTO.getInnerCode());
        payType.setStartDate(tradeReportDTO.getStartDate());
        payType.setEndDate(tradeReportDTO.getEndDate());
        payType.setUserId(tradeReportDTO.getUserId());
        payType.setRoleId(ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode());
        List<TradeTypeDTO> tradeTypeData = tradeByPayTypeDao.selectTradeDataByInnerCode(payType);
        //如果数据为空 需要填充数据
        if (null == tradeTypeData ||  tradeTypeData.size() < 3) {
            List<String> tempParam = Lists.newArrayList();
            if(null == tradeTypeData){
                tradeTypeData = Lists.newArrayList();
            }
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
        List<TradeDayDTO> data = installTradeDay(tradeReportDTO, tradeDayData);
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
    private BigDecimal divide(Long turnover, Integer orderNum) {
        BigDecimal bd1 = new BigDecimal(turnover);
        BigDecimal bd2 = new BigDecimal(orderNum);
        return bd1.divide(bd2, 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * formatInputDate:(这里用一句话描述这个方法的作用)格式化输入日期参数
     *
     * @param tradeReportDTO
     * @return    设定文件
     * @return TradeReportDTO    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    private TradeReportDTO formatInputDate(TradeReportDTO tradeReportDTO, boolean isWeekly) {

        if (!Strings.isNullOrEmpty(tradeReportDTO.getStartDate())) {
            tradeReportDTO.setStartDate(DateUtils.formatDateStrInput(tradeReportDTO.getStartDate()));
        }
        if (!Strings.isNullOrEmpty(tradeReportDTO.getEndDate())) {
            tradeReportDTO.setEndDate(DateUtils.formatDateStrInput(tradeReportDTO.getEndDate()));
        }
        //如果没有传递时间和商家，则默认上周和全部商户数据
        if ((Strings.isNullOrEmpty(tradeReportDTO.getStartDate()) || Strings.isNullOrEmpty(tradeReportDTO.getEndDate())) && isWeekly) {
            tradeReportDTO.setStartDate(DateUtils.getMondayStr(-1));
            tradeReportDTO.setEndDate(DateUtils.getSundayStr(-1));
        }
        if (!isWeekly && (Strings.isNullOrEmpty(tradeReportDTO.getStartDate()) || Strings.isNullOrEmpty(tradeReportDTO.getEndDate()))) {
            tradeReportDTO.setEndDate(DateUtils.getDateStrByDay(-1));
            tradeReportDTO.setStartDate(DateUtils.getDateStrByDay(-7));
        }
        return tradeReportDTO;
    }

    /**
     * (non-Javadoc)查询周报历史时间段
     * @see net.fnsco.order.api.trade.TradeReportService#queryWeeklyHisDate(net.fnsco.order.api.dto.TradeReportDTO)
     * @author tangliang
     * @date 2017年8月2日 上午9:22:25
     */
    @Override
    public WeeklyHisDateDTO queryWeeklyHisDate(TradeReportDTO tradeReportDTO) {

        WeeklyHisDateDTO datas = new WeeklyHisDateDTO();
        String minDate = tradeByDayDao.selectMinTradeDateByUserId(tradeReportDTO.getUserId(), ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode());
        if (Strings.isNullOrEmpty(minDate)) {
            datas.setCurrentPageNum(tradeReportDTO.getPageNum());
            datas.setTotalPageNum(0);
            return datas;
        }
        List<DateDTO> data = countTradeHisDate(minDate);
        datas.setCurrentPageNum(tradeReportDTO.getPageNum());
        if (data.size() % pageSize == 0) {
            datas.setTotalPageNum(data.size() / pageSize);
        } else {
            datas.setTotalPageNum(data.size() / pageSize + 1);
        }

        //分页处理
        Integer pageNum = tradeReportDTO.getPageNum();
        //不存在的页码
        if (data.size() <= (pageNum - 1) * pageSize) {
            return null;
        }
        //当现有长度大于分页要的数据
        if (data.size() > pageNum * pageSize) {
            data = data.subList((pageNum - 1) * pageSize, pageNum * pageSize);
        }

        if (data.size() <= pageNum * pageSize) {
            data = data.subList((pageNum - 1) * pageSize, data.size());
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
    private static List<DateDTO> countTradeHisDate(String minDateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        List<DateDTO> dates = Lists.newArrayList();
        try {
            Date startDate = format.parse(minDateStr);
            start.setTime(startDate);
            /**
             * 开始时间为产生数据的一周的周一开始，以自然周开始
             */
            while (start.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                start.set(Calendar.DATE, start.get(Calendar.DATE) - 1);
            }

            DateDTO date = null;
            while (end.compareTo(start) >= 0) {
                int w = end.get(Calendar.DAY_OF_WEEK);
                String dateTime = format1.format(end.getTime());
                if (w == Calendar.MONDAY) {
                    if (date != null) {
                        date.setStartDate(dateTime);
                        dates.add(date);
                    }
                }
                if (w == Calendar.SUNDAY) {
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
    private List<TradeDayDTO> installTradeDay(TradeReportDTO tradeReportDTO, List<TradeDayDTO> tradeDayData) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
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
            Date endDate = format.parse(tradeReportDTO.getEndDate());
            end.setTime(endDate);
            boolean flag = true;
            while (end.compareTo(start) >= 0) {
                flag = true;
                String dateTime = format.format(end.getTime());
                for (TradeDayDTO tradeDayDTO : tradeDayData) {
                    if (dateTime.equals(tradeDayDTO.getTradeDate())) {
                        tradeDayDTO.setTradeDate(format1.format(end.getTime()));
                        datas.add(tradeDayDTO);
                        flag = false;
                    }
                }
                if (flag) {
                    TradeDayDTO tempDto = new TradeDayDTO();
                    tempDto.setTradeDate(format1.format(end.getTime()));
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
        //反着排序
        Collections.reverse(datas);
        return datas;
    }

    /**
     * (non-Javadoc)查询消费模式
     * @see net.fnsco.order.api.trade.TradeReportService#queryTrandPeak(net.fnsco.order.api.dto.TradeReportDTO)
     * @author tangliang
     * @date 2017年8月3日 下午2:48:48
     */
    @Override
    public ConsPatternDTO queryConsumption(TradeReportDTO tradeReportDTO) {

        formatInputDate(tradeReportDTO, false);//格式化入参
        ConsPatternDTO datas = new ConsPatternDTO();
        datas.setBankOrderNumTot(0);
        datas.setBankTurnoverTot(new BigDecimal("0"));
        datas.setWxOrderNumTot(0);
        datas.setWxTurnoverTot(new BigDecimal("0"));
        datas.setAliOrderNumTot(0);
        datas.setAliTurnoverTot(new BigDecimal("0"));
        datas.setOtherOrderNumTot(0);
        datas.setOtherTurnoverTot(new BigDecimal("0"));
        TradeByPayType payType = new TradeByPayType();
        payType.setInnerCode(tradeReportDTO.getInnerCode());
        payType.setStartDate(tradeReportDTO.getStartDate());
        payType.setEndDate(tradeReportDTO.getEndDate());
        payType.setUserId(tradeReportDTO.getUserId());
        payType.setRoleId(ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode());
        List<TradeTypeDTO> tradeTypeData = tradeByPayTypeDao.selectTradeDataByInnerCode(payType);
        //如果数据为空 需要填充数据
        if (tradeTypeData.size() < 3) {
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
            if(ConstantEnum.PayTypeEnum.PAYBYCARD.getCode().equals(tradeTypeDTO.getPayType())){
                datas.setBankOrderNumTot(tradeTypeDTO.getOrderNum());
                datas.setBankTurnoverTot(tradeTypeDTO.getTurnover());
            }else if (ConstantEnum.PayTypeEnum.PAYBYWX.getCode().equals(tradeTypeDTO.getPayType())){
                datas.setWxOrderNumTot(tradeTypeDTO.getOrderNum());
                datas.setWxTurnoverTot(tradeTypeDTO.getTurnover());
            }else if (ConstantEnum.PayTypeEnum.PAYBYALIPAY.getCode().equals(tradeTypeDTO.getPayType())){
                datas.setAliOrderNumTot(tradeTypeDTO.getOrderNum());
                datas.setAliTurnoverTot(tradeTypeDTO.getTurnover());
            }else{
                datas.setOtherOrderNumTot(tradeTypeDTO.getOrderNum());
                datas.setOtherTurnoverTot(tradeTypeDTO.getTurnover());
            }
            
            orderNumTotal += tradeTypeDTO.getOrderNum();
            turnoverTotal = turnoverTotal.add(tradeTypeDTO.getTurnover());
        }
        datas.setOrderNumTotal(orderNumTotal);
        datas.setTurnoverTotal(turnoverTotal);
        datas.setStartDate(DateUtils.strFormatToStr(payType.getStartDate()));
        datas.setEndDate(DateUtils.strFormatToStr(payType.getEndDate()));
        return datas;

    }

    /**
     * (non-Javadoc)查询经营趋势的数据
     * @see net.fnsco.order.api.trade.TradeReportService#queryBusinessTrends(net.fnsco.order.api.dto.TradeReportDTO)
     * @author tangliang
     * @date 2017年8月3日 下午4:41:46
     */
    @Override
    public BusinessTrendDTO queryBusinessTrends(TradeReportDTO tradeReportDTO) {

        formatInputDate(tradeReportDTO, false);//格式化入参
        BusinessTrendDTO datas = new BusinessTrendDTO();
        TradeByDay record = new TradeByDay();
        record.setInnerCode(tradeReportDTO.getInnerCode());
        record.setStartTradeDate(tradeReportDTO.getStartDate());
        record.setEndTradeDate(tradeReportDTO.getEndDate());
        record.setUserId(tradeReportDTO.getUserId());
        record.setRoleId(ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode());
        List<TradeDayDTO> tradeDayData = tradeByDayDao.selectByInnerCode(record);
        List<TradeDayDTO> data = installTradeDay(tradeReportDTO, tradeDayData);
        int orderNumTotal = 0;
        BigDecimal turnoverTotal = new BigDecimal(0.00);
        for (TradeDayDTO tradeDayDTO : data) {
            orderNumTotal += tradeDayDTO.getOrderNum();
            turnoverTotal = turnoverTotal.add(tradeDayDTO.getTurnover());
        }
        //设置返回数据
        datas.setOrderNumTotal(orderNumTotal);
        datas.setTurnoverTotal(turnoverTotal);
        datas.setStartDate(DateUtils.formatDateStrOutput(tradeReportDTO.getStartDate()));
        datas.setEndDate(DateUtils.formatDateStrOutput(tradeReportDTO.getEndDate()));
        if (BigDecimal.ZERO.compareTo(new BigDecimal(orderNumTotal)) != 0) {
            datas.setOrderPrice(turnoverTotal.divide(new BigDecimal(orderNumTotal), 2, BigDecimal.ROUND_HALF_UP));
        } else {
            datas.setOrderPrice(BigDecimal.ZERO);
        }
        datas.setTradeDayData(data);
        return datas;

    }

    /**
     * (non-Javadoc)查询交易峰值
     * @see net.fnsco.order.api.trade.TradeReportService#queryPeakTrade(net.fnsco.order.api.dto.TradeReportDTO)
     * @author tangliang
     * @date 2017年8月3日 下午5:34:19
     */
    @Override
    public PeakTradeDTO queryPeakTrade(TradeReportDTO tradeReportDTO) {

        formatInputDate(tradeReportDTO, false);//格式化入参
        TradeByHour record = new TradeByHour();
        record.setStartTradeDate(tradeReportDTO.getStartDate());
        record.setEndTradeDate(tradeReportDTO.getEndDate());
        record.setUserId(tradeReportDTO.getUserId());
        record.setRoleId(ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode());
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
        data.setStartDate(DateUtils.formatDateStrOutput(tradeReportDTO.getStartDate()));
        data.setEndDate(DateUtils.formatDateStrOutput(tradeReportDTO.getEndDate()));
        //如果没有数据填充数据
        List<TradeHourDTO> datas = installTradeHour(tradeReportDTO, tradeHourData);
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
    private List<TradeHourDTO> installTradeHour(TradeReportDTO tradeReportDTO, List<TradeHourDTO> tradeDayHour) {
        List<TradeHourDTO> datas = Lists.newArrayList();
        boolean flag = true;
        for (int i = 0; i < 24; i++) {//对每个时间点判断填充
            flag = true;
            for (TradeHourDTO tradeHourDTO : tradeDayHour) {
                if (i == Integer.valueOf(tradeHourDTO.getTradeHour())) {
                    datas.add(tradeHourDTO);
                    flag = false;
                }
            }
            if (flag) {
                TradeHourDTO temp = new TradeHourDTO();
                temp.setOrderNum(0);
                temp.setTradeHour(String.valueOf(i));
                temp.setTurnover(new BigDecimal(0.00));
                datas.add(temp);
            }
        }
        return datas;
    }
}
