package net.fnsco.trading.service.pay.channel.zxyh;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.beust.jcommander.internal.Maps;
import com.google.common.base.Strings;

import net.fnsco.bigdata.api.constant.BigdataConstant;
import net.fnsco.bigdata.api.dto.MercQueryDTO;
import net.fnsco.bigdata.api.dto.MerchantCoreEntityZxyhDTO;
import net.fnsco.bigdata.api.dto.TradeAliPayCallBackDTO;
import net.fnsco.bigdata.api.dto.TradeResultZXDTO;
import net.fnsco.bigdata.api.dto.TradeWeChatCallBackDTO;
import net.fnsco.bigdata.api.merchant.MerchantChannelService;
import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.bigdata.comm.ServiceConstant.PaySubTypeAllEnum;
import net.fnsco.bigdata.comm.ServiceConstant.PayTypeEnum;
import net.fnsco.bigdata.comm.ServiceConstant.TradeStateEnum;
import net.fnsco.bigdata.comm.ServiceConstant.TradeTypeEnum;
import net.fnsco.bigdata.service.dao.master.AppUserMerchant1Dao;
import net.fnsco.bigdata.service.dao.master.MerchantChannelDao;
import net.fnsco.bigdata.service.dao.master.MerchantEntityCoreRefDao;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantEntity;
import net.fnsco.bigdata.service.domain.MerchantEntityCoreRef;
import net.fnsco.bigdata.service.sys.SequenceService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.HttpUtils;
import net.fnsco.trading.comm.TradeConstants;
import net.fnsco.trading.comm.TradeConstants.ZxyhPassivePayCode;
import net.fnsco.trading.comm.TradeConstants.ZxyhPassivePayType;
import net.fnsco.trading.constant.E789ApiConstant;
import net.fnsco.trading.service.merchant.AppUserMerchantService;
import net.fnsco.trading.service.merchantentity.dao.AppUserMerchantEntityDAO;
import net.fnsco.trading.service.merchantentity.entity.AppUserMerchantEntityDO;
import net.fnsco.trading.service.order.TradeOrderService;
import net.fnsco.trading.service.order.dao.TradeOrderDAO;
import net.fnsco.trading.service.order.entity.TradeOrderDO;
import net.fnsco.trading.service.pay.OrderPaymentService;
import net.fnsco.trading.service.pay.channel.zxyh.dto.ActiveAlipayDTO;
import net.fnsco.trading.service.pay.channel.zxyh.dto.ActiveWeiXinDTO;
import net.fnsco.trading.service.pay.channel.zxyh.dto.MerchantZxyhDTO;
import net.fnsco.trading.service.pay.channel.zxyh.dto.PassivePayDTO;
import net.fnsco.trading.service.pay.channel.zxyh.dto.PassivePayReqDTO;
import net.fnsco.trading.service.pay.channel.zxyh.dto.PassivePayResultDTO;
import net.fnsco.trading.service.pay.channel.zxyh.util.ZxyhPayMD5Util;

@Service
public class PaymentService extends BaseService implements OrderPaymentService {
	@Autowired
	private Environment env;
	@Autowired
	private TradeOrderService tradeOrderService;
	@Autowired
	private MerchantChannelDao channelDao;
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private MerchantEntityCoreRefDao merchantEntityCoreRefDao;
	@Autowired
	private TradeOrderDAO orderDAO;
	@Autowired
	private AppUserMerchantService appUserMerchantService;
	@Autowired
	private MerchantCoreService merchantCoreService;
	@Autowired
	private AppUserMerchantEntityDAO appUserMerchantEntityDAO;

	/**
	 * 查询交易状态
	 * 
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public Integer getDealStatus(Integer id, String paySubType, String secMerId, String orderNo, Date orderCeateTime) {
		String merId = env.getProperty("zxyh.merId");
		String prefix = env.getProperty("zxyh.query.trade.url");
		String url = "/MPay/backTransAction.do";
		Map<String, String> mercMap = Maps.newHashMap();
		TradeResultZXDTO tradeResultZXDTO = new TradeResultZXDTO();
		// 相同
		tradeResultZXDTO.init(merId);
		tradeResultZXDTO.setSecMerId(secMerId);// 是与merId关联的分账子商户号
		tradeResultZXDTO.setAccountFlag("Y");
		tradeResultZXDTO.setIndependentTransactionFlag("Y");
		tradeResultZXDTO.setFetchOrderNo("Y");
		// 微信
		if (StringUtils.equals("01", paySubType)) {
			tradeResultZXDTO.setTxnSubType("383000");
			tradeResultZXDTO.setPayAccessType("02");
			tradeResultZXDTO.setOrigOrderId(orderNo);
			tradeResultZXDTO.setOrigOrderTime(DateUtils.dateFormat1ToStr(orderCeateTime));
			tradeResultZXDTO.setOrderTime(DateUtils.dateFormat1ToStr(orderCeateTime));
		} else if (StringUtils.equals("02", paySubType)) {// 支付宝
			tradeResultZXDTO.setTxnSubType("381004");
			tradeResultZXDTO.setSeqId(orderNo); // 原交易中信流水号或原交易商户订单号
		} else {
			return 0;
		}
		String mercStr = JSON.toJSONString(tradeResultZXDTO);
		mercMap = JSON.parseObject(mercStr, Map.class);
		String respStr = ZxyhPayMD5Util.request(mercMap, url, prefix);
		// 解析返回报文
		Map<String, Object> respMap = ZxyhPayMD5Util.getResp(respStr);
		logger.info("查询交易状态返回报文=" + respMap.toString());
		TradeOrderDO tradeOrderDO = new TradeOrderDO();
		tradeOrderDO.setId(id);
		// 微信返回结果
		if ("SUCCESS".equals(respMap.get("origRespCode"))) {
			tradeOrderDO.setRespCode(E789ApiConstant.ResponCodeEnum.DEAL_SUCCESS.getCode());
		} else if ("NOTPAY".equals(respMap.get("origRespCode"))) {
			tradeOrderDO.setRespCode(E789ApiConstant.ResponCodeEnum.DEAL_UNPAY.getCode());
		}

		if ("00".equals(respMap.get("txnState"))) {// 支付宝返回结果
			// 更新交易为已成功
			tradeOrderDO.setRespCode(E789ApiConstant.ResponCodeEnum.DEAL_SUCCESS.getCode());
		} else if ("01".equals(respMap.get("txnState"))) {
			// 更新状态为已退货成功
			tradeOrderDO.setRespCode(E789ApiConstant.ResponCodeEnum.DEAL_SEALS_RETURN.getCode());

		} else if ("99".equals(respMap.get("txnState"))) {
			// 更新状态为失败
			tradeOrderDO.setRespCode(E789ApiConstant.ResponCodeEnum.DEAL_FAIL.getCode());
		} else if ("02".equals(respMap.get("txnState"))) {
			// 订单已关闭
			tradeOrderDO.setRespCode(E789ApiConstant.ResponCodeEnum.DEAL_CLOSED.getCode());
		} else if ("04".equals(respMap.get("txnState")) || "06".equals(respMap.get("txnState"))
				|| "08".equals(respMap.get("txnState")) || "09".equals(respMap.get("txnState"))) {
			tradeOrderDO.setRespCode(E789ApiConstant.ResponCodeEnum.DEAL_IN_PROGRESS.getCode());
			return 0;
		}
		tradeOrderDO.setPayOrderNo((String) respMap.get("origSeqId")); // 对应的中信订单号
		tradeOrderDO.setRespMsg(E789ApiConstant.ResponCodeEnum.getNameByCode(tradeOrderDO.getRespCode()));
		String endTime = (String) respMap.get("endTime");
		if (!Strings.isNullOrEmpty(endTime)) {
			tradeOrderDO.setCompleteTime(DateUtils.StrToDate(endTime));// 交易完成时间
		}
		Integer row = tradeOrderService.doUpdate(tradeOrderDO);
		return row;
	}

	/**
	 * 获取未入驻成功的商户信息列表
	 * 
	 */
	public void queryAloneMchtInfoList(List<MercQueryDTO> list) {
		String merId = env.getProperty("zxyh.merId");

		if (!list.isEmpty()) {
			for (MercQueryDTO mercQueryDTO : list) {
				//调用中信接口查询商户审核状态
				Map<String, Object> map = this.queryAloneMchtInfo(merId, mercQueryDTO.getChannel_mer_id());
				String respCode = (String) map.get("respCode");
				//中信对应审核状态 0-新增待审核, 2-正常,3-审核拒绝
				String mchtChkStatus = (String) map.get("mchtChkStatus");
				if ("0000".equals(respCode)) {
					if (StringUtils.equalsIgnoreCase("0", mchtChkStatus)) {
						continue;
					}
					if (StringUtils.equalsIgnoreCase("2", mchtChkStatus)) {
						mchtChkStatus = "5";// 审核通过
					} else if (StringUtils.equalsIgnoreCase("3", mchtChkStatus)) {
						mchtChkStatus = "3";// 审核拒绝
					}

					logger.error("内部商户号=" + mercQueryDTO.getInner_code() + "更新状态为" + mchtChkStatus);
					merchantCoreService.updateStatusByInnerCode(mercQueryDTO.getInner_code(), mchtChkStatus);
				}
			}
		}

	}

