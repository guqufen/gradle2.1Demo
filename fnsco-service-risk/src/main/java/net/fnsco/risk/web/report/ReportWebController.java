package net.fnsco.risk.web.report;

import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.risk.service.report.ReportService;
import net.fnsco.risk.service.report.entity.ReportInfoDO;

@Controller
@RequestMapping(value = "web/report", method = RequestMethod.POST)
public class ReportWebController extends BaseController{
    @Autowired
    private ReportService reportService;

    //根据用户id分页查询绑定下的商户列表
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ResponseBody
    @RequestMapping(value = "queryMerchants", method = RequestMethod.GET)
    public ResultDTO page(ReportInfoDO reportInfoDO) {
        Map<String, Integer> params = super.copyParamsToInteger(new String[] { "currentPageNum", "pageSize" });
        Integer page = params.get("currentPageNum");
        Integer rows = params.get("pageSize");
        ResultPageDTO<ReportInfoDO> pager = this.reportService.page(reportInfoDO, page,rows);
        return success(pager);
    }
    
    //查询全年风控曲线图
    @RequestMapping("queryYearReport")  
    @ResponseBody
    public ResultDTO queryYearReport(@RequestParam String userId,@RequestParam String merchantId){
        return reportService.queryYearReport(NumberUtils.toInt(userId),NumberUtils.toInt(merchantId));
    }
    //查询风控报告明细
    @RequestMapping("queryReportDetails")  
    @ResponseBody
    public ResultDTO queryReportDetails(@RequestParam String merchantId){
        return reportService.queryReportDetails(NumberUtils.toInt(merchantId));
    }
    //给后台人员发送生成报告消息
    @RequestMapping("backPersonnelMes")  
    @ResponseBody
    public ResultDTO backPersonnelMes(@RequestParam String userId,@RequestParam String merchantId){
        return reportService.backPersonnelMes(NumberUtils.toInt(userId),NumberUtils.toInt(merchantId));
    }
    //查询行业类别
    @RequestMapping("queryIndustry")  
    @ResponseBody
    public ResultDTO queryIndustry(@RequestParam String id){
        return reportService.queryIndustry(NumberUtils.toInt(id));
    }
}
