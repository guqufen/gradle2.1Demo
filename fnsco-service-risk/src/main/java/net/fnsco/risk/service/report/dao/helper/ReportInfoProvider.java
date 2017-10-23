package net.fnsco.risk.service.report.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.report.entity.ReportInfoDO;

public class ReportInfoProvider {

    private Logger              logger     = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "risk_report_info";

    public String update(Map<String, Object> params) {
        ReportInfoDO reportInfo = (ReportInfoDO) params.get("reportInfo");
        return new SQL() {
            {
                UPDATE(TABLE_NAME);
                if (StringUtils.isNotBlank(reportInfo.getMerNum())) {
                    SET("mer_num=#{reportInfo.merNum}");
                }
                if (reportInfo.getStatus() != null) {
                    SET("status=#{reportInfo.status}");
                }
                if (reportInfo.getDecorationLevel() != null) {
                    SET("decoration_level=#{reportInfo.decorationLevel}");
                }
                if (reportInfo.getCreateTime() != null) {
                    SET("create_time=#{reportInfo.createTime}");
                }
                if (reportInfo.getLastModifyTime() != null) {
                    SET("last_modify_time=#{reportInfo.lastModifyTime}");
                }
                if (StringUtils.isNotBlank(reportInfo.getMerName())) {
                    SET("mer_name=#{reportInfo.merName}");
                }
                if (StringUtils.isNotBlank(reportInfo.getBusinessLicenseNum())) {
                    SET("business_license_num=#{reportInfo.businessLicenseNum}");
                }
                if (StringUtils.isNotBlank(reportInfo.getBusinessAddress())) {
                    SET("business_address=#{reportInfo.businessAddress}");
                }
                if (StringUtils.isNotBlank(reportInfo.getBusinessDueTime())) {
                    SET("business_due_time=#{reportInfo.businessDueTime}");
                }
                if (StringUtils.isNotBlank(reportInfo.getIndustry())) {
                    SET("industry=#{reportInfo.industry}");
                }
                if (StringUtils.isNotBlank(reportInfo.getTradingArea())) {
                    SET("trading_area=#{reportInfo.tradingArea}");
                }
                if (StringUtils.isNotBlank(reportInfo.getTurnover())) {
                    SET("turnover=#{reportInfo.turnover}");
                }
                if (reportInfo.getSize() != null) {
                    SET("size=#{reportInfo.size}");
                }
                if (StringUtils.isNotBlank(reportInfo.getReportCycle())) {
                    SET("report_cycle=#{reportInfo.reportCycle}");
                }
                if (StringUtils.isNotBlank(reportInfo.getReportTimer())) {
                    SET("report_timer=#{reportInfo.reportTimer}");
                }
                if (StringUtils.isNotBlank(reportInfo.getRiskWarning())) {
                    SET("risk_warning=#{reportInfo.riskWarning}");
                }
                if (StringUtils.isNotBlank(reportInfo.getQuota())) {
                    SET("quota=#{reportInfo.quota}");
                }
                if (StringUtils.isNotBlank(reportInfo.getFeeRate())) {
                    SET("fee_rate=#{reportInfo.feeRate}");
                }
                if (StringUtils.isNotBlank(reportInfo.getLoanCycle())) {
                    SET("loan_cycle=#{reportInfo.loanCycle}");
                }
                if (reportInfo.getDecorationLevel() != null) {
                    SET("decoration_level=#{reportInfo.decorationLevel}");
                }
                WHERE("id = #{reportInfo.id}");
            }
        }.toString();
    }

