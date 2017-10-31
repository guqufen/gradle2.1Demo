package net.fnsco.bigdata.api.merchant;

import java.util.List;

import net.fnsco.bigdata.api.dto.MerShopDetailDTO;
import net.fnsco.bigdata.service.domain.MerchantShop;

/**
 * @desc 实体店铺service
 * @author tangliang
 * @version 0.0.1-SNAPSHOT
 * @since Ver 1.1
 * @Date 2017年10月31日 下午4:25:52
 */

public interface MerchantShopService {

	int deleteByPrimaryKey(Integer id);

	int insert(MerchantShop record);

	int insertSelective(MerchantShop record);

	MerchantShop selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(MerchantShop record);

	int updateByPrimaryKey(MerchantShop record);

	/**
	 * selectByEntityInnerCode:(这里用一句话描述这个方法的作用)
	 *
	 * @param @param
	 *            entityInnerCode
	 * @param @return
	 *            设定文件
	 * @return List<MerchantShop> DOM对象
	 * @author tangliang
	 * @date 2017年10月31日 下午2:45:13
	 */
	List<MerShopDetailDTO> selectByEntityInnerCode(String entityInnerCode);
	
	/**
	 * getMerShopInnerCode:(获取唯一的店铺商户号)
	 *
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @author tangliang
	 * @date   2017年10月31日 下午4:27:15
	 */
	String getMerShopInnerCode();
}
