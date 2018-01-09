package net.fnsco.trading.service.pay.channel.zxyh;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import net.fnsco.bigdata.service.dao.master.AppUserMerchant1Dao;
import net.fnsco.bigdata.service.dao.master.MerchantChannelDao;
import net.fnsco.bigdata.service.dao.master.MerchantTerminalDao;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantTerminal;
import net.fnsco.bigdata.service.sys.SequenceService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.trading.service.pay.channel.zxyh.dto.H5PayDTO;
import net.fnsco.trading.service.pay.channel.zxyh.dto.H5PayReqDTO;
import net.fnsco.trading.service.pay.channel.zxyh.dto.H5PayResultDTO;
import net.fnsco.trading.service.pay.channel.zxyh.util.ZxyhPayMD5Util;

@Service
public class H5PaymentService extends BaseService {

	@Autowired
	private AppUserMerchant1Dao appUserMerchant1Dao;
	@Autowired
	private MerchantChannelDao merchantChannelDao;
	@Autowired
	private Environment env;
	@Autowired
	private SequenceService sequenceService;

	public ResultDTO<H5PayResultDTO> H5Pay(H5PayReqDTO h5PayJO) {
		String url = "/MPay/backTransAction.do";// 微信测试
		String merId = env.getProperty("zxyh.merId");
		H5PayDTO h5PayDTO = new H5PayDTO();

		h5PayDTO.init(merId);

		// 通过userId查找绑定的内部商户号
		String innerCode = appUserMerchant1Dao.selectInnerCodeByUserId(h5PayJO.getUserId());
		if (null == innerCode) {
			logger.info("该用户没有绑定内部商户号，请核查后重新交易");
			return ResultDTO.fail("1002");
		}
		// 通过内部商户号查找绑定的中信商户号
		MerchantChannel merchantChannel = merchantChannelDao.selectByInnerCodeType(innerCode, "05");
		if (null == merchantChannel) {
			logger.info("该内部商户号没有绑定中信渠道的商户号，请核查后重新交易,innerCode=[" + innerCode + "");
			return ResultDTO.fail("1002");
		}

		h5PayDTO.setBackEndUrl(env.getProperty("zxyh.h5.backurl"));// 接收支付网关异步通知回调地址
		h5PayDTO.setSecMerId(merchantChannel.getChannelMerId());// 分账子商户号，使用分账功能时上传，是与merId关联的分账子商户号
		h5PayDTO.setTermIp(h5PayJO.getTermIp());// 客户端真实IP
		h5PayDTO.setOrderId(
				DateUtils.getNowYMDOnlyStr() + innerCode + sequenceService.getOrderSequence("t_trade_order"));// 商户订单号
		h5PayDTO.setOrderTime(DateUtils.getNowDateStr());// 订单生成时间
		h5PayDTO.setOrderBody(h5PayJO.getOrderBody());// 商品或支付简要描述
		h5PayDTO.setTxnAmt(h5PayJO.getAmt());// 交易总金额，以分为单位
		h5PayDTO.setSceneInfo(h5PayJO.getSceneInfo());// 应用描述

		String passiveStr = JSON.toJSONString(h5PayDTO);// 将对象转换为JSON字符串
		Map<String, String> passiveMap = JSON.parseObject(passiveStr, Map.class);// 将JSON字符串转换为map
		String respStr = ZxyhPayMD5Util.request(passiveMap, url, env.getProperty("zxyh.pay.url"));

		// 解析返回报文
		Map<String, Object> respMap = ZxyhPayMD5Util.getResp(respStr);
		logger.info(respMap+"");
		String json = JSON.toJSONString(respMap);
		H5PayDTO h5PayDTO2 = JSON.parseObject(json, H5PayDTO.class);

		H5PayResultDTO h5PayResultDTO = new H5PayResultDTO();
		h5PayResultDTO.setRespCode(h5PayDTO2.getRespCode());
		h5PayResultDTO.setRespMsg(h5PayDTO2.getRespMsg());
		h5PayResultDTO.setOrderNo(h5PayDTO2.getOrderId());
		h5PayResultDTO.setOrderTime(h5PayDTO2.getOrderTime());
		h5PayResultDTO.setMwebUrl(h5PayDTO2.getMwebUrl());

		return ResultDTO.success(h5PayResultDTO);
	}
	
	public String callBack(String respStr){
		
		H5PayDTO h5PayDTO = JSON.parseObject(respStr, H5PayDTO.class);
		if(h5PayDTO == null){
			logger.error("h5通知结果数据有误，解析出来的数据为空！");
			return "fail";
		}
		
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
