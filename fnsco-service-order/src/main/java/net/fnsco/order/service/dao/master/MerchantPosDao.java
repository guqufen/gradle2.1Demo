package net.fnsco.order.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.order.api.dto.PosDetailDTO;
import net.fnsco.order.api.dto.PosInfoDTO;
import net.fnsco.order.api.dto.PosListDTO;
import net.fnsco.order.service.domain.MerchantPos;
/**
 * @desc POS机信息DAO
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月16日 上午10:39:06
 */
public interface MerchantPosDao {

    int deleteByPrimaryKey(Integer id);

    int insert(MerchantPos record);

    int insertSelective(MerchantPos record);

    MerchantPos selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MerchantPos record);

    int updateByPrimaryKey(MerchantPos record);
    
    /**
     * selectPosNamesByInnerCode:(这里用一句话描述这个方法的作用)根据innercode查询出 POS姓名
     * @param innerCode
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月16日 下午1:27:24
     * @return List<String>    DOM对象
     */
    List<PosInfoDTO> selectPosNamesByInnerCode(@Param("innerCode")String innerCode);
    
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