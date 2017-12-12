package net.fnsco.web.controller.e789.pay.jhf;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
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
import io.swagger.annotations.ApiParam;
import net.fnsco.bigdata.api.merchant.MerchantService;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.bigdata.service.domain.MerchantEntity;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.dby.AESUtil;
import net.fnsco.core.utils.dby.JHFMd5Util;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.constant.ConstantEnum;
import net.fnsco.trading.comm.TradeConstants;
import net.fnsco.trading.service.merchantentity.AppUserMerchantEntityService;
import net.fnsco.trading.service.order.TradeOrderService;
import net.fnsco.trading.service.order.entity.TradeOrderDO;
import net.fnsco.web.controller.e789.jo.GetQRUrlJO;
import net.fnsco.web.controller.e789.vo.GetOrderInfoResultVO;
import net.fnsco.web.controller.e789.vo.GetQRUrlResultVO;

/**
 * 
 * @desc e789中的分闪付支付相关功能
 * @author   sxf
 * @version  
 * @since    Ver 1.1
 * @Date     2017年12月05日 上午11:53:16
 *
 */
@RestController
@RequestMapping(value = "/app2c/trade/fsf")
@Api(value = "/app2c/trade/fsf", tags = { "首页-分闪付支付接口" })
public class PayFsfController extends BaseController {
    @Autowired
    private MerchantService              merchantService;
    @Autowired
    private TradeOrderService            tradeOrderService;
    @Autowired
    private AppUserMerchantEntityService entityService;
    @Autowired
    private Environment                  env;

    /**
     * 获取聚惠分二维码url，生成二维码用，支付使用固定秘钥
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/getQRUrl", method = RequestMethod.POST)
    @ApiOperation(value = "首页-分闪付支付-获取分闪付url，用于生成二维码")
    public ResultDTO<GetQRUrlResultVO> getQRUrl(@RequestBody GetQRUrlJO getQRUrlJO) {
        Integer userId = getQRUrlJO.getUserId();
        MerchantEntity merchantEntity = entityService.queryMerInfoByUserId(userId);
        if (null == merchantEntity) {
            return ResultDTO.fail(TradeConstants.E_ENTITY_MERCHANT_NOT_EXIT_ERROR);
        }
        //根据用户id获取绑定的分闪付商户信息
        MerchantChannel merchantChannelJhf = merchantService.getMerChannelByEntityInnerCodeType(merchantEntity.getEntityInnerCode(), "04");
        if (null == merchantChannelJhf) {
            return ResultDTO.fail(ApiConstant.E_PAY_NOT_EXIT_ERROR);
        }
        String innerCode = merchantChannelJhf.getInnerCode();
        TradeOrderDO tradeOrder = new TradeOrderDO();
        tradeOrder.setInnerCode(merchantChannelJhf.getInnerCode());
        tradeOrder.setChannelMerId(merchantChannelJhf.getChannelMerId());
        tradeOrder.setChannelType("04");
        tradeOrder.setInstallmentNum(getQRUrlJO.getInstallmentNum());
        tradeOrder.setEntityInnerCode(merchantChannelJhf.getEntityInnerCode());
        tradeOrder.setCreateUserId(String.valueOf(getQRUrlJO.getUserId()));
        BigDecimal amountB = new BigDecimal(getQRUrlJO.getPaymentAmount());
        BigDecimal amountBs = amountB.multiply(new BigDecimal("100"));
        tradeOrder.setTxnAmount(amountBs);
        //支付方式00刷卡01二维码02分期付
        tradeOrder.setPayType("02");
        //交易子类型00刷卡01微信02支付宝03聚惠分
        tradeOrder.setPaySubType("03");
        tradeOrder.setTxnType(1);
        //交易子类型10购买消费11充值消费20购买撤销21充值撤销
        tradeOrder.setTxnSubType(TradeConstants.TxnSubTypeEnum.BUY_CONSUME.getCode());
        //00pos机01app02台码
        tradeOrder.setPayMedium(TradeConstants.PayMediumEnum.APP.getCode());
        tradeOrder.setRespCode(ConstantEnum.RespCodeEnum.HANDLING.getCode());
        tradeOrder.setSyncStatus(0);
        tradeOrderService.doAdd(tradeOrder);
        String url = env.getProperty("app.base.url") + "/trade/fsf/pay/dealPayOrder";
        url += "?" + "&commID=" + innerCode + "&reqData=" + getReqData(innerCode, tradeOrder.getOrderNo());
        GetQRUrlResultVO result = new GetQRUrlResultVO();
        result.setUrl(url);
        result.setOrderNo(tradeOrder.getOrderNo());
        //，通知地址，回调地址。
        return success(result);
    }

    /**
     * 单条订单查询
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/getOrderInfo", method = RequestMethod.POST)
    @ApiOperation(value = "首页-分闪付支付-获取订单信息")
    public ResultDTO<GetOrderInfoResultVO> getOrderInfo(@ApiParam(value = "订单号") @RequestParam String orderNo) {
        TradeOrderDO tradeOrderDO = tradeOrderService.queryOneByOrderId(orderNo);
        tradeOrderDO.setCompleteTimeStr(DateUtils.dateFormatToStr(tradeOrderDO.getCompleteTime()));
        tradeOrderDO.setCreateTimeStr(DateUtils.dateFormatToStr(tradeOrderDO.getCreateTime()));
        tradeOrderDO.setOrderCeateTimeStr(DateUtils.dateFormatToStr(tradeOrderDO.getOrderCeateTime()));
        MerchantCore merChantCore = merchantService.getMerChantCoreByInnerCode(tradeOrderDO.getInnerCode());
        if (null != merChantCore) {
            tradeOrderDO.setMercName(merChantCore.getMerName());
        }
        GetOrderInfoResultVO result = new GetOrderInfoResultVO();
        result.setOrderNo(tradeOrderDO.getOrderNo());
        result.setRespCode(tradeOrderDO.getRespCode());
        return success(result);
    }

    private String getReqData(String innerCode, String orderNo) {
        String reqData = "";
        MerchantChannel merchantChannelJhf = merchantService.getMerChannelByMerChannelInnerCodeType(innerCode, "04");
        if (null == merchantChannelJhf) {
            return reqData;
        }
        String transTime = DateUtils.dateFormat1ToStr(new Date());
        Map<String, String> result = Maps.newHashMap();
        result.put("innerCode", innerCode);
        result.put("orderNo", orderNo);
        result.put("transTime", transTime);
        String singDataStr = innerCode + orderNo + transTime;
        logger.error("签名前数据" + singDataStr);
        String singData = JHFMd5Util.encode32(singDataStr);
        result.put("singData", singData);
        String dateTemp = JSON.toJSONString(result);
        try {
            reqData = URLEncoder.encode(AESUtil.encode(dateTemp, TradeConstants.RECHANGE_AES_KEY), "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("生成分期付url时AES加密出错", e);
        }
        return reqData;
    }
}
