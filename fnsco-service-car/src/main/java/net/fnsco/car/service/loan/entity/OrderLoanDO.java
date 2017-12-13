package net.fnsco.car.service.loan.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrderLoanDO {

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
     * 汽车型号
     */
    private Integer carSubTypeId;
    
    /**
     * 	汽车品牌名称
     */
    private String carTypeName;


	/**
	 * 贷款金额
	 */
	private BigDecimal amount;

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
     * 所属运营商帐号ID
     */
    private Integer sysUserId;
    
    /**
     * 运营商名称
     */
    private String agentName;
    
	/**
	 * carTypeId
	 *
	 * @return  the carTypeId
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getCarTypeId() {
		return carTypeId;
	}

	/**
	 * carTypeId
	 *
	 * @param   carTypeId    the carTypeId to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setCarTypeId(Integer carTypeId) {
		this.carTypeId = carTypeId;
	}

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
	 * @return the carTypeId
	 */
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
	 * carTypeId
	 *
	 * @return  the carTypeId
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getCarSubTypeId() {
		return carSubTypeId;
	}

	/**
	 * @param carTypeId the carTypeId to set
	 */

	public void setCarSubTypeId(Integer carSubTypeId) {
		this.carSubTypeId = carSubTypeId;
	}

	
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
		return "OrderLoanDO [id=" + id + ", customerId=" + customerId + ", cityId=" + cityId + ", amount=" + amount
				+ ", suggestCode=" + suggestCode + ", createTime=" + createTime + ", lastUpdateTime=" + lastUpdateTime
				+ ", status=" + status + ", carSubTypeId=" + carSubTypeId +"]";
	}

	
}