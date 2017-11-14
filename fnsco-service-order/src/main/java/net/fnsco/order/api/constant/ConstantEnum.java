package net.fnsco.order.api.constant;

import java.util.Map;

import com.beust.jcommander.internal.Maps;

public class ConstantEnum {

    public static enum AppTypeEnum {
                                    SQB("SQB", "数钱吧"), LKL("LKL", "拉卡拉");
        private String code;
        private String name;

        private AppTypeEnum(String code, String name) {
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
            for (AppTypeEnum eopen : AppTypeEnum.values()) {
                if (eopen.code.equals(code)) {
                    return eopen.name;
                }
            }
            return "";
        }
    }

    public static enum AppOsTypeEnum {
                                      ANDROID("1", "安卓"), IOS("2", "IOS");
        private String code;
        private String name;

        private AppOsTypeEnum(String code, String name) {
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
            for (AppOsTypeEnum eopen : AppOsTypeEnum.values()) {
                if (eopen.code.equals(code)) {
                    return eopen.name;
                }
            }
            return "";
        }
    }

    public static enum AuthorTypeEnum {
                                       SHOPOWNER("1", "店长"), CLERK("2", "店员");
        private String code;
        private String name;

        private AuthorTypeEnum(String code, String name) {
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
            for (AuthorTypeEnum eopen : AuthorTypeEnum.values()) {
                if (eopen.code.equals(code)) {
                    return eopen.name;
                }
            }
            return "";
        }
    }

    //支付方式枚举
    public static enum PayTypeEnum {
                                    PAYBYCARD("00", "刷卡"), PAYBYWX("01", "微信"), PAYBYALIPAY("02", "支付宝"), PAYBYOTHER("06", "其他");

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

    //贷记卡和借记卡
    public static enum DcTypeEnum {
                                   DOMESTICDEBITCARD("00", "境内借记卡"), INLANDCREDITCARD("01", "境内贷记卡"), OVERSEASDEBITCARD("60", "境外借记卡"), OVERSEASCREDITCARD("61", "境外贷记卡");

        private String code;
        private String name;

        private DcTypeEnum(String code, String name) {
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
            for (DcTypeEnum eopen : DcTypeEnum.values()) {
                if (eopen.code.equals(code)) {
                    return eopen.name;
                }
            }
            return "";
        }
    }

    //贷记卡和借记卡
    public static enum RespCodeEnum {
                                     HANDLING("1000", "处理中"), SUCCESS("1001", "成功"), FAIL("1002", "失败"), REFUNDS("1003", "退款");

        private String code;
        private String name;

        private RespCodeEnum(String code, String name) {
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
            for (RespCodeEnum eopen : RespCodeEnum.values()) {
                if (eopen.code.equals(code)) {
                    return eopen.name;
                }
            }
            return "";
        }
    }
    
    public static enum IntegralTypeEnum {
		/**
		 * CODE_POS01("001", "0") 001：表示CODE
		 * 0：表示类型，0-不封顶无条件；1-表示积分封顶，10分；2-表示次数，当天第1次有效；3-表示前3次
		 */
		CODE_POS01("001", "1", "POS收银交易金额>10"), 
		CODE_POS02("002", "1", "POS收银交易金额>=500"), 
		CODE_YQ01("003", "2", "邀请新商家分享连接-未知"), 
		CODE_YQ02("004", "0", "邀请新商家成功"), 
		CODE_SQ("005", "0", "申请新机新增POS机"), 
		CODE_LR("006", "3", "录入店铺信息新增店铺，前三次有效"), 
		CODE_JZ("007", "1", "每日记账，最多10分"),
		CODE_YQ03("008", "2", "邀请新商家分享连接-微信聊天"),
		CODE_YQ04("009", "2", "邀请新商家分享连接-微信朋友圈"),
		CODE_YQ05("010", "2", "邀请新商家分享连接-QQ聊天"),
		CODE_YQ06("011", "2", "邀请新商家分享连接-QQ空间"),
		CODE_YQ07("012", "2", "邀请新商家分享连接-新浪微博"),
    	INTEGRAL_TYPE("11","11","11");

		private String code;
		private String type;
		private String name;
//		public static String INTEGRAL_TYPE="11";

		private IntegralTypeEnum(String code, String type, String name) {
			this.code = code;
			this.name = name;
			this.type = type;
		}

		/**
		 * @return the code
		 */
		public String getCode() {
			return code;
		}

		public String getType() {
			return type;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		public static String getTypeByCode(String code) {

			for (IntegralTypeEnum eopen : IntegralTypeEnum.values()) {
				if (eopen.code.equals(code)) {

					return eopen.type;
				}
			}
			return null;
		}
		
		public static String getNameByCode(String code) {

			for (IntegralTypeEnum eopen : IntegralTypeEnum.values()) {
				if (eopen.code.equals(code)) {

					return eopen.type;
				}
			}
			return null;
		}
	}
}
