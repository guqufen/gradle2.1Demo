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
import net.fnsco.risk.service.report.entity.ReportRepaymentHistoryDO;
import net.fnsco.risk.service.report.dao.helper.ReportRepaymentHistoryProvider;

import java.util.List;;

public interface ReportRepaymentHistoryDAO {

    @Results({@Result( column = "report_id",property = "reportId"),@Result( column = "month_one",property = "monthOne"),@Result( column = "month_two",property = "monthTwo"),@Result( column = "month_three",property = "monthThree"),@Result( column = "month_fore",property = "monthFore"),@Result( column = "month_five",property = "monthFive"),@Result( column = "month_six",property = "monthSix"),@Result( column = "month_seven",property = "monthSeven"),@Result( column = "month_eight",property = "monthEight"),@Result( column = "month_nine",property = "monthNine"),@Result( column = "month_ten",property = "monthTen"),@Result( column = "month_eleven",property = "monthEleven"),@Result( column = "month_twelve",property = "monthTwelve") })
    @Select("SELECT * FROM risk_report_repayment_history WHERE id = #{id}")
    public ReportRepaymentHistoryDO getById(@Param("id") int id);

    //根据reportId查询
    @Results({@Result( column = "id",property = "id"),@Result( column = "month_one",property = "monthOne"),@Result( column = "month_two",property = "monthTwo"),@Result( column = "month_three",property = "monthThree"),@Result( column = "month_fore",property = "monthFore"),@Result( column = "month_five",property = "monthFive"),@Result( column = "month_six",property = "monthSix"),@Result( column = "month_seven",property = "monthSeven"),@Result( column = "month_eight",property = "monthEight"),@Result( column = "month_nine",property = "monthNine"),@Result( column = "month_ten",property = "monthTen"),@Result( column = "month_eleven",property = "monthEleven"),@Result( column = "month_twelve",property = "monthTwelve") })
    @Select("SELECT * from risk_report_repayment_history where last_modify_time=(select max(last_modify_time) from risk_report_repayment_history);")
    public ReportRepaymentHistoryDO getByReportId(@Param("reportId") int reportId);
    
    @Insert("INSERT into risk_report_repayment_history(id,report_id,month_one,month_two,month_three,month_fore,month_five,month_six,month_seven,month_eight,month_nine,month_ten,month_eleven,month_twelve) VALUES (#{id},#{reportId},#{monthOne},#{monthTwo},#{monthThree},#{monthFore},#{monthFive},#{monthSix},#{monthSeven},#{monthEight},#{monthNine},#{monthTen},#{monthEleven},#{monthTwelve})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(ReportRepaymentHistoryDO reportRepaymentHistory);

    @Delete("DELETE FROM risk_report_repayment_history WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = ReportRepaymentHistoryProvider.class, method = "update")
    public int update(@Param("reportRepaymentHistory") ReportRepaymentHistoryDO  reportRepaymentHistory);

    @Results({@Result( column = "report_id",property = "reportId"),@Result( column = "month_one",property = "monthOne"),@Result( column = "month_two",property = "monthTwo"),@Result( column = "month_three",property = "monthThree"),@Result( column = "month_fore",property = "monthFore"),@Result( column = "month_five",property = "monthFive"),@Result( column = "month_six",property = "monthSix"),@Result( column = "month_seven",property = "monthSeven"),@Result( column = "month_eight",property = "monthEight"),@Result( column = "month_nine",property = "monthNine"),@Result( column = "month_ten",property = "monthTen"),@Result( column = "month_eleven",property = "monthEleven"),@Result( column = "month_twelve",property = "monthTwelve") })
    @SelectProvider(type = ReportRepaymentHistoryProvider.class, method = "pageList")
    public List<ReportRepaymentHistoryDO> pageList(@Param("reportRepaymentHistory") ReportRepaymentHistoryDO reportRepaymentHistory, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = ReportRepaymentHistoryProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("reportRepaymentHistory") ReportRepaymentHistoryDO reportRepaymentHistory);

}