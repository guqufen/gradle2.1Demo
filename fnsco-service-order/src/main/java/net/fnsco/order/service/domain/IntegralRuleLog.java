package net.fnsco.order.service.domain;

import java.util.Date;

import net.fnsco.core.base.DTO;

/**
 * 积分日志表：i_integral_log实体对象
 * 
 * @author Administrator
 *
 */
public class IntegralRuleLog extends DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;// 主键

	private String entityInnerCode;// 实体内部商户号

	private String ruleCode;// 积分规则代码

	private Integer integral;// 积分值

	private String integralDate;// 记分日期

	private String description;// 积分描述

	private Date createTime;// 创建时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEntityInnerCode() {
		return entityInnerCode;
	}

	public void setEntityInnerCode(String entityInnerCode) {
		this.entityInnerCode = entityInnerCode;
	}

	public String getRuleCode() {
		return ruleCode;
	}

	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public String getIntegralDate() {
		return integralDate;
	}

	public void setIntegralDate(String integralDate) {
		this.integralDate = integralDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
