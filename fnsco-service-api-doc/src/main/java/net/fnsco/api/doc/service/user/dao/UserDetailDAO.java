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
import net.fnsco.api.doc.service.user.entity.UserDetailDO;
import net.fnsco.api.doc.service.user.dao.helper.UserDetailProvider;

import java.util.List;;

public interface UserDetailDAO {

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "user_id",property = "userId"),@Result( column = "area_id",property = "areaId"),@Result( column = "nick_name",property = "nickName"),@Result( column = "zip_code",property = "zipCode"),@Result( column = "head_url",property = "headUrl") })
    @Select("SELECT * FROM t_user_detail WHERE id = #{id}")
    public UserDetailDO getById(@Param("id") int id);
    
    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "user_id",property = "userId"),@Result( column = "area_id",property = "areaId"),@Result( column = "nick_name",property = "nickName"),@Result( column = "zip_code",property = "zipCode"),@Result( column = "head_url",property = "headUrl") })
    @Select("SELECT * FROM t_user_detail WHERE user_id = #{iuserIdd}")
    public UserDetailDO getByUserId(@Param("userId") Long userId);
    
    @Insert("INSERT into t_user_detail(id,create_date,modify_date,user_id,address,area_id,birth,country,gender,name,nick_name,zip_code,head_url,university) VALUES (#{id},#{createDate},#{modifyDate},#{userId},#{address},#{areaId},#{birth},#{country},#{gender},#{name},#{nickName},#{zipCode},#{headUrl},#{university})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(UserDetailDO userDetail);

    @Delete("DELETE FROM t_user_detail WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = UserDetailProvider.class, method = "update")
    public int update(@Param("userDetail") UserDetailDO  userDetail);

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "user_id",property = "userId"),@Result( column = "area_id",property = "areaId"),@Result( column = "nick_name",property = "nickName"),@Result( column = "zip_code",property = "zipCode"),@Result( column = "head_url",property = "headUrl") })
    @SelectProvider(type = UserDetailProvider.class, method = "pageList")
    public List<UserDetailDO> pageList(@Param("userDetail") UserDetailDO userDetail, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = UserDetailProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("userDetail") UserDetailDO userDetail);

}