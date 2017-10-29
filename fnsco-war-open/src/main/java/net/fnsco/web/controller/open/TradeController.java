package net.fnsco.web.controller.open;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.api.merchant.MerchantService;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.service.trade.TradeOrderService;
import net.fnsco.order.service.trade.entity.TradeOrderDO;
import net.fnsco.web.controller.open.jo.TradeJO;

/**
 * 
 * @desc 聚惠分分期付相关产品功能
 * @author   sxf
 * @version  
 * @since    Ver 1.1
 * @Date	 2017年10月27日 上午11:53:16
 *
 */
@RestController
@RequestMapping(value = "/open/trade/jhf", method = RequestMethod.POST)
@Api(value = "/open/trade/jhf", tags = { "聚惠分相关功能接口" })
public class TradeController extends BaseController {
    @Autowired
    private MerchantService   merchantService;
    @Autowired
    private TradeOrderService tradeOrderService;
    @Autowired
    private Environment       evn;

    /**
     * 获取聚惠分二维码url获取
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/getQRUrl")
    @ApiOperation(value = "获取商户编号")
    public ResultDTO getMerCode(@RequestBody TradeJO tradeJO) {
        MerchantChannel merchantChannel = merchantService.getMerChannel(tradeJO.getMerCode(), "00");
        if (null == merchantChannel) {
            return ResultDTO.fail("拉卡拉渠道信息不存在");
        }
        merchantChannel = merchantService.getMerChannelByInnerCodeType(merchantChannel.getInnerCode(), "04");
        if (null == merchantChannel) {
            return ResultDTO.fail("聚惠分渠道信息不存在");
        }
        TradeOrderDO tradeOrder = new TradeOrderDO();
        tradeOrder.setChannelMerId(merchantChannel.getChannelMerId());
        tradeOrder.setChannelType("04");
        tradeOrder.setInstallmentNum(tradeJO.getInstallmentNum());
        tradeOrder.setMercId(merchantChannel.getInnerCode());
        tradeOrder.setCreateUserId(tradeJO.getSnCode());
        tradeOrder.setTxnAmount(new BigDecimal(tradeJO.getPaymentAmount().replace("/.", "")));
        //支付方式00刷卡01二维码02分期付
        tradeOrder.setPayType("02");
        tradeOrder.setTxnType(1);
        tradeOrderService.doAdd(tradeOrder);
        String url = evn.getProperty("jhf.qr.pay.url");
        //        payAmount   支付金额    必填
        //        npr 唯一标识    必填
        //        uniqueIdType    分期数 必填
        //        commID  商户Id    必填
        //        unionId 客户ID    可选
        //        payCallBackParams   支付成功后回调参数   必填
        url += "?commID=" + merchantChannel.getChannelMerId() + "&payAmount=" + tradeJO.getPaymentAmount() + "&uniqueIdType=" + tradeJO.getInstallmentNum() + "unionId="
               + merchantChannel.getInnerCode() + "&npr=" + 1 + "&payCallBackParams=";
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("url", url);
        resultMap.put("orderId", 123);
        //，通知地址，回调地址。
        return success(resultMap);
    }
}
