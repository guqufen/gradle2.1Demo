package net.fnsco.web.controller.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.service.act.ApplyUserService;
import net.fnsco.order.service.act.LoanApplyUserService;
import net.fnsco.order.service.act.entity.ApplyUserDO;
import net.fnsco.order.service.act.entity.LoanApplyUserDO;

/**
 * 
 * @desc 
 * @author   sxf
 * @version  
 * @since    Ver 1.1
 * @Date	 2017年10月11日 上午11:17:18
 *
 */
@RestController
@RequestMapping(value = "/mobile/act", method = RequestMethod.POST)
public class ApplyUserController extends BaseController {
    @Autowired
    private ApplyUserService     applyUserService;
    @Autowired
    private LoanApplyUserService loanApplyUserService;

    @RequestMapping("/addApplyUser")
    @ApiOperation(value = "微信推荐成为数钱吧的用户活动，保存活动报名用户信息保存")
    public ResultDTO<String> addApplyUser(ApplyUserDO applyUser) {
        applyUserService.doAdd(applyUser);
        return ResultDTO.success();
    }

    @RequestMapping("/addLoanApplyUser")
    @ApiOperation(value = "收银台贷贷看申请活动用户信息保存")
    public ResultDTO<String> addLoanApplyUser(LoanApplyUserDO loanApplyUserDO) {
        loanApplyUserDO.setCardType("00");
        loanApplyUserDO.setLoanType("0");
        loanApplyUserService.doAdd(loanApplyUserDO,1);
        return ResultDTO.success();
    }
}
