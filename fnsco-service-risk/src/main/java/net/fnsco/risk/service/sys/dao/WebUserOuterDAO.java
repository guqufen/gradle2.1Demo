package net.fnsco.risk.service.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import net.fnsco.risk.service.sys.dao.helper.WebUserOuterProvider;
import net.fnsco.risk.service.sys.entity.AgentDO;
import net.fnsco.risk.service.sys.entity.WebUserOuterDO;;

public interface WebUserOuterDAO {

	@Results({ @Result(column = "email", property = "email"), @Result(column = "real_name", property = "realName"),
			@Result(column = "alias_name", property = "aliasName"), @Result(column = "agent_id", property = "agentId"),
			@Result(column = "modify_time", property = "modifyTime"),
			@Result(column = "modify_user_id", property = "modifyUserId"),
			@Result(column = "creater_time", property = "createrTime") })
	@Select("SELECT * FROM risk_web_user_outer WHERE id = #{id}")
	public WebUserOuterDO getById(@Param("id") int id);

	@Insert("INSERT into risk_web_user_outer(id,type,name,password,real_name,mobile,email,sex,alias_name,department,agent_id,remark,modify_time,modify_user_id,creater_time,status) VALUES (#{id},#{type},#{name},#{password},#{realName},#{mobile},#{email},#{sex},#{aliasName},#{department},#{agentId},#{remark},#{modifyTime},#{modifyUserId},#{createrTime},1)")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insert(WebUserOuterDO webUserOuter);

	@Update("UPDATE risk_web_user_outer SET status=#{status} WHERE id = #{id}")
	public int updateById(@Param("id") Integer id, @Param("status") Integer status);

	@UpdateProvider(type = WebUserOuterProvider.class, method = "update")
	public int update(@Param("webUserOuter") WebUserOuterDO webUserOuter);

	@Results({ @Result(column = "real_name", property = "realName"),
			@Result(column = "alias_name", property = "aliasName"), @Result(column = "agent_id", property = "agentId"),
			@Result(column = "modify_time", property = "modifyTime"),
			@Result(column = "modify_user_id", property = "modifyUserId"),
			@Result(column = "creater_time", property = "createrTime") })
	@SelectProvider(type = WebUserOuterProvider.class, method = "pageList")
	public List<WebUserOuterDO> pageList(@Param("webUserOuter") WebUserOuterDO webUserOuter,
			@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

	@SelectProvider(type = WebUserOuterProvider.class, method = "pageListCount")
	public Integer pageListCount(@Param("webUserOuter") WebUserOuterDO webUserOuter);

	@Results({ @Result(column = "real_name", property = "realName"),
			@Result(column = "alias_name", property = "aliasName"), @Result(column = "agent_id", property = "agentId"),
			@Result(column = "modify_time", property = "modifyTime"),
			@Result(column = "modify_user_id", property = "modifyUserId"),
			@Result(column = "creater_time", property = "createrTime") })
	@Select("SELECT * FROM risk_web_user_outer WHERE name = #{name}")
	public WebUserOuterDO getByUserName(@Param("name") String name);

	@Results({ @Result(column = "id", property = "id"), @Result(column = "name", property = "name") })
	@Select("SELECT * FROM m_agent")
	public List<AgentDO> queryType();

	@Results({ @Result(column = "name", property = "name") })
	@Select("SELECT name FROM m_agent WHERE id= #{id}")
	public String queryTypeName(Integer id);

	@Select("SELECT id FROM risk_web_user_outer WHERE agent_id in(SELECT distinct agent_id FROM risk_user_merc_rel WHERE inner_code = #{innerCode})")
	public List<Integer> getByInnercode(String innerCode);

	@Results({ @Result(column = "real_name", property = "realName"),
			@Result(column = "alias_name", property = "aliasName"), @Result(column = "agent_id", property = "agentId"),
			@Result(column = "modify_time", property = "modifyTime"),
			@Result(column = "modify_user_id", property = "modifyUserId"),
			@Result(column = "creater_time", property = "createrTime") })
	@SelectProvider(type = WebUserOuterProvider.class, method = "pageMerAlloList")
	public List<WebUserOuterDO> pageMercAlloList(@Param("webUserOuter") WebUserOuterDO webUserOuter,
			@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

//	@SelectProvider(type = WebUserOuterProvider.class, method = "pageMerAlloListCount")
//	public Integer pageMercAlloListCount(@Param("department") String department,@Param("agentList") List<Integer> agentList);
	
	@SelectProvider(type = WebUserOuterProvider.class, method = "pageMerAlloListCount")
	public Integer pageMercAlloListCount(@Param("webUserOuter") WebUserOuterDO webUserOuter);
}