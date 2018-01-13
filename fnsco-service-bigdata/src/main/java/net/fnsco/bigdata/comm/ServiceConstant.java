package net.fnsco.bigdata.comm;

import java.util.Map;

import com.google.common.collect.Maps;

public class ServiceConstant {
    public static String              STR_1            = "1";
    public static String              STR_0            = "0";

    //支付类型
    public static Map<String, String> PAY_TYPE_MAP     = Maps.newHashMap();
    
    public static Map<String, String> LKL_PAY_TYPE_MAP     = Maps.newHashMap();
    
    public static Map<String, String> LKL_PAY_SUB_TYPE_MAP     = Maps.newHashMap();
    
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
        /**
         *	00 借记卡
			01 贷记卡
			91 微信
			92 支付宝
			93 百度钱包
			96 拉卡拉钱包
			04 未知卡类型

         */
        LKL_PAY_TYPE_MAP.put("00", "00");
        LKL_PAY_TYPE_MAP.put("01", "00");
        LKL_PAY_TYPE_MAP.put("91", "01");
        LKL_PAY_TYPE_MAP.put("92", "01");
        LKL_PAY_TYPE_MAP.put("93", "01");
        LKL_PAY_TYPE_MAP.put("96", "01");
        LKL_PAY_TYPE_MAP.put("04", "01");
        
        
        LKL_PAY_SUB_TYPE_MAP.put("00", "00");
        LKL_PAY_SUB_TYPE_MAP.put("01", "00");
        LKL_PAY_SUB_TYPE_MAP.put("91", "01");
        LKL_PAY_SUB_TYPE_MAP.put("92", "02");
        LKL_PAY_SUB_TYPE_MAP.put("93", "09");
        LKL_PAY_SUB_TYPE_MAP.put("96", "09");
        LKL_PAY_SUB_TYPE_MAP.put("04", "09");
        
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

    /*0-刷卡
    1-微信
    2-支付宝
    3-银联钱包
    4-百度钱包
    5-京东钱包
    6-拉卡拉钱包*/
    public static enum PaySubTypeAllEnum {
                                       SK_PAY("00", "刷卡支付"), WX_PAY("01", "微信支付"),
                                       ZFB_PAY("02", "支付宝支付"), YL_PAY("03", "银联钱包"),
                                       BD_PAY("04", "百度钱包"),JD_PAY("05", "京东钱包"),
                                       LKL_PAY("06", "拉卡拉钱包"),QQ_PAY("07","QQ钱包"),
                                       QT_PAY("08", "其他(无卡或者现金)");
        private String code;
        private String name;

        private PaySubTypeAllEnum(String code, String name) {
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
            for (PaySubTypeAllEnum eopen : PaySubTypeAllEnum.values()) {
                if (eopen.code.equals(code)) {
                    return eopen.name;
                }
            }
            return "其它";
        }
    }

    public static enum PaySubTypeEnum {
                                       SK_PAY("00", "刷卡支付"), WX_PAY("01", "微信支付"), ZFB_PAY("02", "支付宝支付"), FSF_PAY("03", "分闪付"),LKL_PAY("06", "拉卡拉钱包");
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
            for (PaySubTypeEnum eopen : PaySubTypeEnum.values()) {
                if (eopen.code.equals(code)) {
                    return eopen.name;
                }
            }
            return "";
        }
    }

    public static enum TradeStateEnum {
                                       SUCCESS("1001", "成功"), FAIL("1002", "失败"), ING("1111", "进行中"), START("0000", "接受通知成功");
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
