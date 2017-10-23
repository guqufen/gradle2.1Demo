package net.fnsco.risk.service.report;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.TaskUtils;
import net.fnsco.risk.service.report.dao.ReportInfoDAO;
import net.fnsco.risk.service.report.dao.ReportRepaymentHistoryDAO;
import net.fnsco.risk.service.report.entity.ReportBusiness;
import net.fnsco.risk.service.report.entity.ReportInfoDO;
import net.fnsco.risk.service.report.entity.ReportRepaymentHistoryDO;
import net.fnsco.risk.service.report.entity.YearReportDO;
import net.fnsco.risk.service.sys.dao.IndustryDAO;
import net.fnsco.risk.service.sys.dao.WebUserOuterDAO;
import net.fnsco.risk.service.sys.entity.IndustryDO;
import net.fnsco.risk.service.sys.entity.WebUserOuterDO;
import net.fnsco.risk.service.trade.dao.TradeDataDAO;

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
    @Autowired
    private TradeDataDAO              tradeDataDAO;
    @Autowired
    private IndustryDAO               industryDAO;

    //前台分页查询风控报告列表
    public ResultPageDTO<ReportInfoDO> page(ReportInfoDO reportInfoDO, Integer pageNum, Integer pageSize) {
        WebUserOuterDO userDo = webUserOuterDAO.getById(reportInfoDO.getUserId());
        reportInfoDO.setAgentId(userDo.getAgentId());
        List<ReportInfoDO> pageList = pageList = this.reportInfoDAO.pageListMercByCondition(reportInfoDO, pageNum, pageSize);
        for (ReportInfoDO li : pageList) {
            li.setIsTrue(1);
            li.setStatus(1);
        }
        Integer count = this.reportInfoDAO.pageListMercByConditionCount(reportInfoDO);
        ResultPageDTO<ReportInfoDO> pager = new ResultPageDTO<ReportInfoDO>(count, pageList);
        return pager;
    }

    //前台商务管理分页查询
    public ResultPageDTO<ReportInfoDO> queryList(ReportInfoDO reportInfoDO, Integer pageNum, Integer pageSize) {
        WebUserOuterDO userDo = webUserOuterDAO.getById(reportInfoDO.getUserId());
        reportInfoDO.setAgentId(userDo.getAgentId());
        List<ReportInfoDO> pageList = Lists.newArrayList();
        boolean flag = false;
        if ( null != reportInfoDO.getStatus() && 1== reportInfoDO.getStatus()) {
            flag = true;
        }
        if (flag) {
            pageList = this.reportInfoDAO.pageListMercByCondition(reportInfoDO, pageNum, pageSize);
        } else {
            pageList = this.reportInfoDAO.pageListAllMerc(reportInfoDO, pageNum, pageSize);
        }
        for (ReportInfoDO li : pageList) {
            try {
                //订阅次数查询
                String time = tradeDataDAO.getByInnerCode(li.getInnerCode());
                if (time != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    Date old = sdf.parse(time);
                    Date now = new Date();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(now);
                    calendar.add(Calendar.MONTH, -3);
                    //小于三个月 符合规则
                    if (calendar.getTime().getTime() < old.getTime()) {
                        li.setIsTrue(1);
                    } else {
                        //大于三个月 不符合规则
                        li.setIsTrue(2);
                    }
                } else {
                    //用户绑定的商铺流水产生的时间不正确或没有流水交易
                    li.setIsTrue(3);
                }
            } catch (ParseException e) {
                //该商铺没有流水
                e.printStackTrace();
            }
        }
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
    public ResultDTO queryYearReport(String innerCode, Integer merchantId) {
        List<YearReportDO> list = new ArrayList<YearReportDO>();

        ReportInfoDO reportInfoDO = reportInfoDAO.getByInnerCode(innerCode);
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
                yearReportDO.setDate(handleDate(date, -6) + "月");
                list.add(yearReportDO);
            }
            if (j == 1) {
                yearReportDO.setTurnover(dto.getMonthTwo());
                yearReportDO.setDate(handleDate(date, -5) + "月");
                list.add(yearReportDO);
            }
            if (j == 2) {
                yearReportDO.setTurnover(dto.getMonthThree());
                yearReportDO.setDate(handleDate(date, -4) + "月");
                list.add(yearReportDO);
            }
            if (j == 3) {
                yearReportDO.setTurnover(dto.getMonthFore());
                yearReportDO.setDate(handleDate(date, -3) + "月");
                list.add(yearReportDO);
            }
            if (j == 4) {
                yearReportDO.setTurnover(dto.getMonthFive());
                yearReportDO.setDate(handleDate(date, -2) + "月");
                list.add(yearReportDO);
            }
            if (j == 5) {
                yearReportDO.setTurnover(dto.getMonthSix());
                yearReportDO.setDate(handleDate(date, -1) + "月");
                list.add(yearReportDO);
            }
            if (j == 6) {
                yearReportDO.setTurnover(dto.getMonthSeven());
                yearReportDO.setDate(handleDate(date, 0) + "月");
                list.add(yearReportDO);
            }
            if (j == 7) {
                yearReportDO.setTurnover(dto.getMonthEight());
                yearReportDO.setDate(handleDate(date, 1) + "月");
                list.add(yearReportDO);
            }
            if (j == 8) {
                yearReportDO.setTurnover(dto.getMonthNine());
                yearReportDO.setDate(handleDate(date, 2) + "月");
                list.add(yearReportDO);
            }
            if (j == 9) {
                yearReportDO.setTurnover(dto.getMonthTen());
                yearReportDO.setDate(handleDate(date, 3) + "月");
                list.add(yearReportDO);
            }
            if (j == 10) {
                yearReportDO.setTurnover(dto.getMonthEleven());
                yearReportDO.setDate(handleDate(date, 4) + "月");
                list.add(yearReportDO);
            }
            if (j == 11) {
                yearReportDO.setTurnover(dto.getMonthTwelve());
                yearReportDO.setDate(handleDate(date, 5) + "月");
                list.add(yearReportDO);
            }
        }
        return ResultDTO.success(list);
    }

    //查询12个月经营流水趋势(交易量)
    public ResultDTO queryTradingVolumeReport(String innerCode, Integer reportId) {
        List<YearReportDO> list = new ArrayList<YearReportDO>();

        ReportInfoDO reportInfoDO = reportInfoDAO.getByInnerCode(innerCode);
        ReportRepaymentHistoryDO dto = reportRepaymentHistoryDAO.getByReportId(reportId);
        //如果查出来的月度营业额为空，则直接返回到页面；否则会报空指针异常
        if (dto == null) {
            return ResultDTO.success(list);
        }
        Date date = reportInfoDO.getLastModifyTime();
        for (int j = 0; j < 12; j++) {
            YearReportDO yearReportDO = new YearReportDO();
            if (j == 0) {
                yearReportDO.setTurnover(dto.getMonthOne());
                yearReportDO.setDate(handleDate(date, -6) + "月");
                list.add(yearReportDO);
            }
            if (j == 1) {
                yearReportDO.setTurnover(dto.getMonthTwo());
                yearReportDO.setDate(handleDate(date, -5) + "月");
                list.add(yearReportDO);
            }
            if (j == 2) {
                yearReportDO.setTurnover(dto.getMonthThree());
                yearReportDO.setDate(handleDate(date, -4) + "月");
                list.add(yearReportDO);
            }
            if (j == 3) {
                yearReportDO.setTurnover(dto.getMonthFore());
                yearReportDO.setDate(handleDate(date, -3) + "月");
                list.add(yearReportDO);
            }
            if (j == 4) {
                yearReportDO.setTurnover(dto.getMonthFive());
                yearReportDO.setDate(handleDate(date, -2) + "月");
                list.add(yearReportDO);
            }
            if (j == 5) {
                yearReportDO.setTurnover(dto.getMonthSix());
                yearReportDO.setDate(handleDate(date, -1) + "月");
                list.add(yearReportDO);
            }
            if (j == 6) {
                yearReportDO.setTurnover(dto.getMonthSeven());
                yearReportDO.setDate(handleDate(date, 0) + "月");
                list.add(yearReportDO);
            }
            if (j == 7) {
                yearReportDO.setTurnover(dto.getMonthEight());
                yearReportDO.setDate(handleDate(date, 1) + "月");
                list.add(yearReportDO);
            }
            if (j == 8) {
                yearReportDO.setTurnover(dto.getMonthNine());
                yearReportDO.setDate(handleDate(date, 2) + "月");
                list.add(yearReportDO);
            }
            if (j == 9) {
                yearReportDO.setTurnover(dto.getMonthTen());
                yearReportDO.setDate(handleDate(date, 3) + "月");
                list.add(yearReportDO);
            }
            if (j == 10) {
                yearReportDO.setTurnover(dto.getMonthEleven());
                yearReportDO.setDate(handleDate(date, 4) + "月");
                list.add(yearReportDO);
            }
            if (j == 11) {
                yearReportDO.setTurnover(dto.getMonthTwelve());
                yearReportDO.setDate(handleDate(date, 5) + "月");
                list.add(yearReportDO);
            }
        }
        return ResultDTO.success(list);
    }

    //查询12个月日均客单价
    public ResultDTO queryUnitPriceReport(String innerCode, Integer reportId) {
        List<YearReportDO> list = new ArrayList<YearReportDO>();

        ReportInfoDO reportInfoDO = reportInfoDAO.getByInnerCode(innerCode);
        ReportRepaymentHistoryDO dto = reportRepaymentHistoryDAO.getByReportId(reportId);
        //如果查出来的月度营业额为空，则直接返回到页面；否则会报空指针异常
        if (dto == null) {
            return ResultDTO.success(list);
        }
        Date date = reportInfoDO.getLastModifyTime();
        for (int j = 0; j < 12; j++) {
            YearReportDO yearReportDO = new YearReportDO();
            if (j == 0) {
                yearReportDO.setTurnover(dto.getMonthOne());
                yearReportDO.setDate(handleDate(date, -6) + "月");
                list.add(yearReportDO);
            }
            if (j == 1) {
                yearReportDO.setTurnover(dto.getMonthTwo());
                yearReportDO.setDate(handleDate(date, -5) + "月");
                list.add(yearReportDO);
            }
            if (j == 2) {
                yearReportDO.setTurnover(dto.getMonthThree());
                yearReportDO.setDate(handleDate(date, -4) + "月");
                list.add(yearReportDO);
            }
            if (j == 3) {
                yearReportDO.setTurnover(dto.getMonthFore());
                yearReportDO.setDate(handleDate(date, -3) + "月");
                list.add(yearReportDO);
            }
            if (j == 4) {
                yearReportDO.setTurnover(dto.getMonthFive());
                yearReportDO.setDate(handleDate(date, -2) + "月");
                list.add(yearReportDO);
            }
            if (j == 5) {
                yearReportDO.setTurnover(dto.getMonthSix());
                yearReportDO.setDate(handleDate(date, -1) + "月");
                list.add(yearReportDO);
            }
            if (j == 6) {
                yearReportDO.setTurnover(dto.getMonthSeven());
                yearReportDO.setDate(handleDate(date, 0) + "月");
                list.add(yearReportDO);
            }
            if (j == 7) {
                yearReportDO.setTurnover(dto.getMonthEight());
                yearReportDO.setDate(handleDate(date, 1) + "月");
                list.add(yearReportDO);
            }
            if (j == 8) {
                yearReportDO.setTurnover(dto.getMonthNine());
                yearReportDO.setDate(handleDate(date, 2) + "月");
                list.add(yearReportDO);
            }
            if (j == 9) {
                yearReportDO.setTurnover(dto.getMonthTen());
                yearReportDO.setDate(handleDate(date, 3) + "月");
                list.add(yearReportDO);
            }
            if (j == 10) {
                yearReportDO.setTurnover(dto.getMonthEleven());
                yearReportDO.setDate(handleDate(date, 4) + "月");
                list.add(yearReportDO);
            }
            if (j == 11) {
                yearReportDO.setTurnover(dto.getMonthTwelve());
                yearReportDO.setDate(handleDate(date, 5) + "月");
                list.add(yearReportDO);
            }
        }
        return ResultDTO.success(list);
    }

    /*    private String handleDateYear(Date date, Integer num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, num);
        SimpleDateFormat sdf = new SimpleDateFormat("YY-MM");
        String dateStr = sdf.format(calendar.getTime());
        return dateStr;
    }
    */
    private String handleDate(Date date, Integer num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, num);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String newStr = sdf.format(calendar.getTime());
        String dateStr = sdf.format(calendar.getTime());
        //包含01  2017-09
        String res = dateStr.substring(5);
        if (res.indexOf("01") > -1) {
            return newStr;
        } else {
            //不包含
            return res;
        }
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
        TaskUtils.execAsyn(new Runnable() {
            @Override
            public void run() {
                MimeMessage message = null;
                String nick = "";
                try {
                    nick = javax.mail.internet.MimeUtility.encodeText("杭州法奈昇科技有限公司");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    message = mailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                    helper.setFrom(new InternetAddress(nick + " <" + ev.getProperty("spring.mail.username") + ">"));
                    helper.setTo(ev.getProperty("manger.mail.address"));
                    helper.setSubject("风控报告");
                    StringBuffer sb = new StringBuffer();
                    sb.append("<div style='font-size:14px;margin-top:50px;line-height:12px;'><p>dear:</p><p>" + dto.getName() + "申请生成关于" + reportInfoDO.getMerName() + "的风控报告,请尽快处理!</p>"
                              + "<p><a style='color:#1229e3;' href='http://www.w3school.com.cn'>W3School</a><p></div>");
                    helper.setText(sb.toString(), true);
                } catch (Exception e) {
                    logger.error("邮件发送失败");
                }
                mailSender.send(message);
            }
        });
        return ResultDTO.success();
    }

    //通知用户
    public ResultDTO headPersonnelMes(Integer userId, Integer merchantId) {
        ReportInfoDO reportInfoDO = reportInfoDAO.getById(merchantId);
        WebUserOuterDO dto = webUserOuterDAO.getById(userId);
        TaskUtils.execAsyn(new Runnable() {
            @Override
            public void run() {
                MimeMessage message = null;
                String nick = "";
                try {
                    nick = javax.mail.internet.MimeUtility.encodeText("杭州法奈昇科技有限公司");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    message = mailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                    helper.setFrom(new InternetAddress(nick + " <" + ev.getProperty("spring.mail.username") + ">"));
                    helper.setTo(ev.getProperty("manger.mail.address"));
                    helper.setSubject("风控报告");
                    StringBuffer sb = new StringBuffer();
                    sb.append("<div style='font-size:14px;margin-top:50px;line-height:12px;'><p>dear:</p><p>" + dto.getName() + "</p><p>关于" + reportInfoDO.getMerName() + "的'风控+'报告已经生成!</p><p>点击查看"
                              + "<a style='color:#1229e3;' href='http://www.w3school.com.cn'>W3School</a></p></div>");
                    helper.setText(sb.toString(), true);
                } catch (MessagingException e) {
                    logger.error("邮件发送失败");
                }
                mailSender.send(message);
            }
        });
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

        //更新风控状态
        reportInfoDO.setLastModifyTime(new Date());
        reportInfoDAO.update(reportInfoDO);

        //重新查询状态
        ReportInfoDO reportInfo = reportInfoDAO.getById(reportInfoDO.getId());//根据ID查找数据
        //审核成功，则发邮件通知用户
        if (1 == reportInfo.getStatus()) {
            List<Integer> userIdList = webUserOuterDAO.getByInnercode(reportInfo.getInnerCode().trim());
            //邮件通知用户
            for (Integer userId : userIdList) {
                headPersonnelMes(userId, reportInfoDO.getId());
            }
        }

        return ResultDTO.success();
    }

    /**
     * 导入数据，批量新增
     * @param objs
     * @return
     */
    public ResultDTO BatchImportToDB(ReportRepaymentHistoryDO reportRepaymentHistory) {

        //插表
        reportRepaymentHistoryDAO.insert(reportRepaymentHistory);

        return ResultDTO.success();
    }

    //查看导入数据
    public ResultPageDTO pageRepay(Integer id, Integer pageNum, Integer pageSize) {
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
    public ResultDTO getByReportId(Integer id) {

        ReportRepaymentHistoryDO dto = new ReportRepaymentHistoryDO();

        dto = reportRepaymentHistoryDAO.getByReportId(id);
        if (dto == null) {
            return ResultDTO.fail("没有找到流水数据");
        }
        List<YearReportDO> list = new ArrayList<YearReportDO>();
        Date dd = dto.getLastModifyTime();
        for (int j = 0; j < 12; j++) {
            YearReportDO yearReportDO = new YearReportDO();
            if (j == 0) {
                yearReportDO.setTurnover(dto.getMonthOne());
                yearReportDO.setDate(handleDate(dd, -6));
                list.add(yearReportDO);
            }
            if (j == 1) {
                yearReportDO.setTurnover(dto.getMonthTwo());
                yearReportDO.setDate(handleDate(dd, -5));
                list.add(yearReportDO);
            }
            if (j == 2) {
                yearReportDO.setTurnover(dto.getMonthThree());
                yearReportDO.setDate(handleDate(dd, -4));
                list.add(yearReportDO);
            }
            if (j == 3) {
                yearReportDO.setTurnover(dto.getMonthFore());
                yearReportDO.setDate(handleDate(dd, -3));
                list.add(yearReportDO);
            }
            if (j == 4) {
                yearReportDO.setTurnover(dto.getMonthFive());
                yearReportDO.setDate(handleDate(dd, -2));
                list.add(yearReportDO);
            }
            if (j == 5) {
                yearReportDO.setTurnover(dto.getMonthSix());
                yearReportDO.setDate(handleDate(dd, -1));
                list.add(yearReportDO);
            }
            if (j == 6) {
                yearReportDO.setTurnover(dto.getMonthSeven());
                yearReportDO.setDate(handleDate(dd, 0));
                list.add(yearReportDO);
            }
            if (j == 7) {
                yearReportDO.setTurnover(dto.getMonthEight());
                yearReportDO.setDate(handleDate(dd, 1));
                list.add(yearReportDO);
            }
            if (j == 8) {
                yearReportDO.setTurnover(dto.getMonthNine());
                yearReportDO.setDate(handleDate(dd, 2));
                list.add(yearReportDO);
            }
            if (j == 9) {
                yearReportDO.setTurnover(dto.getMonthTen());
                yearReportDO.setDate(handleDate(dd, 3));
                list.add(yearReportDO);
            }
            if (j == 10) {
                yearReportDO.setTurnover(dto.getMonthEleven());
                yearReportDO.setDate(handleDate(dd, 4));
                list.add(yearReportDO);
            }
            if (j == 11) {
                yearReportDO.setTurnover(dto.getMonthTwelve());
                yearReportDO.setDate(handleDate(dd, 5));
                list.add(yearReportDO);
            }
        }
        return ResultDTO.success(list);
    }
    
  //查找经营趋势数据或客单价
    public ResultDTO getReportBusinessOrUnit(Integer id,Integer num ) {
    	ReportBusiness report = new ReportBusiness();
    	String innerCode = reportRepaymentHistoryDAO.getInnerCodeById(id);
    	report.setInnerCode(innerCode);
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    	Calendar c1 = Calendar.getInstance();
    	c1.setTime(new Date());
        c1.add(Calendar.DATE, - 1);
        Date d1 = c1.getTime();
        String startDay = format.format(d1);
        report.setStartDay(startDay);
        Calendar c2 = Calendar.getInstance();
    	c2.setTime(new Date());
        c2.add(Calendar.MONTH, -3);
        Date d2 = c2.getTime();
        String endDay = format.format(d2);
        report.setEndDay(endDay);
        List<ReportBusiness> reportBusiness = reportRepaymentHistoryDAO.getTurnover(report);
        if (reportBusiness == null) {
            return ResultDTO.fail("没有找到数据");
        }
        List<YearReportDO> list = new ArrayList<YearReportDO>();
        if(num==1) {
        	for(ReportBusiness rep : reportBusiness) {
        		YearReportDO yearReportDO = new YearReportDO();
        		yearReportDO.setDate(rep.getTradeDate());
        		BigDecimal bd=new BigDecimal(rep.getTurnover());
        		yearReportDO.setTurnover(bd);
        		list.add(yearReportDO);
        	}	
        	return ResultDTO.success(list);
        }else{
        	for(ReportBusiness rep : reportBusiness) {
        		YearReportDO yearReportDO = new YearReportDO();
        		yearReportDO.setDate(rep.getTradeDate());
        		BigDecimal bd=new BigDecimal(rep.getOrderPrice());
        		yearReportDO.setTurnover(bd);
        		list.add(yearReportDO);
        	}	
        	return ResultDTO.success(list);
        }
    }
    
    public ResultDTO queryIndustry(int id) {
        IndustryDO dto = industryDAO.getById(id);
        return ResultDTO.success(dto);
    }
}
