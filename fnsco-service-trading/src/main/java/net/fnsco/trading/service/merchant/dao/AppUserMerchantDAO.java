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

    @Results({@Result( column = "role_id",property = "roleId"),@Result( column = "inner_code",property = "innerCode"),@Result( column = "app_user_id",property = "appUserId"),@Result( column = "modefy_time",property = "modefyTime") })
    @Select("SELECT * FROM u_app_user_merchant WHERE id = #{id}")
    public AppUserMerchantDO getById(@Param("id") int id);

    @Insert("INSERT into u_app_user_merchant(id,role_id,inner_code,app_user_id,modefy_time) VALUES (#{id},#{roleId},#{innerCode},#{appUserId},#{modefyTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(AppUserMerchantDO appUserMerchant);

    @Delete("DELETE FROM u_app_user_merchant WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = AppUserMerchantProvider.class, method = "update")
    public int update(@Param("appUserMerchant") AppUserMerchantDO  appUserMerchant);

    @Results({@Result( column = "role_id",property = "roleId"),@Result( column = "inner_code",property = "innerCode"),@Result( column = "app_user_id",property = "appUserId"),@Result( column = "modefy_time",property = "modefyTime") })
    @SelectProvider(type = AppUserMerchantProvider.class, method = "pageList")
    public List<AppUserMerchantDO> pageList(@Param("appUserMerchant") AppUserMerchantDO appUserMerchant, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = AppUserMerchantProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("appUserMerchant") AppUserMerchantDO appUserMerchant);

    @Select("SELECT inner_code FROM u_app_user_merchant WHERE app_user_id = #{userId}")
	public String getInnerCodeByUserId(@Param("userId") Integer userId);

}