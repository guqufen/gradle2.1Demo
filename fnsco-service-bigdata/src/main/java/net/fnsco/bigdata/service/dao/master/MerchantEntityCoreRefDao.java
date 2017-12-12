package net.fnsco.bigdata.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.bigdata.service.domain.MerchantEntityCoreRef;
/**
 * @desc 商户店铺信息关系DAO
 * @author   tangliang
 * @since    Ver 1.1
 * @Date	 2017年10月26日 下午2:57:13
 *
 */
public interface MerchantEntityCoreRefDao {

    int deleteByPrimaryKey(Integer id);

    int insert(MerchantEntityCoreRef record);

    int insertSelective(MerchantEntityCoreRef record);

    MerchantEntityCoreRef selectByPrimaryKey(Integer id);
    
    List<MerchantEntityCoreRef> selectByInnerCode(@Param("innerCode") String innerCode);
    
    int updateByPrimaryKeySelective(MerchantEntityCoreRef record);
    
    /**删除关系
     * @param ids
     * @return
     */
    int deleteByMerCoreIds(Integer[] ids);
    
    /**
     * 根据条件更新
     * @param record
     * @return
     */
    int updateByInnerCode(MerchantEntityCoreRef record);

    int updateByPrimaryKey(MerchantEntityCoreRef record);
    
    /**
     * 返回满足条件的条数
     * @param record
     * @return
     */
    int countByCondition(MerchantEntityCoreRef record);
    
    /**
     * selectByInnerCode:(根据innercode查询关系)
     *
     * @param  @param innerCode
     * @param  @return    设定文件
     * @return MerchantEntityCoreRef    DOM对象
     * @author tangliang
     * @date   2017年11月2日 上午10:54:11
     */
    MerchantEntityCoreRef selectByInnerCodeLimit1(@Param("innerCode")String innerCode);
    
    List<MerchantEntityCoreRef> selectByEntityInnerCode(@Param("entityInnerCode") String entityInnerCode);
}