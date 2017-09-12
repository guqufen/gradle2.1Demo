package net.fnsco.risk.service.sys.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.risk.service.sys.entity.WebUserOuterDO;
import net.fnsco.risk.service.sys.dao.helper.WebUserOuterProvider;

import java.util.List;;

public interface WebUserOuterDAO {

    @Results({@Result( column = "real_name",property = "realName"),@Result( column = "alias_name",property = "aliasName"),@Result( column = "agent_id",property = "agentId"),@Result( column = "modify_time",property = "modifyTime"),@Result( column = "modify_user_id",property = "modifyUserId"),@Result( column = "creater_time",property = "createrTime") })
    @Select("SELECT * FROM risk_web_user_outer WHERE id = #{id}")
    public WebUserOuterDO getById(@Param("id") int id);

    @Insert("INSERT into risk_web_user_outer(id,type,name,password,real_name,mobile,email,sex,alias_name,department,agent_id,remark,modify_time,modify_user_id,creater_time) VALUES (#{id},#{type},#{name},#{password},#{realName},#{mobile},#{email},#{sex},#{aliasName},#{department},#{agentId},#{remark},#{modifyTime},#{modifyUserId},#{createrTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(WebUserOuterDO webUserOuter);

    @Delete("DELETE FROM risk_web_user_outer WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = WebUserOuterProvider.class, method = "update")
    public int update(@Param("webUserOuter") WebUserOuterDO  webUserOuter);

    @Results({@Result( column = "real_name",property = "realName"),@Result( column = "alias_name",property = "aliasName"),@Result( column = "agent_id",property = "agentId"),@Result( column = "modify_time",property = "modifyTime"),@Result( column = "modify_user_id",property = "modifyUserId"),@Result( column = "creater_time",property = "createrTime") })
    @SelectProvider(type = WebUserOuterProvider.class, method = "pageList")
    public List<WebUserOuterDO> pageList(@Param("webUserOuter") WebUserOuterDO webUserOuter, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = WebUserOuterProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("webUserOuter") WebUserOuterDO webUserOuter);

}