package net.fnsco.bigdata.api.dto;

public class AloneMchtInfo {
	private String respCode	;//返回结果	String		0000-成功 
//										5005-参数错误 
//										9407-系统异常
//										5002-商户不存在
//										5016-客户端报文验签失败
//										5021-暂不支持此签名方法
//										5017-签名校验异常
	private String respMsg;	//返回信息	String		
	private String txnTime;	//进件时间戳	String		格式:yyyymmddhhmmssSSS 例:20170804145636822
	private String signMethod;	//验签方式	String	Y	02-MD5  03-RSA
	private String mchtNm	;//商户全称	String		
	private String mchtCnAbbr;	//商户简称	String		
	private String manageScope;	//经营范围	String		
	private String mchtTel	;//商户电话	String		
	private String contact	;//联系人姓名	String		
	private String commTel;	//联系人电话	String		
	private String commEmail;	//联系人邮箱	String		
	private String licenceNo;	//营业执照编号	String		
	private String taxNo	;//税务证编号	String		
	private String bankLicenceNo	;//组织机构代码	String		
	private String manager;	//法人姓名	String		
	private String identityNo;	//法人身份证	String		
	private String provCode	;//商户所属省	String		详见省市区表
	private String cityCode	;//商户所属市	String		详见省市区表
	private String areaCode;	//商户所属区	String		详见省市区表
	private String addr;	//详细地址	String		
	private String memo	;//备注	String		
	private String WXActive	;//是否开通微信	String		Y-是 N-否
	private String mainMchtTp;	//微信主商户号类型	String		00-医疗教育类 
//				01-公共事业类 
//				02-其他类 
//				03-WAP
	private String qGroupId;	//微信一级类目	String		参见《微信类目表》
	private String qnodeId;	//微信二级类目	String		参见《微信类目表》
	private String feeRateT	;//微信手续费(%)	String		
	private String settleCycle;	//微信结算周期	String		
	private String ZFBActive;	//是否开通支付宝	String		Y-是N-否 
	private String mainMchtTpA;	//支付宝主商户号类型	String		D1-普通类
	private String categIdC	;//支付宝一级类目	String		参见《支付宝类目表》
	private String feeRateA	;//支付宝商户手续费(%)	String		
	private String settleCycleA;	//支付宝结算周期	String		
	private String isISV	;//是否为ISV商户	String		
	private String isvpid	;//ISV商户请添PID	String		
	private String BDActive;	//是否开通百度	String		Y-是 N-否
	private String mainMchtTpBaid;	//百度主商户号类型	String		21-综合类
	private String categIdABaid	;//类目一	String		参见《百度类目表》 
	private String categIdBBaid	;//类目二	String		参见《百度类目表》 
	private String categIdCBaid	;//类目三	String		参见《百度类目表》 
	private String feeRateBaid	;//百度钱包商户手续费(%)	String		
	private String settleCycleBaid	;//百度结算周期	String		
	private String secMerId	;//独立商户号	String		
	private String merId	;//平台商户号	String		
	private String thirdMchtNo;	//第三方平台子商户号	String		
	private String zxFlag	;//是否中信银行账户	String		
	private String acctTp;	//账户类型	String		
	private String settleAcctNm	;//结算开户名	String		
	private String settleAcct	;//结算账号	String		
	private String crtIdentityNo;	//开户身份证号	String		
	private String crtTelNo;//	开户手机号	String		
	private String settleBankAllName;	//收款银行全称	String		
	private String settleBankCode;	//收款银行行号	String		
	private String mchtStatus	;//商户状态	String		0-开启，1-关闭，2-待开启
	private String mchtChkStatus;	//商户审核状态	String		0-新增待审核, 2-正常,3-审核拒绝
	private String signAture	;//签名	String	Y	
	/**
	 * respCode
	 *
	 * @return  the respCode
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getRespCode() {
		return respCode;
	}
	/**
	 * respCode
	 *
	 * @param   respCode    the respCode to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	/**
	 * respMsg
	 *
	 * @return  the respMsg
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getRespMsg() {
		return respMsg;
	}
	/**
	 * respMsg
	 *
	 * @param   respMsg    the respMsg to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	/**
	 * txnTime
	 *
	 * @return  the txnTime
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getTxnTime() {
		return txnTime;
	}
	/**
	 * txnTime
	 *
	 * @param   txnTime    the txnTime to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
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
	 * qnodeId
	 *
	 * @return  the qnodeId
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getQnodeId() {
		return qnodeId;
	}
	/**
	 * qnodeId
	 *
	 * @param   qnodeId    the qnodeId to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setQnodeId(String qnodeId) {
		this.qnodeId = qnodeId;
	}
	/**
	 * feeRateT
	 *
	 * @return  the feeRateT
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getFeeRateT() {
		return feeRateT;
	}
	/**
	 * feeRateT
	 *
	 * @param   feeRateT    the feeRateT to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setFeeRateT(String feeRateT) {
		this.feeRateT = feeRateT;
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
	
	public String getBDActive() {
		return BDActive;
	}
	/**
	 * bDActive
	 *
	 * @param   bDActive    the bDActive to set
	 * @since   CodingExample Ver 1.0
	 */
	
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
	 * zxFlag
	 *
	 * @return  the zxFlag
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getZxFlag() {
		return zxFlag;
	}
	/**
	 * zxFlag
	 *
	 * @param   zxFlag    the zxFlag to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setZxFlag(String zxFlag) {
		this.zxFlag = zxFlag;
	}
	/**
	 * acctTp
	 *
	 * @return  the acctTp
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getAcctTp() {
		return acctTp;
	}
	/**
	 * acctTp
	 *
	 * @param   acctTp    the acctTp to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setAcctTp(String acctTp) {
		this.acctTp = acctTp;
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
	 * crtIdentityNo
	 *
	 * @return  the crtIdentityNo
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getCrtIdentityNo() {
		return crtIdentityNo;
	}
	/**
	 * crtIdentityNo
	 *
	 * @param   crtIdentityNo    the crtIdentityNo to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setCrtIdentityNo(String crtIdentityNo) {
		this.crtIdentityNo = crtIdentityNo;
	}
	/**
	 * crtTelNo
	 *
	 * @return  the crtTelNo
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getCrtTelNo() {
		return crtTelNo;
	}
	/**
	 * crtTelNo
	 *
	 * @param   crtTelNo    the crtTelNo to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setCrtTelNo(String crtTelNo) {
		this.crtTelNo = crtTelNo;
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
	 * mchtStatus
	 *
	 * @return  the mchtStatus
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getMchtStatus() {
		return mchtStatus;
	}
	/**
	 * mchtStatus
	 *
	 * @param   mchtStatus    the mchtStatus to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setMchtStatus(String mchtStatus) {
		this.mchtStatus = mchtStatus;
	}
	/**
	 * mchtChkStatus
	 *
	 * @return  the mchtChkStatus
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getMchtChkStatus() {
		return mchtChkStatus;
	}
	/**
	 * mchtChkStatus
	 *
	 * @param   mchtChkStatus    the mchtChkStatus to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setMchtChkStatus(String mchtChkStatus) {
		this.mchtChkStatus = mchtChkStatus;
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
	
	

}
