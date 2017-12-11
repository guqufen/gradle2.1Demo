package net.fnsco.web.controller.open.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.car.service.city.DicCityService;
import net.fnsco.car.service.city.entity.DicCityDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.web.controller.vo.QueryCityVO;

@RestController
@RequestMapping(value = "/h5/city", method = RequestMethod.GET)
@Api(value = "/h5/city", tags = { "业务申请-查询城市" })
public class CityChooseController extends BaseController {
	@Autowired
	private DicCityService dicCityService;

	// 查询城市接口
	@RequestMapping(value = "/queryCity")
	@ApiOperation(value = "买车申请-查询城市")
	public ResultDTO<List<QueryCityVO>> queryCityList() {
		List<QueryCityVO> resultList = new ArrayList<>();
		QueryCityVO queryCity = new QueryCityVO();
		List<DicCityDO> list = dicCityService.queryCityList();
		if (list.size() > 0) {
			for (DicCityDO dicCityDO : list) {
				queryCity.setValue(dicCityDO.getId());
				queryCity.setText(dicCityDO.getName());
				resultList.add(queryCity);
			}
		}
		return ResultDTO.success(resultList);
	}

}
