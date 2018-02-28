package net.fnsco.core.cityzencard;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * @desc 市民卡代扣业务
 * @author tangliang
 * @version 0.0.1-SNAPSHOT
 * @since Ver 1.1
 * @Date 2018年2月27日 下午3:34:25
 */

public class ZShiMingKaUtil {

	private static Logger logger = LoggerFactory.getLogger(ZShiMingKaUtil.class);
	
	private static final String SHIMINGKA_URL = "http://115.236.162.166:28082/HzsmkPay/merWithhold.do";
	private static final String FIX_POSITION = "lib/test.pfx";
	private static final String SECRET_KEY = "hzsmk";
	
	/**
	 * postWHP0001:(2.1下发签约短信验证码-WHP0001)
	 *
	 * @param @return 
	 *            设定文件
	 * @return String DOM对象
	 * @author tangliang
	 * @date 2018年2月27日 下午3:39:12
	 */
	public static JSONObject postWHP0001(WHP0001 wHP0001) {
		// 生成请求报文
		String reqMessage = DeductHandler.handleRequestMessage(wHP0001, FIX_POSITION, SECRET_KEY);
		// String reqMessage = "\"{\"a\":\"1\", \"b\":\"2\"}\"";
		logger.info(reqMessage);
		// 发送报文，返回结果
		String respMessage = HttpUtil.postReq(SHIMINGKA_URL, reqMessage);
		logger.info(respMessage);
		if (StringUtils.isEmpty(respMessage)) {
			logger.error("postWHP0001签约短信接口有误!");
			return null;
		}
		return JSONObject.parseObject(respMessage);
	}

	/**
	 * postWHPU0002:(2.2代扣签约-WHP0002)
	 *
	 * @param @return
	 *            设定文件
	 * @return JSONObject DOM对象
	 * @author tangliang
	 * @date 2018年2月27日 下午4:02:28
	 */
	public static JSONObject postWHPU0002(WHP0002 wHP0002) {

		// 生成请求报文
		String reqMessage = DeductHandler.handleRequestMessage(wHP0002, FIX_POSITION, SECRET_KEY);
		// String reqMessage = "\"{\"a\":\"1\", \"b\":\"2\"}\"";
		logger.info(reqMessage);
		// 发送报文，返回结果
		String respMessage = HttpUtil.postReq(SHIMINGKA_URL, reqMessage);
		logger.info(respMessage);

		if (StringUtils.isEmpty(respMessage)) {
			logger.error("postWHP0002签约短信接口有误!");
			return null;
		}
		return JSONObject.parseObject(respMessage);
	}

	/**
	 * postWHP0003:(2.3单笔支付-WHP0003)
	 *
	 * @param @return
	 *            设定文件
	 * @return JSONObject DOM对象
	 * @author tangliang
	 * @date 2018年2月27日 下午4:04:19
	 */
	public static  JSONObject postWHP0003(WHP0003 wHP0003) {

		// 生成请求报文
		String reqMessage = DeductHandler.handleRequestMessage(wHP0003, FIX_POSITION, SECRET_KEY);
		// String reqMessage = "\"{\"a\":\"1\", \"b\":\"2\"}\"";
		logger.info(reqMessage);
		// 发送报文，返回结果
		String respMessage = HttpUtil.postReq(SHIMINGKA_URL, reqMessage);
		logger.info(respMessage);

		if (StringUtils.isEmpty(respMessage)) {
			logger.error("postWHP0003签约短信接口有误!");
			return null;
		}
		return JSONObject.parseObject(respMessage);
	}
}
