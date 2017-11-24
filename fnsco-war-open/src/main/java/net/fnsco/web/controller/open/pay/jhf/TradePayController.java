package net.fnsco.web.controller.open.pay.jhf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.bigdata.api.merchant.MerchantService;
import net.fnsco.core.base.BaseController;
import net.fnsco.trading.service.order.TradeOrderService;
import net.fnsco.trading.service.order.entity.TradeOrderDO;

/**
 * 
 * @desc 聚惠分分期付第三方调用接口相关功能
 * @author   sxf
 * @version  
 * @since    Ver 1.1
 * @Date     2017年10月27日 上午11:53:16
 *
 */
@Controller
@RequestMapping(value = "/trade/pay", method = RequestMethod.POST)
@Api(value = "/trade/pay", tags = { "聚惠分相关功能接口" })
public class TradePayController extends BaseController {
    @Autowired
    private MerchantService     merchantService;
    @Autowired
    private TradeOrderService   tradeOrderService;
    @Autowired
    private MerchantCoreService merchantCoreService;
    @Autowired
    private Environment         env;

    /**
     * 二维码扫码后跳转到聚惠分平台
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/dealPayOrder", method = RequestMethod.GET)
    @ApiOperation(value = "获取聚惠分二维码url")
    public String dealPayOrder(String orderNo, String commID) {
        TradeOrderDO tradeOrderDO = tradeOrderService.queryOneByOrderId(orderNo);
        String url = env.getProperty("open.base.url") + "/pay/dealPayFail.html";
        if (null != tradeOrderDO) {
            Integer handleNum = tradeOrderDO.getHandleNum();
            if (null == handleNum || handleNum == 0) {
                url = env.getProperty("jhf.open.api.url") + "/api/thirdPay/dealPayOrder";
                url += "?commID=" + commID + "&reqData=" + tradeOrderService.getReqData(tradeOrderDO);
                TradeOrderDO tradeOrderTemp = new TradeOrderDO();
                tradeOrderTemp.setId(tradeOrderDO.getId());
                tradeOrderTemp.setHandleNum(1);
                tradeOrderService.doUpdate(tradeOrderTemp);
            }
        }
        logger.error("分闪付跳转到聚惠分平台前的url"+url);
        return "redirect:" + url;
    }

}
