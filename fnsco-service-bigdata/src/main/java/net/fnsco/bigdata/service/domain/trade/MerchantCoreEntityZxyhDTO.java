package net.fnsco.bigdata.service.domain.trade;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import net.fnsco.bigdata.service.domain.MerchantBank;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantContact;
import net.fnsco.core.base.DTO;

public class MerchantCoreEntityZxyhDTO{
//	    public void init(String pid, String merId) {
//	        this.setIsISV("1");//填写数字，1=是，0=否
//	        this.setIsvpid(pid);
//	        this.setBDActive("N");
//	        this.setApType("1");//1-新增 
//	        this.setSignMethod("02");//02-MD5  03-RSA
//	        this.setMerId(merId);
//	        this.setMainMchtTpA("D1");//D1-普通类
//	        this.setOlCodeA("D1|D2");//支付方式编码，可填多个，以“|”分割；D1-二清被扫 D2-二清主扫
//	        this.setMainMchtTp("02");//微信主商户号类型
//	        //        00-医疗教育类 01-公共事业类 02-其他类 
//	        //        03-WAP
//
//	        this.setOlCode1("01|02|03|06");//支付方式编码，可填多个，以“|”分割
//	        //        01-被扫  
//	        //        02-主扫  
//	        //        03-公众号支付
//	        //        06-H5
//
//	    }
		private String innerCode;
	    
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
	    private String olCode1;          //微信支付方式  String  WXActive=Y时必填   支付方式编码，可填多个，以“|”分割
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
	    private String jsapiPath;
	    

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

	    
	    
	    
	    private List<MerchantContact> contacts;// 关联的联系人信息
	    private List<MerchantBank> banks;// 关联的银行卡信息
	    private List<MerchantChannel> channels;//关联的通道及相关信息
	    
	    
	    public String getInnerCode() {
			return innerCode;
		}

		public void setInnerCode(String innerCode) {
			this.innerCode = innerCode;
		}
	    /**
	     * olCode1
	     *
	     * @return  the olCode1
	     * @since   CodingExample Ver 1.0
	    */

	    public String getOlCode1() {
	        return olCode1;
	    }

	    /**
	     * olCode1
	     *
	     * @param   olCode1    the olCode1 to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setOlCode1(String olCode1) {
	        this.olCode1 = olCode1;
	    }

	    /**
	     * mchtNm
	     *
	     * @return  the mchtNm
	     * @since   CodingExample Ver 1.0
	    */

	    public String getMchtNm() {
	        return mchtNm;
	    }

	    /**
	     * mchtNm
	     *
	     * @param   mchtNm    the mchtNm to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setMchtNm(String mchtNm) {
	        this.mchtNm = mchtNm;
	    }

	    /**
	     * mchtCnAbbr
	     *
	     * @return  the mchtCnAbbr
	     * @since   CodingExample Ver 1.0
	    */

	    public String getMchtCnAbbr() {
	        return mchtCnAbbr;
	    }

	    /**
	     * mchtCnAbbr
	     *
	     * @param   mchtCnAbbr    the mchtCnAbbr to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setMchtCnAbbr(String mchtCnAbbr) {
	        this.mchtCnAbbr = mchtCnAbbr;
	    }

	    /**
	     * manageScope
	     *
	     * @return  the manageScope
	     * @since   CodingExample Ver 1.0
	    */

	    public String getManageScope() {
	        return manageScope;
	    }

	    /**
	     * manageScope
	     *
	     * @param   manageScope    the manageScope to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setManageScope(String manageScope) {
	        this.manageScope = manageScope;
	    }

	    /**
	     * etpsAttr
	     *
	     * @return  the etpsAttr
	     * @since   CodingExample Ver 1.0
	    */

	    public String getEtpsAttr() {
	        return etpsAttr;
	    }

	    /**
	     * etpsAttr
	     *
	     * @param   etpsAttr    the etpsAttr to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setEtpsAttr(String etpsAttr) {
	        this.etpsAttr = etpsAttr;
	    }

	    /**
	     * etpsTp
	     *
	     * @return  the etpsTp
	     * @since   CodingExample Ver 1.0
	    */

	    public String getEtpsTp() {
	        return etpsTp;
	    }

