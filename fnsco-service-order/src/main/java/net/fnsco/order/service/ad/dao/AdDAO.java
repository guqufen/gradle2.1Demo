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

	@Results({ @Result(column = "img_path", property = "img_path"),
			@Result(column = "create_time", property = "createTime"),
			@Result(column = "update_time", property = "updateTime"),
			@Result(column = "create_user_id", property = "createUserId"),
			@Result(column = "url", property = "url"),
			@Result(column = "type", property = "type"),
			@Result(column = "deviceType", property = "deviceType")})
	@Select("SELECT * FROM sys_ad WHERE id = #{id}")
	public AdDO getById(@Param("id") int id);

	@Insert("INSERT into sys_ad(id,title,img_path,category,summary,content,create_time,update_time,create_user_id,url,type,device_type) VALUES (#{id},#{title},#{img_path},#{category},#{summary},#{content},#{createTime},#{updateTime},#{createUserId},#{url},#{type},#{deviceType})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insert(AdDO ad);

	@Delete("DELETE FROM sys_ad WHERE id = #{id}")
	public int deleteById(@Param("id") int id);

	@UpdateProvider(type = AdProvider.class, method = "update")
	public int update(@Param("ad") AdDO ad);

	@Results({ @Result(column = "id", property = "id"),
			@Result(column = "title", property = "title"),
			@Result(column = "img_path", property = "img_path"),
			@Result(column = "url", property = "url"),
			@Result(column = "category", property = "category"),
			@Result(column = "summary", property = "summary"),
			@Result(column = "create_time", property = "createTime"),
			@Result(column = "update_time", property = "updateTime"),
			@Result(column = "create_user_id", property = "createUserId"),
			@Result(column = "type", property = "type"),
			@Result(column = "device_type", property = "deviceType")})
	@SelectProvider(type = AdProvider.class, method = "pageList")
	public List<AdDO> pageList(@Param("ad") AdDO ad, @Param("pageNum") Integer pageNum,
			@Param("pageSize") Integer pageSize);

	@SelectProvider(type = AdProvider.class, method = "pageListCount")
	public Integer pageListCount(@Param("ad") AdDO ad);

	/**
	 * app接口获取广告资讯 queryAdList:(这里用一句话描述这个方法的作用)
	 *
	 * @param @return
	 *            设定文件
	 * @return List<AdDO> DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	@Select("SELECT * FROM sys_ad Where type = #{type} AND device_type=#{deviceType} ORDER BY create_time DESC LIMIT #{count}")
	public List<AdDO> queryAdList(@Param("type")Integer type,@Param("deviceType")Integer deviceType,@Param("count")Integer count);

}