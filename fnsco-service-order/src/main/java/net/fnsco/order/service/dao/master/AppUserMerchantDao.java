package net.fnsco.order.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.order.api.dto.AppUserMerchantDTO;
import net.fnsco.order.api.dto.TradeMerchantDTO;
import net.fnsco.order.service.domain.AppUserMerchant;

public interface AppUserMerchantDao { 
    //根据appUserId和roleId查询用户实体集合
    List<AppUserMerchant> selectByPrimaryKey(@Param("appUserId")Integer appUserId,@Param("roleId")String roleId);
    List<AppUserMerchant> selectByInnerCode(@Param("innerCode")String innerCode,@Param("roleId")String roleId);
    AppUserMerchant selectByall(@Param("innerCode")String innerCode,@Param("appUserId")Integer appUserId,@Param("roleId")String roleId);
    int deleteByPrimaryKey(@Param("innerCode")String innerCode,@Param("appUserId")Integer appUserId);
    int updateByPrimaryKeySelective(AppUserMerchantDTO dto);
    int insertSelective(AppUserMerchant dto);
    int deleteByMerCoreIds(Integer[] ids);
    
    /**
     * deleteByMerIdAndUserId:(根据商户ID和用户ID 删除关系)
     * @param userId
     * @param merId
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月4日 上午11:17:35
     * @return int    DOM对象
     */
    int deleteByMerIdAndUserId(@Param("userId")Integer userId,@Param("merId")Integer merId);
    
    /**
     * selectByUserIdAndRoleId:(这里用一句话描述这个方法的作用)根据userId和roleId查询商家信息
     *
     * @return    设定文件
     * @return List<TradeMerchantDTO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<TradeMerchantDTO> selectByUserIdAndRoleId(@Param("appUserId")Integer appUserId,@Param("roleId")String roleId);
    
    int updateByUserIdAndInnerCode(AppUserMerchant record);
}
