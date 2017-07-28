package net.fnsco.withhold.service.trade;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.SmsUtil;
import net.fnsco.withhold.comm.ApiConstant;
import net.fnsco.withhold.comm.ServiceConstant;
import net.fnsco.withhold.service.trade.dao.TradeDataDAO;
import net.fnsco.withhold.service.trade.dao.WithholdInfoDAO;
import net.fnsco.withhold.service.trade.entity.TradeDataDO;
import net.fnsco.withhold.service.trade.entity.WithholdInfoDO;
import net.fnsco.withhold.service.trade.pay.ainpay.ANOrderPaymentService;

@Service
public class TradeDataService extends BaseService {

    private Logger       logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TradeDataDAO tradeDataDAO;

    // 查询
    public TradeDataDO doQueryById(Integer id) {
        TradeDataDO obj = this.tradeDataDAO.getById(id);
        return obj;
    }

    public ResultPageDTO<TradeDataDO> page(TradeDataDO tradeData, Integer pageNum, Integer pageSize) {
        logger.info("开始分页查询TradeDataService.page, tradeData=" + tradeData.toString());
        List<TradeDataDO> pageList = this.tradeDataDAO.pageList(tradeData, pageNum, pageSize);
        Integer count = this.tradeDataDAO.pageListCount(tradeData);
        ResultPageDTO<TradeDataDO> pager = new ResultPageDTO<TradeDataDO>(count, pageList);
        return pager;
    }
}