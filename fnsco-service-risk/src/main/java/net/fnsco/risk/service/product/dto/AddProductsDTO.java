package net.fnsco.risk.service.product.dto;

import net.fnsco.core.base.DTO;

public class AddProductsDTO extends DTO{

	/**
     * 代理商id
     */
    private Integer agentId;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 贷款额度最小值
     */
    private String amountMin;

    /**
     * 贷款额度最大值
     */
    private String amountMax;

    /**
     * 最小费率
     */
    private String rateMin;

    /**
     * 最大费率
     */
    private String rateMax;

    /**
     * 产品周期，贷款周期
     */
    private String cycle;

    /**
     * 产品说明
     */
    private String desc;

	/**
	 * @return the agentId
	 */
	public Integer getAgentId() {
		return agentId;
	}

	/**
	 * @param agentId the agentId to set
	 */
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmountMin() {
		return amountMin;
	}

	public void setAmountMin(String amountMin) {
		this.amountMin = amountMin;
	}

	public String getAmountMax() {
		return amountMax;
	}

	public void setAmountMax(String amountMax) {
		this.amountMax = amountMax;
	}

	public String getRateMin() {
		return rateMin;
	}

	public void setRateMin(String rateMin) {
		this.rateMin = rateMin;
	}

	public String getRateMax() {
		return rateMax;
	}

	public void setRateMax(String rateMax) {
		this.rateMax = rateMax;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
    
}