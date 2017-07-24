package net.fnsco.withhold.web.trade;

import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.withhold.service.trade.WithholdLogService;
import net.fnsco.withhold.service.trade.entity.WithholdLogDO;

@Controller
@RequestMapping(value ="/withholdLog", method = RequestMethod.POST)
@Api(value = "/withholdLog", tags = {""})
public class WithholdLogController extends BaseController {

 @Autowired
 private WithholdLogService withholdLogService;


 // 列表页
 @RequestMapping("/list")
 public String list() {
     return "";
 }

 // 分页
 @ApiOperation(value = "分页查询", notes = "分页查询")
 @ResponseBody
 @RequestMapping(value = "query", method = RequestMethod.GET)
 public ResultDTO page(@RequestBody WithholdLogDO withholdLog) {
     logger.info("开始分页查询WithholdLogController.page, withholdLog=" + withholdLog.toString());
     Map<String, Integer> params = super.copyParamsToInteger(new String[] { "page", "rows" });
     Integer page = params.get("page");
     Integer rows = params.get("rows");
     ResultPageDTO<WithholdLogDO> pager = this.withholdLogService.page(withholdLog, page,rows);
     return success(pager);
 }

 // 添加
 @ApiOperation(value = "新增保存", notes = "新增保存")
 @ResponseBody
 @RequestMapping(value = "doAdd")
 public ResultDTO doAdd (@RequestBody WithholdLogDO withholdLog) {
    WithholdLogDO   resultDO = this.withholdLogService.doAdd(withholdLog,super.getUserId());
    return success(resultDO);
 }

 // 修改
 @ApiOperation(value = "修改保存", notes = "修改保存")
 @ResponseBody
 @RequestMapping(value = "doUpdate")
 public ResultDTO doUpdate (@RequestBody WithholdLogDO withholdLog) {
     Integer result = this.withholdLogService.doUpdate(withholdLog,getUserId());
     return success(result);
 }

 // 删除
 @ApiOperation(value = "删除", notes = "删除")
 @ResponseBody
 @RequestMapping(value = "doDelete")
 public ResultDTO doDelete(@RequestBody WithholdLogDO withholdLog) {
     Integer result = this.withholdLogService.doDelete(withholdLog,getUserId());
     return success(result);
 }
 // 详情
 @ApiOperation(value = "查询详情", notes = "查询详情")
 @ResponseBody
 @RequestMapping(value = "detail")
 public ResultDTO detail(@RequestParam String id) {
     WithholdLogDO result = this.withholdLogService.doQueryById(NumberUtils.toInt(id));
     return success(result);
 }
}