	/**
	 * 查询独立商户是否入驻成功接口 queryAloneMchtInfo:(这里用一句话描述这个方法的作用)
	 *
	 * @param @param
	 *            merId
	 * @param @param
	 *            secMerId
	 * @param @return
	 *            设定文件
	 * @return Map<String,Object> DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public Map<String, Object> queryAloneMchtInfo(String merId, String secMerId) {
		String prefix = env.getProperty("zxyh.query.url");
		String url = "/MPayTransaction/ind/queryAloneMchtInfo.do";
		Map<String, String> mercMap = Maps.newHashMap();
		mercMap.put("merId", merId);
		mercMap.put("secMerId", secMerId);
		mercMap.put("signMethod", "02");
		logger.error("secMerId=" + secMerId);
		String respStr = ZxyhPayMD5Util.request(mercMap, url, prefix);
		// 解析返回报文
		Map<String, Object> respMap = ZxyhPayMD5Util.getResp(respStr);
		return respMap;
	}

	/**
	 * 
	 * trade:(入驻独立商户) void 返回Result对象
	 * 
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	public Map<String, Object> mechAdd(MerchantCoreEntityZxyhDTO core) {
		String pid = env.getProperty("zxyh.alipay.pid");
		String merId = env.getProperty("zxyh.merId");
		String prefix = env.getProperty("zxyh.add.url");
		String url = "/MPayTransaction/ind/mchtadd.do";
		MerchantZxyhDTO mercDTO = new MerchantZxyhDTO();
		mercDTO.init(pid, merId);
		// 赋值
		mercDTO.setMchtNm(core.getMchtNm());
		mercDTO.setMchtCnAbbr(core.getMchtCnAbbr());
		mercDTO.setEtpsAttr(core.getEtpsAttr());
		mercDTO.setEtpsTp(core.getEtpsTp());
		mercDTO.setMchtTel(core.getMchtTel());
		mercDTO.setContact(core.getContact());
		mercDTO.setCommTel(core.getCommTel());
		mercDTO.setCommEmail(core.getCommEmail());
		mercDTO.setLicenceNo(core.getLicenceNo());
		mercDTO.setManager(core.getManager());
		mercDTO.setIdentityNo(core.getIdentityNo());
		mercDTO.setProvCode(core.getProvCode());
		mercDTO.setCityCode(core.getCityCode());
		mercDTO.setAreaCode(core.getAreaCode());
		mercDTO.setAddr(core.getAddr());
		// 是否开通微信
		mercDTO.setWXActive(core.getWXActive());
		if (StringUtils.equals("Y", core.getWXActive())) {
			mercDTO.setqGroupId(core.getqGroupId());
			mercDTO.setCategroryId(core.getCategroryId());
			mercDTO.setFeeRate(core.getFeeRate());
			mercDTO.setSettleCycle(core.getSettleCycle());
		}
		// 公众号
		mercDTO.setSubAppid(core.getSubAppid());
		mercDTO.setJsapiPath(core.getJsapiPath());
		// 支付宝
		mercDTO.setZFBActive(core.getZFBActive());
		if (StringUtils.equals("Y", core.getZFBActive())) {
			mercDTO.setCategIdC(core.getCategIdC());
			mercDTO.setFeeRateA(core.getFeeRateA());
			mercDTO.setSettleCycleA(core.getSettleCycleA());
		}
		// 银行信息
		mercDTO.setThirdMchtNo(core.getThirdMchtNo());// 第三方平台子商户号
		mercDTO.setIsOrNotZxMchtNo(core.getIsOrNotZxMchtNo());
		mercDTO.setAcctType(core.getAcctType());// 账户类型 填写数字 1-企业账户，2-个人账户
		mercDTO.setSettleAcctNm(core.getSettleAcctNm());
		mercDTO.setSettleAcct(core.getSettleAcct());
		mercDTO.setAccIdeNo(core.getAccIdeNo());
		mercDTO.setAccPhone(core.getAccPhone());
		mercDTO.setSettleBankAllName(core.getSettleBankAllName());
		mercDTO.setSettleBankCode(core.getSettleBankCode());

		String mercStr = JSON.toJSONString(mercDTO);
		logger.info("入件参数=" + mercStr);
		Map<String, String> mercMap = JSON.parseObject(mercStr, Map.class);
		String respStr = ZxyhPayMD5Util.request(mercMap, url, prefix);
		// 解析返回报文
		Map<String, Object> respMap = ZxyhPayMD5Util.getResp(respStr);
		return respMap;
	}

	/**
	 * 微信主扫
	 * 
	 * @return
	 */
	@Transactional
	public ResultDTO<Map<String, Object>> generateQRCodeWeiXin(Integer userId, String txnAmt, String entityInnerCode,
			String innerCode) {
		String merId = env.getProperty("zxyh.merId");
		ActiveWeiXinDTO weiXinDTO = new ActiveWeiXinDTO();
		weiXinDTO.init(merId);
		String url = "/MPay/backTransAction.do";
		weiXinDTO.setEncoding("UTF-8");
		weiXinDTO.setBackEndUrl(env.getProperty("zxyh.backurl.wx")); // 接收支付网关异步通知回调地址

		MerchantChannel channel = channelDao.selectByInnerCodeType(innerCode, "05");
		if (channel != null) {
			weiXinDTO.setSecMerId(channel.getChannelMerId());// 分账子商户号 C
																// String(15)
																// 使用分账功能时上传，是与merId关联的分账子商户号

		}
		weiXinDTO.setTermId("WEB");// 终端编号 C String(8) 终端编号默认WEB
		weiXinDTO.setTermIp("");// 终端IP C String(16)
								// APP和网页支付提交用户端ip，主扫支付填调用付API的机器IP

		weiXinDTO.setOrderId(
				DateUtils.getNowYMDOnlyStr() + innerCode + sequenceService.getOrderSequence("t_trade_data")); // 商户系统内部的订单号
																												// M
																												// ,32
																												// 个字符内、可包含字母,
																												// 确保在商户系统唯一
		weiXinDTO.setOrderTime(DateUtils.getNowDateStr()); // 订单生成时间，M 格式
															// 为[yyyyMMddHHmmss]
															// ,如2009年12月25日9点10分10秒
															// 表示为20091225091010
		weiXinDTO.setProductId(""); // 商品ID C Strng(32) 此id为二维码中包含的商品ID，商户自行定义。
		String orderBody = "商品描述";
		weiXinDTO.setOrderBody(orderBody); // 商品或支付单简要描述 M
		weiXinDTO.setOrderDetail("");// 商品详细描述 C
		weiXinDTO.setOrderGoodsTag("");// 商品标记 C String(32) 商品标记，代金券或立减优惠功能的参数
		weiXinDTO.setTxnAmt(txnAmt);// M 订单总金额(交易单位为分，例:1.23元=123) 只能整数
		weiXinDTO.setAccountFlag("Y");// 必填 Y 分账标识
		weiXinDTO.setSecMerFeeRate("");// 分账子商户交易费率 secMerFeeRate C String(16)
										// 使用分账功能时上传，是分账子商户secMerId的交易费率（若费率是0.3%，此值传0.003，小数点后最多四位），此费率值不能比平台商户merId的费率低
		weiXinDTO.setAttach(""); // 附加数据 C String(127)
									// 仅微信、QQ钱包适用；附加数据，在支付通知中原样返回，该字段主要用于商户携带订单的自定义数据，要求格式如下bank_mch_name=xxxxx&bank_mch_id=xxxx&商户自定义参数
		weiXinDTO.setLimitPay(""); // 指定支付方式 limitPay C String(32)
									// 仅微信适用；no_credit--指定不能使用信用卡支付
		weiXinDTO.setNeedBankType("");// 是否需要获取支付行类型 C String(1)
										// 传“Y”，即会在查询订单以及异步通知时返回“bankType”（支付行类型）、“orderOpenId”（支付用户id）字段
		weiXinDTO.setIndependentTransactionFlag("Y");// 必填Y
		weiXinDTO.setOrderType("");// 订单类型 orderType R String(1)
									// 京东支付必填，固定值：0或者1（0：：实物，1：虚拟）

		String aliPayStr = JSON.toJSONString(weiXinDTO);
		Map<String, String> aliPayMap = JSON.parseObject(aliPayStr, Map.class);
		// 发送中信报文
		String respStr = ZxyhPayMD5Util.request(aliPayMap, url, env.getProperty("zxyh.pay.url")); // 生产是8092//
																									// 测试"https://120.27.165.177:8099"
		// 插入交易数据
		TradeOrderDO tradeOrderDO = new TradeOrderDO();
		tradeOrderDO.setInnerCode(innerCode);
		tradeOrderDO.setOrderNo(weiXinDTO.getOrderId());
		tradeOrderDO.setTxnAmount(new BigDecimal(txnAmt));
		tradeOrderDO.setEntityInnerCode(entityInnerCode);
		tradeOrderDO.setChannelMerId(channel.getChannelMerId());// 渠道商户号
		tradeOrderDO.setChannelType(channel.getChannelType());// 渠道类型
		tradeOrderDO.setOrderCeateTime(new Date());
		tradeOrderDO.setTxnType(1);
		tradeOrderDO.setPayType("01");// 支付方式
		tradeOrderDO.setPaySubType(E789ApiConstant.PayTypeEnum.PAYBYWX.getCode());
		tradeOrderDO.setCreateTime(new Date());
		tradeOrderDO.setRespCode(E789ApiConstant.ResponCodeEnum.DEAL_IN_PROGRESS.getCode());
		tradeOrderDO.setPayMedium(BigdataConstant.PayMediumEnum.APP.getCode());
		tradeOrderDO.setSyncStatus(0);
		logger.info("插入交易数据");
		tradeOrderService.doAdd(tradeOrderDO);
		// 解析返回报文
		Map<String, Object> respMap = ZxyhPayMD5Util.getResp(respStr);
		logger.info("返回报文=" + JSONObject.toJSONString(respMap));
		if ("0000".equals(respMap.get("respCode"))) {
			respMap.put("orderId", tradeOrderDO.getOrderNo());
			respMap.put("respCode", tradeOrderDO.getRespCode());
			return ResultDTO.success(respMap);
		} else {
			respMap.put("orderId", tradeOrderDO.getOrderNo());
			respMap.put("respCode", tradeOrderDO.getRespCode());
			//更新此条交易为已失败
			TradeOrderDO tradeOrderDO2 = new TradeOrderDO();
			tradeOrderDO2.setId(tradeOrderDO.getId());
			tradeOrderDO2.setRespCode(E789ApiConstant.ResponCodeEnum.DEAL_FAIL.getCode());
			tradeOrderService.doUpdate(tradeOrderDO2);
			return ResultDTO.fail(respMap, (String) respMap.get("respMsg"));
		}

	}

