package net.fnsco.trading.service.third.reCharge;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.google.common.base.Strings;

import net.fnsco.bigdata.comm.ServiceConstant.TradeStateEnum;
import net.fnsco.bigdata.comm.ServiceConstant.TradeTypeEnum;
import net.fnsco.core.alipay.AlipayAppPayRequestParams;
import net.fnsco.core.alipay.AlipayClientUtil;
import net.fnsco.core.alipay.AlipayRefundRequestParams;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.CodeUtil;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.HttpUtils;
import net.fnsco.freamwork.comm.Md5Util;
import net.fnsco.trading.comm.TradeConstants;
import net.fnsco.trading.comm.TradeConstants.ThrRechargeStateEnum;
import net.fnsco.trading.comm.TradeConstants.WithdrawStateEnum;
import net.fnsco.trading.service.account.AppAccountBalanceService;
import net.fnsco.trading.service.third.reCharge.dto.ChargeDTO;
import net.fnsco.trading.service.third.reCharge.dto.ChargeResultDTO;
import net.fnsco.trading.service.third.reCharge.dto.CheckChargePackageDTO;
import net.fnsco.trading.service.third.reCharge.dto.CheckMobileDTO;
import net.fnsco.trading.service.third.reCharge.dto.JuheDTO;
import net.fnsco.trading.service.third.reCharge.entity.RechargeOrderDO;
import net.fnsco.trading.service.withdraw.TradeWithdrawService;
import net.fnsco.trading.service.withdraw.entity.TradeWithdrawDO;

@Service
public class PrepaidRefillService extends BaseService {

	@Autowired
	private TradeWithdrawService tradeWithdrawService;
	@Autowired
	private RechargeOrderService rechargeOrderService;
	@Autowired
	private Environment env;
	@Autowired
	private AppAccountBalanceService appAccountBalanceService;

	/**
	 * 号码充值套餐优惠资费查询，面额：10，20，30，50，100，200,循环获取各个面额原价与优惠价格
	 * 
	 * @param phone
	 * @return
	 */
	public ResultDTO<CheckChargePackageDTO> prepaidRefillCheck(String phone) {

		String telQueryUrl = "http://op.juhe.cn/ofpay/mobile/telquery?cardnum=";
		Integer[] denos = { 10, 20, 30, 50, 100, 200, 300 };
		String result;
		CheckChargePackageDTO phChargePackageDTO = new CheckChargePackageDTO();
		List<CheckMobileDTO> list = new ArrayList<>();

		for (Integer done : denos) {

			try {
				/**
				 * 充值资费查询，聚合数据返回 { "reason": "成功", "result": { "cardid":
				 * "191404", //卡类ID "cardname": "江苏电信话费100元直充", //卡类名称
				 * "inprice": 98.4, //购买价格 "game_area": "江苏苏州电信" //手机号码归属地 },
				 * "error_code": 0 }
				 */
				String url = telQueryUrl + done + "&phoneno=" + phone + "&key=" + env.getProperty("jh.phone.feekey");
				// result = RechargeUtil.get(url, 0);
				result = HttpUtils.get(url);// 改成调用HttpClientUtil工具类方法
				logger.info(result);

				JuheDTO juhe = JSON.parseObject(result, JuheDTO.class);
				if (juhe.getError_code() != 0) {
					logger.error("话费资费查询失败,原因:+" + juhe.getReason());
					return ResultDTO.fail(juhe.getReason());

				} else {
					logger.info("" + juhe.getResult());

					Map<String, Object> map = JSONObject.parseObject(juhe.getResult().toString(), Map.class);
					CheckMobileDTO phChargeDTO = new CheckMobileDTO();
					phChargeDTO.setId(String.valueOf(done));
					phChargeDTO.setName(done + "元");
					BigDecimal bigDecimal = new BigDecimal(map.get("inprice").toString());
					phChargeDTO.setInprice(bigDecimal + "");
					list.add(phChargeDTO);
					phChargePackageDTO.setCompany(map.get("game_area").toString());
					phChargePackageDTO.setCardArea(map.get("game_area").toString());
					phChargePackageDTO.setType("1");// 设置支持类型，1-全国
				}
			} catch (Exception e) {
				logger.error("话费资费查询出现异常，prepaidRefillCheck,", e);
			}
		}

		phChargePackageDTO.setList(list);// 设置套餐资费列表
		return ResultDTO.success(phChargePackageDTO);
	}

	/**
	 * 话费充值-套餐查询,单查,通过传入PID和手机号查询实际售价金额
	 * 
	 * @param phone:充值号码
	 * @param amt:充值金额
	 * @return:售价金额
	 */
	public String queryCharge(String phone, Integer amt) {
		String telQueryUrl = "http://op.juhe.cn/ofpay/mobile/telquery?cardnum=";
		String result;
		ChargeResultDTO ph = new ChargeResultDTO();
		try {
			/**
			 * 充值资费查询，聚合数据返回 { "reason": "成功", "result": { "cardid": "191404",
			 * //卡类ID "cardname": "江苏电信话费100元直充", //卡类名称 "inprice": 98.4, //购买价格
			 * "game_area": "江苏苏州电信" //手机号码归属地 }, "error_code": 0 }
			 */
			String url = telQueryUrl + amt + "&phoneno=" + phone + "&key=" + env.getProperty("jh.phone.feekey");
			// result = RechargeUtil.get(url, 0);
			result = HttpUtils.get(url);// 改成调用HttpClientUtil工具类方法
			logger.info(result);

			JuheDTO juhe = JSON.parseObject(result, JuheDTO.class);
			if (juhe.getError_code() != 0) {
				logger.error("话费充值--话费资费查询失败,原因:+" + juhe.getReason());
				return null;

			} else {
				logger.info("话费充值--话费资费查询成功" + juhe.getResult());

				Map<String, Object> map = JSONObject.parseObject(juhe.getResult().toString(), Map.class);

				return map.get("inprice").toString();
			}
		} catch (Exception e) {
			logger.error("话费资费查询出现异常，prepaidRefillCheck,", e);
		}

		return null;

	}

