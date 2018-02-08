package net.fnsco.web.carBrand;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import io.swagger.annotations.Api;
import net.fnsco.car.service.carBrand.CarBrandService;
import net.fnsco.car.service.carBrand.entity.CarBrandDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.OssLoaclUtil;

@RestController
@RequestMapping(value="/web/carBrand")
@Api(value="/web/carBrand", tags={"汽车品牌新增与查询url"})
public class carBrandController extends BaseController{

	@Autowired
	private CarBrandService carBrandService;
	@Autowired
	private Environment env;
	
	@RequestMapping(value = "/list")
//	@RequiresPermissions(value = { "car:brand:list" })
	public ResultPageDTO<CarBrandDO> pageList(CarBrandDO carBrandDO,Integer currentPageNum,Integer pageSize){
		ResultPageDTO<CarBrandDO> pager = carBrandService.page(carBrandDO, currentPageNum, pageSize);
		return pager;
	}
	
	@RequestMapping(value = "/selectMenuTree")
	public ResultDTO<CarBrandDO> selectMenuTree(CarBrandDO carBrandDO){
		ResultDTO<CarBrandDO> pager = carBrandService.selectMenuTree(carBrandDO);
		return pager;
	}
	
	@RequestMapping(value = "/doAdd")
	public ResultDTO doAdd(CarBrandDO carBrandDO) {

		carBrandService.insert(carBrandDO);
		return success();
	}
	
	@RequestMapping(value = "/doUpdate")
	public ResultDTO doUpdate(CarBrandDO carBrandDO) {

		return carBrandService.update(carBrandDO);
	}

	@RequestMapping(value = "/delete")
	public ResultDTO delete(Integer[] ids) {	
		
		return carBrandService.delete(ids);
	}

	/**
	 * 图片上传(导入),上传到oss服务器
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/doImport")
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
