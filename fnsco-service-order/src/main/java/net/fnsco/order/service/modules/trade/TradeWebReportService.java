package net.fnsco.order.service.modules.trade;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import net.fnsco.bigdata.api.dto.TradeDataListResultDTO;
import net.fnsco.bigdata.api.dto.TradeDataResultDTO;
import net.fnsco.bigdata.comm.ServiceConstant;
import net.fnsco.bigdata.service.trade.dao.TradeDataSimpleDAO;
import net.fnsco.bigdata.service.trade.entity.TradeDataDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.order.api.config.SysConfigService;
import net.fnsco.order.service.domain.SysConfig;

/**
 * 
 * @desc 查询交易流水web报表
 * @author   sxf
 * @version  
 * @since    Ver 1.1
 * @Date	 2017年9月6日 上午10:36:09
 *
 */
@Service
public class TradeWebReportService extends BaseService {
    @Autowired
    private SysConfigService   sysConfigService;
    @Autowired
    private TradeDataSimpleDAO tradeDataSimpleDAO;

    /**
     * 
     * getAllTradeDataTotle:(获取所有交易数据总金额和总笔数)
     *   void    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public TradeDataResultDTO getAllTradeDataTotle() {
        SysConfig condition = new SysConfig();
        condition.setGroup("oldTradeData");
        List<SysConfig> sysConfigList = sysConfigService.selectAllByCondition(condition);
        BigDecimal amtTot = BigDecimal.ZERO;
        Long count = 0l;
        for (SysConfig config : sysConfigList) {
            if (config.getName().equals("01")) {
                amtTot = amtTot.add(new BigDecimal(config.getValue()));
            }
            if (config.getName().equals("02")) {
                count = count + Long.parseLong(config.getValue());
            }
        }
        TradeDataResultDTO tradeResult = tradeDataSimpleDAO.getAllTradeDataTotle();
        if (null != tradeResult) {
            BigDecimal tempAmt = new BigDecimal(tradeResult.getAmtTot());
            tempAmt = tempAmt.divide(new BigDecimal("100"));
            amtTot = amtTot.add(tempAmt);
            count = count + Long.parseLong(tradeResult.getCount());
        } else {
            tradeResult = new TradeDataResultDTO();
        }
        tradeResult.setAmtTot(amtTot.toString());
        tradeResult.setCount(count.toString());
        return tradeResult;
    }

    public List<TradeDataListResultDTO> getTradeDataList(String startDate, Integer pageSize) {
        List<TradeDataListResultDTO> resultList = Lists.newArrayList();
        if (null == pageSize) {
            pageSize = 20;
        }
        if (Strings.isNullOrEmpty(startDate)) {
            startDate = DateUtils.getYesterdayDateStr();
        }
        List<TradeDataDO> tradeList = tradeDataSimpleDAO.getTradeDataList(startDate, pageSize);
        for (TradeDataDO data : tradeList) {
            TradeDataListResultDTO resultDTO = new TradeDataListResultDTO();
            resultDTO.setAmt("0");
            try{
	            BigDecimal amt = new BigDecimal(data.getAmt());
	            amt = amt.divide(new BigDecimal("100"));
	            resultDTO.setAmt(amt.toString());
            }catch(Exception ex){
            	logger.error("大屏查询",ex);
            }
            resultDTO.setPayType(ServiceConstant.PaySubTypeEnum.getNameByCode(data.getPaySubType()));
            resultDTO.setCreateTime(DateUtils.dateFormatToStr(data.getCreateTime()));
            resultDTO.setTermId(data.getTermId());
            resultList.add(resultDTO);
        }
        return resultList;
    }
}