	/**
	 * 支付宝主扫
	 *
	 */
	@Transactional
	public ResultDTO<Map<String, Object>> generateQRCodeAliPay(Integer userId, String ip, String txnAmt,String entityInnerCode, String innerCode) {
		String merId = env.getProperty("zxyh.merId");
		ActiveAlipayDTO activeAlipayDTO = new ActiveAlipayDTO();
		activeAlipayDTO.init(merId);
		String url = "/MPay/backTransAction.do";

		activeAlipayDTO.setEncoding("UTF-8");
		activeAlipayDTO.setBackEndUrl(env.getProperty("zxyh.backurl.zfb")); // 接收支付网关异步通知回调地址

		// 根据内部商户号获取独立商户号
		MerchantChannel channel = channelDao.selectByInnerCodeType(innerCode, "05");
		logger.info("渠道商户信息" + JSONObject.toJSONString(channel));
		if (channel == null) {
			logger.info(E789ApiConstant.E_ADD_FIRST);
			return ResultDTO.fail(E789ApiConstant.E_ADD_FIRST);
		}
		activeAlipayDTO.setSecMerId(channel.getChannelMerId());// 独立商户号

		activeAlipayDTO.setTermIp(ip);// 发起支付的客户端真实IP
		activeAlipayDTO.setOrderId(
				DateUtils.getNowYMDOnlyStr() + innerCode + sequenceService.getOrderSequence("t_trade_order")); // 商户系统内部的订单号,32
																												// 个字符内、可包含字母,
																												// 确保在商户系统唯一
		activeAlipayDTO.setOrderTime(DateUtils.getNowDateStr()); // 订单生成时间，格式
																	// 为[yyyyMMddHHmmss]
																	// ,如2009年12月25日9点10分10秒
																	// 表示为20091225091010
		String orderBody = "商品描述";
		activeAlipayDTO.setOrderBody(orderBody); // 商品或支付单简要描述
		activeAlipayDTO.setOrderDetail("");// C String(100) 商品详细描述
		activeAlipayDTO.setTxnAmt(txnAmt);// 订单总金额(交易单位为分，例:1.23元=123) 只能整数

		activeAlipayDTO.setAccountFlag("Y");// 必填 Y
		activeAlipayDTO.setSecMerFeeRate("");// C String(16)
												// 使用分账功能时上传，是分账子商户secMerId的交易费率（若费率是0.3%，此值传0.003，小数点后最多四位），此费率值不能比平台商户merId的费率低
		activeAlipayDTO.setDisablePayChannels("");// 要禁用的信用渠道 C String(256)
													// 禁用支付渠道 creditCard：信用卡
													// credit_group：所有信用渠道，包含（信用卡，花呗）
		activeAlipayDTO.setNeedBankType("");// 是否需要获取支付行类型 needBankType C
											// String(1)
											// 传“Y”，即会在查询订单以及异步通知时返回“bankType”（支付行类型）、“orderOpenId”（支付用户id）字段
		activeAlipayDTO.setIndependentTransactionFlag("Y");// 必填Y

		String aliPayStr = JSON.toJSONString(activeAlipayDTO);
		Map<String, String> aliPayMap = JSON.parseObject(aliPayStr, Map.class);
		// 发送中信报文
		String respStr = ZxyhPayMD5Util.request(aliPayMap, url, env.getProperty("zxyh.pay.url"));
		// 插入交易数据
		TradeOrderDO tradeOrderDO = new TradeOrderDO();
		tradeOrderDO.setOrderNo(activeAlipayDTO.getOrderId());
		tradeOrderDO.setTxnAmount(new BigDecimal(txnAmt));
		tradeOrderDO.setEntityInnerCode(entityInnerCode);
		tradeOrderDO.setChannelMerId(channel.getChannelMerId());// 渠道商户号
		tradeOrderDO.setChannelType(channel.getChannelType());// 渠道类型
		tradeOrderDO.setOrderCeateTime(new Date());
		tradeOrderDO.setTxnType(1);
		tradeOrderDO.setPayType("01");// 支付方式
		tradeOrderDO.setPaySubType(E789ApiConstant.PayTypeEnum.PAYBYALIPAY.getCode());
		tradeOrderDO.setCreateTime(new Date());
		tradeOrderDO.setRespCode(E789ApiConstant.ResponCodeEnum.DEAL_IN_PROGRESS.getCode());
		tradeOrderDO.setInnerCode(innerCode);
		tradeOrderDO.setPayMedium(BigdataConstant.PayMediumEnum.APP.getCode());
		tradeOrderDO.setSyncStatus(0);
		logger.info("交易数据订单号=" + tradeOrderDO.getOrderNo());
		tradeOrderService.doAdd(tradeOrderDO);
		// 解析返回报文
		Map<String, Object> respMap = ZxyhPayMD5Util.getResp(respStr);
		logger.info("返回报文=" + respMap);
		if ("0000".equals(respMap.get("respCode"))) {
			respMap.put("orderId", tradeOrderDO.getOrderNo());
			respMap.put("respCode", tradeOrderDO.getRespCode());
			return ResultDTO.success(respMap);
		} else {
			//更新此条交易为已失败
			TradeOrderDO tradeOrderDO2 = new TradeOrderDO();
			tradeOrderDO2.setId(tradeOrderDO.getId());
			tradeOrderDO2.setRespCode(E789ApiConstant.ResponCodeEnum.DEAL_FAIL.getCode());
			tradeOrderService.doUpdate(tradeOrderDO2);
			logger.error(JSONObject.toJSONString(respMap));
			return ResultDTO.fail(respMap);
		}
	}

