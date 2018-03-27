package net.fnsco.bigdata.service.modules.trade;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.beust.jcommander.internal.Maps;
import com.google.common.base.Strings;

import net.fnsco.bigdata.api.dto.TradeDataDTO;
import net.fnsco.bigdata.api.trade.TradeDataService;
import net.fnsco.bigdata.service.dao.master.MerchantChannelDao;
import net.fnsco.bigdata.service.dao.master.MerchantCoreDao;
import net.fnsco.bigdata.service.dao.master.MerchantPosDao;
import net.fnsco.bigdata.service.dao.master.trade.TradeDataDAO;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.bigdata.service.domain.MerchantPos;
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
    private MerchantCoreDao     merchantCoreDao;
    @Autowired
    private MerchantPosDao      merchantPosDao;

    /**
     * 保存交易流水
     */
    @Transactional
    public boolean saveTradeData(TradeDataDTO tradeData) {
        //ServiceConstant.STR_1.equals(tradeData.getValidate()) && 
    	logger.error("交易流水:"+tradeData.getOrderNo());
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
            if (!Strings.isNullOrEmpty(tradeData.getTermId()) && !Strings.isNullOrEmpty(tradeData.getChannelType())) {
                MerchantPos merchantPos = merchantPosDao.selectOneByTerminalCodeChannelType(tradeData.getTermId(), tradeData.getChannelType());
                if (null != merchantPos) {
                    innerCode = merchantPos.getInnerCode();
                }
            } else {
                if (!Strings.isNullOrEmpty(tradeData.getMerId()) && !Strings.isNullOrEmpty(tradeData.getChannelType())) {
                    MerchantChannel channel = merchantChannelDao.selectByMerCode(tradeData.getMerId(), tradeData.getChannelType());
                    if (channel != null) {
                        innerCode = channel.getInnerCode();
                    }
                }
            }
        } else if ("02".equals(tradeData.getChannelType())) {//01浦发02爱农03法奈昇
            if (!Strings.isNullOrEmpty(tradeData.getMerId()) && !Strings.isNullOrEmpty(tradeData.getChannelType())) {

                MerchantChannel channel = merchantChannelDao.selectByMerCode(tradeData.getInnerCode(), tradeData.getChannelType());
                if (channel != null) {
                    innerCode = channel.getInnerCode();
                } else {
                    logger.error("内部商户号没有渠道对应:" + tradeData.getInnerCode());
                }
            }
        } else if ("01".equals(tradeData.getChannelType()) || "03".equals(tradeData.getChannelType())) {//01浦发02爱农03法奈昇
            MerchantChannel channel = merchantChannelDao.selectByMerCode(tradeData.getMerId(), tradeData.getChannelType());
            if (channel != null) {
                innerCode = channel.getInnerCode();
            }
        } else {
            MerchantChannel channel = merchantChannelDao.selectByMerCode(tradeData.getMerId(), tradeData.getChannelType());
            if (channel != null) {
                innerCode = channel.getInnerCode();
            }
        }
        
        //插入表之前，需要通过 终端号+金额+日期+渠道内部商户号+参考号+交易类型1消费2撤销  去判断是否已经插入过数据，如果已经插入，则不再插入
        TradeData tradeDateCondition  = new TradeData();
        tradeDateCondition.setAmt(tradeData.getAmt());
        tradeDateCondition.setTermId(tradeData.getTermId());
        String timeStamp = tradeData.getTimeStamp();
		if(!Strings.isNullOrEmpty(timeStamp) && timeStamp.length()>7) {
        	tradeDateCondition.setTimeStampBack(timeStamp.substring(0, 8));//年月日相同就可以
        }
        tradeDateCondition.setInnerCode(innerCode);
        tradeDateCondition.setReferNo(tradeData.getReferNo());
        tradeDateCondition.setTxnType(tradeData.getTxnType());
        int totalNum = tradeListDAO.queryTotalByCondition(tradeDateCondition);
        if(totalNum > 0) {
        	logger.warn("交易流水已存在!不再操作" + tradeData.getOrderNo());
        	return true;
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
            if (Strings.isNullOrEmpty(tradeData.getMerId()) || Strings.isNullOrEmpty(tradeData.getChannelTermCode())) {
                logger.info("渠道终端号或商户号为空，不能查找到原交易");
                return false;
            }
            TradeData tmp = tradeListDAO.selectByCMT(tradeData);
            if (tmp == null) {
                logger.info("没有找到原交易记录merId=[" + tradeData.getMerId() + "],channerTermCode=[" + tradeData.getChannelTermCode() + "],referNo=[" + tradeData.getOrgMerOrderId() + "]");
                return false;
            }
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
        MerchantChannel merchantChannel = merchantChannelDao.selectByMerCode(merId, tradeData.getSource());
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
     * 条件分页查询
     * (non-Javadoc)
     * @see net.fnsco.bigdata.api.trade.TradeDataService#queryMerchantCore(net.fnsco.order.api.dto.TradeDataDTO, int, int)
     * @auth tangliang
     * @date 2017年6月28日 下午5:13:54
     */
    @Override
    public ResultPageDTO<TradeData> queryTradeData(TradeDataDTO tradeDataDTO, int currentPageNum, int perPageSize,Integer agentId) {
        TradeData tradeData = new TradeData();
        //查询agentid下所有内部商户号信息
        if(agentId != null){
        	 List<String> innerCodeList = merchantCoreDao.queryAllInnercodeByAgentId(agentId);
             tradeData.setInnerCodeList(innerCodeList);	
        }
       
        
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
    public String queryTotalAmount(TradeDataDTO tradeDataDTO,Integer agentId) {
        TradeData tradeData = new TradeData();
        //查询agentid下所有内部商户号信息
        if(agentId != null){
        	List<String> innerCodeList = merchantCoreDao.queryAllInnercodeByAgentId(agentId);
            tradeData.setInnerCodeList(innerCodeList);
        }
        
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
    public List<TradeData> queryDataList(TradeDataDTO tradeDataDTO) {
        TradeData tradeData = new TradeData();
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
        List<TradeData> datas = tradeListDAO.queryByAllCondition(tradeData);
        Set<String> sqlPos = new HashSet<String>();
        Set<String> sqlMer = new HashSet<String>();
        for (TradeData tradeData2 : datas) {
        	String termId = tradeData2.getTermId();
        	if(!Strings.isNullOrEmpty(termId)) {
        		sqlPos.add(termId);
        	}
        	String InnerCode = tradeData2.getInnerCode();
        	if(!Strings.isNullOrEmpty(InnerCode)) {
        		sqlMer.add(InnerCode);
        	}
        }
        String[] toBeStoredPos = sqlPos.toArray(new String[sqlPos.size()]);    
        Map<String,MerchantPos> posMap = Maps.newHashMap();
        if(toBeStoredPos.length!=0) {
        	List<MerchantPos> merchantPosList =  merchantPosDao.selectByTermId(toBeStoredPos);
        	for(MerchantPos pos : merchantPosList) {
        		if(!Strings.isNullOrEmpty(pos.getChannelTerminalCode())) {
        			posMap.put(pos.getChannelTerminalCode(), pos);
        		}
        		if(!Strings.isNullOrEmpty(pos.getQrChannelTerminalCode())) {
        			posMap.put(pos.getQrChannelTerminalCode(), pos);
        		} 
        	}
        }
        String[] toBeStoredMer = sqlMer.toArray(new String[sqlMer.size()]); 
        Map<String,String> mercMap = Maps.newHashMap();
        if(toBeStoredMer.length!=0) {
        	List<MerchantCore> coreList = merchantCoreDao.selectListByInnerCode(toBeStoredMer);
        	for(MerchantCore core : coreList) {
        		String merName = core.getMerName();
        		mercMap.put(core.getInnerCode(), merName);
        	}
        }
        for(TradeData tradeData2 : datas) {
        	String term = tradeData2.getTermId();
        	MerchantPos pos = posMap.get(term);
        	if(pos!=null) {
        		tradeData2.setSnCode(pos.getSnCode());
        	}
        	String InnerCode = tradeData2.getInnerCode();
        	String merName = mercMap.get(InnerCode);
        	if(!Strings.isNullOrEmpty(merName)) {
        		tradeData2.setMerName(merName);
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
    
    /**
     * 通过渠道终端号商户号和订单号查找交易
     * @param record
     * @return
     */
   public TradeData selectByCMT(TradeData record){
	   return tradeListDAO.selectByCMT(record);
   }
   
   /**
    * 
    */
   public int updateByPrimaryKeySelective(TradeData record){
	   return tradeListDAO.updateByPrimaryKey(record);
   }
}