	    /**
	     * etpsTp
	     *
	     * @param   etpsTp    the etpsTp to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setEtpsTp(String etpsTp) {
	        this.etpsTp = etpsTp;
	    }

	    /**
	     * mchtTel
	     *
	     * @return  the mchtTel
	     * @since   CodingExample Ver 1.0
	    */

	    public String getMchtTel() {
	        return mchtTel;
	    }

	    /**
	     * mchtTel
	     *
	     * @param   mchtTel    the mchtTel to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setMchtTel(String mchtTel) {
	        this.mchtTel = mchtTel;
	    }

	    /**
	     * contact
	     *
	     * @return  the contact
	     * @since   CodingExample Ver 1.0
	    */

	    public String getContact() {
	        return contact;
	    }

	    /**
	     * contact
	     *
	     * @param   contact    the contact to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setContact(String contact) {
	        this.contact = contact;
	    }

	    /**
	     * commTel
	     *
	     * @return  the commTel
	     * @since   CodingExample Ver 1.0
	    */

	    public String getCommTel() {
	        return commTel;
	    }

	    /**
	     * commTel
	     *
	     * @param   commTel    the commTel to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setCommTel(String commTel) {
	        this.commTel = commTel;
	    }

	    /**
	     * commEmail
	     *
	     * @return  the commEmail
	     * @since   CodingExample Ver 1.0
	    */

	    public String getCommEmail() {
	        return commEmail;
	    }

	    /**
	     * commEmail
	     *
	     * @param   commEmail    the commEmail to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setCommEmail(String commEmail) {
	        this.commEmail = commEmail;
	    }

	    /**
	     * licenceNo
	     *
	     * @return  the licenceNo
	     * @since   CodingExample Ver 1.0
	    */

	    public String getLicenceNo() {
	        return licenceNo;
	    }

	    /**
	     * licenceNo
	     *
	     * @param   licenceNo    the licenceNo to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setLicenceNo(String licenceNo) {
	        this.licenceNo = licenceNo;
	    }

	    /**
	     * taxNo
	     *
	     * @return  the taxNo
	     * @since   CodingExample Ver 1.0
	    */

	    public String getTaxNo() {
	        return taxNo;
	    }

	    /**
	     * taxNo
	     *
	     * @param   taxNo    the taxNo to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setTaxNo(String taxNo) {
	        this.taxNo = taxNo;
	    }

	    /**
	     * bankLicenceNo
	     *
	     * @return  the bankLicenceNo
	     * @since   CodingExample Ver 1.0
	    */

	    public String getBankLicenceNo() {
	        return bankLicenceNo;
	    }

	    /**
	     * bankLicenceNo
	     *
	     * @param   bankLicenceNo    the bankLicenceNo to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setBankLicenceNo(String bankLicenceNo) {
	        this.bankLicenceNo = bankLicenceNo;
	    }

	    /**
	     * manager
	     *
	     * @return  the manager
	     * @since   CodingExample Ver 1.0
	    */

	    public String getManager() {
	        return manager;
	    }

	    /**
	     * manager
	     *
	     * @param   manager    the manager to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setManager(String manager) {
	        this.manager = manager;
	    }

	    /**
	     * identityNo
	     *
	     * @return  the identityNo
	     * @since   CodingExample Ver 1.0
	    */

	    public String getIdentityNo() {
	        return identityNo;
	    }

	    /**
	     * identityNo
	     *
	     * @param   identityNo    the identityNo to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setIdentityNo(String identityNo) {
	        this.identityNo = identityNo;
	    }

	    /**
	     * provCode
	     *
	     * @return  the provCode
	     * @since   CodingExample Ver 1.0
	    */

	    public String getProvCode() {
	        return provCode;
	    }

	    /**
	     * provCode
	     *
	     * @param   provCode    the provCode to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setProvCode(String provCode) {
	        this.provCode = provCode;
	    }

	    /**
	     * cityCode
	     *
	     * @return  the cityCode
	     * @since   CodingExample Ver 1.0
	    */

	    public String getCityCode() {
	        return cityCode;
	    }

	    /**
	     * cityCode
	     *
	     * @param   cityCode    the cityCode to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setCityCode(String cityCode) {
	        this.cityCode = cityCode;
	    }

	    /**
	     * areaCode
	     *
	     * @return  the areaCode
	     * @since   CodingExample Ver 1.0
	    */

