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
import net.fnsco.web.controller.e789.jo.FlowChargeJO;
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

		if (flowPackageCheckJO.getPhone().trim().length() > 11) {

			logger.error("手机充值-手机号码非法，请输入11位手机号，不能带空格！！");
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
	public ResultDTO<ChargeResultDTO> prepaidCharge(@RequestBody FlowChargeJO fl) {

		ChargeResultDTO ph = new ChargeResultDTO();

		// 非帐户余额支付，暂不支持
		if (!"0".equals(fl.getPayType())) {

			logger.error("手机充值-支付方式非法，暂时只支持帐户余额充值方式，请重新选择！！");
			return ResultDTO.fail("暂时只支持帐户余额充值方式，请重新选择！！");
		}

		logger.info("手机充值-输入的支付密码加密前的passwd=" + fl.getPayPassword());
		String password = Md5Util.getInstance().md5(fl.getPayPassword());
		// 根据id查询用户是否存在获取原密码
		AppUser mAppUser = appUserService.selectAppUserById(fl.getUserId());
		if (null == mAppUser) {

			logger.error("手机充值-用户Id未找到相关信息，appUserId=" + fl.getUserId());
			return ResultDTO.fail(ApiConstant.E_NOREGISTER_LOGIN);
		}

		// 查到的密码和原密码做比较
		if (!password.equals(mAppUser.getPayPassword())) {

			logger.error("手机充值-支付密码错误，请核对后重新输入！！db_passwd=" + mAppUser.getPayPassword() + ",password=" + password);
			return ResultDTO.fail(E789ApiConstant.E_APP_PAY_PASSWORD_ERROR);
		}

		// 根据userId和待扣金额查询账户是否有足够的钱进行充值交易，并更新
		Boolean isEnough = appAccountBalanceService.doFrozenBalance(fl.getUserId(),
				new BigDecimal(fl.getInprice()).multiply(new BigDecimal(100)));
		if (!isEnough) {

			logger.error("手机充值-帐户余额不足，请充值！！appUserId=" + fl.getUserId());
			return ResultDTO.fail(ApiConstant.E_ACCOUNT_BALANCE_NULL);
		}

		if (fl.getPhone().length() > 11) {

			logger.error("手机充值-手机号码非法，请输入11位手机号，不能带空格！！");
			return ResultDTO.fail(ApiConstant.E_APP_PHONE_ERROR);
		}

		ChargeDTO chargeDTO = new ChargeDTO();
		chargeDTO.setType(fl.getType());
		chargeDTO.setInprice(fl.getInprice());
		chargeDTO.setPhone(fl.getPhone());
		chargeDTO.setUserId(fl.getUserId());
		chargeDTO.setName(fl.getName());
		chargeDTO.setPid(fl.getPid());

		// 手机充值
		if (0 == fl.getType()) {

			ph = prepaidRefillService.prepaidRefillCharge(chargeDTO);

			// 话费充值
		} else if (1 == fl.getType()) {

			ph = prepaidRefillService.flowCharge(chargeDTO);// fl.getPhone(),
															// fl.getPid(),
															// innerCode);

		} else {

			return ResultDTO.fail("手机充值-交易类型不匹配");
		}

		if ("1001".equals(ph.getRespCode())) {

			// 成功，则更新原账户
			Boolean b = appAccountBalanceService.doUpdateFrozenAmount(fl.getUserId(),
					new BigDecimal(fl.getInprice()).multiply(new BigDecimal(100)));
			if (!b) {
				logger.error("手机充值-充值成功之后，账户扣款更新失败，请联系相关技术人员查看,orderNo=" + ph.getOrderNo());
			}

			return ResultDTO.success(ph);

			// 系统异常，进行中。先扣掉账户金额，待后续定时查询结果用
		} else if ("1000".equals(ph.getRespCode())) {
			Boolean b = appAccountBalanceService.doUpdateFrozenAmount(fl.getUserId(),
					new BigDecimal(fl.getInprice()).multiply(new BigDecimal(100)));
			if (!b) {
				logger.error("手机充值-充值交易正在进行中，账户扣款更新失败，请联系相关技术人员查看,orderNo=" + ph.getOrderNo());
			}

			return ResultDTO.success(ph);

			// 失败，更新原账户
		} else {
			isEnough = appAccountBalanceService.doFrozenBalance(fl.getUserId(),
					new BigDecimal(0).subtract(new BigDecimal(fl.getInprice()).setScale(2, BigDecimal.ROUND_HALF_UP)
							.multiply(new BigDecimal(100))));
			if (!isEnough) {
				logger.error("手机充值-充值失败之后，账户更新失败，请联系相关技术人员查看,orderNo=" + ph.getOrderNo());
			}
			return ResultDTO.fail(ph.getRespMsg());
		}
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
