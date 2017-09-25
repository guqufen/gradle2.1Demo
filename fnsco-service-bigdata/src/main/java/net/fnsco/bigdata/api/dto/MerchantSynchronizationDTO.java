/**
 * 
 */
package net.fnsco.bigdata.api.dto;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

/**@desc 
 * @author tangliang
 * @date 2017年9月21日 上午10:54:00
 */
public class MerchantSynchronizationDTO {
	
	private String merchant;
	private String businessLicenseNum;
	private String paperNum;
	private String merLegalPerson;
	private String legalPersonTel;
	private String paperValidTime;
	private String businessLicenseValidTime;
	private String merRegistAddress;
	private String mercFlag;
	private String channelMerchant;
	private String salesSlip;
	
	private String linkMan;
	private String linkManTel;
	private String linkManEmail;
	private String financeLinkMan;
	private String financeLinkManTel;
	private String financeLinkManEmail;
	private String merPrincipal;
	private String merPrincipalTel;
	private String merPrincipalEmail;
	
	private String merInstallArea;
	private String accountType;
	private String accountHolderID;
	private String accountName;
	private String accountNo;
	private String subBankName;
	private String openBankNum;
	private String createTime;
	private String busiCode;
	private String privateKye;
	private String taxRegistCode;
	private String xx;
	private String fileInfos;
	private String snCode;
	private String debitCardRate;
	private String creditCardRate;
	private String debitCardFee;
	private String creditCardFee;
	private String debitCardMaxFee;
	private String creditCardMaxFee;
	private String posType;
	private String posFactory;
	private String merchantCode;
	private String terminalCode;
	private String innerTermCode;
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	public String getBusinessLicenseNum() {
		return businessLicenseNum;
	}
	public void setBusinessLicenseNum(String businessLicenseNum) {
		this.businessLicenseNum = businessLicenseNum;
	}
	public String getPaperNum() {
		return paperNum;
	}
	public void setPaperNum(String paperNum) {
		this.paperNum = paperNum;
	}
	public String getMerLegalPerson() {
		return merLegalPerson;
	}
	public void setMerLegalPerson(String merLegalPerson) {
		this.merLegalPerson = merLegalPerson;
	}
	public String getLegalPersonTel() {
		return legalPersonTel;
	}
	public void setLegalPersonTel(String legalPersonTel) {
		this.legalPersonTel = legalPersonTel;
	}
	public String getPaperValidTime() {
		return paperValidTime;
	}
	public void setPaperValidTime(String paperValidTime) {
		this.paperValidTime = paperValidTime;
	}
	public String getBusinessLicenseValidTime() {
		return businessLicenseValidTime;
	}
	public void setBusinessLicenseValidTime(String businessLicenseValidTime) {
		this.businessLicenseValidTime = businessLicenseValidTime;
	}
	public String getMerRegistAddress() {
		return merRegistAddress;
	}
	public void setMerRegistAddress(String merRegistAddress) {
		this.merRegistAddress = merRegistAddress;
	}
	public String getMercFlag() {
		return mercFlag;
	}
	public void setMercFlag(String mercFlag) {
		this.mercFlag = mercFlag;
	}
	public String getChannelMerchant() {
		return channelMerchant;
	}
	public void setChannelMerchant(String channelMerchant) {
		this.channelMerchant = channelMerchant;
	}
	public String getSalesSlip() {
		return salesSlip;
	}
	public void setSalesSlip(String salesSlip) {
		this.salesSlip = salesSlip;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getLinkManTel() {
		return linkManTel;
	}
	public void setLinkManTel(String linkManTel) {
		this.linkManTel = linkManTel;
	}
	public String getLinkManEmail() {
		return linkManEmail;
	}
	public void setLinkManEmail(String linkManEmail) {
		this.linkManEmail = linkManEmail;
	}
	public String getFinanceLinkMan() {
		return financeLinkMan;
	}
	public void setFinanceLinkMan(String financeLinkMan) {
		this.financeLinkMan = financeLinkMan;
	}
	public String getFinanceLinkManTel() {
		return financeLinkManTel;
	}
	public void setFinanceLinkManTel(String financeLinkManTel) {
		this.financeLinkManTel = financeLinkManTel;
	}
	public String getFinanceLinkManEmail() {
		return financeLinkManEmail;
	}
	public void setFinanceLinkManEmail(String financeLinkManEmail) {
		this.financeLinkManEmail = financeLinkManEmail;
	}
	public String getMerPrincipal() {
		return merPrincipal;
	}
	public void setMerPrincipal(String merPrincipal) {
		this.merPrincipal = merPrincipal;
	}
	public String getMerPrincipalTel() {
		return merPrincipalTel;
	}
	public void setMerPrincipalTel(String merPrincipalTel) {
		this.merPrincipalTel = merPrincipalTel;
	}
	public String getMerPrincipalEmail() {
		return merPrincipalEmail;
	}
	public void setMerPrincipalEmail(String merPrincipalEmail) {
		this.merPrincipalEmail = merPrincipalEmail;
	}
	public String getMerInstallArea() {
		return merInstallArea;
	}
	public void setMerInstallArea(String merInstallArea) {
		this.merInstallArea = merInstallArea;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountHolderID() {
		return accountHolderID;
	}
	public void setAccountHolderID(String accountHolderID) {
		this.accountHolderID = accountHolderID;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getSubBankName() {
		return subBankName;
	}
	public void setSubBankName(String subBankName) {
		this.subBankName = subBankName;
	}
	public String getOpenBankNum() {
		return openBankNum;
	}
	public void setOpenBankNum(String openBankNum) {
		this.openBankNum = openBankNum;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getBusiCode() {
		return busiCode;
	}
	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	public String getPrivateKye() {
		return privateKye;
	}
	public void setPrivateKye(String privateKye) {
		this.privateKye = privateKye;
	}
	public String getTaxRegistCode() {
		return taxRegistCode;
	}
	public void setTaxRegistCode(String taxRegistCode) {
		this.taxRegistCode = taxRegistCode;
	}
	public String getXx() {
		return xx;
	}
	public void setXx(String xx) {
		this.xx = xx;
	}
	public String getFileInfos() {
		return fileInfos;
	}
	public void setFileInfos(String fileInfos) {
		this.fileInfos = fileInfos;
	}
	public String getSnCode() {
		return snCode;
	}
	public void setSnCode(String snCode) {
		this.snCode = snCode;
	}
	public String getDebitCardRate() {
		return debitCardRate;
	}
	public void setDebitCardRate(String debitCardRate) {
		this.debitCardRate = debitCardRate;
	}
	public String getCreditCardRate() {
		return creditCardRate;
	}
	public void setCreditCardRate(String creditCardRate) {
		this.creditCardRate = creditCardRate;
	}
	public String getDebitCardFee() {
		return debitCardFee;
	}
	public void setDebitCardFee(String debitCardFee) {
		this.debitCardFee = debitCardFee;
	}
	public String getCreditCardFee() {
		return creditCardFee;
	}
	public void setCreditCardFee(String creditCardFee) {
		this.creditCardFee = creditCardFee;
	}
	public String getDebitCardMaxFee() {
		return debitCardMaxFee;
	}
	public void setDebitCardMaxFee(String debitCardMaxFee) {
		this.debitCardMaxFee = debitCardMaxFee;
	}
	public String getCreditCardMaxFee() {
		return creditCardMaxFee;
	}
	public void setCreditCardMaxFee(String creditCardMaxFee) {
		this.creditCardMaxFee = creditCardMaxFee;
	}
	public String getPosType() {
		return posType;
	}
	public void setPosType(String posType) {
		this.posType = posType;
	}
	public String getPosFactory() {
		return posFactory;
	}
	public void setPosFactory(String posFactory) {
		this.posFactory = posFactory;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getTerminalCode() {
		return terminalCode;
	}
	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}
	public String getInnerTermCode() {
		return innerTermCode;
	}
	public void setInnerTermCode(String innerTermCode) {
		this.innerTermCode = innerTermCode;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSONObject.toJSONString(this);
	}
	
	/**组装数据
     * @param lists
     * @return
     */
    public static List<Object[]> installListDatas(List<MerchantSynchronizationDTO> lists) {
        List<Object[]>  result = Lists.newArrayList();
        
        for (MerchantSynchronizationDTO merDo : lists) {
            Object[]  data = new Object[45]; 
            data[0] = (merDo.getMerchant());
            data[1] =(merDo.getBusinessLicenseNum());
            data[2] =(merDo.getPaperNum());
            data[3] =(merDo.getMerLegalPerson());
            data[4] =(merDo.getLegalPersonTel());
            data[5] =(merDo.getPaperValidTime());
            data[6] =(merDo.getBusinessLicenseValidTime());
            data[7] =(merDo.getMerRegistAddress());
            data[8] =(merDo.getMercFlag());
            data[9] =(merDo.getChannelMerchant());
            data[10] =(merDo.getSalesSlip());
            
            data[11] =(merDo.getLinkMan());
            data[12] =(merDo.getLinkManTel());
            data[13] =(merDo.getLinkManEmail());
            data[14] =(merDo.getFinanceLinkMan());
            data[15] =(merDo.getFinanceLinkManTel());
            data[16] =(merDo.getFinanceLinkManEmail());
            data[17] =(merDo.getMerPrincipal());
            data[18] =(merDo.getMerPrincipalTel());
            data[19] =(merDo.getMerPrincipalEmail());
    
            data[20] =(merDo.getMerInstallArea());
            data[21] =(merDo.getAccountType());
            data[22] =(merDo.getAccountHolderID());
            data[23] =(merDo.getAccountName());
            data[24] =(merDo.getAccountNo());
            data[25] =(merDo.getSubBankName());
            data[26] =(merDo.getOpenBankNum());
            data[27] =(merDo.getCreateTime());
            data[28] =(merDo.getBusiCode());
            data[29] =(merDo.getPrivateKye());
            data[30] =(merDo.getTaxRegistCode());
            data[31] =(merDo.getXx());
            
            data[32] =(merDo.getFileInfos());
            data[33] =(merDo.getSnCode());
            data[34] =(merDo.getDebitCardRate());
            data[35] =(merDo.getCreditCardRate());
            data[36] =(merDo.getDebitCardFee());
            data[37] =(merDo.getCreditCardFee());
            data[38] =(merDo.getDebitCardMaxFee());
            data[39] =(merDo.getCreditCardMaxFee());
            data[40] =(merDo.getPosType());
            data[41] =(merDo.getPosFactory());
            data[42] =(merDo.getMerchantCode());
            data[43] =(merDo.getTerminalCode());
            data[44] =(merDo.getInnerTermCode());
            
            result.add(data);
        }
        
        return result;
    }
}
