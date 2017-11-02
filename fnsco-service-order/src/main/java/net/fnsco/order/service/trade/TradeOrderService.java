package net.fnsco.order.service.trade;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import net.fnsco.core.utils.dby.AESUtil;
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
        tradeOrder.setOrderNo(DateUtils.getNowDateStr() + tradeOrder.getInnerCode() + DbUtil.getRandomStr(3));
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

    // 查询
    public TradeOrderDO queryOneByOrderId(String orderNo) {
        TradeOrderDO obj = this.tradeOrderDAO.queryByOrderId(orderNo);
        if (obj != null && "1000".equals(obj.getRespCode())) {//如果没有重新查询一次
            updateOrderStatues(orderNo);
            obj = this.tradeOrderDAO.queryByOrderId(orderNo);
        }
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
        if (!"0".equals(order.getSettlementStatus())) {
            tradeOrderDO.setCompleteTime(new Date());
        } 
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
            if (null == tradeOrderDO.getOrderCeateTime() && !Strings.isNullOrEmpty(order.getTime())) {//回调时更新订单创建时间
                tradeOrderDO.setOrderCeateTime(DateUtils.toParseYmdhms(order.getTime()));
            }
            doUpdate(tradeOrderDO);
        }
        return ResultDTO.success();
    }

    //定时任务查询订单状态
    public void updateOrderStatues(String orderNoArg) {
        String keyStr = env.getProperty("jhf.api.AES.key");
        List<TradeOrderDO> tradeOrderList = this.tradeOrderDAO.queryAllNotComplete(orderNoArg);
        String url = env.getProperty("jhf.open.api.url") + "/api/thirdPay/queryPayOrderList";
        for (TradeOrderDO order : tradeOrderList) {
            String orderNo = order.getOrderNo();
            Map<String, String> params = Maps.newHashMap();
            params.put("thirdPayNo", orderNo);
            params.put("commID", order.getChannelMerId());
            Map<String, String> param = Maps.newHashMap();
            String args = JSON.toJSONString(params);
            String reqData = "";
            reqData = AESUtil.encode(args, keyStr);
            param.put("reqData", reqData);
            param.put("commID", order.getChannelMerId());
            String resultJson = "";
            try {
                resultJson = HttpUtils.post(url, param);
            } catch (UnsupportedEncodingException e) {
                logger.error("调用聚惠芬查询订单信息时出错", e);
            }
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
            if (!Strings.isNullOrEmpty(resultJson)) {
                try {
                    JSONObject jsonObj = JSON.parseObject(resultJson);
                    String rspDataStr = jsonObj.getString("rspData");
                    String decodeStr = AESUtil.decode(rspDataStr, keyStr);
                    List<OrderDTO> orderDtoList = JSON.parseArray(decodeStr, OrderDTO.class);
                    if (null != orderDtoList) {
                        for (OrderDTO dto : orderDtoList) {
                            if (null != dto) {
                                updateOrderInfo(dto);
                            }
                        }
                    }
                } catch (Exception ex) {
                    logger.error("定时更新分期付状态数据出错", ex);
                }
            }
        }
    }

    //定时同步分闪付交易成功数据
    public void syncOrderTradeData() {
        List<TradeOrderDO> tradeOrderList = this.tradeOrderDAO.queryAllNotSyncDate();
        for (TradeOrderDO order : tradeOrderList) {
            saveTradeData(order);
        }
    }

    @Transactional
    public void saveTradeData(TradeOrderDO order) {
        TradeDataDTO tradeData = new TradeDataDTO();
        tradeData.setMerId(order.getChannelMerId());
        tradeData.setChannelType(order.getChannelType());
        tradeData.setInnerCode(order.getInnerCode());
        tradeData.setAmt(order.getTxnAmount().toString());
        tradeData.setOrderNo(order.getOrderNo());
        tradeData.setOrderTime(DateUtils.dateFormat1ToStr(order.getOrderCeateTime()));
        tradeData.setOrderInfo("");
        tradeData.setTimeStamp(DateUtils.dateFormat1ToStr(order.getCompleteTime()));
        tradeData.setTradeDetail("");
        tradeData.setTermId("");
        tradeData.setBatchNo("");
        tradeData.setSysTraceNo("");
        tradeData.setAuthCode("");
        tradeData.setOrderIdScan("");
        tradeData.setSource("02");//来源00拉卡拉01导入02同步03法奈昇04浦发
        tradeData.setSendTime(DateUtils.dateFormat1ToStr(order.getCreateTime()));
        tradeData.setPayType(order.getPayType());
        tradeData.setPaySubType(order.getPaySubType());
        tradeData.setReferNo("");
        tradeData.setChannelTermCode("");
        tradeData.setRespCode(order.getRespCode());
        tradeData.setCardNo("");
        tradeData.setCardOrg("");
        tradeData.setTxnType(String.valueOf(order.getTxnType()));
        tradeData.setPayMedium("00");//支付媒介00pos机01app02台码
        tradeData.setMd5(DbUtil.MD5(JSON.toJSONString(tradeData)));
        tradeDataService.saveTradeData(tradeData);
        order.setSyncStatus(1);//已同步数据
        tradeOrderDAO.update(order);
    }
}