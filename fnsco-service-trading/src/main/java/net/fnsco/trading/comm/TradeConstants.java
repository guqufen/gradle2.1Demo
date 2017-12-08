package net.fnsco.trading.comm;

import java.util.Map;

import com.google.common.collect.Maps;

public class TradeConstants {
    //个人用户充值使用秘钥
    public static final String        RECHANGE_AES_KEY = "a8bc3defec5916b469d9c1fa095b5b43";
    //交易类型
    public static Map<String, String> TXT_TYPE_MAP     = Maps.newHashMap();
    static {
        TXT_TYPE_MAP.put("012001", "1");//消费交易
        TXT_TYPE_MAP.put("012002", "2");//消费撤销交易
        TXT_TYPE_MAP.put("012003", "1");//退货交易
        TXT_TYPE_MAP.put("012004", "1");//预授权交易
        TXT_TYPE_MAP.put("012005", "2");//预授权撤销交易
        TXT_TYPE_MAP.put("012006", "1");//预授权完成交易
        TXT_TYPE_MAP.put("012007", "1");//风险信息上送交易
        TXT_TYPE_MAP.put("012008", "1");//调账交易
        TXT_TYPE_MAP.put("012009", "1");//脱机消费交易
        TXT_TYPE_MAP.put("012010", "1");//消费冲正交易
        TXT_TYPE_MAP.put("012011", "1");//消费撤销冲正交易
        TXT_TYPE_MAP.put("012012", "1");//预授权冲正交易
        TXT_TYPE_MAP.put("012013", "1");//预授权撤销冲正交易
        TXT_TYPE_MAP.put("012014", "1");//预授权完成冲正交易
        TXT_TYPE_MAP.put("012015", "1");//预授权完成交易
        TXT_TYPE_MAP.put("012016", "1");//TC上送交易
        TXT_TYPE_MAP.put("012017", "1");//脚本执行结果通知交易
        TXT_TYPE_MAP.put("012018", "2");//预授权完成撤销交易
        TXT_TYPE_MAP.put("012019", "1");//POS卡卡转账交易
        TXT_TYPE_MAP.put("012020", "2");//POS卡卡转账撤销交易
        TXT_TYPE_MAP.put("012021", "1");//分期付款交易
        TXT_TYPE_MAP.put("012022", "1");//POS凭条电子化获取交易
        TXT_TYPE_MAP.put("012023", "1");//机场VIP查询交易
        TXT_TYPE_MAP.put("012024", "1");//分期付款冲正交易
        TXT_TYPE_MAP.put("012025", "1");//查询签购单交易
        TXT_TYPE_MAP.put("012031", "1");//报账通POS户名回显交易
        TXT_TYPE_MAP.put("012040", "1");//自动冲正POS类交易
        TXT_TYPE_MAP.put("012041", "1");//地区银联接入离线预授权完成交易
        TXT_TYPE_MAP.put("012043", "2");//消费撤消冲正交易
        TXT_TYPE_MAP.put("012044", "2");//预授权完成撤消冲正交易
        TXT_TYPE_MAP.put("012045", "1");//银联后台脚本通知交易
        TXT_TYPE_MAP.put("012046", "1");//指定账户圈存、现金充值、现金充值撤销冲正交易
        TXT_TYPE_MAP.put("012047", "1");//非指定账户圈存冲正交易
        TXT_TYPE_MAP.put("012050", "1");//BLZF签到
        TXT_TYPE_MAP.put("012052", "1");//EMV更新公钥
        TXT_TYPE_MAP.put("012053", "1");//EMV更新交易参数
        TXT_TYPE_MAP.put("012060", "1");//签到交易
        TXT_TYPE_MAP.put("012061", "1");//签退交易
        TXT_TYPE_MAP.put("012062", "1");//EMV公钥下载
        TXT_TYPE_MAP.put("012063", "1");//EMV参数下载
        TXT_TYPE_MAP.put("012064", "1");//批上送交易
        TXT_TYPE_MAP.put("012065", "1");//结算二交易
        TXT_TYPE_MAP.put("012066", "1");//POS查询交易
        TXT_TYPE_MAP.put("012070", "1");//指定账户圈存交易
        TXT_TYPE_MAP.put("012071", "1");//指定账户圈存冲正交易
        TXT_TYPE_MAP.put("012072", "1");//非指定账户圈存交易
        TXT_TYPE_MAP.put("012073", "1");//非指定账户圈存冲正交易
        TXT_TYPE_MAP.put("012074", "1");//现金充值交易
        TXT_TYPE_MAP.put("012075", "1");//现金充值冲正交易
        TXT_TYPE_MAP.put("012076", "2");//现金充值撤销交易
        TXT_TYPE_MAP.put("012077", "1");//现金充值撤销冲正交易
        TXT_TYPE_MAP.put("012080", "1");//助农取款交易
        TXT_TYPE_MAP.put("012081", "1");//助农取款冲正交易
        TXT_TYPE_MAP.put("012083", "1");//积分宝后台发送交易
        TXT_TYPE_MAP.put("012090", "1");//自动冲正新增轮询交易
        TXT_TYPE_MAP.put("012091", "1");//退货校验交易
        TXT_TYPE_MAP.put("012093", "1");//退货-UI交易
        TXT_TYPE_MAP.put("012096", "1");//预授权完成-UI发起交易
        TXT_TYPE_MAP.put("012099", "1");//脱机补单-UI交易
        TXT_TYPE_MAP.put("012160", "1");//MPOS机构签到
        TXT_TYPE_MAP.put("012161", "1");//MPOS大额消费
        TXT_TYPE_MAP.put("012201", "1");//冲正查询补打交易
        TXT_TYPE_MAP.put("012204", "1");//大额转账
        TXT_TYPE_MAP.put("012205", "1");//交易明细查询
        TXT_TYPE_MAP.put("012206", "1");//代付确认
        TXT_TYPE_MAP.put("112204", "1");//大额转账消费
        TXT_TYPE_MAP.put("112205", "1");//大额转账代付
        TXT_TYPE_MAP.put("112206", "1");//银联全渠道交易结果查询
        TXT_TYPE_MAP.put("012401", "1");//扫码支付(微信、支付宝等）
        TXT_TYPE_MAP.put("012402", "2");//扫码撤销(微信、支付宝等）
        TXT_TYPE_MAP.put("012403", "2");//扫码退货
        TXT_TYPE_MAP.put("012411", "1");//主扫订单生成
        TXT_TYPE_MAP.put("012412", "2");//主扫订单关闭
        TXT_TYPE_MAP.put("012413", "2");//主扫订单退货
        TXT_TYPE_MAP.put("012466", "1");//微信被扫交易查询
        TXT_TYPE_MAP.put("012467", "1");//主扫订单查询(主扫退货查询)
        TXT_TYPE_MAP.put("012493", "2");//页面扫码退货
        TXT_TYPE_MAP.put("012501", "1");//银联增值服务消费
        TXT_TYPE_MAP.put("012502", "2");//银联增值服务消费撤销
        TXT_TYPE_MAP.put("012503", "1");//银联增值服务退货
        TXT_TYPE_MAP.put("012510", "1");//银联增值服务消费冲正
        TXT_TYPE_MAP.put("012511", "1");//银联增值服务消费撤销冲正
        TXT_TYPE_MAP.put("012566", "1");//银联增值服务查询
        TXT_TYPE_MAP.put("021001", "1");//订单查询交易
        TXT_TYPE_MAP.put("021002", "1");//交易确认
        TXT_TYPE_MAP.put("022060", "1");//POS签到
        TXT_TYPE_MAP.put("022061", "1");//预留POS签退
        TXT_TYPE_MAP.put("022001", "1");//POS消费
        TXT_TYPE_MAP.put("022010", "1");//POS消费冲正
        TXT_TYPE_MAP.put("022002", "2");//POS消费撤销
        TXT_TYPE_MAP.put("022011", "1");//POS消费撤销冲正
        TXT_TYPE_MAP.put("022003", "1");//POS消费退货
        TXT_TYPE_MAP.put("022004", "1");//POS预授权
        TXT_TYPE_MAP.put("022012", "1");//POS预授权冲正
        TXT_TYPE_MAP.put("022005", "2");//POS预授权撤销
        TXT_TYPE_MAP.put("022064", "1");//POS批上送
        TXT_TYPE_MAP.put("022009", "1");//POS批结算
        TXT_TYPE_MAP.put("022066", "1");//POS查询余额
        TXT_TYPE_MAP.put("022071", "1");//POS批上送退货
        TXT_TYPE_MAP.put("031003", "1");//现金积分
        TXT_TYPE_MAP.put("015001", "1");//一键激活
        TXT_TYPE_MAP.put("015002", "1");//反激活
        TXT_TYPE_MAP.put("091001", "1");//签到
        TXT_TYPE_MAP.put("091002", "1");//代付
        TXT_TYPE_MAP.put("091003", "1");//代付查询
        TXT_TYPE_MAP.put("091010", "1");//代付确认
        TXT_TYPE_MAP.put("091011", "1");//代付交易查询
        TXT_TYPE_MAP.put("016001", "1");//行业卡签到
        TXT_TYPE_MAP.put("016002", "1");//行业卡更新发卡方","1");//
        TXT_TYPE_MAP.put("016003", "1");//行业卡更新卡表
        TXT_TYPE_MAP.put("016004", "1");//行业卡结算
        TXT_TYPE_MAP.put("016005", "1");//行业卡激活
        TXT_TYPE_MAP.put("016006", "1");//行业卡积分
        TXT_TYPE_MAP.put("016007", "1");//行业卡积分兑换
        TXT_TYPE_MAP.put("016008", "1");//行业卡管理员改密","1");//
        TXT_TYPE_MAP.put("016009", "2");//行业卡积分撤销
        TXT_TYPE_MAP.put("016010", "1");//行业卡积分兑换撤销
        TXT_TYPE_MAP.put("016011", "1");//行业卡会员卡转账","1");//
        TXT_TYPE_MAP.put("016012", "1");//行业卡查询
        TXT_TYPE_MAP.put("016013", "1");//行业卡联机改密
        TXT_TYPE_MAP.put("016014", "1");//行业卡联机消费
        TXT_TYPE_MAP.put("016015", "2");//行业卡消费撤销
        TXT_TYPE_MAP.put("016016", "1");//行业卡现金充值
        TXT_TYPE_MAP.put("016017", "1");//行业卡联机续期
        TXT_TYPE_MAP.put("016021", "1");//行业卡银行卡充值","1");//
        TXT_TYPE_MAP.put("016023", "1");//行业卡会员卡充值","1");//
        TXT_TYPE_MAP.put("016024", "1");//行业卡交易查询
        TXT_TYPE_MAP.put("016026", "1");//行业卡密码重置
        TXT_TYPE_MAP.put("016040", "1");//行业卡冲正
        TXT_TYPE_MAP.put("016106", "1");//行业卡会员积分冲正
        TXT_TYPE_MAP.put("016107", "1");//行业卡积分兑换冲正
        TXT_TYPE_MAP.put("016109", "1");//行业卡会员积分撤销冲正
        TXT_TYPE_MAP.put("016110", "1");//行业卡积分兑换撤销冲正
        TXT_TYPE_MAP.put("016111", "1");//行业卡会员卡转账冲正","1");//
        TXT_TYPE_MAP.put("016114", "1");//行业卡联机消费冲正
        TXT_TYPE_MAP.put("016115", "1");//行业卡联机消费撤销冲正
        TXT_TYPE_MAP.put("016116", "1");//行业卡现金充值冲正
        TXT_TYPE_MAP.put("016123", "1");//行业卡会员卡充值冲正","1");//
        TXT_TYPE_MAP.put("999901", "1");//拉卡拉便利支付签到
        TXT_TYPE_MAP.put("999903", "1");//拉卡拉信用卡还款
        TXT_TYPE_MAP.put("999904", "1");//拉卡拉手机充值
        TXT_TYPE_MAP.put("999905", "1");//拉卡拉账单号查询
        TXT_TYPE_MAP.put("999906", "1");//拉卡拉账单号支付
        TXT_TYPE_MAP.put("999907", "1");//拉卡拉信用卡还款确认
        TXT_TYPE_MAP.put("999908", "1");//TC上送
        TXT_TYPE_MAP.put("999911", "1");//拉卡拉POS贷款合同查询
        TXT_TYPE_MAP.put("999912", "1");//拉卡拉POS贷刷卡还款

    }

