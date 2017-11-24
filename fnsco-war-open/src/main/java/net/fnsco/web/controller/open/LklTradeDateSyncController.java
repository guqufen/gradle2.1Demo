package net.fnsco.web.controller.open;

import java.io.BufferedReader;
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
import com.lakala.sign.LKLSignature;

import io.swagger.annotations.Api;
import net.fnsco.bigdata.api.constant.BigdataConstant;
import net.fnsco.bigdata.comm.ServiceConstant;
import net.fnsco.core.base.BaseController;
import net.fnsco.order.service.trade.TradeDataLklService;
import net.fnsco.order.service.trade.entity.TradeDataLklDO;
import net.fnsco.trading.comm.TradeConstants;
import net.fnsco.web.controller.open.jo.LklTradeDataJO;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/syncData/lkl", method = RequestMethod.POST)
@Api(value = "/syncData/lkl", tags = { "拉卡拉交易实时传输交口" })
public class LklTradeDateSyncController extends BaseController {
    @Autowired
    private TradeDataLklService tradeDataLklService;
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
    
    源数据date:{"mch_id":"822121063000020","device_info":"98673463","result_code":"SUCCESS","trade_type":"012001","total_fee":"000000000000002","transaction_id":"17072551699348","out_trade_no":"201710010005462173","time_end":"20170725165108","pay_type":"00","card_no":"622384*******9421","pay_amt":"000000000000002","batchbillno":"000003","systraceno":"000013","orderid_scan":"","bank_type":"03110000","refernumber":"072551699348","coupon_fee":"","openid":""}
    私匙加签sign:PakEuHGvFDthtdqnKKc0POjDQ2FgaAZ98eoChRaipaQ+VMBjIVfhEKG0qKM2JoEpl9olk0wGULgQvApOZpX6f+1jhl4CKp4ZR1vLrQkRI3hihONO+8GkWZyTMDx4WFpMxyCzIFP0NvvjJsjw3hM+SCMJ6vF/lVipcVgnWXPotxw=
    */
    @RequestMapping("/transtradeSave")
    @ResponseBody
    public Object transtradeSave(@RequestParam String sign, @RequestParam String data) {
        logger.error("拉卡拉同步数据输入参数：" + data);
        LklTradeDataJO dataJO = JSON.parseObject(data, LklTradeDataJO.class);
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
        TradeDataLklDO tradeDataLkl = new TradeDataLklDO();
        tradeDataLkl.setMerId(dataJO.getMch_id());
        if ("SUCCESS".equals(dataJO.getResult_code())) {
            tradeDataLkl.setRespCode(ServiceConstant.TradeStateEnum.SUCCESS.getCode());
        } else {
            tradeDataLkl.setRespCode(ServiceConstant.TradeStateEnum.FAIL.getCode());
        }
        tradeDataLkl.setRespMsg(dataJO.getErr_code() + dataJO.getErr_code_des());
        tradeDataLkl.setAmt(dataJO.getTotal_fee());
        tradeDataLkl.setTxnType(TradeConstants.TXT_TYPE_MAP.get(dataJO.getTrade_type()));
        tradeDataLkl.setTxnSubType(dataJO.getTrade_type());//交易类型
        tradeDataLkl.setOrderNo(dataJO.getTransaction_id());
        tradeDataLkl.setTimeStamp(dataJO.getTime_end());
        tradeDataLkl.setPayType(ServiceConstant.PAY_TYPE_MAP.get(dataJO.getPay_type()));
        tradeDataLkl.setCertifyId(dataJO.getCard_no());
        tradeDataLkl.setBatchNo(dataJO.getBatchbillno());
        tradeDataLkl.setTraceNo(dataJO.getSystraceno());
        tradeDataLkl.setOrderIdScan(dataJO.getOrderid_scan());
        tradeDataLkl.setReferNo(dataJO.getRefernumber());
        tradeDataLkl.setBankId(dataJO.getBank_type());//付款银行
        tradeDataLkl.setSource("06");
        tradeDataLkl.setPayMedium(BigdataConstant.PayMediumEnum.POS.getCode());
        tradeDataLkl.setChannelTermCode(dataJO.getDevice_info());
        tradeDataLkl.setTermId(dataJO.getDevice_info());
        tradeDataLkl.setChannelType(BigdataConstant.ChannelTypeEnum.LKL.getCode());
        tradeDataLklService.doAdd(tradeDataLkl);
        JSONObject jsonObject = new JSONObject();
        if (!unsignReslut) {
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
