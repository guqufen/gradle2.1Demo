package net.fnsco.controller.app.trade;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fnsco.core.base.BaseController;

/**
 * @desc 交易统计APP接口类
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月27日 上午10:36:32
 */
@RestController
@RequestMapping(value = "/app/tradeReport", method = RequestMethod.POST)
public class TradeReportController extends BaseController{

}
