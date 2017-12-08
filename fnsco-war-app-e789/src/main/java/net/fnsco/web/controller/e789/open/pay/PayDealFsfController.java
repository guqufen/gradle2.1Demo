package net.fnsco.web.controller.e789.open.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.bigdata.api.merchant.MerchantService;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.utils.dby.AESUtil;
import net.fnsco.trading.comm.TradeConstants;
import net.fnsco.trading.service.order.TradeOrderService;
import net.fnsco.trading.service.order.entity.TradeOrderDO;
import net.fnsco.web.controller.e789.dto.TradeDTO;

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
@RequestMapping(value = "/trade/fsf", method = RequestMethod.POST)
@Api(value = "/trade/fsf", tags = { "分闪付支付处理相关接口" })
public class PayDealFsfController extends BaseController {
    @Autowired
    private MerchantService   merchantService;
    @Autowired
    private TradeOrderService tradeOrderService;
    @Autowired
    private Environment       env;

    /**
     * App支付,用户二维码扫描转到聚惠分平台进行支付
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/pay/dealPayOrder", method = RequestMethod.GET)
    @ApiOperation(value = "二维码扫描转到聚惠分平台进行支付")
    public String dealPayOrder(@ApiParam(value = "请求数据") String reqData, @ApiParam(value = "内部商务号") String commID) {
        TradeDTO trade = getReqData(reqData);
        String orderNo = trade.getOrderNo();
        TradeOrderDO tradeOrderDO = tradeOrderService.queryOneByOrderId(orderNo);
        String url = env.getProperty("open.base.url") + "/pay/dealPayFail.html";
        if (null != tradeOrderDO) {
            Integer handleNum = tradeOrderDO.getHandleNum();
            if (null == handleNum || handleNum == 0) {
                url = env.getProperty("jhf.open.api.url") + "/api/thirdPay/dealPayOrder";
                String payNotifyUrl = env.getProperty("open.base.url") + "/trade/jhf/payCompleteNotice";
                String payCallBackUrl = env.getProperty("open.base.url") + "/trade/jhf/payCompleteCallback?orderNo=" + orderNo;
                url += "?commID=" + tradeOrderDO.getChannelMerId() + "&reqData=" + tradeOrderService.getReqData(tradeOrderDO, payNotifyUrl, payCallBackUrl);
                TradeOrderDO tradeOrderTemp = new TradeOrderDO();
                tradeOrderTemp.setId(tradeOrderDO.getId());
                tradeOrderTemp.setHandleNum(1);
                tradeOrderService.doUpdate(tradeOrderTemp);
            }
        }
        logger.error("分闪付跳转到聚惠分平台前的url" + url);
        return "redirect:" + url;
    }

    /**
     * 充值直接跳转到聚惠分平台
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/rechange/dealPayOrder", method = RequestMethod.GET)
    @ApiOperation(value = "充值,直接跳转到聚惠分平台")
    public String dealPayOrder(@ApiParam(value = "请求数据") String reqData) {
        TradeDTO trade = getReqData(reqData);
        String orderNo = trade.getOrderNo();
        TradeOrderDO tradeOrderDO = tradeOrderService.queryOneByOrderId(orderNo);
        String url = env.getProperty("app.base.url") + "/pay/dealPayFail.html";
        if (null != tradeOrderDO) {
            Integer handleNum = tradeOrderDO.getHandleNum();
            if (null == handleNum || handleNum == 0) {
                url = env.getProperty("jhf.open.api.url") + "/api/thirdPay/dealPayOrder";
                String payNotifyUrl = env.getProperty("open.base.url") + "/trade/jhf/payCompleteNotice";
                String payCallBackUrl = env.getProperty("open.base.url") + "/trade/jhf/payCompleteCallback?orderNo=" + orderNo;
                url += "?commID=" + tradeOrderDO.getChannelMerId() + "&reqData=" + tradeOrderService.getReqData(tradeOrderDO, payNotifyUrl, payCallBackUrl);
                TradeOrderDO tradeOrderTemp = new TradeOrderDO();
                tradeOrderTemp.setId(tradeOrderDO.getId());
                tradeOrderTemp.setHandleNum(1);
                tradeOrderService.doUpdate(tradeOrderTemp);
            }
        }
        logger.error("分闪付跳转到聚惠分平台前的url" + url);
        return "redirect:" + url;
    }

    private TradeDTO getReqData(String innerCode, String reqData) {
        MerchantChannel merchantChannelJhf = merchantService.getMerChannelByMerChannelInnerCodeType(innerCode, "04");
        if (null == merchantChannelJhf) {
            return null;
        }
        String keyStr = merchantChannelJhf.getChannelMerKey();
        String decodeStr = "";
        TradeDTO resultDto = null;
        try {
            decodeStr = AESUtil.decode(reqData, keyStr);
            logger.error("第三方接入获取二维码url解密后入参：" + decodeStr);
            resultDto = JSON.parseObject(decodeStr, TradeDTO.class);
        } catch (Exception ex) {
            logger.error("第三方接入获取二维码url出错", ex);
            return null;
        }
        return resultDto;
    }

    private TradeDTO getReqData(String reqData) {
        String decodeStr = "";
        TradeDTO resultDto = null;
        try {
            decodeStr = AESUtil.decode(reqData, TradeConstants.RECHANGE_AES_KEY);
            logger.error("第三方接入获取二维码url解密后入参：" + decodeStr);
            resultDto = JSON.parseObject(decodeStr, TradeDTO.class);
        } catch (Exception ex) {
            logger.error("第三方接入获取二维码url出错", ex);
            return null;
        }
        return resultDto;
    }
}
