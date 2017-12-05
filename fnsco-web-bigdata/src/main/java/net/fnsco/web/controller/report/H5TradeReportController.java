package net.fnsco.web.controller.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import net.fnsco.bigdata.api.dto.TradeDataListResultDTO;
import net.fnsco.bigdata.api.dto.TradeDataResultDTO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.service.modules.trade.TradeWebReportService;

/**
 * @desc 大屏幕报表接口
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date     2017年7月12日 上午9:52:53
 */
@Controller
@RequestMapping(value = "/h5/report")
@Api(value = "/h5/report", tags = { "大屏幕报表" })
public class H5TradeReportController extends BaseController {
    @Autowired
    private TradeWebReportService tradeWebReportService;

    /**
     * appMsgIndex:(大屏幕报表统计查询)
     *
     * @param sysmsg
     * @param currentPageNum
     * @param pageSize
     * @return    设定文件
     * @return ResultPageDTO<SysAppMessage>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping(value = "/getTradeDataTotle", method = RequestMethod.GET)
    @ResponseBody
    //@RequiresPermissions(value = { "trade:report:list" })
    public ResultDTO getTradeDataTotle() {
        TradeDataResultDTO result = tradeWebReportService.getAllTradeDataTotle();
        return success(result);
    }

    /**
     * appMsgIndex:(大屏幕交易详情查询)
     *
     * @param sysmsg
     * @param currentPageNum
     * @param pageSize
     * @return    设定文件
     * @return ResultPageDTO<SysAppMessage>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping(value = "/getTradeDataList", method = RequestMethod.GET)
    @ResponseBody
    //@RequiresPermissions(value = { "trade:report:list" })
    public ResultDTO getTradeDataList(@RequestParam("startDate") String startDate, @RequestParam("pageSize") Integer pageSize) {
        List<TradeDataListResultDTO> result = tradeWebReportService.getTradeDataList(startDate, pageSize);
        return success(result);
    }
}
