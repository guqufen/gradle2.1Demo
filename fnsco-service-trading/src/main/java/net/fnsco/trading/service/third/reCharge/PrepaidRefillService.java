package net.fnsco.trading.service.third.reCharge;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.fnsco.bigdata.comm.ServiceConstant.TradeStateEnum;
import net.fnsco.bigdata.service.sys.SequenceService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.CodeUtil;
import net.fnsco.freamwork.comm.Md5Util;
import net.fnsco.trading.service.third.reCharge.dto.ChargeDTO;
import net.fnsco.trading.service.third.reCharge.dto.ChargeResultDTO;
import net.fnsco.trading.service.third.reCharge.dto.CheckChargePackageDTO;
import net.fnsco.trading.service.third.reCharge.dto.CheckMobileDTO;
import net.fnsco.trading.service.third.reCharge.dto.JuheDTO;
import net.fnsco.trading.service.third.reCharge.entity.RechargeOrderDO;
import net.fnsco.trading.service.third.reCharge.util.RechargeUtil;
import net.fnsco.trading.service.withdraw.TradeWithdrawErrorService;
import net.fnsco.trading.service.withdraw.TradeWithdrawService;
import net.fnsco.trading.service.withdraw.entity.TradeWithdrawDO;

@Service
public class PrepaidRefillService extends BaseService {

	@Autowired
	private TradeWithdrawService tradeWithdrawService;
	@Autowired
	private RechargeOrderService rechargeOrderService;

	private final String OpenId = "JH284e6d24f2e3f5d89668a64b50c2c886";

	// 配置流量充值
	private final String APPKEYFLOW = "6121e6e31f9e1eff037d8c416e0ee4fe";

	// 配置话费充值
	private final String APPKEYREPAID = "9b9450b3b217f8c0c7dcb2a28eafbbb7";
	private final String telQueryUrl = "http://op.juhe.cn/ofpay/mobile/telquery?cardnum=*&phoneno=!&key="
			+ APPKEYREPAID;
	private final String onlineUrl = "http://op.juhe.cn/ofpay/mobile/onlineorder?key=" + APPKEYREPAID
			+ "&phoneno=!&cardnum=*&orderid=@&sign=$";
	public final String orderstaUrl = "http://op.juhe.cn/ofpay/mobile/ordersta?key=" + APPKEYREPAID + "&orderid=!";

