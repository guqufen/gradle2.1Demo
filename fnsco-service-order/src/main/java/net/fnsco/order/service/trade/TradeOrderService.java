package net.fnsco.order.service.trade;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.beust.jcommander.internal.Maps;
import com.google.common.base.Strings;

import net.fnsco.bigdata.api.dto.TradeDataDTO;
import net.fnsco.bigdata.api.trade.TradeDataService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.DbUtil;
import net.fnsco.core.utils.HttpUtils;
import net.fnsco.order.api.constant.ConstantEnum;
import net.fnsco.order.api.dto.OrderDTO;
import net.fnsco.order.service.trade.dao.TradeOrderDAO;
import net.fnsco.order.service.trade.entity.TradeOrderDO;

@Service
public class TradeOrderService extends BaseService {

    private Logger           logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TradeOrderDAO    tradeOrderDAO;
    @Autowired
    private Environment      env;
    @Autowired
    private TradeDataService tradeDataService;

    // 分页
    public ResultPageDTO<TradeOrderDO> page(TradeOrderDO tradeOrder, Integer pageNum, Integer pageSize) {
        logger.info("开始分页查询TradeOrderService.page, tradeOrder=" + tradeOrder.toString());
        List<TradeOrderDO> pageList = this.tradeOrderDAO.pageList(tradeOrder, pageNum, pageSize);
        Integer count = this.tradeOrderDAO.pageListCount(tradeOrder);
        ResultPageDTO<TradeOrderDO> pager = new ResultPageDTO<TradeOrderDO>(count, pageList);
        return pager;
    }

    // 添加
    public TradeOrderDO doAdd(TradeOrderDO tradeOrder) {
        logger.info("开始添加TradeOrderService.add,tradeOrder=" + tradeOrder.toString());
        tradeOrder.setCreateTime(new Date());
        //tradeOrder.setOrderCeateTime(new Date());
        tradeOrder.setOrderNo(DateUtils.getNowDateStr() + tradeOrder.getMercId() + DbUtil.getRandomStr(3));
        this.tradeOrderDAO.insert(tradeOrder);
        return tradeOrder;
    }

    // 修改
    public Integer doUpdate(TradeOrderDO tradeOrder) {
        logger.info("开始修改TradeOrderService.update,tradeOrder=" + tradeOrder.toString());
        int rows = this.tradeOrderDAO.update(tradeOrder);
        return rows;
    }

    // 删除
    public Integer doDelete(TradeOrderDO tradeOrder, Integer loginUserId) {
        logger.info("开始删除TradeOrderService.delete,tradeOrder=" + tradeOrder.toString());
        int rows = this.tradeOrderDAO.deleteById(tradeOrder.getId());
        return rows;
    }

    // 查询
    public TradeOrderDO doQueryById(Integer id) {
        TradeOrderDO obj = this.tradeOrderDAO.getById(id);
        return obj;
    }

    // 查询
    public TradeOrderDO queryByOrderId(String orderNo) {
        TradeOrderDO obj = this.tradeOrderDAO.queryByOrderId(orderNo);
        return obj;
    }

    public TradeOrderDO doQueryByPayOrderNo(String salesOrderNo) {
        TradeOrderDO obj = this.tradeOrderDAO.queryBySalesOrderNo(salesOrderNo);
        return obj;
    }

    public ResultDTO updateOrderInfo(OrderDTO order) {
        //String payCallBackParams = order.getPayCallBackParams();
        //TradeOrderDO tradeOrderTemp = JSON.parseObject(payCallBackParams, TradeOrderDO.class);
        //String orderNo = tradeOrderTemp.getOrderNo();
        TradeOrderDO tradeOrderDO = queryByOrderId(order.getThirdPayNo());
        if (null == tradeOrderDO) {
            logger.error("订单不存在" + JSON.toJSONString(tradeOrderDO));
            return ResultDTO.fail("订单不存在");
        }
        //      orderStatus 订单状态    （0 未支付 1支付成功 2支付失败 3已退货）
        String respCode = ConstantEnum.RESP_CODE_MAP.get(order.getOrderStatus());
        if (Strings.isNullOrEmpty(respCode)) {
            respCode = tradeOrderDO.getRespCode();
        }
        tradeOrderDO.setRespCode(respCode);
        tradeOrderDO.setPayOrderNo(order.getSalesOrderNo());

        //结算状态（0 未结算 1已结算   2结算中   3已退款）
        if (Strings.isNullOrEmpty(order.getSettlementStatus())) {
            try {
                tradeOrderDO.setSettleStatus(Integer.parseInt(order.getSettlementStatus()));
                tradeOrderDO.setSettleDate(new Date());
            } catch (Exception ex) {
                logger.error("支付完成时回调时结算状态转换为int出错", ex);
            }
        }
        if ("3".equals(order.getSettlementStatus())) {//3已退货
            tradeOrderDO.setId(null);
            tradeOrderDO.setTxnType(2);
            tradeOrderDO.setCreateTime(new Date());
            doAdd(tradeOrderDO);
        } else {
            if (null != order.getOrderCeateTime()) {//回调时更新订单创建时间
                tradeOrderDO.setOrderCeateTime(order.getOrderCeateTime());
            }
            doUpdate(tradeOrderDO);
        }
        return ResultDTO.success();
    }

    //定时任务查询订单状态
    public void updateOrderStatues() {
        List<TradeOrderDO> tradeOrderList = this.tradeOrderDAO.queryAllNotComplete();
        String url = env.getProperty("jhf.open.api.url");
        for (TradeOrderDO order : tradeOrderList) {
            String orderNo = order.getOrderNo();
            Map<String, String> params = Maps.newHashMap();
            params.put("salesOrderNo", orderNo);
            String resultJson = HttpUtils.get(url, params);
            //thirdPayNo  订单号
            //commId  商户ID
            //payAmount   支付金额
            //npr 分期期数
            //commercialRate  商户费率
            //cardHolderRate  持卡人费率
            //settlementAmount    结算金额
            //orderStatus 订单状态
            //settlementStatus    结算状态
            //payCallBackParams   商户上送参数

            OrderDTO orderDto = JSON.parseObject(resultJson, OrderDTO.class);
            updateOrderInfo(orderDto);
        }
    }

    //定时同步分闪付交易成功数据
    public void syncOrderTradeData() {
        List<TradeOrderDO> tradeOrderList = this.tradeOrderDAO.queryAllNotSyncDate();
        for (TradeOrderDO order : tradeOrderList) {
            TradeDataDTO tradeData = new TradeDataDTO();
            tradeDataService.saveTradeData(tradeData);
        }
    }
}