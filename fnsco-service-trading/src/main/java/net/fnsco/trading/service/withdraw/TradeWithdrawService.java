package net.fnsco.trading.service.withdraw;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.CodeUtil;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.dby.AESUtil;
import net.fnsco.core.utils.dby.JHFMd5Util;
import net.fnsco.trading.comm.TradeConstants;
import net.fnsco.trading.service.account.AppAccountBalanceService;
import net.fnsco.trading.service.account.entity.AppAccountBalanceDO;
import net.fnsco.trading.service.bank.entity.AppUserBankDO;
import net.fnsco.trading.service.order.dto.TradeJhfJO;
import net.fnsco.trading.service.third.ticket.entity.TicketOrderDO;
import net.fnsco.trading.service.withdraw.dao.TradeWithdrawDAO;
import net.fnsco.trading.service.withdraw.dto.MonthWithdrawCountDTO;
import net.fnsco.trading.service.withdraw.entity.TradeWithdrawDO;

@Service
public class TradeWithdrawService extends BaseService {

    private Logger                   logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TradeWithdrawDAO         tradeWithdrawDAO;
    @Autowired
    private AppAccountBalanceService appAccountBalanceService;
    @Autowired
    private Environment              env;

    // 查询
    public TradeWithdrawDO queryOneByOrderId(String orderNo) {
        TradeWithdrawDO obj = this.tradeWithdrawDAO.queryByOrderId(orderNo);
        return obj;
    }

    // 分页
    public ResultPageDTO<TradeWithdrawDO> page(TradeWithdrawDO tradeWithdraw, Integer pageNum, Integer pageSize) {
        logger.info("开始分页查询TradeWithdrawService.page, tradeWithdraw=" + tradeWithdraw.toString());
        if (!StringUtils.isEmpty(tradeWithdraw.getStartTime())) {
        	tradeWithdraw.setStartCreateTime(DateUtils.StrToDate(DateUtils.getDateStartTime(tradeWithdraw.getStartTime())));
        }
        if (!StringUtils.isEmpty(tradeWithdraw.getEndTime())) {
        	tradeWithdraw.setEndCreateTime(DateUtils.StrToDate(DateUtils.getDateEndTime(tradeWithdraw.getEndTime())));
        }
        List<TradeWithdrawDO> pageList = this.tradeWithdrawDAO.pageList(tradeWithdraw, pageNum, pageSize);
        Integer count = this.tradeWithdrawDAO.pageListCount(tradeWithdraw);
        ResultPageDTO<TradeWithdrawDO> pager = new ResultPageDTO<TradeWithdrawDO>(count, pageList);
        return pager;
    }

    // 添加

    // 如果方法运行时，已经存在一个事物中，那么加入该事物，否则为自己创建一个新事物。
    @Transactional(propagation = Propagation.REQUIRED)
    // @Transactional(isolation = Isolation.DEFAULT)
    public TradeWithdrawDO doAdd(TradeWithdrawDO tradeWithdraw) {
        logger.info("开始添加TradeWithdrawService.add,tradeWithdraw=" + tradeWithdraw.toString());
        tradeWithdraw.setCreateTime(new Date());
        tradeWithdraw.setUpdateTime(new Date());
        if (Strings.isNullOrEmpty(tradeWithdraw.getOrderNo())) {
            tradeWithdraw.setOrderNo(CodeUtil.generateOrderCode(""));
        }

        // 设置账户金额
        // 扣除或增加账户余额
        this.tradeWithdrawDAO.insert(tradeWithdraw);
        return tradeWithdraw;
    }

    // 修改
    public Integer doUpdate(TradeWithdrawDO tradeWithdraw) {
        logger.info("开始修改TradeWithdrawService.update,tradeWithdraw=" + tradeWithdraw.toString());
        int rows = this.tradeWithdrawDAO.update(tradeWithdraw);
        return rows;
    }

    public Integer updateOnlyFail(TradeWithdrawDO tradeWithdraw) {
        logger.info("开始修改TradeWithdrawService.update,tradeWithdraw=" + tradeWithdraw.toString());
        int rows = this.tradeWithdrawDAO.updateOnlyFail(tradeWithdraw);
        return rows;
    }

