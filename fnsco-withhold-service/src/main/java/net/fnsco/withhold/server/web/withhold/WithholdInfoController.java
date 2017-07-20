package net.fnsco.withhold.server.web.withhold;

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
import net.fnsco.withhold.server.service.withhold.WithholdInfoService;
import net.fnsco.withhold.server.service.withhold.entity.WithholdInfoDO;

@Controller
@RequestMapping(value ="/withholdInfo", produces = "application/*;charset=utf-8", method = RequestMethod.POST)
@Api(value = "/withholdInfo", tags = {""})
public class WithholdInfoController extends BaseController {

 @Autowired
 private WithholdInfoService withholdInfoService;


 // 列表页
 @RequestMapping("/list.htm")
 public String list() {
     return "";
 }

 // 分页
 @ApiOperation(value = "分页查询", notes = "分页查询")
 @ResponseBody
 @RequestMapping(value = "query.htm", method = RequestMethod.GET)
 public ResultDTO page(@RequestBody WithholdInfoDO withholdInfo) {
     logger.info("开始分页查询WithholdInfoController.page, withholdInfo=" + withholdInfo.toString());
     Map<String, Integer> params = super.copyParamsToInteger(new String[] { "page", "rows" });
     Integer page = params.get("page");
     Integer rows = params.get("rows");
     ResultPageDTO<WithholdInfoDO> pager = this.withholdInfoService.page(withholdInfo, page,rows);
     return success(pager);
 }

 // 添加
 @ApiOperation(value = "新增保存", notes = "新增保存")
 @ResponseBody
 @RequestMapping(value = "doAdd.htm")
 public ResultDTO doAdd (@RequestBody WithholdInfoDO withholdInfo) {
    WithholdInfoDO   resultDO = this.withholdInfoService.doAdd(withholdInfo,super.getUserId());
    return success(resultDO);
 }

 // 修改
 @ApiOperation(value = "修改保存", notes = "修改保存")
 @ResponseBody
 @RequestMapping(value = "doUpdate.htm")
 public ResultDTO doUpdate (@RequestBody WithholdInfoDO withholdInfo) {
     Integer result = this.withholdInfoService.doUpdate(withholdInfo,getUserId());
     return success(result);
 }

 // 删除
 @ApiOperation(value = "删除", notes = "删除")
 @ResponseBody
 @RequestMapping(value = "doDelete.htm")
 public ResultDTO doDelete(@RequestBody WithholdInfoDO withholdInfo) {
     Integer result = this.withholdInfoService.doDelete(withholdInfo,getUserId());
     return success(result);
 }
 // 详情
 @ApiOperation(value = "查询详情", notes = "查询详情")
 @ResponseBody
 @RequestMapping(value = "detail.htm")
 public ResultDTO detail(@RequestParam String id) {
     WithholdInfoDO result = this.withholdInfoService.doQueryById(NumberUtils.toInt(id));
     return success(result);
 }
}