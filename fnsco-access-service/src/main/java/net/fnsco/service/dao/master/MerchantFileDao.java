package net.fnsco.service.dao.master;

import net.fnsco.service.domain.MerchantFile;

/**
 * @desc 商家文件DAO
 * @author tangliang
 * @date 2017年6月21日 上午10:22:16
 */
public interface MerchantFileDao {

    int deleteByPrimaryKey(Integer id);

    int insert(MerchantFile record);

    int insertSelective(MerchantFile record);

    MerchantFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MerchantFile record);

    int updateByPrimaryKey(MerchantFile record);
}