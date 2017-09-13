package net.fnsco.risk.service.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.risk.service.report.dao.ReportInfoDAO;
import net.fnsco.risk.service.report.dao.ReportRepaymentHistoryDAO;
import net.fnsco.risk.service.report.entity.ReportInfoDO;
import net.fnsco.risk.service.report.entity.ReportRepaymentHistoryDO;
import net.fnsco.risk.service.report.entity.YearReportDO;
import net.fnsco.risk.web.report.JavaMailSender;

@Service
public class ReportService extends BaseService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ReportInfoDAO reportInfoDAO;
//    @Autowired
//    private JavaMailSender mailSender;

    @Autowired
    private ReportRepaymentHistoryDAO reportRepaymentHistoryDAO;
    //分页查询风控报告列表
    public ResultPageDTO<ReportInfoDO> page(ReportInfoDO reportInfoDO, Integer pageNum, Integer pageSize) {
        List<ReportInfoDO> pageList = this.reportInfoDAO.pageList(reportInfoDO, pageNum, pageSize);
        Integer count = this.reportInfoDAO.pageListCount(reportInfoDO);
        ResultPageDTO<ReportInfoDO> pager = new ResultPageDTO<ReportInfoDO>(count, pageList);
        return pager;
    }
    //查询12个月风控历史
    public ResultDTO queryYearReport(Integer id){
        List<YearReportDO> list=new ArrayList<YearReportDO>();
        ReportInfoDO reportInfoDO=reportInfoDAO.getById(id);
        //判断风控报告的状态
        if(reportInfoDO.getStatus()!=1){
            return ResultDTO.fail("风控报告状态不正常");
        }
        //当前时间 
        ReportRepaymentHistoryDO dto=reportRepaymentHistoryDAO.getByReportId(id);
        Date date=reportInfoDO.getLastModifyTime();
        for (int j=0 ; j<12; j++) {  
            YearReportDO yearReportDO=new YearReportDO();
            if(j==0){
                yearReportDO.setTurnover(dto.getMonthOne());
                yearReportDO.setDate(handleDate(date,-6));
                list.add(yearReportDO);
            }
            if(j==1){
                yearReportDO.setTurnover(dto.getMonthTwo());
                yearReportDO.setDate(handleDate(date,-5));
                list.add(yearReportDO);
            }
            if(j==2){
                yearReportDO.setTurnover(dto.getMonthThree());
                yearReportDO.setDate(handleDate(date,-4));
                list.add(yearReportDO);
            }
            if(j==3){
                yearReportDO.setTurnover(dto.getMonthFore());
                yearReportDO.setDate(handleDate(date,-3));
                list.add(yearReportDO);
            }
            if(j==4){
                yearReportDO.setTurnover(dto.getMonthFive());
                yearReportDO.setDate(handleDate(date,-2));
                list.add(yearReportDO);
            }
            if(j==5){
                yearReportDO.setTurnover(dto.getMonthSix());
                yearReportDO.setDate(handleDate(date,-1));
                list.add(yearReportDO);
            }
            if(j==6){
                yearReportDO.setTurnover(dto.getMonthSeven());
                yearReportDO.setDate(handleDate(date,0));
                list.add(yearReportDO);
            }
            if(j==7){
                yearReportDO.setTurnover(dto.getMonthEight());
                yearReportDO.setDate(handleDate(date,1));
                list.add(yearReportDO);
            }
            if(j==8){
                yearReportDO.setTurnover(dto.getMonthNine());
                yearReportDO.setDate(handleDate(date,2));
                list.add(yearReportDO);
            }
            if(j==9){
                yearReportDO.setTurnover(dto.getMonthTen());
                yearReportDO.setDate(handleDate(date,3));
                list.add(yearReportDO);
            }
            if(j==10){
                yearReportDO.setTurnover(dto.getMonthEleven());
                yearReportDO.setDate(handleDate(date,4));
                list.add(yearReportDO);
            }
            if(j==11){
                yearReportDO.setTurnover(dto.getMonthTwelve());
                yearReportDO.setDate(handleDate(date,5));
                list.add(yearReportDO);
            }
        }  
        return ResultDTO.success(list);
    }
    private String handleDate(Date date,Integer num){
        Calendar calendar = Calendar.getInstance();    
        calendar.setTime(date);    
        calendar.add(Calendar.MONTH, num);
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM");
        String dateStr=sdf.format(calendar.getTime());
        return dateStr;
    }
    //查询风控报告明细
    public ResultDTO queryReportDetails(Integer id) {
        ReportInfoDO reportInfoDO=reportInfoDAO.getById(id);
        return ResultDTO.success(reportInfoDO);
    }
    
}