	    public String getAreaCode() {
	        return areaCode;
	    }

	    /**
	     * areaCode
	     *
	     * @param   areaCode    the areaCode to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setAreaCode(String areaCode) {
	        this.areaCode = areaCode;
	    }

	    /**
	     * addr
	     *
	     * @return  the addr
	     * @since   CodingExample Ver 1.0
	    */

	    public String getAddr() {
	        return addr;
	    }

	    /**
	     * addr
	     *
	     * @param   addr    the addr to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setAddr(String addr) {
	        this.addr = addr;
	    }

	    /**
	     * memo
	     *
	     * @return  the memo
	     * @since   CodingExample Ver 1.0
	    */

	    public String getMemo() {
	        return memo;
	    }

	    /**
	     * memo
	     *
	     * @param   memo    the memo to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setMemo(String memo) {
	        this.memo = memo;
	    }

	    /**
	     * wXActive
	     *
	     * @return  the wXActive
	     * @since   CodingExample Ver 1.0
	    */
	    @JSONField(name = "WXActive")
	    public String getWXActive() {
	        return WXActive;
	    }

	    /**
	     * wXActive
	     *
	     * @param   wXActive    the wXActive to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setWXActive(String wXActive) {
	        WXActive = wXActive;
	    }

	    /**
	     * mainMchtTp
	     *
	     * @return  the mainMchtTp
	     * @since   CodingExample Ver 1.0
	    */

	    public String getMainMchtTp() {
	        return mainMchtTp;
	    }

	    /**
	     * mainMchtTp
	     *
	     * @param   mainMchtTp    the mainMchtTp to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setMainMchtTp(String mainMchtTp) {
	        this.mainMchtTp = mainMchtTp;
	    }

	    /**
	     * subAppid
	     *
	     * @return  the subAppid
	     * @since   CodingExample Ver 1.0
	    */

	    public String getSubAppid() {
	        return subAppid;
	    }

	    /**
	     * subAppid
	     *
	     * @param   subAppid    the subAppid to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setSubAppid(String subAppid) {
	        this.subAppid = subAppid;
	    }

	    public String getJsapiPath() {
			return jsapiPath;
		}

		public void setJsapiPath(String jsapiPath) {
			this.jsapiPath = jsapiPath;
		}
	    /**
	     * qGroupId
	     *
	     * @return  the qGroupId
	     * @since   CodingExample Ver 1.0
	    */

	    public String getqGroupId() {
	        return qGroupId;
	    }

	    /**
	     * qGroupId
	     *
	     * @param   qGroupId    the qGroupId to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setqGroupId(String qGroupId) {
	        this.qGroupId = qGroupId;
	    }

	    /**
	     * categroryId
	     *
	     * @return  the categroryId
	     * @since   CodingExample Ver 1.0
	    */

	    public String getCategroryId() {
	        return categroryId;
	    }

	    /**
	     * categroryId
	     *
	     * @param   categroryId    the categroryId to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setCategroryId(String categroryId) {
	        this.categroryId = categroryId;
	    }

	    /**
	     * feeRate
	     *
	     * @return  the feeRate
	     * @since   CodingExample Ver 1.0
	    */

	    public String getFeeRate() {
	        return feeRate;
	    }

	    /**
	     * feeRate
	     *
	     * @param   feeRate    the feeRate to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setFeeRate(String feeRate) {
	        this.feeRate = feeRate;
	    }

	    /**
	     * settleCycle
	     *
	     * @return  the settleCycle
	     * @since   CodingExample Ver 1.0
	    */

	    public String getSettleCycle() {
	        return settleCycle;
	    }

	    /**
	     * settleCycle
	     *
	     * @param   settleCycle    the settleCycle to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setSettleCycle(String settleCycle) {
	        this.settleCycle = settleCycle;
	    }

	    /**
	     * zFBActive
	     *
	     * @return  the zFBActive
	     * @since   CodingExample Ver 1.0
	    */
	    @JSONField(name = "ZFBActive")
	    public String getZFBActive() {
	        return ZFBActive;
	    }

