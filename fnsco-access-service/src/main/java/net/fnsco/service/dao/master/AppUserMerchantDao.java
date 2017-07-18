package net.fnsco.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.api.dto.AppUserMerchantDTO;
import net.fnsco.service.domain.AppUserMerchant;

public interface AppUserMerchantDao { 
    //根据appUserId和roleId查询用户实体集合
    List<AppUserMerchant> selectByPrimaryKey(@Param("appUserId")Integer appUserId,@Param("roleId")String roleId);
    List<AppUserMerchant> selectByInnerCode(@Param("innerCode")String innerCode,@Param("roleId")String roleId);
    AppUserMerchant selectByCode(@Param("innerCode")String innerCode,@Param("roleId")String roleId);
    int deleteByPrimaryKey(@Param("innerCode")String innerCode,@Param("appUserId")Integer appUserId);
    int updateByPrimaryKeySelective(AppUserMerchantDTO dto);
}
