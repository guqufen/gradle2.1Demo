package net.fnsco.risk.service.report.entity;

import java.math.BigDecimal;
import java.util.Date;

public class YearReportDO {
    private String date;
    private BigDecimal Turnover;
    public BigDecimal getTurnover() {
        return Turnover;
    }
    public void setTurnover(BigDecimal turnover) {
        Turnover = turnover;
    }
    public String getDate() {
        return date;
    }
    @Override
    public String toString() {
        return "YearReportDO [date=" + date + ", Turnover=" + Turnover + "]";
    }
    public void setDate(String date) {
        this.date = date;
    }


}
