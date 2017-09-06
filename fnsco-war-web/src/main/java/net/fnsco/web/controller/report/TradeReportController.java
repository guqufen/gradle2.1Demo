package net.fnsco.web.controller.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.trade.TradeReportService;
import net.fnsco.order.service.domain.SysAppMessage;

/**
 * @desc 大屏幕报表接口
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date     2017年7月12日 上午9:52:53
 */
@Controller
@RequestMapping(value = "/web/report")
@Api(value = "/web/report", tags = { "大屏幕报表" })
public class TradeReportController extends BaseController {

    @Autowired
    private TradeReportService tradeReportService;

    /**
     * appMsgIndex:(大屏幕报表查询)
     *
     * @param sysmsg
     * @param currentPageNum
     * @param pageSize
     * @return    设定文件
     * @return ResultPageDTO<SysAppMessage>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    //@RequiresPermissions(value = { "trade:report:list" })
    public ResultDTO appMsgIndex(SysAppMessage sysmsg, Integer currentPageNum, Integer pageSize) {
        //tradeReportService.queryBusinessTrends(tradeReportDTO);
        return success();
    }
}
