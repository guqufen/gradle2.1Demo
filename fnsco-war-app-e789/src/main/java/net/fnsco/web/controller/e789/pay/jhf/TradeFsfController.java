package net.fnsco.web.controller.e789.pay.jhf;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import net.fnsco.web.controller.e789.jo.GetQRUrlJO;
import net.fnsco.web.controller.e789.jo.TradeJO;
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
@RequestMapping(value = "/app2c/trade/jhf", method = RequestMethod.POST)
@Api(value = "/app2c/trade/jhf", tags = { "分闪付支付接口" })
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
    public ResultDTO<GetQRUrlResultVO> getQRUrl(@RequestBody GetQRUrlJO getQRUrlJO) {
        String innerCode = "";
        Integer userId = getQRUrlJO.getUserId();
        //根据用户id获取绑定的分闪付商户信息
        MerchantChannel merchantChannelJhf = merchantService.getMerChannelByInnerCodeType(innerCode, "04");
        if (null == merchantChannelJhf) {
            return ResultDTO.fail(ApiConstant.E_PAY_NOT_EXIT_ERROR);
        }
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
        tradeOrder.setRespCode(ConstantEnum.RespCodeEnum.HANDLING.getCode());
        tradeOrder.setSyncStatus(0);
        tradeOrderService.doAdd(tradeOrder);
        String url = env.getProperty("open.base.url") + "/trade/pay/dealPayOrder";
        url += "?orderNo=" + tradeOrder.getOrderNo() + "&commID=" + tradeOrder.getChannelMerId() + "&reqData=123";
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
    @RequestMapping(value = "/getOrderInfo")
    @ApiOperation(value = "获取商户编号")
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
    /**
     * 二维码扫码后跳转到聚惠分平台
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/dealPayOrder")
    @ApiOperation(value = "充值跳转到聚惠分平台进行支付")
    public String dealPayOrder(@ApiParam(value = "请求参数") String reqData) {
        String orderNo = "";
        String commID = "";
        TradeOrderDO tradeOrderDO = tradeOrderService.queryOneByOrderId(orderNo);
        String url = env.getProperty("open.base.url") + "/pay/dealPayFail.html";
        if (null != tradeOrderDO) {
            Integer handleNum = tradeOrderDO.getHandleNum();
            if (null == handleNum || handleNum == 0) {
                url = env.getProperty("jhf.open.api.url") + "/api/thirdPay/dealPayOrder";
                url += "?commID=" + tradeOrderDO.getChannelMerId() + "&reqData=" + tradeOrderService.getReqData(tradeOrderDO);
                TradeOrderDO tradeOrderTemp = new TradeOrderDO();
                tradeOrderTemp.setId(tradeOrderDO.getId());
                tradeOrderTemp.setHandleNum(1);
                tradeOrderService.doUpdate(tradeOrderTemp);
            }
        }
        logger.error("分闪付跳转到聚惠分平台前的url" + url);
        return "redirect:" + url;
    }
}
