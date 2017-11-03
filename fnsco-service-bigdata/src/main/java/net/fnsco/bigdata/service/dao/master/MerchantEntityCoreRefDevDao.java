package net.fnsco.bigdata.service.dao.master;

import org.apache.ibatis.annotations.Param;

import net.fnsco.bigdata.service.domain.MerchantEntityCoreRefDev;
/**
 * @desc 商户店铺信息关系DAO
 * @author   tangliang
 * @since    Ver 1.1
 * @Date	 2017年10月26日 下午2:57:13
 *
 */
public interface MerchantEntityCoreRefDevDao {

    int deleteByPrimaryKey(Integer id);

    int insert(MerchantEntityCoreRefDev record);

    int insertSelective(MerchantEntityCoreRefDev record);

    MerchantEntityCoreRefDev selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MerchantEntityCoreRefDev record);

    int updateByPrimaryKey(MerchantEntityCoreRefDev record);
    
    /**
     * selectByInnerCode:(根据innercode查询关系)
     *
     * @param  @param innerCode
     * @param  @return    设定文件
     * @return MerchantEntityCoreRef    DOM对象
     * @author tangliang
     * @date   2017年11月2日 上午10:54:11
     */
    MerchantEntityCoreRefDev selectByInnerCode(@Param("innerCode")String innerCode);
}