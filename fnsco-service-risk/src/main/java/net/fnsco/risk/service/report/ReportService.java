package net.fnsco.risk.service.report;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.risk.service.report.dao.ReportInfoDAO;
import net.fnsco.risk.service.report.entity.ReportInfoDO;

@Service
public class ReportService extends BaseService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ReportInfoDAO reportInfoDAO;
    //分页查询风控报告列表
    public ResultPageDTO<ReportInfoDO> page(ReportInfoDO reportInfoDO, Integer pageNum, Integer pageSize) {
        List<ReportInfoDO> pageList = this.reportInfoDAO.pageList(reportInfoDO, pageNum, pageSize);
        Integer count = this.reportInfoDAO.pageListCount(reportInfoDO);
        ResultPageDTO<ReportInfoDO> pager = new ResultPageDTO<ReportInfoDO>(count, pageList);
        return pager;
    }
}
