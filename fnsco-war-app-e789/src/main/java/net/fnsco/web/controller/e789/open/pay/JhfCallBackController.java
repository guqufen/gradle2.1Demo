package net.fnsco.web.controller.e789.open.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.dby.AESUtil;
import net.fnsco.trading.service.order.TradeOrderResearchService;
import net.fnsco.trading.service.order.TradeOrderService;
import net.fnsco.trading.service.order.dto.OrderDTO;
import net.fnsco.trading.service.order.entity.TradeOrderDO;

/**
 * 
 * @desc 聚惠分分期付相关产品功能
 * @author   sxf
 * @version  
 * @since    Ver 1.1
 * @Date	 2017年10月27日 上午11:53:16
 *
 */
@Controller
@RequestMapping(value = "/trade/jhf", method = RequestMethod.POST)
@Api(value = "/trade/jhf", tags = { "聚惠分回调接口" })
public class JhfCallBackController extends BaseController {

    @Autowired
    private TradeOrderService         tradeOrderService;
    @Autowired
    private TradeOrderResearchService tradeOrderResearchService;
    @Autowired
    private Environment               env;

    /**
    * 支付完成时的通知，显示我们自己的页面
    *
    * @param userName
    * @return
    */
    @RequestMapping(value = "/pay/payCompleteNotice")
    @ApiOperation(value = "支付完成时的通知")
    @ResponseBody
    public ResultDTO payCompleteNotice(String rspData) {
        logger.error("聚惠分支付完成时的通知密文入参：" + rspData);
        String keyStr = env.getProperty("jhf.api.AES.key");
        String result = "处理成功";
        if (!Strings.isNullOrEmpty(rspData)) {
            try {
                String decodeStr = AESUtil.decode(rspData, keyStr);
                logger.error("聚惠分支付完成时的通知解密后入参：" + decodeStr);
                //聚惠芬支付完成时的通知解密后入参：{"payCallBackParams":"","settlementStatus":"0","thirdPayNo":"20171102155606073111374535549766","orderStatus":"2","singData":"001AA0CC08241A447BF7250B500C4B83"}
                OrderDTO order = JSON.parseObject(decodeStr, OrderDTO.class);
                return tradeOrderService.updateOrderInfo(order);
            } catch (Exception ex) {
                logger.error("聚惠分支付完成时的通知更新出错", ex);
                result = "处理失败，业务处理异常";
            }
        } else {
            logger.error("聚惠分支付完成时的通知密文入参为空");
            result = "处理失败，入参为空";
        }
        return fail(result);
    }

    /**
     * 支付完成时的回调页面
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/pay/payCompleteCallback", method = RequestMethod.GET)
    @ApiOperation(value = "支付页面完成时的回调")
    public String payCompleteCallback(String orderNo) {
        String url = env.getProperty("web.base.url");
        logger.error("聚惠芬支付页面完成时的回调入参：" + orderNo);
        TradeOrderDO order = tradeOrderService.queryOneByOrderId(orderNo);
        //分期数
        //支付总金额
        if (null == order) {
            order = new TradeOrderDO();
        }
        //status = ConstantEnum.RespCodeEnum.getNameByCode(order.getRespCode());
        return "redirect:" + url + "/pay/jhfPayCallback.html?paymentAmount=" + order.getTxnAmount() + "&installmentNum=" + order.getInstallmentNum() + "&orderDate="
               + DateUtils.dateFormat1ToStr(order.getCreateTime()) + "&status=" + order.getRespCode();
    }

    /**
     * 充值完成时的通知，显示我们自己的页面
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/rechange/payCompleteNotice")
    @ApiOperation(value = "充值完成时的通知")
    @ResponseBody
    public ResultDTO rechangePayCompleteNotice(String rspData) {
        logger.error("聚惠分充值完成时的通知密文入参：" + rspData);
        String keyStr = env.getProperty("jhf.api.AES.key");
        String result = "处理成功";
        if (!Strings.isNullOrEmpty(rspData)) {
            try {
                String decodeStr = AESUtil.decode(rspData, keyStr);
                logger.error("聚惠分充值完成时的通知解密后入参：" + decodeStr);
                //聚惠芬支付完成时的通知解密后入参：{"payCallBackParams":"","settlementStatus":"0","thirdPayNo":"20171102155606073111374535549766","orderStatus":"2","singData":"001AA0CC08241A447BF7250B500C4B83"}
                OrderDTO order = JSON.parseObject(decodeStr, OrderDTO.class);
                return tradeOrderResearchService.updateResearchOrderInfo(order);
            } catch (Exception ex) {
                logger.error("聚惠分充值完成时的通知更新出错", ex);
                result = "处理失败，业务处理异常";
            }
        } else {
            logger.error("聚惠分支付完成时的通知密文入参为空");
            result = "处理失败，入参为空";
        }
        return fail(result);
    }

    /**
     * 充值完成时的回调页面
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/rechange/payCompleteCallback", method = RequestMethod.GET)
    @ApiOperation(value = "充值页面完成时的回调")
    public String rechangePayCompleteCallback(String orderNo) {
        String url = env.getProperty("web.base.url");
        logger.error("聚惠芬充值页面完成时的回调入参：" + orderNo);
        TradeOrderDO order = tradeOrderService.queryOneByOrderId(orderNo);
        //分期数
        //支付总金额
        if (null == order) {
            order = new TradeOrderDO();
        }
        //status = ConstantEnum.RespCodeEnum.getNameByCode(order.getRespCode());
        return "redirect:" + url + "/pay/jhfPayCallback.html?paymentAmount=" + order.getTxnAmount() + "&installmentNum=" + order.getInstallmentNum() + "&orderDate="
               + DateUtils.dateFormat1ToStr(order.getCreateTime()) + "&status=" + order.getRespCode();
    }
}
