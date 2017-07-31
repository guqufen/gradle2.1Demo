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
import net.fnsco.withhold.service.trade.WithholdService;

@Controller
@RequestMapping(value = "/web/withholdInfo", method = RequestMethod.POST)
@Api(value = "/web/withholdInfo", tags = { "代扣管理" })
public class WithholdController extends BaseController {
    @Autowired
    private WithholdService withholdService;

    /**
     * 
     * collectPayment:(这里用一句话描述这个方法的作用)
     *
     * @param type 0首次1第二次2第三次扣款
     * @return   ResultDTO    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
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
}
