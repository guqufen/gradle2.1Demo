package net.fnsco.web.controller.merchant;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.fnsco.bigdata.service.modules.merchant.MerchantInfoImportService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.ReadExcel;

@Controller
@RequestMapping(value = "/web/merchantinfoImport")
public class MerchantInfoImportController extends BaseController {

	@Autowired
	private MerchantInfoImportService merchantInfoImportService;

	// 重写doPost方法，处理事件识别请求
	@RequestMapping(value = "/doImport", method = RequestMethod.POST)
	@ResponseBody
	protected Map<String, String> doImport(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

		MultipartFile file = fileMap.get("excel_file");
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
		// 批量导入。参数：文件名，文件。
		ResultDTO<String> result = merchantInfoImportService.batchImportToDB(customerList);
		if (result.isSuccess()) {
			String Msg = "批量导入EXCEL成功！";
			request.getSession().setAttribute("msg", Msg);
		} else {
			String Msg = "批量导入EXCEL失败！";
			request.getSession().setAttribute("msg", Msg);
			Map<String, String> map = new HashMap<>();
			map.put("data", result.getMessage());
			return map;
		}
		Map<String, String> map = new HashMap<>();
		map.put("data", "success");
		return map;
	}
}