package net.fnsco.service.dao.master;

import net.fnsco.service.domain.MerchantContact;

/**
 * @desc 商家联系信息DAO
 * @author tangliang
 * @date 2017年6月21日 下午2:45:50
 */
public interface MerchantContactDao {

    int insert(MerchantContact record);

    int insertSelective(MerchantContact record);

   
}