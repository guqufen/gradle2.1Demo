package net.fnsco.risk.service.report.entity;

import java.util.Date;

public class ReportInfoDO {
    //是否可以查看报告
    private Integer isCheck;
    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
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

    /**
     * 
     */
    private Integer id;

    /**
     * 商户名（必须40个字符）
     */
    private String merName;

    /**
     * 营业执照号码
     */
    private String businessLicenseNum;

    /**
     * 经营地址
     */
    private String businessAddress;

    /**
     * 营业期限
     */
    private String businessDueTime;

    /**
     * 行业
     */
    private String industry;

    /**
     * 商圈
     */
    private String tradingArea;

    /**
     * 营业额
     */
    private String turnover;

    /**
     * 规模
     */
    private Integer size;

    /**
     * 报告周期
     */
    private String reportCycle;

    /**
     * 报告时间
     */
    private String reportTimer;

    /**
     * 风险提醒
     */
    private String riskWarning;

    /**
     * 额度
     */
    private String quota;

    /**
     * 费率
     */
    private String feeRate;

    /**
     * 贷款周期
     */
    private String loanCycle;
    /**
     * 商户编码
     */
    private String merNum;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后修改时间
     */
    private Date lastModifyTime;
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
        return "ReportInfoDO [innerCode=" + innerCode + ", customerType=" + customerType + ", webUserOuterId=" + webUserOuterId + ", id=" + id + ", merName=" + merName + ", businessLicenseNum="
               + businessLicenseNum + ", businessAddress=" + businessAddress + ", businessDueTime=" + businessDueTime + ", industry=" + industry + ", tradingArea=" + tradingArea + ", turnover="
               + turnover + ", size=" + size + ", reportCycle=" + reportCycle + ", reportTimer=" + reportTimer + ", riskWarning=" + riskWarning + ", quota=" + quota + ", feeRate=" + feeRate
               + ", loanCycle=" + loanCycle + ", merNum=" + merNum + ", status=" + status + ", createTime=" + createTime + ", lastModifyTime=" + lastModifyTime + "]";
    }

}