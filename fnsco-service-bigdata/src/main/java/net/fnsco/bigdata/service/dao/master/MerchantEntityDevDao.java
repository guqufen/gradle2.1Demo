package net.fnsco.bigdata.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.bigdata.api.dto.MerEntityDTO;
import net.fnsco.bigdata.api.dto.MerchantShopDTO;
import net.fnsco.bigdata.service.domain.MerchantEntityDev;
/**
 * @desc  商户店铺DAO
 * @author   tangliang
 * @version  
 * @since    Ver 1.1
 * @Date	 2017年10月26日 下午2:48:26
 *
 */
public interface MerchantEntityDevDao {

    int deleteByPrimaryKey(Integer id);

    int insert(MerchantEntityDev record);

    int insertSelective(MerchantEntityDev record);

    MerchantEntityDev selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MerchantEntityDev record);

    int updateByPrimaryKey(MerchantEntityDev record);
    
    /**
     * queryAllMerEntity:(实体商户列表概念)
     *
     * @param  @param userId
     * @param  @return    设定文件
     * @return List<MerEntityDTO>    DOM对象
     * @author tangliang
     * @date   2017年11月1日 下午2:58:04
     */
    List<MerEntityDTO> queryAllMerEntity(@Param("userId")Integer userId);
    /**
     * queryAllShopDetail:(APP接口用到 查询店铺列表接口)
     *
     * @param  @param userId
     * @param  @return    设定文件
     * @return List<MerchantShopDTO>    DOM对象
     * @author tangliang
     * @date   2017年10月31日 下午2:55:30
     */
    List<MerchantShopDTO> queryAllShopDetail(@Param("userId")Integer userId);
    
    /**
     * 通过实体内部商户号查找数据
     * @param record
     * @return
     */
    MerchantEntityDev selectByEntityInnerCode(@Param("entityInnerCode")String entityInnerCode);
    
    /**
     * 通过实体商户号更新数据
     * @param entityInnerCode
     * @return
     */
    int updateByEntityInnerCode(MerchantEntityDev record);
    
    /**
     * queryPageList:(分页条件)
     *
     * @param  @param pages
     * @param  @return    设定文件
     * @return List<MerchantEntity>    DOM对象
     * @author tangliang
     * @date   2017年10月27日 上午11:04:46
     */
//    List<MerchantEntity> queryPageList(PageDTO<MerchantEntity> pages);
    
    /**
     * 条件查询总数
     * @param pages
     * @return
     */
//    int queryTotalByCondition(MerchantEntity record);
}