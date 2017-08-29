package net.fnsco.web.controller.app.trade;

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
import net.fnsco.bigdata.api.dto.TradeDataQueryDTO;
import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.bigdata.api.trade.TradeDataService;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.bigdata.service.domain.trade.TradeData;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.order.comm.ServiceConstant.PaySubTypeAllEnum;
import net.fnsco.order.comm.ServiceConstant.PaySubTypeEnum;
import net.fnsco.order.comm.ServiceConstant.TradeStateEnum;
import net.fnsco.order.comm.ServiceConstant.TradeTypeEnum;
import net.fnsco.web.controller.app.jo.TradeDataJO;

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
                jo.setPayType(tradeData.getPaySubType());
                jo.setPayTypeName(PaySubTypeEnum.getNameByCode(jo.getPayType()));
                String payTypeStr = PaySubTypeEnum.getNameByCode(jo.getPayType());
                if(Strings.isNullOrEmpty(payTypeStr)){
                    jo.setPayType("99");
                    jo.setPayTypeName("其它");
                }
                jo.setStatus(tradeData.getRespCode());
                String statusName =TradeStateEnum.getNameByCode(jo.getStatus());
                String payType =tradeData.getPaySubType();
                if("1".equals(tradeData.getTxnType())){//交易类型1消费2撤销
                    if("00".equals(payType)){//交易子类型00刷卡01微信02支付宝
                        jo.setStatusName("刷卡"+statusName);
                    }else{
                        jo.setStatusName("扫码"+statusName);
                    }
                }else if("2".equals(tradeData.getTxnType())){//交易类型1消费2撤销
                    jo.setStatusName("撤销"+statusName);
                }
                
                jo.setId(tradeData.getId());
                jo.setTradeTime(tradeData.getTimeStamp());
                jo.setTxnType(tradeData.getTxnType());
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
        TradeDataJO result = new TradeDataJO();
        TradeData tradeData = tradeDataService.queryByTradeId(tradeQueryDTO.getTradeId());
        if(null == tradeData){
            return ResultDTO.success(result);
        }
        int count = tradeDataService.selectCountByIRT(tradeData);
        if(count>1){
            result.setCancel("1");
        }else{
            result.setCancel("0");
        }
        if("2".equals(tradeData.getTxnType())){
            result.setCancel("0");
        }
        MerchantCore merchantCore = merchantCoreService.queryByInnerCode(tradeData.getInnerCode());
        
        result.setAmount(getAtm(tradeData.getAmt()));
        result.setPayType(tradeData.getPaySubType());
        result.setPayTypeName(PaySubTypeAllEnum.getNameByCode(result.getPayType()));
        result.setStatus(tradeData.getRespCode());
        String statusName =TradeStateEnum.getNameByCode(result.getStatus());
        String payType =tradeData.getPaySubType();
        if("1".equals(tradeData.getTxnType())){//交易类型1消费2撤销
            if("00".equals(payType)){//交易子类型00刷卡01微信02支付宝
                result.setStatusName("刷卡"+statusName);
            }else{
                result.setStatusName("扫码"+statusName);
            }
        }else if("2".equals(tradeData.getTxnType())){//交易类型1消费2撤销
            result.setStatusName("撤单"+statusName);
        }
        //result.setStatusName(TradeStateEnum.getNameByCode(result.getStatus()));
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
        //result.setTxnType(tradeData.getTxnType());
        return ResultDTO.success(result);
    }

}
