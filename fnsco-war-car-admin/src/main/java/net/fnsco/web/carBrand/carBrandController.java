package net.fnsco.web.carBrand;

import java.io.File;
import java.io.IOException;
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
	public ResultDTO<CarBrandDO> selectMenuTree(){
		ResultDTO<CarBrandDO> pager = carBrandService.selectMenuTree();
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
	public ResultDTO delete(CarBrandDO carBrandDO) {

//		carBrandService.delete(carBrandDO);
		return carBrandService.delete(carBrandDO);
	}
	
	/**
	 * 图片上传(导入)
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
		
		File f = new File("E:\\img\\brand\\"+name);

		try {
			file.transferTo(f);
			resultPath = "/img/brand/"+name;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResultDTO.success(resultPath);
	}
}
