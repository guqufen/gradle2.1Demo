package net.fnsco.web.controller.open;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DbUtil;
import net.fnsco.core.utils.dby.AESUtil;
import net.fnsco.order.api.dto.OrderDTO;
import net.fnsco.order.service.trade.TradeOrderService;

/**
 * 
 * @desc 聚惠分分期付相关产品功能
 * @author   sxf
 * @version  
 * @since    Ver 1.1
 * @Date	 2017年10月27日 上午11:53:16
 *
 */
@RestController
@RequestMapping(value = "/trade/jhf", method = RequestMethod.POST)
@Api(value = "/trade/jhf", tags = { "聚惠芬回调接口" })
public class TradeCallBackController extends BaseController {

    @Autowired
    private TradeOrderService tradeOrderService;
    @Autowired
    private Environment       env;

    //    thirdPayNo  订单号
    //    salesOrderNo    订单号 
    //    orderStatus 订单状态    （0 未支付 1支付成功 2支付失败 3已退货）
    //    settlementStatus    结算状态    （0 未结算 1已结算   2结算中   3已退款）
    //    payCallBackParams   商户上送参数  
    //    singData
    /**
     * 支付完成时的通知，显示我们自己的页面
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/payCompleteNotice")
    @ApiOperation(value = "支付完成时的通知")
    public ResultDTO payCompleteNotice(@RequestBody String rspData) {
        logger.error("聚惠芬支付完成时的通知密文入参：" + rspData);
        String keyStr =env.getProperty("jhf.api.AES.key");
        String decodeStr = AESUtil.decode(rspData, keyStr);
        logger.error("聚惠芬支付完成时的通知解密后入参：" + decodeStr);
        OrderDTO order = JSON.parseObject(decodeStr, OrderDTO.class);
        //        OrderDTO order = new OrderDTO();
        //        order.setThirdPayNo(thirdPayNo);
        //        order.setSalesOrderNo(salesOrderNo);
        //        order.setOrderStatus(orderStatus);
        //        order.setSettlementStatus(settlementStatus);
        //        order.setPayCallBackParams(payCallBackParams);
        ResultDTO result = tradeOrderService.updateOrderInfo(order);
        return success();
    }

    /**
     * 支付完成时的回调
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/payCompleteCallback")
    @ApiOperation(value = "支付完成时的回调")
    public ResultDTO payCompleteCallback(String thirdPayNo, String salesOrderNo, String orderStatus, String settlementStatus, String payCallBackParams) {
        //md5校验
        String singDataStr = thirdPayNo + salesOrderNo + orderStatus + settlementStatus + payCallBackParams + "78d496a2e2ba419d8e4f90af0431c763";
        String md5Str = DbUtil.MD5(singDataStr);
        String url = env.getProperty("jhf.qr.pay.url");
        //保存订单信息
        //成功或失败则同步到交易流水信息
        OrderDTO order = new OrderDTO();
        order.setThirdPayNo(thirdPayNo);
        order.setSalesOrderNo(salesOrderNo);
        order.setOrderStatus(orderStatus);
        order.setSettlementStatus(settlementStatus);
        order.setPayCallBackParams(payCallBackParams);
        order.setOrderCeateTime(new Date());
        logger.error("聚惠芬支付完成时的回调入参：" + JSON.toJSONString(order));
        ResultDTO result = tradeOrderService.updateOrderInfo(order);
        return success(result);
    }
}
