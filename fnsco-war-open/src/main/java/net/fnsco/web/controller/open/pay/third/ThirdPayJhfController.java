package net.fnsco.web.controller.open.pay.third;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.api.merchant.MerchantService;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.dby.AESUtil;
import net.fnsco.core.utils.dby.JHFMd5Util;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.constant.ConstantEnum;
import net.fnsco.order.service.trade.TradeOrderService;
import net.fnsco.order.service.trade.entity.TradeOrderDO;
import net.fnsco.web.controller.open.jo.TradeJO;
import net.fnsco.web.controller.open.jo.TradeJhfJO;

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
@RequestMapping(value = "/trade/thirdPay", method = RequestMethod.POST)
@Api(value = "/trade/thirdPay", tags = { "聚惠分相关功能接口" })
public class ThirdPayJhfController extends BaseController {
    @Autowired
    private MerchantService   merchantService;
    @Autowired
    private TradeOrderService tradeOrderService;
    @Autowired
    private Environment       env;

    /**
     * 收银台获取聚惠分二维码url获取，入参为渠道商户号
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/getQRUrl")
    @ApiOperation(value = "第三方接入获取聚惠分二维码url")
    @ResponseBody
    public ResultDTO dealPayOrder(String merCode, String rspData) {
        if (Strings.isNullOrEmpty(rspData)) {
            logger.error("第三方接入获取二维码url密文入参为空");
            fail("失败，rspData入参为空");
        }
        MerchantChannel merchantChannelJhf = merchantService.getMerChannelByMerChannelInnerCodeType(merCode, "04");
        if (null == merchantChannelJhf) {
            return ResultDTO.fail(ApiConstant.E_PAY_NOT_EXIT_ERROR);
        }
        String keyStr = merchantChannelJhf.getChannelMerKey();
        TradeJO tradeJO = new TradeJO();
        try {
            String decodeStr = AESUtil.decode(rspData, keyStr);
            logger.error("第三方接入获取二维码url解密后入参：" + decodeStr);
            //聚惠芬支付完成时的通知解密后入参：{"payCallBackParams":"","settlementStatus":"0","thirdPayNo":"20171102155606073111374535549766","orderStatus":"2","singData":"001AA0CC08241A447BF7250B500C4B83"}
            tradeJO = JSON.parseObject(decodeStr, TradeJO.class);
        } catch (Exception ex) {
            logger.error("第三方接入获取二维码url出错", ex);
            return fail("失败，解密处理异常");
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
        
        String payNotifyUrl = env.getProperty("open.base.url") + "/trade/jhf/payCompleteNotice";
        String payCallBackUrl = env.getProperty("open.base.url") + "/trade/jhf/payCompleteCallback?orderNo=" + tradeOrder.getOrderNo();
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
        String createTimerStr = DateUtils.dateFormat1ToStr(tradeOrder.getCreateTime());
        String payCallBackParams = JSON.toJSONString(tradeOrder);
        //MD5(商户Id+订单号+支付金额+分期数+交易时间+通知URL+回调URL+通知参数)
        BigDecimal amountTemp = tradeOrder.getTxnAmount();
        BigDecimal amountTemps = amountTemp.divide(new BigDecimal("100"));
        String singDataStr = merchantChannelJhf.getChannelMerId() + tradeOrder.getOrderNo() + amountTemps.toString() + String.valueOf(tradeOrder.getInstallmentNum()) + createTimerStr + payNotifyUrl
                             + payCallBackUrl;
        logger.error("第三方接入支付签名前数据" + singDataStr);
        String singData = JHFMd5Util.encode32(singDataStr);
        logger.error("第三方接入支付签名" + singData);
        TradeJhfJO jhfJO = new TradeJhfJO();
        jhfJO.setCommID(tradeOrder.getChannelMerId());
        jhfJO.setPeriodNum(String.valueOf(tradeOrder.getInstallmentNum()));

        jhfJO.setPayAmount(amountTemps.toString());
        jhfJO.setPayCallBackParams("");
        jhfJO.setPayCallBackUrl(payCallBackUrl);//payCallBackUrl
        jhfJO.setPayNotifyUrl(payNotifyUrl);
        jhfJO.setSingData(singData);
        jhfJO.setThirdPayNo(tradeOrder.getOrderNo());
        jhfJO.setTransTime(createTimerStr);
        jhfJO.setUnionId(tradeOrder.getInnerCode());
        String reqData = "";
        String dateTemp = JSON.toJSONString(jhfJO);
        try {
            reqData = URLEncoder.encode(AESUtil.encode(dateTemp, keyStr), "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("第三方接入支付生成分期付url时AES加密出错", e);
        }
        String url = env.getProperty("open.base.url") + "/trade/thirdPay/dealPayOrder";
        url += "?orderNo="+tradeOrder.getOrderNo()+"&commID=" + tradeOrder.getChannelMerId() + "&reqData=" + reqData;
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("url", url);
        resultMap.put("orderNo", tradeOrder.getOrderNo());
        //，通知地址，回调地址。
        return success(resultMap);
    }
    /**
     * 收银台获取聚惠分二维码url获取(暂时未使用)
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/dealPayOrder", method = RequestMethod.GET)
    @ApiOperation(value = "获取聚惠分二维码url")
    public String dealPayOrder(String orderNo,String commID, String reqData) {
        String url = env.getProperty("jhf.open.api.url") + "/api/thirdPay/dealPayOrder";
        url += "?commID=" + commID + "&reqData=" + reqData;
        return "forward:"+url;
    }
    /**
     * 收银台查询单条订单信息
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/getOrderInfo", method = RequestMethod.GET)
    @ApiOperation(value = "获取商户编号")
    @ResponseBody
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

}
