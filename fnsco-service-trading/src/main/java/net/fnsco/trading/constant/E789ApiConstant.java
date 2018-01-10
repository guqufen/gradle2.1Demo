package net.fnsco.trading.constant;

import net.fnsco.core.constants.CoreConstants;

public class E789ApiConstant extends CoreConstants {

	public static String E_DATA_SOURCE_TIMEOUT = "228701"; // 数据源超时
	public static String E_PAR_ERROR = "228702"; // 参数错误
	public static String E_IMAGE_TYPE_ERROR = "228703"; // 图片类型错误
	public static String E_IMAGE_LENGTH_ERROR = "228704"; // 图片长宽错误
	public static String E_IMAGE_SIZE_ERROR = "228705"; // 图片大小错误
	public static String E_IDENTIFY_FAILURE = "228706"; // 识别失败(计费一次,识别身份证错误，出现此问题的原因一般为：您上传了非身份证图片或您上传的身份证图片不完整)
	public static String E_OTHER_ERROR = "228707"; // 其他错误,具体参照reason
	public static String E_DISSUPOPORT_GET = "228708"; // 不支持GET请求

	public static String E_NOT_FOUND_PRE = "210301"; // 库中无此身份证记录
	public static String E_SERVER_EXC = "210302"; // 第三方服务器异常
	public static String E_SERVER_MAINTENANCE = "210303"; // 服务器维护
	public static String E_PAR_ERROR_ID = "210304"; // 参数异常
	public static String E_NETWORK_ERROR = "210305"; // 网络错误，请重试
	public static String E_DATA_SOURCE_ERROR = "210306"; // 数据源错误，具体参照reason

	public static String E_IDCARD_OUT_OF_TIME = "210000"; // 身份证已过期
	public static String E_UPLOAD_IDCARD_FAIL = "210001"; // 身份证上传失败
	public static String E_FORNT_NOT_FOUND = "210002"; // 请上传身份证正面照
	public static String E_BACK_NOT_FOUND = "210003"; // 请上传身份证反面照

	public static String E_BANK_IS_EXIST = "210005"; // 银行卡已存在
	public static String E_NOT_FIND_INNERCODE = "210006"; // 未找到对应内部商户号
	public static String E_NOT_FIND_ENTITY_INNERCODE = "210007"; // 未找到对应实体内部商户号
	public static String E_NOT_FIND_CHANNEL_INNERCODE = "210008"; // 未找到对应渠道商户号
	public static String E_APP_PAY_PASSWORD_ERROR      = "210009"; //支付密码错误
	public static String E_ADD_FIRST      = "210010"; //请先入建中信商户
	
	public static String E_ID_CARD_F_ERROR      = "2100020"; //身份证正面识别失败
	public static String E_ID_CARD_B_ERROR      = "210021"; //身份证反面识别失败
	public static String E_UNBOUND_SUCCESS      = "2100022"; //身份证反面识别失败

