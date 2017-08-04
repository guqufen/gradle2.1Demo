package net.fnsco.withhold.web.open;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.withhold.service.trade.TradeDataService;
import net.fnsco.withhold.service.trade.entity.TradeDataDO;

@Controller
@RequestMapping(value = "/open", method = RequestMethod.POST)
@Api(value = "/open", tags = { "支付回调接口" })
public class PayCallback extends BaseController {
    private static final String[] base64Keys     = new String[] { "subject", "body", "remark", "respMsg", "resv" };
    private static final String[] base64JsonKeys = new String[] { "paras" };
    @Autowired
    private TradeDataService      tradeDataService;

    // 分页
    @ApiOperation(value = "浦发代收回调接口", notes = "浦发代收回调接口")
    @ResponseBody
    @RequestMapping(value = "/pfWithholdCallback", method = RequestMethod.GET)
    public ResultDTO pfWithholdCallback() {
        Map<String, String> parameterMap = getParameterMap();
        transMap(parameterMap);
        logger.error("收到爱农代收扣回调：" + parameterMap);
        String orderId = parameterMap.get("merOrderId");
        if (Strings.isNullOrEmpty(orderId)) {
            logger.error("爱农回调订单号为空");
            return ResultDTO.fail();
        }
        TradeDataDO tradeData = tradeDataService.findByOrderSn(orderId);
        if (tradeData == null) {
            logger.error("爱农回调订单不存在" + orderId);
            return ResultDTO.fail();
        }
        if (parameterMap.containsKey("respCode")) {
            String respCode = parameterMap.get("respCode");
            logger.info("respCode" + respCode);
            if (respCode.trim().equals("1001")) {
                tradeData.setRespCode("1001");
                tradeData.setRespMsg(parameterMap.get("respMsg"));
                tradeDataService.update(tradeData);
            }
        }
        return ResultDTO.success();
    }

    private void transMap(Map<String, String> paras) {
        for (String key : base64Keys) {
            if (paras.containsKey(key)) {
                paras.put(key, new String(Base64.getDecoder().decode(paras.get(key).getBytes())));
            }
        }
        for (String key : base64JsonKeys) {
            if (paras.containsKey(key)) {
                String values = paras.get(key).replace("\r", "").replace("\n", "");
                String bavalue = "";
                try {
                    bavalue = new String(Base64.getDecoder().decode(values.getBytes()), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    logger.error("转换爱农参数异常" + e);
                }
                paras.put(key, bavalue);
            }
        }
    }
}