    public String pageList(Map<String, Object> params) {
        ReportInfoDO reportInfo = (ReportInfoDO) params.get("reportInfo");
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
        return new SQL() {
            {
                SELECT("*");
                FROM(TABLE_NAME);
                if (StringUtils.isNotBlank(reportInfo.getMerNum())) {
                    WHERE("mer_num=#{reportInfo.merNum}");
                }
                if (reportInfo.getCreateTime() != null) {
                    WHERE("create_time=#{reportInfo.createTime}");
                }
                if (reportInfo.getLastModifyTime() != null) {
                    WHERE("last_modify_time=#{reportInfo.lastModifyTime}");
                }
                if (reportInfo.getId() != null) {
                    WHERE("id=#{reportInfo.id}");
                }
                if (StringUtils.isNotBlank(reportInfo.getMerName())) {
                    WHERE("mer_name like CONCAT('%',#{reportInfo.merName},'%')");
                }
                if (StringUtils.isNotBlank(reportInfo.getBusinessLicenseNum())) {
                    WHERE("business_license_num like CONCAT('%',#{reportInfo.businessLicenseNum},'%')");
                }
                if (StringUtils.isNotBlank(reportInfo.getBusinessAddress())) {
                    WHERE("business_address=#{reportInfo.businessAddress}");
                }
                if (StringUtils.isNotBlank(reportInfo.getBusinessDueTime())) {
                    WHERE("business_due_time=#{reportInfo.businessDueTime}");
                }
                if (StringUtils.isNotBlank(reportInfo.getIndustry())) {
                    WHERE("industry=#{reportInfo.industry}");
                }
                if (StringUtils.isNotBlank(reportInfo.getTradingArea())) {
                    WHERE("trading_area like CONCAT('%', #{reportInfo.tradingArea}, '%')");
                }
                if (StringUtils.isNotBlank(reportInfo.getTurnover())) {
                    WHERE("turnover=#{reportInfo.turnover}");
                }
                if (reportInfo.getSize() != null) {
                    WHERE("size=#{reportInfo.size}");
                }
                if (StringUtils.isNotBlank(reportInfo.getReportCycle())) {
                    WHERE("report_cycle=#{reportInfo.reportCycle}");
                }
                if (StringUtils.isNotBlank(reportInfo.getReportTimer())) {
                    WHERE("report_timer=#{reportInfo.reportTimer}");
                }
                if (StringUtils.isNotBlank(reportInfo.getRiskWarning())) {
                    WHERE("risk_warning=#{reportInfo.riskWarning}");
                }
                if (StringUtils.isNotBlank(reportInfo.getQuota())) {
                    WHERE("quota=#{reportInfo.quota}");
                }
                if (StringUtils.isNotBlank(reportInfo.getFeeRate())) {
                    WHERE("fee_rate=#{reportInfo.feeRate}");
                }
                if (StringUtils.isNotBlank(reportInfo.getLoanCycle())) {
                    WHERE("loan_cycle=#{reportInfo.loanCycle}");
                }
                if (reportInfo.getAgentId() != null) {
                    WHERE("inner_code in (SELECT inner_code FROM risk_user_merc_rel WHERE agent_id=#{reportInfo.agentId})");
                }
                ORDER_BY("id desc limit " + start + ", " + limit);
            }
        }.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        ReportInfoDO reportInfo = (ReportInfoDO) params.get("reportInfo");
        return new SQL() {
            {
                SELECT("count(1)");
                FROM(TABLE_NAME);
                if (StringUtils.isNotBlank(reportInfo.getMerNum())) {
                    WHERE("mer_num=#{reportInfo.merNum}");
                }
                if (reportInfo.getStatus() != null) {
                    WHERE("status=#{reportInfo.status}");
                }
                if (reportInfo.getCreateTime() != null) {
                    WHERE("create_time=#{reportInfo.createTime}");
                }
                if (reportInfo.getLastModifyTime() != null) {
                    WHERE("last_modify_time=#{reportInfo.lastModifyTime}");
                }
                if (reportInfo.getId() != null) {
                    WHERE("id=#{reportInfo.id}");
                }
                if (StringUtils.isNotBlank(reportInfo.getMerName())) {
                    WHERE("mer_name like CONCAT('%',#{reportInfo.merName},'%')");
                }
                if (StringUtils.isNotBlank(reportInfo.getBusinessLicenseNum())) {
                    WHERE("business_license_num like CONCAT('%',#{reportInfo.businessLicenseNum},'%')");
                }
                if (StringUtils.isNotBlank(reportInfo.getBusinessAddress())) {
                    WHERE("business_address=#{reportInfo.businessAddress}");
                }
                if (StringUtils.isNotBlank(reportInfo.getBusinessDueTime())) {
                    WHERE("business_due_time=#{reportInfo.businessDueTime}");
                }
                if (StringUtils.isNotBlank(reportInfo.getIndustry())) {
                    WHERE("industry=#{reportInfo.industry}");
                }
                if (StringUtils.isNotBlank(reportInfo.getTradingArea())) {
                    WHERE("trading_area like CONCAT('%', #{reportInfo.tradingArea}, '%')");
                }
                if (StringUtils.isNotBlank(reportInfo.getTurnover())) {
                    WHERE("turnover=#{reportInfo.turnover}");
                }
                if (reportInfo.getSize() != null) {
                    WHERE("size=#{reportInfo.size}");
                }
                if (StringUtils.isNotBlank(reportInfo.getReportCycle())) {
                    WHERE("report_cycle=#{reportInfo.reportCycle}");
                }
                if (StringUtils.isNotBlank(reportInfo.getReportTimer())) {
                    WHERE("report_timer=#{reportInfo.reportTimer}");
                }
                if (StringUtils.isNotBlank(reportInfo.getRiskWarning())) {
                    WHERE("risk_warning=#{reportInfo.riskWarning}");
                }
                if (StringUtils.isNotBlank(reportInfo.getQuota())) {
                    WHERE("quota=#{reportInfo.quota}");
                }
                if (StringUtils.isNotBlank(reportInfo.getFeeRate())) {
                    WHERE("fee_rate=#{reportInfo.feeRate}");
                }
                if (StringUtils.isNotBlank(reportInfo.getLoanCycle())) {
                    WHERE("loan_cycle=#{reportInfo.loanCycle}");
                }
                if (reportInfo.getAgentId() != null) {
                    WHERE("inner_code in (SELECT inner_code FROM risk_user_merc_rel WHERE agent_id=#{reportInfo.agentId})");
                }
            }
        }.toString();
    }

