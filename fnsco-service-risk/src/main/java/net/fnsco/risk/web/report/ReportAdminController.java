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
@RequestMapping(value = "/report", method = RequestMethod.POST)
public class ReportAdminController extends BaseController{
    @Autowired
    private ReportService reportService;
    // 分页
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ResponseBody
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public ResultDTO page(ReportInfoDO reportInfoDO) {
        Map<String, Integer> params = super.copyParamsToInteger(new String[] { "currentPageNum", "pageSize" });
        Integer page = params.get("currentPageNum");
        Integer rows = params.get("pageSize");
        ResultPageDTO<ReportInfoDO> pager = this.reportService.page(reportInfoDO, page,rows);
        return success(pager);
    }
    //通知商户风控报告
    @ResponseBody
    @RequestMapping(value = "headPersonnelMes", method = RequestMethod.GET)
    public ResultDTO headPersonnelMes(@RequestParam String userId,@RequestParam String merchantId) {
        return reportService.headPersonnelMes(NumberUtils.toInt(userId),NumberUtils.toInt(merchantId));
    }
}
