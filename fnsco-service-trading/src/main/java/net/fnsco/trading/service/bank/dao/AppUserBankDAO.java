package net.fnsco.trading.service.bank.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.fnsco.trading.service.bank.dao.helper.AppUserBankProvider;
import net.fnsco.trading.service.bank.entity.AppUserBankDO;
import net.fnsco.trading.service.bank.entity.BankNameAndTypeDTO;

import java.util.List;;

public interface AppUserBankDAO {

	@Results({ @Result(column = "app_user_id", property = "appUserId"),
			@Result(column = "account_type", property = "accountType"),
			@Result(column = "account_no", property = "accountNo"),
			@Result(column = "account_name", property = "accountName"),
			@Result(column = "account_card_id", property = "accountCardId"),
			@Result(column = "sub_bank_name", property = "subBankName"),
			@Result(column = "open_bank_prince", property = "openBankPrince"),
			@Result(column = "open_bank", property = "openBank"),
			@Result(column = "open_bank_city", property = "openBankCity"),
			@Result(column = "open_bank_num", property = "openBankNum"),
			@Result(column = "account_phone", property = "accountPhone"),
			@Result(column = "create_time", property = "createTime"),
			@Result(column = "update_time", property = "updateTime"),
			@Result(column = "bank_name", property = "bankName"), @Result(column = "type", property = "type") })
	@Select("SELECT * FROM u_app_user_bank WHERE id = #{id}")
	public AppUserBankDO getById(@Param("id") int id);
	
	@Results({ @Result(column = "id", property = "id")})
	@Select("SELECT id FROM u_app_user_bank WHERE app_user_id = #{appUserId} LIMIT 1")
	public AppUserBankDO getByAppUserId(@Param("appUserId") int appUserId);

	@Insert("INSERT into u_app_user_bank(id,app_user_id,account_type,account_no,account_name,account_card_id,sub_bank_name,open_bank_prince,open_bank,open_bank_city,open_bank_num,account_phone,create_time,update_time,bank_name,type,status) VALUES (#{id},#{appUserId},#{accountType},#{accountNo},#{accountName},#{accountCardId},#{subBankName},#{openBankPrince},#{openBank},#{openBankCity},#{openBankNum},#{accountPhone},#{createTime},#{updateTime},#{bankName},#{type},#{status})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public Integer insert(AppUserBankDO appUserBank);

	@Delete("DELETE FROM u_app_user_bank WHERE id = #{id}")
	public int deleteById(@Param("id") int id);

	@UpdateProvider(type = AppUserBankProvider.class, method = "update")
	public int update(@Param("appUserBank") AppUserBankDO appUserBank);

	@Results({ @Result(column = "app_user_id", property = "appUserId"),
			@Result(column = "account_type", property = "accountType"),
			@Result(column = "account_no", property = "accountNo"),
			@Result(column = "account_name", property = "accountName"),
			@Result(column = "account_card_id", property = "accountCardId"),
			@Result(column = "sub_bank_name", property = "subBankName"),
			@Result(column = "open_bank_prince", property = "openBankPrince"),
			@Result(column = "open_bank", property = "openBank"),
			@Result(column = "open_bank_city", property = "openBankCity"),
			@Result(column = "open_bank_num", property = "openBankNum"),
			@Result(column = "account_phone", property = "accountPhone") })
	@SelectProvider(type = AppUserBankProvider.class, method = "pageList")
	public List<AppUserBankDO> pageList(@Param("appUserBank") AppUserBankDO appUserBank,
			@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

	@SelectProvider(type = AppUserBankProvider.class, method = "pageListCount")
	public Integer pageListCount(@Param("appUserBank") AppUserBankDO appUserBank);

	@Delete("UPDATE u_app_user_bank SET status = '1' WHERE id = #{bankId}")
	public int unBindBankCard(@Param("bankId") Integer bankId);

	@Results({ @Result(column = "account_no", property = "accountNo"),
			@Result(column = "bank_name", property = "bankName"), @Result(column = "type", property = "type") })
	@Select("SELECT id, account_no,bank_name,type FROM u_app_user_bank WHERE app_user_id = #{userId} and status != 1")
	public List<AppUserBankDO> getBankList(@Param("userId") String userId);

	@Results({ @Result(column = "type", property = "type"),@Result(column = "bank_name", property = "bank_name")})
	@Select(" SELECT type,bank_name FROM sys_bank_card_type WHERE LEFT (#{bankCardNum},card_trim_length) = card_trim_value	AND card_total_length = #{cardTotalLength} ORDER BY card_trim_length DESC;")
	public BankNameAndTypeDTO queryByCertifyId(@Param("bankCardNum") String bankCardNum,
			@Param("cardTotalLength") String cardTotalLength);

	@Results({ @Result(column = "account_no", property = "accountNo")})
	@Select(" SELECT account_no FROM u_app_user_bank WHERE account_no=#{bankCardNum} ")
	public List<String> getByBankNO(String bankCardNum);

}