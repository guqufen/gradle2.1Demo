package net.fnsco.api.sysappmsg;

import net.fnsco.service.domain.SysMsgAppSucc;

/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月19日 下午3:22:51
 */

public interface SysMsgAppSuccService {
    int deleteByPrimaryKey(Integer id);

    int insert(SysMsgAppSucc record);

    int insertSelective(SysMsgAppSucc record);

    SysMsgAppSucc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMsgAppSucc record);

    int updateByPrimaryKey(SysMsgAppSucc record);
}
