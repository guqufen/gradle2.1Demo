package net.fnsco.web.open;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.risk.service.report.ReportService;
import net.fnsco.risk.service.report.entity.ReportInfoDO;

/**
 * 
 * @desc 前端商务管理
 * @author   sxf
 * @version  
 * @since    Ver 1.1
 * @Date	 2017年10月22日 上午9:28:51
 *
 */
@Controller
@RequestMapping(value = "/web/open/merc", method = RequestMethod.POST)
@Api(value = "/web/open/merc", tags = { "前端商务管理" })
public class MercOpenControler extends BaseController {
    @Autowired
    private ReportService reportService;

    //根据用户id分页查询绑定下的商户列表
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ResponseBody
    @RequestMapping(value = "/queryList", method = RequestMethod.GET)
    public ResultDTO page(ReportInfoDO reportInfoDO) {
        Map<String, Integer> params = super.copyParamsToInteger(new String[] { "currentPageNum", "pageSize" });
        Integer page = params.get("currentPageNum");
        Integer rows = params.get("pageSize");
        reportInfoDO.setUserId(getUserId());
        ResultPageDTO<ReportInfoDO> pager = this.reportService.queryList(reportInfoDO, page, rows);
        return success(pager);
    }

}
