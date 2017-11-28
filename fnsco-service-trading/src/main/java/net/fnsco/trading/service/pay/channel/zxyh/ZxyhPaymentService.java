package net.fnsco.trading.service.pay.channel.zxyh;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.beust.jcommander.internal.Maps;

import net.fnsco.bigdata.comm.ServiceConstant.PaySubTypeAllEnum;
import net.fnsco.bigdata.comm.ServiceConstant.PayTypeEnum;
import net.fnsco.bigdata.comm.ServiceConstant.TradeStateEnum;
import net.fnsco.bigdata.comm.ServiceConstant.TradeTypeEnum;
import net.fnsco.bigdata.service.dao.master.MerchantChannelDao;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.trade.MerchantCoreEntityZxyhDTO;
import net.fnsco.bigdata.service.domain.trade.TradeData;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.DbUtil;
import net.fnsco.core.utils.HttpUtils;
import net.fnsco.trading.service.pay.OrderPaymentService;
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

    /**
     * 
     * trade:(入驻独立商户)
     *   void    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public void mechAdd(MerchantCoreEntityZxyhDTO core) {
        String pid = env.getProperty("zxyh.alipay.pid");
        String merId = env.getProperty("zxyh.merId");
        String url = "/MPayTransaction/ind/mchtadd.do";
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
            mercDTO.setFeeRateA(core.getFeeRate());
            mercDTO.setSettleCycleA(core.getSettleCycle());
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
        System.out.println(respMap);
        String resultJson = "";
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
    public String PassivePay(PassivePayDTO passivePayDTO){

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
    	if( "ZFB_PAY".equals(passivePayDTO.getStdprocode()) ){
    		//不可优惠金额，支付宝必填
    		if( org.apache.commons.lang3.StringUtils.isNotEmpty(passivePayDTO.getStddiscamt()) ){
    			passDTO.setStddiscamt(passivePayDTO.getStddiscamt());
    		}else{
    			passDTO.setStddiscamt("0");
    		}
    		passDTO.setStdprocode("481003");//交易码，481000：微信消费 481003：支付宝二清消费
    	}else if("WX_PAY".equals(passivePayDTO.getStdprocode())){
    		passDTO.setStdprocode("481000");//交易码，481000：微信消费 481003：支付宝二清消费
    	}else{
    		return "交易码错误"+passivePayDTO.getStdprocode();
    	}

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
    	tradeData.setInnerCode(passivePayDTO.getStdmercno());//设置内部商户号
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
    	tradeData.setMd5(passivePayDTO.getSignAture());//设置md5唯一标识
    	tradeData.setSendTime(passDTO.getStdbegtime());//交易传送时间
    	tradeData.setCreateTime(new Date());//设置交易创建时间
    	tradeData.setStatus(TradeStateEnum.SUCCESS.getCode());//设置交易状态1-正常交易；0非正常交易（包括撤销交易和撤销原交易
    	tradeData.setPayMedium("01");//设置支付媒介，00-POS机；01-APP；02-台码
    	tradeData.setChannelTermCode(passDTO.getStdtermid());//设置渠道终端编号
    	
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
