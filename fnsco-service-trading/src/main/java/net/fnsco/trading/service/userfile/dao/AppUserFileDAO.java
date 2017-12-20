package net.fnsco.trading.service.userfile.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.trading.service.userfile.entity.AppUserFileDO;
import net.fnsco.trading.service.userfile.dao.helper.AppUserFileProvider;

import java.util.List;;

public interface AppUserFileDAO {

    @Results({@Result( column = "app_user_id",property = "appUserId"),@Result( column = "file_name",property = "fileName"),@Result( column = "file_type",property = "fileType"),@Result( column = "file_path",property = "filePath"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM u_app_user_file WHERE id = #{id}")
    public AppUserFileDO getById(@Param("id") int id);

    @Insert("INSERT into u_app_user_file(id,app_user_id,file_name,file_type,file_path,create_time) VALUES (#{id},#{appUserId},#{fileName},#{fileType},#{filePath},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(AppUserFileDO appUserFile);

    @Delete("DELETE FROM u_app_user_file WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = AppUserFileProvider.class, method = "update")
    public int update(@Param("appUserFile") AppUserFileDO  appUserFile);

    @Results({@Result( column = "app_user_id",property = "appUserId"),@Result( column = "file_name",property = "fileName"),@Result( column = "file_type",property = "fileType"),@Result( column = "file_path",property = "filePath"),@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = AppUserFileProvider.class, method = "pageList")
    public List<AppUserFileDO> pageList(@Param("appUserFile") AppUserFileDO appUserFile, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = AppUserFileProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("appUserFile") AppUserFileDO appUserFile);

}