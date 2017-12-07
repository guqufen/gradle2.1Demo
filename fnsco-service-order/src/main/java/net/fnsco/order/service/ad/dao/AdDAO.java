package net.fnsco.order.service.ad.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.order.service.ad.dao.helper.AdProvider;
import net.fnsco.order.service.ad.entity.AdDO;

import java.util.List;;

public interface AdDAO {

    @Results({@Result( column = "img_path",property = "imgPath"),@Result( column = "create_time",property = "createTime"),@Result( column = "update_time",property = "updateTime"),@Result( column = "create_user_id",property = "createUserId") })
    @Select("SELECT * FROM sys_ad WHERE id = #{id}")
    public AdDO getById(@Param("id") int id);

    @Insert("INSERT into sys_ad(id,title,img_path,category,summary,content,create_time,update_time,create_user_id) VALUES (#{id},#{title},#{imgPath},#{category},#{summary},#{content},#{createTime},#{updateTime},#{createUserId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(AdDO ad);

    @Delete("DELETE FROM sys_ad WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = AdProvider.class, method = "update")
    public int update(@Param("ad") AdDO  ad);

    @Results({@Result( column = "img_path",property = "imgPath"),@Result( column = "create_time",property = "createTime"),@Result( column = "update_time",property = "updateTime"),@Result( column = "create_user_id",property = "createUserId") })
    @SelectProvider(type = AdProvider.class, method = "pageList")
    public List<AdDO> pageList(@Param("ad") AdDO ad, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = AdProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("ad") AdDO ad);
    
    
    @Select("SELECT * FROM sys_ad")
	public List<AdDO> queryAdList();

}