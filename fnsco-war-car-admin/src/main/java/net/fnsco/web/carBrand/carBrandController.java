package net.fnsco.web.carBrand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

		carBrandService.update(carBrandDO);
		return success();
	}
	
	@RequestMapping(value = "/delete")
	public ResultDTO delete(CarBrandDO carBrandDO) {

//		carBrandService.delete(carBrandDO);
		return carBrandService.delete(carBrandDO);
	}
}
