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
import net.fnsco.api.doc.service.user.entity.UserTokenDO;
import net.fnsco.api.doc.service.user.dao.helper.UserTokenProvider;

import java.util.List;;

public interface UserTokenDAO {

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "user_id",property = "userId"),@Result( column = "login_ip",property = "loginIp"),@Result( column = "expire_date",property = "expireDate") })
    @Select("SELECT * FROM t_user_token WHERE id = #{id}")
    public UserTokenDO getById(@Param("id") int id);

    @Insert("INSERT into t_user_token(id,create_date,modify_date,user_id,login_ip,token,expire_date) VALUES (#{id},#{createDate},#{modifyDate},#{userId},#{loginIp},#{token},#{expireDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(UserTokenDO userToken);

    @Delete("DELETE FROM t_user_token WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = UserTokenProvider.class, method = "update")
    public int update(@Param("userToken") UserTokenDO  userToken);

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "user_id",property = "userId"),@Result( column = "login_ip",property = "loginIp"),@Result( column = "expire_date",property = "expireDate") })
    @SelectProvider(type = UserTokenProvider.class, method = "pageList")
    public List<UserTokenDO> pageList(@Param("userToken") UserTokenDO userToken, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = UserTokenProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("userToken") UserTokenDO userToken);

}