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
import net.fnsco.withhold.service.trade.entity.TradeDataDO;

/**
 * 测试环境商户号200000000000001，密钥88888888，测试环境地址见文档，测试环境不发验证码的，验证码默认是123456，网银测试环境暂时只开放工行；
 * @desc 
 * @author   sxf
 * @version  
 * @since    Ver 1.1
 * @Date	 2017年7月25日 上午10:53:11
 *
 */
@Service
public class ANOrderPaymentService extends BaseService {
    private Logger      logger = Logger.getLogger(this.getClass());
    @Autowired
    private Environment env;

    /**
     * 3.2.1　单笔代付 
     * 代收是，从持卡人银行卡里面扣款，代付是给人打钱
     * @param orderPayment
     * @return
     */
    public TradeDataDO withholdPaySendPost(TradeDataDO tradeDataDO) {
        String merId = env.getProperty(ApiConstant.MER_ID);
        Map<String, String> paras = new HashMap<String, String>();
        paras.put("version", "1.0.0");// 消息版本号
        paras.put("txnType", "12");// 交易类型
        paras.put("txnSubType", "01");// 交易子类型
        paras.put("bizType", "000401");// 产品类型
        paras.put("accessType", "0");// 接入类型
        paras.put("accessMode", "01");// 接入方式

        paras.put("merId", merId);// 商户号       
        paras.put("merOrderId", tradeDataDO.getOrderSn());// 商户订单号
        paras.put("accNo", tradeDataDO.getBankCard());//银行卡卡号
        paras.put("accType", tradeDataDO.getBankCard());//账号类型：01：借记卡03：存折04：公司账号

        JSONObject jo = new JSONObject();
        jo.put("certifTp", tradeDataDO.getCertifType()); //证件类型   身份证 01
        jo.put("certify_id", tradeDataDO.getCertifyId());// 证号
        jo.put("phoneNo", tradeDataDO.getMobile());//手机号
        jo.put("customerNm", tradeDataDO.getUserName());//姓名
        if (("01").equals(tradeDataDO.getAccountType())) {//对私
            jo.put("issInsName", tradeDataDO.getSubBankName());//开户支行名
        }
        paras.put("customerInfo", jo.toString()); // 银行卡验证信息及身份信息

        paras.put("txnTime", tradeDataDO.getTxnTime());// 订单发送时间
        paras.put("txnAmt", tradeDataDO.getTxnAmt().toString());// 交易金额（分）
        paras.put("currency", "CNY");// 交易币种
        paras.put("backUrl", tradeDataDO.getBackUrl());// 后台通地址
        paras.put("payType", "0401");// 支付方式

        paras.put("bankId", tradeDataDO.getAnBankId());// 银行编号
        paras.put("subject", tradeDataDO.getSubject());// 商品标题
        paras.put("body", tradeDataDO.getBody());// 商品描述
        paras.put("ppFlag", tradeDataDO.getAccountType());//  对公对私标志00：对公 01：对私
        paras.put("purpose", "");// 用途

        paras.put("merResv1", "");// 请求保留域
        Map<String, String> result1 = HttpRequestPayHelper.sendPost(env.getProperty(ApiConstant.AN_PAY_URL), env.getProperty(ApiConstant.AN_PAY_KEY), paras, "UTF-8", 1);
        if (null == result1) {
            return null;
        }
        String resv = result1.get("resv");
        if (Strings.isNullOrEmpty(resv)) {
            logger.error("单笔代付" + tradeDataDO.getMobile());
        }
        tradeDataDO.setRespCode(result1.get("respCode"));
        tradeDataDO.setRespMsg(result1.get("respMsg"));

        return tradeDataDO;
    }

    /**
     * 3.2.1　单笔代收
     * 代收是，从持卡人银行卡里面扣款，代付是给人打钱
     * @param orderPayment
     * @return
     */
    public TradeDataDO collectPaymentSendPost(TradeDataDO tradeDataDO) {
        String merId = env.getProperty(ApiConstant.MER_ID);
        String anBackURL = env.getProperty("anBackURL");
        Map<String, String> paras = new HashMap<String, String>();
        paras.put("version", "1.0.0");// 消息版本号
        paras.put("txnType", "11");// 交易类型
        paras.put("txnSubType", "01");// 交易子类型
        paras.put("bizType", "000501");// 产品类型
        paras.put("accessType", "0");// 接入类型
        paras.put("accessMode", "01");// 接入方式

        paras.put("merId", merId);// 商户号       
        paras.put("merOrderId", tradeDataDO.getOrderSn());// 商户订单号
        paras.put("accNo", tradeDataDO.getBankCard());//银行卡卡号
        paras.put("accType", tradeDataDO.getAccType());//账号类型：01：借记卡03：存折04：公司账号

        JSONObject jo = new JSONObject();
        jo.put("certifTp", tradeDataDO.getCertifType()); //证件类型   身份证 01
        jo.put("certify_id", tradeDataDO.getCertifyId());// 证号
        jo.put("phoneNo", tradeDataDO.getMobile());//手机号
        jo.put("customerNm", tradeDataDO.getUserName());//姓名
        jo.put("smsCode", "");//短信验证码
        jo.put("expired", "");//有效期 
        jo.put("cvv2", "");//CVV2
        jo.put("issInsProvince", "");//开户行省 
        jo.put("issInsCity", "");//开户行市

        if (("01").equals(tradeDataDO.getAccountType())) {//对私
            jo.put("iss_ins_name", tradeDataDO.getSubBankName());//开户支行名称
        }
        paras.put("customerInfo", jo.toString()); // 银行卡验证信息及身份信息

        paras.put("txnTime", tradeDataDO.getTxnTime());// 订单发送时间
        paras.put("txnAmt", tradeDataDO.getTxnAmt().toString());// 交易金额（分）
        paras.put("currency", "CNY");// 交易币种
        paras.put("backUrl", anBackURL);// 后台通地址
        paras.put("payType", "0401");// 支付方式

        paras.put("bankId", tradeDataDO.getAnBankId());// 银行编号
        paras.put("subject", tradeDataDO.getSubject());// 商品标题
        paras.put("body", tradeDataDO.getBody());// 商品描述
        paras.put("ppFlag", tradeDataDO.getAccountType());//  对公对私标志00：对公 01：对私
        paras.put("purpose", "");// 用途

        paras.put("merResv1", "");// 请求保留域
        Map<String, String> result1 = HttpRequestPayHelper.sendPost(env.getProperty(ApiConstant.AN_PAY_URL), env.getProperty(ApiConstant.AN_PAY_KEY), paras, "UTF-8", 1);
        if (null == result1) {
            return null;
        }
        String resv = result1.get("resv");
        if (Strings.isNullOrEmpty(resv)) {
            logger.error("单笔代付" + tradeDataDO.getMobile());
        }
        tradeDataDO.setRespCode(result1.get("respCode"));
        tradeDataDO.setRespMsg(result1.get("respMsg"));

        return tradeDataDO;
    }
}