	    /**
	     * zFBActive
	     *
	     * @param   zFBActive    the zFBActive to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setZFBActive(String zFBActive) {
	        ZFBActive = zFBActive;
	    }

	    /**
	     * mainMchtTpA
	     *
	     * @return  the mainMchtTpA
	     * @since   CodingExample Ver 1.0
	    */

	    public String getMainMchtTpA() {
	        return mainMchtTpA;
	    }

	    /**
	     * mainMchtTpA
	     *
	     * @param   mainMchtTpA    the mainMchtTpA to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setMainMchtTpA(String mainMchtTpA) {
	        this.mainMchtTpA = mainMchtTpA;
	    }

	    /**
	     * olCodeA
	     *
	     * @return  the olCodeA
	     * @since   CodingExample Ver 1.0
	    */

	    public String getOlCodeA() {
	        return olCodeA;
	    }

	    /**
	     * olCodeA
	     *
	     * @param   olCodeA    the olCodeA to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setOlCodeA(String olCodeA) {
	        this.olCodeA = olCodeA;
	    }

	    /**
	     * categIdC
	     *
	     * @return  the categIdC
	     * @since   CodingExample Ver 1.0
	    */

	    public String getCategIdC() {
	        return categIdC;
	    }

	    /**
	     * categIdC
	     *
	     * @param   categIdC    the categIdC to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setCategIdC(String categIdC) {
	        this.categIdC = categIdC;
	    }

	    /**
	     * feeRateA
	     *
	     * @return  the feeRateA
	     * @since   CodingExample Ver 1.0
	    */

	    public String getFeeRateA() {
	        return feeRateA;
	    }

	    /**
	     * feeRateA
	     *
	     * @param   feeRateA    the feeRateA to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setFeeRateA(String feeRateA) {
	        this.feeRateA = feeRateA;
	    }

	    /**
	     * settleCycleA
	     *
	     * @return  the settleCycleA
	     * @since   CodingExample Ver 1.0
	    */

	    public String getSettleCycleA() {
	        return settleCycleA;
	    }

	    /**
	     * settleCycleA
	     *
	     * @param   settleCycleA    the settleCycleA to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setSettleCycleA(String settleCycleA) {
	        this.settleCycleA = settleCycleA;
	    }

	    /**
	     * isISV
	     *
	     * @return  the isISV
	     * @since   CodingExample Ver 1.0
	    */

	    public String getIsISV() {
	        return isISV;
	    }

	    /**
	     * isISV
	     *
	     * @param   isISV    the isISV to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setIsISV(String isISV) {
	        this.isISV = isISV;
	    }

	    /**
	     * isvpid
	     *
	     * @return  the isvpid
	     * @since   CodingExample Ver 1.0
	    */

	    public String getIsvpid() {
	        return isvpid;
	    }

	    /**
	     * isvpid
	     *
	     * @param   isvpid    the isvpid to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setIsvpid(String isvpid) {
	        this.isvpid = isvpid;
	    }

	    /**
	     * bDActive
	     *
	     * @return  the bDActive
	     * @since   CodingExample Ver 1.0
	    */
	    @JSONField(name = "BDActive")
	    public String getBDActive() {
	        return BDActive;
	    }

	    /**
	     * bDActive
	     *
	     * @param   bDActive    the bDActive to set
	     * @since   CodingExample Ver 1.0
	     */
	    @JSONField(name = "BDActive")
	    public void setBDActive(String bDActive) {
	        BDActive = bDActive;
	    }

	    /**
	     * mainMchtTpBaid
	     *
	     * @return  the mainMchtTpBaid
	     * @since   CodingExample Ver 1.0
	    */

	    public String getMainMchtTpBaid() {
	        return mainMchtTpBaid;
	    }

	    /**
	     * mainMchtTpBaid
	     *
	     * @param   mainMchtTpBaid    the mainMchtTpBaid to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setMainMchtTpBaid(String mainMchtTpBaid) {
	        this.mainMchtTpBaid = mainMchtTpBaid;
	    }

	    /**
	     * olCodeBaid
	     *
	     * @return  the olCodeBaid
	     * @since   CodingExample Ver 1.0
	    */

	    public String getOlCodeBaid() {
	        return olCodeBaid;
	    }

	    /**
	     * olCodeBaid
	     *
	     * @param   olCodeBaid    the olCodeBaid to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setOlCodeBaid(String olCodeBaid) {
	        this.olCodeBaid = olCodeBaid;
	    }

	    /**
	     * categIdABaid
	     *
	     * @return  the categIdABaid
	     * @since   CodingExample Ver 1.0
	    */

