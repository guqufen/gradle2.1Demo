package net.fnsco.order.api.constant;

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
    public static enum PayTypeEnum{
        PAYBYCARD("00","刷卡"),PAYBYWX("01","微信"),PAYBYALIPAY("02","支付宝"),PAYBYOTHER("06","其他");
        
        private String code;
        private String name;
        private PayTypeEnum(String code, String name){
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
}
