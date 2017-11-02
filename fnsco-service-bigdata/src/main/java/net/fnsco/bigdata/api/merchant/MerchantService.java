/**
 * 
 */
package net.fnsco.bigdata.api.merchant;

import java.util.List;

import net.fnsco.bigdata.api.dto.MerChantCoreDTO;
import net.fnsco.bigdata.api.dto.MerChantCoreDetailDTO;
import net.fnsco.bigdata.api.dto.MerTerminalsDTO;
import net.fnsco.bigdata.api.dto.MerchantDTO;
import net.fnsco.bigdata.api.dto.PosDetailDTO;
import net.fnsco.bigdata.api.dto.PosInfoDTO;
import net.fnsco.bigdata.api.dto.PosListDTO;
import net.fnsco.bigdata.api.dto.TerminalDetailDTO;
import net.fnsco.bigdata.api.dto.TerminalsDTO;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantTerminal;
import net.fnsco.bigdata.service.domain.MerchantUserRel;
import net.fnsco.core.base.ResultDTO;

/**@desc 商户相关服务接口
 * @author sxfei
 * @date 2017年6月21日 上午10:12:23
 */
public interface MerchantService {
    String getMerCode(String merNum, String channelType);
    /**
     * 获取商户渠道信息信息
     * @param merNum 商户号
     * @param channelType
     * @return
     */
    public MerchantChannel getMerChannel(String merCode, String channelType);
    /**
     * 
     * updatePosName:(更新pos机名称，拉卡拉机器使用)
     *
     * @param snCode
     * @param posName   void    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    void updatePosName(String snCode, String posName);

    /**
     * 
     * getPosName:(查询pos机名称)
     *
     * @param snCode
     * @return   String    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    String getPosName(String snCode);
    /**
     * 
     * getPosName:(查询pos机对应的app用户)
     *
     * @param snCode
     * @return   String    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<String> getMerchantAppUser(String snCode);
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
     * getMerchantsScoresByUserId：根据用户ID查询商户积分信息列表
     * @param userId:用户ID
     * @return
     */
    List<MerChantCoreDTO> getMerchantsScoresByUserId(Integer userId);
    /**
     * getMerchantsScoresByUserId：根据用户ID和内部商户号查询商户积分信息列表
     * @param userId:用户ID
     * @return
     */
    MerChantCoreDTO getScoreByUserIdInnerCode(MerchantUserRel merchantUserRel);

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
     * getAllPosInfo:(这里用一句话描述这个方法的作用)获取POS机列表
     * @param merchantDTO
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月16日 下午1:15:09
     * @return List<PosListDTO>    DOM对象
     */
    List<PosListDTO> getAllPosInfo(MerchantDTO merchantDTO);

    /**
     * getAllReportPos:(这里用一句话描述这个方法的作用)查询用户下面所有的POS列表信息
     * @param merchantDTO
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月18日 下午4:53:46
     * @return List<PosInfoDTO>    DOM对象
     */
    List<PosInfoDTO> getAllReportPos(MerchantDTO merchantDTO);

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
    boolean updatePosInfo(TerminalsDTO dto);

    /**
     * 
     * getTerminalDetailByCode:(根据终端号查询终端信息)
     *
     * @param terminalCode
     * @return   TerminalsDTO    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    MerchantTerminal getTerminalDetailByCode(String terminalCode);
    
}
