package net.fnsco.trading.service.order;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.trading.service.order.dao.TradeOrderExtDAO;
import net.fnsco.trading.service.order.entity.TradeOrderExtDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class TradeOrderExtService extends BaseService {

    private Logger           logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TradeOrderExtDAO tradeOrderExtDAO;

    // 分页
    public ResultPageDTO<TradeOrderExtDO> page(TradeOrderExtDO tradeOrderExt, Integer pageNum, Integer pageSize) {
        logger.info("开始分页查询TradeOrderExtService.page, tradeOrderExt=" + tradeOrderExt.toString());
        List<TradeOrderExtDO> pageList = this.tradeOrderExtDAO.pageList(tradeOrderExt, pageNum, pageSize);
        Integer count = this.tradeOrderExtDAO.pageListCount(tradeOrderExt);
        ResultPageDTO<TradeOrderExtDO> pager = new ResultPageDTO<TradeOrderExtDO>(count, pageList);
        return pager;
    }

    // 添加
    public TradeOrderExtDO doAdd(TradeOrderExtDO tradeOrderExt) {
        logger.info("开始添加TradeOrderExtService.add,tradeOrderExt=" + tradeOrderExt.toString());
        this.tradeOrderExtDAO.insert(tradeOrderExt);
        return tradeOrderExt;
    }

    // 修改
    public Integer doUpdate(TradeOrderExtDO tradeOrderExt, Integer loginUserId) {
        logger.info("开始修改TradeOrderExtService.update,tradeOrderExt=" + tradeOrderExt.toString());
        int rows = this.tradeOrderExtDAO.update(tradeOrderExt);
        return rows;
    }

    // 删除
    public Integer doDelete(TradeOrderExtDO tradeOrderExt, Integer loginUserId) {
        logger.info("开始删除TradeOrderExtService.delete,tradeOrderExt=" + tradeOrderExt.toString());
        int rows = this.tradeOrderExtDAO.deleteById(tradeOrderExt.getId());
        return rows;
    }

    // 查询
    public TradeOrderExtDO doQueryById(Integer id) {
        TradeOrderExtDO obj = this.tradeOrderExtDAO.getById(id);
        return obj;
    }
}