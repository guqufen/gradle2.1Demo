package net.fnsco.trading.service.pay.channel.zxyh;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.beust.jcommander.internal.Maps;

import net.fnsco.bigdata.api.dto.MerchantCoreEntityZxyhDTO;
import net.fnsco.bigdata.api.merchant.MerchantChannelService;
import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.bigdata.service.dao.master.MerchantChannelDao;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.sys.SequenceService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.utils.DateUtils;
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
    @Autowired
    private MerchantChannelDao channelDao;
    @Autowired
    private SequenceService  sequenceService;

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
     * @return 
     */
    public Map<String, Object> generateQRCodeWeiXin(String innerCode,String orderBody,String txnAmt){
    	String merId = env.getProperty("zxyh.merId");
    	ActiveWeiXinDTO weiXinDTO = new ActiveWeiXinDTO();
    	weiXinDTO.init(merId);
    	String url = ":8099/MPay/backTransAction.do"; //生产是8092
    	weiXinDTO.setEncoding("UTF-8");
    	weiXinDTO.setBackEndUrl(""); //接收支付网关异步通知回调地址
    	MerchantChannel channel = channelDao.selectByInnerCodeType(innerCode, "05");
    	if(channel != null){
    		weiXinDTO.setMerId(channel.getChannelMerId()); //商户编号	M	String(15)	普通商户或平台商户的商户号
    		
    	}
    	weiXinDTO.setSecMerId("");//分账子商户号 C	String(15)	使用分账功能时上传，是与merId关联的分账子商户号
    	weiXinDTO.setTermId("");//终端编号	C	String(8)	终端编号默认WEB
    	weiXinDTO.setTermIp("");//终端IP	C	String(16)	APP和网页支付提交用户端ip，主扫支付填调用付API的机器IP
    	
    	weiXinDTO.setOrderId(DateUtils.getNowYMDOnlyStr() + innerCode + sequenceService.getOrderSequence("t_trade_order")); //商户系统内部的订单号 M ,32 个字符内、可包含字母, 确保在商户系统唯一
    	weiXinDTO.setOrderTime(DateUtils.getNowDateStr()); //订单生成时间，M 格式 为[yyyyMMddHHmmss] ,如2009年12月25日9点10分10秒 表示为20091225091010
    	weiXinDTO.setProductId("");	//商品ID	C	Strng(32)	此id为二维码中包含的商品ID，商户自行定义。
    	weiXinDTO.setOrderBody(orderBody); //商品或支付单简要描述 	M
    	weiXinDTO.setOrderDetail("");//商品详细描述 	C
    	weiXinDTO.setOrderGoodsTag("");//商品标记	C	String(32)	商品标记，代金券或立减优惠功能的参数
    	weiXinDTO.setTxnAmt(txnAmt);// M 订单总金额(交易单位为分，例:1.23元=123) 只能整数
    	weiXinDTO.setAccountFlag("Y");//必填  Y 分账标识
    	weiXinDTO.setSecMerFeeRate("");//分账子商户交易费率	secMerFeeRate	C	String(16)	使用分账功能时上传，是分账子商户secMerId的交易费率（若费率是0.3%，此值传0.003，小数点后最多四位），此费率值不能比平台商户merId的费率低
    	weiXinDTO.setAttach("");	//附加数据	C	String(127)	仅微信、QQ钱包适用；附加数据，在支付通知中原样返回，该字段主要用于商户携带订单的自定义数据，要求格式如下bank_mch_name=xxxxx&bank_mch_id=xxxx&商户自定义参数
    	weiXinDTO.setLimitPay("");	//指定支付方式	limitPay	C	String(32)	仅微信适用；no_credit--指定不能使用信用卡支付
    	weiXinDTO.setNeedBankType("");//是否需要获取支付行类型	C	String(1)	传“Y”，即会在查询订单以及异步通知时返回“bankType”（支付行类型）、“orderOpenId”（支付用户id）字段
    	weiXinDTO.setIndependentTransactionFlag("Y");//必填Y
    	weiXinDTO.setOrderType("");//订单类型	orderType	R	String(1)	京东支付必填，固定值：0或者1（0：：实物，1：虚拟）
        
        String aliPayStr = JSON.toJSONString(weiXinDTO);
        Map<String, String> aliPayMap = JSON.parseObject(aliPayStr, Map.class);
        //发送中信报文
        String respStr = ZxyhPayMD5Util.request(aliPayMap, url);
        //解析返回报文
        Map<String, Object> respMap = ZxyhPayMD5Util.getResp(respStr);
//        System.out.println(JSON.toJSON(respMap).toString());
        return respMap;
    }
    
    
    /**
     * 支付宝主扫
     *
     */
    public Map<String, Object> generateQRCodeAliPay(String innerCode,String ip,String orderBody,String txnAmt){
    	String merId = env.getProperty("zxyh.merId");
    	ActiveAlipayDTO activeAlipayDTO = new ActiveAlipayDTO();
    	activeAlipayDTO.init(merId);
    	String url = ":8099/MPay/backTransAction.do";
    	
        activeAlipayDTO.setEncoding("UTF-8");
        activeAlipayDTO.setBackEndUrl(""); //接收支付网关异步通知回调地址
        //根据内部商户号获取独立商户号
        MerchantChannel channel = channelDao.selectByInnerCodeType(innerCode, "05");
        if(channel != null){
        	activeAlipayDTO.setSecMerId(channel.getChannelMerId());//独立商户号  
        	
        }
        activeAlipayDTO.setTermIp(ip);//发起支付的客户端真实IP
        activeAlipayDTO.setOrderId(DateUtils.getNowYMDOnlyStr() + innerCode + sequenceService.getOrderSequence("t_trade_order")); //商户系统内部的订单号,32 个字符内、可包含字母, 确保在商户系统唯一
        activeAlipayDTO.setOrderTime(DateUtils.getNowDateStr()); //订单生成时间，格式 为[yyyyMMddHHmmss] ,如2009年12月25日9点10分10秒 表示为20091225091010
        activeAlipayDTO.setOrderBody(orderBody); //商品或支付单简要描述
        activeAlipayDTO.setOrderDetail("");//C	String(100)	商品详细描述
        activeAlipayDTO.setTxnAmt(txnAmt);//订单总金额(交易单位为分，例:1.23元=123) 只能整数
        
        activeAlipayDTO.setAccountFlag("Y");//必填  Y
        activeAlipayDTO.setSecMerFeeRate("");//C	String(16)	使用分账功能时上传，是分账子商户secMerId的交易费率（若费率是0.3%，此值传0.003，小数点后最多四位），此费率值不能比平台商户merId的费率低
        activeAlipayDTO.setDisablePayChannels("");//要禁用的信用渠道 C	String(256)	禁用支付渠道 creditCard：信用卡  credit_group：所有信用渠道，包含（信用卡，花呗）
        activeAlipayDTO.setNeedBankType("");//是否需要获取支付行类型	needBankType	C	String(1)	传“Y”，即会在查询订单以及异步通知时返回“bankType”（支付行类型）、“orderOpenId”（支付用户id）字段
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
