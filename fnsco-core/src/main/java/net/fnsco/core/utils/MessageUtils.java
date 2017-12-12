package net.fnsco.core.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
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
	
	
	
	
	public MessageValidateDTO getValidateCode(String deviceId, String mobile, String type) {
		// 生成6位验证码
		final String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
		MessageValidateDTO object = new MessageValidateDTO(code, System.currentTimeMillis());
		return object;
	}
	/**
	 * 发送验证码
	 * @param deviceId
	 * @param mobile
	 * @return
	 */
	public ResultDTO sendValidateCode(String mobile,String code) {
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

	
	public ResultDTO<Object> validateCode2(String code, String mobile, MessageValidateDTO mDTO) {
		// 非空判断
		if (Strings.isNullOrEmpty(code)) {
			return ResultDTO.fail("非法请求,没有验证码");
		}
		if (Strings.isNullOrEmpty(mobile)) {
			return ResultDTO.fail("非法请求,没有手机号");
		}
		if (mDTO.getCode() == null) {
			return ResultDTO.fail("非法请求,验证码不正确");
		}

		long newTime = System.currentTimeMillis();

		if (!StringUtils.equals(code, mDTO.getCode())) {
			logger.warn("非法请求,验证码不正确");
			return ResultDTO.fail("非法请求,验证码不正确");
			
		} else if ((newTime - mDTO.getTime()) / 1000 / 60 > 30) {// 验证码超过30分钟
			logger.warn("验证码超时");
			return ResultDTO.fail("验证码超时");
		} else {
			return ResultDTO.success();
		}
	}
	
	
	/**
	 * 比对验证码
	 * @param deviceId
	 * @param code
	 * @param mobile
	 * @return
	 */
	public ResultDTO<Object> validateCode(String deviceId, String code, String mobile) {
		// 非空判断
//		if (Strings.isNullOrEmpty(deviceId)) {
//			return ResultDTO.fail("非法请求,没有deviceId");
//		} else if (Strings.isNullOrEmpty(code)) {
//			return ResultDTO.fail("非法请求,没有验证码");
//		}
//		if (Strings.isNullOrEmpty(mobile)) {
//			return ResultDTO.fail("非法请求,没有手机号");
//		}
//		if (MsgCodeMap.get(mobile + deviceId) == null) {
//			return ResultDTO.fail("非法请求,验证码不正确");
//		}
//		// 从Map中根据手机号取到存入的验证码
//		MessageValidateDTO codeDto = MsgCodeMap.get(mobile + deviceId);
//		if (null == codeDto) {
//			return ResultDTO.fail("非法请求,验证码不正确");
//		}
//		// 时间
//		long newTime = System.currentTimeMillis();
//		// 验证码超过30分钟
//		if ((newTime - codeDto.getTime()) / 1000 / 60 > 30) {
//			MsgCodeMap.remove(mobile + deviceId);
//			return ResultDTO.fail("验证码超时");
//		}
//		// 验证码正确
//		if (code.equals(codeDto.getCode())) {
//			MsgCodeMap.remove(mobile + deviceId);
//			return ResultDTO.success();
//		}
//		return ResultDTO.fail("非法请求,验证码不正确");
		return null;
	}




	

}
