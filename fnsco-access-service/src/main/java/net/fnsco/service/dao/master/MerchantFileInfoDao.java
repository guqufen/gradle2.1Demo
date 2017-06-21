package net.fnsco.service.dao.master;

import net.fnsco.api.dto.MerchantFileInfoDTO;

/**
 * @desc 商家文件DAO
 * @author tangliang
 * @date 2017年6月21日 上午10:22:16
 */
public interface MerchantFileInfoDao {

    int deleteByPrimaryKey(Integer id);

    int insert(MerchantFileInfoDTO record);

    int insertSelective(MerchantFileInfoDTO record);

    MerchantFileInfoDTO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MerchantFileInfoDTO record);

    int updateByPrimaryKey(MerchantFileInfoDTO record);
}