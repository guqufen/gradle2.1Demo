package net.fnsco.bigdata.service.modules.trade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fnsco.bigdata.api.dto.TradeDataDTO;
import net.fnsco.bigdata.api.trade.TradeDataService;
import net.fnsco.bigdata.service.domain.trade.TradeData;
import net.fnsco.core.base.ResultDTO;

/**
 * @desc excel上传实现类
 * @author hjt
 * @date 2017年6月21日 上午10:15:40
 */
@Service
public class TradeDataImportService {
    @Autowired
    private TradeDataService tradeDataService;

    // 批量导入交易流水
    @Transactional
    public ResultDTO<String> tradeBatchImportToDB(List<Object[]> customerList) {
        // 循环便利customList数组，将其中excel每一行的数据分批导入数据库
        if (customerList.size() != 0) {
            // excel导出的空数据是“null”，赋值一个空字符串
            int timeNum = 2;
            for (Object[] objs : customerList) {
                timeNum = timeNum + 1;
                for (int i = 0; i < objs.length; i++) {
                    if (objs[i] == null) {
                        objs[i] = "";
                    }
                }
                // 内部商户号
                String innercode = String.valueOf(objs[0]);
                // 交易类型
                String txntype = String.valueOf(objs[1]);
                // 交易子类型
                String txnsubtype = String.valueOf(objs[2]);
                // 交易币种
                String currency = String.valueOf(objs[3]);
                // 应答码
                String respcode = String.valueOf(objs[4]);
                // 清算日期
                String settledate = String.valueOf(objs[5]);
                // 卡号
                String certifyid = String.valueOf(objs[6]);
                // 通道号
                String msgdestid = String.valueOf(objs[7]);
                // 渠道类型
                String channeltype = String.valueOf(objs[8]);
                // 交易金额
                String amt = String.valueOf(objs[9]);
                //支付方式
                String paytype = String.valueOf(objs[10]);
                //交易子类型
                String paysubtype = String.valueOf(objs[11]);
                //交易时间
                String timestamp = String.valueOf(objs[12]);
                //通道商户号
                String merid = String.valueOf(objs[13]);
                //通道终端号
                String termid = String.valueOf(objs[14]);
                //批次号
                String batchno = String.valueOf(objs[15]);
                //终端流水号
                String traceno = String.valueOf(objs[16]);
                //参考号
                String referno = String.valueOf(objs[17]);
                //授权码
                String authcode = String.valueOf(objs[18]);
                //来源
                String source = String.valueOf(objs[19]);
                //交易传送时间
                String sendtime = String.valueOf(objs[20]);
                //记录创建时间
                String createtime = String.valueOf(objs[21]);
                //支付超时时间
                String paytimeout = String.valueOf(objs[22]);
                //商品标题
                String subject = String.valueOf(objs[23]);
                //商品描述
                String body = String.valueOf(objs[24]);
                //银行卡验证信息及身份信息
                String customerinfo = String.valueOf(objs[25]);
                //持卡人IP
                String customerip = String.valueOf(objs[26]);
                //受理订单号
                String tn = String.valueOf(objs[27]);
                //应答信息
                String respmsg = String.valueOf(objs[28]);
                //交易成功时间
                String succtime = String.valueOf(objs[29]);

                //	String innerCode = merchantCoreService.getInnerCode();

                try {
                    TradeDataDTO tradeData = new TradeDataDTO();
                    tradeData.setInnerCode(innercode);
                    tradeData.setTxnType(txntype);
                    tradeData.setTxnSubType(txnsubtype);
                    tradeData.setCurrency(currency);
                    tradeData.setRespCode(respcode);
                    tradeData.setSettleDate(settledate);
                    String type = tradeDataService.queryByCertifyId(certifyid);
                    String dcType = "";
                    if ("DC".equals(type)) {
                        dcType = "00 境内借记卡";
                    } else if ("CC".equals(type)) {
                        dcType = "01 境内货记卡";
                    }
                    tradeData.setDcType(dcType);
                    tradeData.setCertifyId(certifyid);
                    tradeData.setMsgDestId(msgdestid);
                    tradeData.setChannelType(channeltype);
                    tradeData.setAmt(amt);
                    tradeData.setPayType(paytype);
                    tradeData.setPaySubType(paysubtype);
                    tradeData.setTimeStamp(timestamp);
                    tradeData.setMerId(merid);
                    tradeData.setTermId(termid);
                    tradeData.setBatchNo(batchno);
                    tradeData.setSysTraceNo(traceno);
                    tradeData.setReferNo(referno);
                    tradeData.setAuthCode(authcode);
                    tradeData.setSource(source);
                    tradeData.setSendTime(sendtime);
                    tradeData.setPayTimeOut(paytimeout);
                    tradeData.setSubject(subject);
                    tradeData.setBody(body);
                    tradeData.setCustomerInfo(customerinfo);
                    tradeData.setCustomerIp(customerip);
                    tradeData.setTn(tn);
                    tradeData.setRespMsg(respmsg);
                    tradeData.setSuccTime(succtime);
                    //日期格式转换
                    Date createTime = null;
                    if ("".equals(createtime)) {
                        String reg = "(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})";
                        createtime = createtime.replaceAll(reg, "$1-$2-$3 $4:$5:$6");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        createTime = sdf.parse(createtime);
                    }
                    tradeData.setCreateTime(createTime);
                    //交易流水打包导入
                    boolean i = tradeDataService.saveTradeData(tradeData);
                    if (i != true) {
                        return ResultDTO.fail("第" + timeNum + "行交易流水信息有误，导入失败");
                    }
                } catch (Exception e) {
                    return ResultDTO.fail("第" + timeNum + "行交易流水信息有误，导入失败");
                }
            }
            return ResultDTO.success();
        }
        // return b;
        return ResultDTO.fail("没有导入数据，Excel为空");
    }
}
