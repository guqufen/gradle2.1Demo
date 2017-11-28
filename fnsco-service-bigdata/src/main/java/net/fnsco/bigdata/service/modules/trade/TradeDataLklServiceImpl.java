package net.fnsco.bigdata.service.modules.trade;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.bigdata.api.dto.TradeDataDTO;
import net.fnsco.bigdata.api.trade.LklTradeDataService;
import net.fnsco.bigdata.service.dao.master.MerchantCoreDao;
import net.fnsco.bigdata.service.dao.master.MerchantTerminalDao;
import net.fnsco.bigdata.service.dao.master.trade.LklTradeDataDAO;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.bigdata.service.domain.trade.TradeDataLkl;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.PageDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年11月27日 下午4:02:29
 */
@Service
public class TradeDataLklServiceImpl extends BaseService implements LklTradeDataService {
	
	@Autowired
    private LklTradeDataDAO        tradeListDAO;
	@Autowired
    private MerchantCoreDao     merchantCoreDao;
	@Autowired
    private MerchantTerminalDao merchantTerminalDao;
	/**
	 * (non-Javadoc)
	 * @see net.fnsco.bigdata.api.trade.LklTradeDataService#queryTradeData(net.fnsco.bigdata.api.dto.TradeDataDTO, int, int)
	 * @author tangliang
	 * @date 2017年11月27日 下午4:02:29
	 */
	@Override
	public ResultPageDTO<TradeDataLkl> queryTradeData(TradeDataDTO tradeDataDTO, int currentPageNum, int perPageSize) {

		TradeDataLkl tradeData = new TradeDataLkl();
        if (tradeDataDTO.getPayType() != null && tradeDataDTO.getPayType().equals("03")) {
            tradeDataDTO.setPayType(null);
        }
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
        PageDTO<TradeDataLkl> pages = new PageDTO<TradeDataLkl>(currentPageNum, perPageSize, tradeData);
        List<TradeDataLkl> datas = tradeListDAO.queryPageList(pages);
        for (TradeDataLkl tradeData2 : datas) {
            if (!Strings.isNullOrEmpty(tradeData2.getInnerCode())) {
                MerchantCore core = merchantCoreDao.selectByInnerCode(tradeData2.getInnerCode());
                if (null != core) {
                    tradeData2.setMerName(core.getMerName());
                }
            }
        }
        int total = tradeListDAO.queryTotalByCondition(tradeData);

        ResultPageDTO<TradeDataLkl> result = new ResultPageDTO<TradeDataLkl>(total, datas);
        result.setCurrentPage(currentPageNum);
        return result;

	}
	
	@Override
    public String queryTotalAmount(TradeDataDTO tradeDataDTO) {
		TradeDataLkl tradeData = new TradeDataLkl();
        if (tradeDataDTO.getPayType() != null &&tradeDataDTO.getPayType().equals("03")) {
            tradeDataDTO.setPayType(null);
        }
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

        String totalAmountList = tradeListDAO.queryTotalAmount(tradeData);
        return totalAmountList;
    }
	
	@Override
    public List<TradeDataLkl> queryDataList(TradeDataDTO tradeDataDTO) {
		TradeDataLkl tradeData = new TradeDataLkl();
        if (tradeDataDTO.getPayType() != null &&tradeDataDTO.getPayType().equals("03")) {
            tradeDataDTO.setPayType(null);
        }
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
        List<TradeDataLkl> datas = tradeListDAO.queryByAllCondition(tradeData);
        for (TradeDataLkl merchantdo : datas) {
            String id = merchantdo.getTermId();
            String code = merchantdo.getInnerCode();
            String sn = merchantTerminalDao.querySnCode(id, code);
            merchantdo.setSnCode(sn);
        }
        for (TradeDataLkl tradeData2 : datas) {
            if (!Strings.isNullOrEmpty(tradeData2.getInnerCode())) {
                MerchantCore core = merchantCoreDao.selectByInnerCode(tradeData2.getInnerCode());
                if (null != core) {
                    tradeData2.setMerName(core.getMerName());
                }
            }
        }
        return datas;
    }
}
