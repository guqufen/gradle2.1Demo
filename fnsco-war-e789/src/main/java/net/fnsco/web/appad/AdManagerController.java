package net.fnsco.web.appad;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.OssLoaclUtil;
import net.fnsco.order.service.ad.AdService;
import net.fnsco.order.service.ad.entity.AdDO;

@Controller
@RequestMapping(value = "/web/e789/ad")
public class AdManagerController extends BaseController{
	
	@Autowired
	private AdService adService;
	@Autowired
	private Environment env;
	
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions(value = { "sys:app:ad:list" })
	public ResultPageDTO<AdDO> adIndex(AdDO appAdDTO, Integer currentPageNum,
			Integer pageSize) {
		return adService.page(appAdDTO, currentPageNum, pageSize);
	}
	
	
	@RequestMapping(value = "/doAdd", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "sys:app:ad:add" })
	public void add(AdDO appAdDTO) {
		Integer userId = this.getUserId();
		adService.doAdd(appAdDTO,userId);
	}

	
	
	/**
	 * 图片上传(导入),上传到oss服务器
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/doImport" , method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO doImport(){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		MultipartFile file = fileMap.get("excel_file_risk_inf");
		
		// 判断文件是否为空
		if (file == null) {
			return null;
		}
		// 获取文件名
		String name = file.getOriginalFilename();
		String resultPath = null;

		// 判断文件是否为空（即判断其大小是否为0或其名称是否为null）
		long size = file.getSize();
		if (name == null || ("").equals(name) && size == 0) {
			return null;
		}
		
		// 保存的文件名后缀
		String prefix = name.substring(name.lastIndexOf(".") + 1);
		String line = System.getProperty("file.separator");// 文件分割符

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

		String yearMonthPath = year + line + month + line;
		String newFileName = System.currentTimeMillis() + "." + prefix;
		String fileKey = year + "/" + month + "/" + newFileName;
		String filepath = yearMonthPath + newFileName;

		String fileURL = this.env.getProperty("fileUpload.url") + line + filepath;

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileURL));
				stream.write(bytes);
				stream.close();

				// 上传阿里云OSS文件服务器
				OssLoaclUtil.uploadFile(fileURL, fileKey);
				String newUrl = OssLoaclUtil.getHeadBucketName() + "^" + fileKey;
				logger.warn("图片地址："+newUrl);
				return ResultDTO.success(newUrl);
			} catch (Exception e) {
				logger.error(name + "上传失败！" + e);
				throw new RuntimeException();
			}
		} else {
			logger.error(name + "上传失败");
			throw new RuntimeException();
		}
	}
}
