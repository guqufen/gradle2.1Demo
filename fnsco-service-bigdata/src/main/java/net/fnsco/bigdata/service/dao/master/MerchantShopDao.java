package net.fnsco.bigdata.service.dao.master;

import net.fnsco.bigdata.service.domain.MerchantShop;
/**
 * @desc 店铺DAO
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年10月27日 上午9:50:36
 */
public interface MerchantShopDao {

    int deleteByPrimaryKey(Integer id);

    int insert(MerchantShop record);

    int insertSelective(MerchantShop record);

    MerchantShop selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MerchantShop record);

    int updateByPrimaryKey(MerchantShop record);
}