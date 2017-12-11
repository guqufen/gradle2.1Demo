package net.fnsco.car.service.buy.entity;

import java.util.Date;

public class OrderBuyDO {

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
     * 汽车品牌
     */
    private Integer carTypeId;
    
    /**
     * 	汽车品牌名称
     */
    private String carTypeName;

    /**
     * 汽车型号
     */
    private String carModel;

    /**
     * 金融方案
     */
    private String buyType;

    /**
     * 推荐码
     */
    private Integer suggestCode;

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
	 * carTypeName
	 *
	 * @return  the carTypeName
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getCarTypeName() {
		return carTypeName;
	}

	/**
	 * carTypeName
	 *
	 * @param   carTypeName    the carTypeName to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setCarTypeName(String carTypeName) {
		this.carTypeName = carTypeName;
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

    public Integer getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(Integer carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getBuyType() {
        return buyType;
    }

    public void setBuyType(String buyType) {
        this.buyType = buyType;
    }

    public Integer getSuggestCode() {
        return suggestCode;
    }

    public void setSuggestCode(Integer suggestCode) {
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
        return "[id="+ id + ", customerId="+ customerId + ", cityId="+ cityId + ", carTypeId="+ carTypeId + ", carModel="+ carModel + ", buyType="+ buyType + ", suggestCode="+ suggestCode + ", createTime="+ createTime + ", lastUpdateTime="+ lastUpdateTime + ", status="+ status + "]";
    }
}