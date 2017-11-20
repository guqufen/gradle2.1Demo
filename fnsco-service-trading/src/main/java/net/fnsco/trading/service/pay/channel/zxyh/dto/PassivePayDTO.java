package net.fnsco.trading.service.pay.channel.zxyh.dto;

/*2.1.2   被扫订单支付（消费）交易接口
2.1.2.1 功能介绍
用户提供微信或支付宝App端的二维码信息，商户扫描用户二维码信息通过支付平台提供的二维码被扫支付的过程。
*/
public class PassivePayDTO {
    private String signMethod;                //签名方法 M   String(2)   签名方式：
    //    02：MD5
    //    03：RSA
    private String stdmsgtype;                //消息类型  M   String(4)   "48"
    private String stdprocode;                //交易码  M   String(6)   481000：微信消费 481003：支付宝二清消费
    private String std400chnl;                //接入渠道  M   String(4)   6005 持牌机构MIS接入
    private String stdpaytype;                //接入支付类型  M   String(2)   02：接口支付。
    private String stdmercno;                 //商户编号   M   String(15)  普通商户或一级商户的商户号，指中信内部15位商户号
    private String stdmercno2;                //二级商户号  C   String(20)  使用分账功能时上传，是与stdmercno关联的分账子商户号
    private String stdtermid;                 // 终端编号  M   String(8)   终端编号
    private String stdorderid;                //商户订单号   M   String(32)  商户系统内部的订单号,32 个字符内、可包含字母, 确保在商户系统唯一
    private String stdbegtime;                //交易起始时间  M   String(14)  商户上送交易时间，格式
    //    为[yyyyMMddHHmmss] ,
    //    如2009年12月25日9点10分10秒
    //    表示为20091225091010
    private String std400memo;                //商品描述  M   String(127) 商品描述
    private String stdtranamt;                //交易金额  M   String(12)  订单总金额(交易单位为分，例:1.23元=123)，
    //    只能整数
    //    若交易码为信e通消费，且消费类型为
    //    01则代表权益次数，消费类型为
    //    02则代表积分值
    private String stddiscamt;                //不可优惠金额  C   String(12)  支付宝不可优惠金额支付宝交易本字段必填。
    private String stdtrancur;                //  交易币种 M   String(3)   默认是156：人民币
    private String stdauthid;                 //授权码    M   String((128)    扫码支付授权码，设备读取用户微信信息
    private String stdqytype;                 //消费类型  C   String(2)   01:权益02:积分
    private String accountFlag;               //分账标识 C   String(1)   使用分账功能时上传，当值为Y时，表示交易为分账交易，分账子商户号stdmercno2必传 Y-分账其它或不传-不分账
    private String secMerFeeRate;             //分账子商户交易费率  C   String(16)  使用分账功能时上传，是分账子商户stdmercno2的交易费率（若费率是0.3%，此值传0.003，小数点后最多四位），此费率值不能比平台商户stdmercno的费率低
    private String signAture;                 //签名   M   String(1024)    填写对报文摘要的签名
    private String needBankType;              //  返回bankType标识  C   String(1)   当值为“Y”时，支付成功、查询支付成功的订单会返回付款银行类型bankType
    private String independentTransactionFlag;//独立商户交易标识  C   String(1)   传“Y”，并且accountFlag也传“Y”时，即代表为独立商户交易

}