	/**
	 * 号码充值套餐优惠资费查询，面额：10，20，30，50，100，200,循环获取各个面额原价与优惠价格
	 * 
	 * @param phone
	 * @return
	 */
	public ResultDTO<CheckChargePackageDTO> prepaidRefillCheck(String phone) {

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
				result = RechargeUtil.get(telQueryUrl.replace("*", done + "").replace("!", phone), 0);
				logger.info(result);

				JuheDTO juhe = JSON.parseObject(result, JuheDTO.class);
				if (juhe.getError_code() == 0) {

					System.out.println(juhe.getResult());

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
				} else {
					logger.error("话费充值失败,原因:+" + juhe.getReason());
					return ResultDTO.fail(juhe.getReason());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		phChargePackageDTO.setList(list);// 设置套餐资费列表
		return ResultDTO.success(phChargePackageDTO);
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
		String sendData = sb.append("?phone=").append(phone).append("&key=").append(APPKEYFLOW).toString();

		try {
			/**
			 * 数据返回格式如下： { "reason": "success", "result": [ { "city": "全国",
			 * //支持城市 "company": "中国移动", //运营商 "companytype": "2", //运营商ID
			 * "name": "中国移动全国流量套餐", //套餐名称 "type": "1", //支持类型1：全国 2：城市
			 * "flows": [ //流量套餐列表 { "id": "3", //套餐ID "p": "10M", //套餐流量名称 "v":
			 * "10", //套餐流量值 "inprice": "2.90" //价格 } ] } ], "error_code": 0 }
			 */
			result = RechargeUtil.net(url, sendData, "GET");
			JuheDTO juhe = JSONObject.parseObject(result, JuheDTO.class);

			// 查询成功
			if (juhe.getError_code() == 0) {

				// 获取需要返回的数据域(套餐类型)
				JSONArray jsonArray = (JSONArray) juhe.getResult();
				String str = jsonArray.get(0).toString();
				/**
				 * 
				 */
				Map<String, Object> map = JSONObject.parseObject(str, Map.class);
				logger.info(map.toString());
				phChargePackageDTO.setCompany(map.get("company").toString());
				phChargePackageDTO.setType(map.get("type").toString());
				List<Map<String, String>> lists = JSONObject.parseObject(map.get("flows").toString(), List.class);
				for (Map<String, String> map2 : lists) {
					/**
					 * 返回的套餐资费list id string 套餐ID; p string 套餐流量名称 ; v string
					 * 套餐流量值 ; inprice string 价格;
					 */
					CheckMobileDTO phChargeDTO = new CheckMobileDTO();
					phChargeDTO.setId(map2.get("id"));
					phChargeDTO.setName(map2.get("p"));
					phChargeDTO.setInprice(map2.get("inprice"));
					list.add(phChargeDTO);
				}
				phChargePackageDTO.setList(list);

				return ResultDTO.success(phChargePackageDTO);
			} else {
				System.out.println(juhe.getError_code() + ":" + juhe.getReason());
				return ResultDTO.fail(juhe.getReason());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultDTO.fail();
	}

	/**
	 * 流量充值
	 * 
	 * @param phone:流量充值手机号
	 * @param pid:流量充值套餐ID
	 * @param innerCode:内部商户号，便于插表数据
	 * @return 请求示例：http://v.juhe.cn/flow/recharge?key=您申请的APPKEY&phone=
	 *         18913513535&pid=8&orderid=a1122111d&sign=
	 *         721a3f667b0eb63f54517971181e7392
	 */
	public ChargeResultDTO flowCharge(ChargeDTO chargeDTO) {

		String result = null;
		ChargeResultDTO ph = new ChargeResultDTO();
		String url = "http://v.juhe.cn/flow/recharge";// 请求接口地址
		String orderid = CodeUtil.generateOrderCode("");

		// md5,校验值，md5(OpenID+key+phone+pid+orderid)，结果转为小写
		String sign = Md5Util.MD5(OpenId + APPKEYFLOW + chargeDTO.getPhone() + chargeDTO.getPid() + orderid)
				.toLowerCase();

		StringBuffer sb = new StringBuffer();
		String sendData = sb.append("?key=").append(APPKEYFLOW).append("&phone=").append(chargeDTO.getPhone())
				.append("&pid=").append(chargeDTO.getPid()).append("&orderid=").append(orderid).append("&sign=")
				.append(sign).toString();

		RechargeOrderDO phoneChargeOrderDO = new RechargeOrderDO();
		phoneChargeOrderDO.setType(String.valueOf(chargeDTO.getType()));// 设置充值类型
		phoneChargeOrderDO.setAppUserId(String.valueOf(chargeDTO.getUserId()));// 设置app用户ID
		phoneChargeOrderDO.setOrderNo(orderid);// 设置商户订单ID
		phoneChargeOrderDO.setMobile(chargeDTO.getPhone());// 设置充值手机号
		phoneChargeOrderDO.setName(chargeDTO.getName());// 设置充值名称
		phoneChargeOrderDO.setAmt(chargeDTO.getInprice().replace(".", ""));// 设置交易金额
		phoneChargeOrderDO.setStatus(0);// 设置交易状态0-进行中
		rechargeOrderService.doAdd(phoneChargeOrderDO);

		TradeWithdrawDO tradeWithdrawDO = new TradeWithdrawDO();
		tradeWithdrawDO.setOrderNo(orderid);// 设置订单号
		tradeWithdrawDO.setOriginalOrderNo(orderid);// 设置原订单号(默认等于当前订单号)
		tradeWithdrawDO.setAmount(new BigDecimal(chargeDTO.getInprice()).multiply(new BigDecimal(100)));// 设置交易金额，优惠金额
		tradeWithdrawDO.setAppUserId(chargeDTO.getUserId());// 设置帐号ID
		tradeWithdrawDO.setRespCode("1000");// 交易进行中，需要再次调用订单查询接口进行查询
		tradeWithdrawDO.setTradeType(2);// 交易类型:2-消费
		tradeWithdrawDO.setTradeSubType(23); // 设置子类型,23流量充值
		tradeWithdrawDO.setStatus(1);// 设置交易状态，执行中
		tradeWithdrawService.doAdd(tradeWithdrawDO);// 更新数据库表

		try {
			result = RechargeUtil.net(url, sendData, "GET");
			logger.info(result);
			JuheDTO juhe = JSONObject.parseObject(result, JuheDTO.class);

			// 交易成功
			if (juhe.getError_code() == 0) {

				Map<String, String> map = JSONObject.parseObject(juhe.getResult().toString(), Map.class);

				phoneChargeOrderDO.setPayOrderNo(map.get("sporder_id").toString());// 设置渠道订单号
				phoneChargeOrderDO.setAmt(map.get("ordercash").toString().replace(".", ""));// 设置实际消费金额
				phoneChargeOrderDO.setRespCode(TradeStateEnum.SUCCESS.getCode());// 交易成功
				phoneChargeOrderDO.setRespMsg(juhe.getReason());// 设置响应
				int ret = rechargeOrderService.doUpdate(phoneChargeOrderDO);
				if (ret < 0) {
					logger.error("充值返回成功，数据更新失败。orderNo=" + phoneChargeOrderDO.getOrderNo() + "userId="
							+ chargeDTO.getUserId());
				}

				tradeWithdrawDO.setOriginalOrderNo(map.get("sporder_id").toString());// 设置渠道订单号
				tradeWithdrawDO
						.setAmount(new BigDecimal(map.get("ordercash").toString()).multiply(new BigDecimal(100)));// 设置实际消费金额
				tradeWithdrawDO.setRespCode(TradeStateEnum.SUCCESS.getCode());// 交易成功
				tradeWithdrawDO.setUpdateTime(new Date());// 设置交易完成时间
				tradeWithdrawDO.setRespMsg(juhe.getReason());// 设置响应
				Integer ret2 = tradeWithdrawService.doUpdate(tradeWithdrawDO);// 更新数据
				if (ret2 < 0) {
					logger.error("充值返回成功，数据更新失败。time=" + tradeWithdrawDO.getUpdateTime() + "userId="
							+ chargeDTO.getUserId());
				}

				ph.setRespCode(TradeStateEnum.SUCCESS.getCode());
				ph.setRespMsg(juhe.getReason());
				ph.setOrderNo(orderid);

			} else if (juhe.getError_code() == 10014) {// 系统内部异常(调用充值类业务时，请务必联系客服或通过订单查询接口检测订单，避免造成损失)

				tradeWithdrawDO.setRespCode("1000");// 交易进行中，需要再次调用订单查询接口进行查询
				tradeWithdrawDO.setRespMsg(juhe.getReason());// 设置响应
				tradeWithdrawDO.setUpdateTime(new Date());// 设置最后更新时间
				Integer ret2 = tradeWithdrawService.doUpdate(tradeWithdrawDO);// 更新数据
				if (ret2 < 0) {
					logger.error(
							"充值失败，数据更新失败。time=" + tradeWithdrawDO.getUpdateTime() + "userId=" + chargeDTO.getUserId());
				}

				phoneChargeOrderDO.setRespCode("1000");// 交易进行失败
				phoneChargeOrderDO.setRespMsg(juhe.getReason());// 设置响应
				int ret = rechargeOrderService.doUpdate(phoneChargeOrderDO);
				if (ret < 0) {
					logger.error("充值返回失败，数据更新失败。orderNo=" + phoneChargeOrderDO.getOrderNo() + "userId="
							+ chargeDTO.getUserId());
				}

				ph.setRespMsg("交易正在处理中，请稍后查询");

			} else {

				tradeWithdrawDO.setRespCode(TradeStateEnum.FAIL.getCode());// 交易进行中，需要再次调用订单查询接口进行查询
				tradeWithdrawDO.setStatus(2);// 状态为2-失败
				tradeWithdrawDO.setRespMsg(juhe.getReason());// 设置响应
				tradeWithdrawDO.setUpdateTime(new Date());// 设置最后更新时间
				Integer ret2 = tradeWithdrawService.doUpdate(tradeWithdrawDO);// 更新数据
				if (ret2 < 0) {
					logger.error(
							"充值失败，数据更新失败。time=" + tradeWithdrawDO.getUpdateTime() + "userId=" + chargeDTO.getUserId());
				}

				phoneChargeOrderDO.setRespCode(TradeStateEnum.FAIL.getCode());// 交易失败
				phoneChargeOrderDO.setRespMsg(juhe.getReason());// 设置响应
				phoneChargeOrderDO.setStatus(2);// 状态为2-失败
				int ret = rechargeOrderService.doUpdate(phoneChargeOrderDO);
				if (ret < 0) {
					logger.error("充值返回失败，数据更新失败。orderNo=" + phoneChargeOrderDO.getOrderNo() + "userId="
							+ chargeDTO.getUserId());
				}

				ph.setRespMsg(juhe.getReason());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ph;
	}

	/**
	 * 话费充值
	 * 
	 * @param phone:手机号
	 * @param pid:套餐id(金额)
	 * @return
	 */
	public ChargeResultDTO prepaidRefillCharge(ChargeDTO chargeDTO) {

		String result = null;
		ChargeResultDTO ph = new ChargeResultDTO();
		String orderid = CodeUtil.generateOrderCode("");

		// md5,校验值，md5(OpenID+key+phone+pid+orderid)，结果转为小写
		String sign = Md5Util.MD5(OpenId + APPKEYREPAID + chargeDTO.getPhone() + chargeDTO.getPid() + orderid);

		RechargeOrderDO phoneChargeOrderDO = new RechargeOrderDO();
		phoneChargeOrderDO.setType(String.valueOf(chargeDTO.getType()));// 设置充值类型
		phoneChargeOrderDO.setAppUserId(String.valueOf(chargeDTO.getUserId()));// 设置app用户ID
		phoneChargeOrderDO.setOrderNo(orderid);// 设置商户订单ID
		phoneChargeOrderDO.setMobile(chargeDTO.getPhone());// 设置充值手机号
		phoneChargeOrderDO.setName(chargeDTO.getName());// 设置充值名称:xx元
		phoneChargeOrderDO.setAmt(chargeDTO.getInprice().replace(".", ""));// 设置交易金额
		phoneChargeOrderDO.setStatus(0);// 设置交易状态0-进行中
		rechargeOrderService.doAdd(phoneChargeOrderDO);

		TradeWithdrawDO tradeWithdrawDO = new TradeWithdrawDO();
		tradeWithdrawDO.setOrderNo(orderid);// 设置订单号
		tradeWithdrawDO.setOriginalOrderNo(orderid);// 设置原订单号(默认等于当前订单号)
		tradeWithdrawDO.setAmount(new BigDecimal(chargeDTO.getInprice()).multiply(new BigDecimal(100)));// 设置交易金额，优惠金额
		tradeWithdrawDO.setAppUserId(chargeDTO.getUserId());// 设置帐号ID
		tradeWithdrawDO.setTradeType(2);// 交易类型:2-消费
		tradeWithdrawDO.setTradeSubType(22);// 交易子类型:22话费充值
		tradeWithdrawDO.setStatus(1);// 设置交易状态，执行中
		tradeWithdrawService.doAdd(tradeWithdrawDO);// 更新数据库表

		try {
			result = RechargeUtil.get(onlineUrl.replace("*", chargeDTO.getPid() + "").replace("!", chargeDTO.getPhone())
					.replace("@", orderid).replace("$", sign), 0);
			JuheDTO juhe = JSONObject.parseObject(result, JuheDTO.class);

			logger.info("result:" + juhe);
			if (juhe.getError_code() == 0) {

				Map<String, Object> map = JSONObject.parseObject(juhe.getResult().toString(), Map.class);

				phoneChargeOrderDO.setPayOrderNo(map.get("sporder_id").toString());// 设置渠道订单号
				phoneChargeOrderDO.setAmt(map.get("ordercash").toString().replace(".", ""));// 设置实际消费金额
				phoneChargeOrderDO.setRespCode(TradeStateEnum.SUCCESS.getCode());// 交易成功
				phoneChargeOrderDO.setRespMsg(juhe.getReason());// 设置响应
				int ret = rechargeOrderService.doUpdate(phoneChargeOrderDO);
				if (ret < 0) {
					logger.error("充值返回成功，数据更新失败。orderNo=" + phoneChargeOrderDO.getOrderNo() + "userId="
							+ chargeDTO.getUserId());
				}

				tradeWithdrawDO.setOriginalOrderNo(map.get("sporder_id").toString());// 设置渠道订单号
				tradeWithdrawDO
						.setAmount(new BigDecimal(map.get("ordercash").toString()).multiply(new BigDecimal(100)));// 设置实际消费金额
				tradeWithdrawDO.setRespCode(TradeStateEnum.SUCCESS.getCode());// 交易成功
				tradeWithdrawDO.setRespCode("1000");// 交易进行中，需要再次调用订单查询接口进行查询
				tradeWithdrawDO.setUpdateTime(new Date());// 设置交易完成时间
				tradeWithdrawDO.setRespMsg(juhe.getReason());// 设置响应
				Integer ret2 = tradeWithdrawService.doUpdate(tradeWithdrawDO);// 更新数据
				if (ret2 < 0) {
					logger.error("充值返回成功，数据更新失败。time=" + tradeWithdrawDO.getUpdateTime() + "userId="
							+ chargeDTO.getUserId());
				}

				ph.setRespCode(TradeStateEnum.SUCCESS.getCode());
				ph.setRespMsg(juhe.getReason());
				ph.setOrderNo(orderid);

			} else if (juhe.getError_code() == 10014) {// 系统内部异常(调用充值类业务时，请务必联系客服或通过订单查询接口检测订单，避免造成损失)

				phoneChargeOrderDO.setRespCode("1000");// 交易进行失败
				phoneChargeOrderDO.setRespMsg(juhe.getReason());// 设置响应
				int ret = rechargeOrderService.doUpdate(phoneChargeOrderDO);
				if (ret < 0) {
					logger.error("充值返回失败，数据更新失败。orderNo=" + phoneChargeOrderDO.getOrderNo() + "userId="
							+ chargeDTO.getUserId());
				}

				tradeWithdrawDO.setRespCode("1000");// 交易进行中，需要再次调用订单查询接口进行查询
				tradeWithdrawDO.setRespMsg(juhe.getReason());// 设置响应
				tradeWithdrawDO.setUpdateTime(new Date());// 设置最后更新时间
				Integer ret2 = tradeWithdrawService.doUpdate(tradeWithdrawDO);// 更新数据
				if (ret2 < 0) {
					logger.error(
							"充值失败，数据更新失败。time=" + tradeWithdrawDO.getUpdateTime() + "userId=" + chargeDTO.getUserId());
				}

				ph.setRespMsg("交易正在处理中，请稍后查询");
			} else {

				phoneChargeOrderDO.setRespCode(TradeStateEnum.FAIL.getCode());// 交易失败
				phoneChargeOrderDO.setRespMsg(juhe.getReason());// 设置响应
				phoneChargeOrderDO.setStatus(2);// 状态为2-失败
				int ret = rechargeOrderService.doUpdate(phoneChargeOrderDO);
				if (ret < 0) {
					logger.error("充值返回失败，数据更新失败。orderNo=" + phoneChargeOrderDO.getOrderNo() + "userId="
							+ chargeDTO.getUserId());
				}

				tradeWithdrawDO.setRespCode(TradeStateEnum.FAIL.getCode());// 交易进行中，需要再次调用订单查询接口进行查询
				tradeWithdrawDO.setStatus(2);// 状态为2-失败
				tradeWithdrawDO.setRespMsg(juhe.getReason());// 设置响应
				tradeWithdrawDO.setUpdateTime(new Date());// 设置最后更新时间
				Integer ret2 = tradeWithdrawService.doUpdate(tradeWithdrawDO);// 更新数据
				if (ret2 < 0) {
					logger.error("充值失败，数据更新失败。orderNo=" + phoneChargeOrderDO.getOrderNo() + "userId="
							+ chargeDTO.getUserId());
				}

				ph.setRespMsg(juhe.getReason());
			}
		} catch (Exception e) {
			logger.error("充值错误:", e);
		}

		return ph;
	}

	/**
	 * 流量充值订单状态查询(用于在充值返回系统内部异常时调用)
	 * 
	 * @param orderNo：原订单号
	 */
	public void queryFlowResult(RechargeOrderDO rechargeOrderDO) {
		String result = null;
		String url = "http://v.juhe.cn/flow/batchquery";// 请求接口地址

		Map params = new HashMap();// 请求参数
		params.put("orderid", rechargeOrderDO.getOrderNo());// 用户订单号，多个以英文逗号隔开，最大支持50组
		params.put("key", APPKEYFLOW);// 应用APPKEY(应用详细页查询)

		try {
			result = RechargeUtil.net(url, params, "GET");
			JuheDTO juhe = JSONObject.parseObject(result, JuheDTO.class);

			// 查询返回成功，则解析result字串取其中的充值状态
			if (juhe.getError_code() == 0) {

				Map<String, String> map = JSONObject.parseObject(juhe.getResult().toString(), Map.class);
				if ("1".equals(map.get("game_state"))) {// 成功

					rechargeOrderDO.setPayOrderNo(map.get("sporder_id").toString());// 设置渠道订单号
					rechargeOrderDO.setAmt(map.get("uordercash").replace(".", ""));// 设置实际消费金额
					rechargeOrderDO.setStatus(1);// 设置交易状态为1-成功
					rechargeOrderService.doUpdate(rechargeOrderDO);

				} else if ("9".equals(map.get("game_state"))) {// 失败

					rechargeOrderDO.setStatus(2);// 设置交易状态为2-失败
					rechargeOrderService.doUpdate(rechargeOrderDO);
				}
			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  话费充值状态查询
	 * 
	 * @param orderid 商家订单号  @return 订单结果
	 * @throws Exception
	 * 
	 * @throws Exception
	 */
	public void orderSta(RechargeOrderDO rechargeOrderDO) {

		String result = null;

		try {
			result = RechargeUtil.get(orderstaUrl.replace("!", rechargeOrderDO.getOrderNo()), 0);
			JuheDTO juhe = JSONObject.parseObject(result, JuheDTO.class);

			// 查询返回成功，则解析result字串取其中的充值状态
			if (juhe.getError_code() == 0) {

				Map<String, String> map = JSONObject.parseObject(juhe.getResult().toString(), Map.class);

				if ("1".equals(map.get("game_state"))) {// 成功

					rechargeOrderDO.setPayOrderNo(map.get("sporder_id").toString());// 设置渠道订单号
					rechargeOrderDO.setAmt(map.get("uordercash").replace(".", ""));// 设置实际消费金额
					rechargeOrderDO.setStatus(1);// 设置交易状态为1-成功
					Integer ret = rechargeOrderService.doUpdate(rechargeOrderDO);
					if (ret < 0) {
						logger.error("充值结果查询成功，原充值交易成功,数据更新失败。orderNo=" + rechargeOrderDO.getOrderNo() + "userId="
								+ rechargeOrderDO.getAppUserId());
					}
				} else if ("9".equals(map.get("game_state"))) {// 失败

					rechargeOrderDO.setStatus(2);// 设置交易状态为2-失败
					Integer ret = rechargeOrderService.doUpdate(rechargeOrderDO);

					if (ret < 0) {
						logger.error("充值结果查询成功，原充值交易失败,数据更新失败。orderNo=" + rechargeOrderDO.getOrderNo() + "userId="
								+ rechargeOrderDO.getAppUserId());
					}
				}
			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}