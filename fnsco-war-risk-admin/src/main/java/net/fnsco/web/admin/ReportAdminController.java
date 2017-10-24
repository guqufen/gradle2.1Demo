package net.fnsco.web.admin;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.ReadExcel;
import net.fnsco.risk.service.report.ReportService;
import net.fnsco.risk.service.report.entity.ReportInfoDO;
import net.fnsco.risk.service.report.entity.ReportRepaymentHistoryDO;
@Controller
@RequestMapping(value = "/web/admin/report", method = RequestMethod.POST)
public class ReportAdminController extends BaseController{
    @Autowired
    private ReportService reportService;
    @Autowired
    private Environment        env;
    
    // 分页
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResultDTO page(ReportInfoDO reportInfoDO) {
        Map<String, Integer> params = super.copyParamsToInteger(new String[] { "currentPageNum", "pageSize" });
        Integer page = params.get("currentPageNum");
        Integer rows = params.get("pageSize");
        reportInfoDO.setUserId(getUserId());
        ResultPageDTO<ReportInfoDO> pager = this.reportService.pageBack(reportInfoDO, page,rows);
        return success(pager);
    }
    //通知商户风控报告
    @ResponseBody
    @RequestMapping(value = "/headPersonnelMes", method = RequestMethod.GET)
    public ResultDTO headPersonnelMes(@RequestParam String userId,@RequestParam String merchantId) {
        return reportService.headPersonnelMes(NumberUtils.toInt(userId),NumberUtils.toInt(merchantId));
    }
    //更新风控报告状态
//    @ResponseBody
//    @RequestMapping(value = "updateReportStatus", method = RequestMethod.GET)
//    public ResultDTO updateReportStatus(ReportInfoDO reportInfoDO) {
//        return reportService.updateReportStatus(reportInfoDO);
//    }
    
    /**
     * 通过id查找当前数据对象
     * @param reportInfoDO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public ResultDTO getById(ReportInfoDO reportInfoDO){

    	return reportService.getById(reportInfoDO);
    }
    
    /**
     *  修改风控报告
     * @param reportInfoDO
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateReport", method = RequestMethod.GET)
    public ResultDTO updateReport(ReportInfoDO reportInfoDO){
    	
    	//如果传过来的ID为空，返回失败
    	if(reportInfoDO.getId() == null){
    		return ResultDTO.fail();
    	}
    	
    	//如果传过来的状态为空，则返回失败
        if (reportInfoDO.getStatus() == null) {
            return ResultDTO.fail();
        }
    	
		return reportService.updateReport(reportInfoDO);
    }

    /** 
     * 下载模版 
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value="/down", method = RequestMethod.GET)
    public void down() throws IOException{

    	String filePath = request.getSession().getServletContext().getRealPath("");
        filePath = filePath + "template/风控数据模版.xls";
        String fileName = "风控数据模版.xls";

         //解析excel，获取客户信息集合。
         ReadExcel.downTemplate(filePath, fileName, response);
    }

    /**
     * 导入数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/doImport", method = RequestMethod.POST)
    public ResultDTO doImport(@Param("id") Integer id){

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		MultipartFile file = fileMap.get("excel_file_risk_inf");

		// 判断文件是否为空
		if (file == null) {
			return null;
		}
		// 获取文件名
		String name = file.getOriginalFilename();
		// 判断文件是否为空（即判断其大小是否为0或其名称是否为null）
		long size = file.getSize();
		if (name == null || ("").equals(name) && size == 0) {
			return null;
		}

		// 创建读取EXCEL对象
        ReadExcel readExcel = new ReadExcel();
        // 解析excel，获取客户信息集合。
        List<Object[]> objs = readExcel.getExcelInfo(name, file);
        
		// 导入的数据行数必须等于1
		if (objs.size() != 1) {
			return ResultDTO.fail("导入数据为空或者条数大于1，请核对后再重新导入");
		}
        
		//判断12个月的数据是否带空值，是否为数字
		Object[] obj = objs.get(0);
		for (int i = 0; i < 12; i++) {
			if( obj[i] == null){
				return ResultDTO.fail("导入数据第"+(i+1)+"列有空数据，请核查导入的12个月数据不能有空值");
			}
			//判断是否为数字
			if( !isNumeric(obj[i].toString())){
				return ResultDTO.fail("导入数据第"+(i+1)+"列数据有非数字字符，请核查");
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
        
        ResultDTO result = reportService.BatchImportToDB(reportRepaymentHistory);

        //导入不成功，返回失败
		if(!result.isSuccess()){
			return result;
		}

        return ResultDTO.success();
    }
    
    /**
     * 判断字符串是否为数字
     * @param str
     * @return
     */
    public boolean isNumeric(String str){ 
    	   Pattern pattern = Pattern.compile("[0-9]*"); 
    	   Matcher isNum = pattern.matcher(str);
    	   if( !isNum.matches() ){
    	       return false; 
    	   } 
    	   return true; 
    	}
    
    /**
     * 查询月份数据
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/queryRepay", method = RequestMethod.GET)
    public ResultPageDTO pageRepay(Integer reportId){

    	Map<String, Integer> params = super.copyParamsToInteger(new String[] { "currentPageNum", "pageSize"});
        Integer page = params.get("currentPageNum");
        Integer rows = params.get("pageSize");

    	return reportService.pageRepay(reportId, page, rows);
    }
    
  /**
   * 查询全年风控还款能力历史与预测曲线图(编辑页面)
   * @param reportId
   * @return
   */
    @RequestMapping(value="/queryReportPre", method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO queryReportPre(@RequestParam Integer reportId){

        	return reportService.getByReportId(reportId);
    }
    /**
     * 查询全年风控经营流水趋势曲线图(编辑页面)
     * @param reportId
     * @return
     */
      @RequestMapping(value="/queryTradingVolumeReport", method = RequestMethod.POST)
      @ResponseBody
      public ResultDTO queryTradingVolumeReport(@RequestParam String innerCode, @RequestParam String merchantId) {
          return reportService.queryTradingVolumeReport(innerCode, NumberUtils.toInt(merchantId));
      }
      /**
       * 查询全年风控日均客单价测曲线图(编辑页面)
       * @param reportId
       * @return
       */
        @RequestMapping(value="/queryUnitPriceReport", method = RequestMethod.POST)
        @ResponseBody
        public ResultDTO queryUnitPriceReport(@RequestParam String innerCode, @RequestParam String merchantId) {
            return reportService.queryUnitPriceReport(innerCode, NumberUtils.toInt(merchantId));
        }
}
