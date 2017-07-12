package net.fnsco.service.dao.master;

import java.util.List;

import net.fnsco.service.domain.SysMsgReceiver;
/**
 * @desc 推送消息接收信息DAO
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月12日 下午1:33:30
 */
public interface SysMsgReceiverDao {

    int deleteByPrimaryKey(Integer id);

    int insert(SysMsgReceiver record);

    int insertSelective(SysMsgReceiver record);

    SysMsgReceiver selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMsgReceiver record);

    int updateByPrimaryKey(SysMsgReceiver record);
    /**
     * queryListByCondition:(这里用一句话描述这个方法的作用) 条件查询数据
     *
     * @param record
     * @return    设定文件
     * @return List<SysMsgReceiver>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<SysMsgReceiver> queryListByCondition(SysMsgReceiver record);
}