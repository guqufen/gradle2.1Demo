package net.fnsco.risk.service.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.risk.service.report.dao.ReportInfoDAO;
import net.fnsco.risk.service.report.dao.ReportRepaymentHistoryDAO;
import net.fnsco.risk.service.report.dao.UserMercRelDAO;
import net.fnsco.risk.service.report.entity.ReportInfoDO;
import net.fnsco.risk.service.report.entity.ReportRepaymentHistoryDO;
import net.fnsco.risk.service.report.entity.UserMercRelDO;
import net.fnsco.risk.service.report.entity.YearReportDO;
import net.fnsco.risk.service.sys.dao.WebUserOuterDAO;
import net.fnsco.risk.service.sys.entity.WebUserOuterDO;

@Service
public class ReportService extends BaseService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ReportInfoDAO reportInfoDAO;
    @Autowired
    private WebUserOuterDAO webUserOuterDAO;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Environment ev;
    @Autowired
    private ReportRepaymentHistoryDAO reportRepaymentHistoryDAO;
    @Autowired
    private UserMercRelDAO userMercRelDAO;
    
    //前台分页查询风控报告列表
    public ResultPageDTO<ReportInfoDO> page(ReportInfoDO reportInfoDO, Integer pageNum, Integer pageSize) {
        List<ReportInfoDO> pageList = this.reportInfoDAO.pageList(reportInfoDO, pageNum, pageSize);
        Integer count = this.reportInfoDAO.pageListCount(reportInfoDO);
        ResultPageDTO<ReportInfoDO> pager = new ResultPageDTO<ReportInfoDO>(count, pageList);
        return pager;
    }
    //后台分页查询风控报告列表
    public ResultPageDTO<ReportInfoDO> pageBack(ReportInfoDO reportInfoDO, Integer pageNum, Integer pageSize) {
        List<ReportInfoDO> pageList = this.reportInfoDAO.pageListBack(reportInfoDO, pageNum, pageSize);
        for( ReportInfoDO li:pageList){
            //根据innerCode查询用户信息
            UserMercRelDO dto=userMercRelDAO.getByInnerCode(li.getInnerCode());
            li.setWebUserOuterId(dto.getWebUserOuterId());
        }
        Integer count = this.reportInfoDAO.pageListCountBack(reportInfoDO);
        ResultPageDTO<ReportInfoDO> pager = new ResultPageDTO<ReportInfoDO>(count, pageList);
        return pager;
    }
    //查询12个月风控历史
    public ResultDTO queryYearReport(Integer merchantId){
        List<YearReportDO> list=new ArrayList<YearReportDO>();
        ReportInfoDO reportInfoDO=reportInfoDAO.getById(merchantId);
        //判断风控报告的状态
        if(reportInfoDO.getStatus()!=1){
            return ResultDTO.fail("风控报告状态不正常");
        }
        //当前时间 
        ReportRepaymentHistoryDO dto=reportRepaymentHistoryDAO.getByReportId(merchantId);
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
    public ResultDTO queryReportDetails(Integer merchantId) {
        ReportInfoDO reportInfoDO=reportInfoDAO.getById(merchantId);
        return ResultDTO.success(reportInfoDO);
    }
    //通知后台人员
    public ResultDTO backPersonnelMes(Integer userId,Integer merchantId) {
        //查询用户的商户名称
        ReportInfoDO reportInfoDO=reportInfoDAO.getById(merchantId);
        WebUserOuterDO dto=webUserOuterDAO.getById(userId);
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            System.out.println(ev.getProperty("username"));
            helper.setFrom("goggb@qq.com");
            helper.setTo("782430551@qq.com");
            helper.setSubject("风控报告");
            StringBuffer sb = new StringBuffer();
            sb.append(dto.getName()+"申请生成关于"+reportInfoDO.getMerName()+"的风控报告,请尽快处理"+"<a style='font-size:50px;' href='http://www.w3school.com.cn'>W3School</a>");
            helper.setText(sb.toString(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
        return ResultDTO.success();
    }
    //通知用户
    public ResultDTO headPersonnelMes(Integer userId,Integer merchantId) {
        ReportInfoDO reportInfoDO=reportInfoDAO.getById(merchantId);
        WebUserOuterDO dto=webUserOuterDAO.getById(userId);
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            System.out.println(ev.getProperty("username"));
            helper.setFrom("goggb@qq.com");
            helper.setTo("782430551@qq.com");
            helper.setSubject("风控报告");
            StringBuffer sb = new StringBuffer();
            sb.append("<h1>"+dto.getName()+"</h1><br>").append("关于"+reportInfoDO.getMerName()+"的'风控+'报告已经生成!点击查看"+"<a style='font-size:50px;' href='http://www.w3school.com.cn'>W3School</a>");
            helper.setText(sb.toString(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
        return ResultDTO.success();
    }
    //更新风控报告状态
    public ResultDTO updateReportStatus(ReportInfoDO reportInfoDO) {
        int num=reportInfoDAO.update(reportInfoDO);
        return ResultDTO.success();
    }
    
}
