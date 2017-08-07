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
import net.fnsco.api.doc.service.user.entity.UserCubeDO;
import net.fnsco.api.doc.service.user.dao.helper.UserCubeProvider;

import java.util.List;;

public interface UserCubeDAO {

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "total_regist_count",property = "totalRegistCount"),@Result( column = "day_regist_count",property = "dayRegistCount"),@Result( column = "day_login_count",property = "dayLoginCount"),@Result( column = "day_old_login_count",property = "dayOldLoginCount"),@Result( column = "total_proj_count",property = "totalProjCount"),@Result( column = "day_proj_count",property = "dayProjCount") })
    @Select("SELECT * FROM t_user_cube WHERE id = #{id}")
    public UserCubeDO getById(@Param("id") int id);

    @Insert("INSERT into t_user_cube(id,create_date,modify_date,total_regist_count,day_regist_count,day_login_count,day_old_login_count,total_proj_count,day_proj_count) VALUES (#{id},#{createDate},#{modifyDate},#{totalRegistCount},#{dayRegistCount},#{dayLoginCount},#{dayOldLoginCount},#{totalProjCount},#{dayProjCount})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(UserCubeDO userCube);

    @Delete("DELETE FROM t_user_cube WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = UserCubeProvider.class, method = "update")
    public int update(@Param("userCube") UserCubeDO  userCube);

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "total_regist_count",property = "totalRegistCount"),@Result( column = "day_regist_count",property = "dayRegistCount"),@Result( column = "day_login_count",property = "dayLoginCount"),@Result( column = "day_old_login_count",property = "dayOldLoginCount"),@Result( column = "total_proj_count",property = "totalProjCount"),@Result( column = "day_proj_count",property = "dayProjCount") })
    @SelectProvider(type = UserCubeProvider.class, method = "pageList")
    public List<UserCubeDO> pageList(@Param("userCube") UserCubeDO userCube, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = UserCubeProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("userCube") UserCubeDO userCube);

}