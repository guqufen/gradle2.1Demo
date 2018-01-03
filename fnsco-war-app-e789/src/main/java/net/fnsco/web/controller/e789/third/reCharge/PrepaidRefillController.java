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
import net.fnsco.trading.service.third.reCharge.dto.ChargeDTO;
import net.fnsco.trading.service.third.reCharge.dto.ChargeResultDTO;
import net.fnsco.trading.service.third.reCharge.dto.CheckChargePackageDTO;
import net.fnsco.web.controller.e789.jo.FlowChargeJO;
import net.fnsco.web.controller.e789.jo.FlowPackageCheckJO;

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

	@RequestMapping("/prepaidCheck")
	@ApiOperation(value = "话费/流量套餐资费查询url")
	public ResultDTO<CheckChargePackageDTO> prepaidCheck(@RequestBody FlowPackageCheckJO flowPackageCheckJO) {

		

		// 话费资费查询
		if (0 == flowPackageCheckJO.getType()) {
			return prepaidRefillService.prepaidRefillCheck(flowPackageCheckJO.getPhone());

			// 流量资费查询
		} else if (1 == flowPackageCheckJO.getType()) {
			return prepaidRefillService.flowPackageCheck(flowPackageCheckJO.getPhone());
		} else {
			return ResultDTO.fail("交易类型不匹配");
		}
	}

	@RequestMapping("/prepaidCharge")
	@ApiOperation(value = "话费/流量充值url")
	public ResultDTO<ChargeResultDTO> prepaidCharge(@RequestBody FlowChargeJO fl) {

		ChargeResultDTO ph = null;

		String password = Md5Util.getInstance().md5(fl.getPayPassword());
		// 根据id查询用户是否存在获取原密码
		AppUser mAppUser = appUserService.selectAppUserById(Integer.valueOf(fl.getUserId()));
		if (null == mAppUser) {
			return ResultDTO.fail(ApiConstant.E_NOREGISTER_LOGIN);
		}
		// 查到的密码和原密码做比较
		if (!password.equals(mAppUser.getPassword())) {
			return ResultDTO.fail(E789ApiConstant.E_APP_PAY_PASSWORD_ERROR);
		}

		// 根据userId和待扣金额查询账户是否有足够的钱进行充值交易，并更新
		Boolean isEnough = appAccountBalanceService.doFrozenBalance(fl.getUserId(), new BigDecimal(fl.getInprice()).multiply(new BigDecimal(100)));
		if (!isEnough) {
			return ResultDTO.fail("账户余额不足");
		}

		if (fl.getPhone().length() > 11) {
			return ResultDTO.fail("手机号长度超过11位(不能带空格)，请核查后重新充值");
		}

		ChargeDTO chargeDTO = new ChargeDTO();
		chargeDTO.setType(fl.getType());
		chargeDTO.setInprice(fl.getInprice());
		chargeDTO.setPhone(fl.getPhone());
		chargeDTO.setUserId(fl.getUserId());
		chargeDTO.setName(fl.getName());
		
		//手机充值
		if (0 == fl.getType()) {

			ph = prepaidRefillService.prepaidRefillCharge(chargeDTO);

			//话费充值
		} else if (1 == fl.getType()) {

			ph = prepaidRefillService.flowCharge(chargeDTO);//fl.getPhone(), fl.getPid(), innerCode);

		} else {

			return ResultDTO.fail("交易类型不匹配");
		}

		if ("1001".equals(ph.getRespCode())) {

			// 成功，则更新原账户
			Boolean b = appAccountBalanceService.doUpdateFrozenAmount(fl.getUserId(), new BigDecimal(fl.getInprice()).multiply(new BigDecimal(100)) );
			if (!b) {
				logger.error("充值成功之后，账户扣款更新失败，请联系相关技术人员查看,orderNo="+ph.getOrderNo());
			}

			return ResultDTO.success(ph);

			//系统异常，进行中。先扣掉账户金额，待后续定时查询结果用
		} else if("1000".equals(ph.getRespCode())){
			Boolean b = appAccountBalanceService.doUpdateFrozenAmount(fl.getUserId(), new BigDecimal(fl.getInprice()).multiply(new BigDecimal(100)) );
			if (!b) {
				logger.error("充值交易正在进行中，账户扣款更新失败，请联系相关技术人员查看,orderNo="+ph.getOrderNo());
			}

			return ResultDTO.fail(ph.getRespMsg());
			
			// 失败，更新原账户
		}else{
			isEnough = appAccountBalanceService.doFrozenBalance(fl.getUserId(),
					new BigDecimal(0).subtract( new BigDecimal(fl.getInprice()).multiply(new BigDecimal(100)) ));
			if (!isEnough) {
				logger.error("充值失败之后，账户更新失败，请联系相关技术人员查看,orderNo="+ph.getOrderNo());
			}
			return ResultDTO.fail(ph.getRespMsg());
		}
	}

//	@RequestMapping("/queryFlowResult")
//	@ApiOperation(value = "话费/流量充值结果查询url")
//	public void queryFlowResult(@RequestBody String orderNo){
//		RechargeOrderDO rechargeOrder = rechargeOrderService.getByOrderNo(orderNo);
//		if("1".equals(rechargeOrder.getType())){
//			prepaidRefillService.queryFlowResult(rechargeOrder);
//		}else if("0".equals(rechargeOrder.getType())){
//			prepaidRefillService.orderSta(rechargeOrder);
//		}
//	}
}
