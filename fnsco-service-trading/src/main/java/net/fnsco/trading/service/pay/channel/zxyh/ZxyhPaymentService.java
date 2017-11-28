package net.fnsco.trading.service.pay.channel.zxyh;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
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

import net.fnsco.bigdata.api.trade.TradeDataService;
import net.fnsco.bigdata.service.dao.master.MerchantChannelDao;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.sys.SequenceService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.utils.DateUtils;

import net.fnsco.bigdata.comm.ServiceConstant.PaySubTypeAllEnum;
import net.fnsco.bigdata.comm.ServiceConstant.PayTypeEnum;
import net.fnsco.bigdata.comm.ServiceConstant.TradeStateEnum;
import net.fnsco.bigdata.comm.ServiceConstant.TradeTypeEnum;
import net.fnsco.bigdata.service.domain.trade.TradeData;
import net.fnsco.core.utils.DbUtil;
import net.fnsco.core.utils.HttpUtils;
import net.fnsco.trading.service.pay.OrderPaymentService;
import net.fnsco.trading.service.pay.channel.zxyh.dto.ActiveAlipayDTO;
import net.fnsco.trading.service.pay.channel.zxyh.dto.ActiveWeiXinDTO;
import net.fnsco.trading.service.pay.channel.zxyh.dto.MerchantZxyhDTO;
import net.fnsco.trading.service.pay.channel.zxyh.dto.PassivePayDTO;
import net.fnsco.trading.service.pay.channel.zxyh.util.SignUtil;
import net.fnsco.trading.service.pay.channel.zxyh.util.ZxyhPayMD5Util;

@Service
public class ZxyhPaymentService extends BaseService implements OrderPaymentService {
    @Autowired
    private Environment env;
    @Autowired
    private MerchantChannelDao merchantChannelDao;
    @Autowired
    private TradeDataService tradeDataService;
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
    
