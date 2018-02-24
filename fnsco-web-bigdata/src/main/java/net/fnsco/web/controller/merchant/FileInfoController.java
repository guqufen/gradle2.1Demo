package net.fnsco.web.controller.merchant;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.google.common.base.Strings;

import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.bigdata.api.merchant.MerchantInfoService;
import net.fnsco.bigdata.service.dao.master.MerchantFileDao;
import net.fnsco.bigdata.service.dao.master.MerchantFileTempDao;
import net.fnsco.bigdata.service.domain.MerchantFile;
import net.fnsco.bigdata.service.domain.MerchantFileTemp;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.constants.CoreMapConstants;
import net.fnsco.core.file.FileService;
import net.fnsco.core.utils.OssLoaclUtil;

/**
 * @desc 文件上传控制器
 * @author tangliang
 * @date 2017年6月21日 上午10:57:41
 */
@Controller
@RequestMapping(value = "/web/fileInfo")
public class FileInfoController extends BaseController {

	@Autowired
	private Environment env;

	@Autowired
	private MerchantInfoService merchantInfoService;

	@Autowired
	private MerchantFileTempDao merchantFileTempDao;

	@Autowired
	private MerchantFileDao merchantFileDao;

	@Autowired
	private MerchantCoreService merchantCoreService;
	
	@Autowired
	private FileService fileService;

	/**
	 * 
	 * getInnoCode:(获取innocode，为了防止重复，需要在获取最新的innercode后去对比，如果没有才能作为最新的innerCode
	 * ,否则重新获取校验，一直到唯一)
	 * 
	 * @return 设定文件
	 * @author tangliang
	 * @date 2017年9月1日 下午1:31:07
	 * @return String DOM对象
	 */
	@ResponseBody
	@RequestMapping(value = "/getInnoCode",method = RequestMethod.POST)
	public String getInnoCode() {

		String innerCode = merchantCoreService.getInnerCode();

		return innerCode;
	}

	/**
	 * saveFiles:(这里用一句话描述这个方法的作用)保存文件信息
	 *
	 * @param fileIds
	 * @return 设定文件
	 * @return String DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	@ResponseBody
	@RequestMapping(value = "/savefiles",method = RequestMethod.POST)
	@Transactional
	public String saveFiles(String fileIds) {
		if (!StringUtils.isEmpty(fileIds)) {
			String[] ids = fileIds.split(",");
			for (String fileId : ids) {
				MerchantFileTemp fileTemp = merchantFileTempDao.selectByPrimaryKey(Integer.valueOf(fileId));
				if (null != fileTemp) {
					MerchantFile file = new MerchantFile();
					BeanUtils.copyProperties(fileTemp, file);
					file.setId(null);
					merchantFileDao.insertSelective(file);
					merchantFileTempDao.deleteByPrimaryKey(Integer.valueOf(fileId));
				}
			}

		}
		return "success";
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

	/**
	 * msgFileUpImport:(这里用一句话描述这个方法的作用) 消息推送文件上传
	 *
	 * @param req
	 * @param response
	 * @return 设定文件
	 * @return String DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	@RequestMapping(value = "/msgfile", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String msgFileUpImport(HttpServletRequest req, HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			// 上传文件原名
			MultipartFile file = entity.getValue();

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
					TreeMap<String, String> paras = new TreeMap<>();
					paras.put("url", newUrl);
					String json = JSONArray.toJSONString(paras);
					response.getWriter().write(json);

				} catch (Exception e) {
					logger.error(fileName + "上传失败！" + e);
					throw new RuntimeException();
				}
			} else {
				logger.error(fileName + "上传失败");
				throw new RuntimeException();
			}

		}
		return null;

	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public String deleteImger(Integer id, String url) {
		// 删除服务器已经保存的对应图片
		boolean re = merchantInfoService.deleteFromDB(id, url);
		if (re) {
			return "true";
		}
		return "false";
	}

	/**
	 * deleteOssFile:(这里用一句话描述这个方法的作用)删除OSS上文件文件
	 *
	 * @param url
	 * @return 设定文件
	 * @return String DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	@RequestMapping(value = "/deleteOssFile",method = RequestMethod.POST)
	@ResponseBody
	public String deleteOssFile(String url) {
		String fileKey = url.substring(url.lastIndexOf("^") + 1);
		OssLoaclUtil.deleteFile(OssLoaclUtil.getHeadBucketName(), fileKey);
		return null;
	}

	/**
	 * getImagePath:(这里用一句话描述这个方法的作用)查看文件外网路径
	 *
	 * @param url
	 * @return 设定文件
	 * @return String DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	@RequestMapping(value = "/getImagePath",method = RequestMethod.POST)
	@ResponseBody
	public String getImagePath(String url) {
		if (!Strings.isNullOrEmpty(url)) {
			String path = url.substring(url.indexOf("^") + 1);
			return OssLoaclUtil.getFileUrl(OssLoaclUtil.getHeadBucketName(), path);
		}
		return "";
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

			MerchantFileTemp fileInfo = new MerchantFileTemp();
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
					fileInfo.setInnerCode(innerCode);
					fileInfo.setFilePath(newUrl);
					fileInfo.setFileType(fileType);
					fileInfo.setFileName(fileName);
					fileInfo.setCreateTime(new Date());
					ResultDTO<Integer> result = merchantInfoService.doAddToDB(fileInfo, 0);
					if (result.isSuccess()) {
						ResultDTO<TreeMap<String, String>> appResult = null;

						TreeMap<String, String> paras = new TreeMap<>();
						paras.put("id", String.valueOf(result.getData()));
						paras.put("url", newUrl);
						paras.put("fileType", fileType);

						appResult = ResultDTO.success(paras);
						String json = isApp ? JSONArray.toJSONString(appResult) : JSONArray.toJSONString(paras);
						response.getWriter().write(json);
					} else {
						logger.error(fileName + "上传失败");
						throw new RuntimeException();
					}

				} catch (Exception e) {
					logger.error(fileName + "上传失败！" + e);
					throw new RuntimeException();
				}
			} else {
				logger.error(fileName + "上传失败");
				throw new RuntimeException();
			}

		}

		return null;
	}
	
	/**
	 * doGetFileStream:(获取文件信息)
	 *
	 * @param      设定文件
	 * @return void    DOM对象
	 * @author tangliang
	 * @date   2017年12月27日 下午3:16:30
	 */
	@GetMapping(value="/doGetFileStream")
	public void doGetFileStream() {
		String fileRelativePath = request.getParameter("filePath");
		if(!Strings.isNullOrEmpty(fileRelativePath)){
			fileRelativePath = fileRelativePath.substring(fileRelativePath.indexOf("^")+1);
	    }
		String fileType = fileRelativePath.substring(fileRelativePath.lastIndexOf(".") + 1).toLowerCase();
		 try {
			 byte[] data = fileService.getFileByteArray(fileRelativePath);
			 OutputStream output = response.getOutputStream();// 得到输出流
			 response.setContentType(CoreMapConstants.imageContentType.get(fileType));// 设定输出的类型
			 output.write(data);
			 output.flush();
		} catch (IOException e) {
			logger.error("获取文件异常!",e);
		}
	}
	
	
}