	    public String getCategIdABaid() {
	        return categIdABaid;
	    }

	    /**
	     * categIdABaid
	     *
	     * @param   categIdABaid    the categIdABaid to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setCategIdABaid(String categIdABaid) {
	        this.categIdABaid = categIdABaid;
	    }

	    /**
	     * categIdBBaid
	     *
	     * @return  the categIdBBaid
	     * @since   CodingExample Ver 1.0
	    */

	    public String getCategIdBBaid() {
	        return categIdBBaid;
	    }

	    /**
	     * categIdBBaid
	     *
	     * @param   categIdBBaid    the categIdBBaid to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setCategIdBBaid(String categIdBBaid) {
	        this.categIdBBaid = categIdBBaid;
	    }

	    /**
	     * categIdCBaid
	     *
	     * @return  the categIdCBaid
	     * @since   CodingExample Ver 1.0
	    */

	    public String getCategIdCBaid() {
	        return categIdCBaid;
	    }

	    /**
	     * categIdCBaid
	     *
	     * @param   categIdCBaid    the categIdCBaid to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setCategIdCBaid(String categIdCBaid) {
	        this.categIdCBaid = categIdCBaid;
	    }

	    /**
	     * feeRateBaid
	     *
	     * @return  the feeRateBaid
	     * @since   CodingExample Ver 1.0
	    */

	    public String getFeeRateBaid() {
	        return feeRateBaid;
	    }

	    /**
	     * feeRateBaid
	     *
	     * @param   feeRateBaid    the feeRateBaid to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setFeeRateBaid(String feeRateBaid) {
	        this.feeRateBaid = feeRateBaid;
	    }

	    /**
	     * settleCycleBaid
	     *
	     * @return  the settleCycleBaid
	     * @since   CodingExample Ver 1.0
	    */

	    public String getSettleCycleBaid() {
	        return settleCycleBaid;
	    }

	    /**
	     * settleCycleBaid
	     *
	     * @param   settleCycleBaid    the settleCycleBaid to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setSettleCycleBaid(String settleCycleBaid) {
	        this.settleCycleBaid = settleCycleBaid;
	    }

	    /**
	     * secMerId
	     *
	     * @return  the secMerId
	     * @since   CodingExample Ver 1.0
	    */

	    public String getSecMerId() {
	        return secMerId;
	    }

	    /**
	     * secMerId
	     *
	     * @param   secMerId    the secMerId to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setSecMerId(String secMerId) {
	        this.secMerId = secMerId;
	    }

	    /**
	     * merId
	     *
	     * @return  the merId
	     * @since   CodingExample Ver 1.0
	    */

	    public String getMerId() {
	        return merId;
	    }

	    /**
	     * merId
	     *
	     * @param   merId    the merId to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setMerId(String merId) {
	        this.merId = merId;
	    }

	    /**
	     * thirdMchtNo
	     *
	     * @return  the thirdMchtNo
	     * @since   CodingExample Ver 1.0
	    */

	    public String getThirdMchtNo() {
	        return thirdMchtNo;
	    }

	    /**
	     * thirdMchtNo
	     *
	     * @param   thirdMchtNo    the thirdMchtNo to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setThirdMchtNo(String thirdMchtNo) {
	        this.thirdMchtNo = thirdMchtNo;
	    }

	    /**
	     * isOrNotZxMchtNo
	     *
	     * @return  the isOrNotZxMchtNo
	     * @since   CodingExample Ver 1.0
	    */

	    public String getIsOrNotZxMchtNo() {
	        return isOrNotZxMchtNo;
	    }

	    /**
	     * isOrNotZxMchtNo
	     *
	     * @param   isOrNotZxMchtNo    the isOrNotZxMchtNo to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setIsOrNotZxMchtNo(String isOrNotZxMchtNo) {
	        this.isOrNotZxMchtNo = isOrNotZxMchtNo;
	    }

	    /**
	     * acctType
	     *
	     * @return  the acctType
	     * @since   CodingExample Ver 1.0
	    */

	    public String getAcctType() {
	        return acctType;
	    }

