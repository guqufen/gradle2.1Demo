package net.fnsco.trading.service.third.ticket;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.FileUtils;
import net.fnsco.trading.service.third.ticket.dao.TicketSiteDAO;
import net.fnsco.trading.service.third.ticket.dto.SiteDTO;
import net.fnsco.trading.service.third.ticket.dto.SiteResultDTO;
import net.fnsco.trading.service.third.ticket.entity.TicketSiteDO;

@Service
public class TicketSiteService extends BaseService {

    private Logger        logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TicketSiteDAO ticketSiteDAO;

    public void importSite() {
        StringBuffer sb = FileUtils.readFileByLines("c:/sitejson.txt");
        SiteResultDTO result = JSON.parseObject(sb.toString(), SiteResultDTO.class);
        List<SiteDTO> list = result.getResult();
        for (SiteDTO siteDTO : list) {
            TicketSiteDO TicketSiteDO = new TicketSiteDO();
            TicketSiteDO.setCode(siteDTO.getSta_code());
            TicketSiteDO.setName(siteDTO.getSta_name());
            TicketSiteDO.setPyName(siteDTO.getSta_ename());
            TicketSiteDO.setCreateTime(new Date());
            TicketSiteDO.setLastModifyTime(new Date());
            ticketSiteDAO.insert(TicketSiteDO);
        }
    }

    public List<TicketSiteDO> querySiteList(String siteName) {
        TicketSiteDO ticketSite = new TicketSiteDO();
        ticketSite.setNameLike(siteName);
        ticketSite.setCodeLike(siteName.toUpperCase());
        ticketSite.setPyNameLike(siteName.toLowerCase());
        List<TicketSiteDO> pageList = this.ticketSiteDAO.pageList(ticketSite, 0, 1000);
        return pageList;
    }

    // 分页
    public ResultPageDTO<TicketSiteDO> page(TicketSiteDO ticketSite, Integer pageNum, Integer pageSize) {
        logger.info("开始分页查询TicketSiteService.page, ticketSite=" + ticketSite.toString());
        List<TicketSiteDO> pageList = this.ticketSiteDAO.pageList(ticketSite, pageNum, pageSize);
        Integer count = this.ticketSiteDAO.pageListCount(ticketSite);
        ResultPageDTO<TicketSiteDO> pager = new ResultPageDTO<TicketSiteDO>(count, pageList);
        return pager;
    }

    // 添加
    public TicketSiteDO doAdd(TicketSiteDO ticketSite, int loginUserId) {
        logger.info("开始添加TicketSiteService.add,ticketSite=" + ticketSite.toString());
        this.ticketSiteDAO.insert(ticketSite);
        return ticketSite;
    }

    // 修改
    public Integer doUpdate(TicketSiteDO ticketSite, Integer loginUserId) {
        logger.info("开始修改TicketSiteService.update,ticketSite=" + ticketSite.toString());
        int rows = this.ticketSiteDAO.update(ticketSite);
        return rows;
    }

    // 删除
    public Integer doDelete(TicketSiteDO ticketSite, Integer loginUserId) {
        logger.info("开始删除TicketSiteService.delete,ticketSite=" + ticketSite.toString());
        int rows = this.ticketSiteDAO.deleteById(ticketSite.getId());
        return rows;
    }

    // 查询
    public TicketSiteDO doQueryById(Integer id) {
        TicketSiteDO obj = this.ticketSiteDAO.getById(id);
        return obj;
    }
}