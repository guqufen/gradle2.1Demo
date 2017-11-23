package net.fnsco.trading.service.pay.channel.zxyh;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.beust.jcommander.internal.Maps;

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
        mercDTO.setWXActive("Y"); // Y默认开通
        mercDTO.setSubAppid(core.getChannels().get(0).getTerminaInfos().get(0).getSubAppId().toString());  //微信公众号
        mercDTO.setqGroupId(core.getChannels().get(0).getTerminaInfos().get(0).getqGroupId());
        mercDTO.setCategroryId(core.getChannels().get(0).getTerminaInfos().get(0).getCategroryId());
        mercDTO.setFeeRate(core.getChannels().get(0).getTerminaInfos().get(0).getWechatFee());//微信费率
        mercDTO.setSettleCycle(core.getChannels().get(0).getTerminaInfos().get(0).getSettleCycle());
        
//        mercDTO.setqGroupId(core.getqGroupId);
        

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
}