	/**
	 * 被扫-交易处理,测试环境：https://120.27.165.177:8099/MPay/misRequest.do
	 * 
	 * @param reqStr：被扫实体对象
	 * @return:被扫交易应答JSON字符串
	 */
	public ResultDTO<PassivePayResultDTO> passivePay(PassivePayReqDTO passivePayReqDTO) {

		String url = "/MPay/misRequest.do";
		String merId = env.getProperty("zxyh.merId");
		PassivePayResultDTO passivePayResultDTO = new PassivePayResultDTO();
		TradeOrderDO tradeOrderDO = new TradeOrderDO();// 交易订单表
		PassivePayDTO passDTO = new PassivePayDTO();
		passDTO.init(merId);// 初始化

		// 判断该app用户是否已绑定商户
		AppUserMerchantEntityDO appUserMerchantEntity = appUserMerchantEntityDAO.selectByEntityInnerCode(passivePayReqDTO.getUserId());
		if (null == appUserMerchantEntity) {
			return ResultDTO.fail(E789ApiConstant.E_NOT_BOUND_MERC);
		}
		String entityInnerCode = appUserMerchantEntity.getEntityInnerCode();
		// 根据实体商户号获取内部商户号
		String innerCode = this.appUserMerchantService.getInnerCodeByUserId(entityInnerCode);
		logger.info("app用户对应渠道内部商户号=" + innerCode);
		if (null == innerCode) {
			logger.error("该用户没有绑定渠道内部商户号，请核查后重新交易");
			return ResultDTO.fail("该用户没有绑定渠道内部商户号");
		}
		// 根据内部商户号和渠道类型获取渠道商户号
		MerchantChannel merchantChannel = channelDao.selectByInnerCodeType(innerCode,
				TradeConstants.ChannelTypeEnum.ZXYH_PAY.getCode());
		logger.info("渠道商户信息" + JSONObject.toJSONString(merchantChannel));
		if (null == merchantChannel) {
			logger.error("该内部商户号没有绑定中信渠道的商户号，请核查后重新交易,innerCode=[" + innerCode + "");
			return ResultDTO.fail("该内部商户号没有绑定中信渠道的商户号，请核查后重新交易");
		}
		passDTO.setStdmercno2(merchantChannel.getChannelMerId());// 二级商户号，使用分账功能时上传，是与stdmercno关联的分账子商户号
		passDTO.setStdauthid(passivePayReqDTO.getAuthId());// 授权码

		// 校验微信授权码，如果校验成功，则
		// String resp = CheckAuthid(passDTO);
		// logger.info("校验微信授权码,respCode=" + resp);

		// 应答码6002-支付授权已过期，请刷新再试,说明当前的是微信付款码
		// if ("6002".equals(resp)) {
		// logger.info("微信付款码已经过期，请重新扫付款码");
		// // map.put("respCode", "1002");
		// // map.put("respMsg", "付款码已经过期,请重新扫付款码");
		// return ResultDTO.fail("付款码已经过期，请重新扫付款码");
		// } else if ("1007".equals(resp)) {// 交易通讯超时（银行系统异常）
		// logger.info("1007=交易通讯超时（银行系统异常）");
		// return ResultDTO.fail("交易失败");
		// }

		// 应答码为0000-成功，表示可以直接用微信进行支付了
		// if ("0000".equals(resp)) {
		// passDTO.setStdprocode(ZxyhPassivePayCode.WX_BS_PAY.getCode());//
		// 交易码，481000：微信消费,481003：支付宝二清消费
		// } else {
		// passDTO.setStdprocode(ZxyhPassivePayCode.ZFB_BS_PAY.getCode());//
		// 交易码，481000：微信消费,481003：支付宝二清消费
		// // 不可优惠金额，支付宝必填
		//
		// passDTO.setStddiscamt("0");
		// }

		// 通过交易子类型判断支付宝还是微信支付
		if (E789ApiConstant.PayTypeEnum.PAYBYWX.getCode().equals(passivePayReqDTO.getPaySubType())) {

			passDTO.setStdprocode(ZxyhPassivePayCode.WX_BS_PAY.getCode());// 交易码，481000：微信消费
		} else {

			passDTO.setStdprocode(ZxyhPassivePayCode.ZFB_BS_PAY.getCode());// 交易码，481003：支付宝二清消费
			passDTO.setStddiscamt("0");// 不可优惠金额，支付宝必填
		}

		passDTO.setStdmsgtype(ZxyhPassivePayType.BS_PAY_TYPE.getCode());// 消息类型"48"
		passDTO.setStdorderid(
				DateUtils.getNowYMDOnlyStr() + innerCode + sequenceService.getOrderSequence("t_trade_order"));// 商户订单号，32个字符内
		passDTO.setStdtrancur("156");// 交易币种 M String(3) 默认是156：人民币
		passDTO.setNeedBankType("Y");// 值为“Y”时，支付成功、查询支付成功的订单会返回付款银行类型bankType
		passDTO.setStdbegtime(DateUtils.getNowDateStr());// 交易起始时间,yyyyMMddHHmmss
		passDTO.setStd400memo("扫码支付");// 商品描述
		passDTO.setStdtranamt(passivePayReqDTO.getAmt());// 交易金额,整数，以分为单位

		// 插表处理
		tradeOrderDO.setOrderNo(passDTO.getStdorderid());// 设置订单号(商户订单号)
		tradeOrderDO.setTxnAmount(new BigDecimal(passDTO.getStdtranamt()));// 设置交易金额
		tradeOrderDO.setChannelMerId(passDTO.getStdmercno2());// 设置渠道商户号
		tradeOrderDO.setChannelType(TradeConstants.ChannelTypeEnum.ZXYH_PAY.getCode());// 设置渠道类型05-中信银行
		tradeOrderDO.setOrderCeateTime(new Date());// 订单创建时间
		tradeOrderDO.setTxnType(Integer.parseInt(TradeTypeEnum.CONSUMER.getCode()));// 设置交易类型
		tradeOrderDO.setTxnSubType(12);// 设置交易子类型，12-扫一扫消费
		tradeOrderDO.setPayType(PayTypeEnum.CODE_PAY.getCode());// 设置支付方式，01-二维码

		// 设置支付子类型，01-微信；02-支付宝
		if (ZxyhPassivePayCode.ZFB_BS_PAY.getCode().equals(passDTO.getStdprocode())) {

			tradeOrderDO.setPaySubType(PaySubTypeAllEnum.ZFB_PAY.getCode());
		} else if (ZxyhPassivePayCode.WX_BS_PAY.getCode().equals(passDTO.getStdprocode())) {

			tradeOrderDO.setPaySubType(PaySubTypeAllEnum.WX_PAY.getCode());
		}

		tradeOrderDO.setSettleStatus(0);// 设置结算状态0-未结算
		tradeOrderDO.setCreateTime(new Date());// 创建时间
		tradeOrderDO.setInnerCode(innerCode);// 设置内部商户号
		tradeOrderDO.setSettleStatus(0);// 设置清算状态0-未清算
		tradeOrderDO.setSyncStatus(0);// 设置同步状态0-未同步
		tradeOrderDO.setPayMedium(BigdataConstant.PayMediumEnum.APP.getCode());// 支付媒介:01-APP
		tradeOrderDO.setEntityInnerCode(appUserMerchantEntity.getEntityInnerCode());// 设置实体商户号

		tradeOrderService.doAdd(tradeOrderDO);// 交易数据插表

		String passiveStr = JSON.toJSONString(passDTO);// 将对象转换为JSON字符串
		Map<String, String> passiveMap = JSON.parseObject(passiveStr, Map.class);// 将JSON字符串转换为map
		String respStr = ZxyhPayMD5Util.request(passiveMap, url, env.getProperty("zxyh.pay.url"));

		// 解析返回报文
		Map<String, Object> respMap = ZxyhPayMD5Util.getResp(respStr);
		logger.info("扫一扫，支付应答信息:" + respMap);
		String json = JSON.toJSONString(respMap);
		PassivePayDTO passDTO1 = JSON.parseObject(json, PassivePayDTO.class);

		TradeOrderDO tradeOrderDO1 = new TradeOrderDO();
		tradeOrderDO1.setId(tradeOrderDO.getId());

		// 支付成功，则直接更新交易状态
		if ("0000".equals(passDTO1.getStd400mgid())) {

			tradeOrderDO1.setRespCode(TradeStateEnum.SUCCESS.getCode());// 设置响应应答码
			tradeOrderDO1.setCompleteTime(DateUtils.StrToDate(passDTO1.getEndTime()));// 交易成功时间(支付完成时间，成功时返回)
			tradeOrderDO1.setSettleAmount(new BigDecimal(passDTO1.getQstranamt()));// 清算金额
			tradeOrderDO1.setSettleDate(DateUtils.StrToDate(passDTO1.getQs400trdt() + "000000"));// 清算日起
			tradeOrderDO1.setRespMsg(passDTO1.getStdrtninfo());// 设置响应信息
			tradeOrderDO1.setPayOrderNo(passDTO1.getStdrefnum());// 支付订单号(平台流水号，供后续退货或者撤销或对账使用)
			logger.error("扫一扫-交易成功");

			// 6001-进行中,用户支付中，需要输入密码,等待5秒,然后调用交易状态查询确定交易支付结果；0005-交易已受理，请稍后查询交易结果
		} else if ("6001".equals(passDTO1.getStd400mgid()) || "0005".equals(passDTO1.getStd400mgid())) {

			tradeOrderDO1.setRespCode("1000");// 设置响应应答码
			tradeOrderDO1.setCompleteTime(new Date());
			logger.error("扫一扫-交易结果未知，respCode=[" + passDTO1.getStd400mgid() + "],respMsg=[" + passDTO1.getStdrtninfo()
					+ "]");

		} else {// 交易失败

			tradeOrderDO1.setRespCode("1002");// 设置响应应答码
			tradeOrderDO1.setCompleteTime(new Date());
			logger.error(
					"扫一扫-交易失败，respCode=[" + passDTO1.getStd400mgid() + "],respMsg=[" + passDTO1.getStdrtninfo() + "]");
		}

		tradeOrderDO1.setRespMsg(passDTO1.getStdrtninfo());// 设置响应信息
		tradeOrderDO1.setPayOrderNo(passDTO1.getStdrefnum());// 支付订单号(平台流水号，供后续退货或者撤销或对账使用)
		tradeOrderService.doUpdate(tradeOrderDO1);// 通过主键更新应答数据

		passivePayResultDTO.setRespCode(tradeOrderDO1.getRespCode());// 应答码
		passivePayResultDTO.setRespMsg(tradeOrderDO1.getRespMsg());// 应答信息
		passivePayResultDTO.setOrderNo(tradeOrderDO1.getOrderNo());// 商户订单号
		passivePayResultDTO.setBegTime(passDTO.getStdbegtime());// 交易起始时间
		passivePayResultDTO.setEndTime(passDTO1.getEndTime());// 支付结束时间
		passivePayResultDTO.setAmt(passivePayReqDTO.getAmt());// 交易金额
		passivePayResultDTO.setReciAmt(passDTO1.getStdreciamt());// 实收金额
		passivePayResultDTO.setPreAmt(passDTO1.getStdpreamt());// 优惠金额

		// return ResultDTO.fail(tradeOrderDO1.getRespMsg());
		return ResultDTO.success(passivePayResultDTO);
	}

