package net.fnsco.trading.service.pay.channel.zxyh;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import net.fnsco.bigdata.comm.ServiceConstant.TradeTypeEnum;
import net.fnsco.bigdata.service.dao.master.AppUserMerchant1Dao;
import net.fnsco.bigdata.service.dao.master.MerchantChannelDao;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.sys.SequenceService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.trading.comm.TradeConstants.PayMediumEnum;
import net.fnsco.trading.constant.E789ApiConstant;
import net.fnsco.trading.constant.E789ApiConstant.PayTypeEnum;
import net.fnsco.trading.constant.E789ApiConstant.ResponCodeEnum;
import net.fnsco.trading.constant.E789ApiConstant.WeChatPayTypeEnum;
import net.fnsco.trading.service.order.TradeOrderService;
import net.fnsco.trading.service.order.entity.TradeOrderDO;
import net.fnsco.trading.service.pay.channel.zxyh.dto.WeChatPubPayDTO;
import net.fnsco.trading.service.pay.channel.zxyh.dto.WeChatPubPayReqDTO;
import net.fnsco.trading.service.pay.channel.zxyh.dto.WeChatPubPayResultDTO;
import net.fnsco.trading.service.pay.channel.zxyh.util.ZxyhPayMD5Util;

@Service
public class WeChatPubPayService extends BaseService {

	@Autowired
	private AppUserMerchant1Dao appUserMerchant1Dao;
	@Autowired
	private MerchantChannelDao merchantChannelDao;
	@Autowired
	private Environment env;
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private TradeOrderService tradeOrderService;

	public ResultDTO<WeChatPubPayResultDTO> weChatPubPay(WeChatPubPayReqDTO h5PayJO) {

		String url = "/MPay/backTransAction.do";// URL
		String merId = env.getProperty("zxyh.merId");
		WeChatPubPayDTO weChatPubPayDTO = new WeChatPubPayDTO();

		weChatPubPayDTO.init(merId);

		// 通过userId查找绑定的内部商户号
		String innerCode = appUserMerchant1Dao.selectInnerCodeByUserId(h5PayJO.getUserId());
		if (null == innerCode) {
			logger.info("该用户没有绑定内部商户号，请核查后重新交易");
			return ResultDTO.fail(E789ApiConstant.E_NOT_FIND_INNERCODE);
		}

		// 通过内部商户号查找绑定的中信商户号
		MerchantChannel merchantChannel = merchantChannelDao.selectByInnerCodeType(innerCode, "05");
		if (null == merchantChannel) {
			logger.info("该内部商户号没有绑定中信渠道的商户号，请核查后重新交易,innerCode=[" + innerCode + "");
			return ResultDTO.fail(E789ApiConstant.E_NOT_FIND_CHANNEL_INNERCODE);
		}

		weChatPubPayDTO.setTxnSubType(WeChatPayTypeEnum.getCodeByType(h5PayJO.getTxnType()));// 交易子类型，010131：微信公众号支付；010134：微信小程序支付；010502：QQ公众号支付
		weChatPubPayDTO.setBackEndUrl(env.getProperty("zxyh.wechat.pubpay.backUrl"));// 后台通知地址,接收支付网关异步通知回调地址
		weChatPubPayDTO.setSecMerId(merchantChannel.getChannelMerId());// 分账子商户号,使用分账功能时上传，是与merId关联的分账子商户号
		// weChatPubPayDTO.setTermIp(h5PayJO.getTermIp());//终端IP,接入商机器IP
		weChatPubPayDTO.setOrderId(
				DateUtils.getNowYMDOnlyStr() + innerCode + sequenceService.getOrderSequence("t_trade_order"));// 商户订单号
		weChatPubPayDTO.setOrderTime(DateUtils.getNowDateStr());// 订单生成时间
		weChatPubPayDTO.setOrderBody(h5PayJO.getOrderBody());// 商品描述
		weChatPubPayDTO.setOrderSubOpenid(h5PayJO.getOrderSubOpenid());// 用户在商户公众号的唯一标识（openid），QQ公众号支付非必填
		weChatPubPayDTO.setTxnAmt(h5PayJO.getAmt());// 交易金额

		// 插入交易数据
		TradeOrderDO tradeOrderDO = new TradeOrderDO();
		tradeOrderDO.setOrderNo(weChatPubPayDTO.getOrderId());// 设置订单号
		tradeOrderDO.setTxnAmount(new BigDecimal(weChatPubPayDTO.getTxnAmt()));// 设置交易金额
		tradeOrderDO.setChannelMerId(merchantChannel.getChannelMerId());// 设置渠道商户号
		tradeOrderDO.setChannelType("05");// 设置渠道类型05-中信银行
		tradeOrderDO.setOrderCeateTime(new Date());// 订单创建时间
		tradeOrderDO.setTxnType(Integer.parseInt(TradeTypeEnum.CONSUMER.getCode()));// 设置交易类型
		// tradeOrderDO.setPayType(PayTypeEnum.CODE_PAY.getCode());//
		// 设置支付方式，01-二维码
		tradeOrderDO.setPaySubType(PayTypeEnum.PAYBYWX.getCode());// 设置支付子类型，01-微信
		tradeOrderDO.setSettleStatus(0);// 设置结算状态0-未结算
		tradeOrderDO.setCreateTime(new Date());// 创建时间
		tradeOrderDO.setInnerCode(innerCode);// 设置内部商户号
		tradeOrderDO.setSettleStatus(0);// 设置清算状态0-未清算
		tradeOrderDO.setSyncStatus(0);// 设置同步状态0-未同步
		tradeOrderDO.setPayMedium(PayMediumEnum.APP.getCode());// 设置支付媒介，APP
		tradeOrderService.doAdd(tradeOrderDO);

		String weChatPubPayStr = JSON.toJSONString(weChatPubPayDTO);// 将对象转换为JSON字符串
		Map<String, String> passiveMap = JSON.parseObject(weChatPubPayStr, Map.class);// 将JSON字符串转换为map
		String respStr = ZxyhPayMD5Util.request(passiveMap, url, env.getProperty("zxyh.pay.url"));

		// 解析返回报文
		Map<String, Object> respMap = ZxyhPayMD5Util.getResp(respStr);
		logger.info(respMap + "");
		String json = JSON.toJSONString(respMap);
		WeChatPubPayDTO weChatPubPayDTO1 = JSON.parseObject(json, WeChatPubPayDTO.class);

		// 通过订单号查询交易
		TradeOrderDO tradeOrderDO1 = tradeOrderService.queryByOrderId(tradeOrderDO.getOrderNo());
		if (null == tradeOrderDO1) {
			logger.info("没有找到该交易请求交易,order_no=[" + tradeOrderDO.getOrderNo() + "]");

			return ResultDTO.fail("交易失败");
		}

		// 交易失败
		if (!"0000".equals(weChatPubPayDTO1.getRespCode())) {

			tradeOrderDO1.setRespCode(ResponCodeEnum.DEAL_FAIL.getCode());// 设置响应应答码
			tradeOrderDO1.setRespMsg(weChatPubPayDTO1.getRespMsg());// 设置响应信息
			tradeOrderDO1.setPayOrderNo(weChatPubPayDTO1.getTxnSeqId());// 支付订单号(平台流水号，供后续退货或者撤销或对账使用)
			tradeOrderService.doUpdate(tradeOrderDO1);// 通过主键更新应答数据
			return ResultDTO.fail(weChatPubPayDTO1.getRespMsg());

		}

		tradeOrderDO1.setRespCode(ResponCodeEnum.DEAL_SUCCESS.getCode());// 设置响应应答码
		tradeOrderDO1.setRespMsg(weChatPubPayDTO1.getRespMsg());// 设置响应信息
		tradeOrderDO1.setPayOrderNo(weChatPubPayDTO1.getTxnSeqId());// 支付订单号(平台流水号，供后续退货或者撤销或对账使用)
		tradeOrderService.doUpdate(tradeOrderDO1);// 通过主键更新应答数据

		// 设置返回应答数据
		WeChatPubPayResultDTO weChatPubPayResultDTO = new WeChatPubPayResultDTO();
		weChatPubPayResultDTO.setRespCode(ResponCodeEnum.DEAL_SUCCESS.getCode());
		weChatPubPayResultDTO.setRespMsg(weChatPubPayDTO1.getRespMsg());// 设置应答信息
		weChatPubPayResultDTO.setOrderNo(weChatPubPayDTO.getOrderId());
		weChatPubPayResultDTO.setAppId(weChatPubPayDTO.getAppId());// 公众号ID，供微信JSAPI调用参数
		weChatPubPayResultDTO.setTimeStamp(weChatPubPayDTO.getTimeStamp());// 时间戳,供微信JSAPI调用参数
		weChatPubPayResultDTO.setNonceStr(weChatPubPayDTO.getNonceStr());// 随机字符串,供微信JSAPI调用参数
		weChatPubPayResultDTO.setExtDetail(respMap.get("package").toString());// 订单详情扩展字符串，供微信JSAPI调用参数

		return ResultDTO.success(weChatPubPayResultDTO);
	}

