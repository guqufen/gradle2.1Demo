package net.fnsco.api.sysappmsg;

import java.util.List;

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
    /**
     * queryFailMsg:(这里用一句话描述这个方法的作用)条件查询
     *
     * @param record
     * @return    设定文件
     * @return List<SysMsgAppFail>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<SysMsgAppFail> queryFailMsg(SysMsgAppFail record);
}
