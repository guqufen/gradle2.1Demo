/**
 * 
 */
package net.fnsco.api.merchant;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.domain.MerchantCore;

/**@desc 商家基本信息
 * @author tangliang
 * @date 2017年6月21日 下午2:19:07
 */
public interface MerchantCoreService {
	
	/**
	 * 保存商家信息
	 * @param merchantInfo
	 * @param loginUserId
	 * @return
	 */
	public ResultDTO<Integer> doAdd(MerchantCore merchantInfo, int loginUserId);
}
