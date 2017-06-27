package net.fnsco.controller.app.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.ApiOperation;
import net.fnsco.api.dto.TradeDataDTO;
import net.fnsco.api.trade.TradeDataService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.comm.Constant;

@RestController
@RequestMapping(value = "/open/trade")
public class TradeDataOpenController extends BaseController {
    @Autowired
    private TradeDataService tradeDataService;

    /**
     * 保存拉卡拉交易数据到库
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "保存交易流水")
    public ResultDTO saveTrade(@RequestBody TradeDataDTO tradeData) {
        logger.error("交易流水数据" + JSON.toJSONString(tradeData));
        String amt = tradeData.getAmt();
        amt = amt.replace("/.", "");
        tradeData.setAmt(amt);
        /*0-刷卡
        1-微信
        2-支付宝
        3-银联钱包
        4-百度钱包
        5-京东钱包
        6-拉卡拉钱包*/

        String payType = tradeData.getPayType();
        tradeData.setPayType(Constant.PAY_TYPE_MAP.get(payType));
        tradeData.setPaySubType(Constant.PAY_SUB_TYPE_MAP.get(payType));
        tradeDataService.saveTradeData(tradeData);
        return success();
    }

}