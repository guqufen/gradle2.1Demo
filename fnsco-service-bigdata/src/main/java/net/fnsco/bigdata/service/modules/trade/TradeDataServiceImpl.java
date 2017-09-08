package net.fnsco.bigdata.service.modules.trade;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import net.fnsco.bigdata.api.dto.TerminalInfoDTO;
import net.fnsco.bigdata.api.dto.TradeDataDTO;
import net.fnsco.bigdata.api.dto.TradeDataPageDTO;
import net.fnsco.bigdata.api.dto.TradeDataQueryDTO;
import net.fnsco.bigdata.api.trade.TradeDataService;
import net.fnsco.bigdata.service.dao.master.MerchantChannelDao;
import net.fnsco.bigdata.service.dao.master.MerchantCoreDao;
import net.fnsco.bigdata.service.dao.master.MerchantTerminalDao;
import net.fnsco.bigdata.service.dao.master.MerchantUserRelDao;
import net.fnsco.bigdata.service.dao.master.trade.TradeDataDAO;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.bigdata.service.domain.MerchantUserRel;
import net.fnsco.bigdata.service.domain.trade.TradeData;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.PageDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.DbUtil;

/**
 * 交易流水服务类
 * @author sxf
 *
 */
@Service
public class TradeDataServiceImpl extends BaseService implements TradeDataService {
    @Autowired
    private TradeDataDAO        tradeListDAO;
    @Autowired
    private MerchantChannelDao  merchantChannelDao;
    @Autowired
    private MerchantUserRelDao  merchantUserRelDao;
    @Autowired
    private MerchantCoreDao     merchantCoreDao;
    @Autowired
    private MerchantTerminalDao merchantTerminalDao;

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
     * @see net.fnsco.bigdata.api.trade.TradeDataService#queryAllByCondition(net.fnsco.order.api.dto.TradeDataQueryDTO)
     */
    @Override
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
        //根据pos查询终端列表
        List<String> posList = merchantCore.getTerminals();
        if (!CollectionUtils.isEmpty(posList)) {
            List<String> terminalList = Lists.newArrayList();
            for (String posId : posList) {
                List<TerminalInfoDTO> tempList = merchantTerminalDao.queryTerByPosId(Integer.parseInt(posId));
                for (TerminalInfoDTO terminal : tempList) {
                    terminalList.add(terminal.getTerminalCode());
                }
            }
            if (!CollectionUtils.isEmpty(terminalList)) {
                tradeData.setTerminalList(terminalList);
            }
        }
        List<String> terminalList = tradeData.getTerminalList();

        Integer appUserId = merchantCore.getUserId();
        List<MerchantUserRel> tempList = merchantUserRelDao.selectByUserId(appUserId);
        result.setMerTotal(tempList.size());
        result.setCount("0");
        result.setAmtTot("0.00");
        if (CollectionUtils.isEmpty(terminalList)) {//无终端则查询按商户查询
            List<String> innerCodeList = Lists.newArrayList();
            //查询条件没有转入内部商户号时查询用户的所有商户信息
            if (CollectionUtils.isEmpty(merchantCore.getInnerCodes())) {
                logger.info("查询流水，内部商务号为空");
                if (!CollectionUtils.isEmpty(tempList)) {
                    for (MerchantUserRel rel : tempList) {
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
            }else{
                tradeData.setInnerCodeList(innerCodeList);
            }
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

    /**
     * 条件分页查询
     * (non-Javadoc)
     * @see net.fnsco.bigdata.api.trade.TradeDataService#queryMerchantCore(net.fnsco.order.api.dto.TradeDataDTO, int, int)
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

        if (!StringUtils.isEmpty(tradeDataDTO.getStartTime())) {
            tradeDataDTO.setStartTime(DateUtils.getDateStartTime(tradeDataDTO.getStartTime()));
        }
        if (!StringUtils.isEmpty(tradeDataDTO.getEndTime())) {
            tradeDataDTO.setEndTime(DateUtils.getDateEndTime(tradeDataDTO.getEndTime()));
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

    @Override
    public int selectCountByIRT(TradeData tradeData) {
        int result = tradeListDAO.selectCountByIRT(tradeData);
        return result;
    }

    @Override
    public List<TradeData> queryDataList(TradeDataDTO tradeDataDTO) {
        TradeData tradeData = new TradeData();
        if (!StringUtils.isEmpty(tradeDataDTO.getStartSendTime())) {
            tradeDataDTO.setStartSendTime(DateUtils.getDateStartTime(tradeDataDTO.getStartSendTime()));
        }
        if (!StringUtils.isEmpty(tradeDataDTO.getEndSendTime())) {
            tradeDataDTO.setEndSendTime(DateUtils.getDateEndTime(tradeDataDTO.getEndSendTime()));
        }
        BeanUtils.copyProperties(tradeDataDTO, tradeData);
        List<TradeData> datas = tradeListDAO.queryByAllCondition(tradeData);
        for (TradeData merchantdo : datas) {
            String id = merchantdo.getTermId();
            String code = merchantdo.getInnerCode();
            String sn = merchantTerminalDao.querySnCode(id, code);
            merchantdo.setSnCode(sn);
        }
        for (TradeData tradeData2 : datas) {
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