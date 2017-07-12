package net.fnsco.service.dao.master;

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
}