package net.fnsco.risk.web.report;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.risk.service.report.sysConfigService;

@Controller
@RequestMapping(value="/sysConfig", method=RequestMethod.POST)
public class sysConfigController {

	@Autowired
	private sysConfigService sysConfigService;

	@ResponseBody
	@RequestMapping(value="getByType", method=RequestMethod.GET)
	public ResultDTO getByType(@Param("type") String type){
		return sysConfigService.getByType(type);
	}
}
