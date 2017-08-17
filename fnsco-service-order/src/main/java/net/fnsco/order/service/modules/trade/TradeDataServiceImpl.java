package net.fnsco.order.service.modules.trade;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.PageDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.DbUtil;
import net.fnsco.order.api.dto.TradeDataDTO;
import net.fnsco.order.api.dto.TradeDataQueryDTO;
import net.fnsco.order.api.trade.TradeDataService;
import net.fnsco.order.service.dao.master.MerchantChannelDao;
import net.fnsco.order.service.dao.master.MerchantCoreDao;
import net.fnsco.order.service.dao.master.MerchantTerminalDao;
import net.fnsco.order.service.dao.master.MerchantUserRelDao;
import net.fnsco.order.service.dao.master.trade.TradeDataDAO;
import net.fnsco.order.service.domain.MerchantChannel;
import net.fnsco.order.service.domain.MerchantCore;
import net.fnsco.order.service.domain.MerchantTerminal;
import net.fnsco.order.service.domain.MerchantUserRel;
import net.fnsco.order.service.domain.trade.TradeData;

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
    @Autowired
    private MerchantUserRelDao merchantUserRelDao;
    @Autowired
    private MerchantCoreDao    merchantCoreDao;
    @Autowired
    private MerchantTerminalDao     merchantTerminalDao;

    /**
     * 保存交易流水
     */
    @Transactional
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
        tradeDataEntity.setTraceNo(tradeData.getSysTraceNo());
        tradeDataEntity.setAuthCode(tradeData.getAuthCode());
        tradeDataEntity.setOrderIdScan(tradeData.getOrderIdScan());
        tradeDataEntity.setSource(tradeData.getSource());
        tradeDataEntity.setMd5(tradeData.getMd5());
        tradeDataEntity.setSendTime(tradeData.getSendTime());
        tradeDataEntity.setPayType(tradeData.getPayType());
        tradeDataEntity.setPaySubType(tradeData.getPaySubType());
        tradeDataEntity.setReferNo(tradeData.getReferNo());
        tradeDataEntity.setInnerCode(innerCode);
        tradeDataEntity.setCreateTime(new Date());
        tradeDataEntity.setRespCode(tradeData.getRespCode());

        tradeDataEntity.setCertifyId(tradeData.getCardNo());
        tradeDataEntity.setDcType(tradeData.getCardOrg());
        tradeDataEntity.setTxnType(tradeData.getTxnType());
        tradeDataEntity.setStatus("1");
        String txnType = tradeData.getTxnType();
        if ("2".equals(txnType)) {
            tradeDataEntity.setStatus("0");
        }
        logger.error("保存交易流水信息" + JSON.toJSONString(tradeDataEntity));
        tradeListDAO.insert(tradeDataEntity);
        logger.warn("插入流水总耗时" + (System.currentTimeMillis() - timer));
        if ("2".equals(txnType)) {
            TradeData temp = tradeListDAO.selectByIRT(tradeDataEntity);
            TradeData data = new TradeData();
            data.setStatus("0");
            data.setId(temp.getId());
            tradeListDAO.updateByPrimaryKeySelective(data);
        }
        return true;
    }

    /**
     * 
     * @author sxf
     * (non-Javadoc)
     * @see net.fnsco.order.api.trade.TradeDataService#queryAllByCondition(net.fnsco.order.api.dto.TradeDataQueryDTO)
     */
    @Override
    public ResultPageDTO<TradeData> queryAllByCondition(TradeDataQueryDTO merchantCore) {
        ResultPageDTO<TradeData> result = new ResultPageDTO<TradeData>(0, null);
        result.setCurrentPage(merchantCore.getCurrentPageNum());
        TradeData tradeData = new TradeData();
        //设置交易时间
        tradeData.setStartTime(DateUtils.getDateStartTime(merchantCore.getStartDate()));
        tradeData.setEndTime(DateUtils.getDateEndTime(merchantCore.getEndDate()));
        tradeData.setPaySubType(merchantCore.getPayType());
        //根据pos查询终端列表
        List<String> posList = merchantCore.getTerminals();
        if (!CollectionUtils.isEmpty(posList)) {
            List<String> terminalList = Lists.newArrayList();
            for (String id : posList) {
                MerchantTerminal terminal = merchantTerminalDao.selectByPrimaryKey(Integer.parseInt(id));
                terminalList.add(terminal.getTerminalCode());
            }
            tradeData.setTerminalList(terminalList);
        }
        List<String> terminalList = tradeData.getTerminalList();
        if (CollectionUtils.isEmpty(terminalList)) {//无终端则查询按商户查询
            Integer appUserId = merchantCore.getUserId();
            List<MerchantUserRel> tempList = merchantUserRelDao.selectByUserId(appUserId);
            result.setMerTotal(tempList.size());
            List<String> innerCodeList = Lists.newArrayList();
            if (!CollectionUtils.isEmpty(tempList)) {
                for (MerchantUserRel rel : tempList) {
                    innerCodeList.add(rel.getInnerCode());
                }
            }
            if (null == innerCodeList || innerCodeList.size() == 0) {
                return result;
            }
            //设置商户号
            tradeData.setInnerCodeList(innerCodeList);
        }
        PageDTO<TradeData> pages = new PageDTO<TradeData>(merchantCore.getCurrentPageNum(), merchantCore.getPerPageSize(), tradeData);
        List<TradeData> datas = tradeListDAO.queryPageList(pages);
        int total = tradeListDAO.queryTotalByCondition(tradeData);
        result.setTotal(total);
        result.setList(datas);
        result.setCurrentPage(merchantCore.getCurrentPageNum());
        return result;
    }

    /**
     * 条件分页查询
     * (non-Javadoc)
     * @see net.fnsco.order.api.trade.TradeDataService#queryMerchantCore(net.fnsco.order.api.dto.TradeDataDTO, int, int)
     * @auth tangliang
     * @date 2017年6月28日 下午5:13:54
     */
    @Override
    public ResultPageDTO<TradeData> queryTradeData(TradeDataDTO tradeDataDTO, int currentPageNum, int perPageSize) {

        TradeData tradeData = new TradeData();
        if (!StringUtils.isEmpty(tradeDataDTO.getStartSendTime())) {
            tradeDataDTO.setStartSendTime(DateUtils.getDateStartTime(tradeDataDTO.getStartSendTime()));
        }
        if (!StringUtils.isEmpty(tradeDataDTO.getEndSendTime())) {
            tradeDataDTO.setEndSendTime(DateUtils.getDateEndTime(tradeDataDTO.getEndSendTime()));
        }
        BeanUtils.copyProperties(tradeDataDTO, tradeData);
        PageDTO<TradeData> pages = new PageDTO<TradeData>(currentPageNum, perPageSize, tradeData);

        List<TradeData> datas = tradeListDAO.queryPageList(pages);
        for (TradeData tradeData2 : datas) {
            if (!Strings.isNullOrEmpty(tradeData2.getInnerCode())) {
                MerchantCore core = merchantCoreDao.selectByInnerCode(tradeData2.getInnerCode());
                if (null != core) {
                    tradeData2.setMerName(core.getMerName());
                }
            }
        }
        int total = tradeListDAO.queryTotalByCondition(tradeData);

        ResultPageDTO<TradeData> result = new ResultPageDTO<TradeData>(total, datas);
        result.setCurrentPage(currentPageNum);
        return result;

    }

    @Override
    public TradeData queryByTradeId(String tradeId) {
        return tradeListDAO.selectByPrimaryKey(tradeId);

    }
}
