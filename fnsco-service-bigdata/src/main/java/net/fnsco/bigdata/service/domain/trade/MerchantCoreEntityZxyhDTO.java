package net.fnsco.bigdata.service.domain.trade;

import java.util.List;

import net.fnsco.bigdata.service.domain.MerchantBank;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantContact;
import net.fnsco.core.base.DTO;

public class MerchantCoreEntityZxyhDTO{
		
		private Integer id;
	    public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		private String mchtNm;           //商户全称
	    private String mchtCnAbbr;       // 商户简称    String  申请类型=1 时，必填，12位以内   
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
	    private String manager;          //法人姓名    String(20)  必填20位以内
	    private String identityNo;       // 法人身份证   String  必填  正确身份证格式
	    private String provCode;         //   商户所属省   String  必填  详见省市区表
	    private String cityCode;         //   商户所属市   String  必填  详见省市区表
	    private String areaCode;         //   商户所属区   String  必填  详见省市区表
	    private String addr;             //   详细地址    String(20)  必填  20位以内

	    private String settleAcctNm;     //   结算开户名   String  申请类型=1 时必填  
	    private String settleAcct;       //  结算账号    String  申请类型=1 时必填  40位以内数字
	    private String accIdeNo;         //   开户身份证号  String  账户类型=2 时必填  正确身份证格式
	    private String accPhone;         //   开户手机号   String  账户类型=2 时必填  11位以内数字
	    private String settleBankAllName;//  收款银行全称  String  申请类型=1 时，必填 
	    private String settleBankCode;   // 收款银行行号  String  非中信账户必填 12 位数字联行号
	    
	    private List<MerchantContact> contacts;// 关联的联系人信息
	    private List<MerchantBank> banks;// 关联的银行卡信息
	    private List<MerchantChannel> channels;//关联的通道及相关信息
	    
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
		public String getMchtNm() {
			return mchtNm;
		}
		public void setMchtNm(String mchtNm) {
			this.mchtNm = mchtNm;
		}
		public String getMchtCnAbbr() {
			return mchtCnAbbr;
		}
		public void setMchtCnAbbr(String mchtCnAbbr) {
			this.mchtCnAbbr = mchtCnAbbr;
		}
		public String getEtpsAttr() {
			return etpsAttr;
		}
		public void setEtpsAttr(String etpsAttr) {
			this.etpsAttr = etpsAttr;
		}
		public String getEtpsTp() {
			return etpsTp;
		}
		public void setEtpsTp(String etpsTp) {
			this.etpsTp = etpsTp;
		}
		public String getMchtTel() {
			return mchtTel;
		}
		public void setMchtTel(String mchtTel) {
			this.mchtTel = mchtTel;
		}
		public String getContact() {
			return contact;
		}
		public void setContact(String contact) {
			this.contact = contact;
		}
		public String getCommTel() {
			return commTel;
		}
		public void setCommTel(String commTel) {
			this.commTel = commTel;
		}
		public String getCommEmail() {
			return commEmail;
		}
		public void setCommEmail(String commEmail) {
			this.commEmail = commEmail;
		}
		public String getLicenceNo() {
			return licenceNo;
		}
		public void setLicenceNo(String licenceNo) {
			this.licenceNo = licenceNo;
		}
		public String getManager() {
			return manager;
		}
		public void setManager(String manager) {
			this.manager = manager;
		}
		public String getIdentityNo() {
			return identityNo;
		}
		public void setIdentityNo(String identityNo) {
			this.identityNo = identityNo;
		}
		public String getProvCode() {
			return provCode;
		}
		public void setProvCode(String provCode) {
			this.provCode = provCode;
		}
		public String getCityCode() {
			return cityCode;
		}
		public void setCityCode(String cityCode) {
			this.cityCode = cityCode;
		}
		public String getAreaCode() {
			return areaCode;
		}
		public void setAreaCode(String areaCode) {
			this.areaCode = areaCode;
		}
		public String getAddr() {
			return addr;
		}
		public void setAddr(String addr) {
			this.addr = addr;
		}
		public String getSettleAcctNm() {
			return settleAcctNm;
		}
		public void setSettleAcctNm(String settleAcctNm) {
			this.settleAcctNm = settleAcctNm;
		}
		public String getSettleAcct() {
			return settleAcct;
		}
		public void setSettleAcct(String settleAcct) {
			this.settleAcct = settleAcct;
		}
		public String getAccIdeNo() {
			return accIdeNo;
		}
		public void setAccIdeNo(String accIdeNo) {
			this.accIdeNo = accIdeNo;
		}
		public String getAccPhone() {
			return accPhone;
		}
		public void setAccPhone(String accPhone) {
			this.accPhone = accPhone;
		}
		public String getSettleBankAllName() {
			return settleBankAllName;
		}
		public void setSettleBankAllName(String settleBankAllName) {
			this.settleBankAllName = settleBankAllName;
		}
		public String getSettleBankCode() {
			return settleBankCode;
		}
		public void setSettleBankCode(String settleBankCode) {
			this.settleBankCode = settleBankCode;
		}
	    
	    
	  
}