    public String pageListBack(Map<String, Object> params) {
        ReportInfoDO reportInfo = (ReportInfoDO) params.get("reportInfo");
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
        return new SQL() {
            {
                SELECT("r.mer_num, " + "r.create_time, " + "r.last_modify_time, " + "r.id, " + "r.mer_name, " + "r.business_license_num, " + "r.business_address, " + "r.business_due_time,"
                       + "r.trading_area, " + "r.turnover, " + "r.size, " + "r.report_cycle, " + "r.report_timer, " + "r.risk_warning, " + "r.quota, " + "r.fee_rate, " + "r.loan_cycle, " + "r.status,"
                       + "(select `first` from sys_industry i where id = r.industry) as industry");
                FROM(TABLE_NAME + " r");
                if (StringUtils.isNotBlank(reportInfo.getMerNum())) {
                    WHERE("mer_num=#{reportInfo.merNum}");
                }
                if (reportInfo.getCreateTime() != null) {
                    WHERE("create_time=#{reportInfo.createTime}");
                }
                if (reportInfo.getLastModifyTime() != null) {
                    WHERE("last_modify_time=#{reportInfo.lastModifyTime}");
                }
                if (reportInfo.getId() != null) {
                    WHERE("id=#{reportInfo.id}");
                }
                if (StringUtils.isNotBlank(reportInfo.getMerName())) {
                    WHERE("mer_name like CONCAT('%',#{reportInfo.merName},'%')");
                }
                if (StringUtils.isNotBlank(reportInfo.getBusinessLicenseNum())) {
                    WHERE("business_license_num like CONCAT('%',#{reportInfo.businessLicenseNum},'%')");
                }
                if (StringUtils.isNotBlank(reportInfo.getBusinessAddress())) {
                    WHERE("business_address=#{reportInfo.businessAddress}");
                }
                if (StringUtils.isNotBlank(reportInfo.getBusinessDueTime())) {
                    WHERE("business_due_time=#{reportInfo.businessDueTime}");
                }
                if (StringUtils.isNotBlank(reportInfo.getIndustry())) {
                    WHERE("industry=#{reportInfo.industry}");
                }
                if (StringUtils.isNotBlank(reportInfo.getTradingArea())) {
                    WHERE("trading_area like CONCAT('%', #{reportInfo.tradingArea}, '%')");
                }
                if (StringUtils.isNotBlank(reportInfo.getTurnover())) {
                    WHERE("turnover=#{reportInfo.turnover}");
                }
                if (reportInfo.getSize() != null) {
                    WHERE("size=#{reportInfo.size}");
                }
                if (StringUtils.isNotBlank(reportInfo.getReportCycle())) {
                    WHERE("report_cycle=#{reportInfo.reportCycle}");
                }
                if (StringUtils.isNotBlank(reportInfo.getReportTimer())) {
                    WHERE("report_timer=#{reportInfo.reportTimer}");
                }
                if (StringUtils.isNotBlank(reportInfo.getRiskWarning())) {
                    WHERE("risk_warning=#{reportInfo.riskWarning}");
                }
                if (StringUtils.isNotBlank(reportInfo.getQuota())) {
                    WHERE("quota=#{reportInfo.quota}");
                }
                if (StringUtils.isNotBlank(reportInfo.getFeeRate())) {
                    WHERE("fee_rate=#{reportInfo.feeRate}");
                }
                if (StringUtils.isNotBlank(reportInfo.getLoanCycle())) {
                    WHERE("loan_cycle=#{reportInfo.loanCycle}");
                }
                if (reportInfo.getCustomerType() == 1) {
                    if (reportInfo.getStatus() != null) {
                        WHERE("status=#{reportInfo.status}");
                    } else {
                        WHERE("status in (0)");
                    }
                }
                if (reportInfo.getCustomerType() == 2) {
                    if (reportInfo.getStatus() != null) {
                        WHERE("status=#{reportInfo.status}");
                    } else {
                        WHERE("status in (2, 4)");
                    }
                }
                ORDER_BY("id desc limit " + start + ", " + limit);
            }
        }.toString();
    }

