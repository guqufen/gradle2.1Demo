package net.fnsco.trading.service.withdraw;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.CodeUtil;
import net.fnsco.trading.service.account.AppAccountBalanceService;
import net.fnsco.trading.service.withdraw.dao.TradeWithdrawDAO;
import net.fnsco.trading.service.withdraw.dto.MonthWithdrawCountDTO;
import net.fnsco.trading.service.withdraw.entity.TradeWithdrawDO;

@Service
public class TradeWithdrawService extends BaseService {

    private Logger                   logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TradeWithdrawDAO         tradeWithdrawDAO;
    @Autowired
    private AppAccountBalanceService appAccountBalanceService;

    // 分页
    public ResultPageDTO<TradeWithdrawDO> page(TradeWithdrawDO tradeWithdraw, Integer pageNum, Integer pageSize) {
        logger.info("开始分页查询TradeWithdrawService.page, tradeWithdraw=" + tradeWithdraw.toString());
        List<TradeWithdrawDO> pageList = this.tradeWithdrawDAO.pageList(tradeWithdraw, pageNum, pageSize);
        Integer count = this.tradeWithdrawDAO.pageListCount(tradeWithdraw);
        ResultPageDTO<TradeWithdrawDO> pager = new ResultPageDTO<TradeWithdrawDO>(count, pageList);
        return pager;
    }

    // 添加

    //如果方法运行时，已经存在一个事物中，那么加入该事物，否则为自己创建一个新事物。  
    @Transactional(propagation = Propagation.REQUIRED)
    //@Transactional(isolation = Isolation.DEFAULT)  
    public TradeWithdrawDO doAdd(TradeWithdrawDO tradeWithdraw) {
        logger.info("开始添加TradeWithdrawService.add,tradeWithdraw=" + tradeWithdraw.toString());
        tradeWithdraw.setCreateTime(new Date());
        tradeWithdraw.setUpdateTime(new Date());
        if( Strings.isNullOrEmpty(tradeWithdraw.getOrderNo())){
        	tradeWithdraw.setOrderNo(CodeUtil.generateOrderCode(""));
        }
    	
        //设置账户金额
        //扣除或增加账户余额
        this.tradeWithdrawDAO.insert(tradeWithdraw);
        return tradeWithdraw;
    }

    // 修改
    public Integer doUpdate(TradeWithdrawDO tradeWithdraw) {
        logger.info("开始修改TradeWithdrawService.update,tradeWithdraw=" + tradeWithdraw.toString());
        int rows = this.tradeWithdrawDAO.update(tradeWithdraw);
        return rows;
    }

    public Integer doUpdateForSuccess(TradeWithdrawDO tradeWithdraw) {
        logger.info("开始修改TradeWithdrawService.update,tradeWithdraw=" + tradeWithdraw.toString());
        int rows = this.tradeWithdrawDAO.update(tradeWithdraw);
        BigDecimal fund = BigDecimal.ZERO.subtract(tradeWithdraw.getAmount());
        appAccountBalanceService.doFrozenBalance(tradeWithdraw.getAppUserId(), fund);
        return rows;
    }

    @Transactional
    public Integer updateFund(TradeWithdrawDO tradeWithdraw) {
        logger.info("开始修改TradeWithdrawService.update,tradeWithdraw=" + tradeWithdraw.toString());
        int rows = this.tradeWithdrawDAO.update(tradeWithdraw);
        BigDecimal fund = BigDecimal.ZERO.subtract(tradeWithdraw.getAmount());
        appAccountBalanceService.updateFund(tradeWithdraw.getAppUserId(), fund);
        return rows;
    }
    // 删除
    public Integer doDelete(TradeWithdrawDO tradeWithdraw, Integer loginUserId) {
        logger.info("开始删除TradeWithdrawService.delete,tradeWithdraw=" + tradeWithdraw.toString());
        int rows = this.tradeWithdrawDAO.deleteById(tradeWithdraw.getId());
        return rows;
    }

    // 查询
    public TradeWithdrawDO doQueryById(Integer id) {
        TradeWithdrawDO obj = this.tradeWithdrawDAO.getById(id);
        return obj;
    }

    // 查询
    public TradeWithdrawDO doQueryByOrderNo(String orderNo) {
        TradeWithdrawDO obj = this.tradeWithdrawDAO.getByOrderNo(orderNo);
        return obj;
    }

    public List<MonthWithdrawCountDTO> doQueryTotalAmountGroupByMouth(Integer appUserId, String tradeMonth, Integer status) {
        return tradeWithdrawDAO.queryTotalAmount(appUserId, tradeMonth, status);
    }
    
    /**
     * 按交易类型查询正在进行的交易列表，便于定时查询交易状态(按照时间大到小)
     * @param start：查询开始时间
     * @param type：交易类型
     * @return
     */
    public List<TradeWithdrawDO> queryOngoing(Date start, Integer type){
    	return tradeWithdrawDAO.queryOngoing(start, type);
    }
}