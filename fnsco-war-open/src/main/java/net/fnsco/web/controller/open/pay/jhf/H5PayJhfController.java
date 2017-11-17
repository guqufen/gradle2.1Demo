package net.fnsco.web.controller.open.pay.jhf;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
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
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.dby.AESUtil;
import net.fnsco.core.utils.dby.JHFMd5Util;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.constant.ConstantEnum;
import net.fnsco.trading.service.order.TradeOrderService;
import net.fnsco.trading.service.order.dto.TradeJhfJO;
import net.fnsco.trading.service.order.entity.TradeOrderDO;
import net.fnsco.web.controller.open.jo.TradeJO;

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
@RequestMapping(value = "/trade/h5Pay", method = RequestMethod.POST)
@Api(value = "/trade/h5Pay", tags = { "聚惠分相关功能接口" })
public class H5PayJhfController extends BaseController {
    @Autowired
    private MerchantService     merchantService;
    @Autowired
    private TradeOrderService   tradeOrderService;
    @Autowired
    private MerchantCoreService merchantCoreService;
    @Autowired
    private Environment         env;

    /**
     * 收银台获取聚惠分二维码url获取，入参为渠道商户号
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/dealPayOrder")
    @ApiOperation(value = "第三方接入获取聚惠分二维码url")
    @ResponseBody
    public ResultDTO dealPayOrder(@RequestBody TradeJO tradeJO) {
        MerchantChannel merchantChannelJhf = merchantService.getMerChannelByMerChannelInnerCodeType(tradeJO.getInnerCode(), "04");
        if (null == merchantChannelJhf) {
            return ResultDTO.fail(ApiConstant.E_PAY_NOT_EXIT_ERROR);
        }
        List<MerchantEntityCoreRef> refList = merchantCoreService.queryEntityCoreRefByInnerCode(merchantChannelJhf.getInnerCode());
        String entityInnerCode = "";
        for (MerchantEntityCoreRef temp : refList) {
            entityInnerCode = temp.getEntityInnerCode();
            break;
        }
        TradeOrderDO tradeOrder = new TradeOrderDO();
        tradeOrder.setInnerCode(merchantChannelJhf.getInnerCode());
        tradeOrder.setChannelMerId(merchantChannelJhf.getChannelMerId());
        tradeOrder.setChannelType("04");
        tradeOrder.setInstallmentNum(tradeJO.getInstallmentNum());
        tradeOrder.setEntityInnerCode(entityInnerCode);
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
        String rspData = tradeOrderService.getReqData(tradeOrder);
        String url = env.getProperty("jhf.open.api.url") + "/api/thirdPay/dealPayOrder";
        url += "?commID=" + tradeOrder.getChannelMerId() + "&reqData=" + rspData;
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("url", url);
        resultMap.put("orderNo", tradeOrder.getOrderNo());
        //，通知地址，回调地址。
        return success(resultMap);
    }

}