	/**
	 * 被扫-交易状态查询
	 * 
	 * @param innerCode
	 * @param passivePayDTO
	 * @return
	 */
	public ResultDTO<PassivePayResultDTO> passivePayResult(String orderNo) {

		String url = "/MPay/misRequest.do";
		PassivePayResultDTO passivePayResultDTO = new PassivePayResultDTO();

		// 通过订单号查询交易交易状态结果，结果为1000进行中，才发状态查询交易给中信银行那边
		TradeOrderDO tradeOrderDO = tradeOrderService.queryByOrderId(orderNo);
		if (null == tradeOrderDO) {
			logger.info("没有找到该交易请求交易,order_no=[" + orderNo + "]");

			return ResultDTO.fail("交易失败");
		}

		// 渠道校验
		if (!TradeConstants.ChannelTypeEnum.ZXYH_PAY.getCode().equals(tradeOrderDO.getChannelType())) {
			
			logger.info("该订单号交易渠道不是中信,order_no=[" + orderNo + "]");
			return ResultDTO.fail("渠道校验失败");
		}

		// 如果原消费交易是非(进行状态的或者应答码为空)的，则可以直接返回应答
		if (!"1000".equals(tradeOrderDO.getRespCode()) && !Strings.isNullOrEmpty(tradeOrderDO.getRespCode())) {

			logger.info("该交易为有确定结果的交易,不用再去中信银行那边查询结果,将直接返回。order_no=[" + orderNo + "]");
			passivePayResultDTO.setRespCode(tradeOrderDO.getRespCode());// 应答码
			passivePayResultDTO.setRespMsg(tradeOrderDO.getRespMsg());// 应答信息
			passivePayResultDTO.setOrderNo(tradeOrderDO.getOrderNo());// 商户订单号
			passivePayResultDTO.setBegTime(DateUtils.dateFormatToStr(tradeOrderDO.getCreateTime()));// 交易起始时间
			passivePayResultDTO.setEndTime(DateUtils.dateFormatToStr(tradeOrderDO.getCompleteTime()));// 支付结束时间
			passivePayResultDTO.setAmt(tradeOrderDO.getTxnAmount().toString());// 交易金额
			return ResultDTO.success(passivePayResultDTO);
		}

		String merId = env.getProperty("zxyh.merId");
		PassivePayDTO passDTO = new PassivePayDTO();
		passDTO.init(merId);// 初始化

		passDTO.setStdmsgtype(ZxyhPassivePayType.BS_CX_TYPE.getCode());// 消息类型"38"

		// 根据原交易支付子类型判断支付宝还是微信支付,还是其他不支持的交易，设置交易码
		if (PaySubTypeAllEnum.ZFB_PAY.getCode().equals(tradeOrderDO.getPaySubType())) {

			passDTO.setStdprocode(ZxyhPassivePayCode.ZFB_BS_CX.getCode());
		} else if (PaySubTypeAllEnum.WX_PAY.getCode().equals(tradeOrderDO.getPaySubType())) {

			passDTO.setStdprocode(ZxyhPassivePayCode.WX_BS_CX.getCode());
		} else {

			logger.info("该订单号原支付子类型为非微信和支付宝，请核查后重新交易。order_no=[" + orderNo + "]");
			return ResultDTO.fail("交易失败");
		}

		passDTO.setStdmercno2(tradeOrderDO.getChannelMerId());// 二级商户号，使用分账功能时上传，是与stdmercno关联的分账子商户号
		passDTO.setOrgorderid(orderNo);// 原支付交易的中信订单号或原支付交易的商户订单号
		passDTO.setStdorderid(DateUtils.getNowYMDOnlyStr() + tradeOrderDO.getInnerCode()
				+ sequenceService.getOrderSequence("t_trade_order"));// 商户订单号，32个字符内
		passDTO.setStdbegtime(DateUtils.getNowDateStr());// 交易起始时间

		String passiveStr = JSON.toJSONString(passDTO);// 将对象转换为JSON字符串
		Map<String, String> passiveMap = JSON.parseObject(passiveStr, Map.class);// 将JSON字符串转换为map
		String respStr = ZxyhPayMD5Util.request(passiveMap, url, env.getProperty("zxyh.pay.url"));

		// 解析返回报文
		Map<String, Object> respMap = ZxyhPayMD5Util.getResp(respStr);
		logger.info("扫一扫，交易结果查询返回:" + respMap);
		String json = JSON.toJSONString(respMap);
		PassivePayDTO passDTO1 = JSON.parseObject(json, PassivePayDTO.class);

		// 原交易成功或者失败，更新原来应答码与应答信息
		// 6001-进行中,用户支付中，需要输入密码,等待5秒,然后调用交易状态查询确定交易支付结果；0005-交易已受理，请稍后查询交易结果
		if ("6001".equals(passDTO1.getSrg400mgid()) || "0005".equals(passDTO1.getSrg400mgid())) {

		} else {

			// 交易成功
			if ("0000".equals(passDTO1.getSrg400mgid())) {

				tradeOrderDO.setRespCode(TradeStateEnum.SUCCESS.getCode());// 设置响应码
				tradeOrderDO.setCompleteTime(DateUtils.StrToDate(passDTO1.getEndTime()));// 交易成功时间(支付完成时间，成功时返回)
				tradeOrderDO.setSettleAmount(new BigDecimal(passDTO1.getQstranamt()));// 清算金额
				tradeOrderDO.setSettleDate(DateUtils.StrToDate(passDTO1.getQs400trdt() + "000000"));// 清算日起

				// 交易失败
			} else {
				// tradeOrderDO.setRespCode(TradeStateEnum.FAIL.getCode());//
				// 设置响应码
				tradeOrderDO.setRespCode(TradeStateEnum.FAIL.getCode());// 设置响应码
			}

			tradeOrderDO.setRespMsg(passDTO1.getStdrtninfo());// 设置响应信息
			tradeOrderDO.setPayOrderNo(passDTO1.getStdrefnum());// 支付订单号(平台流水号，供)
			tradeOrderService.doUpdate(tradeOrderDO);// 通过主键更新应答数据
		}

		passivePayResultDTO.setRespCode(tradeOrderDO.getRespCode());// 应答码
		passivePayResultDTO.setRespMsg(tradeOrderDO.getRespMsg());// 应答信息
		passivePayResultDTO.setOrderNo(tradeOrderDO.getOrderNo());// 商户订单号
		passivePayResultDTO.setBegTime(passDTO.getStdbegtime());// 交易起始时间
		passivePayResultDTO.setEndTime(passDTO1.getEndTime());// 支付结束时间
		passivePayResultDTO.setAmt(passDTO1.getStdtranamt());// 交易金额
		passivePayResultDTO.setReciAmt(passDTO1.getStdreciamt());// 实收金额
		passivePayResultDTO.setPreAmt(passDTO1.getStdpreamt());// 优惠金额

		return ResultDTO.success(passivePayResultDTO);
	}

