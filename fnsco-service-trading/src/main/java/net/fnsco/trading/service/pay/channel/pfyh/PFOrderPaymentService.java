package net.fnsco.trading.service.pay.channel.pfyh;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sosopay.SosopayClient;
import com.sosopay.SosopayConstants;
import com.sosopay.excption.SosopayApiException;
import com.sosopay.factory.SosopayAPIClientFactory;
import com.sosopay.request.SosopayTradeCancelRequest;
import com.sosopay.request.SosopayTradePayRequest;
import com.sosopay.request.SosopayTradePrecreateRequest;
import com.sosopay.request.SosopayTradeQueryRequest;
import com.sosopay.response.SosopayTradeCancelResponse;
import com.sosopay.response.SosopayTradePayResponse;
import com.sosopay.response.SosopayTradePrecreateResponse;
import com.sosopay.response.SosopayTradeQueryResponse;

import net.fnsco.bigdata.service.dao.master.MerchantChannelDao;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.trading.comm.TradeConstants;
import net.fnsco.trading.service.order.entity.TradeOrderDO;

@Service
public class PFOrderPaymentService extends BaseService {
    private Logger             logger     = Logger.getLogger(this.getClass());
    private static String      OK         = "1001";
    private static String      ERROR      = "1002";
    private static String      PROCESSING = "1111";
    private static String      CLOSE      = "8888";
    //private static String serviceUrl = "http://www.sssyin.cn/openGateway/openService/";
    //测试账号
    //private static String busiCode   = "0010000001";
    //private static String privateKye = "98SDKFUO432HNODS098EEE07U9DUOIUE";

    //private static String busiCode   = "952010524110";
    //private static String privateKye = "2CF4B554B6DF92AD6E5A7A73DFADD39214746BAB";
    //private static String serviceUrl = "http://uat.sssyin.cn:9000/openGateway/openService/";
    @Autowired
    private MerchantChannelDao merchantChannelDao;
    @Autowired
    private Environment        env;

    /**
     * 商户被扫，返回二维码让客户扫
     * @param orderPayment
     * @return
     */
    public TradeOrderDO beisaoPaySendPost(TradeOrderDO orderPayment) {
        MerchantChannel merchantChannel = merchantChannelDao.selectByInnerCodeType(orderPayment.getInnerCode(), "01");
        //商户号
        String busiCode = merchantChannel.getChannelMerId();
        //商户秘钥
        String privateKye = merchantChannel.getChannelMerKey();
        String serviceUrl = env.getProperty("pf.pay.url");
        SosopayTradePrecreateRequest request = new SosopayTradePrecreateRequest();
        BigDecimal amtBig = orderPayment.getTxnAmount();
        amtBig = amtBig.divide(new BigDecimal(100));
        double amt = amtBig.doubleValue();
        request.setAmt(amt);
        request.setBusiCode(busiCode);
        request.setChargeCode(orderPayment.getOrderNo());//商户交易上行流水号(需要唯一)
        request.setDevid(String.valueOf(orderPayment.getCreateUserId()));//设备号
        //0   被扫接口传0自动判定渠道
        //1   支付宝
        //2   微信
        if (TradeConstants.PayTypeEnum.WX_PAY.getCode().equals(orderPayment.getTxnType())) {
            request.setChannelType(SosopayConstants.CHANNEL_TYPE_WEIXIN);//支付渠道
        } else if (TradeConstants.PayTypeEnum.ZFB_PAY.getCode().equals(orderPayment.getTxnType())) {
            request.setChannelType(SosopayConstants.CHANNEL_TYPE_ALI);//支付渠道
        }
        request.setTimeExpire(120);//超时时间
        //sxf request.setPaySubject(orderPayment.getSubject());//支付描述信息
        request.setOperid(orderPayment.getCreateUserId());//操作员编号
        //sxf request.setNotifyUrl(orderPayment.getBackUrl());

        //List<GoodsInfo> goodsInfos = new ArrayList<GoodsInfo>();
        //GoodsInfo goods1 = new GoodsInfo("0123456789","商品名称1","商品类型","2.00","商品描述1","5");
        //goodsInfos.add(goods1);
        //request.setGoodsInfos(goodsInfos);
        SosopayClient client = SosopayAPIClientFactory.getSosopayClient(busiCode, privateKye, serviceUrl);
        try {
            logger.error("调用浦发请求参数：" + JSON.toJSONString(request));
            SosopayTradePrecreateResponse response = client.execute(request);
            logger.error("调用浦发beisaoPaySendPost返回内容：" + response.getBody());
            String code = response.getResult().getCode();
            orderPayment.setRespMsg(code + response.getResult().getInfo());
            if ("SUCCESS".equals(code)) {
                orderPayment.setRespCode(PROCESSING);
                orderPayment.setPayOrderNo(response.getChargeDownCode());
                orderPayment.setRespMsg(response.getBarCode());
            } else {
                orderPayment.setRespCode(ERROR);
            }
        } catch (SosopayApiException e) {
            logger.error("调用浦发银行主扫接口出错！", e);
            orderPayment.setRespCode(ERROR);
        }
        return orderPayment;
    }

