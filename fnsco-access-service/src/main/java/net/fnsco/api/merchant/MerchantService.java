/**
 * 
 */
package net.fnsco.api.merchant;

import java.util.List;

import net.fnsco.api.dto.MerChantCoreDTO;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.domain.MerchantTerminal;

/**@desc 商户相关服务接口
 * @author sxfei
 * @date 2017年6月21日 上午10:12:23
 */
public interface MerchantService {
    String getMerCode(String merNum, String channelType);
    
    /**
     * getMerchantsCoreByUserId:(这里用一句话描述这个方法的作用) 根据用户ID 查询商户列表
     *
     * @param userId
     * @return    设定文件
     * @return ResultDTO<MerchantCore>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    ResultDTO<List<MerChantCoreDTO>> getMerchantsCoreByUserId(Integer userId);
    
    /**
     * getMerchantTerminalByUserId:(这里用一句话描述这个方法的作用)根据用户ID 查询出关联的所有终端信息
     *
     * @param userId
     * @return    设定文件
     * @return ResultDTO<List<MerchantTerminal>>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    ResultDTO<List<MerchantTerminal>> getMerchantTerminalByUserId(Integer userId);
}
