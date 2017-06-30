package net.fnsco.service.dao.master;

import java.util.List;

import net.fnsco.service.domain.Agent;
/**
 * @desc 代理商DAO
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年6月30日 下午3:28:33
 *
 */
public interface AgentDao {

    int deleteByPrimaryKey(Integer id);

    int insert(Agent record);

    int insertSelective(Agent record);

    Agent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Agent record);

    int updateByPrimaryKey(Agent record);
    /**
     * queryAll:(这里用一句话描述这个方法的作用)查询 所有代理商
     *
     * @return    设定文件
     * @return List<Agent>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<Agent> queryAll();
}