package net.fnsco.risk.web.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.risk.service.report.IndustryService;

/**
 * 负责对行业表查询
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/industry")
public class IndestryController extends BaseController{

	@Autowired
	private IndustryService industryService;
	
	@ResponseBody
	@RequestMapping("queryAll")
	public ResultDTO queryAll(){
		
		return industryService.queryAll();
	}
}
