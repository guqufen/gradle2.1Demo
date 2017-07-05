package net.fnsco.service.comm;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

public class ServiceConstant {
    public static String              STR_1            = "1";
    public static String              STR_0            = "0";

    //支付类型
    public static Map<String, String> PAY_TYPE_MAP     = Maps.newHashMap();
    //支付子类型
    public static Map<String, String> PAY_SUB_TYPE_MAP = Maps.newHashMap();
    /*0-刷卡
    1-微信
    2-支付宝
    3-银联钱包
    4-百度钱包
    5-京东钱包
    6-拉卡拉钱包*/
    static {
        PAY_TYPE_MAP.put("0", "00");
        PAY_TYPE_MAP.put("1", "01");
        PAY_TYPE_MAP.put("2", "01");
        PAY_TYPE_MAP.put("3", "01");
        PAY_TYPE_MAP.put("4", "01");
        PAY_TYPE_MAP.put("5", "01");
        PAY_TYPE_MAP.put("6", "01");
        PAY_SUB_TYPE_MAP.put("0", "00");
        PAY_SUB_TYPE_MAP.put("1", "01");
        PAY_SUB_TYPE_MAP.put("2", "02");
        PAY_SUB_TYPE_MAP.put("3", "03");
        PAY_SUB_TYPE_MAP.put("4", "04");
        PAY_SUB_TYPE_MAP.put("5", "05");
        PAY_SUB_TYPE_MAP.put("6", "06");
    }

    public static enum PayTypeEnum {
                                    CARD_PAY("00", "刷卡支付"), CODE_PAY("01", "扫码支付");
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

    public static enum PaySubTypeEnum {
                                       SK_PAY("00", "刷卡支付"), WX_PAY("01", "微信支付"), ZFB_PAY("02", "支付宝支付"), YL_PAY("02", "银联支付");
        private String code;
        private String name;

        private PaySubTypeEnum(String code, String name) {
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

    public static enum TradeStateEnum {
                                       SUCCESS("1001", "成功"), FAIL("1002", "失败");
        private String code;
        private String name;

        private TradeStateEnum(String code, String name) {
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
            for (TradeStateEnum eopen : TradeStateEnum.values()) {
                if (eopen.code.equals(code)) {
                    return eopen.name;
                }
            }
            return "";
        }
    }

    //交易类型1消费2撤销
    public static enum TradeTypeEnum {
                                      CONSUMER("1", "消费"), CANCEL("2", "撤销");
        private String code;
        private String name;

        private TradeTypeEnum(String code, String name) {
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
            for (TradeTypeEnum eopen : TradeTypeEnum.values()) {
                if (eopen.code.equals(code)) {
                    return eopen.name;
                }
            }
            return "";
        }
    }

}
