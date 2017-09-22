package net.fnsco.risk.web.report;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
@Controller
@RequestMapping(value = "/report", method = RequestMethod.POST)
public class ReportAdminController extends BaseController{
    @Autowired
    private ReportService reportService;
    @Autowired
    private Environment        env;
    
    // 分页
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ResponseBody
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public ResultDTO page(ReportInfoDO reportInfoDO) {
        Map<String, Integer> params = super.copyParamsToInteger(new String[] { "currentPageNum", "pageSize" });
        Integer page = params.get("currentPageNum");
        Integer rows = params.get("pageSize");
        ResultPageDTO<ReportInfoDO> pager = this.reportService.pageBack(reportInfoDO, page,rows);
        return success(pager);
    }
    //通知商户风控报告
    @ResponseBody
    @RequestMapping(value = "headPersonnelMes", method = RequestMethod.GET)
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
    @RequestMapping(value = "getById", method = RequestMethod.GET)
    public ResultDTO getById(ReportInfoDO reportInfoDO){

    	return reportService.getById(reportInfoDO);
    }
    
    /**
     *  修改风控报告
     * @param reportInfoDO
     * @return
     */
    @ResponseBody
    @RequestMapping(value="updateReport", method = RequestMethod.GET)
    public ResultDTO updateReport(ReportInfoDO reportInfoDO){
    	
    	//如果传过来的ID为空，返回成功
    	if(reportInfoDO.getId() == null){
    		return ResultDTO.success();
    	}
    	
		return reportService.updateReport(reportInfoDO);
    }

    /** 
     * 下载模版 
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value="down", method = RequestMethod.GET)
    public void down() throws IOException{

    	String filePath = request.getSession().getServletContext().getRealPath("");
        filePath = filePath + "template/risk_inf_format.xls";
        String fileName = "risk_inf_format.xls";

         //解析excel，获取客户信息集合。
         ReadExcel.downTemplate(filePath, fileName, response);
    }

    /**
     * 导入数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value="doImport", method = RequestMethod.POST)
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
        ResultDTO result = reportService.BatchImportToDB(objs, id);

        //导入不成功，返回失败
		if(!result.isSuccess()){
			return ResultDTO.fail();
		}

        return ResultDTO.success();
    }
    
    /**
     * 查询月份数据
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="queryRepay", method = RequestMethod.GET)
    public ResultPageDTO pageRepay(Integer reportId){

    	Map<String, Integer> params = super.copyParamsToInteger(new String[] { "currentPageNum", "pageSize"});
        Integer page = params.get("currentPageNum");
        Integer rows = params.get("pageSize");

    	return reportService.pageRepay(reportId, page, rows);
    }
    
  /**
   * 查询全年风控曲线图(编辑页面)
   * @param reportId
   * @return
   */
    @RequestMapping(value="queryReportPre", method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO queryReportPre(@RequestParam Integer reportId){

        	return reportService.getByReportId(reportId);
    }
}
