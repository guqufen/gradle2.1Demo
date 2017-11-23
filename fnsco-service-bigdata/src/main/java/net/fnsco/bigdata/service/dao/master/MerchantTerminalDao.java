package net.fnsco.bigdata.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.bigdata.api.dto.TerminalDetailDTO;
import net.fnsco.bigdata.api.dto.TerminalInfoDTO;
import net.fnsco.bigdata.api.dto.TerminalsDTO;
import net.fnsco.bigdata.service.domain.MerchantTerminal;
/**
 * @desc 商家终端信息DAO
 * @author tangliang
 * @date 2017年6月22日 下午3:18:34
 */
public interface MerchantTerminalDao {

    int deleteByPrimaryKey(Integer id);
    /**
     * deleteByChannelId:(这里用一句话描述这个方法的作用)根据channelId删除数据
     * @param channelId
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月17日 下午1:46:29
     * @return int    DOM对象
     */
    int deleteByChannelId(@Param("channelId")Integer channelId);
    /**
     * deleteByPosId:(这里用一句话描述这个方法的作用)根据POSid删除
     * @param posId
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月17日 下午2:16:08
     * @return int    DOM对象
     */
    int deleteByPosId(@Param("posId")Integer posId);
    
    int insert(MerchantTerminal record);
    /**
     * insertBatch:(这里用一句话描述这个方法的作用)批量插入数据
     * @param record
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月17日 下午1:26:08
     * @return int    DOM对象
     */
    int insertBatch(List<MerchantTerminal> record);
    
    int insertSelective(MerchantTerminal record);

    MerchantTerminal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MerchantTerminal record);

    int updateByPrimaryKey(MerchantTerminal record);
    
    /**
     * selectByTerminalCode:(这里用一句话描述这个方法的作用)根据终端号查询费率
     * @param terCode
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月30日 下午1:38:33
     * @return MerchantTerminal    DOM对象
     */
    List<MerchantTerminal> selectByTerminalCode(@Param("terCode")String terCode);
    
    /**
     * 根据innercode查询出实体
     * @param innerCode
     * @return
     */
    List<MerchantTerminal> selectByInnerCode(@Param("innerCode")String innerCode);
    /**
     * 
     * queryByInnerCode:(这里用一句话描述这个方法的作用) APP端使用
     *
     * @param innerCode
     * @return    设定文件
     * @return List<TerminalsDTO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<TerminalsDTO> queryByInnerCode(@Param("innerCode")String innerCode);
    /**
     * selectByUserId:(这里用一句话描述这个方法的作用) 根据用户ID查询出所有的终端信息
     *
     * @param userId
     * @return    设定文件
     * @return List<MerchantTerminal>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<MerchantTerminal> selectByUserId(@Param("userId") Integer userId);
    /**
     * queryDetailById:(这里用一句话描述这个方法的作用) 根据ID查询详情 APP使用
     *
     * @param terId
     * @return    设定文件
     * @return List<TerminalDetailDTO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    TerminalDetailDTO queryDetailById(@Param("terId") Integer terId);
    /**
     * deleteByMerCoreIds:(这里用一句话描述这个方法的作用) 根据core实体IDS删除关联数据
     *
     * @param ids
     * @return    设定文件
     * @return int    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    int deleteByMerCoreIds(Integer[] ids);
    
    /**
     * queryAllTerByPosId:(这里用一句话描述这个方法的作用)查询所有数据
     * @param posId
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月17日 下午2:49:34
     * @return List<MerchantTerminal>    DOM对象
     */
    List<MerchantTerminal> queryAllTerByPosId(@Param("posId")Integer posId);
    /**
     * 通过终端号查询sncode
     * @param id
      * @return
     */
     String querySnCode(@Param("id") String id,@Param("code") String code);
     
     /**
      * selectByTerminalType:(查询)
      * @param posId
      * @param innerCode
      * @param terminalType
      * @return    设定文件
      * @author    tangliang
      * @date      2017年9月22日 下午2:32:25
      * @return MerchantTerminal    DOM对象
      */
     MerchantTerminal selectByTerminalType(@Param("posId")Integer posId,@Param("innerCode")String innerCode,@Param("terminalType")String terminalType);
     
}