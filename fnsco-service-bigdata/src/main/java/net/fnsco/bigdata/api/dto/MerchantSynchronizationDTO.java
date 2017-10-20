/**
 * 
 */
package net.fnsco.bigdata.api.dto;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import net.fnsco.core.utils.StringUtil;

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
	private String openBank;
	private String openBankPrince;
	private String openBankCity;
	
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
	private String innerCode;
	
	/**
     * innerCode
     *
     * @return  the innerCode
     * @since   CodingExample Ver 1.0
    */
    
    public String getInnerCode() {
        return innerCode;
    }
    /**
     * innerCode
     *
     * @param   innerCode    the innerCode to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }
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
	/**
     * openBank
     *
     * @return  the openBank
     * @since   CodingExample Ver 1.0
    */
    
    public String getOpenBank() {
        return openBank;
    }
    /**
     * openBank
     *
     * @param   openBank    the openBank to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }
    /**
     * openBankPrince
     *
     * @return  the openBankPrince
     * @since   CodingExample Ver 1.0
    */
    
    public String getOpenBankPrince() {
        return openBankPrince;
    }
    /**
     * openBankPrince
     *
     * @param   openBankPrince    the openBankPrince to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setOpenBankPrince(String openBankPrince) {
        this.openBankPrince = openBankPrince;
    }
    /**
     * openBankCity
     *
     * @return  the openBankCity
     * @since   CodingExample Ver 1.0
    */
    
    public String getOpenBankCity() {
        return openBankCity;
    }
    /**
     * openBankCity
     *
     * @param   openBankCity    the openBankCity to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setOpenBankCity(String openBankCity) {
        this.openBankCity = openBankCity;
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
    /**
     * installMerchantSynDto:(组装对象)
     * @param objs
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月28日 上午10:36:23
     * @return MerchantSynchronizationDTO    DOM对象
     */
    public static  MerchantSynchronizationDTO installMerchantSynDto(Object[] objs){
        MerchantSynchronizationDTO dto = new MerchantSynchronizationDTO();
     // 工商注册名称（协议签约一致）
        dto.setMerchant(StringUtil.valueOf(objs[0]).trim());
        // 营业执照注册号
        dto.setBusinessLicenseNum(StringUtil.valueOf(objs[1]).trim());
        // 法人身份证号码
        dto.setPaperNum(StringUtil.valueOf(objs[2]).trim());
        // 法人姓名
        dto.setMerLegalPerson(StringUtil.valueOf(objs[3]).trim());
        // 法人手机号
        dto.setLegalPersonTel(StringUtil.valueOf(objs[4]).trim());
        // 证件有效期
        dto.setPaperValidTime(StringUtil.valueOf(objs[5]).trim());
        // 营业执照有效期
        dto.setBusinessLicenseValidTime(StringUtil.valueOf(objs[6]).trim());
        // 商户注册地址
        dto.setMerRegistAddress(StringUtil.valueOf(objs[7]).trim());
        // 商户标签
        dto.setMercFlag(StringUtil.valueOf(objs[8]).trim());
        // channelMerchant// 商户入网注册名称
        dto.setChannelMerchant(StringUtil.valueOf(objs[9]).trim());

        // 签购单名称
        dto.setSalesSlip(StringUtil.valueOf(objs[10]).trim());
        // 商户联系人
        dto.setLinkMan(StringUtil.valueOf(objs[11]).trim());
        // 联系电话
        dto.setLinkManTel(StringUtil.valueOf(objs[12]).trim());
        //邮箱
        dto.setLinkManEmail(StringUtil.valueOf(objs[13]).trim());
        //财务联系人信息
        dto.setFinanceLinkMan(StringUtil.valueOf(objs[14]).trim());
        dto.setFinanceLinkManTel(StringUtil.valueOf(objs[15]).trim());
        dto.setFinanceLinkManEmail(StringUtil.valueOf(objs[16]).trim());
        //商户负责人信息
        dto.setMerPrincipal(StringUtil.valueOf(objs[17]).trim());
        dto.setMerPrincipalTel(StringUtil.valueOf(objs[18]).trim());
        dto.setMerPrincipalEmail(StringUtil.valueOf(objs[19]).trim());
        
        // 装机地址
        dto.setMerInstallArea(StringUtil.valueOf(objs[20]).trim());
        // 开户类型
        dto.setAccountType(StringUtil.valueOf(objs[21]).trim());
        // 开户人身份证
        dto.setAccountHolderID(StringUtil.valueOf(objs[22]).trim());
        // 入账人
        dto.setAccountName(StringUtil.valueOf(objs[23]).trim());
        // 入账账号
        dto.setAccountNo(StringUtil.valueOf(objs[24]).trim());
        // 开户行
        dto.setSubBankName(StringUtil.valueOf(objs[25]).trim());
        dto.setOpenBankNum(StringUtil.valueOf(objs[26]).trim());
        dto.setOpenBank(StringUtil.valueOf(objs[27]).trim());
        dto.setOpenBankPrince(StringUtil.valueOf(objs[28]).trim());
        dto.setOpenBankCity(StringUtil.valueOf(objs[29]).trim());
        //createTime
        dto.setCreateTime(StringUtil.valueOf(objs[30]).trim());
        // busiCode
        dto.setBusiCode(StringUtil.valueOf(objs[31]).trim());
        dto.setPrivateKye(StringUtil.valueOf(objs[32]).trim());
        dto.setTaxRegistCode(StringUtil.valueOf(objs[33]).trim());
        // 扫码扣率
        dto.setXx(StringUtil.valueOf(objs[34]).trim());
        //文件信息
        dto.setFileInfos(StringUtil.valueOf(objs[35]).trim());
        
        // 一号pos机
        // 备注/1号机具SN
        dto.setSnCode(StringUtil.valueOf(objs[36]).trim());
        //debitCardRate
        dto.setDebitCardRate(StringUtil.valueOf(objs[37]).trim());
        //creditCardRate
        dto.setCreditCardRate(StringUtil.valueOf(objs[38]).trim());
        //debitCardFee
        dto.setDebitCardFee(StringUtil.valueOf(objs[39]).trim());
        //creditCardFee
        dto.setCreditCardFee(StringUtil.valueOf(objs[40]).trim());
        //debitCardMaxFee
        dto.setDebitCardMaxFee(StringUtil.valueOf(objs[41]).trim());
        //creditCardMaxFee
        dto.setCreditCardMaxFee(StringUtil.valueOf(objs[42]).trim());
        //posType
        dto.setPosType(StringUtil.valueOf(objs[43]).trim());
        //posFactory
        dto.setPosFactory(StringUtil.valueOf(objs[44]).trim());
        //merchantCode
        dto.setMerchantCode(StringUtil.valueOf(objs[45]).trim());
        //terminalCode
        dto.setTerminalCode(StringUtil.valueOf(objs[46]).trim());
        //innerTermCode
        dto.setInnerTermCode(StringUtil.valueOf(objs[47]).trim());
        //innerCode
        dto.setInnerCode(StringUtil.valueOf(objs[48]).trim());
        
        return dto;
    }
    
    
}