	    /**
	     * acctType
	     *
	     * @param   acctType    the acctType to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setAcctType(String acctType) {
	        this.acctType = acctType;
	    }

	    /**
	     * settleAcctNm
	     *
	     * @return  the settleAcctNm
	     * @since   CodingExample Ver 1.0
	    */

	    public String getSettleAcctNm() {
	        return settleAcctNm;
	    }

	    /**
	     * settleAcctNm
	     *
	     * @param   settleAcctNm    the settleAcctNm to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setSettleAcctNm(String settleAcctNm) {
	        this.settleAcctNm = settleAcctNm;
	    }

	    /**
	     * settleAcct
	     *
	     * @return  the settleAcct
	     * @since   CodingExample Ver 1.0
	    */

	    public String getSettleAcct() {
	        return settleAcct;
	    }

	    /**
	     * settleAcct
	     *
	     * @param   settleAcct    the settleAcct to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setSettleAcct(String settleAcct) {
	        this.settleAcct = settleAcct;
	    }

	    /**
	     * accIdeNo
	     *
	     * @return  the accIdeNo
	     * @since   CodingExample Ver 1.0
	    */

	    public String getAccIdeNo() {
	        return accIdeNo;
	    }

	    /**
	     * accIdeNo
	     *
	     * @param   accIdeNo    the accIdeNo to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setAccIdeNo(String accIdeNo) {
	        this.accIdeNo = accIdeNo;
	    }

	    /**
	     * accPhone
	     *
	     * @return  the accPhone
	     * @since   CodingExample Ver 1.0
	    */

	    public String getAccPhone() {
	        return accPhone;
	    }

	    /**
	     * accPhone
	     *
	     * @param   accPhone    the accPhone to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setAccPhone(String accPhone) {
	        this.accPhone = accPhone;
	    }

	    /**
	     * settleBankAllName
	     *
	     * @return  the settleBankAllName
	     * @since   CodingExample Ver 1.0
	    */

	    public String getSettleBankAllName() {
	        return settleBankAllName;
	    }

	    /**
	     * settleBankAllName
	     *
	     * @param   settleBankAllName    the settleBankAllName to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setSettleBankAllName(String settleBankAllName) {
	        this.settleBankAllName = settleBankAllName;
	    }

	    /**
	     * settleBankCode
	     *
	     * @return  the settleBankCode
	     * @since   CodingExample Ver 1.0
	    */

	    public String getSettleBankCode() {
	        return settleBankCode;
	    }

	    /**
	     * settleBankCode
	     *
	     * @param   settleBankCode    the settleBankCode to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setSettleBankCode(String settleBankCode) {
	        this.settleBankCode = settleBankCode;
	    }

	    /**
	     * apType
	     *
	     * @return  the apType
	     * @since   CodingExample Ver 1.0
	    */

	    public String getApType() {
	        return apType;
	    }

	    /**
	     * apType
	     *
	     * @param   apType    the apType to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setApType(String apType) {
	        this.apType = apType;
	    }

	    /**
	     * signMethod
	     *
	     * @return  the signMethod
	     * @since   CodingExample Ver 1.0
	    */

	    public String getSignMethod() {
	        return signMethod;
	    }

	    /**
	     * signMethod
	     *
	     * @param   signMethod    the signMethod to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setSignMethod(String signMethod) {
	        this.signMethod = signMethod;
	    }

	    /**
	     * signAture
	     *
	     * @return  the signAture
	     * @since   CodingExample Ver 1.0
	    */

	    public String getSignAture() {
	        return signAture;
	    }

	    /**
	     * signAture
	     *
	     * @param   signAture    the signAture to set
	     * @since   CodingExample Ver 1.0
	     */

	    public void setSignAture(String signAture) {
	        this.signAture = signAture;
	    }

		
	
	    
		public List<MerchantChannel> getChannels() {
			return channels;
		}
		public void setChannels(List<MerchantChannel> channels) {
			this.channels = channels;
		}
		public List<MerchantContact> getContacts() {
			return contacts;
		}
		public void setContacts(List<MerchantContact> contacts) {
			this.contacts = contacts;
		}
		public List<MerchantBank> getBanks() {
			return banks;
		}
		public void setBanks(List<MerchantBank> banks) {
			this.banks = banks;
		}	
		
}
