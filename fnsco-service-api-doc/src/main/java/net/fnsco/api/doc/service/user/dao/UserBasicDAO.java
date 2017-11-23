package net.fnsco.api.doc.service.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.fnsco.api.doc.service.user.dao.helper.UserBasicProvider;
import net.fnsco.api.doc.service.user.entity.UserBasicDO;;

public interface UserBasicDAO {

    @Results({ @Result(column = "create_date", property = "createDate"), @Result(column = "modify_date", property = "modifyDate"), @Result(column = "locked_date", property = "lockedDate"),
               @Result(column = "register_ip", property = "registerIp") })
    @Select("SELECT * FROM t_user_basic WHERE id = #{id}")
    public UserBasicDO getById(@Param("id") Long id);

    @Select("SELECT count(id) FROM t_user_basic ")
    public int countTotalRegist();
    
    @Select("SELECT email FROM t_user_basic ")
    public List<String> queryEmail();

    @Select(" select b.id as user_id,b.email,b.role,b.valid,b.locked," + "b.create_date as regist_date,d.nick_name,d.head_url " + "from t_user_basic b inner join t_user_detail d on b.id = d.user_id "
            + "where b.id = #{userId}")
    public Map getDetailByUserId(@Param("userId") Integer userId);

    @Select("SELECT count(1) FROM t_user_basic WHERE email = #{email} and id != #{exceptId}")
    public int countByEmail(@Param("email") String email, @Param("exceptId") Integer exceptId);

    @Results({ @Result(column = "create_date", property = "createDate"), @Result(column = "modify_date", property = "modifyDate"), @Result(column = "locked_date", property = "lockedDate"),
               @Result(column = "register_ip", property = "registerIp") })
    @Select("SELECT * FROM t_user_basic WHERE email = #{email}")
    public UserBasicDO getByEmail(@Param("email") String email);

    @Insert("INSERT into t_user_basic(id,create_date,modify_date,phone,email,password,valid,role,locked,locked_date,register_ip) VALUES (#{id},#{createDate},#{modifyDate},#{phone},#{email},#{password},#{valid},#{role},#{locked},#{lockedDate},#{registerIp})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(UserBasicDO userBasic);

    @Delete("DELETE FROM t_user_basic WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = UserBasicProvider.class, method = "update")
    public int update(@Param("userBasic") UserBasicDO userBasic);

    @Results({ @Result(column = "create_date", property = "createDate"), @Result(column = "modify_date", property = "modifyDate"), @Result(column = "locked_date", property = "lockedDate"),
               @Result(column = "register_ip", property = "registerIp") })
    @SelectProvider(type = UserBasicProvider.class, method = "pageList")
    public List<UserBasicDO> pageList(@Param("userBasic") UserBasicDO userBasic, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = UserBasicProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("userBasic") UserBasicDO userBasic);

}