package net.fnsco.risk.service.sys.entity;

import java.math.BigDecimal;

public class IndustryDO {

    /**
     * 
     */
    private Integer id;

    /**
     * mcc码
     */
    private String code;

    /**
     * 分类
     */
    private String businessForm;

    /**
     * 分类说明
     */
    private String first;

    /**
     * 适用范围
     */
    private String third;

    /**
     * 手续费率
     */
    private String fourth;

    /**
     * 状态 启用/停用
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;
    
    /**
     * 行业最大交易金额(单位:分)
     */
    private BigDecimal maxPrice;
    
    /**
     * 净利率
     */
    private String interestRate;
    
    /**
     * pos机使率
     */
    private String posUsage;
    
    
    public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getPosUsage() {
		return posUsage;
	}

	public void setPosUsage(String posUsage) {
		this.posUsage = posUsage;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBusinessForm() {
        return businessForm;
    }

    public void setBusinessForm(String businessForm) {
        this.businessForm = businessForm;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getFourth() {
        return fourth;
    }

    public void setFourth(String fourth) {
        this.fourth = fourth;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", code="+ code + ", businessForm="+ businessForm + ", first="+ first + ", third="+ third + ", fourth="+ fourth + ", status="+ status + ", remark="+ remark + "]";
    }
}