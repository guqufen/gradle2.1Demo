package net.fnsco.web.controller.open;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.api.constant.BigdataConstant;
import net.fnsco.bigdata.api.dto.TradeDataDTO;
import net.fnsco.bigdata.api.trade.TradeDataService;
import net.fnsco.bigdata.comm.ServiceConstant;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.HttpUtils;
import net.fnsco.order.api.trade.TradeReportService;

/**
 * 交易流水处理
 * @author sxf
 * @since 2017-06-27
 * Created by sxf on 2017-06-27
 *
 */
@RestController
@RequestMapping(value = "/open/trade", method = RequestMethod.POST)
@Api(value = "/open/merchant", tags = { "交易流水处理接口" })
public class TradeDataOpenController extends BaseController {
    @Autowired
    private TradeDataService   tradeDataService;
    @Autowired
    private TradeReportService tradeReportService;

    /**
     * 保存拉卡拉交易数据到库
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/save")
    @ApiOperation(value = "保存交易流水")
    public ResultDTO saveTrade(@RequestBody TradeDataDTO tradeData) {
        logger.error("交易流水数据" + JSON.toJSONString(tradeData));
        String amt = tradeData.getAmt();
        if (!Strings.isNullOrEmpty(amt)) {
            amt = amt.replaceAll("\\.", "");
            tradeData.setAmt(amt);
        }
        /*
        0-刷卡
        1-微信
        2-支付宝
        3-银联钱包
        4-百度钱包
        5-京东钱包
        6-拉卡拉钱包*/
        String payType = tradeData.getPayType();
        //tradeData.setCardOrg(ConstantEnum.DcTypeEnum.getNameByCode(code));
        tradeData.setPayType(ServiceConstant.PAY_TYPE_MAP.get(payType));
        tradeData.setPaySubType(ServiceConstant.PAY_SUB_TYPE_MAP.get(payType));
        tradeData.setRespCode(ServiceConstant.TradeStateEnum.SUCCESS.getCode());
        tradeData.setPayMedium(BigdataConstant.PayMediumEnum.POS.getCode());
        if ("00".equals(tradeData.getSource())) {
            tradeData.setChannelType(BigdataConstant.ChannelTypeEnum.LKL.getCode());
        }
        tradeData.setChannelTermCode(tradeData.getTermId());
        tradeData.setValidate(ServiceConstant.STR_1);
        tradeDataService.saveTradeData(tradeData);
        String timeStamp = tradeData.getTimeStamp();
        syncReport(timeStamp);
        return success();
    }

    /**
     * 对外交易数据保存到库
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/saveComm")
    @ApiOperation(value = "保存交易流水")
    public ResultDTO saveTradeComm(@RequestBody TradeDataDTO tradeData) {
        logger.error("对外交易流水数据" + JSON.toJSONString(tradeData));
        String amt = tradeData.getAmt();
        if (!Strings.isNullOrEmpty(amt)) {
            amt = amt.replaceAll("\\.", "");
            tradeData.setAmt(amt);
        }
        String payType = tradeData.getPayType();
        //tradeData.setCardOrg(ConstantEnum.DcTypeEnum.getNameByCode(code));
        tradeData.setPayType(ServiceConstant.PAY_TYPE_MAP.get(payType));
        tradeData.setPaySubType(ServiceConstant.PAY_SUB_TYPE_MAP.get(payType));
        tradeData.setRespCode(ServiceConstant.TradeStateEnum.SUCCESS.getCode());
        tradeData.setPayMedium(BigdataConstant.PayMediumEnum.FIX_QR.getCode());
        if ("00".equals(tradeData.getSource())) {
            tradeData.setChannelType(BigdataConstant.ChannelTypeEnum.LKL.getCode());
        }
        tradeData.setChannelTermCode(tradeData.getTermId());
        tradeDataService.saveTradeData(tradeData);
        String timeStamp = tradeData.getTimeStamp();
        syncReport(timeStamp);
        return success();
    }

    private void syncReport(String timeStamp) {
        try {
            if (Strings.isNullOrEmpty(timeStamp)) {
                return;
            }
            if (timeStamp.length() >= 8) {
                timeStamp = timeStamp.substring(0, 8);
            }
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
            String timeStampEnd = "";
            try {
                timeStampEnd = DateUtils.getDateStrYYYYMMDD(sf.parse(timeStamp), 1);
            } catch (ParseException e) {
                logger.error("插入交易流水同步报表数据出错", e);
            }
            String nowDateStr = DateUtils.getDateStrYYYYMMDD(new Date());
            if (!nowDateStr.equals(timeStamp)) {
                logger.error("不是当天的数据则重新生成日报" + timeStamp);
                tradeReportService.buildTradeReportDaTa(timeStamp + "000000", timeStampEnd + "000000");
            }
        } catch (Exception e) {
            logger.error("插入交易流水同步报表数据出错", e);
        }
    }

}
