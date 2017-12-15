package net.fnsco.trading.service.bank.entity;

import java.util.Date;

public class AppUserBankDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 内部商户号 15位
     */
    private String appUserId;

    /**
     * 账户类型0对私1对公
     */
    private String accountType;

    /**
     * 开户账号
     */
    private String accountNo;

    /**
     * 账户开户名
     */
    private String accountName;

    /**
     * 开户人身份证号
     */
    private String accountCardId;

    /**
     * 支行名称
     */
    private String subBankName;

    /**
     * 开户行所在省
     */
    private String openBankPrince;

    /**
     * 开户行
     */
    private String openBank;

    /**
     * 开户行所在市
     */
    private String openBankCity;

    /**
     * 开户行行号
     */
    private String openBankNum;

    /**
     * 开户手机号
     */
    private String accountPhone;
    
    private String status;
    private Date create_time;
    private Date update_time;
    private String bank_name;
    
    /**
	 * create_time
	 *
	 * @return  the create_time
	 * @since   CodingExample Ver 1.0
	*/
	
	public Date getCreate_time() {
		return create_time;
	}

	/**
	 * create_time
	 *
	 * @param   create_time    the create_time to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	/**
	 * update_time
	 *
	 * @return  the update_time
	 * @since   CodingExample Ver 1.0
	*/
	
	public Date getUpdate_time() {
		return update_time;
	}

	/**
	 * update_time
	 *
	 * @param   update_time    the update_time to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	/**
	 * bank_name
	 *
	 * @return  the bank_name
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getBank_name() {
		return bank_name;
	}

	/**
	 * bank_name
	 *
	 * @param   bank_name    the bank_name to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	private String type;



    /**
	 * status
	 *
	 * @return  the status
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getStatus() {
		return status;
	}

	/**
	 * status
	 *
	 * @param   status    the status to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setStatus(String status) {
		this.status = status;
	}

	

	/**
	 * type
	 *
	 * @return  the type
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getType() {
		return type;
	}

	/**
	 * type
	 *
	 * @param   type    the type to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setType(String type) {
		this.type = type;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountCardId() {
        return accountCardId;
    }

    public void setAccountCardId(String accountCardId) {
        this.accountCardId = accountCardId;
    }

    public String getSubBankName() {
        return subBankName;
    }

    public void setSubBankName(String subBankName) {
        this.subBankName = subBankName;
    }

    public String getOpenBankPrince() {
        return openBankPrince;
    }

    public void setOpenBankPrince(String openBankPrince) {
        this.openBankPrince = openBankPrince;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public String getOpenBankCity() {
        return openBankCity;
    }

    public void setOpenBankCity(String openBankCity) {
        this.openBankCity = openBankCity;
    }

    public String getOpenBankNum() {
        return openBankNum;
    }

    public void setOpenBankNum(String openBankNum) {
        this.openBankNum = openBankNum;
    }

    public String getAccountPhone() {
        return accountPhone;
    }

    public void setAccountPhone(String accountPhone) {
        this.accountPhone = accountPhone;
    }

	/**
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AppUserBankDO [id=" + id + ", appUserId=" + appUserId + ", accountType=" + accountType + ", accountNo="
				+ accountNo + ", accountName=" + accountName + ", accountCardId=" + accountCardId + ", subBankName="
				+ subBankName + ", openBankPrince=" + openBankPrince + ", openBank=" + openBank + ", openBankCity="
				+ openBankCity + ", openBankNum=" + openBankNum + ", accountPhone=" + accountPhone + ", status="
				+ status + ", create_time=" + create_time + ", update_time=" + update_time + ", bank_name=" + bank_name
				+ ", type=" + type + "]";
	}

	
    


    
}