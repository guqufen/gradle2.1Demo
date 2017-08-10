package net.fnsco.auth.controller.web.dept;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import net.fnsco.auth.service.dept.SysDeptService;
import net.fnsco.auth.service.sys.entity.DeptDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
@Controller
@RequestMapping(value = "/web/dept")
public class DeptManageController extends BaseController {
    
	private SysDeptService sysDeptService;
	

	 // 分页
	@RequestMapping(value = "/query",method= RequestMethod.GET)
	@ResponseBody
	 public ResultDTO<DeptDO> query(DeptDO dept,@RequestParam("currentPageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize) {
		 ResultPageDTO<DeptDO> result=sysDeptService.queryList(dept, pageNum, pageSize);
	     return success(result);
	 }
}