    public static enum PayTypeEnum {
                                    WX_PAY("41", "微信支付"), ZFB_PAY("42", "支付宝支付");
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

    public static enum ChannelTypeEnum {
                                        AN_PAY("00", "爱农渠道"), PF_PAY("01", "浦发渠道");
        private String code;
        private String name;

        private ChannelTypeEnum(String code, String name) {
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
            for (ChannelTypeEnum eopen : ChannelTypeEnum.values()) {
                if (eopen.code.equals(code)) {
                    return eopen.name;
                }
            }
            return "";
        }
    }

    /**
     * 中信银行微信/支付宝被扫消息类型
     * @author Administrator
     *
     */
    public static enum ZxyhPassivePayType {
                                           BS_PAY_TYPE("48", "被扫支付类型码"), BS_CX_TYPE("38", "被扫结果查询类型码"), BS_AUTH_TYPE("07", "微信支付授权码查询类型码");

        private String code;
        private String name;

        private ZxyhPassivePayType(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 中信银行微信/支付宝被扫交易码
     * @author Administrator
     *
     */
    public static enum ZxyhPassivePayCode {

                                           WX_BS_PAY("481000", "微信被扫支付"), ZFB_BS_PAY("481003", "支付宝被扫支付"), WX_BS_CX("381000", "微信交易结果状态查询"), ZFB_BS_CX("381003",
                                                                                                                                                       "支付宝交易结果状态查询"), WX_AUTH_CK("070101",
                                                                                                                                                                                  "微信支付授权码查询交易码");

        private String code;
        private String name;

        private ZxyhPassivePayCode(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

    //00pos机01app02台码
    public static enum PayMediumEnum {
                                      POS("00", "pos机"), APP("01", "app"), QR("02", "台码");
        private String code;
        private String name;

        private PayMediumEnum(String code, String name) {
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
}