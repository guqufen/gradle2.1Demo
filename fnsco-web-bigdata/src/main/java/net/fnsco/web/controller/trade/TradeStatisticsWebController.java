package net.fnsco.web.controller.trade;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import net.fnsco.bigdata.api.dto.TradeDataDTO;
import net.fnsco.bigdata.api.trade.TradeDataService;
import net.fnsco.bigdata.api.trade.TradeStatisticsService;
import net.fnsco.bigdata.service.domain.trade.TradeData;
import net.fnsco.bigdata.service.domain.trade.TradeStatistics;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.ExcelUtils;
import net.sf.json.JSONObject;

/**
 * @desc 交易统计控制器
 * @author 
 * @date 2017年6月28日 下午2:22:46
 */
@Controller
@RequestMapping(value = "/web/tradeStatistics")
@Api(value = "/web", tags = { "交易统计控制器" })
public class TradeStatisticsWebController extends BaseController {

    @Autowired
    private TradeStatisticsService tradeStatisticsService;
    @Autowired
    private TradeDataService       tradeDataService;
    /**
     * 交易统计分页查询
     * 
     * @param tradeDataDTO
     * @param currentPageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = { "m:trade:list" })
    public ResultPageDTO<TradeStatistics> query(TradeStatistics tradeStatistics, Integer currentPageNum, Integer pageSize) {

        return tradeStatisticsService.queryTradeData(tradeStatistics, currentPageNum, pageSize);
    }
    /**
     * 交易统计详情分页查询
     * 
     * @param tradeDataDTO
     * @param currentPageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/queryTrade", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = { "m:trade:list" })
    public ResultPageDTO<TradeData> queryTrade(TradeStatistics tradeStatistics, Integer currentPageNum, Integer pageSize) {
    	TradeDataDTO tradeDataDTO = new TradeDataDTO();
    	tradeDataDTO.setInnerCode(tradeStatistics.getInnerCode());
    	tradeDataDTO.setTermId(tradeStatistics.getTerminalCode());
    	String day = DateUtils.strFormatToStr(tradeStatistics.getTradeDate());
    	tradeDataDTO.setStartTime(day);
    	tradeDataDTO.setEndTime(day);
        return tradeDataService.queryTradeData(tradeDataDTO, currentPageNum, pageSize);
    }
    /**
     * 交易流水excel导出
     * 
     * @param tradeDataDTO
     * @param currentPageNum
     * @param pageSize
     * @param req
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = { "m:trade:export" })
    public void export(TradeStatistics tradeStatistics, HttpServletRequest req, HttpServletResponse response)
            throws IOException {
        List<TradeStatistics> dataList = tradeStatisticsService.queryDataList(tradeStatistics);
        // 转换日期显示
        if (dataList != null) {
            for (TradeStatistics merchantdo : dataList) {
            }
        }

        JSONObject jObject = new JSONObject();
        jObject.put("data", dataList);
        List<TradeStatistics> list = (List<TradeStatistics>) jObject.get("data");
        String itemMark = "innerCode,merId,merName,terminalCode,tradeDate,orderNum,turnover";
        String itemParap = "内部商户号,结算商户号,商户名,终端号,支付日期,交易笔数,交易金额";

        String[] itemMarks = itemMark.split(",");// 键
        String[] itemParaps = itemParap.split(",");// 列头

        HSSFWorkbook workbook = ExcelUtils.getInputStream(itemParaps.length, itemMarks, itemParaps, list, "交易统计");

        response.setContentType("application/vnd.ms-excel;");
        String nowStr = DateUtils.getNowYMDStr();
        String fileName = "交易统计" + nowStr + ".xls";
        response.setHeader("Content-disposition",
                "attachment;filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1"));// 设定输出文件头

        OutputStream ouputStream = response.getOutputStream();
        workbook.write(ouputStream);

        ouputStream.flush();
        ouputStream.close();
    }
}
