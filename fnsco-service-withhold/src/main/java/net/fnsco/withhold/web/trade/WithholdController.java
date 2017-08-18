package net.fnsco.withhold.web.trade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.withhold.service.trade.WithholdInfoService;
import net.fnsco.withhold.service.trade.WithholdService;
import net.fnsco.withhold.service.trade.entity.WithholdInfoDO;

@Controller
@RequestMapping(value = "/web/withholdInfo", method = RequestMethod.POST)
@Api(value = "/web/withholdInfo", tags = { "代扣管理" })
public class WithholdController extends BaseController {
    @Autowired
    private WithholdService withholdService;
    @Autowired
    private WithholdInfoService withholdInfoService;
    
    
    @ApiOperation(value = "代扣定时任务", notes = "代扣定时任务")
    @ResponseBody
    @RequestMapping(value = "collectPayment", method = RequestMethod.GET)
    public ResultDTO collectPayment(@RequestParam Integer type) {
        withholdService.collectPayment(type);
        return ResultDTO.success();
    }

    @ApiOperation(value = "代扣定时提前三天提醒", notes = "代扣定时提前三天提醒")
    @ResponseBody
    @RequestMapping(value = "collectPaymentRemind", method = RequestMethod.GET)
    public ResultDTO collectPaymentRemind() {
        withholdService.collectPaymentRemind();
        return ResultDTO.success();
    }
    //根据用户id查询代扣信息详情
    @ApiOperation(value = "查询代扣详情", notes = "查询代扣详情")
    @ResponseBody
    @RequestMapping(value = "queryById", method = RequestMethod.GET)
    public ResultDTO queryById(@RequestParam Integer id) {
        WithholdInfoDO dto=withholdInfoService.doQueryById(id);
        return ResultDTO.success(dto);
    }
}
