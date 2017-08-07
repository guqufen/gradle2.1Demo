package net.fnsco.api.doc.service.other.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.api.doc.service.other.entity.SuggestDO;
import net.fnsco.api.doc.service.other.dao.helper.SuggestProvider;

import java.util.List;;

public interface SuggestDAO {

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "user_id",property = "userId"),@Result( column = "deal_date",property = "dealDate") })
    @Select("SELECT * FROM t_suggest WHERE id = #{id}")
    public SuggestDO getById(@Param("id") int id);

    @Insert("INSERT into t_suggest(id,create_date,modify_date,user_id,title,content,deal_date,status,type) VALUES (#{id},#{createDate},#{modifyDate},#{userId},#{title},#{content},#{dealDate},#{status},#{type})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(SuggestDO suggest);

    @Delete("DELETE FROM t_suggest WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = SuggestProvider.class, method = "update")
    public int update(@Param("suggest") SuggestDO  suggest);

    @Results({@Result( column = "create_date",property = "createDate"),@Result( column = "modify_date",property = "modifyDate"),@Result( column = "user_id",property = "userId"),@Result( column = "deal_date",property = "dealDate") })
    @SelectProvider(type = SuggestProvider.class, method = "pageList")
    public List<SuggestDO> pageList(@Param("suggest") SuggestDO suggest, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = SuggestProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("suggest") SuggestDO suggest);

}