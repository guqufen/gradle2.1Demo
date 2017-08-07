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
import net.fnsco.api.doc.service.user.entity.UserExtDO;
import net.fnsco.api.doc.service.user.dao.helper.UserExtProvider;

import java.util.List;;

public interface UserExtDAO {

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "user_id",property = "userId"),@Result( column = "last_fetch_sys_msg_date",property = "lastFetchSysMsgDate") })
    @Select("SELECT * FROM t_user_ext WHERE id = #{id}")
    public UserExtDO getById(@Param("id") int id);

    @Insert("INSERT into t_user_ext(id,create_date,modify_date,user_id,last_fetch_sys_msg_date) VALUES (#{id},#{createDate},#{modifyDate},#{userId},#{lastFetchSysMsgDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(UserExtDO userExt);

    @Delete("DELETE FROM t_user_ext WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = UserExtProvider.class, method = "update")
    public int update(@Param("userExt") UserExtDO  userExt);

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "user_id",property = "userId"),@Result( column = "last_fetch_sys_msg_date",property = "lastFetchSysMsgDate") })
    @SelectProvider(type = UserExtProvider.class, method = "pageList")
    public List<UserExtDO> pageList(@Param("userExt") UserExtDO userExt, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = UserExtProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("userExt") UserExtDO userExt);

}