    public String pageListCountBack(Map<String, Object> params) {
        ReportInfoDO reportInfo = (ReportInfoDO) params.get("reportInfo");
        return new SQL() {
            {
                SELECT("count(1)");
                FROM(TABLE_NAME);
                if (StringUtils.isNotBlank(reportInfo.getMerNum())) {
                    WHERE("mer_num=#{reportInfo.merNum}");
                }
                if (reportInfo.getStatus() != null) {
                    WHERE("status=#{reportInfo.status}");
                }
                if (reportInfo.getCreateTime() != null) {
                    WHERE("create_time=#{reportInfo.createTime}");
                }
                if (reportInfo.getLastModifyTime() != null) {
                    WHERE("last_modify_time=#{reportInfo.lastModifyTime}");
                }
                if (reportInfo.getId() != null) {
                    WHERE("id=#{reportInfo.id}");
                }
                if (StringUtils.isNotBlank(reportInfo.getMerName())) {
                    WHERE("mer_name like CONCAT('%', #{reportInfo.merName}, '%')");
                }
                if (StringUtils.isNotBlank(reportInfo.getBusinessLicenseNum())) {
                    WHERE("business_license_num LIKE concat('%', #{reportInfo.businessLicenseNum}, '%')");
                }
                if (StringUtils.isNotBlank(reportInfo.getBusinessAddress())) {
                    WHERE("business_address=#{reportInfo.businessAddress}");
                }
                if (StringUtils.isNotBlank(reportInfo.getBusinessDueTime())) {
                    WHERE("business_due_time=#{reportInfo.businessDueTime}");
                }
                if (StringUtils.isNotBlank(reportInfo.getIndustry())) {
                    WHERE("industry=#{reportInfo.industry}");
                }
                if (StringUtils.isNotBlank(reportInfo.getTradingArea())) {
                    WHERE("trading_area like CONCAT('%', #{reportInfo.tradingArea}, '%')");
                }
                if (StringUtils.isNotBlank(reportInfo.getTurnover())) {
                    WHERE("turnover=#{reportInfo.turnover}");
                }
                if (reportInfo.getSize() != null) {
                    WHERE("size=#{reportInfo.size}");
                }
                if (StringUtils.isNotBlank(reportInfo.getReportCycle())) {
                    WHERE("report_cycle=#{reportInfo.reportCycle}");
                }
                if (StringUtils.isNotBlank(reportInfo.getReportTimer())) {
                    WHERE("report_timer=#{reportInfo.reportTimer}");
                }
                if (StringUtils.isNotBlank(reportInfo.getRiskWarning())) {
                    WHERE("risk_warning=#{reportInfo.riskWarning}");
                }
                if (StringUtils.isNotBlank(reportInfo.getQuota())) {
                    WHERE("quota=#{reportInfo.quota}");
                }
                if (StringUtils.isNotBlank(reportInfo.getFeeRate())) {
                    WHERE("fee_rate=#{reportInfo.feeRate}");
                }
                if (StringUtils.isNotBlank(reportInfo.getLoanCycle())) {
                    WHERE("loan_cycle=#{reportInfo.loanCycle}");
                }
                if (reportInfo.getCustomerType() == 1) {
                    if (reportInfo.getStatus() != null) {
                        WHERE("status=#{reportInfo.status}");
                    } else {
                        WHERE("status in (0)");
                    }
                }
                if (reportInfo.getCustomerType() == 2) {
                    if (reportInfo.getStatus() != null) {
                        WHERE("status=#{reportInfo.status}");
                    } else {
                        WHERE("status in (2, 4)");
                    }
                }
            }
        }.toString();
    }

