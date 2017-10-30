package net.fnsco.web.controller.open;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.api.dto.MerChantCoreDTO;
import net.fnsco.bigdata.api.merchant.MerchantService;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.constant.ConstantEnum;
import net.fnsco.order.service.trade.TradeOrderService;
import net.fnsco.order.service.trade.entity.TradeOrderDO;
import net.fnsco.web.controller.open.jo.OrderJO;
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
        MerchantChannel merchantChannelJhf = merchantService.getMerChannelByInnerCodeType(merchantChannel.getInnerCode(), "04");
        if (null == merchantChannelJhf) {
            return ResultDTO.fail("聚惠分渠道信息不存在");
        }
        TradeOrderDO tradeOrder = new TradeOrderDO();
        tradeOrder.setInnerCode(merchantChannelJhf.getInnerCode());
        tradeOrder.setChannelMerId(merchantChannelJhf.getChannelMerId());
        tradeOrder.setChannelType("04");
        tradeOrder.setInstallmentNum(tradeJO.getInstallmentNum());
        tradeOrder.setMercId(merchantChannelJhf.getInnerCode());
        tradeOrder.setCreateUserId(tradeJO.getSnCode());
        String amount = tradeJO.getPaymentAmount().replaceAll("\\.", "");
        tradeOrder.setTxnAmount(new BigDecimal(amount));
        //支付方式00刷卡01二维码02分期付
        tradeOrder.setPayType("02");
        //交易子类型00刷卡01微信02支付宝03聚惠分
        tradeOrder.setPaySubType("03");
        tradeOrder.setTxnType(1);
        tradeOrder.setRespCode(ConstantEnum.RespCodeEnum.HANDLING.getCode());
        tradeOrder.setSyncStatus(0);
        tradeOrderService.doAdd(tradeOrder);
        String url = evn.getProperty("jhf.qr.pay.url");
        //        payAmount   支付金额    必填
        //        npr 唯一标识    必填
        //        uniqueIdType    分期数 必填
        //        commID  商户Id    必填
        //        unionId 客户ID    可选
        //        payCallBackParams   支付成功后回调参数   必填
        url += "?commID=" + merchantChannelJhf.getChannelMerId() + "&payAmount=" + tradeJO.getPaymentAmount() + "&uniqueIdType=" + tradeJO.getInstallmentNum() + "unionId="
               + merchantChannelJhf.getInnerCode() + "&npr=" + tradeOrder.getOrderNo() + "&payCallBackParams=";
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("url", url);
        resultMap.put("orderNo", tradeOrder.getOrderNo());
        //，通知地址，回调地址。
        return success(resultMap);
    }

    //    salesOrderNo    订单号 
    //    orderStatus 订单状态    （0 未支付 1支付成功 2支付失败 3已退货）
    //    settlementStatus    结算状态    （0 未结算 1已结算   2结算中   3已退款）
    //    payCallBackParams   商户上送参数  
    /**
     * 支付完成时的回调
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/payCompleteCallback")
    @ApiOperation(value = "支付完成时的回调")
    public ResultDTO payCompleteCallback(String salesOrderNo, String orderStatus, String settlementStatus, String payCallBackParams) {
        //保存订单信息
        //成功或失败则同步到交易流水信息
        OrderJO order = new OrderJO();
        order.setSalesOrderNo(salesOrderNo);
        order.setOrderStatus(orderStatus);
        order.setSettlementStatus(settlementStatus);
        order.setPayCallBackParams(payCallBackParams);
        logger.error("支付完成时的回调入参：" + JSON.toJSONString(order));
        ResultDTO result = saveOrderInfo(order);
        return success(result);
    }

    private ResultDTO saveOrderInfo(OrderJO order) {
        String payCallBackParams = order.getPayCallBackParams();
        TradeOrderDO tradeOrderTemp = JSON.parseObject(payCallBackParams, TradeOrderDO.class);
        String orderNo = tradeOrderTemp.getOrderNo();
        TradeOrderDO tradeOrderDO = tradeOrderService.queryByOrderId(orderNo);
        if (null == tradeOrderDO) {
            logger.error("订单不存在" + JSON.toJSONString(tradeOrderDO));
            return fail("订单不存在");
        }
        //      orderStatus 订单状态    （0 未支付 1支付成功 2支付失败 3已退货）
        String respCode = ConstantEnum.RESP_CODE_MAP.get(order.getOrderStatus());
        if (Strings.isNullOrEmpty(respCode)) {
            respCode = tradeOrderDO.getRespCode();
        }
        tradeOrderDO.setRespCode(respCode);
        tradeOrderDO.setPayOrderNo(order.getSalesOrderNo());
        //结算状态（0 未结算 1已结算   2结算中   3已退款）
        if (Strings.isNullOrEmpty(order.getSettlementStatus())) {
            try {
                tradeOrderDO.setSettleStatus(Integer.parseInt(order.getSettlementStatus()));
                tradeOrderDO.setSettleDate(new Date());
            } catch (Exception ex) {
                logger.error("支付完成时回调时结算状态转换为int出错", ex);
            }
        }
        if ("3".equals(order.getSettlementStatus())) {//3已退货
            tradeOrderDO.setId(null);
            tradeOrderDO.setTxnType(2);
            tradeOrderService.doAdd(tradeOrderDO);
        } else {
            tradeOrderService.doUpdate(tradeOrderDO);
        }
        return null;
    }

    /**
     * 支付完成时的通知，显示我们自己的页面
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/payCompleteNotice")
    @ApiOperation(value = "支付完成时的通知")
    public ResultDTO payCompleteNotice(String salesOrderNo, String orderStatus, String settlementStatus, String payCallBackParams) {
        String url = evn.getProperty("jhf.qr.pay.url");
        OrderJO order = new OrderJO();
        order.setSalesOrderNo(salesOrderNo);
        order.setOrderStatus(orderStatus);
        order.setSettlementStatus(settlementStatus);
        order.setPayCallBackParams(payCallBackParams);
        logger.error("支付完成时的通知入参：" + JSON.toJSONString(order));
        ResultDTO result = saveOrderInfo(order);
        return success(url);
    }

    /**
     * 单条订单查询
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/getOrderInfo")
    @ApiOperation(value = "获取商户编号")
    public ResultDTO getOrderInfo(@RequestParam String orderNo) {
        TradeOrderDO tradeOrderDO = tradeOrderService.queryByOrderId(orderNo);
        MerChantCoreDTO merChantCoreDTO = merchantService.getMerChantCoreByInnerCode(tradeOrderDO.getInnerCode());
        if (null != merChantCoreDTO) {
            tradeOrderDO.setMercName(merChantCoreDTO.getMerName());
        }
        return success(tradeOrderDO);
    }

}
