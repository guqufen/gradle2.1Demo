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

import com.beust.jcommander.internal.Maps;
import com.lakala.sign.LKLApiException;
import com.lakala.sign.LKLSignature;

import io.swagger.annotations.Api;
import net.fnsco.bigdata.api.trade.TradeDataService;
import net.fnsco.core.base.BaseController;
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
            System.out.println(unsignReslut);
        } catch (LKLApiException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getErrMsg());
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        if (unsignReslut) {
            //客户业务              
            System.out.println("mch_id         = " + dataMap.get("mch_id")); //商户号                          
            System.out.println("device_info    = " + dataMap.get("device_info")); //设备号                          
            System.out.println("nonce_str      = " + dataMap.get("nonce_str")); //随机字符串                      
            System.out.println("sign           = " + dataMap.get("sign")); //签名                            
            System.out.println("result_code    = " + dataMap.get("result_code")); //业务结果                        
            System.out.println("err_code       = " + dataMap.get("err_code")); //错误代码                        
            System.out.println("err_code_des   = " + dataMap.get("err_code_des")); //错误代码描述                    
            System.out.println("openid         = " + dataMap.get("openid")); //用户标识                        
            System.out.println("trade_type     = " + dataMap.get("trade_type")); //交易类型                        
            System.out.println("total_fee      = " + dataMap.get("total_fee")); //总金额                          
            System.out.println("transaction_id = " + dataMap.get("transaction_id")); //商户支付订单号                  
            System.out.println("out_trade_no   = " + dataMap.get("out_trade_no")); //商户订单号                      
            System.out.println("attach         = " + dataMap.get("attach")); //商家数据包                      
            System.out.println("time_end       = " + dataMap.get("time_end")); //支付完成时间                    
            System.out.println("Pay_type       = " + dataMap.get("Pay_type")); //支付方式                        
            System.out.println("card_no        = " + dataMap.get("card_no")); //交易卡号                        
            System.out.println("pay_amt        = " + dataMap.get("pay_amt")); //支付金额                        
            System.out.println("batchbillno    = " + dataMap.get("batchbillno")); //批次号                          
            System.out.println("systraceno     = " + dataMap.get("systraceno")); //凭证号                          
            System.out.println("orderid_scan   = " + dataMap.get("orderid_scan")); //扫码订单号                      
            System.out.println("refernumber    = " + dataMap.get("refernumber")); //系统参考号                      
            System.out.println("bank_type      = " + dataMap.get("bank_type")); //付款银行                        
            System.out.println("fee_type       = " + dataMap.get("fee_type")); //货币种类                        
            System.out.println("cash_fee       = " + dataMap.get("cash_fee")); //现金支付金额                    
            System.out.println("cash_fee_type  = " + dataMap.get("cash_fee_type")); //现金支付货币类型                
            System.out.println("coupon_fee     = " + dataMap.get("coupon_fee")); //代金券或立减优惠金额            
            System.out.println("coupon_count   = " + dataMap.get("coupon_count")); //代金券或立减优惠使用数量        
            System.out.println("coupon_id_$n   = " + dataMap.get("coupon_id_$n")); //代金券或立减优惠ID              
            System.out.println("coupon_fee_$n  = " + dataMap.get("coupon_fee_$n")); //单个代金券或立减优惠支付金额    
            System.out.println("is_subscribe   = " + dataMap.get("is_subscribe")); //是否关注公众账号                
            //验签成功，接收成功返回SUCCESS
            jsonObject.element("return_code", "SUCCESS");
            jsonObject.element("return_msg", "ok");

        } else {
            //验签失败，返回FAIL，会执行重发
            jsonObject.element("return_code", "FAIL");
            jsonObject.element("return_msg", "验签失败");
        }
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
