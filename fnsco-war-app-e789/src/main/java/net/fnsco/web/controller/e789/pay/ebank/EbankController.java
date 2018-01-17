package net.fnsco.web.controller.e789.pay.ebank;

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
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.service.domain.AppUser;
import net.fnsco.trading.constant.E789ApiConstant;
import net.fnsco.trading.service.pay.channel.ebank.EbankService;
import net.fnsco.trading.service.pay.channel.ebank.dto.E4033ResultDTO;
import net.fnsco.trading.service.pay.channel.ebank.entity.E4029Entity;
import net.fnsco.trading.service.pay.channel.ebank.entity.EMaintainResultEntity;
import net.fnsco.web.controller.e789.pay.ebank.jo.E4028JO;
import net.fnsco.web.controller.e789.pay.ebank.jo.E4031JO;
import net.fnsco.web.controller.e789.pay.ebank.jo.E4032JO;
import net.fnsco.web.controller.e789.pay.ebank.jo.E4033JO;

@RestController
@RequestMapping(value = "/app2c/trade/ebank", method = RequestMethod.POST)
@Api(value = "/app2c/trade/ebank", tags = { "平安银行-橙e收款" })
public class EbankController extends BaseController {

	@Autowired
	private EbankService ebankService;
	@Autowired
	private AppUserService appUserService;

	/**
	 * 付款人账户协议查询
	 * 
	 * @param e4028jo
	 * @return
	 */
	@RequestMapping("/acctQuery")
	@ApiOperation(value = "付款人账户协议查询")
	public ResultDTO E4028Trade(@RequestBody E4028JO e4028jo) {

		return ebankService.E4028Trade(e4028jo.getOppAccNo());
	}

	/**
	 * 付款人账户协议维护：五要素需齐全
	 * 
	 * @param e4029Entity
	 * @return
	 */
	@RequestMapping("/acctMaintain")
	@ApiOperation(value = "付款人账户协议维护")
	public ResultDTO E4029Trade(@RequestBody E4029Entity e4029Entity) {

		return ebankService.E4029Trade(e4029Entity);
	}

	/**
	 * 付款人账户验证
	 * 
	 * @param e4031jo
	 * @return
	 */
	@RequestMapping("/acctCheck")
	public ResultDTO<EMaintainResultEntity> E4031Trade(@RequestBody E4031JO e4031jo) {

		return ebankService.E4031Trade(e4031jo.getOppAccNo(), e4031jo.getMobile());
	}

	/**
	 * 付款
	 * 
	 * @param e4032jo
	 * @return
	 */
	@RequestMapping("/ePay")
	public ResultDTO E4032Trade(@RequestBody E4032JO e4032jo) {

		if (Strings.isNullOrEmpty(e4032jo.getPayPassword())) {

			logger.error("手机充值-请输入支付密码，appUserId=" + e4032jo.getUserId());
			return ResultDTO.fail("支付密码不能为空");
		}

		String password = Md5Util.getInstance().md5(e4032jo.getPayPassword());
		// 根据id查询用户是否存在获取原密码
		AppUser mAppUser = appUserService.selectAppUserById(e4032jo.getUserId());
		if (null == mAppUser) {

			logger.error("手机充值-用户Id未找到相关信息，appUserId=" + e4032jo.getUserId());
			return ResultDTO.fail(ApiConstant.E_NOREGISTER_LOGIN);
		}

		// 查到的密码和原密码做比较
		if (!password.equals(mAppUser.getPayPassword())) {

			logger.error("手机充值-支付密码错误，请核对后重新输入！！db_passwd=" + mAppUser.getPayPassword() + ",password=" + password);
			return ResultDTO.fail(E789ApiConstant.E_APP_PAY_PASSWORD_ERROR);
		}

		return ebankService.E4032Trade(e4032jo.getOppAccNo(), e4032jo.getAmount(), e4032jo.getUserId());
	}

	/**
	 * 付款交易结果查询
	 * 
	 * @param orderNo:订单号
	 * @return
	 */
	@RequestMapping("/ePayResult")
	public ResultDTO<E4033ResultDTO> E4033Trade(@RequestBody E4033JO e4033jo) {

		return ebankService.E4033Trade(e4033jo.getOrderNo());
	}
}
