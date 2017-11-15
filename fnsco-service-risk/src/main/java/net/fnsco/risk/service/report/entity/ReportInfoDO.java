package net.fnsco.risk.service.report.entity;

import java.util.Date;

public class ReportInfoDO {
    //agent_id
    private Integer agentId;

    //判断三个月是否生成
    private Integer isTrue;
    //登录用户ID
    private Integer userId;
    /**
     * 
     */
    private Integer id;

    /**
     * 商户名（必须40个字符）
     */
    private String  merName;

    /**
     * 营业执照号码
     */
    private String  businessLicenseNum;

    /**
     * 经营地址
     */
    private String  businessAddress;

    /**
     * 营业期限
     */
    private String  businessDueTime;

    /**
     * 行业
     */
    private String  industry;
    
    /**
     * 行业名称
     */
    private String  industryName;

    /**
     * 商圈
     */
    private String  tradingArea;

    /**
     * 营业额
     */
    private String  turnover;

    /**
     * 规模
     */
    private Integer size;

    /**
     * 报告周期
     */
    private String  reportCycle;

    /**
     * 报告时间
     */
    private String  reportTimer;

    /**
     * 风险提醒
     */
    private String  riskWarning;

    /**
     * 额度
     */
    private String  quota;

    /**
     * 费率
     */
    private String  feeRate;

    /**
     * 贷款周期
     */
    private String  loanCycle;
    /**
     * 商户编码
     */
    private String  merNum;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date    createTime;
    /**
     * 最后修改时间
     */
    private Date    lastModifyTime;
    
    /**
     * 新增加KEY --商户姓名、法人姓名、营业执照号 
     */
    private String key;
    
    /**
     * 报告查看次数
     */
    private Integer viewNum;
    /**
     * 最后报告时间
     */
    private Date lastViewTime;
    
    private String lastViewTimeStr;
    
    private String evaluation;

    public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getLastViewTimeStr() {
		return lastViewTimeStr;
	}

	public void setLastViewTimeStr(String lastViewTimeStr) {
		this.lastViewTimeStr = lastViewTimeStr;
	}

	public Integer getViewNum() {
		return viewNum;
	}

	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}

	public Date getLastViewTime() {
		return lastViewTime;
	}

	public void setLastViewTime(Date lastViewTime) {
		this.lastViewTime = lastViewTime;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

    public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	/**
     * userId
     *
     * @return  the userId
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getUserId() {
        return userId;
    }

    /**
     * userId
     *
     * @param   userId    the userId to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(Integer isTrue) {
        this.isTrue = isTrue;
    }

    private String decorationLevel;

    public String getDecorationLevel() {
        return decorationLevel;
    }

    public void setDecorationLevel(String decorationLevel) {
        this.decorationLevel = decorationLevel;
    }

    private String innerCode;

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }

    private Integer customerType;

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    private Integer webUserOuterId;

    public Integer getWebUserOuterId() {
        return webUserOuterId;
    }

    public void setWebUserOuterId(Integer webUserOuterId) {
        this.webUserOuterId = webUserOuterId;
    }

    public String getMerNum() {
        return merNum;
    }

    public void setMerNum(String merNum) {
        this.merNum = merNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getBusinessLicenseNum() {
        return businessLicenseNum;
    }

    public void setBusinessLicenseNum(String businessLicenseNum) {
        this.businessLicenseNum = businessLicenseNum;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessDueTime() {
        return businessDueTime;
    }

    public void setBusinessDueTime(String businessDueTime) {
        this.businessDueTime = businessDueTime;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getTradingArea() {
        return tradingArea;
    }

    public void setTradingArea(String tradingArea) {
        this.tradingArea = tradingArea;
    }

    public String getTurnover() {
        return turnover;
    }

    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getReportCycle() {
        return reportCycle;
    }

    public void setReportCycle(String reportCycle) {
        this.reportCycle = reportCycle;
    }

    public String getReportTimer() {
        return reportTimer;
    }

    public void setReportTimer(String reportTimer) {
        this.reportTimer = reportTimer;
    }

    public String getRiskWarning() {
        return riskWarning;
    }

    public void setRiskWarning(String riskWarning) {
        this.riskWarning = riskWarning;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public String getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(String feeRate) {
        this.feeRate = feeRate;
    }

    public String getLoanCycle() {
        return loanCycle;
    }

    public void setLoanCycle(String loanCycle) {
        this.loanCycle = loanCycle;
    }

    @Override
    public String toString() {
        return "ReportInfoDO [agentId=" + agentId + ", isTrue=" + isTrue + ", decorationLevel=" + decorationLevel + ", innerCode=" + innerCode + ", customerType=" + customerType + ", webUserOuterId="
               + webUserOuterId + ", id=" + id + ", merName=" + merName + ", businessLicenseNum=" + businessLicenseNum + ", businessAddress=" + businessAddress + ", businessDueTime=" + businessDueTime
               + ", industry=" + industry + ", tradingArea=" + tradingArea + ", turnover=" + turnover + ", size=" + size + ", reportCycle=" + reportCycle + ", reportTimer=" + reportTimer
               + ", riskWarning=" + riskWarning + ", quota=" + quota + ", feeRate=" + feeRate + ", loanCycle=" + loanCycle + ", merNum=" + merNum + ", status=" + status + ", createTime=" + createTime
               + ", lastModifyTime=" + lastModifyTime + "]";
    }

}