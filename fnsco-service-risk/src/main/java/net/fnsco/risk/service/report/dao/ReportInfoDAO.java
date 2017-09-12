package net.fnsco.risk.service.report.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.risk.service.report.entity.ReportInfoDO;
import net.fnsco.risk.service.report.dao.helper.ReportInfoProvider;

import java.util.List;;

public interface ReportInfoDAO {

    @Results({@Result( column = "mer_name",property = "merName"),@Result( column = "business_license_num",property = "businessLicenseNum"),@Result( column = "business_address",property = "businessAddress"),@Result( column = "business_due_time",property = "businessDueTime"),@Result( column = "trading_area",property = "tradingArea"),@Result( column = "report_cycle",property = "reportCycle"),@Result( column = "report_timer",property = "reportTimer"),@Result( column = "risk_warning",property = "riskWarning"),@Result( column = "fee_rate",property = "feeRate"),@Result( column = "loan_cycle",property = "loanCycle") })
    @Select("SELECT * FROM risk_report_info WHERE id = #{id}")
    public ReportInfoDO getById(@Param("id") int id);

    @Insert("INSERT into risk_report_info(id,mer_name,business_license_num,business_address,business_due_time,industry,trading_area,turnover,size,report_cycle,report_timer,risk_warning,quota,fee_rate,loan_cycle) VALUES (#{id},#{merName},#{businessLicenseNum},#{businessAddress},#{businessDueTime},#{industry},#{tradingArea},#{turnover},#{size},#{reportCycle},#{reportTimer},#{riskWarning},#{quota},#{feeRate},#{loanCycle})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(ReportInfoDO reportInfo);

    @Delete("DELETE FROM risk_report_info WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = ReportInfoProvider.class, method = "update")
    public int update(@Param("reportInfo") ReportInfoDO  reportInfo);

    @Results({@Result( column = "mer_name",property = "merName"),@Result( column = "business_license_num",property = "businessLicenseNum"),@Result( column = "business_address",property = "businessAddress"),@Result( column = "business_due_time",property = "businessDueTime"),@Result( column = "trading_area",property = "tradingArea"),@Result( column = "report_cycle",property = "reportCycle"),@Result( column = "report_timer",property = "reportTimer"),@Result( column = "risk_warning",property = "riskWarning"),@Result( column = "fee_rate",property = "feeRate"),@Result( column = "loan_cycle",property = "loanCycle") })
    @SelectProvider(type = ReportInfoProvider.class, method = "pageList")
    public List<ReportInfoDO> pageList(@Param("reportInfo") ReportInfoDO reportInfo, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = ReportInfoProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("reportInfo") ReportInfoDO reportInfo);

}