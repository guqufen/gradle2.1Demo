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
import com.google.common.collect.Maps;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.api.merchant.MerchantService;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.DbUtil;
import net.fnsco.order.api.constant.ConstantEnum;
import net.fnsco.order.api.dto.OrderDTO;
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
        String urlLocalhost = evn.getProperty("jhf.qr.pay.url");
        //        commID  商户Id
        //        thirdPayNo  订单号
        //        payAmount   支付金额
        //        npr 分期数
        //        unionId 客户ID
        //        transTime   交易时间
        //        payNotifyUrl    通知URL
        //        payCallBackUrl  支付结束的回调URL
        //        payCallBackParams   支付成功后通知参数
        //        singData    MD5签名
        String createTimerStr = DateUtils.dateFormatToStr(tradeOrder.getCreateTime());
        String payCallBackParams = JSON.toJSONString(tradeOrder);
        //MD5(商户Id+订单号+支付金额+分期数+交易时间+通知URL+回调URL+通知参数)
        String singDataStr =  merchantChannelJhf.getChannelMerId()+tradeOrder.getOrderNo()
        +tradeJO.getPaymentAmount()+tradeOrder.getInstallmentNum()+createTimerStr;
        url += "?thirdPayNo=" + tradeOrder.getOrderNo() + "&commID=" + merchantChannelJhf.getChannelMerId() +
                "&payAmount=" + tradeJO.getPaymentAmount() + "&uniqueIdType="
               + tradeJO.getInstallmentNum() + "unionId=" + merchantChannelJhf.getInnerCode() + 
               "&transTime="+createTimerStr+
               "&npr=" + tradeOrder.getInstallmentNum() + "&singData="+DbUtil.MD5(singDataStr)+"&payNotifyUrl="+urlLocalhost+"&payCallBackUrl=&payCallBackParams=";
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
        OrderDTO order = new OrderDTO();
        order.setSalesOrderNo(salesOrderNo);
        order.setOrderStatus(orderStatus);
        order.setSettlementStatus(settlementStatus);
        order.setPayCallBackParams(payCallBackParams);
        order.setOrderCeateTime(new Date());
        logger.error("支付完成时的回调入参：" + JSON.toJSONString(order));
        ResultDTO result = tradeOrderService.updateOrderInfo(order);
        return success(result);
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
        OrderDTO order = new OrderDTO();
        order.setSalesOrderNo(salesOrderNo);
        order.setOrderStatus(orderStatus);
        order.setSettlementStatus(settlementStatus);
        order.setPayCallBackParams(payCallBackParams);
        logger.error("支付完成时的通知入参：" + JSON.toJSONString(order));
        ResultDTO result = tradeOrderService.updateOrderInfo(order);
        return success(url);
    }

    /**
     * 单条订单查询
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/getOrderInfo", method = RequestMethod.GET)
    @ApiOperation(value = "获取商户编号")
    public ResultDTO getOrderInfo(@RequestParam String orderNo) {
        TradeOrderDO tradeOrderDO = tradeOrderService.queryByOrderId(orderNo);
        tradeOrderDO.setCompleteTimeStr(DateUtils.dateFormatToStr(tradeOrderDO.getCompleteTime()));
        tradeOrderDO.setCreateTimeStr(DateUtils.dateFormatToStr(tradeOrderDO.getCreateTime()));
        tradeOrderDO.setOrderCeateTimeStr(DateUtils.dateFormatToStr(tradeOrderDO.getOrderCeateTime()));
        MerchantCore merChantCore = merchantService.getMerChantCoreByInnerCode(tradeOrderDO.getInnerCode());
        if (null != merChantCore) {
            tradeOrderDO.setMercName(merChantCore.getMerName());
        }
        return success(tradeOrderDO);
    }

}
