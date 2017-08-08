package net.fnsco.auth.controller.web.dept;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yaml.snakeyaml.scanner.Constant;

import io.swagger.annotations.ApiOperation;
import net.fnsco.auth.service.dept.SysDeptService;
import net.fnsco.auth.service.sys.entity.DeptDO;
import net.fnsco.auth.service.sys.entity.SysDeptEntity;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
@Controller
@RequestMapping(value = "/web/dept")
public class DeptManageController extends BaseController {
    
	private SysDeptService sysDeptService;
	
	 // 列表页
	 @RequestMapping("/list")
	 public String list() {
	     return "";
	 }

	 // 分页
	 @ApiOperation(value = "分页查询", notes = "分页查询")
	 @ResponseBody
	 @RequestMapping(value = "query", method = RequestMethod.GET)
	 public ResultDTO page(@RequestBody DeptDO dept) {
	     logger.info("开始分页查询BankCodeController.page, bankCode=" + dept.toString());
	     Map<String, Integer> params = super.copyParamsToInteger(new String[] { "page", "rows" });
	     Integer page = params.get("page");
	     Integer rows = params.get("rows");
	     ResultPageDTO<DeptDO> pager = this.sysDeptService.page(dept, page,rows);
	     return success(pager);
	 }

	 // 添加
	 @ApiOperation(value = "新增保存", notes = "新增保存")
	 @ResponseBody
	 @RequestMapping(value = "doAdd")
	 public ResultDTO doAdd (@RequestBody DeptDO dept) {
		 DeptDO   resultDO = this.sysDeptService.doAdd(dept,super.getUserId());
	    return success(resultDO);
	 }

	 // 修改
	 @ApiOperation(value = "修改保存", notes = "修改保存")
	 @ResponseBody
	 @RequestMapping(value = "doUpdate")
	 public ResultDTO doUpdate (@RequestBody DeptDO dept) {
	     Integer result = this.sysDeptService.doUpdate(dept,getUserId());
	     return success(result);
	 }

	 // 删除
	 @ApiOperation(value = "删除", notes = "删除")
	 @ResponseBody
	 @RequestMapping(value = "doDelete")
	 public ResultDTO doDelete(@RequestBody DeptDO dept) {
	     Integer result = this.sysDeptService.doDelete(dept,getUserId());
	     return success(result);
	 }

}














