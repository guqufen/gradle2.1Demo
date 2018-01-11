package net.fnsco.trading.service.order;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;

import net.fnsco.bigdata.api.constant.BigdataConstant;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.trading.comm.TradeConstants.WithdrawStateEnum;
import net.fnsco.trading.service.order.dao.TradeOrderDAO;
import net.fnsco.trading.service.order.dto.OrderDTO;
import net.fnsco.trading.service.order.entity.TradeOrderDO;
import net.fnsco.trading.service.withdraw.TradeWithdrawService;
import net.fnsco.trading.service.withdraw.entity.TradeWithdrawDO;

/**
 * 
 * @desc 个人分散付充值相关接口
 * @author   sxf
 * @version  
 * @since    Ver 1.1
 * @Date	 2017年12月20日 上午10:53:52
 *
 */
@Service
public class TradeOrderResearchService extends BaseService {
    @Autowired
    private TradeOrderDAO        tradeOrderDAO;
    @Autowired
    private TradeWithdrawService tradeWithdrawService;

    /**
     * 
     * updateOrderInfo:(充值订单更新)
     *
     * @param order
     * @return   ResultDTO    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @Transactional
    public ResultDTO updateResearchOrderInfo(OrderDTO order) {
        TradeWithdrawDO tradeOrderDO = tradeWithdrawService.queryOneByOrderId(order.getThirdPayNo());
        if (null == tradeOrderDO) {
            logger.error("订单不存在" + JSON.toJSONString(tradeOrderDO));
            return ResultDTO.fail("订单不存在");
        }
        String respCode = BigdataConstant.RESP_CODE_MAP.get(order.getOrderStatus());
        if (Strings.isNullOrEmpty(respCode)) {
            respCode = tradeOrderDO.getRespCode();
        }
        tradeOrderDO.setRespCode(respCode);
        tradeOrderDO.setOriginalOrderNo(order.getSalesOrderNo());
        if (!"0".equals(order.getOrderStatus())) {
            tradeOrderDO.setSuccTime(DateUtils.getNowDateStr());
        }
        //（0 未支付 1支付成功 2支付失败 3已退货）
        if ("1".equals(order.getOrderStatus())) {
            BigDecimal orderAmountB = new BigDecimal("0");
            BigDecimal eachMoneyB = new BigDecimal("0");
            try {
                orderAmountB = new BigDecimal(order.getOrderAmount());
                orderAmountB = orderAmountB.multiply(new BigDecimal("100"));
                eachMoneyB = new BigDecimal(order.getEachMoney());
                eachMoneyB = eachMoneyB.multiply(new BigDecimal("100"));
            } catch (Exception ex) {
                logger.error("聚惠分支付完成，返回金额数据转换出错", ex);
            }
            tradeOrderDO.setOrderAmount(orderAmountB);
            tradeOrderDO.setEachMoney(eachMoneyB);
            tradeOrderDO.setCardHolderRate(order.getCardHolderRate());
        }
        //结算状态（0 未结算 1已结算   2结算中   3已退款）
        //（0 未支付 1支付成功 2支付失败 3已退货）
        if ("3".equals(order.getOrderStatus())) {//1已结算
            logger.error("更新分闪付充值状态为失败3已退货" + order.getOrderStatus() + order.getSettlementStatus() + JSON.toJSONString(tradeOrderDO));
            tradeWithdrawService.doUpdate(tradeOrderDO);
        } else {
            Integer i = tradeWithdrawService.updateOnlyFail(tradeOrderDO);
            if (i > 0) {
                if ("1".equals(order.getOrderStatus())) {
                    logger.error("分闪付充值成功" + tradeOrderDO.getOrderNo() + tradeOrderDO.getOrderAmount());
                    tradeOrderDO.setStatus(WithdrawStateEnum.SUCCESS.getCode());
                    tradeWithdrawService.researchForSuccess(tradeOrderDO);
                }
            }
        }
        if ("2".equals(order.getOrderStatus())) {
            TradeWithdrawDO tradeOrderTemp = new TradeWithdrawDO();
            tradeOrderTemp.setId(tradeOrderDO.getId());
            tradeOrderTemp.setStatus(WithdrawStateEnum.FAIL.getCode());
            logger.error("更新分闪付充值状态为失败" + tradeOrderDO.getOrderNo());
            tradeWithdrawService.doUpdate(tradeOrderTemp);
        }
        return ResultDTO.success();
    }
}