    public Integer researchForSuccess(TradeWithdrawDO tradeWithdraw) {
        logger.info("开始修改TradeWithdrawService.update,tradeWithdraw=" + tradeWithdraw.toString());
        int rows = this.tradeWithdrawDAO.update(tradeWithdraw);
        /**
         * 先判断是否有余额信息数据，如果没有则添加
         */
        appAccountBalanceService.doQueryById(tradeWithdraw.getAppUserId());
        BigDecimal fund = BigDecimal.ZERO.subtract(tradeWithdraw.getAmount());
        appAccountBalanceService.doFrozenBalance(tradeWithdraw.getAppUserId(), fund);
        return rows;
    }
    
    /**
     * doAccountBalanceWithdrawals:(余额提现)
     *
     * @param  @param appUserId
     * @param  @param cashAccount
     * @param  @param appUserBank
     * @param  @return    设定文件
     * @return boolean    DOM对象
     * @author tangliang
     * @date   2018年2月2日 下午2:00:05
     */
    @Transactional
    public boolean doAccountBalanceWithdrawals(Integer appUserId,String cashAccount,AppUserBankDO appUserBank) {
    	/**
    	 * 余额大于提现余额、支付密码正确，开始提现操作
    	 */
    	TradeWithdrawDO tradeWithdraw = new TradeWithdrawDO();
    	tradeWithdraw.setAmount(new BigDecimal(cashAccount));
    	tradeWithdraw.setAppUserId(appUserId);
    	tradeWithdraw.setBankAccountCardId(appUserBank.getAccountCardId());
    	tradeWithdraw.setBankAccountName(appUserBank.getAccountName());
    	tradeWithdraw.setBankAccountNo(appUserBank.getAccountNo());
    	tradeWithdraw.setBankAccountPhone(appUserBank.getAccountPhone());
    	tradeWithdraw.setBankAccountType(appUserBank.getAccountType());
    	tradeWithdraw.setBankOpenBank(appUserBank.getOpenBank());
    	tradeWithdraw.setBankOpenBankNum(appUserBank.getOpenBankNum());
    	tradeWithdraw.setBankSubBankName(appUserBank.getSubBankName());
    	tradeWithdraw.setStatus(0);
    	tradeWithdraw.setTradeType(2);
    	tradeWithdraw.setTradeSubType(20);
    	tradeWithdraw.setFee(new BigDecimal(0));
    	tradeWithdraw.setRespCode("1000");
    	tradeWithdraw.setRespMsg("提现记录产生");
    	tradeWithdraw.setSuccTime(DateUtils.dateFormat1ToStr(new Date()));
    	this.doAdd(tradeWithdraw);
    	
    	
    	/**
    	 * 减掉余额,利用sql来判断扣除,防止扣除不及时
    	 */
    	boolean result = appAccountBalanceService.updateFund(appUserId,new BigDecimal(cashAccount));
    	if(!result) {
    		tradeWithdraw.setStatus(2);
    		tradeWithdraw.setRespCode("1002");
        	tradeWithdraw.setRespMsg("提现失败");
    		this.doUpdate(tradeWithdraw);
    		return false;
    	}else {
    		tradeWithdraw.setStatus(1);
    		tradeWithdraw.setRespMsg("提现进行中");
    		this.doUpdate(tradeWithdraw);
    	}
    	
    	return true;
    }
    
    @Transactional
    public Integer updateFund(TradeWithdrawDO tradeWithdraw) {
        logger.info("开始修改TradeWithdrawService.update,tradeWithdraw=" + tradeWithdraw.toString());
        int rows = this.tradeWithdrawDAO.update(tradeWithdraw);
        BigDecimal fund = BigDecimal.ZERO.subtract(tradeWithdraw.getAmount());
        appAccountBalanceService.updateFund(tradeWithdraw.getAppUserId(), fund);
        return rows;
    }

    // 删除
    public Integer doDelete(TradeWithdrawDO tradeWithdraw, Integer loginUserId) {
        logger.info("开始删除TradeWithdrawService.delete,tradeWithdraw=" + tradeWithdraw.toString());
        int rows = this.tradeWithdrawDAO.deleteById(tradeWithdraw.getId());
        return rows;
    }

