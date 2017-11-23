package net.fnsco.trading.service.order;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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

import net.fnsco.bigdata.api.constant.BigdataConstant;
import net.fnsco.bigdata.api.dto.TradeDataDTO;
import net.fnsco.bigdata.api.trade.TradeDataService;
import net.fnsco.bigdata.service.sys.SequenceService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.DbUtil;
import net.fnsco.core.utils.HttpUtils;
import net.fnsco.core.utils.dby.AESUtil;
import net.fnsco.core.utils.dby.JHFMd5Util;
import net.fnsco.trading.service.order.dao.TradeOrderDAO;
import net.fnsco.trading.service.order.dto.OrderDTO;
import net.fnsco.trading.service.order.dto.TradeJhfJO;
import net.fnsco.trading.service.order.entity.TradeOrderDO;

@Service
public class TradeOrderService extends BaseService {

    private Logger           logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TradeOrderDAO    tradeOrderDAO;
    @Autowired
    private Environment      env;
    @Autowired
    private TradeDataService tradeDataService;
    @Autowired
    private SequenceService  sequenceService;

    public String getReqData(TradeOrderDO tradeOrder) {
        String keyStr = env.getProperty("jhf.api.AES.key");
        String payNotifyUrl = env.getProperty("open.base.url") + "/trade/jhf/payCompleteNotice";
        String payCallBackUrl = env.getProperty("open.base.url") + "/trade/jhf/payCompleteCallback?orderNo=" + tradeOrder.getOrderNo();
        //        commID  商户Id
        //        thirdPayNo  订单号
        //        payAmount   支付金额
        //        npr 分期数
        //        unionId 客户ID
        //        transTime   交易时间
        //        payNotifyUrl    通知URL
        //        payCallBackUrl  支付结束的回调URL
        //        payCallBackParams   支付成功后通知参数
        //        singData    MD5签名
        String createTimerStr = DateUtils.dateFormat1ToStr(tradeOrder.getCreateTime());
        String payCallBackParams = JSON.toJSONString(tradeOrder);
        //MD5(商户Id+订单号+支付金额+分期数+交易时间+通知URL+回调URL+通知参数)
        BigDecimal amountTemp = tradeOrder.getTxnAmount();
        BigDecimal amountTemps = amountTemp.divide(new BigDecimal("100"));
        String singDataStr = tradeOrder.getChannelMerId() + tradeOrder.getOrderNo() + amountTemps.toString() + String.valueOf(tradeOrder.getInstallmentNum()) + createTimerStr + payNotifyUrl
                             + payCallBackUrl;
        logger.error("签名前数据" + singDataStr);
        String singData = JHFMd5Util.encode32(singDataStr);
        logger.error("签名" + singData);
        TradeJhfJO jhfJO = new TradeJhfJO();
        jhfJO.setCommID(tradeOrder.getChannelMerId());
        jhfJO.setPeriodNum(String.valueOf(tradeOrder.getInstallmentNum()));

        jhfJO.setPayAmount(amountTemps.toString());
        jhfJO.setPayCallBackParams("");
        jhfJO.setPayCallBackUrl(payCallBackUrl);//payCallBackUrl
        jhfJO.setPayNotifyUrl(payNotifyUrl);
        jhfJO.setSingData(singData);
        jhfJO.setThirdPayNo(tradeOrder.getOrderNo());
        jhfJO.setTransTime(createTimerStr);
        jhfJO.setUnionId(tradeOrder.getInnerCode());
        String reqData = "";
        String dateTemp = JSON.toJSONString(jhfJO);
        try {
            reqData = URLEncoder.encode(AESUtil.encode(dateTemp, keyStr), "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("生成分期付url时AES加密出错", e);
        }
        return reqData;
    }

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
        if (Strings.isNullOrEmpty(tradeOrder.getOrderNo())) {
            tradeOrder.setOrderNo(DateUtils.getNowYMDOnlyStr() + tradeOrder.getInnerCode() + sequenceService.getOrderSequence("t_trade_order"));
        }
        this.tradeOrderDAO.insert(tradeOrder);

        return tradeOrder;
    }

    // 修改
    public Integer doUpdate(TradeOrderDO tradeOrder) {
        logger.info("开始修改TradeOrderService.update,tradeOrder=" + tradeOrder.toString());
        int rows = this.tradeOrderDAO.update(tradeOrder);
        if (rows == 0) {
            logger.error("订单有可能出现问题，未能更新到数据" + JSON.toJSONString(tradeOrder));
        }
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
        if ("3".equals(order.getOrderStatus())) {//3已退货
            tradeOrderDO.setId(null);
            tradeOrderDO.setTxnType(2);
            tradeOrderDO.setSyncStatus(0);
            tradeOrderDO.setRespCode("1001");
            tradeOrderDO.setCreateTime(new Date());
            doAdd(tradeOrderDO);
        } else {
            if (null == tradeOrderDO.getOrderCeateTime() && !Strings.isNullOrEmpty(order.getTime())) {//回调时更新订单创建时间
                tradeOrderDO.setOrderCeateTime(DateUtils.toParseYmdhms(order.getTime()));
            }
            tradeOrderDAO.updateOnlyFail(tradeOrderDO);
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
            //调用聚惠芬查询订单信息解密后参数[{"periodNum":12,"payAmount":600.11,"salesOrderNo":"1509672149955","time":"2017-11-03 09:22:30 000000","settlementStatus":"0","thirdPayNo":"20171103092212073111374535549120","settlementAmount":558.10,"commercialRate":7.00,"cardHolderRate":0.00,"commId":32,"orderStatus":"0"},{"periodNum":12,"payAmount":600.11,"salesOrderNo":"1509672163885","time":"2017-11-03 09:22:44 000000","settlementStatus":"2","thirdPayNo":"20171103092212073111374535549120","settlementAmount":558.10,"commercialRate":7.00,"cardHolderRate":0.00,"commId":32,"orderStatus":"1"}]
            if (!Strings.isNullOrEmpty(resultJson)) {
                try {
                    JSONObject jsonObj = JSON.parseObject(resultJson);
                    String rspDataStr = jsonObj.getString("rspData");
                    if (Strings.isNullOrEmpty(rspDataStr)) {
                        logger.error("调用聚惠芬查询订单信息参数为空,入参" + param.toString());
                        return;
                    }
                    String decodeStr = AESUtil.decode(rspDataStr, keyStr);
                    logger.error("调用聚惠芬查询订单信息解密后参数" + decodeStr);
                    List<OrderDTO> orderDtoList = JSON.parseArray(decodeStr, OrderDTO.class);
                    if (null != orderDtoList) {
                        for (OrderDTO dto : orderDtoList) {
                            if (null != dto) {
                                dto.setOrderAmount(dto.getPayAmount());
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
            logger.error("同步分期付订单交易数据" + order.getOrderNo());
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