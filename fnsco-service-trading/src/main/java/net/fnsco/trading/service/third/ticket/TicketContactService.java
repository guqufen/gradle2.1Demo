package net.fnsco.trading.service.third.ticket;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.trading.service.third.ticket.dao.TicketContactDAO;
import net.fnsco.trading.service.third.ticket.entity.TicketContactDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class TicketContactService extends BaseService {

    private Logger           logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TicketContactDAO ticketContactDAO;

    // 分页
    public ResultPageDTO<TicketContactDO> page(TicketContactDO ticketContact, Integer pageNum, Integer pageSize) {
        logger.info("开始分页查询TicketContactService.page, ticketContact=" + ticketContact.toString());
        List<TicketContactDO> pageList = this.ticketContactDAO.pageList(ticketContact, pageNum, pageSize);
        Integer count = this.ticketContactDAO.pageListCount(ticketContact);
        ResultPageDTO<TicketContactDO> pager = new ResultPageDTO<TicketContactDO>(count, pageList);
        return pager;
    }

    // 添加
    public TicketContactDO doAdd(TicketContactDO ticketContact) {
        logger.info("开始添加TicketContactService.add,ticketContact=" + ticketContact.toString());
        this.ticketContactDAO.insert(ticketContact);
        return ticketContact;
    }

    // 修改
    public Integer doUpdate(TicketContactDO ticketContact, Integer loginUserId) {
        logger.info("开始修改TicketContactService.update,ticketContact=" + ticketContact.toString());
        int rows = this.ticketContactDAO.update(ticketContact);
        return rows;
    }

    // 删除
    public Integer doDelete(TicketContactDO ticketContact) {
        logger.info("开始删除TicketContactService.delete,ticketContact=" + ticketContact.toString());
        int rows = this.ticketContactDAO.deleteById(ticketContact.getId());
        return rows;
    }

    // 查询
    public TicketContactDO doQueryById(Integer id) {
        TicketContactDO obj = this.ticketContactDAO.getById(id);
        return obj;
    }
}