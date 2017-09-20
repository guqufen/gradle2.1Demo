package net.fnsco.risk.service.report.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ReportRepaymentHistoryDO {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 报告id
     */
    private Integer reportId;

    /**
     * 1月
     */
    private BigDecimal monthOne;

    /**
     * 2月
     */
    private BigDecimal monthTwo;

    /**
     * 3月
     */
    private BigDecimal monthThree;

    /**
     * 4月
     */
    private BigDecimal monthFore;

    /**
     * 5月
     */
    private BigDecimal monthFive;

    /**
     * 6月
     */
    private BigDecimal monthSix;

    /**
     * 7月
     */
    private BigDecimal monthSeven;

    /**
     * 8月
     */
    private BigDecimal monthEight;

    /**
     * 9月
     */
    private BigDecimal monthNine;

    /**
     * 10月
     */
    private BigDecimal monthTen;

    /**
     * 11月
     */
    private BigDecimal monthEleven;

    /**
     * 12月
     */
    private BigDecimal monthTwelve;
    
    /**
     * 最后修改时间
     */
    private Date lastModifyTime;

    /**
     * 最后修改时间，字符串形式yyyy-mm-dd hh:mm:ss
     */
    private String lastModifyTimeStr;
    
	public String getLastModifyTimeStr() {
		return lastModifyTimeStr;
	}

	public void setLastModifyTimeStr(String lastModifyTimeStr) {
		this.lastModifyTimeStr = lastModifyTimeStr;
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

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public BigDecimal getMonthOne() {
        return monthOne;
    }

    public void setMonthOne(BigDecimal monthOne) {
        this.monthOne = monthOne;
    }

    public BigDecimal getMonthTwo() {
        return monthTwo;
    }

    public void setMonthTwo(BigDecimal monthTwo) {
        this.monthTwo = monthTwo;
    }

    public BigDecimal getMonthThree() {
        return monthThree;
    }

    public void setMonthThree(BigDecimal monthThree) {
        this.monthThree = monthThree;
    }

    public BigDecimal getMonthFore() {
        return monthFore;
    }

    public void setMonthFore(BigDecimal monthFore) {
        this.monthFore = monthFore;
    }

    public BigDecimal getMonthFive() {
        return monthFive;
    }

    public void setMonthFive(BigDecimal monthFive) {
        this.monthFive = monthFive;
    }

    public BigDecimal getMonthSix() {
        return monthSix;
    }

    public void setMonthSix(BigDecimal monthSix) {
        this.monthSix = monthSix;
    }

    public BigDecimal getMonthSeven() {
        return monthSeven;
    }

    public void setMonthSeven(BigDecimal monthSeven) {
        this.monthSeven = monthSeven;
    }

    public BigDecimal getMonthEight() {
        return monthEight;
    }

    public void setMonthEight(BigDecimal monthEight) {
        this.monthEight = monthEight;
    }

    public BigDecimal getMonthNine() {
        return monthNine;
    }

    public void setMonthNine(BigDecimal monthNine) {
        this.monthNine = monthNine;
    }

    public BigDecimal getMonthTen() {
        return monthTen;
    }

    public void setMonthTen(BigDecimal monthTen) {
        this.monthTen = monthTen;
    }

    public BigDecimal getMonthEleven() {
        return monthEleven;
    }

    public void setMonthEleven(BigDecimal monthEleven) {
        this.monthEleven = monthEleven;
    }

    public BigDecimal getMonthTwelve() {
        return monthTwelve;
    }

    public void setMonthTwelve(BigDecimal monthTwelve) {
        this.monthTwelve = monthTwelve;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", reportId="+ reportId + ", monthOne="+ monthOne + ", monthTwo="+ monthTwo + ", monthThree="+ monthThree + ", monthFore="+ monthFore + ", monthFive="+ monthFive + ", monthSix="+ monthSix + ", monthSeven="+ monthSeven + ", monthEight="+ monthEight + ", monthNine="+ monthNine + ", monthTen="+ monthTen + ", monthEleven="+ monthEleven + ", monthTwelve="+ monthTwelve + "]";
    }
}