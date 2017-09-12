package net.fnsco.risk.service.report.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.report.entity.ReportRepaymentHistoryDO;
public class ReportRepaymentHistoryProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "risk_report_repayment_history";

    public String update(Map<String, Object> params) {
        ReportRepaymentHistoryDO reportRepaymentHistory = (ReportRepaymentHistoryDO) params.get("reportRepaymentHistory");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (reportRepaymentHistory.getReportId() != null) {
            SET("report_id=#{reportRepaymentHistory.reportId}");
        }
        if (reportRepaymentHistory.getMonthOne() != null) {
            SET("month_one=#{reportRepaymentHistory.monthOne}");
        }
        if (reportRepaymentHistory.getMonthTwo() != null) {
            SET("month_two=#{reportRepaymentHistory.monthTwo}");
        }
        if (reportRepaymentHistory.getMonthThree() != null) {
            SET("month_three=#{reportRepaymentHistory.monthThree}");
        }
        if (reportRepaymentHistory.getMonthFore() != null) {
            SET("month_fore=#{reportRepaymentHistory.monthFore}");
        }
        if (reportRepaymentHistory.getMonthFive() != null) {
            SET("month_five=#{reportRepaymentHistory.monthFive}");
        }
        if (reportRepaymentHistory.getMonthSix() != null) {
            SET("month_six=#{reportRepaymentHistory.monthSix}");
        }
        if (reportRepaymentHistory.getMonthSeven() != null) {
            SET("month_seven=#{reportRepaymentHistory.monthSeven}");
        }
        if (reportRepaymentHistory.getMonthEight() != null) {
            SET("month_eight=#{reportRepaymentHistory.monthEight}");
        }
        if (reportRepaymentHistory.getMonthNine() != null) {
            SET("month_nine=#{reportRepaymentHistory.monthNine}");
        }
        if (reportRepaymentHistory.getMonthTen() != null) {
            SET("month_ten=#{reportRepaymentHistory.monthTen}");
        }
        if (reportRepaymentHistory.getMonthEleven() != null) {
            SET("month_eleven=#{reportRepaymentHistory.monthEleven}");
        }
        if (reportRepaymentHistory.getMonthTwelve() != null) {
            SET("month_twelve=#{reportRepaymentHistory.monthTwelve}");
        }
        WHERE("id = #{reportRepaymentHistory.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        ReportRepaymentHistoryDO reportRepaymentHistory = (ReportRepaymentHistoryDO) params.get("reportRepaymentHistory");
        Integer pageNum = (Integer) params.get("pageNum");
        Integer pageSize = (Integer) params.get("pageSize");
        if (pageNum == null || pageNum == 0) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 20;
        }
        int start = (pageNum - 1) * pageSize;
        int limit = pageSize;
        return new SQL() {{
        SELECT("*");
        FROM(TABLE_NAME);
        if (reportRepaymentHistory.getId() != null) {
            WHERE("id=#{reportRepaymentHistory.id}");
        }
        if (reportRepaymentHistory.getReportId() != null) {
            WHERE("report_id=#{reportRepaymentHistory.reportId}");
        }
        if (reportRepaymentHistory.getMonthOne() != null) {
            WHERE("month_one=#{reportRepaymentHistory.monthOne}");
        }
        if (reportRepaymentHistory.getMonthTwo() != null) {
            WHERE("month_two=#{reportRepaymentHistory.monthTwo}");
        }
        if (reportRepaymentHistory.getMonthThree() != null) {
            WHERE("month_three=#{reportRepaymentHistory.monthThree}");
        }
        if (reportRepaymentHistory.getMonthFore() != null) {
            WHERE("month_fore=#{reportRepaymentHistory.monthFore}");
        }
        if (reportRepaymentHistory.getMonthFive() != null) {
            WHERE("month_five=#{reportRepaymentHistory.monthFive}");
        }
        if (reportRepaymentHistory.getMonthSix() != null) {
            WHERE("month_six=#{reportRepaymentHistory.monthSix}");
        }
        if (reportRepaymentHistory.getMonthSeven() != null) {
            WHERE("month_seven=#{reportRepaymentHistory.monthSeven}");
        }
        if (reportRepaymentHistory.getMonthEight() != null) {
            WHERE("month_eight=#{reportRepaymentHistory.monthEight}");
        }
        if (reportRepaymentHistory.getMonthNine() != null) {
            WHERE("month_nine=#{reportRepaymentHistory.monthNine}");
        }
        if (reportRepaymentHistory.getMonthTen() != null) {
            WHERE("month_ten=#{reportRepaymentHistory.monthTen}");
        }
        if (reportRepaymentHistory.getMonthEleven() != null) {
            WHERE("month_eleven=#{reportRepaymentHistory.monthEleven}");
        }
        if (reportRepaymentHistory.getMonthTwelve() != null) {
            WHERE("month_twelve=#{reportRepaymentHistory.monthTwelve}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        ReportRepaymentHistoryDO reportRepaymentHistory = (ReportRepaymentHistoryDO) params.get("reportRepaymentHistory");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (reportRepaymentHistory.getId() != null) {
            WHERE("id=#{reportRepaymentHistory.id}");
        }
        if (reportRepaymentHistory.getReportId() != null) {
            WHERE("report_id=#{reportRepaymentHistory.reportId}");
        }
        if (reportRepaymentHistory.getMonthOne() != null) {
            WHERE("month_one=#{reportRepaymentHistory.monthOne}");
        }
        if (reportRepaymentHistory.getMonthTwo() != null) {
            WHERE("month_two=#{reportRepaymentHistory.monthTwo}");
        }
        if (reportRepaymentHistory.getMonthThree() != null) {
            WHERE("month_three=#{reportRepaymentHistory.monthThree}");
        }
        if (reportRepaymentHistory.getMonthFore() != null) {
            WHERE("month_fore=#{reportRepaymentHistory.monthFore}");
        }
        if (reportRepaymentHistory.getMonthFive() != null) {
            WHERE("month_five=#{reportRepaymentHistory.monthFive}");
        }
        if (reportRepaymentHistory.getMonthSix() != null) {
            WHERE("month_six=#{reportRepaymentHistory.monthSix}");
        }
        if (reportRepaymentHistory.getMonthSeven() != null) {
            WHERE("month_seven=#{reportRepaymentHistory.monthSeven}");
        }
        if (reportRepaymentHistory.getMonthEight() != null) {
            WHERE("month_eight=#{reportRepaymentHistory.monthEight}");
        }
        if (reportRepaymentHistory.getMonthNine() != null) {
            WHERE("month_nine=#{reportRepaymentHistory.monthNine}");
        }
        if (reportRepaymentHistory.getMonthTen() != null) {
            WHERE("month_ten=#{reportRepaymentHistory.monthTen}");
        }
        if (reportRepaymentHistory.getMonthEleven() != null) {
            WHERE("month_eleven=#{reportRepaymentHistory.monthEleven}");
        }
        if (reportRepaymentHistory.getMonthTwelve() != null) {
            WHERE("month_twelve=#{reportRepaymentHistory.monthTwelve}");
        }
        }}.toString();
    }
}
