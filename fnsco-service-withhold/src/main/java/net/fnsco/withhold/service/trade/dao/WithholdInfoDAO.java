package net.fnsco.withhold.service.trade.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.withhold.service.trade.entity.WithholdInfoDO;
import net.fnsco.withhold.service.trade.dao.helper.WithholdInfoProvider;

import java.util.List;;

public interface WithholdInfoDAO {
    @Results({ @Result(column = "user_name", property = "userName"),@Result(column = "product_type_code", property = "productTypeCode"), @Result(column = "certif_type", property = "certifType"), @Result(column = "certify_id", property = "certifyId"),
               @Result(column = "debit_day", property = "debitDay"), @Result(column = "amount_total", property = "amountTotal"), @Result(column = "bank_card", property = "bankCard"),
               @Result(column = "modify_user_id", property = "modifyUserId"), @Result(column = "modify_time", property = "modifyTime"), @Result(column = "sub_bank_name", property = "subBankName"),
               @Result(column = "an_bank_id", property = "anBankId"), @Result(column = "account_type", property = "accountType"), @Result(column = "acc_type", property = "accType"),
               @Result(column = "open_bank_num", property = "openBankNum"), @Result(column = "fail_total", property = "failTotal"), @Result(column = "contract_num", property = "contractNum")})

    @Select("SELECT * FROM w_withhold_info WHERE id = #{id}")
    public WithholdInfoDO getById(@Param("id") int id);

    @Results({ @Result(column = "user_name", property = "userName"),@Result(column = "product_type_code", property = "productTypeCode"), @Result(column = "certif_type", property = "certifType"), @Result(column = "certify_id", property = "certifyId"),
               @Result(column = "debit_day", property = "debitDay"), @Result(column = "amount_total", property = "amountTotal"), @Result(column = "bank_card", property = "bankCard"),
               @Result(column = "modify_user_id", property = "modifyUserId"), @Result(column = "modify_time", property = "modifyTime"), @Result(column = "sub_bank_name", property = "subBankName"),
               @Result(column = "an_bank_id", property = "anBankId"), @Result(column = "account_type", property = "accountType"), @Result(column = "acc_type", property = "accType"),
               @Result(column = "open_bank_num", property = "openBankNum"), @Result(column = "fail_total", property = "failTotal") , @Result(column = "contract_num", property = "contractNum")})

    @Select("SELECT * FROM w_withhold_info WHERE status = '1' and debit_day = #{debitDay} and fail_total =#{failTotal} and start_date<= #{startDate}")
    public List<WithholdInfoDO> getByDebitDayFail(@Param("debitDay") String debitDay, @Param("failTotal") int failTotal,@Param("startDate") String startDate);

    @Results({ @Result(column = "user_name", property = "userName"),@Result(column = "product_type_code", property = "productTypeCode"), @Result(column = "certif_type", property = "certifType"), @Result(column = "certify_id", property = "certifyId"),
               @Result(column = "debit_day", property = "debitDay"), @Result(column = "amount_total", property = "amountTotal"), @Result(column = "bank_card", property = "bankCard"),
               @Result(column = "modify_user_id", property = "modifyUserId"), @Result(column = "modify_time", property = "modifyTime"), @Result(column = "sub_bank_name", property = "subBankName"),
               @Result(column = "an_bank_id", property = "anBankId"), @Result(column = "account_type", property = "accountType"), @Result(column = "acc_type", property = "accType"),
               @Result(column = "open_bank_num", property = "openBankNum"), @Result(column = "fail_total", property = "failTotal"), @Result(column = "contract_num", property = "contractNum") })

    @Select("SELECT * FROM w_withhold_info WHERE status = '1' and debit_day = #{debitDay}  ")
    public List<WithholdInfoDO> getByDebitDay(@Param("debitDay") String debitDay);

    @Insert("INSERT into w_withhold_info(id,product_type_code,user_name,mobile,certif_type,certify_id,debit_day,amount,amount_total,bank_card,status,modify_user_id,modify_time,total,sub_bank_name,an_bank_id,account_type,acc_type,open_bank_num,fail_total,start_date,end_date,contract_num) VALUES (#{id},#{productTypeCode},#{userName},#{mobile},#{certifType},#{certifyId},#{debitDay},#{amount},#{amountTotal},#{bankCard},#{status},#{modifyUserId},#{modifyTime},#{total},#{subBankName},#{anBankId},#{accountType},#{accType},#{openBankNum},#{failTotal},#{startDate},#{endDate},#{contractNum})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(WithholdInfoDO withholdInfo);

    @Delete("DELETE FROM w_withhold_info WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = WithholdInfoProvider.class, method = "update")
    public int update(@Param("withholdInfo") WithholdInfoDO withholdInfo);

    @Results({ @Result(column = "user_name", property = "userName"),@Result(column = "product_type_code", property = "productTypeCode"),@Result(column = "certif_type", property = "certifType"), @Result(column = "certify_id", property = "certifyId"),
               @Result(column = "debit_day", property = "debitDay"), @Result(column = "amount_total", property = "amountTotal"), @Result(column = "bank_card", property = "bankCard"),
               @Result(column = "modify_user_id", property = "modifyUserId"), @Result(column = "modify_time", property = "modifyTime"), @Result(column = "sub_bank_name", property = "subBankName"),
               @Result(column = "an_bank_id", property = "anBankId"), @Result(column = "account_type", property = "accountType"), @Result(column = "open_bank_num", property = "openBankNum"), @Result(column = "contract_num", property = "contractNum")})
    @SelectProvider(type = WithholdInfoProvider.class, method = "pageList")
    public List<WithholdInfoDO> pageList(@Param("withholdInfo") WithholdInfoDO withholdInfo, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = WithholdInfoProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("withholdInfo") WithholdInfoDO withholdInfo);

}