/**
 * 
 */
package net.fnsco.bigdata.api.merchant;

import net.fnsco.bigdata.service.domain.MerchantFileTemp;
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
	ResultDTO<Integer> doAddToDB (MerchantFileTemp fileInfo,int loginUserId);
	
	/**
	 * 根据ID删除文件数据
	 * @param id
	 * @return
	 */
	boolean deleteFromDB(Integer id,String url);
}
