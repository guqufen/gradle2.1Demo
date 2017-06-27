package net.fnsco.controller.web.merchant;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONArray;

import net.fnsco.api.merchant.MerchantInfoService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.CodeUtil;
import net.fnsco.service.domain.MerchantFile;
/**
 * @desc 文件上传控制器
 * @author tangliang
 * @date 2017年6月21日 上午10:57:41
 */
@Controller
@RequestMapping("/web/fileInfo")
public class FileInfoController extends BaseController{

	@Autowired
	private Environment env;

	@Autowired
	private MerchantInfoService merchantInfoService;

	// 列表页
	@RequestMapping("/list")
	public String list() {
		return "ups/control/fileUploadView";
	}
	
	/**
	 * 获取innocode
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getInnoCode")
	public String getInnoCode(){
		return CodeUtil.generateMerchantCode("F");
	}


	@ResponseBody
	@RequestMapping(value = "/Import", produces = "text/html;charset=UTF-8")
	public String Import(HttpServletRequest req, HttpServletResponse response) {
		return commImport(req, response, false);
	}

	@ResponseBody
	@RequestMapping(value = "/app/fileInfo/Import", produces = "text/html;charset=UTF-8")
	public String appImport(HttpServletRequest req, HttpServletResponse response) {
		return commImport(req, response, true);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String deleteImger(Integer id){
		boolean re = merchantInfoService.deleteFromDB(id);
		if(re){
			return "true";
		}
		return "false";
	}
	
	/**
	 * @param req
	 * @param response
	 * @param isApp
	 * @return
	 */
	private String commImport(HttpServletRequest req, HttpServletResponse response, boolean isApp) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			// 上传文件原名
			MultipartFile file = entity.getValue();

			MerchantFile fileInfo = new MerchantFile();
			String innerCode = req.getParameter("innerCode");
			String fileType = req.getParameter("fileTypeKey");
			String fileName = file.getOriginalFilename();
			String line = System.getProperty("file.separator");// 文件分割符
			// 保存文件的路径
			String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
			// 数据库存的路径
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			String stry = this.env.getProperty("fileUpload.url") + line + year;// +"\\"+month+"\\";
			File yearPath = new File(stry);
			// 如果文件夹不存在则创建
			if (!yearPath.exists()) {
				logger.info("年份目录不存在");
				yearPath.mkdirs();
			} else {
				logger.info("年份目录已存在");
			}

			String strm = this.env.getProperty("fileUpload.url") + line + year + line + month + line;
			File monthPath = new File(strm);
			if (!monthPath.exists()) {
				logger.info("月份目录不存在");
				monthPath.mkdirs();
			} else {
				logger.info("月份目录已存在");
			}

			// String str = year+"/"+month+"/";
			String yearMonthPath = year + line + month + line;
			String filepath = yearMonthPath + System.currentTimeMillis() + "." + prefix;

			String fileURL = this.env.getProperty("fileUpload.url") + line + filepath;

			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();

					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileURL));
					stream.write(bytes);
					stream.close();

					String newUrl = null;

					if(!isApp){
						newUrl = StringUtils.replace(filepath, line, "^");
//						fileInfo.setSaveURL(newUrl);
					}else{

						newUrl = filepath;

						if(!"/".equals(line)){
							newUrl = StringUtils.replace(filepath, line, "/");
						}

//						fileInfo.setSaveURL(newUrl);
					}

					fileInfo.setInnerCode(innerCode);
					fileInfo.setFilePath(newUrl);
					fileInfo.setFileType(fileType);
					fileInfo.setFileName(fileName);
					fileInfo.setCreateTime(new Date());
//					SysUser loginUser = (SysUser)getSessionUser();
					ResultDTO<Integer> result = merchantInfoService.doAddToDB(fileInfo,0);
					if (result.isSuccess()) {
						ResultDTO<TreeMap<String, String>> appResult = new ResultDTO<>();
						appResult.setSuccess("上传成功");
						appResult.setCode("200");

						TreeMap<String, String> paras = new TreeMap<>();

						paras.put("id", String.valueOf(result.getData()));
						paras.put("url", newUrl);
						paras.put("fileType", fileType);

						appResult.setData(paras);

//						String json = isApp?Result.toNewJson(appResult):Result.toNewJson(paras);//待定
						String json = isApp?JSONArray.toJSONString(appResult):JSONArray.toJSONString(paras);
						response.getWriter().write(json);
					} else {
						logger.error(fileName + "上传失败");
						throw new RuntimeException();
					}

				} catch (Exception e) {
					logger.error(fileName + "上传失败！"+e);
					throw new RuntimeException();
				}
			} else {
				logger.error(fileName + "上传失败");
				throw new RuntimeException();
			}

		}

		return null;
	}

}