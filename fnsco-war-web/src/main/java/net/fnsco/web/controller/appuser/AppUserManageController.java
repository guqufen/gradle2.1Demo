package net.fnsco.web.controller.appuser;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.ExcelUtils;
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.order.api.dto.AppUserManageDTO;
import net.fnsco.order.api.dto.AppUserMerchantDTO;
import net.fnsco.order.api.dto.BandDto;
import net.sf.json.JSONObject;

/**
 * @desc APP用户管理控制器
 * @author tangliang
 * @version 0.0.1-SNAPSHOT
 * @since Ver 1.1
 * @Date 2017年7月13日 下午1:31:13
 */
@Controller
@RequestMapping(value = "/web/appsuser")
public class AppUserManageController extends BaseController {

	@Autowired
	private AppUserService AppUserService;

	/**
	 * appMsgIndex:(这里用一句话描述这个方法的作用) 分页查询
	 *
	 * @param sysmsg
	 * @param currentPageNum
	 * @param pageSize
	 * @return 设定文件
	 * @return ResultPageDTO<SysAppMessage> DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions(value = { "sys:app:user:list" })
	public ResultPageDTO<AppUserManageDTO> appUserIndex(AppUserManageDTO sysmsg, Integer currentPageNum,
			Integer pageSize) {
		return AppUserService.queryPageList(sysmsg, currentPageNum, pageSize);
	}

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions(value = { "sys:app:user:export" })
	public void export(AppUserManageDTO sysmsg, HttpServletRequest req, HttpServletResponse response)
			throws IOException {
		List<AppUserManageDTO> dataList = AppUserService.queryAppPageList(sysmsg);
		if (dataList != null) {
			for (AppUserManageDTO merchantdo : dataList) {
				Date li = merchantdo.getRegTime();
				if (li != null) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String dateString = formatter.format(li);
					merchantdo.setRegTimeStr(dateString);
				}
			}
		}
		JSONObject jObject = new JSONObject();
		jObject.put("data", dataList);
		List<AppUserManageDTO> list = (List<AppUserManageDTO>) jObject.get("data");
		String itemMark = "userName,mobile,merNames,regTimeStr";
		String itemParap = "用户名称,手机号码,绑定店铺,注册时间";

		String[] itemMarks = itemMark.split(",");// 键
		String[] itemParaps = itemParap.split(",");// 列头

		HSSFWorkbook workbook = ExcelUtils.getInputStream(itemParaps.length, itemMarks, itemParaps, list, "APP用户管理");

		response.setContentType("application/vnd.ms-excel;");
		String nowStr = DateUtils.getNowDateStr2();
		String fileName = "APP用户管理" + nowStr + ".xls";
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1"));// 设定输出文件头

		OutputStream ouputStream = response.getOutputStream();
		workbook.write(ouputStream);

		ouputStream.flush();
		ouputStream.close();
	}

	@RequestMapping(value = "/modifyRoles", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "sys:app:user:update" })
	public ResultDTO modifyRole(BandDto bandDto) {
		return AppUserService.modifyRole(bandDto);
	}

	// 判断成为店主
	@RequestMapping(value = "/judgeRoles", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "sys:app:user:list" })
	public ResultDTO judgeRoles(@RequestBody AppUserMerchantDTO[] terminals) {
		List<AppUserMerchantDTO> params = Arrays.asList(terminals);
		return AppUserService.judgeRoles(params);
	}

	@RequestMapping(value = "/changeRole", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "sys:app:user:update" })
	public ResultDTO changeRole(@RequestBody AppUserMerchantDTO[] terminals) {
		List<AppUserMerchantDTO> params = Arrays.asList(terminals);
		return AppUserService.changeRole(params);
	}
}
