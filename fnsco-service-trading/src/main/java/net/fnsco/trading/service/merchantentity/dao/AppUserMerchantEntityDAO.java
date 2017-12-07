package net.fnsco.trading.service.merchantentity.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.trading.service.merchantentity.entity.AppUserMerchantEntityDO;
import net.fnsco.trading.service.merchantentity.dao.helper.AppUserMerchantEntityProvider;

import java.util.List;;

public interface AppUserMerchantEntityDAO {

    @Results({@Result( column = "entity_inner_code",property = "entityInnerCode"),@Result( column = "app_user_id",property = "appUserId"),@Result( column = "shop_inner_code",property = "shopInnerCode"),@Result( column = "role_id",property = "roleId"),@Result( column = "modefy_time",property = "modefyTime") })
    @Select("SELECT * FROM u_app_user_merchant_entity WHERE id = #{id}")
    public AppUserMerchantEntityDO getById(@Param("id") int id);

    @Insert("INSERT into u_app_user_merchant_entity(id,entity_inner_code,app_user_id,shop_inner_code,role_id,modefy_time) VALUES (#{id},#{entityInnerCode},#{appUserId},#{shopInnerCode},#{roleId},#{modefyTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(AppUserMerchantEntityDO appUserMerchantEntity);

    @Delete("DELETE FROM u_app_user_merchant_entity WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = AppUserMerchantEntityProvider.class, method = "update")
    public int update(@Param("appUserMerchantEntity") AppUserMerchantEntityDO  appUserMerchantEntity);

    @Results({@Result( column = "entity_inner_code",property = "entityInnerCode"),@Result( column = "app_user_id",property = "appUserId"),@Result( column = "shop_inner_code",property = "shopInnerCode"),@Result( column = "role_id",property = "roleId"),@Result( column = "modefy_time",property = "modefyTime") })
    @SelectProvider(type = AppUserMerchantEntityProvider.class, method = "pageList")
    public List<AppUserMerchantEntityDO> pageList(@Param("appUserMerchantEntity") AppUserMerchantEntityDO appUserMerchantEntity, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = AppUserMerchantEntityProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("appUserMerchantEntity") AppUserMerchantEntityDO appUserMerchantEntity);

}