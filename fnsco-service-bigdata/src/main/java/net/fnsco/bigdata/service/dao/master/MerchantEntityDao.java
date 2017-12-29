package net.fnsco.bigdata.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.bigdata.api.dto.ChannelMerchantDTO;
import net.fnsco.bigdata.api.dto.IndustryDTO;
import net.fnsco.bigdata.api.dto.MerEntityDTO;
import net.fnsco.bigdata.api.dto.MerchantShopDTO;
import net.fnsco.bigdata.service.domain.MerchantEntity;
import net.fnsco.core.base.PageDTO;
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
     * 查询绑定的渠道商户信息
     * @return
     */
    List<ChannelMerchantDTO> queryPageChannelMerByEntityInnerCode(PageDTO<String> pages);
    
    /**
     * 查询总数
     * @param entityInnerCode
     * @return
     */
    int queryCountChannelMerByEntityInnerCode(@Param("entityInnerCode")String entityInnerCode);
    
    /**
     * queryPageList:(分页条件)
     *
     * @param  @param pages
     * @param  @return    设定文件
     * @return List<MerchantEntity>    DOM对象
     * @author tangliang
     * @date   2017年10月27日 上午11:04:46
     */
    List<MerchantEntity> queryPageList(PageDTO<MerchantEntity> pages);
    
    /**
     * 条件查询总数
     * @param pages
     * @return
     */
    int queryTotalByCondition(MerchantEntity record);
    /**
     * 
     * @param innerCode
     * @return
     */
    MerchantEntity queryMerEntityByInnerCode(@Param("innerCode")String innerCode);
    
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
    MerchantEntity selectByEntityInnerCode(@Param("entityInnerCode")String entityInnerCode);
    
    /**
     * 通过实体商户号更新数据
     * @param entityInnerCode
     * @return
     */
    int updateByEntityInnerCode(MerchantEntity record);

    
    
	List<IndustryDTO> pageNameList(PageDTO<IndustryDTO> pages);
	Integer pageNameListCount(IndustryDTO industryDO);
    
}