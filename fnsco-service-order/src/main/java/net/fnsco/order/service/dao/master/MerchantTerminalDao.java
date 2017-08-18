package net.fnsco.order.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.order.api.dto.TerminalDetailDTO;
import net.fnsco.order.api.dto.TerminalInfoDTO;
import net.fnsco.order.api.dto.TerminalsDTO;
import net.fnsco.order.service.domain.MerchantTerminal;
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
     * queryTerByPosId:(这里用一句话描述这个方法的作用)根据POS ID获取
     * @param posId
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月16日 下午2:09:34
     * @return List<String>    DOM对象
     */
    List<TerminalInfoDTO> queryTerByPosId(@Param("posId")Integer posId);
    
    /**
     * queryAllTerByPosId:(这里用一句话描述这个方法的作用)查询所有数据
     * @param posId
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月17日 下午2:49:34
     * @return List<MerchantTerminal>    DOM对象
     */
    List<MerchantTerminal> queryAllTerByPosId(@Param("posId")Integer posId);
    
}