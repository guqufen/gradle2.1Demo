package net.fnsco.order.service.domain;

import com.alibaba.fastjson.JSONObject;

import net.fnsco.core.base.DTO;
/**
 * @desc 商家银行卡信息表
 * @author tangliang
 * @date 2017年6月22日 下午2:58:30
 */
public class MerchantBank extends DTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2302587502297875010L;

	private Integer id;

	private String innerCode;
	
    private String accountType;
    
    private String accountNo;

    private String accountName;

    private String accountCardId;

    private String subBankName;

    private String openBankPrince;

    private String openBank;

    private String openBankCity;

    private String openBankNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
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

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getAccountCardId() {
        return accountCardId;
    }

    public void setAccountCardId(String accountCardId) {
        this.accountCardId = accountCardId == null ? null : accountCardId.trim();
    }

    public String getSubBankName() {
        return subBankName;
    }

    public void setSubBankName(String subBankName) {
        this.subBankName = subBankName == null ? null : subBankName.trim();
    }

    public String getOpenBankPrince() {
        return openBankPrince;
    }

    public void setOpenBankPrince(String openBankPrince) {
        this.openBankPrince = openBankPrince == null ? null : openBankPrince.trim();
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank == null ? null : openBank.trim();
    }

    public String getOpenBankCity() {
        return openBankCity;
    }

    public void setOpenBankCity(String openBankCity) {
        this.openBankCity = openBankCity == null ? null : openBankCity.trim();
    }

    public String getOpenBankNum() {
        return openBankNum;
    }

    public void setOpenBankNum(String openBankNum) {
        this.openBankNum = openBankNum == null ? null : openBankNum.trim();
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return JSONObject.toJSONString(this);
    }
    
    @Override
    public int hashCode() {
    	// TODO Auto-generated method stub
    	return super.hashCode();
    }
}