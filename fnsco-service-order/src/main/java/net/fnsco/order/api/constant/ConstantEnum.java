package net.fnsco.order.api.constant;

import java.util.Map;

import com.beust.jcommander.internal.Maps;

public class ConstantEnum {
    public static Map<String, String> RESP_CODE_MAP = Maps.newHashMap();
    static {
        //0 未支付 1支付成功 2支付失败 3已退货
        //RESP_CODE_MAP.put("0", "1000");
        RESP_CODE_MAP.put("1", "1001");
        RESP_CODE_MAP.put("2", "1002");
        //RESP_CODE_MAP.put("3", "1003");
    }

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
                                     HANDLING("1000", "处理中"), SUCCESS("1001", "成功"), FAIL("1002", "失败");

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
}
