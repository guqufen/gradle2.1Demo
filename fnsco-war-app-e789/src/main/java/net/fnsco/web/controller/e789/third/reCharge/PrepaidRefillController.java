package net.fnsco.web.controller.e789.third.reCharge;

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
import net.fnsco.freamwork.comm.Md5Util;
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.service.domain.AppUser;
import net.fnsco.trading.constant.E789ApiConstant;
import net.fnsco.trading.service.account.AppAccountBalanceService;
import net.fnsco.trading.service.third.reCharge.PrepaidRefillService;
import net.fnsco.trading.service.third.reCharge.RechargeOrderService;
import net.fnsco.trading.service.third.reCharge.dto.ChargeDTO;
import net.fnsco.trading.service.third.reCharge.dto.ChargeResultDTO;
import net.fnsco.trading.service.third.reCharge.dto.CheckChargePackageDTO;
import net.fnsco.trading.service.third.reCharge.entity.RechargeOrderDO;
import net.fnsco.web.controller.e789.jo.FlowPackageCheckJO;
import net.fnsco.web.controller.e789.third.ticket.jo.ThrRechargeJO;

/**
 * 功能：账户页-手机充值的话费充值和流量充值控制器url
 * 
 * @author yx，
 *
 */
@RestController
@RequestMapping(value = "/app2c/phoneCharge", method = RequestMethod.POST)
@Api(value = "/app2c/phoneCharge", tags = { "账户页-手机充值" })
public class PrepaidRefillController extends BaseController {

	@Autowired
	private PrepaidRefillService prepaidRefillService;
	@Autowired
	private AppAccountBalanceService appAccountBalanceService;
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private RechargeOrderService rechargeOrderService;

	@RequestMapping("/prepaidCheck")
	@ApiOperation(value = "话费/流量套餐资费查询url")
	public ResultDTO<CheckChargePackageDTO> prepaidCheck(@RequestBody FlowPackageCheckJO flowPackageCheckJO) {

		logger.info("手机充值-套餐查询请求参数:" + flowPackageCheckJO.toString());

		if (flowPackageCheckJO.getPhone().trim().length() > 11) {

			logger.error("手机充值资费查询-手机号码非法，请输入11位手机号，不能带空格！！");
			return ResultDTO.fail(ApiConstant.E_APP_PHONE_ERROR);
		}

		// 话费资费查询
		if (0 == flowPackageCheckJO.getType()) {
			return prepaidRefillService.prepaidRefillCheck(flowPackageCheckJO.getPhone().trim());

			// 流量资费查询
		} else if (1 == flowPackageCheckJO.getType()) {

			return prepaidRefillService.flowPackageCheck(flowPackageCheckJO.getPhone().trim());
		} else {

			return ResultDTO.fail("手机充值资费查询-交易类型不匹配");
		}
	}

	@RequestMapping("/prepaidCharge")
	@ApiOperation(value = "话费/流量充值url")
	public ResultDTO<ChargeResultDTO> prepaidCharge(@RequestBody ChargeDTO chargeDTO) {

		// 判断APP用户ID是否合法
		String userId = this.request.getHeader("userId");// 从头部获取userId
		if (null == userId || Integer.parseInt(userId) != chargeDTO.getUserId()) {
			logger.error("用户登录状态非法。");
			return ResultDTO.fail("用户登录状态非法。head->userId=" + userId + ",param->userId=" + chargeDTO.getUserId());
		}

		// 判断手机号长度是否合法
		if (chargeDTO.getPhone().length() > 11) {

			logger.error("手机充值-手机号码非法，请输入11位手机号，不能带空格！！");
			return ResultDTO.fail(ApiConstant.E_APP_PHONE_ERROR);
		}

		// 帐户余额支付
		if ("0".equals(chargeDTO.getPayType())) {

			// 余额支付，需要校验用户支付密码是否正确
			AppUser mAppUser = appUserService.selectAppUserById(chargeDTO.getUserId());// 根据id查询用户是否存在获取原密码
			if (null == mAppUser) {

				logger.error("手机充值-用户Id未找到相关信息，appUserId=" + chargeDTO.getUserId());
				return ResultDTO.fail(ApiConstant.E_NOREGISTER_LOGIN);
			}
			logger.info("手机充值-输入的支付密码加密前的passwd=" + chargeDTO.getPayPassword());
			String password = Md5Util.getInstance().md5(chargeDTO.getPayPassword());
			if (!password.equals(mAppUser.getPayPassword())) {// 查到的密码和原密码做比较

				logger.error("手机充值-支付密码错误，请核对后重新输入！！db_passwd=" + mAppUser.getPayPassword() + ",password=" + password);
				return ResultDTO.fail(E789ApiConstant.E_APP_PAY_PASSWORD_ERROR);
			}

			// 根据userId和待扣金额查询账户是否有足够的钱进行充值交易
			Boolean isEnough = appAccountBalanceService.doJudgeBalance(chargeDTO.getUserId(),
					new BigDecimal(chargeDTO.getInprice()).multiply(new BigDecimal(100)));
			if (!isEnough) {

				logger.error("手机充值-帐户余额不足，请充值！！appUserId=" + chargeDTO.getUserId());

				return ResultDTO.fail(ApiConstant.E_ACCOUNT_BALANCE_NULL);
			}

			// 手机充值
			chargeDTO.setPayPassword(null);
			return prepaidRefillService.acctRecharge(chargeDTO);
			// 支付宝充值
		} else if ("1".equals(chargeDTO.getPayType())) {
			return prepaidRefillService.aliPayRecharge(chargeDTO);
		}

		// logger.error("手机充值-支付方式非法，暂时只支持帐户余额充值方式，请重新选择！！");
		return ResultDTO.fail();
	}

	@RequestMapping("/queryResult")
	@ApiOperation(value = "话费/流量充值结果查询url")
	public ResultDTO<ChargeResultDTO> queryFlowResult(@RequestBody ThrRechargeJO thrRechargeJO) {

		String orderNo = thrRechargeJO.getOrderNo();

		RechargeOrderDO rechargeOrder = rechargeOrderService.getByOrderNo(orderNo);
		if (null == rechargeOrder) {
			logger.error("此订单号找不到原交易，请核查后重新请求：orderNo=" + orderNo);
			return ResultDTO.fail("此订单号找不到原交易，请核查后重新请求：orderNo=" + orderNo);
		}

		// 如果该订单已经有确定状态，则直接返回结果
		if ("1".equals(rechargeOrder.getStatus())) {
			return ResultDTO.success(rechargeOrder.getRespMsg());
		} else if ("2".equals(rechargeOrder.getStatus())) {
			return ResultDTO.success(rechargeOrder.getRespMsg());
		}

		// 根据充值类型，确定执行话费充值结果查询/流量充值查询
		if ("1".equals(rechargeOrder.getType())) {
			return prepaidRefillService.queryFlowResult(rechargeOrder);
		} else if ("0".equals(rechargeOrder.getType())) {
			return prepaidRefillService.orderSta(rechargeOrder);
		}
		return ResultDTO.fail();
	}
}
