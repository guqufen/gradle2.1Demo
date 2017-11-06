package net.fnsco.bigdata.api.merchant;

import java.util.List;

import net.fnsco.bigdata.api.dto.MerShopDetailDTO;
import net.fnsco.bigdata.service.domain.MerchantShopDev;

/**
 * @desc 实体店铺service
 * @author tangliang
 * @version 0.0.1-SNAPSHOT
 * @since Ver 1.1
 * @Date 2017年10月31日 下午4:25:52
 */

public interface MerchantShopService {

	int deleteByPrimaryKey(Integer id);

	int insert(MerchantShopDev record);

	int insertSelective(MerchantShopDev record);

	MerchantShopDev selectByPrimaryKey(Integer id);
	
	/**
	 * deleteByShopInnerCode:(按照SHOPINNERCode删除)
	 *
	 * @param  @param shopInnerCode
	 * @param  @return    设定文件
	 * @return int    DOM对象
	 * @author tangliang
	 * @date   2017年11月1日 上午11:40:20
	 */
	int deleteByShopInnerCode(String shopInnerCode);
	
	/**
	 * updateByShopInnerCodeSelective:(按照条件更新)
	 *
	 * @param  @param record
	 * @param  @return    设定文件
	 * @return int    DOM对象
	 * @author tangliang
	 * @date   2017年11月1日 上午10:10:38
	 */
	int updateByShopInnerCodeSelective(MerchantShopDev record);

	int updateByPrimaryKeySelective(MerchantShopDev record);

	int updateByPrimaryKey(MerchantShopDev record);

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
