package net.fnsco.trading.service.pay.zxyh;

public class Mach {
    private String mchtNm;           //     商户全称    String  申请类型=1 时，必填，20位以内   
    private String mchtCnAbbr;       // 商户简称    String  申请类型=1 时，必填，12位以内   
    private String manageScope;      //经营范围    String  非必填 
    private String etpsAttr;         //  商户性质    String  必填  1-政府机构
    //            2-国营企业
    //            3-私营企业
    //            4-外资企业
    //            5-个体工商户
    //            7-事业单位
    private String etpsTp;           // 商户种类    String  必填  1-企业
    //            2-个体工商户
    //            3-个人
    private String mchtTel;          // 商户电话    String(18)  必填  18位以内数字
    private String contact;          // 联系人姓名   String  必填  
    private String commTel;          // 联系人电话   String(18)  必填  18位以内数字
    private String commEmail;        //  联系人邮箱   String  必填  正确邮箱格式
    private String licenceNo;        //  营业执照编号  String  etpsTp为1或2时必填   
    private String taxNo;            //  税务证编号   String(60)  非必填 
    private String bankLicenceNo;    //  组织机构代码  String(60)  非必填 
    private String manager;          //法人姓名    String(20)  必填20位以内
    private String identityNo;       // 法人身份证   String  必填  正确身份证格式
    private String provCode;         //   商户所属省   String  必填  详见省市区表
    private String cityCode;         //   商户所属市   String  必填  详见省市区表
    private String areaCode;         //   商户所属区   String  必填  详见省市区表
    private String addr;             //   详细地址    String(20)  必填  20位以内
    private String memo;             //   备注  String(256)     
    private String WXActive;         //   是否开通微信  String  必填  Y-是 N-否
    private String mainMchtTp;       //  微信主商户号类型    String  WXActive=Y时必填   00-医疗教育类 01-公共事业类 02-其他类 
    //            03-WAP
    //            olCode1 微信支付方式  String  WXActive=Y时必填   支付方式编码，可填多个，以“|”分割
    //            01-被扫  
    //            02-主扫  
    //            03-公众号支付
    //            06-H5
    private String subAppid;         //   关联公众号   String  当选择了微信公众号支付时(olcode1=03)必填  绑定商户公众号对应的appid
    //            示例:wx931386123456789e
    //            jsapiPath   支付授权目录  String  当选择了微信公众号支付时(olcode1=03)必填  商户公众号api支付授权目录，要求符合URI格式规范。用”|”隔开，填1到5个目录。
    //            示例:http://www.qq.com/wechat1/|http://www.qq.com/wechat2/|http://www.qq.com/wechat3/
    //            subscribeAppid  推荐关注公众号 String  非必填 微信分配的服务商公众号APPID,可与subAppid相同。
    //            示例：wx931386123456789e
    private String qGroupId;         //  微信一级类目  String  WXActive=Y时必填   参见《微信类目表》 
    private String categroryId;      //微信二级类目  String  WXActive=Y时必填   参见《微信类目表》 
    private String feeRate;          //微信手续费(%)    String  WXActive=Y时必填，  不能低于平台商户成本费率。数值范围(10以内最多3位小数，0至9.999)。如 0.1%的费率，填写0.1
    private String settleCycle;      //微信结算周期  String  WXActive=Y时必填   填写字母，T1、D1、D0
    private String ZFBActive;        //  是否开通支付宝 String  必填  Y-是N-否 
    private String mainMchtTpA;      //支付宝主商户号类型   String  ZFBActive=Y时必填  D1-普通类
    private String olCodeA;          //支付宝支付方式 String  ZFBActive=Y时必填  支付方式编码，可填多个，以“|”分割；
    //            D1-二清被扫 
    //            D2-二清主扫
    private String categIdC;         //   支付宝一级类目 String  ZFBActive=Y时必填  参见《支付宝类目表》 
    private String feeRateA;         //   支付宝商户手续费(%) String  ZFBActive=Y时必填  不能低于平台商户成本费率。数值范围(10以内最多3位小数，0至9.999)) 如 0.1%的费率，填写0.1
    private String settleCycleA;     //   支付宝结算周期 String  ZFBActive=Y时必填  填写字母，T1、D1、D0
    private String isISV;            //  是否为ISV商户    String  ZFBActive=Y时必填  填写数字，1=是，0=否
    private String isvpid;           // ISV商户请添PID  String  isISV为1时必填  
    private String BDActive;         //   是否开通百度  String  必填  Y-是 N-否
    private String mainMchtTpBaid;   // 百度主商户号类型    String  BDActive=Y时必填   21-综合类
    private String olCodeBaid;       // 百度支付方式  String  BDActive=Y时必填   支付方式编码，可填多个，以“|”分割；
    //            E1-被扫 
    //            E2-主扫 
    private String categIdABaid;     //  类目一 String  BDActive=Y时必填   参见《百度类目表》 
    private String categIdBBaid;     //  类目二 String  BDActive=Y时必填   参见《百度类目表》 
    private String categIdCBaid;     //   类目三 String  BDActive=Y时必填   参见《百度类目表》
    private String feeRateBaid;      //百度钱包商户手续费(%)    String  BDActive=Y时必填   不能低于平台商户成本费率。数值范围(10以内最多3位小数，0至9.999)) 如 0.1%的费率，填写0.1
    private String settleCycleBaid;  // 百度结算周期  String  BDActive=Y时必填   填写字母，T1、D1、D0 
    private String secMerId;         //  独立商户号   String  申请类型=2 or 3 or 4 时，必填   
    private String merId;            // 平台商户号   String  申请类型=1 时必填  填上级平台商户的平台商户号
    private String thirdMchtNo;      // 第三方平台子商户号   String  申请类型=1 时必填  
    private String isOrNotZxMchtNo;  // 是否中信银行账户    String  申请类型=1 时必填  填写字母
    //Y-是，N-否
    private String acctType;         //   账户类型    String  申请类型=1 时必填  填写数字
    //1-企业账户，2-个人账 户。
    private String settleAcctNm;     //   结算开户名   String  申请类型=1 时必填  
    private String settleAcct;       //  结算账号    String  申请类型=1 时必填  40位以内数字
    private String accIdeNo;         //   开户身份证号  String  账户类型=2 时必填  正确身份证格式
    private String accPhone;         //   开户手机号   String  账户类型=2 时必填  11位以内数字
    private String settleBankAllName;//  收款银行全称  String  申请类型=1 时，必填 
    private String settleBankCode;   // 收款银行行号  String  非中信账户必填 12 位数字联行号
    private String apType;           //  申请类型    String  必填  1-新增 
    private String signMethod;       //  验签方式    String  必填  02-MD5  03-RSA
    private String signAture;        //  签名  String  必填  填写对报文摘要的签名

}