    //查询所有有3个月交易流水的商户信息
    /*SELECT
    tt.*,
    (select report.trading_area from risk_report_info report where tt.inner_code = report.inner_code order by create_time desc limit 1),
    (select report.industry from risk_report_info report where tt.inner_code = report.inner_code order by create_time desc limit 1),
    (select report.size from risk_report_info report where tt.inner_code = report.inner_code order by create_time desc limit 1)
    FROM
    (
        SELECT
            c.id,c.inner_code,c.mer_name,c.business_license_num, (
                SELECT
                    MAX(time_stamp) 
                FROM
                    t_trade_data t
                WHERE
                    t.inner_code = c.inner_code
            ) as maxTime
        FROM
            m_merchant_core c
        where c.inner_code in (select inner_code from risk_user_merc_rel rel where rel.inner_code=c.inner_code and agent_id='2')
    ) tt 
    WHERE
    tt.maxTime >=SUBDATE(CURDATE(),INTERVAL 3 month)
    order by tt.id*/
    public String pageListAllMerc(Map<String, Object> params) {
        ReportInfoDO reportInfo = (ReportInfoDO) params.get("reportInfo");
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
        return new SQL() {
            {
                SELECT( "tt.*," + "(select report.trading_area from risk_report_info report where tt.inner_code = report.inner_code order by create_time desc limit 1) trading_area,"
                       + "(select report.industry from risk_report_info report where tt.inner_code = report.inner_code order by create_time desc limit 1) industry,"
                       + "(select report.size from risk_report_info report where tt.inner_code = report.inner_code order by create_time desc limit 1) size " + "FROM " + "( " + "SELECT "
                       + "c.id,c.inner_code,c.mer_name,c.business_license_num, (" + "SELECT " + "MAX(time_stamp) " + "FROM " + "t_trade_data t " + "WHERE " + "t.inner_code = c.inner_code "
                       + ") as maxTime " + "FROM " + "m_merchant_core c " + "where c.inner_code in (select inner_code from risk_user_merc_rel rel where rel.inner_code=c.inner_code and agent_id='"
                       + reportInfo.getAgentId() + "') " + ") tt  " + "WHERE " + "tt.maxTime >=SUBDATE(CURDATE(),INTERVAL 3 month)");
                ORDER_BY(" tt.id desc limit " + start + ", " + limit);
            }
        }.toString();
    }

