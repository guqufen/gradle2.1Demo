package net.fnsco.trading.service.pay.channel.zxyh;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.beust.jcommander.internal.Maps;

import net.fnsco.bigdata.api.merchant.MerchantChannelService;
import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.bigdata.service.domain.trade.MerchantCoreEntityZxyhDTO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.utils.HttpUtils;
import net.fnsco.trading.service.pay.OrderPaymentService;
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
//        String resultJson = "";
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
