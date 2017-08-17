package net.fnsco.auth.service.sys.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.auth.service.sys.entity.UserTokenDO;
import net.fnsco.auth.service.sys.dao.helper.UserTokenProvider;

import java.util.List;;

public interface UserTokenDAO {

    @Results({ @Result(column = "user_id", property = "userId"), @Result(column = "expire_time", property = "expireTime"), @Result(column = "update_time", property = "updateTime") })
    @Select("SELECT * FROM sys_user_token WHERE id = #{id}")
    public UserTokenDO getById(@Param("id") int id);

    @Results({ @Result(column = "user_id", property = "userId"), @Result(column = "expire_time", property = "expireTime"), @Result(column = "update_time", property = "updateTime") })
    @Select("SELECT * FROM sys_user_token WHERE token = #{token}")
    public UserTokenDO queryByToken(@Param("token") String token);

    @Results({ @Result(column = "user_id", property = "userId"), @Result(column = "expire_time", property = "expireTime"), @Result(column = "update_time", property = "updateTime") })
    @Select("SELECT * FROM sys_user_token WHERE user_id = #{userId}")
    public UserTokenDO queryByUserId(@Param("userId") Integer userId);

    @Insert("INSERT into sys_user_token(id,user_id,token,expire_time,update_time) VALUES (#{id},#{userId},#{token},#{expireTime},#{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(UserTokenDO userToken);

    @Delete("DELETE FROM sys_user_token WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = UserTokenProvider.class, method = "update")
    public int update(@Param("userToken") UserTokenDO userToken);

    @Results({ @Result(column = "user_id", property = "userId"), @Result(column = "expire_time", property = "expireTime"), @Result(column = "update_time", property = "updateTime") })
    @SelectProvider(type = UserTokenProvider.class, method = "pageList")
    public List<UserTokenDO> pageList(@Param("userToken") UserTokenDO userToken, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = UserTokenProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("userToken") UserTokenDO userToken);

}