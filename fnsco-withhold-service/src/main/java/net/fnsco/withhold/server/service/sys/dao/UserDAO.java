package net.fnsco.withhold.server.service.sys.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.withhold.server.service.sys.entity.UserDO;
import net.fnsco.withhold.server.service.sys.dao.helper.UserProvider;

import java.util.List;;

public interface UserDAO {

    @Select("SELECT * FROM sys_user WHERE id = #{id}")
    public UserDO getById(@Param("id") int id);

    @Insert("INSERT into sys_user(id,type,name,password,realName,mobile,sex,aliasName,department,agentId,remark,modifyTime,modifyUserId) VALUES (#{id},#{type},#{name},#{password},#{realName},#{mobile},#{sex},#{aliasName},#{department},#{agentId},#{remark},#{modifyTime},#{modifyUserId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(UserDO user);

    @Delete("DELETE FROM sys_user WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = UserProvider.class, method = "update")
    public int update(@Param("user") UserDO  user);

    @SelectProvider(type = UserProvider.class, method = "pageList")
    public List<UserDO> pageList(@Param("user") UserDO user, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = UserProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("user") UserDO user);

}