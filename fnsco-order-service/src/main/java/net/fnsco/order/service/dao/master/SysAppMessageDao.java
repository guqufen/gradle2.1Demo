package net.fnsco.order.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.core.base.PageDTO;
import net.fnsco.order.service.domain.SysAppMessage;
/**
 * @desc APP信息推送实体操作DAO 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月11日 下午3:42:17
 *
 */
public interface SysAppMessageDao {

    int deleteByPrimaryKey(Integer id);

    int insert(SysAppMessage record);

    int insertSelective(SysAppMessage record);

    SysAppMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAppMessage record);

    int updateByPrimaryKey(SysAppMessage record);
    /**
     * updateStatusByIdAndStatus:(这里用一句话描述这个方法的作用) 逻辑删除 修改状态
     *
     * @param record
     * @return    设定文件
     * @return int    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    int updateStatusByIdAndStatus(SysAppMessage record);
    
    /**
     * 条件分页查询
     */
    List<SysAppMessage> queryPageList(PageDTO<SysAppMessage> pages);
    
    /**
     * 条件查询总数
     * @param pages
     * @return
     */
    int queryTotalByCondition(SysAppMessage record);
    
    /**
     * queryListByCondition:(这里用一句话描述这个方法的作用)根据条件查询
     *
     * @param record
     * @return    设定文件
     * @return List<SysAppMessage>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<SysAppMessage> queryListByCondition(SysAppMessage record);
    /**
     * queryExecuteData:(这里用一句话描述这个方法的作用) 查询待执行任务数据
     *
     * @return    设定文件
     * @return List<SysAppMessage>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<SysAppMessage> queryExecuteData();
    /**
     * queryListByIds:(这里用一句话描述这个方法的作用)根据ID查询出详情
     *
     * @param ids
     * @return    设定文件
     * @return List<SysAppMessage>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<SysAppMessage> queryListByIds(@Param("ids")List<Integer> ids);
}