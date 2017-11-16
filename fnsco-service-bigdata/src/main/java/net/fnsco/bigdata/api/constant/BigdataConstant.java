package net.fnsco.bigdata.api.constant;

import java.util.Map;
import java.util.TreeMap;

import com.google.common.collect.Maps;

import net.fnsco.core.constants.CoreConstants;

public class BigdataConstant extends CoreConstants {
    public static String E_USERID_NULL             = "5110";
    public static String E_UPDATE_FAIL             = "5111";
    public static String WEB_MER_CHANNEL_NOTUNIQUE = "5902"; //商户渠道号和渠道组合要唯一
    public static String WEB_MER_BANKNO_UNIQUE     = "5903"; //银行卡号唯一
    public static String APP_MER_ENTITY_INNERCODE_NULL     = "5904"; //实体商户号为空
    public static String APP_MER_SHOP_INNERCODE_NULL       = "5905"; //店铺商户号为空
    static {
        ERROR_MESSGE_MAP.put(WEB_MER_CHANNEL_NOTUNIQUE, "渠道商户号和渠道类型组合需要唯一");
        ERROR_MESSGE_MAP.put(E_USERID_NULL, "入参ID为null");
        ERROR_MESSGE_MAP.put(E_UPDATE_FAIL, "更新失败");
        ERROR_MESSGE_MAP.put(WEB_MER_BANKNO_UNIQUE, "商户银行卡号录入重复");
        ERROR_MESSGE_MAP.put(APP_MER_ENTITY_INNERCODE_NULL, "实体商户号为空");
        ERROR_MESSGE_MAP.put(APP_MER_SHOP_INNERCODE_NULL, "店铺商户号为空");
    }

    /**
     * 渠道类型00拉卡拉01浦发02爱农03法奈昇
     * @desc 
     * @author   sxf
     * @version  BigdataConstant
     * @since    Ver 1.1
     * @Date	 2017年9月12日 下午12:00:43
     *
     */
    public enum ChannelTypeEnum {
                                 //渠道类型00拉卡拉01浦发02爱农03法奈昇
                                 LKL("00", "拉卡拉"), PF("01", "浦发"), AN("02", "爱农"), FNS("03", "法奈昇"), JHF("04", "聚惠分");
        private String code;
        private String name;

        ChannelTypeEnum(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public Map<String, String> getMap() {
            Map<String, String> map = new TreeMap<>();
            for (ChannelTypeEnum mType : ChannelTypeEnum.values()) {
                map.put(mType.code, mType.name);
            }
            return map;
        }

        public static String getNameByCode(String code) {
            for (ChannelTypeEnum eType : ChannelTypeEnum.values()) {
                if (eType.code.equals(code)) {
                    return eType.name;
                }
            }
            return "";
        }

    }

    //来源00拉卡拉01导入02同步
    public enum SourceEnum {
                            //来源00拉卡拉01导入02同步
                            LKL("00", "拉卡拉"), DR("01", "导入"), TB("02", "同步"), FNS("03", "法奈昇"), PF("04", "浦发");
        private String code;
        private String name;

        SourceEnum(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public Map<String, String> getMap() {
            Map<String, String> map = new TreeMap<>();
            for (SourceEnum mType : SourceEnum.values()) {
                map.put(mType.code, mType.name);
            }
            return map;
        }

        public String getNameByCode(String code) {
            for (SourceEnum eType : SourceEnum.values()) {
                if (eType.code.equals(code)) {
                    return eType.name;
                }
            }
            return "";
        }

    }

    //支付媒介00pos机01app02台码
    public enum PayMediumEnum {
                               //支付媒介00pos机01app02台码
                               POS("00", "pos机"), APP("01", "app"), FIX_QR("02", "台码");
        private String code;
        private String name;

        PayMediumEnum(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public Map<String, String> getMap() {
            Map<String, String> map = new TreeMap<>();
            for (PayMediumEnum mType : PayMediumEnum.values()) {
                map.put(mType.code, mType.name);
            }
            return map;
        }

        public static String getNameByCode(String code) {
            for (PayMediumEnum eType : PayMediumEnum.values()) {
                if (eType.code.equals(code)) {
                    return eType.name;
                }
            }
            return "";
        }

    }

    //数据来源00拉卡拉01导入02同步
    public enum DataSourceEnum {

                                LKL("00", "拉卡拉"), IMPORT("01", "导入"), SYNC("02", "同步");
        private String code;
        private String name;

        DataSourceEnum(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public Map<String, String> getMap() {
            Map<String, String> map = new TreeMap<>();
            for (DataSourceEnum mType : DataSourceEnum.values()) {
                map.put(mType.code, mType.name);
            }
            return map;
        }

        public static String getNameByCode(String code) {
            for (DataSourceEnum eType : DataSourceEnum.values()) {
                if (eType.code.equals(code)) {
                    return eType.name;
                }
            }
            return "";
        }

    }

    public static Map<Integer, String> payStateNameMap = Maps.newHashMap();
    static {
        payStateNameMap.put(0, "待付款");
        payStateNameMap.put(1, "已付款");
        payStateNameMap.put(2, "已撤单");
        payStateNameMap.put(3, "申请撤单");
        payStateNameMap.put(4, "部分退款");
        payStateNameMap.put(5, "已退款");
        payStateNameMap.put(6, "交易关闭");
        payStateNameMap.put(7, "系统保留状态");
        payStateNameMap.put(8, "退款中");
    }
    public static Map<Integer, String> payStateCodeMap = Maps.newHashMap();
    static {
        payStateCodeMap.put(0, "1002");
        payStateCodeMap.put(1, "1001");
        payStateCodeMap.put(2, "1002");
        payStateCodeMap.put(3, "1002");
        payStateCodeMap.put(4, "1002");
        payStateCodeMap.put(5, "1002");
        payStateCodeMap.put(6, "1002");
        payStateCodeMap.put(7, "1002");
        payStateCodeMap.put(8, "1002");
    }
    public static Map<String, String> RESP_CODE_MAP = Maps.newHashMap();
    static {
        //0 未支付 1支付成功 2支付失败 3已退货
        //RESP_CODE_MAP.put("0", "1000");
        RESP_CODE_MAP.put("1", "1001");
        RESP_CODE_MAP.put("2", "1002");
        //RESP_CODE_MAP.put("3", "1003");
    }
}
