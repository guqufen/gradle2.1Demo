package net.fnsco.trading.service.report;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.trading.service.order.dao.TradeOrderByDayDAO;
import net.fnsco.trading.service.order.dao.TradeOrderByPayMediumDAO;
import net.fnsco.trading.service.order.dao.TradeOrderByPayTypeDAO;
import net.fnsco.trading.service.order.dao.TradeOrderDAO;
import net.fnsco.trading.service.order.dao.TradeOrderDateTempDAO;
import net.fnsco.trading.service.order.entity.TradeOrderByDayDO;
import net.fnsco.trading.service.order.entity.TradeOrderByPayMediumDO;
import net.fnsco.trading.service.order.entity.TradeOrderByPayTypeDO;
import net.fnsco.trading.service.order.entity.TradeOrderDO;
import net.fnsco.trading.service.order.entity.TradeOrderDateTempDO;

/**
 * @desc 分别按照天、支付方式、来统计t_trade_order表中数据
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月13日 下午4:47:08
 */
@Service
public class ReportStatService extends BaseService {
	
	@Autowired
	private TradeOrderDateTempDAO tradeOrderDateTempDAO;
	@Autowired
	private TradeOrderDAO tradeOrderDAO;
	@Autowired
	private TradeOrderByDayDAO tradeOrderByDayDAO;
	@Autowired
	private TradeOrderByPayTypeDAO tradeOrderByPayTypeDAO;
	@Autowired
	private TradeOrderByPayMediumDAO tradeOrderByPayMediumDAO;
	/**
	 * createReportData:(报表统计生成)
	 *
	 * @param  @param startDate
	 * @param  @param endDate    设定文件
	 * @return void    DOM对象
	 * @author tangliang
	 * @date   2017年12月13日 下午4:50:47
	 */
	@Transactional
	public void createReportData(Date startDate,Date endDate) {
		tradeOrderDateTempDAO.deleteAll();//首先清空全表数据，从新统计
		
		TradeOrderDO conTradeOrder = new TradeOrderDO();
		conTradeOrder.setStartCreateDate(startDate);
		conTradeOrder.setEndCreateDate(endDate);
		conTradeOrder.setRespCode("1001");//只统计成功的数据
		
		List<TradeOrderDO> tempDatas = tradeOrderDAO.queryByCondition(conTradeOrder);
		//如果没有数据直接返回
        if(CollectionUtils.isEmpty(tempDatas)){
            return;
        }
        
		List<TradeOrderDateTempDO> tempData = Lists.newArrayList();
		for (TradeOrderDO tradeOrderDO : tempDatas) {
			TradeOrderDateTempDO tradeOrderDateTemp = new TradeOrderDateTempDO();
			tradeOrderDateTemp.setInnerCode(tradeOrderDO.getInnerCode());
			tradeOrderDateTemp.setAmt(tradeOrderDO.getTxnAmount() == null?null:tradeOrderDO.getTxnAmount().toString());
			tradeOrderDateTemp.setPaySubType(tradeOrderDO.getPaySubType());
			tradeOrderDateTemp.setProcedureFee(null);
			tradeOrderDateTemp.setPayMedium(tradeOrderDO.getPayMedium());
			String timeStamp = DateUtils.dateFormat1ToStr(tradeOrderDO.getCreateTime());
			tradeOrderDateTemp.setTimeStamp(timeStamp);
			tradeOrderDateTemp.setTradeDate(timeStamp.substring(0, 8));
			tradeOrderDateTemp.setTradeHoure(timeStamp.substring(8, 10));
			tempData.add(tradeOrderDateTemp);
		}
		tradeOrderDateTempDAO.insertBatch(tempData);
		
		//统计之前先删除，防止重复统计
		String startTradeDate = DateUtils.strToDate2(startDate);
		String endTradeDate = DateUtils.strToDate2(endDate);
		TradeOrderByDayDO tradeOrderByDay = new TradeOrderByDayDO();
		tradeOrderByDay.setStartTradeDate(startTradeDate);
		tradeOrderByDay.setEndTradeDate(endTradeDate);
		tradeOrderByDayDAO.deleteByCondition(tradeOrderByDay);
		TradeOrderByPayTypeDO tradeOrderByPayType = new TradeOrderByPayTypeDO();
		tradeOrderByPayType.setStartTradeDate(startTradeDate);
		tradeOrderByPayType.setEndTradeDate(endTradeDate);
		tradeOrderByPayTypeDAO.deleteByCondition(tradeOrderByPayType);
		TradeOrderByPayMediumDO tradeOrderByPayMedium = new TradeOrderByPayMediumDO();
		tradeOrderByPayMedium.setStartTradeDate(startTradeDate);
		tradeOrderByPayMedium.setEndTradeDate(endTradeDate);
		tradeOrderByPayMediumDAO.deleteByCondition(tradeOrderByPayMedium);
		
		//分别按天、支付渠道、支付媒介,统计查询且插入对应表中
		List<TradeOrderByDayDO> tradeDateDayList = tradeOrderDateTempDAO.selectTradeDataByDay();
		for (TradeOrderByDayDO tradeOrderByDayDO : tradeDateDayList) {
			tradeOrderByDayDO.setCreateTime(new Date());
		}
		tradeOrderByDayDAO.insertBatch(tradeDateDayList);
		List<TradeOrderByPayTypeDO> tradeDatePayTypeList = tradeOrderDateTempDAO.selectTradeDataByPayType();
		tradeOrderByPayTypeDAO.insertBatch(tradeDatePayTypeList);
		List<TradeOrderByPayMediumDO> tradeOrderByPayMediumList = tradeOrderDateTempDAO.selectTradeDataByPayMedium();
		tradeOrderByPayMediumDAO.insertBatch(tradeOrderByPayMediumList);
	}
	
}