    // 查询
    public TradeWithdrawDO doQueryById(Integer id) {
        TradeWithdrawDO obj = this.tradeWithdrawDAO.getById(id);
        return obj;
    }

    // 查询
    public TradeWithdrawDO doQueryByOriginalOrderNo(String orderNo) {
        TradeWithdrawDO obj = this.tradeWithdrawDAO.getByOriginalOrderNo(orderNo);
        return obj;
    }

    public List<MonthWithdrawCountDTO> doQueryTotalAmountGroupByMouth(Integer appUserId, String tradeMonth, List<Integer> status) {
        return tradeWithdrawDAO.queryTotalAmount(appUserId, tradeMonth, status);
    }

    /**
     * 按交易类型查询正在进行的交易列表，便于定时查询交易状态(按照时间大到小)
     * 
     * @param start：查询开始时间
     * @param type：交易类型
     * @return
     */
    public List<TradeWithdrawDO> queryOngoing(Date start, Integer type) {
        return tradeWithdrawDAO.queryOngoing(start, type);
    }

    public void doAddForTicket(TicketOrderDO order) {
        AppAccountBalanceDO appAccountBalance = appAccountBalanceService.doQueryByAppUserId(order.getAppUserId());
        TradeWithdrawDO tradeWithdraw = new TradeWithdrawDO();
        tradeWithdraw.setAmount(order.getOrderAmount());
        tradeWithdraw.setAppUserId(order.getAppUserId());
        tradeWithdraw.setOriginalOrderNo(order.getOrderNo());
        // tradeWithdraw.setChannelMerId(channelMerId);
        // tradeWithdraw.setFee(fee);
        tradeWithdraw.setFund(appAccountBalance.getFund());
        tradeWithdraw.setStatus(0);
        tradeWithdraw.setTradeSubType(TradeConstants.TxnSubTypeEnum.BUY_HCP.getCode());
        tradeWithdraw.setTradeType(TradeConstants.TradeTypeEnum.WITHDRAW.getCode());
        tradeWithdraw.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());
        // tradeWithdraw.setRespMsg(respMsg);
        doAdd(tradeWithdraw);
    }

    public void doRefundForTicket(TicketOrderDO order) {
        AppAccountBalanceDO appAccountBalance = appAccountBalanceService.doQueryByAppUserId(order.getAppUserId());
        TradeWithdrawDO tradeWithdraw = new TradeWithdrawDO();
        tradeWithdraw.setAmount(order.getOrderAmount());
        tradeWithdraw.setAppUserId(order.getAppUserId());
        // tradeWithdraw.setChannelMerId(channelMerId);
        // tradeWithdraw.setFee(fee);
        tradeWithdraw.setFund(appAccountBalance.getFund());
        tradeWithdraw.setTradeType(TradeConstants.TxnSubTypeEnum.REFUND_HCP.getCode());
        tradeWithdraw.setTradeSubType(TradeConstants.TradeTypeEnum.INCOME.getCode());
        tradeWithdraw.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());
        tradeWithdraw.setStatus(0);
        doAdd(tradeWithdraw);
    }

    public String getReqData(TradeWithdrawDO tradeOrder, String payNotifyUrl, String payCallBackUrl) {
        String keyStr = env.getProperty("jhf.api.AES.key");
        // commID 商户Id
        // thirdPayNo 订单号
        // payAmount 支付金额
        // npr 分期数
        // unionId 客户ID
        // transTime 交易时间
        // payNotifyUrl 通知URL
        // payCallBackUrl 支付结束的回调URL
        // payCallBackParams 支付成功后通知参数
        // singData MD5签名
        String createTimerStr = DateUtils.dateFormat1ToStr(tradeOrder.getCreateTime());
        String payCallBackParams = JSON.toJSONString(tradeOrder);
        // MD5(商户Id+订单号+支付金额+分期数+交易时间+通知URL+回调URL+通知参数)
        BigDecimal amountTemp = tradeOrder.getAmount();
        BigDecimal amountTemps = amountTemp.divide(new BigDecimal("100"));
        String singDataStr = tradeOrder.getChannelMerId() + tradeOrder.getOrderNo() + amountTemps.toString() + String.valueOf(tradeOrder.getInstallmentNum()) + createTimerStr + payNotifyUrl
                             + payCallBackUrl;
        logger.error("签名前数据" + singDataStr);
        String singData = JHFMd5Util.encode32(singDataStr);
        logger.error("签名" + singData);
        TradeJhfJO jhfJO = new TradeJhfJO();
        jhfJO.setCommID(tradeOrder.getChannelMerId());
        jhfJO.setPeriodNum(String.valueOf(tradeOrder.getInstallmentNum()));

        jhfJO.setPayAmount(amountTemps.toString());
        jhfJO.setPayCallBackParams("");
        jhfJO.setPayCallBackUrl(payCallBackUrl);// payCallBackUrl
        jhfJO.setPayNotifyUrl(payNotifyUrl);
        jhfJO.setSingData(singData);
        jhfJO.setThirdPayNo(tradeOrder.getOrderNo());
        jhfJO.setTransTime(createTimerStr);
        jhfJO.setUnionId(String.valueOf(tradeOrder.getAppUserId()));
        String reqData = "";
        String dateTemp = JSON.toJSONString(jhfJO);
        try {
            reqData = URLEncoder.encode(AESUtil.encode(dateTemp, keyStr), "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("生成分期付url时AES加密出错", e);
        }
        return reqData;
    }

    /**
     * 通过订单号查找
     * @param orderNo
     * @return
     */
    public TradeWithdrawDO getByOrderNo(String orderNo) {

        return tradeWithdrawDAO.getByOrderNo(orderNo);
    }

    /**
     * 通过订单号查找最新的一条
     * @param orderNo
     * @return
     */
    public TradeWithdrawDO getUndoByOrderNo(String orderNo) {
        return tradeWithdrawDAO.getUndoByOrderNo(orderNo);
    }
    
    /**
     * doAlipayRechangeNotify:(处理支付宝充值回调订单状态)
     *
     * @param  @param params
     * @param  @param isPaySuccess    设定文件
     * @return void    DOM对象
     * @author tangliang
     * @date   2018年2月2日 下午2:27:32
     */
    @Transactional
    public void doAlipayRechangeNotify(Map<String, String> params,boolean isPaySuccess,TradeWithdrawDO tradeWithdraw) {
		//失败
		if(!isPaySuccess) {
			tradeWithdraw.setStatus(2);
			tradeWithdraw.setRespCode("1002");
			tradeWithdraw.setUpdateTime(new Date());
			tradeWithdraw.setOriginalOrderNo(params.get("trade_no"));//支付宝交易凭证号
			tradeWithdraw.setAmount(new BigDecimal(params.get("total_amount")).multiply(new BigDecimal(100)));
			tradeWithdraw.setOrderAmount(tradeWithdraw.getAmount());
			tradeWithdraw.setSuccTime(DateUtils.dateFormat1ToStr(new Date()));
			tradeWithdraw.setRespMsg("支付宝充值失败!未付款或关闭");
			this.doUpdate(tradeWithdraw);
		}else {
			/**
			 * 充值成功后，需要在帐号上增加余额
			 */
			Integer appUserId = tradeWithdraw.getAppUserId();
			BigDecimal fund = new BigDecimal(params.get("total_amount")).multiply(new BigDecimal(100));
			appAccountBalanceService.doQueryByAppUserId(appUserId);
			appAccountBalanceService.updateFund(appUserId,BigDecimal.ZERO.subtract(fund));
			
			/**
			 * 更新订单信息
			 */
			tradeWithdraw.setStatus(3);
			tradeWithdraw.setRespCode("1001");
			tradeWithdraw.setUpdateTime(new Date());
			tradeWithdraw.setOriginalOrderNo(params.get("trade_no"));//支付宝交易凭证号
			tradeWithdraw.setSuccTime(DateUtils.dateFormat1ToStr(new Date()));
			tradeWithdraw.setRespMsg("支付宝充值成功");
			this.doUpdate(tradeWithdraw);
		}
    }
 
}