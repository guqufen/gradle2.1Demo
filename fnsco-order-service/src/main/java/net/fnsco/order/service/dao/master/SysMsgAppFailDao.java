package net.fnsco.order.service.dao.master;

import java.util.List;

import net.fnsco.order.service.domain.SysMsgAppFail;

public interface SysMsgAppFailDao {

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