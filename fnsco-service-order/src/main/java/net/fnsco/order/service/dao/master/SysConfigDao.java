package net.fnsco.order.service.dao.master;

import java.util.List;

import net.fnsco.order.service.domain.SysConfig;
/**
 * @desc 配置DAO
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月2日 下午3:02:19
 */
public interface SysConfigDao {

    int deleteByPrimaryKey(Integer id);

    int insert(SysConfig record);

    int insertSelective(SysConfig record);

    SysConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateByPrimaryKey(SysConfig record);
    /**
     * selectByCondition:(这里用一句话描述这个方法的作用)根据name属性来查询属性值
     *
     * @param record
     * @return    设定文件
     * @return SysConfig    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    SysConfig selectByCondition(SysConfig record);
    
    List<SysConfig> selectAllByCondition(SysConfig record);
    
    /**
     * 通过积分查询商户所在等级
     * @param record
     * @return
     */
    SysConfig selectLevelByScores(SysConfig record);
    
    
    /**
     * 通过积分查询商户所在等级
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
}