	/**
	 * 被扫-交易状态查询-定时任务
	 * 
	 * @param innerCode
	 * @param passivePayDTO
	 * @return
	 * 
	 * 		public void PassivePayResult(TradeOrderDO tradeOrderDO) {
	 * 
	 *         String url = "/MPay/misRequest.do";
	 * 
	 *         String merId = env.getProperty("zxyh.merId"); PassivePayDTO
	 *         passDTO = new PassivePayDTO(); passDTO.init(merId);
	 * 
	 *         passDTO.setStdmsgtype(ZxyhPassivePayType.BS_CX_TYPE.getCode());//
	 *         消息类型 M // String(4) // "38" //
	 *         根据原交易支付子类型判断支付宝还是微信支付,还是其他不支持的交易，设置交易码 if
	 *         (PaySubTypeAllEnum.ZFB_PAY.getCode().equals(tradeOrderDO.
	 *         getPaySubType())) {
	 *         passDTO.setStdprocode(ZxyhPassivePayCode.ZFB_BS_CX.getCode()); }
	 *         else if (PaySubTypeAllEnum.WX_PAY.getCode().equals(tradeOrderDO.
	 *         getPaySubType())) {
	 *         passDTO.setStdprocode(ZxyhPassivePayCode.WX_BS_CX.getCode()); }
	 *         else { logger.info("该订单号原支付子类型为非微信和支付宝，请核查后重新交易。order_no=[" +
	 *         tradeOrderDO.getOrderNo() + "]"); }
	 * 
	 *         passDTO.setStdmercno2(tradeOrderDO.getChannelMerId());//
	 *         二级商户号，使用分账功能时上传，是与stdmercno关联的分账子商户号
	 *         passDTO.setOrgorderid(tradeOrderDO.getOrderNo());//
	 *         原支付交易的中信订单号或原支付交易的商户订单号
	 *         passDTO.setStdorderid(DateUtils.getNowYMDOnlyStr() +
	 *         tradeOrderDO.getInnerCode() +
	 *         sequenceService.getOrderSequence("t_trade_order"));//
	 *         商户订单号，32个字符内 passDTO.setStdbegtime(DateUtils.getNowDateStr());//
	 *         交易起始时间
	 * 
	 *         String passiveStr = JSON.toJSONString(passDTO);// 将对象转换为JSON字符串
	 *         Map<String, String> passiveMap = JSON.parseObject(passiveStr,
	 *         Map.class);// 将JSON字符串转换为map String respStr =
	 *         ZxyhPayMD5Util.request(passiveMap, url,
	 *         env.getProperty("zxyh.pay.url"));
	 * 
	 *         // 解析返回报文 Map<String, Object> respMap =
	 *         ZxyhPayMD5Util.getResp(respStr); System.out.println(respMap);
	 *         String json = JSON.toJSONString(respMap); PassivePayDTO passDTO1
	 *         = JSON.parseObject(json, PassivePayDTO.class);
	 * 
	 *         // 原交易成功或者失败，更新原来应答码与应答信息 //
	 *         6001-进行中,用户支付中，需要输入密码,等待5秒,然后调用交易状态查询确定交易支付结果；0005-交易已受理，
	 *         请稍后查询交易结果 if (!"6001".equals(passDTO1.getSrg400mgid()) &&
	 *         !"0005".equals(passDTO1.getSrg400mgid())) { if
	 *         ("0000".equals(passDTO1.getSrg400mgid())) {
	 *         tradeOrderDO.setRespCode(TradeStateEnum.SUCCESS.getCode());//
	 *         设置响应码 tradeOrderDO.setCompleteTime(DateUtils.StrToDate(passDTO1.
	 *         getEndTime()));// 交易成功时间(支付完成时间，成功时返回)
	 *         tradeOrderDO.setSettleAmount(new
	 *         BigDecimal(passDTO1.getQstranamt()));// 清算金额
	 *         tradeOrderDO.setSettleDate(DateUtils.StrToDate(passDTO1.
	 *         getQs400trdt() + "000000"));// 清算日起 } else { //
	 *         tradeOrderDO.setRespCode(TradeStateEnum.FAIL.getCode());// //
	 *         设置响应码 }
	 * 
	 *         tradeOrderDO.setRespMsg(passDTO1.getStdrtninfo());// 设置响应信息
	 *         tradeOrderDO.setPayOrderNo(passDTO1.getStdrefnum());//
	 *         支付订单号(平台流水号，供) tradeOrderService.doUpdate(tradeOrderDO);//
	 *         通过主键更新应答数据 } }
	 */
	public void PassivePayResult(TradeOrderDO tradeOrderDO) {

		String url = "/MPay/misRequest.do";

		String merId = env.getProperty("zxyh.merId");
		PassivePayDTO passDTO = new PassivePayDTO();
		passDTO.init(merId);

		passDTO.setStdmsgtype(ZxyhPassivePayType.BS_CX_TYPE.getCode());// 消息类型 M
																		// String(4)
																		// "38"
		// 根据原交易支付子类型判断支付宝还是微信支付,还是其他不支持的交易，设置交易码
		if (PaySubTypeAllEnum.ZFB_PAY.getCode().equals(tradeOrderDO.getPaySubType())) {
			passDTO.setStdprocode(ZxyhPassivePayCode.ZFB_BS_CX.getCode());
		} else if (PaySubTypeAllEnum.WX_PAY.getCode().equals(tradeOrderDO.getPaySubType())) {
			passDTO.setStdprocode(ZxyhPassivePayCode.WX_BS_CX.getCode());
		} else {
			logger.info("该订单号原支付子类型为非微信和支付宝，请核查后重新交易。order_no=[" + tradeOrderDO.getOrderNo() + "]");
		}

		passDTO.setStdmercno2(tradeOrderDO.getChannelMerId());// 二级商户号，使用分账功能时上传，是与stdmercno关联的分账子商户号
		passDTO.setOrgorderid(tradeOrderDO.getOrderNo());// 原支付交易的中信订单号或原支付交易的商户订单号
		passDTO.setStdorderid(DateUtils.getNowYMDOnlyStr() + tradeOrderDO.getInnerCode()
				+ sequenceService.getOrderSequence("t_trade_order"));// 商户订单号，32个字符内
		passDTO.setStdbegtime(DateUtils.getNowDateStr());// 交易起始时间

		String passiveStr = JSON.toJSONString(passDTO);// 将对象转换为JSON字符串
		Map<String, String> passiveMap = JSON.parseObject(passiveStr, Map.class);// 将JSON字符串转换为map
		String respStr = ZxyhPayMD5Util.request(passiveMap, url, env.getProperty("zxyh.pay.url"));

		// 解析返回报文
		Map<String, Object> respMap = ZxyhPayMD5Util.getResp(respStr);
		System.out.println(respMap);
		String json = JSON.toJSONString(respMap);
		PassivePayDTO passDTO1 = JSON.parseObject(json, PassivePayDTO.class);

		// 原交易成功或者失败，更新原来应答码与应答信息
		// 6001-进行中,用户支付中，需要输入密码,等待5秒,然后调用交易状态查询确定交易支付结果；0005-交易已受理，请稍后查询交易结果
		if (!"6001".equals(passDTO1.getSrg400mgid()) && !"0005".equals(passDTO1.getSrg400mgid())) {
			if ("0000".equals(passDTO1.getSrg400mgid())) {
				tradeOrderDO.setRespCode(TradeStateEnum.SUCCESS.getCode());// 设置响应码
				tradeOrderDO.setCompleteTime(DateUtils.StrToDate(passDTO1.getEndTime()));// 交易成功时间(支付完成时间，成功时返回)
				tradeOrderDO.setSettleAmount(new BigDecimal(passDTO1.getQstranamt()));// 清算金额
				tradeOrderDO.setSettleDate(DateUtils.StrToDate(passDTO1.getQs400trdt() + "000000"));// 清算日起
			} else {
				// tradeOrderDO.setRespCode(TradeStateEnum.FAIL.getCode());//
				// 设置响应码
			}

			tradeOrderDO.setRespMsg(passDTO1.getStdrtninfo());// 设置响应信息
			tradeOrderDO.setPayOrderNo(passDTO1.getStdrefnum());// 支付订单号(平台流水号，供)
			tradeOrderService.doUpdate(tradeOrderDO);// 通过主键更新应答数据
		}
	}

