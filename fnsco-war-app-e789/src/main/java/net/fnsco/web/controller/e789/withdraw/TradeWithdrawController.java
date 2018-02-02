package net.fnsco.web.controller.e789.withdraw;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.freamwork.comm.Md5Util;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.service.dao.master.AppUserDao;
import net.fnsco.order.service.domain.AppUser;
import net.fnsco.trading.service.account.AppAccountBalanceService;
import net.fnsco.trading.service.account.entity.AppAccountBalanceDO;
import net.fnsco.trading.service.bank.AppUserBankService;
import net.fnsco.trading.service.bank.entity.AppUserBankDO;
import net.fnsco.trading.service.withdraw.TradeWithdrawService;
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
	
	@Autowired
	private AppAccountBalanceService appAccountBalanceService;
	@Autowired
	private AppUserDao appUserDao;
	@Autowired
	private AppUserBankService appUserBankService;
	@Autowired
	private TradeWithdrawService tradeWithdrawService;
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
    	
    	if(null == withdrawCashJO.getUserId()) {
 			return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
 		}
    	if(Strings.isNullOrEmpty(withdrawCashJO.getCashAccount())) {
    		return ResultDTO.fail(ApiConstant.E_CASH_ACCOUNT_NULL);
    	}
    	if(null == withdrawCashJO.getBankCardId()) {
    		return ResultDTO.fail(ApiConstant.E_BANK_CARDID_NULL);
    	}
    	if(Strings.isNullOrEmpty(withdrawCashJO.getPayPassword())) {
    		return ResultDTO.fail(ApiConstant.E_PAY_PASSWORD_NULL);
    	}
    	
    	/**
    	 * 将传入的提现金额转换为单位分,支付密码加密
    	 */
    	BigDecimal cashAccount = new BigDecimal(withdrawCashJO.getCashAccount()).multiply(new BigDecimal(100));
    	withdrawCashJO.setCashAccount(cashAccount.toString());
    	withdrawCashJO.setPayPassword(Md5Util.getInstance().md5(withdrawCashJO.getPayPassword()));
    	
    	/**
    	 * 判断支付密码
    	 */
    	AppUser appUser = appUserDao.selectAppUserById(withdrawCashJO.getUserId());
    	if(null == appUser || Strings.isNullOrEmpty(appUser.getPayPassword()) || !withdrawCashJO.getPayPassword().equals(appUser.getPayPassword())) {
    		return ResultDTO.fail(ApiConstant.E_PAY_PASSWORD_NULL);
    	}
    	
    	/**
    	 * 判断余额
    	 */
    	AppAccountBalanceDO appAccountBalance = appAccountBalanceService.doQueryByAppUserId(withdrawCashJO.getUserId());
    	if(null == appAccountBalance.getFund() || appAccountBalance.getFund().compareTo(new BigDecimal(0)) <=0 || appAccountBalance.getFund().compareTo(new BigDecimal(withdrawCashJO.getCashAccount())) <0 ) {
    		return ResultDTO.fail(ApiConstant.E_ACCOUNT_BALANCE_NULL);
    	}
    	
    	
    	/**
    	 * 校验银行卡信息
    	 */
    	AppUserBankDO appUserBank = appUserBankService.doQueryById(withdrawCashJO.getBankCardId());
    	if(null == appUserBank) {
    		return ResultDTO.fail(ApiConstant.E_BANK_CARDID_NULL);
    	}
    	
    	/**
    	 * 提现操作
    	 */
    	boolean withdrawResult = tradeWithdrawService.doAccountBalanceWithdrawals(withdrawCashJO.getUserId(), withdrawCashJO.getCashAccount(), appUserBank);
    	
    	if(!withdrawResult) {
    		return ResultDTO.fail(ApiConstant.E_ACCOUNT_BALANCE_NULL);
    	}
        return success(null);
    }
}
