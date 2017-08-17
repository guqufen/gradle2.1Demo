/**
 * 
 */
package net.fnsco.order.api.merchant;

import java.util.List;

import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.dto.MerChantCoreDTO;
import net.fnsco.order.api.dto.MerChantCoreDetailDTO;
import net.fnsco.order.api.dto.MerTerminalsDTO;
import net.fnsco.order.api.dto.MerchantDTO;
import net.fnsco.order.api.dto.PosDetailDTO;
import net.fnsco.order.api.dto.PosListDTO;
import net.fnsco.order.api.dto.TerminalDetailDTO;
import net.fnsco.order.api.dto.TerminalsDTO;
import net.fnsco.order.api.dto.TradeMerchantDTO;
import net.fnsco.order.controller.app.jo.TerminalJO;

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
    
    
    ResultDTO<TerminalsDTO> updateTerminal(TerminalsDTO terminalsDTO);
    
    /**
     * getShopOwnerMerChant:(这里用一句话描述这个方法的作用)根据userId查询是店主的商户信息
     *
     * @param merchantDTO
     * @return    设定文件
     * @return List<TradeMerchantDTO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<TradeMerchantDTO> getShopOwnerMerChant(MerchantDTO merchantDTO);
    
    /**
     * getAllPosInfo:(这里用一句话描述这个方法的作用)获取POS机列表
     * @param merchantDTO
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月16日 下午1:15:09
     * @return List<PosListDTO>    DOM对象
     */
    List<PosListDTO> getAllPosInfo(MerchantDTO merchantDTO);
    /**
     * getPosDetail:(这里用一句话描述这个方法的作用)根据POSid查询详情
     * @param posId
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月16日 下午2:14:40
     * @return PosDetailDTO    DOM对象
     */
    PosDetailDTO getPosDetail(Integer posId);
    
    /**
     * updatePosInfo:(这里用一句话描述这个方法的作用)更新POS设备信息
     * @param terminalJO
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月16日 下午2:50:03
     * @return boolean    DOM对象
     */
    boolean updatePosInfo(TerminalJO terminalJO);
}
