/**
 * 
 */
package net.fnsco.api.merchant;

import net.fnsco.api.dto.MerchantFileInfoDTO;
import net.fnsco.core.base.ResultDTO;

/**@desc 文件信息接口
 * @author tangliang
 * @date 2017年6月21日 上午10:12:23
 */
public interface MerchantInfoService {
	
	/**
	 * 增加文件信息入库
	 * @param fileInfo
	 * @param loginUserId
	 * @return
	 */
	public ResultDTO<Integer> doAddToDB (MerchantFileInfoDTO fileInfo,int loginUserId);
}