    /**
     * 商户被扫是否成功查询
     * @param orderPayment
     * @return
     */
    public TradeOrderDO beisaoPayQuerySendPost(TradeOrderDO orderPayment) {
        String serviceUrl = env.getProperty("pf.pay.url");
        SosopayTradeQueryRequest request = new SosopayTradeQueryRequest();
        request.setChargeCode(orderPayment.getOrderNo());
        MerchantChannel merchantChannel = merchantChannelDao.selectByInnerCodeType(orderPayment.getInnerCode(), "01");
        //商户号
        String busiCode = merchantChannel.getChannelMerId();
        //商户秘钥
        String privateKye = merchantChannel.getChannelMerKey();
        //报文不加密
        SosopayClient client = SosopayAPIClientFactory.getSosopayClient(busiCode, privateKye, serviceUrl);
        try {
            SosopayTradeQueryResponse response = client.execute(request);
            String code = response.getResult().getCode();
            logger.error("调用浦发beisaoPayQuerySendPost返回内容：" + response.getBody());
            if ("SUCCESS".equals(code)) {
                Integer state = response.getState();
                if (state != null) {
                    if (state == 1) {
                        orderPayment.setRespCode(OK);
                        orderPayment.setRespMsg("交易成功");
                        orderPayment.setCompleteTime(DateUtils.getNowTime());
                        return orderPayment;
                    } else if (state != 0) {
                        orderPayment.setRespCode(ERROR);
                        orderPayment.setRespMsg("交易失败");
                        return orderPayment;
                    } else {
                        orderPayment.setRespCode(PROCESSING);
                        orderPayment.setRespMsg("交易进行中");
                        return orderPayment;
                    }
                }
            }
        } catch (SosopayApiException e) {
            logger.error("浦发银行查询主扫交易是否成功接口出错！", e);
        }
        return null;
    }

    /**
     * 
     * zhusaoPaySendPost:(主扫接口)
     *
     * @param orderPayment
     * @return   TradeOrderDO    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public TradeOrderDO zhusaoPaySendPost(TradeOrderDO orderPayment) {
        String serviceUrl = env.getProperty("pf.pay.url");
        MerchantChannel merchantChannel = merchantChannelDao.selectByInnerCodeType(orderPayment.getInnerCode(), "01");
        //商户号
        String busiCode = merchantChannel.getChannelMerId();
        //商户秘钥
        String privateKye = merchantChannel.getChannelMerKey();
        SosopayTradePayRequest request = new SosopayTradePayRequest();
        //交易金额
        BigDecimal amtBig = orderPayment.getTxnAmount();
        amtBig = amtBig.divide(new BigDecimal(100));
        double amt = amtBig.doubleValue();
        request.setAmt(amt);
        //商户编号
        request.setBusiCode(busiCode);
        //商户订单流水号
        request.setChargeCode(orderPayment.getOrderNo());//商户交易上行流水号(需要唯一)
        request.setDevid(String.valueOf(orderPayment.getCreateUserId()));//设备号
        //二维码
        //sxf request.setDynamicId(orderPayment.getPayCode());

        //sxf request.setPaySubject(orderPayment.getSubject());
        //操作员
        request.setOperid(orderPayment.getCreateUserId());
        //sxf request.setNotifyUrl(orderPayment.getBackUrl());

        //List<GoodsInfo> goodsInfos = new ArrayList<GoodsInfo>();
        //GoodsInfo goods1 = new GoodsInfo("0123456789", "商品名称1", "商品类型", "2.00", "商品描述1", "5");
        //goodsInfos.add(goods1);
        //request.setGoodsInfos(goodsInfos);

        //报文加密
        //String encryType = SosopayConstants.ENCRY_TYPE_3DES;
        // SosopayClient client = SosopayAPIClientFactory.getSosopayClient(busiCode, privateKye,
        //    serviceUrl, encryType);
        //报文不加密
        SosopayClient client = SosopayAPIClientFactory.getSosopayClient(busiCode, privateKye, serviceUrl);
        try {
            logger.error("调用浦发请求参数：" + JSON.toJSONString(request));
            SosopayTradePayResponse response = client.execute(request);
            logger.error("调用浦发zhusaoPaySendPost返回内容：" + response.getBody());
            String code = response.getResult().getCode();
            orderPayment.setRespMsg(code + response.getResult().getInfo());
            if ("SUCCESS".equals(code)) {
                orderPayment.setRespCode(OK);
                orderPayment.setPayOrderNo(response.getChargeDownCode());
                orderPayment.setCompleteTime(DateUtils.getNowTime());
            } else {
                orderPayment.setRespCode(ERROR);
            }
        } catch (SosopayApiException e) {
            logger.error("调用浦发银行主扫接口出错！", e);
            orderPayment.setRespCode(ERROR);
        }
        return orderPayment;
    }

    /**
     * 客户二维码主扫订单撤消
     * @param orderPayment
     * @return
     */
    public TradeOrderDO payTradeCancelSendPost(TradeOrderDO orderPayment) {
        String serviceUrl = env.getProperty("pf.pay.url");
        MerchantChannel merchantChannel = merchantChannelDao.selectByInnerCodeType(orderPayment.getInnerCode(), "01");
        //商户号
        String busiCode = merchantChannel.getChannelMerId();
        //商户秘钥
        String privateKye = merchantChannel.getChannelMerKey();
        SosopayTradeCancelRequest request = new SosopayTradeCancelRequest();
        request.setBusiCode(busiCode);
        request.setChargeCode(orderPayment.getOrderNo());
        //报文不加密
        SosopayClient client = SosopayAPIClientFactory.getSosopayClient(busiCode, privateKye, serviceUrl);
        try {
            logger.error("调用浦发请求参数：" + JSON.toJSONString(request));
            SosopayTradeCancelResponse response = client.execute(request);
            logger.error("调用浦发返回内容：" + JSON.toJSONString(response));
            String code = response.getResult().getCode();
            orderPayment.setRespMsg(code + response.getResult().getInfo());
            if ("SUCCESS".equals(code)) {
                orderPayment.setRespCode(CLOSE);
            }
        } catch (SosopayApiException e) {
            logger.error("调用浦发银行撤消订单接口出错！", e);
        }
        return orderPayment;
    }

