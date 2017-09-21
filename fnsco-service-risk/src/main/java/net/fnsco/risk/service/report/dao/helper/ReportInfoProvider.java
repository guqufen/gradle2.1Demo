package net.fnsco.risk.service.report.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.report.entity.ReportInfoDO;
public class ReportInfoProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "risk_report_info";

    public String update(Map<String, Object> params) {
        ReportInfoDO reportInfo = (ReportInfoDO) params.get("reportInfo");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(reportInfo.getMerNum())){
            SET("mer_num=#{reportInfo.merNum}");
        }
        if (reportInfo.getStatus()!= null) {
            SET("status=#{reportInfo.status}");
        }
        if (reportInfo.getCreateTime()!= null) {
            SET("create_time=#{reportInfo.createTime}");
        }
        if (reportInfo.getLastModifyTime()!= null) {
            SET("last_modify_time=#{reportInfo.lastModifyTime}");
        }
        if (StringUtils.isNotBlank(reportInfo.getMerName())){
            SET("mer_name=#{reportInfo.merName}");
        }
        if (StringUtils.isNotBlank(reportInfo.getBusinessLicenseNum())){
            SET("business_license_num=#{reportInfo.businessLicenseNum}");
        }
        if (StringUtils.isNotBlank(reportInfo.getBusinessAddress())){
            SET("business_address=#{reportInfo.businessAddress}");
        }
        if (StringUtils.isNotBlank(reportInfo.getBusinessDueTime())){
            SET("business_due_time=#{reportInfo.businessDueTime}");
        }
        if (StringUtils.isNotBlank(reportInfo.getIndustry())){
            SET("industry=#{reportInfo.industry}");
        }
        if (StringUtils.isNotBlank(reportInfo.getTradingArea())){
            SET("trading_area=#{reportInfo.tradingArea}");
        }
        if (StringUtils.isNotBlank(reportInfo.getTurnover())){
            SET("turnover=#{reportInfo.turnover}");
        }
        if (reportInfo.getSize() != null) {
            SET("size=#{reportInfo.size}");
        }
        if (StringUtils.isNotBlank(reportInfo.getReportCycle())){
            SET("report_cycle=#{reportInfo.reportCycle}");
        }
        if (StringUtils.isNotBlank(reportInfo.getReportTimer())){
            SET("report_timer=#{reportInfo.reportTimer}");
        }
        if (StringUtils.isNotBlank(reportInfo.getRiskWarning())){
            SET("risk_warning=#{reportInfo.riskWarning}");
        }
        if (StringUtils.isNotBlank(reportInfo.getQuota())){
            SET("quota=#{reportInfo.quota}");
        }
        if (StringUtils.isNotBlank(reportInfo.getFeeRate())){
            SET("fee_rate=#{reportInfo.feeRate}");
        }
        if (StringUtils.isNotBlank(reportInfo.getLoanCycle())){
            SET("loan_cycle=#{reportInfo.loanCycle}");
        }
        WHERE("id = #{reportInfo.id}");
        }}.toString();
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
        return new SQL() {{
        SELECT("*");
        FROM(TABLE_NAME);
        if (StringUtils.isNotBlank(reportInfo.getMerNum())){
            WHERE("mer_num=#{reportInfo.merNum}");
        }
        if (reportInfo.getCreateTime()!= null) {
            WHERE("create_time=#{reportInfo.createTime}");
        }
        if (reportInfo.getLastModifyTime()!= null) {
            WHERE("last_modify_time=#{reportInfo.lastModifyTime}");
        }
        if (reportInfo.getId() != null) {
            WHERE("id=#{reportInfo.id}");
        }
        if (StringUtils.isNotBlank(reportInfo.getMerName())){
            WHERE("mer_name=#{reportInfo.merName}");
        }
        if (StringUtils.isNotBlank(reportInfo.getBusinessLicenseNum())){
            WHERE("business_license_num=#{reportInfo.businessLicenseNum}");
        }
        if (StringUtils.isNotBlank(reportInfo.getBusinessAddress())){
            WHERE("business_address=#{reportInfo.businessAddress}");
        }
        if (StringUtils.isNotBlank(reportInfo.getBusinessDueTime())){
            WHERE("business_due_time=#{reportInfo.businessDueTime}");
        }
        if (StringUtils.isNotBlank(reportInfo.getIndustry())){
            WHERE("industry=#{reportInfo.industry}");
        }
        if (StringUtils.isNotBlank(reportInfo.getTradingArea())){
            WHERE("trading_area=#{reportInfo.tradingArea}");
        }
        if (StringUtils.isNotBlank(reportInfo.getTurnover())){
            WHERE("turnover=#{reportInfo.turnover}");
        }
        if (reportInfo.getSize() != null) {
            WHERE("size=#{reportInfo.size}");
        }
        if (StringUtils.isNotBlank(reportInfo.getReportCycle())){
            WHERE("report_cycle=#{reportInfo.reportCycle}");
        }
        if (StringUtils.isNotBlank(reportInfo.getReportTimer())){
            WHERE("report_timer=#{reportInfo.reportTimer}");
        }
        if (StringUtils.isNotBlank(reportInfo.getRiskWarning())){
            WHERE("risk_warning=#{reportInfo.riskWarning}");
        }
        if (StringUtils.isNotBlank(reportInfo.getQuota())){
            WHERE("quota=#{reportInfo.quota}");
        }
        if (StringUtils.isNotBlank(reportInfo.getFeeRate())){
            WHERE("fee_rate=#{reportInfo.feeRate}");
        }
        if (StringUtils.isNotBlank(reportInfo.getLoanCycle())){
            WHERE("loan_cycle=#{reportInfo.loanCycle}");
        }
        if(reportInfo.getWebUserOuterId()!=null){
            WHERE("inner_code in (SELECT inner_code FROM risk_user_merc_rel WHERE web_user_outer_id=#{reportInfo.webUserOuterId})");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        ReportInfoDO reportInfo = (ReportInfoDO) params.get("reportInfo");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (StringUtils.isNotBlank(reportInfo.getMerNum())){
            WHERE("mer_num=#{reportInfo.merNum}");
        }
        if (reportInfo.getStatus()!= null) {
            WHERE("status=#{reportInfo.status}");
        }
        if (reportInfo.getCreateTime()!= null) {
            WHERE("create_time=#{reportInfo.createTime}");
        }
        if (reportInfo.getLastModifyTime()!= null) {
            WHERE("last_modify_time=#{reportInfo.lastModifyTime}");
        }
        if (reportInfo.getId() != null) {
            WHERE("id=#{reportInfo.id}");
        }
        if (StringUtils.isNotBlank(reportInfo.getMerName())){
            WHERE("mer_name=#{reportInfo.merName}");
        }
        if (StringUtils.isNotBlank(reportInfo.getBusinessLicenseNum())){
            WHERE("business_license_num=#{reportInfo.businessLicenseNum}");
        }
        if (StringUtils.isNotBlank(reportInfo.getBusinessAddress())){
            WHERE("business_address=#{reportInfo.businessAddress}");
        }
        if (StringUtils.isNotBlank(reportInfo.getBusinessDueTime())){
            WHERE("business_due_time=#{reportInfo.businessDueTime}");
        }
        if (StringUtils.isNotBlank(reportInfo.getIndustry())){
            WHERE("industry=#{reportInfo.industry}");
        }
        if (StringUtils.isNotBlank(reportInfo.getTradingArea())){
            WHERE("trading_area=#{reportInfo.tradingArea}");
        }
        if (StringUtils.isNotBlank(reportInfo.getTurnover())){
            WHERE("turnover=#{reportInfo.turnover}");
        }
        if (reportInfo.getSize() != null) {
            WHERE("size=#{reportInfo.size}");
        }
        if (StringUtils.isNotBlank(reportInfo.getReportCycle())){
            WHERE("report_cycle=#{reportInfo.reportCycle}");
        }
        if (StringUtils.isNotBlank(reportInfo.getReportTimer())){
            WHERE("report_timer=#{reportInfo.reportTimer}");
        }
        if (StringUtils.isNotBlank(reportInfo.getRiskWarning())){
            WHERE("risk_warning=#{reportInfo.riskWarning}");
        }
        if (StringUtils.isNotBlank(reportInfo.getQuota())){
            WHERE("quota=#{reportInfo.quota}");
        }
        if (StringUtils.isNotBlank(reportInfo.getFeeRate())){
            WHERE("fee_rate=#{reportInfo.feeRate}");
        }
        if (StringUtils.isNotBlank(reportInfo.getLoanCycle())){
            WHERE("loan_cycle=#{reportInfo.loanCycle}");
        }
        if(reportInfo.getWebUserOuterId()!=null){
            WHERE("inner_code in (SELECT inner_code FROM risk_user_merc_rel WHERE web_user_outer_id=#{reportInfo.webUserOuterId})");
        }
        }}.toString();
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
        return new SQL() {{
        SELECT("*");
        FROM(TABLE_NAME);
        if (StringUtils.isNotBlank(reportInfo.getMerNum())){
            WHERE("mer_num=#{reportInfo.merNum}");
        }
        if (reportInfo.getCreateTime()!= null) {
            WHERE("create_time=#{reportInfo.createTime}");
        }
        if (reportInfo.getLastModifyTime()!= null) {
            WHERE("last_modify_time=#{reportInfo.lastModifyTime}");
        }
        if (reportInfo.getId() != null) {
            WHERE("id=#{reportInfo.id}");
        }
        if (StringUtils.isNotBlank(reportInfo.getMerName())){
            WHERE("mer_name=#{reportInfo.merName}");
        }
        if (StringUtils.isNotBlank(reportInfo.getBusinessLicenseNum())){
            WHERE("business_license_num=#{reportInfo.businessLicenseNum}");
        }
        if (StringUtils.isNotBlank(reportInfo.getBusinessAddress())){
            WHERE("business_address=#{reportInfo.businessAddress}");
        }
        if (StringUtils.isNotBlank(reportInfo.getBusinessDueTime())){
            WHERE("business_due_time=#{reportInfo.businessDueTime}");
        }
        if (StringUtils.isNotBlank(reportInfo.getIndustry())){
            WHERE("industry=#{reportInfo.industry}");
        }
        if (StringUtils.isNotBlank(reportInfo.getTradingArea())){
            WHERE("trading_area=#{reportInfo.tradingArea}");
        }
        if (StringUtils.isNotBlank(reportInfo.getTurnover())){
            WHERE("turnover=#{reportInfo.turnover}");
        }
        if (reportInfo.getSize() != null) {
            WHERE("size=#{reportInfo.size}");
        }
        if (StringUtils.isNotBlank(reportInfo.getReportCycle())){
            WHERE("report_cycle=#{reportInfo.reportCycle}");
        }
        if (StringUtils.isNotBlank(reportInfo.getReportTimer())){
            WHERE("report_timer=#{reportInfo.reportTimer}");
        }
        if (StringUtils.isNotBlank(reportInfo.getRiskWarning())){
            WHERE("risk_warning=#{reportInfo.riskWarning}");
        }
        if (StringUtils.isNotBlank(reportInfo.getQuota())){
            WHERE("quota=#{reportInfo.quota}");
        }
        if (StringUtils.isNotBlank(reportInfo.getFeeRate())){
            WHERE("fee_rate=#{reportInfo.feeRate}");
        }
        if (StringUtils.isNotBlank(reportInfo.getLoanCycle())){
            WHERE("loan_cycle=#{reportInfo.loanCycle}");
        }
        if(reportInfo.getCustomerType()==1){
            if (reportInfo.getStatus()!= null) {
                WHERE("status=#{reportInfo.status}");
            }else{
                WHERE("status in (0)");
            }
        }
        if(reportInfo.getCustomerType()==2){
            if (reportInfo.getStatus()!= null) {
                WHERE("status=#{reportInfo.status}");
            }else{
                WHERE("status in (2,3)");
            }
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCountBack(Map<String, Object> params) {
        ReportInfoDO reportInfo = (ReportInfoDO) params.get("reportInfo");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (StringUtils.isNotBlank(reportInfo.getMerNum())){
            WHERE("mer_num=#{reportInfo.merNum}");
        }
        if (reportInfo.getStatus()!= null) {
            WHERE("status=#{reportInfo.status}");
        }
        if (reportInfo.getCreateTime()!= null) {
            WHERE("create_time=#{reportInfo.createTime}");
        }
        if (reportInfo.getLastModifyTime()!= null) {
            WHERE("last_modify_time=#{reportInfo.lastModifyTime}");
        }
        if (reportInfo.getId() != null) {
            WHERE("id=#{reportInfo.id}");
        }
        if (StringUtils.isNotBlank(reportInfo.getMerName())){
            WHERE("mer_name like CONCAT('%', #{reportInfo.merName}, '%')");
        }
        if (StringUtils.isNotBlank(reportInfo.getBusinessLicenseNum())){
            WHERE("business_license_num LIKE concat('%', #{reportInfo.businessLicenseNum}, '%')");
        }
        if (StringUtils.isNotBlank(reportInfo.getBusinessAddress())){
            WHERE("business_address=#{reportInfo.businessAddress}");
        }
        if (StringUtils.isNotBlank(reportInfo.getBusinessDueTime())){
            WHERE("business_due_time=#{reportInfo.businessDueTime}");
        }
        if (StringUtils.isNotBlank(reportInfo.getIndustry())){
            WHERE("industry=#{reportInfo.industry}");
        }
        if (StringUtils.isNotBlank(reportInfo.getTradingArea())){
            WHERE("trading_area like CONCAT('%', #{reportInfo.tradingArea}, '%')");
        }
        if (StringUtils.isNotBlank(reportInfo.getTurnover())){
            WHERE("turnover=#{reportInfo.turnover}");
        }
        if (reportInfo.getSize() != null) {
            WHERE("size=#{reportInfo.size}");
        }
        if (StringUtils.isNotBlank(reportInfo.getReportCycle())){
            WHERE("report_cycle=#{reportInfo.reportCycle}");
        }
        if (StringUtils.isNotBlank(reportInfo.getReportTimer())){
            WHERE("report_timer=#{reportInfo.reportTimer}");
        }
        if (StringUtils.isNotBlank(reportInfo.getRiskWarning())){
            WHERE("risk_warning=#{reportInfo.riskWarning}");
        }
        if (StringUtils.isNotBlank(reportInfo.getQuota())){
            WHERE("quota=#{reportInfo.quota}");
        }
        if (StringUtils.isNotBlank(reportInfo.getFeeRate())){
            WHERE("fee_rate=#{reportInfo.feeRate}");
        }
        if (StringUtils.isNotBlank(reportInfo.getLoanCycle())){
            WHERE("loan_cycle=#{reportInfo.loanCycle}");
        }
        if(reportInfo.getCustomerType()==1){
            if (reportInfo.getStatus()!= null) {
                WHERE("status=#{reportInfo.status}");
            }else{
                WHERE("status in (0)");
            }
        }
        if(reportInfo.getCustomerType()==2){
            if (reportInfo.getStatus()!= null) {
                WHERE("status=#{reportInfo.status}");
            }else{
                WHERE("status in (2,3)");
            }
        }
        }}.toString();
    }
    
}

