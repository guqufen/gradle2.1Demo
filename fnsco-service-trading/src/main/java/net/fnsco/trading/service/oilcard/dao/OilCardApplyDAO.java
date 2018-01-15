package net.fnsco.trading.service.oilcard.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.trading.service.oilcard.entity.OilCardApplyDO;
import net.fnsco.trading.service.oilcard.dao.helper.OilCardApplyProvider;

import java.util.List;;

public interface OilCardApplyDAO {

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "create_time",property = "createTime") })
    @Select("SELECT * FROM act_oil_card_apply WHERE id = #{id}")
    public OilCardApplyDO getById(@Param("id") int id);

    @Insert("INSERT into act_oil_card_apply(id,inner_code,mobile,name,create_time) VALUES (#{id},#{innerCode},#{mobile},#{name},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(OilCardApplyDO oilCardApply);

    @Delete("DELETE FROM act_oil_card_apply WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = OilCardApplyProvider.class, method = "update")
    public int update(@Param("oilCardApply") OilCardApplyDO  oilCardApply);

    @Results({@Result( column = "inner_code",property = "innerCode"),@Result( column = "create_time",property = "createTime") })
    @SelectProvider(type = OilCardApplyProvider.class, method = "pageList")
    public List<OilCardApplyDO> pageList(@Param("oilCardApply") OilCardApplyDO oilCardApply, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = OilCardApplyProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("oilCardApply") OilCardApplyDO oilCardApply);

}