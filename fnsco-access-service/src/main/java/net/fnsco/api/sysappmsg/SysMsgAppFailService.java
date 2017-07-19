package net.fnsco.api.sysappmsg;

import net.fnsco.service.domain.SysMsgAppFail;

/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月19日 下午3:48:37
 */

public interface SysMsgAppFailService {
    
    int deleteByPrimaryKey(Integer id);

    int insert(SysMsgAppFail record);

    int insertSelective(SysMsgAppFail record);

    SysMsgAppFail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMsgAppFail record);

    int updateByPrimaryKey(SysMsgAppFail record);
}
