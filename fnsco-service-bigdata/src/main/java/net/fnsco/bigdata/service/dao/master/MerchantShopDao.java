package net.fnsco.bigdata.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.bigdata.api.dto.MerShopDetailDTO;
import net.fnsco.bigdata.service.domain.MerchantShop;
/**
 * @desc 店铺DAO
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年10月27日 上午9:50:36
 */
public interface MerchantShopDao {

    int deleteByPrimaryKey(Integer id);

    int insert(MerchantShop record);

    int insertSelective(MerchantShop record);

    MerchantShop selectByPrimaryKey(Integer id);
    
    /**
     * deleteByShopInnerCode:(按照shopInnerCode删除)
     *
     * @param  @param shopInnerCode
     * @param  @return    设定文件
     * @return int    DOM对象
     * @author tangliang
     * @date   2017年11月1日 上午11:41:07
     */
    int deleteByShopInnerCode(@Param("shopInnerCode")String shopInnerCode);
    
    /**
     * updateByCondition:(按照条件更新)
     *
     * @param  @param record
     * @param  @return    设定文件
     * @return int    DOM对象
     * @author tangliang
     * @date   2017年11月1日 上午10:11:32
     */
    int updateByShopInnerCodeSelective(MerchantShop record);
    
    int updateByPrimaryKeySelective(MerchantShop record);

    int updateByPrimaryKey(MerchantShop record);
    
    /**
     * selectByEntityInnerCode:(这里用一句话描述这个方法的作用)
     *
     * @param  @param entityInnerCode
     * @param  @return    设定文件
     * @return List<MerchantShop>    DOM对象
     * @author tangliang
     * @date   2017年10月31日 下午2:45:13
     */
    List<MerShopDetailDTO> selectByEntityInnerCode(@Param("entityInnerCode")String entityInnerCode);
    
    /**
     * queryTotalByCondition:(按照条件查询总数)
     *
     * @param  @param record
     * @param  @return    设定文件
     * @return int    DOM对象
     * @author tangliang
     * @date   2017年10月31日 下午4:32:09
     */
    int queryTotalByCondition(MerchantShop record);
}