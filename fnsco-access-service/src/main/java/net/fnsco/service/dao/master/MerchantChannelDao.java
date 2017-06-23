package net.fnsco.service.dao.master;

import net.fnsco.service.domain.MerchantChannel;
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
}