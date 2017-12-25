package net.fnsco.core.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
/**
 * @desc 内部商户号生产工具类
 * @author tangliang
 * @date 2017年6月23日 下午4:04:24
 */
public class CodeUtil {
	
	// 生成商户号 参数常用‘F’
	public static String generateMerchantCode(String prefix) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH) + 1;
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int value = (int) (Math.random() * (999999999 - 100000000) + 100000000);
		StringBuilder sb = new StringBuilder();
		sb.append(StringUtils.leftPad(String.valueOf(month), 2, '0')).append(StringUtils.leftPad(String.valueOf(day), 2, '0'))
				.append(StringUtils.leftPad(String.valueOf(hour), 2, '0')).append(value);

		return sb.toString();
	}
	
	// 生成店铺账套号
		public static String generateAccountId(String prefix) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			int month = calendar.get(Calendar.MONTH) + 1;
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int value = (int) (Math.random() * (999999999 - 100000000) + 100000000);
			StringBuilder sb = new StringBuilder();
			sb.append(prefix).append(StringUtils.leftPad(String.valueOf(month), 2, '0')).append(StringUtils.leftPad(String.valueOf(day), 2, '0'))
					.append(StringUtils.leftPad(String.valueOf(hour), 2, '0')).append(value);

			return sb.toString();
		}

	// 生成单号
	public static String generateOrderCode(String prefix) {
		
		String nowDateStr = DateUtils.getNowDateStr();
		int value = (int) (Math.random() * (99999999 - 10000000) + 10000000);
		StringBuilder sb = new StringBuilder();
		sb.append(prefix).append(nowDateStr).append(value);
		return sb.toString();
	}

	// 生成流水号
	public static String generateorderReqNo(String prefix) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		long milles = System.currentTimeMillis();
		int value = (int) (Math.random() * (999999 - 100000) + 100000);
		StringBuilder sb = new StringBuilder();
		sb.append(prefix).append(milles).append(value);

		return sb.toString();
	}
	
	// 生成通道终端批次号
	public static String generateBatchCode(String prefix) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return prefix+sdf.format(new Date());
	}
	
}
