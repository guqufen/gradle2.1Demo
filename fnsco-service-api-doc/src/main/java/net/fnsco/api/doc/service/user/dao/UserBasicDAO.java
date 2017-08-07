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
import net.fnsco.api.doc.service.user.entity.UserBasicDO;
import net.fnsco.api.doc.service.user.dao.helper.UserBasicProvider;

import java.util.List;;

public interface UserBasicDAO {

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "locked_date",property = "lockedDate"),@Result( column = "register_ip",property = "registerIp") })
    @Select("SELECT * FROM t_user_basic WHERE id = #{id}")
    public UserBasicDO getById(@Param("id") int id);

    @Insert("INSERT into t_user_basic(id,create_date,modify_date,phone,email,password,valid,role,locked,locked_date,register_ip) VALUES (#{id},#{createDate},#{modifyDate},#{phone},#{email},#{password},#{valid},#{role},#{locked},#{lockedDate},#{registerIp})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(UserBasicDO userBasic);

    @Delete("DELETE FROM t_user_basic WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = UserBasicProvider.class, method = "update")
    public int update(@Param("userBasic") UserBasicDO  userBasic);

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "locked_date",property = "lockedDate"),@Result( column = "register_ip",property = "registerIp") })
    @SelectProvider(type = UserBasicProvider.class, method = "pageList")
    public List<UserBasicDO> pageList(@Param("userBasic") UserBasicDO userBasic, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = UserBasicProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("userBasic") UserBasicDO userBasic);

}