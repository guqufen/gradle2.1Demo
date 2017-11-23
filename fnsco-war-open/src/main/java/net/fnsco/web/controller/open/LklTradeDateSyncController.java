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
import org.springframework.web.bind.annotation.RequestParam;
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
    private final static String publickeyFile = "rsa_public_key.pem";
    private final static String charset       = "UTF-8";

    /**
     * 同步数据
     * 同步数据示例
     * {
    "mch_id": "822491007630166",
    "device_info": "822491007630166",
    "nonce_str": "822491007630166",
    "sign": "36800",
    "result_code": "822491007630166",
    "err_code": "822491007630166",
    "err_code_des": "822491007630166",
    "openid": "822491007630166",
    "trade_type": "822491007630166",
    "total_fee": 49100763,
    "transaction_id": "822491007630166",
    "out_trade_no": "822491007630166",
    "attach": "822491007630166",
    "time_end": "822491007630166",
    "Pay_type": "822491007630166",
    "card_no": "822491*****0166",
    "pay_amt": 910076,
    "batchbillno": "822491007630166",
    "systraceno": "822491007630166",
    "orderid_scan": "822491007630166",
    "refernumber": "822491007630166",
    "bank_type": "822491007630166",
    "fee_type": "822491007630166",
    "cash_fee": 22491007,
    "cash_fee_type": "822491007630166",
    "coupon_fee": 82249100,
    "coupon_count": 9,
    "coupon_id_$n": "822491007630166",
    "coupon_fee_$n": 910076,
    "is_subscribe": "822491007630166"
    } 
    */
    @RequestMapping("/transtradeSave")
    @ResponseBody
    public Object transtradeSave(@RequestParam String sign, @RequestParam String data) {
        logger.error("拉卡拉同步数据输入参数：" + data);
        LklTradeDataJO DataJO = JSON.parseObject(data, LklTradeDataJO.class);
        Map<String, String> dataMap = toLinkedHashMap(data);
        String path = request.getServletContext().getRealPath("WEB-INF/" + publickeyFile);
        // 获取到的公匙
        String publicKey = getPublicKey(path);
        // 把数字证书放到map里准备验签
        dataMap.put("sign", sign);
        // 验签返回结果true或false
        boolean unsignReslut = true;
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
            return jsonObject;
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
            String value = String.valueOf(jsonObject.get(key));
            data.put(key, value);
        }
        return data;
    }
}