    /**
     * 
     * zhusaoPaySendPost:(公众号支付接口-暂时未使用)
     *
     * @param orderPayment
     * @return   TradeOrderDO    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public TradeOrderDO gzhPaySendPost(TradeOrderDO orderPayment) {
        String serviceUrl = env.getProperty("pf.pay.url");
        MerchantChannel merchantChannel = merchantChannelDao.selectByInnerCodeType(orderPayment.getInnerCode(), "01");
        //商户号
        String busiCode = merchantChannel.getChannelMerId();
        //商户秘钥
        String privateKye = merchantChannel.getChannelMerKey();
        SosopayTradePayRequest request = new SosopayTradePayRequest();
        //交易金额
        BigDecimal amtBig = orderPayment.getTxnAmount();
        amtBig = amtBig.divide(new BigDecimal(100));
        double amt = amtBig.doubleValue();
        request.setAmt(amt);
        //商户编号
        request.setBusiCode(busiCode);
        //商户订单流水号
        request.setChargeCode(orderPayment.getOrderNo());//商户交易上行流水号(需要唯一)
        request.setDevid(String.valueOf(orderPayment.getCreateUserId()));//设备号
        //二维码
        //sxf request.setDynamicId(orderPayment.getPayCode());

        //sxf request.setPaySubject(orderPayment.getSubject());
        //操作员
        request.setOperid(orderPayment.getCreateUserId());
        //sxf request.setNotifyUrl(orderPayment.getBackUrl());

        //List<GoodsInfo> goodsInfos = new ArrayList<GoodsInfo>();
        //GoodsInfo goods1 = new GoodsInfo("0123456789", "商品名称1", "商品类型", "2.00", "商品描述1", "5");
        //goodsInfos.add(goods1);
        //request.setGoodsInfos(goodsInfos);

        //报文加密
        //String encryType = SosopayConstants.ENCRY_TYPE_3DES;
        // SosopayClient client = SosopayAPIClientFactory.getSosopayClient(busiCode, privateKye,
        //    serviceUrl, encryType);
        //报文不加密
        SosopayClient client = SosopayAPIClientFactory.getSosopayClient(busiCode, privateKye, serviceUrl);
        try {
            logger.error("调用浦发请求参数：" + JSON.toJSONString(request));
            SosopayTradePayResponse response = client.execute(request);
            logger.error("调用浦发zhusaoPaySendPost返回内容：" + response.getBody());
            String code = response.getResult().getCode();
            orderPayment.setRespMsg(code + response.getResult().getInfo());
            if ("SUCCESS".equals(code)) {
                orderPayment.setRespCode(OK);
                orderPayment.setPayOrderNo(response.getChargeDownCode());
                orderPayment.setCompleteTime(DateUtils.getNowTime());
            } else {
                orderPayment.setRespCode(ERROR);
            }
        } catch (SosopayApiException e) {
            logger.error("调用浦发银行主扫接口出错！", e);
            orderPayment.setRespCode(ERROR);
        }
        return orderPayment;
    }
}
