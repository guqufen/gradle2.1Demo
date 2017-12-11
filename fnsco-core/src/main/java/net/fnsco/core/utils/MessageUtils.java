package net.fnsco.core.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.dto.MessageValidateDTO;

/**
 * 发送验证码，校验验证码
 * 
 * @author Administrator
 *
 */
public class MessageUtils {
	private static final Logger logger = LoggerFactory.getLogger(MessageUtils.class);

	private static Map<String, MessageValidateDTO> MsgCodeMap = new HashMap<>();

	/**
	 * 生成验证码并发送
	 * @param deviceId
	 * @param mobile
	 * @return
	 */
	public ResultDTO getValidateCode(String deviceId,String mobile) {
		if (Strings.isNullOrEmpty(deviceId)) {
			return ResultDTO.fail("deviceId为空");
		} else if (Strings.isNullOrEmpty(mobile)) {
			return ResultDTO.fail("手机号为空");
		}
		// 生成6位验证码
		final String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
		MessageValidateDTO object = new MessageValidateDTO(code, System.currentTimeMillis());
        MsgCodeMap.put(mobile + deviceId, object);
		/**
		 * 开启线程发送手机验证码
		 */
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String callback = SmsUtil.Code(mobile, code);
					JSONObject callbackJson = (JSONObject) JSONObject.parse(callback);
					String resultCode = callbackJson.getString("code");
					if ("0".equals(resultCode)) {
						logger.warn("验证码" + code + "已经发送至手机号" + mobile + "返回详情" + callback);
					} else {
						logger.warn("验证码" + code + "未能够发送至手机" + mobile + "错误code" + resultCode + ";错误详情" + callback);
					}
				} catch (Exception e) {
					logger.warn("验证码" + code + "未能够发送至手机" + mobile + "错误原因:异常错误");
					logger.error("短信发送异常 ", e);
				}
			}
		}).start();
		return ResultDTO.success();
	}

	
	
	
	/**
	 * 比对验证码
	 * @param deviceId
	 * @param code
	 * @param mobile
	 * @return
	 */
	private ResultDTO validateCode(String deviceId, String code, String mobile) {
		// 非空判断
		if (Strings.isNullOrEmpty(deviceId)) {
			return ResultDTO.fail("非法请求,没有deviceId");
		} else if (Strings.isNullOrEmpty(code)) {
			return ResultDTO.fail("非法请求,没有验证码");
		}
		if (Strings.isNullOrEmpty(mobile)) {
			return ResultDTO.fail("非法请求,没有手机号");
		}
		if (MsgCodeMap.get(mobile + deviceId) == null) {
			return ResultDTO.fail("非法请求,验证码不正确");
		}
		// 从Map中根据手机号取到存入的验证码
		MessageValidateDTO codeDto = MsgCodeMap.get(mobile + deviceId);
		if (null == codeDto) {
			return ResultDTO.fail("非法请求,验证码不正确");
		}
		// 时间
		long newTime = System.currentTimeMillis();
		// 验证码超过30分钟
		if ((newTime - codeDto.getTime()) / 1000 / 60 > 30) {
			MsgCodeMap.remove(mobile + deviceId);
			return ResultDTO.fail("验证码超时");
		}
		// 验证码正确
		if (code.equals(codeDto.getCode())) {
			MsgCodeMap.remove(mobile + deviceId);
			return ResultDTO.success();
		}
		return ResultDTO.fail("非法请求,验证码不正确");
	}

}
