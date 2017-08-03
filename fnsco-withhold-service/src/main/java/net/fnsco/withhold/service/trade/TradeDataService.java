package net.fnsco.withhold.service.trade;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.constants.CoreConstants;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.SmsUtil;
import net.fnsco.withhold.comm.ApiConstant;
import net.fnsco.withhold.comm.ServiceConstant;
import net.fnsco.withhold.service.trade.dao.TradeDataDAO;
import net.fnsco.withhold.service.trade.entity.TradeDataDO;

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

    public TradeDataDO findByOrderSn(String orderId) {
        TradeDataDO obj = this.tradeDataDAO.getByOrderSn(orderId);
        return obj;
    }

    public void update(TradeDataDO tradeData) {
        this.tradeDataDAO.update(tradeData);
    }

    public ResultPageDTO<TradeDataDO> page(TradeDataDO tradeData, Integer pageNum, Integer pageSize) {
        logger.info("开始分页查询TradeDataService.page, tradeData=" + tradeData.toString());
        List<TradeDataDO> pageList = this.tradeDataDAO.pageList(tradeData, pageNum, pageSize);
        Integer count = this.tradeDataDAO.pageListCount(tradeData);
        ResultPageDTO<TradeDataDO> pager = new ResultPageDTO<TradeDataDO>(count, pageList);
        return pager;
    }

    //修改状态
    public ResultDTO repair(TradeDataDO tradeData) {
        TradeDataDO dto = new TradeDataDO();
        dto.setId(tradeData.getId());
        dto.setStatus(tradeData.getStatus());
        TradeDataDO tradeDataDO = tradeDataDAO.getById(tradeData.getId());
        if(tradeDataDO==null){
            return ResultDTO.fail();
        }
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String datestr= df.format(date);
        if(Strings.isNullOrEmpty(tradeDataDO.getTxnTime())){
            return ResultDTO.fail();
        }
        String time=tradeDataDO.getTxnTime().substring(0, 8);
        int txTime=Integer.valueOf(time);
        int daTime=Integer.valueOf(datestr);
        if(txTime>=daTime){
            return ResultDTO.fail(ApiConstant.WEB_TIME_ERROR);
        }
        int num = this.tradeDataDAO.update(dto);
        return ResultDTO.success();
    }
}