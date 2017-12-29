package net.fnsco.web.controller.industry;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.bigdata.service.sys.IndustryService;
import net.fnsco.bigdata.service.sys.entity.IndustryDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

/**
 * 负责对行业表查询
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/web/industry")
public class IndustryController extends BaseController{

	@Autowired
	private IndustryService industryService;
	
	@ResponseBody
	@RequestMapping("/queryAll")
	public ResultDTO queryAll(){
		
		return industryService.queryAll();
	}
	@ResponseBody
	@RequestMapping("queryList")
	public ResultDTO pageList(IndustryDO industryDO){
		Map<String, Integer> params = super.copyParamsToInteger(new String[] { "currentPageNum", "pageSize" });
        Integer page = params.get("currentPageNum");
        Integer rows = params.get("pageSize");
        ResultPageDTO<IndustryDO> pager = this.industryService.pageNameList(industryDO, page, rows);
		return success(pager);
	}
}
