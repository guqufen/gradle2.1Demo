package net.fnsco.web.controller.e789.third.phoneBill;

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
import net.fnsco.trading.service.merchant.AppUserMerchantService;
import net.fnsco.trading.service.third.phoneBill.PrepaidRefillService;
import net.fnsco.trading.service.third.phoneBill.dto.PhoneChargeDTO;
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

	@RequestMapping("/prepaidRefillCheck")
	@ApiOperation(value = "手机号码充值套餐资费查询url")
	public ResultDTO<List<PhoneChargeDTO>> prepaidRefillCheck(@RequestBody FlowPackageCheckJO flowPackageCheckJO) {
		return prepaidRefillService.prepaidRefillCheck(flowPackageCheckJO.getPhone());
	}

	@RequestMapping("/prepaidRefill")
	@ApiOperation(value = "手机号码充值url")
	public ResultDTO<PrepaidRefillVO> prepaidRefill(@RequestBody FlowChargeJO fl) {
		// 根据userId获取内部商户号
		String innerCode = this.appUserMerchantService.getInnerCodeByUserId(fl.getUserId());
		if (Strings.isNullOrEmpty(innerCode)) {
			return ResultDTO.fail("该用户没有绑定内部商户号，请核查后重新交易");
		}
		return prepaidRefillService.prepaidRefillCharge(fl.getPhone(), fl.getPid(), innerCode);
	}

	@RequestMapping("/flowPackageCheck")
	@ApiOperation(value = "检测手机号支持的流量资费套餐url")
	public ResultDTO<List<PhoneChargeDTO>> flowPackageCheck(@RequestBody FlowPackageCheckJO flowPackageCheckJO) {
		return prepaidRefillService.flowPackageCheck(flowPackageCheckJO.getPhone());
	}

	@RequestMapping("/flowCharge")
	@ApiOperation(value = "手机流量充值url")
	public ResultDTO<PrepaidRefillVO> flowCharge(@RequestBody FlowChargeJO fl) {
		// 根据userId获取内部商户号
		String innerCode = this.appUserMerchantService.getInnerCodeByUserId(fl.getUserId());
		if (Strings.isNullOrEmpty(innerCode)) {
			return ResultDTO.fail("该用户没有绑定内部商户号，请核查后重新交易");
		}
		return prepaidRefillService.flowCharge(fl.getPhone(), fl.getPid(), innerCode);
	}
}
