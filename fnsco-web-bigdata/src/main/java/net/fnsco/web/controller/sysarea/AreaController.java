package net.fnsco.web.controller.sysarea;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.bigdata.api.merchant.AreaService;
import net.fnsco.bigdata.service.domain.Area;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;

@Controller
@RequestMapping("/area")
public class AreaController extends BaseController{

	@Autowired
	private AreaService areaService;

	/**
	 * 加载省份
	 * @return
	 */
	@RequestMapping("showProvinceCityArea")
	@ResponseBody
	public ResultDTO<Area> showProvinceCityArea(){
		List<Area> areas = areaService.getProvinceList();
		return success(areas);
	}
	
	/**
	 * 加载市/区
	 * @param areaId
	 * @return
	 */
	@RequestMapping("areaChange")
	@ResponseBody
	public ResultDTO<Area> areaChange(@RequestParam("areaId") Integer areaId){
		List<Area> areas = areaService.getListBySupperId(areaId);
		return success(areas);
	} 
}
