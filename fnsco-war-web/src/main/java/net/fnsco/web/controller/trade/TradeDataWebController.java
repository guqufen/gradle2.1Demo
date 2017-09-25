package net.fnsco.web.controller.trade;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import io.swagger.annotations.Api;
import net.fnsco.bigdata.api.dto.TradeDataDTO;
import net.fnsco.bigdata.api.trade.TradeDataService;
import net.fnsco.bigdata.service.domain.trade.TradeData;
import net.fnsco.bigdata.service.modules.trade.TradeDataImportService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.ExcelUtils;
import net.fnsco.core.utils.ReadExcel;
import net.fnsco.freamwork.business.WebUserDTO;
import net.sf.json.JSONObject;

/**
 * @desc 交易统计控制器
 * @author tangliang
 * @date 2017年6月28日 下午2:22:46
 */
@Controller
@RequestMapping(value = "/web/trade")
@Api(value = "/web", tags = { "交易统计控制器" })
public class TradeDataWebController extends BaseController {

    @Autowired
    private TradeDataService tradeDataService;
    @Autowired
    private TradeDataImportService tradeDataImportService;
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
    public ResultPageDTO<TradeData> query(TradeDataDTO tradeDataDTO, Integer currentPageNum, Integer pageSize) {

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
    public void export(TradeDataDTO tradeDataDTO, HttpServletRequest req, HttpServletResponse response)
            throws IOException {
        List<TradeData> dataList = tradeDataService.queryDataList(tradeDataDTO);
        // 转换日期显示
        if (dataList != null) {
            for (TradeData merchantdo : dataList) {
                // 支付方式
                if ("00".equals(merchantdo.getPayType())) {
                    merchantdo.setPayType("刷卡");
                } else if ("01".equals(merchantdo.getPayType())) {
                    merchantdo.setPayType("扫码");
                }
                // 处理金额
                if (null != merchantdo.getAmt()) {
                    BigDecimal str = new BigDecimal(merchantdo.getAmt());
                    double resu = str.divide(new BigDecimal(100)).doubleValue();
                    DecimalFormat df = new DecimalFormat("#0.00");
                    merchantdo.setAmt(df.format(resu));
                }
                // 处理来源
                if (null != merchantdo.getSource()) {
                    if ("00".equals(merchantdo.getSource())) {
                        merchantdo.setSource("拉卡拉");
                    } else {
                        merchantdo.setSource("其他");
                    }
                }
                // 处理交易类型
                if (null != merchantdo.getTxnType()) {
                    if ("1".equals(merchantdo.getTxnType())) {
                        merchantdo.setTxnType("消费");
                    } else if ("2".equals(merchantdo.getTxnType())) {
                        merchantdo.setTxnType("撤销");
                    }
                }
                // 处理状态
                if ("0".equals(merchantdo.getStatus())) {
                    merchantdo.setStatus("非正常交易");
                } else if ("1".equals(merchantdo.getStatus())) {
                    merchantdo.setStatus("正常交易");
                }
                String li0 = merchantdo.getTimeStamp();
                String li1 = merchantdo.getOrderTime();
                Date li2 = merchantdo.getCreateTime();
                if (li0 != null) {
                    String year = li0.substring(0, 4);
                    String month = li0.substring(4, 6);
                    String day = li0.substring(6, 8);
                    String hour = li0.substring(8, 10);
                    String minutes = li0.substring(10, 12);
                    String seconds = li0.substring(12, 14);
                    String dateString0 = year + "-" + month + "-" + day + ' ' + hour + ':' + minutes + ':' + seconds;
                    merchantdo.setTimeStamp(dateString0);
                }
                if (li1 != null) {
                    String year = li1.substring(0, 4);
                    String month = li1.substring(4, 6);
                    String day = li1.substring(6, 8);
                    String hour = li1.substring(8, 10);
                    String minutes = li1.substring(10, 12);
                    String seconds = li1.substring(12, 14);
                    String dateString1 = year + "-" + month + "-" + day + ' ' + hour + ':' + minutes + ':' + seconds;
                    merchantdo.setOrderTime(dateString1);
                }
                if (li2 != null) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString2 = formatter.format(li2);
                    merchantdo.setCreateTimeStr(dateString2);
                }
            }
        }

        JSONObject jObject = new JSONObject();
        jObject.put("data", dataList);
        List<TradeData> list = (List<TradeData>) jObject.get("data");
        String itemMark = "orderNo,orderIdScan,merName,innerCode,snCode,referNo,txnType,merId,timeStamp,payType,amt,certifyId,orderTime,termId,batchNo,sysTraceNo,authCode,source,createTimeStr,status";
        String itemParap = "订单号,扫码交易的订单号,商户名,内部商户号,终端SN码,参考号,交易类型,结算商户号,支付时间,支付方式,交易金额(元),持卡人卡号,订单时间,终端号,批次号,凭证号,授权码,来源,创建时间,状态";

        String[] itemMarks = itemMark.split(",");// 键
        String[] itemParaps = itemParap.split(",");// 列头

        HSSFWorkbook workbook = ExcelUtils.getInputStream(itemParaps.length, itemMarks, itemParaps, list, "交易流水");

        response.setContentType("application/vnd.ms-excel;");
        String nowStr = DateUtils.getNowYMDStr();
        String fileName = "交易流水" + nowStr + ".xls";
        response.setHeader("Content-disposition",
                "attachment;filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1"));// 设定输出文件头

        OutputStream ouputStream = response.getOutputStream();
        workbook.write(ouputStream);

        ouputStream.flush();
        ouputStream.close();
    }
    @RequestMapping(value = "/down", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = { "m:trade:export" })
    public void down() throws IOException {
        String filePath = request.getSession().getServletContext().getRealPath("");
        filePath = filePath + "template/交易流水导入模板.xlsx";
        String fileName = "交易流水导入模板.xlsx";
        //解析excel，获取交易流水信息集合。
        ReadExcel.downTemplate(filePath, fileName, response);
    }

    /**
        * 
        * doImport:(商户数据导入)
        *
        * @return   Map<String,String>    返回Result对象
        * @throws 
        * @since  CodingExample　Ver 1.1
        */
    @RequestMapping(value = "/doImport", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> doImport() {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        MultipartFile file = fileMap.get("excel_file_trade");
        // 判断文件是否为空
        if (file == null) {
            return null;
        }
        // 获取文件名
        String name = file.getOriginalFilename();
        // 进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
        long size = file.getSize();
        if (name == null || ("").equals(name) && size == 0) {
            return null;
        }
        // 创建处理EXCEL
        ReadExcel readExcel = new ReadExcel();
        // 解析excel，获取客户信息集合。
        List<Object[]> customerList = readExcel.getExcelInfo(name, file);
        // 获取当前登录的用户
        WebUserDTO adminUser = (WebUserDTO) getSessionUser();
        Integer userId = adminUser.getId();
        // 批量导入。参数：文件名，文件。
        ResultDTO<String> result = null;
        result = tradeDataImportService.tradeBatchImportToDB(customerList,name);
        if (!result.isSuccess()) {
            Map<String, String> map = new HashMap<>();
            if (("").equals(result.getMessage())) {
                map.put("data", "批量导入EXCEL失败！");
                return map;
            }
            map.put("data", result.getMessage());
            return map;
        }
        Map<String, String> map = new HashMap<>();
        map.put("data", "success");
        return map;
    }
}
