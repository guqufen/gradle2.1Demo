package net.fnsco.order.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.order.api.dto.AppUserSettingDTO;
import net.fnsco.order.service.domain.AppUserSetting;

/**
 * @desc APP消息设置DAO
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年9月12日 下午2:51:51
 */
public interface AppUserSettingDao {

    int deleteByPrimaryKey(Integer id);
    /**
     * deleteByUserId:(根据用户ID删除数据)
     * @param userId
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月19日 下午2:24:45
     * @return int    DOM对象
     */
    int deleteByUserId(@Param("userId")Integer userId);
    
    int insert(AppUserSetting record);

    int insertSelective(AppUserSetting record);

    AppUserSetting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppUserSetting record);

    int updateByPrimaryKey(AppUserSetting record);
    /**
     * updateByCondition:(根据入参更新数据)
     * @param record
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月12日 下午4:53:47
     * @return int    DOM对象
     */
    int updateByCondition(AppUserSetting record);
    /**
     * selectAllByUserId:(这里用一句话描述这个方法的作用)根据APP用户ID 
     * @param userId
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月12日 下午3:08:24
     * @return List<AppUserSettingDTO>    DOM对象
     */
    List<AppUserSettingDTO> selectAllByUserId(@Param("userId")Integer userId);
    
    /**
     * selectByUserIdAndType:(根据用户id和消息类型查询状态)
     * @param userId
     * @param noticeType
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月13日 上午11:48:13
     * @return AppUserSetting    DOM对象
     */
    AppUserSetting selectByUserIdAndType(@Param("userId")Integer userId,@Param("noticeType")String noticeType);
}