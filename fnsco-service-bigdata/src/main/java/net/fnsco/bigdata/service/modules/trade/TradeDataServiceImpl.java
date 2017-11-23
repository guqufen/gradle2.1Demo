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
import net.fnsco.bigdata.service.domain.MerchantTerminal;
import net.fnsco.bigdata.service.domain.MerchantUserRel;
import net.fnsco.bigdata.service.domain.trade.TradeData;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.PageDTO;
import net.fnsco.core.base.ResultDTO;
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
        //ServiceConstant.STR_1.equals(tradeData.getValidate()) && 
        if (!Strings.isNullOrEmpty(tradeData.getMd5())) {
            //需要校验
            TradeData temp = tradeListDAO.selectByMd5(tradeData.getMd5());
            if (null != temp) {
                logger.error("交易流水已存在" + tradeData.getOrderNo() + ",丢弃该交易流水");
                return true;
            }
        }
        long timer = System.currentTimeMillis();
        String innerCode = "";
        String merId = tradeData.getMerId();
        //拉卡拉渠道
        if ("00".equals(tradeData.getChannelType())) {
            if (!Strings.isNullOrEmpty(tradeData.getTermId())
                && !Strings.isNullOrEmpty(tradeData.getChannelType())) {
                MerchantTerminal merchantTerminal = merchantTerminalDao
                    .selectOneByTermId(tradeData.getTermId(), tradeData.getChannelType());
                if (null != merchantTerminal) {
                    innerCode = merchantTerminal.getInnerCode();
                }
            } else {
                if (!Strings.isNullOrEmpty(tradeData.getMerId())
                    && !Strings.isNullOrEmpty(tradeData.getChannelType())) {
                    MerchantChannel channel = merchantChannelDao
                        .selectByMerCode(tradeData.getMerId(), tradeData.getChannelType());
                    if (channel != null) {
                        innerCode = channel.getInnerCode();
                    }
                }
            }
        } else if ("02".equals(tradeData.getChannelType())) {//01浦发02爱农03法奈昇
            if (!Strings.isNullOrEmpty(tradeData.getMerId())
                && !Strings.isNullOrEmpty(tradeData.getChannelType())) {

                MerchantChannel channel = merchantChannelDao
                    .selectByMerCode(tradeData.getInnerCode(), tradeData.getChannelType());
                if (channel != null) {
                    innerCode = channel.getInnerCode();
                } else {
                    logger.error("内部商户号没有渠道对应:" + tradeData.getInnerCode());
                }
            }
        } else if ("01".equals(tradeData.getChannelType())
                   || "03".equals(tradeData.getChannelType())) {//01浦发02爱农03法奈昇
            MerchantChannel channel = merchantChannelDao.selectByMerCode(tradeData.getMerId(),
                tradeData.getChannelType());
            if (channel != null) {
                innerCode = channel.getInnerCode();
            }
        } else {
            MerchantChannel channel = merchantChannelDao.selectByMerCode(tradeData.getMerId(),
                tradeData.getChannelType());
            if (channel != null) {
                innerCode = channel.getInnerCode();
            }
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
        tradeDataEntity.setChannelTermCode(tradeData.getChannelTermCode());
        //        if(tradeData.getCreateTime()!=null) {
        //        	tradeDataEntity.setCreateTime(tradeData.getCreateTime());
        //        }
        tradeDataEntity.setRespCode(tradeData.getRespCode());

        tradeDataEntity.setCertifyId(tradeData.getCardNo());
        tradeDataEntity.setDcType(tradeData.getCardOrg());
        tradeDataEntity.setTxnType(tradeData.getTxnType());
        tradeDataEntity.setStatus("1");

        tradeDataEntity.setPayMedium(tradeData.getPayMedium());
        tradeDataEntity.setChannelType(tradeData.getChannelType());
        String txnType = tradeData.getTxnType();
        if ("2".equals(txnType)) {
            tradeDataEntity.setStatus("0");
        }
        if (!"1001".equals(tradeData.getRespCode())) {
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
     * 富友流水同步导入到交易表
     */
    @Transactional
    public boolean saveTradeData(TradeData tradeData) {

        if (!Strings.isNullOrEmpty(tradeData.getMd5())) {
            //需要校验
            TradeData temp = tradeListDAO.selectByMd5(tradeData.getMd5());
            if (null != temp) {
                logger.error("交易流水已存在" + tradeData.getOrderNo() + ",丢弃该交易流水");
                return true;
            }
        }

        long timer = System.currentTimeMillis();
        tradeListDAO.insert(tradeData);
        logger.warn("插入流水总耗时" + (System.currentTimeMillis() - timer));
        String txnType = tradeData.getTxnType();
        if ("2".equals(txnType)) {
        	if(Strings.isNullOrEmpty(tradeData.getMerId()) || Strings.isNullOrEmpty(tradeData.getChannelTermCode())){
        		logger.info("渠道终端号或商户号为空，不能查找到原交易");
        		return false;
        	}
            TradeData tmp = tradeListDAO.selectByCMT(tradeData);
            TradeData data = new TradeData();
            data.setStatus("0");
            data.setId(tmp.getId());
            tradeListDAO.updateByPrimaryKeySelective(data);
        }

        return true;
    }

    /**
     * 保存交易流水
     */
    @Transactional
    public ResultDTO<String> batchSaveTradeData(TradeData tradeData) {
        long timer = System.currentTimeMillis();
        String innerCode = "";
        String merId = tradeData.getMerId();
        MerchantChannel merchantChannel = merchantChannelDao.selectByMerCode(merId,
            tradeData.getSource());
        if (null == merchantChannel) {
            logger.error("渠道商户不存在" + merId + ":" + tradeData.getSource() + ",丢弃该交易流水");
            return ResultDTO.fail("渠道商户不存在" + merId + ":" + tradeData.getSource() + ",丢弃该交易流水");
        } else {
            innerCode = merchantChannel.getInnerCode();
        }
        logger.warn("插入流水，获取商户耗时" + (System.currentTimeMillis() - timer));
        String txnType = tradeData.getTxnType();
        if ("2".equals(txnType)) {
            tradeData.setStatus("0");
        }
        logger.error("保存交易流水信息" + JSON.toJSONString(tradeData));
        tradeListDAO.insert(tradeData);
        logger.warn("插入流水总耗时" + (System.currentTimeMillis() - timer));
        if ("2".equals(txnType)) {
            TradeData temp = tradeListDAO.selectByIRT(tradeData);
            TradeData data = new TradeData();
            data.setStatus("0");
            data.setId(temp.getId());
            tradeListDAO.updateByPrimaryKeySelective(data);
        }
        return ResultDTO.success();
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
        if (!Strings.isNullOrEmpty(merchantCore.getPayMedium())) {
            tradeData.setPayMedium(merchantCore.getPayMedium());
        }
        //根据pos查询终端列表
        List<String> posList = merchantCore.getTerminals();
        if (!CollectionUtils.isEmpty(posList)) {
            List<String> terminalList = Lists.newArrayList();
            for (String posId : posList) {
                List<TerminalInfoDTO> tempList = merchantTerminalDao
                    .queryTerByPosId(Integer.parseInt(posId));
                for (TerminalInfoDTO terminal : tempList) {
                    if (!Strings.isNullOrEmpty(terminal.getTerminalCode())) {
                        terminalList.add(terminal.getTerminalCode());
                    }
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
        } else {
            tradeData.setInnerCodeList(innerCodeList);
        }

        PageDTO<TradeData> pages = new PageDTO<TradeData>(merchantCore.getCurrentPageNum(),
            merchantCore.getPerPageSize(), tradeData);
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
    public ResultPageDTO<TradeData> queryTradeData(TradeDataDTO tradeDataDTO, int currentPageNum,
                                                   int perPageSize) {
        TradeData tradeData = new TradeData();
        if (tradeDataDTO.getPayType() != null && tradeDataDTO.getPayType().equals("03")) {
            tradeDataDTO.setPayType(null);
        }
        if (!StringUtils.isEmpty(tradeDataDTO.getStartSendTime())) {
            tradeDataDTO
                .setStartSendTime(DateUtils.getDateStartTime(tradeDataDTO.getStartSendTime()));
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
    public String queryTotalAmount(TradeDataDTO tradeDataDTO) {
        TradeData tradeData = new TradeData();
        if (tradeDataDTO.getPayType().equals("03")) {
            tradeDataDTO.setPayType(null);
        }
        if (!StringUtils.isEmpty(tradeDataDTO.getStartSendTime())) {
            tradeDataDTO
                .setStartSendTime(DateUtils.getDateStartTime(tradeDataDTO.getStartSendTime()));
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
    public List<TradeData> queryDataList(TradeDataDTO tradeDataDTO) {
        TradeData tradeData = new TradeData();
        if (tradeDataDTO.getPayType().equals("03")) {
            tradeDataDTO.setPayType(null);
        }
        if (!StringUtils.isEmpty(tradeDataDTO.getStartSendTime())) {
            tradeDataDTO
                .setStartSendTime(DateUtils.getDateStartTime(tradeDataDTO.getStartSendTime()));
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

    @Override
    public String queryByCertifyId(String certifyid) {
        String cardTotalLength = String.valueOf(certifyid.trim().length());
        String type = tradeListDAO.queryByCertifyId(certifyid, cardTotalLength);
        return type;
    }
}
