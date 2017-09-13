package net.fnsco.order.api.appuser;

import java.util.List;

import net.fnsco.order.api.dto.AppSettingDTO;
import net.fnsco.order.api.dto.AppUserSettingDTO;
import net.fnsco.order.service.domain.AppUserSetting;

/**
 * @desc   APP用户设置service接口
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年9月12日 下午2:57:19
 */
public interface AppUserSettingService {
    
    int deleteByPrimaryKey(Integer id);

    int insert(AppUserSetting record);

    int insertSelective(AppUserSetting record);

    AppUserSetting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppUserSetting record);

    int updateByPrimaryKey(AppUserSetting record);
    /**
     * updateByPrimaryKeySelective:(根据DTO参数更新)
     * @param record
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月12日 下午4:50:11
     * @return int    DOM对象
     */
    int updateByPrimaryKeySelective(AppSettingDTO record);
    /**
     * installSettingStatus:(根据app用户id获取设置状态信息)
     * @param userId
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月12日 下午3:13:18
     * @return List<AppUserSettingDTO>    DOM对象
     */
    List<AppUserSettingDTO> installSettingStatus(Integer userId);
}
