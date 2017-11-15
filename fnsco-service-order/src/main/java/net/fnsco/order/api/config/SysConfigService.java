package net.fnsco.order.api.config;

import java.util.List;

import net.fnsco.bigdata.api.dto.PermissionsDTO;
import net.fnsco.order.api.dto.AppConfigDTO;
import net.fnsco.order.service.domain.SysConfig;

/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月2日 下午3:17:30
 */

public interface SysConfigService {
    int deleteByPrimaryKey(Integer id);

    int insert(SysConfig record);

    int insertSelective(SysConfig record);

    SysConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateByPrimaryKey(SysConfig record);
    
    SysConfig selectByCondition(SysConfig record);
    /**
     * getValueUrl:(这里用一句话描述这个方法的作用)
     *
     * @param appConfigDTO
     * @return    设定文件
     * @return String    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    String getValueUrl(AppConfigDTO appConfigDTO);
    
    List<SysConfig> selectAllByCondition(SysConfig record);
    
    /**
     * 通过商户积分查询所属vip等级
     * @param record
     * @return
     */
    SysConfig selectLevelByScores(SysConfig record);
    /**
     * 通过商户积分查询下一级vip等级
     * @param record
     * @return
     */
    SysConfig selectNextLevelByScores(SysConfig record);
    
    /**
     * 通过type查询最大值所在列，便于积分判断
     * @param type
     * @return
     */
    SysConfig selectMaxByType(String type);
    /**
     * 获取等级权限
     * @return
     */
    List<PermissionsDTO> selectLevelPrivilege();
}
