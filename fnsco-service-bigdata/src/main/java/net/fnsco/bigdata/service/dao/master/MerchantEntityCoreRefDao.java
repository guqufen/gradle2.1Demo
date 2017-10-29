package net.fnsco.bigdata.service.dao.master;

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

    int updateByPrimaryKeySelective(MerchantEntityCoreRef record);

    int updateByPrimaryKey(MerchantEntityCoreRef record);
    
    /**
     * 返回满足条件的条数
     * @param record
     * @return
     */
    int countByCondition(MerchantEntityCoreRef record);
}