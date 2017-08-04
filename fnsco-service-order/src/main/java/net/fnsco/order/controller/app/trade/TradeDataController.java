package net.fnsco.order.controller.app.trade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.order.api.dto.TradeDataQueryDTO;
import net.fnsco.order.api.merchant.MerchantCoreService;
import net.fnsco.order.api.trade.TradeDataService;
import net.fnsco.order.controller.app.jo.TradeDataJO;
import net.fnsco.order.service.comm.ServiceConstant.PaySubTypeAllEnum;
import net.fnsco.order.service.comm.ServiceConstant.PaySubTypeEnum;
import net.fnsco.order.service.comm.ServiceConstant.TradeStateEnum;
import net.fnsco.order.service.comm.ServiceConstant.TradeTypeEnum;
import net.fnsco.order.service.domain.MerchantCore;
import net.fnsco.order.service.domain.trade.TradeData;

/**
 * 交易流水处理类
 * @author sxf
 *
 */
@RestController
@RequestMapping(value = "/app/trade", method = RequestMethod.POST)
public class TradeDataController extends BaseController {
    @Autowired
    private TradeDataService    tradeDataService;
    @Autowired
    private MerchantCoreService merchantCoreService;

    /**
     * 查询交易流水信息
     *
     * @param userName
     * @return
     */
    //@ApiIgnore //使用该注解忽略这个API
    @RequestMapping(value = "/queryList")
    @ApiOperation(value = "查询交易流水")
    public ResultDTO queryList(@RequestBody TradeDataQueryDTO tradeQueryDTO) {
        logger.warn("/queryList查询交易流水入参:" + JSON.toJSONString(tradeQueryDTO));
        ResultPageDTO<TradeData> temp = tradeDataService.queryAllByCondition(tradeQueryDTO);
        List<TradeData> list = temp.getList();
        List<TradeDataJO> resultList = Lists.newArrayList();
        if (null != list) {
            for (TradeData tradeData : list) {
                TradeDataJO jo = new TradeDataJO();
                jo.setAmount(getAtm(tradeData.getAmt()));
                String payTypeStr = PaySubTypeEnum.getNameByCode(jo.getPayType());
                if(Strings.isNullOrEmpty(payTypeStr)){
                    jo.setPayType("99");
                    jo.setPayTypeName("其它");
                }else{
                    jo.setPayType(tradeData.getPaySubType());
                    jo.setPayTypeName(PaySubTypeEnum.getNameByCode(jo.getPayType()));
                }
                jo.setStatus(tradeData.getRespCode());
                jo.setStatusName(TradeStateEnum.getNameByCode(jo.getStatus()));
                jo.setId(tradeData.getId());
                jo.setTradeTime(tradeData.getTimeStamp());
                resultList.add(jo);
            }
        }
        ResultPageDTO<TradeDataJO> result = new ResultPageDTO<TradeDataJO>(temp.getTotal(), resultList);
        result.setCurrentPage(temp.getCurrentPage());
        result.setMerTotal(temp.getMerTotal());
        return success(result);
    }
    private String getAtm(String amount){
        if (Strings.isNullOrEmpty(amount)) {
            return "";
        }
        if(amount.length()==1){
            amount="00"+amount;
        }else if(amount.length()==2){
            amount="0"+amount;
        }
        String temp1 = amount.substring(0, amount.length() - 2);
        String temp2 = amount.substring(amount.length() - 2, amount.length());
        return (temp1 + "." + temp2);
    }
    /**
     * 查询交易流水信息
     *
     * @param userName
     * @return
     */
    //@ApiIgnore //使用该注解忽略这个API
    @RequestMapping(value = "/information")
    @ApiOperation(value = "查询交易流水")
    public ResultDTO information(@RequestBody TradeDataQueryDTO tradeQueryDTO) {
        TradeData tradeData = tradeDataService.queryByTradeId(tradeQueryDTO.getTradeId());
        if(null == tradeData){
            TradeDataJO result = new TradeDataJO();
            return ResultDTO.success(result);
        }
        MerchantCore merchantCore = merchantCoreService.queryByInnerCode(tradeData.getInnerCode());
        TradeDataJO result = new TradeDataJO();
        result.setAmount(getAtm(tradeData.getAmt()));
        result.setPayType(tradeData.getPaySubType());
        result.setPayTypeName(PaySubTypeAllEnum.getNameByCode(result.getPayType()));
        result.setStatus(tradeData.getRespCode());
        result.setStatusName(TradeStateEnum.getNameByCode(result.getStatus()));
        result.setId(tradeData.getId());
        result.setTradeTime(tradeData.getTimeStamp());
        if(null != merchantCore){
            result.setMerName(merchantCore.getMerName());
        }else{
            result.setMerName("未知商户");
        }
        result.setInnerCode(tradeData.getInnerCode());
        result.setTradeType(tradeData.getTxnType());
        result.setTradeTypeName(TradeTypeEnum.getNameByCode(result.getTradeType()));

        result.setBatchNo(tradeData.getBatchNo());
        result.setTermId(tradeData.getTermId());
        result.setTraceNo(tradeData.getTraceNo());
        result.setReferNo(tradeData.getReferNo());
        result.setCertifyId(tradeData.getCertifyId());
        result.setOrderNo(tradeData.getOrderNo());
        result.setOrderTime(tradeData.getOrderTime());
        result.setRespMsg(tradeData.getRespMsg());
        result.setOrderIdScan(tradeData.getOrderIdScan());
        return ResultDTO.success(result);
    }

}