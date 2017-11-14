package net.fnsco.trading.service.pay.channel.zxyh;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.beust.jcommander.internal.Maps;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.utils.HttpUtils;
import net.fnsco.trading.util.SignUtil;

@Service
public class ZxyhPaymentService extends BaseService {
    @Autowired
    private Environment env;

    public void trade() {
        String resultJson = "";
        String url = env.getProperty("");
        Map<String, String>  parameterMap= Maps.newHashMap();
        String signAture = SignUtil.zxyhSign(parameterMap);
        String sendData = "";
        Map<String, String> params = Maps.newHashMap();
        params.put("sendData", sendData);
        try {
            resultJson = HttpUtils.post(url, params);
        } catch (UnsupportedEncodingException e) {
            logger.error("调用中信银行接口出错", e);
        }
    }
}
