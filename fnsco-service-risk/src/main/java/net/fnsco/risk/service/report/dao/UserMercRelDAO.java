package net.fnsco.risk.service.report.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.risk.service.report.entity.UserMercRelDO;
import net.fnsco.risk.service.report.dao.helper.UserMercRelProvider;

import java.util.List;;

public interface UserMercRelDAO {

    @Results({@Result( column = "web_user_outer_id",property = "webUserOuterId"),@Result( column = "inner_code",property = "innerCode") })
    @Select("SELECT * FROM risk_user_merc_rel WHERE id = #{id}")
    public UserMercRelDO getById(@Param("id") int id);

    //根据innerCode查询风控信息
    @Results({@Result( column = "web_user_outer_id",property = "webUserOuterId"),@Result( column = "inner_code",property = "innerCode") })
    @Select("SELECT * FROM risk_user_merc_rel WHERE inner_code = #{innerCode}")
    public UserMercRelDO getByInnerCode(@Param("innerCode") String innerCode);

    
    @Insert("INSERT into risk_user_merc_rel(id,web_user_outer_id,inner_code) VALUES (#{id},#{webUserOuterId},#{innerCode})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(UserMercRelDO userMercRel);

    @Delete("DELETE FROM risk_user_merc_rel WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = UserMercRelProvider.class, method = "update")
    public int update(@Param("userMercRel") UserMercRelDO  userMercRel);

    @Results({@Result( column = "web_user_outer_id",property = "webUserOuterId"),@Result( column = "inner_code",property = "innerCode") })
    @SelectProvider(type = UserMercRelProvider.class, method = "pageList")
    public List<UserMercRelDO> pageList(@Param("userMercRel") UserMercRelDO userMercRel, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = UserMercRelProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("userMercRel") UserMercRelDO userMercRel);

}