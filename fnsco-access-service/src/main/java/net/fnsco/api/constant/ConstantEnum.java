package net.fnsco.api.constant;

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
}