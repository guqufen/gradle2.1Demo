package net.fnsco.risk.service.report.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.risk.service.report.entity.UserMercRelDO;
import net.fnsco.risk.service.report.dao.helper.UserMercRelProvider;

import java.util.List;;

public interface UserMercRelDAO {

    @Results({@Result( column = "agent_id",property = "agentId"),@Result( column = "entity_inner_code",property = "entityInnerCode") })
    @Select("SELECT * FROM risk_user_merc_rel WHERE id = #{id}")
    public UserMercRelDO getById(@Param("id") int id);

    //根据innerCode查询风控信息
    @Results({@Result( column = "agent_id",property = "agentId"),@Result( column = "entity_inner_code",property = "entityInnerCode") })
    @Select("SELECT * FROM risk_user_merc_rel WHERE entity_inner_code = #{entityInnerCode}")
    public UserMercRelDO getByInnerCode(@Param("entityInnerCode") String entityInnerCode);

    
    @Insert("INSERT into risk_user_merc_rel(id, agent_id, entity_inner_code) VALUES (#{id},#{agentId},#{entityInnerCode})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(UserMercRelDO userMercRel);

    @Delete("DELETE FROM risk_user_merc_rel WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = UserMercRelProvider.class, method = "update")
    public int update(@Param("userMercRel") UserMercRelDO  userMercRel);

    @Results({@Result( column = "agent_id",property = "agentId"),@Result( column = "entity_inner_code",property = "entityInnerCode") })
    @SelectProvider(type = UserMercRelProvider.class, method = "pageList")
    public List<UserMercRelDO> pageList(@Param("userMercRel") UserMercRelDO userMercRel, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = UserMercRelProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("userMercRel") UserMercRelDO userMercRel);

    /**
     * 通过agentId查找内部商户号集合
     * @param agentId
     * @return
     */
    @Select("Select inner_code from risk_user_merc_rel WHERE agent_id = #{agentId}")
    public List<String> getinnerCodeByAgentid(@Param("agentId") Integer agentId);
    
    /**
     * 查询所有的商户号
     * @return
     */
    @Select("SELECT DISTINCT entity_inner_code FROM risk_user_merc_rel")
    public List<String> getAllEntityInnerCode();
    
    /**
     * 通过agentId和innerCode移除数据
     */
    @Delete("delete FROM risk_user_merc_rel WHERE agent_id = #{userMercRel.agentId} AND entity_inner_code = #{userMercRel.entityInnerCode}")
    public int deleteByagentEntityInnerCode(@Param("userMercRel")UserMercRelDO userMercRel);

}