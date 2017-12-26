package net.fnsco.risk.service.product.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProductDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 代理商编号
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
    private String description;

    /**
     * 还款能力最小值
     */
    private BigDecimal payAbilityMin;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后更新时间
     */
    private Date lastModifyTime;

    /**
     * 状态
     */
    private String status;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgentId() {
        return agentId;
    }

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
    
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPayAbilityMin() {
        return payAbilityMin;
    }

    public void setPayAbilityMin(BigDecimal payAbilityMin) {
        this.payAbilityMin = payAbilityMin;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", agentId="+ agentId + ", name="+ name + ", amountMin="+ amountMin + ", amountMax="+ amountMax + ", rateMin="+ rateMin + ", rateMax="+ rateMax + ", cycle="+ cycle + ", description="+ description + ", payAbilityMin="+ payAbilityMin + ", createTime="+ createTime + ", lastModifyTime="+ lastModifyTime + ", status="+ status + "]";
    }
}