package net.fnsco.risk.service.report;

import java.math.BigDecimal;
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
import org.springframework.transaction.annotation.Transactional;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.risk.service.report.dao.ReportInfoDAO;
import net.fnsco.risk.service.report.dao.ReportRepaymentHistoryDAO;
import net.fnsco.risk.service.report.entity.ReportInfoDO;
import net.fnsco.risk.service.report.entity.ReportRepaymentHistoryDO;
import net.fnsco.risk.service.report.entity.YearReportDO;
import net.fnsco.risk.service.sys.dao.WebUserOuterDAO;
import net.fnsco.risk.service.sys.entity.WebUserOuterDO;

@Service
public class ReportService extends BaseService {
    private Logger                    logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ReportInfoDAO             reportInfoDAO;
    @Autowired
    private WebUserOuterDAO           webUserOuterDAO;
    @Autowired
    private JavaMailSender            mailSender;
    @Autowired
    private Environment               ev;
    @Autowired
    private ReportRepaymentHistoryDAO reportRepaymentHistoryDAO;

    //前台分页查询风控报告列表
    public ResultPageDTO<ReportInfoDO> page(ReportInfoDO reportInfoDO, Integer pageNum, Integer pageSize) {
        List<ReportInfoDO> pageList = this.reportInfoDAO.pageList(reportInfoDO, pageNum, pageSize);
//        for (ReportInfoDO li : pageList) {
//            //根据innerCode查询用户信息
//            
//            //li.getInnerCode()
//        }
        Integer count = this.reportInfoDAO.pageListCount(reportInfoDO);
        ResultPageDTO<ReportInfoDO> pager = new ResultPageDTO<ReportInfoDO>(count, pageList);
        return pager;
    }

    //后台分页查询风控报告列表
    public ResultPageDTO<ReportInfoDO> pageBack(ReportInfoDO reportInfoDO, Integer pageNum, Integer pageSize) {
        List<ReportInfoDO> pageList = this.reportInfoDAO.pageListBack(reportInfoDO, pageNum, pageSize);
        Integer count = this.reportInfoDAO.pageListCountBack(reportInfoDO);
        ResultPageDTO<ReportInfoDO> pager = new ResultPageDTO<ReportInfoDO>(count, pageList);
        return pager;
    }

    //查询12个月风控历史
    public ResultDTO queryYearReport(Integer userId, Integer merchantId) {
        List<YearReportDO> list = new ArrayList<YearReportDO>();
        ReportInfoDO reportInfoDO = reportInfoDAO.getById(userId);
        //判断风控报告的状态
        if (reportInfoDO.getStatus() != 1) {
            return ResultDTO.fail("风控报告状态不正常");
        }
        //当前时间 
        ReportRepaymentHistoryDO dto = reportRepaymentHistoryDAO.getByReportId(merchantId);
        //如果查出来的月度营业额为空，则直接返回到页面；否则会报空指针异常
        if (dto == null) {
            return ResultDTO.success(list);
        }
        Date date = reportInfoDO.getLastModifyTime();
        for (int j = 0; j < 12; j++) {
            YearReportDO yearReportDO = new YearReportDO();
            if (j == 0) {
                yearReportDO.setTurnover(dto.getMonthOne());
                yearReportDO.setDate(handleDate(date, -6));
                list.add(yearReportDO);
            }
            if (j == 1) {
                yearReportDO.setTurnover(dto.getMonthTwo());
                yearReportDO.setDate(handleDate(date, -5));
                list.add(yearReportDO);
            }
            if (j == 2) {
                yearReportDO.setTurnover(dto.getMonthThree());
                yearReportDO.setDate(handleDate(date, -4));
                list.add(yearReportDO);
            }
            if (j == 3) {
                yearReportDO.setTurnover(dto.getMonthFore());
                yearReportDO.setDate(handleDate(date, -3));
                list.add(yearReportDO);
            }
            if (j == 4) {
                yearReportDO.setTurnover(dto.getMonthFive());
                yearReportDO.setDate(handleDate(date, -2));
                list.add(yearReportDO);
            }
            if (j == 5) {
                yearReportDO.setTurnover(dto.getMonthSix());
                yearReportDO.setDate(handleDate(date, -1));
                list.add(yearReportDO);
            }
            if (j == 6) {
                yearReportDO.setTurnover(dto.getMonthSeven());
                yearReportDO.setDate(handleDate(date, 0));
                list.add(yearReportDO);
            }
            if (j == 7) {
                yearReportDO.setTurnover(dto.getMonthEight());
                yearReportDO.setDate(handleDate(date, 1));
                list.add(yearReportDO);
            }
            if (j == 8) {
                yearReportDO.setTurnover(dto.getMonthNine());
                yearReportDO.setDate(handleDate(date, 2));
                list.add(yearReportDO);
            }
            if (j == 9) {
                yearReportDO.setTurnover(dto.getMonthTen());
                yearReportDO.setDate(handleDate(date, 3));
                list.add(yearReportDO);
            }
            if (j == 10) {
                yearReportDO.setTurnover(dto.getMonthEleven());
                yearReportDO.setDate(handleDate(date, 4));
                list.add(yearReportDO);
            }
            if (j == 11) {
                yearReportDO.setTurnover(dto.getMonthTwelve());
                yearReportDO.setDate(handleDate(date, 5));
                list.add(yearReportDO);
            }
        }
        return ResultDTO.success(list);
    }

