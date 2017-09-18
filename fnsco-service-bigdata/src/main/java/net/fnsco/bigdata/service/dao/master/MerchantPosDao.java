package net.fnsco.bigdata.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.bigdata.api.dto.PosDetailDTO;
import net.fnsco.bigdata.api.dto.PosInfoDTO;
import net.fnsco.bigdata.api.dto.PosInfosDTO;
import net.fnsco.bigdata.api.dto.PosListDTO;
import net.fnsco.bigdata.service.domain.MerchantPos;
/**
 * @desc POS机信息DAO
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月16日 上午10:39:06
 */
public interface MerchantPosDao {

    int deleteByPrimaryKey(Integer id);
    /**
     * deleteByChannelId:(这里用一句话描述这个方法的作用)根据channelId删除
     * @param channelId
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月17日 下午1:41:59
     * @return int    DOM对象
     */
    int deleteByChannelId(@Param("channelId")Integer channelId);

    int insert(MerchantPos record);
    
    Integer insertPos(MerchantPos record);

    int insertSelective(MerchantPos record);

    MerchantPos selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MerchantPos record);

    int updateByPrimaryKey(MerchantPos record);
    
    /**
     * selectAllByUserId:(这里用一句话描述这个方法的作用)根据userID查询列表
     * @param userId
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月18日 下午4:58:22
     * @return List<PosInfoDTO>    DOM对象
     */
    List<PosInfoDTO> selectAllByUserId(@Param("userId")Integer userId);
    /**
     * selectByChannelId:(这里用一句话描述这个方法的作用)根据channelId查询
     * @param channelId
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月17日 下午2:44:43
     * @return List<MerchantPos>    DOM对象
     */
    List<MerchantPos> selectByChannelId(@Param("channelId")Integer channelId);
    
    /**
     * selectPosNamesByInnerCode:(这里用一句话描述这个方法的作用)根据innercode查询出 POS姓名
     * @param innerCode
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月16日 下午1:27:24
     * @return List<String>    DOM对象
     */
    List<PosInfosDTO> selectPosNamesByInnerCode(@Param("innerCode")String innerCode);
    
    /**
     * selectAllPosInfo:(这里用一句话描述这个方法的作用) 根据用户ID查询所有POS机设备
     * @param userId
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月16日 下午1:33:08
     * @return List<PosListDTO>    DOM对象
     */
    List<PosListDTO> selectAllPosInfo(@Param("userId")Integer userId);
    
    /**
     * selectDetailById:(这里用一句话描述这个方法的作用)根据ID查询详情
     * @param id
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月16日 下午2:04:13
     * @return PosDetailDTO    DOM对象
     */
    PosDetailDTO selectDetailById(Integer id);
}