    /**
     * 被扫交易处理,测试环境：https://120.27.165.177:8099/MPay/misRequest.do
     * @param reqStr：被扫实体对象
     * @return:被扫交易应答JSON字符串
     */
    public String PassivePay(String innerCode, PassivePayDTO passivePayDTO){

    	String url = "/MPay/misRequest.do";
    	PassivePayDTO passDTO = new PassivePayDTO();
    	passDTO.init();

    	//通过内部商户号查找绑定的中信商户号
    	MerchantChannel merchantChannel = merchantChannelDao.selectByInnerCodeType(innerCode, "05");
    	if( null == merchantChannel){
    		logger.info("该内部商户号没有绑定中信渠道的商户号，请核查后重新交易,innerCode=["+innerCode+"");
    		Map<String, String> map = new HashMap<>();
    		map.put("respCode", "9999");
    		map.put("respMsg", "系统异常");
    		return map.toString();
    	}
    	passDTO.setStdmercno2(merchantChannel.getChannelMerId());//二级商户号，使用分账功能时上传，是与stdmercno关联的分账子商户号
    	passDTO.setStdprocode(passivePayDTO.getStdprocode());//交易码，481000：微信消费 481003：支付宝二清消费
    	passDTO.setStddiscamt(passivePayDTO.getStddiscamt());//不可优惠金额，支付宝必填
    	passDTO.setStdmsgtype("48");// 消息类型 M String(4) "48"
    	passDTO.setStdorderid(System.currentTimeMillis() + "");//商户订单号，32个字符内
    	passDTO.setStdtrancur("156");// 交易币种 M String(3) 默认是156：人民币
    	passDTO.setNeedBankType("Y");// 值为“Y”时，支付成功、查询支付成功的订单会返回付款银行类型bankType
    	passDTO.setStdbegtime(DateUtils.getNowDateStr());//交易起始时间,yyyyMMddHHmmss
    	passDTO.setStd400memo(passivePayDTO.getStd400memo());//商品描述
    	passDTO.setStdtranamt(passivePayDTO.getStdtranamt());//交易金额,整数，以分为单位
    	passDTO.setStdauthid(passivePayDTO.getStdauthid());//授权码

    	//插表处理
    	TradeData tradeData = new TradeData();
    	tradeData.setId(DbUtil.getUuid());//设置ID
    	tradeData.setInnerCode(innerCode);//设置内部商户号
    	tradeData.setTxnType(TradeTypeEnum.CONSUMER.getCode());//设置交易类型
    	tradeData.setCurrency(passDTO.getStdtrancur());//设置交易币种
    	tradeData.setBody(passDTO.getStd400memo());//商品描述
    	tradeData.setChannelType("05");//设置渠道类型
    	tradeData.setAmt(passDTO.getStdtranamt());//设置交易金额
    	tradeData.setOrderNo(passDTO.getStdorderid());//设置订单号(商户订单号)
    	tradeData.setOrderTime(passDTO.getStdbegtime());//设置交易起始时间
    	tradeData.setPayType(PayTypeEnum.CODE_PAY.getCode());//设置支付方式，01-二维码
    	//设置支付子类型，01-微信；02-支付宝
    	if( "481003".equals(passDTO.getStdprocode()) ){
    		tradeData.setPaySubType(PaySubTypeAllEnum.ZFB_PAY.getCode());
    	}else if("481000".equals(passDTO.getStdprocode())){
    		tradeData.setPaySubType(PaySubTypeAllEnum.WX_PAY.getCode());
    	}
    	tradeData.setTimeStamp(passDTO.getStdbegtime());//交易时间戳

    	String passiveStr = JSON.toJSONString(passDTO);//将对象转换为JSON字符串
    	tradeData.setTradeDetail(passiveStr);//设置交易详情，整个请求JSON字符串
    	tradeData.setMerId(passDTO.getStdmercno2());//设置结算商户号(二级商户号)
    	tradeData.setSource("07");//设置来源，07-中信银行扫码
    	tradeData.setMd5(passivePayDTO.getSignAture());//设置md5唯一标识
    	tradeData.setSendTime(passDTO.getStdbegtime());//交易传送时间
    	tradeData.setCreateTime(new Date());//设置交易创建时间
    	tradeData.setStatus("1");//设置交易状态1-正常交易；0非正常交易（包括撤销交易和撤销原交易
    	tradeData.setPayMedium("01");//设置支付媒介，00-POS机；01-APP；02-台码
    	tradeData.setChannelTermCode(passDTO.getStdtermid());//设置渠道终端编号
    	tradeDataService.saveTradeData(tradeData);//保存数据
    	
    	Map<String, String> passiveMap = JSON.parseObject(passiveStr, Map.class);//将JSON字符串转换为map
    	String respStr = ZxyhPayMD5Util.request(passiveMap, url, "https://120.27.165.177:8099");

    	//解析返回报文
        Map<String, Object> respMap = ZxyhPayMD5Util.getResp(respStr);
        System.out.println(respMap);
        String json = JSON.toJSONString(respMap);
        PassivePayDTO passDTO1 = JSON.parseObject(json, PassivePayDTO.class);

        tradeData.setOrgMerOrderId(passDTO.getStdorderid());//设置原商户订单号为当前商户订单号
    	TradeData tradeData2 = tradeDataService.selectByCMT(tradeData);//通过渠道终端号商户号和原订单号查找该笔交易(主要查找到主键key)
    	if( null == tradeData2){
    		logger.info("没有找到该交易请求交易,order_no=["+tradeData.getOrgMerOrderId()+"],channel_term_code=["+tradeData.getChannelTermCode()+"],mer_id=["+tradeData.getMerId()+"]");
    		Map<String, String> map = new HashMap<>();
    		map.put("respCode", "9999");
    		map.put("respMsg", "系统异常");
    		return map.toString();
    	}

        //支付成功，则直接更新交易状态
        if("0000".equals(passDTO1.getStd400mgid())){
        	tradeData2.setRespCode(TradeStateEnum.SUCCESS.getCode());//设置响应应答码
        	
        	tradeData2.setSuccTime(passDTO1.getEndTime());//交易成功时间(支付完成时间，成功时返回)
        	
        	tradeData2.setSettleAmount(passDTO1.getQstranamt());//清算金额
        	tradeData2.setSettleCurrency(passDTO1.getQstrancur());//清算币种
        	tradeData2.setSettleDate(passDTO1.getQs400trdt());//清算日起
        	
        }else if("1111".equals(passDTO1.getStd400mgid())){//进行中
        	
        }else{//交易失败
        	tradeData2.setRespCode(TradeStateEnum.FAIL.getCode());//设置响应应答码
        }
        tradeData2.setRespMsg(passDTO1.getStdrtninfo());//设置响应信息
        tradeData2.setTn(passDTO1.getStdrefnum());//受理订单号(取字段平台交易流水号)
        tradeData2.setOrderIdScan(passDTO1.getTransactionId());//渠道订单号，交易成功返回，供后续退货/撤销使用
        tradeDataService.updateByPrimaryKeySelective(tradeData2);//通过主键更新应答数据

		return null;
    	
    }
    
