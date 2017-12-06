package net.fnsco.bigdata.service.sys.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import net.fnsco.bigdata.service.sys.dao.helper.AppAdProvider;
import net.fnsco.bigdata.service.sys.entity.AppAdDO;

import java.util.List;;

public interface AppAdDAO {

    @Results({@Result( column = "fileName",property = "filename"),@Result( column = "filePath",property = "filepath"),@Result( column = "createTime",property = "createtime"),@Result( column = "updateTime",property = "updatetime"),@Result( column = "createUserId",property = "createuserid") })
    @Select("SELECT * FROM sys_app_ad WHERE id = #{id}")
    public AppAdDO getById(@Param("id") int id);

    @Insert("INSERT into sys_app_ad(id,fileName,filePath,category,content,createTime,updateTime,createUserId) VALUES (#{id},#{filename},#{filepath},#{category},#{content},#{createtime},#{updatetime},#{createuserid})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(AppAdDO appAd);

    @Delete("DELETE FROM sys_app_ad WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = AppAdProvider.class, method = "update")
    public int update(@Param("appAd") AppAdDO  appAd);

    @Results({@Result( column = "fileName",property = "filename"),@Result( column = "filePath",property = "filepath"),@Result( column = "createTime",property = "createtime"),@Result( column = "updateTime",property = "updatetime"),@Result( column = "createUserId",property = "createuserid") })
    @SelectProvider(type = AppAdProvider.class, method = "pageList")
    public List<AppAdDO> pageList(@Param("appAd") AppAdDO appAd, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = AppAdProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("appAd") AppAdDO appAd);

}