    //查询所有有3个月交易流水的商户信息
    public String pageListAllMercCount(Map<String, Object> params) {
        ReportInfoDO reportInfo = (ReportInfoDO) params.get("reportInfo");
        return new SQL() {
            {
                SELECT( "count(1) " + " FROM " + "( " + "SELECT " + "c.id,c.inner_code,c.mer_name,c.business_license_num, (" + "SELECT " + "MAX(time_stamp) " + "FROM " + "t_trade_data t "
                       + "WHERE " + "t.inner_code = c.inner_code " + ") as maxTime " + "FROM " + "m_merchant_core c "
                       + "where c.inner_code in (select inner_code from risk_user_merc_rel rel where rel.inner_code=c.inner_code and agent_id='" + reportInfo.getAgentId() + "') " + ") tt  " + "WHERE "
                       + "tt.maxTime >=SUBDATE(CURDATE(),INTERVAL 3 month)");

            }
        }.toString();

    }

    //按条件查询所有有3个月交易流水的商户信息
    /*SELECT
    tt.*,report.trading_area ,report.industry ,report.size 
    FROM
    (
        SELECT
            c.id,c.inner_code,c.mer_name,c.business_license_num, (
                SELECT
                    MAX(time_stamp) 
                FROM
                    t_trade_data t
                WHERE
                    t.inner_code = c.inner_code
            ) as maxTime
        FROM
            m_merchant_core c
        where c.inner_code in (select inner_code from risk_user_merc_rel rel where rel.inner_code=c.inner_code and agent_id='2')
    ) tt ,(select * from risk_report_info where id in (select max(id) from risk_report_info group by id)) report  
    WHERE
    tt.maxTime >=SUBDATE(CURDATE(),INTERVAL 3 month)
    and tt.inner_code = report.inner_code
    order by tt.id*/
    public String pageListMercByCondition(Map<String, Object> params) {
        ReportInfoDO reportInfo = (ReportInfoDO) params.get("reportInfo");
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
        return new SQL() {
            {
                SELECT( "tt.*,report.trading_area ,report.industry ,report.size " + " FROM " + "( " + "SELECT " + "c.inner_code,c.legal_person,c.mer_name,c.business_license_num, ("
                       + "SELECT " + "MAX(time_stamp) " + "FROM " + "t_trade_data t " + "WHERE " + "t.inner_code = c.inner_code " + ") as maxTime " + "FROM " + "m_merchant_core c "
                       + "where c.inner_code in (select inner_code from risk_user_merc_rel rel where rel.inner_code=c.inner_code and agent_id='" + reportInfo.getAgentId() + "') "
                       + ") tt ,(select * from risk_report_info where id in (select max(id) from risk_report_info group by id)) report " + "WHERE " + "tt.maxTime >=SUBDATE(CURDATE(),INTERVAL 3 month) and tt.inner_code = report.inner_code ");

                if (StringUtils.isNotBlank(reportInfo.getMerNum())) {
                    WHERE("report.mer_num=#{reportInfo.merNum}");
                }
                if (reportInfo.getCreateTime() != null) {
                    WHERE("report.create_time=#{reportInfo.createTime}");
                }
                if (reportInfo.getLastModifyTime() != null) {
                    WHERE("report.last_modify_time=#{reportInfo.lastModifyTime}");
                }
                if (reportInfo.getId() != null) {
                    WHERE("report.id=#{reportInfo.id}");
                }
                if (StringUtils.isNotBlank(reportInfo.getMerName())) {
                    WHERE("report.mer_name like CONCAT('%',#{reportInfo.merName},'%')");
                }
                if (StringUtils.isNotBlank(reportInfo.getBusinessLicenseNum())) {
                    WHERE("report.business_license_num like CONCAT('%',#{reportInfo.businessLicenseNum},'%')");
                }
                if (StringUtils.isNotBlank(reportInfo.getBusinessAddress())) {
                    WHERE("report.business_address=#{reportInfo.businessAddress}");
                }
                if (StringUtils.isNotBlank(reportInfo.getBusinessDueTime())) {
                    WHERE("report.business_due_time=#{reportInfo.businessDueTime}");
                }
                if (StringUtils.isNotBlank(reportInfo.getIndustry())) {
                    WHERE("report.industry=#{reportInfo.industry}");
                }
                if (StringUtils.isNotBlank(reportInfo.getTradingArea())) {
                    WHERE("report.trading_area like CONCAT('%', #{reportInfo.tradingArea}, '%')");
                }
                if (StringUtils.isNotBlank(reportInfo.getTurnover())) {
                    WHERE("report.turnover=#{reportInfo.turnover}");
                }
                if (reportInfo.getSize() != null) {
                    WHERE("report.size=#{reportInfo.size}");
                }
                if (StringUtils.isNotBlank(reportInfo.getReportCycle())) {
                    WHERE("report_cycle=#{reportInfo.reportCycle}");
                }
                if (StringUtils.isNotBlank(reportInfo.getReportTimer())) {
                    WHERE("report.report_timer=#{reportInfo.reportTimer}");
                }
                if (StringUtils.isNotBlank(reportInfo.getRiskWarning())) {
                    WHERE("report.risk_warning=#{reportInfo.riskWarning}");
                }
                if (StringUtils.isNotBlank(reportInfo.getQuota())) {
                    WHERE("report.quota=#{reportInfo.quota}");
                }
                if (StringUtils.isNotBlank(reportInfo.getFeeRate())) {
                    WHERE("report.fee_rate=#{reportInfo.feeRate}");
                }
                if (StringUtils.isNotBlank(reportInfo.getLoanCycle())) {
                    WHERE("report.loan_cycle=#{reportInfo.loanCycle}");
                }
                if (reportInfo.getCustomerType() == 1) {
                    if (reportInfo.getStatus() != null) {
                        WHERE("report.status=#{reportInfo.status}");
                    } else {
                        WHERE("report.status in (0)");
                    }
                }
                if (reportInfo.getCustomerType() == 2) {
                    if (reportInfo.getStatus() != null) {
                        WHERE("report.status=#{reportInfo.status}");
                    } else {
                        WHERE("report.status in (2, 4)");
                    }
                }
                ORDER_BY("report.id desc limit " + start + ", " + limit);
            }
        }.toString();
    }

