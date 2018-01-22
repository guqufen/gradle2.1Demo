package net.fnsco.web.controller.open.pay.jhf;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.bigdata.api.merchant.MerchantService;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.bigdata.service.domain.MerchantEntityCoreRef;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.dby.AESUtil;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.constant.ConstantEnum;
import net.fnsco.trading.service.order.TradeOrderService;
import net.fnsco.trading.service.order.entity.TradeOrderDO;
import net.fnsco.web.controller.open.jo.TradeJO;
import net.fnsco.web.controller.open.jo.TradeThirdPayJO;

/**
 * 
 * @desc 分闪付分期付第三方调用接口相关功能
 * @author   sxf
 * @version  
 * @since    Ver 1.1
 * @Date     2017年10月27日 上午11:53:16
 *
 */
@Controller
@RequestMapping(value = "/trade/thirdPay")
@Api(value = "/trade/thirdPay", tags = { "分闪付第三方调用相关功能接口" })
public class ThirdPayJhfController extends BaseController {
    @Autowired
    private MerchantService     merchantService;
    @Autowired
    private TradeOrderService   tradeOrderService;
    @Autowired
    private MerchantCoreService merchantCoreService;
    @Autowired
    private Environment         env;

    /**
     * 收银台获取分闪付二维码url获取，入参为渠道商户号
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/getQRUrl", method = RequestMethod.POST)
    @ApiOperation(value = "第三方接入获取分闪付二维码url")
    @ResponseBody
    public ResultDTO getQRUrl(String innerCode, String reqData) {
        logger.error(innerCode + "第三方接入获取二维码url密文入参为" + reqData);
        if (Strings.isNullOrEmpty(reqData)) {
            logger.error("第三方接入获取二维码url密文入参为空");
            return fail("失败，rspData入参为空");
        }
        if (Strings.isNullOrEmpty(innerCode)) {
            logger.error("第三方接入获取二维码url中innerCode入参为空");
            return fail("失败，innerCode入参为空");
        }
        TradeJO tradeJO = getReqData(innerCode, reqData);
        if (null == tradeJO) {
            return ResultDTO.fail(ApiConstant.E_PAY_NOT_EXIT_ERROR);
        }
        List<MerchantEntityCoreRef> refList = merchantCoreService.queryEntityCoreRefByInnerCode(tradeJO.getInnerCode());
        String entityInnerCode = "";
        for (MerchantEntityCoreRef temp : refList) {
            entityInnerCode = temp.getEntityInnerCode();
            break;
        }
        TradeOrderDO tradeOrder = new TradeOrderDO();
        tradeOrder.setInnerCode(tradeJO.getInnerCode());
        tradeOrder.setChannelMerId(tradeJO.getChannelMerId());
        tradeOrder.setChannelType("04");
        tradeOrder.setInstallmentNum(tradeJO.getPeriodNum());
        tradeOrder.setEntityInnerCode(entityInnerCode);
        //tradeOrder.setCreateUserId(tradeJO.getSnCode());
        BigDecimal amountB = new BigDecimal(tradeJO.getPaymentAmount());
        BigDecimal amountBs = amountB.multiply(new BigDecimal("100"));
        tradeOrder.setTxnAmount(amountBs);
        //支付方式00刷卡01二维码02分期付
        tradeOrder.setPayType("02");
        //交易子类型00刷卡01微信02支付宝03分闪付
        tradeOrder.setPaySubType("03");
        tradeOrder.setTxnType(1);
        tradeOrder.setRespCode(ConstantEnum.RespCodeEnum.HANDLING.getCode());
        tradeOrder.setSyncStatus(0);
        tradeOrderService.doAdd(tradeOrder);

        String url = env.getProperty("open.base.url") + "/trade/pay/dealPayOrder";
        url += "?orderNo=" + tradeOrder.getOrderNo() + "&commID=123" + "&reqData=123";
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("url", url);
        resultMap.put("orderNo", tradeOrder.getOrderNo());
        //，通知地址，回调地址。
        return success(resultMap);
    }

    private TradeJO getReqData(String innerCode, String reqData) {
        MerchantChannel merchantChannelJhf = merchantService.getMerChannelByMerChannelInnerCodeType(innerCode, "04");
        if (null == merchantChannelJhf) {
            return null;
        }
        String keyStr = merchantChannelJhf.getChannelMerKey();
        TradeJO tradeJO = new TradeJO();
        tradeJO.setChannelMerId(merchantChannelJhf.getChannelMerId());
        try {
            String decodeStr = AESUtil.decode(reqData, keyStr);
            logger.error("第三方接入获取二维码url解密后入参：" + decodeStr);
            //聚惠芬支付完成时的通知解密后入参：{"payCallBackParams":"","settlementStatus":"0","thirdPayNo":"20171102155606073111374535549766","orderStatus":"2","singData":"001AA0CC08241A447BF7250B500C4B83"}
            tradeJO = JSON.parseObject(decodeStr, TradeJO.class);
            tradeJO.setChannelMerId(tradeJO.getChannelMerId());
        } catch (Exception ex) {
            logger.error("第三方接入获取二维码url出错", ex);
            return null;
        }
        return tradeJO;
    }

    /**
     * 收银台查询单条订单信息
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/getOrderInfo", method = RequestMethod.POST)
    @ApiOperation(value = "获取商户编号")
    @ResponseBody
    public ResultDTO getOrderInfo(String innerCode, String reqData) {
        TradeJO tradeJO = getReqData(innerCode, reqData);
        if (null == tradeJO) {
            return ResultDTO.fail(ApiConstant.E_PAY_NOT_EXIT_ERROR);
        }
        TradeOrderDO tradeOrderDO = tradeOrderService.queryOneByOrderId(tradeJO.getOrderNo());
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
     * 订单列表查询
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/getOrderList", method = RequestMethod.POST)
    @ApiOperation(value = "订单列表查询")
    @ResponseBody
    public ResultDTO getOrderList(String innerCode, String reqData) {
        TradeJO tradeJO = getReqData(innerCode, reqData);
        if (null == tradeJO) {
            return ResultDTO.fail(ApiConstant.E_PAY_NOT_EXIT_ERROR);
        }
        TradeOrderDO tradeOrder = new TradeOrderDO();
        tradeOrder.setOrderNo(tradeJO.getOrderNo());
        tradeOrder.setOrderTop10(tradeJO.getDate());
        tradeOrder.setInnerCode(tradeJO.getInnerCode());
        //tradeOrder.setRespCode("1001");
        //tradeOrder.setSettleStatus(4);
        ResultPageDTO<TradeOrderDO> resultDTO = tradeOrderService.page(tradeOrder, tradeJO.getPageNum(), tradeJO.getPageSize());
        List<TradeOrderDO> tradeOrderList = resultDTO.getList();
        List<TradeThirdPayJO> resultList = Lists.newArrayList();
        for (TradeOrderDO tradeOrderDO : tradeOrderList) {
            TradeThirdPayJO tradeOrderJO = new TradeThirdPayJO();
            tradeOrderJO.setId(tradeOrderDO.getId());
            tradeOrderJO.setOrderNo(tradeOrderDO.getOrderNo());
            tradeOrderJO.setInstallmentNum(tradeOrderDO.getInstallmentNum());
            tradeOrderJO.setCompleteTime(DateUtils.dateFormatToStr(tradeOrderDO.getCompleteTime()));
            tradeOrderJO.setCreateTime(DateUtils.dateFormatToStr(tradeOrderDO.getCreateTime()));
            tradeOrderJO.setTxnAmount(tradeOrderDO.getTxnAmount());
            tradeOrderJO.setRespCode(tradeOrderDO.getRespCode());
            tradeOrderJO.setRespMsg(tradeOrderDO.getRespCode());
            tradeOrderJO.setInnerCode(tradeOrderDO.getInnerCode());
            BigDecimal eachMoney = tradeOrderDO.getEachMoney();
            if (null != eachMoney) {
                eachMoney = eachMoney.divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
                tradeOrderJO.setEachMoney(eachMoney);
            }
            BigDecimal orderAmount = tradeOrderDO.getOrderAmount();
            if (null != orderAmount) {
                orderAmount = orderAmount.divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
                tradeOrderJO.setOrderAmount(orderAmount);
            }
            MerchantCore merChantCore = merchantService.getMerChantCoreByInnerCode(tradeOrderDO.getInnerCode());
            if (null != merChantCore) {
                tradeOrderJO.setMercName(merChantCore.getMerName());
            }
            resultList.add(tradeOrderJO);
        }
        return success(resultList);
    }
}
