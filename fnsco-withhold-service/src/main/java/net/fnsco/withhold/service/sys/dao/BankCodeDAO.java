package net.fnsco.withhold.service.sys.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.withhold.service.sys.entity.BankCodeDO;
import net.fnsco.withhold.service.sys.dao.helper.BankCodeProvider;

import java.util.List;;

public interface BankCodeDAO {
    @Results({ @Result(column = "bank_name", property = "bankName"), @Result(column = "card_name", property = "cardName"), @Result(column = "card_trim_value", property = "cardTrimValue"),
               @Result(column = "card_trim_length", property = "cardTrimLength"), @Result(column = "card_total_length", property = "cardTotalLength") })
    @Select("SELECT * FROM sys_bank_code WHERE id = #{id}")
    public BankCodeDO getById(@Param("id") int id);

    @Results({ @Result(column = "bank_name", property = "bankName"), @Result(column = "card_name", property = "cardName"), @Result(column = "card_trim_value", property = "cardTrimValue"),
               @Result(column = "card_trim_length", property = "cardTrimLength"), @Result(column = "card_total_length", property = "cardTotalLength") })
    @Select("select * from sys_bank_code where card_trim_value=left(#{cardNum}, card_trim_length) and card_trim_length = #{cardLenth} order by card_trim_length desc limit 1 ")
    public BankCodeDO getByCardNum(@Param("cardNum") String cardNum, @Param("cardLenth") int cardLenth);

    @Insert("INSERT into sys_bank_code(id,code,bank_name,card_name,card_trim_value,type,card_trim_length,card_total_length) VALUES (#{id},#{code},#{bankName},#{cardName},#{cardTrimValue},#{type},#{cardTrimLength},#{cardTotalLength})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(BankCodeDO bankCode);

    @Delete("DELETE FROM sys_bank_code WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = BankCodeProvider.class, method = "update")
    public int update(@Param("bankCode") BankCodeDO bankCode);

    @Results({ @Result(column = "bank_name", property = "bankName"), @Result(column = "card_name", property = "cardName"), @Result(column = "card_trim_value", property = "cardTrimValue"),
               @Result(column = "card_trim_length", property = "cardTrimLength"), @Result(column = "card_total_length", property = "cardTotalLength") })
    @SelectProvider(type = BankCodeProvider.class, method = "pageList")
    public List<BankCodeDO> pageList(@Param("bankCode") BankCodeDO bankCode, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = BankCodeProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("bankCode") BankCodeDO bankCode);

}