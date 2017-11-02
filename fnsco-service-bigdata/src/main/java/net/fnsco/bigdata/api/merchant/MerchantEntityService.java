package net.fnsco.bigdata.api.merchant;

import net.fnsco.bigdata.service.domain.MerchantEntity;

/**
 * @desc  商户实体service类
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年10月27日 上午10:57:56
 */

public interface MerchantEntityService {
	
	int deleteByPrimaryKey(Integer id);

    int insert(MerchantEntity record);

    int insertSelective(MerchantEntity record);

    MerchantEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MerchantEntity record);

    int updateByPrimaryKey(MerchantEntity record);
}
