package net.fnsco.trading.service.third.ticket;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.trading.service.third.ticket.dao.TicketOrderPassengerDAO;
import net.fnsco.trading.service.third.ticket.entity.TicketOrderPassengerDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class TicketOrderPassengerService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private TicketOrderPassengerDAO ticketOrderPassengerDAO;

 // 分页
 public ResultPageDTO<TicketOrderPassengerDO> page(TicketOrderPassengerDO ticketOrderPassenger, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询TicketOrderPassengerService.page, ticketOrderPassenger=" + ticketOrderPassenger.toString());
     List<TicketOrderPassengerDO> pageList = this.ticketOrderPassengerDAO.pageList(ticketOrderPassenger, pageNum, pageSize);
     Integer count = this.ticketOrderPassengerDAO.pageListCount(ticketOrderPassenger);
   ResultPageDTO<TicketOrderPassengerDO> pager =  new ResultPageDTO<TicketOrderPassengerDO>(count,pageList);
     return pager;
 }

 // 添加
 public TicketOrderPassengerDO doAdd (TicketOrderPassengerDO ticketOrderPassenger,int loginUserId) {
     logger.info("开始添加TicketOrderPassengerService.add,ticketOrderPassenger=" + ticketOrderPassenger.toString());
     this.ticketOrderPassengerDAO.insert(ticketOrderPassenger);
     return ticketOrderPassenger;
 }

 // 修改
 public Integer doUpdate (TicketOrderPassengerDO ticketOrderPassenger,Integer loginUserId) {
     logger.info("开始修改TicketOrderPassengerService.update,ticketOrderPassenger=" + ticketOrderPassenger.toString());
     int rows=this.ticketOrderPassengerDAO.update(ticketOrderPassenger);
     return rows;
 }

 // 删除
 public Integer doDelete (TicketOrderPassengerDO ticketOrderPassenger,Integer loginUserId) {
     logger.info("开始删除TicketOrderPassengerService.delete,ticketOrderPassenger=" + ticketOrderPassenger.toString());
     int rows=this.ticketOrderPassengerDAO.deleteById(ticketOrderPassenger.getId());
     return rows;
 }

 // 查询
 public TicketOrderPassengerDO doQueryById (Integer id) {
     TicketOrderPassengerDO obj = this.ticketOrderPassengerDAO.getById(id);
     return obj;
 }
}