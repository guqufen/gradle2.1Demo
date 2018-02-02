package net.fnsco.web.controller.e789.account;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.trading.service.account.AppAccountBalanceService;
import net.fnsco.trading.service.account.entity.AppAccountBalanceDO;
import net.fnsco.web.controller.e789.jo.AccountBalanceJO;
import net.fnsco.web.controller.e789.vo.AccountBalanceVO;

/**
 * @desc 帐号信息控制器
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月4日 下午5:01:12
 */
@RestController
@RequestMapping(value = "/app2c/account", method = RequestMethod.POST)
@Api(value = "/app2c/account", tags = { "我的-帐号信息相关功能接口" })
public class AccountBalanceController extends BaseController {
	
	@Autowired
	private AppAccountBalanceService appAccountBalanceService;
	
	/**
	 * queryBalance:(获取个人帐号余额信息)
	 *
	 * @param  @param accountBalanceJO
	 * @param  @return    设定文件
	 * @return ResultDTO<GetQRUrlResultVO>    DOM对象
	 * @author tangliang
	 * @date   2017年12月4日 下午5:07:55
	 */
 	@RequestMapping(value = "/queryBalance")
    @ApiOperation(value = "我的-钱包-获取帐号余额接口")
    public ResultDTO<AccountBalanceVO> queryBalance(@RequestBody AccountBalanceJO accountBalanceJO) {
 		if(null == accountBalanceJO.getUserId()) {
 			return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
 		}
 		AppAccountBalanceDO appAccountBalanceDO = appAccountBalanceService.doQueryByAppUserId(accountBalanceJO.getUserId());
 		AccountBalanceVO resultVO = new AccountBalanceVO();
 		if(null == appAccountBalanceDO) {
 			 resultVO.setAccountBalance("0.00");
 			 appAccountBalanceService.initialiseAccountBalance(accountBalanceJO.getUserId());//如果该用户不存在余额数据，则初始化一条
 			 return success(resultVO);
 		}
 		
 		
 		if(null == appAccountBalanceDO.getFund()) {
 			resultVO.setAccountBalance("0.00");
 		}else {
 			resultVO.setAccountBalance(String.format("%.2f", appAccountBalanceDO.getFund().divide(new BigDecimal(100)).doubleValue()));
 		}
 		
        return success(resultVO);
    }
 	
}
