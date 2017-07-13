package net.fnsco.service.dao.master;

import org.apache.ibatis.annotations.Param;

import net.fnsco.service.domain.MsgRead;
/**
 * @desc 记录APP用户阅读消息时间DAO
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月11日 下午5:49:13
 */
public interface MsgReadDao {

    int deleteByPrimaryKey(Integer id);

    int insert(MsgRead record);

    int insertSelective(MsgRead record);

    MsgRead selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsgRead record);

    int updateByPrimaryKey(MsgRead record);
    /**
     * selectByUserId:(这里用一句话描述这个方法的作用) 根据userID查询
     *
     * @param userId
     * @return    设定文件
     * @return MsgRead    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    MsgRead selectByUserId(@Param("userId") Integer userId);
}