package net.fnsco.order.service.act.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.order.service.act.entity.ApplyUserDO;
import net.fnsco.order.service.act.dao.helper.ApplyUserProvider;

import java.util.List;;

public interface ApplyUserDAO {

    @Results({@Result( column = "user_name",property = "userName"),@Result( column = "contact_num",property = "contactNum"),@Result( column = "merc_name",property = "mercName"),@Result( column = "from_user_name",property = "fromUserName"),@Result( column = "from_user_contact_num",property = "fromUserContactNum") })
    @Select("SELECT * FROM act_apply_user WHERE id = #{id}")
    public ApplyUserDO getById(@Param("id") int id);

    @Insert("INSERT into act_apply_user(id,user_name,contact_num,merc_name,from_user_name,from_user_contact_num) VALUES (#{id},#{userName},#{contactNum},#{mercName},#{fromUserName},#{fromUserContactNum})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(ApplyUserDO applyUser);

    @Delete("DELETE FROM act_apply_user WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = ApplyUserProvider.class, method = "update")
    public int update(@Param("applyUser") ApplyUserDO  applyUser);

    @Results({@Result( column = "user_name",property = "userName"),@Result( column = "contact_num",property = "contactNum"),@Result( column = "merc_name",property = "mercName"),@Result( column = "from_user_name",property = "fromUserName"),@Result( column = "from_user_contact_num",property = "fromUserContactNum") })
    @SelectProvider(type = ApplyUserProvider.class, method = "pageList")
    public List<ApplyUserDO> pageList(@Param("applyUser") ApplyUserDO applyUser, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = ApplyUserProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("applyUser") ApplyUserDO applyUser);

}