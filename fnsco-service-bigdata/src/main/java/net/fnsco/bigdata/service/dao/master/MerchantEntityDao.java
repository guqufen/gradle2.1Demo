package net.fnsco.bigdata.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.bigdata.api.dto.MerchantShopDTO;
import net.fnsco.bigdata.service.domain.MerchantEntity;
/**
 * @desc  商户店铺DAO
 * @author   tangliang
 * @version  
 * @since    Ver 1.1
 * @Date	 2017年10月26日 下午2:48:26
 *
 */
public interface MerchantEntityDao {

    int deleteByPrimaryKey(Integer id);

    int insert(MerchantEntity record);

    int insertSelective(MerchantEntity record);

    MerchantEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MerchantEntity record);

    int updateByPrimaryKey(MerchantEntity record);
    
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