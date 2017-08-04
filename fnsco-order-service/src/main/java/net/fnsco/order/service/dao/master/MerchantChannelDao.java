package net.fnsco.order.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.order.service.domain.MerchantChannel;

/**
 * @desc 商户渠道DAO
 * @author tangliang
 * @date 2017年6月22日 下午4:07:59
 */
public interface MerchantChannelDao {

    int deleteByPrimaryKey(Integer id);

    int insert(MerchantChannel record);

    int insertSelective(MerchantChannel record);

    MerchantChannel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MerchantChannel record);

    int updateByPrimaryKey(MerchantChannel record);

    MerchantChannel selectByMerCode(@Param("merCode") String merCode, @Param("channelType") String channelType);
    
    /**
     * 根据innercode查询
     * @param innerCode
     * @return
     */
    List<MerchantChannel> selectByInnerCode(@Param("innerCode") String innerCode);
    /**
     * deleteByMerCoreIds:(这里用一句话描述这个方法的作用) 根据core实体IDS删除关联数据
     *
     * @param ids
     * @return    设定文件
     * @return int    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    int deleteByMerCoreIds(Integer[] ids);
}