	static {
		// 身份识别证错误信息
		ERROR_MESSGE_MAP.put(E_DATA_SOURCE_TIMEOUT, "数据源超时");
		ERROR_MESSGE_MAP.put(E_PAR_ERROR, "身份证识别参数错误");
		ERROR_MESSGE_MAP.put(E_IMAGE_TYPE_ERROR, "图片类型错误");
		ERROR_MESSGE_MAP.put(E_IMAGE_LENGTH_ERROR, "图片长宽错误");
		ERROR_MESSGE_MAP.put(E_IMAGE_SIZE_ERROR, "图片大小错误");
		ERROR_MESSGE_MAP.put(E_IDENTIFY_FAILURE, "识别失败(您上传了非身份证图片或您上传的身份证图片不完整)");
		ERROR_MESSGE_MAP.put(E_OTHER_ERROR, "其他错误,具体参照reason");
		ERROR_MESSGE_MAP.put(E_DISSUPOPORT_GET, "不支持GET请求");

		// 身份证实名验证证错误信息
		ERROR_MESSGE_MAP.put(E_NOT_FOUND_PRE, "库中无此身份证记录");
		ERROR_MESSGE_MAP.put(E_SERVER_EXC, "第三方服务器异常");
		ERROR_MESSGE_MAP.put(E_SERVER_MAINTENANCE, "服务器维护");
		ERROR_MESSGE_MAP.put(E_PAR_ERROR_ID, "实名验证参数异常");
		ERROR_MESSGE_MAP.put(E_NETWORK_ERROR, "网络错误，请重试");
		ERROR_MESSGE_MAP.put(E_DATA_SOURCE_ERROR, "数据源错误，具体参照reason");

		ERROR_MESSGE_MAP.put(E_IDCARD_OUT_OF_TIME, "身份证已过期");
		ERROR_MESSGE_MAP.put(E_UPLOAD_IDCARD_FAIL, "身份证上传失败");
		ERROR_MESSGE_MAP.put(E_FORNT_NOT_FOUND, "请上传身份证正面照");
		ERROR_MESSGE_MAP.put(E_BACK_NOT_FOUND, "请上传身份证反面照");

		ERROR_MESSGE_MAP.put(E_BANK_IS_EXIST, "银行卡已存在");
		ERROR_MESSGE_MAP.put(E_NOT_FIND_INNERCODE, "未找到对应内部商户号");
		ERROR_MESSGE_MAP.put(E_NOT_FIND_ENTITY_INNERCODE, "未找到对应实体内部商户号");
		ERROR_MESSGE_MAP.put(E_NOT_FIND_CHANNEL_INNERCODE, "未找到对应渠道商户号");
		ERROR_MESSGE_MAP.put(E_APP_PAY_PASSWORD_ERROR, "支付密码错误");
		ERROR_MESSGE_MAP.put(E_ADD_FIRST, "请先入建中信商户");
		
		ERROR_MESSGE_MAP.put(E_ID_CARD_F_ERROR, "身份证正面识别失败");
		ERROR_MESSGE_MAP.put(E_ID_CARD_B_ERROR, "身份证反面识别失败");
		ERROR_MESSGE_MAP.put(E_UNBOUND_SUCCESS, "解绑成功");
		
	}

	public static enum ResponCodeEnum {
		DEAL_IN_PROGRESS("1000", "处理中"), DEAL_SUCCESS("1001", "成功"), DEAL_FAIL("1002", "失败"), DEAL_SEALS_RETURN("1003",
				"已退货"),DEAL_UNPAY("1004","订单未支付"),DEAL_CLOSED("1005","订单已关闭");

		private String code;
		private String name;

		private ResponCodeEnum(String code, String name) {
			this.code = code;
			this.name = name;
		}

		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		public static String getNameByCode(String code) {
			for (ResponCodeEnum eopen : ResponCodeEnum.values()) {
				if (eopen.code.equals(code)) {
					return eopen.name;
				}
			}
			return "";
		}

	}

	// 交易子类型枚举
	public static enum PayTypeEnum {
		PAYBYCARD("00", "刷卡"), PAYBYWX("01", "微信"), PAYBYALIPAY("02", "支付宝"), PAYBY_JUHUIFEN("03",
				"聚惠分"), PAYBYOTHER("06", "其他");

		private String code;
		private String name;

		private PayTypeEnum(String code, String name) {
			this.code = code;
			this.name = name;
		}

		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		public static String getNameByCode(String code) {
			for (PayTypeEnum eopen : PayTypeEnum.values()) {
				if (eopen.code.equals(code)) {
					return eopen.name;
				}
			}
			return "";
		}
	}

	// 交易子类型枚举
	public static enum WeChatPayTypeEnum {
		GZH("GZH", "010131"), // 微信公众号
		XCX("XCX", "010134"), // 微信小程序
		QQPAY("QQPAY", "010502");// QQ公众号

		private String type;
		private String code;

		private WeChatPayTypeEnum(String type, String code) {
			this.type = type;
			this.code = code;
		}

		/**
		 * @return the code
		 */
		public String getType() {
			return code;
		}

		/**
		 * @return the name
		 */
		public String getCode() {
			return code;
		}

		public static String getCodeByType(String type) {
			for (WeChatPayTypeEnum eopen : WeChatPayTypeEnum.values()) {
				if (eopen.type.equals(type)) {
					return eopen.code;
				}
			}
			return GZH.code;// 默认微信公众号
		}
	}
}
