package net.fnsco.web.controller.e789.third.phoneBill;

import java.math.BigDecimal;
import java.util.List;

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
import net.fnsco.trading.service.third.phoneBill.dto.PhoneChargePackageDTO;
import net.fnsco.web.controller.e789.jo.FlowChargeJO;
import net.fnsco.web.controller.e789.jo.FlowPackageCheckJO;
import net.fnsco.web.controller.e789.third.ticket.vo.PrepaidRefillVO;

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
	public ResultDTO<PhoneChargePackageDTO> prepaidCheck(@RequestBody FlowPackageCheckJO flowPackageCheckJO) {

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
	public ResultDTO<PrepaidRefillVO> prepaidCharge(@RequestBody FlowChargeJO fl) {

		// 根据userId和待扣金额查询账户是否有足够的钱进行充值交易，并更新
		Boolean isEnough = appAccountBalanceService.doFrozenBalance(fl.getUserId(), new BigDecimal(fl.getInprice()));
		if (!isEnough) {
			return ResultDTO.fail("账户余额不足");
		}

		// 根据userId获取内部商户号
		String innerCode = this.appUserMerchantService.getInnerCodeByUserId(fl.getUserId());
		if (Strings.isNullOrEmpty(innerCode)) {
			return ResultDTO.fail("该用户没有绑定内部商户号，请核查后重新交易");
		}

		if (0 == fl.getType()) {
			ResultDTO<PrepaidRefillVO> list = prepaidRefillService.prepaidRefillCharge(fl.getPhone(), fl.getPid(), innerCode);
			return prepaidRefillService.prepaidRefillCharge(fl.getPhone(), fl.getPid(), innerCode);
		} else if (1 == fl.getType()) {
			return prepaidRefillService.flowCharge(fl.getPhone(), fl.getPid(), innerCode);
		} else {
			return ResultDTO.fail("交易类型不匹配");
		}

	}

}
