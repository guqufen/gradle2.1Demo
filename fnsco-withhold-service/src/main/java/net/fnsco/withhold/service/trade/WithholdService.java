package net.fnsco.withhold.service.trade;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.SmsUtil;
import net.fnsco.withhold.comm.ApiConstant;
import net.fnsco.withhold.comm.ServiceConstant;
import net.fnsco.withhold.service.trade.dao.TradeDataDAO;
import net.fnsco.withhold.service.trade.dao.WithholdInfoDAO;
import net.fnsco.withhold.service.trade.entity.TradeDataDO;
import net.fnsco.withhold.service.trade.entity.WithholdInfoDO;
import net.fnsco.withhold.service.trade.pay.ainpay.ANOrderPaymentService;

@Service
public class WithholdService extends BaseService {

    private Logger                logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TradeDataDAO          tradeDataDAO;
    @Autowired
    private WithholdInfoDAO       withholdInfoDAO;
    @Autowired
    private ANOrderPaymentService aNOrderPaymentService;
    @Autowired
    private Environment           env;

    /**
     * 
     * 扣款通知：提醒时间：到期日提前三天 ，中午12点
     * collectPayment:(这里用一句话描述这个方法的作用)
     *
     * @param type   void    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public void collectPaymentRemind() {
        String dayStr = DateUtils.getAfterDayStr();
        String monthStr = DateUtils.getNowMonthStr();
        logger.debug("开始提醒" + dayStr);
        List<WithholdInfoDO> withholdInfoList = withholdInfoDAO.getByDebitDay(dayStr);
        for (WithholdInfoDO withholdInfo : withholdInfoList) {
            try {
                String day = withholdInfo.getDebitDay();
                int temp = Integer.parseInt(day);
                day = String.valueOf(temp);
                String amount = withholdInfo.getAmount().divide(new BigDecimal("100")).toString();
                SmsUtil.withholdRemind(withholdInfo.getMobile(), withholdInfo.getUserName(), amount, monthStr, day);
            } catch (Exception ex) {
                logger.error("扣款提醒短信发送失败", ex);
            }
        }
    }

    /**
     * 
     * type 1,2,3 2.扣款时间：早上 9点  下午 1点   下午5点 ，若第一次扣款没有成功，第二次继续请求，第二次没有成功，第三次继续请求。
     * collectPayment:(这里用一句话描述这个方法的作用)
     *
     * @param type   void    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    // 代收扣
    public void collectPayment(int type) {
        String dayStr = DateUtils.getNowDateDayStr();
        String monthStr = DateUtils.getNowDateMonthStr() + "-" + dayStr;
        String startDate = DateUtils.getNowYMDStr();
        logger.debug(type + "开始代扣" + dayStr);
        List<WithholdInfoDO> withholdInfoList = withholdInfoDAO.getByDebitDayFail(dayStr, type,startDate);
        for (WithholdInfoDO withholdInfo : withholdInfoList) {
            TradeDataDO tradeData = new TradeDataDO();
            TradeDataDO temp = tradeDataDAO.getByWithholdId(withholdInfo.getId(), monthStr);
            if (type == 0) {
                if (null != temp) {
                    logger.error("重复扣款，已忽略本次扣款" + withholdInfo.getUserName());
                    continue;
                }

            } else if (type > 0) {
                temp.setStatus(ServiceConstant.PayStatusEnum.PAY_RE_FAIL.getCode());
                tradeDataDAO.update(temp);
            }
            init(tradeData, withholdInfo);
            tradeData.setWithholdDate(monthStr);
            tradeData.setPayTimes(type + 1);
            tradeDataDAO.insert(tradeData);
            TradeDataDO result = aNOrderPaymentService.collectPaymentSendPost(tradeData);
            if (null == result) {
                //发送短信
                payFail(result, withholdInfo, "调用爱农代收接口失败", type);
                logger.error("调用爱农出错");
                return;
            }
            String respCode = result.getRespCode();
            if (ServiceConstant.AnPayResultEnum.AN_PAY_SUCC.getCode().equals(respCode)) {
                paySuccess(result, withholdInfo, type);
            } else {
                String failReason = ServiceConstant.anErrorMap.get(respCode);
                payFail(result, withholdInfo, failReason, type);
            }
        }
    }

    private void paySuccess(TradeDataDO result, WithholdInfoDO withholdInfo, int type) {
        String monthStr = DateUtils.getNowMonthStr();
        try {
            String amount = withholdInfo.getAmount().divide(new BigDecimal("100")).toString();
            SmsUtil.withholdSucc(withholdInfo.getMobile(), monthStr, amount);
        } catch (Exception ex) {
            logger.error("扣款成功短信发送失败", ex);
        }
        try {
            result.setStatus(ServiceConstant.PayStatusEnum.PAY_SUCC.getCode());
            tradeDataDAO.update(result);
        } catch (Exception ex) {
            logger.error(">>>>>>>>扣款成功更新流水状态出错", ex);
        }
        try {
            BigDecimal amount = withholdInfo.getAmount();
            BigDecimal amountTotal = withholdInfo.getAmountTotal();
            //已扣金额加本次扣款额=已扣总金额
            amountTotal = amountTotal.add(amount);
            withholdInfo.setAmountTotal(amountTotal);

            String startDate = withholdInfo.getEndDate();
            String nowDate = DateUtils.getNowDateStr2();
            //相等则完成扣款
            if (startDate.equals(nowDate)) {
                withholdInfo.setStatus(2);
            }
            withholdInfo.setFailTotal(0);
            withholdInfoDAO.update(withholdInfo);
        } catch (Exception ex) {
            logger.error(">>>>>>>>扣款成功更新扣款信息中的失败次数、已扣总金额、状态出错", ex);
        }
    }

    private void payFail(TradeDataDO result, WithholdInfoDO withholdInfo, String failReason, int type) {
        try {
            SmsUtil.withholdFail(withholdInfo.getMobile(), withholdInfo.getUserName());
        } catch (Exception ex) {
            logger.error("扣款失败短信发送失败", ex);
        }
        try {
            result.setStatus(ServiceConstant.PayStatusEnum.PAY_FAIL.getCode());
            result.setFailReason(failReason);
            tradeDataDAO.update(result);
        } catch (Exception ex) {
            logger.error(">>>>>>>>扣款失败更新流水状态出错", ex);
        }
        try {
            withholdInfo.setFailTotal(type + 1);
            if (type == 2) {//最后一次失败则为0
                withholdInfo.setFailTotal(0);
            }
            String startDate = withholdInfo.getEndDate();
            String nowDate = DateUtils.getNowDateStr2();
            //相等则完成扣款
            if (startDate.equals(nowDate)) {
                withholdInfo.setStatus(2);
            }
            withholdInfoDAO.update(withholdInfo);
        } catch (Exception ex) {
            logger.error(">>>>>>>>扣款失败更新扣款信息中的失败次数出错", ex);
        }
    }

    private void init(TradeDataDO tradeData, WithholdInfoDO withholdInfo) {
        String merId = env.getProperty(ApiConstant.MER_ID);
        String anBackURL = env.getProperty("anBackURL");
        tradeData.setMerId(merId);
        tradeData.setTxnType("11");// 交易类型
        tradeData.setTxnSubType("01");// 交易子类型
        tradeData.setBizType("000501");// 产品类型
        tradeData.setAccessType("0");// 接入类型
        tradeData.setCurrency("CNY");// 交易币种
        tradeData.setBackUrl(anBackURL);// 后台通地址
        tradeData.setPayType("0501");// 支付方式

        String orderSn = createOrderSN(withholdInfo);
        tradeData.setStatus(ServiceConstant.PayStatusEnum.PAYING.getCode());
        tradeData.setWithholdId(withholdInfo.getId());
        tradeData.setOrderSn(orderSn);
        //paras.put("merOrderId"// 商户订单号
        tradeData.setBankCard(withholdInfo.getBankCard());
        //paras.put("accNo", //银行卡卡号
        tradeData.setAccType(withholdInfo.getAccType());
        //paras.put("accType"//账号类型：01：借记卡03：存折04：公司账号
        tradeData.getSubBankName();
        //jo.put("iss_ins_name", tradeDataDO.getSubBankName());//开户支行名称
        tradeData.setSubBankName(withholdInfo.getSubBankName());
        // paras.put("txnTime", tradeDataDO.getTxnTime());// 订单发送时间
        tradeData.setTxnTime(DateUtils.getNowDateStr());
        //paras.put("txnAmt", tradeDataDO.getTxnAmt().toString());// 交易金额（分）
        tradeData.setTxnAmt(withholdInfo.getAmount());
        //jo.put("certifTp", tradeDataDO.getCertifType()); //证件类型   身份证 01
        tradeData.setCertifType(withholdInfo.getCertifType());
        //jo.put("certify_id", tradeDataDO.getCertifyId());// 证号
        tradeData.setCertifyId(withholdInfo.getCertifyId());
        //jo.put("phoneNo", tradeDataDO.getMobile());//手机号
        tradeData.setMobile(withholdInfo.getMobile());
        //jo.put("customerNm", tradeDataDO.getUserName());//姓名
        tradeData.setUserName(withholdInfo.getUserName());
        //paras.put("bankId", tradeDataDO.getAnBankId());// 银行编号
        tradeData.setAnBankId(withholdInfo.getAnBankId());
        //paras.put("ppFlag", tradeDataDO.getAccountType());//  对公对私标志00：对公 01：对私
        tradeData.setAccountType(withholdInfo.getAccountType());

        tradeData.setSubject("");
        tradeData.setBody("");
    }

    private String createOrderSN(WithholdInfoDO withholdInfo) {
        String order_sn = "";
        String mobile = withholdInfo.getMobile();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String rand = getFixLenthString(3);
        order_sn = simpleDateFormat.format(new Date()) + mobile + rand;
        String pre = this.env.getProperty("fns.order.sn.pre");
        if (Strings.isNullOrEmpty(pre)) {
            pre = "";
        }
        logger.error("订单前缀为" + pre);
        return pre + order_sn;
    }

    private static String getFixLenthString(int strLength) {

        Random rm = new Random();

        // 获得随机数  
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

        // 将获得的获得随机数转化为字符串  
        String fixLenthString = String.valueOf(pross);

        // 返回固定的长度的随机数  
        return fixLenthString.substring(1, strLength + 1);
    }

}