	/**
	 * 校验微信授权码
	 * 
	 * @param passivePayDTO
	 * @return：true为微信授权码，可用此码进行微信交易;false为非微信授权码，可用此码尝试进行支付宝交易
	 */
	public String checkAuthid(PassivePayDTO passivePayDTO) {
		String url = "/MPay/misRequest.do";
		String merId = env.getProperty("zxyh.merId");
		PassivePayDTO passDTO = new PassivePayDTO();
		passDTO.init(merId);

		passDTO.setStdmsgtype(ZxyhPassivePayType.BS_AUTH_TYPE.getCode());// 消息类型
																			// M
																			// String(4)
																			// "07"
		passDTO.setStdprocode(ZxyhPassivePayCode.WX_AUTH_CK.getCode());// 交易码

		passDTO.setStdmercno2(passivePayDTO.getStdmercno2());// 二级商户号，使用分账功能时上传，是与stdmercno关联的分账子商户号
		passDTO.setStdauthid(passivePayDTO.getStdauthid());// 设置授权码
		passDTO.setStdorderid(DateUtils.getNowYMDOnlyStr() + passivePayDTO.getStdauthid()
				+ sequenceService.getOrderSequence("t_trade_order"));// 商户订单号，32个字符内

		String passiveStr = JSON.toJSONString(passDTO);// 将对象转换为JSON字符串
		Map<String, String> passiveMap = JSON.parseObject(passiveStr, Map.class);// 将JSON字符串转换为map
		String respStr = ZxyhPayMD5Util.request(passiveMap, url, env.getProperty("zxyh.pay.url"));

		// 解析返回报文
		Map<String, Object> respMap = ZxyhPayMD5Util.getResp(respStr);
		logger.info("扫一扫，校验授权码交易返回:" + respMap);
		String json = JSON.toJSONString(respMap);
		PassivePayDTO passDTO1 = JSON.parseObject(json, PassivePayDTO.class);
		logger.info("校验微信授权码:二级商户号=[" + passivePayDTO.getStdmercno2());
		logger.info("校验微信授权码：返回respCode=[" + passDTO1.getStd400mgid());

		return passDTO1.getStd400mgid();
	}

