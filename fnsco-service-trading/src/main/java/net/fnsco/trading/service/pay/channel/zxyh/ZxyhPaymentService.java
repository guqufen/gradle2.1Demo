package net.fnsco.trading.service.pay.channel.zxyh;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.beust.jcommander.internal.Maps;

import net.fnsco.bigdata.api.dto.MerchantCoreEntityZxyhDTO;
import net.fnsco.bigdata.api.merchant.MerchantChannelService;
import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.utils.HttpUtils;
import net.fnsco.trading.service.pay.OrderPaymentService;
import net.fnsco.trading.service.pay.channel.zxyh.dto.ActiveAlipayDTO;
import net.fnsco.trading.service.pay.channel.zxyh.dto.ActiveWeiXinDTO;
import net.fnsco.trading.service.pay.channel.zxyh.dto.MerchantZxyhDTO;
import net.fnsco.trading.service.pay.channel.zxyh.util.SignUtil;
import net.fnsco.trading.service.pay.channel.zxyh.util.ZxyhPayMD5Util;

@Service
public class ZxyhPaymentService extends BaseService implements OrderPaymentService {
    @Autowired
    private Environment env;
    @Autowired
	private MerchantChannelService merchantChannelService;
    @Autowired
	private MerchantCoreService merchantCoreService;

