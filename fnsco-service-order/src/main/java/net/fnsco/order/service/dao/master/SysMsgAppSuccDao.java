package net.fnsco.order.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.order.service.domain.SysMsgAppSucc;

/**
 * @desc 成功消息保存DAO
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月19日 下午3:21:00
 */
public interface SysMsgAppSuccDao {

    int deleteByPrimaryKey(Integer id);

    int insert(SysMsgAppSucc record);

    int insertSelective(SysMsgAppSucc record);

    SysMsgAppSucc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMsgAppSucc record);

    int updateByPrimaryKey(SysMsgAppSucc record);
    /**
     * updateByMsgIds:(这里用一句话描述这个方法的作用) 根据IDs更新
     *
     * @param msgIds
     * @return    设定文件
     * @return int    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    int updateByMsgIds(@Param("msgIds")List<Integer> msgIds);
    /**
     * selectTotalUnreadCount:(这里用一句话描述这个方法的作用)根据用户ID查询未读条数
     *
     * @param userId
     * @return    设定文件
     * @return int    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    int selectTotalUnreadCount(@Param("userId") Integer userId);
    
    /**
     * selectTotalUnread:(这里用一句话描述这个方法的作用)根据用户ID查询未读信息IDS
     *
     * @param userId
     * @return    设定文件
     * @return List<SysMsgAppSucc>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<SysMsgAppSucc> selectTotalUnread(@Param("userId") Integer userId);
}