package net.fnsco.withhold.web.trade;

import java.util.Map;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import net.fnsco.withhold.comm.ApiConstant;
import net.fnsco.withhold.service.trade.WithholdInfoService;
import net.fnsco.withhold.service.trade.entity.WithholdInfoDO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value ="/web/withholdInfo", method = RequestMethod.POST)
@Api(value = "/web/withholdInfo", tags = {"代扣管理"})
public class WithholdInfoController extends BaseController {

 @Autowired
 private WithholdInfoService withholdInfoService;


 // 列表页
 @RequestMapping("/list")
 public String list() {
     return "";
 }

 // 分页
 @ApiOperation(value = "分页查询", notes = "分页查询")
 @ResponseBody
 @RequestMapping(value = "query", method = RequestMethod.GET)
 public ResultDTO page(WithholdInfoDO withholdInfo) {
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
 @RequestMapping(value = "doAdd")
 public ResultDTO doAdd ( WithholdInfoDO withholdInfo) {
    WithholdInfoDO   resultDO = this.withholdInfoService.doAdd(withholdInfo,super.getUserId());
    if(null == resultDO ){
    	return ResultDTO.fail(ApiConstant.WEB_BANK_CARD_NULL);
    }
    return success(resultDO);
 }

 // 修改
 @ApiOperation(value = "修改保存", notes = "修改保存")
 @ResponseBody
 @RequestMapping(value = "doUpdate")
 public ResultDTO doUpdate (WithholdInfoDO withholdInfo) {
     Integer result = this.withholdInfoService.doUpdate(withholdInfo,getUserId());
     return success(result);
 }

 // 删除
 @ApiOperation(value = "删除", notes = "删除")
 @ResponseBody
 @RequestMapping(value = "doDelete")
 public ResultDTO doDelete(@RequestBody WithholdInfoDO withholdInfo) {
     Integer result = this.withholdInfoService.doDelete(withholdInfo,getUserId());
     return success(result);
 }
 // 详情
 @ApiOperation(value = "查询详情", notes = "查询详情")
 @ResponseBody
 @RequestMapping(value = "detail")
 public ResultDTO detail(@RequestParam String id) {
     WithholdInfoDO result = this.withholdInfoService.doQueryById(NumberUtils.toInt(id));
     return success(result);
 }
}