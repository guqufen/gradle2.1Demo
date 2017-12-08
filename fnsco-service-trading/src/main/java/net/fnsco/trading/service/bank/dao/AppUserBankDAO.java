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

import java.util.List;;

public interface AppUserBankDAO {

    @Results({@Result( column = "app_user_id",property = "appUserId"),@Result( column = "account_type",property = "accountType"),@Result( column = "account_no",property = "accountNo"),@Result( column = "account_name",property = "accountName"),@Result( column = "account_card_id",property = "accountCardId"),@Result( column = "sub_bank_name",property = "subBankName"),@Result( column = "open_bank_prince",property = "openBankPrince"),@Result( column = "open_bank",property = "openBank"),@Result( column = "open_bank_city",property = "openBankCity"),@Result( column = "open_bank_num",property = "openBankNum"),@Result( column = "account_phone",property = "accountPhone") })
    @Select("SELECT * FROM u_app_user_bank WHERE id = #{id}")
    public AppUserBankDO getById(@Param("id") int id);

    @Insert("INSERT into u_app_user_bank(id,app_user_id,account_type,account_no,account_name,account_card_id,sub_bank_name,open_bank_prince,open_bank,open_bank_city,open_bank_num,account_phone) VALUES (#{id},#{appUserId},#{accountType},#{accountNo},#{accountName},#{accountCardId},#{subBankName},#{openBankPrince},#{openBank},#{openBankCity},#{openBankNum},#{accountPhone})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(AppUserBankDO appUserBank);

    @Delete("DELETE FROM u_app_user_bank WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = AppUserBankProvider.class, method = "update")
    public int update(@Param("appUserBank") AppUserBankDO  appUserBank);

    @Results({@Result( column = "app_user_id",property = "appUserId"),@Result( column = "account_type",property = "accountType"),@Result( column = "account_no",property = "accountNo"),@Result( column = "account_name",property = "accountName"),@Result( column = "account_card_id",property = "accountCardId"),@Result( column = "sub_bank_name",property = "subBankName"),@Result( column = "open_bank_prince",property = "openBankPrince"),@Result( column = "open_bank",property = "openBank"),@Result( column = "open_bank_city",property = "openBankCity"),@Result( column = "open_bank_num",property = "openBankNum"),@Result( column = "account_phone",property = "accountPhone") })
    @SelectProvider(type = AppUserBankProvider.class, method = "pageList")
    public List<AppUserBankDO> pageList(@Param("appUserBank") AppUserBankDO appUserBank, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = AppUserBankProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("appUserBank") AppUserBankDO appUserBank);

    @Delete("DELETE FROM u_app_user_bank WHERE app_user_id = #{userID} and id=#{bankId}")
	public int unBindBankCard(String userID, Integer bankId);

}