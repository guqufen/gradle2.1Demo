package net.fnsco.web.controller.open;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.car.service.carBrand.CarBrandService;
import net.fnsco.car.service.carBrand.entity.CarBrandDO;
import net.fnsco.core.base.ResultDTO;

@RestController
@RequestMapping(value = "/api/carBrand")
@Api(value = "/api/carBrand", tags = { "汽车品牌" })
public class CarBrandController {

	@Autowired
	private CarBrandService carBrandServic;

	@RequestMapping("/selectHot")
	@ApiOperation("查询热门汽车品牌")
	public ResultDTO selectHot(){
		return carBrandServic.selectHot();
	}

	@RequestMapping("/selectAll")
	@ApiOperation("查询汽车品牌,A-Z排序")
	public ResultDTO<Map<String, Set<CarBrandDO>>> selectAll() {
		return carBrandServic.selectAll();
	}

	@RequestMapping("/selectChild")
	@ApiOperation("通过父id查询汽车品牌子对象数据")
	public ResultDTO selectChild(@RequestBody Integer id) {
		return carBrandServic.selectChild(id);
	}
}
