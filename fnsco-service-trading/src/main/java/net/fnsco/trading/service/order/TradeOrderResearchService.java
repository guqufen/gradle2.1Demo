package net.fnsco.trading.service.order;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // 查询
    public TradeOrderDO queryByOrderId(String orderNo) {
        TradeOrderDO obj = this.tradeOrderDAO.queryByOrderId(orderNo);
        return obj;
    }

    /**
     * 
     * updateOrderInfo:(充值订单更新)
     *
     * @param order
     * @return   ResultDTO    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public ResultDTO updateResearchOrderInfo(OrderDTO order) {
        TradeOrderDO tradeOrderDO = queryByOrderId(order.getThirdPayNo());
        if (null == tradeOrderDO) {
            logger.error("订单不存在" + JSON.toJSONString(tradeOrderDO));
            return ResultDTO.fail("订单不存在");
        }
        String respCode = BigdataConstant.RESP_CODE_MAP.get(order.getOrderStatus());
        if (Strings.isNullOrEmpty(respCode)) {
            respCode = tradeOrderDO.getRespCode();
        }
        tradeOrderDO.setRespCode(respCode);
        tradeOrderDO.setPayOrderNo(order.getSalesOrderNo());
        if (!"0".equals(order.getOrderStatus())) {
            tradeOrderDO.setCompleteTime(new Date());
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
        if (!Strings.isNullOrEmpty(order.getSettlementStatus())) {
            try {
                tradeOrderDO.setSettleStatus(Integer.parseInt(order.getSettlementStatus()));
                if ("1".equals(order.getSettlementStatus())) {
                    tradeOrderDO.setSettleDate(new Date());
                    if (null != order.getSettlementAmount()) {//通知接口未返回结算金额
                        BigDecimal settleAmount = new BigDecimal(order.getSettlementAmount());
                        settleAmount = settleAmount.multiply(new BigDecimal("100"));
                        tradeOrderDO.setSettleAmount(settleAmount);
                    }
                }
            } catch (Exception ex) {
                logger.error("支付完成时回调时结算状态转换为int出错", ex);
            }
        }
        //（0 未支付 1支付成功 2支付失败 3已退货）
        if ("3".equals(order.getOrderStatus()) || "1".equals(order.getSettlementStatus())) {//1已结算
            logger.error("更新分闪付充值状态为失败3已退货或1已结算"+order.getOrderStatus()+order.getSettlementStatus()+JSON.toJSONString(tradeOrderDO));
            tradeOrderDAO.update(tradeOrderDO);
        } else {
            if (null == tradeOrderDO.getOrderCeateTime() && !Strings.isNullOrEmpty(order.getTime())) {//回调时更新订单创建时间
                tradeOrderDO.setOrderCeateTime(DateUtils.toParseYmdhms(order.getTime()));
            }
            Integer i = tradeOrderDAO.updateOnlyFail(tradeOrderDO);
            if (i > 0) {
                if ("1".equals(order.getOrderStatus())) {
                    logger.error("分闪付充值成功" + tradeOrderDO.getOrderNo() + tradeOrderDO.getOrderAmount());
                    TradeWithdrawDO tradeWithdraw = tradeWithdrawService.doQueryByOriginalOrderNo(tradeOrderDO.getOrderNo());
                    tradeWithdraw.setStatus(WithdrawStateEnum.SUCCESS.getCode());
                    tradeWithdrawService.researchForSuccess(tradeWithdraw);
                }
            }
        }
        if ("2".equals(order.getOrderStatus())) {
            TradeWithdrawDO tradeWithdraw = tradeWithdrawService.doQueryByOriginalOrderNo(tradeOrderDO.getOrderNo());
            tradeWithdraw.setStatus(WithdrawStateEnum.FAIL.getCode());
            logger.error("更新分闪付充值状态为失败" + tradeOrderDO.getOrderNo());
            tradeWithdrawService.doUpdate(tradeWithdraw);
        }
        return ResultDTO.success();
    }
}
