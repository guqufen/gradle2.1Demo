package net.fnsco.order.service.trade;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;

import net.fnsco.bigdata.service.dao.master.MerchantChannelDao;
import net.fnsco.bigdata.service.dao.master.MerchantPosDao;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantPos;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.DbUtil;
import net.fnsco.freamwork.comm.Md5Util;
import net.fnsco.order.service.trade.dao.TradeDataLklDAO;
import net.fnsco.order.service.trade.entity.TradeDataLklDO;

@Service
public class TradeDataLklService extends BaseService {

    private Logger             logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TradeDataLklDAO    tradeDataLklDAO;
    @Autowired
    private MerchantPosDao     merchantPosDao;
    @Autowired
    private MerchantChannelDao merchantChannelDao;

    // 分页
    public ResultPageDTO<TradeDataLklDO> page(TradeDataLklDO tradeDataLkl, Integer pageNum, Integer pageSize) {
        logger.info("开始分页查询TradeDataLklService.page, tradeDataLkl=" + tradeDataLkl.toString());
        List<TradeDataLklDO> pageList = this.tradeDataLklDAO.pageList(tradeDataLkl, pageNum, pageSize);
        Integer count = this.tradeDataLklDAO.pageListCount(tradeDataLkl);
        ResultPageDTO<TradeDataLklDO> pager = new ResultPageDTO<TradeDataLklDO>(count, pageList);
        return pager;
    }

    // 添加
    public TradeDataLklDO doAdd(TradeDataLklDO tradeDataLkl) {
        logger.info("开始添加TradeDataLklService.add,tradeDataLkl=" + tradeDataLkl.toString());
        String innerCode = "";
        String merId = tradeDataLkl.getMerId();
        //拉卡拉渠道
        if ("00".equals(tradeDataLkl.getChannelType())) {
            if (!Strings.isNullOrEmpty(tradeDataLkl.getTermId()) && !Strings.isNullOrEmpty(tradeDataLkl.getChannelType())) {
                MerchantPos merchantPos = merchantPosDao.selectOneByTerminalCodeChannelType(tradeDataLkl.getTermId(), tradeDataLkl.getChannelType());
                if (null != merchantPos) {
                    innerCode = merchantPos.getInnerCode();
                }
            } else {
                if (!Strings.isNullOrEmpty(tradeDataLkl.getMerId()) && !Strings.isNullOrEmpty(tradeDataLkl.getChannelType())) {
                    MerchantChannel channel = merchantChannelDao.selectByMerCode(tradeDataLkl.getMerId(), tradeDataLkl.getChannelType());
                    if (channel != null) {
                        innerCode = channel.getInnerCode();
                    }
                }
            }
        }
        tradeDataLkl.setInnerCode(innerCode);
        tradeDataLkl.setId(DbUtil.getUuid());
        tradeDataLkl.setCreateTime(new Date());
        tradeDataLkl.setSendTime(DateUtils.getNowDateStr());
        tradeDataLkl.setStatus("1");
        tradeDataLkl.setMd5(Md5Util.string2MD5(JSON.toJSONString(tradeDataLkl)));
        this.tradeDataLklDAO.insert(tradeDataLkl);
        return tradeDataLkl;
    }

    // 修改
    public Integer doUpdate(TradeDataLklDO tradeDataLkl, Integer loginUserId) {
        logger.info("开始修改TradeDataLklService.update,tradeDataLkl=" + tradeDataLkl.toString());
        int rows = this.tradeDataLklDAO.update(tradeDataLkl);
        return rows;
    }

    // 删除
    public Integer doDelete(TradeDataLklDO tradeDataLkl, Integer loginUserId) {
        logger.info("开始删除TradeDataLklService.delete,tradeDataLkl=" + tradeDataLkl.toString());
        int rows = this.tradeDataLklDAO.deleteById(tradeDataLkl.getId());
        return rows;
    }

    // 查询
    public TradeDataLklDO doQueryById(Integer id) {
        TradeDataLklDO obj = this.tradeDataLklDAO.getById(id);
        return obj;
    }
}