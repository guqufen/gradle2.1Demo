package net.fnsco.web.controller.e789.pay.jhf;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.api.merchant.MerchantService;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.constant.ConstantEnum;
import net.fnsco.trading.service.order.TradeOrderService;
import net.fnsco.trading.service.order.entity.TradeOrderDO;
import net.fnsco.web.controller.e789.jo.TradeJO;

/**
 * 
 * @desc 分闪付支付相关功能
 * @author   sxf
 * @version  
 * @since    Ver 1.1
 * @Date     2017年10月27日 上午11:53:16
 *
 */
@RestController
@RequestMapping(value = "/open/trade/jhf", method = RequestMethod.POST)
@Api(value = "/open/trade/jhf", tags = { "分闪付支付相关接口功能" })
public class TradeFsfController extends BaseController {
    @Autowired
    private MerchantService   merchantService;
    @Autowired
    private TradeOrderService tradeOrderService;
    @Autowired
    private Environment       env;

    /**
     * 获取聚惠分二维码url获取
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/getQRUrl")
    @ApiOperation(value = "获取分闪付url")
    public ResultDTO getMerCode(@RequestBody TradeJO tradeJO) {
        MerchantChannel merchantChannel = merchantService.getMerChannel(tradeJO.getMerCode(), "00");
        if (null == merchantChannel) {
            return ResultDTO.fail("拉卡拉渠道信息不存在");
        }
        MerchantChannel merchantChannelJhf = merchantService.getMerChannelByInnerCodeType(merchantChannel.getInnerCode(), "04");
        if (null == merchantChannelJhf) {
            return ResultDTO.fail(ApiConstant.E_PAY_NOT_EXIT_ERROR);
        }
        TradeOrderDO tradeOrder = new TradeOrderDO();
        tradeOrder.setInnerCode(merchantChannelJhf.getInnerCode());
        tradeOrder.setChannelMerId(merchantChannelJhf.getChannelMerId());
        tradeOrder.setChannelType("04");
        tradeOrder.setInstallmentNum(tradeJO.getInstallmentNum());
        tradeOrder.setEntityInnerCode(merchantChannelJhf.getEntityInnerCode());
        tradeOrder.setCreateUserId(tradeJO.getSnCode());
        BigDecimal amountB = new BigDecimal(tradeJO.getPaymentAmount());
        BigDecimal amountBs = amountB.multiply(new BigDecimal("100"));
        tradeOrder.setTxnAmount(amountBs);
        //支付方式00刷卡01二维码02分期付
        tradeOrder.setPayType("02");
        //交易子类型00刷卡01微信02支付宝03聚惠分
        tradeOrder.setPaySubType("03");
        tradeOrder.setTxnType(1);
        tradeOrder.setRespCode(ConstantEnum.RespCodeEnum.HANDLING.getCode());
        tradeOrder.setSyncStatus(0);
        tradeOrderService.doAdd(tradeOrder);

        //String url = env.getProperty("jhf.open.api.url") + "/api/thirdPay/dealPayOrder";
        //url += "?commID=" + tradeOrder.getChannelMerId() + "&reqData=" + reqData;
        String url = env.getProperty("open.base.url") + "/trade/pay/dealPayOrder";
        url += "?orderNo=" + tradeOrder.getOrderNo() + "&commID=" + tradeOrder.getChannelMerId() + "&reqData=123";
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("url", url);
        resultMap.put("orderNo", tradeOrder.getOrderNo());
        //，通知地址，回调地址。
        return success(resultMap);
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
        TradeOrderDO tradeOrderDO = tradeOrderService.queryOneByOrderId(orderNo);
        tradeOrderDO.setCompleteTimeStr(DateUtils.dateFormatToStr(tradeOrderDO.getCompleteTime()));
        tradeOrderDO.setCreateTimeStr(DateUtils.dateFormatToStr(tradeOrderDO.getCreateTime()));
        tradeOrderDO.setOrderCeateTimeStr(DateUtils.dateFormatToStr(tradeOrderDO.getOrderCeateTime()));
        MerchantCore merChantCore = merchantService.getMerChantCoreByInnerCode(tradeOrderDO.getInnerCode());
        if (null != merChantCore) {
            tradeOrderDO.setMercName(merChantCore.getMerName());
        }
        return success(tradeOrderDO);
    }

    /**
     * 单条订单查询
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/getOrderList", method = RequestMethod.GET)
    @ApiOperation(value = "获取商户编号")
    public ResultDTO<TradeOrderDO> getOrderList(@RequestBody TradeJO tradeJO) {
        MerchantChannel merchantChannel = merchantService.getMerChannel(tradeJO.getMerCode(), "00");
        if (null == merchantChannel) {
            return fail("拉卡拉渠道信息不存在");
        }
        MerchantChannel merchantChannelJhf = merchantService.getMerChannelByInnerCodeType(merchantChannel.getInnerCode(), "04");
        if (null == merchantChannelJhf) {
            return ResultDTO.fail(ApiConstant.E_PAY_NOT_EXIT_ERROR);
        }
        TradeOrderDO tradeOrder = new TradeOrderDO();
        tradeOrder.setOrderNoAfter6(tradeJO.getOrderNo());
        tradeOrder.setOrderTop10(tradeJO.getDate());
        tradeOrder.setInnerCode(merchantChannelJhf.getInnerCode());
        tradeOrder.setRespCode("1001");
        //tradeOrder.setSettleStatus(4);
        ResultPageDTO<TradeOrderDO> resultDTO = tradeOrderService.page(tradeOrder, tradeJO.getPageNum(), tradeJO.getPageSize());
        List<TradeOrderDO> resultList = resultDTO.getList();
        for (TradeOrderDO tradeOrderDO : resultList) {
            tradeOrderDO.setCompleteTimeStr(DateUtils.dateFormatToStr(tradeOrderDO.getCompleteTime()));
            tradeOrderDO.setCreateTimeStr(DateUtils.dateFormatToStr(tradeOrderDO.getCreateTime()));
            tradeOrderDO.setOrderCeateTimeStr(DateUtils.dateFormatToStr(tradeOrderDO.getOrderCeateTime()));
            //            BigDecimal eachMoney = tradeOrderDO.getEachMoney();
            //            if (null != eachMoney) {
            //                eachMoney = eachMoney.divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
            //                tradeOrderDO.setEachMoney(eachMoney);
            //            }
            //            BigDecimal orderAmount = tradeOrderDO.getOrderAmount();
            //            if (null != orderAmount) {
            //                orderAmount = orderAmount.divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
            //                tradeOrderDO.setOrderAmount(orderAmount);
            //            }
            //MerchantCore merChantCore = merchantService.getMerChantCoreByInnerCode(tradeOrderDO.getInnerCode());
            //if (null != merChantCore) {
            //    tradeOrderDO.setMercName(merChantCore.getMerName());
            //}
        }
        return success(resultDTO);
    }
}
