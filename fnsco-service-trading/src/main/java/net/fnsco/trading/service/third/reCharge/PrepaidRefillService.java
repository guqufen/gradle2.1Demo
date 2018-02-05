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

import net.fnsco.bigdata.comm.ServiceConstant.TradeStateEnum;
import net.fnsco.core.alipay.AlipayAppPayRequestParams;
import net.fnsco.core.alipay.AlipayClientUtil;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.CodeUtil;
import net.fnsco.core.utils.HttpUtils;
import net.fnsco.freamwork.comm.Md5Util;
import net.fnsco.trading.comm.TradeConstants;
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
				phChargePackageDTO.setCompany(map.get("company").toString());
				phChargePackageDTO.setType(map.get("type").toString());
				List<Map<String, String>> lists = JSONObject.parseObject(map.get("flows").toString(), List.class);
				for (Map<String, String> map2 : lists) {
					map2.get("");

					// 通过pid查找实际售价金额
					if (map.get("pid").equals(pid.trim())) {

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
	 * 帐户余额-话费/流量流量充值
	 * 
	 * @param phone:流量充值手机号
	 * @param pid:流量充值套餐ID
	 * @param innerCode:内部商户号，便于插表数据
	 * @return 请求示例：http://v.juhe.cn/flow/recharge?key=您申请的APPKEY&phone=
	 *         18913513535&pid=8&orderid=a1122111d&sign=
	 *         721a3f667b0eb63f54517971181e7392
	 */
	public ResultDTO<ChargeResultDTO> flowCharge(ChargeDTO chargeDTO) {

		String orderid = CodeUtil.generateOrderCode("");
		return ResultDTO.success(acctRecharge(chargeDTO));
	}

	/**
	 * 话费充值
	 * 
	 * @param phone:手机号
	 * @param pid:套餐id(金额)
	 * @return
	 */
	public ResultDTO<ChargeResultDTO> prepaidRefillCharge(ChargeDTO chargeDTO) {

		String result = null;
		String onlineUrl = "http://op.juhe.cn/ofpay/mobile/onlineorder";

		ChargeResultDTO ph = new ChargeResultDTO();
		String orderid = CodeUtil.generateOrderCode("");

		// md5,校验值，md5(OpenID+key+phone+pid+orderid)，结果转为小写
		String sign = Md5Util.MD5(env.getProperty("jh.phone.OpenId") + env.getProperty("jh.phone.feekey")
				+ chargeDTO.getPhone() + chargeDTO.getPid() + orderid);

		RechargeOrderDO phoneChargeOrderDO = new RechargeOrderDO();
		phoneChargeOrderDO.setType(String.valueOf(chargeDTO.getType()));// 设置充值类型
		phoneChargeOrderDO.setAppUserId(String.valueOf(chargeDTO.getUserId()));// 设置app用户ID
		phoneChargeOrderDO.setOrderNo(orderid);// 设置商户订单ID
		phoneChargeOrderDO.setMobile(chargeDTO.getPhone());// 设置充值手机号
		phoneChargeOrderDO.setName(chargeDTO.getName());// 设置充值名称:xx元
		phoneChargeOrderDO.setAmt(chargeDTO.getInprice().replace(".", ""));// 设置交易金额
		phoneChargeOrderDO.setStatus(WithdrawStateEnum.INIT.getCode());// 设置交易状态0-进行中
		rechargeOrderService.doAdd(phoneChargeOrderDO);

		TradeWithdrawDO tradeWithdrawDO = new TradeWithdrawDO();
		tradeWithdrawDO.setOrderNo(orderid);// 设置订单号
		tradeWithdrawDO.setOriginalOrderNo(orderid);// 设置原订单号(默认等于当前订单号)
		tradeWithdrawDO.setAmount(new BigDecimal(phoneChargeOrderDO.getAmt()));// 设置交易金额，优惠金额
		tradeWithdrawDO.setAppUserId(chargeDTO.getUserId());// 设置帐号ID
		tradeWithdrawDO.setTradeType(2);// 交易类型:2-消费
		tradeWithdrawDO.setTradeSubType(22);// 交易子类型:22话费充值
		tradeWithdrawDO.setStatus(WithdrawStateEnum.PROCESSING.getCode());// 设置交易状态，1-执行中
		tradeWithdrawService.doAdd(tradeWithdrawDO);// 更新数据库表

		TradeWithdrawDO tradeWithdrawDO1 = new TradeWithdrawDO();
		tradeWithdrawDO1.setId(tradeWithdrawDO.getId());
		RechargeOrderDO phoneChargeOrderDO1 = new RechargeOrderDO();
		phoneChargeOrderDO1.setId(phoneChargeOrderDO.getId());
		phoneChargeOrderDO1.setOrderNo(phoneChargeOrderDO.getOrderNo());

		// 更新余额和对应的冻结金额(账户扣款)
		BigDecimal inprice = new BigDecimal(chargeDTO.getInprice()).multiply(new BigDecimal(100)).setScale(0,
				BigDecimal.ROUND_HALF_UP);
		Boolean flag = appAccountBalanceService.doFrozenBalance(chargeDTO.getUserId(), inprice);
		// 更新余额失败，则更新流水表数据为交易失败
		if (!flag) {

			logger.error("话费充值-帐户余额冻结失败！！appUserId=" + chargeDTO.getUserId());
			tradeWithdrawDO1.setRespCode(TradeStateEnum.FAIL.getCode());// 交易进行中，需要再次调用订单查询接口进行查询
			tradeWithdrawDO1.setStatus(WithdrawStateEnum.FAIL.getCode());// 状态为2-失败
			tradeWithdrawDO1.setRespMsg("帐户余额冻结失败,帐户余额不足.");// 设置响应
			tradeWithdrawDO1.setUpdateTime(new Date());// 设置最后更新时间
			Integer ret2 = tradeWithdrawService.doUpdate(tradeWithdrawDO1);// 更新数据
			if (ret2 < 0) {
				logger.error(
						"话费充值失败，数据更新失败。time=" + tradeWithdrawDO1.getUpdateTime() + "userId=" + chargeDTO.getUserId());
			}
			phoneChargeOrderDO1.setRespCode(TradeStateEnum.FAIL.getCode());// 账户冻结失败，更新数据
			phoneChargeOrderDO1.setRespMsg("帐户余额冻结失败,帐户余额不足.");// 设置响应
			int ret = rechargeOrderService.doUpdate(phoneChargeOrderDO1);
			if (ret < 0) {
				logger.error("话费充值成功，数据更新失败。orderNo=" + phoneChargeOrderDO1.getOrderNo() + "userId="
						+ chargeDTO.getUserId());
			}
			return ResultDTO.fail("帐户余额不足");
		}

		try {
			StringBuffer sBuffer = new StringBuffer();
			String sendDate = sBuffer.append("?key=").append(env.getProperty("jh.phone.feekey")).append("&phoneno=")
					.append(chargeDTO.getPhone()).append("&cardnum=").append(chargeDTO.getPid()).append("&orderid=")
					.append(orderid).append("&sign=").append(sign).toString();

			result = HttpUtils.get(onlineUrl + sendDate);// 改成调用HttpClientUtil工具类方法
			// result = RechargeUtil.get(onlineUrl.replace("*",

			JuheDTO juhe = JSONObject.parseObject(result, JuheDTO.class);

			logger.info("result:" + juhe);
			if (juhe.getError_code() == 0) {

				Map<String, Object> map = JSONObject.parseObject(juhe.getResult().toString(), Map.class);

				phoneChargeOrderDO1.setPayOrderNo(map.get("sporder_id").toString());// 设置渠道订单号
				// 实际消费金额，先取两位小数再乘以100，再去掉小数位，再变成String
				BigDecimal realInprice = new BigDecimal(map.get("ordercash").toString())
						.setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100))
						.setScale(0, BigDecimal.ROUND_HALF_UP);
				String amtStr = realInprice.toString();
				phoneChargeOrderDO1.setAmt(amtStr);// 设置实际消费金额
				phoneChargeOrderDO1.setRespCode("1000");// 订单提交成功，等待充值
				phoneChargeOrderDO1.setRespMsg(juhe.getReason());// 设置响应
				int ret = rechargeOrderService.doUpdate(phoneChargeOrderDO1);
				if (ret < 0) {
					logger.error("话费充值返回成功，数据更新失败。orderNo=" + phoneChargeOrderDO1.getOrderNo() + "userId="
							+ chargeDTO.getUserId());
				}

				tradeWithdrawDO1.setOriginalOrderNo(map.get("sporder_id").toString());// 设置渠道订单号
				tradeWithdrawDO1.setAmount(new BigDecimal(phoneChargeOrderDO1.getAmt()));// 设置实际消费金额
				// 交易成功
				tradeWithdrawDO1.setRespCode("1000");// 订单提交成功，等待充值需要再次调用订单查询接口进行查询
				tradeWithdrawDO1.setUpdateTime(new Date());// 设置交易完成时间
				tradeWithdrawDO1.setRespMsg(juhe.getReason());// 设置响应
				Integer ret2 = tradeWithdrawService.doUpdate(tradeWithdrawDO);// 更新数据
				if (ret2 < 0) {
					logger.error("话费充值返回成功，数据更新失败。time=" + tradeWithdrawDO1.getUpdateTime() + "userId="
							+ chargeDTO.getUserId());
				}

				ph.setRespCode("1000");
				ph.setRespMsg(juhe.getReason());
				ph.setOrderNo(orderid);

				// 如果扣掉的金额和返回的实际金额不一致，需要将不一致的金额扣掉/加上
				if (0 != inprice.compareTo(realInprice)) {

					BigDecimal diff = realInprice.subtract(inprice);
					logger.info("话费充值-交易金额和实际扣款金额有差别:inprice=[" + inprice + "],实际扣款realInprice=[" + realInprice
							+ "],差值=[" + diff + "]");

					flag = appAccountBalanceService.doFrozenBalance(chargeDTO.getUserId(), diff);
					if (!flag) {
						logger.error("话费充值-交易金额和实际扣款金额不一致扣款还原出错！！appUserId=" + chargeDTO.getUserId());
						ph.setRespMsg("交易部分成功");
					}
				}

			} else if (juhe.getError_code() == 10014) {// 系统内部异常(调用充值类业务时，请务必联系客服或通过订单查询接口检测订单，避免造成损失)

				phoneChargeOrderDO1.setRespCode("1000");// 交易进行失败
				phoneChargeOrderDO1.setRespMsg(juhe.getReason());// 设置响应
				int ret = rechargeOrderService.doUpdate(phoneChargeOrderDO1);
				if (ret < 0) {
					logger.error("话费充值交易正在处理中，数据更新失败。orderNo=" + phoneChargeOrderDO1.getOrderNo() + "userId="
							+ chargeDTO.getUserId());
				}

				tradeWithdrawDO1.setRespCode("1000");// 交易进行中，需要再次调用订单查询接口进行查询
				tradeWithdrawDO1.setRespMsg(juhe.getReason());// 设置响应
				tradeWithdrawDO1.setUpdateTime(new Date());// 设置最后更新时间
				Integer ret2 = tradeWithdrawService.doUpdate(tradeWithdrawDO1);// 更新数据
				if (ret2 < 0) {
					logger.error("话费充值交易正在处理中，数据更新失败。time=" + tradeWithdrawDO1.getUpdateTime() + "userId="
							+ chargeDTO.getUserId());
				}

				ph.setRespCode("1000");
				ph.setRespMsg("交易正在处理中，请稍后查询");
			} else {

				phoneChargeOrderDO1.setRespCode(TradeStateEnum.FAIL.getCode());// 交易失败
				phoneChargeOrderDO1.setRespMsg(juhe.getReason());// 设置响应
				phoneChargeOrderDO1.setStatus(2);// 状态为2-失败
				int ret = rechargeOrderService.doUpdate(phoneChargeOrderDO1);
				if (ret < 0) {
					logger.error("话费充值返回失败，phoneChargeOrder数据更新失败。orderNo=" + phoneChargeOrderDO1.getOrderNo()
							+ "userId=" + chargeDTO.getUserId());
				}

				tradeWithdrawDO1.setRespCode(TradeStateEnum.FAIL.getCode());// 交易进行中，需要再次调用订单查询接口进行查询
				tradeWithdrawDO1.setStatus(2);// 状态为2-失败
				tradeWithdrawDO1.setRespMsg(juhe.getReason());// 设置响应
				tradeWithdrawDO1.setUpdateTime(new Date());// 设置最后更新时间
				Integer ret2 = tradeWithdrawService.doUpdate(tradeWithdrawDO1);// 更新数据
				if (ret2 < 0) {
					logger.error("话费充值失败，tradeWithdraw数据更新失败。orderNo=" + phoneChargeOrderDO1.getOrderNo() + "userId="
							+ chargeDTO.getUserId());
				}

				// 设置返回应答数据
				ph.setRespCode("1002");
				ph.setRespMsg(juhe.getReason());

				// 还原APP账户余额
				flag = appAccountBalanceService.doFrozenBalance(chargeDTO.getUserId(),
						new BigDecimal(0).subtract(inprice));
				if (!flag) {
					logger.error("手机充值-充值失败之后，账户余额还原失败，请联系相关技术人员查看,appUserId=[" + chargeDTO.getUserId() + "],orderNo="
							+ ph.getOrderNo() + "inprice=[" + chargeDTO.getInprice() + "]");
					ph.setRespMsg("系统异常，请联系技术人员查看");
				}
			}
		} catch (Exception e) {
			logger.error("话费充值异常:", e);
		}

		return ResultDTO.success(ph);
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
			ph.setRespCode("1002");
			ph.setRespMsg("该订单号未查到相关数据数据为空");
			return ResultDTO.success(ph);
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
			ph.setRespCode("1002");
			ph.setRespMsg("该订单号未查到相关数据数据为空");
			return ResultDTO.success(ph);
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

		String orderid = CodeUtil.generateOrderCode("");
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
		// phoneChargeOrderDO.setStatus(WithdrawStateEnum.INIT.getCode());//设置交易状态0-进行中，不能设置状态，避免被定时任务跑
		rechargeOrderService.doAdd(phoneChargeOrderDO);

		tradeWithdrawDO.setOrderNo(orderid);// 设置订单号
		tradeWithdrawDO.setOriginalOrderNo(orderid);// 设置原订单号(默认等于当前订单号)
		tradeWithdrawDO.setAmount(new BigDecimal(phoneChargeOrderDO.getAmt()));// 设置交易金额，优惠金额
		tradeWithdrawDO.setAppUserId(chargeDTO.getUserId());// 设置帐号ID
		tradeWithdrawDO.setTradeType(2);// 交易类型:2-消费
		if (0 == chargeDTO.getType()) {

			// 先通过pid查询实际售价
			String amt = queryCharge(chargeDTO.getPhone(), Integer.parseInt(chargeDTO.getPid()));

			logger.info("话费充值-pid金额校验:amt=[" + amt + "],inprice=[" + chargeDTO.getInprice() + "]");
			if (!amt.equals(chargeDTO.getInprice())) {

				logger.error("套餐售价金额与app端传入参不一致，交易失败");
				tradeWithdrawDO.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());
				tradeWithdrawDO.setRespMsg("套餐售价金额与app端传入参不一致，交易失败");
				tradeWithdrawDO.setStatus(WithdrawStateEnum.FAIL.getCode());

				phoneChargeOrderDO.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());
				phoneChargeOrderDO.setRespMsg("套餐售价金额与app端传入参不一致，交易失败");
				phoneChargeOrderDO.setStatus(WithdrawStateEnum.FAIL.getCode());

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

			// 先通过pid查询实际售价
			String amt = queryFlowExpen(chargeDTO.getPhone(), chargeDTO.getPid());

			if (!amt.equals(chargeDTO.getInprice())) {
				logger.error("套餐售价金额与app端传入参不一致，交易失败");

				logger.error("套餐售价金额与app端传入参不一致，交易失败");
				tradeWithdrawDO.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());
				tradeWithdrawDO.setRespMsg("套餐售价金额与app端传入参不一致，交易失败");

				phoneChargeOrderDO.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());
				phoneChargeOrderDO.setRespMsg("套餐售价金额与app端传入参不一致，交易失败");

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
			// tradeWithdrawDO.setTradeSubType(TradeConstants.TxnSubTypeEnum.BUY_LT.getCode());//
			// 交易子类型:23流量充值
			payName = "流量充值";
		}
		tradeWithdrawDO.setStatus(WithdrawStateEnum.PROCESSING.getCode());// 设置交易状态，1-执行中
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
			int ret = rechargeOrderService.doUpdate(phoneChargeOrderDO1);
			if (ret < 0) {
				logger.error(payName + "帐户余额不足，phoneChargeOrder数据更新失败。orderNo=" + orderid + "userId="
						+ chargeDTO.getUserId());
			}
			ph.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());
			ph.setRespMsg("帐户余额不足");
			return ResultDTO.success(ph);
		}

		// 失败，则
		if (TradeConstants.RespCodeEnum.FAIL.getCode().equals(ph.getRespCode())) {
			// 还原APP账户余额
			flag = appAccountBalanceService.doFrozenBalance(chargeDTO.getUserId(), new BigDecimal(0).subtract(inprice));
			if (!flag) {
				logger.error(payName + "-充值失败之后，账户余额还原失败，请联系相关技术人员查看,appUserId=[" + chargeDTO.getUserId() + "],orderNo="
						+ ph.getOrderNo() + "inprice=[" + chargeDTO.getInprice() + "]");
				ph.setRespMsg("系统异常，请联系技术人员查看");
			}
		}

		// 获取充值结果
		JuheDTO juhe = recharge(chargeDTO, orderid);
		if (juhe.getError_code() == 0) {

			Map<String, Object> map = JSONObject.parseObject(juhe.getResult().toString(), Map.class);

			phoneChargeOrderDO1.setPayOrderNo(map.get("sporder_id").toString());// 设置聚合订单号

			phoneChargeOrderDO1.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());// 订单提交成功，等待充值
			phoneChargeOrderDO1.setRespMsg(juhe.getReason());// 设置响应

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
			phoneChargeOrderDO1.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());// 订单提交成功，等待充值

			tradeWithdrawDO1.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());// 交易进行中，需要再次调用订单查询接口进行查询
			tradeWithdrawDO1.setRespMsg(juhe.getReason());// 设置响应
			tradeWithdrawDO1.setUpdateTime(new Date());// 设置最后更新时间

			ph.setRespCode(TradeConstants.RespCodeEnum.HANDLING.getCode());
			ph.setRespMsg("交易正在处理中，请稍后查询");
		} else {

			phoneChargeOrderDO1.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());// 交易失败
			phoneChargeOrderDO1.setRespMsg(juhe.getReason());// 设置响应
			phoneChargeOrderDO1.setStatus(WithdrawStateEnum.FAIL.getCode());// 状态为2-失败

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

				// // 先通过pid查询实际售价
				// String amt = queryCharge(chargeDTO.getPhone(),
				// Integer.parseInt(chargeDTO.getPid()));
				// if (amt == null) {
				// logger.error("无此套餐");
				// juhe1.setError_code(null);
				// juhe1.setReason("无此套餐");
				// return juhe1;
				// }
				//
				// if (!amt.equals(chargeDTO.getInprice())) {
				// logger.error("套餐售价金额与app端传入参不一致，交易失败");
				// juhe1.setError_code(null);
				// juhe1.setReason("套餐售价金额与app端传入参不一致，交易失败");
				// return juhe1;
				// }

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

				// 先通过pid查询实际售价
				// String amt = queryFlowExpen(chargeDTO.getPhone(),
				// chargeDTO.getPid());
				//
				// if (amt == null) {
				// logger.error("无此套餐");
				// juhe1.setError_code(null);
				// juhe1.setReason("无此套餐");
				// return juhe1;
				// }
				//
				// if (!amt.equals(chargeDTO.getInprice())) {
				// logger.error("套餐售价金额与app端传入参不一致，交易失败");
				// juhe1.setError_code(null);
				// juhe1.setReason("套餐售价金额与app端传入参不一致，交易失败");
				// return juhe1;
				// }

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
		String orderNo = CodeUtil.generateOrderCode("");

		// 数据存入订单表中
		TradeWithdrawDO tradeWithdrawDO = new TradeWithdrawDO();
		tradeWithdrawDO.setAppUserId(chargeDTO.getUserId());// 设置用户ID
		tradeWithdrawDO.setOrderNo(orderNo);// 设置商户订单号

		tradeWithdrawDO.setStatus(WithdrawStateEnum.PROCESSING.getCode());// 设置交易状态，1-执行中
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
				reChargeOrderDO.setStatus(WithdrawStateEnum.FAIL.getCode());

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
			requestParams.setBody(chargeDTO.getName());// 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。示例值:Iphone6
		} else {

			// 先通过pid查询实际售价
			String amt = queryFlowExpen(chargeDTO.getPhone(), chargeDTO.getPid());

			if (!amt.equals(chargeDTO.getInprice())) {
				logger.error("套餐售价金额与app端传入参不一致，交易失败");

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
			requestParams.setBody(chargeDTO.getName());// 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。示例值:Iphone6
		}

		tradeWithdrawService.doAdd(tradeWithdrawDO);// 新增
		rechargeOrderService.doAdd(reChargeOrderDO);

		requestParams.setOutTradeNo(orderNo);// 商户订单号
		requestParams.setTotalAmount(chargeDTO.getInprice());// 付款价格
		requestParams.setNotifyUrl("http://6a2205d0.ngrok.io" + "/admin/trade/alipay/rechargePayNotify");// 回调地址,env.getProperty("web.base.url")
		logger.info("NotifyUrl:" + requestParams.getNotifyUrl());
		String body = AlipayClientUtil.createPayOrderParams(requestParams);

		chargeResultDTO.setBody(body);
		chargeResultDTO.setOrderNo(orderNo);
		logger.info("alipay->body:" + body);
		return ResultDTO.success(chargeResultDTO);
	}
}
