package net.fnsco.web.controller.e789.recharge;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.alipay.AlipayAppPayRequestParams;
import net.fnsco.core.alipay.AlipayClientUtil;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.dby.AESUtil;
import net.fnsco.core.utils.dby.JHFMd5Util;
import net.fnsco.trading.comm.TradeConstants;
import net.fnsco.trading.comm.TradeConstants.WithdrawStateEnum;
import net.fnsco.trading.service.order.TradeOrderResearchService;
import net.fnsco.trading.service.order.dto.OrderDTO;
import net.fnsco.trading.service.withdraw.TradeWithdrawService;
import net.fnsco.trading.service.withdraw.entity.TradeWithdrawDO;
import net.fnsco.web.controller.e789.jo.AlipayRechargeJO;
import net.fnsco.web.controller.e789.jo.GetQRUrlJO;
import net.fnsco.web.controller.e789.vo.AlipayAppPayParamsVO;
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
    private Environment               env;
    @Autowired
    private TradeOrderResearchService tradeOrderResearchService;
    @Autowired
    private TradeWithdrawService      tradeWithdrawService;
    
    private static final String RECHANGE_NOTIFY_URL = "/trade/alipay/appPayNotify";//支付宝充值回调

    /**
     * 获取聚惠分url，不生成二维码，个人充值使用的是固定秘钥
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/jhf/getQRUrl", method = RequestMethod.POST)
    @ApiOperation(value = "我的页面-钱包-分闪付充值保存，返回的url跳转到h5页面")
    public ResultDTO<GetQRUrlResultVO> getQRUrl(@RequestBody GetQRUrlJO getQRUrlJO) {
        String channelMerId = env.getProperty("rechange.fsf.channel.merid");
        if (Strings.isNullOrEmpty(getQRUrlJO.getPaymentAmount())) {
            return ResultDTO.fail("充值金额不能为空");
        }
        BigDecimal amountB = new BigDecimal(getQRUrlJO.getPaymentAmount());
        BigDecimal amountBs = amountB.multiply(new BigDecimal("100"));
        if (amountBs.compareTo(new BigDecimal("65000")) < 0) {
            return ResultDTO.fail("充值金额不能低于650元");
        }
        if (amountBs.compareTo(new BigDecimal("4000000")) > 0) {
            return ResultDTO.fail("充值金额不能高于40000元");
        }
        TradeWithdrawDO tradeWithdraw = new TradeWithdrawDO();
        tradeWithdraw.setAppUserId(getQRUrlJO.getUserId());
        tradeWithdraw.setAmount(amountBs);
        tradeWithdraw.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());
        tradeWithdraw.setTradeType(TradeConstants.TradeTypeEnum.INCOME.getCode());
        tradeWithdraw.setStatus(WithdrawStateEnum.INIT.getCode());
        tradeWithdraw.setFee(BigDecimal.ZERO);
        tradeWithdraw.setChannelMerId(channelMerId);
        tradeWithdraw.setChannelType(TradeConstants.ChannelTypeEnum.JHF_PAY.getCode());
        tradeWithdraw.setTradeSubType(TradeConstants.TxnSubTypeEnum.INCOME_RESEARCH.getCode());
        tradeWithdraw.setTradeType(TradeConstants.TradeTypeEnum.INCOME.getCode());
        tradeWithdraw.setInstallmentNum(getQRUrlJO.getInstallmentNum());
        tradeWithdrawService.doAdd(tradeWithdraw);

        String url = env.getProperty("app.base.url") + "/trade/fsf/rechange/dealPayOrder";
        url += "?commID=&reqData=" + getReqData(tradeWithdraw.getOrderNo());
        GetQRUrlResultVO result = new GetQRUrlResultVO();
        result.setUrl(url);
        result.setOrderNo(tradeWithdraw.getOrderNo());
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

    /**
     * alipayPayMent:(支付宝充值)
     *
     * @param  @param alipayRechargeJO
     * @param  @return    设定文件
     * @return ResultDTO<String>    DOM对象
     * @author tangliang
     * @date   2018年2月1日 下午3:16:09
     */
    @PostMapping(value = "/alipay")
    @ApiOperation(value = "我的页面-钱包-支付宝充值",notes = "tangliang")
    public ResultDTO<AlipayAppPayParamsVO> alipayPayMent(@RequestBody AlipayRechargeJO alipayRechargeJO){
    	
    	if (Strings.isNullOrEmpty(alipayRechargeJO.getPaymentAmount())) {
            return ResultDTO.fail("充值金额不能为空");
        }
        BigDecimal amountB = new BigDecimal(alipayRechargeJO.getPaymentAmount());
        BigDecimal amountBs = amountB.multiply(new BigDecimal("100"));
        if (amountBs.compareTo(new BigDecimal("0")) < 0) {
            return ResultDTO.fail("充值金额不能低于0元");
        }
    	
        /**
         * 保存订单情况
         */
        String notifyUrl = env.getProperty("app.base.url")+RECHANGE_NOTIFY_URL;
        TradeWithdrawDO tradeWithdraw = new TradeWithdrawDO();
        tradeWithdraw.setAppUserId(alipayRechargeJO.getUserId());
        tradeWithdraw.setAmount(amountBs);
        tradeWithdraw.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());
        tradeWithdraw.setTradeType(TradeConstants.TradeTypeEnum.INCOME.getCode());
        tradeWithdraw.setStatus(WithdrawStateEnum.INIT.getCode());
        tradeWithdraw.setFee(BigDecimal.ZERO);
        tradeWithdraw.setBackUrl(notifyUrl);
        tradeWithdraw.setChannelType(TradeConstants.ChannelTypeEnum.ALI_PAY.getCode());
        tradeWithdraw.setTradeSubType(TradeConstants.TxnSubTypeEnum.INCOME_RESEARCH.getCode());
        tradeWithdraw.setTradeType(TradeConstants.TradeTypeEnum.INCOME.getCode());
        tradeWithdraw.setCreateTime(new Date());
        tradeWithdraw.setOrderAmount(amountBs);
        tradeWithdrawService.doAdd(tradeWithdraw);

        
        /**
         * 支付宝下单，将参数返回给app端
         */
        AlipayAppPayRequestParams requestParams = new AlipayAppPayRequestParams();
        requestParams.setBody("e789帐号充值");
        requestParams.setSubject("e789帐号充值");
        requestParams.setTotalAmount(String.format("%.2f", amountB.doubleValue()));
        requestParams.setOutTradeNo(tradeWithdraw.getOrderNo());
        requestParams.setNotifyUrl(notifyUrl);
        String body = AlipayClientUtil.createPayOrderParams(requestParams);
        
        AlipayAppPayParamsVO param = new AlipayAppPayParamsVO();
        param.setParams(body);
    	return ResultDTO.success(param);
    }
}
