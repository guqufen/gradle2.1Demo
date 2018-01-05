package net.fnsco.trading.service.withdraw;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

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
import net.fnsco.trading.service.order.dto.TradeJhfJO;
import net.fnsco.trading.service.third.ticket.entity.TicketOrderDO;
import net.fnsco.trading.service.withdraw.dao.TradeWithdrawDAO;
import net.fnsco.trading.service.withdraw.dto.MonthWithdrawCountDTO;
import net.fnsco.trading.service.withdraw.entity.TradeWithdrawDO;

@Service
public class TradeWithdrawService extends BaseService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TradeWithdrawDAO tradeWithdrawDAO;
	@Autowired
	private AppAccountBalanceService appAccountBalanceService;
	@Autowired
	private Environment env;

	// 查询
	public TradeWithdrawDO queryOneByOrderId(String orderNo) {
		TradeWithdrawDO obj = this.tradeWithdrawDAO.queryByOrderId(orderNo);
		return obj;
	}

	// 分页
	public ResultPageDTO<TradeWithdrawDO> page(TradeWithdrawDO tradeWithdraw, Integer pageNum, Integer pageSize) {
		logger.info("开始分页查询TradeWithdrawService.page, tradeWithdraw=" + tradeWithdraw.toString());
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
		BigDecimal fund = BigDecimal.ZERO.subtract(tradeWithdraw.getAmount());
		appAccountBalanceService.doFrozenBalance(tradeWithdraw.getAppUserId(), fund);
		return rows;
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

	public List<MonthWithdrawCountDTO> doQueryTotalAmountGroupByMouth(Integer appUserId, String tradeMonth,
			Integer status) {
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
		String singDataStr = tradeOrder.getChannelMerId() + tradeOrder.getOrderNo() + amountTemps.toString()
				+ String.valueOf(tradeOrder.getInstallmentNum()) + createTimerStr + payNotifyUrl + payCallBackUrl;
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
}