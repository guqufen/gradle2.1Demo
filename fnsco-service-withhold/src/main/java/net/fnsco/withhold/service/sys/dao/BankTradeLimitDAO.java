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
import net.fnsco.withhold.service.sys.entity.BankTradeLimitDO;
import net.fnsco.withhold.service.sys.dao.helper.BankTradeLimitProvider;

import java.util.List;;

public interface BankTradeLimitDAO {

    @Results({@Result( column = "bank_code",property = "bankCode"),@Result( column = "bank_name",property = "bankName"),@Result( column = "trade_times_limit",property = "tradeTimesLimit"),@Result( column = "trade_day_limit",property = "tradeDayLimit") })
    @Select("SELECT * FROM sys_bank_trade_limit WHERE id = #{id}")
    public BankTradeLimitDO getById(@Param("id") int id);
    
    @Results({@Result( column = "bank_code",property = "bankCode"),@Result( column = "bank_name",property = "bankName"),@Result( column = "trade_times_limit",property = "tradeTimesLimit"),@Result( column = "trade_day_limit",property = "tradeDayLimit") })
    @Select("SELECT * FROM sys_bank_trade_limit WHERE bank_code = #{bankCode}")
    public BankTradeLimitDO getByBankCode(@Param("bankCode") String bankCode);

    @Insert("INSERT into sys_bank_trade_limit(id,bank_code,bank_name,trade_times_limit,trade_day_limit) VALUES (#{id},#{bankCode},#{bankName},#{tradeTimesLimit},#{tradeDayLimit})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(BankTradeLimitDO bankTradeLimit);

    @Delete("DELETE FROM sys_bank_trade_limit WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = BankTradeLimitProvider.class, method = "update")
    public int update(@Param("bankTradeLimit") BankTradeLimitDO  bankTradeLimit);

    @Results({@Result( column = "bank_code",property = "bankCode"),@Result( column = "bank_name",property = "bankName"),@Result( column = "trade_times_limit",property = "tradeTimesLimit"),@Result( column = "trade_day_limit",property = "tradeDayLimit") })
    @SelectProvider(type = BankTradeLimitProvider.class, method = "pageList")
    public List<BankTradeLimitDO> pageList(@Param("bankTradeLimit") BankTradeLimitDO bankTradeLimit, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = BankTradeLimitProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("bankTradeLimit") BankTradeLimitDO bankTradeLimit);

}