package net.fnsco.order.service.modules.trade;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import net.fnsco.bigdata.api.dto.TradeDataPageDTO;
import net.fnsco.bigdata.api.dto.TradeDataQueryDTO;
import net.fnsco.bigdata.service.dao.master.MerchantPosDao;
import net.fnsco.bigdata.service.dao.master.trade.TradeDataDAO;
import net.fnsco.bigdata.service.domain.MerchantPos;
import net.fnsco.bigdata.service.domain.trade.TradeData;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.PageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.order.service.dao.master.AppUserMerchantDao;
import net.fnsco.order.service.domain.AppUserMerchant;

/**
 * 交易流水服务类
 * @author sxf
 *
 */
@Service
public class TradeDataAppServiceImpl extends BaseService{
    @Autowired
    private TradeDataDAO        tradeListDAO;
    @Autowired
    private AppUserMerchantDao  appUserMerchantDao;
    @Autowired
    private MerchantPosDao      merchantPosDao;

    /**
     * 
     * @author sxf
     * (non-Javadoc)
     * @see net.fnsco.bigdata.api.trade.TradeDataService#queryAllByCondition(net.fnsco.order.api.dto.TradeDataQueryDTO)
     */
    public TradeDataPageDTO<TradeData> queryAllByCondition(TradeDataQueryDTO merchantCore) {
        TradeDataPageDTO<TradeData> result = new TradeDataPageDTO<TradeData>(0, null);
        result.setCurrentPage(merchantCore.getCurrentPageNum());
        TradeData tradeData = new TradeData();
        //设置交易时间
        tradeData.setStartTime(DateUtils.getDateStartTime(merchantCore.getStartDate()));
        tradeData.setEndTime(DateUtils.getDateEndTime(merchantCore.getEndDate()));
        if (!CollectionUtils.isEmpty(merchantCore.getPayType())) {
            tradeData.setPaySubTypes(merchantCore.getPayType());
        }
        if (!Strings.isNullOrEmpty(merchantCore.getPayMedium())) {
            tradeData.setPayMedium(merchantCore.getPayMedium());
        }
        //根据pos查询终端列表
        List<String> posList = merchantCore.getTerminals();
        if (!CollectionUtils.isEmpty(posList)) {
            List<String> terminalList = Lists.newArrayList();
            for (String posId : posList) {
            	MerchantPos merPos = merchantPosDao.selectByPrimaryKey(Integer.parseInt(posId));
            	
            	if(!Strings.isNullOrEmpty(merPos.getTerminalCode())) {
            		terminalList.add(merPos.getTerminalCode());
            	}
            	
            	if(!Strings.isNullOrEmpty(merPos.getQrChannelTerminalCode())) {
            		terminalList.add(merPos.getQrChannelTerminalCode());
            	}
            }
            if (!CollectionUtils.isEmpty(terminalList)) {
                tradeData.setTerminalList(terminalList);
            }
        }

        Integer appUserId = merchantCore.getUserId();
        List<AppUserMerchant> tempList = appUserMerchantDao.selectByUserId(appUserId);
        result.setMerTotal(tempList.size());
        result.setCount("0");
        result.setAmtTot("0.00");

        List<String> innerCodeList = Lists.newArrayList();
        //查询条件没有转入内部商户号时查询用户的所有商户信息
        if (CollectionUtils.isEmpty(merchantCore.getInnerCodes())) {
            logger.info("查询流水，内部商务号为空");
            if (!CollectionUtils.isEmpty(tempList)) {
                for (AppUserMerchant rel : tempList) {
                    innerCodeList.add(rel.getInnerCode());
                }
            }
            if (null == innerCodeList || innerCodeList.size() == 0) {
                logger.info("查询流水，内部商务号没有");
                return result;
            }
        } else {
            logger.info("查询流水，内部商务号不为空");
            innerCodeList = merchantCore.getInnerCodes();
        }
        //设置商户号
        if (CollectionUtils.isEmpty(innerCodeList)) {
            return result;
        } else {
            tradeData.setInnerCodeList(innerCodeList);
        }

        PageDTO<TradeData> pages = new PageDTO<TradeData>(merchantCore.getCurrentPageNum(), merchantCore.getPerPageSize(), tradeData);
        List<TradeData> datas = tradeListDAO.queryPageList(pages);
        int total = tradeListDAO.queryTotalByCondition(tradeData);
        result.setTotal(total);
        result.setList(datas);
        result.setCurrentPage(merchantCore.getCurrentPageNum());
        //查询总金额
        Map map = tradeListDAO.querySumByCondition(tradeData);
        Long count = null;
        Double amtSum = null;
        String amtSumStr = "0.00";
        if (map != null) {
            count = (Long) map.get("count");
            amtSum = (Double) map.get("amt");
        }
        if (count != null) {
            result.setCount(String.valueOf(count));
        }
        if (amtSum != null) {
            DecimalFormat df = new DecimalFormat("#0.00");
            amtSum = amtSum / 100;
            amtSumStr = df.format(amtSum);
        }
        result.setAmtTot(amtSumStr);
        return result;
    }
   
}