	public String callBack(String respStr) {

		WeChatPubPayDTO weChatPayDTO = JSON.parseObject(respStr, WeChatPubPayDTO.class);
		if (weChatPayDTO == null) {
			logger.error("通知结果数据有误，解析出来的数据为空！");
			return "fail";
		}

		// 通过订单号查询交易
		TradeOrderDO tradeOrderDO1 = tradeOrderService.queryByOrderId(weChatPayDTO.getOrderId());
		if (null == tradeOrderDO1) {
			logger.info("回调函数，没有找到该交易请求交易,order_no=[" + weChatPayDTO.getOrderId() + "]");
		}

		// 成功
		if ("0000".equals(weChatPayDTO.getRespCode())) {
			tradeOrderDO1.setRespCode(ResponCodeEnum.DEAL_SUCCESS.getCode());
		} else {
			tradeOrderDO1.setRespCode(ResponCodeEnum.DEAL_FAIL.getCode());
		}

		tradeOrderDO1.setRespMsg(weChatPayDTO.getRespMsg());// 应答信息
		tradeOrderDO1.setSettleAmount(new BigDecimal(weChatPayDTO.getSettleAmt()));// 清算金额
		tradeOrderDO1.setSettleDate(DateUtils.StrToDate(weChatPayDTO.getSettleDate() + "000000"));// 清算日期
		tradeOrderDO1.setEndCreateDate(DateUtils.StrToDate(weChatPayDTO.getEndTime()));// 支付完成时间
		tradeOrderService.doUpdate(tradeOrderDO1);// 通过主键更新应答数据

		Map<String, String> map = new HashMap<>();
		map.put("respCode", "0000");
		map.put("respMsg", "OK");
		String b64ReqStr = null;

		try {
			b64ReqStr = Base64.encodeBase64String(map.toString().getBytes("utf-8")).replaceAll("\\+", "#");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		// 生成最后的报文
		String finalB64ReqStr = "sendData=" + b64ReqStr;
		System.out.println("req :" + finalB64ReqStr);
		return finalB64ReqStr;
	}
}
