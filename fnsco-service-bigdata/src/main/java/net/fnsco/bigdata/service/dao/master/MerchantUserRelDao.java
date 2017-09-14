package net.fnsco.bigdata.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.bigdata.service.domain.MerchantUserRel;

/**
 * @desc 商家跟APP用户关系DAO
 * @author tangliang
 * @date 2017年6月22日 下午4:00:15
 */
public interface MerchantUserRelDao {

    int insert(MerchantUserRel record);

    int insertSelective(MerchantUserRel record);

    MerchantUserRel selectByPrimaryKey(Integer id);

    MerchantUserRel selectByUserIdInnerCode(@Param("appUserId") Integer appUserId, @Param("innerCode") String innerCode);
    
    List<MerchantUserRel> selectByInnerCode(@Param("innerCode") String innerCode);
    
    List<MerchantUserRel> selectByUserId(@Param("appUserId") Integer appUserId);
    //删除绑定关系
    int deleteByPrimaryKey(@Param("innerCode")String innerCode,@Param("appUserId")Integer appUserId);
    
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
}