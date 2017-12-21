package net.fnsco.trading.service.merchant.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.fnsco.trading.service.merchant.dao.helper.AppUserMerchantProvider;
import net.fnsco.trading.service.merchant.entity.AppUserMerchantDO;

import java.util.List;;

public interface AppUserMerchantDAO {

	@Results({@Result( column = "app_user_id",property = "appUserId"),@Result( column = "account_type",property = "accountType"),@Result( column = "account_no",property = "accountNo"),@Result( column = "account_name",property = "accountName"),@Result( column = "account_card_id",property = "accountCardId"),@Result( column = "sub_bank_name",property = "subBankName"),@Result( column = "open_bank_prince",property = "openBankPrince"),@Result( column = "open_bank",property = "openBank"),@Result( column = "open_bank_city",property = "openBankCity"),@Result( column = "open_bank_num",property = "openBankNum"),@Result( column = "account_phone",property = "accountPhone"),@Result( column = "create_time",property = "createTime"),@Result( column = "update_time",property = "updateTime"),@Result( column = "bank_name",property = "bankName") })
    @Select("SELECT * FROM u_app_user_merchant WHERE id = #{id}")
    public AppUserMerchantDO getById(@Param("id") int id);

	@Insert("INSERT into u_app_user_bank(id,app_user_id,account_type,account_no,account_name,account_card_id,sub_bank_name,open_bank_prince,open_bank,open_bank_city,open_bank_num,account_phone,status,create_time,update_time,bank_name,type) VALUES (#{id},#{appUserId},#{accountType},#{accountNo},#{accountName},#{accountCardId},#{subBankName},#{openBankPrince},#{openBank},#{openBankCity},#{openBankNum},#{accountPhone},#{status},#{createTime},#{updateTime},#{bankName},#{type})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(AppUserMerchantDO appUserMerchant);

    @Delete("DELETE FROM u_app_user_merchant WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = AppUserMerchantProvider.class, method = "update")
    public int update(@Param("appUserMerchant") AppUserMerchantDO  appUserMerchant);

    @Results({@Result( column = "app_user_id",property = "appUserId"),@Result( column = "account_type",property = "accountType"),@Result( column = "account_no",property = "accountNo"),@Result( column = "account_name",property = "accountName"),@Result( column = "account_card_id",property = "accountCardId"),@Result( column = "sub_bank_name",property = "subBankName"),@Result( column = "open_bank_prince",property = "openBankPrince"),@Result( column = "open_bank",property = "openBank"),@Result( column = "open_bank_city",property = "openBankCity"),@Result( column = "open_bank_num",property = "openBankNum"),@Result( column = "account_phone",property = "accountPhone"),@Result( column = "create_time",property = "createTime"),@Result( column = "update_time",property = "updateTime"),@Result( column = "bank_name",property = "bankName") })
    @SelectProvider(type = AppUserMerchantProvider.class, method = "pageList")
    public List<AppUserMerchantDO> pageList(@Param("appUserMerchant") AppUserMerchantDO appUserMerchant, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = AppUserMerchantProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("appUserMerchant") AppUserMerchantDO appUserMerchant);

    @Select("SELECT inner_code FROM u_app_user_merchant WHERE app_user_id = #{userId} limit 1")
	public String getInnerCodeByUserId(@Param("userId") Integer userId);

}