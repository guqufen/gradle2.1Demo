package net.fnsco.web.controller.e789.third.phoneBill;

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
import net.fnsco.trading.service.account.AppAccountBalanceService;
import net.fnsco.trading.service.merchant.AppUserMerchantService;
import net.fnsco.trading.service.third.phoneBill.PrepaidRefillService;
import net.fnsco.trading.service.third.phoneBill.dto.CheckChargePackageDTO;
import net.fnsco.trading.service.third.phoneBill.dto.ChargeDTO;
import net.fnsco.trading.service.third.phoneBill.dto.ChargeResultDTO;
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
	private AppUserMerchantService appUserMerchantService;
	@Autowired
	private AppAccountBalanceService appAccountBalanceService;

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

		// 根据userId和待扣金额查询账户是否有足够的钱进行充值交易，并更新
		Boolean isEnough = appAccountBalanceService.doFrozenBalance(fl.getUserId(), new BigDecimal(fl.getInprice()));
		if (!isEnough) {
			return ResultDTO.fail("账户余额不足");
		}

		if (fl.getPhone().length() > 11) {
			return ResultDTO.fail("手机号长度超过11位(不能带空格)，请核查后重新充值");
		}

		ChargeDTO chargeDTO = new ChargeDTO();
		chargeDTO.setPid(fl.getPid());
		chargeDTO.setInprice(fl.getInprice());
		chargeDTO.setPhone(fl.getPhone());
		chargeDTO.setUserId(fl.getUserId());
		
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
			Boolean b = appAccountBalanceService.doUpdateFrozenAmount(fl.getUserId(), new BigDecimal(fl.getInprice()));
			if (!b) {
				logger.error("充值成功之后，账户扣款更新失败，请联系相关技术人员查看");
			}

			return ResultDTO.success(ph);

			// 失败，更新原账户
		} else {
			isEnough = appAccountBalanceService.doFrozenBalance(fl.getUserId(),
					new BigDecimal(0).subtract(new BigDecimal(fl.getInprice())));
			if (!isEnough) {
				logger.error("充值失败之后，账户更新失败，请联系相关技术人员查看");
			}
			return ResultDTO.fail(ph.getRespMsg());
		}
	}

}