    private String handleDate(Date date, Integer num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, num);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String dateStr = sdf.format(calendar.getTime());
        return dateStr;
    }

    //查询风控报告明细
    public ResultDTO queryReportDetails(Integer merchantId) {
        ReportInfoDO reportInfoDO = reportInfoDAO.getById(merchantId);
        return ResultDTO.success(reportInfoDO);
    }

    //通知后台人员
    public ResultDTO backPersonnelMes(Integer userId, Integer merchantId) {
        //查询用户的商户名称
        ReportInfoDO reportInfoDO = reportInfoDAO.getById(merchantId);
        WebUserOuterDO dto = webUserOuterDAO.getById(userId);
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            System.out.println(ev.getProperty("username"));
            helper.setFrom("fanaisheng@zheft.cn");
            helper.setTo("782430551@qq.com");
            helper.setSubject("风控报告");
            StringBuffer sb = new StringBuffer();
            sb.append(dto.getName() + "申请生成关于" + reportInfoDO.getMerName() + "的风控报告,请尽快处理" + "<a style='font-size:50px;' href='http://www.w3school.com.cn'>W3School</a>");
            helper.setText(sb.toString(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
        return ResultDTO.success();
    }

    //通知用户
    public ResultDTO headPersonnelMes(Integer userId, Integer merchantId) {
        ReportInfoDO reportInfoDO = reportInfoDAO.getById(merchantId);
        WebUserOuterDO dto = webUserOuterDAO.getById(userId);
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            logger.warn(ev.getProperty("username"));
            System.out.println(ev.getProperty("username"));
            helper.setFrom("fanaisheng@zheft.cn");
            helper.setTo("782430551@qq.com");
            helper.setSubject("风控报告");
            StringBuffer sb = new StringBuffer();
            sb.append(dto.getName() + "<br>").append("关于" + reportInfoDO.getMerName() + "的'风控+'报告已经生成!点击查看" + "<a style='font-size:50px;' href='http://www.w3school.com.cn'>W3School</a>");
            helper.setText(sb.toString(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
        return ResultDTO.success();
    }

    /**
     * 根据id查找当前数据,便于修改用
     * @param reportInfoDO
     * @return
     */
    public ResultDTO getById(ReportInfoDO reportInfoDO) {
        ReportInfoDO reportInfo = reportInfoDAO.getById(reportInfoDO.getId());
        return ResultDTO.success(reportInfo);
    }

    /**
     * 更新风控报告, yx
     * @param reportInfoDO
     * @return
     */
    @Transactional
    public ResultDTO updateReport(ReportInfoDO reportInfoDO) {

        //将状态改为待审核
        if (reportInfoDO.getStatus() == null) {
            //默认将状态改为待审核
            reportInfoDO.setStatus(0);
        }

        int result = reportInfoDAO.update(reportInfoDO);

        return ResultDTO.success();
    }

    /**
     * 导入数据，批量新增
     * @param objs
     * @return
     */
    public ResultDTO BatchImportToDB( List<Object[]> objs, Integer id){
    	if(objs.size() > 0){
    		for(Object[] obj: objs){
    			
    			if(obj.length != 12){
    				return ResultDTO.fail("导入数据有误，请按照模版导入12个月数据");
    			}
    			for (int i = 0; i < obj.length; i++) {
					if(obj[i] == null){
						return ResultDTO.fail("导入数据有空数据，请按照模版导入12个月数据");
					}
				}
    			ReportRepaymentHistoryDO reportRepaymentHistory = new ReportRepaymentHistoryDO();
    			reportRepaymentHistory.setReportId(id);
    			reportRepaymentHistory.setMonthOne(BigDecimal.valueOf(Double.valueOf(obj[0].toString())));
    			reportRepaymentHistory.setMonthTwo(BigDecimal.valueOf(Double.valueOf(obj[1].toString())) );
    			reportRepaymentHistory.setMonthThree(BigDecimal.valueOf(Double.valueOf(obj[2].toString())));
    			reportRepaymentHistory.setMonthFore(BigDecimal.valueOf(Double.valueOf(obj[3].toString())));
    			reportRepaymentHistory.setMonthFive(BigDecimal.valueOf(Double.valueOf(obj[4].toString())));
    			reportRepaymentHistory.setMonthSix(BigDecimal.valueOf(Double.valueOf(obj[5].toString())));
    			reportRepaymentHistory.setMonthSeven(BigDecimal.valueOf(Double.valueOf(obj[6].toString())));
    			reportRepaymentHistory.setMonthEight(BigDecimal.valueOf(Double.valueOf(obj[7].toString())));
    			reportRepaymentHistory.setMonthNine(BigDecimal.valueOf(Double.valueOf(obj[8].toString())));
    			reportRepaymentHistory.setMonthTen(BigDecimal.valueOf(Double.valueOf(obj[9].toString())));
    			reportRepaymentHistory.setMonthEleven(BigDecimal.valueOf(Double.valueOf(obj[10].toString())));
    			reportRepaymentHistory.setMonthTwelve(BigDecimal.valueOf(Double.valueOf(obj[11].toString())));
    			reportRepaymentHistory.setLastModifyTime(new Date());
    			//插表
        		reportRepaymentHistoryDAO.insert(reportRepaymentHistory);
    		}
    		
    	}else{
    		return ResultDTO.fail("导入数据为空，请核对后重新导入");
    	}
    	
    	return ResultDTO.success();
    }

    //查看导入数据
    public ResultPageDTO pageRepay(Integer id, Integer pageNum, Integer pageSize ){
    	ReportRepaymentHistoryDO reportRepaymentHistory = new ReportRepaymentHistoryDO();
    	reportRepaymentHistory.setReportId(id);
    	List<ReportRepaymentHistoryDO> pageList = this.reportRepaymentHistoryDAO.pageList(reportRepaymentHistory, pageNum, pageSize);
    	for (ReportRepaymentHistoryDO reportRepaymentHistoryDO : pageList) {
    		reportRepaymentHistoryDO.setLastModifyTimeStr(DateUtils.dateFormatToStr(reportRepaymentHistoryDO.getLastModifyTime()));
		}

        ResultPageDTO<ReportRepaymentHistoryDO> pager = new ResultPageDTO<ReportRepaymentHistoryDO>(1, pageList);
        return pager;
    }
    
    
  //查看导入数据
    public ResultDTO getByReportId(Integer id ){

    	ReportRepaymentHistoryDO dto = new ReportRepaymentHistoryDO();

    	dto = reportRepaymentHistoryDAO.getByReportId(id);
    	List<YearReportDO> list=new ArrayList<YearReportDO>();
    	for (int j=0 ; j<12; j++) {  
            YearReportDO yearReportDO=new YearReportDO();
            if(j==0){
                yearReportDO.setTurnover(dto.getMonthOne());
                yearReportDO.setDate("1月");
                list.add(yearReportDO);
            }
            if(j==1){
                yearReportDO.setTurnover(dto.getMonthTwo());
                yearReportDO.setDate("2月");
                list.add(yearReportDO);
            }
            if(j==2){
                yearReportDO.setTurnover(dto.getMonthThree());
                yearReportDO.setDate("3月");
                list.add(yearReportDO);
            }
            if(j==3){
                yearReportDO.setTurnover(dto.getMonthFore());
                yearReportDO.setDate("4月");
                list.add(yearReportDO);
            }
            if(j==4){
                yearReportDO.setTurnover(dto.getMonthFive());
                yearReportDO.setDate("5月");
                list.add(yearReportDO);
            }
            if(j==5){
                yearReportDO.setTurnover(dto.getMonthSix());
                yearReportDO.setDate("6月");
                list.add(yearReportDO);
            }
            if(j==6){
                yearReportDO.setTurnover(dto.getMonthSeven());
                yearReportDO.setDate("7月");
                list.add(yearReportDO);
            }
            if(j==7){
                yearReportDO.setTurnover(dto.getMonthEight());
                yearReportDO.setDate("8月");
                list.add(yearReportDO);
            }
            if(j==8){
                yearReportDO.setTurnover(dto.getMonthNine());
                yearReportDO.setDate("9月");
                list.add(yearReportDO);
            }
            if(j==9){
                yearReportDO.setTurnover(dto.getMonthTen());
                yearReportDO.setDate("10月");
                list.add(yearReportDO);
            }
            if(j==10){
                yearReportDO.setTurnover(dto.getMonthEleven());
                yearReportDO.setDate("11月");
                list.add(yearReportDO);
            }
            if(j==11){
                yearReportDO.setTurnover(dto.getMonthTwelve());
                yearReportDO.setDate("12月");
                list.add(yearReportDO);
            }
    	}
        return ResultDTO.success(list);
    }
}