    public String pageListMercByConditionCount(Map<String, Object> params) {
        ReportInfoDO reportInfo = (ReportInfoDO) params.get("reportInfo");
        return new SQL() {
            {
                SELECT( "count(1) " + " FROM " + "( " + "SELECT " + "c.id,c.inner_code,c.mer_name,c.business_license_num, (" + "SELECT " + "MAX(time_stamp) " + "FROM " + "t_trade_data t "
                       + "WHERE " + "t.inner_code = c.inner_code " + ") as maxTime " + "FROM " + "m_merchant_core c "
                       + "where c.inner_code in (select inner_code from risk_user_merc_rel rel where rel.inner_code=c.inner_code and agent_id='" + reportInfo.getAgentId() + "') "
                       + ") tt ,(select * from risk_report_info where id in (select max(id) from risk_report_info group by id)) report " + "WHERE " + "tt.maxTime >=SUBDATE(CURDATE(),INTERVAL 3 month)  and tt.inner_code = report.inner_code ");

                if (StringUtils.isNotBlank(reportInfo.getMerNum())) {
                    WHERE("report.mer_num=#{reportInfo.merNum}");
                }
                if (reportInfo.getStatus() != null) {
                    WHERE("report.status=#{reportInfo.status}");
                }
                if (reportInfo.getCreateTime() != null) {
                    WHERE("report.create_time=#{reportInfo.createTime}");
                }
                if (reportInfo.getLastModifyTime() != null) {
                    WHERE("report.last_modify_time=#{reportInfo.lastModifyTime}");
                }
                if (reportInfo.getId() != null) {
                    WHERE("report.id=#{reportInfo.id}");
                }
                if (StringUtils.isNotBlank(reportInfo.getMerName())) {
                    WHERE("report.mer_name like CONCAT('%', #{reportInfo.merName}, '%')");
                }
                if (StringUtils.isNotBlank(reportInfo.getBusinessLicenseNum())) {
                    WHERE("report.business_license_num LIKE concat('%', #{reportInfo.businessLicenseNum}, '%')");
                }
                if (StringUtils.isNotBlank(reportInfo.getBusinessAddress())) {
                    WHERE("report.business_address=#{reportInfo.businessAddress}");
                }
                if (StringUtils.isNotBlank(reportInfo.getBusinessDueTime())) {
                    WHERE("report.business_due_time=#{reportInfo.businessDueTime}");
                }
                if (StringUtils.isNotBlank(reportInfo.getIndustry())) {
                    WHERE("report.industry=#{reportInfo.industry}");
                }
                if (StringUtils.isNotBlank(reportInfo.getTradingArea())) {
                    WHERE("report.trading_area like CONCAT('%', #{reportInfo.tradingArea}, '%')");
                }
                if (StringUtils.isNotBlank(reportInfo.getTurnover())) {
                    WHERE("report.turnover=#{reportInfo.turnover}");
                }
                if (reportInfo.getSize() != null) {
                    WHERE("report.size=#{reportInfo.size}");
                }
                if (StringUtils.isNotBlank(reportInfo.getReportCycle())) {
                    WHERE("report.report_cycle=#{reportInfo.reportCycle}");
                }
                if (StringUtils.isNotBlank(reportInfo.getReportTimer())) {
                    WHERE("report.report_timer=#{reportInfo.reportTimer}");
                }
                if (StringUtils.isNotBlank(reportInfo.getRiskWarning())) {
                    WHERE("report.risk_warning=#{reportInfo.riskWarning}");
                }
                if (StringUtils.isNotBlank(reportInfo.getQuota())) {
                    WHERE("report.quota=#{reportInfo.quota}");
                }
                if (StringUtils.isNotBlank(reportInfo.getFeeRate())) {
                    WHERE("report.fee_rate=#{reportInfo.feeRate}");
                }
                if (StringUtils.isNotBlank(reportInfo.getLoanCycle())) {
                    WHERE("report.loan_cycle=#{reportInfo.loanCycle}");
                }
                if (reportInfo.getCustomerType() == 1) {
                    if (reportInfo.getStatus() != null) {
                        WHERE("report.status=#{reportInfo.status}");
                    } else {
                        WHERE("report.status in (0)");
                    }
                }
                if (reportInfo.getCustomerType() == 2) {
                    if (reportInfo.getStatus() != null) {
                        WHERE("report.status=#{reportInfo.status}");
                    } else {
                        WHERE("report.status in (2, 4)");
                    }
                }
            }
        }.toString();
    }
}