    public String PassivePayResult(PassivePayDTO passivePayDTO){
    	String url = "/MPay/misRequest.do";
    	PassivePayDTO passDTO = new PassivePayDTO();
    	passDTO.init();
    	
    	passDTO.setStdmsgtype("38");// 消息类型 M String(4) "38"
    	//交易码
    	if( "ZFB_CX".equals(passivePayDTO.getStdprocode()) ){
    		passDTO.setStdprocode("381003");
    	}else if("WX_CX".equals(passivePayDTO.getStdprocode())){
    		passDTO.setStdprocode("381000");
    	}
    	//通过内部商户号查找绑定的中信商户号
    	MerchantChannel merchantChannel = merchantChannelDao.selectByInnerCodeType(passivePayDTO.getStdmercno(), "05");
    	if( null == merchantChannel){
    		logger.info("该内部商户号没有绑定中信渠道的商户号，请核查后重新交易,innerCode=["+passivePayDTO.getStdmercno()+"");
    		return null;
    	}

    	passDTO.setStdmercno2(merchantChannel.getChannelMerId());//二级商户号，使用分账功能时上传，是与stdmercno关联的分账子商户号
    	passDTO.setOrgorderid(passivePayDTO.getOrgorderid());//原支付交易的中信订单号或原支付交易的商户订单号
    	passDTO.setStdorderid(System.currentTimeMillis() + "");//商户订单号，32个字符内
    	passDTO.setStdbegtime(DateUtils.getNowDateStr());//交易起始时间

    	String passiveStr = JSON.toJSONString(passDTO);//将对象转换为JSON字符串
    	Map<String, String> passiveMap = JSON.parseObject(passiveStr, Map.class);//将JSON字符串转换为map
    	String respStr = ZxyhPayMD5Util.request(passiveMap, url, "https://120.27.165.177:8099");

    	//解析返回报文
        Map<String, Object> respMap = ZxyhPayMD5Util.getResp(respStr);
        System.out.println(respMap);
        String json = JSON.toJSONString(respMap);
        PassivePayDTO passDTO1 = JSON.parseObject(json, PassivePayDTO.class);
    	return null;
    }
    
    public String PassivePayRefund(PassivePayDTO passivePayDTO){
    	
    	String url = "/MPay/misRequest.do";
    	PassivePayDTO passDTO = new PassivePayDTO();
    	passDTO.init();

    	//通过内部商户号查找绑定的中信商户号
    	MerchantChannel merchantChannel = merchantChannelDao.selectByInnerCodeType(passivePayDTO.getStdmercno(), "05");
    	if( null == merchantChannel){
    		logger.info("该内部商户号没有绑定中信渠道的商户号，请核查后重新交易,innerCode=["+passivePayDTO.getStdmercno()+"");
    		return null;
    	}
    	passDTO.setStdmercno2(merchantChannel.getChannelMerId());//二级商户号，使用分账功能时上传，是与stdmercno关联的分账子商户号

    	passDTO.setStdmsgtype("48");// 消息类型 M String(4) "48"
    	if( "ZFB_REF".equals(passivePayDTO.getStdprocode()) ){
    		passDTO.setStdprocode("481003");//交易码，481000：微信消费 481003：支付宝二清消费
    	}else if("WX_REF".equals(passivePayDTO.getStdprocode())){
    		passDTO.setStdprocode("481000");//交易码，481000：微信消费 481003：支付宝二清消费
    	}else{
    		return "交易码错误"+passivePayDTO.getStdprocode();
    	}

    	passDTO.setStdorderid(System.currentTimeMillis() + "");//商户订单号，32个字符内
    	passDTO.setStdtrancur("156");// 交易币种 M String(3) 默认是156：人民币
    	passDTO.setNeedBankType("Y");// 值为“Y”时，支付成功、查询支付成功的订单会返回付款银行类型bankType

    	passDTO.setStdbegtime(DateUtils.getNowDateStr());//交易起始时间,yyyyMMddHHmmss
    	passDTO.setStdtranamt(passivePayDTO.getStdtranamt());//交易金额,整数，以分为单位
    	passDTO.setOrgrefnum(passivePayDTO.getOrgrefnum());//原支付交易的中信订单号

    	//插表处理
    	TradeData tradeData = new TradeData();
    	tradeData.setId(DbUtil.getUuid());//设置ID

    	String passiveStr = JSON.toJSONString(passDTO);//将对象转换为JSON字符串
    	Map<String, String> passiveMap = JSON.parseObject(passiveStr, Map.class);//将JSON字符串转换为map
    	String respStr = ZxyhPayMD5Util.request(passiveMap, url, "https://120.27.165.177:8099");

    	//解析返回报文
        Map<String, Object> respMap = ZxyhPayMD5Util.getResp(respStr);
        System.out.println(respMap);
        String json = JSON.toJSONString(respMap);
        PassivePayDTO passDTO1 = JSON.parseObject(json, PassivePayDTO.class);
        
        
//        String resultJson = "";
		return null;
    	
    }
}