    /**
     * 
     * trade:(入驻独立商户)
     *   void    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public Map<String, Object> mechAdd(MerchantCoreEntityZxyhDTO core) {
        String pid = env.getProperty("zxyh.alipay.pid");
        String merId = env.getProperty("zxyh.merId");
        String url = ":9001/MPayTransaction/ind/mchtadd.do";
        MerchantZxyhDTO mercDTO = new MerchantZxyhDTO();
        mercDTO.init(pid, merId);
        //赋值
        mercDTO.setMchtNm(core.getMchtNm());
        mercDTO.setMchtCnAbbr(core.getMchtCnAbbr());
        mercDTO.setEtpsAttr(core.getEtpsAttr());
        mercDTO.setEtpsTp(core.getEtpsTp());
        mercDTO.setMchtTel(core.getMchtTel());
        mercDTO.setContact(core.getContact());
        mercDTO.setCommTel(core.getCommTel());
        mercDTO.setCommEmail(core.getCommEmail());
        mercDTO.setLicenceNo(core.getLicenceNo());
        mercDTO.setManager(core.getManager());
        mercDTO.setIdentityNo(core.getIdentityNo());
        mercDTO.setProvCode(core.getProvCode());
        mercDTO.setCityCode(core.getCityCode());
        mercDTO.setAreaCode(core.getAreaCode());
        mercDTO.setAddr(core.getAddr());
        //是否开通微信
        mercDTO.setWXActive(core.getWXActive()); 
        if(StringUtils.equals("Y", core.getWXActive())){
        	mercDTO.setqGroupId(core.getqGroupId());
        	mercDTO.setCategroryId(core.getCategroryId());
        	mercDTO.setFeeRate(core.getFeeRate());
        	mercDTO.setSettleCycle(core.getSettleCycle());
        }
        //公众号
        mercDTO.setSubAppid(core.getSubAppid());
        mercDTO.setJsapiPath(core.getJsapiPath());
        //支付宝
        mercDTO.setZFBActive(core.getZFBActive());
        if(StringUtils.equals("Y", core.getZFBActive())){
        	mercDTO.setCategIdC(core.getqGroupId());
            mercDTO.setFeeRateA(core.getFeeRateA());
            mercDTO.setSettleCycleA(core.getSettleCycleA());
        }
        //银行信息
        mercDTO.setThirdMchtNo(core.getThirdMchtNo());//第三方平台子商户号
        mercDTO.setIsOrNotZxMchtNo(core.getIsOrNotZxMchtNo());
        mercDTO.setAcctType(core.getAcctType());//账户类型 填写数字 1-企业账户，2-个人账户
        mercDTO.setSettleAcctNm(core.getSettleAcctNm());
        mercDTO.setSettleAcct(core.getSettleAcct());
        mercDTO.setAccIdeNo(core.getAccIdeNo());
        mercDTO.setAccPhone(core.getAccPhone());
        mercDTO.setSettleBankAllName(core.getSettleBankAllName());
        mercDTO.setSettleBankCode(core.getSettleBankCode());
        

        String mercStr = JSON.toJSONString(mercDTO);
        Map<String, String> mercMap = JSON.parseObject(mercStr, Map.class);
        String respStr = ZxyhPayMD5Util.request(mercMap, url);
        //解析返回报文
        Map<String, Object> respMap = ZxyhPayMD5Util.getResp(respStr);
        return respMap;
    }

    
    /**
     * 微信主扫
     */
    public void generateQRCodeWeiXin(){
    	String merId = env.getProperty("zxyh.merId");
    	ActiveWeiXinDTO weiXinDTO = new ActiveWeiXinDTO();
    	weiXinDTO.init(merId);
    	String url = ":8099/MPay/backTransAction.do"; //生产是8092
    	weiXinDTO.setEncoding("UTF-8");
    	weiXinDTO.setBackEndUrl(""); //接收支付网关异步通知回调地址
//        activeAlipayDTO.setMerId("994400000000009");//普通商户或平台商户的商户号
    	weiXinDTO.setSecMerId("");//独立商户号  
    	weiXinDTO.setTermIp("192.168.1.162");//发起支付的客户端真实IP
    	weiXinDTO.setOrderId(""); //商户系统内部的订单号,32 个字符内、可包含字母, 确保在商户系统唯一
    	weiXinDTO.setOrderTime(""); //订单生成时间，格式 为[yyyyMMddHHmmss] ,如2009年12月25日9点10分10秒 表示为20091225091010
    	weiXinDTO.setOrderBody(""); //商品或支付单简要描述
    	weiXinDTO.setOrderDetail("");//商品详细描述
    	weiXinDTO.setTxnAmt("");//订单总金额(交易单位为分，例:1.23元=123) 只能整数
        
    	weiXinDTO.setAccountFlag("Y");//必填  Y
    	weiXinDTO.setSecMerFeeRate("");//
    	weiXinDTO.setNeedBankType("");//
    	weiXinDTO.setIndependentTransactionFlag("Y");//必填Y
        
        String aliPayStr = JSON.toJSONString(weiXinDTO);
        Map<String, String> aliPayMap = JSON.parseObject(aliPayStr, Map.class);
        //发送中信报文
        String respStr = ZxyhPayMD5Util.request(aliPayMap, url);
        //解析返回报文
        Map<String, Object> respMap = ZxyhPayMD5Util.getResp(respStr);
        System.out.println(JSON.toJSON(respMap).toString());
    }
    
    
    /**
     * 支付宝主扫
     *
     */
    public Map<String, Object> generateQRCodeAliPay(){
    	String merId = env.getProperty("zxyh.merId");
    	ActiveAlipayDTO activeAlipayDTO = new ActiveAlipayDTO();
    	activeAlipayDTO.init(merId);
    	String url = ":8099/MPay/backTransAction.do";
        activeAlipayDTO.setEncoding("UTF-8");
        activeAlipayDTO.setBackEndUrl(""); //接收支付网关异步通知回调地址
//        activeAlipayDTO.setMerId("994400000000009");//普通商户或平台商户的商户号
        activeAlipayDTO.setSecMerId("");//独立商户号  
        activeAlipayDTO.setTermIp("192.168.1.162");//发起支付的客户端真实IP
        activeAlipayDTO.setOrderId(""); //商户系统内部的订单号,32 个字符内、可包含字母, 确保在商户系统唯一
        activeAlipayDTO.setOrderTime(""); //订单生成时间，格式 为[yyyyMMddHHmmss] ,如2009年12月25日9点10分10秒 表示为20091225091010
        activeAlipayDTO.setOrderBody(""); //商品或支付单简要描述
        activeAlipayDTO.setOrderDetail("");//商品详细描述
        activeAlipayDTO.setTxnAmt("");//订单总金额(交易单位为分，例:1.23元=123) 只能整数
        
        activeAlipayDTO.setAccountFlag("Y");//必填  Y
        activeAlipayDTO.setSecMerFeeRate("");//
        activeAlipayDTO.setNeedBankType("");//
        activeAlipayDTO.setIndependentTransactionFlag("Y");//必填Y
        
        String aliPayStr = JSON.toJSONString(activeAlipayDTO);
        Map<String, String> aliPayMap = JSON.parseObject(aliPayStr, Map.class);
        //发送中信报文
        String respStr = ZxyhPayMD5Util.request(aliPayMap, url);
        //解析返回报文
        Map<String, Object> respMap = ZxyhPayMD5Util.getResp(respStr);
//        System.out.println(JSON.toJSON(respMap).toString());
        return respMap;
    }
    
    public void test() {
        Map<String, String> parameterMap = Maps.newHashMap();
        String signAture = SignUtil.zxyhSign(parameterMap);
        String sendData = "";
        Map<String, String> params = Maps.newHashMap();
        params.put("sendData", sendData);
        String url = "";
        try {
            String resultJson = HttpUtils.post(url, params);
        } catch (UnsupportedEncodingException e) {
            logger.error("调用中信银行接口出错", e);
        }
    }
}
