package net.fnsco.web.controller.e789.pay.ebank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.trading.service.pay.channel.ebank.EbankService;
import net.fnsco.trading.service.pay.channel.ebank.entity.E4029Entity;
import net.fnsco.web.controller.e789.pay.ebank.jo.E4028JO;
import net.fnsco.web.controller.e789.pay.ebank.jo.E4031JO;
import net.fnsco.web.controller.e789.pay.ebank.jo.E4032JO;

@RestController
@RequestMapping(value = "/app2c/trade/ebank", method = RequestMethod.POST)
@Api(value = "/app2c/trade/ebank", tags = { "平安银行-橙e收款" })
public class EbankController extends BaseController {

	@Autowired
	private EbankService ebankService;

	/**
	 * 付款人账户协议查询
	 * 
	 * @param e4028jo
	 * @return
	 */
	@RequestMapping("/acctQuery")
	@ApiOperation(value = "付款人账户协议查询")
	public ResultDTO E4028Trade(@RequestBody E4028JO e4028jo) {
		return ebankService.E4028Trade(e4028jo.getAcctNo());
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
	public ResultDTO E4031Trade(@RequestBody E4031JO e4031jo) {
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
		return ebankService.E4032Trade(e4032jo.getAcctNo(), e4032jo.getAmount());
	}
}