	/**
	 * 支付宝主扫回调函数
	 */
	public ResultDTO<Object> aliCallBack(String respStr) {
		// 解析返回报文
		assert respStr.startsWith("sendData=");
		String respB64Str = respStr.substring(9);
		// base64解码,并对一些特殊字符进行置换
		byte[] respJsBs = Base64.decodeBase64(respB64Str);
		String respJsStr = null;
		try {
			respJsStr = new String(respJsBs, "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		TradeAliPayCallBackDTO aliDTO = JSON.parseObject(respJsStr, TradeAliPayCallBackDTO.class);
		if (aliDTO == null) {
			return ResultDTO.fail("中信返回数据有误");
		}
		logger.error("支付宝回调返回数据=" + aliDTO.toString());
		TradeOrderDO tradeOrderDO = new TradeOrderDO();
		tradeOrderDO.setOrderNo(aliDTO.getOrderId());// 商户订单号
		tradeOrderDO.setPayOrderNo(aliDTO.getTransactionId());// 渠道(扫码)订单号
		if ("0000".equals(aliDTO.getRespCode())) { // 应答成功
			tradeOrderDO.setRespCode(E789ApiConstant.ResponCodeEnum.DEAL_SUCCESS.getCode());// 交易返回码
		} else {
			return ResultDTO.fail("回调失败");
		}
		tradeOrderDO.setRespMsg(aliDTO.getRespMsg());
		Date tradeEndTime = DateUtils.formateToDate(aliDTO.getEndTime());
		tradeOrderDO.setCompleteTime(tradeEndTime);// 交易完成时间
		tradeOrderDO.setSettleAmount(new BigDecimal(aliDTO.getSettleAmt()));// 清算金额
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String settleDateStr = aliDTO.getSettleDate();
		try {
			tradeOrderDO.setSettleDate(
					sdf.parse(settleDateStr + String.format("%1$0" + (14 - settleDateStr.length()) + "d", 0)));// 清算日期
																												// [yyyyMMdd]
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int i = tradeOrderDO.getSettleAmount().compareTo(new BigDecimal(0));
		if (i > 0) {
			tradeOrderDO.setSettleStatus(1);
		} else {
			tradeOrderDO.setSettleStatus(2);
		}
		orderDAO.updateByOrderId(tradeOrderDO);
		if (tradeOrderDO.getId() != null) {
			return ResultDTO.success();
		} else {
			return ResultDTO.fail("支付宝主扫交易插入失败");
		}
	}

	/**
	 * 微信主扫回调函数
	 */
	public ResultDTO<Object> weChatCallBack(String respStr) {
		// 解析返回报文
		assert respStr.startsWith("sendData=");
		String respB64Str = respStr.substring(9);
		// base64解码,并对一些特殊字符进行置换
		byte[] respJsBs = Base64.decodeBase64(respB64Str);
		String respJsStr = null;
		try {
			respJsStr = new String(respJsBs, "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		TradeWeChatCallBackDTO weChatDTO = JSON.parseObject(respJsStr, TradeWeChatCallBackDTO.class);
		if (weChatDTO == null) {
			return ResultDTO.fail("中信返回数据有误");
		}
		logger.error("微信回调函数返回" + weChatDTO.toString());
		TradeOrderDO tradeOrderDO = new TradeOrderDO();
		tradeOrderDO.setOrderNo(weChatDTO.getOrderId());// 商户订单号
		tradeOrderDO.setPayOrderNo(weChatDTO.getTransactionId());// 渠道(扫码)订单号
		if ("0000".equals(weChatDTO.getRespCode())) { // 应答成功
			tradeOrderDO.setRespCode(E789ApiConstant.ResponCodeEnum.DEAL_SUCCESS.getCode());// 交易返回码
		} else {
			return ResultDTO.fail("回调失败");
		}
		tradeOrderDO.setRespCode(weChatDTO.getRespCode());// 交易返回码
		tradeOrderDO.setRespMsg(weChatDTO.getRespMsg());
		Date tradeEndTime = DateUtils.formateToDate(weChatDTO.getEndTime());
		tradeOrderDO.setCompleteTime(tradeEndTime);// 交易完成时间
		tradeOrderDO.setSettleAmount(new BigDecimal(weChatDTO.getSettleAmt()));// 清算金额
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String settleDateStr = weChatDTO.getSettleDate();
		try {
			tradeOrderDO.setSettleDate(
					sdf.parse(settleDateStr + String.format("%1$0" + (14 - settleDateStr.length()) + "d", 0)));// 清算日期
																												// [yyyyMMdd]
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int i = tradeOrderDO.getSettleAmount().compareTo(new BigDecimal(0));
		if (i > 0) {
			tradeOrderDO.setSettleStatus(1);
		} else {
			tradeOrderDO.setSettleStatus(2);
		}
		orderDAO.updateByOrderId(tradeOrderDO);
		if (tradeOrderDO.getId() != null) {
			return ResultDTO.success();
		} else {
			return ResultDTO.fail("微信主扫交易插入失败");
		}
	}

	public Integer getCheckStatus(String innerCode) {

		Integer status = orderDAO.getCheckStatus(innerCode);
		return status;
	}

}
