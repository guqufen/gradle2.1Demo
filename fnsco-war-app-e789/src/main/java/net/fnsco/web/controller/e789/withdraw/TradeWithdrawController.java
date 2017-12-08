package net.fnsco.web.controller.e789.withdraw;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.web.controller.e789.jo.WithdrawCashJO;
import net.fnsco.web.controller.e789.vo.CommonVO;

/**
 * @desc 提现和充值业务控制器
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月4日 下午5:19:55
 */
@RestController
@RequestMapping(value = "/app2c/withdraw", method = RequestMethod.POST)
@Api(value = "/app2c/withdraw", tags = { "我的-提现相关功能接口" })
public class TradeWithdrawController extends BaseController {

    /**
     * withdrawals:(提现接口)
     *
     * @param  @param withdrawCashJO
     * @param  @return    设定文件
     * @return ResultDTO<CommonVO>    DOM对象
     * @author tangliang
     * @date   2017年12月4日 下午5:31:24
     */
    @RequestMapping(value = "/withdrawals")
    @ApiOperation(value = "我的-钱包-提现-提现接口")
    public ResultDTO<CommonVO> withdrawals(@RequestBody WithdrawCashJO withdrawCashJO) {

        return success(null);
    }

}
