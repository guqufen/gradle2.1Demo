/**
 * 
 */
package net.fnsco.api.merchant;

import java.util.List;

import net.fnsco.api.dto.MerChantCoreDTO;
import net.fnsco.api.dto.MerChantCoreDetailDTO;
import net.fnsco.api.dto.MerTerminalsDTO;
import net.fnsco.api.dto.MerchantDTO;
import net.fnsco.api.dto.TerminalDetailDTO;
import net.fnsco.core.base.ResultDTO;

/**@desc 商户相关服务接口
 * @author sxfei
 * @date 2017年6月21日 上午10:12:23
 */
public interface MerchantService {
    String getMerCode(String merNum, String channelType);

    ResultDTO addMerChant(MerchantDTO merchantDTO);

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
    ResultDTO<List<MerTerminalsDTO>> getMerchantTerminalByUserId(Integer userId);
    
    /**
     * 
     * getMerChantDetailById:(这里用一句话描述这个方法的作用) 根据商家ID获取商家详细信息
     *
     * @param merId
     * @return    设定文件
     * @return ResultDTO<List<MerChantCoreDetailDTO>>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    ResultDTO<MerChantCoreDetailDTO> getMerChantDetailById(Integer merId);
    
    /**
     * getTerminalDetailByTerId:(这里用一句话描述这个方法的作用)根据设备ID查询详情
     *
     * @param terId
     * @return    设定文件
     * @return ResultDTO<TerminalDetailDTO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    ResultDTO<TerminalDetailDTO> getTerminalDetailByTerId(Integer terId);
}
