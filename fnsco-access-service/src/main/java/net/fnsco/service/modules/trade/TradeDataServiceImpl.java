package net.fnsco.service.modules.trade;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import net.fnsco.api.dto.TradeDataDTO;
import net.fnsco.api.dto.TradeDataQueryDTO;
import net.fnsco.api.trade.TradeDataService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.PageDTO;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DbUtil;
import net.fnsco.service.dao.master.MerchantChannelDao;
import net.fnsco.service.dao.master.trade.TradeDataDAO;
import net.fnsco.service.domain.MerchantChannel;
import net.fnsco.service.domain.trade.TradeData;

/**
 * 交易流水服务类
 * @author sxf
 *
 */
@Service
public class TradeDataServiceImpl extends BaseService implements TradeDataService {
    @Autowired
    private TradeDataDAO       tradeListDAO;
    @Autowired
    private MerchantChannelDao merchantChannelDao;

    /**
     * 保存交易流水
     */
    public boolean saveTradeData(TradeDataDTO tradeData) {
        /*if (ServiceConstant.STR_1.equals(tradeData.getValidate()) && !Strings.isNullOrEmpty(tradeData.getMd5())) {
            //需要校验
            TradeData temp = tradeListDAO.selectByPrimaryKey(tradeData.getMd5());
            if (null != temp) {
                logger.error("交易流水已存在" + tradeData.getOrderNo() + ",丢弃该交易流水");
                return true;
            }
        }*/
        long timer = System.currentTimeMillis();
        String innerCode = "";
        String merId = tradeData.getMerId();
        MerchantChannel merchantChannel = merchantChannelDao.selectByMerCode(merId, tradeData.getSource());
        if (null == merchantChannel) {
            //logger.error("渠道商户不存在" + merId + ":" + tradeData.getSource() + ",丢弃该交易流水");
            //return true;
        } else {
            innerCode = merchantChannel.getInnerCode();
        }
        logger.warn("插入流水，获取商户耗时" + (System.currentTimeMillis() - timer));
        TradeData tradeDataEntity = new TradeData();
        tradeDataEntity.setId(DbUtil.getUuid());
        tradeDataEntity.setAmt(tradeData.getAmt());
        tradeDataEntity.setOrderNo(tradeData.getOrderNo());
        tradeDataEntity.setOrderTime(tradeData.getOrderTime());
        tradeDataEntity.setOrderInfo(tradeData.getOrderInfo());
        tradeDataEntity.setTimeStamp(tradeData.getTimeStamp());
        tradeDataEntity.setTradeDetail(tradeData.getTradeDetail());

        tradeDataEntity.setMerId(merId);
        tradeDataEntity.setTermId(tradeData.getTermId());
        tradeDataEntity.setBatchNo(tradeData.getBatchNo());
        tradeDataEntity.setSysTraceNo(tradeData.getSysTraceNo());
        tradeDataEntity.setAuthCode(tradeData.getAuthCode());
        tradeDataEntity.setOrderIdScan(tradeData.getOrderIdScan());
        tradeDataEntity.setSource(tradeData.getSource());
        tradeDataEntity.setMd5(tradeData.getMd5());
        tradeDataEntity.setSendTime(tradeData.getSendTime());
        tradeDataEntity.setPayType(tradeData.getPayType());
        tradeDataEntity.setPaySubType(tradeData.getPaySubType());

        tradeDataEntity.setInnerCode(innerCode);
        tradeDataEntity.setCreateTime(new Date());
        logger.error("保存交易流水信息" + JSON.toJSONString(tradeDataEntity));
        tradeListDAO.insert(tradeDataEntity);
        logger.warn("插入流水总耗时" + (System.currentTimeMillis() - timer));
        return true;
    }

    /**
     * 
     * @author sxf
     * (non-Javadoc)
     * @see net.fnsco.api.trade.TradeDataService#queryAllByCondition(net.fnsco.api.dto.TradeDataQueryDTO)
     */
    @Override
    public ResultPageDTO<TradeData> queryAllByCondition(TradeDataQueryDTO merchantCore) {
        TradeData tradeData = new TradeData();
        BeanUtils.copyProperties(merchantCore, tradeData);
        PageDTO<TradeData> pages = new PageDTO<TradeData>(merchantCore.getCurrentPageNum(), merchantCore.getPerPageSize(), tradeData);
        List<TradeData> datas = tradeListDAO.queryPageList(pages);
        int total = tradeListDAO.queryTotalByCondition(tradeData);
        ResultPageDTO<TradeData> result = new ResultPageDTO<TradeData>(total, datas);
        result.setCurrentPage(merchantCore.getCurrentPageNum());
        return result;
    }

    /**
     * 条件分页查询
     * (non-Javadoc)
     * @see net.fnsco.api.trade.TradeDataService#queryMerchantCore(net.fnsco.api.dto.TradeDataDTO, int, int)
     * @auth tangliang
     * @date 2017年6月28日 下午5:13:54
     */
    @Override
    public ResultPageDTO<TradeData> queryTradeData(TradeDataDTO tradeDataDTO, int currentPageNum, int perPageSize) {

        TradeData tradeData = new TradeData();
        BeanUtils.copyProperties(tradeDataDTO, tradeData);
        PageDTO<TradeData> pages = new PageDTO<TradeData>(currentPageNum, perPageSize, tradeData);

        List<TradeData> datas = tradeListDAO.queryPageList(pages);
        int total = tradeListDAO.queryTotalByCondition(tradeData);

        ResultPageDTO<TradeData> result = new ResultPageDTO<TradeData>(total, datas);
        result.setCurrentPage(currentPageNum);
        return result;

    }
}
