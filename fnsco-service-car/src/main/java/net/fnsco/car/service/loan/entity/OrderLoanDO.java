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
	 * 所在城市
	 */
	private Integer cityId;

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
		return "[id=" + id + ", customerId=" + customerId + ", cityId=" + cityId + ", amount=" + amount
				+ ", suggestCode=" + suggestCode + ", createTime=" + createTime + ", lastUpdateTime=" + lastUpdateTime
				+ ", status=" + status + "]";
	}
}