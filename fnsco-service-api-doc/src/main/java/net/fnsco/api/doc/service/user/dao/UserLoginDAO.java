package net.fnsco.api.doc.service.user.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.api.doc.service.user.entity.UserLoginDO;
import net.fnsco.api.doc.service.user.dao.helper.UserLoginProvider;

import java.util.List;;

public interface UserLoginDAO {

    @Results({ @Result(column = "create_date", property = "createDate"), @Result(column = "modify_date", property = "modifyDate"), @Result(column = "login_date", property = "loginDate"),
               @Result(column = "login_failure_count", property = "loginFailureCount"), @Result(column = "login_count", property = "loginCount"), @Result(column = "login_ip", property = "loginIp"),
               @Result(column = "user_id", property = "userId"), @Result(column = "login_type", property = "loginType"), @Result(column = "login_status", property = "loginStatus"),
               @Result(column = "auth_code", property = "authCode") })
    @Select("SELECT * FROM t_user_login WHERE id = #{id}")
    public UserLoginDO getById(@Param("id") int id);

    @Results({ @Result(column = "create_date", property = "createDate"), @Result(column = "modify_date", property = "modifyDate"), @Result(column = "login_date", property = "loginDate"),
               @Result(column = "login_failure_count", property = "loginFailureCount"), @Result(column = "login_count", property = "loginCount"), @Result(column = "login_ip", property = "loginIp"),
               @Result(column = "user_id", property = "userId"), @Result(column = "login_type", property = "loginType"), @Result(column = "login_status", property = "loginStatus"),
               @Result(column = "auth_code", property = "authCode") })
    @Select("SELECT * FROM t_user_login WHERE user_id = #{userId}")
    public UserLoginDO getByUserId(@Param("userId") Long userId);

    @Insert("update t_user_login set login_failure_count = 0 where login_failure_count > 0")
    public void resetLoginFailCount();

    @Insert("INSERT into t_user_login(id,create_date,modify_date,login_date,login_failure_count,login_count,login_ip,user_id,login_type,login_status,auth_code) VALUES (#{id},#{createDate},#{modifyDate},#{loginDate},#{loginFailureCount},#{loginCount},#{loginIp},#{userId},#{loginType},#{loginStatus},#{authCode})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(UserLoginDO userLogin);

    @Delete("DELETE FROM t_user_login WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = UserLoginProvider.class, method = "update")
    public int update(@Param("userLogin") UserLoginDO userLogin);

    @Results({ @Result(column = "create_date", property = "createDate"), @Result(column = "modify_date", property = "modifyDate"), @Result(column = "login_date", property = "loginDate"),
               @Result(column = "login_failure_count", property = "loginFailureCount"), @Result(column = "login_count", property = "loginCount"), @Result(column = "login_ip", property = "loginIp"),
               @Result(column = "user_id", property = "userId"), @Result(column = "login_type", property = "loginType"), @Result(column = "login_status", property = "loginStatus"),
               @Result(column = "auth_code", property = "authCode") })
    @SelectProvider(type = UserLoginProvider.class, method = "pageList")
    public List<UserLoginDO> pageList(@Param("userLogin") UserLoginDO userLogin, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = UserLoginProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("userLogin") UserLoginDO userLogin);

}