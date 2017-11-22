package net.fnsco.web.controller.open;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.beust.jcommander.internal.Maps;
import com.lakala.sign.LKLApiException;
import com.lakala.sign.LKLSignature;

import io.swagger.annotations.Api;
import net.fnsco.bigdata.api.trade.TradeDataService;
import net.fnsco.core.base.BaseController;
import net.fnsco.web.controller.open.jo.LklTradeDataJO;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/syncData/lkl", method = RequestMethod.POST)
@Api(value = "/syncData/lkl", tags = { "拉卡拉交易实时传输交口" })
public class LklTradeDateSyncController extends BaseController {
    @Autowired
    private TradeDataService    tradeDataService;
    @Autowired
    private Environment         env;
    private final static String publickeyFile = "rsa_public_keys.pem";
    private final static String charset       = "UTF-8";

    @RequestMapping("/transtradeSave")
    @ResponseBody
    public Object transtradeSave(String sign, String data) {
        logger.error("拉卡拉同步数据输入参数：" + data);
        LklTradeDataJO DataJO = JSON.parseObject(data, LklTradeDataJO.class);
        Map<String, String> dataMap = toLinkedHashMap(data);
        String path = request.getServletContext().getRealPath("WEB-INF/" + publickeyFile);
        // 获取到的公匙
        String publicKey = getPublicKey(path);

        // 把数字证书放到map里准备验签
        dataMap.put("sign", sign);

        // 验签返回结果true或false
        boolean unsignReslut = false;
        try {
            unsignReslut = LKLSignature.rsaCheckV1(dataMap, publicKey, charset);
        } catch (Exception e) {
            logger.error("拉卡拉同步数据验签异常", e);
        }
        JSONObject jsonObject = new JSONObject();
        if (unsignReslut) {
            //验签失败，返回FAIL，会执行重发
            jsonObject.element("return_code", "FAIL");
            jsonObject.element("return_msg", "验签失败");
        }
        jsonObject.element("return_code", "SUCCESS");
        jsonObject.element("return_msg", "ok");
        return jsonObject;
    }

    /**
     * 读取公钥
     */
    private static String getPublicKey(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String publickey = br.readLine();
            System.out.println(publickey);
            return publickey;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * json字符串数据转化成Map
     * 
     * @param json字符
     *            �?
     * @return json对应的map
     * 
     * **/
    public static HashMap<String, String> toLinkedHashMap(String object) {
        HashMap<String, String> data = new LinkedHashMap<String, String>();
        // 将json字符串转换成jsonObject
        JSONObject jsonObject = JSONObject.fromObject(object);
        Iterator it = jsonObject.keys();
        // 遍历jsonObject数据，添加到Map对象
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            String value = (String) jsonObject.get(key);
            data.put(key, value);
        }
        return data;
    }
}
