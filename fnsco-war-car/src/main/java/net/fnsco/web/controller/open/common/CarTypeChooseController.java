package net.fnsco.web.controller.open.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.car.service.carBrand.CarBrandService;
import net.fnsco.car.service.carBrand.entity.CarBrandDO;
import net.fnsco.car.service.city.entity.DicCityDO;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.web.controller.vo.QueryBrandVO;

@RestController
@RequestMapping(value = "/h5/type", method = RequestMethod.POST)
@Api(value = "/h5/type", tags = { "业务申请-查询汽车品牌" })
public class CarTypeChooseController {
	
	@Autowired
	private CarBrandService carBrandService;

	// 查询城市接口
	@RequestMapping(value = "/queryBrand")
	@ApiOperation(value = "业务申请-查询汽车品牌")
	public ResultDTO<List<QueryBrandVO>> queryBrandList() {
		List<QueryBrandVO> resultList = new ArrayList<>();
		QueryBrandVO queryBrand = new QueryBrandVO();
		List<CarBrandDO> list = carBrandService.queryCityList();
		if (list.size() > 0) {
			for (CarBrandDO carBrandDO : list) {
				queryBrand.setValue(carBrandDO.getId());
				queryBrand.setText(carBrandDO.getName());
				resultList.add(queryBrand);
			}
		}
		return ResultDTO.success(resultList);
	}

}
