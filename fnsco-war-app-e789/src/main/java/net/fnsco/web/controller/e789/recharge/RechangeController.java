package net.fnsco.web.controller.e789.recharge;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.api.merchant.MerchantService;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.constant.ConstantEnum;
import net.fnsco.trading.service.order.TradeOrderService;
import net.fnsco.trading.service.order.entity.TradeOrderDO;
import net.fnsco.web.controller.e789.jo.GetQRUrlJO;
import net.fnsco.web.controller.e789.vo.GetQRUrlResultVO;
/**
 * 
 * @desc e789中的分闪付充值相关功能
 * @author   sxf
 * @version  
 * @since    Ver 1.1
 * @Date     2017年12月05日 上午11:53:16
 *
 */
@RestController
@RequestMapping(value = "/open2c/trade/jhf", method = RequestMethod.POST)
@Api(value = "/open2c/trade/jhf", tags = { "e789中的分闪付支付接口" })
public class RechangeController extends BaseController {
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
    public ResultDTO<GetQRUrlResultVO> getQRUrl(@RequestParam GetQRUrlJO getQRUrlJO) {
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

}
