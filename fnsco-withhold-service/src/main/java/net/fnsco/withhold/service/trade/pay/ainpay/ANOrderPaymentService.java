package net.fnsco.withhold.service.trade.pay.ainpay;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;

import net.fnsco.core.base.BaseService;
import net.fnsco.withhold.comm.ApiConstant;

@Service
public class ANOrderPaymentService extends BaseService {
    private Logger      logger = Logger.getLogger(this.getClass());
    @Autowired
    private Environment env;

    /**
     * 3.2.1　单笔代付
     * @param orderPayment
     * @return
     */
    public OrderPaymentDO withholdPaySendPost(OrderPaymentDO orderPayment) {
        String merId = env.getProperty(ApiConstant.MER_ID);
        Map<String, String> paras = new HashMap<String, String>();

        paras.put("version", "1.0.0");// 消息版本号
        paras.put("txnType", "12");// 交易类型
        paras.put("txnSubType", "01");// 交易子类型
        paras.put("bizType", "000401");// 产品类型
        paras.put("accessType", "0");// 接入类型
        paras.put("accessMode", "01");// 接入方式

        paras.put("merId", merId);// 商户号       
        paras.put("merOrderId", orderPayment.getOrder_sn());// 商户订单号
        paras.put("accNo", "");//银行卡卡号
        JSONObject jo = new JSONObject();
        jo.put("certifTp", certifTp); //证件类型   身份证 01
        jo.put("certify_id", certify_id);// 证号
        jo.put("phoneNo", phoneNo);//手机号
        jo.put("customerNm", customerNm);//姓名
        if (("01").equals(ppFlag)) {//对私
            jo.put("issInsName", bankName);//开户支行名
        }
        paras.put("customerInfo", jo.toString()); // 银行卡验证信息及身份信息

        paras.put("txnTime", orderPayment.getTxnTime());// 订单发送时间
        paras.put("txnAmt", orderPayment.getTxnAmt().toString());// 交易金额（分）
        paras.put("currency", "CNY");// 交易币种
        paras.put("backUrl", orderPayment.getBackUrl());// 后台通地址
        paras.put("payType", "0401");// 支付方式

        paras.put("bankId", orderPayment.getPayTimeOut());// 银行编号
        paras.put("subject", orderPayment.getSubject());// 商品标题
        paras.put("body", orderPayment.getBody());// 商品描述
        paras.put("ppFlag", orderPayment.getCustomerIp());//  对公对私标志00：对公 01：对私
        paras.put("purpose", "");// 用途

        paras.put("merResv1", orderPayment.getMerReserved());// 请求保留域
        Map<String, String> result1 = HttpRequestPayHelper.sendPost(env.getProperty(ApiConstant.AN_PAY_URL), paras, "UTF-8", 1);
        if (null == result1) {
            return null;
        }
        String resv = result1.get("resv");
        if (Strings.isNullOrEmpty(resv)) {
            logger.error("被扫爱农返回字段resv二维码url为空" + orderPayment.getPhoneNo());
        }
        orderPayment.setRespCode(result1.get("respCode"));
        orderPayment.setResv(result1.get("resv"));
        orderPayment.setRespMsg(result1.get("respMsg"));
        orderPayment.setTn(result1.get("tn"));

        return orderPayment;
    }

}