	/**
	 * 
	 * @param phone:手机号
	 * @param pid:套餐ID
	 * @return:实际售价金额，如果为空表示没找到pid套餐或者查询套餐失败等等，其他表示成功
	 */
	public String queryFlowExpen(String phone, String pid) {

		String result = null;
		String url = "http://v.juhe.cn/flow/telcheck";// 请求接口地址
		CheckChargePackageDTO phChargePackageDTO = new CheckChargePackageDTO();

		StringBuffer sb = new StringBuffer();
		String sendData = sb.append("?phone=").append(phone).append("&key=").append(env.getProperty("jh.phone.flowkey"))
				.toString();

		try {

			result = HttpUtils.get(url + sendData);// 改成调用HttpClientUtil工具类方法
			JuheDTO juhe = JSONObject.parseObject(result, JuheDTO.class);

			// 查询失败
			if (juhe.getError_code() != 0) {

				logger.error("流量资费查询出错:" + juhe.getError_code() + ":" + juhe.getReason());
				return null;

			} else {

				// 获取需要返回的数据域(套餐类型)
				JSONArray jsonArray = (JSONArray) juhe.getResult();
				String str = jsonArray.get(0).toString();

				Map<String, Object> map = JSONObject.parseObject(str, Map.class);
				logger.info(map.toString());

				// 获取流量资费列表
				List<Map<String, String>> lists = JSONObject.parseObject(map.get("flows").toString(), List.class);
				for (Map<String, String> map2 : lists) {

					// 通过id查找实际售价金额，并返回
					if (map2.get("id").equals(pid.trim())) {

						return new BigDecimal(map2.get("inprice")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					}
				}

				return null;
			}
		} catch (Exception e) {
			logger.error("流量资费套餐查询异常，flowPackageCheck,", e);
		}
		return null;

	}

	/**
	 * 号码流量套餐优惠资费查询
	 * 
	 * @param phone:手机号码
	 * @return
	 */
	public ResultDTO<CheckChargePackageDTO> flowPackageCheck(String phone) {
		String result = null;
		String url = "http://v.juhe.cn/flow/telcheck";// 请求接口地址
		CheckChargePackageDTO phChargePackageDTO = new CheckChargePackageDTO();
		List<CheckMobileDTO> list = new ArrayList<>();

		StringBuffer sb = new StringBuffer();
		String sendData = sb.append("?phone=").append(phone).append("&key=").append(env.getProperty("jh.phone.flowkey"))
				.toString();

		try {
			/**
			 * 数据返回格式如下： { "reason": "success", "result": [ { "city": "全国",
			 * //支持城市 "company": "中国移动", //运营商 "companytype": "2", //运营商ID
			 * "name": "中国移动全国流量套餐", //套餐名称 "type": "1", //支持类型1：全国 2：城市
			 * "flows": [ //流量套餐列表 { "id": "3", //套餐ID "p": "10M", //套餐流量名称 "v":
			 * "10", //套餐流量值 "inprice": "2.90" //价格 } ] } ], "error_code": 0 }
			 */
			result = HttpUtils.get(url + sendData);// 改成调用HttpClientUtil工具类方法
			// result = RechargeUtil.net(url, sendData, "GET");
			JuheDTO juhe = JSONObject.parseObject(result, JuheDTO.class);

			// 查询失败
			if (juhe.getError_code() != 0) {

				logger.error("流量资费查询出错:" + juhe.getError_code() + ":" + juhe.getReason());
				return ResultDTO.fail(juhe.getReason());

			} else {

				// 获取需要返回的数据域(套餐类型)
				JSONArray jsonArray = (JSONArray) juhe.getResult();
				String str = jsonArray.get(0).toString();

				Map<String, Object> map = JSONObject.parseObject(str, Map.class);
				logger.info(map.toString());
				phChargePackageDTO.setCompany(map.get("company").toString());
				phChargePackageDTO.setType(map.get("type").toString());
				List<Map<String, String>> lists = JSONObject.parseObject(map.get("flows").toString(), List.class);
				for (Map<String, String> map2 : lists) {
					/**
					 * 返回的套餐资费list [id string 套餐ID; p string 套餐流量名称 ; v string
					 * 套餐流量值 ; inprice string 价格]
					 */
					CheckMobileDTO phChargeDTO = new CheckMobileDTO();
					phChargeDTO.setId(map2.get("id"));
					phChargeDTO.setName(map2.get("p"));
					// 金额设置两位小数
					phChargeDTO.setInprice(
							(new BigDecimal(map2.get("inprice")).setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
					list.add(phChargeDTO);
				}
				phChargePackageDTO.setList(list);

				return ResultDTO.success(phChargePackageDTO);
			}
		} catch (Exception e) {
			logger.error("流量资费套餐查询异常，flowPackageCheck,", e);
		}
		return ResultDTO.fail();
	}

	/**
	 * 流量充值订单状态查询(用于在充值返回系统内部异常时调用)
	 * 
	 * @param orderNo：原订单号
	 */
	@SuppressWarnings({ "unchecked" })
	public ResultDTO<ChargeResultDTO> queryFlowResult(RechargeOrderDO rechargeOrderDO) {

		String result = null;
		String url = "http://v.juhe.cn/flow/ordersta";// 请求接口地址
		ChargeResultDTO ph = new ChargeResultDTO();

		TradeWithdrawDO tradeWithdrawDO = tradeWithdrawService.getByOrderNo(rechargeOrderDO.getOrderNo());
		if (null == tradeWithdrawDO) {// 如果该条数据为空

			logger.info("流量充值订单查询-该条数据为空oderNo=" + rechargeOrderDO.getOrderNo());

			tradeWithdrawDO = new TradeWithdrawDO();
			tradeWithdrawDO.setOrderNo(rechargeOrderDO.getOrderNo());// 设置订单号
			tradeWithdrawDO.setAmount(new BigDecimal(rechargeOrderDO.getAmt()));// 设置交易金额，优惠金额
			tradeWithdrawDO.setAppUserId(Integer.parseInt(rechargeOrderDO.getAppUserId()));// 设置帐号ID
			tradeWithdrawDO.setTradeType(2);// 交易类型:2-消费
			if ("0" == rechargeOrderDO.getType()) {
				tradeWithdrawDO.setTradeSubType(TradeConstants.TxnSubTypeEnum.BUY_HF.getCode());// 交易子类型:22话费充值
			} else {
				tradeWithdrawDO.setTradeSubType(TradeConstants.TxnSubTypeEnum.BUY_LT.getCode());// 交易子类型:23流量充值
			}
			tradeWithdrawDO.setStatus(WithdrawStateEnum.PROCESSING.getCode());// 设置交易状态，1-执行中
			tradeWithdrawService.doAdd(tradeWithdrawDO);// 更新数据库表
		}

		// 如果结果已经明确
		if ("1001".equals(tradeWithdrawDO.getRespCode())) {

			logger.info("流量充值-结果查询：该订单已经在tradeWith表中更新为确定状态，不必再去聚合查询支付结果orderNo=" + rechargeOrderDO.getOrderNo());
			rechargeOrderDO.setPayOrderNo(tradeWithdrawDO.getOriginalOrderNo());// 设置渠道订单号
			rechargeOrderDO.setAmt(tradeWithdrawDO.getAmount().toString());// 设置实际消费金额
			rechargeOrderDO.setStatus(1);// 设置交易状态为1-成功
			rechargeOrderDO.setRespCode(TradeStateEnum.SUCCESS.getCode());
			rechargeOrderDO.setRespMsg("充值成功");
			rechargeOrderService.doUpdate(rechargeOrderDO);

			ph.setRespCode(rechargeOrderDO.getRespCode());
			ph.setOrderNo(rechargeOrderDO.getOrderNo());
			ph.setRespMsg(tradeWithdrawDO.getRespMsg());
			return ResultDTO.success(ph);
		} else if ("1002".equals(tradeWithdrawDO.getRespCode())) {

			logger.info("流量充值-结果查询：该订单已经在tradeWith表中更新为确定状态，不必再去聚合查询支付结果orderNo=" + rechargeOrderDO.getOrderNo());
			rechargeOrderDO.setStatus(2);// 设置交易状态为2-失败
			rechargeOrderDO.setRespMsg(tradeWithdrawDO.getRespMsg());
			rechargeOrderService.doUpdate(rechargeOrderDO);

			ph.setRespCode(rechargeOrderDO.getRespCode());
			ph.setRespMsg(tradeWithdrawDO.getRespMsg());
			return ResultDTO.success(ph);
		}

		// 查询主体
		try {
			// 请求参数
			StringBuffer sb = new StringBuffer();
			String sendData = sb.append("?key=").append(env.getProperty("jh.phone.flowkey")).append("&orderid=")
					.append(rechargeOrderDO.getOrderNo()).toString();

			// 用户订单号，多个以英文逗号隔开，最大支持50组
			// 应用APPKEY(应用详细页查询)
			// result = RechargeUtil.net(url, params, "GET");
			result = HttpUtils.get(url + sendData);
			logger.info("流量充值-结果查询-返回数据:" + result);
			JuheDTO juhe = JSONObject.parseObject(result, JuheDTO.class);

			TradeWithdrawDO tradeWithdrawDO1 = new TradeWithdrawDO();
			tradeWithdrawDO1.setId(tradeWithdrawDO.getId());

			// 查询返回成功，则解析result字串取其中的充值状态
			if (juhe.getError_code() == 0) {

				Map<String, String> map = JSONObject.parseObject(juhe.getResult().toString(), Map.class);
				if ("1".equals(map.get("game_state"))) {// 成功

					logger.info("流量充值-查询结果:充值成功");
					rechargeOrderDO.setPayOrderNo(map.get("sporder_id").toString());// 设置渠道订单号

					String amtStr = new BigDecimal(map.get("uordercash")).setScale(2, BigDecimal.ROUND_HALF_UP)
							.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP).toString();

					// 更新冻结金额(减掉本次冻结的金额)
					Boolean flag = appAccountBalanceService.doUpdateFrozenAmount(tradeWithdrawDO.getAppUserId(),
							new BigDecimal(amtStr));
					// 更新冻结金额失败，则记录日志，以便于后续对账
					if (!flag) {
						logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
						logger.error("流量充值-查询结果:充值成功，但是更新冻结金额出错，orderNo=" + tradeWithdrawDO.getOrderNo());
						logger.error("流量充值-查询结果:充值成功，但是更新冻结金额出错，appUserId=" + tradeWithdrawDO.getAppUserId());
						logger.error("流量充值-查询结果:充值成功，但是更新冻结金额出错，amt=" + amtStr);
						logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					}

					rechargeOrderDO.setAmt(amtStr);// 设置实际消费金额
					rechargeOrderDO.setStatus(1);// 设置交易状态为1-成功
					rechargeOrderDO.setRespCode(TradeStateEnum.SUCCESS.getCode());
					rechargeOrderDO.setRespMsg("充值成功");
					rechargeOrderService.doUpdate(rechargeOrderDO);

					tradeWithdrawDO.setAmount(new BigDecimal(rechargeOrderDO.getAmt()));// 设置实际消费金额
					tradeWithdrawDO.setStatus(3);
					tradeWithdrawDO.setRespCode(TradeStateEnum.SUCCESS.getCode());
					tradeWithdrawDO.setRespMsg("充值成功");
					tradeWithdrawService.doUpdate(tradeWithdrawDO);

					ph.setRespCode(tradeWithdrawDO.getRespCode());
					ph.setOrderNo(tradeWithdrawDO.getOrderNo());
					ph.setRespMsg(tradeWithdrawDO.getRespMsg());
					return ResultDTO.success(ph);

				} else if ("9".equals(map.get("game_state"))) {// 失败

					logger.info("流量充值-查询结果:充值失败，需要将原用户账户资金还原");
					// 还原APP账户余额
					Boolean flag = appAccountBalanceService.doFrozenBalance(tradeWithdrawDO.getAppUserId(),
							new BigDecimal(0).subtract(tradeWithdrawDO.getAmount()));
					if (!flag) {
						logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
						logger.error("手机充值-查询结果:充值失败，账户余额还原失败，请联系相关技术人员查看,appUserId=[" + tradeWithdrawDO.getAppUserId()
								+ "],orderNo=" + tradeWithdrawDO.getOrderNo() + "inprice=["
								+ tradeWithdrawDO.getAmount() + "]");
						logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					}

					rechargeOrderDO.setStatus(2);// 设置交易状态为2-失败
					rechargeOrderService.doUpdate(rechargeOrderDO);

					tradeWithdrawDO.setStatus(2);
					tradeWithdrawDO.setRespCode(TradeStateEnum.FAIL.getCode());
					tradeWithdrawDO.setRespMsg(juhe.getReason());
					tradeWithdrawService.doUpdate(tradeWithdrawDO);

					ph.setRespCode(tradeWithdrawDO.getRespCode());
					ph.setRespMsg(juhe.getReason());
					return ResultDTO.success(ph);
				} else if ("0".equals(map.get("game_state"))) {// 失败

					logger.info("流量充值-支付结果未知，需要继续进行结果查询");
					ph.setRespCode("1000");
					ph.setRespMsg("支付正在处理，请稍后查询");
					return ResultDTO.success(ph);
				}

			} else if (210509 == juhe.getError_code() || 210511 == juhe.getError_code()) {// 订单号错误或者订单号不存在

				logger.info("流量充值-查询结果:充值失败," + juhe.getReason());
				rechargeOrderDO.setStatus(2);// 设置交易状态为2-失败
				rechargeOrderService.doUpdate(rechargeOrderDO);

				tradeWithdrawDO.setStatus(2);
				tradeWithdrawDO.setRespCode(TradeStateEnum.FAIL.getCode());
				tradeWithdrawDO.setRespMsg(juhe.getReason());
				tradeWithdrawService.doUpdate(tradeWithdrawDO);

				ph.setRespCode(tradeWithdrawDO.getRespCode());
				ph.setRespMsg(juhe.getReason());
				return ResultDTO.success(ph);
			} else {

				logger.info("流量充值-支付结果未知，需要继续进行结果查询");
				ph.setRespCode("1000");
				ph.setRespMsg("支付正在处理，请稍后查询");
				return ResultDTO.success("支付正在处理，请稍后查询");
			}
		} catch (Exception e) {
			logger.error("流量充值结果查询-异常", e);
		}
		return ResultDTO.fail();
	}

	/**
	 *  话费充值状态查询
	 * 
	 * @param orderid 商家订单号  @return 订单结果
	 * @throws Exception
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ResultDTO<ChargeResultDTO> orderSta(RechargeOrderDO rechargeOrderDO) {

		String result = null;
		String orderstaUrl = "http://op.juhe.cn/ofpay/mobile/ordersta";
		ChargeResultDTO ph = new ChargeResultDTO();

		TradeWithdrawDO tradeWithdrawDO = tradeWithdrawService.getByOrderNo(rechargeOrderDO.getOrderNo());
		if (null == tradeWithdrawDO) {// 如果该条数据为空

			logger.info("话费充值订单查询-该条数据为空oderNo=" + rechargeOrderDO.getOrderNo());

			tradeWithdrawDO = new TradeWithdrawDO();
			tradeWithdrawDO.setOrderNo(rechargeOrderDO.getOrderNo());// 设置订单号
			tradeWithdrawDO.setAmount(new BigDecimal(rechargeOrderDO.getAmt()));// 设置交易金额，优惠金额
			tradeWithdrawDO.setAppUserId(Integer.parseInt(rechargeOrderDO.getAppUserId()));// 设置帐号ID
			tradeWithdrawDO.setTradeType(2);// 交易类型:2-消费
			if ("0" == rechargeOrderDO.getType()) {
				tradeWithdrawDO.setTradeSubType(TradeConstants.TxnSubTypeEnum.BUY_HF.getCode());// 交易子类型:22话费充值
			} else {
				tradeWithdrawDO.setTradeSubType(TradeConstants.TxnSubTypeEnum.BUY_LT.getCode());// 交易子类型:23流量充值
			}
			tradeWithdrawDO.setStatus(WithdrawStateEnum.PROCESSING.getCode());// 设置交易状态，1-执行中
			tradeWithdrawService.doAdd(tradeWithdrawDO);// 更新数据库表

		}

		// 如果结果已经明确
		if ("1001".equals(tradeWithdrawDO.getRespCode())) {

			logger.info("话费充值-结果查询：该订单已经在tradeWith表中更新为确定状态，不必再去聚合查询支付结果orderNo=" + rechargeOrderDO.getOrderNo());
			rechargeOrderDO.setPayOrderNo(tradeWithdrawDO.getOriginalOrderNo());// 设置渠道订单号
			rechargeOrderDO.setAmt(tradeWithdrawDO.getAmount().toString());// 设置实际消费金额
			rechargeOrderDO.setStatus(1);// 设置交易状态为1-成功
			rechargeOrderDO.setRespCode(TradeStateEnum.SUCCESS.getCode());
			rechargeOrderDO.setRespMsg("充值成功");
			rechargeOrderService.doUpdate(rechargeOrderDO);

			ph.setRespCode("1001");
			ph.setOrderNo(rechargeOrderDO.getOrderNo());
			ph.setRespMsg(rechargeOrderDO.getRespMsg());
			return ResultDTO.success(ph);
		} else if ("1002".equals(tradeWithdrawDO.getRespCode())) {

			logger.info("手机充值-结果查询：该订单已经在tradeWith表中更新为确定状态，不必再去聚合查询支付结果orderNo=" + rechargeOrderDO.getOrderNo());
			rechargeOrderDO.setStatus(2);// 设置交易状态为2-失败
			rechargeOrderDO.setRespMsg(tradeWithdrawDO.getRespMsg());
			rechargeOrderService.doUpdate(rechargeOrderDO);

			ph.setRespCode("1002");
			ph.setRespMsg(rechargeOrderDO.getRespMsg());
			return ResultDTO.success(ph);
		}

		try {
			StringBuffer sb = new StringBuffer();
			String sendData = sb.append("?key=").append(env.getProperty("jh.phone.feekey")).append("&orderid=")
					.append(rechargeOrderDO.getOrderNo()).toString();
			result = HttpUtils.get(orderstaUrl + sendData);
			logger.info("话费充值结果查询-返回数据:" + result);
			// result = RechargeUtil.get(orderstaUrl.replace("!",
			// rechargeOrderDO.getOrderNo()), 0);
			JuheDTO juhe = JSONObject.parseObject(result, JuheDTO.class);

			TradeWithdrawDO tradeWithdrawDO1 = new TradeWithdrawDO();
			tradeWithdrawDO1.setId(tradeWithdrawDO.getId());
			// 查询返回成功，则解析result字串取其中的充值状态
			if (juhe.getError_code() == 0) {

				Map<String, String> map = JSONObject.parseObject(juhe.getResult().toString(), Map.class);

				if ("1".equals(map.get("game_state"))) {// 成功

					logger.info("手机充值-查询结果:充值成功");
					rechargeOrderDO.setPayOrderNo(map.get("sporder_id").toString());// 设置渠道订单号

					String amtStr = new BigDecimal(map.get("uordercash")).setScale(2, BigDecimal.ROUND_HALF_UP)
							.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP).toString();

					// 更新冻结金额(减掉本次冻结的金额)
					Boolean flag = appAccountBalanceService.doUpdateFrozenAmount(tradeWithdrawDO.getAppUserId(),
							new BigDecimal(amtStr));
					// 更新冻结金额失败，则记录日志，以便于后续对账
					if (!flag) {
						logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
						logger.error("手机充值-查询结果:充值成功，但是更新冻结金额出错，orderNo=" + tradeWithdrawDO.getOrderNo());
						logger.error("手机充值-查询结果:充值成功，但是更新冻结金额出错，appUserId=" + tradeWithdrawDO.getAppUserId());
						logger.error("手机充值-查询结果:充值成功，但是更新冻结金额出错，amt=" + amtStr);
						logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					}

					rechargeOrderDO.setAmt(amtStr);// 设置实际消费金额
					rechargeOrderDO.setStatus(1);// 设置交易状态为1-成功
					rechargeOrderDO.setRespCode(TradeStateEnum.SUCCESS.getCode());
					rechargeOrderDO.setRespMsg("充值成功");
					Integer ret = rechargeOrderService.doUpdate(rechargeOrderDO);
					if (ret < 0) {
						logger.error("充值结果查询成功，原充值交易成功,数据更新失败。orderNo=" + rechargeOrderDO.getOrderNo() + "userId="
								+ rechargeOrderDO.getAppUserId());
					}

					tradeWithdrawDO1.setAmount(new BigDecimal(rechargeOrderDO.getAmt()));// 设置实际消费金额
					tradeWithdrawDO1.setStatus(3);
					tradeWithdrawDO1.setRespCode(TradeStateEnum.SUCCESS.getCode());
					tradeWithdrawDO1.setRespMsg("充值成功");
					tradeWithdrawService.doUpdate(tradeWithdrawDO1);

					ph.setRespCode("1001");
					ph.setOrderNo(tradeWithdrawDO.getOrderNo());
					ph.setRespMsg(rechargeOrderDO.getRespMsg());
					return ResultDTO.success(ph);
				} else if ("9".equals(map.get("game_state"))) {// 失败

					logger.info("手机充值-查询结果:充值失败，需要将原用户账户资金还原");
					// 还原APP账户余额
					Boolean flag = appAccountBalanceService.doFrozenBalance(tradeWithdrawDO.getAppUserId(),
							new BigDecimal(0).subtract(tradeWithdrawDO.getAmount()));
					if (!flag) {
						logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
						logger.error("手机充值-查询结果:充值失败，账户余额还原失败，请联系相关技术人员查看,appUserId=[" + tradeWithdrawDO.getAppUserId()
								+ "],orderNo=" + tradeWithdrawDO.getOrderNo() + "inprice=["
								+ tradeWithdrawDO.getAmount() + "]");
						logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					}

					rechargeOrderDO.setStatus(2);// 设置交易状态为2-失败
					Integer ret = rechargeOrderService.doUpdate(rechargeOrderDO);
					if (ret < 0) {
						logger.error("充值结果查询成功，原充值交易失败,数据更新失败。orderNo=" + rechargeOrderDO.getOrderNo() + "userId="
								+ rechargeOrderDO.getAppUserId());
					}

					tradeWithdrawDO1.setStatus(2);
					tradeWithdrawDO1.setRespCode(TradeStateEnum.FAIL.getCode());
					tradeWithdrawDO1.setRespMsg(juhe.getReason());
					tradeWithdrawService.doUpdate(tradeWithdrawDO1);

					ph.setRespCode("1002");
					ph.setRespMsg(juhe.getReason());

					return ResultDTO.success(ph);
				}
			} else if (208509 == juhe.getError_code()) {// 订单号错误或者订单号不存在

				logger.info("手机充值-查询结果:充值失败," + juhe.getReason());
				rechargeOrderDO.setStatus(2);// 设置交易状态为2-失败
				rechargeOrderService.doUpdate(rechargeOrderDO);

				tradeWithdrawDO.setStatus(2);
				tradeWithdrawDO.setRespCode(TradeStateEnum.FAIL.getCode());
				tradeWithdrawDO.setRespMsg(juhe.getReason());
				tradeWithdrawService.doUpdate(tradeWithdrawDO);

				ph.setRespCode(tradeWithdrawDO.getRespCode());
				ph.setRespMsg(juhe.getReason());
				return ResultDTO.success(ph);
			} else {

				logger.info("手机充值-支付结果未知，需要继续进行结果查询");
				ph.setRespCode("1000");
				ph.setRespMsg("支付正在处理，请稍后查询");

				return ResultDTO.success(ph);
			}
		} catch (Exception e) {
			logger.error("话费充值结果查询-异常", e);
		}
		return ResultDTO.fail();
	}

	/**
	 * 用帐户余额充值话费/流量，判断账户金额，插表
	 * 
	 * @param chargeDTO
	 * @return
	 */
	public ResultDTO<ChargeResultDTO> acctRecharge(ChargeDTO chargeDTO) {

		String orderid = CodeUtil.generateOrderCode("ACCT");
		TradeWithdrawDO tradeWithdrawDO = new TradeWithdrawDO();
		ChargeResultDTO ph = new ChargeResultDTO();
		String payName = null;

		RechargeOrderDO phoneChargeOrderDO = new RechargeOrderDO();
		phoneChargeOrderDO.setType(String.valueOf(chargeDTO.getType()));// 设置充值类型0-话费；1-流量
		phoneChargeOrderDO.setAppUserId(String.valueOf(chargeDTO.getUserId()));// 设置app用户ID
		phoneChargeOrderDO.setOrderNo(orderid);// 设置商户订单ID
		phoneChargeOrderDO.setMobile(chargeDTO.getPhone());// 设置充值手机号
		phoneChargeOrderDO.setName(chargeDTO.getName());// 设置充值名称:xx元
		phoneChargeOrderDO.setAmt(chargeDTO.getInprice().replace(".", ""));// 设置交易金额
		phoneChargeOrderDO.setStatus(ThrRechargeStateEnum.PROCESSING.getCode());// 设置交易状态3-待支付

		tradeWithdrawDO.setOrderNo(orderid);// 设置订单号
		tradeWithdrawDO.setOriginalOrderNo(orderid);// 设置原订单号(默认等于当前订单号)
		tradeWithdrawDO.setAmount(new BigDecimal(phoneChargeOrderDO.getAmt()));// 设置交易金额，优惠金额
		tradeWithdrawDO.setAppUserId(chargeDTO.getUserId());// 设置帐号ID
		tradeWithdrawDO.setTradeType(Integer.parseInt(TradeTypeEnum.CONSUMER.getCode()));// 交易类型:2-消费
		if (0 == chargeDTO.getType()) {

			// 先通过pid查询实际售价,然后判断传过来的金额和售价是否一致
			String amt = queryCharge(chargeDTO.getPhone(), Integer.parseInt(chargeDTO.getPid()));

			logger.info("话费充值-pid金额校验:amt=[" + amt + "],inprice=[" + chargeDTO.getInprice() + "]");
			if (!amt.equals(chargeDTO.getInprice())) {

				logger.error("套餐售价金额与app端传入参不一致，交易失败");
				tradeWithdrawDO.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());
				tradeWithdrawDO.setRespMsg("套餐售价金额与app端传入参不一致，交易失败");
				tradeWithdrawDO.setStatus(WithdrawStateEnum.FAIL.getCode());

				phoneChargeOrderDO.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());
				phoneChargeOrderDO.setRespMsg("套餐售价金额与app端传入参不一致，交易失败");
				phoneChargeOrderDO.setStatus(ThrRechargeStateEnum.FAIL.getCode());

				tradeWithdrawService.doAdd(tradeWithdrawDO);// 新增
				rechargeOrderService.doAdd(phoneChargeOrderDO);

				return ResultDTO.fail("套餐售价金额与app端传入参不一致，交易失败");
			}

			// 售价金额正常
			BigDecimal inprice = new BigDecimal(amt).multiply(new BigDecimal(100)).setScale(0,
					BigDecimal.ROUND_HALF_UP);
			tradeWithdrawDO.setAmount(inprice);// 设置交易金额
			phoneChargeOrderDO.setAmt(tradeWithdrawDO.getAmount().toString());// 设置交易金额
			tradeWithdrawDO.setTradeSubType(TradeConstants.TxnSubTypeEnum.BUY_HF.getCode());// 交易子类型:22话费充值
			payName = "话费充值";
		} else {

			// 先通过pid查询实际售价,然后判断传过来的金额和售价是否一致
			String amt = queryFlowExpen(chargeDTO.getPhone(), chargeDTO.getPid());

			if (!amt.equals(chargeDTO.getInprice())) {

				logger.error("套餐售价金额与app端传入参不一致，交易失败");
				tradeWithdrawDO.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());
				tradeWithdrawDO.setRespMsg("套餐售价金额与app端传入参不一致，交易失败");
				tradeWithdrawDO.setStatus(WithdrawStateEnum.FAIL.getCode());

				phoneChargeOrderDO.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());
				phoneChargeOrderDO.setRespMsg("套餐售价金额与app端传入参不一致，交易失败");
				phoneChargeOrderDO.setStatus(ThrRechargeStateEnum.FAIL.getCode());

				tradeWithdrawService.doAdd(tradeWithdrawDO);// 新增
				rechargeOrderService.doAdd(phoneChargeOrderDO);

				return ResultDTO.fail("套餐售价金额与app端传入参不一致，交易失败");
			}

