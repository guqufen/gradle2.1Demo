package net.fnsco.bigdata.api.merchant;

import net.fnsco.bigdata.service.domain.MerchantEntityDev;

/**
 * @desc  商户实体service类
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年10月27日 上午10:57:56
 */

public interface MerchantEntityDevService {
	
	int deleteByPrimaryKey(Integer id);

    int insert(MerchantEntityDev record);

    int insertSelective(MerchantEntityDev record);

    MerchantEntityDev selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MerchantEntityDev record);

    int updateByPrimaryKey(MerchantEntityDev record);
}
