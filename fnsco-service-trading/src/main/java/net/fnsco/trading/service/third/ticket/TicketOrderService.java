package net.fnsco.trading.service.third.ticket;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.trading.service.third.ticket.dao.TicketOrderDAO;
import net.fnsco.trading.service.third.ticket.entity.TicketOrderDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class TicketOrderService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private TicketOrderDAO ticketOrderDAO;

 // 分页
 public ResultPageDTO<TicketOrderDO> page(TicketOrderDO ticketOrder, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询TicketOrderService.page, ticketOrder=" + ticketOrder.toString());
     List<TicketOrderDO> pageList = this.ticketOrderDAO.pageList(ticketOrder, pageNum, pageSize);
     Integer count = this.ticketOrderDAO.pageListCount(ticketOrder);
   ResultPageDTO<TicketOrderDO> pager =  new ResultPageDTO<TicketOrderDO>(count,pageList);
     return pager;
 }

 // 添加
 public TicketOrderDO doAdd (TicketOrderDO ticketOrder,int loginUserId) {
     logger.info("开始添加TicketOrderService.add,ticketOrder=" + ticketOrder.toString());
     this.ticketOrderDAO.insert(ticketOrder);
     return ticketOrder;
 }

 // 修改
 public Integer doUpdate (TicketOrderDO ticketOrder,Integer loginUserId) {
     logger.info("开始修改TicketOrderService.update,ticketOrder=" + ticketOrder.toString());
     int rows=this.ticketOrderDAO.update(ticketOrder);
     return rows;
 }

 // 删除
 public Integer doDelete (TicketOrderDO ticketOrder,Integer loginUserId) {
     logger.info("开始删除TicketOrderService.delete,ticketOrder=" + ticketOrder.toString());
     int rows=this.ticketOrderDAO.deleteById(ticketOrder.getId());
     return rows;
 }

 // 查询
 public TicketOrderDO doQueryById (Integer id) {
     TicketOrderDO obj = this.ticketOrderDAO.getById(id);
     return obj;
 }
}