			// 售价金额正常
			BigDecimal inprice = new BigDecimal(amt).multiply(new BigDecimal(100)).setScale(0,
					BigDecimal.ROUND_HALF_UP);
			tradeWithdrawDO.setAmount(inprice);// 设置交易金额
			phoneChargeOrderDO.setAmt(tradeWithdrawDO.getAmount().toString());// 设置交易金额
			tradeWithdrawDO.setTradeSubType(TradeConstants.TxnSubTypeEnum.BUY_LT.getCode());// 交易子类型:23流量充值
			payName = "流量充值";
		}
		tradeWithdrawDO.setStatus(WithdrawStateEnum.PROCESSING.getCode());// 设置交易状态，1-执行中

		rechargeOrderService.doAdd(phoneChargeOrderDO);
		tradeWithdrawService.doAdd(tradeWithdrawDO);// 更新数据库表

		// 余额支付
		BigDecimal inprice = new BigDecimal(chargeDTO.getInprice()).multiply(new BigDecimal(100)).setScale(0,
				BigDecimal.ROUND_HALF_UP);

		// 定义对象，便于更新使用
		TradeWithdrawDO tradeWithdrawDO1 = new TradeWithdrawDO();
		tradeWithdrawDO1.setId(tradeWithdrawDO.getId());
		RechargeOrderDO phoneChargeOrderDO1 = new RechargeOrderDO();
		phoneChargeOrderDO1.setId(phoneChargeOrderDO.getId());

		// 更新余额和对应的冻结金额(账户扣款)
		Boolean flag = appAccountBalanceService.doFrozenBalance(chargeDTO.getUserId(), inprice);
		// 更新余额失败，则更新流水表数据为交易失败
		if (!flag) {

			logger.error(payName + "-帐户余额冻结失败！！appUserId=" + chargeDTO.getUserId());
			tradeWithdrawDO1.setRespCode(TradeStateEnum.FAIL.getCode());// 交易进行中，需要再次调用订单查询接口进行查询
			tradeWithdrawDO1.setStatus(WithdrawStateEnum.FAIL.getCode());// 状态为2-失败
			tradeWithdrawDO1.setRespMsg(payName + "帐户余额冻结失败,帐户余额不足.");// 设置响应
			tradeWithdrawDO1.setUpdateTime(new Date());// 设置最后更新时间
			Integer ret2 = tradeWithdrawService.doUpdate(tradeWithdrawDO1);// 更新数据
			if (ret2 < 0) {
				logger.error(payName + "帐户余额不足，tradeWithdraw数据更新失败。time=" + tradeWithdrawDO1.getUpdateTime() + "userId="
						+ chargeDTO.getUserId());
			}

			phoneChargeOrderDO1.setRespCode(TradeStateEnum.FAIL.getCode());// 账户冻结失败，更新数据
			phoneChargeOrderDO1.setRespMsg("帐户余额冻结失败,帐户余额不足.");// 设置响应
			phoneChargeOrderDO1.setStatus(ThrRechargeStateEnum.FAIL.getCode());
			int ret = rechargeOrderService.doUpdate(phoneChargeOrderDO1);
			if (ret < 0) {
				logger.error(payName + "帐户余额不足，phoneChargeOrder数据更新失败。orderNo=" + orderid + "userId="
						+ chargeDTO.getUserId());
			}
			ph.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());
			ph.setRespMsg("帐户余额不足");
			return ResultDTO.success(ph);
		}

		// 获取充值结果
		JuheDTO juhe = recharge(chargeDTO, orderid);
		if (juhe.getError_code() == 0) {

			Map<String, Object> map = JSONObject.parseObject(juhe.getResult().toString(), Map.class);

			phoneChargeOrderDO1.setPayOrderNo(map.get("sporder_id").toString());// 设置聚合订单号

			phoneChargeOrderDO1.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());// 订单提交成功，等待充值
			phoneChargeOrderDO1.setRespMsg(juhe.getReason());// 设置响应
			phoneChargeOrderDO1.setStatus(ThrRechargeStateEnum.PROCESSING.getCode());

			tradeWithdrawDO1.setOriginalOrderNo(map.get("sporder_id").toString());// 设置渠道订单号
			tradeWithdrawDO1.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());// 订单提交成功，等待充值需要再次调用订单查询接口进行查询
			tradeWithdrawDO1.setUpdateTime(new Date());// 设置交易完成时间
			tradeWithdrawDO1.setRespMsg(juhe.getReason());// 设置响应

			ph.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());
			ph.setRespMsg(juhe.getReason());
			ph.setOrderNo(orderid);

		} else if (juhe.getError_code() == 10014) {// 系统内部异常(调用充值类业务时，请务必联系客服或通过订单查询接口检测订单，避免造成损失)

			phoneChargeOrderDO1.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());// 交易进行中
			phoneChargeOrderDO1.setRespMsg(juhe.getReason());// 设置响应
			phoneChargeOrderDO1.setStatus(ThrRechargeStateEnum.PROCESSING.getCode());// 订单提交成功，等待充值

			tradeWithdrawDO1.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());// 交易进行中，需要再次调用订单查询接口进行查询
			tradeWithdrawDO1.setRespMsg(juhe.getReason());// 设置响应
			tradeWithdrawDO1.setUpdateTime(new Date());// 设置最后更新时间

			ph.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());
			ph.setRespMsg("交易正在处理中，请稍后查询");
		} else {

			phoneChargeOrderDO1.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());// 交易失败
			phoneChargeOrderDO1.setRespMsg(juhe.getReason());// 设置响应
			phoneChargeOrderDO1.setStatus(ThrRechargeStateEnum.FAIL.getCode());// 状态为2-失败

			tradeWithdrawDO1.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());// 交易进行中，需要再次调用订单查询接口进行查询
			tradeWithdrawDO1.setStatus(WithdrawStateEnum.FAIL.getCode());// 状态为2-失败
			tradeWithdrawDO1.setRespMsg(juhe.getReason());// 设置响应
			tradeWithdrawDO1.setUpdateTime(new Date());// 设置最后更新时间

			// 设置返回应答数据
			ph.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());
			ph.setRespMsg(juhe.getReason());

			// 还原APP账户余额
			flag = appAccountBalanceService.doFrozenBalance(chargeDTO.getUserId(), new BigDecimal(0).subtract(inprice));
			if (!flag) {
				logger.error(payName + "-充值失败之后，账户余额还原失败，请联系相关技术人员查看,appUserId=[" + chargeDTO.getUserId() + "],orderNo="
						+ ph.getOrderNo() + "inprice=[" + chargeDTO.getInprice() + "]");
				ph.setRespMsg("系统异常，请联系技术人员查看");
			}
		}

		return ResultDTO.success(ph);
	}

	/**
	 * 充值话费/流量主方法
	 * 
	 * @param chargeDTO
	 * @param orderId:订单号
	 * @return:充值结果
	 */
	public JuheDTO recharge(ChargeDTO chargeDTO, String orderId) {

		String result = null;
		String hfUrl = "http://op.juhe.cn/ofpay/mobile/onlineorder";// 话费充值请求url
		String flUrl = "http://v.juhe.cn/flow/recharge";// 流量充值请求url
		String payName = null;
		JuheDTO juhe1 = new JuheDTO<>();

		try {

			// 话费充值
			if (0 == chargeDTO.getType()) {

				// md5,校验值，md5(OpenID+key+phone+pid+orderid)，结果转为小写
				String sign = Md5Util.MD5(env.getProperty("jh.phone.OpenId") + env.getProperty("jh.phone.feekey")
						+ chargeDTO.getPhone() + chargeDTO.getPid() + orderId).toLowerCase();
				StringBuffer sBuffer = new StringBuffer();
				String sendData = sBuffer.append("?key=").append(env.getProperty("jh.phone.feekey")).append("&phoneno=")
						.append(chargeDTO.getPhone()).append("&cardnum=").append(chargeDTO.getPid()).append("&orderid=")
						.append(orderId).append("&sign=").append(sign).toString();
				result = HttpUtils.get(hfUrl + sendData);// 改成调用HttpClientUtil工具类方法

				// 流量充值
			} else {

				// md5,校验值，md5(OpenID+key+phone+pid+orderid)，结果转为小写
				String sign = Md5Util.MD5(env.getProperty("jh.phone.OpenId") + env.getProperty("jh.phone.flowkey")
						+ chargeDTO.getPhone() + chargeDTO.getPid() + orderId).toLowerCase();

				StringBuffer sb = new StringBuffer();
				String sendData = sb.append("?key=").append(env.getProperty("jh.phone.flowkey")).append("&phone=")
						.append(chargeDTO.getPhone()).append("&pid=").append(chargeDTO.getPid()).append("&orderid=")
						.append(orderId).append("&sign=").append(sign).toString();
				result = HttpUtils.get(flUrl + sendData);// 改成调用HttpClientUtil工具类方法
			}

			JuheDTO juhe = JSONObject.parseObject(result, JuheDTO.class);// 解析返回

			logger.info(payName + "result:" + juhe);
			return juhe;
		} catch (Exception e) {
			logger.error(payName + "异常:", e);
		}

		return null;

	}

	/**
	 * 支付宝支付下单
	 * 
	 * @param alipayRequestParams
	 * @return
	 */
	public ResultDTO<ChargeResultDTO> aliPayRecharge(ChargeDTO chargeDTO) {

		ChargeResultDTO chargeResultDTO = new ChargeResultDTO();

		AlipayAppPayRequestParams requestParams = new AlipayAppPayRequestParams();
		String orderNo = CodeUtil.generateOrderCode("ALI");

		// 数据存入订单表中
		TradeWithdrawDO tradeWithdrawDO = new TradeWithdrawDO();
		tradeWithdrawDO.setAppUserId(chargeDTO.getUserId());// 设置用户ID
		tradeWithdrawDO.setOrderNo(orderNo);// 设置商户订单号

		tradeWithdrawDO.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());// 应答码；1000-处理中
		tradeWithdrawDO.setTradeType(TradeConstants.TradeTypeEnum.INCOME.getCode());// 交易类型；收入、支出
		tradeWithdrawDO.setStatus(WithdrawStateEnum.INIT.getCode());// 状态0-未执行。支付宝支付时，手机充值并没进行
		tradeWithdrawDO.setFee(BigDecimal.ZERO);// 设置手续费0
		tradeWithdrawDO.setChannelType(TradeConstants.ChannelTypeEnum.JHF_PAY.getCode());// 渠道
		tradeWithdrawDO.setTradeSubType(TradeConstants.TxnSubTypeEnum.INCOME_RESEARCH.getCode());
		tradeWithdrawDO.setOriginalOrderNo(chargeDTO.getPid());// 暂存套餐ID

		// 数据存入手机充值表
		RechargeOrderDO reChargeOrderDO = new RechargeOrderDO();
		reChargeOrderDO.setType(String.valueOf(chargeDTO.getType()));// 设置充值类型0-话费；1-流量
		reChargeOrderDO.setAppUserId(String.valueOf(chargeDTO.getUserId()));// 设置app用户ID
		reChargeOrderDO.setOrderNo(orderNo);// 设置商户订单ID
		reChargeOrderDO.setMobile(chargeDTO.getPhone());// 设置充值手机号
		reChargeOrderDO.setName(chargeDTO.getName());// 设置充值名称:xx元
		reChargeOrderDO.setStatus(ThrRechargeStateEnum.INGOING.getCode());// 状态设置为3，待支付

		reChargeOrderDO.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());// 应答码:正在处理中

		if (0 == chargeDTO.getType()) {// 话费充值

			// 先通过pid查询实际售价
			String amt = queryCharge(chargeDTO.getPhone(), Integer.parseInt(chargeDTO.getPid()));

			logger.info("话费充值-pid金额校验:amt=[" + amt + "],inprice=[" + chargeDTO.getInprice() + "]");
			if (!amt.equals(chargeDTO.getInprice())) {

				logger.error("套餐售价金额与app端传入参不一致，交易失败");
				tradeWithdrawDO.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());
				tradeWithdrawDO.setRespMsg("套餐售价金额与app端传入参不一致，交易失败");
				tradeWithdrawDO.setStatus(WithdrawStateEnum.FAIL.getCode());

				reChargeOrderDO.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());
				reChargeOrderDO.setRespMsg("套餐售价金额与app端传入参不一致，交易失败");
				reChargeOrderDO.setStatus(ThrRechargeStateEnum.FAIL.getCode());

				tradeWithdrawService.doAdd(tradeWithdrawDO);// 新增
				rechargeOrderService.doAdd(reChargeOrderDO);

				return ResultDTO.fail("套餐售价金额与app端传入参不一致，交易失败");
			}

			// 售价金额正常
			BigDecimal inprice = new BigDecimal(amt).multiply(new BigDecimal(100)).setScale(0,
					BigDecimal.ROUND_HALF_UP);
			tradeWithdrawDO.setAmount(inprice);// 设置交易金额
			reChargeOrderDO.setAmt(tradeWithdrawDO.getAmount().toString());// 设置交易金额
			tradeWithdrawDO.setTradeSubType(TradeConstants.TxnSubTypeEnum.BUY_HF.getCode());// 交易子类型:22话费充值
			requestParams.setSubject("话费充值订单");// 商品的标题/交易标题/订单标题/订单关键字等。示例值:大乐透
			requestParams.setBody(chargeDTO.getPid());// 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。示例值:Iphone6，传pid
		} else {

			// 先通过pid查询实际售价
			String amt = queryFlowExpen(chargeDTO.getPhone(), chargeDTO.getPid());

			if (!amt.equals(chargeDTO.getInprice())) {

				logger.error("套餐售价金额与app端传入参不一致，交易失败");
				tradeWithdrawDO.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());
				tradeWithdrawDO.setRespMsg("套餐售价金额与app端传入参不一致，交易失败");

				reChargeOrderDO.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());
				reChargeOrderDO.setRespMsg("套餐售价金额与app端传入参不一致，交易失败");

				tradeWithdrawService.doAdd(tradeWithdrawDO);// 新增
				rechargeOrderService.doAdd(reChargeOrderDO);

				return ResultDTO.fail("套餐售价金额与app端传入参不一致，交易失败");
			}

			// 售价金额正常
			BigDecimal inprice = new BigDecimal(amt).multiply(new BigDecimal(100)).setScale(0,
					BigDecimal.ROUND_HALF_UP);
			tradeWithdrawDO.setAmount(inprice);// 设置交易金额
			reChargeOrderDO.setAmt(tradeWithdrawDO.getAmount().toString());// 设置交易金额
			tradeWithdrawDO.setTradeSubType(TradeConstants.TxnSubTypeEnum.BUY_LT.getCode());// 交易子类型:23流量充值
			requestParams.setSubject("流量充值订单");// 商品的标题/交易标题/订单标题/订单关键字等。示例值:大乐透
			requestParams.setBody(chargeDTO.getPid());// 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。示例值:Iphone6
		}

		tradeWithdrawService.doAdd(tradeWithdrawDO);// 新增
		rechargeOrderService.doAdd(reChargeOrderDO);

		requestParams.setOutTradeNo(orderNo);// 商户订单号
		requestParams.setTotalAmount(chargeDTO.getInprice());// 付款价格
		requestParams.setNotifyUrl(env.getProperty("app.base.url") + "/trade/alipay/rechargePayNotify");// 回调地址,env.getProperty("app.base.url")
		logger.info("NotifyUrl:" + requestParams.getNotifyUrl());
		String body = AlipayClientUtil.createPayOrderParams(requestParams);

		chargeResultDTO.setBody(body);
		chargeResultDTO.setOrderNo(orderNo);
		logger.info("alipay->body:" + body);
		return ResultDTO.success(chargeResultDTO);
	}

	/**
	 * 支付宝回调之后，继续处理充值
	 * 
	 * @param params
	 * @param isSuccess
	 * @param reChargeOrderDO
	 */
	public void doAlipayThrChangeNotify(Map<String, String> params, Boolean isSuccess,
			RechargeOrderDO reChargeOrderDO) {

		ChargeDTO chargeDTO = new ChargeDTO();
		String payName = null;
		String orderNo = params.get("out_trade_no");
		// String status = params.get("trade_status");// 交易状态

		// 订单表查找订单号交易
		TradeWithdrawDO tradeWithdrawDO = tradeWithdrawService.getByOrderNo(orderNo);// 通过订单号查找原交易
		// 订单号流水表为空,则需要将recharge表数据复制给withdraw，然后插表
		if (null == tradeWithdrawDO) {

			logger.error("支付宝回调通知函数，订单号在表中找不到原交易，orderNo=[" + orderNo + "]");
			tradeWithdrawDO = new TradeWithdrawDO();
			tradeWithdrawDO.setOrderNo(orderNo);// 设置订单号
			tradeWithdrawDO.setAmount(new BigDecimal(reChargeOrderDO.getAmt()));// 设置交易金额，优惠金额
			tradeWithdrawDO.setAppUserId(chargeDTO.getUserId());// 设置帐号ID
			tradeWithdrawDO.setTradeType(2);// 交易类型:2-消费
			if ("0" == reChargeOrderDO.getType()) {
				tradeWithdrawDO.setTradeSubType(TradeConstants.TxnSubTypeEnum.BUY_HF.getCode());// 交易子类型:22话费充值
				payName = "话费充值";
			} else {
				tradeWithdrawDO.setTradeSubType(TradeConstants.TxnSubTypeEnum.BUY_LT.getCode());// 交易子类型:23流量充值
				payName = "流量充值";
			}
			tradeWithdrawDO.setStatus(WithdrawStateEnum.PROCESSING.getCode());// 设置交易状态，1-执行中
			tradeWithdrawService.doAdd(tradeWithdrawDO);// 更新数据库表
		}

		if (!isSuccess) {

			logger.error(payName + "-支付宝扣款交易失败");

			RechargeOrderDO phoneChargeOrderDO1 = new RechargeOrderDO();
			phoneChargeOrderDO1.setId(reChargeOrderDO.getId());// 设置ID
			phoneChargeOrderDO1.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());// 设置应答码
			phoneChargeOrderDO1.setRespMsg(payName + "-支付宝扣款交易失败");// 设置应答信息
			phoneChargeOrderDO1.setStatus(ThrRechargeStateEnum.FAIL.getCode());// 设置状态码
			rechargeOrderService.doUpdate(phoneChargeOrderDO1);

			TradeWithdrawDO tradeWithdraw1 = new TradeWithdrawDO();
			tradeWithdraw1.setId(tradeWithdrawDO.getId());
			tradeWithdraw1.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());// 设置应答码
			tradeWithdraw1.setRespMsg(payName + "-支付宝扣款交易失败");// 设置应答信息
			tradeWithdraw1.setStatus(WithdrawStateEnum.FAIL.getCode());// 设置状态码
			tradeWithdrawService.doUpdate(tradeWithdraw1);

		} else {

			// 判断pid是否为空
			if (Strings.isNullOrEmpty(tradeWithdrawDO.getOriginalOrderNo())) {

				logger.error("充值pid为空,orderNo=" + orderNo);
				// 充值失败,退款
				AlipayRefundRequestParams requestParams = new AlipayRefundRequestParams();
				requestParams.setOutTradeNo(orderNo);// 设置商户订单号
				requestParams.setRefundAmount(params.get("receipt_amount"));// 设置退款金额为实收金额
				AlipayTradeRefundResponse alipayTradeRefundResponse = AlipayClientUtil
						.createTradeReturnOrderParams(requestParams);

				// 退款成功且金额发生变动
				if ("Y".equals(alipayTradeRefundResponse.getFundChange()) && alipayTradeRefundResponse.isSuccess()) {

					logger.error(payName + "退款成功,orderno=[" + orderNo + "]");

					// 调用失败
				} else {

					logger.error(payName + "退款失败,orderno=[" + orderNo + "]");
				}

				RechargeOrderDO phoneChargeOrderDO1 = new RechargeOrderDO();
				phoneChargeOrderDO1.setId(reChargeOrderDO.getId());// 设置ID
				phoneChargeOrderDO1.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());// 设置应答码
				phoneChargeOrderDO1.setRespMsg(payName + "-充值pid为空");// 设置应答信息
				phoneChargeOrderDO1.setStatus(ThrRechargeStateEnum.FAIL.getCode());// 设置状态码
				rechargeOrderService.doUpdate(phoneChargeOrderDO1);

				TradeWithdrawDO tradeWithdraw1 = new TradeWithdrawDO();
				tradeWithdraw1.setId(tradeWithdrawDO.getId());
				tradeWithdraw1.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());// 设置应答码
				tradeWithdraw1.setRespMsg(payName + "-充值pid为空");// 设置应答信息
				tradeWithdraw1.setStatus(WithdrawStateEnum.FAIL.getCode());// 设置状态码
				tradeWithdrawService.doUpdate(tradeWithdraw1);

				return;
			}

			Date gmtPayment = DateUtils.toParseYmdhms(params.get("gmt_payment"));
			Date notifyTime = DateUtils.toParseYmdhms(params.get("notify_time"));

			// 回调时间大于付款时间15分钟(time想减是毫秒数，除以1000变为秒，再除以60变为分)
			if (15 < ((notifyTime.getTime() - gmtPayment.getTime()) / (1000 * 60))) {

				logger.error(payName + "-支付宝扣款回调超时," + "支付时间=[" + params.get("gmt_payment") + "],回调时间=["
						+ params.get("notify_time") + "],准备扣款");

				// 回调超时,退款
				AlipayRefundRequestParams requestParams = new AlipayRefundRequestParams();
				requestParams.setOutTradeNo(orderNo);// 设置商户订单号
				requestParams.setRefundAmount(params.get("receipt_amount"));// 设置退款金额为实收金额
				AlipayTradeRefundResponse alipayTradeRefundResponse = AlipayClientUtil
						.createTradeReturnOrderParams(requestParams);

				logger.info("支付宝退款返回：" + JSON.toJSONString(alipayTradeRefundResponse));

				// 调用成功
				if ("Y".equals(alipayTradeRefundResponse.getFundChange()) && alipayTradeRefundResponse.isSuccess()) {

					logger.error(payName + "退款成功,orderno=[" + orderNo + "]");

					// 调用失败
				} else {

					logger.error(payName + "退款失败,orderno=[" + orderNo + "]");
				}

				TradeWithdrawDO tradeWithdraw1 = new TradeWithdrawDO();
				tradeWithdraw1.setId(tradeWithdrawDO.getId());
				tradeWithdraw1.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());// 设置应答码
				tradeWithdraw1.setRespMsg(payName + "-支付宝扣款回调超时");// 设置应答信息
				tradeWithdraw1.setStatus(WithdrawStateEnum.FAIL.getCode());// 设置状态码
				tradeWithdrawService.doUpdate(tradeWithdraw1);

				RechargeOrderDO phoneChargeOrderDO1 = new RechargeOrderDO();
				phoneChargeOrderDO1.setId(reChargeOrderDO.getId());// 设置ID
				phoneChargeOrderDO1.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());// 设置应答码
				phoneChargeOrderDO1.setRespMsg(payName + "-支付宝扣款回调超时");// 设置应答信息
				phoneChargeOrderDO1.setStatus(ThrRechargeStateEnum.FAIL.getCode());// 设置状态码
				rechargeOrderService.doUpdate(phoneChargeOrderDO1);
				return;
			}

			logger.info("手机充值-支付宝回调，支付成功，继续进行" + payName);

			// 手机充值
			chargeDTO.setType(Integer.parseInt(reChargeOrderDO.getType()));// 设置类型
			chargeDTO.setPid(tradeWithdrawDO.getOriginalOrderNo());// 设置套餐ID
			chargeDTO.setPhone(reChargeOrderDO.getMobile());// 充值手机号

			RechargeOrderDO phoneChargeOrderDO1 = new RechargeOrderDO();
			TradeWithdrawDO tradeWithdrawDO1 = new TradeWithdrawDO();
			phoneChargeOrderDO1.setId(reChargeOrderDO.getId());
			tradeWithdrawDO1.setId(tradeWithdrawDO.getId());

			// 充值交易调用
			JuheDTO juhe = recharge(chargeDTO, orderNo);
			if (juhe.getError_code() == 0) {

				Map<String, Object> map = JSONObject.parseObject(juhe.getResult().toString(), Map.class);

				phoneChargeOrderDO1.setPayOrderNo(map.get("sporder_id").toString());// 设置聚合订单号
				phoneChargeOrderDO1.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());// 订单提交成功，等待充值
				phoneChargeOrderDO1.setRespMsg(juhe.getReason());// 设置响应
				phoneChargeOrderDO1.setStatus(ThrRechargeStateEnum.PROCESSING.getCode());// 设置状态，0-进行中
				tradeWithdrawDO1.setOriginalOrderNo(map.get("sporder_id").toString());// 设置渠道订单号
				tradeWithdrawDO1.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());// 订单提交成功，等待充值需要再次调用订单查询接口进行查询
				tradeWithdrawDO1.setUpdateTime(new Date());// 设置交易完成时间
				tradeWithdrawDO1.setRespMsg(juhe.getReason());// 设置响应

				// 系统内部异常(调用充值类业务时，请务必联系客服或通过订单查询接口检测订单，避免造成损失)
			} else if (juhe.getError_code() == 10014) {

				phoneChargeOrderDO1.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());// 交易进行中
				phoneChargeOrderDO1.setRespMsg(juhe.getReason());// 设置响应
				phoneChargeOrderDO1.setStatus(ThrRechargeStateEnum.PROCESSING.getCode());// 订单提交成功，等待充值

				tradeWithdrawDO1.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());// 交易进行中，需要再次调用订单查询接口进行查询
				tradeWithdrawDO1.setRespMsg(juhe.getReason());// 设置响应
				tradeWithdrawDO1.setUpdateTime(new Date());// 设置最后更新时间

				// 充值失败
			} else {

				phoneChargeOrderDO1.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());// 交易失败
				phoneChargeOrderDO1.setRespMsg(juhe.getReason());// 设置响应
				phoneChargeOrderDO1.setStatus(WithdrawStateEnum.FAIL.getCode());// 状态为2-失败

				tradeWithdrawDO1.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());// 交易进行中，需要再次调用订单查询接口进行查询
				tradeWithdrawDO1.setStatus(WithdrawStateEnum.FAIL.getCode());// 状态为2-失败
				tradeWithdrawDO1.setRespMsg(juhe.getReason());// 设置响应
				tradeWithdrawDO1.setUpdateTime(new Date());// 设置最后更新时间

				// 充值失败,退款
				AlipayRefundRequestParams requestParams = new AlipayRefundRequestParams();
				requestParams.setOutTradeNo(orderNo);// 设置商户订单号
				requestParams.setRefundAmount(params.get("receipt_amount"));// 设置退款金额为实收金额
				AlipayTradeRefundResponse alipayTradeRefundResponse = AlipayClientUtil
						.createTradeReturnOrderParams(requestParams);

				// 退款成功且金额发生变动
				if ("Y".equals(alipayTradeRefundResponse.getFundChange()) && alipayTradeRefundResponse.isSuccess()) {

					logger.error(payName + "退款成功,orderno=[" + orderNo + "]");

					// 调用失败
				} else {

					logger.error(payName + "退款失败,orderno=[" + orderNo + "]");
				}
			}

			// 更新数据
			tradeWithdrawService.doUpdate(tradeWithdrawDO1);
			rechargeOrderService.doUpdate(phoneChargeOrderDO1);
		}
	}
}
