package net.fnsco.car.service.safe.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrderSafeDO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 顾客ID
     */
    private Integer customerId;
    
    /**
     * 顾客名称
     */
    private String customerName;
    
    /**
     * 顾客手机号
     */
    private String customerPhone;

    /**
     * 所在城市
     */
    private Integer cityId;
    
    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 汽车原价(分)
     */
    private BigDecimal carOriginalPrice;

    /**
     * 保险公司ID
     */
    private Integer insuCompanyId;
    
    /**
     * 保险公司名称
     */
    private String insuCompanyName;

    /**
     * 预估保费（分）
     */
    private BigDecimal estiPremiums;

    /**
     * 推荐码
     */
    private String suggestCode;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后更新时间
     */
    private Date lastUpdateTime;

    /**
     * 状态0申请9完成
     */
    private Integer status;
    
    /**
     * 所属运营商帐号ID
     */
    private Integer sysUserId;
    
    private String agentName;
    
    /**
	 * agentName
	 *
	 * @return  the agentName
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getAgentName() {
		return agentName;
	}

	/**
	 * agentName
	 *
	 * @param   agentName    the agentName to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	/**
	 * sysUserId
	 *
	 * @return  the sysUserId
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getSysUserId() {
		return sysUserId;
	}

	/**
	 * sysUserId
	 *
	 * @param   sysUserId    the sysUserId to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
	}

	/**
	 * customerName
	 *
	 * @return  the customerName
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * customerName
	 *
	 * @param   customerName    the customerName to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * customerPhone
	 *
	 * @return  the customerPhone
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getCustomerPhone() {
		return customerPhone;
	}

	/**
	 * customerPhone
	 *
	 * @param   customerPhone    the customerPhone to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	/**
	 * cityName
	 *
	 * @return  the cityName
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getCityName() {
		return cityName;
	}

	/**
	 * cityName
	 *
	 * @param   cityName    the cityName to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * insuCompanyName
	 *
	 * @return  the insuCompanyName
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getInsuCompanyName() {
		return insuCompanyName;
	}

	/**
	 * insuCompanyName
	 *
	 * @param   insuCompanyName    the insuCompanyName to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setInsuCompanyName(String insuCompanyName) {
		this.insuCompanyName = insuCompanyName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public BigDecimal getCarOriginalPrice() {
        return carOriginalPrice;
    }

    public void setCarOriginalPrice(BigDecimal carOriginalPrice) {
        this.carOriginalPrice = carOriginalPrice;
    }

    public Integer getInsuCompanyId() {
        return insuCompanyId;
    }

    public void setInsuCompanyId(Integer insuCompanyId) {
        this.insuCompanyId = insuCompanyId;
    }

    public BigDecimal getEstiPremiums() {
        return estiPremiums;
    }

    public void setEstiPremiums(BigDecimal estiPremiums) {
        this.estiPremiums = estiPremiums;
    }

    public String getSuggestCode() {
        return suggestCode;
    }

    public void setSuggestCode(String suggestCode) {
        this.suggestCode = suggestCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", customerId="+ customerId + ", cityId="+ cityId + ", carOriginalPrice="+ carOriginalPrice + ", insuCompanyId="+ insuCompanyId + ", estiPremiums="+ estiPremiums + ", suggestCode="+ suggestCode + ", createTime="+ createTime + ", lastUpdateTime="+ lastUpdateTime + ", status="+ status + "]";
    }
}