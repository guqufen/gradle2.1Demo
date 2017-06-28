/**
 * 
 */
package net.fnsco.api.merchant;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.domain.MerchantFileTemp;

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
	public ResultDTO<Integer> doAddToDB (MerchantFileTemp fileInfo,int loginUserId);
	
	/**
	 * 根据ID删除文件数据
	 * @param id
	 * @return
	 */
	public boolean deleteFromDB(Integer id,String url);
}
