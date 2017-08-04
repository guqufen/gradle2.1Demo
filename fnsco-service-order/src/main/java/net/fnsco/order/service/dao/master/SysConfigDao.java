package net.fnsco.order.service.dao.master;

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
}