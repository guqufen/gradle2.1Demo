package net.fnsco.web.controller.e789.recharge;

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
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.api.merchant.MerchantService;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.dby.AESUtil;
import net.fnsco.core.utils.dby.JHFMd5Util;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.constant.ConstantEnum;
import net.fnsco.trading.comm.TradeConstants;
import net.fnsco.trading.service.order.TradeOrderResearchService;
import net.fnsco.trading.service.order.TradeOrderService;
import net.fnsco.trading.service.order.dto.OrderDTO;
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
@RequestMapping(value = "/app2c/rechange")
@Api(value = "/app2c/rechange", tags = { "我的-钱包-充值相关接口" })
public class RechangeController extends BaseController {
    @Autowired
    private MerchantService           merchantService;
    @Autowired
    private TradeOrderService         tradeOrderService;
    @Autowired
    private Environment               env;
    @Autowired
    private TradeOrderResearchService tradeOrderResearchService;

    /**
     * 获取聚惠分url，不生成二维码，个人充值使用的是固定秘钥
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/jhf/getQRUrl", method = RequestMethod.POST)
    @ApiOperation(value = "我的页面-钱包-分闪付充值保存，返回的url跳转到h5页面")
    public ResultDTO<GetQRUrlResultVO> getQRUrl(@RequestBody GetQRUrlJO getQRUrlJO) {
        String innerCode = env.getProperty("rechange.fsf.fns.innerCode");
        //根据用户id获取绑定的分闪付商户信息
        MerchantChannel merchantChannelJhf = merchantService.getMerChannelByMerChannelInnerCodeType(innerCode, "04");
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
        //00pos机01app02台码
        tradeOrder.setPayMedium(TradeConstants.PayMediumEnum.APP.getCode());
        tradeOrder.setTxnType(1);
        //交易子类型10购买消费11充值消费20购买撤销21充值撤销
        tradeOrder.setTxnSubType(TradeConstants.TxnSubTypeEnum.INCOME_RESEARCH.getCode());
        tradeOrder.setTxnSubType(11);//交易子类型10购买消费11充值消费20购买撤销21充值撤销
        tradeOrder.setRespCode(ConstantEnum.RespCodeEnum.HANDLING.getCode());
        tradeOrder.setSyncStatus(0);
        tradeOrderService.research(tradeOrder);
        String url = env.getProperty("app.base.url") + "/trade/fsf/rechange/dealPayOrder";
        url += "?commID=&reqData=" + getReqData(tradeOrder.getOrderNo());
        GetQRUrlResultVO result = new GetQRUrlResultVO();
        result.setUrl(url);
        result.setOrderNo(tradeOrder.getOrderNo());
        //，通知地址，回调地址。
        return success(result);
    }

    private String getReqData(String orderNo) {
        String reqData = "";
        String transTime = DateUtils.dateFormat1ToStr(new Date());
        Map<String, String> result = Maps.newHashMap();
        result.put("orderNo", orderNo);
        result.put("transTime", transTime);
        String singDataStr = orderNo + transTime;
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

    /**
     * 获取聚惠分url，不生成二维码，个人充值使用的是固定秘钥
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/test/research", method = RequestMethod.POST)
    @ApiOperation(value = "我的页面-钱包-分闪付充值保存，返回的url跳转到h5页面")
    public ResultDTO research(@RequestBody OrderDTO order) {
        return tradeOrderResearchService.updateResearchOrderInfo(order);
    }
}
